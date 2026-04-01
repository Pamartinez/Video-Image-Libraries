package com.samsung.android.photoremaster.sdk.enhancer;

import com.samsung.android.photoremaster.sdk.ip.PrSize;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EnhanceResult {
    private final boolean isSuccess;
    private final byte[] outBgrBuffer;
    private final PrSize prSize;

    public EnhanceResult(boolean z) {
        this.isSuccess = z;
        this.prSize = null;
        this.outBgrBuffer = null;
    }

    public byte[] getOutBgrBuffer() {
        return this.outBgrBuffer;
    }

    public PrSize getPrSize() {
        return this.prSize;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public String toString() {
        return String.format(Locale.US, "EnhanceResult { isSuccess = %b, prSize= %s, outBgrBuffer = %s }", new Object[]{Boolean.valueOf(this.isSuccess), this.prSize, this.outBgrBuffer});
    }

    @Deprecated
    public EnhanceResult(boolean z, PrSize prSize2, byte[] bArr) {
        this.isSuccess = z;
        this.prSize = prSize2;
        this.outBgrBuffer = bArr;
    }
}
