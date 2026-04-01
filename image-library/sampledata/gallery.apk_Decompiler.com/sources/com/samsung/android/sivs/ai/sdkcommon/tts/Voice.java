package com.samsung.android.sivs.ai.sdkcommon.tts;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Voice implements Parcelable {
    public static final Parcelable.Creator<Voice> CREATOR = new Parcelable.Creator<Voice>() {
        public Voice createFromParcel(Parcel parcel) {
            return new Voice(parcel);
        }

        public Voice[] newArray(int i2) {
            return new Voice[i2];
        }
    };
    public static final String KEY_FEATURE_BOOL_IS_AI_VOICE = "KEY_FEATURE_BOOL_IS_AI_VOICE";
    public static final String KEY_FEATURE_BOOL_IS_BIXBY_VOICE = "KEY_FEATURE_BOOL_IS_BIXBY_VOICE";
    public static final String KEY_FEATURE_BOOL_IS_PERSONAL_TTS = "KEY_FEATURE_BOOL_IS_PERSONAL_TTS";
    public static final String KEY_FEATURE_BOOL_IS_SERVER_TTS = "KEY_FEATURE_BOOL_IS_SERVER_TTS";
    public static final String KEY_FEATURE_BOOL_REQUIRES_AUTHORIZATION = "KEY_FEATURE_BOOL_REQUIRES_AUTHORIZATION";
    public static final String KEY_FEATURE_INT_VERSION_CODE = "KEY_FEATURE_INT_VERSION_CODE";
    public static final String KEY_FEATURE_STRING_MODEL_TYPE = "KEY_FEATURE_STRING_MODEL_TYPE";
    public static final String KEY_FEATURE_STRING_PACKAGE_NAME = "KEY_FEATURE_STRING_PACKAGE_NAME";
    public static final String KEY_FEATURE_STRING_PERSONAL_TTS_STATUS = "KEY_FEATURE_STRING_PERSONAL_TTS_STATUS";
    public static final String KEY_FEATURE_STRING_PERSONAL_TTS_UID = "KEY_FEATURE_STRING_PERSONAL_TTS_UID";
    public static final String KEY_FEATURE_STRING_VERSION_NAME = "KEY_FEATURE_STRING_VERSION_NAME";
    public static final String KEY_FEATURE_STRING_VOICE_NAME = "KEY_FEATURE_STRING_VOICE_NAME";
    private Bundle mFeatures;
    private Locale mLocale;
    private String mTag;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum eCUSTOM_VOICE_STATUS {
        IDLE,
        ON_TRAINING,
        TRAINING_DONE,
        UNKNOWN,
        NOT_CUSTOM_VOICE
    }

    public Voice(String str, Locale locale, Bundle bundle) {
        this.mTag = str;
        this.mLocale = locale;
        this.mFeatures = bundle;
    }

    public int describeContents() {
        return 0;
    }

    public eCUSTOM_VOICE_STATUS getCustomVoiceStatus() {
        if (isCustomVoice()) {
            try {
                return eCUSTOM_VOICE_STATUS.valueOf(getFeatures().getString(KEY_FEATURE_STRING_PERSONAL_TTS_STATUS, eCUSTOM_VOICE_STATUS.UNKNOWN.name()));
            } catch (Exception unused) {
            }
        }
        return eCUSTOM_VOICE_STATUS.NOT_CUSTOM_VOICE;
    }

    public Bundle getFeatures() {
        return this.mFeatures;
    }

    public Locale getLocale() {
        return this.mLocale;
    }

    public String getLocaleISO3Code() {
        if (this.mLocale == null) {
            return null;
        }
        return this.mLocale.getISO3Language() + "-" + this.mLocale.getISO3Country();
    }

    public String getLocaleISOCode() {
        if (this.mLocale == null) {
            return null;
        }
        return this.mLocale.getLanguage() + "-" + this.mLocale.getCountry();
    }

    public String getPackageName() {
        if (getFeatures() != null) {
            return getFeatures().getString(KEY_FEATURE_STRING_PACKAGE_NAME, (String) null);
        }
        return null;
    }

    public String getTag() {
        return this.mTag;
    }

    public String getVariant() {
        Locale locale = this.mLocale;
        if (locale != null) {
            return locale.getVariant();
        }
        return null;
    }

    public int getVersionCode() {
        if (getFeatures() != null) {
            return getFeatures().getInt(KEY_FEATURE_INT_VERSION_CODE, -1);
        }
        return -1;
    }

    public String getVersionName() {
        if (getFeatures() != null) {
            return getFeatures().getString(KEY_FEATURE_STRING_VERSION_NAME, (String) null);
        }
        return null;
    }

    public String getVoiceName() {
        if (getFeatures() != null) {
            return getFeatures().getString(KEY_FEATURE_STRING_VOICE_NAME, "unknown");
        }
        return "unknown";
    }

    public boolean isCustomVoice() {
        return getFeatures().getBoolean(KEY_FEATURE_BOOL_IS_PERSONAL_TTS, false);
    }

    public String toString() {
        Locale locale = this.mLocale;
        if (locale == null) {
            return "";
        }
        String iSO3Language = locale.getISO3Language();
        String iSO3Country = this.mLocale.getISO3Country();
        String variant = this.mLocale.getVariant();
        return iSO3Language + "-" + iSO3Country + "-" + variant;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mTag);
        parcel.writeSerializable(this.mLocale);
        parcel.writeBundle(this.mFeatures);
    }

    public Voice(Parcel parcel) {
        this.mTag = parcel.readString();
        if (Build.VERSION.SDK_INT >= 33) {
            this.mLocale = (Locale) parcel.readSerializable(Locale.class.getClassLoader(), Locale.class);
        } else {
            this.mLocale = (Locale) parcel.readSerializable();
        }
        this.mFeatures = parcel.readBundle();
    }
}
