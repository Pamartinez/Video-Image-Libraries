package com.samsung.android.sdk.scs.ai.language;

import com.samsung.android.sdk.scs.ai.language.ExtractionCategory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExtractionInputParams {
    private final ExtractionCategory.MultiModal category;
    private byte[] data;
    private String dataMimeType;
    private final String inputText;

    @Deprecated
    public ExtractionInputParams(String str, ExtractionCategory extractionCategory) {
        this.inputText = str;
        this.category = ExtractionCategory.MultiModal.valueOf(extractionCategory.name());
        this.dataMimeType = "";
    }

    public ExtractionCategory.MultiModal getCategory() {
        return this.category;
    }

    public byte[] getData() {
        return this.data;
    }

    public String getDataMimeType() {
        return this.dataMimeType;
    }

    public String getInputText() {
        return this.inputText;
    }

    public void setData(byte[] bArr) {
        if (bArr != null) {
            byte[] bArr2 = new byte[bArr.length];
            this.data = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
    }

    public void setDataMimeType(String str) {
        this.dataMimeType = str;
    }

    public ExtractionInputParams(String str, ExtractionCategory.MultiModal multiModal) {
        this.inputText = str;
        this.category = multiModal;
        this.dataMimeType = "";
    }

    @Deprecated
    public ExtractionInputParams(String str, byte[] bArr, ExtractionCategory extractionCategory) {
        this.inputText = str;
        this.data = bArr;
        this.category = ExtractionCategory.MultiModal.valueOf(extractionCategory.name());
        this.dataMimeType = "";
    }

    public ExtractionInputParams(String str, byte[] bArr, ExtractionCategory.MultiModal multiModal) {
        this.inputText = str;
        this.data = bArr;
        this.category = multiModal;
        this.dataMimeType = "";
    }

    @Deprecated
    public ExtractionInputParams(String str, byte[] bArr, ExtractionCategory extractionCategory, String str2) {
        this.inputText = str;
        this.data = bArr;
        this.category = ExtractionCategory.MultiModal.valueOf(extractionCategory.name());
        this.dataMimeType = str2;
    }

    public ExtractionInputParams(String str, byte[] bArr, ExtractionCategory.MultiModal multiModal, String str2) {
        this.inputText = str;
        this.data = bArr;
        this.category = multiModal;
        this.dataMimeType = str2;
    }
}
