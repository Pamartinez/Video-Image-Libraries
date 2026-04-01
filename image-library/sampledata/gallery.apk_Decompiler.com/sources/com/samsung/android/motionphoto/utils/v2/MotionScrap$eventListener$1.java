package com.samsung.android.motionphoto.utils.v2;

import Ae.d;
import Tf.v;
import android.media.MediaCodec;
import com.samsung.android.motionphoto.utils.v2.MotionScrap;
import com.samsung.android.motionphoto.utils.v2.video.EventListener;
import com.samsung.scsp.media.file.FileApiContract;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J'\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\r\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\r\u0010\nJ\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"com/samsung/android/motionphoto/utils/v2/MotionScrap$eventListener$1", "Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;", "Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "mpFile", "", "mimeType", "Landroid/media/MediaCodec$BufferInfo;", "bufferInfo", "Lme/x;", "onEachFrameTransferred", "(Lcom/samsung/android/motionphoto/utils/v2/MPFile;Ljava/lang/String;Landroid/media/MediaCodec$BufferInfo;)V", "onTransferComplete", "(Lcom/samsung/android/motionphoto/utils/v2/MPFile;)V", "onEachFrameTranscoded", "onTranscodingComplete", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MotionScrap$eventListener$1 implements EventListener {
    final /* synthetic */ MotionScrap this$0;

    public MotionScrap$eventListener$1(MotionScrap motionScrap) {
        this.this$0 = motionScrap;
    }

    public void onEachFrameTranscoded(MPFile mPFile, String str, MediaCodec.BufferInfo bufferInfo) {
        j.e(mPFile, "mpFile");
        j.e(str, FileApiContract.Parameter.MIME_TYPE);
        j.e(bufferInfo, "bufferInfo");
        if (v.t0(str, "video")) {
            this.this$0.reportProgress();
        }
        d access$getOnTranscodingProgressChangedListener$p = this.this$0.onTranscodingProgressChangedListener;
        if (access$getOnTranscodingProgressChangedListener$p != null) {
            MotionScrap motionScrap = this.this$0;
            if (v.t0(str, "video")) {
                MotionScrap.ProgressTracker access$getTranscodingProgressTracker$p = motionScrap.transcodingProgressTracker;
                if (access$getTranscodingProgressTracker$p == null) {
                    j.k("transcodingProgressTracker");
                    throw null;
                } else if (access$getTranscodingProgressTracker$p.update(bufferInfo)) {
                    MotionScrap.ProgressTracker access$getTranscodingProgressTracker$p2 = motionScrap.transcodingProgressTracker;
                    if (access$getTranscodingProgressTracker$p2 != null) {
                        i report = access$getTranscodingProgressTracker$p2.report();
                        access$getOnTranscodingProgressChangedListener$p.invoke(mPFile, Integer.valueOf(((Number) report.d).intValue()), Integer.valueOf(((Number) report.e).intValue()));
                        return;
                    }
                    j.k("transcodingProgressTracker");
                    throw null;
                }
            }
        }
    }

    public void onEachFrameTransferred(MPFile mPFile, String str, MediaCodec.BufferInfo bufferInfo) {
        j.e(mPFile, "mpFile");
        j.e(str, FileApiContract.Parameter.MIME_TYPE);
        j.e(bufferInfo, "bufferInfo");
        if (v.t0(str, "video")) {
            this.this$0.reportProgress();
        }
        d access$getOnMergeProgressChangedListener$p = this.this$0.onMergeProgressChangedListener;
        if (access$getOnMergeProgressChangedListener$p != null) {
            MotionScrap motionScrap = this.this$0;
            if (v.t0(str, "video")) {
                MotionScrap.ProgressTracker access$getTotalProgressTracker$p = motionScrap.totalProgressTracker;
                if (access$getTotalProgressTracker$p == null) {
                    j.k("totalProgressTracker");
                    throw null;
                } else if (access$getTotalProgressTracker$p.update(bufferInfo)) {
                    MotionScrap.ProgressTracker access$getTotalProgressTracker$p2 = motionScrap.totalProgressTracker;
                    if (access$getTotalProgressTracker$p2 != null) {
                        i report = access$getTotalProgressTracker$p2.report();
                        access$getOnMergeProgressChangedListener$p.invoke(mPFile, Integer.valueOf(((Number) report.d).intValue()), Integer.valueOf(((Number) report.e).intValue()));
                        return;
                    }
                    j.k("totalProgressTracker");
                    throw null;
                }
            }
        }
    }

    public void onTranscodingComplete(MPFile mPFile) {
        j.e(mPFile, "mpFile");
        d access$getOnTranscodingCompleteListener$p = this.this$0.onTranscodingCompleteListener;
        if (access$getOnTranscodingCompleteListener$p != null) {
            MotionScrap motionScrap = this.this$0;
            MotionScrap.ProgressTracker access$getTranscodingProgressTracker$p = motionScrap.transcodingProgressTracker;
            if (access$getTranscodingProgressTracker$p != null) {
                access$getTranscodingProgressTracker$p.updateFileCount();
                MotionScrap.ProgressTracker access$getTranscodingProgressTracker$p2 = motionScrap.transcodingProgressTracker;
                if (access$getTranscodingProgressTracker$p2 != null) {
                    i reportFiles = access$getTranscodingProgressTracker$p2.reportFiles();
                    access$getOnTranscodingCompleteListener$p.invoke(mPFile, Integer.valueOf(((Number) reportFiles.d).intValue()), Integer.valueOf(((Number) reportFiles.e).intValue()));
                    return;
                }
                j.k("transcodingProgressTracker");
                throw null;
            }
            j.k("transcodingProgressTracker");
            throw null;
        }
    }

    public void onTransferComplete(MPFile mPFile) {
        j.e(mPFile, "mpFile");
        d access$getOnMergeCompleteListener$p = this.this$0.onMergeCompleteListener;
        if (access$getOnMergeCompleteListener$p != null) {
            MotionScrap motionScrap = this.this$0;
            MotionScrap.ProgressTracker access$getTotalProgressTracker$p = motionScrap.totalProgressTracker;
            if (access$getTotalProgressTracker$p != null) {
                access$getTotalProgressTracker$p.updateFileCount();
                MotionScrap.ProgressTracker access$getTotalProgressTracker$p2 = motionScrap.totalProgressTracker;
                if (access$getTotalProgressTracker$p2 != null) {
                    i reportFiles = access$getTotalProgressTracker$p2.reportFiles();
                    access$getOnMergeCompleteListener$p.invoke(mPFile, Integer.valueOf(((Number) reportFiles.d).intValue()), Integer.valueOf(((Number) reportFiles.e).intValue()));
                    return;
                }
                j.k("totalProgressTracker");
                throw null;
            }
            j.k("totalProgressTracker");
            throw null;
        }
    }
}
