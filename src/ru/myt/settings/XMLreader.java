package ru.myt.settings;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLreader {

    private static final Logger logger = LogManager.getLogger(XMLreader.class);
    private File configFile;
    public static XMLreader instance;

    private ArrayList<RouteSettings> routesSettings = new ArrayList<>();

    private int timeToGetRailway;
    private int notifyPeriodBeforeSelectedTrain;
    private int notifyIntervalBeforeSelectedTrain;
    private int countTrainsToShowInMenu;
    private String taxiInfo;
    private boolean debugMode;

    public static XMLreader getInstance() {
        if (instance == null) {
            instance = new XMLreader();
        }
        return instance;
    }

    private XMLreader() {
        configFile = new File("config.xml");
        if (Settings.DEBUG_MODE) {
            logger.info("Begin processing XML settings file");
        }

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(configFile);
            doc.getDocumentElement().normalize();

            NodeList nodeLst = doc.getElementsByTagName("yaAPIkey");
            Element elem = (Element) nodeLst.item(0);
            String yaKey = ((Node) elem.getChildNodes().item(0)).getNodeValue();

            nodeLst = doc.getElementsByTagName("timeToGetDepStation");
            elem = (Element) nodeLst.item(0);

            timeToGetRailway = (Integer.parseInt(((Node) elem.getChildNodes().item(0)).getNodeValue()));

            nodeLst = doc.getElementsByTagName("notifyPeriodBeforeSelectedTrain");
            elem = (Element) nodeLst.item(0);
            notifyPeriodBeforeSelectedTrain = (Integer.parseInt(((Node) elem.getChildNodes().item(0)).getNodeValue()));

            nodeLst = doc.getElementsByTagName("notifyIntervalBeforeSelectedTrain");
            elem = (Element) nodeLst.item(0);
            notifyIntervalBeforeSelectedTrain = (Integer.parseInt(((Node) elem.getChildNodes().item(0)).getNodeValue()));

            nodeLst = doc.getElementsByTagName("countTrainsToShowInMenu");
            elem = (Element) nodeLst.item(0);
            countTrainsToShowInMenu = (Integer.parseInt(((Node) elem.getChildNodes().item(0)).getNodeValue()));

            nodeLst = doc.getElementsByTagName("taxiInfo");
            elem = (Element) nodeLst.item(0);
            taxiInfo = ((Node) elem.getChildNodes().item(0)).getNodeValue();

            nodeLst = doc.getElementsByTagName("debugMode");
            elem = (Element) nodeLst.item(0);
            if (((Node) elem.getChildNodes().item(0)).getNodeValue().equalsIgnoreCase("true")) {
                debugMode = true;
            } else {
                debugMode = false;
            }

            nodeLst = doc.getElementsByTagName("route");

            for (int i = 0; i < nodeLst.getLength(); i++) {
                Node currentNode = nodeLst.item(i);
                if (currentNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element currentElement = (Element) currentNode;

                    RouteSettings routeSettings = new RouteSettings();
                    routeSettings.setApikey(yaKey);

                    NodeList currentNodeList = currentElement.getElementsByTagName("from");
                    Element currentElementChild = (Element) currentNodeList.item(0);
                    routeSettings.setFrom(((Node) currentElementChild.getChildNodes().item(0)).getNodeValue());

                    currentNodeList = currentElement.getElementsByTagName("to");
                    currentElementChild = (Element) currentNodeList.item(0);
                    routeSettings.setTo(((Node) currentElementChild.getChildNodes().item(0)).getNodeValue());

                    currentNodeList = currentElement.getElementsByTagName("priority");
                    currentElementChild = (Element) currentNodeList.item(0);
                    routeSettings.setPriority(Integer.parseInt(((Node) currentElementChild.getChildNodes().item(0)).getNodeValue()));

                    currentNodeList = currentElement.getElementsByTagName("color");
                    currentElementChild = (Element) currentNodeList.item(0);
                    routeSettings.setColor(getColorFromString(((Node) currentElementChild.getChildNodes().item(0)).getNodeValue()));

                    routesSettings.add(routeSettings);
                }
            }
        } catch (Exception errorReadingXMLfile) {
            if (Settings.DEBUG_MODE) {
                logger.fatal("Processing XML settings file FAILED, exit immediately", errorReadingXMLfile);
            }
            System.exit(0);
        }
        if (Settings.DEBUG_MODE) {
            logger.info("End processing XML settings file successfully");
        }
    }

    public int getTimeToGetRailway() {
        return timeToGetRailway;
    }

    public int getNotifyPeriodBeforeSelectedTrain() {
        return notifyPeriodBeforeSelectedTrain;
    }

    public int getNotifyIntervalBeforeSelectedTrain() {
        return notifyIntervalBeforeSelectedTrain;
    }

    public int getCountTrainsToShowInMenu() {
        return countTrainsToShowInMenu;
    }

    public ArrayList<RouteSettings> getRoutesSettings() {
        return routesSettings;
    }

    public String getTaxiInfo() {
        return taxiInfo;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    private Color getColorFromString(String colorAsString) {
        Color color = null;
        try {
            String delims = "[,]";
            String[] colorIngredients = colorAsString.split(delims);

            int rValue = Integer.parseInt(colorIngredients[0]);
            int gValue = Integer.parseInt(colorIngredients[1]);
            int bValue = Integer.parseInt(colorIngredients[2]);

            return color = new Color(rValue, gValue, bValue);
        } catch (Exception e) {
            logger.error("Unable to parse following color from config, replaced with default value: " + colorAsString);
            return color = new Color(255, 255, 255);
        }
    }
}
