package com.samsung.android.motionphoto.utils.v2;

import Ke.v0;
import android.graphics.Bitmap;
import java.util.Map;
import kotlin.Metadata;
import ne.C1203u;

@Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0014\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0014\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\b\u0010\n\u001a\u00020\u0005H\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\u0005H\u0016J\b\u0010\r\u001a\u00020\u0005H\u0016J\b\u0010\u000e\u001a\u00020\u0005H\u0016J\b\u0010\u000f\u001a\u00020\u0005H\u0016J\u0014\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0014\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0005H\u0016¨\u0006\u0017"}, d2 = {"com/samsung/android/motionphoto/utils/v2/MotionPhotoInfoImpl$autoPlayVideoInfo$2$2", "Lcom/samsung/android/motionphoto/utils/v2/VideoInfo;", "getMimeType", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "numberOfTracks", "", "getTrackDurations", "", "", "getSampleCount", "getVideoFrameCount", "getVideoDurationMs", "getWidth", "getHeight", "getRotation", "getBitrate", "getTrackFirstTimestampUs", "getTrackLastTimestampUs", "getThumbnailImage", "Landroid/graphics/Bitmap;", "getFrameAt", "timeUs", "option", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MotionPhotoInfoImpl$autoPlayVideoInfo$2$2 implements VideoInfo {
    public int getBitrate() {
        return 0;
    }

    public Bitmap getFrameAt(long j2, int i2) {
        throw new v0("An operation is not implemented: Not yet implemented", 2);
    }

    public int getHeight() {
        return 0;
    }

    public MimeType getMimeType() {
        return MimeType.NONE;
    }

    public int getRotation() {
        return 0;
    }

    public Map<MimeType, Long> getSampleCount() {
        return C1203u.d;
    }

    public Bitmap getThumbnailImage() {
        throw new v0("An operation is not implemented: Not yet implemented", 2);
    }

    public Map<MimeType, Long> getTrackDurations() {
        return C1203u.d;
    }

    public Map<MimeType, Long> getTrackFirstTimestampUs() {
        return C1203u.d;
    }

    public Map<MimeType, Long> getTrackLastTimestampUs() {
        return C1203u.d;
    }

    public long getVideoDurationMs() {
        return 0;
    }

    public int getVideoFrameCount() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }

    public int numberOfTracks() {
        return 0;
    }
}
