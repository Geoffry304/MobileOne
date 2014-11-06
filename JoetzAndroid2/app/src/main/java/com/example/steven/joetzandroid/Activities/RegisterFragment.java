package com.example.steven.joetzandroid.Activities;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.steven.joetzandroid.Domain.Adres;
import com.example.steven.joetzandroid.Domain.Contact;
import com.example.steven.joetzandroid.Domain.Ouder;
import com.example.steven.joetzandroid.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private EditText naamEditText;
    private EditText voornaamEditText;
    private EditText straatEditText;
    private EditText nummerEditText;
    private EditText gemeenteEditText;
    private EditText postCodeEditText;
    private EditText telnrEditText;
    private EditText gsmEditText;
    private EditText emailEditText;
    private EditText paswoordEditText;
    private EditText paswoord2EditText;
    private Button cancelButton;
    private Button registerButton;
    private Firebase ref;
    private HashMap<String,EditText> editTexts;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ref = new Firebase("https://mobileone.firebaseio.com/");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        editTexts = new HashMap<String, EditText>();
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        naamEditText = (EditText)view.findViewById(R.id.naamEditText);
        editTexts.put("naam", naamEditText);
        voornaamEditText = (EditText)view.findViewById(R.id.voornNaamEditText);
        editTexts.put("voornaam",voornaamEditText);
        straatEditText = (EditText)view.findViewById(R.id.straatEditText);
        editTexts.put("straat",straatEditText);
        nummerEditText = (EditText)view.findViewById(R.id.nummerEditText);
        editTexts.put("nummer",nummerEditText);
        gemeenteEditText = (EditText)view.findViewById(R.id.gemeenteEditText);
        editTexts.put("gemeente",gemeenteEditText);
        postCodeEditText = (EditText)view.findViewById(R.id.postcodeEditText);
        editTexts.put("postcode",postCodeEditText);
        telnrEditText = (EditText)view.findViewById(R.id.telefoonEditText);
        editTexts.put("telnr",telnrEditText);
        gsmEditText = (EditText)view.findViewById(R.id.gsmEditText);
        editTexts.put("gsm",gsmEditText);
        emailEditText = (EditText)view.findViewById(R.id.emailEditTextRegis);
        editTexts.put("e-mail",emailEditText);
        paswoordEditText = (EditText)view.findViewById(R.id.passwordEditText);
        editTexts.put("paswoord",paswoordEditText);
        paswoord2EditText = (EditText)view.findViewById(R.id.passwordReEditText);
        editTexts.put("herhaal paswoord",paswoord2EditText);
        cancelButton = (Button)view.findViewById(R.id.cancelButton);
        registerButton = (Button)view.findViewById(R.id.registerButtonReg);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    createUser();
            }
        });
        return view;
    }
    private boolean passwoordVergelijking()
    {
        return paswoordEditText.getText().toString().equals(paswoord2EditText.getText().toString());
    }

    private boolean foutGegevens()
    {
        ArrayList<String> foutM = new ArrayList<String>();
        ArrayList<String> goed = new ArrayList<String>();
        for(Map.Entry<String, EditText> entry : editTexts.entrySet())
        {
            if(entry.getValue().getText().toString().isEmpty())
            {

                if(entry.getKey() == "gsm" || entry.getKey() == "telnr")
                {
                    if(goed.contains("telnr") || goed.contains("gsm"))
                    {

                    }



                        foutM.add(entry.getKey());

                }



                foutM.add(entry.getKey());
            }
            else if(entry.getKey() == "e-mail")
            {
                String email = entry.getValue().getText().toString();
                if(email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
                    {

                    }
                else
                {

                    foutM.add(entry.getKey());
                }
            }
            else if(entry.getKey() == "postcode")
            {
                int p = Integer.valueOf(entry.getValue().getText().toString());
                if(p<1000 || p>9999)
                {


                    foutM.add(entry.getKey());
                }
                else
                {
                    goed.add(entry.getKey());
                }
            }
            goed.add(entry.getKey());
        }
        if(!passwoordVergelijking())
        {
            foutM.add("Paswoorden komen niet overeen.");


            paswoordEditText.setText("");
            paswoord2EditText.setText("");
        }

        return foutM.isEmpty();
    }

    private Ouder getOuder()
    {
        Ouder ouder = new Ouder(emailEditText.getText().toString(),paswoordEditText.getText().toString(),naamEditText.getText().toString(),voornaamEditText.getText().toString());
        Adres adres = new Adres(straatEditText.getText().toString(),
                Integer.valueOf(nummerEditText.getText().toString()),
                Integer.valueOf(postCodeEditText.getText().toString()),
                gemeenteEditText.getText().toString());
        Contact contact = new Contact(adres,telnrEditText.getText().toString(),emailEditText.getText().toString());
        ouder.setContact(contact);
        return ouder;
    }

    private void createUser(){

       final Ouder ouder = getOuder();
        if(ref == null)
        {
            ref = new Firebase("https://mobileone.firebaseio.com/");
        }
        ref.createUser(ouder.getEmail(),ouder.getPassword(),new Firebase.ResultHandler(){


            @Override
            public void onSuccess() {
                ref.authWithPassword(ouder.getEmail(),ouder.getPassword(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Map<String,String>map = ouder.ouderToHashMap();
                        map.put("provider",authData.getProvider());
                        ref.child("profile").child(authData.getUid()).setValue(map);
                        Log.d("Auth",authData.getUid() + " "+ouder.getFirstName()+" werd toegevoegd");
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Log.d("Firebase",firebaseError.getMessage());
                    }
                });
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Log.d("Firebase",firebaseError.getMessage());
            }
        });

    }




}
