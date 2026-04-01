package com.samsung.android.motionphoto.utils.v2;

import Tf.v;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0016B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "NONE", "IMAGE_JPEG", "IMAGE_HEIC", "IMAGE_AVIF", "AUDIO_AAC", "VIDEO_AVC", "VIDEO_HEVC", "VIDEO_MP4", "VIDEO_MOV", "toString", "isImage", "", "isVideo", "isAudio", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MimeType {
    NONE("none"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_HEIC("image/heic"),
    IMAGE_AVIF("image/avif"),
    AUDIO_AAC(Encode.CodecsMime.AUDIO_CODEC_AAC),
    VIDEO_AVC(Encode.CodecsMime.VIDEO_CODEC_H264),
    VIDEO_HEVC("video/hevc"),
    VIDEO_MP4(Encode.ContentType.VIDEO_MP4),
    VIDEO_MOV("video/quicktime");
    
    public static final Companion Companion = null;
    private final String value;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MimeType$Companion;", "", "<init>", "()V", "of", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "value", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final MimeType of(String str) {
            j.e(str, "value");
            for (MimeType mimeType : MimeType.getEntries()) {
                if (j.a(mimeType.getValue(), str)) {
                    return mimeType;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }

        private Companion() {
        }
    }

    static {
        MimeType[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private MimeType(String str) {
        this.value = str;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final String getValue() {
        return this.value;
    }

    public final boolean isAudio() {
        return v.t0(this.value, "audio");
    }

    public final boolean isImage() {
        return v.t0(this.value, "image");
    }

    public final boolean isVideo() {
        return v.t0(this.value, "video");
    }

    public String toString() {
        return this.value;
    }
}
