package com.samsung.android.sdk.scs.ai.visual.c2pa;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/DigitalSourceType;", "", "uri", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getUri", "()Ljava/lang/String;", "DIGITAL_CAPTURE", "NEGATIVE_FILM", "POSITIVE_FILM", "PRINT", "MINOR_HUMAN_EDITS", "COMPOSITE_CAPTURE", "ALGORITHMICALLY_ENHANCED", "DATA_DRIVEN_MEDIA", "DIGITAL_ART", "VIRTUAL_RECORDING", "COMPOSITE_SYNTHETIC", "TRAINED_ALGORITHMIC_MEDIA", "COMPOSITE_WITH_TRAINED_ALGORITHMIC_MEDIA", "ALGORITHMIC_MEDIA", "C2PA_TRAINED_ALGORITHMIC_MEDIA_OLD", "C2PA_TRAINED_ALGORITHMIC_MEDIA_NEW", "C2PA_EMPTY", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum DigitalSourceType {
    DIGITAL_CAPTURE("http://cv.iptc.org/newscodes/digitalsourcetype/digitalCapture"),
    NEGATIVE_FILM("http://cv.iptc.org/newscodes/digitalsourcetype/negativeFilm"),
    POSITIVE_FILM("http://cv.iptc.org/newscodes/digitalsourcetype/positiveFilm"),
    PRINT("http://cv.iptc.org/newscodes/digitalsourcetype/print"),
    MINOR_HUMAN_EDITS("http://cv.iptc.org/newscodes/digitalsourcetype/minorHumanEdits"),
    COMPOSITE_CAPTURE("http://cv.iptc.org/newscodes/digitalsourcetype/compositeCapture"),
    ALGORITHMICALLY_ENHANCED("http://cv.iptc.org/newscodes/digitalsourcetype/algorithmicallyEnhanced"),
    DATA_DRIVEN_MEDIA("http://cv.iptc.org/newscodes/digitalsourcetype/dataDrivenMedia"),
    DIGITAL_ART("http://cv.iptc.org/newscodes/digitalsourcetype/digitalArt"),
    VIRTUAL_RECORDING("http://cv.iptc.org/newscodes/digitalsourcetype/virtualRecording"),
    COMPOSITE_SYNTHETIC("http://cv.iptc.org/newscodes/digitalsourcetype/compositeSynthetic"),
    TRAINED_ALGORITHMIC_MEDIA("http://cv.iptc.org/newscodes/digitalsourcetype/trainedAlgorithmicMedia"),
    COMPOSITE_WITH_TRAINED_ALGORITHMIC_MEDIA("http://cv.iptc.org/newscodes/digitalsourcetype/compositeWithTrainedAlgorithmicMedia"),
    ALGORITHMIC_MEDIA("http://cv.iptc.org/newscodes/digitalsourcetype/algorithmicMedia"),
    C2PA_TRAINED_ALGORITHMIC_MEDIA_OLD("c2pa.trainedAlgorithmicData"),
    C2PA_TRAINED_ALGORITHMIC_MEDIA_NEW("http://c2pa.org/digitalsourcetype/trainedAlgorithmicData"),
    C2PA_EMPTY("http://c2pa.org/digitalsourcetype/empty");
    
    private final String uri;

    static {
        DigitalSourceType[] $values;
        $ENTRIES = c.t($values);
    }

    private DigitalSourceType(String str) {
        this.uri = str;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final String getUri() {
        return this.uri;
    }
}
