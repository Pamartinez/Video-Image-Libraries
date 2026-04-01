package com.samsung.android.gallery.support.type;

import com.samsung.android.gallery.support.R$color;
import com.samsung.android.gallery.support.R$string;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ColorDetectedType {
    DARK_BROWN(Integer.valueOf(R$color.dark_brown), Integer.valueOf(R$string.dark_brown)),
    LIGHT_BEIGE(Integer.valueOf(R$color.light_beige), Integer.valueOf(R$string.light_beige)),
    SKY_BLUE(Integer.valueOf(R$color.sky_blue), Integer.valueOf(R$string.sky_blue)),
    FOREST_GREEN(Integer.valueOf(R$color.forest_green), Integer.valueOf(R$string.forest_green)),
    VIOLET(Integer.valueOf(R$color.violet), Integer.valueOf(R$string.violet)),
    BLUISH_GREEN(Integer.valueOf(R$color.bluish_green), Integer.valueOf(R$string.bluish_green)),
    ORANGE(Integer.valueOf(R$color.orange), Integer.valueOf(R$string.orange)),
    PURPLISH_BLUE(Integer.valueOf(R$color.purplish_blue), Integer.valueOf(R$string.purplish_blue)),
    PINKISH_RED(Integer.valueOf(R$color.pinkish_red), Integer.valueOf(R$string.pinkish_red)),
    PURPLE(Integer.valueOf(R$color.purple), Integer.valueOf(R$string.purple)),
    YELLOWISH_GREEN(Integer.valueOf(R$color.yellowish_green), Integer.valueOf(R$string.yellowish_green)),
    YELLOWISH_ORANGE(Integer.valueOf(R$color.yellowish_orange), Integer.valueOf(R$string.yellowish_orange)),
    BLUE(Integer.valueOf(R$color.blue), Integer.valueOf(R$string.blue)),
    GREEN(Integer.valueOf(R$color.green), Integer.valueOf(R$string.green)),
    RED(Integer.valueOf(R$color.red), Integer.valueOf(R$string.red)),
    YELLOW(Integer.valueOf(R$color.yellow), Integer.valueOf(R$string.yellow)),
    MAGENTA(Integer.valueOf(R$color.magenta), Integer.valueOf(R$string.magenta)),
    CYAN(Integer.valueOf(R$color.cyan), Integer.valueOf(R$string.cyan)),
    WHITE(Integer.valueOf(R$color.white), Integer.valueOf(R$string.white)),
    SILVER(Integer.valueOf(R$color.silver), Integer.valueOf(R$string.silver)),
    LIGHT_GRAY(Integer.valueOf(R$color.light_gray), Integer.valueOf(R$string.light_gray)),
    MOUSE_GRAY(Integer.valueOf(R$color.mouse_gray), Integer.valueOf(R$string.mouse_gray)),
    DARK_GRAY(Integer.valueOf(R$color.dark_gray), Integer.valueOf(R$string.dark_gray)),
    BLACK(Integer.valueOf(R$color.black), Integer.valueOf(R$string.black)),
    NOT_DEFINED((String) null, Integer.valueOf(R$string.color));
    
    private static final String TAG = "ColorDetectedType";
    private Integer mColorResId;
    private Integer mTitleResId;

    private ColorDetectedType(Integer num, Integer num2) {
        this.mColorResId = num;
        this.mTitleResId = num2;
    }

    public static Integer getColorResId(String str) {
        try {
            return valueOf(str.toUpperCase()).mColorResId;
        } catch (IllegalArgumentException | NullPointerException unused) {
            Log.w(TAG, "getColorResId : invalid key = " + str);
            return null;
        }
    }

    public static Integer getTitleResId(String str) {
        try {
            return valueOf(str.toUpperCase()).mTitleResId;
        } catch (IllegalArgumentException | NullPointerException unused) {
            Log.w(TAG, "getTitleResId : invalid key = " + str);
            return NOT_DEFINED.getTitleResId();
        }
    }

    public Integer getTitleResId() {
        return this.mTitleResId;
    }
}
