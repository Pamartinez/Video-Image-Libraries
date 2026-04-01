package com.samsung.android.gallery.module.story.transcode;

import A.a;
import N2.j;
import android.media.MediaMuxer;
import android.os.Handler;
import android.os.Message;
import com.samsung.android.gallery.database.dal.local.table.HiddenShareHelper;
import com.samsung.android.gallery.module.story.transcode.config.EncodeInfo;
import com.samsung.android.gallery.module.story.transcode.config.KenBurnsInfo;
import com.samsung.android.gallery.module.story.transcode.config.Mode;
import com.samsung.android.gallery.module.story.transcode.transcoder.ITranscoder;
import com.samsung.android.gallery.module.story.transcode.transcoder.VideoTranscoder;
import com.samsung.android.gallery.module.story.transcode.transcoder.audio.AudioTranscoder;
import com.samsung.android.gallery.module.story.transcode.transcoder.audio.AudioWithBgmTranscoder;
import com.samsung.android.gallery.module.story.transcode.util.CommonUtil;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import e6.C0453a;
import g6.g;
import h.C0199b;
import h4.C0464a;
import ia.C0694a;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighlightEncoder {
    private final EncodeInfo mEncodeInfo;
    private final Handler mHandler = new Handler(ThreadUtil.createBackgroundThreadLooper("HighlightEncoder")) {
        public void handleMessage(Message message) {
            try {
                int i2 = message.what;
                if (i2 == 0) {
                    HighlightEncoder.this.prepare();
                } else if (i2 == 1) {
                    HighlightEncoder.this.encodeInternal();
                } else if (i2 == 2) {
                    HighlightEncoder.this.release();
                }
            } catch (IOException e) {
                HighlightEncoder.this.handleException(e);
            }
        }
    };
    private boolean mHasError;
    private boolean mIsEncoding;
    private boolean mIsPaused = false;
    private EncodeEventListener mListener;
    private MediaMuxer mMuxer;
    private boolean mMuxerStarted;
    private String mOutputFilePath;
    private long mStartTimeMilli;
    private final Object mStopLock = new Object();
    private boolean mSuccess;
    private final ArrayList<ITranscoder> mTranscoders = new ArrayList<>();
    private boolean mUserStopped;

    public HighlightEncoder(EncodeInfo encodeInfo) {
        this.mEncodeInfo = encodeInfo;
        initialize(encodeInfo);
    }

    private String createMetaData(int i2, int i7) {
        return i2 + ";" + i7;
    }

    private void deleteFile() {
        SecureFile secureFile = new SecureFile(this.mOutputFilePath);
        if (secureFile.exists()) {
            a.v("delete encoded file [", "]", "HighlightEncoder", secureFile.delete());
        }
    }

    /* access modifiers changed from: private */
    public void encodeInternal() {
        if (isExportingStopped() || !isEncoderRunning()) {
            onEncodingDone(false);
            return;
        }
        updateProgressBar();
        Iterator<ITranscoder> it = this.mTranscoders.iterator();
        while (it.hasNext()) {
            it.next().transcode(this.mMuxer, this.mMuxerStarted);
        }
        startMuxer();
        if (!this.mIsPaused) {
            this.mHandler.sendEmptyMessage(1);
        }
    }

    private void executeFileOperation() {
        CommonUtil.updateCreationTime(this.mOutputFilePath);
        if (isHiddenShare()) {
            String str = this.mOutputFilePath;
            EncodeInfo encodeInfo = this.mEncodeInfo;
            HiddenShareHelper.insertToShareTable(str, createMetaData(encodeInfo.storyId, encodeInfo.uniqueKey), "storyHighlight");
            setComplete();
            return;
        }
        MediaScanner.scanFile(this.mOutputFilePath, new C0694a(this));
    }

    /* access modifiers changed from: private */
    public void handleException(Exception exc) {
        onError(exc);
        onEncodingDone(true);
    }

    private void initialize(EncodeInfo encodeInfo) {
        this.mOutputFilePath = encodeInfo.outFilePath;
        this.mHandler.post(new C0199b(1, this, encodeInfo));
    }

    private boolean isEncoderRunning() {
        return this.mTranscoders.stream().anyMatch(new C0464a(4));
    }

    private boolean isExportingStopped() {
        if (this.mUserStopped || this.mHasError) {
            return true;
        }
        return false;
    }

    private boolean isHiddenShare() {
        if (this.mEncodeInfo.uniqueKey != 0) {
            return true;
        }
        return false;
    }

    private boolean isTranscoderReady() {
        return this.mTranscoders.stream().allMatch(new C0464a(6));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeFileOperation$3() {
        Log.d("HighlightEncoder", "scan finished", Logger.getSimpleName((Object) this.mListener));
        setComplete();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initialize$0(EncodeInfo encodeInfo) {
        HashMap<ThumbnailInterface, KenBurnsInfo> hashMap = encodeInfo.kenBurnsMap;
        if (hashMap == null || hashMap.isEmpty()) {
            handleException(new IllegalArgumentException("invalid ken burns map"));
            return;
        }
        ArrayList arrayList = new ArrayList(encodeInfo.kenBurnsMap.keySet());
        if (supportBGM(encodeInfo)) {
            this.mTranscoders.add(new AudioWithBgmTranscoder(arrayList, encodeInfo, new C0694a(this)));
        } else {
            this.mTranscoders.add(new AudioTranscoder(arrayList, encodeInfo, new C0694a(this)));
        }
        this.mTranscoders.add(new VideoTranscoder(arrayList, encodeInfo, new C0694a(this)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startMuxer$2(ITranscoder iTranscoder) {
        iTranscoder.addTrack(this.mMuxer);
    }

    private void onEncodingDone(boolean z) {
        boolean z3;
        this.mHandler.sendEmptyMessage(2);
        if (z || isExportingStopped()) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.mSuccess = z3;
        long currentTimeMillis = System.currentTimeMillis() - this.mStartTimeMilli;
        Log.d("HighlightEncoder", "encode elapsed +" + currentTimeMillis);
    }

    private void onEncodingStarted() {
        setEncoding(true);
        EncodeEventListener encodeEventListener = this.mListener;
        if (encodeEventListener != null) {
            encodeEventListener.onStarted();
        }
        this.mStartTimeMilli = System.currentTimeMillis();
    }

    /* access modifiers changed from: private */
    public void onError(Exception exc) {
        this.mHasError = true;
        exc.printStackTrace();
        a.s(exc, new StringBuilder("onError="), "HighlightEncoder");
    }

    private void prepareDirectory() {
        if (!FileUtils.makeDirectoryIfAbsent((File) new SecureFile(FileUtils.getDirectoryFromPath(this.mOutputFilePath)))) {
            Log.d("HighlightEncoder", "failed create directory : " + Logger.getEncodedString(FileUtils.getDirectoryFromPath(this.mOutputFilePath)));
            throw new RuntimeException("fail to create folder");
        }
    }

    private void prepareMuxer() {
        this.mMuxer = new MediaMuxer(this.mOutputFilePath, 0);
        this.mMuxerStarted = false;
    }

    /* access modifiers changed from: private */
    public void release() {
        try {
            Log.d("HighlightEncoder", "releasing transcoder objects");
            TimeTickLog timeTickLog = new TimeTickLog("HighlightEncoder#release");
            this.mTranscoders.forEach(new C0453a(28));
            this.mTranscoders.clear();
            timeTickLog.tick("transcoder");
            releaseMuxer();
            timeTickLog.tick("muxer");
            Log.d("HighlightEncoder", "success ", Boolean.valueOf(this.mSuccess), Boolean.valueOf(this.mUserStopped), Boolean.valueOf(this.mHasError), Integer.valueOf(this.mEncodeInfo.storyId), Integer.valueOf(this.mEncodeInfo.uniqueKey));
            if (!isExportingStopped()) {
                executeFileOperation();
            } else {
                setComplete();
                deleteFile();
            }
            timeTickLog.tock(0);
            synchronized (this.mStopLock) {
                setEncoding(false);
                this.mStopLock.notifyAll();
                this.mHandler.getLooper().quitSafely();
            }
        } catch (Throwable th) {
            synchronized (this.mStopLock) {
                setEncoding(false);
                this.mStopLock.notifyAll();
                this.mHandler.getLooper().quitSafely();
                throw th;
            }
        }
    }

    private void releaseMuxer() {
        MediaMuxer mediaMuxer = this.mMuxer;
        if (mediaMuxer != null) {
            try {
                if (this.mMuxerStarted) {
                    mediaMuxer.stop();
                }
                this.mMuxer.release();
                this.mMuxer = null;
            } catch (Exception e) {
                Log.e("HighlightEncoder", "Exception in releasing muxer.");
                e.printStackTrace();
            }
        }
    }

    private void setComplete() {
        EncodeEventListener encodeEventListener = this.mListener;
        if (encodeEventListener != null) {
            encodeEventListener.onCompleted(this.mSuccess);
            setListener((EncodeEventListener) null);
        }
    }

    private void setEncoding(boolean z) {
        this.mIsEncoding = z;
    }

    private void startMuxer() {
        if (!this.mMuxerStarted && isTranscoderReady()) {
            this.mTranscoders.forEach(new g(4, this));
            this.mMuxer.setOrientationHint(0);
            this.mMuxer.start();
            this.mMuxerStarted = true;
        }
    }

    private boolean supportBGM(EncodeInfo encodeInfo) {
        return !Mode.MOTION_PHOTO.equals(encodeInfo.mode);
    }

    private void updateProgressBar() {
        EncodeEventListener encodeEventListener;
        if (this.mMuxerStarted && (encodeEventListener = this.mListener) != null) {
            encodeEventListener.onProgressChanged((float) (this.mTranscoders.stream().mapToDouble(new com.samsung.android.sdk.ocr.a(2)).sum() / ((double) this.mTranscoders.stream().filter(new C0464a(5)).count())));
        }
    }

    public void encode() {
        try {
            prepareDirectory();
            onEncodingStarted();
            this.mHandler.sendEmptyMessage(0);
            this.mHandler.sendEmptyMessage(1);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public String getResultPath() {
        return this.mOutputFilePath;
    }

    public void prepare() {
        TimeTickLog timeTickLog = new TimeTickLog("HighlightEncoder#prepare");
        Iterator<ITranscoder> it = this.mTranscoders.iterator();
        while (it.hasNext()) {
            ITranscoder next = it.next();
            next.prepare();
            timeTickLog.tick("prepare ".concat(next.getClass().getSimpleName()));
        }
        prepareMuxer();
        timeTickLog.tock(0);
    }

    public void setListener(EncodeEventListener encodeEventListener) {
        this.mListener = encodeEventListener;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        if (r8.mIsEncoding != false) goto L_0x0048;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stopEncoding() {
        /*
            r8 = this;
            java.lang.String r0 = "Stop method finally mEncoding :"
            java.lang.String r1 = "Stop method finally mEncoding :"
            java.lang.String r2 = "Stop method finally mEncoding :"
            java.lang.Object r3 = r8.mStopLock
            monitor-enter(r3)
            r4 = 1
            r8.mUserStopped = r4     // Catch:{ all -> 0x004e }
            r4 = 0
            r8.mSuccess = r4     // Catch:{ all -> 0x004e }
            r4 = 2
            java.lang.String r5 = "HighlightEncoder"
            java.lang.String r6 = "Calling wait on stop lock."
            com.samsung.android.gallery.support.utils.Log.e(r5, r6)     // Catch:{ InterruptedException -> 0x0052 }
            java.util.ArrayList<com.samsung.android.gallery.module.story.transcode.transcoder.ITranscoder> r5 = r8.mTranscoders     // Catch:{ InterruptedException -> 0x0052 }
            e6.a r6 = new e6.a     // Catch:{ InterruptedException -> 0x0052 }
            r7 = 27
            r6.<init>(r7)     // Catch:{ InterruptedException -> 0x0052 }
            r5.forEach(r6)     // Catch:{ InterruptedException -> 0x0052 }
            java.lang.Object r5 = r8.mStopLock     // Catch:{ InterruptedException -> 0x0052 }
            boolean r6 = r8.mIsEncoding     // Catch:{ InterruptedException -> 0x0052 }
            if (r6 == 0) goto L_0x002c
            r6 = 500(0x1f4, double:2.47E-321)
            goto L_0x002e
        L_0x002c:
            r6 = 0
        L_0x002e:
            r5.wait(r6)     // Catch:{ InterruptedException -> 0x0052 }
            java.lang.String r0 = "HighlightEncoder"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x004e }
            r1.<init>(r2)     // Catch:{ all -> 0x004e }
            boolean r2 = r8.mIsEncoding     // Catch:{ all -> 0x004e }
            r1.append(r2)     // Catch:{ all -> 0x004e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x004e }
            com.samsung.android.gallery.support.utils.Log.e(r0, r1)     // Catch:{ all -> 0x004e }
            boolean r0 = r8.mIsEncoding     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x0075
        L_0x0048:
            android.os.Handler r8 = r8.mHandler     // Catch:{ all -> 0x004e }
            r8.sendEmptyMessage(r4)     // Catch:{ all -> 0x004e }
            goto L_0x0075
        L_0x004e:
            r8 = move-exception
            goto L_0x0094
        L_0x0050:
            r1 = move-exception
            goto L_0x0077
        L_0x0052:
            r2 = move-exception
            java.lang.String r5 = "HighlightEncoder"
            java.lang.String r6 = "Stop lock interrupted."
            com.samsung.android.gallery.support.utils.Log.e(r5, r6)     // Catch:{ all -> 0x0050 }
            r2.printStackTrace()     // Catch:{ all -> 0x0050 }
            java.lang.String r0 = "HighlightEncoder"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x004e }
            r2.<init>(r1)     // Catch:{ all -> 0x004e }
            boolean r1 = r8.mIsEncoding     // Catch:{ all -> 0x004e }
            r2.append(r1)     // Catch:{ all -> 0x004e }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x004e }
            com.samsung.android.gallery.support.utils.Log.e(r0, r1)     // Catch:{ all -> 0x004e }
            boolean r0 = r8.mIsEncoding     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x0075
            goto L_0x0048
        L_0x0075:
            monitor-exit(r3)     // Catch:{ all -> 0x004e }
            return
        L_0x0077:
            java.lang.String r2 = "HighlightEncoder"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x004e }
            r5.<init>(r0)     // Catch:{ all -> 0x004e }
            boolean r0 = r8.mIsEncoding     // Catch:{ all -> 0x004e }
            r5.append(r0)     // Catch:{ all -> 0x004e }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x004e }
            com.samsung.android.gallery.support.utils.Log.e(r2, r0)     // Catch:{ all -> 0x004e }
            boolean r0 = r8.mIsEncoding     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x0093
            android.os.Handler r8 = r8.mHandler     // Catch:{ all -> 0x004e }
            r8.sendEmptyMessage(r4)     // Catch:{ all -> 0x004e }
        L_0x0093:
            throw r1     // Catch:{ all -> 0x004e }
        L_0x0094:
            monitor-exit(r3)     // Catch:{ all -> 0x004e }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.story.transcode.HighlightEncoder.stopEncoding():void");
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Logger.getSimpleName((Object) this));
        sb2.append("{");
        sb2.append(this.mUserStopped);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mHasError);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return j.h(sb2, this.mIsEncoding, " }");
    }
}
