package com.project.widget.android.homewidget;

/**
 * Created by Zsolt on 2016. 12. 18..
 */

class DictionaryItem {
    String magyar, angol, roman;

    DictionaryItem(String magyar, String angol, String roman) {
        this.magyar = magyar;
        this.angol = angol;
        this.roman = roman;
    }

    public String getMagyar() {
        return magyar;
    }

    public String getAngol() {
        return angol;
    }

    public String getRoman() {
        return roman;
    }
}
