/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.net.URI;
import java.util.Arrays;


/**
 *
 * @author LENOVO
 */
    public class SmsSender {
    

     /**
     */
   // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC6b7c1f5a1a0d02afeddd7e768d7ba732";
    public static final String AUTH_TOKEN =
            "5a25ac47898a44f821e6662f757d28f6";


    public void send(String s,String x){
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
       Message message = Message 
                .creator(new PhoneNumber("+21692048315"), // to
                        new PhoneNumber("+16619644137"), // from
                       "Une nouvelle réclamation vient d'etre ajoutée"+ s)
                .create();
  System.out.println("aslema");
        System.out.println(message.getSid());

     
    
}
}


