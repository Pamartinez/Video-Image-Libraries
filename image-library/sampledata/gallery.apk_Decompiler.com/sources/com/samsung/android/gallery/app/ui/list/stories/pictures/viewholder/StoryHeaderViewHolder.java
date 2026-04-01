package com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewholders.CheckboxListViewHolder;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHeaderViewHolder extends CheckboxListViewHolder {
    private boolean mLocationAuthEnabled;

    public StoryHeaderViewHolder(View view, int i2) {
        super(view, i2);
        checkLocationAuthChanged();
    }

    private boolean checkLocationAuthChanged() {
        boolean z = this.mLocationAuthEnabled;
        boolean isLocationAuthEnabled = isLocationAuthEnabled();
        this.mLocationAuthEnabled = isLocationAuthEnabled;
        if (z != isLocationAuthEnabled) {
            return true;
        }
        return false;
    }

    private boolean isLocationAuthEnabled() {
        if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR) || PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            return true;
        }
        return false;
    }

    public void setEnabledHeader(float f) {
        this.itemView.setAlpha(f);
    }

    public void updateLocationAuthState() {
        checkLocationAuthChanged();
    }
}
