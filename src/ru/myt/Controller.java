package ru.myt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JList;
import javax.swing.JMenuItem;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.myt.interfaces.ShowTrains;
import ru.myt.objects.Train;
import ru.myt.settings.Settings;

public class Controller extends MouseAdapter implements ActionListener {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    private ArrayList<Train> availableTrains = new ArrayList<>();
    private ArrayList<Train> nearestTrains = new ArrayList<>();
    private Notification notification;

    private int lastDayOfYearUpdated = 0; //to control interval of data refresh
    private int countTrainsAvailableToCatch = 0;
    private boolean isAvailableTrainsChanged = false;
    private boolean isNearestTrainsChanged = false;

    private ArrayList<ShowTrains> GUIListeners = new ArrayList<>();
    private DataSource data = null;

    public Controller() {
        updateAvailableTrains();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (Settings.DEBUG_MODE) {
                    LOGGER.info("Controller: updating states");
                }
                updateAvailableTrains();
                notifyGuiListeners();

            }
        }, 1 * 60 * 500, 1 * 60 * 500);

    }

    public void addNewTrainsToShowListener(ShowTrains listener) {
        GUIListeners.add(listener);
        isAvailableTrainsChanged = true;
        isNearestTrainsChanged = true;
        notifyGuiListeners();
    }

    public void removeNewTrainsToShowListener(ShowTrains listener) {
        GUIListeners.remove(listener);
    }

    private void notifyGuiListeners() {
        calculateNearestTrains();
        if (isNearestTrainsChanged) {
            if (Settings.DEBUG_MODE) {
                LOGGER.info("Controller: NearestTrainsChanged to guiListeners");
            }
            for (ShowTrains guilistener : GUIListeners) {
                guilistener.showNearestTrains(nearestTrains);

                if (nearestTrains.size() == 0) {
                    guilistener.showNotification("Оповещение MYT", "Ушел последний поезд :)");
                }
            }
            isNearestTrainsChanged = false;
        }
        if (isAvailableTrainsChanged) {
            if (Settings.DEBUG_MODE) {
                LOGGER.info("Controller: AvailableTrainsChanged to guiListeners");
            }
            for (ShowTrains guilistener : GUIListeners) {
                guilistener.showAvailableTrains(availableTrains);
                if (!availableTrains.isEmpty()) {
                    guilistener.showNotification("Оповещение MYT", "Расписание на сегодня загружено");
                } else {
                    guilistener.showNotification("Оповещение MYT", "Нет поездов или проблема с настройками");
                }
            }
            isAvailableTrainsChanged = false;
        }

        if (notification != null && notification.isShouldBeNotified()) {
            if (Settings.DEBUG_MODE) {
                LOGGER.info("Controller: notificationForSelectedTrainChanged to guiListeners");
            }
            for (ShowTrains guilistener : GUIListeners) {
                guilistener.showNotificationAboutSelectedTrain(notification.getNearestNotification());
            }
        }
    }

    private void updateAvailableTrains() {
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_YEAR) != lastDayOfYearUpdated) {

            try {
                if (data == null) {
                    data = new DataSource();
                }
                availableTrains = data.getTrains();
                lastDayOfYearUpdated = cal.get(Calendar.DAY_OF_YEAR);

            } catch (Exception e) {
                LOGGER.fatal("Updating trains from Yandex FAILED", e);
                availableTrains.clear();
                data = null;
            } finally {
                isAvailableTrainsChanged = true;
            }
        }
    }

    private void calculateNearestTrains() {
        if (!availableTrains.isEmpty()) {

            ArrayList<Train> newNearestTrains = new ArrayList<>();
            int newCountTrainsAvailableToCatch = 0;
            long curTime = System.currentTimeMillis();

            for (Train train : availableTrains) {
                if (train.getDepartureTime().getTime() > (curTime)) {
                    newNearestTrains.add(train);
                    if (train.isEnoughTimeToCatchTrain()) {
                        newCountTrainsAvailableToCatch++;
                    }
                }
            }
            if (nearestTrains.size() != newNearestTrains.size()) {
                nearestTrains.clear();
                nearestTrains.addAll(newNearestTrains);
                isNearestTrainsChanged = true;
            }
            if (newCountTrainsAvailableToCatch != countTrainsAvailableToCatch) {
                countTrainsAvailableToCatch = newCountTrainsAvailableToCatch;
                isNearestTrainsChanged = true;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof JMenuItem) {
            JMenuItem selectedItem = (JMenuItem) evt.getSource();
            if (Settings.DEBUG_MODE) {
                LOGGER.info("Selected GUI element: " + selectedItem.getName());
            }
            if (selectedItem.getName().equalsIgnoreCase("COMMAND-showAvailableTrains")) {

                for (ShowTrains GUIlistener : GUIListeners) {
                    GUIlistener.showAvailableTrainsWindow();
                }
                return;
            }

            if (selectedItem.getName().equalsIgnoreCase("COMMAND-showTaxiInfo")) {
                for (ShowTrains GUIlistener : GUIListeners) {
                    GUIlistener.showTaxiInfoWindow();
                }
                return;
            }
            changeSelectedTrain(selectedItem.getName());
            notifyGuiListeners();
            return;
        }
    }

    @Override
    public void mouseClicked(MouseEvent evt
    ) {
        JList list = (JList) evt.getSource();
        if (evt.getClickCount() == 2) {
            int index = list.locationToIndex(evt.getPoint());
            Train doubleClickedTrain = (Train) list.getModel().getElementAt(index);
            changeSelectedTrain(doubleClickedTrain.getUniqueID());
        }
        notifyGuiListeners();
    }

    private void changeSelectedTrain(String newUniqueId) {

        for (Train currentAvailableTrain : availableTrains) {
            if (currentAvailableTrain.getUniqueID().equals(newUniqueId)) {
                isNearestTrainsChanged = true;
                if (currentAvailableTrain.isSelected()) {
                    currentAvailableTrain.setSelected(false);
                    notification = null;
                } else {
                    currentAvailableTrain.setSelected(true);
                    notification = new Notification(currentAvailableTrain);
                }
            } else {
                currentAvailableTrain.setSelected(false);
            }
        }
    }

}
