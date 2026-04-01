package com.samsung.android.sum.solution;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.graph.Graph;
import com.samsung.android.sum.core.types.OptionBase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Option extends Graph.Option {
    public static final Parcelable.Creator<Option> CREATOR = new Parcelable.Creator<Option>() {
        public Option createFromParcel(Parcel parcel) {
            return new Option(parcel);
        }

        public Option[] newArray(int i2) {
            return new Option[i2];
        }
    };
    public static final int OPTION_AUDIO_BITRATE = 100;
    public static final int OPTION_CUSTOM_BASE = 10000;
    public static final int OPTION_FILTER_THRESHOLD = 103;
    public static final int OPTION_SAVE_AS_COPY = 104;
    public static final int OPTION_SCALE_FACTOR = 102;
    public static final int OPTION_VIDEO_BITRATE = 101;

    public Option() {
    }

    public static int makeCustomOption(int i2) {
        return i2 + 10000;
    }

    public int getAudioBitrate() {
        return ((Integer) get(100, 0)).intValue();
    }

    public Float getFilterThreshold() {
        return (Float) get(103, Float.valueOf(0.0f));
    }

    public float getScale() {
        return ((Float) get(102, Float.valueOf(0.0f))).floatValue();
    }

    public int getVideoBitrate() {
        return ((Integer) get(101, 0)).intValue();
    }

    public OptionBase set(int i2) {
        getAll().put(Integer.valueOf(i2), (Object) null);
        return this;
    }

    public Option setAudioBitrate(int i2) {
        getAll().put(100, Integer.valueOf(i2));
        return this;
    }

    public Option setFilterThreshold(float f) {
        getAll().put(103, Float.valueOf(f));
        return this;
    }

    public OptionBase setIfExists(int i2, Object obj) {
        if (obj == null) {
            return this;
        }
        return set(i2, obj);
    }

    public Option setScale(float f) {
        getAll().put(102, Float.valueOf(f));
        return this;
    }

    public Option setVideoBitrate(int i2) {
        getAll().put(101, Integer.valueOf(i2));
        return this;
    }

    public Option(Parcel parcel) {
        super(parcel);
    }

    public OptionBase set(int i2, Object obj) {
        getAll().put(Integer.valueOf(i2), obj);
        return this;
    }
}
