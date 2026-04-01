package androidx.media3.extractor;

import A.a;
import F2.U;
import android.util.Base64;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.flac.PictureFrame;
import androidx.media3.extractor.metadata.vorbis.VorbisComment;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VorbisUtil {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CommentHeader {
        public final String[] comments;
        public final int length;
        public final String vendor;

        public CommentHeader(String str, String[] strArr, int i2) {
            this.vendor = str;
            this.comments = strArr;
            this.length = i2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Mode {
        public final boolean blockFlag;
        public final int mapping;
        public final int transformType;
        public final int windowType;

        public Mode(boolean z, int i2, int i7, int i8) {
            this.blockFlag = z;
            this.windowType = i2;
            this.transformType = i7;
            this.mapping = i8;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class VorbisIdHeader {
        public final int bitrateMaximum;
        public final int bitrateMinimum;
        public final int bitrateNominal;
        public final int blockSize0;
        public final int blockSize1;
        public final int channels;
        public final byte[] data;
        public final boolean framingFlag;
        public final int sampleRate;
        public final int version;

        public VorbisIdHeader(int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14, boolean z, byte[] bArr) {
            this.version = i2;
            this.channels = i7;
            this.sampleRate = i8;
            this.bitrateMaximum = i10;
            this.bitrateNominal = i11;
            this.bitrateMinimum = i12;
            this.blockSize0 = i13;
            this.blockSize1 = i14;
            this.framingFlag = z;
            this.data = bArr;
        }
    }

    public static int iLog(int i2) {
        int i7 = 0;
        while (i2 > 0) {
            i7++;
            i2 >>>= 1;
        }
        return i7;
    }

    private static long mapType1QuantValues(long j2, long j3) {
        return (long) Math.floor(Math.pow((double) j2, 1.0d / ((double) j3)));
    }

    public static Metadata parseVorbisComments(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str = list.get(i2);
            String[] splitAtFirst = Util.splitAtFirst(str, "=");
            if (splitAtFirst.length != 2) {
                a.C("Failed to parse Vorbis comment: ", str, "VorbisUtil");
            } else if (splitAtFirst[0].equals("METADATA_BLOCK_PICTURE")) {
                try {
                    arrayList.add(PictureFrame.fromPictureBlock(new ParsableByteArray(Base64.decode(splitAtFirst[1], 0))));
                } catch (RuntimeException e) {
                    Log.w("VorbisUtil", "Failed to parse vorbis picture", e);
                }
            } else {
                arrayList.add(new VorbisComment(splitAtFirst[0], splitAtFirst[1]));
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    public static U parseVorbisCsdFromEsdsInitializationData(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        parsableByteArray.skipBytes(1);
        int i2 = 0;
        while (parsableByteArray.bytesLeft() > 0 && parsableByteArray.peekUnsignedByte() == 255) {
            i2 += ScoverState.TYPE_NFC_SMART_COVER;
            parsableByteArray.skipBytes(1);
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte() + i2;
        int i7 = 0;
        while (parsableByteArray.bytesLeft() > 0 && parsableByteArray.peekUnsignedByte() == 255) {
            i7 += ScoverState.TYPE_NFC_SMART_COVER;
            parsableByteArray.skipBytes(1);
        }
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte() + i7;
        byte[] bArr2 = new byte[readUnsignedByte];
        int position = parsableByteArray.getPosition();
        System.arraycopy(bArr, position, bArr2, 0, readUnsignedByte);
        int i8 = position + readUnsignedByte + readUnsignedByte2;
        int length = bArr.length - i8;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, i8, bArr3, 0, length);
        return U.D(bArr2, bArr3);
    }

    private static void readFloors(VorbisBitArray vorbisBitArray) {
        int readBits = vorbisBitArray.readBits(6) + 1;
        for (int i2 = 0; i2 < readBits; i2++) {
            int readBits2 = vorbisBitArray.readBits(16);
            if (readBits2 == 0) {
                vorbisBitArray.skipBits(8);
                vorbisBitArray.skipBits(16);
                vorbisBitArray.skipBits(16);
                vorbisBitArray.skipBits(6);
                vorbisBitArray.skipBits(8);
                int readBits3 = vorbisBitArray.readBits(4) + 1;
                for (int i7 = 0; i7 < readBits3; i7++) {
                    vorbisBitArray.skipBits(8);
                }
            } else if (readBits2 == 1) {
                int readBits4 = vorbisBitArray.readBits(5);
                int[] iArr = new int[readBits4];
                int i8 = -1;
                for (int i10 = 0; i10 < readBits4; i10++) {
                    int readBits5 = vorbisBitArray.readBits(4);
                    iArr[i10] = readBits5;
                    if (readBits5 > i8) {
                        i8 = readBits5;
                    }
                }
                int i11 = i8 + 1;
                int[] iArr2 = new int[i11];
                for (int i12 = 0; i12 < i11; i12++) {
                    iArr2[i12] = vorbisBitArray.readBits(3) + 1;
                    int readBits6 = vorbisBitArray.readBits(2);
                    if (readBits6 > 0) {
                        vorbisBitArray.skipBits(8);
                    }
                    for (int i13 = 0; i13 < (1 << readBits6); i13++) {
                        vorbisBitArray.skipBits(8);
                    }
                }
                vorbisBitArray.skipBits(2);
                int readBits7 = vorbisBitArray.readBits(4);
                int i14 = 0;
                int i15 = 0;
                for (int i16 = 0; i16 < readBits4; i16++) {
                    i14 += iArr2[iArr[i16]];
                    while (i15 < i14) {
                        vorbisBitArray.skipBits(readBits7);
                        i15++;
                    }
                }
            } else {
                throw ParserException.createForMalformedContainer("floor type greater than 1 not decodable: " + readBits2, (Throwable) null);
            }
        }
    }

    private static void readMappings(int i2, VorbisBitArray vorbisBitArray) {
        int i7;
        int readBits = vorbisBitArray.readBits(6) + 1;
        for (int i8 = 0; i8 < readBits; i8++) {
            int readBits2 = vorbisBitArray.readBits(16);
            if (readBits2 != 0) {
                Log.e("VorbisUtil", "mapping type other than 0 not supported: " + readBits2);
            } else {
                if (vorbisBitArray.readBit()) {
                    i7 = vorbisBitArray.readBits(4) + 1;
                } else {
                    i7 = 1;
                }
                if (vorbisBitArray.readBit()) {
                    int readBits3 = vorbisBitArray.readBits(8) + 1;
                    for (int i10 = 0; i10 < readBits3; i10++) {
                        int i11 = i2 - 1;
                        vorbisBitArray.skipBits(iLog(i11));
                        vorbisBitArray.skipBits(iLog(i11));
                    }
                }
                if (vorbisBitArray.readBits(2) == 0) {
                    if (i7 > 1) {
                        for (int i12 = 0; i12 < i2; i12++) {
                            vorbisBitArray.skipBits(4);
                        }
                    }
                    for (int i13 = 0; i13 < i7; i13++) {
                        vorbisBitArray.skipBits(8);
                        vorbisBitArray.skipBits(8);
                        vorbisBitArray.skipBits(8);
                    }
                } else {
                    throw ParserException.createForMalformedContainer("to reserved bits must be zero after mapping coupling steps", (Throwable) null);
                }
            }
        }
    }

    private static Mode[] readModes(VorbisBitArray vorbisBitArray) {
        int readBits = vorbisBitArray.readBits(6) + 1;
        Mode[] modeArr = new Mode[readBits];
        for (int i2 = 0; i2 < readBits; i2++) {
            modeArr[i2] = new Mode(vorbisBitArray.readBit(), vorbisBitArray.readBits(16), vorbisBitArray.readBits(16), vorbisBitArray.readBits(8));
        }
        return modeArr;
    }

    private static void readResidues(VorbisBitArray vorbisBitArray) {
        int i2;
        int readBits = vorbisBitArray.readBits(6) + 1;
        int i7 = 0;
        while (i7 < readBits) {
            if (vorbisBitArray.readBits(16) <= 2) {
                vorbisBitArray.skipBits(24);
                vorbisBitArray.skipBits(24);
                vorbisBitArray.skipBits(24);
                int readBits2 = vorbisBitArray.readBits(6) + 1;
                vorbisBitArray.skipBits(8);
                int[] iArr = new int[readBits2];
                for (int i8 = 0; i8 < readBits2; i8++) {
                    int readBits3 = vorbisBitArray.readBits(3);
                    if (vorbisBitArray.readBit()) {
                        i2 = vorbisBitArray.readBits(5);
                    } else {
                        i2 = 0;
                    }
                    iArr[i8] = (i2 * 8) + readBits3;
                }
                for (int i10 = 0; i10 < readBits2; i10++) {
                    for (int i11 = 0; i11 < 8; i11++) {
                        if ((iArr[i10] & (1 << i11)) != 0) {
                            vorbisBitArray.skipBits(8);
                        }
                    }
                }
                i7++;
            } else {
                throw ParserException.createForMalformedContainer("residueType greater than 2 is not decodable", (Throwable) null);
            }
        }
    }

    public static CommentHeader readVorbisCommentHeader(ParsableByteArray parsableByteArray) {
        return readVorbisCommentHeader(parsableByteArray, true, true);
    }

    public static VorbisIdHeader readVorbisIdentificationHeader(ParsableByteArray parsableByteArray) {
        boolean z;
        verifyVorbisHeaderCapturePattern(1, parsableByteArray, false);
        int readLittleEndianUnsignedIntToInt = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readLittleEndianUnsignedIntToInt2 = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        if (readLittleEndianInt <= 0) {
            readLittleEndianInt = -1;
        }
        int readLittleEndianInt2 = parsableByteArray.readLittleEndianInt();
        if (readLittleEndianInt2 <= 0) {
            readLittleEndianInt2 = -1;
        }
        int readLittleEndianInt3 = parsableByteArray.readLittleEndianInt();
        if (readLittleEndianInt3 <= 0) {
            readLittleEndianInt3 = -1;
        }
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        int pow = (int) Math.pow(2.0d, (double) (readUnsignedByte2 & 15));
        int pow2 = (int) Math.pow(2.0d, (double) ((readUnsignedByte2 & 240) >> 4));
        if ((parsableByteArray.readUnsignedByte() & 1) > 0) {
            z = true;
        } else {
            z = false;
        }
        return new VorbisIdHeader(readLittleEndianUnsignedIntToInt, readUnsignedByte, readLittleEndianUnsignedIntToInt2, readLittleEndianInt, readLittleEndianInt2, readLittleEndianInt3, pow, pow2, z, Arrays.copyOf(parsableByteArray.getData(), parsableByteArray.limit()));
    }

    public static Mode[] readVorbisModes(ParsableByteArray parsableByteArray, int i2) {
        int i7 = 0;
        verifyVorbisHeaderCapturePattern(5, parsableByteArray, false);
        int readUnsignedByte = parsableByteArray.readUnsignedByte() + 1;
        VorbisBitArray vorbisBitArray = new VorbisBitArray(parsableByteArray.getData());
        vorbisBitArray.skipBits(parsableByteArray.getPosition() * 8);
        for (int i8 = 0; i8 < readUnsignedByte; i8++) {
            skipBook(vorbisBitArray);
        }
        int readBits = vorbisBitArray.readBits(6) + 1;
        while (i7 < readBits) {
            if (vorbisBitArray.readBits(16) == 0) {
                i7++;
            } else {
                throw ParserException.createForMalformedContainer("placeholder of time domain transforms not zeroed out", (Throwable) null);
            }
        }
        readFloors(vorbisBitArray);
        readResidues(vorbisBitArray);
        readMappings(i2, vorbisBitArray);
        Mode[] readModes = readModes(vorbisBitArray);
        if (vorbisBitArray.readBit()) {
            return readModes;
        }
        throw ParserException.createForMalformedContainer("framing bit after modes not set as expected", (Throwable) null);
    }

    private static void skipBook(VorbisBitArray vorbisBitArray) {
        long j2;
        if (vorbisBitArray.readBits(24) == 5653314) {
            int readBits = vorbisBitArray.readBits(16);
            int readBits2 = vorbisBitArray.readBits(24);
            int i2 = 0;
            if (!vorbisBitArray.readBit()) {
                boolean readBit = vorbisBitArray.readBit();
                while (i2 < readBits2) {
                    if (!readBit) {
                        vorbisBitArray.skipBits(5);
                    } else if (vorbisBitArray.readBit()) {
                        vorbisBitArray.skipBits(5);
                    }
                    i2++;
                }
            } else {
                vorbisBitArray.skipBits(5);
                while (i2 < readBits2) {
                    i2 += vorbisBitArray.readBits(iLog(readBits2 - i2));
                }
            }
            int readBits3 = vorbisBitArray.readBits(4);
            if (readBits3 > 2) {
                throw ParserException.createForMalformedContainer("lookup type greater than 2 not decodable: " + readBits3, (Throwable) null);
            } else if (readBits3 == 1 || readBits3 == 2) {
                vorbisBitArray.skipBits(32);
                vorbisBitArray.skipBits(32);
                int readBits4 = vorbisBitArray.readBits(4) + 1;
                vorbisBitArray.skipBits(1);
                if (readBits3 != 1) {
                    j2 = ((long) readBits) * ((long) readBits2);
                } else if (readBits != 0) {
                    j2 = mapType1QuantValues((long) readBits2, (long) readBits);
                } else {
                    j2 = 0;
                }
                vorbisBitArray.skipBits((int) (j2 * ((long) readBits4)));
            }
        } else {
            throw ParserException.createForMalformedContainer("expected code book to start with [0x56, 0x43, 0x42] at " + vorbisBitArray.getPosition(), (Throwable) null);
        }
    }

    public static boolean verifyVorbisHeaderCapturePattern(int i2, ParsableByteArray parsableByteArray, boolean z) {
        if (parsableByteArray.bytesLeft() < 7) {
            if (z) {
                return false;
            }
            throw ParserException.createForMalformedContainer("too short header: " + parsableByteArray.bytesLeft(), (Throwable) null);
        } else if (parsableByteArray.readUnsignedByte() != i2) {
            if (z) {
                return false;
            }
            throw ParserException.createForMalformedContainer("expected header type " + Integer.toHexString(i2), (Throwable) null);
        } else if (parsableByteArray.readUnsignedByte() == 118 && parsableByteArray.readUnsignedByte() == 111 && parsableByteArray.readUnsignedByte() == 114 && parsableByteArray.readUnsignedByte() == 98 && parsableByteArray.readUnsignedByte() == 105 && parsableByteArray.readUnsignedByte() == 115) {
            return true;
        } else {
            if (z) {
                return false;
            }
            throw ParserException.createForMalformedContainer("expected characters 'vorbis'", (Throwable) null);
        }
    }

    public static CommentHeader readVorbisCommentHeader(ParsableByteArray parsableByteArray, boolean z, boolean z3) {
        if (z) {
            verifyVorbisHeaderCapturePattern(3, parsableByteArray, false);
        }
        String readString = parsableByteArray.readString((int) parsableByteArray.readLittleEndianUnsignedInt());
        int length = readString.length();
        long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
        String[] strArr = new String[((int) readLittleEndianUnsignedInt)];
        int i2 = length + 15;
        for (int i7 = 0; ((long) i7) < readLittleEndianUnsignedInt; i7++) {
            String readString2 = parsableByteArray.readString((int) parsableByteArray.readLittleEndianUnsignedInt());
            strArr[i7] = readString2;
            i2 = i2 + 4 + readString2.length();
        }
        if (!z3 || (parsableByteArray.readUnsignedByte() & 1) != 0) {
            return new CommentHeader(readString, strArr, i2 + 1);
        }
        throw ParserException.createForMalformedContainer("framing bit expected to be set", (Throwable) null);
    }
}
