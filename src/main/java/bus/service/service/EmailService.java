package bus.service.service;

import bus.service.beans.RouteStop;
import bus.service.json.EditRoute;
import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.codemonkey.simplejavamail.email.Email;

import javax.mail.Message;
import java.text.MessageFormat;

public class EmailService {

    public void sendChangesEmail(EditRoute editRoute) {
        String text = generateTextMessage(editRoute);

        //TODO Цикл из пользователей с номером маршрута

        sendEmail("gromoshtannik@gmail.com", "Изменение в маршруте " + editRoute.getRouteNumber(), text);
    }

    private String generateTextMessage(EditRoute editRoute) {

        String title = "На маршруте №" + editRoute.getRouteNumber() + " произошли следующие изменения:\n";

        String deleted = "Были удалены следующие остановки:\n";

        for (RouteStop routeStop : editRoute.getDeletedStopRoutes()) {
            deleted += routeStop.getStopName() + "\n";
        }

        String added = "Были добавлены следующие остановки:\n";

        for (RouteStop routeStop : editRoute.getAddedRouteStops()) {
            added += routeStop.getStopName() + "\n";
        }

        String text = title;

        if (!editRoute.getDeletedStopRoutes().isEmpty()) {
            text += deleted;
        }

        if (!editRoute.getAddedRouteStops().isEmpty()) {
            text += added;
        }

        return text;
    }

    private void sendEmail(String emailAddress, String subject, String text) {
        final Email email = new Email();
        email.setFromAddress("BUS SERVICE", "bus.service.leti@yandex.ru");
        email.addRecipient("", emailAddress, Message.RecipientType.TO);
        email.setText(text);
        email.setSubject(subject);

        final String host = "smtp.yandex.ru";
        final int port = 465;
        final String username = "bus.service.leti";
        final String password = "Ats50Fg80W";
        new Mailer(host, port, username, password, TransportStrategy.SMTP_SSL).sendMail(email);
    }


}
