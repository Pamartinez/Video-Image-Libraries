package androidx.media3.exoplayer.mediacodec;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements MediaCodecUtil.ScoreProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Format f1022a;

    public /* synthetic */ b(Format format) {
        this.f1022a = format;
    }

    public final int getScore(Object obj) {
        return MediaCodecUtil.lambda$getDecoderInfosSortedByFullFormatSupport$1(this.f1022a, (MediaCodecInfo) obj);
    }
}
