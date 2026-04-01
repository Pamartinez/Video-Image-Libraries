package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import androidx.appcompat.R$drawable;
import androidx.appcompat.widget.ResourceManagerInternal;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppCompatDrawableManager {
    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private static AppCompatDrawableManager INSTANCE;
    private ResourceManagerInternal mResourceManager;

    public static synchronized AppCompatDrawableManager get() {
        AppCompatDrawableManager appCompatDrawableManager;
        synchronized (AppCompatDrawableManager.class) {
            try {
                if (INSTANCE == null) {
                    preload();
                }
                appCompatDrawableManager = INSTANCE;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return appCompatDrawableManager;
    }

    public static synchronized PorterDuffColorFilter getPorterDuffColorFilter(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        synchronized (AppCompatDrawableManager.class) {
            porterDuffColorFilter = ResourceManagerInternal.getPorterDuffColorFilter(i2, mode);
        }
        return porterDuffColorFilter;
    }

    public static synchronized void preload() {
        synchronized (AppCompatDrawableManager.class) {
            if (INSTANCE == null) {
                AppCompatDrawableManager appCompatDrawableManager = new AppCompatDrawableManager();
                INSTANCE = appCompatDrawableManager;
                appCompatDrawableManager.mResourceManager = ResourceManagerInternal.get();
                INSTANCE.mResourceManager.setHooks(new ResourceManagerInternal.ResourceManagerHooks() {
                    private final int[] TINT_CHECKABLE_BUTTON_LIST = {R$drawable.abc_btn_check_material_anim, R$drawable.abc_btn_radio_material_anim};

                    public Drawable createDrawableFor(ResourceManagerInternal resourceManagerInternal, Context context, int i2) {
                        return null;
                    }

                    public ColorStateList getTintListForDrawableRes(Context context, int i2) {
                        return null;
                    }

                    public PorterDuff.Mode getTintModeForDrawableRes(int i2) {
                        return null;
                    }

                    public boolean tintDrawable(Context context, int i2, Drawable drawable) {
                        return false;
                    }

                    public boolean tintDrawableUsingColorFilter(Context context, int i2, Drawable drawable) {
                        return false;
                    }
                });
            }
        }
    }

    public static void tintDrawable(Drawable drawable, TintInfo tintInfo, int[] iArr) {
        ResourceManagerInternal.tintDrawable(drawable, tintInfo, iArr);
    }

    public synchronized Drawable getDrawable(Context context, int i2) {
        return this.mResourceManager.getDrawable(context, i2);
    }

    public synchronized ColorStateList getTintList(Context context, int i2) {
        return this.mResourceManager.getTintList(context, i2);
    }

    public synchronized void onConfigurationChanged(Context context) {
        this.mResourceManager.onConfigurationChanged(context);
    }

    public synchronized Drawable getDrawable(Context context, int i2, boolean z) {
        return this.mResourceManager.getDrawable(context, i2, z);
    }
}
