package androidx.core.app;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import i.C0212a;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ActivityCompat extends ContextCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static void requestPermissions(Activity activity, String[] strArr, int i2) {
            activity.requestPermissions(strArr, i2);
        }

        public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api31Impl {
        public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
            try {
                return ((Boolean) PackageManager.class.getMethod("shouldShowRequestPermissionRationale", new Class[]{String.class}).invoke(activity.getApplication().getPackageManager(), new Object[]{str})).booleanValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return activity.shouldShowRequestPermissionRationale(str);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api32Impl {
        public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int i2);
    }

    public static void finishAffinity(Activity activity) {
        activity.finishAffinity();
    }

    public static void recreate(Activity activity) {
        activity.recreate();
    }

    public static void requestPermissions(Activity activity, String[] strArr, int i2) {
        String[] strArr2;
        HashSet hashSet = new HashSet();
        int i7 = 0;
        while (i7 < strArr.length) {
            if (!TextUtils.isEmpty(strArr[i7])) {
                if (Build.VERSION.SDK_INT < 33 && TextUtils.equals(strArr[i7], "android.permission.POST_NOTIFICATIONS")) {
                    hashSet.add(Integer.valueOf(i7));
                }
                i7++;
            } else {
                throw new IllegalArgumentException(C0212a.p(new StringBuilder("Permission request for permissions "), Arrays.toString(strArr), " must not contain null or empty values"));
            }
        }
        int size = hashSet.size();
        if (size > 0) {
            strArr2 = new String[(strArr.length - size)];
        } else {
            strArr2 = strArr;
        }
        if (size > 0) {
            if (size != strArr.length) {
                int i8 = 0;
                for (int i10 = 0; i10 < strArr.length; i10++) {
                    if (!hashSet.contains(Integer.valueOf(i10))) {
                        strArr2[i8] = strArr[i10];
                        i8++;
                    }
                }
            } else {
                return;
            }
        }
        if (activity instanceof RequestPermissionsRequestCodeValidator) {
            ((RequestPermissionsRequestCodeValidator) activity).validateRequestPermissionsRequestCode(i2);
        }
        Api23Impl.requestPermissions(activity, strArr, i2);
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 33 && TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) {
            return false;
        }
        if (i2 >= 32) {
            return Api32Impl.shouldShowRequestPermissionRationale(activity, str);
        }
        if (i2 == 31) {
            return Api31Impl.shouldShowRequestPermissionRationale(activity, str);
        }
        return Api23Impl.shouldShowRequestPermissionRationale(activity, str);
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i2, Bundle bundle) {
        activity.startActivityForResult(intent, i2, bundle);
    }

    public static void startIntentSenderForResult(Activity activity, IntentSender intentSender, int i2, Intent intent, int i7, int i8, int i10, Bundle bundle) {
        activity.startIntentSenderForResult(intentSender, i2, intent, i7, i8, i10, bundle);
    }
}
