package ru.myt;

import java.util.ArrayList;
import java.util.Collections;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.myt.objects.Train;
import ru.myt.rsclient.RSClient;
import ru.myt.settings.Settings;

public class DataSource {

    private static final Logger LOGGER = LogManager.getLogger(DataSource.class);
    private ArrayList<Train> availableTrains = new ArrayList<>();

    public ArrayList<Train> getTrains() throws Exception {

        if (Settings.DEBUG_MODE) {
            LOGGER.info("Updating trains from yandex...");
        }
        availableTrains.clear();
        availableTrains.addAll(RSClient.getInstance().getTrainsForRoute(Settings.ROUTES_SETTINGS));
        //place to replace for trains for debug
        //TrainsForDebug debugTrains = new TrainsForDebug();
        //newAvailableTrains = debugTrains.getTrainsForRoute(settings);

        if (Settings.DEBUG_MODE) {
            LOGGER.info("AllLoadedTrains are:");
            for (Train train : availableTrains) {
                LOGGER.info(train.toFullString());
            }
        }

        deDublicateAndSortTrains();

        return availableTrains;
    }

    private void deDublicateAndSortTrains() {

        ArrayList<Train> uniqueTrains = new ArrayList<>();
        uniqueTrains.add(availableTrains.get(0));
        boolean isTrainSholdBeAdded;
        Train shouldBeRemovedTrain;

        //TODO may be BAD realization from the loop point of view 
        
        for (Train currentTrain : availableTrains) {
            isTrainSholdBeAdded = true;
            shouldBeRemovedTrain = null;
            for (Train candidateTrain : uniqueTrains) {
                switch (Train.isTheSameTrains(currentTrain, candidateTrain)) {
                    case notTheSame:
                        break;
                    case betterThenExistant:
                        shouldBeRemovedTrain = candidateTrain;
                        break;
                    case theSameOrWorseThenExistant:
                        isTrainSholdBeAdded = false;
                        break;
                }
            }
            if (isTrainSholdBeAdded) {
                uniqueTrains.add(currentTrain);
            }
            if (shouldBeRemovedTrain != null) {
                uniqueTrains.remove(shouldBeRemovedTrain);
            }
        }
        
        Collections.sort(uniqueTrains);
        availableTrains.clear();
        availableTrains.addAll(uniqueTrains);

        if (Settings.DEBUG_MODE) {
            LOGGER.info("Unique and sorted trains are:");
            for (Train train : availableTrains) {
                LOGGER.info(train.toFullString());
            }
        }

    }
}
