package androidx.media3.extractor.mkv;

import androidx.media3.extractor.ExtractorInput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface EbmlProcessor {
    void binaryElement(int i2, int i7, ExtractorInput extractorInput);

    void endMasterElement(int i2);

    void floatElement(int i2, double d);

    int getElementType(int i2);

    void integerElement(int i2, long j2);

    boolean isLevel1Element(int i2);

    void startMasterElement(int i2, long j2, long j3);

    void stringElement(int i2, String str);
}
