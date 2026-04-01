package com.samsung.android.sdk.scs.ai.text.event;

import G.a;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.text.TextServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.Def;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EventExtractor {
    private static final String TAG = "ScsApi@EventExtractor";
    private final boolean isEventExtractorSupported;
    private final boolean isEventHasYearMonthDaySupported;
    private final boolean isEventIndexSupported;
    private TextServiceExecutor mServiceExecutor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SourceType {
        DEFAULT,
        TEXT_SELECTION
    }

    public EventExtractor(Context context) {
        boolean z;
        boolean z3;
        boolean z7 = false;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_EVENT) == 0) {
            z = true;
        } else {
            z = false;
        }
        this.isEventExtractorSupported = z;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_EVENT_HAS_YEAR_MONTH_DAY) == 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.isEventHasYearMonthDaySupported = z3;
        this.isEventIndexSupported = Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_EVENT_INDEX) == 0 ? true : z7;
        Log.d(TAG, "initConnection");
        this.mServiceExecutor = new TextServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isSupported$0(String str) {
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putString("language", str);
            Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getEventSupported", (String) null, bundle);
            if (call == null) {
                Log.e(TAG, "EventExtractor.isSupported(). ContentResolver result is null!!");
                return Boolean.FALSE;
            } else if (call.isEmpty()) {
                Log.e(TAG, "EventExtractor.isSupported(). result is empty!!");
                return Boolean.FALSE;
            } else {
                int i2 = call.getInt(OCRServiceConstant.KEY_RESULT_CODE);
                if (i2 == 1) {
                    return Boolean.valueOf(call.getBoolean("textSupportedBoolean"));
                }
                Log.e(TAG, "unexpected resultCode!!! resultCode: " + i2);
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception :: isSupported", e);
            return Boolean.FALSE;
        }
    }

    public Task<List<Event>> extract(String str, String str2, SourceType sourceType) {
        Log.d(TAG, "EventExtractor extract");
        EventExtractRunnable eventExtractRunnable = new EventExtractRunnable(this.mServiceExecutor);
        eventExtractRunnable.setLanguage(str);
        eventExtractRunnable.setText(str2);
        eventExtractRunnable.setSourceType(sourceType);
        eventExtractRunnable.setEventHasYearMonthDaySupported(this.isEventHasYearMonthDaySupported);
        eventExtractRunnable.setEventIndexSupported(this.isEventIndexSupported);
        this.mServiceExecutor.execute(eventExtractRunnable);
        return eventExtractRunnable.getTask();
    }

    public boolean isSupported(String str) {
        Boolean bool;
        Log.d(TAG, "EventExtractor isSupported - language : " + str);
        if (!this.isEventExtractorSupported) {
            return false;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new a(5, this, str));
        try {
            bool = (Boolean) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            submit.cancel(true);
            Log.e(TAG, "Timeout in isSupported : " + e.getMessage());
            bool = Boolean.FALSE;
        } catch (Exception e7) {
            Log.e(TAG, "Exception occurred in isSupported : " + e7.getMessage());
            bool = Boolean.FALSE;
        } catch (Throwable th) {
            newSingleThreadExecutor.shutdownNow();
            throw th;
        }
        newSingleThreadExecutor.shutdownNow();
        return bool.booleanValue();
    }
}
