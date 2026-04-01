package androidx.activity;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.LifecycleEventObserver;
import java.lang.reflect.Field;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ImmLeaksCleaner implements LifecycleEventObserver {
    private static Field sHField;
    private static Field sNextServedViewField;
    private static int sReflectedFieldsInitialized;
    private static Field sServedViewField;
    private Activity mActivity;

    private static void initializeReflectiveFields() {
        Class<InputMethodManager> cls = InputMethodManager.class;
        try {
            sReflectedFieldsInitialized = 2;
            Field declaredField = cls.getDeclaredField("mServedView");
            sServedViewField = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = cls.getDeclaredField("mNextServedView");
            sNextServedViewField = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = cls.getDeclaredField("mH");
            sHField = declaredField3;
            declaredField3.setAccessible(true);
            sReflectedFieldsInitialized = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:30|31|32|44) */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x0046 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onStateChanged(androidx.lifecycle.LifecycleOwner r2, androidx.lifecycle.Lifecycle.Event r3) {
        /*
            r1 = this;
            androidx.lifecycle.Lifecycle$Event r2 = androidx.lifecycle.Lifecycle.Event.ON_DESTROY
            if (r3 == r2) goto L_0x0005
            goto L_0x004e
        L_0x0005:
            int r2 = sReflectedFieldsInitialized
            if (r2 != 0) goto L_0x000c
            initializeReflectiveFields()
        L_0x000c:
            int r2 = sReflectedFieldsInitialized
            r3 = 1
            if (r2 != r3) goto L_0x004e
            android.app.Activity r1 = r1.mActivity
            java.lang.String r2 = "input_method"
            java.lang.Object r1 = r1.getSystemService(r2)
            android.view.inputmethod.InputMethodManager r1 = (android.view.inputmethod.InputMethodManager) r1
            java.lang.reflect.Field r2 = sHField     // Catch:{ IllegalAccessException -> 0x004e }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ IllegalAccessException -> 0x004e }
            if (r2 != 0) goto L_0x0024
            goto L_0x004e
        L_0x0024:
            monitor-enter(r2)
            java.lang.reflect.Field r3 = sServedViewField     // Catch:{ IllegalAccessException -> 0x004a, ClassCastException -> 0x0048 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ IllegalAccessException -> 0x004a, ClassCastException -> 0x0048 }
            android.view.View r3 = (android.view.View) r3     // Catch:{ IllegalAccessException -> 0x004a, ClassCastException -> 0x0048 }
            if (r3 != 0) goto L_0x0033
            monitor-exit(r2)     // Catch:{ all -> 0x0031 }
            return
        L_0x0031:
            r1 = move-exception
            goto L_0x004c
        L_0x0033:
            boolean r3 = r3.isAttachedToWindow()     // Catch:{ all -> 0x0031 }
            if (r3 == 0) goto L_0x003b
            monitor-exit(r2)     // Catch:{ all -> 0x0031 }
            return
        L_0x003b:
            java.lang.reflect.Field r3 = sNextServedViewField     // Catch:{ IllegalAccessException -> 0x0046 }
            r0 = 0
            r3.set(r1, r0)     // Catch:{ IllegalAccessException -> 0x0046 }
            monitor-exit(r2)     // Catch:{ all -> 0x0031 }
            r1.isActive()
            return
        L_0x0046:
            monitor-exit(r2)     // Catch:{ all -> 0x0031 }
            goto L_0x004e
        L_0x0048:
            monitor-exit(r2)     // Catch:{ all -> 0x0031 }
            goto L_0x004e
        L_0x004a:
            monitor-exit(r2)     // Catch:{ all -> 0x0031 }
            goto L_0x004e
        L_0x004c:
            monitor-exit(r2)     // Catch:{ all -> 0x0031 }
            throw r1
        L_0x004e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.ImmLeaksCleaner.onStateChanged(androidx.lifecycle.LifecycleOwner, androidx.lifecycle.Lifecycle$Event):void");
    }
}
