package androidx.media3.extractor.mp4;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MimeTypeResolver {
    public static String getContainerMimeType(Format format) {
        String str = format.sampleMimeType;
        if (MimeTypes.isVideo(str)) {
            return Encode.ContentType.VIDEO_MP4;
        }
        if (MimeTypes.isAudio(str)) {
            return "audio/mp4";
        }
        if (!MimeTypes.isImage(str)) {
            return "application/mp4";
        }
        if (Objects.equals(str, "image/heic")) {
            return "image/heif";
        }
        if (Objects.equals(str, "image/avif")) {
            return "image/avif";
        }
        return "application/mp4";
    }

    public static String getContainerMimeType(List<TrackSampleTable> list) {
        boolean z = false;
        String str = null;
        for (TrackSampleTable trackSampleTable : list) {
            String str2 = trackSampleTable.track.format.sampleMimeType;
            if (MimeTypes.isVideo(str2)) {
                return Encode.ContentType.VIDEO_MP4;
            }
            if (MimeTypes.isAudio(str2)) {
                z = true;
            } else if (MimeTypes.isImage(str2)) {
                if (Objects.equals(str2, "image/heic")) {
                    str = "image/heif";
                } else if (Objects.equals(str2, "image/avif")) {
                    str = "image/avif";
                }
            }
        }
        if (z) {
            return "audio/mp4";
        }
        if (str != null) {
            return str;
        }
        return "application/mp4";
    }
}
