package com.samsung.android.gallery.module.settings;

import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum DetailEnhancerOption {
    MAXIMUM(SettingPreference.DetailEnhancerMaximum.key, R$string.maximum, "2"),
    MINIMUM(SettingPreference.DetailEnhancerMinimum.key, R$string.minimum, "1"),
    OFF(SettingPreference.DetailEnhancerOff.key, R$string.opt_off, "0");
    
    private final String mKey;
    private final int mTitle;
    private final String mValue;

    private DetailEnhancerOption(String str, int i2, String str2) {
        this.mKey = str;
        this.mTitle = i2;
        this.mValue = str2;
    }

    public static DetailEnhancerOption getCurrent() {
        String loadString = GalleryPreference.getInstance().loadString(PreferenceName.AUTO_UPSCALE_IMAGE);
        DetailEnhancerOption detailEnhancerOption = MAXIMUM;
        if (detailEnhancerOption.getValue().equals(loadString)) {
            return detailEnhancerOption;
        }
        DetailEnhancerOption detailEnhancerOption2 = MINIMUM;
        if (detailEnhancerOption2.getValue().equals(loadString)) {
            return detailEnhancerOption2;
        }
        return OFF;
    }

    public String getKey() {
        return this.mKey;
    }

    public int getTitleResId() {
        return this.mTitle;
    }

    public String getValue() {
        return this.mValue;
    }
}
