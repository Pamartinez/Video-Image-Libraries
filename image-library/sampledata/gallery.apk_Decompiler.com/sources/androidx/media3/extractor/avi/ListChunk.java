package androidx.media3.extractor.avi;

import F2.C0040v;
import F2.G;
import F2.N;
import F2.U;
import androidx.media3.common.util.ParsableByteArray;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ListChunk implements AviChunk {
    public final U children;
    private final int type;

    private ListChunk(int i2, U u) {
        this.type = i2;
        this.children = u;
    }

    private static AviChunk createBox(int i2, int i7, ParsableByteArray parsableByteArray) {
        switch (i2) {
            case 1718776947:
                return StreamFormatChunk.parseFrom(i7, parsableByteArray);
            case 1751742049:
                return AviMainHeaderChunk.parseFrom(parsableByteArray);
            case 1752331379:
                return AviStreamHeaderChunk.parseFrom(parsableByteArray);
            case 1852994675:
                return StreamNameChunk.parseFrom(parsableByteArray);
            default:
                return null;
        }
    }

    public static ListChunk parseFrom(int i2, ParsableByteArray parsableByteArray) {
        AviChunk aviChunk;
        C0040v.c(4, "initialCapacity");
        Object[] objArr = new Object[4];
        int limit = parsableByteArray.limit();
        int i7 = 0;
        int i8 = -2;
        while (parsableByteArray.bytesLeft() > 8) {
            int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
            int position = parsableByteArray.getPosition() + parsableByteArray.readLittleEndianInt();
            parsableByteArray.setLimit(position);
            if (readLittleEndianInt == 1414744396) {
                aviChunk = parseFrom(parsableByteArray.readLittleEndianInt(), parsableByteArray);
            } else {
                aviChunk = createBox(readLittleEndianInt, i8, parsableByteArray);
            }
            if (aviChunk != null) {
                if (aviChunk.getType() == 1752331379) {
                    i8 = ((AviStreamHeaderChunk) aviChunk).getTrackType();
                }
                int i10 = i7 + 1;
                int e = N.e(objArr.length, i10);
                if (e > objArr.length) {
                    objArr = Arrays.copyOf(objArr, e);
                }
                objArr[i7] = aviChunk;
                i7 = i10;
            }
            parsableByteArray.setPosition(position);
            parsableByteArray.setLimit(limit);
        }
        return new ListChunk(i2, U.w(i7, objArr));
    }

    public <T extends AviChunk> T getChild(Class<T> cls) {
        G A10 = this.children.listIterator(0);
        while (A10.hasNext()) {
            T t = (AviChunk) A10.next();
            if (t.getClass() == cls) {
                return t;
            }
        }
        return null;
    }

    public int getType() {
        return this.type;
    }
}
