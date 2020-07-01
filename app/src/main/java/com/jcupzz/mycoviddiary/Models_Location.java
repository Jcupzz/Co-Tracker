package com.jcupzz.mycoviddiary;

import android.location.Location;

public class Models_Location {

    public Models_Location(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    Location location;

}
