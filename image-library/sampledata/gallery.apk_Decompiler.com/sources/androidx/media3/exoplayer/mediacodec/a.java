package androidx.media3.exoplayer.mediacodec;

import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements MediaCodecUtil.ScoreProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1021a;

    public /* synthetic */ a(int i2) {
        this.f1021a = i2;
    }

    public final int getScore(Object obj) {
        MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) obj;
        switch (this.f1021a) {
            case 0:
                return MediaCodecUtil.lambda$getDecoderInfosSortedBySoftwareOnly$2(mediaCodecInfo);
            default:
                return MediaCodecUtil.lambda$applyWorkarounds$3(mediaCodecInfo);
        }
    }
}
