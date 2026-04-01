package androidx.core.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.view.View;
import androidx.core.util.Pair;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ActivityOptionsCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ActivityOptionsCompatImpl extends ActivityOptionsCompat {
        private final ActivityOptions mActivityOptions;

        public ActivityOptionsCompatImpl(ActivityOptions activityOptions) {
            this.mActivityOptions = activityOptions;
        }

        public Bundle toBundle() {
            return this.mActivityOptions.toBundle();
        }
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, Pair<View, String>... pairArr) {
        android.util.Pair[] pairArr2;
        if (pairArr != null) {
            pairArr2 = new android.util.Pair[pairArr.length];
            for (int i2 = 0; i2 < pairArr.length; i2++) {
                Pair<View, String> pair = pairArr[i2];
                pairArr2[i2] = android.util.Pair.create((View) pair.first, (String) pair.second);
            }
        } else {
            pairArr2 = null;
        }
        return new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity, pairArr2));
    }

    public abstract Bundle toBundle();
}
