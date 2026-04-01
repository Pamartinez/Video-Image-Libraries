package com.samsung.android.gallery.support.helper;

import A.a;
import Ad.C0720a;
import F3.C0395a;
import L5.b;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.samsung.android.gallery.support.annotation.ApplicationContext;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DeviceInfo {
    private static final Object LOCK = new Object();
    private static final DeviceDisplay sDefaultDisplay = new DeviceDisplay();
    private static volatile Boolean sGestureNaviBar;
    static final ConcurrentHashMap<String, Integer> sSystemDimenResMap = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DeviceDisplay {
        private final Object LOCK;
        private final Blackboard mBlackboard;
        private volatile Size mSize;

        public DeviceDisplay() {
            this((Blackboard) null);
        }

        public int getHeight() {
            return getSize().getHeight();
        }

        public int getLongSideLength() {
            Size size = getSize();
            return Math.max(size.getWidth(), size.getHeight());
        }

        public int getShortSideLength() {
            Size size = getSize();
            return Math.min(size.getWidth(), size.getHeight());
        }

        public Size getSize() {
            Size size = this.mSize;
            if (size != null) {
                return size;
            }
            Blackboard blackboard = this.mBlackboard;
            Context context = blackboard != null ? (Context) blackboard.read("data://activity") : null;
            if (context == null) {
                context = Blackboard.getActivity();
            }
            if (context == null) {
                Log.e("DeviceInfo", "getSize : activity not exist");
                context = AppResources.getAppContext();
            }
            return getSize(context);
        }

        public int getVolume() {
            Size size = getSize();
            return size.getHeight() * size.getWidth();
        }

        public int getWidth() {
            return getSize().getWidth();
        }

        public void initialize(Size size) {
            synchronized (this.LOCK) {
                this.mSize = size;
            }
        }

        public DeviceDisplay recycle() {
            synchronized (this.LOCK) {
                this.mSize = null;
            }
            return this;
        }

        public DeviceDisplay(Blackboard blackboard) {
            this.LOCK = new Object();
            this.mBlackboard = blackboard;
        }

        public Size getSize(Context context) {
            if (this.mSize == null) {
                synchronized (this.LOCK) {
                    try {
                        if (this.mSize == null) {
                            DisplayMetrics realDisplayMetrics = DeviceInfo.getRealDisplayMetrics(context);
                            this.mSize = new Size(realDisplayMetrics.widthPixels, realDisplayMetrics.heightPixels);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return this.mSize;
        }
    }

    public static void clearDexMode(Context context) {
        if (context != null) {
            SeApiCompat.clearDexMode(context);
        }
    }

    public static String genRandomSerial() {
        byte[] bytes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".getBytes();
        Random random = new Random(System.currentTimeMillis());
        byte[] bArr = new byte[11];
        bArr[0] = 90;
        bArr[1] = 88;
        for (int i2 = 2; i2 < 11; i2++) {
            bArr[i2] = bytes[random.nextInt(bytes.length)];
        }
        return new String(bArr);
    }

    public static int getAndroidDimenResId(String str) {
        return sSystemDimenResMap.computeIfAbsent(str, new b(22)).intValue();
    }

    public static DeviceDisplay getDefaultDisplay() {
        return sDefaultDisplay;
    }

    public static String getDeviceSerial() {
        String str;
        try {
            str = Build.getSerial();
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("getDeviceSerial failed. e="), "DeviceInfo");
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return GalleryPreference.getInstance().computeStringIfAbsent("serial_number", new C0720a(4));
    }

    public static DeviceDisplay getDisplay(Context context) {
        if (context == null || (context instanceof ApplicationContext)) {
            context = Blackboard.getContext();
            Log.e("DeviceInfo", "getDisplay#context" + Logger.getSimpleName((Object) context));
            if (context == null) {
                return getDefaultDisplay();
            }
        }
        Blackboard instance = Blackboard.getInstance(context.toString());
        return (DeviceDisplay) instance.computeIfAbsent("data://DeviceDisplay", new C0395a(instance, 1));
    }

    public static int getDisplayHeight(Context context) {
        return getDisplay(context).getHeight();
    }

    public static int getDisplayRotation(Context context) {
        if (Build.VERSION.SDK_INT > 30) {
            try {
                return context.getDisplay().getRotation();
            } catch (Exception unused) {
            }
        }
        if (context == null) {
            context = AppResources.getAppContext();
        }
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    public static Size getDisplaySize(Context context) {
        return getDisplay(context).getSize();
    }

    public static int getDisplayVolume(Context context) {
        return getDisplay(context).getVolume();
    }

    public static int getDisplayWidth(Context context) {
        return getDisplay(context).getWidth();
    }

    public static String getKeyboardLanguage() {
        try {
            System.currentTimeMillis();
            String str = (String) Optional.ofNullable((InputMethodManager) AppResources.getAppContext().getSystemService(InputMethodManager.class)).map(new b(23)).map(new b(24)).orElse((Object) null);
            if (str != null && str.length() > 0) {
                return str;
            }
        } catch (NullPointerException | UnsupportedOperationException e) {
            Log.w("DeviceInfo", "getKeyboardLanguage failed. e=" + e.getMessage());
        }
        return Locale.getDefault().getLanguage();
    }

    public static int getNavigationBarHeight() {
        int androidDimenResId;
        if (!Features.isEnabled(Features.USE_NAVIGATION_BAR) || (androidDimenResId = getAndroidDimenResId("navigation_bar_height")) <= 0) {
            return 0;
        }
        return AppResources.getAppContext().getResources().getDimensionPixelSize(androidDimenResId);
    }

    public static int getNavigationBarTopInPixel(Context context) {
        int i2;
        boolean isLandscape = ResourceCompat.isLandscape(context);
        DisplayMetrics realDisplayMetrics = getRealDisplayMetrics(context);
        if (isLandscape) {
            i2 = realDisplayMetrics.widthPixels;
        } else {
            i2 = realDisplayMetrics.heightPixels;
        }
        return i2 - getNavigationBarHeight();
    }

    public static DisplayMetrics getRealDisplayMetrics(Context context) {
        if (context == null) {
            context = Blackboard.getActivity();
            Log.e("DeviceInfo", "getRealDisplayMetrics with null context. use blackboard instead " + Logger.getSimpleName((Object) context) + "");
        }
        if (context instanceof ContextThemeWrapper) {
            try {
                return getRealMetrics(context.getDisplay());
            } catch (Exception unused) {
            }
        }
        if (context instanceof Activity) {
            return getRealMetrics(((Activity) context).getWindowManager().getDefaultDisplay());
        }
        if (context == null) {
            context = AppResources.getAppContext();
        }
        return getRealMetrics(((WindowManager) context.getSystemService("window")).getDefaultDisplay());
    }

    public static Size getRealDisplaySize(Context context) {
        DisplayMetrics realDisplayMetrics = getRealDisplayMetrics(context);
        Size size = new Size(realDisplayMetrics.widthPixels, realDisplayMetrics.heightPixels);
        DeviceDisplay display = getDisplay(context);
        if (realDisplayMetrics.heightPixels != display.getHeight()) {
            Log.e((CharSequence) "DeviceInfo", "getRealDisplaySize#recover " + Logger.getSimpleName((Object) context), display.getSize(), size);
            display.initialize(size);
            sDefaultDisplay.initialize(size);
        }
        return size;
    }

    public static DisplayMetrics getRealMetrics(Display display) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getRealMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int getStatusBarHeight() {
        int androidDimenResId = getAndroidDimenResId("status_bar_height");
        if (androidDimenResId > 0) {
            return AppResources.getAppContext().getResources().getDimensionPixelSize(androidDimenResId);
        }
        return 0;
    }

    public static boolean isAdvancedMouseCompat(Context context) {
        if (SdkConfig.atLeast(SdkConfig.SEM.T_MR5) || isDexMode(context)) {
            return true;
        }
        return false;
    }

    public static boolean isDexMode(Context context) {
        if (context == null || !SeApiCompat.isDexMode(context)) {
            return false;
        }
        return true;
    }

    public static boolean isDexOnPc(Context context) {
        if (context == null || !Features.isEnabled(Features.SUPPORT_DEX_ON_PC)) {
            return false;
        }
        return SeApiCompat.isDexOnPc(context);
    }

    public static boolean isGestureNaviBar(Activity activity) {
        return !isDexMode(activity) && isGestureNaviBar();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeviceDisplay lambda$getDisplay$0(Blackboard blackboard, String str) {
        return new DeviceDisplay(blackboard);
    }

    public static void recycle(Context context) {
        synchronized (LOCK) {
            sGestureNaviBar = null;
        }
        if (context != null) {
            sDefaultDisplay.initialize(getDisplay(context).recycle().getSize(context));
        }
    }

    public static void clearDexMode() {
        SeApiCompat.clearDexMode((Context) null);
    }

    public static boolean isGestureNaviBar() {
        boolean booleanValue;
        synchronized (LOCK) {
            try {
                if (sGestureNaviBar == null) {
                    boolean z = false;
                    if (SeApiCompat.getSettingsGlobalInt(AppResources.getAppContext(), "navigation_bar_gesture_while_hidden", 0) > 0) {
                        z = true;
                    }
                    sGestureNaviBar = Boolean.valueOf(z);
                }
                booleanValue = sGestureNaviBar.booleanValue();
            } catch (Throwable th) {
                throw th;
            }
        }
        return booleanValue;
    }
}
