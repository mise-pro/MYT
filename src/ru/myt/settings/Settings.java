package ru.myt.settings;

import java.util.ArrayList;

public class Settings {

    //displaySettings
    public static final int TIME_TO_GET_RAILWAY = XMLreader.getInstance().getTimeToGetRailway();
    public static final int NOTIFY_PERIOD_BEFORE_SELECTED_TRAIN = XMLreader.getInstance().getNotifyPeriodBeforeSelectedTrain();
    public static final int NOTIFY_INTERVAL_PERIOD_BEFORE_SELECTED_TRAIN  = XMLreader.getInstance().getNotifyIntervalBeforeSelectedTrain();
    public static final int COUNT_NEAREST_TRAINS_TO_SHOW  = XMLreader.getInstance().getCountTrainsToShowInMenu();
    public static final String TAXI_INFO  = XMLreader.getInstance().getTaxiInfo();
    public static final boolean DEBUG_MODE  = XMLreader.getInstance().isDebugMode();
    
    //routesSettings
    public static final ArrayList<RouteSettings> ROUTES_SETTINGS = XMLreader.getInstance().getRoutesSettings();

}
