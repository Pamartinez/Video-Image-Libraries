package com.samsung.android.gallery.module.settings;

import A.a;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.support.providers.CmhUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CmhProviderPermission {
    private static final Uri CMH_AUTHORITY_URI = CmhUri.getAuthority();

    public static boolean get() {
        boolean z;
        ThreadUtil.assertBgThread("CmhProviderPermission#get should run on background thread");
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Bundle call = AppResources.getAppContext().getContentResolver().call(CMH_AUTHORITY_URI, "get_permission", (String) null, (Bundle) null);
            if (call == null || !call.getBoolean("permission_value", false)) {
                z = false;
            } else {
                z = true;
            }
            Log.d("CmhProviderPermission", "get" + Logger.vt(Boolean.valueOf(z), Long.valueOf(currentTimeMillis)));
            return z;
        } catch (Exception e) {
            a.s(e, new StringBuilder("get failed e="), "CmhProviderPermission");
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$load$0(Consumer consumer, boolean z) {
        try {
            consumer.accept(Boolean.valueOf(z));
        } catch (Exception unused) {
        }
    }

    public static void load(Consumer<Boolean> consumer) {
        SimpleThreadPool.getInstance().execute(new U5.a(1, consumer));
    }

    public static void set(boolean z) {
        ThreadUtil.assertBgThread("CmhProviderPermission#set should run on background thread");
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Bundle bundle = new Bundle();
            bundle.putBoolean("permission_value", z);
            AppResources.getAppContext().getContentResolver().call(CMH_AUTHORITY_URI, "set_permission", (String) null, bundle);
            Log.d("CmhProviderPermission", "set" + Logger.vt(Boolean.valueOf(z), Long.valueOf(currentTimeMillis)));
        } catch (Exception e) {
            a.s(e, new StringBuilder("set failed e="), "CmhProviderPermission");
        }
    }
}
