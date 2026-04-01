package com.samsung.android.motionphoto.utils.v2.video;

import android.media.MediaFormat;
import com.samsung.android.motionphoto.utils.v2.MPFile;
import com.samsung.android.motionphoto.utils.v2.MimeType;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl;
import com.samsung.android.motionphoto.utils.v2.MotionScrap;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1192j;
import ne.C1194l;
import ne.z;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0012J\r\u0010\u0014\u001a\u00020\u0002¢\u0006\u0004\b\u0014\u0010\u0012J\r\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0017J\r\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001c\u001a\u00020\u0019¢\u0006\u0004\b\u001c\u0010\u001bJ\r\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ)\u0010&\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#\u0018\u00010\"2\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b&\u0010'J+\u0010)\u001a \u0012\u0004\u0012\u00020 \u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#0\"0(¢\u0006\u0004\b)\u0010*J/\u0010-\u001a\u00020\n2\u0006\u0010+\u001a\u00020 2\u0018\u0010,\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#0\"¢\u0006\u0004\b-\u0010.J\r\u00100\u001a\u00020/¢\u0006\u0004\b0\u00101J\r\u00102\u001a\u00020/¢\u0006\u0004\b2\u00101R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\t\u00103R4\u0010,\u001a \u0012\u0004\u0012\u00020 \u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#0\"048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b,\u00105R\u0014\u00109\u001a\u0002068BX\u0004¢\u0006\u0006\u001a\u0004\b7\u00108¨\u0006:"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/VideoTranscodingTask;", "Lcom/samsung/android/motionphoto/utils/v2/video/VideoTask;", "", "id", "Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "mpFile", "<init>", "(ILcom/samsung/android/motionphoto/utils/v2/MPFile;)V", "Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$TranscodingVO;", "transcodingVO", "Lme/x;", "setTranscodingVO$motionphoto_utils_v2_0_17_release", "(Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$TranscodingVO;)V", "setTranscodingVO", "", "getVideoTrackDuration", "()J", "getTranscodingVideoHeight", "()I", "getTranscodingVideoWidth", "getTranscodingRotation", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "getTranscodingAudioCodecType", "()Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "getTranscodingVideoCodecType", "Landroid/media/MediaFormat;", "getTranscodingAudioCodecFormat", "()Landroid/media/MediaFormat;", "getTranscodingVideoCodecFormat", "", "getTranscodingVideoRatio", "()F", "", "type", "", "Lme/i;", "Ljava/nio/ByteBuffer;", "Landroid/media/MediaCodec$BufferInfo;", "getSamples", "(Ljava/lang/String;)Ljava/util/List;", "", "getAllSamples", "()Ljava/util/Map;", "mimeType", "samples", "addSamples", "(Ljava/lang/String;Ljava/util/List;)V", "", "isRatioChangedAfterRotate", "()Z", "isSilentAudioTrackRequired", "Lcom/samsung/android/motionphoto/utils/v2/MotionScrap$TranscodingVO;", "", "Ljava/util/Map;", "Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfoImpl;", "getMpInfo", "()Lcom/samsung/android/motionphoto/utils/v2/MotionPhotoInfoImpl;", "mpInfo", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoTranscodingTask extends VideoTask {
    private Map<String, List<i>> samples = new LinkedHashMap();
    private MotionScrap.TranscodingVO transcodingVO = new MotionScrap.TranscodingVO(0, 0, 0, (MimeType) null, (MimeType) null, (MediaFormat) null, (MediaFormat) null, 127, (e) null);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideoTranscodingTask(int i2, MPFile mPFile) {
        super(i2, mPFile);
        j.e(mPFile, "mpFile");
    }

    private final MotionPhotoInfoImpl getMpInfo() {
        return getMpFile().getMpInfo();
    }

    public final void addSamples(String str, List<? extends i> list) {
        j.e(str, FileApiContract.Parameter.MIME_TYPE);
        j.e(list, "samples");
        this.samples.put(str, list);
    }

    public final Map<String, List<i>> getAllSamples() {
        return z.f0(this.samples);
    }

    public final List<i> getSamples(String str) {
        j.e(str, "type");
        return this.samples.get(str);
    }

    public final MediaFormat getTranscodingAudioCodecFormat() {
        MediaFormat audioCodecFormat = this.transcodingVO.getAudioCodecFormat();
        j.b(audioCodecFormat);
        return audioCodecFormat;
    }

    public final MimeType getTranscodingAudioCodecType() {
        return this.transcodingVO.getAudioCodec();
    }

    public final int getTranscodingRotation() {
        return this.transcodingVO.getRotation();
    }

    public final MediaFormat getTranscodingVideoCodecFormat() {
        MediaFormat videoCodecFormat = this.transcodingVO.getVideoCodecFormat();
        j.b(videoCodecFormat);
        return videoCodecFormat;
    }

    public final MimeType getTranscodingVideoCodecType() {
        return this.transcodingVO.getVideoCodec();
    }

    public final int getTranscodingVideoHeight() {
        return this.transcodingVO.getHeight();
    }

    public final float getTranscodingVideoRatio() {
        return ((float) getTranscodingVideoWidth()) / ((float) getTranscodingVideoHeight());
    }

    public final int getTranscodingVideoWidth() {
        return this.transcodingVO.getWidth();
    }

    public final long getVideoTrackDuration() {
        Map<MimeType, Long> trackDurationsOfVideo = getMpInfo().getTrackDurationsOfVideo();
        j.d(trackDurationsOfVideo, "getTrackDurationsOfVideo(...)");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : trackDurationsOfVideo.entrySet()) {
            if (((MimeType) next.getKey()).isVideo()) {
                linkedHashMap.put(next.getKey(), next.getValue());
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry value : linkedHashMap.entrySet()) {
            arrayList.add((Long) value.getValue());
        }
        Object L02 = C1194l.L0(arrayList);
        j.b(L02);
        return ((Number) L02).longValue();
    }

    public final boolean isRatioChangedAfterRotate() {
        if (C1192j.d0(new Integer[]{90, 270}, Integer.valueOf(getRotation())) != C1192j.d0(new Integer[]{90, 270}, Integer.valueOf(getTranscodingRotation()))) {
            return true;
        }
        return false;
    }

    public final boolean isSilentAudioTrackRequired() {
        if (getTranscodingAudioCodecType() == MimeType.NONE || getSamples(getTranscodingAudioCodecType().getValue()) != null) {
            return false;
        }
        return true;
    }

    public final void setTranscodingVO$motionphoto_utils_v2_0_17_release(MotionScrap.TranscodingVO transcodingVO2) {
        j.e(transcodingVO2, "transcodingVO");
        this.transcodingVO = transcodingVO2;
    }
}
