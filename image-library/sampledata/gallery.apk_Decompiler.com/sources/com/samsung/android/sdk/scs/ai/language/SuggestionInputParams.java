package com.samsung.android.sdk.scs.ai.language;

import com.samsung.android.sdk.scs.ai.language.SuggestionCategory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionInputParams {
    private final SuggestionCategory.MultiModal category;
    private byte[] data;
    private String dataMimeType;
    private final String inputText;

    public SuggestionInputParams(String str, SuggestionCategory.MultiModal multiModal) {
        this.inputText = str;
        this.category = multiModal;
        this.dataMimeType = "";
    }

    public SuggestionCategory.MultiModal getCategory() {
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

    public SuggestionInputParams(String str, byte[] bArr, SuggestionCategory.MultiModal multiModal) {
        this.inputText = str;
        this.data = bArr;
        this.category = multiModal;
        this.dataMimeType = "";
    }

    public SuggestionInputParams(String str, byte[] bArr, SuggestionCategory.MultiModal multiModal, String str2) {
        this.inputText = str;
        this.data = bArr;
        this.category = multiModal;
        this.dataMimeType = str2;
    }
}
