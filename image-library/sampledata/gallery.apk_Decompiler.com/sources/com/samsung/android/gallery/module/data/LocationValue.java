package com.samsung.android.gallery.module.data;

import A.a;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocationValue {
    public long dateModified;
    public long id;
    public double latitude;
    public double longitude;
    public String path;
    public long size;
    public long takenTime;

    public static LocationValue parse(String str) {
        LocationValue locationValue = new LocationValue();
        try {
            String[] split = str.split(";");
            locationValue.latitude = Double.parseDouble(split[0]);
            locationValue.longitude = Double.parseDouble(split[1]);
            locationValue.id = Long.parseLong(split[2]);
            locationValue.takenTime = Long.parseLong(split[3]);
            locationValue.dateModified = Long.parseLong(split[4]);
            locationValue.size = Long.parseLong(split[5]);
            locationValue.path = split[6];
            return locationValue;
        } catch (Exception e) {
            a.s(e, new StringBuilder("parse(s) failed. e="), "LocationValue");
            return locationValue;
        }
    }

    public long computeTakenTime() {
        if (this.path == null) {
            return 0;
        }
        try {
            File file = new File(this.path);
            if (file.exists() && file.length() == this.size && file.lastModified() / 1000 == this.dateModified) {
                return this.takenTime;
            }
            return 0;
        } catch (Exception e) {
            a.s(e, new StringBuilder("computeTakenTime failed. e="), "LocationValue");
            return 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof LocationValue) {
            LocationValue locationValue = (LocationValue) obj;
            if (locationValue.latitude == this.latitude && locationValue.longitude == this.longitude && locationValue.id == this.id && locationValue.takenTime == this.takenTime && locationValue.dateModified == this.dateModified && locationValue.size == this.size && TextUtils.equals(locationValue.path, this.path)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public String toString() {
        return "" + Math.round(this.latitude) + "." + Math.round(this.longitude) + ";" + this.id + ";" + this.takenTime + ";" + this.dateModified + ";" + this.size;
    }

    public String value() {
        return "" + this.latitude + ";" + this.longitude + ";" + this.id + ";" + this.takenTime + ";" + this.dateModified + ";" + this.size + ";" + this.path;
    }

    public static LocationValue parse(Bundle bundle) {
        LocationValue locationValue = new LocationValue();
        try {
            locationValue.latitude = bundle.getDouble("__latitude");
            locationValue.longitude = bundle.getDouble("__longitude");
            locationValue.id = bundle.getLong("__absID");
            locationValue.takenTime = bundle.getLong("__dateTaken");
            locationValue.dateModified = bundle.getLong("__dateModified");
            locationValue.size = bundle.getLong("__size");
            locationValue.path = bundle.getString("__absPath");
            return locationValue;
        } catch (Exception e) {
            a.s(e, new StringBuilder("parse(b) failed. e="), "LocationValue");
            return locationValue;
        }
    }
}
