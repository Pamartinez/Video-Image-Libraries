package com.samsung.android.ocr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MOCRConstants {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MOCREngineType {
        None(-1),
        Stride(0);
        
        private final int value;

        private MOCREngineType(int i2) {
            this.value = i2;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MOCRImageType {
        Generic(0),
        SPenInput(1),
        HorizontalDocument(2);
        
        private final int value;

        private MOCRImageType(int i2) {
            this.value = i2;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MOCRLanguageMode {
        Auto(0),
        ImageLevel(1),
        LineLevel(2);
        
        private final int value;

        private MOCRLanguageMode(int i2) {
            this.value = i2;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MOCRPxlFmt {
        ARGB8888(0),
        NV21(1),
        RGBA8888(2),
        RGB(3);
        
        private final int value;

        private MOCRPxlFmt(int i2) {
            this.value = i2;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MOCRSTRBackbone {
        Auto(0),
        CRNN(1),
        ViT(2);
        
        private final int value;

        private MOCRSTRBackbone(int i2) {
            this.value = i2;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MOCRSpec {
        HighAccuracy(0),
        HighPerformance(1);
        
        private final int value;

        private MOCRSpec(int i2) {
            this.value = i2;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MOCRStatus {
        MOCRSuccess(0),
        MOCRModelReadError(1),
        MOCRModelPropertiesError(2),
        MOCRModelCharsetError(3),
        MOCRModelNotInitError(4),
        MOCRModelConfigureError(1),
        MOCRModelExecutionError(2),
        MOCRUnsupportedFormat(3),
        MOCRNoResultText(11),
        MOCRNoTextDetection(12),
        MOCRActiveEngineError(13),
        MOCRUnknownError(-1);
        
        private final int value;

        private MOCRStatus(int i2) {
            this.value = i2;
        }

        public int getValue() {
            return this.value;
        }
    }
}
