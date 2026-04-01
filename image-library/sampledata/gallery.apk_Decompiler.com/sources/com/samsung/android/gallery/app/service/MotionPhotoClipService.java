package com.samsung.android.gallery.app.service;

import W.a;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.module.service.abstraction.ProgressJob;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.message.MotionPhotoOperationMsgMgr;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.samsung.android.sdk.sgpl.media.MediaMetadataInterface;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoClipService extends AbsProgressService {
    private String mDialogTitle = null;
    private int mOperatedCount = 0;
    private int mSuccessCount = 0;
    private int mTotalCount = 0;
    private int mType = -1;

    public MotionPhotoClipService() {
        super("MotionPhotoClipService", "com.samsung.android.gallery.app.service.MotionPhotoClipService");
    }

    private boolean changeVideoDate(String str, long j2) {
        try {
            MediaMetadataInterface mediaMetadataInterface = new MediaMetadataInterface(str);
            mediaMetadataInterface.setAttribute(MediaMetadataInterface.Attribute.ATTR_CREATION_TIME, TimeUtil.getExifDateTime(j2));
            return mediaMetadataInterface.saveAttributes();
        } catch (Exception e) {
            Log.e("MotionPhotoClipService", e.toString());
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$doJob$0(MediaItem mediaItem, Boolean bool, String str) {
        onComplete(mediaItem, bool.booleanValue(), str);
    }

    private void onComplete(MediaItem mediaItem, boolean z, String str) {
        if (z) {
            Log.d("MotionPhotoClipService", "do job [" + this.mOperatedCount + "][" + this.mTotalCount + "] success " + mediaItem);
            this.mSuccessCount = this.mSuccessCount + 1;
            MediaScanner.scanFile(str, new a(8));
            return;
        }
        Log.e("MotionPhotoClipService", "do job [" + this.mOperatedCount + "][" + this.mTotalCount + "] failed " + Logger.getEncodedString(str) + ArcCommonLog.TAG_COMMA + mediaItem);
    }

    private void onOperate(MediaItem mediaItem, BiConsumer<Boolean, String> biConsumer) {
        String path = mediaItem.getPath();
        if (TextUtils.isEmpty(path)) {
            biConsumer.accept(Boolean.FALSE, "path null");
            return;
        }
        int i2 = this.mType;
        if (i2 == 0) {
            biConsumer.accept(Boolean.valueOf(operateDelete(mediaItem)), path);
        } else if (i2 != 1) {
            Boolean bool = Boolean.FALSE;
            biConsumer.accept(bool, "invalid type " + this.mType);
        } else {
            String buildUnique = new FileNameBuilder(path).setExtension(IFormat.FORMAT_MP4).buildUnique();
            biConsumer.accept(Boolean.valueOf(operateExport(mediaItem, buildUnique)), buildUnique);
        }
    }

    private boolean operateDelete(MediaItem mediaItem) {
        return MotionPhotoUtils.deleteVideo(mediaItem.getPath());
    }

    private boolean operateExport(MediaItem mediaItem, String str) {
        if (!MotionPhotoUtils.exportVideo(mediaItem.getPath(), str)) {
            return false;
        }
        if (changeVideoDate(str, mediaItem.getDateTaken())) {
            return true;
        }
        Log.e((CharSequence) "MotionPhotoClipService", "failed change video date", mediaItem);
        return true;
    }

    public boolean addJobs(Intent intent) {
        MediaItem[] mediaItemArr = (MediaItem[]) this.mBlackboard.pop("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.w("MotionPhotoClipService", "items are empty.");
            return false;
        }
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null) {
                addToQueue(new ProgressJob(mediaItem));
            }
        }
        if (isQueueEmpty()) {
            Log.w("MotionPhotoClipService", "queue is empty.");
            return false;
        }
        int queueSize = getQueueSize();
        this.mTotalCount = queueSize;
        String notificationMessage = MotionPhotoOperationMsgMgr.getNotificationMessage(queueSize, this.mType);
        this.mNotificationMessage = notificationMessage;
        this.mDialogTitle = notificationMessage;
        Log.d("MotionPhotoClipService", "add jobs [" + this.mTotalCount + "]");
        return true;
    }

    public void doJob(ProgressJob progressJob) {
        try {
            MediaItem mediaItem = (MediaItem) progressJob.getParam(0);
            this.mNotificationTitle = mediaItem.getTitle();
            DialogHelper dialogHelper = this.mDialogHelper;
            int i2 = this.mOperatedCount + 1;
            this.mOperatedCount = i2;
            dialogHelper.updateDialog(i2, this.mTotalCount, getPercentage());
            updateNotification();
            onOperate(mediaItem, new A9.a(12, this, mediaItem));
        } catch (Exception e) {
            Log.e("MotionPhotoClipService", e.getMessage());
        } catch (Throwable th) {
            ((com.samsung.android.sdk.scs.ai.language.a) this.mJobCallback).a();
            throw th;
        }
        ((com.samsung.android.sdk.scs.ai.language.a) this.mJobCallback).a();
    }

    public String getChannelName() {
        return MotionPhotoOperationMsgMgr.getChannelName(this.mTotalCount, this.mType);
    }

    public int getPercentage() {
        int i2 = this.mTotalCount;
        if (i2 != 0) {
            return (this.mOperatedCount * 100) / i2;
        }
        return 0;
    }

    public void onCleanInternal() {
        Blackboard.getApplicationInstance().erase("data://running_motion_photo_clip_service");
        this.mDialogHelper.dismissDialog();
        Utils.showToast((Context) this, MotionPhotoOperationMsgMgr.getResultDescription(this.mType, this.mTotalCount, this.mSuccessCount, isInterrupted()));
        super.onCleanInternal();
    }

    public void onContinueInternal() {
        this.mDialogHelper.showDialog(this.mDialogTitle, this.mOperatedCount, this.mTotalCount, getPercentage());
    }

    public void onEndInternal() {
        Log.d("MotionPhotoClipService", "onEnd", Integer.valueOf(this.mOperatedCount), Integer.valueOf(this.mTotalCount), Integer.valueOf(this.mSuccessCount), Boolean.valueOf(isInterrupted()));
        DebugLogger.getDeleteInstance().lambda$apply$0("MotionPhotoClip", Logger.v("T=" + this.mTotalCount, "S=" + this.mSuccessCount, "C=" + this.mOperatedCount, "TP=" + this.mType));
        super.onEndInternal();
    }

    public boolean onPrepareInternal(Intent intent) {
        this.mType = intent.getIntExtra("motion_photo_operation_type", -1);
        return super.onPrepareInternal(intent);
    }

    public void onStartInternal() {
        super.onStartInternal();
        Blackboard.getApplicationInstance().publish("data://running_motion_photo_clip_service", Boolean.TRUE);
        DialogHelper dialogHelper = this.mDialogHelper;
        String str = this.mDialogTitle;
        int i2 = this.mTotalCount;
        dialogHelper.showDialog(str, 1, i2, 100 / i2);
    }
}
