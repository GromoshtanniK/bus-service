package bus.service.service;

import bus.service.beans.RouteStop;
import bus.service.beans.StopTime;
import bus.service.beans.User;
import bus.service.dao.UserDao;
import bus.service.json.ChangedStop;
import bus.service.json.EditRoute;
import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.codemonkey.simplejavamail.email.Email;

import javax.mail.Message;
import java.sql.SQLException;
import java.util.List;

public class EmailService {

    private UserDao userDao = new UserDao();

    public void sendChangesEmail(EditRoute editRoute) {
        String text = generateTextMessage(editRoute);
        try {
            List<User> linkedUsersByRouteNumber = userDao.getLinkedUsersByRouteNumber(editRoute.getRouteNumber());
            for (User user : linkedUsersByRouteNumber) {
                sendEmail(user.getEmail(), "Изменение в маршруте " + editRoute.getRouteNumber(), text);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        String changed = "\n\nБыли изменены следующие остановки:\n";

        for (ChangedStop changedStop : editRoute.getChangedRouteStops()) {

            String changedText = "\nОстановка " + changedStop.getStopName() + ":\n";

            if (changedStop.isChangedCords()) {
                changedText += "Измелись координаты\n";
            }

            for (StopTime stopTime : changedStop.getAddedTimes()) {
                changedText += "Добавилось время " + stopTime.getHours() +" : " + stopTime.getMinutes() + "\n";
            }

            for (StopTime stopTime : changedStop.getChangedTimes()) {
                changedText += "Изменилось время на " + stopTime.getHours() +" : " + stopTime.getMinutes() + "\n";
            }

            for (StopTime stopTime : changedStop.getDeletedTimes()) {
                changedText += "Удалилось время " + stopTime.getHours() +" : " + stopTime.getMinutes() + "\n";
            }

            changed += changedText;
        }

        String text = title;

        if (!editRoute.getDeletedStopRoutes().isEmpty()) {
            text += deleted;
        }

        if (!editRoute.getAddedRouteStops().isEmpty()) {
            text += added;
        }

        if (!editRoute.getChangedRouteStops().isEmpty()) {
            text += changed;
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
