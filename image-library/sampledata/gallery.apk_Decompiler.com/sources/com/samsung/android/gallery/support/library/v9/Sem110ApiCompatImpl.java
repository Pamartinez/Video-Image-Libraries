package com.samsung.android.gallery.support.library.v9;

import N2.j;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.VideoTranscoderCompat;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.gallery.support.library.v9.media.SemVideoTranscoderCompat110;
import com.samsung.android.view.SemWindowManager;
import com.samsung.scsp.media.file.FileApiContract;
import i.C0212a;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem110ApiCompatImpl extends Sem100ApiCompatImpl {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SemWmMethodHolder {
        static final Method method = getMethod();

        public static Method getMethod() {
            try {
                return Reflector.getMethod(SemWindowManager.class, "isFolded", new Class[0]);
            } catch (Error | Exception e) {
                C0212a.y(e, new StringBuilder("getMethod failed. e="), SemWmMethodHolder.class.getSimpleName());
                return null;
            }
        }
    }

    public Intent createClipboardIntent(String str, String str2) {
        Intent intent = new Intent();
        try {
            intent.putExtra("type", 2);
            intent.putExtra(FileApiContract.Parameter.PATH, str2);
            return intent;
        } catch (Exception e) {
            j.C(e, new StringBuilder("createClipboardIntent failed e="), this.TAG);
            return intent;
        }
    }

    public VideoTranscoderCompat createVideoTranscoderCompat() {
        return new SemVideoTranscoderCompat110();
    }

    public boolean isFolded(Activity activity) {
        try {
            Object invoke = SemWmMethodHolder.method.invoke(SemWindowManager.getInstance(), (Object[]) null);
            if (invoke == null || !((Boolean) invoke).booleanValue()) {
                return false;
            }
            return true;
        } catch (Error | Exception unused) {
            return false;
        }
    }

    public boolean isMainScreen(Configuration configuration) {
        if (configuration == null) {
            return false;
        }
        int i2 = configuration.semDisplayDeviceType;
        if (i2 == 0 || i2 == -1) {
            return true;
        }
        return false;
    }

    public void setLaunchOverTargetTask(Intent intent, int i2, boolean z) {
        try {
            intent.semSetLaunchOverTargetTask(i2, z);
        } catch (Exception e) {
            Log.w(this.TAG, "setLaunchOverTargetTask failed", e);
        }
    }
}
