package com.samsung.android.sdk.scs.ai.visual.c2pa;

import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/SignatureInfo;", "", "issuer", "", "certSerialNumber", "time", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getIssuer", "()Ljava/lang/String;", "getCertSerialNumber", "getTime", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SignatureInfo {
    private final String certSerialNumber;
    private final String issuer;
    private final String time;

    public SignatureInfo(String str, String str2, String str3) {
        this.issuer = str;
        this.certSerialNumber = str2;
        this.time = str3;
    }

    public static /* synthetic */ SignatureInfo copy$default(SignatureInfo signatureInfo, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = signatureInfo.issuer;
        }
        if ((i2 & 2) != 0) {
            str2 = signatureInfo.certSerialNumber;
        }
        if ((i2 & 4) != 0) {
            str3 = signatureInfo.time;
        }
        return signatureInfo.copy(str, str2, str3);
    }

    public final String component1() {
        return this.issuer;
    }

    public final String component2() {
        return this.certSerialNumber;
    }

    public final String component3() {
        return this.time;
    }

    public final SignatureInfo copy(String str, String str2, String str3) {
        return new SignatureInfo(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SignatureInfo)) {
            return false;
        }
        SignatureInfo signatureInfo = (SignatureInfo) obj;
        if (j.a(this.issuer, signatureInfo.issuer) && j.a(this.certSerialNumber, signatureInfo.certSerialNumber) && j.a(this.time, signatureInfo.time)) {
            return true;
        }
        return false;
    }

    public final String getCertSerialNumber() {
        return this.certSerialNumber;
    }

    public final String getIssuer() {
        return this.issuer;
    }

    public final String getTime() {
        return this.time;
    }

    public int hashCode() {
        int i2;
        int i7;
        String str = this.issuer;
        int i8 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i10 = i2 * 31;
        String str2 = this.certSerialNumber;
        if (str2 == null) {
            i7 = 0;
        } else {
            i7 = str2.hashCode();
        }
        int i11 = (i10 + i7) * 31;
        String str3 = this.time;
        if (str3 != null) {
            i8 = str3.hashCode();
        }
        return i11 + i8;
    }

    public String toString() {
        String str = this.issuer;
        String str2 = this.certSerialNumber;
        return C0212a.p(C0086a.q("SignatureInfo(issuer=", str, ", certSerialNumber=", str2, ", time="), this.time, ")");
    }
}
