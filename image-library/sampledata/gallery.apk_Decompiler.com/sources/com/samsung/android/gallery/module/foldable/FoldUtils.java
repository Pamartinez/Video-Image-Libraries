package com.samsung.android.gallery.module.foldable;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FoldUtils {
    public static final boolean SUPPORT_FLIP_COVER_GALLERY = Features.isEnabled(Features.SUPPORT_FLIP_COVER_GALLERY);

    public static FoldableScreen getScreenType(Activity activity) {
        Configuration configuration = null;
        Resources resources = activity != null ? activity.getResources() : null;
        if (resources != null) {
            configuration = resources.getConfiguration();
        }
        return getScreenType(activity, configuration);
    }

    public static boolean isFlipCoverScreen(Activity activity) {
        if (activity == null || !FoldableScreen.FLIP_COVER.equals(getScreenType(activity))) {
            return false;
        }
        return true;
    }

    public static FoldableScreen getScreenType(Activity activity, Configuration configuration) {
        if (activity == null || configuration == null) {
            return FoldableScreen.NONE;
        }
        return SUPPORT_FLIP_COVER_GALLERY ? SeApiCompat.isFolded(activity) ? FoldableScreen.FLIP_COVER : FoldableScreen.MAIN : SeApiCompat.isMainScreen(configuration) ? FoldableScreen.MAIN : FoldableScreen.FOLD_SUB;
    }
}
