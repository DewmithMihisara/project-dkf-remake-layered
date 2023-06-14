package lk.ijse.project_dkf.controller.util;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Gmail {
    public static int getOtp(String ownerMail ,MailTypes mailTypes){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls.enable",true);
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.host","smtp.gmail.com");

        String user = "dkf.galle@gmail.com";
        String password = "xgeowwlwkgxtcpav";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,password);
            }
        });

        int otp=(int)(Math.random()*90000)+10000;
        String msg="";
        String subject="";

        switch (mailTypes){
            case NEW_AC -> {
                msg= "New Account is create. \nThis is OTP for it \nYour OTP = " +otp;
                subject="OTP For New Acount";
            }
            case FORGOT_PW -> {
                msg= "This is OTP for reset password. Your OTP ="+otp;
                subject="OTP For Reset Password";
            }
        }

        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(ownerMail));
            message.setFrom(new InternetAddress(user));
            message.setSubject(subject);
            message.setContent(msg,"text/html");

            Transport.send(message);

        }catch (Exception e){
            e.printStackTrace();
        }
        return otp;
    }

}
