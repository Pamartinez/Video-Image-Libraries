package com.samsung.android.gallery.module.effectfilter;

import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Filter {
    NONE("None", R$string.original, -1);
    
    final int displayNameResId;
    final int intensity;
    final String rawName;

    static {
        int i2;
        String str;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        NONE = new Filter("NONE", 0, "None", R$string.original, -1);
        SdkConfig.SEM sem = SdkConfig.SEM.T_MR5;
        if (SdkConfig.atLeast(sem)) {
            i2 = R$string.funny;
        } else {
            i2 = R$string.comics;
        }
        Soft = new Filter("Soft", 1, "Soft", i2, 50);
        int i13 = R$string.happy;
        Warm = new Filter("Warm", 2, "Warm", i13, 50);
        int i14 = R$string.lounge;
        Blossom = new Filter("Blossom", 3, "Blossom", i14, 50);
        if (Features.isEnabled(Features.USE_LOLLI_FILTER)) {
            str = "Lolli";
        } else {
            str = "Light";
        }
        String str2 = str;
        int i15 = R$string.relaxing;
        Light = new Filter("Light", 4, str2, i15, 50);
        int i16 = R$string.upbeat;
        int i17 = i16;
        Cool = new Filter("Cool", 5, "Cool", i16, 50);
        int i18 = R$string.sentimental;
        BW = new Filter("BW", 6, "B&W", i18, 100);
        if (SdkConfig.atLeast(sem)) {
            i7 = R$string.mysterious;
        } else {
            i7 = R$string.mystery;
        }
        Grayscale = new Filter("Grayscale", 7, "Grayscale", i7, 100);
        if (SdkConfig.atLeast(sem)) {
            i8 = R$string.romantic;
        } else {
            i8 = R$string.lovely;
        }
        KissMe = new Filter("KissMe", 8, "KissMe", i8, 50);
        int i19 = R$string.intense;
        Frosty = new Filter("Frosty", 9, "Frosty", i19, 50);
        int i20 = R$string.dynamic;
        Ivory = new Filter("Ivory", 10, "Ivory", i20, 50);
        if (SdkConfig.atLeast(sem)) {
            i10 = R$string.funny;
        } else {
            i10 = R$string.comics;
        }
        Breeze = new Filter("Breeze", 11, "Cinepea", i10, 50);
        Sunbeam = new Filter("Sunbeam", 12, "Sunbeam", i13, 50);
        Amber = new Filter("Amber", 13, "Lomo 400", i14, 50);
        Crystal = new Filter("Crystal", 14, "Crystal", i15, 50);
        Chill = new Filter("Chill", 15, "Chill", i17, 50);
        Shade = new Filter("Shade", 16, "Noir mono myfilter", i18, 100);
        if (SdkConfig.atLeast(sem)) {
            i11 = R$string.mysterious;
        } else {
            i11 = R$string.mystery;
        }
        Shadow = new Filter("Shadow", 17, "Shadow", i11, 100);
        if (SdkConfig.atLeast(sem)) {
            i12 = R$string.romantic;
        } else {
            i12 = R$string.lovely;
        }
        Glow = new Filter("Glow", 18, "Fleecia film", i12, 50);
        Shiver = new Filter("Shiver", 19, "Excitea film 1008", i19, 50);
        Pulse = new Filter("Pulse", 20, "Jazzchrome film", i20, 50);
        $VALUES = $values();
    }

    private Filter(String str, int i2, int i7) {
        this.rawName = str;
        this.displayNameResId = i2;
        this.intensity = i7;
    }

    public static Filter get(String str) {
        for (Filter filter : values()) {
            if (filter.getRawName().equalsIgnoreCase(str)) {
                return filter;
            }
        }
        return NONE;
    }

    public static boolean hasFilter(String str) {
        for (Filter rawName2 : values()) {
            if (rawName2.getRawName().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public String getDisplayName() {
        return AppResources.getString(this.displayNameResId);
    }

    public int getIntensity() {
        return this.intensity;
    }

    public String getRawName() {
        return this.rawName;
    }

    public boolean noneFilter() {
        return NONE.equals(this);
    }
}
