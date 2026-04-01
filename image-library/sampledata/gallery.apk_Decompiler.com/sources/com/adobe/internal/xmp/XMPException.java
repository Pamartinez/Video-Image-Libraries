package com.adobe.internal.xmp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XMPException extends Exception {
    private int errorCode;

    public XMPException(String str, int i2) {
        super(str);
        this.errorCode = i2;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public XMPException(String str, int i2, Throwable th) {
        super(str, th);
        this.errorCode = i2;
    }
}
