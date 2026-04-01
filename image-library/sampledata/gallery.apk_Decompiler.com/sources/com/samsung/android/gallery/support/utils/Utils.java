package com.samsung.android.gallery.support.utils;

import A.a;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.R$color;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.R$bool;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.scsp.framework.core.network.HeaderSetup;
import gc.e;
import java.io.Closeable;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Utils {
    private static volatile Reference<Toast> sToast = new WeakReference((Object) null);

    public static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                Log.w("CURSOR", "close failed {" + Logger.getSimpleName((Object) closeable) + "} e=" + e);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void createAndShowToast(Context context, String str, int i2, int i7, int i8, int i10) {
        try {
            Toast toast = sToast.get();
            if (toast != null) {
                toast.cancel();
            }
            Toast makeText = Toast.makeText(context, str, i2);
            if (i7 != -1) {
                makeText.setGravity(i7, i8, i10);
            }
            makeText.show();
            sToast = new WeakReference(makeText);
        } catch (Exception e) {
            a.s(e, new StringBuilder("createToast failed. e="), "Utils");
        }
    }

    public static void finishAndStartGalleryActivity(Activity activity) {
        try {
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                Intent launchIntentForPackage = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
                if (launchIntentForPackage != null) {
                    launchIntentForPackage.addFlags(65536);
                    activity.finish();
                    activity.startActivity(launchIntentForPackage);
                    return;
                }
                return;
            }
            recreateActivity(activity);
        } catch (ActivityNotFoundException | NullPointerException e) {
            Log.e("Utils", "finishAndStartGalleryActivity e=" + e);
        }
    }

    public static Activity getActivity(View view) {
        if (view != null) {
            return getActivity(view.getContext());
        }
        return null;
    }

    public static int getPermanentDeleteButtonTextColor(Context context) {
        if (context.getResources().getBoolean(R$bool.isNightTheme)) {
            return context.getColor(R$color.sesl_functional_red_dark);
        }
        return context.getColor(R$color.sesl_functional_red_light);
    }

    public static int[] getRange(int i2, int i7, int i8, int i10) {
        int i11 = 0;
        if (i8 <= i10) {
            return new int[]{0, i8 - 1};
        }
        int i12 = i10 / 2;
        if (i7 - i2 > i10) {
            return null;
        }
        int i13 = (i7 + i2) / 2;
        int i14 = i13 + i12;
        int i15 = (i13 - i12) + 1;
        if (i15 < 0) {
            i14 += -i15;
        } else if (i14 >= i8) {
            i11 = i15 - ((i14 - i8) + 1);
            i14 = i8 - 1;
        } else {
            i11 = i15;
        }
        return new int[]{i11, i14};
    }

    public static int getTextColorFromTextColorAttr(Context context, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i2});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }

    public static float getTypedValue(Resources resources, int i2, float f) {
        if (resources == null) {
            return f;
        }
        TypedValue typedValue = new TypedValue();
        resources.getValue(i2, typedValue, true);
        return typedValue.getFloat();
    }

    public static String getWifiAddress() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) AppResources.getAppContext().getSystemService(HeaderSetup.Key.WIFI);
        if (wifiManager == null || !wifiManager.isWifiEnabled() || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return null;
        }
        int ipAddress = connectionInfo.getIpAddress();
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }
        try {
            return InetAddress.getByAddress(BigInteger.valueOf((long) ipAddress).toByteArray()).getHostAddress();
        } catch (UnknownHostException unused) {
            Log.e("WIFIIP", "Unable to get host address.");
            return null;
        }
    }

    public static void hideSip(IBinder iBinder) {
        InputMethodManager inputMethodManager = (InputMethodManager) AppResources.getAppContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(iBinder, 0);
        }
    }

    public static boolean isAnimationDuration0x(Context context) {
        if (context != null && Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f) == 0.0f) {
            return true;
        }
        return false;
    }

    public static void recreateActivity(Activity activity) {
        try {
            activity.recreate();
        } catch (ActivityNotFoundException | NullPointerException e) {
            Log.e("Utils", "recreateActivity e=" + e);
        }
    }

    public static void resetSilently(InputStream inputStream) {
        try {
            inputStream.reset();
        } catch (Exception unused) {
        }
    }

    public static boolean restartGalleryActivity(Context context) {
        try {
            Context applicationContext = context.getApplicationContext();
            Intent launchIntentForPackage = applicationContext.getPackageManager().getLaunchIntentForPackage(applicationContext.getPackageName());
            if (launchIntentForPackage == null) {
                return false;
            }
            launchIntentForPackage.addFlags(335544320);
            context.startActivity(launchIntentForPackage);
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            Log.e("Utils", "restartGalleryActivity e=" + e);
            return false;
        }
    }

    public static void setCustomTextCursor(EditText editText, int i2) {
        editText.setTextCursorDrawable(i2);
    }

    public static void setPermanentDeleteButtonTextColor(Dialog dialog) {
        if (dialog instanceof AlertDialog) {
            AlertDialog alertDialog = (AlertDialog) dialog;
            Optional.ofNullable(alertDialog.getButton(-1)).ifPresent(new P(5, alertDialog));
        }
    }

    public static void showShortToast(Context context, String str) {
        ThreadUtil.runOnUiThread(new C0685x(5, context, str));
    }

    public static void showThemeToast(Context context, String str, int i2) {
        if (context != null) {
            showToast((Context) new ContextThemeWrapper(context.getApplicationContext(), 16974123), str, i2);
        }
    }

    public static void showToast(Context context, int i2) {
        if (context != null) {
            showToast(context, context.getString(i2), 0);
        }
    }

    public static Activity getActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        while (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    public static void showShortToast(Context context, int i2) {
        showShortToast(context, context.getString(i2));
    }

    public static void showToast(Context context, int i2, int i7) {
        if (context != null) {
            showToast(context, context.getString(i2), i7);
        }
    }

    public static void showThemeToast(Context context, String str) {
        showThemeToast(context, str, 0);
    }

    public static void showToast(Context context, String str) {
        showToast(context, str, 0);
    }

    public static void showThemeToast(Context context, int i2) {
        if (context != null) {
            showThemeToast(context, context.getString(i2));
        }
    }

    public static void showToast(Context context, String str, int i2) {
        showToast(context, str, i2, -1, 0, 0);
    }

    public static void showThemeToast(int i2) {
        showThemeToast(AppResources.getAppContext(), i2);
    }

    public static void showToast(Context context, int i2, int i7, int i8, int i10) {
        if (context != null) {
            showToast(context, context.getString(i2), 0, i7, i8, i10);
        }
    }

    public static void showToast(Context context, String str, int i2, int i7, int i8, int i10) {
        if (context != null) {
            ThreadUtil.runOnUiThread(new e(context, str, i2, i7, i8, i10));
        }
    }

    public static void showDebugToast(Context context, String str) {
    }
}
