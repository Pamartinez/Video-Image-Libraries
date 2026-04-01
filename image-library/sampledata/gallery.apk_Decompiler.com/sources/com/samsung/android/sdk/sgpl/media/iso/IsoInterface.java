package com.samsung.android.sdk.sgpl.media.iso;

import N2.j;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import i.C0212a;
import java.io.EOFException;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IsoInterface {
    public static final int BOX_AUTH = 1635087464;
    public static final int BOX_FREE = 1718773093;
    public static final int BOX_FTYP = 1718909296;
    public static final int BOX_GPS = 1735422752;
    public static final int BOX_GPS0 = 1735422768;
    public static final int BOX_HDLR = 1751411826;
    public static final int BOX_ILOC = 1768714083;
    public static final int BOX_ILST = 1768715124;
    public static final int BOX_LOCI = 1819239273;
    public static final int BOX_MDAT = 1835295092;
    public static final int BOX_MDHD = 1835296868;
    public static final int BOX_MDIA = 1835297121;
    public static final int BOX_META = 1835365473;
    public static final int BOX_MOOF = 1836019558;
    public static final int BOX_MOOV = 1836019574;
    public static final int BOX_MVHD = 1836476516;
    public static final int BOX_SOCT = 1936679796;
    public static final int BOX_TKHD = 1953196132;
    public static final int BOX_TRAK = 1953653099;
    public static final int BOX_UDTA = 1969517665;
    public static final int BOX_UUID = 1970628964;
    public static final int BOX_XMP = 1481461855;
    public static final int BOX_XYZ = -1451722374;
    public static final int FOURCC_SOUN = 1936684398;
    public static final int FOURCC_VIDE = 1986618469;
    private static final boolean LOGV = Log.isLoggable(TAG, 6);
    private static final String TAG = "IsoInterface";
    private List<Box> mFlattened = new ArrayList();
    private List<Box> mRoots = new ArrayList();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Box {
        public List<Box> children;
        public byte[] data;
        public int extraData;
        public int headerSize;
        public Box parent = null;
        public final long[] range;
        public final int type;
        public UUID uuid;

        public Box(int i2, long[] jArr) {
            this.type = i2;
            this.range = jArr;
        }
    }

    private IsoInterface(FileDescriptor fileDescriptor) {
        try {
            int i2 = OsConstants.SEEK_SET;
            Os.lseek(fileDescriptor, 4, i2);
            try {
                int readInt = readInt(fileDescriptor);
                Log.w(TAG, MediaDefs.Image.HEIF.HEIF_FTYP_BOX + readInt);
                if (readInt == 1718909296) {
                    long lseek = Os.lseek(fileDescriptor, 0, OsConstants.SEEK_END);
                    Os.lseek(fileDescriptor, 0, i2);
                    while (true) {
                        Box parseNextBox = parseNextBox(fileDescriptor, lseek, "");
                        if (parseNextBox == null) {
                            break;
                        }
                        this.mRoots.add(parseNextBox);
                    }
                    ArrayDeque arrayDeque = new ArrayDeque(this.mRoots);
                    while (!arrayDeque.isEmpty()) {
                        Box box = (Box) arrayDeque.poll();
                        this.mFlattened.add(box);
                        List<Box> list = box.children;
                        if (list != null) {
                            arrayDeque.addAll(list);
                        }
                    }
                    return;
                }
            } catch (EOFException unused) {
            }
            if (LOGV) {
                Log.w(TAG, "Missing 'ftyp' header");
            }
        } catch (ErrnoException unused2) {
        }
    }

    public static IsoInterface fromFile(File file) {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            IsoInterface fromFileDescriptor = fromFileDescriptor(fileInputStream.getFD());
            fileInputStream.close();
            return fromFileDescriptor;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static IsoInterface fromFileDescriptor(FileDescriptor fileDescriptor) {
        return new IsoInterface(fileDescriptor);
    }

    private static boolean isBoxParent(int i2) {
        switch (i2) {
            case 1684631142:
            case 1701082227:
            case 1751740006:
            case 1752069225:
            case BOX_ILST /*1768715124*/:
            case 1768977007:
            case 1785737832:
            case BOX_MDIA /*1835297121*/:
            case BOX_META /*1835365473*/:
            case 1835430497:
            case 1835626086:
            case BOX_MOOF /*1836019558*/:
            case BOX_MOOV /*1836019574*/:
            case 1936289382:
            case 1937007212:
            case 1953653094:
            case BOX_TRAK /*1953653099*/:
            case 1953654118:
            case BOX_UDTA /*1969517665*/:
                return true;
            default:
                return false;
        }
    }

    public static boolean isSupportedMimeType(String str) {
        if (str != null) {
            String lowerCase = str.toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            char c5 = 65535;
            switch (lowerCase.hashCode()) {
                case -1664118678:
                    if (lowerCase.equals("video/3gp2")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case -1664118616:
                    if (lowerCase.equals("video/3gpp")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case -1662382439:
                    if (lowerCase.equals(Encode.ContentType.VIDEO_MPEG)) {
                        c5 = 2;
                        break;
                    }
                    break;
                case -648684635:
                    if (lowerCase.equals("audio/3gpp2")) {
                        c5 = 3;
                        break;
                    }
                    break;
                case -48069494:
                    if (lowerCase.equals(Encode.ContentType.VIDEO_3G2)) {
                        c5 = 4;
                        break;
                    }
                    break;
                case 13915911:
                    if (lowerCase.equals("video/x-flv")) {
                        c5 = 5;
                        break;
                    }
                    break;
                case 187078282:
                    if (lowerCase.equals("audio/aac")) {
                        c5 = 6;
                        break;
                    }
                    break;
                case 187090232:
                    if (lowerCase.equals("audio/mp4")) {
                        c5 = 7;
                        break;
                    }
                    break;
                case 1331847841:
                    if (lowerCase.equals("video/mj2")) {
                        c5 = 8;
                        break;
                    }
                    break;
                case 1331848029:
                    if (lowerCase.equals(Encode.ContentType.VIDEO_MP4)) {
                        c5 = 9;
                        break;
                    }
                    break;
                case 1503095279:
                    if (lowerCase.equals("audio/3gp2")) {
                        c5 = 10;
                        break;
                    }
                    break;
                case 1503095341:
                    if (lowerCase.equals(Encode.CodecsMime.AUDIO_CODEC_AMR)) {
                        c5 = 11;
                        break;
                    }
                    break;
                case 1504831518:
                    if (lowerCase.equals("audio/mpeg")) {
                        c5 = 12;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    return true;
                default:
                    return false;
            }
        } else {
            throw new NullPointerException("mimeType shouldn't be null");
        }
    }

    private static Box parseNextBox(FileDescriptor fileDescriptor, long j2, String str) {
        long j3;
        FileDescriptor fileDescriptor2 = fileDescriptor;
        long j8 = j2;
        String str2 = str;
        long lseek = Os.lseek(fileDescriptor2, 0, OsConstants.SEEK_CUR);
        long j10 = j8 - lseek;
        int i2 = 8;
        if (j10 < ((long) 8)) {
            return null;
        }
        long unsignedLong = Integer.toUnsignedLong(readInt(fileDescriptor2));
        int readInt = readInt(fileDescriptor2);
        if (unsignedLong != 0) {
            if (unsignedLong == 1) {
                j10 = (((long) readInt(fileDescriptor2)) & 4294967295L) | (((long) readInt(fileDescriptor2)) << 32);
                i2 = 16;
            } else {
                j10 = unsignedLong;
            }
        }
        int i7 = (j10 > ((long) i2) ? 1 : (j10 == ((long) i2) ? 0 : -1));
        if (i7 >= 0) {
            long j11 = lseek + j10;
            if (j11 <= j8) {
                Box box = new Box(readInt, new long[]{lseek, j10});
                box.headerSize = i2;
                if (readInt != 1751411826 || j10 < 20) {
                    j3 = lseek;
                } else {
                    j3 = lseek;
                    Os.lseek(fileDescriptor2, j3 + 16, OsConstants.SEEK_SET);
                    int readInt2 = readInt(fileDescriptor2);
                    if (readInt2 == 1986618469) {
                        box.extraData = FOURCC_VIDE;
                    } else if (readInt2 == 1936684398) {
                        box.extraData = FOURCC_SOUN;
                    }
                }
                if (readInt == 1970628964) {
                    box.headerSize += 16;
                    box.uuid = readUuid(fileDescriptor2);
                    if (LOGV) {
                        StringBuilder t = C0212a.t(str2, "  UUID ");
                        t.append(box.uuid);
                        Log.v(TAG, t.toString());
                    }
                    if (j10 > 2147483647L) {
                        Log.w(TAG, "Skipping abnormally large uuid box");
                        return null;
                    }
                    try {
                        byte[] bArr = new byte[((int) (j10 - ((long) box.headerSize)))];
                        box.data = bArr;
                        Os.read(fileDescriptor2, bArr, 0, bArr.length);
                    } catch (OutOfMemoryError e) {
                        Log.w(TAG, "Couldn't read large uuid box", e);
                        return null;
                    }
                } else if (readInt == 1481461855) {
                    if (j10 > 2147483647L) {
                        Log.w(TAG, "Skipping abnormally large xmp box");
                        return null;
                    }
                    try {
                        byte[] bArr2 = new byte[((int) (j10 - ((long) box.headerSize)))];
                        box.data = bArr2;
                        Os.read(fileDescriptor2, bArr2, 0, bArr2.length);
                    } catch (OutOfMemoryError e7) {
                        Log.w(TAG, "Couldn't read large xmp box", e7);
                        return null;
                    }
                } else if (readInt == 1835365473 && i7 != 0) {
                    readInt(fileDescriptor2);
                    if (readInt(fileDescriptor2) != 1751411826) {
                        box.headerSize += 4;
                    }
                    Os.lseek(fileDescriptor2, j3 + ((long) box.headerSize), OsConstants.SEEK_SET);
                }
                if (LOGV) {
                    StringBuilder t3 = C0212a.t(str2, "Found box ");
                    t3.append(typeToString(readInt));
                    t3.append(" at ");
                    t3.append(j3);
                    t3.append(" hdr ");
                    t3.append(box.headerSize);
                    t3.append(" length ");
                    t3.append(j10);
                    Log.v(TAG, t3.toString());
                }
                if (isBoxParent(readInt)) {
                    box.children = new ArrayList();
                    while (true) {
                        Box parseNextBox = parseNextBox(fileDescriptor2, j11, C0212a.A(str2, "  "));
                        if (parseNextBox == null) {
                            break;
                        }
                        box.children.add(parseNextBox);
                        parseNextBox.parent = box;
                    }
                }
                Os.lseek(fileDescriptor2, j11, OsConstants.SEEK_SET);
                return box;
            }
        }
        StringBuilder j12 = j.j(lseek, "Invalid box at ", " of length ");
        j12.append(j10);
        j12.append(". End of parent ");
        j12.append(j8);
        Log.w(TAG, j12.toString());
        return null;
    }

    private static int readInt(FileDescriptor fileDescriptor) {
        byte[] bArr = new byte[4];
        if (Os.read(fileDescriptor, bArr, 0, 4) == 4) {
            return Memory.peekInt(bArr, 0, ByteOrder.BIG_ENDIAN);
        }
        throw new EOFException();
    }

    private static UUID readUuid(FileDescriptor fileDescriptor) {
        return new UUID((((long) readInt(fileDescriptor)) << 32) | (((long) readInt(fileDescriptor)) & 4294967295L), (((long) readInt(fileDescriptor)) << 32) | (((long) readInt(fileDescriptor)) & 4294967295L));
    }

    public static String typeToString(int i2) {
        byte[] bArr = new byte[4];
        Memory.pokeInt(bArr, 0, i2, ByteOrder.BIG_ENDIAN);
        return new String(bArr);
    }

    public byte[] getBoxBytes(int i2) {
        for (Box next : this.mFlattened) {
            if (next.type == i2) {
                return next.data;
            }
        }
        return null;
    }

    public int getBoxHeaderSize(int i2) {
        for (Box next : this.mFlattened) {
            if (next.type == i2) {
                return next.headerSize;
            }
        }
        return -1;
    }

    public long[] getBoxRanges(int i2) {
        LongArray longArray = new LongArray();
        for (Box next : this.mFlattened) {
            if (next.type == i2) {
                longArray.add(next.range[0] + ((long) next.headerSize));
                long[] jArr = next.range;
                longArray.add(jArr[0] + jArr[1]);
            }
        }
        return longArray.toArray();
    }

    public long[] getBoxRangesWithHeaderSize(int i2) {
        LongArray longArray = new LongArray();
        for (Box next : this.mFlattened) {
            if (next.type == i2) {
                longArray.add(next.range[0]);
                long[] jArr = next.range;
                longArray.add(jArr[0] + jArr[1]);
            }
        }
        return longArray.toArray();
    }

    public long[] getPathRangesWithHeaderSize(int i2) {
        LongArray longArray = new LongArray();
        LongArray longArray2 = new LongArray();
        if (i2 != -1451722374 && i2 != 1969517665 && i2 != 1751411826 && i2 != 1635087464) {
            return longArray.toArray();
        }
        for (Box next : this.mFlattened) {
            if (next.type == i2) {
                longArray.add(next.range[0]);
                long[] jArr = next.range;
                longArray.add(jArr[0] + jArr[1]);
                for (Box box = next.parent; box != null; box = box.parent) {
                    longArray.add(box.range[0]);
                    long[] jArr2 = box.range;
                    longArray.add(jArr2[0] + jArr2[1]);
                    int i7 = box.type;
                    if (i7 == 1835295092) {
                        longArray.clear();
                        return longArray.toArray();
                    }
                    longArray2.add((long) i7);
                }
                return longArray.toArray();
            }
        }
        return longArray.toArray();
    }

    public long[] getTrackBoxRangesWithHeaderSize(int i2) {
        LongArray longArray = new LongArray();
        for (Box next : this.mFlattened) {
            if (next.type == 1751411826 && next.extraData == i2) {
                Box box = next.parent;
                if (box != null && box.type == 1835297121) {
                    box = box.parent;
                }
                if (box != null && box.type == 1953653099) {
                    longArray.add(box.range[0]);
                    long[] jArr = box.range;
                    longArray.add(jArr[0] + jArr[1]);
                }
            }
        }
        return longArray.toArray();
    }

    public byte[] getBoxBytes(UUID uuid) {
        for (Box next : this.mFlattened) {
            if (next.type == 1970628964 && Objects.equals(next.uuid, uuid)) {
                return next.data;
            }
        }
        return null;
    }

    public long[] getBoxRanges(UUID uuid) {
        LongArray longArray = new LongArray();
        for (Box next : this.mFlattened) {
            if (next.type == 1970628964 && Objects.equals(next.uuid, uuid)) {
                longArray.add(next.range[0] + ((long) next.headerSize));
                long[] jArr = next.range;
                longArray.add(jArr[0] + jArr[1]);
            }
        }
        return longArray.toArray();
    }
}
