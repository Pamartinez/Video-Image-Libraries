package com.samsung.android.imagetranslation.jni;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LttEngineProperty {
    public static final int PROPERTY_API_VERSION_ADD_GETVER = 1;
    public static final int PROPERTY_API_VERSION_OLD_LIB = -1;
    public static final int PROPERTY_TABULAR_HINTS = 2;
    private int mKey;
    private Object mValue;

    public LttEngineProperty(int i2, Object obj) {
        this.mKey = i2;
        this.mValue = obj;
    }

    public int getIntValue(Object obj) {
        return ((Integer) obj).intValue();
    }

    public int getKey() {
        return this.mKey;
    }

    public String getStringValue(Object obj) {
        return (String) obj;
    }

    public Object getValue() {
        return this.mValue;
    }

    public boolean isValid() {
        if (this.mValue != null && this.mKey == 1) {
            return true;
        }
        return false;
    }

    public void setKey(int i2) {
        this.mKey = i2;
    }

    public void setValue(Object obj) {
        this.mValue = obj;
    }
}
