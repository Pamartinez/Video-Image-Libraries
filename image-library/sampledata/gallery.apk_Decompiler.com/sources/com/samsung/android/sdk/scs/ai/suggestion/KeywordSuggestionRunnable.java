package com.samsung.android.sdk.scs.ai.suggestion;

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
public class KeywordSuggestionRunnable extends TaskRunnable<List<Keyword>> {
    private static final String TAG = "ScsApi@KeywordSuggestionRunnable";
    private Context mContext;
    private int mCount;
    private boolean mIsLocationGranted;
    private KeywordSuggestionType mSuggestionType;

    public KeywordSuggestionRunnable(Context context, KeywordSuggestionType keywordSuggestionType, int i2, boolean z) {
        this.mContext = context;
        this.mSuggestionType = keywordSuggestionType;
        this.mCount = i2;
        this.mIsLocationGranted = z;
    }

    public void execute() {
        Log.d(TAG, "run");
        Bundle bundle = new Bundle();
        bundle.putInt("count", this.mCount);
        bundle.putBoolean("locationAccess", this.mIsLocationGranted);
        bundle.putString("client", this.mSuggestionType.getType());
        try {
            Bundle call = this.mContext.getContentResolver().call(Uri.parse("content://com.samsung.android.scs.ai.suggestion"), "suggest", (String) null, bundle);
            if (call == null) {
                Log.e(TAG, "returnBundle is null");
                C0086a.t(5, "returnBundle is null", this.mSource);
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
                ArrayList<String> stringArrayList = call.getStringArrayList("queryStringList");
                ArrayList<String> stringArrayList2 = call.getStringArrayList("tagTypeList");
                if (stringArrayList == null || stringArrayList2 == null) {
                    Log.e(TAG, "null!! queryStringList: " + stringArrayList + ", tagTypeList: " + stringArrayList2);
                    C0086a.t(2000, "bundle content is null", this.mSource);
                    return;
                }
                int size = stringArrayList.size();
                if (size != stringArrayList2.size()) {
                    StringBuilder o2 = C0086a.o(size, "unexpected size!!! : ", " vs ");
                    o2.append(stringArrayList2.size());
                    Log.e(TAG, o2.toString());
                    C0086a.t(2000, "list size mismatched", this.mSource);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (int i7 = 0; i7 < size; i7++) {
                    arrayList.add(new Keyword(stringArrayList.get(i7), stringArrayList2.get(i7)));
                }
                this.mSource.setResult(arrayList);
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.toString() + ", Cannot call suggestion provider");
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_SUGGESTION_SUGGEST_KEYWORD;
    }
}
