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
import com.example.steven.joetzandroid.firebase.FirebaseAuth;
import com.example.steven.joetzandroid.firebase.FirebaseProfile;
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
    private static final String TAG = "RegisterFragment";
    private EditText naamEditText;
    private EditText voornaamEditText;
    private EditText emailEditText;
    private EditText paswoordEditText;
    private EditText paswoord2EditText;
    private Button cancelButton;
    private Button registerButton;

    private HashMap<String,EditText> editTexts;
    private EditText foutText;
    private FirebaseAuth auth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = new FirebaseAuth();
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
        emailEditText = (EditText)view.findViewById(R.id.emailEditTextRegis);
        editTexts.put("e-mail",emailEditText);
        paswoordEditText = (EditText)view.findViewById(R.id.passwordEditText);
        editTexts.put("paswoord",paswoordEditText);
        paswoord2EditText = (EditText)view.findViewById(R.id.passwordReEditText);
        editTexts.put("herhaal paswoord",paswoord2EditText);
        cancelButton = (Button)view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDestroyView();
            }
        });
        registerButton = (Button)view.findViewById(R.id.registerButtonReg);
        foutText = (EditText)view.findViewById(R.id.foutText);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerButton.setTextColor(getResources().getColor(R.color.list_background_pressed));

                if(foutGegevens())
                {
                    createUser();
                }

                else
                {
                    setFoutText();
                }


            }
        });
        return view;
    }
    private boolean passwoordVergelijking()
    {
        return paswoordEditText.getText().toString().equals(paswoord2EditText.getText().toString());
    }
    private ArrayList<String> foutM;
    private boolean foutGegevens()
    {
        foutM = new ArrayList<String>();
        for(Map.Entry<String, EditText> entry : editTexts.entrySet()) {
            if (entry.getValue().getText().toString().isEmpty()) {

                foutM.add(entry.getKey()+" mag niet leeg zijn");
            }
            if (entry.getKey() == "e-mail")
            {
                if(!emailCheck(entry.getValue().getText().toString()))
                {
                    foutM.add("E-mail patroon niet correct (voorbeeld@comp.be)");
                }
            }
            if (!passwoordVergelijking()) {
                foutM.add("Paswoorden komen niet overeen.");

                paswoordEditText.setText("");
                paswoord2EditText.setText("");
            }
        }
            return foutM.size()==0;
    }

    private Ouder getOuder()
    {
        Ouder ouder = new Ouder(emailEditText.getText().toString(),paswoordEditText.getText().toString(),naamEditText.getText().toString(),voornaamEditText.getText().toString());

        return ouder;
    }

    private void createUser(){

        Ouder ouder = getOuder();

        auth.registerUser(ouder.getEmail(),ouder.getPassword());
        auth.createUserProfileName(ouder);

    }
    private void setFoutText()
    {
        String fout = "";
        for(String s : foutM)
        {
            fout += s;
            fout += "\n";
        }
        foutText.setText(fout);
    }

    private boolean emailCheck(String email)
    {
        if(email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
        {
            return true;
        }
        return false;
    }





}
