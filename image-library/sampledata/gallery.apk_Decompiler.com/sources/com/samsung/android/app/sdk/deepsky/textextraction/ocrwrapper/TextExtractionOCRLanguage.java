package com.samsung.android.app.sdk.deepsky.textextraction.ocrwrapper;

import com.samsung.android.sdk.ocr.OCRLanguage;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u000b\u001a\u00020\fH ¢\u0006\u0002\b\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/ocrwrapper/TextExtractionOCRLanguage;", "", "id", "", "<init>", "(Ljava/lang/String;II)V", "getId", "()I", "All", "English", "Korean", "getConvertedId", "Lcom/samsung/android/sdk/ocr/OCRLanguage;", "getConvertedId$deepsky_sdk_textextraction_8_5_30_release", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum TextExtractionOCRLanguage {
    ;
    
    public static final Companion Companion = null;
    private final int id;

    @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\r\u0010\u0002\u001a\u00020\u0003H\u0010¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/ocrwrapper/TextExtractionOCRLanguage.All", "Lcom/samsung/android/app/sdk/deepsky/textextraction/ocrwrapper/TextExtractionOCRLanguage;", "getConvertedId", "Lcom/samsung/android/sdk/ocr/OCRLanguage;", "getConvertedId$deepsky_sdk_textextraction_8_5_30_release", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class All extends TextExtractionOCRLanguage {
        public All(String str, int i2) {
            super(str, i2, -1, (e) null);
        }

        public OCRLanguage getConvertedId$deepsky_sdk_textextraction_8_5_30_release() {
            return OCRLanguage.AUTO;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/ocrwrapper/TextExtractionOCRLanguage$Companion;", "", "<init>", "()V", "getByValue", "Lcom/samsung/android/app/sdk/deepsky/textextraction/ocrwrapper/TextExtractionOCRLanguage;", "value", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final TextExtractionOCRLanguage getByValue(int i2) {
            TextExtractionOCRLanguage textExtractionOCRLanguage;
            TextExtractionOCRLanguage[] values = TextExtractionOCRLanguage.values();
            int length = values.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    textExtractionOCRLanguage = null;
                    break;
                }
                textExtractionOCRLanguage = values[i7];
                if (textExtractionOCRLanguage.getId() == i2) {
                    break;
                }
                i7++;
            }
            if (textExtractionOCRLanguage == null) {
                return TextExtractionOCRLanguage.All;
            }
            return textExtractionOCRLanguage;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\r\u0010\u0002\u001a\u00020\u0003H\u0010¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/ocrwrapper/TextExtractionOCRLanguage.English", "Lcom/samsung/android/app/sdk/deepsky/textextraction/ocrwrapper/TextExtractionOCRLanguage;", "getConvertedId", "Lcom/samsung/android/sdk/ocr/OCRLanguage;", "getConvertedId$deepsky_sdk_textextraction_8_5_30_release", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class English extends TextExtractionOCRLanguage {
        public English(String str, int i2) {
            super(str, i2, 0, (e) null);
        }

        public OCRLanguage getConvertedId$deepsky_sdk_textextraction_8_5_30_release() {
            return OCRLanguage.ENGLISH;
        }
    }

    @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\r\u0010\u0002\u001a\u00020\u0003H\u0010¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/ocrwrapper/TextExtractionOCRLanguage.Korean", "Lcom/samsung/android/app/sdk/deepsky/textextraction/ocrwrapper/TextExtractionOCRLanguage;", "getConvertedId", "Lcom/samsung/android/sdk/ocr/OCRLanguage;", "getConvertedId$deepsky_sdk_textextraction_8_5_30_release", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Korean extends TextExtractionOCRLanguage {
        public Korean(String str, int i2) {
            super(str, i2, 1, (e) null);
        }

        public OCRLanguage getConvertedId$deepsky_sdk_textextraction_8_5_30_release() {
            return OCRLanguage.KOREAN;
        }
    }

    static {
        TextExtractionOCRLanguage[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    public abstract OCRLanguage getConvertedId$deepsky_sdk_textextraction_8_5_30_release();

    public final int getId() {
        return this.id;
    }

    private TextExtractionOCRLanguage(int i2) {
        this.id = i2;
    }
}
