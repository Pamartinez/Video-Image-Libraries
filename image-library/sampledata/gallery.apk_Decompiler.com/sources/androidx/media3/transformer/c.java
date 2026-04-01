package androidx.media3.transformer;

import android.media.MediaCodecInfo;
import androidx.media3.transformer.DefaultEncoderFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements DefaultEncoderFactory.EncoderFallbackCost {
    public final /* synthetic */ String d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;

    public /* synthetic */ c(String str, int i2, int i7) {
        this.d = str;
        this.e = i2;
        this.f = i7;
    }

    public final int getParameterSupportGap(MediaCodecInfo mediaCodecInfo) {
        return DefaultEncoderFactory.lambda$filterEncodersByResolution$0(this.d, this.e, this.f, mediaCodecInfo);
    }
}
