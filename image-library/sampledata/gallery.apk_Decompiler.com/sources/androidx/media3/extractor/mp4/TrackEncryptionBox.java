package androidx.media3.extractor.mp4;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.extractor.TrackOutput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TrackEncryptionBox {
    public final TrackOutput.CryptoData cryptoData;
    public final byte[] defaultInitializationVector;
    public final boolean isEncrypted;
    public final int perSampleIvSize;
    public final String schemeType;

    public TrackEncryptionBox(boolean z, String str, int i2, byte[] bArr, int i7, int i8, byte[] bArr2) {
        boolean z3;
        boolean z7 = false;
        if (i2 == 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Assertions.checkArgument((bArr2 == null ? true : z7) ^ z3);
        this.isEncrypted = z;
        this.schemeType = str;
        this.perSampleIvSize = i2;
        this.defaultInitializationVector = bArr2;
        this.cryptoData = new TrackOutput.CryptoData(schemeToCryptoMode(str), bArr, i7, i8);
    }

    private static int schemeToCryptoMode(String str) {
        if (str == null) {
            return 1;
        }
        char c5 = 65535;
        switch (str.hashCode()) {
            case 3046605:
                if (str.equals("cbc1")) {
                    c5 = 0;
                    break;
                }
                break;
            case 3046671:
                if (str.equals("cbcs")) {
                    c5 = 1;
                    break;
                }
                break;
            case 3049879:
                if (str.equals("cenc")) {
                    c5 = 2;
                    break;
                }
                break;
            case 3049895:
                if (str.equals("cens")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
                return 2;
            case 2:
            case 3:
                break;
            default:
                Log.w("TrackEncryptionBox", "Unsupported protection scheme type '" + str + "'. Assuming AES-CTR crypto mode.");
                break;
        }
        return 1;
    }
}
