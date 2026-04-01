package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TrackOutput {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CryptoData {
        public final int clearBlocks;
        public final int cryptoMode;
        public final int encryptedBlocks;
        public final byte[] encryptionKey;

        public CryptoData(int i2, byte[] bArr, int i7, int i8) {
            this.cryptoMode = i2;
            this.encryptionKey = bArr;
            this.encryptedBlocks = i7;
            this.clearBlocks = i8;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && CryptoData.class == obj.getClass()) {
                CryptoData cryptoData = (CryptoData) obj;
                if (this.cryptoMode == cryptoData.cryptoMode && this.encryptedBlocks == cryptoData.encryptedBlocks && this.clearBlocks == cryptoData.clearBlocks && Arrays.equals(this.encryptionKey, cryptoData.encryptionKey)) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return ((((Arrays.hashCode(this.encryptionKey) + (this.cryptoMode * 31)) * 31) + this.encryptedBlocks) * 31) + this.clearBlocks;
        }
    }

    void format(Format format);

    int sampleData(DataReader dataReader, int i2, boolean z) {
        return sampleData(dataReader, i2, z, 0);
    }

    int sampleData(DataReader dataReader, int i2, boolean z, int i7);

    void sampleData(ParsableByteArray parsableByteArray, int i2, int i7);

    void sampleMetadata(long j2, int i2, int i7, int i8, CryptoData cryptoData);

    void sampleData(ParsableByteArray parsableByteArray, int i2) {
        sampleData(parsableByteArray, i2, 0);
    }

    void durationUs(long j2) {
    }
}
