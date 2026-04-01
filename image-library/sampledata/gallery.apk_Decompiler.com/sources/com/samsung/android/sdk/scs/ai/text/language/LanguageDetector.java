package com.samsung.android.sdk.scs.ai.text.language;

import G.a;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.text.TextServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.Def;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LanguageDetector {
    private static final String TAG = "ScsApi@LanguageDetector";
    private final boolean isLDSupported;
    private TextServiceExecutor mServiceExecutor;

    public LanguageDetector(Context context) {
        boolean z;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_DETECT_LANGUAGE) == 0) {
            z = true;
        } else {
            z = false;
        }
        this.isLDSupported = z;
        Log.d(TAG, "initConnection");
        this.mServiceExecutor = new TextServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$detect$0(String str) {
        String str2 = "";
        int length = str.length();
        if (length > 100000) {
            Log.i(TAG, String.format("Language detect input length(%d) exceed MAX_VAL(%d), so cut to %d", new Object[]{Integer.valueOf(length), 100000, 100000}));
            str = str.substring(0, 100000);
        }
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putString("string", str);
            Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "detectLanguage", (String) null, bundle);
            if (call == null) {
                Log.e(TAG, "LanguageDetector.detect(). ContentResolver result is null!!");
                return str2;
            }
            int i2 = call.getInt(OCRServiceConstant.KEY_RESULT_CODE);
            if (i2 != 1) {
                Log.e(TAG, "unexpected resultCode!!! resultCode: " + i2);
                return str2;
            }
            String string = call.getString("detectedLanguageText");
            if (string != null) {
                try {
                    if (string.length() != 0) {
                        return string;
                    }
                } catch (Exception e) {
                    str2 = string;
                    e = e;
                    Log.e(TAG, "Exception :: detect", e);
                    return str2;
                }
            }
            Log.e(TAG, "null!! detectedLanguage: " + string);
            return str2;
        } catch (Exception e7) {
            e = e7;
            Log.e(TAG, "Exception :: detect", e);
            return str2;
        }
    }

    public String detect(String str) {
        if (!this.isLDSupported) {
            Log.e(TAG, "Feature.FEATURE_TEXT_DETECT_LANGUAGE not supported!");
            return "";
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new a(1, this, str));
        try {
            String str2 = (String) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            newSingleThreadExecutor.shutdownNow();
            return str2;
        } catch (TimeoutException e) {
            submit.cancel(true);
            Log.e(TAG, "Timeout in detect : " + e.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return "";
        } catch (Exception e7) {
            Log.e(TAG, "Exception occurred in detect : " + e7.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return "";
        } catch (Throwable th) {
            newSingleThreadExecutor.shutdownNow();
            throw th;
        }
    }
}
