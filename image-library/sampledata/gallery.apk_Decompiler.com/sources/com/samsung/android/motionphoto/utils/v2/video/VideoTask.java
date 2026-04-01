package com.samsung.android.motionphoto.utils.v2.video;

import android.media.MediaFormat;
import com.samsung.android.motionphoto.utils.v2.MPFile;
import com.samsung.android.motionphoto.utils.v2.MimeType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\f\u001a\u00020\rJ\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fJ\u0006\u0010\u0012\u001a\u00020\u0003J\u0006\u0010\u0013\u001a\u00020\u0003J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0003J\u0006\u0010\u0017\u001a\u00020\u0010J\u0006\u0010\u0018\u001a\u00020\u0010J\u0006\u0010\u0019\u001a\u00020\u0011J\u0006\u0010\u001a\u001a\u00020\u0011J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ\u0011\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0000H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006 "}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoTask;", "", "id", "", "mpFile", "Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "<init>", "(ILcom/samsung/android/motionphoto/utils/v2/MPFile;)V", "getId", "()I", "getMpFile", "()Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "path", "", "getCodecFormats", "", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "Landroid/media/MediaFormat;", "getVideoWidth", "getVideoHeight", "getVideoRatio", "", "getRotation", "getAudioCodecType", "getVideoCodecType", "getAudioCodecFormat", "getVideoCodecFormat", "getDistinctDurationMs", "", "getDistinctDurationUs", "compareTo", "other", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoTask implements Comparable<VideoTask> {
    private final int id;
    private final MPFile mpFile;

    public VideoTask(int i2, MPFile mPFile) {
        j.e(mPFile, "mpFile");
        this.id = i2;
        this.mpFile = mPFile;
    }

    public final MediaFormat getAudioCodecFormat() {
        MediaFormat audioCodecFormat = this.mpFile.getAudioCodecFormat();
        j.b(audioCodecFormat);
        return audioCodecFormat;
    }

    public final MimeType getAudioCodecType() {
        return this.mpFile.getAudioCodecType();
    }

    public final Map<MimeType, MediaFormat> getCodecFormats() {
        return this.mpFile.getCodecFormats();
    }

    public final long getDistinctDurationMs() {
        return this.mpFile.getDistinctDurationMs();
    }

    public final long getDistinctDurationUs() {
        return getDistinctDurationMs() * ((long) 1000);
    }

    public final int getId() {
        return this.id;
    }

    public final MPFile getMpFile() {
        return this.mpFile;
    }

    public final int getRotation() {
        return this.mpFile.getRotation();
    }

    public final MediaFormat getVideoCodecFormat() {
        return this.mpFile.getVideoCodecFormat();
    }

    public final MimeType getVideoCodecType() {
        return this.mpFile.getVideoCodecType();
    }

    public final int getVideoHeight() {
        return this.mpFile.getVideoHeight();
    }

    public final float getVideoRatio() {
        return ((float) getVideoWidth()) / ((float) getVideoHeight());
    }

    public final int getVideoWidth() {
        return this.mpFile.getVideoWidth();
    }

    public final String path() {
        return this.mpFile.path();
    }

    public int compareTo(VideoTask videoTask) {
        j.e(videoTask, "other");
        return this.id - videoTask.id;
    }
}
