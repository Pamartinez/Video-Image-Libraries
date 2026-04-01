package com.samsung.android.gallery.module.lottie.service;

import A2.d;
import A4.C0366a;
import A9.a;
import A9.b;
import N2.j;
import c0.C0086a;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapTemplateBinder;
import com.samsung.android.gallery.module.lottie.recorder.RecordingOperation;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefFileCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MemoryUtils;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.scsp.media.file.FileApiContract;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import org.json.JSONException;
import org.json.JSONObject;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapVideoMaker {
    final JSONObject FAIL;
    final JSONObject SUCCESS;
    String json;
    RecapTemplateBinder mBinder;
    private AtomicBoolean mInterrupted = new AtomicBoolean(false);
    long mPss;
    RecordingOperation mRecorder;
    private boolean mSavePublic;

    public RecapVideoMaker(String str) {
        JSONObject jSONObject = new JSONObject();
        this.FAIL = jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        this.SUCCESS = jSONObject2;
        this.json = str;
        try {
            jSONObject.put("result", "fail");
            jSONObject.put(OCRServiceConstant.KEY_RESULT_CODE, "-1");
            jSONObject2.put("result", "success");
            jSONObject2.put(OCRServiceConstant.KEY_RESULT_CODE, "1");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void addFailReason(String str) {
        try {
            this.FAIL.put(KeywordInfo.EXTRA_BUNDLE_KEY_REASON, str);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public void addSefFileTypes(File file) {
        try {
            byte[] array = ByteBuffer.allocate(4).putInt(this.mBinder.getType()).array();
            SefFileCompat sefFileCompat = SeApiCompat.getSefFileCompat();
            SefInfo sefInfo = SefInfo.RECAP_SAVED_VIDEO;
            sefFileCompat.addData(file, sefInfo.keyName, array, sefInfo.keyCode);
        } catch (IOException unused) {
            Log.e("RecapVideoMaker", "fail set sef");
        }
    }

    private String getTargetFilePath() {
        if (this.mSavePublic) {
            return getPublicTargetPath();
        }
        FileUtils.makeDirectoryIfAbsent((File) new SecureFile("/data/sec/gallery/recap"));
        return new FileNameBuilder("/data/sec/gallery/recap/recapMovie.mp4").setDateTime().buildUnique();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createVideo$1(Consumer consumer, w wVar, String str) {
        Log.i("RecapVideoMaker", "Lottie created : " + wVar);
        if (this.mInterrupted.get() || wVar == null) {
            if (str == null) {
                str = "interrupted.2";
            }
            sendFailResponse(consumer, str);
            return;
        }
        SimpleThreadPool.getInstance().execute(new b(this, wVar, consumer, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createVideo$2(Consumer consumer) {
        this.mBinder = new RecapTemplateBinder();
        try {
            boolean data = this.mBinder.setData(new JSONObject(this.json));
            if (this.mInterrupted.get()) {
                sendFailResponse(consumer, "interrupted.1");
            } else if (data) {
                this.mBinder.createLottie(new a(0, this, consumer), this.mInterrupted);
            } else {
                Log.e("RecapVideoMaker", "fail : " + this.json);
                sendFailResponse(consumer, "not enough contents");
            }
        } catch (JSONException e) {
            Log.e("RecapVideoMaker", "fail create json");
            sendFailResponse(consumer, "JsonParseError" + e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$startRecord$4(Integer num) {
        return this.mBinder.needFrameDrop(num);
    }

    /* access modifiers changed from: private */
    public void sendFailResponse(Consumer<JSONObject> consumer, String str) {
        addFailReason(str);
        consumer.accept(this.FAIL);
        RecordingOperation recordingOperation = this.mRecorder;
        if (recordingOperation != null) {
            recordingOperation.setProgressListener((Consumer<Float>) null);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: startRecord */
    public void lambda$createVideo$0(w wVar, Consumer<JSONObject> consumer) {
        final long currentTimeMillis = System.currentTimeMillis();
        Log.d("RecapVideoMaker", "start record");
        final SecureFile secureFile = new SecureFile(getTargetFilePath());
        final Consumer<JSONObject> consumer2 = consumer;
        RecordingOperation recordingOperation = new RecordingOperation(wVar, secureFile, new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                StringBuilder j2 = j.j(MemoryUtils.getCachedPssMegaBytes() - RecapVideoMaker.this.mPss, "end record. +", " MB, elapsed=");
                j2.append(Logger.vt(currentTimeMillis));
                Log.i("RecapVideoMaker", j2.toString());
                if (bool.booleanValue()) {
                    RecapVideoMaker.this.sendFailResponse(consumer2, "interrupted.3");
                    return;
                }
                RecapVideoMaker.this.addSefFileTypes(secureFile);
                try {
                    RecapVideoMaker recapVideoMaker = RecapVideoMaker.this;
                    recapVideoMaker.SUCCESS.put("timestamp", recapVideoMaker.mBinder.dataTimeStamp());
                    RecapVideoMaker recapVideoMaker2 = RecapVideoMaker.this;
                    recapVideoMaker2.SUCCESS.put("type", recapVideoMaker2.mBinder.getType());
                    RecapVideoMaker.this.SUCCESS.put(FileApiContract.Parameter.PATH, secureFile.getAbsolutePath());
                    RecapVideoMaker recapVideoMaker3 = RecapVideoMaker.this;
                    recapVideoMaker3.SUCCESS.put("usedFileIds", recapVideoMaker3.mBinder.getUsedFileIds());
                    consumer2.accept(RecapVideoMaker.this.SUCCESS);
                    RecordingOperation recordingOperation = RecapVideoMaker.this.mRecorder;
                    if (recordingOperation != null) {
                        recordingOperation.setProgressListener((Consumer<Float>) null);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        this.mRecorder = recordingOperation;
        recordingOperation.setProgressListener(new C0366a(16));
        this.mRecorder.setFrameDropper(new A5.a(1, this));
        try {
            this.mRecorder.start();
        } catch (Exception unused) {
            sendFailResponse(consumer2, "record fail");
        }
    }

    public void createVideo(Consumer<JSONObject> consumer) {
        this.mPss = MemoryUtils.getCachedPssMegaBytes();
        SimpleThreadPool.getInstance().execute(new d(8, this, consumer));
    }

    public String getPublicTargetPath() {
        String languageCountry = AppResources.getLanguageCountry();
        String name = this.mBinder.getName();
        StringBuilder sb2 = new StringBuilder();
        C0086a.z(sb2, StorageInfo.getDefault().movies, "/[", languageCountry, "_");
        sb2.append(name.replace("RecapTemplate", ""));
        sb2.append("]_Recap.mp4");
        return new FileNameBuilder(sb2.toString()).setDateTime().buildUnique();
    }

    public void interrupt() {
        RecordingOperation recordingOperation = this.mRecorder;
        if (recordingOperation != null) {
            recordingOperation.cancel();
        }
        this.mInterrupted.set(true);
    }

    public boolean isSuccess(JSONObject jSONObject) {
        if (this.mInterrupted.get() || !this.SUCCESS.equals(jSONObject)) {
            return false;
        }
        return true;
    }

    public void savePublic() {
        this.mSavePublic = true;
    }
}
