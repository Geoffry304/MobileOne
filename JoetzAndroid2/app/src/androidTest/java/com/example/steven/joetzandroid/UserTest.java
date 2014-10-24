package com.example.steven.joetzandroid;

import android.test.InstrumentationTestCase;

import com.example.steven.joetzandroid.Domain.Kind;
import com.example.steven.joetzandroid.Domain.LeeftijdsCategorie;
import com.example.steven.joetzandroid.Domain.Ouder;
import com.example.steven.joetzandroid.Domain.User;

import junit.framework.Assert;

/**
 * Created by Steven on 23/10/14.
 */
public class UserTest extends InstrumentationTestCase {

    User user = new User();

    String[]correctEmail = {"mkyong@yahoo.com", "mkyong-100@yahoo.com", "mkyong.100@yahoo.com"
    ,"mkyong111@mkyong.com","mkyong-100@mkyong.net", "mkyong.100@mkyong.com.au",
    "mkyong@1.com", "mkyong@gmail.com.com",
    "mkyong+100@gmail.com", "mkyong-100@yahoo-test.com"};
    String[]falseEmail = { "mkyong", "mkyong@.com.my",
            "mkyong123@gmail.a", "mkyong123@.com", "mkyong123@.com.com",
            ".mkyong@mkyong.com", "mkyong()*@gmail.com", "mkyong@%*.com",
            "mkyong..2002@gmail.com", "mkyong.@gmail.com",
            "mkyong@mkyong@gmail.com", "mkyong@gmail.com.1a"};

    public void testEmailTrue(){
        for(String s : correctEmail)
        {
            user.setEmail(s);
            assertEquals(user.getEmail(),s);
            assertNotNull(user.getEmail());
        }

    }
    public void testEmailFalse()
    {
        try
        {
            for(String s : falseEmail)
            {
                user.setEmail(s);
                Assert.fail("Should trow illegalArgumentException");
            }

        }
        catch (IllegalArgumentException e)
        {
            Assert.assertNull(user.getEmail());
        }
    }

    public void testRegisterFalseCatchException() {
       String rijks = "abecde";

       try
       {
           user.setRijksregister(rijks);
           Assert.fail("Should throw IllegalArgumentException");
       }
       catch (IllegalArgumentException e)
       {
           Assert.assertNull(user.getRijksregister());
       }



    }
    public void testRegisterTrue(){
        user.setRijksregister("12345678901");
        assertTrue(user.getRijksregister().equals("12345678901"));

    }

    private Ouder o = new Ouder();
    private Kind k = new Kind();

    public void testAddChildTrue(){
       int size = o.getKinderen().size();
        o.addKind(k);
        assertTrue(o.getKinderen().contains(k));
        assertTrue(o.getKinderen().size() == size +1);

    }
    public void testAddChildFalse(){
        int size = o.getKinderen().size();
        o.addKind(k);
        o.addKind(k);
        assertFalse(o.getKinderen().size() == size + 2);
    }

    public void testRemoveChildTrue()
    {
        o.addKind(k);
        o.removeKind(k);
        assertFalse(o.getKinderen().contains(k));
    }
    LeeftijdsCategorie l = new LeeftijdsCategorie(2005,2000);
    public void testVanLeeftijdTrue()
    {

        assertTrue(l.getVanLeeftijd() == 9);
        assertTrue(l.getTotLeeftijd()==14);
    }
}
