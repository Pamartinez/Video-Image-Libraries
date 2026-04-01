package com.samsung.android.app.sdk.deepsky.textextraction.util;

import Tf.v;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.knox.SemPersonaManager;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\f\u0010\u000bJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u000bJ\u0015\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u000bR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/util/SemApiCompat;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Landroid/os/Bundle;", "getKnoxInfoForApp", "(Landroid/content/Context;)Landroid/os/Bundle;", "", "isAfwForByod", "(Landroid/content/Context;)Z", "isAfwForCl", "", "id", "Landroid/os/UserHandle;", "getUserHandle", "(I)Landroid/os/UserHandle;", "isKnoxMode", "isAfw", "knoxInfo", "Landroid/os/Bundle;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SemApiCompat {
    public static final SemApiCompat INSTANCE = new SemApiCompat();
    private static Bundle knoxInfo;

    private SemApiCompat() {
    }

    private final Bundle getKnoxInfoForApp(Context context) {
        if (knoxInfo == null) {
            knoxInfo = SemPersonaManager.getKnoxInfoForApp(context);
        }
        return knoxInfo;
    }

    private final UserHandle getUserHandle(int i2) {
        if (i2 == -1) {
            UserHandle userHandle = UserHandle.SEM_ALL;
            j.d(userHandle, "SEM_ALL");
            return userHandle;
        } else if (i2 != 0) {
            UserHandle userHandle2 = UserHandle.SEM_CURRENT;
            j.d(userHandle2, "SEM_CURRENT");
            return userHandle2;
        } else {
            UserHandle userHandle3 = UserHandle.SEM_OWNER;
            j.d(userHandle3, "SEM_OWNER");
            return userHandle3;
        }
    }

    private final boolean isAfwForByod(Context context) {
        try {
            Object systemService = context.getSystemService("user");
            j.c(systemService, "null cannot be cast to non-null type android.os.UserManager");
            UserManager userManager = (UserManager) systemService;
            if (userManager.getUserCount() > 1) {
                List<UserHandle> userProfiles = userManager.getUserProfiles();
                if (j.a(getUserHandle(0), Process.myUserHandle()) || userProfiles.size() <= 1) {
                    return false;
                }
                return true;
            }
        } catch (SecurityException e) {
            String message = e.getMessage();
            LibLogger.e("SemApiCompat", "isAfwForByod failed " + message);
        }
        return false;
    }

    private final boolean isAfwForCl(Context context) {
        Object systemService = context.getSystemService("device_policy");
        j.c(systemService, "null cannot be cast to non-null type android.app.admin.DevicePolicyManager");
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) systemService;
        List<ComponentName> activeAdmins = devicePolicyManager.getActiveAdmins();
        if (activeAdmins != null) {
            Iterable<ComponentName> iterable = activeAdmins;
            if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
                return false;
            }
            for (ComponentName packageName : iterable) {
                if (devicePolicyManager.isDeviceOwnerApp(packageName.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean isAfw(Context context) {
        j.e(context, "context");
        boolean isAfwForByod = isAfwForByod(context);
        boolean isAfwForCl = isAfwForCl(context);
        LibLogger.d("SemApiCompat", "isAfwForByod(" + isAfwForByod + "), isAfwForCl(" + isAfwForCl + ")");
        if (isAfwForByod || isAfwForCl) {
            return true;
        }
        return false;
    }

    public final boolean isKnoxMode(Context context) {
        String str;
        int i2;
        j.e(context, "context");
        try {
            Bundle knoxInfoForApp = getKnoxInfoForApp(context);
            if (knoxInfoForApp != null) {
                str = knoxInfoForApp.getString("isKnoxMode");
            } else {
                str = null;
            }
            boolean p02 = v.p0(str, SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE, false);
            if (knoxInfoForApp != null) {
                i2 = knoxInfoForApp.getInt("userid");
            } else {
                i2 = -1;
            }
            LibLogger.d("SemApiCompat", "isKnoxMode(" + p02 + "), userid(" + i2 + ")");
            return p02;
        } catch (Exception e) {
            String message = e.getMessage();
            LibLogger.w("SemApiCompat", "isKnoxMode failed, " + message);
            return false;
        }
    }
}
