package com.samsung.android.sdk.scs.ai.image;

import Bc.a;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.base.ResultException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetectLargestRunnable extends TaskRunnable<Boundary> {
    private static final String TAG = "ScsApi@DetectLargestRunnable";
    Bitmap mBitmap;
    Boundary mLargestBoundary;
    ImageServiceExecutor mServiceExecutor;
    Uri mUri;

    public DetectLargestRunnable(ImageServiceExecutor imageServiceExecutor) {
        this.mServiceExecutor = imageServiceExecutor;
    }

    public void execute() {
        if (this.mUri == null && this.mBitmap == null) {
            Log.e(TAG, "RESULT_MISSING_MANDATORY_FIELD!!!");
            this.mSource.setException(new IllegalArgumentException("input is null"));
            return;
        }
        try {
            Bundle bundle = new Bundle();
            Uri uri = this.mUri;
            if (uri != null) {
                bundle.putParcelable(OCRServiceConstant.KEY_PARAM_URI, uri);
            } else {
                bundle.putParcelable("bmp", this.mBitmap);
            }
            Bundle b = ((a) this.mServiceExecutor.getImageService()).b(bundle);
            if (b == null) {
                Log.e(TAG, "detectLargestBoundary(). retBundle is null!!");
                this.mSource.setException(new ResultException(5, "retBundle is null"));
                return;
            }
            int i2 = b.getInt(OCRServiceConstant.KEY_RESULT_CODE);
            if (i2 != 1) {
                Log.e(TAG, "detectLargestBoundary(). Abnormal resultCode!!! resultCode: " + i2);
                if (i2 == 500) {
                    this.mSource.setException(new ResultException(500));
                } else {
                    this.mSource.setException(new ResultException(2000, Integer.toString(i2)));
                }
            } else {
                Boundary rect = Boundary.create().setRect((Rect) b.getParcelable("rect"));
                this.mLargestBoundary = rect;
                this.mSource.setResult(rect);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_IMAGE_GET_LARGEST_BOUNDARY;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public void setUri(Uri uri) {
        this.mUri = uri;
    }
}
