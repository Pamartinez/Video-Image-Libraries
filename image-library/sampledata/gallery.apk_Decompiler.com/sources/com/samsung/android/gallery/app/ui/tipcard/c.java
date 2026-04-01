package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import com.samsung.android.gallery.app.ui.tipcard.NoMediaTipCard;
import com.samsung.android.gallery.support.utils.SystemEnvironment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements SystemEnvironment.EnvironmentChangeListener {
    public final void onEnvironmentChange(Context context) {
        NoMediaTipCard.FolderHolder.initialize();
    }
}
