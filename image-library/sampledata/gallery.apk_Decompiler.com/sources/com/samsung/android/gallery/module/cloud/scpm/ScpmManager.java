package com.samsung.android.gallery.module.cloud.scpm;

import N2.t;
import Rd.a;
import Rd.b;
import android.content.Context;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.scsp.framework.core.api.Response;
import i.C0212a;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScpmManager {
    private static final ScpmManager sInstance = new ScpmManager();
    private final Object LOCK = new Object();
    private volatile b mConfiguration;

    private b buildConfig(Context context) {
        try {
            TimeTickLog timeTickLog = new TimeTickLog();
            b bVar = new b(context);
            boolean z = false;
            if (context.getPackageManager().resolveContentProvider("com.samsung.android.scpm.policy", 0) != null) {
                z = true;
            }
            if (z) {
                t d = bVar.d();
                timeTickLog.tick();
                if (d.d == 2) {
                    Log.e((CharSequence) "CloudScpmManager", "buildConfig#register failed", Integer.valueOf(d.e), (String) d.f);
                    return null;
                }
                a b = bVar.b();
                timeTickLog.tick();
                if (b.d == 2) {
                    Log.e((CharSequence) "CloudScpmManager", "buildConfig#init failed", Integer.valueOf(b.e), (String) b.f);
                }
            }
            Log.d("CloudScpmManager", "buildConfig" + Logger.vt(timeTickLog));
            return bVar;
        } catch (Throwable th) {
            A.a.z(th, new StringBuilder("buildConfig failed e="), "CloudScpmManager");
            return null;
        }
    }

    private b getConfig(Context context) {
        if (this.mConfiguration == null) {
            synchronized (this.LOCK) {
                try {
                    if (this.mConfiguration == null) {
                        this.mConfiguration = buildConfig(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.mConfiguration;
    }

    public static ScpmManager getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        getConfig(context);
    }

    public String readData(a aVar) {
        boolean z;
        FileInputStream fileInputStream;
        if (aVar == null) {
            Log.e("CloudScpmManager", "readData failed. null data");
            return null;
        }
        ParcelFileDescriptor parcelFileDescriptor = aVar.g;
        if (aVar.d == 2 || parcelFileDescriptor == null) {
            if (parcelFileDescriptor != null) {
                z = true;
            } else {
                z = false;
            }
            Log.e((CharSequence) "CloudScpmManager", "readData failed", Boolean.valueOf(z), Integer.valueOf(aVar.e), (String) aVar.f);
            return null;
        }
        try {
            fileInputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
            String str = new String(FileUtils.readBytes(fileInputStream), StandardCharsets.UTF_8);
            fileInputStream.close();
            if (parcelFileDescriptor == null) {
                return str;
            }
            parcelFileDescriptor.close();
            return str;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("readData failed. e="), "CloudScpmManager");
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
        throw th;
    }

    public void update(Context context, String str) {
        a aVar;
        b config = getConfig(context);
        boolean z = false;
        if (config.b.getPackageManager().resolveContentProvider("com.samsung.android.scpm.policy", 0) != null) {
            long currentTimeMillis = System.currentTimeMillis();
            Context context2 = config.b;
            String str2 = config.f3687a;
            String l = C0212a.l("getConfiguration : ", str);
            int i2 = Qd.a.f3654a;
            if (l != null) {
                android.util.Log.i(str2, l);
            }
            try {
                ParcelFileDescriptor c5 = config.c(str);
                Bundle bundle = new Bundle();
                if (c5 == null) {
                    Bundle a7 = config.a("getLastError", context2.getPackageName(), bundle);
                    Qd.a.a(str2, "cannot get new policy : " + a7.getInt("rcode") + ArcCommonLog.TAG_COMMA + a7.getString(Response.RMSG));
                    aVar = a.j(a7, (ParcelFileDescriptor) null);
                } else {
                    aVar = a.j(config.a("getStatus", context2.getPackageName(), bundle), c5);
                }
            } catch (Exception e) {
                Qd.a.a(str2, "cannot get new policy : " + e.getMessage());
                aVar = a.k(e);
            }
            String readData = readData(aVar);
            if (TextUtils.isEmpty(readData)) {
                Log.e("CloudScpmManager", "update skip. no data" + Logger.vt(Logger.getEncodedString(str), Long.valueOf(currentTimeMillis)));
                return;
            }
            if ("gallery-gotolink".equals(str)) {
                z = GotoLink.getInstance().update(readData);
            }
            Log.d("CloudScpmManager", "update" + Logger.vt(Logger.getEncodedString(str), Boolean.valueOf(z), Long.valueOf(currentTimeMillis)) + "");
            return;
        }
        Log.e("CloudScpmManager", "update skip. not available");
    }
}
