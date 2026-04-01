package com.samsung.android.sdk.scs.ai.language;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WritingComposerInputParams {
    private byte[] data;
    private String dataMimeType;
    private final String dynamicFormat;
    private final String dynamicTone;
    private final WritingComposerFormat format;
    private final String inputText;
    private int outputSentenceCount;
    private final WritingComposerTone tone;

    public WritingComposerInputParams(String str, WritingComposerFormat writingComposerFormat, WritingComposerTone writingComposerTone) {
        this.inputText = str;
        this.format = writingComposerFormat;
        this.tone = writingComposerTone;
        this.dynamicFormat = "";
        this.dynamicTone = "";
    }

    public byte[] getData() {
        return this.data;
    }

    public String getDataMimeType() {
        return this.dataMimeType;
    }

    public String getDynamicFormat() {
        return this.dynamicFormat;
    }

    public String getDynamicTone() {
        return this.dynamicTone;
    }

    public WritingComposerFormat getFormat() {
        return this.format;
    }

    public String getInputText() {
        return this.inputText;
    }

    public int getOutputSentenceCount() {
        return this.outputSentenceCount;
    }

    public WritingComposerTone getTone() {
        return this.tone;
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

    public void setOutputSentenceCount(int i2) {
        this.outputSentenceCount = i2;
    }

    public WritingComposerInputParams(String str, String str2, String str3) {
        this.inputText = str;
        this.format = WritingComposerFormat.WRITINGCOMPOSER_STANDARD;
        this.tone = WritingComposerTone.WRITINGCOMPOSER_PROFESSIONAL;
        this.dynamicFormat = str2;
        this.dynamicTone = str3;
    }

    public WritingComposerInputParams(String str, String str2, byte[] bArr, WritingComposerFormat writingComposerFormat, WritingComposerTone writingComposerTone, int i2) {
        this.inputText = str;
        this.dataMimeType = str2;
        this.data = bArr;
        this.format = writingComposerFormat;
        this.tone = writingComposerTone;
        this.outputSentenceCount = i2;
        this.dynamicFormat = "";
        this.dynamicTone = "";
    }

    public WritingComposerInputParams(String str, String str2, byte[] bArr, String str3, String str4, int i2) {
        this.inputText = str;
        this.dataMimeType = str2;
        this.data = bArr;
        this.format = WritingComposerFormat.WRITINGCOMPOSER_STANDARD;
        this.tone = WritingComposerTone.WRITINGCOMPOSER_PROFESSIONAL;
        this.outputSentenceCount = i2;
        this.dynamicFormat = str3;
        this.dynamicTone = str4;
    }
}
