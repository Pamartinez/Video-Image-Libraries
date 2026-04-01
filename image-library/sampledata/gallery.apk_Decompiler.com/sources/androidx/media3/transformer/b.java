package androidx.media3.transformer;

import android.media.MediaCodecInfo;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.transformer.DefaultEncoderFactory;
import androidx.media3.transformer.Transformer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements DefaultEncoderFactory.EncoderFallbackCost, ListenerSet.Event {
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b(Object obj, Object obj2) {
        this.d = obj;
        this.e = obj2;
    }

    public int getParameterSupportGap(MediaCodecInfo mediaCodecInfo) {
        return DefaultEncoderFactory.lambda$filterEncodersByHdrEditingSupport$3((String) this.d, (ColorInfo) this.e, mediaCodecInfo);
    }

    public void invoke(Object obj) {
        ((FallbackListener) this.d).lambda$onTransformationRequestFinalized$0((TransformationRequest) this.e, (Transformer.Listener) obj);
    }
}
