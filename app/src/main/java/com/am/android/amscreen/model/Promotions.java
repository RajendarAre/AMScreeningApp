package com.am.android.amscreen.model;

import java.io.Serializable;

/**
 * Created by Rajendar Are on 9/28/15.
 */
public class Promotions implements Serializable {

    private Button[] buttonPojoArray;
    private Button buttonPojo;
    private String description;
    private String footer;
    private String image;
    private String title;

    public void setButtonPojoArray(Button[] buttonPojoArray) {
        this.buttonPojoArray = buttonPojoArray;
    }

    public Button[] getButtonPojoArray() {
        return buttonPojoArray;
    }

    public void setButtonPojo(Button buttonPojo) {
        this.buttonPojo = buttonPojo;
    }

    public Button getButtonPojo() {
        return buttonPojo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getFooter() {
        return footer;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
