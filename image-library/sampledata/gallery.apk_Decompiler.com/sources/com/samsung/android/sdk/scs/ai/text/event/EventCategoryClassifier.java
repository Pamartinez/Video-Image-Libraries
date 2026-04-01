package com.samsung.android.sdk.scs.ai.text.event;

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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EventCategoryClassifier {
    private static final String TAG = "ScsApi@EventCategoryClassifier";
    private final boolean isCECSupported;
    private final TextServiceExecutor mServiceExecutor;

    public EventCategoryClassifier(Context context) {
        boolean z;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_EVENT_CATEGORY) == 0) {
            z = true;
        } else {
            z = false;
        }
        this.isCECSupported = z;
        Log.d(TAG, "initConnection");
        this.mServiceExecutor = new TextServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List lambda$classify$0(ArrayList arrayList) {
        ArrayList<String> arrayList2 = null;
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("string", arrayList);
            Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getEventCategory", (String) null, bundle);
            if (call == null) {
                Log.e(TAG, "EventCategoryClassifier.classify(). ContentResolver result is null!!");
                return null;
            }
            int i2 = call.getInt(OCRServiceConstant.KEY_RESULT_CODE);
            if (i2 != 1) {
                Log.e(TAG, "unexpected resultCode!!! resultCode: " + i2);
                return null;
            }
            ArrayList<String> stringArrayList = call.getStringArrayList("eventCategoryList");
            if (stringArrayList != null) {
                return stringArrayList;
            }
            try {
                Log.e(TAG, "null!!");
                return null;
            } catch (Exception e) {
                arrayList2 = stringArrayList;
                e = e;
                Log.e(TAG, "Exception :: classify", e);
                return arrayList2;
            }
        } catch (Exception e7) {
            e = e7;
            Log.e(TAG, "Exception :: classify", e);
            return arrayList2;
        }
    }

    public List<String> classify(ArrayList<String> arrayList) {
        if (!this.isCECSupported) {
            Log.e(TAG, "Feature.FEATURE_TEXT_GET_EVENT_CATEGORY not supported!");
            return null;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new a(4, this, arrayList));
        try {
            List<String> list = (List) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            newSingleThreadExecutor.shutdownNow();
            return list;
        } catch (TimeoutException e) {
            submit.cancel(true);
            Log.e(TAG, "Timeout in classify : " + e.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Exception e7) {
            Log.e(TAG, "Exception occurred in classify : " + e7.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Throwable th) {
            newSingleThreadExecutor.shutdownNow();
            throw th;
        }
    }
}
