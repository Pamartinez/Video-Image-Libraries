package com.samsung.android.sdk.scs.ai.suggestion;

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
public class FolderNameSuggestionRunnable extends TaskRunnable<List<String>> {
    private static final String TAG = "FolderNameSuggestionRunnable";
    private Context mContext;
    private ArrayList<String> mPackageNames = new ArrayList<>();

    public FolderNameSuggestionRunnable(Context context) {
        this.mContext = context;
    }

    public void execute() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("packageNames", this.mPackageNames);
        Bundle call = contentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.suggestion"), "methodSuggestFolderName", (String) null, bundle);
        if (call == null) {
            Log.e(TAG, "getSuggestedFolderNameForApps ContentResolver result is null!!");
            C0086a.t(5, "ContentResolver is null", this.mSource);
            return;
        }
        int i2 = call.getInt(OCRServiceConstant.KEY_RESULT_CODE);
        if (i2 != 1) {
            C0086a.u(i2, "unexpected resultCode!!! resultCode: ", TAG);
            if (i2 == 500) {
                C0086a.s(500, this.mSource);
            } else {
                this.mSource.setException(new ResultException(2000, Integer.toString(i2)));
            }
        } else {
            ArrayList<String> stringArrayList = call.getStringArrayList("resultSuggestFolderName");
            if (stringArrayList == null) {
                Log.e(TAG, "null!! result: " + stringArrayList);
                C0086a.t(2000, "bundle content is null", this.mSource);
                return;
            }
            this.mSource.setResult(stringArrayList);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_SUGGESTION_SUGGEST_FOLDER_NAME;
    }

    public void setPackageNames(List<String> list) {
        this.mPackageNames.addAll(list);
    }
}
