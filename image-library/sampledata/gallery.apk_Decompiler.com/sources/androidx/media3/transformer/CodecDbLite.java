package androidx.media3.transformer;

import F2.A;
import F2.C0042x;
import F2.D0;
import F2.F;
import F2.G;
import F2.Q;
import F2.U;
import F2.V;
import F2.X;
import F2.y0;
import G0.c;
import N2.j;
import android.os.Build;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.transformer.VideoEncoderSettings;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CodecDbLite {
    private static final V ENCODER_DATASET;
    private static final VideoEncoderEntry ENCODER_DEFAULT = new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Chipset {
        private static final Chipset UNKNOWN = new Chipset("", "");
        private final String manufacturer;
        private final String model;

        public Chipset(String str, String str2) {
            this.manufacturer = str;
            this.model = str2;
        }

        public static Chipset current() {
            if (Build.VERSION.SDK_INT >= 31) {
                return new Chipset(Build.SOC_MANUFACTURER, Build.SOC_MODEL);
            }
            return UNKNOWN;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Chipset)) {
                return false;
            }
            Chipset chipset = (Chipset) obj;
            if (!Objects.equals(this.manufacturer, chipset.manufacturer) || !Objects.equals(this.model, chipset.model)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.manufacturer, this.model});
        }

        public String toString() {
            return j.d("Chipset(", this.manufacturer, " ", this.model, ")");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class VideoEncoderEntry {
        /* access modifiers changed from: private */
        public final int bFrameResolutionCutoff;
        /* access modifiers changed from: private */
        public final int formatOptimizations;
        /* access modifiers changed from: private */
        public final int maxBFrames;
        /* access modifiers changed from: private */
        public final String mimeType;

        private VideoEncoderEntry(String str, int i2, int i7, int i8) {
            this.mimeType = str;
            this.maxBFrames = i2;
            this.bFrameResolutionCutoff = i7;
            this.formatOptimizations = i8;
        }
    }

    static {
        V v;
        c cVar = new c(4, false);
        cVar.z(new Chipset("Google", "Tensor G2"), new VideoEncoderEntry("video/hevc", 1, 37538929, 0));
        cVar.z(new Chipset("Google", "Tensor G2"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 1, 32739600, 0));
        cVar.z(new Chipset("Google", "Tensor G3"), new VideoEncoderEntry("video/hevc", 1, 37538350, 0));
        cVar.z(new Chipset("Google", "Tensor G3"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 1, 32750593, 0));
        cVar.z(new Chipset("Google", "Tensor G4"), new VideoEncoderEntry("video/av01", 1, 32844500, 0));
        cVar.z(new Chipset("Google", "Tensor G4"), new VideoEncoderEntry("video/hevc", 1, 51851802, 0));
        cVar.z(new Chipset("Google", "Tensor G4"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 1, 44206216, 0));
        cVar.z(new Chipset("Mediatek", "MT6761"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6762"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6765"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6769T"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6769T"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6769Z"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6769Z"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6785"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6785"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6789V/CD"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6789V/CD"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6833V/NZA"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6833V/NZA"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("Mediatek", "MT6893"), new VideoEncoderEntry("video/hevc", 1, 34028841, 0));
        cVar.z(new Chipset("Mediatek", "MT6893"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 1, 457499715, 0));
        cVar.z(new Chipset("Mediatek", "MT6983"), new VideoEncoderEntry("video/hevc", 1, 36134374, 0));
        cVar.z(new Chipset("Mediatek", "MT6983"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 1, 189533581, 0));
        cVar.z(new Chipset("QTI", "SDM450"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM4350"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM4350"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM6125"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM6125"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM6225"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM6225"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM6375"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM6375"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM8250"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM8250"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM8350"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM8350"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("QTI", "SM8450"), new VideoEncoderEntry("video/hevc", 1, 497664000, 1));
        cVar.z(new Chipset("QTI", "SM8450"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 1, 497664000, 1));
        cVar.z(new Chipset("QTI", "SM8475"), new VideoEncoderEntry("video/hevc", 1, 497664000, 1));
        cVar.z(new Chipset("QTI", "SM8475"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 1, 497664000, 1));
        cVar.z(new Chipset("QTI", "SM8550"), new VideoEncoderEntry("video/hevc", 1, 497664000, 1));
        cVar.z(new Chipset("QTI", "SM8550"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 1, 110196681, 1));
        cVar.z(new Chipset("QTI", "SM8650"), new VideoEncoderEntry("video/hevc", 1, 34344411, 1));
        cVar.z(new Chipset("QTI", "SM8650"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 1, 132451733, 1));
        cVar.z(new Chipset("QTI", "SM8750"), new VideoEncoderEntry("video/hevc", 1, 52435727, 1));
        cVar.z(new Chipset("QTI", "SM8750"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 1, 159007069, 1));
        cVar.z(new Chipset("Samsung", "Exynos 850"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("Samsung", "Exynos 850"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("Samsung", "s5e8825"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("Samsung", "s5e8825"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("Samsung", "s5e9925"), new VideoEncoderEntry("video/hevc", 1, 51506898, 0));
        cVar.z(new Chipset("Samsung", "s5e9925"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 2, 40856748, 0));
        cVar.z(new Chipset("Spreadtrum", "SC9863A"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("Spreadtrum", "SC9863A"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        cVar.z(new Chipset("Spreadtrum", "T606"), new VideoEncoderEntry(Encode.CodecsMime.VIDEO_CODEC_H264, 0, 0, 0));
        cVar.z(new Chipset("Spreadtrum", "T606"), new VideoEncoderEntry("video/hevc", 0, 0, 0));
        A a7 = (A) cVar.e;
        if (a7 == null) {
            v = F.f244j;
        } else {
            Set entrySet = a7.entrySet();
            if (((AbstractCollection) entrySet).isEmpty()) {
                v = F.f244j;
            } else {
                C0042x xVar = (C0042x) entrySet;
                X x9 = new X(((A) xVar.e).size());
                Iterator it = xVar.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    Object key = entry.getKey();
                    y0 f = ((Q) entry.getValue()).f();
                    x9.b(key, f);
                    i2 += f.g;
                }
                v = new V((D0) x9.a(), i2);
            }
        }
        ENCODER_DATASET = v;
    }

    public static VideoEncoderSettings getRecommendedVideoEncoderSettings(Format format) {
        VideoEncoderEntry videoEncoderEntry;
        int i2;
        Assertions.checkArgument(MimeTypes.isVideo(format.sampleMimeType), "MIME must be a video MIME type.");
        Chipset current = Chipset.current();
        V v = ENCODER_DATASET;
        if (!v.f250h.containsKey(current)) {
            return VideoEncoderSettings.DEFAULT;
        }
        VideoEncoderSettings.Builder builder = new VideoEncoderSettings.Builder();
        U u = (U) v.f250h.get(current);
        if (u == null) {
            G g = U.e;
            u = y0.f278h;
        }
        int i7 = 0;
        while (true) {
            if (i7 >= u.size()) {
                videoEncoderEntry = null;
                break;
            } else if (((VideoEncoderEntry) u.get(i7)).mimeType.equals(format.sampleMimeType)) {
                videoEncoderEntry = (VideoEncoderEntry) u.get(i7);
                break;
            } else {
                i7++;
            }
        }
        if (videoEncoderEntry == null) {
            return builder.build();
        }
        if (format.getPixelCount() == -1 || format.frameRate == -1.0f) {
            i2 = Integer.MAX_VALUE;
        } else {
            i2 = C0246a.j0((long) Math.round(((float) format.getPixelCount()) * format.frameRate));
        }
        if (i2 < videoEncoderEntry.bFrameResolutionCutoff) {
            builder.setMaxBFrames(videoEncoderEntry.maxBFrames);
            if ((videoEncoderEntry.formatOptimizations & 1) != 0) {
                builder.setTemporalLayers(1, 2);
            }
        }
        return builder.build();
    }
}
