package com.samsung.android.sdk.scs.ai.suggestion;

import Dc.a;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import c0.C0086a;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.base.ResultException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppCategoryDetailsRunnable extends TaskRunnable<List<AppCategoryResult>> {
    private static final String TAG = "AppCategoryDetailsRunnable";
    private List<AppCategoryResult> mAppCategoryResult;
    private Context mContext;
    private ArrayList<String> mPackageNames = new ArrayList<>();

    public AppCategoryDetailsRunnable(Context context) {
        this.mContext = context;
    }

    private void generateResult(List<a> list) {
        for (a next : list) {
            AppCategoryResult create = AppCategoryResult.create();
            create.setPackageName(next.d);
            create.setCategoryString(next.e);
            create.setCategoryId(next.f);
            this.mAppCategoryResult.add(create);
        }
    }

    public void execute() {
        Log.i(TAG, "execute() called");
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("packageNames", this.mPackageNames);
        Bundle call = contentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.suggestion"), "methodSuggestAppCategoryDetails", (String) null, bundle);
        if (call == null) {
            Log.e(TAG, "getAppCategories ContentResolver result is null!!");
            C0086a.t(5, "ContentResolver is null", this.mSource);
            return;
        }
        call.setClassLoader(a.class.getClassLoader());
        int i2 = call.getInt(OCRServiceConstant.KEY_RESULT_CODE);
        if (i2 != 1) {
            C0086a.u(i2, "unexpected resultCode!!! resultCode: ", TAG);
            if (i2 == 500) {
                C0086a.s(500, this.mSource);
            } else {
                this.mSource.setException(new ResultException(2000, Integer.toString(i2)));
            }
        } else {
            ArrayList parcelableArrayList = call.getParcelableArrayList("resultSuggestAppCategoryDetails");
            if (parcelableArrayList == null) {
                Log.e(TAG, "null!! result: " + parcelableArrayList);
                C0086a.t(2000, "bundle content is null", this.mSource);
                return;
            }
            this.mAppCategoryResult = new ArrayList();
            generateResult(parcelableArrayList);
            this.mSource.setResult(this.mAppCategoryResult);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_SUGGESTION_SUGGEST_APP_CATEGORY_DETAILS;
    }

    public void setPackageNames(List<String> list) {
        this.mPackageNames.addAll(list);
    }
}
