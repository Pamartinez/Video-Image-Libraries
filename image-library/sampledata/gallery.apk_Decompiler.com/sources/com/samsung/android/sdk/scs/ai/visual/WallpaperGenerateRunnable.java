package com.samsung.android.sdk.scs.ai.visual;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import c0.C0086a;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.base.ResultException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.visual.ai.sdkcommon.s;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WallpaperGenerateRunnable extends TaskRunnable<WallpaperResult> {
    private static final String TAG = "WallpaperGenerateRunnable";
    private Bitmap mAlphaBitmap;
    private Bitmap mInputBitmap;
    private Bundle mInputBundle;
    private String mInputType;
    private int mPriority;
    private final WallpaperServiceExecutor mServiceExecutor;
    private String mTime;
    private String mWeather;

    public WallpaperGenerateRunnable(WallpaperServiceExecutor wallpaperServiceExecutor) {
        this.mServiceExecutor = wallpaperServiceExecutor;
    }

    private void setResult(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "generate(). retBundle is null!!");
            C0086a.t(5, "retBundle is null", this.mSource);
            return;
        }
        WallpaperResult wallpaperResult = new WallpaperResult();
        int i2 = bundle.getInt(OCRServiceConstant.KEY_RESULT_CODE);
        wallpaperResult.setResult(i2);
        if (i2 != 1) {
            C0086a.u(i2, "generate(). Abnormal resultCode!!! resultCode: ", TAG);
            if (i2 == 500) {
                C0086a.s(500, this.mSource);
            } else {
                this.mSource.setException(new ResultException(2000, Integer.toString(i2)));
            }
        } else {
            wallpaperResult.setBundle(bundle);
            this.mSource.setResult(wallpaperResult);
        }
    }

    public void execute() {
        try {
            if (getBundle() == null && this.mInputBitmap == null) {
                this.mSource.setException(new ResultException(700));
                return;
            }
            if (getBundle() == null) {
                Bundle bundle = new Bundle();
                bundle.putString("time", this.mTime);
                bundle.putString("weather", this.mWeather);
                bundle.putInt("priority", this.mPriority);
                bundle.putString("inputType", this.mInputType);
                bundle.putParcelable("inputBitmap", this.mInputBitmap);
                bundle.putParcelable("alphaBitmap", this.mAlphaBitmap);
                setBundle(bundle);
            }
            String str = TAG;
            Log.d(str, "execute(), mInputType : " + getBundle().getString("inputType"));
            getBundle().putString("taskId", getTask().getTaskId());
            ImageEditorParamUtils.transformBitmapsInBundle(getBundle());
            setResult(((s) this.mServiceExecutor.getWallpaperService()).b(new Bundle(getBundle())));
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public Bundle getBundle() {
        return this.mInputBundle;
    }

    public String getFeatureName() {
        return Feature.FEATURE_VISUAL_WALLPAPER;
    }

    public void setBundle(Bundle bundle) {
        this.mInputBundle = bundle;
    }

    public void setInBitmap(Bitmap bitmap, Bitmap bitmap2, String str, String str2, int i2) {
        this.mInputBitmap = bitmap;
        this.mAlphaBitmap = bitmap2;
        this.mTime = str;
        this.mWeather = str2;
        this.mPriority = i2;
        this.mInputType = "bitmap";
    }
}
