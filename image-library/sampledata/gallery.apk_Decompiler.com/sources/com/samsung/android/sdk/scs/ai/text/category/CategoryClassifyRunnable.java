package com.samsung.android.sdk.scs.ai.text.category;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import c0.C0086a;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.text.TextServiceExecutor;
import com.samsung.android.sdk.scs.ai.text.category.DocumentCategoryClassifier;
import com.samsung.android.sdk.scs.base.ResultException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryClassifyRunnable extends TaskRunnable<List<DocumentCategory>> {
    private static final String TAG = "ScsApi@CategoryClassifyRunnable";
    private DocumentCategoryClassifier.ClassifyOptions mOptions;
    private TextServiceExecutor mServiceExecutor;
    private ArrayList<String> mTextList;

    public CategoryClassifyRunnable(TextServiceExecutor textServiceExecutor) {
        this.mServiceExecutor = textServiceExecutor;
    }

    public void execute() {
        ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
        Bundle bundle = new Bundle();
        bundle.putString("language", this.mOptions.getLanguage());
        bundle.putString("requestType", this.mOptions.getRequestType());
        bundle.putString(BuddyContract.Address.COUNTRY, this.mOptions.getCountry());
        bundle.putStringArrayList("string", this.mTextList);
        Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getCategoryClassify", (String) null, bundle);
        if (call == null) {
            Log.e(TAG, "DocumentCategoryClassifier.classify(). ContentResolver result is null!!");
            C0086a.t(5, "ContentResolver result is null", this.mSource);
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
            long[] longArray = call.getLongArray("categoryIdList");
            ArrayList<String> stringArrayList = call.getStringArrayList("categoryNameList");
            double[] doubleArray = call.getDoubleArray("categoryScoreArray");
            if (longArray == null || stringArrayList == null || doubleArray == null) {
                Log.e(TAG, "null!! categoryIdArray: " + Arrays.toString(longArray) + ", categoryNameList: " + stringArrayList + ", categoryScoreArray: " + Arrays.toString(doubleArray));
                C0086a.t(2000, "bundle content is null", this.mSource);
                return;
            }
            int length = longArray.length;
            if (length == stringArrayList.size() && length == doubleArray.length) {
                ArrayList arrayList = new ArrayList();
                for (int i7 = 0; i7 < length; i7++) {
                    DocumentCategory documentCategory = new DocumentCategory();
                    documentCategory.setId(Long.valueOf(longArray[i7]));
                    documentCategory.setName(stringArrayList.get(i7));
                    documentCategory.setScore(doubleArray[i7]);
                    arrayList.add(documentCategory);
                }
                this.mSource.setResult(arrayList);
                return;
            }
            StringBuilder o2 = C0086a.o(length, "unexpected size!!! : ", " vs ");
            C0086a.A(o2, stringArrayList, " vs ");
            o2.append(doubleArray.length);
            Log.e(TAG, o2.toString());
            C0086a.t(2000, "list size mismatched", this.mSource);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_TEXT_GET_DOCUMENT_CATEGORY;
    }

    public void setOptions(DocumentCategoryClassifier.ClassifyOptions classifyOptions) {
        this.mOptions = classifyOptions;
        if (classifyOptions.getLanguage() == null) {
            classifyOptions.setLanguage(Locale.getDefault().getLanguage());
        }
    }

    public void setTextList(ArrayList<String> arrayList) {
        this.mTextList = arrayList;
    }
}
