package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import java.io.EOFException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ExtractorUtil {
    public static void checkContainerInput(boolean z, String str) {
        if (!z) {
            throw ParserException.createForMalformedContainer(str, (Throwable) null);
        }
    }

    public static boolean peekFullyQuietly(ExtractorInput extractorInput, byte[] bArr, int i2, int i7, boolean z) {
        try {
            return extractorInput.peekFully(bArr, i2, i7, z);
        } catch (EOFException e) {
            if (z) {
                return false;
            }
            throw e;
        }
    }

    public static int peekToLength(ExtractorInput extractorInput, byte[] bArr, int i2, int i7) {
        int i8 = 0;
        while (i8 < i7) {
            int peek = extractorInput.peek(bArr, i2 + i8, i7 - i8);
            if (peek == -1) {
                break;
            }
            i8 += peek;
        }
        return i8;
    }

    public static boolean readFullyQuietly(ExtractorInput extractorInput, byte[] bArr, int i2, int i7) {
        try {
            extractorInput.readFully(bArr, i2, i7);
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    public static boolean skipFullyQuietly(ExtractorInput extractorInput, int i2) {
        try {
            extractorInput.skipFully(i2);
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }
}
