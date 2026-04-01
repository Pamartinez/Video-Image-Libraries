package bd;

import Ae.b;
import android.media.MediaCodec;
import android.media.MediaFormat;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslator;
import com.samsung.android.motionphoto.utils.v2.MimeType;
import com.samsung.android.motionphoto.utils.v2.io.HEIFMetaWriter;
import com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader;
import com.samsung.android.motionphoto.utils.v2.io.JPEGMetaWriter;
import com.samsung.android.motionphoto.utils.v2.video.VideoTranscoder;
import com.samsung.android.motionphoto.utils.v2.video.VideoTranscodingTask;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.IColorEffect;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightControl;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import kotlin.jvm.internal.t;
import kotlin.jvm.internal.u;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ h(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return GuidingLightControl.setColorEffect$lambda$1((VibeRenderEffectBase) this.e, (GuidingLightControl) this.f, (IColorEffect) this.g, ((Boolean) obj).booleanValue());
            case 1:
                return HEIFMetaWriter.writeCameraDebugInfo$lambda$5((ImageMetaReader.Box) this.e, (HEIFMetaWriter) this.f, (byte[]) this.g, (FileChannel) obj);
            case 2:
                return Integer.valueOf(JPEGMetaWriter.writeXMP$lambda$9((t) this.e, (t) this.f, (ByteBuffer) this.g, (FileChannel) obj));
            case 3:
                return VideoTranscoder.prepareEncoder$lambda$20((VideoTranscodingTask) this.e, (MediaFormat) this.f, (VideoTranscoder) this.g, (List) obj);
            case 4:
                return VideoTranscoder.prepareEncoder$lambda$21((VideoTranscoder) this.e, (VideoTranscodingTask) this.f, (MimeType) this.g, (MediaCodec.BufferInfo) obj);
            case 5:
                return OcrData.joinToStringWithSeparator$lambda$9((String) this.e, (String) this.f, (String) this.g, (OcrData.BlockInfo) obj);
            default:
                return ImageTranslator.showTranslationDialog$lambda$9((ImageTranslator) this.e, (OcrData.BlockInfo) this.f, (u) this.g, (x) obj);
        }
    }
}
