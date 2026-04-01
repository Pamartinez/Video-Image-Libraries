package com.samsung.android.gallery.widget.toolbar;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.R$dimen;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryCoverAppbarLayout extends CoverAppbarLayout {
    public StoryCoverAppbarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getCustomHeight() {
        int i2;
        int i7;
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        if (DeviceInfo.isDexMode(getContext())) {
            i2 = R$dimen.story_pictures_cover_height_ratio_dex;
        } else if (configuration.orientation == 2) {
            i2 = R$dimen.story_pictures_cover_height_ratio_land;
        } else {
            i2 = R$dimen.story_pictures_cover_height_ratio_port;
        }
        float typedValue = Utils.getTypedValue(resources, i2, 1.0f);
        float f = resources.getDisplayMetrics().density;
        if (typedValue < 1.0f) {
            i7 = configuration.screenHeightDp;
        } else {
            i7 = configuration.screenWidthDp;
        }
        return (int) (f * ((float) i7) * typedValue);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateInternalHeightAndOffset();
    }
}
