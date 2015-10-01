package com.am.android.amscreen.model;

import java.io.Serializable;

/**
 * Created by Rajendar Are on 9/28/15.
 */

public class Button implements Serializable {

    private String target;
    private String title;

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
