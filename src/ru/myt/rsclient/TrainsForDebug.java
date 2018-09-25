package ru.myt.rsclient;

import java.util.ArrayList;
import ru.myt.objects.Train;
import ru.myt.settings.RouteSettings;

public class TrainsForDebug {

        public ArrayList<Train> getTrainsForRoute(ArrayList<RouteSettings> routeSettingsArray) {
        ArrayList<Train> listOfTrainsForRoute = new ArrayList<>();

        //Prior=3; uniqueID=6269_2_9601681_g16_4; name=Икша - Одинцово; depTime=2016-04-23 20:14:00.0; arTime=2016-04-23 20:57:00.0
        Train train = new Train();
        train.setPriority(3);
        train.setUniqueID("6269_2_9601681_g16_4");
        train.setName("Икша - Одинцово");
        train.setDepartPlace("Икша");
        train.setArrPlace("Одинцово");
        train.setDepartureTime("2016-05-25 13:00:00");
        train.setArrivalTime("2016-05-25 20:57:00");
        listOfTrainsForRoute.add(train);

        //Prior=3; uniqueID=6271_2_9600781_g16_4; name=Лобня - Можайск; depTime=2016-04-23 20:24:00.0; arTime=2016-04-23 21:09:00.0
        train = new Train();
        train.setPriority(3);
        train.setUniqueID("6271_2_9600781_g16_4");
        train.setName("Лобня - Можайск");
        train.setDepartPlace("Лобня");
        train.setArrPlace("Можайск");
        train.setDepartureTime("2016-05-25 13:02:00");
        train.setArrivalTime("2016-05-25 21:09:00");
        listOfTrainsForRoute.add(train);

        //Prior=3; uniqueID=6249_0_2000009_g16_4; name=М-Савёловская - Кубинка 1; depTime=2016-04-23 15:45:00.0; arTime=2016-04-23 16:27:00.0
        train = new Train();
        train.setPriority(3);
        train.setUniqueID("6249_0_2000009_g16_4");
        train.setName("М-Савёловская - Кубинка 1");
        train.setDepartPlace("М-Савёловская");
        train.setArrPlace("Кубинка 1");
        train.setDepartureTime("2016-05-25 13:01:00");
        train.setArrivalTime("2016-05-25 16:27:00");
        listOfTrainsForRoute.add(train);

        //Prior=3; uniqueID=6269_2_9601681_g16_4; name=Икша - Одинцово; depTime=2016-04-23 20:14:00.0; arTime=2016-04-23 20:57:00.0
        train = new Train();
        train.setPriority(1);
        train.setUniqueID("6269_2_9601681_g16_4");
        train.setName("Икша - Одинцово");
        train.setDepartPlace("Икша");
        train.setArrPlace("Одинцово");
        train.setDepartureTime("2016-05-25 20:14:00");
        train.setArrivalTime("2016-05-25 20:53:00");
        listOfTrainsForRoute.add(train);

        //Prior=1; uniqueID=6271_2_9600781_g16_4; name=Лобня - Можайск; depTime=2016-04-23 20:24:00.0; arTime=2016-04-23 21:09:00.0
        train = new Train();
        train.setPriority(1);
        train.setUniqueID("6271_2_9600781_g16_4");
        train.setName("Лобня - Можайск");
        train.setDepartPlace("Лобня");
        train.setArrPlace("Можайск");
        train.setDepartureTime("2016-05-25 13:06:00");
        train.setArrivalTime("2016-05-25 21:05:00");
        listOfTrainsForRoute.add(train);

        //Prior=2; uniqueID=6092x6091_2_9601626_g16_4; name=Столбовая - Голицыно; depTime=2016-04-23 22:20:00.0; arTime=2016-04-23 22:54:00.0
        train = new Train();
        train.setPriority(2);
        train.setUniqueID("6092x6091_2_9601626_g16_4");
        train.setName("Столбовая - Голицыно");
        train.setDepartPlace("Столбовая");
        train.setArrPlace("Голицыно");
        train.setDepartureTime("2016-05-25 22:20:00");
        train.setArrivalTime("2016-05-25 22:54:00");
        listOfTrainsForRoute.add(train);

        //Prior=4; uniqueID=6092x6091_2_9601626_g16_4; name=Столбовая - Голицыно; depTime=2016-04-23 22:20:00.0; arTime=2016-04-23 22:54:00.0
        train = new Train();
        train.setPriority(4);
        train.setUniqueID("6092x6091_2_9601626_g16_4");
        train.setName("Столбовая - Голицыно");
        train.setDepartPlace("Столбовая");
        train.setArrPlace("Голицыно");
        train.setDepartureTime("2016-05-25 22:20:00");
        train.setArrivalTime("2016-05-25 22:58:00");
        listOfTrainsForRoute.add(train);

        //Prior=4; uniqueID=6082x6081_1_9601276_g16_4; name=Львовская - Голицыно; depTime=2016-04-23 17:49:00.0; arTime=2016-04-23 18:23:00.0
        train = new Train();
        train.setPriority(4);
        train.setUniqueID("6082x6081_1_9601276_g16_4");
        train.setName("Львовская - Голицыно");
        train.setDepartPlace("Львовская");
        train.setArrPlace("Голицыно");
        train.setDepartureTime("2016-05-25 17:49:00");
        train.setArrivalTime("2016-05-25 18:23:00");
        listOfTrainsForRoute.add(train);

        return listOfTrainsForRoute;
    }
    
}
