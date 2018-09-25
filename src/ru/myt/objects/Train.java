package ru.myt.objects;

import java.awt.Color;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import ru.myt.settings.Settings;

public class Train implements Comparable<Train> {

    private int priority; //fromXML, from user point of view
    private String uniqueID;
    private String name;
    private Timestamp arrivalTime;
    private Timestamp departureTime;
    private Timestamp durationTime;
    private String departPlace;
    private String arrPlace;
    //private String initialDepartPlace;
    private String finalDestinationPlace;
    private boolean selected;
    private boolean enoughTimeToCatchTrain;
    private Color color;

    public String getFinalDestinationPlace() {
        return finalDestinationPlace;
    }

    public void setFinalDestinationPlace(String finalDestinationPlace) {
        this.finalDestinationPlace = finalDestinationPlace;
    }

    public static enum CompareTrainsResult {
        notTheSame,
        betterThenExistant,
        theSameOrWorseThenExistant;
    }

    public Train() {
        this.selected = false;
        this.color = new Color(255, 255, 255);
    }

    @Override
    public String toString() {
        if (durationTime != null && departPlace != null && arrPlace != null && departureTime != null && arrPlace != null) {
            String durationStr;
            SimpleDateFormat outputFormat1 = new SimpleDateFormat("HH:mm");

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            cal.setTimeInMillis(durationTime.getTime());
            durationStr = "" + (cal.get(cal.MINUTE) + 60 * cal.get(cal.HOUR_OF_DAY));

            return outputFormat1.format(departureTime.getTime()) + " (" + departPlace + ") - " + outputFormat1.format(arrivalTime.getTime()) + " (" + arrPlace + ")" + " (" + durationStr + "\")";
        } else {
            return name;
        }
    }
    
    public String toStringWithFinalDestination() {
        
        return this.toString()+ " (" + finalDestinationPlace + ")";
    }

    public String toFullString() {
        String str;
        str = "Prior=" + priority + "; uniqueID=" + uniqueID + "; name=" + name + "; depTime=" + departureTime + "; arTime=" + arrivalTime;
        str = str.concat("; DepPlace= " + departPlace + "; arrPlace= " + arrPlace);
        return str;

    }

    private void calculateEnoughTimeToCatchTrain(long timeToGetDeparturePlace) {
        long curTime = System.currentTimeMillis();

        if (this.getDepartureTime().getTime() > (curTime + timeToGetDeparturePlace)) {
            setEnoughTimeToCatchTrain(true);
        } else {
            setEnoughTimeToCatchTrain(false);
        }
    }

    public static CompareTrainsResult isTheSameTrains(Train train1, Train train2) {
        CompareTrainsResult result = CompareTrainsResult.notTheSame;

        if (train1.getUniqueID().equals(train2.getUniqueID())) {
            if (train1.getPriority() < train2.getPriority()) {
                result = CompareTrainsResult.betterThenExistant;
            } else {
                result = CompareTrainsResult.theSameOrWorseThenExistant;
            }
        }
        return result;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        Timestamp timestamp = Timestamp.valueOf(arrivalTime);
        this.arrivalTime = timestamp;
        setDurationTime();
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        Timestamp timestamp = Timestamp.valueOf(departureTime);
        this.departureTime = timestamp;
        setDurationTime();
    }

    public Timestamp getDurationTime() {
        return durationTime;
    }

    private void setDurationTime() {

        durationTime = Timestamp.valueOf("2007-09-23 10:10:10.0");
        if (arrivalTime != null
                && departureTime != null) {
            this.durationTime.setTime((this.arrivalTime.getTime()) - (departureTime.getTime()));
        } else {
            this.durationTime.setTime(0L);
        }
    }

    @Override
    public int compareTo(Train train) {
        int result = 0;
        if (this.departureTime.getTime() < train.getDepartureTime().getTime()) {
            result = -1;
        } else if (this.departureTime.getTime() > train.getDepartureTime().getTime()) {
            result = 1;
        }

        return result;
    }

    public String getDepartPlace() {
        return departPlace;
    }

    public void setDepartPlace(String departPlace) {
        this.departPlace = departPlace;
    }

    public String getArrPlace() {
        return arrPlace;
    }

    public void setArrPlace(String arrPlace) {
        this.arrPlace = arrPlace;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean Selected) {
        this.selected = Selected;
    }

    /**
     * @return the enoughTimeToCatchTrain
     */
    public boolean isEnoughTimeToCatchTrain() {
        calculateEnoughTimeToCatchTrain(Settings.TIME_TO_GET_RAILWAY * 60 * 1000);
        return enoughTimeToCatchTrain;
    }

    /**
     * @param enoughTimeToCatchTrain the enoughTimeToCatchTrain to set
     */
    private void setEnoughTimeToCatchTrain(boolean enoughTimeToCatchTrain) {
        this.enoughTimeToCatchTrain = enoughTimeToCatchTrain;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
