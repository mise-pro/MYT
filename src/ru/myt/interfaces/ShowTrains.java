package ru.myt.interfaces;

import java.util.ArrayList;
import ru.myt.objects.Train;

public interface ShowTrains {
    
    public void showAvailableTrains(ArrayList <Train> availableTrains);
    public void showNearestTrains(ArrayList <Train> nearestTrains);
    public void showNotificationAboutSelectedTrain(int timeBeforeSelectedTrain);
    public void showNotification(String title, String text);
    public void showAvailableTrainsWindow();
    public void showTaxiInfoWindow();
}
