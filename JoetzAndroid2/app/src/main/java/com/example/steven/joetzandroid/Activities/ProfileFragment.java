package com.example.steven.joetzandroid.Activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.steven.joetzandroid.Adapters.KindAdapter;
import com.example.steven.joetzandroid.Domain.Contact;
import com.example.steven.joetzandroid.Domain.Kind;
import com.example.steven.joetzandroid.Domain.Ouder;
import com.example.steven.joetzandroid.R;
import com.example.steven.joetzandroid.firebase.FirebaseAuth;
import com.example.steven.joetzandroid.firebase.FirebaseProfile;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";
    private Ouder ouder;
    private EditText txtVoornaam;
    private EditText txtNaam;
    private EditText txtNr;
    private EditText txtPostcode;
    private EditText txtStraat;
    private EditText txtGemeente;
    private EditText txtEmail;
    private EditText txtWebsite;
    private EditText txtTelefoon;
    private EditText txtInfo;
    private FirebaseProfile frbProfile;
    private ListView kinderen;
    private Button btnOpslaan;


    //dummiedata

    private FirebaseAuth auth = new FirebaseAuth();

    public void setFirebaseAuth(FirebaseAuth auth){
        this.auth = auth;
    }

    public FirebaseAuth getFirebaseAuth(){
        return auth;
    }

    public void setOuder(Ouder ouder){
        this.ouder = ouder;
    }

    public Ouder getOuder(){return this.ouder;}

    public ProfileFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, ouder.toString());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        frbProfile = new FirebaseProfile(this.auth);
        loadElementen(view);
        listenersElementen();
        KindAdapter adapter = new KindAdapter(container.getContext(), ouder.getKinderen());
        kinderen.setAdapter(adapter);

        this.setOuder(frbProfile.getOuder());
        btnOpslaan.setOnClickListener(onKlikOpslaan);

        kinderen.setOnItemClickListener(onItemKlik);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private AdapterView.OnItemClickListener onItemKlik = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Kind k = (Kind)kinderen.getAdapter().getItem(i);
            if (k.getOuder() == null){
                k = new Kind();
                k.setOuder(ouder);
            }
            KindFragment kindFragment = new KindFragment();
            kindFragment.setKind(k);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.frame_profile_kind, kindFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    };

    private View.OnClickListener onKlikOpslaan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listenersElementen();
            frbProfile.updateOuder(ouder);
            frbProfile.updateOuderContact(ouder);







        }
    };

    public void listenersElementen(){
        txtNaam.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                ouder.setLastName(txtNaam.getText().toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        txtVoornaam.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                ouder.setFirstName(txtVoornaam.getText().toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        txtTelefoon.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                ouder.getContact().setTelnr(txtTelefoon.getText().toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        txtEmail.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                ouder.setEmail(txtEmail.getText().toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        txtGemeente.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                ouder.getContact().getAdres().setGemeente(txtGemeente.getText().toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        txtInfo.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                ouder.getContact().setInfo(txtInfo.getText().toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        txtStraat.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                ouder.getContact().getAdres().setStraat(txtStraat.getText().toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        txtPostcode.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                ouder.getContact().getAdres().setPostcode(Integer.parseInt(txtPostcode.getText().toString()));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        txtNr.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                ouder.getContact().getAdres().setNummer(Integer.parseInt(txtNr.getText().toString()));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        txtWebsite.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                ouder.getContact().setWebsite(txtWebsite.getText().toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
    }


    public void loadElementen(View view){
        txtNaam = (EditText)view.findViewById(R.id.profileEditTextNaam);
        txtVoornaam = (EditText)view.findViewById(R.id.profileEditTextVoornaam);
        txtNr = (EditText)view.findViewById(R.id.profileEditTextNr);
        txtPostcode = (EditText)view.findViewById(R.id.profileEditTextPostcode);
        txtStraat = (EditText)view.findViewById(R.id.profileEditTextStraat);
        txtGemeente = (EditText)view.findViewById(R.id.profileEditTextGemeente);
        txtEmail = (EditText)view.findViewById(R.id.profileEditTextEmail);
        txtWebsite = (EditText)view.findViewById(R.id.profileEditTextWebsite);
        txtTelefoon = (EditText)view.findViewById(R.id.profileEditTextTelefoon);
        txtInfo = (EditText)view.findViewById(R.id.profileEditTextInfo);
        kinderen = (ListView)view.findViewById(R.id.profileListKinderen);
        btnOpslaan = (Button)view.findViewById(R.id.profileButtonOpslaan);
        txtNaam.setText(ouder.getLastName());
        txtVoornaam.setText(ouder.getFirstName());
        txtNr.setText(ouder.getContact().getAdres().getNummer());
        txtStraat.setText(ouder.getContact().getAdres().getStraat());
        txtGemeente.setText(ouder.getContact().getAdres().getGemeente());
        txtPostcode.setText(ouder.getContact().getAdres().getPostcode());
        txtEmail.setText(ouder.getContact().getEmail());
        txtWebsite.setText(ouder.getContact().getWebsite());
        txtTelefoon.setText(ouder.getContact().getTelnr());
        txtInfo.setText(ouder.getContact().getInfo());
    }


}
