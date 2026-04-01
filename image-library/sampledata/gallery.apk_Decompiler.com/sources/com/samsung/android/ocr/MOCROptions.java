package com.samsung.android.ocr;

import com.samsung.android.ocr.MOCRConstants;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MOCROptions {
    private final boolean detectOrientation;
    private final boolean detectTables;
    private final boolean disableLineProcessing;
    private final int engineSpec;
    private final boolean filterIcons;
    private final boolean forceLang;
    private final boolean getCharResult;
    private final int imageType;
    private final int languageMode;
    private final int numThreads;
    private final int ocrMode;
    private final boolean optimizeCpu;
    private final boolean runInverted;
    private final boolean useBeamSearch;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public boolean detectOrientation = true;
        /* access modifiers changed from: private */
        public boolean detectTables = true;
        /* access modifiers changed from: private */
        public boolean disableLineProcessing = false;
        /* access modifiers changed from: private */
        public int engineSpec = MOCRConstants.MOCRSpec.HighAccuracy.getValue();
        /* access modifiers changed from: private */
        public boolean filterIcons = true;
        /* access modifiers changed from: private */
        public boolean forceLang = false;
        /* access modifiers changed from: private */
        public boolean getCharResult = false;
        /* access modifiers changed from: private */
        public int imageType = MOCRConstants.MOCRImageType.Generic.getValue();
        /* access modifiers changed from: private */
        public int languageMode = MOCRConstants.MOCRLanguageMode.Auto.getValue();
        /* access modifiers changed from: private */
        public int numThreads = 4;
        /* access modifiers changed from: private */
        public int ocrMode = MOCRConstants.MOCRSTRBackbone.Auto.getValue();
        /* access modifiers changed from: private */
        public boolean optimizeCpu = false;
        /* access modifiers changed from: private */
        public boolean runInverted = false;
        /* access modifiers changed from: private */
        public boolean useBeamSearch = false;

        public MOCROptions build() {
            return new MOCROptions(this, 0);
        }

        public Builder setDetectOrientation(boolean z) {
            this.detectOrientation = z;
            return this;
        }

        public Builder setDetectTables(boolean z) {
            this.detectTables = z;
            return this;
        }

        public Builder setDisableLineProcessing(boolean z) {
            this.disableLineProcessing = z;
            return this;
        }

        public Builder setEngineSpec(int i2) {
            this.engineSpec = i2;
            return this;
        }

        public Builder setFilterIcons(boolean z) {
            this.filterIcons = z;
            return this;
        }

        public Builder setForceLang(boolean z) {
            this.forceLang = z;
            return this;
        }

        public Builder setGetCharResult(boolean z) {
            this.getCharResult = z;
            return this;
        }

        public Builder setImageType(int i2) {
            this.imageType = i2;
            return this;
        }

        public Builder setLanguageMode(int i2) {
            this.languageMode = i2;
            return this;
        }

        public Builder setNumThreads(int i2) {
            this.numThreads = i2;
            return this;
        }

        public Builder setOptimizeCpu(boolean z) {
            this.optimizeCpu = z;
            return this;
        }

        public Builder setRunInverted(boolean z) {
            this.runInverted = z;
            return this;
        }

        public Builder setSTRBackbone(int i2) {
            this.ocrMode = i2;
            return this;
        }

        public Builder setUseBeamSearch(boolean z) {
            this.useBeamSearch = z;
            return this;
        }
    }

    public /* synthetic */ MOCROptions(Builder builder, int i2) {
        this(builder);
    }

    public int getEngineSpec() {
        return this.engineSpec;
    }

    public int getImageType() {
        return this.imageType;
    }

    public int getLanguageMode() {
        return this.languageMode;
    }

    public int getNumThreads() {
        return this.numThreads;
    }

    public int getSTRBackbone() {
        return this.ocrMode;
    }

    public boolean isDetectOrientation() {
        return this.detectOrientation;
    }

    public boolean isDetectTables() {
        return this.detectTables;
    }

    public boolean isDisableLineProcessing() {
        return this.disableLineProcessing;
    }

    public boolean isFilterIcons() {
        return this.filterIcons;
    }

    public boolean isForceLang() {
        return this.forceLang;
    }

    public boolean isGetCharResult() {
        return this.getCharResult;
    }

    public boolean isOptimizeCpu() {
        return this.optimizeCpu;
    }

    public boolean isRunInverted() {
        return this.runInverted;
    }

    public boolean isUseBeamSearch() {
        return this.useBeamSearch;
    }

    private MOCROptions(Builder builder) {
        this.useBeamSearch = builder.useBeamSearch;
        this.disableLineProcessing = builder.disableLineProcessing;
        this.optimizeCpu = builder.optimizeCpu;
        this.getCharResult = builder.getCharResult;
        this.forceLang = builder.forceLang;
        this.runInverted = builder.runInverted;
        this.numThreads = builder.numThreads;
        this.filterIcons = builder.filterIcons;
        this.detectOrientation = builder.detectOrientation;
        this.detectTables = builder.detectTables;
        this.languageMode = builder.languageMode;
        this.ocrMode = builder.ocrMode;
        this.engineSpec = builder.engineSpec;
        this.imageType = builder.imageType;
    }
}
