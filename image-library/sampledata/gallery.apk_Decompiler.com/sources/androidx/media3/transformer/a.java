package androidx.media3.transformer;

import android.media.MediaCodecInfo;
import androidx.media3.transformer.DefaultEncoderFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements DefaultEncoderFactory.EncoderFallbackCost {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;
    public final /* synthetic */ int f;

    public /* synthetic */ a(String str, int i2, int i7) {
        this.d = i7;
        this.e = str;
        this.f = i2;
    }

    public final int getParameterSupportGap(MediaCodecInfo mediaCodecInfo) {
        switch (this.d) {
            case 0:
                return Math.abs(EncoderUtil.getClosestSupportedSampleRate(mediaCodecInfo, this.e, this.f) - this.f);
            case 1:
                return Math.abs(EncoderUtil.getSupportedBitrateRange(mediaCodecInfo, this.e).clamp(Integer.valueOf(this.f)).intValue() - this.f);
            default:
                return DefaultEncoderFactory.lambda$filterEncodersByBitrateMode$2(this.e, this.f, mediaCodecInfo);
        }
    }
}
