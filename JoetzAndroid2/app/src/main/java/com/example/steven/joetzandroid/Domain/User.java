package com.example.steven.joetzandroid.Domain;

/**
 * Created by Steven on 23/10/14.
 */
public class User {

    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String rijksregister;

    public User()
    {

    }

    public User(String email, String lastName, String firstName) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public User(String email, String password, String lastName, String firstName)
    {
        this.setEmail(email);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }


    public void setRijksregister(String rijksregister)
    {
        if(rijksregister.length()==11 && !rijksregister.matches("[^-9]"))
        {
            this.rijksregister = rijksregister;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    public void setEmail(String email) {
        if(email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
        {
            this.email = email;
        }
        else
        {
            throw new IllegalArgumentException();
        }

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getRijksregister() {
        return rijksregister;
    }
}
