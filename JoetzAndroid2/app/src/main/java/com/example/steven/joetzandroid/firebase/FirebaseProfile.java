package com.example.steven.joetzandroid.firebase;

import android.util.Log;

import com.example.steven.joetzandroid.Domain.Contact;
import com.example.steven.joetzandroid.Domain.Kind;
import com.example.steven.joetzandroid.Domain.Ouder;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Steven on 13/11/14.
 */
public class FirebaseProfile implements Firebase.CompletionListener{

    private final Firebase ref = new Firebase("https://mobileone.firebaseio.com/profile");
    private static final String TAG = "FireBaseProfile";
    private FirebaseAuth auth;
    private Boolean completed;

    private Ouder ouder;

    public FirebaseProfile(FirebaseAuth auth)
    {
        this.auth = auth;
        this.completed = false;
        this.ouder = new Ouder();
        setOuderIdFromAuth();
        makeProfileFromAuth();

    }
    public FirebaseProfile()
    {
        this.auth = new FirebaseAuth();
        this.completed = false;
        this.ouder = new Ouder();
        setOuderIdFromAuth();
        makeProfileFromAuth();
    }

    private void setOuderIdFromAuth()
    {
        if(auth.getUser().getUid() != null)
        {
            ouder.setId(auth.getUser().getUid());

        }

    }

    public void makeProfileFromAuth()
    {


        if(auth.getUser()!=null)
        {
            Firebase childref = ref.child(auth.getUser().getUid());

            childref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d(TAG,dataSnapshot.getValue().toString());
                    Map<String,Object> map = (Map<String,Object>)dataSnapshot.getValue();
                    translateHashMap(map,auth.getUser().getUid());
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Log.d(TAG,"The read failed : "+firebaseError);
                }
            });

        }


    }

    public boolean updateOuder(Ouder ouder)
    {
        Firebase ouderRef = ref.child(ouder.getId());
        ouderRef.setValue(ouder.ouderToHashMap(),this);
        return completed;
    }

    public boolean updateOuderContact(Ouder ouder)
    {
        Firebase ouderRef = ref.child(ouder.getId());
        ouderRef.setValue(ouder.ouderContactToHashMap(),this);
        return completed;
    }

    public boolean updateKind(Kind kind){


        Firebase kindRef = ref.child(kind.getOuder().getId()).child(kind.getId());
        kindRef.setValue(kind.kindToHashMap(),this);
        return completed;
    }

    public boolean updateKindAdres(Kind kind)
    {
        Firebase kindRef = ref.child(kind.getOuder().getId()).child(kind.getId());
        kindRef.setValue(kind.kindAdresToHashMap(),this);
        return completed;
    }

    private Ouder translateHashMap(Map<String,Object>snapshot,String id)
    {
        if(ouder.getId().isEmpty())
        {
            ouder.setId(id);
        }

        Contact contact = new Contact();

        ouder.setContact(contact);
        for(Map.Entry<String,Object> entry : snapshot.entrySet())
        {
            if(entry.getKey()=="naam")
            {
                ouder.setLastName(entry.getValue().toString());
                Log.d(TAG,ouder.getLastName());
            }
            if(entry.getKey()=="voornaam")
            {
                ouder.setFirstName(entry.getValue().toString());
               Log.d(TAG,ouder.getFirstName());
            }
            if(entry.getKey()=="gemeente")
            {
                contact.getAdres().setGemeente(entry.getValue().toString());
            }
            if(entry.getKey()=="straat")
            {
                contact.getAdres().setStraat(entry.getValue().toString());
            }
            if(entry.getKey()=="nummer")
            {
                contact.getAdres().setNummer(Integer.valueOf(entry.getValue().toString()));
            }
            if(entry.getKey() == "postcode")
            {
                contact.getAdres().setPostcode(Integer.valueOf(entry.getValue().toString()));
            }
            if(entry.getKey()=="email")
            {
                contact.setEmail(entry.getValue().toString());
            }
        }
        return ouder;
    }

    public Ouder getOuder()
    {
        return this.ouder;
    }
    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
        if (firebaseError != null)
        {
            Log.d(TAG,"Firebase error : "+firebaseError.getMessage());
            completed = false;
        }
        else
        {
            completed = true;
        }
    }
}
