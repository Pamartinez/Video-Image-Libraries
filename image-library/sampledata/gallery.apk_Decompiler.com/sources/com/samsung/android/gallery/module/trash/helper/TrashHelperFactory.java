package com.samsung.android.gallery.module.trash.helper;

import android.content.Context;
import com.samsung.android.gallery.module.trash.helper.onetrash.OneTrashDeleteHelper;
import com.samsung.android.gallery.module.trash.helper.onetrash.OneTrashEmptyHelper;
import com.samsung.android.gallery.module.trash.helper.onetrash.OneTrashExternalHelper;
import com.samsung.android.gallery.module.trash.helper.onetrash.OneTrashRestoreHelper;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TrashHelperFactory {
    public static TrashDeleteHelper getDeleteHelper(Context context) {
        return PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH ? new OneTrashDeleteHelper(context) : new TrashDeleteHelper(context);
    }

    public static TrashEmptyHelper getEmptyHelper(Context context, boolean z) {
        if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
            return new OneTrashEmptyHelper(context, z);
        }
        return new TrashEmptyHelper(context, z);
    }

    public static TrashExternalHelper getExternalHelper(Context context) {
        if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
            return new OneTrashExternalHelper(context);
        }
        return new TrashExternalHelper(context);
    }

    public static TrashRestoreHelper getRestoreHelper(Context context, boolean z) {
        if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
            return new OneTrashRestoreHelper(context, z);
        }
        return new TrashRestoreHelper(context, z);
    }

    public static TrashDeleteHelper getDeleteHelper(Context context, boolean z) {
        return PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH ? new OneTrashDeleteHelper(context, z) : new TrashDeleteHelper(context, z);
    }
}
