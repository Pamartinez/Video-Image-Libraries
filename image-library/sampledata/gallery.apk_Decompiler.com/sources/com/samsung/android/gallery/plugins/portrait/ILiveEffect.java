package com.samsung.android.gallery.plugins.portrait;

import android.app.Activity;
import com.samsung.android.gallery.module.share.ShareComponent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ILiveEffect {
    Activity getActivity();

    ShareComponent getShareComponent();

    boolean onMenuClicked(int i2);
}
