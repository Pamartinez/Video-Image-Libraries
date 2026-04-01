package com.samsung.android.gallery.module.lottie.service;

import A4.C0366a;
import A9.c;
import A9.d;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ForegroundInfo;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.service.notification.RecapNotificationHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.parameter.GPPInterfaceKey;
import com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapWorker extends Worker {
    private static final List<String> SUPPORTED_LOCALES = Arrays.asList(new String[]{"vi", "es", "zh", "ja", "pt", "th", "hi", "pl", "ko", "en", "de", "fr", "it"});
    static final AtomicBoolean mWork = new AtomicBoolean(false);
    static final ConcurrentHashMap<UUID, String> mWorkerMap = new ConcurrentHashMap<>();
    RecapScreenReceiver mScreenOffReceiver = new RecapScreenReceiver();
    RecapVideoMaker mVideoMaker;

    public RecapWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    public static void addWorkerId(UUID uuid, String str) {
        mWorkerMap.put(uuid, str);
    }

    private static Bundle checkUnSupportedLanguage() {
        String lowerCase = Locale.getDefault().getLanguage().toLowerCase(Locale.ENGLISH);
        Log.i("RecapWorker", "language=" + lowerCase);
        if (SUPPORTED_LOCALES.contains(lowerCase)) {
            return null;
        }
        DebugLogger recapInstance = DebugLogger.getRecapInstance();
        recapInstance.insertLog("enqueue fail. unsupported language :" + lowerCase);
        Bundle bundle = new Bundle();
        bundle.putString("result", "fail : unsupported language " + lowerCase);
        return bundle;
    }

    public static void clearOnWorksOrPending() {
        ConcurrentHashMap<UUID, String> concurrentHashMap = mWorkerMap;
        if (!concurrentHashMap.isEmpty()) {
            concurrentHashMap.forEach(new c(0));
            concurrentHashMap.keySet().forEach(new C0366a(17));
            concurrentHashMap.clear();
            WorkManager.getInstance(AppResources.getAppContext()).cancelAllWorkByTag("RecapWorker");
            DebugLogger.getRecapInstance().insertLog("cancelAllWork");
        }
    }

    private ForegroundInfo createForegroundInfo(String str) {
        RecapNotificationHelper recapNotificationHelper = new RecapNotificationHelper(getApplicationContext(), "RecapWorker", RecapWorker.class.getName());
        recapNotificationHelper.create("RecapWorker");
        return new ForegroundInfo(recapNotificationHelper.getId(), recapNotificationHelper.getStartBuilder(getApplicationContext(), str).build(), 1073741824);
    }

    private ListenableWorker.Result exec() {
        String string = getInputData().getString(GPPInterfaceKey.JSON_KEY_SEF_DATA_FILE_PATH);
        if (string == null || !FileUtils.exists(string)) {
            Log.i("RecapWorker", "no file : " + string);
            return getFailure("no data file");
        }
        boolean z = getInputData().getBoolean("save_public", false);
        String string2 = getInputData().getString("recap_title");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        byte[] readFile = FileUtils.readFile(string);
        FileUtils.delete(string);
        if (readFile != null) {
            setForegroundAsync(createForegroundInfo(string2));
            RecapVideoMaker recapVideoMaker = new RecapVideoMaker(new String(readFile));
            this.mVideoMaker = recapVideoMaker;
            if (z) {
                recapVideoMaker.savePublic();
            }
            AtomicReference atomicReference = new AtomicReference();
            AtomicReference atomicReference2 = new AtomicReference();
            this.mVideoMaker.createVideo(new d(this, atomicReference2, z, atomicReference, countDownLatch, 0));
            try {
                if (countDownLatch.await(10, TimeUnit.MINUTES)) {
                    String str = (String) atomicReference.get();
                    if (str == null) {
                        return getSuccess((String) atomicReference2.get());
                    }
                    return getFailure(str);
                }
                Log.e("RecapWorker", "MSG_REQUEST_CREATE video create timeout");
                DebugLogger recapInstance = DebugLogger.getRecapInstance();
                recapInstance.insertLog("fail " + getId() + ", timeout");
                return getFailure("timeout");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            Log.e("RecapWorker", "MSG_REQUEST_CREATE fail. no data.");
            DebugLogger recapInstance2 = DebugLogger.getRecapInstance();
            recapInstance2.insertLog("fail " + getId() + ", no data");
            return getFailure("no data");
        }
    }

    private static ListenableWorker.Result getFailure(String str) {
        return ListenableWorker.Result.failure(new Data.Builder().putString(KeywordInfo.EXTRA_BUNDLE_KEY_REASON, str).build());
    }

    private static ListenableWorker.Result getSuccess(String str) {
        return ListenableWorker.Result.success(new Data.Builder().putString("type", str).build());
    }

    public static Bundle init(Context context, String str, Bundle bundle, boolean z) {
        Bundle checkUnSupportedLanguage = checkUnSupportedLanguage();
        if (checkUnSupportedLanguage != null) {
            return checkUnSupportedLanguage;
        }
        clearOnWorksOrPending();
        String str2 = FileUtils.getExternalFilesDir("tmp") + "/recap.json";
        if (!TextUtils.isEmpty(str)) {
            FileUtils.writeFile(str2, str.getBytes(StandardCharsets.UTF_8));
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(RecapWorker.class);
        builder.setConstraints(new Constraints.Builder().build());
        builder.setInputData(new Data.Builder().putString(GPPInterfaceKey.JSON_KEY_SEF_DATA_FILE_PATH, str2).putString("recap_title", bundle.getString("title", context.getString(R$string.recap))).putBoolean("save_public", z).build());
        builder.addTag("RecapWorker");
        builder.setTraceTag("RecapWorker");
        OneTimeWorkRequest oneTimeWorkRequest = (OneTimeWorkRequest) builder.build();
        WorkManager.getInstance(context).enqueue((WorkRequest) oneTimeWorkRequest);
        UUID id = oneTimeWorkRequest.getId();
        addWorkerId(id, "queue");
        Bundle bundle2 = new Bundle();
        bundle2.putString("result", "success : " + str);
        Log.i("RecapWorker", "exec worker : " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            DebugLogger recapInstance = DebugLogger.getRecapInstance();
            recapInstance.insertLog("enqueue : " + id + ("{t:" + jSONObject.optInt("type", 0) + ",y:" + jSONObject.optInt("year", 0) + ",m:" + jSONObject.optInt("fromMonth", 0) + "}"));
            AnalyticsLogger.getInstance().open();
            AnalyticsLogger.getInstance().postCustomDimension(AnalyticsScreenId.SCREEN_NONE.toString(), AnalyticsEventId.EVENT_DEV_RECAP_VIDEO_CREATION_ENQUEUE.toString(), new Pair[]{new Pair("type", jSONObject.optString("type", "0")), new Pair("year", jSONObject.optString("year", "0")), new Pair("fromMonth", jSONObject.optString("fromMonth", "0"))});
            AnalyticsLogger.getInstance().close();
            return bundle2;
        } catch (JSONException unused) {
            DebugLogger.getRecapInstance().insertLog("enqueue : " + id);
            return bundle2;
        }
    }

    private void insertResult(JSONObject jSONObject) {
        Bundle call = AppResources.getAppContext().getContentResolver().call("com.samsung.storygenprovider", "recap_insert", jSONObject.toString(), (Bundle) null);
        Log.i("RecapWorker", "sendResponse return=" + Logger.toString(call));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$exec$0(AtomicReference atomicReference, boolean z, AtomicReference atomicReference2, CountDownLatch countDownLatch, JSONObject jSONObject) {
        if (this.mVideoMaker.isSuccess(jSONObject)) {
            insertResult(jSONObject);
            Log.i("RecapWorker", "MSG_REQUEST_CREATE success : " + Logger.getEncodedString((Object) jSONObject));
            atomicReference.set(jSONObject.optString("type"));
        } else {
            Log.w("RecapWorker", "MSG_REQUEST_CREATE fail : " + jSONObject);
            DebugLogger recapInstance = DebugLogger.getRecapInstance();
            recapInstance.insertLog("fail " + getId() + ArcCommonLog.TAG_COMMA + jSONObject);
            atomicReference2.set(jSONObject.optString(KeywordInfo.EXTRA_BUNDLE_KEY_REASON));
        }
        countDownLatch.countDown();
    }

    public ListenableWorker.Result doWork() {
        AtomicBoolean atomicBoolean = mWork;
        if (atomicBoolean.compareAndSet(false, true)) {
            UUID id = getId();
            Log.i("RecapWorker", "doWork start : " + id);
            DebugLogger recapInstance = DebugLogger.getRecapInstance();
            recapInstance.insertLog("doWork S: " + id);
            addWorkerId(id, "work");
            AnalyticsLogger.getInstance().open();
            this.mScreenOffReceiver.register(getApplicationContext(), id);
            AnalyticsLogger instance = AnalyticsLogger.getInstance();
            AnalyticsScreenId analyticsScreenId = AnalyticsScreenId.SCREEN_NONE;
            instance.postLog(analyticsScreenId.toString(), AnalyticsEventId.EVENT_DEV_RECAP_VIDEO_CREATION_START.toString());
            ListenableWorker.Result exec = exec();
            AnalyticsLogger.getInstance().postLog(analyticsScreenId.toString(), AnalyticsEventId.EVENT_DEV_RECAP_VIDEO_CREATION_END.toString(), exec.toString().replace("{mOutputData=Data {", "").replace("}}", ""));
            this.mScreenOffReceiver.unregister(getApplicationContext());
            AnalyticsLogger.getInstance().close();
            Log.i("RecapWorker", "doWork end : " + id);
            mWorkerMap.remove(id);
            atomicBoolean.set(false);
            DebugLogger recapInstance2 = DebugLogger.getRecapInstance();
            recapInstance2.insertLog("doWork E: " + id + ArcCommonLog.TAG_COMMA + exec);
            return exec;
        }
        Log.i("RecapWorker", "doWork fail");
        Log.e("RecapWorker", "MSG_REQUEST_CREATE fail. already running");
        DebugLogger recapInstance3 = DebugLogger.getRecapInstance();
        recapInstance3.insertLog("doWork R: " + getId());
        return ListenableWorker.Result.retry();
    }

    public void onStopped() {
        if (Build.VERSION.SDK_INT >= 31) {
            Log.w("RecapWorker", "onStopped : " + getStopReason());
            DebugLogger recapInstance = DebugLogger.getRecapInstance();
            recapInstance.insertLog("stop : " + getStopReason());
        }
        RecapScreenReceiver recapScreenReceiver = this.mScreenOffReceiver;
        if (recapScreenReceiver != null) {
            recapScreenReceiver.unregister(getApplicationContext());
        }
        RecapVideoMaker recapVideoMaker = this.mVideoMaker;
        if (recapVideoMaker != null) {
            recapVideoMaker.interrupt();
        }
        super.onStopped();
    }
}
