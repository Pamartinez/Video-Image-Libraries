package com.samsung.o3dp.lib3dphotography;

import A0.l;
import He.F;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import com.samsung.android.liveeffect.service.c;
import com.samsung.android.liveeffect.service.d;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.RemasterUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class O3DPDepthEstimator {
    private static final String TAG = "O3DPDepthEstimator";
    private final Context mContext;
    private DepthMap mDepthMap;

    public O3DPDepthEstimator(Context context) {
        this.mContext = context;
    }

    private void prepareDepthMap(Bitmap bitmap) {
        DepthMap depthMap = new DepthMap();
        this.mDepthMap = depthMap;
        depthMap.width = bitmap.getWidth();
        this.mDepthMap.height = bitmap.getHeight();
        DepthMap depthMap2 = this.mDepthMap;
        depthMap2.data = new float[(depthMap2.width * depthMap2.height)];
    }

    public boolean estimate(Bitmap bitmap) {
        Bitmap bitmap2;
        l lVar;
        Context context;
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            if (RemasterUtil.isAtLeastOneUi7_0()) {
                lVar = new l();
                context = this.mContext;
                F.K("LiveEffectServiceConnector", "estimateDepth() - E");
                lVar.c(context);
                bitmap2 = ((c) d.a((IBinder) lVar.e)).h(bitmap);
                lVar.q(context);
                F.K("LiveEffectServiceConnector", "estimateDepth() - X");
            } else {
                bitmap2 = RemasterUtil.requestRemasterService(this.mContext, bitmap, RemasterUtil.REMASTER_SERVICE_PURPOSE_FOR_3D_PHOTO, 3, 21);
            }
            if (bitmap2 == null) {
                LogUtil.w(TAG, "resultDepthMap is null");
                return false;
            }
            ByteBuffer allocate = ByteBuffer.allocate(bitmap2.getByteCount());
            allocate.order(ByteOrder.nativeOrder());
            bitmap2.copyPixelsToBuffer(allocate);
            allocate.rewind();
            prepareDepthMap(bitmap2);
            if (this.mDepthMap.data.length == 0) {
                LogUtil.w(TAG, "mDepthMap.data size is 0");
                return false;
            }
            for (int i2 = 0; i2 < this.mDepthMap.data.length; i2++) {
                float f = allocate.getFloat();
                DepthMap depthMap = this.mDepthMap;
                depthMap.depthMin = Math.min(depthMap.depthMin, f);
                DepthMap depthMap2 = this.mDepthMap;
                depthMap2.depthMax = Math.max(depthMap2.depthMax, f);
                this.mDepthMap.data[i2] = f;
            }
            LogUtil.d(TAG, "Depth Max : " + this.mDepthMap.depthMax + " , Min : " + this.mDepthMap.depthMin);
            long uptimeMillis2 = SystemClock.uptimeMillis();
            LogUtil.d(TAG, "Depth estimation takes " + (uptimeMillis2 - uptimeMillis) + " ms");
            return true;
        } catch (RemoteException e) {
            LogUtil.e(TAG, "RemoteException at estimateDepth() - " + e);
            return false;
        } catch (Throwable th) {
            lVar.q(context);
            throw th;
        }
    }

    public DepthMap getDepthMap() {
        return this.mDepthMap;
    }
}
