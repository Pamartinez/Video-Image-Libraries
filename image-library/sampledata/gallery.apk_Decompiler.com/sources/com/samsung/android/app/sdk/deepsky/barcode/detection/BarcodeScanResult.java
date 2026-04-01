package com.samsung.android.app.sdk.deepsky.barcode.detection;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/detection/BarcodeScanResult;", "", "", "rawData", "", "qrPositionArray", "<init>", "(Ljava/lang/String;[I)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getRawData", "[I", "getQrPositionArray", "()[I", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BarcodeScanResult {
    private final int[] qrPositionArray;
    private final String rawData;

    public BarcodeScanResult(String str, int[] iArr) {
        j.e(str, "rawData");
        j.e(iArr, "qrPositionArray");
        this.rawData = str;
        this.qrPositionArray = iArr;
    }

    public boolean equals(Object obj) {
        Class<?> cls;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            cls = obj.getClass();
        } else {
            cls = null;
        }
        if (!BarcodeScanResult.class.equals(cls)) {
            return false;
        }
        j.c(obj, "null cannot be cast to non-null type com.samsung.android.app.sdk.deepsky.barcode.detection.BarcodeScanResult");
        BarcodeScanResult barcodeScanResult = (BarcodeScanResult) obj;
        if (!j.a(this.rawData, barcodeScanResult.rawData)) {
            return false;
        }
        return Arrays.equals(this.qrPositionArray, barcodeScanResult.qrPositionArray);
    }

    public final int[] getQrPositionArray() {
        return this.qrPositionArray;
    }

    public final String getRawData() {
        return this.rawData;
    }

    public int hashCode() {
        return Arrays.hashCode(this.qrPositionArray) + (this.rawData.hashCode() * 31);
    }

    public String toString() {
        return N2.j.d("BarcodeScanResult(rawData=", this.rawData, ", qrPositionArray=", Arrays.toString(this.qrPositionArray), ")");
    }
}
