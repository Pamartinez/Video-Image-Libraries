package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TintResources extends ResourcesWrapper {
    private final WeakReference<Context> mContextRef;

    public TintResources(Context context, Resources resources) {
        super(resources);
        this.mContextRef = new WeakReference<>(context);
    }

    public Drawable getDrawable(int i2) {
        Drawable drawableCanonical = getDrawableCanonical(i2);
        Context context = this.mContextRef.get();
        if (!(drawableCanonical == null || context == null)) {
            ResourceManagerInternal.get().tintDrawableUsingColorFilter(context, i2, drawableCanonical);
        }
        return drawableCanonical;
    }
}
