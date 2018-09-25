package ru.myt;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import ru.myt.objects.Train;
import ru.myt.settings.Settings;

public class Notification {

    private Train currentTrain;
    private boolean shouldBeNotified = false;
    private Map notificationMap = new HashMap<Integer, Boolean>();//<minutes before Train>,<already notified?>
    private int nearestNotificationInMinutes = 0;

    public Notification(Train selectedTrain) {
        currentTrain = selectedTrain;

        Calendar cal = Calendar.getInstance();
        int notificationStep = 0;

        while (notificationStep * 60 * 1000 < Settings.NOTIFY_PERIOD_BEFORE_SELECTED_TRAIN * 60 * 1000) {

            if (cal.getTimeInMillis() < currentTrain.getDepartureTime().getTime() - Settings.TIME_TO_GET_RAILWAY * 60 * 1000 - notificationStep * 60 * 1000) {
                notificationMap.put(notificationStep, false);
            } else {
                notificationMap.put(notificationStep, true);
            }
            notificationStep = notificationStep + Settings.NOTIFY_INTERVAL_PERIOD_BEFORE_SELECTED_TRAIN;
        }
    }

    public void refreshNotifications() {
        Calendar cal = Calendar.getInstance();
        int notificationStep = Settings.NOTIFY_PERIOD_BEFORE_SELECTED_TRAIN;
        while (notificationStep >= 0) {
            if (notificationMap.get(notificationStep) == null) {
                notificationStep = notificationStep - 1;
                continue;
            }
            if (!(boolean) notificationMap.get(notificationStep)
                    && (cal.getTimeInMillis() > currentTrain.getDepartureTime().getTime() - Settings.TIME_TO_GET_RAILWAY * 60 * 1000 - notificationStep * 60 * 1000)) {
                notificationMap.put(notificationStep, true);
                shouldBeNotified = true;
                nearestNotificationInMinutes = notificationStep;
            }
            notificationStep = notificationStep - Settings.NOTIFY_INTERVAL_PERIOD_BEFORE_SELECTED_TRAIN;
        }
    }

    public boolean isShouldBeNotified() {
        refreshNotifications();
        return shouldBeNotified;
    }

    public int getNearestNotification() {
        shouldBeNotified = false;
        return nearestNotificationInMinutes;
    }

}
