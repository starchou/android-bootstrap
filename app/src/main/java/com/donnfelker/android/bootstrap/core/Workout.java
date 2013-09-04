package com.donnfelker.android.bootstrap.core;

import java.io.Serializable;

public class Workout implements Serializable {

    private static final long serialVersionUID = -6641292855569751236L;

    private String description;
    private String name;
    private String objectId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
