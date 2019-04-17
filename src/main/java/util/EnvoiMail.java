package util;

import mysqlUtil.Requetes;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Arrays;
import java.util.Properties;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author :
 * @dateCreation :
 * @description :
 */
//----------------------------------------------------------------------------------------------------------------------
public class EnvoiMail
{
    public EnvoiMail(String sujet, String txtMessage)
    {
        try
        {
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.host", "smtp.mailtrap.io");
            prop.put("mail.smtp.port", "2525");

            Session session = Session.getInstance(prop, new javax.mail.Authenticator()
            {
                @Override
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication("7176bb10117349", "300f493677d65f");
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("avengers@mail.com"));
            InternetAddress[] address = new InternetAddress[Requetes.getAdressesMail().size()];
            for (int i = 0; i < Requetes.getAdressesMail().size(); i++)
            {
                address[i] = new InternetAddress(Requetes.getAdressesMail().get(i));
            }
            message.setRecipients(Message.RecipientType.TO, address);

            message.setSubject(sujet);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(txtMessage, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

}
