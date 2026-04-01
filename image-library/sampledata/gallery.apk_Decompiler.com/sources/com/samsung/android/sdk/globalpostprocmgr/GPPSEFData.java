package com.samsung.android.sdk.globalpostprocmgr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GPPSEFData {
    private boolean isFileInsert;
    private byte[] mData;
    private String mDataFileName;
    private int mDataType;
    private String mFileName;
    private String mKeyName;
    private int mOption;

    public GPPSEFData() {
    }

    public byte[] getData() {
        return this.mData;
    }

    public String getDataFileName() {
        return this.mDataFileName;
    }

    public int getDataType() {
        return this.mDataType;
    }

    public String getFileName() {
        return this.mFileName;
    }

    public String getKeyName() {
        return this.mKeyName;
    }

    public int getOption() {
        return this.mOption;
    }

    public boolean isFileInsert() {
        return this.isFileInsert;
    }

    public void setData(byte[] bArr) {
        this.mData = bArr;
    }

    public void setDataFileName(String str) {
        this.mDataFileName = str;
    }

    public void setDataType(int i2) {
        this.mDataType = i2;
    }

    public void setFileInsert(boolean z) {
        this.isFileInsert = z;
    }

    public void setFileName(String str) {
        this.mFileName = str;
    }

    public void setKeyName(String str) {
        this.mKeyName = str;
    }

    public void setOption(int i2) {
        this.mOption = i2;
    }

    public GPPSEFData(String str, String str2, String str3, byte[] bArr, int i2, int i7, boolean z) {
        this.mFileName = str;
        this.mKeyName = str2;
        this.mDataFileName = str3;
        this.mData = bArr;
        this.mDataType = i2;
        this.mOption = i7;
        this.isFileInsert = z;
    }
}
