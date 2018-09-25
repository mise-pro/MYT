package ru.myt.rsclient;

import static java.lang.System.currentTimeMillis;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.net.ssl.SSLContext;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.glassfish.jersey.SslConfigurator;
import ru.myt.objects.Train;
import ru.myt.rsclient.jaxbfiles.Response;
import ru.myt.rsclient.jaxbfiles.Threads;
import ru.myt.settings.RouteSettings;
import ru.myt.settings.Settings;

public class RSClient {

    private static final Logger LOGGER = LogManager.getLogger(RSClient.class);
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "https://api.rasp.yandex.net/v1.0/search/";
    private static RSClient instance;

    public static RSClient getInstance() {
        if (instance == null) {
            instance = new RSClient();
        }
        return instance;
    }

    private RSClient() {
        SslConfigurator sslConfig = SslConfigurator.newInstance();
        final SSLContext sslContext = sslConfig.createSSLContext();
        client = ClientBuilder.newBuilder().sslContext(sslContext).build();
    }

    public ArrayList<Train> getTrainsForRoute(ArrayList<RouteSettings> routesSettingsArray) throws Exception {

        ArrayList<Train> listOfTrainsForRoute = new ArrayList<>();
        try {
            for (RouteSettings routeSettings : routesSettingsArray) {

                URI uri = UriBuilder.fromUri(BASE_URI + "?apikey={apikey}&format=xml&from={from}&to={to}&lang=ru&date={date}&transport_types=suburban")
                        .resolveTemplate("apikey", routeSettings.getApikey())
                        .resolveTemplate("from", routeSettings.getFrom())
                        .resolveTemplate("to", routeSettings.getTo())
                        .resolveTemplate("date", new SimpleDateFormat("yyyy-MM-dd").format(currentTimeMillis()))
                        .build();

                webTarget = client.target(uri);

                Response result = webTarget.request().accept(MediaType.APPLICATION_XML).get(Response.class);
                List<Threads> rawList = result.getThreads();

                for (Threads threads : rawList) {
                    Train train = new Train();
                    train.setPriority(routeSettings.getPriority());
                    train.setUniqueID(threads.getThread().getUid());
                    train.setName(threads.getThread().getShortTitle());
                    train.setDepartureTime(threads.getDeparture());
                    train.setArrivalTime(threads.getArrival());
                    train.setColor(routeSettings.getColor());
                    train.setFinalDestinationPlace(threads.getThread().getShortTitle());

                    if ((threads.getFrom().getShortTitle()).length() > 0) {
                        train.setDepartPlace(threads.getFrom().getShortTitle());
                    } else {
                        train.setDepartPlace(threads.getFrom().getTitle());
                    }

                    if ((threads.getTo().getShortTitle()).length() > 0) {
                        train.setArrPlace(threads.getTo().getShortTitle());
                    } else {
                        train.setArrPlace(threads.getTo().getTitle());
                    }

                    listOfTrainsForRoute.add(train);
                }
            }
            if (Settings.DEBUG_MODE) {
                LOGGER.info("Trains updated from Yandex succesfully");
            }

        } finally {
            close();
        }
        return listOfTrainsForRoute;
    }

    public void close() {
        client.close();
        client = null;
        instance = null;
    }

}
