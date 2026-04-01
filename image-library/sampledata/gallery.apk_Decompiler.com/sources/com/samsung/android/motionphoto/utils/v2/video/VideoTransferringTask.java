package com.samsung.android.motionphoto.utils.v2.video;

import com.samsung.android.motionphoto.utils.v2.MPFile;
import com.samsung.android.sum.core.SLog;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ+\u0010\u0014\u001a \u0012\u0004\u0012\u00020\u000f\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00110\u00100\u000e¢\u0006\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\t\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransferringTask;", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTask;", "", "id", "Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "mpFile", "<init>", "(ILcom/samsung/android/motionphoto/utils/v2/MPFile;)V", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;", "transcodingTask", "(Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;)V", "", "isFromTranscoding", "()Z", "", "", "", "Lme/i;", "Ljava/nio/ByteBuffer;", "Landroid/media/MediaCodec$BufferInfo;", "getAllTranscodedSamples", "()Ljava/util/Map;", "_transcodingTask", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;", "getTranscodingTask", "()Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoTransferringTask extends VideoTask {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private VideoTranscodingTask _transcodingTask;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoTransferringTask$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(VideoTransferringTask.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideoTransferringTask(int i2, MPFile mPFile) {
        super(i2, mPFile);
        j.e(mPFile, "mpFile");
        String str = TAG;
        String path = mPFile.path();
        SLog.i(str, "create MuxingTask w/ id=" + i2 + ", path=" + path);
    }

    private final VideoTranscodingTask getTranscodingTask() {
        VideoTranscodingTask videoTranscodingTask = this._transcodingTask;
        j.b(videoTranscodingTask);
        return videoTranscodingTask;
    }

    public final Map<String, List<i>> getAllTranscodedSamples() {
        return getTranscodingTask().getAllSamples();
    }

    public final boolean isFromTranscoding() {
        if (this._transcodingTask != null) {
            return true;
        }
        return false;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VideoTransferringTask(VideoTranscodingTask videoTranscodingTask) {
        this(videoTranscodingTask.getId(), videoTranscodingTask.getMpFile());
        j.e(videoTranscodingTask, "transcodingTask");
        this._transcodingTask = videoTranscodingTask;
    }
}
