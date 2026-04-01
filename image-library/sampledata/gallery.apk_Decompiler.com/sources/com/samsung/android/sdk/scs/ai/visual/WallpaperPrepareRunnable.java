package com.samsung.android.sdk.scs.ai.visual;

import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.visual.ai.sdkcommon.s;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WallpaperPrepareRunnable extends TaskRunnable<Boolean> {
    private static final String TAG = "WallpaperPrepareRunnable";
    WallpaperServiceExecutor mServiceExecutor;

    public WallpaperPrepareRunnable(WallpaperServiceExecutor wallpaperServiceExecutor) {
        this.mServiceExecutor = wallpaperServiceExecutor;
    }

    public void execute() {
        Parcel obtain;
        Parcel obtain2;
        Log.i(TAG, "execute");
        try {
            s sVar = (s) this.mServiceExecutor.getWallpaperService();
            sVar.getClass();
            obtain = Parcel.obtain();
            obtain2 = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.IWallpaperService");
            sVar.f4192a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            obtain2.recycle();
            obtain.recycle();
            this.mSource.setResult(Boolean.TRUE);
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Exception : " + e);
            throw new RuntimeException(e);
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_VISUAL_WALLPAPER;
    }
}
