package com.samsung.android.gallery.module.trash.factory;

import android.content.Context;
import com.samsung.android.gallery.module.trash.abstraction.ITrashProvider;
import com.samsung.android.gallery.module.trash.db.TrashLocalProvider;
import com.samsung.android.gallery.module.trash.db.TrashMpProvider;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TrashProviderFactory {
    public static ITrashProvider getInstance(Context context) {
        if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
            return new TrashMpProvider(context);
        }
        return new TrashLocalProvider(context);
    }
}
