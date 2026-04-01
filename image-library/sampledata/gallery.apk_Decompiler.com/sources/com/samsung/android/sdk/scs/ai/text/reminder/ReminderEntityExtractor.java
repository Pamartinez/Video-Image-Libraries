package com.samsung.android.sdk.scs.ai.text.reminder;

import Tc.a;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import c0.C0086a;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.text.TextServiceExecutor;
import com.samsung.android.sdk.scs.ai.text.reminder.ReminderEntity;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.Def;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReminderEntityExtractor {
    private static final String TAG = "ScsApi@ReminderEntityExtractor";
    private final boolean isReminderEntityExtractorSupported;
    private final TextServiceExecutor mServiceExecutor;

    public ReminderEntityExtractor(Context context) {
        boolean z;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_REMINDER_ENTITY) == 0) {
            z = true;
        } else {
            z = false;
        }
        this.isReminderEntityExtractorSupported = z;
        Log.d(TAG, "initConnection");
        this.mServiceExecutor = new TextServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isSupported$0(String str) {
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putString("language", str);
            Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getReminderEntitySupported", (String) null, bundle);
            if (call == null) {
                Log.e(TAG, "ReminderEntityExtractor.isSupported(). ContentResolver result is null!!");
                return Boolean.FALSE;
            } else if (call.isEmpty()) {
                Log.e(TAG, "ReminderEntityExtractor.isSupported(). result is empty!!");
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

    /* access modifiers changed from: private */
    public /* synthetic */ Bundle lambda$requestExtract$1(String str, String str2) {
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putString("language", str);
            bundle.putString("string", str2);
            return textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getReminderEntity", (String) null, bundle);
        } catch (Exception e) {
            Log.e(TAG, "Exception :: requestExtract", e);
            return null;
        }
    }

    private Bundle requestExtract(String str, String str2) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new a((Object) this, (Object) str2, (Object) str, 4));
        try {
            Bundle bundle = (Bundle) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            newSingleThreadExecutor.shutdownNow();
            return bundle;
        } catch (TimeoutException e) {
            submit.cancel(true);
            Log.e(TAG, "Timeout in requestExtract : " + e.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Exception e7) {
            Log.e(TAG, "Exception occurred in requestExtract : " + e7.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Throwable th) {
            newSingleThreadExecutor.shutdownNow();
            throw th;
        }
    }

    public ReminderEntity extract(String str, String str2) {
        Log.d(TAG, "ReminderEntity extract - language: " + str2);
        if (!this.isReminderEntityExtractorSupported) {
            Log.e(TAG, "Feature.FEATURE_TEXT_GET_REMINDER_ENTITY not supported!");
            return null;
        }
        Bundle requestExtract = requestExtract(str, str2);
        if (requestExtract == null) {
            Log.e(TAG, "ReminderEntityExtractor.extract(). ContentResolver result is null!!");
            return null;
        }
        int i2 = requestExtract.getInt(OCRServiceConstant.KEY_RESULT_CODE);
        if (i2 == 1) {
            return new ReminderEntity(ReminderEntity.Place.valueOf(requestExtract.getString("reminderPlace")), ReminderEntity.State.valueOf(requestExtract.getString("reminderState")), requestExtract.getInt("startIndex"), requestExtract.getInt("endIndex"));
        }
        C0086a.u(i2, "unexpected resultCode! resultCode: ", TAG);
        return null;
    }

    public boolean isSupported(String str) {
        Boolean bool;
        Log.d(TAG, "ReminderEntityExtractor isSupported - language : " + str);
        if (!this.isReminderEntityExtractorSupported) {
            return false;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new G.a(6, this, str));
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
