package ru.myt.settings;

import java.awt.Color;

public class RouteSettings {
    
    private String from;
    private String to;
    private int priority;
    private Color color;
    private String apikey;
    
    public String getFrom() {
        return from;
    }

    protected void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    protected void setTo(String to) {
        this.to = to;
    }

    public int getPriority() {
        return priority;
    }

    protected void setPriority(int priority) {
        this.priority = priority;
    }

    public Color getColor() {
        return color;
    }

    protected void setColor(Color color) {
        this.color = color;
    }
   

    public String getApikey() {
        return apikey;
    }

    protected void setApikey(String apikey) {
        this.apikey = apikey;
    }
    
}
