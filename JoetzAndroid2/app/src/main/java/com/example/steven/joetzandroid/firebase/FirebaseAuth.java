package com.example.steven.joetzandroid.firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.steven.joetzandroid.Domain.Ouder;
import com.facebook.Session;
import com.facebook.SessionState;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Steven on 12/11/14.
 */
public class FirebaseAuth {


    private static final Firebase ref = new Firebase("https://mobileone.firebaseio.com/");
    private static AuthData user;
    private final String TAG = "FirebaseAuth";

    public FirebaseAuth()
    {

    }

    public static AuthData getUser()
    {
            user = ref.getAuth();
            return user;
    }

    public boolean loginWithFacebook(final Session session, SessionState state, Exception exception)
    {
        if(state.isOpened())
        {
            ref.authWithOAuthToken("facebook", session.getAccessToken(), new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {

                    Log.d(TAG,authData.getProviderData().toString());
                    //Toast.makeText(getActivity().getApplicationContext(), "Facebook : ingelogd als =>" + authData.getProviderData().get("displayName").toString(), Toast.LENGTH_LONG).show();
                    user = authData;
                    HashMap<String,Object> fbMap = (HashMap<String,Object>) authData.getProviderData().get("cachedUserProfile");

                    Ouder ouder = new Ouder();

                    ouder.setFirstName((String)fbMap.get("first_name"));
                    ouder.setLastName((String)fbMap.get("last_name"));
                    ouder.setEmail((String)fbMap.get("email"));
                    ouder.setFoto(("http://graph.facebook.com/"+(fbMap.get("id"))+"/picture?type=large"));
                    checkIfUserHasProfile(user.getUid(),ouder);
                }

                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    //onLogged("Error "+firebaseError.getMessage(),false);
                    //Toast.makeText(getActivity().getApplicationContext(),firebaseError.getMessage(),Toast.LENGTH_LONG).show();
                    user = null;
                }
            });
        }
        else if(state.isClosed())
        {


        }

        return getUser() != null;
    }

    public void checkIfUserHasProfile(final String id, final Ouder ouder)
    {
        ref.child("profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(id))
                {
                    createUserProfileName(ouder);
                }
                else
                {
                    //updateUserProfile(ouder);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public boolean logIn(String email,String password)
    {

        ref.authWithPassword(email, password,
                new Firebase.AuthResultHandler() {

                    @Override
                    public void onAuthenticated(AuthData authData) {
                        user = authData;

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError error) {
                        user = null;
                        handleFirebaseError(error);
                    }
                });
        return getUser() != null;
    }
    public boolean registerUser(final String email, final String password)
    {
        ref.createUser(email,password, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                logIn(email,password);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                user = null;
               handleFirebaseError(firebaseError);


            }
        });
        return getUser() != null;
    }

    public boolean createUserProfileName(Ouder ouder)
    {
        if (getUser()!=null)
        {
            Map<String,String> map = ouder.ouderToHashMap();
            map.put("provider",user.getProvider());
            ref.child("profile").child(user.getUid()).setValue(map);
            Log.d("Auth",user.getUid() + " "+ouder.getFirstName()+" werd toegevoegd");
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean updateUserProfile(Ouder ouder)
    {
        if(getUser()!= null)
        {
            HashMap<String, String> map = ouder.ouderToHashMap();
            map.put("provider",user.getProvider());
            ref.child("profile").child(user.getUid()).setValue(map);
            return true;
        }
        return false;
    }

    public boolean changePassword(String oldPassword, final String newPassword)
    {


        if(getUser()!=null)
        {
            final Object email = user.getProviderData().get("email");
            ref.changePassword(email.toString(),oldPassword,newPassword,new Firebase.ResultHandler() {
                boolean success;

                @Override
                public void onSuccess() {
                    String em = email.toString();

                    ref.unauth();
                    logIn(em,newPassword);

                }

                @Override
                public void onError(FirebaseError firebaseError) {
                  handleFirebaseError(firebaseError);
                    ref.unauth();
                }

            });

        }
        return getUser() != null;

    }

    private ArrayList<String> handleFirebaseError(FirebaseError firebaseError)
    {
        ArrayList<String > errors = new ArrayList<String>();
        switch (firebaseError.getCode())
        {
            case FirebaseError.INVALID_AUTH_ARGUMENTS : errors.add(firebaseError.getMessage());break;
            case FirebaseError.AUTHENTICATION_PROVIDER_DISABLED : errors.add(firebaseError.getMessage());break;
            case FirebaseError.DISCONNECTED : errors.add(firebaseError.getMessage());break;
            case FirebaseError.EMAIL_TAKEN:errors.add(firebaseError.getMessage());break;
            case FirebaseError.EXPIRED_TOKEN:errors.add(firebaseError.getMessage());break;
            case FirebaseError.INVALID_EMAIL:errors.add(firebaseError.getMessage());break;
            case FirebaseError.INVALID_PASSWORD:errors.add(firebaseError.getMessage());break;
            case FirebaseError.INVALID_PROVIDER : errors.add(firebaseError.getMessage());break;
            case FirebaseError.UNKNOWN_ERROR : errors.add(firebaseError.getMessage());break;
            case FirebaseError.NETWORK_ERROR: errors.add(firebaseError.getMessage());break;
            case FirebaseError.UNAVAILABLE : errors.add(firebaseError.getMessage());break;
            default:errors.add("nothing i suspect");

        }
        for(String s : errors)
        {
            Log.e(TAG,s);
        }
        return errors;
    }
}
