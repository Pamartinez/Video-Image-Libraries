package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TintContextWrapper extends ContextWrapper {
    private static final Object CACHE_LOCK = new Object();
    private static ArrayList<WeakReference<TintContextWrapper>> sCache;
    private final Resources mResources;
    private final Resources.Theme mTheme;

    private TintContextWrapper(Context context) {
        super(context);
        if (VectorEnabledTintResources.shouldBeUsed()) {
            VectorEnabledTintResources vectorEnabledTintResources = new VectorEnabledTintResources(this, context.getResources());
            this.mResources = vectorEnabledTintResources;
            Resources.Theme newTheme = vectorEnabledTintResources.newTheme();
            this.mTheme = newTheme;
            newTheme.setTo(context.getTheme());
            return;
        }
        this.mResources = new TintResources(this, context.getResources());
        this.mTheme = null;
    }

    private static boolean shouldWrap(Context context) {
        if ((context instanceof TintContextWrapper) || (context.getResources() instanceof TintResources) || (context.getResources() instanceof VectorEnabledTintResources) || !VectorEnabledTintResources.shouldBeUsed()) {
            return false;
        }
        return true;
    }

    public static Context wrap(Context context) {
        TintContextWrapper tintContextWrapper;
        if (!shouldWrap(context)) {
            return context;
        }
        synchronized (CACHE_LOCK) {
            try {
                ArrayList<WeakReference<TintContextWrapper>> arrayList = sCache;
                if (arrayList == null) {
                    sCache = new ArrayList<>();
                } else {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        WeakReference weakReference = sCache.get(size);
                        if (weakReference == null || weakReference.get() == null) {
                            sCache.remove(size);
                        }
                    }
                    for (int size2 = sCache.size() - 1; size2 >= 0; size2--) {
                        WeakReference weakReference2 = sCache.get(size2);
                        if (weakReference2 != null) {
                            tintContextWrapper = (TintContextWrapper) weakReference2.get();
                        } else {
                            tintContextWrapper = null;
                        }
                        if (tintContextWrapper != null && tintContextWrapper.getBaseContext() == context) {
                            return tintContextWrapper;
                        }
                    }
                }
                TintContextWrapper tintContextWrapper2 = new TintContextWrapper(context);
                sCache.add(new WeakReference(tintContextWrapper2));
                return tintContextWrapper2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public AssetManager getAssets() {
        return this.mResources.getAssets();
    }

    public Resources getResources() {
        return this.mResources;
    }

    public Resources.Theme getTheme() {
        Resources.Theme theme = this.mTheme;
        if (theme == null) {
            return super.getTheme();
        }
        return theme;
    }

    public void setTheme(int i2) {
        Resources.Theme theme = this.mTheme;
        if (theme == null) {
            super.setTheme(i2);
        } else {
            theme.applyStyle(i2, true);
        }
    }
}
