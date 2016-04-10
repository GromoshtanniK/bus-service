import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.codemonkey.simplejavamail.email.Email;
import org.junit.Test;

import javax.mail.Message;

public class EmailTest {

    @Test
    public void kek() {
        final Email email = new Email();
        email.setFromAddress("BUS SERVICE", "bus.service.leti@yandex.ru");
        email.addRecipient("", "gromoshtannik@gmail.com", Message.RecipientType.TO);
        email.setText("We should meet up!");
        email.setSubject("hey");


        final String host = "smtp.yandex.ru";
        final int port = 465;
        final String username = "bus.service.leti";
        final String password = "Ats50Fg80W";
        new Mailer(host, port, username, password, TransportStrategy.SMTP_SSL).sendMail(email);
    }

}
