package androidx.media3.exoplayer.mediacodec;

import K.a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaCodecSelector {
    public static final MediaCodecSelector DEFAULT = new a(3);
    public static final MediaCodecSelector PREFER_SOFTWARE = new a(4);

    List<MediaCodecInfo> getDecoderInfos(String str, boolean z, boolean z3);
}
