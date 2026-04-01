package com.samsung.android.sdk.scs.ai.image;

import Cc.a;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.base.ResultException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetectRunnable extends TaskRunnable<List<Boundary>> {
    private static final String TAG = "ScsApi@DetectLargestRunnable";
    Bitmap mBitmap;
    List<Boundary> mBoundaryList;
    ImageServiceExecutor mServiceExecutor;
    Uri mUri;

    public DetectRunnable(ImageServiceExecutor imageServiceExecutor) {
        this.mServiceExecutor = imageServiceExecutor;
    }

    private void generateResult(List<a> list) {
        for (a next : list) {
            ArrayList arrayList = next.f;
            if (arrayList != null) {
                generateResult(arrayList);
            }
            this.mBoundaryList.add(Boundary.create().setRect(next.d).setType(BoundaryType.get(next.e)));
        }
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
            Bundle a7 = ((Bc.a) this.mServiceExecutor.getImageService()).a(bundle);
            if (a7 == null) {
                Log.e(TAG, "detectBoundaries(). retBundle is null!!");
                this.mSource.setException(new ResultException(5, "retBundle is null"));
                return;
            }
            a7.setClassLoader(a.class.getClassLoader());
            int i2 = a7.getInt(OCRServiceConstant.KEY_RESULT_CODE);
            if (i2 != 1) {
                Log.e(TAG, "detectBoundaries(). Abnormal resultCode!!! resultCode: " + i2);
                if (i2 == 500) {
                    this.mSource.setException(new ResultException(500));
                } else {
                    this.mSource.setException(new ResultException(2000, Integer.toString(i2)));
                }
            } else {
                ArrayList parcelableArrayList = a7.getParcelableArrayList("boundaryList");
                this.mBoundaryList = new ArrayList();
                generateResult(parcelableArrayList);
                this.mSource.setResult(this.mBoundaryList);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_IMAGE_GET_BOUNDARIES;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public void setUri(Uri uri) {
        this.mUri = uri;
    }
}
