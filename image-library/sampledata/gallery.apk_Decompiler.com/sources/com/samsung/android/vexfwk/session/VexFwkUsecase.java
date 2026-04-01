package com.samsung.android.vexfwk.session;

import c0.C0086a;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u001f\b\u0002\u0018\u0000 !2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001!B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b ¨\u0006\""}, d2 = {"Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "", "id", "", "<init>", "(Ljava/lang/String;II)V", "getId", "()I", "DOCUMENT_DETECTION", "DOCUMENT_DEWARPING", "OBJECT_SEGMENTATION", "OBJECT_REMOVAL", "FRAME_RATE_CONVERSION", "WINE_DETECTION", "DOCUMENT_REFINEMENT", "VIDEO_OBJECT_REMOVAL", "OBJECT_EXTRACTION", "IMAGE_OBJECT_REMOVAL", "IMAGE_TRANSLATION", "TRANSLATION_UTIL", "IMAGE_CLIPPER", "IMAGE_TAGGER", "PORTRAIT_BOKEH_EFFECT", "IMAGE_EFFECT", "IMAGE_STYLE_TRANSFER", "IMAGE_ENHANCER", "IMAGE_OCR", "HUMAN_SEGMENTATION", "IMAGE_REMOVAL_SUGGESTION", "DEPTHMAP_PROCESSOR", "FOCALLENGTH_PROCESSOR", "UNIFIED_DETECTOR", "IMAGE_TAGGER_V2", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum VexFwkUsecase {
    DOCUMENT_DETECTION(0),
    DOCUMENT_DEWARPING(1),
    OBJECT_SEGMENTATION(2),
    OBJECT_REMOVAL(3),
    FRAME_RATE_CONVERSION(4),
    WINE_DETECTION(5),
    DOCUMENT_REFINEMENT(6),
    VIDEO_OBJECT_REMOVAL(7),
    OBJECT_EXTRACTION(8),
    IMAGE_OBJECT_REMOVAL(9),
    IMAGE_TRANSLATION(10),
    TRANSLATION_UTIL(11),
    IMAGE_CLIPPER(12),
    IMAGE_TAGGER(13),
    PORTRAIT_BOKEH_EFFECT(14),
    IMAGE_EFFECT(15),
    IMAGE_STYLE_TRANSFER(16),
    IMAGE_ENHANCER(17),
    IMAGE_OCR(18),
    HUMAN_SEGMENTATION(19),
    IMAGE_REMOVAL_SUGGESTION(20),
    DEPTHMAP_PROCESSOR(21),
    FOCALLENGTH_PROCESSOR(22),
    UNIFIED_DETECTOR(23),
    IMAGE_TAGGER_V2(24);
    
    public static final Companion Companion = null;
    private final int id;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/session/VexFwkUsecase$Companion;", "", "<init>", "()V", "from", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "id", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final VexFwkUsecase from(int i2) {
            VexFwkUsecase vexFwkUsecase;
            VexFwkUsecase[] values = VexFwkUsecase.values();
            int length = values.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    vexFwkUsecase = null;
                    break;
                }
                vexFwkUsecase = values[i7];
                if (vexFwkUsecase.getId() == i2) {
                    break;
                }
                i7++;
            }
            if (vexFwkUsecase != null) {
                return vexFwkUsecase;
            }
            throw new IllegalArgumentException(C0086a.i(i2, "Unknown usecase id "));
        }

        private Companion() {
        }
    }

    static {
        VexFwkUsecase[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private VexFwkUsecase(int i2) {
        this.id = i2;
    }

    public static final VexFwkUsecase from(int i2) {
        return Companion.from(i2);
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int getId() {
        return this.id;
    }
}
