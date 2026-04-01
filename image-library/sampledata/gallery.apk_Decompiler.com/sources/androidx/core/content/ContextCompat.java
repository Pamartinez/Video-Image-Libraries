package androidx.core.content;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.ObjectsCompat;
import java.io.File;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ContextCompat {
    private static final Object sSync = new Object();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static Drawable getDrawable(Context context, int i2) {
            return context.getDrawable(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static int getColor(Context context, int i2) {
            return context.getColor(i2);
        }

        public static <T> T getSystemService(Context context, Class<T> cls) {
            return context.getSystemService(cls);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api24Impl {
        public static Context createDeviceProtectedStorageContext(Context context) {
            return context.createDeviceProtectedStorageContext();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api26Impl {
        public static ComponentName startForegroundService(Context context, Intent intent) {
            return context.startForegroundService(intent);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api28Impl {
        public static Executor getMainExecutor(Context context) {
            return context.getMainExecutor();
        }
    }

    public static int checkSelfPermission(Context context, String str) {
        ObjectsCompat.requireNonNull(str, "permission must be non-null");
        if (Build.VERSION.SDK_INT >= 33 || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        if (NotificationManagerCompat.from(context).areNotificationsEnabled()) {
            return 0;
        }
        return -1;
    }

    public static Context createDeviceProtectedStorageContext(Context context) {
        return Api24Impl.createDeviceProtectedStorageContext(context);
    }

    public static int getColor(Context context, int i2) {
        return Api23Impl.getColor(context, i2);
    }

    public static ColorStateList getColorStateList(Context context, int i2) {
        return ResourcesCompat.getColorStateList(context.getResources(), i2, context.getTheme());
    }

    public static Drawable getDrawable(Context context, int i2) {
        return Api21Impl.getDrawable(context, i2);
    }

    @Deprecated
    public static File[] getExternalCacheDirs(Context context) {
        return context.getExternalCacheDirs();
    }

    @Deprecated
    public static File[] getExternalFilesDirs(Context context, String str) {
        return context.getExternalFilesDirs(str);
    }

    public static Executor getMainExecutor(Context context) {
        return Api28Impl.getMainExecutor(context);
    }

    public static <T> T getSystemService(Context context, Class<T> cls) {
        return Api23Impl.getSystemService(context, cls);
    }

    public static boolean startActivities(Context context, Intent[] intentArr, Bundle bundle) {
        context.startActivities(intentArr, bundle);
        return true;
    }

    @Deprecated
    public static void startActivity(Context context, Intent intent, Bundle bundle) {
        context.startActivity(intent, bundle);
    }

    public static void startForegroundService(Context context, Intent intent) {
        Api26Impl.startForegroundService(context, intent);
    }
}
