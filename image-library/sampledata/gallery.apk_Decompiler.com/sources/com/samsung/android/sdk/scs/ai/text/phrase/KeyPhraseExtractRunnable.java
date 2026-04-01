package com.samsung.android.sdk.scs.ai.text.phrase;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import c0.C0086a;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.text.TextServiceExecutor;
import com.samsung.android.sdk.scs.ai.text.phrase.KeyPhraseExtractor;
import com.samsung.android.sdk.scs.base.ResultException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeyPhraseExtractRunnable extends TaskRunnable<List<KeyPhrase>> {
    private static final String TAG = "ScsApi@KeyPhraseExtractRunnable";
    private KeyPhraseExtractor.ExtractOptions mOptions;
    private String mRequestType;
    private TextServiceExecutor mServiceExecutor;
    private String mText;

    public KeyPhraseExtractRunnable(TextServiceExecutor textServiceExecutor, String str) {
        this.mRequestType = str;
        this.mServiceExecutor = textServiceExecutor;
    }

    public void execute() {
        ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
        Bundle bundle = new Bundle();
        bundle.putString("language", this.mOptions.getLanguage());
        bundle.putString("requestType", this.mOptions.getRequestType());
        bundle.putString(ActionHandler.ACTION_TYPE, this.mOptions.getActionType());
        bundle.putSerializable("userTags", (Serializable) this.mOptions.getUserTags());
        bundle.putSerializable("extraInfo", (Serializable) this.mOptions.getExtraInfo());
        bundle.putInt("topK", this.mOptions.getTopK());
        bundle.putString("string", this.mText);
        Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getKeyPhrase", (String) null, bundle);
        if (call == null) {
            Log.e(TAG, "KeyPhraseExtractor.extract(). ContentResolver result is null!!");
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
            ArrayList<String> stringArrayList = call.getStringArrayList("phraseTextList");
            double[] doubleArray = call.getDoubleArray("phraseScoreArray");
            if (stringArrayList == null || doubleArray == null) {
                Log.e(TAG, "null!! keyPhraseTextList: " + stringArrayList + ", keyPhraseScoreArray: " + Arrays.toString(doubleArray));
                C0086a.t(2000, "bundle content is null", this.mSource);
                return;
            }
            int size = stringArrayList.size();
            if (size != doubleArray.length) {
                StringBuilder o2 = C0086a.o(size, "unexpected size!!! : ", " vs ");
                o2.append(doubleArray.length);
                Log.e(TAG, o2.toString());
                C0086a.t(2000, "list size mismatched", this.mSource);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (int i7 = 0; i7 < size; i7++) {
                KeyPhrase keyPhrase = new KeyPhrase();
                keyPhrase.setString(stringArrayList.get(i7));
                keyPhrase.setScore(doubleArray[i7]);
                arrayList.add(keyPhrase);
            }
            this.mSource.setResult(arrayList);
        }
    }

    public String getFeatureName() {
        if (KeyPhraseExtractor.RequestType.EVENT_TITLE.name().equals(this.mRequestType)) {
            return Feature.FEATURE_TEXT_GET_KEY_PHRASE_EVENT_TITLE;
        }
        return Feature.FEATURE_TEXT_GET_KEY_PHRASE;
    }

    public void setOptions(KeyPhraseExtractor.ExtractOptions extractOptions) {
        this.mOptions = extractOptions;
        if (extractOptions.getLanguage() == null) {
            extractOptions.setLanguage(Locale.getDefault().getLanguage());
        }
    }

    public void setText(String str) {
        this.mText = str;
    }
}
