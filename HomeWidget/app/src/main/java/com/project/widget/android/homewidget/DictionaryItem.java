package com.project.widget.android.homewidget;

/**
 * Created by Zsolt on 2016. 12. 18..
 */

class DictionaryItem {
    String hungarian, english, romanian;

    DictionaryItem(String hungarian, String english, String romanian) {
        this.hungarian = hungarian;
        this.english = english;
        this.romanian = romanian;
    }

    public String getHungarian() {
        return hungarian;
    }

    public String getEnglish() {
        return english;
    }

    public String getRomanian() {
        return romanian;
    }
}
