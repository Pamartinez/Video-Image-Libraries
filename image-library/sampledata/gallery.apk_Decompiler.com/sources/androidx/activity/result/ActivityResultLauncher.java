package androidx.activity.result;

import androidx.core.app.ActivityOptionsCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ActivityResultLauncher<I> {
    public void launch(I i2) {
        launch(i2, (ActivityOptionsCompat) null);
    }

    public abstract void launch(I i2, ActivityOptionsCompat activityOptionsCompat);

    public abstract void unregister();
}
