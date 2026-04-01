package com.samsung.o3dp.lib3dphotography;

import A0.l;
import He.F;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import com.samsung.android.liveeffect.service.c;
import com.samsung.android.liveeffect.service.d;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.RemasterUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MiracleFilterEngine {
    private static final String TAG = "MiracleFilterEngine";

    public static Bitmap runEngine(Context context, Bitmap bitmap) {
        l lVar;
        LogUtil.d(TAG, "runEngine() - E");
        Bitmap bitmap2 = null;
        try {
            if (RemasterUtil.isAtLeastOneUi8_5()) {
                lVar = new l();
                F.K("LiveEffectServiceConnector", "applyMiracleFilter() - E");
                lVar.c(context);
                Bitmap b = ((c) d.a((IBinder) lVar.e)).b(bitmap);
                lVar.q(context);
                F.K("LiveEffectServiceConnector", "applyMiracleFilter() - X");
                bitmap2 = b;
            }
            LogUtil.d(TAG, "runEngine() - X");
            return bitmap2;
        } catch (RemoteException e) {
            LogUtil.e(TAG, "RemoteException at runEngine() - " + e);
            return null;
        } catch (Throwable th) {
            lVar.q(context);
            throw th;
        }
    }
}
