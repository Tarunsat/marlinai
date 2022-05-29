package com.example.marlinai;

public class Global {
    private static Global instance = new Global();

    // Getter-Setters
    public static Global getInstance() {
        return instance;
    }

    public static void setInstance(Global instance) {
        Global.instance = instance;
    }


    private String Flyin ="";
    private String Flyout="";



    private Global() {

    }



    public String getFlyin() {
        return Flyin;
    }
    public String getFlyout(){ return Flyout;}
    public void setFlyin(String notification_index) {
        this.Flyin = notification_index;
    }
    public void setFlyout(String notification_index) {
        this.Flyout = notification_index;
    }
}
