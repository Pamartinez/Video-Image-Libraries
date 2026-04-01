package androidx.profileinstaller;

import c0.C0086a;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ProfileTranscoder {
    static final byte[] MAGIC_PROF = {112, 114, 111, 0};
    static final byte[] MAGIC_PROFM = {112, 114, 109, 0};

    private static int computeMethodFlags(DexProfileData dexProfileData) {
        int i2 = 0;
        for (Map.Entry<Integer, Integer> value : dexProfileData.methods.entrySet()) {
            i2 |= ((Integer) value.getValue()).intValue();
        }
        return i2;
    }

    private static byte[] createCompressibleBody(DexProfileData[] dexProfileDataArr, byte[] bArr) {
        int i2 = 0;
        int i7 = 0;
        for (DexProfileData dexProfileData : dexProfileDataArr) {
            i7 += (dexProfileData.classSetSize * 2) + Encoding.utf8Length(generateDexKey(dexProfileData.apkName, dexProfileData.dexName, bArr)) + 16 + dexProfileData.hotMethodRegionSize + getMethodBitmapStorageSize(dexProfileData.numMethodIds);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i7);
        if (Arrays.equals(bArr, ProfileVersion.V009_O_MR1)) {
            int length = dexProfileDataArr.length;
            while (i2 < length) {
                DexProfileData dexProfileData2 = dexProfileDataArr[i2];
                writeLineHeader(byteArrayOutputStream, dexProfileData2, generateDexKey(dexProfileData2.apkName, dexProfileData2.dexName, bArr));
                writeLineData(byteArrayOutputStream, dexProfileData2);
                i2++;
            }
        } else {
            for (DexProfileData dexProfileData3 : dexProfileDataArr) {
                writeLineHeader(byteArrayOutputStream, dexProfileData3, generateDexKey(dexProfileData3.apkName, dexProfileData3.dexName, bArr));
            }
            int length2 = dexProfileDataArr.length;
            while (i2 < length2) {
                writeLineData(byteArrayOutputStream, dexProfileDataArr[i2]);
                i2++;
            }
        }
        if (byteArrayOutputStream.size() == i7) {
            return byteArrayOutputStream.toByteArray();
        }
        throw Encoding.error("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + i7);
    }

    private static WritableFileSection createCompressibleClassSection(DexProfileData[] dexProfileDataArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        int i7 = 0;
        while (i2 < dexProfileDataArr.length) {
            try {
                DexProfileData dexProfileData = dexProfileDataArr[i2];
                Encoding.writeUInt16(byteArrayOutputStream, i2);
                Encoding.writeUInt16(byteArrayOutputStream, dexProfileData.classSetSize);
                i7 = i7 + 4 + (dexProfileData.classSetSize * 2);
                writeClasses(byteArrayOutputStream, dexProfileData);
                i2++;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (i7 == byteArray.length) {
            WritableFileSection writableFileSection = new WritableFileSection(FileSectionType.CLASSES, i7, byteArray, true);
            byteArrayOutputStream.close();
            return writableFileSection;
        }
        throw Encoding.error("Expected size " + i7 + ", does not match actual size " + byteArray.length);
        throw th;
    }

    private static WritableFileSection createCompressibleMethodsSection(DexProfileData[] dexProfileDataArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        int i7 = 0;
        while (i2 < dexProfileDataArr.length) {
            try {
                DexProfileData dexProfileData = dexProfileDataArr[i2];
                int computeMethodFlags = computeMethodFlags(dexProfileData);
                byte[] createMethodBitmapRegionForS = createMethodBitmapRegionForS(computeMethodFlags, dexProfileData);
                byte[] createMethodsWithInlineCaches = createMethodsWithInlineCaches(dexProfileData);
                Encoding.writeUInt16(byteArrayOutputStream, i2);
                int length = createMethodBitmapRegionForS.length + 2 + createMethodsWithInlineCaches.length;
                Encoding.writeUInt32(byteArrayOutputStream, (long) length);
                Encoding.writeUInt16(byteArrayOutputStream, computeMethodFlags);
                byteArrayOutputStream.write(createMethodBitmapRegionForS);
                byteArrayOutputStream.write(createMethodsWithInlineCaches);
                i7 = i7 + 6 + length;
                i2++;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (i7 == byteArray.length) {
            WritableFileSection writableFileSection = new WritableFileSection(FileSectionType.METHODS, i7, byteArray, true);
            byteArrayOutputStream.close();
            return writableFileSection;
        }
        throw Encoding.error("Expected size " + i7 + ", does not match actual size " + byteArray.length);
        throw th;
    }

    private static byte[] createMethodBitmapRegionForS(int i2, DexProfileData dexProfileData) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            writeMethodBitmapForS(byteArrayOutputStream, i2, dexProfileData);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static byte[] createMethodsWithInlineCaches(DexProfileData dexProfileData) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            writeMethodsWithInlineCaches(byteArrayOutputStream, dexProfileData);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static String enforceSeparator(String str, String str2) {
        if ("!".equals(str2)) {
            return str.replace(NumericEnum.SEP, "!");
        }
        if (NumericEnum.SEP.equals(str2)) {
            return str.replace("!", NumericEnum.SEP);
        }
        return str;
    }

    private static String extractKey(String str) {
        int indexOf = str.indexOf("!");
        if (indexOf < 0) {
            indexOf = str.indexOf(NumericEnum.SEP);
        }
        if (indexOf > 0) {
            return str.substring(indexOf + 1);
        }
        return str;
    }

    private static DexProfileData findByDexName(DexProfileData[] dexProfileDataArr, String str) {
        if (dexProfileDataArr.length <= 0) {
            return null;
        }
        String extractKey = extractKey(str);
        for (int i2 = 0; i2 < dexProfileDataArr.length; i2++) {
            if (dexProfileDataArr[i2].dexName.equals(extractKey)) {
                return dexProfileDataArr[i2];
            }
        }
        return null;
    }

    private static String generateDexKey(String str, String str2, byte[] bArr) {
        String dexKeySeparator = ProfileVersion.dexKeySeparator(bArr);
        if (str.length() <= 0) {
            return enforceSeparator(str2, dexKeySeparator);
        }
        if (str2.equals("classes.dex")) {
            return str;
        }
        if (str2.contains("!") || str2.contains(NumericEnum.SEP)) {
            return enforceSeparator(str2, dexKeySeparator);
        }
        if (str2.endsWith(".apk")) {
            return str2;
        }
        StringBuilder s = C0212a.s(str);
        s.append(ProfileVersion.dexKeySeparator(bArr));
        s.append(str2);
        return s.toString();
    }

    private static int getMethodBitmapStorageSize(int i2) {
        return roundUpToByte(i2 * 2) / 8;
    }

    private static int getMethodBitmapStorageSizeForS(int i2, int i7) {
        return roundUpToByte(Integer.bitCount(i2 & -2) * i7) / 8;
    }

    private static int methodFlagBitmapIndex(int i2, int i7, int i8) {
        if (i2 == 1) {
            throw Encoding.error("HOT methods are not stored in the bitmap");
        } else if (i2 == 2) {
            return i7;
        } else {
            if (i2 == 4) {
                return i7 + i8;
            }
            throw Encoding.error(C0086a.i(i2, "Unexpected flag: "));
        }
    }

    private static int[] readClasses(InputStream inputStream, int i2) {
        int[] iArr = new int[i2];
        int i7 = 0;
        for (int i8 = 0; i8 < i2; i8++) {
            i7 += Encoding.readUInt16(inputStream);
            iArr[i8] = i7;
        }
        return iArr;
    }

    private static int readFlagsFromBitmap(BitSet bitSet, int i2, int i7) {
        int i8 = 2;
        if (!bitSet.get(methodFlagBitmapIndex(2, i2, i7))) {
            i8 = 0;
        }
        if (bitSet.get(methodFlagBitmapIndex(4, i2, i7))) {
            return i8 | 4;
        }
        return i8;
    }

    public static byte[] readHeader(InputStream inputStream, byte[] bArr) {
        if (Arrays.equals(bArr, Encoding.read(inputStream, bArr.length))) {
            return Encoding.read(inputStream, ProfileVersion.V010_P.length);
        }
        throw Encoding.error("Invalid magic");
    }

    private static void readHotMethodRegion(InputStream inputStream, DexProfileData dexProfileData) {
        int available = inputStream.available() - dexProfileData.hotMethodRegionSize;
        int i2 = 0;
        while (inputStream.available() > available) {
            i2 += Encoding.readUInt16(inputStream);
            dexProfileData.methods.put(Integer.valueOf(i2), 1);
            for (int readUInt16 = Encoding.readUInt16(inputStream); readUInt16 > 0; readUInt16--) {
                skipInlineCache(inputStream);
            }
        }
        if (inputStream.available() != available) {
            throw Encoding.error("Read too much data during profile line parse");
        }
    }

    public static DexProfileData[] readMeta(InputStream inputStream, byte[] bArr, byte[] bArr2, DexProfileData[] dexProfileDataArr) {
        if (Arrays.equals(bArr, ProfileVersion.METADATA_V001_N)) {
            if (!Arrays.equals(ProfileVersion.V015_S, bArr2)) {
                return readMetadata001(inputStream, bArr, dexProfileDataArr);
            }
            throw Encoding.error("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
        } else if (Arrays.equals(bArr, ProfileVersion.METADATA_V002)) {
            return readMetadataV002(inputStream, bArr2, dexProfileDataArr);
        } else {
            throw Encoding.error("Unsupported meta version");
        }
    }

    public static DexProfileData[] readMetadata001(InputStream inputStream, byte[] bArr, DexProfileData[] dexProfileDataArr) {
        if (Arrays.equals(bArr, ProfileVersion.METADATA_V001_N)) {
            int readUInt8 = Encoding.readUInt8(inputStream);
            byte[] readCompressed = Encoding.readCompressed(inputStream, (int) Encoding.readUInt32(inputStream), (int) Encoding.readUInt32(inputStream));
            if (inputStream.read() <= 0) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(readCompressed);
                try {
                    DexProfileData[] readMetadataForNBody = readMetadataForNBody(byteArrayInputStream, readUInt8, dexProfileDataArr);
                    byteArrayInputStream.close();
                    return readMetadataForNBody;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                throw Encoding.error("Content found after the end of file");
            }
        } else {
            throw Encoding.error("Unsupported meta version");
        }
        throw th;
    }

    private static DexProfileData[] readMetadataForNBody(InputStream inputStream, int i2, DexProfileData[] dexProfileDataArr) {
        int i7 = 0;
        if (inputStream.available() == 0) {
            return new DexProfileData[0];
        }
        if (i2 == dexProfileDataArr.length) {
            String[] strArr = new String[i2];
            int[] iArr = new int[i2];
            for (int i8 = 0; i8 < i2; i8++) {
                int readUInt16 = Encoding.readUInt16(inputStream);
                iArr[i8] = Encoding.readUInt16(inputStream);
                strArr[i8] = Encoding.readString(inputStream, readUInt16);
            }
            while (i7 < i2) {
                DexProfileData dexProfileData = dexProfileDataArr[i7];
                if (dexProfileData.dexName.equals(strArr[i7])) {
                    int i10 = iArr[i7];
                    dexProfileData.classSetSize = i10;
                    dexProfileData.classes = readClasses(inputStream, i10);
                    i7++;
                } else {
                    throw Encoding.error("Order of dexfiles in metadata did not match baseline");
                }
            }
            return dexProfileDataArr;
        }
        throw Encoding.error("Mismatched number of dex files found in metadata");
    }

    public static DexProfileData[] readMetadataV002(InputStream inputStream, byte[] bArr, DexProfileData[] dexProfileDataArr) {
        int readUInt16 = Encoding.readUInt16(inputStream);
        byte[] readCompressed = Encoding.readCompressed(inputStream, (int) Encoding.readUInt32(inputStream), (int) Encoding.readUInt32(inputStream));
        if (inputStream.read() <= 0) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(readCompressed);
            try {
                DexProfileData[] readMetadataV002Body = readMetadataV002Body(byteArrayInputStream, bArr, readUInt16, dexProfileDataArr);
                byteArrayInputStream.close();
                return readMetadataV002Body;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            throw Encoding.error("Content found after the end of file");
        }
        throw th;
    }

    private static DexProfileData[] readMetadataV002Body(InputStream inputStream, byte[] bArr, int i2, DexProfileData[] dexProfileDataArr) {
        int i7 = 0;
        if (inputStream.available() == 0) {
            return new DexProfileData[0];
        }
        if (i2 == dexProfileDataArr.length) {
            while (i7 < i2) {
                Encoding.readUInt16(inputStream);
                String readString = Encoding.readString(inputStream, Encoding.readUInt16(inputStream));
                long readUInt32 = Encoding.readUInt32(inputStream);
                int readUInt16 = Encoding.readUInt16(inputStream);
                DexProfileData findByDexName = findByDexName(dexProfileDataArr, readString);
                if (findByDexName != null) {
                    findByDexName.mTypeIdCount = readUInt32;
                    int[] readClasses = readClasses(inputStream, readUInt16);
                    if (Arrays.equals(bArr, ProfileVersion.V001_N)) {
                        findByDexName.classSetSize = readUInt16;
                        findByDexName.classes = readClasses;
                    }
                    i7++;
                } else {
                    throw Encoding.error(C0212a.l("Missing profile key: ", readString));
                }
            }
            return dexProfileDataArr;
        }
        throw Encoding.error("Mismatched number of dex files found in metadata");
    }

    private static void readMethodBitmap(InputStream inputStream, DexProfileData dexProfileData) {
        BitSet valueOf = BitSet.valueOf(Encoding.read(inputStream, Encoding.bitsToBytes(dexProfileData.numMethodIds * 2)));
        int i2 = 0;
        while (true) {
            int i7 = dexProfileData.numMethodIds;
            if (i2 < i7) {
                int readFlagsFromBitmap = readFlagsFromBitmap(valueOf, i2, i7);
                if (readFlagsFromBitmap != 0) {
                    Integer num = dexProfileData.methods.get(Integer.valueOf(i2));
                    if (num == null) {
                        num = 0;
                    }
                    dexProfileData.methods.put(Integer.valueOf(i2), Integer.valueOf(readFlagsFromBitmap | num.intValue()));
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public static DexProfileData[] readProfile(InputStream inputStream, byte[] bArr, String str) {
        if (Arrays.equals(bArr, ProfileVersion.V010_P)) {
            int readUInt8 = Encoding.readUInt8(inputStream);
            byte[] readCompressed = Encoding.readCompressed(inputStream, (int) Encoding.readUInt32(inputStream), (int) Encoding.readUInt32(inputStream));
            if (inputStream.read() <= 0) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(readCompressed);
                try {
                    DexProfileData[] readUncompressedBody = readUncompressedBody(byteArrayInputStream, str, readUInt8);
                    byteArrayInputStream.close();
                    return readUncompressedBody;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                throw Encoding.error("Content found after the end of file");
            }
        } else {
            throw Encoding.error("Unsupported version");
        }
        throw th;
    }

    private static DexProfileData[] readUncompressedBody(InputStream inputStream, String str, int i2) {
        InputStream inputStream2 = inputStream;
        int i7 = i2;
        if (inputStream2.available() == 0) {
            return new DexProfileData[0];
        }
        DexProfileData[] dexProfileDataArr = new DexProfileData[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            int readUInt16 = Encoding.readUInt16(inputStream2);
            int readUInt162 = Encoding.readUInt16(inputStream2);
            long readUInt32 = Encoding.readUInt32(inputStream2);
            long readUInt322 = Encoding.readUInt32(inputStream2);
            long readUInt323 = Encoding.readUInt32(inputStream2);
            dexProfileDataArr[i8] = new DexProfileData(str, Encoding.readString(inputStream2, readUInt16), readUInt322, 0, readUInt162, (int) readUInt32, (int) readUInt323, new int[readUInt162], new TreeMap());
        }
        for (int i10 = 0; i10 < i7; i10++) {
            DexProfileData dexProfileData = dexProfileDataArr[i10];
            readHotMethodRegion(inputStream2, dexProfileData);
            dexProfileData.classes = readClasses(inputStream2, dexProfileData.classSetSize);
            readMethodBitmap(inputStream2, dexProfileData);
        }
        return dexProfileDataArr;
    }

    private static int roundUpToByte(int i2) {
        return (i2 + 7) & -8;
    }

    private static void setMethodBitmapBit(byte[] bArr, int i2, int i7, DexProfileData dexProfileData) {
        int methodFlagBitmapIndex = methodFlagBitmapIndex(i2, i7, dexProfileData.numMethodIds);
        int i8 = methodFlagBitmapIndex / 8;
        bArr[i8] = (byte) ((1 << (methodFlagBitmapIndex % 8)) | bArr[i8]);
    }

    private static void skipInlineCache(InputStream inputStream) {
        Encoding.readUInt16(inputStream);
        int readUInt8 = Encoding.readUInt8(inputStream);
        if (readUInt8 != 6 && readUInt8 != 7) {
            while (readUInt8 > 0) {
                Encoding.readUInt8(inputStream);
                for (int readUInt82 = Encoding.readUInt8(inputStream); readUInt82 > 0; readUInt82--) {
                    Encoding.readUInt16(inputStream);
                }
                readUInt8--;
            }
        }
    }

    public static boolean transcodeAndWriteBody(OutputStream outputStream, byte[] bArr, DexProfileData[] dexProfileDataArr) {
        if (Arrays.equals(bArr, ProfileVersion.V015_S)) {
            writeProfileForS(outputStream, dexProfileDataArr);
            return true;
        } else if (Arrays.equals(bArr, ProfileVersion.V010_P)) {
            writeProfileForP(outputStream, dexProfileDataArr);
            return true;
        } else if (Arrays.equals(bArr, ProfileVersion.V005_O)) {
            writeProfileForO(outputStream, dexProfileDataArr);
            return true;
        } else if (Arrays.equals(bArr, ProfileVersion.V009_O_MR1)) {
            writeProfileForO_MR1(outputStream, dexProfileDataArr);
            return true;
        } else if (!Arrays.equals(bArr, ProfileVersion.V001_N)) {
            return false;
        } else {
            writeProfileForN(outputStream, dexProfileDataArr);
            return true;
        }
    }

    private static void writeClasses(OutputStream outputStream, DexProfileData dexProfileData) {
        int[] iArr = dexProfileData.classes;
        int length = iArr.length;
        int i2 = 0;
        int i7 = 0;
        while (i2 < length) {
            int i8 = iArr[i2];
            Encoding.writeUInt16(outputStream, i8 - i7);
            i2++;
            i7 = i8;
        }
    }

    private static WritableFileSection writeDexFileSection(DexProfileData[] dexProfileDataArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Encoding.writeUInt16(byteArrayOutputStream, dexProfileDataArr.length);
            int i2 = 2;
            for (DexProfileData dexProfileData : dexProfileDataArr) {
                Encoding.writeUInt32(byteArrayOutputStream, dexProfileData.dexChecksum);
                Encoding.writeUInt32(byteArrayOutputStream, dexProfileData.mTypeIdCount);
                Encoding.writeUInt32(byteArrayOutputStream, (long) dexProfileData.numMethodIds);
                String generateDexKey = generateDexKey(dexProfileData.apkName, dexProfileData.dexName, ProfileVersion.V015_S);
                int utf8Length = Encoding.utf8Length(generateDexKey);
                Encoding.writeUInt16(byteArrayOutputStream, utf8Length);
                i2 = i2 + 14 + utf8Length;
                Encoding.writeString(byteArrayOutputStream, generateDexKey);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (i2 == byteArray.length) {
                WritableFileSection writableFileSection = new WritableFileSection(FileSectionType.DEX_FILES, i2, byteArray, false);
                byteArrayOutputStream.close();
                return writableFileSection;
            }
            throw Encoding.error("Expected size " + i2 + ", does not match actual size " + byteArray.length);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static void writeHeader(OutputStream outputStream, byte[] bArr) {
        outputStream.write(MAGIC_PROF);
        outputStream.write(bArr);
    }

    private static void writeLineData(OutputStream outputStream, DexProfileData dexProfileData) {
        writeMethodsWithInlineCaches(outputStream, dexProfileData);
        writeClasses(outputStream, dexProfileData);
        writeMethodBitmap(outputStream, dexProfileData);
    }

    private static void writeLineHeader(OutputStream outputStream, DexProfileData dexProfileData, String str) {
        Encoding.writeUInt16(outputStream, Encoding.utf8Length(str));
        Encoding.writeUInt16(outputStream, dexProfileData.classSetSize);
        Encoding.writeUInt32(outputStream, (long) dexProfileData.hotMethodRegionSize);
        Encoding.writeUInt32(outputStream, dexProfileData.dexChecksum);
        Encoding.writeUInt32(outputStream, (long) dexProfileData.numMethodIds);
        Encoding.writeString(outputStream, str);
    }

    private static void writeMethodBitmap(OutputStream outputStream, DexProfileData dexProfileData) {
        byte[] bArr = new byte[getMethodBitmapStorageSize(dexProfileData.numMethodIds)];
        for (Map.Entry next : dexProfileData.methods.entrySet()) {
            int intValue = ((Integer) next.getKey()).intValue();
            int intValue2 = ((Integer) next.getValue()).intValue();
            if ((intValue2 & 2) != 0) {
                setMethodBitmapBit(bArr, 2, intValue, dexProfileData);
            }
            if ((intValue2 & 4) != 0) {
                setMethodBitmapBit(bArr, 4, intValue, dexProfileData);
            }
        }
        outputStream.write(bArr);
    }

    private static void writeMethodBitmapForS(OutputStream outputStream, int i2, DexProfileData dexProfileData) {
        byte[] bArr = new byte[getMethodBitmapStorageSizeForS(i2, dexProfileData.numMethodIds)];
        for (Map.Entry next : dexProfileData.methods.entrySet()) {
            int intValue = ((Integer) next.getKey()).intValue();
            int intValue2 = ((Integer) next.getValue()).intValue();
            int i7 = 0;
            for (int i8 = 1; i8 <= 4; i8 <<= 1) {
                if (!(i8 == 1 || (i8 & i2) == 0)) {
                    if ((i8 & intValue2) == i8) {
                        int i10 = (dexProfileData.numMethodIds * i7) + intValue;
                        int i11 = i10 / 8;
                        bArr[i11] = (byte) ((1 << (i10 % 8)) | bArr[i11]);
                    }
                    i7++;
                }
            }
        }
        outputStream.write(bArr);
    }

    private static void writeMethodsWithInlineCaches(OutputStream outputStream, DexProfileData dexProfileData) {
        int i2 = 0;
        for (Map.Entry next : dexProfileData.methods.entrySet()) {
            int intValue = ((Integer) next.getKey()).intValue();
            if ((((Integer) next.getValue()).intValue() & 1) != 0) {
                Encoding.writeUInt16(outputStream, intValue - i2);
                Encoding.writeUInt16(outputStream, 0);
                i2 = intValue;
            }
        }
    }

    private static void writeProfileForN(OutputStream outputStream, DexProfileData[] dexProfileDataArr) {
        Encoding.writeUInt16(outputStream, dexProfileDataArr.length);
        for (DexProfileData dexProfileData : dexProfileDataArr) {
            String generateDexKey = generateDexKey(dexProfileData.apkName, dexProfileData.dexName, ProfileVersion.V001_N);
            Encoding.writeUInt16(outputStream, Encoding.utf8Length(generateDexKey));
            Encoding.writeUInt16(outputStream, dexProfileData.methods.size());
            Encoding.writeUInt16(outputStream, dexProfileData.classes.length);
            Encoding.writeUInt32(outputStream, dexProfileData.dexChecksum);
            Encoding.writeString(outputStream, generateDexKey);
            for (Integer intValue : dexProfileData.methods.keySet()) {
                Encoding.writeUInt16(outputStream, intValue.intValue());
            }
            for (int writeUInt16 : dexProfileData.classes) {
                Encoding.writeUInt16(outputStream, writeUInt16);
            }
        }
    }

    private static void writeProfileForO(OutputStream outputStream, DexProfileData[] dexProfileDataArr) {
        Encoding.writeUInt8(outputStream, dexProfileDataArr.length);
        for (DexProfileData dexProfileData : dexProfileDataArr) {
            String generateDexKey = generateDexKey(dexProfileData.apkName, dexProfileData.dexName, ProfileVersion.V005_O);
            Encoding.writeUInt16(outputStream, Encoding.utf8Length(generateDexKey));
            Encoding.writeUInt16(outputStream, dexProfileData.classes.length);
            Encoding.writeUInt32(outputStream, (long) (dexProfileData.methods.size() * 4));
            Encoding.writeUInt32(outputStream, dexProfileData.dexChecksum);
            Encoding.writeString(outputStream, generateDexKey);
            for (Integer intValue : dexProfileData.methods.keySet()) {
                Encoding.writeUInt16(outputStream, intValue.intValue());
                Encoding.writeUInt16(outputStream, 0);
            }
            for (int writeUInt16 : dexProfileData.classes) {
                Encoding.writeUInt16(outputStream, writeUInt16);
            }
        }
    }

    private static void writeProfileForO_MR1(OutputStream outputStream, DexProfileData[] dexProfileDataArr) {
        byte[] createCompressibleBody = createCompressibleBody(dexProfileDataArr, ProfileVersion.V009_O_MR1);
        Encoding.writeUInt8(outputStream, dexProfileDataArr.length);
        Encoding.writeCompressed(outputStream, createCompressibleBody);
    }

    private static void writeProfileForP(OutputStream outputStream, DexProfileData[] dexProfileDataArr) {
        byte[] createCompressibleBody = createCompressibleBody(dexProfileDataArr, ProfileVersion.V010_P);
        Encoding.writeUInt8(outputStream, dexProfileDataArr.length);
        Encoding.writeCompressed(outputStream, createCompressibleBody);
    }

    private static void writeProfileForS(OutputStream outputStream, DexProfileData[] dexProfileDataArr) {
        writeProfileSections(outputStream, dexProfileDataArr);
    }

    private static void writeProfileSections(OutputStream outputStream, DexProfileData[] dexProfileDataArr) {
        int length;
        ArrayList arrayList = new ArrayList(3);
        ArrayList arrayList2 = new ArrayList(3);
        arrayList.add(writeDexFileSection(dexProfileDataArr));
        arrayList.add(createCompressibleClassSection(dexProfileDataArr));
        arrayList.add(createCompressibleMethodsSection(dexProfileDataArr));
        long length2 = ((long) ProfileVersion.V015_S.length) + ((long) MAGIC_PROF.length) + 4 + ((long) (arrayList.size() * 16));
        Encoding.writeUInt32(outputStream, (long) arrayList.size());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            WritableFileSection writableFileSection = (WritableFileSection) arrayList.get(i2);
            Encoding.writeUInt32(outputStream, writableFileSection.mType.getValue());
            Encoding.writeUInt32(outputStream, length2);
            if (writableFileSection.mNeedsCompression) {
                byte[] bArr = writableFileSection.mContents;
                byte[] compress = Encoding.compress(bArr);
                arrayList2.add(compress);
                Encoding.writeUInt32(outputStream, (long) compress.length);
                Encoding.writeUInt32(outputStream, (long) bArr.length);
                length = compress.length;
            } else {
                arrayList2.add(writableFileSection.mContents);
                Encoding.writeUInt32(outputStream, (long) writableFileSection.mContents.length);
                Encoding.writeUInt32(outputStream, 0);
                length = writableFileSection.mContents.length;
            }
            length2 += (long) length;
        }
        for (int i7 = 0; i7 < arrayList2.size(); i7++) {
            outputStream.write((byte[]) arrayList2.get(i7));
        }
    }
}
