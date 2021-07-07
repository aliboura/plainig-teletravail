package dz.djezzydevs.hrplaning.utils;

import org.springframework.core.io.ClassPathResource;

public class MailUtils {

    public static String getMailContent(String title, String body, String date) {
//        ClassPathResource resource = new ClassPathResource("images/logo.png");
//        helper.addInline("logoImage", resource);

        String mailContent =
                "<div " +
                "style='width:98%;height: 100px;background: #f1f1f1;border: 1px solid #e9e9e9;margin: 5px;font-size: 22px;font-weight: 500;font-family: Century Gothic'>" +
              //  "<img src='cid:logoImage' width='284' style='margin: 0px 15px 0px 5px;' />" +
              //  " <h2 style='color: #343a40;margin: 10px; padding:10px;font-family: Century Gothic'>"+ title +" </h2>"+
                "</div>";
        mailContent += "<div style='width:96%;padding:10px;border-bottom: 1px solid #d0d0d0;margin: 5px;height: auto;margin-top: 10px'>" ;
           //     " <h4 style='color: #343a40;margin: 10px;font-family: Century Gothic'>" + title + "</h4>";
        mailContent += "<h6 style='color: #1A252F;margin: 10px;font-weight: 500;font-family: Century Gothic ;font-size:12px;'>Bonjour,</h6>";
        mailContent += "<h6 style='color: #1A252F;margin: 10px 10px 10px 15px;font-weight: 500;font-family: Century Gothic;font-size:12px;'> " + body + ".</h6>";
        mailContent += "<h6 style='color: #1A252F;margin: 10px;font-weight: 500;font-family: Century Gothic;font-size:12px;'>Cordialement.</h6><br/>";
      //  mailContent += "<span style='color: #343a40;margin: 10px;font-weight: 400;font-size:10px;font-family: Century Gothic'>" + date + ".</span>";
        mailContent += "</div>";
        mailContent += "<h6 style='color: #343a40;margin-left: 10px;font-family: Century Gothic;margin-top: -3px;font-weight: 400;font-size: 11px;'>Cet email est une notification automatique, merci de ne pas répondre à ce message. Connectez-vous sur la plateforme Djezzy RH Planing des émployés en présentiel <a href='https://digitalapps.djezzy.dz/rh-planing/#/login'> [RH Planing] </a> pour retrouver votre planing.<br> Pour vos demandes de support insérez une requête via SM9</h6>";
      //  mailContent += "<h6 style='color: #343a40;margin-left: 10px;font-family: Century Gothic;margin-top: -3px;font-weight: 400;font-size: 11px;'>Pour vos demandes de support insérez une requête via SM9</h6>";



        return mailContent;
    }

}
