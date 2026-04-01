package androidx.exifinterface.media;

import android.content.res.AssetManager;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import androidx.exifinterface.media.ExifInterfaceUtils;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExifInterface {
    /* access modifiers changed from: private */
    public static final Charset ASCII;
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_1 = {4};
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_2 = {8};
    public static final int[] BITS_PER_SAMPLE_RGB = {8, 8, 8};
    private static final Pattern DATETIME_PRIMARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
    private static final Pattern DATETIME_SECONDARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
    private static final boolean DEBUG = Log.isLoggable("ExifInterface", 3);
    /* access modifiers changed from: private */
    public static final byte[] EXIF_ASCII_PREFIX = {65, 83, 67, 73, 73, 0, 0, 0};
    private static final ExifTag[] EXIF_POINTER_TAGS;
    static final ExifTag[][] EXIF_TAGS;
    private static final List<Integer> FLIPPED_ROTATION_ORDER = Arrays.asList(new Integer[]{2, 7, 4, 5});
    private static final Pattern GPS_TIMESTAMP_PATTERN = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
    private static final byte[] HEIF_BRAND_AVIF = {97, 118, 105, 102};
    private static final byte[] HEIF_BRAND_AVIS = {97, 118, 105, 115};
    private static final byte[] HEIF_BRAND_HEIC = {104, 101, 105, 99};
    private static final byte[] HEIF_BRAND_MIF1 = {109, 105, 102, 49};
    private static final byte[] HEIF_TYPE_FTYP = {102, 116, 121, 112};
    static final byte[] IDENTIFIER_EXIF_APP1;
    private static final byte[] IDENTIFIER_XMP_APP1;
    private static final ExifTag[] IFD_EXIF_TAGS;
    /* access modifiers changed from: private */
    public static final int[] IFD_FORMAT_BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    /* access modifiers changed from: private */
    public static final String[] IFD_FORMAT_NAMES = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
    private static final ExifTag[] IFD_GPS_TAGS;
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS;
    private static final ExifTag[] IFD_THUMBNAIL_TAGS;
    private static final ExifTag[] IFD_TIFF_TAGS;
    static final byte[] JPEG_SIGNATURE = {-1, -40, -1};
    private static final Pattern NON_ZERO_TIME_PATTERN = Pattern.compile(".*[1-9].*");
    private static final ExifTag[] ORF_CAMERA_SETTINGS_TAGS;
    private static final ExifTag[] ORF_IMAGE_PROCESSING_TAGS;
    private static final byte[] ORF_MAKER_NOTE_HEADER_1 = {79, 76, 89, 77, 80, 0};
    private static final byte[] ORF_MAKER_NOTE_HEADER_2 = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
    private static final ExifTag[] ORF_MAKER_NOTE_TAGS;
    private static final ExifTag[] PEF_TAGS;
    static final byte[] PNG_ITXT_XMP_KEYWORD = "XML:com.adobe.xmp\u0000\u0000\u0000\u0000\u0000".getBytes(StandardCharsets.UTF_8);
    private static final byte[] PNG_SIGNATURE = {-119, 80, 78, 71, 13, 10, 26, 10};
    private static final Set<String> RATIONAL_TAGS_HANDLED_AS_DECIMALS_FOR_COMPATIBILITY = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance"})));
    private static final List<Integer> ROTATION_ORDER = Arrays.asList(new Integer[]{1, 6, 3, 8});
    private static final ExifTag TAG_RAF_IMAGE_SIZE = new ExifTag("StripOffsets", 273, 3);
    private static final byte[] WEBP_CHUNK_TYPE_ANIM = "ANIM".getBytes(Charset.defaultCharset());
    private static final byte[] WEBP_CHUNK_TYPE_ANMF = "ANMF".getBytes(Charset.defaultCharset());
    private static final byte[] WEBP_CHUNK_TYPE_EXIF = {69, 88, 73, 70};
    private static final byte[] WEBP_CHUNK_TYPE_VP8 = "VP8 ".getBytes(Charset.defaultCharset());
    private static final byte[] WEBP_CHUNK_TYPE_VP8L = "VP8L".getBytes(Charset.defaultCharset());
    private static final byte[] WEBP_CHUNK_TYPE_VP8X = "VP8X".getBytes(Charset.defaultCharset());
    private static final byte[] WEBP_SIGNATURE_1 = {82, 73, 70, 70};
    private static final byte[] WEBP_SIGNATURE_2 = {87, 69, 66, 80};
    private static final byte[] WEBP_VP8_SIGNATURE = {-99, 1, 42};
    private static final HashMap<Integer, Integer> sExifPointerTagMap = new HashMap<>();
    private static final HashMap<Integer, ExifTag>[] sExifTagMapsForReading;
    private static final HashMap<String, ExifTag>[] sExifTagMapsForWriting;
    private static final SimpleDateFormat sFormatterPrimary;
    private static final SimpleDateFormat sFormatterSecondary;
    private boolean mAreThumbnailStripsConsecutive;
    private AssetManager.AssetInputStream mAssetInputStream;
    private final HashMap<String, ExifAttribute>[] mAttributes;
    private Set<Integer> mAttributesOffsets;
    private ByteOrder mExifByteOrder;
    private boolean mFileOnDiskContainsSeparateXmpMarker;
    private String mFilename;
    private boolean mHasThumbnail;
    private boolean mHasThumbnailStrips;
    private boolean mIsExifDataOnly;
    private int mMimeType;
    private boolean mModified;
    private int mOffsetToExifData;
    private int mOrfMakerNoteOffset;
    private int mOrfThumbnailLength;
    private int mOrfThumbnailOffset;
    private FileDescriptor mSeekableFileDescriptor;
    private byte[] mThumbnailBytes;
    private int mThumbnailCompression;
    private int mThumbnailLength;
    private int mThumbnailOffset;
    private ExifAttribute mXmpFromSeparateMarker;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ByteOrderedDataOutputStream extends FilterOutputStream {
        private ByteOrder mByteOrder;
        final DataOutputStream mOutputStream;

        public ByteOrderedDataOutputStream(OutputStream outputStream, ByteOrder byteOrder) {
            super(outputStream);
            this.mOutputStream = new DataOutputStream(outputStream);
            this.mByteOrder = byteOrder;
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public void write(byte[] bArr) {
            this.mOutputStream.write(bArr);
        }

        public void writeByte(int i2) {
            this.mOutputStream.write(i2);
        }

        public void writeInt(int i2) {
            ByteOrder byteOrder = this.mByteOrder;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.mOutputStream.write(i2 & ScoverState.TYPE_NFC_SMART_COVER);
                this.mOutputStream.write((i2 >>> 8) & ScoverState.TYPE_NFC_SMART_COVER);
                this.mOutputStream.write((i2 >>> 16) & ScoverState.TYPE_NFC_SMART_COVER);
                this.mOutputStream.write((i2 >>> 24) & ScoverState.TYPE_NFC_SMART_COVER);
            } else if (byteOrder == ByteOrder.BIG_ENDIAN) {
                this.mOutputStream.write((i2 >>> 24) & ScoverState.TYPE_NFC_SMART_COVER);
                this.mOutputStream.write((i2 >>> 16) & ScoverState.TYPE_NFC_SMART_COVER);
                this.mOutputStream.write((i2 >>> 8) & ScoverState.TYPE_NFC_SMART_COVER);
                this.mOutputStream.write(i2 & ScoverState.TYPE_NFC_SMART_COVER);
            }
        }

        public void writeShort(short s) {
            ByteOrder byteOrder = this.mByteOrder;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.mOutputStream.write(s & 255);
                this.mOutputStream.write((s >>> 8) & ScoverState.TYPE_NFC_SMART_COVER);
            } else if (byteOrder == ByteOrder.BIG_ENDIAN) {
                this.mOutputStream.write((s >>> 8) & ScoverState.TYPE_NFC_SMART_COVER);
                this.mOutputStream.write(s & 255);
            }
        }

        public void writeUnsignedInt(long j2) {
            if (j2 <= 4294967295L) {
                writeInt((int) j2);
                return;
            }
            throw new IllegalArgumentException("val is larger than the maximum value of a 32-bit unsigned integer");
        }

        public void writeUnsignedShort(int i2) {
            if (i2 <= 65535) {
                writeShort((short) i2);
                return;
            }
            throw new IllegalArgumentException("val is larger than the maximum value of a 16-bit unsigned integer");
        }

        public void write(byte[] bArr, int i2, int i7) {
            this.mOutputStream.write(bArr, i2, i7);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ExifAttribute {
        public final byte[] bytes;
        public final long bytesOffset;
        public final int format;
        public final int numberOfComponents;

        public ExifAttribute(int i2, int i7, byte[] bArr) {
            this(i2, i7, -1, bArr);
        }

        public static ExifAttribute createByte(String str) {
            if (str.length() != 1 || str.charAt(0) < '0' || str.charAt(0) > '1') {
                byte[] bytes2 = str.getBytes(ExifInterface.ASCII);
                return new ExifAttribute(1, bytes2.length, bytes2);
            }
            return new ExifAttribute(1, 1, new byte[]{(byte) (str.charAt(0) - '0')});
        }

        public static ExifAttribute createDouble(double[] dArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[12] * dArr.length)]);
            wrap.order(byteOrder);
            for (double putDouble : dArr) {
                wrap.putDouble(putDouble);
            }
            return new ExifAttribute(12, dArr.length, wrap.array());
        }

        public static ExifAttribute createSLong(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[9] * iArr.length)]);
            wrap.order(byteOrder);
            for (int putInt : iArr) {
                wrap.putInt(putInt);
            }
            return new ExifAttribute(9, iArr.length, wrap.array());
        }

        public static ExifAttribute createSRational(Rational[] rationalArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[10] * rationalArr.length)]);
            wrap.order(byteOrder);
            for (Rational rational : rationalArr) {
                wrap.putInt((int) rational.numerator);
                wrap.putInt((int) rational.denominator);
            }
            return new ExifAttribute(10, rationalArr.length, wrap.array());
        }

        public static ExifAttribute createString(String str) {
            byte[] bytes2 = (str + 0).getBytes(ExifInterface.ASCII);
            return new ExifAttribute(2, bytes2.length, bytes2);
        }

        public static ExifAttribute createULong(long[] jArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[4] * jArr.length)]);
            wrap.order(byteOrder);
            for (long j2 : jArr) {
                wrap.putInt((int) j2);
            }
            return new ExifAttribute(4, jArr.length, wrap.array());
        }

        public static ExifAttribute createURational(Rational[] rationalArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[5] * rationalArr.length)]);
            wrap.order(byteOrder);
            for (Rational rational : rationalArr) {
                wrap.putInt((int) rational.numerator);
                wrap.putInt((int) rational.denominator);
            }
            return new ExifAttribute(5, rationalArr.length, wrap.array());
        }

        public static ExifAttribute createUShort(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[3] * iArr.length)]);
            wrap.order(byteOrder);
            for (int i2 : iArr) {
                wrap.putShort((short) i2);
            }
            return new ExifAttribute(3, iArr.length, wrap.array());
        }

        public double getDoubleValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a double value");
            } else if (value instanceof String) {
                return Double.parseDouble((String) value);
            } else {
                if (value instanceof long[]) {
                    long[] jArr = (long[]) value;
                    if (jArr.length == 1) {
                        return (double) jArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof int[]) {
                    int[] iArr = (int[]) value;
                    if (iArr.length == 1) {
                        return (double) iArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof double[]) {
                    double[] dArr = (double[]) value;
                    if (dArr.length == 1) {
                        return dArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof Rational[]) {
                    Rational[] rationalArr = (Rational[]) value;
                    if (rationalArr.length == 1) {
                        return rationalArr[0].calculate();
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a double value");
                }
            }
        }

        public int getIntValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a integer value");
            } else if (value instanceof String) {
                return Integer.parseInt((String) value);
            } else {
                if (value instanceof long[]) {
                    long[] jArr = (long[]) value;
                    if (jArr.length == 1) {
                        return (int) jArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof int[]) {
                    int[] iArr = (int[]) value;
                    if (iArr.length == 1) {
                        return iArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a integer value");
                }
            }
        }

        public String getStringValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                return null;
            }
            if (value instanceof String) {
                return (String) value;
            }
            StringBuilder sb2 = new StringBuilder();
            int i2 = 0;
            if (value instanceof long[]) {
                long[] jArr = (long[]) value;
                while (i2 < jArr.length) {
                    sb2.append(jArr[i2]);
                    i2++;
                    if (i2 != jArr.length) {
                        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    }
                }
                return sb2.toString();
            } else if (value instanceof int[]) {
                int[] iArr = (int[]) value;
                while (i2 < iArr.length) {
                    sb2.append(iArr[i2]);
                    i2++;
                    if (i2 != iArr.length) {
                        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    }
                }
                return sb2.toString();
            } else if (value instanceof double[]) {
                double[] dArr = (double[]) value;
                while (i2 < dArr.length) {
                    sb2.append(dArr[i2]);
                    i2++;
                    if (i2 != dArr.length) {
                        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    }
                }
                return sb2.toString();
            } else if (!(value instanceof Rational[])) {
                return null;
            } else {
                Rational[] rationalArr = (Rational[]) value;
                while (i2 < rationalArr.length) {
                    sb2.append(rationalArr[i2].numerator);
                    sb2.append('/');
                    sb2.append(rationalArr[i2].denominator);
                    i2++;
                    if (i2 != rationalArr.length) {
                        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    }
                }
                return sb2.toString();
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v17, resolved type: int[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v19, resolved type: long[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v23, resolved type: int[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v25, resolved type: int[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v29, resolved type: double[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v30, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v32, resolved type: double[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v33, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v34, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v35, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v36, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v37, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v38, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v39, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v40, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v41, resolved type: java.lang.String} */
        /* JADX WARNING: type inference failed for: r13v21, types: [androidx.exifinterface.media.ExifInterface$Rational[]] */
        /* JADX WARNING: type inference failed for: r13v27, types: [androidx.exifinterface.media.ExifInterface$Rational[]] */
        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            r4.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x003c, code lost:
            return r13;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x003d, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x003e, code lost:
            android.util.Log.e("ExifInterface", "IOException occurred while closing InputStream", r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0042, code lost:
            return r13;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
            r4.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:0x011f, code lost:
            return r12;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:83:0x0120, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:84:0x0121, code lost:
            android.util.Log.e("ExifInterface", "IOException occurred while closing InputStream", r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x0125, code lost:
            return r12;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:102:0x0157 A[SYNTHETIC, Splitter:B:102:0x0157] */
        /* JADX WARNING: Removed duplicated region for block: B:108:0x0163 A[SYNTHETIC, Splitter:B:108:0x0163] */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object getValue(java.nio.ByteOrder r13) {
            /*
                r12 = this;
                java.lang.String r1 = "IOException occurred while closing InputStream"
                java.lang.String r2 = "ExifInterface"
                r3 = 0
                androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r4 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream     // Catch:{ IOException -> 0x014d, all -> 0x014a }
                byte[] r0 = r12.bytes     // Catch:{ IOException -> 0x014d, all -> 0x014a }
                r4.<init>((byte[]) r0)     // Catch:{ IOException -> 0x014d, all -> 0x014a }
                r4.setByteOrder(r13)     // Catch:{ IOException -> 0x0035 }
                int r13 = r12.format     // Catch:{ IOException -> 0x0035 }
                r0 = 0
                switch(r13) {
                    case 1: goto L_0x0126;
                    case 2: goto L_0x00d1;
                    case 3: goto L_0x00c0;
                    case 4: goto L_0x00af;
                    case 5: goto L_0x0094;
                    case 6: goto L_0x0126;
                    case 7: goto L_0x00d1;
                    case 8: goto L_0x0083;
                    case 9: goto L_0x0072;
                    case 10: goto L_0x0055;
                    case 11: goto L_0x0043;
                    case 12: goto L_0x001f;
                    default: goto L_0x0015;
                }
            L_0x0015:
                r4.close()     // Catch:{ IOException -> 0x0019 }
                return r3
            L_0x0019:
                r0 = move-exception
                r12 = r0
                android.util.Log.e(r2, r1, r12)
                return r3
            L_0x001f:
                int r13 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                double[] r13 = new double[r13]     // Catch:{ IOException -> 0x0035 }
            L_0x0023:
                int r5 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                if (r0 >= r5) goto L_0x0039
                double r5 = r4.readDouble()     // Catch:{ IOException -> 0x0035 }
                r13[r0] = r5     // Catch:{ IOException -> 0x0035 }
                int r0 = r0 + 1
                goto L_0x0023
            L_0x0030:
                r0 = move-exception
                r12 = r0
                r3 = r4
                goto L_0x0161
            L_0x0035:
                r0 = move-exception
                r12 = r0
                goto L_0x0150
            L_0x0039:
                r4.close()     // Catch:{ IOException -> 0x003d }
                return r13
            L_0x003d:
                r0 = move-exception
                r12 = r0
                android.util.Log.e(r2, r1, r12)
                return r13
            L_0x0043:
                int r13 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                double[] r13 = new double[r13]     // Catch:{ IOException -> 0x0035 }
            L_0x0047:
                int r5 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                if (r0 >= r5) goto L_0x0039
                float r5 = r4.readFloat()     // Catch:{ IOException -> 0x0035 }
                double r5 = (double) r5     // Catch:{ IOException -> 0x0035 }
                r13[r0] = r5     // Catch:{ IOException -> 0x0035 }
                int r0 = r0 + 1
                goto L_0x0047
            L_0x0055:
                int r13 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                androidx.exifinterface.media.ExifInterface$Rational[] r13 = new androidx.exifinterface.media.ExifInterface.Rational[r13]     // Catch:{ IOException -> 0x0035 }
            L_0x0059:
                int r5 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                if (r0 >= r5) goto L_0x0039
                int r5 = r4.readInt()     // Catch:{ IOException -> 0x0035 }
                long r7 = (long) r5     // Catch:{ IOException -> 0x0035 }
                int r5 = r4.readInt()     // Catch:{ IOException -> 0x0035 }
                long r9 = (long) r5     // Catch:{ IOException -> 0x0035 }
                androidx.exifinterface.media.ExifInterface$Rational r6 = new androidx.exifinterface.media.ExifInterface$Rational     // Catch:{ IOException -> 0x0035 }
                r11 = 0
                r6.<init>(r7, r9)     // Catch:{ IOException -> 0x0035 }
                r13[r0] = r6     // Catch:{ IOException -> 0x0035 }
                int r0 = r0 + 1
                goto L_0x0059
            L_0x0072:
                int r13 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                int[] r13 = new int[r13]     // Catch:{ IOException -> 0x0035 }
            L_0x0076:
                int r5 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                if (r0 >= r5) goto L_0x0039
                int r5 = r4.readInt()     // Catch:{ IOException -> 0x0035 }
                r13[r0] = r5     // Catch:{ IOException -> 0x0035 }
                int r0 = r0 + 1
                goto L_0x0076
            L_0x0083:
                int r13 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                int[] r13 = new int[r13]     // Catch:{ IOException -> 0x0035 }
            L_0x0087:
                int r5 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                if (r0 >= r5) goto L_0x0039
                short r5 = r4.readShort()     // Catch:{ IOException -> 0x0035 }
                r13[r0] = r5     // Catch:{ IOException -> 0x0035 }
                int r0 = r0 + 1
                goto L_0x0087
            L_0x0094:
                int r13 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                androidx.exifinterface.media.ExifInterface$Rational[] r13 = new androidx.exifinterface.media.ExifInterface.Rational[r13]     // Catch:{ IOException -> 0x0035 }
            L_0x0098:
                int r5 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                if (r0 >= r5) goto L_0x0039
                long r7 = r4.readUnsignedInt()     // Catch:{ IOException -> 0x0035 }
                long r9 = r4.readUnsignedInt()     // Catch:{ IOException -> 0x0035 }
                androidx.exifinterface.media.ExifInterface$Rational r6 = new androidx.exifinterface.media.ExifInterface$Rational     // Catch:{ IOException -> 0x0035 }
                r11 = 0
                r6.<init>(r7, r9)     // Catch:{ IOException -> 0x0035 }
                r13[r0] = r6     // Catch:{ IOException -> 0x0035 }
                int r0 = r0 + 1
                goto L_0x0098
            L_0x00af:
                int r13 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                long[] r13 = new long[r13]     // Catch:{ IOException -> 0x0035 }
            L_0x00b3:
                int r5 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                if (r0 >= r5) goto L_0x0039
                long r5 = r4.readUnsignedInt()     // Catch:{ IOException -> 0x0035 }
                r13[r0] = r5     // Catch:{ IOException -> 0x0035 }
                int r0 = r0 + 1
                goto L_0x00b3
            L_0x00c0:
                int r13 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                int[] r13 = new int[r13]     // Catch:{ IOException -> 0x0035 }
            L_0x00c4:
                int r5 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                if (r0 >= r5) goto L_0x0039
                int r5 = r4.readUnsignedShort()     // Catch:{ IOException -> 0x0035 }
                r13[r0] = r5     // Catch:{ IOException -> 0x0035 }
                int r0 = r0 + 1
                goto L_0x00c4
            L_0x00d1:
                int r13 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                byte[] r5 = androidx.exifinterface.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x0035 }
                int r5 = r5.length     // Catch:{ IOException -> 0x0035 }
                if (r13 < r5) goto L_0x00f7
                r13 = r0
            L_0x00db:
                byte[] r5 = androidx.exifinterface.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x0035 }
                int r5 = r5.length     // Catch:{ IOException -> 0x0035 }
                if (r13 >= r5) goto L_0x00f2
                byte[] r5 = r12.bytes     // Catch:{ IOException -> 0x0035 }
                byte r5 = r5[r13]     // Catch:{ IOException -> 0x0035 }
                byte[] r6 = androidx.exifinterface.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x0035 }
                byte r6 = r6[r13]     // Catch:{ IOException -> 0x0035 }
                if (r5 == r6) goto L_0x00ef
                goto L_0x00f7
            L_0x00ef:
                int r13 = r13 + 1
                goto L_0x00db
            L_0x00f2:
                byte[] r13 = androidx.exifinterface.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x0035 }
                int r0 = r13.length     // Catch:{ IOException -> 0x0035 }
            L_0x00f7:
                java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0035 }
                r13.<init>()     // Catch:{ IOException -> 0x0035 }
            L_0x00fc:
                int r5 = r12.numberOfComponents     // Catch:{ IOException -> 0x0035 }
                if (r0 >= r5) goto L_0x0118
                byte[] r5 = r12.bytes     // Catch:{ IOException -> 0x0035 }
                byte r5 = r5[r0]     // Catch:{ IOException -> 0x0035 }
                if (r5 != 0) goto L_0x0107
                goto L_0x0118
            L_0x0107:
                r6 = 32
                if (r5 < r6) goto L_0x0110
                char r5 = (char) r5     // Catch:{ IOException -> 0x0035 }
                r13.append(r5)     // Catch:{ IOException -> 0x0035 }
                goto L_0x0115
            L_0x0110:
                r5 = 63
                r13.append(r5)     // Catch:{ IOException -> 0x0035 }
            L_0x0115:
                int r0 = r0 + 1
                goto L_0x00fc
            L_0x0118:
                java.lang.String r12 = r13.toString()     // Catch:{ IOException -> 0x0035 }
            L_0x011c:
                r4.close()     // Catch:{ IOException -> 0x0120 }
                return r12
            L_0x0120:
                r0 = move-exception
                r13 = r0
                android.util.Log.e(r2, r1, r13)
                return r12
            L_0x0126:
                byte[] r12 = r12.bytes     // Catch:{ IOException -> 0x0035 }
                int r13 = r12.length     // Catch:{ IOException -> 0x0035 }
                r5 = 1
                if (r13 != r5) goto L_0x013f
                byte r13 = r12[r0]     // Catch:{ IOException -> 0x0035 }
                if (r13 < 0) goto L_0x013f
                if (r13 > r5) goto L_0x013f
                java.lang.String r12 = new java.lang.String     // Catch:{ IOException -> 0x0035 }
                int r13 = r13 + 48
                char r13 = (char) r13     // Catch:{ IOException -> 0x0035 }
                char[] r5 = new char[r5]     // Catch:{ IOException -> 0x0035 }
                r5[r0] = r13     // Catch:{ IOException -> 0x0035 }
                r12.<init>(r5)     // Catch:{ IOException -> 0x0035 }
                goto L_0x011c
            L_0x013f:
                java.lang.String r13 = new java.lang.String     // Catch:{ IOException -> 0x0035 }
                java.nio.charset.Charset r0 = androidx.exifinterface.media.ExifInterface.ASCII     // Catch:{ IOException -> 0x0035 }
                r13.<init>(r12, r0)     // Catch:{ IOException -> 0x0035 }
                goto L_0x0039
            L_0x014a:
                r0 = move-exception
                r12 = r0
                goto L_0x0161
            L_0x014d:
                r0 = move-exception
                r12 = r0
                r4 = r3
            L_0x0150:
                java.lang.String r13 = "IOException occurred during reading a value"
                android.util.Log.w(r2, r13, r12)     // Catch:{ all -> 0x0030 }
                if (r4 == 0) goto L_0x0160
                r4.close()     // Catch:{ IOException -> 0x015b }
                goto L_0x0160
            L_0x015b:
                r0 = move-exception
                r12 = r0
                android.util.Log.e(r2, r1, r12)
            L_0x0160:
                return r3
            L_0x0161:
                if (r3 == 0) goto L_0x016c
                r3.close()     // Catch:{ IOException -> 0x0167 }
                goto L_0x016c
            L_0x0167:
                r0 = move-exception
                r13 = r0
                android.util.Log.e(r2, r1, r13)
            L_0x016c:
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.ExifAttribute.getValue(java.nio.ByteOrder):java.lang.Object");
        }

        public int size() {
            return ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[this.format] * this.numberOfComponents;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("(");
            sb2.append(ExifInterface.IFD_FORMAT_NAMES[this.format]);
            sb2.append(", data length:");
            return C0086a.l(sb2, this.bytes.length, ")");
        }

        public ExifAttribute(int i2, int i7, long j2, byte[] bArr) {
            this.format = i2;
            this.numberOfComponents = i7;
            this.bytesOffset = j2;
            this.bytes = bArr;
        }

        public static ExifAttribute createULong(long j2, ByteOrder byteOrder) {
            return createULong(new long[]{j2}, byteOrder);
        }

        public static ExifAttribute createUShort(int i2, ByteOrder byteOrder) {
            return createUShort(new int[]{i2}, byteOrder);
        }

        public static ExifAttribute createURational(Rational rational, ByteOrder byteOrder) {
            return createURational(new Rational[]{rational}, byteOrder);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Rational {
        public final long denominator;
        public final long numerator;

        public static Rational createFromDouble(double d) {
            long j2;
            long j3;
            long j8;
            long j10 = 1;
            if (d >= 9.223372036854776E18d || d <= -9.223372036854776E18d) {
                if (d > MapUtil.INVALID_LOCATION) {
                    j2 = Long.MAX_VALUE;
                } else {
                    j2 = Long.MIN_VALUE;
                }
                return new Rational(j2, 1);
            }
            double abs = Math.abs(d);
            long j11 = 0;
            long j12 = 1;
            double d2 = abs;
            long j13 = 0;
            while (true) {
                double d3 = d2 % 1.0d;
                long j14 = (long) (d2 - d3);
                j3 = j13 + (j14 * j10);
                j8 = (j14 * j11) + j12;
                d2 = 1.0d / d3;
                long j15 = j10;
                if (Math.abs(abs - (((double) j3) / ((double) j8))) <= 1.0E-8d * abs) {
                    break;
                }
                j12 = j11;
                j10 = j3;
                j13 = j15;
                j11 = j8;
            }
            if (d < MapUtil.INVALID_LOCATION) {
                j3 = -j3;
            }
            return new Rational(j3, j8);
        }

        public double calculate() {
            return ((double) this.numerator) / ((double) this.denominator);
        }

        public String toString() {
            return this.numerator + "/" + this.denominator;
        }

        private Rational(long j2, long j3) {
            if (j3 == 0) {
                this.numerator = 0;
                this.denominator = 1;
                return;
            }
            this.numerator = j2;
            this.denominator = j3;
        }
    }

    static {
        ExifTag exifTag = new ExifTag("NewSubfileType", 254, 4);
        ExifTag exifTag2 = new ExifTag("SubfileType", ScoverState.TYPE_NFC_SMART_COVER, 4);
        ExifTag exifTag3 = new ExifTag("ImageWidth", 256, 3, 4);
        ExifTag exifTag4 = new ExifTag("ImageLength", 257, 3, 4);
        ExifTag exifTag5 = new ExifTag("BitsPerSample", 258, 3);
        ExifTag exifTag6 = exifTag5;
        ExifTag exifTag7 = exifTag;
        ExifTag exifTag8 = exifTag4;
        ExifTag[] exifTagArr = {exifTag7, exifTag2, exifTag3, exifTag8, exifTag6, new ExifTag("Compression", 259, 3), new ExifTag("PhotometricInterpretation", 262, 3), new ExifTag("ImageDescription", 270, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), new ExifTag("StripOffsets", 273, 3, 4), new ExifTag("Orientation", 274, 3), new ExifTag("SamplesPerPixel", 277, 3), new ExifTag("RowsPerStrip", 278, 3, 4), new ExifTag("StripByteCounts", 279, 3, 4), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", 301, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("Artist", 315, 2), new ExifTag("WhitePoint", 318, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("JPEGInterchangeFormat", ASVLOFFSCREEN.ASVL_PAF_RGB24_B8G8R8, 4), new ExifTag("JPEGInterchangeFormatLength", 514, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("SensorTopBorder", 4, 4), new ExifTag("SensorLeftBorder", 5, 4), new ExifTag("SensorBottomBorder", 6, 4), new ExifTag("SensorRightBorder", 7, 4), new ExifTag("ISO", 23, 3), new ExifTag("JpgFromRaw", 46, 7), new ExifTag("Xmp", 700, 1)};
        IFD_TIFF_TAGS = exifTagArr;
        ExifTag[] exifTagArr2 = {new ExifTag("ExposureTime", 33434, 5), new ExifTag("FNumber", 33437, 5), new ExifTag("ExposureProgram", 34850, 3), new ExifTag("SpectralSensitivity", 34852, 2), new ExifTag("PhotographicSensitivity", 34855, 3), new ExifTag("OECF", 34856, 7), new ExifTag("SensitivityType", 34864, 3), new ExifTag("StandardOutputSensitivity", 34865, 4), new ExifTag("RecommendedExposureIndex", 34866, 4), new ExifTag("ISOSpeed", 34867, 4), new ExifTag("ISOSpeedLatitudeyyy", 34868, 4), new ExifTag("ISOSpeedLatitudezzz", 34869, 4), new ExifTag("ExifVersion", 36864, 2), new ExifTag("DateTimeOriginal", 36867, 2), new ExifTag("DateTimeDigitized", 36868, 2), new ExifTag("OffsetTime", 36880, 2), new ExifTag("OffsetTimeOriginal", 36881, 2), new ExifTag("OffsetTimeDigitized", 36882, 2), new ExifTag("ComponentsConfiguration", 37121, 7), new ExifTag("CompressedBitsPerPixel", 37122, 5), new ExifTag("ShutterSpeedValue", 37377, 10), new ExifTag("ApertureValue", 37378, 5), new ExifTag("BrightnessValue", 37379, 10), new ExifTag("ExposureBiasValue", 37380, 10), new ExifTag("MaxApertureValue", 37381, 5), new ExifTag("SubjectDistance", 37382, 5), new ExifTag("MeteringMode", 37383, 3), new ExifTag("LightSource", 37384, 3), new ExifTag("Flash", 37385, 3), new ExifTag("FocalLength", 37386, 5), new ExifTag("SubjectArea", 37396, 3), new ExifTag("MakerNote", 37500, 7), new ExifTag("UserComment", 37510, 7), new ExifTag("SubSecTime", 37520, 2), new ExifTag("SubSecTimeOriginal", 37521, 2), new ExifTag("SubSecTimeDigitized", 37522, 2), new ExifTag("FlashpixVersion", 40960, 7), new ExifTag("ColorSpace", 40961, 3), new ExifTag("PixelXDimension", 40962, 3, 4), new ExifTag("PixelYDimension", 40963, 3, 4), new ExifTag("RelatedSoundFile", 40964, 2), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("FlashEnergy", 41483, 5), new ExifTag("SpatialFrequencyResponse", 41484, 7), new ExifTag("FocalPlaneXResolution", 41486, 5), new ExifTag("FocalPlaneYResolution", 41487, 5), new ExifTag("FocalPlaneResolutionUnit", 41488, 3), new ExifTag("SubjectLocation", 41492, 3), new ExifTag("ExposureIndex", 41493, 5), new ExifTag("SensingMethod", 41495, 3), new ExifTag("FileSource", 41728, 7), new ExifTag("SceneType", 41729, 7), new ExifTag("CFAPattern", 41730, 7), new ExifTag("CustomRendered", 41985, 3), new ExifTag("ExposureMode", 41986, 3), new ExifTag("WhiteBalance", 41987, 3), new ExifTag("DigitalZoomRatio", 41988, 5), new ExifTag("FocalLengthIn35mmFilm", 41989, 3), new ExifTag("SceneCaptureType", 41990, 3), new ExifTag("GainControl", 41991, 3), new ExifTag("Contrast", 41992, 3), new ExifTag("Saturation", 41993, 3), new ExifTag("Sharpness", 41994, 3), new ExifTag("DeviceSettingDescription", 41995, 7), new ExifTag("SubjectDistanceRange", 41996, 3), new ExifTag("ImageUniqueID", 42016, 2), new ExifTag("CameraOwnerName", 42032, 2), new ExifTag("BodySerialNumber", 42033, 2), new ExifTag("LensSpecification", 42034, 5), new ExifTag("LensMake", 42035, 2), new ExifTag("LensModel", 42036, 2), new ExifTag("Gamma", 42240, 5), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, 3, 4)};
        IFD_EXIF_TAGS = exifTagArr2;
        ExifTag[] exifTagArr3 = {new ExifTag("GPSVersionID", 0, 1), new ExifTag("GPSLatitudeRef", 1, 2), new ExifTag("GPSLatitude", 2, 5, 10), new ExifTag("GPSLongitudeRef", 3, 2), new ExifTag("GPSLongitude", 4, 5, 10), new ExifTag("GPSAltitudeRef", 5, 1), new ExifTag("GPSAltitude", 6, 5), new ExifTag("GPSTimeStamp", 7, 5), new ExifTag("GPSSatellites", 8, 2), new ExifTag("GPSStatus", 9, 2), new ExifTag("GPSMeasureMode", 10, 2), new ExifTag("GPSDOP", 11, 5), new ExifTag("GPSSpeedRef", 12, 2), new ExifTag("GPSSpeed", 13, 5), new ExifTag("GPSTrackRef", 14, 2), new ExifTag("GPSTrack", 15, 5), new ExifTag("GPSImgDirectionRef", 16, 2), new ExifTag("GPSImgDirection", 17, 5), new ExifTag("GPSMapDatum", 18, 2), new ExifTag("GPSDestLatitudeRef", 19, 2), new ExifTag("GPSDestLatitude", 20, 5), new ExifTag("GPSDestLongitudeRef", 21, 2), new ExifTag("GPSDestLongitude", 22, 5), new ExifTag("GPSDestBearingRef", 23, 2), new ExifTag("GPSDestBearing", 24, 5), new ExifTag("GPSDestDistanceRef", 25, 2), new ExifTag("GPSDestDistance", 26, 5), new ExifTag("GPSProcessingMethod", 27, 7), new ExifTag("GPSAreaInformation", 28, 7), new ExifTag("GPSDateStamp", 29, 2), new ExifTag("GPSDifferential", 30, 3), new ExifTag("GPSHPositioningError", 31, 5)};
        IFD_GPS_TAGS = exifTagArr3;
        ExifTag[] exifTagArr4 = {new ExifTag("InteroperabilityIndex", 1, 2)};
        IFD_INTEROPERABILITY_TAGS = exifTagArr4;
        ExifTag exifTag9 = new ExifTag("NewSubfileType", 254, 4);
        ExifTag exifTag10 = new ExifTag("SubfileType", ScoverState.TYPE_NFC_SMART_COVER, 4);
        ExifTag exifTag11 = exifTag10;
        ExifTag[] exifTagArr5 = {exifTag9, exifTag11, new ExifTag("ThumbnailImageWidth", 256, 3, 4), new ExifTag("ThumbnailImageLength", 257, 3, 4), new ExifTag("BitsPerSample", 258, 3), new ExifTag("Compression", 259, 3), new ExifTag("PhotometricInterpretation", 262, 3), new ExifTag("ImageDescription", 270, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), new ExifTag("StripOffsets", 273, 3, 4), new ExifTag("ThumbnailOrientation", 274, 3), new ExifTag("SamplesPerPixel", 277, 3), new ExifTag("RowsPerStrip", 278, 3, 4), new ExifTag("StripByteCounts", 279, 3, 4), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", 301, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("Artist", 315, 2), new ExifTag("WhitePoint", 318, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("JPEGInterchangeFormat", ASVLOFFSCREEN.ASVL_PAF_RGB24_B8G8R8, 4), new ExifTag("JPEGInterchangeFormatLength", 514, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, 3, 4)};
        IFD_THUMBNAIL_TAGS = exifTagArr5;
        ExifTag[] exifTagArr6 = {new ExifTag("ThumbnailImage", 256, 7), new ExifTag("CameraSettingsIFDPointer", 8224, 4), new ExifTag("ImageProcessingIFDPointer", 8256, 4)};
        ORF_MAKER_NOTE_TAGS = exifTagArr6;
        ExifTag[] exifTagArr7 = {new ExifTag("PreviewImageStart", 257, 4), new ExifTag("PreviewImageLength", 258, 4)};
        ORF_CAMERA_SETTINGS_TAGS = exifTagArr7;
        ExifTag[] exifTagArr8 = {new ExifTag("AspectFrame", 4371, 3)};
        ORF_IMAGE_PROCESSING_TAGS = exifTagArr8;
        ExifTag[] exifTagArr9 = {new ExifTag("ColorSpace", 55, 3)};
        PEF_TAGS = exifTagArr9;
        ExifTag[][] exifTagArr10 = {exifTagArr, exifTagArr2, exifTagArr3, exifTagArr4, exifTagArr5, exifTagArr, exifTagArr6, exifTagArr7, exifTagArr8, exifTagArr9};
        EXIF_TAGS = exifTagArr10;
        EXIF_POINTER_TAGS = new ExifTag[]{new ExifTag("SubIFDPointer", 330, 4), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("CameraSettingsIFDPointer", 8224, 1), new ExifTag("ImageProcessingIFDPointer", 8256, 1)};
        sExifTagMapsForReading = new HashMap[exifTagArr10.length];
        sExifTagMapsForWriting = new HashMap[exifTagArr10.length];
        Charset forName = Charset.forName("US-ASCII");
        ASCII = forName;
        IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(forName);
        IDENTIFIER_XMP_APP1 = MediaDefs.Meta.XMP.XMP_SIGNATURE.getBytes(forName);
        Locale locale = Locale.US;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", locale);
        sFormatterPrimary = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
        sFormatterSecondary = simpleDateFormat2;
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
        int i2 = 0;
        while (true) {
            ExifTag[][] exifTagArr11 = EXIF_TAGS;
            if (i2 < exifTagArr11.length) {
                sExifTagMapsForReading[i2] = new HashMap<>();
                sExifTagMapsForWriting[i2] = new HashMap<>();
                for (ExifTag exifTag12 : exifTagArr11[i2]) {
                    sExifTagMapsForReading[i2].put(Integer.valueOf(exifTag12.number), exifTag12);
                    sExifTagMapsForWriting[i2].put(exifTag12.name, exifTag12);
                }
                i2++;
            } else {
                HashMap<Integer, Integer> hashMap = sExifPointerTagMap;
                ExifTag[] exifTagArr12 = EXIF_POINTER_TAGS;
                hashMap.put(Integer.valueOf(exifTagArr12[0].number), 5);
                hashMap.put(Integer.valueOf(exifTagArr12[1].number), 1);
                hashMap.put(Integer.valueOf(exifTagArr12[2].number), 2);
                hashMap.put(Integer.valueOf(exifTagArr12[3].number), 3);
                hashMap.put(Integer.valueOf(exifTagArr12[4].number), 7);
                hashMap.put(Integer.valueOf(exifTagArr12[5].number), 8);
                return;
            }
        }
    }

    public ExifInterface(File file) {
        ExifTag[][] exifTagArr = EXIF_TAGS;
        this.mAttributes = new HashMap[exifTagArr.length];
        this.mAttributesOffsets = new HashSet(exifTagArr.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (file != null) {
            initForFilename(file.getAbsolutePath());
            return;
        }
        throw new NullPointerException("file cannot be null");
    }

    private void addDefaultValuesForCompatibility() {
        String attribute = getAttribute("DateTimeOriginal");
        if (attribute != null && getAttribute("DateTime") == null) {
            this.mAttributes[0].put("DateTime", ExifAttribute.createString(attribute));
        }
        if (getAttribute("ImageWidth") == null) {
            this.mAttributes[0].put("ImageWidth", ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute("ImageLength") == null) {
            this.mAttributes[0].put("ImageLength", ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute("Orientation") == null) {
            this.mAttributes[0].put("Orientation", ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute("LightSource") == null) {
            this.mAttributes[1].put("LightSource", ExifAttribute.createULong(0, this.mExifByteOrder));
        }
    }

    private static double convertRationalLatLonToDouble(String str, String str2) {
        try {
            String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX, -1);
            String[] split2 = split[0].split("/", -1);
            double parseDouble = Double.parseDouble(split2[0].trim()) / Double.parseDouble(split2[1].trim());
            String[] split3 = split[1].split("/", -1);
            String[] split4 = split[2].split("/", -1);
            double parseDouble2 = ((Double.parseDouble(split4[0].trim()) / Double.parseDouble(split4[1].trim())) / 3600.0d) + ((Double.parseDouble(split3[0].trim()) / Double.parseDouble(split3[1].trim())) / 60.0d) + parseDouble;
            if (!str2.equals("S")) {
                if (!str2.equals("W")) {
                    if (!str2.equals("N")) {
                        if (!str2.equals("E")) {
                            throw new IllegalArgumentException();
                        }
                    }
                    return parseDouble2;
                }
            }
            return -parseDouble2;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void copyChunksUpToGivenChunkType(ByteOrderedDataInputStream byteOrderedDataInputStream, ByteOrderedDataOutputStream byteOrderedDataOutputStream, byte[] bArr, byte[] bArr2) {
        while (true) {
            byte[] bArr3 = new byte[4];
            byteOrderedDataInputStream.readFully(bArr3);
            copyWebPChunk(byteOrderedDataInputStream, byteOrderedDataOutputStream, bArr3);
            if (Arrays.equals(bArr3, bArr)) {
                return;
            }
            if (bArr2 != null && Arrays.equals(bArr3, bArr2)) {
                return;
            }
        }
    }

    private void copyWebPChunk(ByteOrderedDataInputStream byteOrderedDataInputStream, ByteOrderedDataOutputStream byteOrderedDataOutputStream, byte[] bArr) {
        int readInt = byteOrderedDataInputStream.readInt();
        byteOrderedDataOutputStream.write(bArr);
        byteOrderedDataOutputStream.writeInt(readInt);
        if (readInt % 2 == 1) {
            readInt++;
        }
        ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream, readInt);
    }

    private ExifAttribute getExifAttribute(String str) {
        ExifAttribute exifAttribute;
        ExifAttribute exifAttribute2;
        if (str != null) {
            if ("ISOSpeedRatings".equals(str)) {
                if (DEBUG) {
                    Log.d("ExifInterface", "getExifAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
                }
                str = "PhotographicSensitivity";
            }
            if ("Xmp".equals(str) && getXmpHandlingForImageType(this.mMimeType) == 2 && (exifAttribute2 = this.mXmpFromSeparateMarker) != null) {
                return exifAttribute2;
            }
            for (int i2 = 0; i2 < EXIF_TAGS.length; i2++) {
                ExifAttribute exifAttribute3 = this.mAttributes[i2].get(str);
                if (exifAttribute3 != null) {
                    return exifAttribute3;
                }
            }
            if (!"Xmp".equals(str) || (exifAttribute = this.mXmpFromSeparateMarker) == null) {
                return null;
            }
            return exifAttribute;
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    private void getHeifAttributes(SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream, int i2) {
        String str;
        String str2;
        String str3;
        int i7;
        final SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream2 = seekableByteOrderedDataInputStream;
        int i8 = Build.VERSION.SDK_INT;
        if (i2 != 15 || i8 >= 31) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                ExifInterfaceUtils.Api23Impl.setDataSource(mediaMetadataRetriever, new MediaDataSource() {
                    long mPosition;

                    public long getSize() {
                        return -1;
                    }

                    public int readAt(long j2, byte[] bArr, int i2, int i7) {
                        if (i7 == 0) {
                            return 0;
                        }
                        if (j2 < 0) {
                            return -1;
                        }
                        try {
                            long j3 = this.mPosition;
                            if (j3 != j2) {
                                if (j3 >= 0 && j2 >= j3 + ((long) seekableByteOrderedDataInputStream2.available())) {
                                    return -1;
                                }
                                seekableByteOrderedDataInputStream2.seek(j2);
                                this.mPosition = j2;
                            }
                            if (i7 > seekableByteOrderedDataInputStream2.available()) {
                                i7 = seekableByteOrderedDataInputStream2.available();
                            }
                            int read = seekableByteOrderedDataInputStream2.read(bArr, i2, i7);
                            if (read >= 0) {
                                this.mPosition += (long) read;
                                return read;
                            }
                        } catch (IOException unused) {
                        }
                        this.mPosition = -1;
                        return -1;
                    }

                    public void close() {
                    }
                });
                String extractMetadata = mediaMetadataRetriever.extractMetadata(33);
                String extractMetadata2 = mediaMetadataRetriever.extractMetadata(34);
                String extractMetadata3 = mediaMetadataRetriever.extractMetadata(26);
                String extractMetadata4 = mediaMetadataRetriever.extractMetadata(17);
                if ("yes".equals(extractMetadata3)) {
                    str3 = mediaMetadataRetriever.extractMetadata(29);
                    str = mediaMetadataRetriever.extractMetadata(30);
                    str2 = mediaMetadataRetriever.extractMetadata(31);
                } else if ("yes".equals(extractMetadata4)) {
                    str3 = mediaMetadataRetriever.extractMetadata(18);
                    str = mediaMetadataRetriever.extractMetadata(19);
                    str2 = mediaMetadataRetriever.extractMetadata(24);
                } else {
                    str3 = null;
                    str2 = null;
                    str = null;
                }
                if (str3 != null) {
                    this.mAttributes[0].put("ImageWidth", ExifAttribute.createUShort(Integer.parseInt(str3), this.mExifByteOrder));
                }
                if (str != null) {
                    this.mAttributes[0].put("ImageLength", ExifAttribute.createUShort(Integer.parseInt(str), this.mExifByteOrder));
                }
                if (str2 != null) {
                    int parseInt = Integer.parseInt(str2);
                    if (parseInt == 90) {
                        i7 = 6;
                    } else if (parseInt == 180) {
                        i7 = 3;
                    } else if (parseInt != 270) {
                        i7 = 1;
                    } else {
                        i7 = 8;
                    }
                    this.mAttributes[0].put("Orientation", ExifAttribute.createUShort(i7, this.mExifByteOrder));
                }
                if (!(extractMetadata == null || extractMetadata2 == null)) {
                    int parseInt2 = Integer.parseInt(extractMetadata);
                    int parseInt3 = Integer.parseInt(extractMetadata2);
                    if (parseInt3 > 6) {
                        seekableByteOrderedDataInputStream2.seek((long) parseInt2);
                        byte[] bArr = new byte[6];
                        seekableByteOrderedDataInputStream2.readFully(bArr);
                        int i10 = parseInt2 + 6;
                        int i11 = parseInt3 - 6;
                        if (Arrays.equals(bArr, IDENTIFIER_EXIF_APP1)) {
                            byte[] bArr2 = new byte[i11];
                            seekableByteOrderedDataInputStream2.readFully(bArr2);
                            this.mOffsetToExifData = i10;
                            readExifSegment(bArr2, 0);
                        } else {
                            throw new IOException("Invalid identifier");
                        }
                    } else {
                        throw new IOException("Invalid exif length");
                    }
                }
                String extractMetadata5 = mediaMetadataRetriever.extractMetadata(41);
                String extractMetadata6 = mediaMetadataRetriever.extractMetadata(42);
                if (!(extractMetadata5 == null || extractMetadata6 == null)) {
                    int parseInt4 = Integer.parseInt(extractMetadata5);
                    int parseInt5 = Integer.parseInt(extractMetadata6);
                    long j2 = (long) parseInt4;
                    seekableByteOrderedDataInputStream2.seek(j2);
                    byte[] bArr3 = new byte[parseInt5];
                    seekableByteOrderedDataInputStream2.readFully(bArr3);
                    this.mXmpFromSeparateMarker = new ExifAttribute(1, parseInt5, j2, bArr3);
                    this.mFileOnDiskContainsSeparateXmpMarker = true;
                }
                if (DEBUG) {
                    Log.d("ExifInterface", "Heif meta: " + str3 + "x" + str + ", rotation " + str2);
                }
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException unused) {
                }
            } catch (RuntimeException e) {
                throw new UnsupportedOperationException("Failed to read EXIF from HEIF file. Given stream is either malformed or unsupported.", e);
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException unused2) {
                }
                throw th;
            }
        } else {
            throw new UnsupportedOperationException("Reading EXIF from AVIF files is supported from SDK 31 and above");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0150 A[LOOP:0: B:8:0x0034->B:51:0x0150, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0156 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void getJpegAttributes(androidx.exifinterface.media.ExifInterface.ByteOrderedDataInputStream r21, int r22, int r23) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r23
            boolean r3 = DEBUG
            java.lang.String r4 = "ExifInterface"
            if (r3 == 0) goto L_0x001d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "getJpegAttributes starting with: "
            r3.<init>(r5)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r4, r3)
        L_0x001d:
            java.nio.ByteOrder r3 = java.nio.ByteOrder.BIG_ENDIAN
            r1.setByteOrder(r3)
            byte r3 = r1.readByte()
            java.lang.String r5 = "Invalid marker: "
            r6 = -1
            if (r3 != r6) goto L_0x019a
            byte r7 = r1.readByte()
            r8 = -40
            if (r7 != r8) goto L_0x0182
            r3 = 2
        L_0x0034:
            byte r5 = r1.readByte()
            if (r5 != r6) goto L_0x0168
            byte r5 = r1.readByte()
            boolean r7 = DEBUG
            if (r7 == 0) goto L_0x0059
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Found JPEG segment indicator: "
            r8.<init>(r9)
            r9 = r5 & 255(0xff, float:3.57E-43)
            java.lang.String r9 = java.lang.Integer.toHexString(r9)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.util.Log.d(r4, r8)
        L_0x0059:
            r8 = -39
            if (r5 == r8) goto L_0x0162
            r8 = -38
            if (r5 != r8) goto L_0x0063
            goto L_0x0162
        L_0x0063:
            int r8 = r1.readUnsignedShort()
            int r9 = r8 + -2
            r10 = 4
            int r3 = r3 + r10
            if (r7 == 0) goto L_0x0091
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r11 = "JPEG segment: "
            r7.<init>(r11)
            r11 = r5 & 255(0xff, float:3.57E-43)
            java.lang.String r11 = java.lang.Integer.toHexString(r11)
            r7.append(r11)
            java.lang.String r11 = " (length: "
            r7.append(r11)
            r7.append(r8)
            java.lang.String r11 = ")"
            r7.append(r11)
            java.lang.String r7 = r7.toString()
            android.util.Log.d(r4, r7)
        L_0x0091:
            java.lang.String r7 = "Invalid length"
            if (r9 < 0) goto L_0x015c
            r11 = -31
            r12 = 0
            r13 = 1
            if (r5 == r11) goto L_0x0105
            r11 = -2
            if (r5 == r11) goto L_0x00e4
            switch(r5) {
                case -64: goto L_0x00ac;
                case -63: goto L_0x00ac;
                case -62: goto L_0x00ac;
                case -61: goto L_0x00ac;
                default: goto L_0x00a1;
            }
        L_0x00a1:
            switch(r5) {
                case -59: goto L_0x00ac;
                case -58: goto L_0x00ac;
                case -57: goto L_0x00ac;
                default: goto L_0x00a4;
            }
        L_0x00a4:
            switch(r5) {
                case -55: goto L_0x00ac;
                case -54: goto L_0x00ac;
                case -53: goto L_0x00ac;
                default: goto L_0x00a7;
            }
        L_0x00a7:
            switch(r5) {
                case -51: goto L_0x00ac;
                case -50: goto L_0x00ac;
                case -49: goto L_0x00ac;
                default: goto L_0x00aa;
            }
        L_0x00aa:
            goto L_0x014e
        L_0x00ac:
            r1.skipFully(r13)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r5 = r0.mAttributes
            r5 = r5[r2]
            if (r2 == r10) goto L_0x00b8
            java.lang.String r9 = "ImageLength"
            goto L_0x00ba
        L_0x00b8:
            java.lang.String r9 = "ThumbnailImageLength"
        L_0x00ba:
            int r11 = r1.readUnsignedShort()
            long r11 = (long) r11
            java.nio.ByteOrder r13 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r11 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r11, (java.nio.ByteOrder) r13)
            r5.put(r9, r11)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r5 = r0.mAttributes
            r5 = r5[r2]
            if (r2 == r10) goto L_0x00d1
            java.lang.String r9 = "ImageWidth"
            goto L_0x00d3
        L_0x00d1:
            java.lang.String r9 = "ThumbnailImageWidth"
        L_0x00d3:
            int r10 = r1.readUnsignedShort()
            long r10 = (long) r10
            java.nio.ByteOrder r12 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r10 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r10, (java.nio.ByteOrder) r12)
            r5.put(r9, r10)
            int r9 = r8 + -7
            goto L_0x014e
        L_0x00e4:
            byte[] r5 = new byte[r9]
            r1.readFully(r5)
            java.lang.String r8 = "UserComment"
            java.lang.String r9 = r0.getAttribute(r8)
            if (r9 != 0) goto L_0x0103
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r9 = r0.mAttributes
            r9 = r9[r13]
            java.lang.String r10 = new java.lang.String
            java.nio.charset.Charset r11 = ASCII
            r10.<init>(r5, r11)
            androidx.exifinterface.media.ExifInterface$ExifAttribute r5 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createString(r10)
            r9.put(r8, r5)
        L_0x0103:
            r9 = r12
            goto L_0x014e
        L_0x0105:
            byte[] r5 = new byte[r9]
            r1.readFully(r5)
            int r8 = r3 + r9
            byte[] r10 = IDENTIFIER_EXIF_APP1
            boolean r11 = androidx.exifinterface.media.ExifInterfaceUtils.startsWith(r5, r10)
            if (r11 == 0) goto L_0x012b
            int r11 = r10.length
            byte[] r5 = java.util.Arrays.copyOfRange(r5, r11, r9)
            int r3 = r22 + r3
            int r9 = r10.length
            int r3 = r3 + r9
            r0.mOffsetToExifData = r3
            r0.readExifSegment(r5, r2)
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r3 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream
            r3.<init>((byte[]) r5)
            r0.setThumbnailData(r3)
            goto L_0x014c
        L_0x012b:
            byte[] r10 = IDENTIFIER_XMP_APP1
            boolean r11 = androidx.exifinterface.media.ExifInterfaceUtils.startsWith(r5, r10)
            if (r11 == 0) goto L_0x014c
            int r11 = r10.length
            int r3 = r3 + r11
            int r10 = r10.length
            byte[] r5 = java.util.Arrays.copyOfRange(r5, r10, r9)
            androidx.exifinterface.media.ExifInterface$ExifAttribute r14 = new androidx.exifinterface.media.ExifInterface$ExifAttribute
            int r9 = r5.length
            long r10 = (long) r3
            r15 = 1
            r19 = r5
            r16 = r9
            r17 = r10
            r14.<init>(r15, r16, r17, r19)
            r0.mXmpFromSeparateMarker = r14
            r0.mFileOnDiskContainsSeparateXmpMarker = r13
        L_0x014c:
            r3 = r8
            goto L_0x0103
        L_0x014e:
            if (r9 < 0) goto L_0x0156
            r1.skipFully(r9)
            int r3 = r3 + r9
            goto L_0x0034
        L_0x0156:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r7)
            throw r0
        L_0x015c:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r7)
            throw r0
        L_0x0162:
            java.nio.ByteOrder r0 = r0.mExifByteOrder
            r1.setByteOrder(r0)
            return
        L_0x0168:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Invalid marker:"
            r1.<init>(r2)
            r2 = r5 & 255(0xff, float:3.57E-43)
            java.lang.String r2 = java.lang.Integer.toHexString(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0182:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r5)
            r2 = r3 & 255(0xff, float:3.57E-43)
            java.lang.String r2 = java.lang.Integer.toHexString(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x019a:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r5)
            r2 = r3 & 255(0xff, float:3.57E-43)
            java.lang.String r2 = java.lang.Integer.toHexString(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.getJpegAttributes(androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream, int, int):void");
    }

    private int getMimeType(BufferedInputStream bufferedInputStream) {
        bufferedInputStream.mark(5000);
        byte[] bArr = new byte[5000];
        bufferedInputStream.read(bArr);
        bufferedInputStream.reset();
        if (isJpegFormat(bArr)) {
            return 4;
        }
        if (isRafFormat(bArr)) {
            return 9;
        }
        int isHeicOrAvifFormat = isHeicOrAvifFormat(bArr);
        if (isHeicOrAvifFormat != 0) {
            return isHeicOrAvifFormat;
        }
        if (isOrfFormat(bArr)) {
            return 7;
        }
        if (isRw2Format(bArr)) {
            return 10;
        }
        if (isPngFormat(bArr)) {
            return 13;
        }
        if (isWebpFormat(bArr)) {
            return 14;
        }
        return 0;
    }

    private void getOrfAttributes(SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream) {
        int i2;
        int i7;
        getRawAttributes(seekableByteOrderedDataInputStream);
        ExifAttribute exifAttribute = this.mAttributes[1].get("MakerNote");
        if (exifAttribute != null) {
            SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream2 = new SeekableByteOrderedDataInputStream(exifAttribute.bytes);
            seekableByteOrderedDataInputStream2.setByteOrder(this.mExifByteOrder);
            byte[] bArr = ORF_MAKER_NOTE_HEADER_1;
            byte[] bArr2 = new byte[bArr.length];
            seekableByteOrderedDataInputStream2.readFully(bArr2);
            seekableByteOrderedDataInputStream2.seek(0);
            byte[] bArr3 = ORF_MAKER_NOTE_HEADER_2;
            byte[] bArr4 = new byte[bArr3.length];
            seekableByteOrderedDataInputStream2.readFully(bArr4);
            if (Arrays.equals(bArr2, bArr)) {
                seekableByteOrderedDataInputStream2.seek(8);
            } else if (Arrays.equals(bArr4, bArr3)) {
                seekableByteOrderedDataInputStream2.seek(12);
            }
            readImageFileDirectory(seekableByteOrderedDataInputStream2, 6);
            ExifAttribute exifAttribute2 = this.mAttributes[7].get("PreviewImageStart");
            ExifAttribute exifAttribute3 = this.mAttributes[7].get("PreviewImageLength");
            if (!(exifAttribute2 == null || exifAttribute3 == null)) {
                this.mAttributes[5].put("JPEGInterchangeFormat", exifAttribute2);
                this.mAttributes[5].put("JPEGInterchangeFormatLength", exifAttribute3);
            }
            ExifAttribute exifAttribute4 = this.mAttributes[8].get("AspectFrame");
            if (exifAttribute4 != null) {
                int[] iArr = (int[]) exifAttribute4.getValue(this.mExifByteOrder);
                if (iArr == null || iArr.length != 4) {
                    Log.w("ExifInterface", "Invalid aspect frame values. frame=" + Arrays.toString(iArr));
                    return;
                }
                int i8 = iArr[2];
                int i10 = iArr[0];
                if (i8 > i10 && (i2 = iArr[3]) > (i7 = iArr[1])) {
                    int i11 = (i8 - i10) + 1;
                    int i12 = (i2 - i7) + 1;
                    if (i11 < i12) {
                        int i13 = i11 + i12;
                        i12 = i13 - i12;
                        i11 = i13 - i12;
                    }
                    ExifAttribute createUShort = ExifAttribute.createUShort(i11, this.mExifByteOrder);
                    ExifAttribute createUShort2 = ExifAttribute.createUShort(i12, this.mExifByteOrder);
                    this.mAttributes[0].put("ImageWidth", createUShort);
                    this.mAttributes[0].put("ImageLength", createUShort2);
                }
            }
        }
    }

    private void getPngAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        ByteOrderedDataInputStream byteOrderedDataInputStream2 = byteOrderedDataInputStream;
        if (DEBUG) {
            Log.d("ExifInterface", "getPngAttributes starting with: " + byteOrderedDataInputStream2);
        }
        byteOrderedDataInputStream2.setByteOrder(ByteOrder.BIG_ENDIAN);
        int position = byteOrderedDataInputStream2.position();
        byteOrderedDataInputStream2.skipFully(PNG_SIGNATURE.length);
        boolean z = false;
        boolean z3 = false;
        while (true) {
            if (z && z3) {
                break;
            }
            try {
                int readInt = byteOrderedDataInputStream2.readInt();
                int readInt2 = byteOrderedDataInputStream2.readInt();
                int position2 = byteOrderedDataInputStream2.position() + readInt + 4;
                if (byteOrderedDataInputStream2.position() - position == 16) {
                    if (readInt2 != 1229472850) {
                        throw new IOException("Encountered invalid PNG file--IHDR chunk should appear as the first chunk");
                    }
                }
                if (readInt2 == 1229278788) {
                    break;
                }
                if (readInt2 == 1700284774 && !z) {
                    this.mOffsetToExifData = byteOrderedDataInputStream2.position() - position;
                    byte[] bArr = new byte[readInt];
                    byteOrderedDataInputStream2.readFully(bArr);
                    int readInt3 = byteOrderedDataInputStream2.readInt();
                    CRC32 crc32 = new CRC32();
                    updateCrcWithInt(crc32, readInt2);
                    crc32.update(bArr);
                    if (((int) crc32.getValue()) == readInt3) {
                        readExifSegment(bArr, 0);
                        validateImages();
                        setThumbnailData(new ByteOrderedDataInputStream(bArr));
                        z = true;
                    } else {
                        throw new IOException("Encountered invalid CRC value for PNG-EXIF chunk.\n recorded CRC value: " + readInt3 + ", calculated CRC value: " + crc32.getValue());
                    }
                } else if (readInt2 == 1767135348 && !z3) {
                    byte[] bArr2 = PNG_ITXT_XMP_KEYWORD;
                    if (readInt >= bArr2.length) {
                        int length = bArr2.length;
                        byte[] bArr3 = new byte[length];
                        byteOrderedDataInputStream2.readFully(bArr3);
                        if (Arrays.equals(bArr3, bArr2)) {
                            int i2 = readInt - length;
                            byte[] bArr4 = new byte[i2];
                            byteOrderedDataInputStream2.readFully(bArr4);
                            this.mXmpFromSeparateMarker = new ExifAttribute(1, i2, (long) (byteOrderedDataInputStream2.position() - position), bArr4);
                            z3 = true;
                        }
                    }
                }
                byteOrderedDataInputStream2.skipFully(position2 - byteOrderedDataInputStream2.position());
            } catch (EOFException e) {
                throw new IOException("Encountered corrupt PNG file.", e);
            }
        }
        this.mFileOnDiskContainsSeparateXmpMarker = z3;
    }

    private void getRafAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        boolean z = DEBUG;
        if (z) {
            Log.d("ExifInterface", "getRafAttributes starting with: " + byteOrderedDataInputStream);
        }
        byteOrderedDataInputStream.skipFully(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[4];
        byteOrderedDataInputStream.readFully(bArr);
        byteOrderedDataInputStream.readFully(bArr2);
        byteOrderedDataInputStream.readFully(bArr3);
        int i2 = ByteBuffer.wrap(bArr).getInt();
        int i7 = ByteBuffer.wrap(bArr2).getInt();
        int i8 = ByteBuffer.wrap(bArr3).getInt();
        byte[] bArr4 = new byte[i7];
        byteOrderedDataInputStream.skipFully(i2 - byteOrderedDataInputStream.position());
        byteOrderedDataInputStream.readFully(bArr4);
        getJpegAttributes(new ByteOrderedDataInputStream(bArr4), i2, 5);
        byteOrderedDataInputStream.skipFully(i8 - byteOrderedDataInputStream.position());
        byteOrderedDataInputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
        int readInt = byteOrderedDataInputStream.readInt();
        if (z) {
            C0086a.C(readInt, "numberOfDirectoryEntry: ", "ExifInterface");
        }
        for (int i10 = 0; i10 < readInt; i10++) {
            int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
            int readUnsignedShort2 = byteOrderedDataInputStream.readUnsignedShort();
            if (readUnsignedShort == TAG_RAF_IMAGE_SIZE.number) {
                short readShort = byteOrderedDataInputStream.readShort();
                short readShort2 = byteOrderedDataInputStream.readShort();
                ExifAttribute createUShort = ExifAttribute.createUShort((int) readShort, this.mExifByteOrder);
                ExifAttribute createUShort2 = ExifAttribute.createUShort((int) readShort2, this.mExifByteOrder);
                this.mAttributes[0].put("ImageLength", createUShort);
                this.mAttributes[0].put("ImageWidth", createUShort2);
                if (DEBUG) {
                    Log.d("ExifInterface", "Updated to length: " + readShort + ", width: " + readShort2);
                    return;
                }
                return;
            }
            byteOrderedDataInputStream.skipFully(readUnsignedShort2);
        }
    }

    private void getRawAttributes(SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream) {
        ExifAttribute exifAttribute;
        parseTiffHeaders(seekableByteOrderedDataInputStream);
        readImageFileDirectory(seekableByteOrderedDataInputStream, 0);
        updateImageSizeValues(seekableByteOrderedDataInputStream, 0);
        updateImageSizeValues(seekableByteOrderedDataInputStream, 5);
        updateImageSizeValues(seekableByteOrderedDataInputStream, 4);
        validateImages();
        if (this.mMimeType == 8 && (exifAttribute = this.mAttributes[1].get("MakerNote")) != null) {
            SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream2 = new SeekableByteOrderedDataInputStream(exifAttribute.bytes);
            seekableByteOrderedDataInputStream2.setByteOrder(this.mExifByteOrder);
            seekableByteOrderedDataInputStream2.skipFully(6);
            readImageFileDirectory(seekableByteOrderedDataInputStream2, 9);
            ExifAttribute exifAttribute2 = this.mAttributes[9].get("ColorSpace");
            if (exifAttribute2 != null) {
                this.mAttributes[1].put("ColorSpace", exifAttribute2);
            }
        }
    }

    private void getRw2Attributes(SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream) {
        if (DEBUG) {
            Log.d("ExifInterface", "getRw2Attributes starting with: " + seekableByteOrderedDataInputStream);
        }
        getRawAttributes(seekableByteOrderedDataInputStream);
        ExifAttribute exifAttribute = this.mAttributes[0].get("JpgFromRaw");
        if (exifAttribute != null) {
            getJpegAttributes(new ByteOrderedDataInputStream(exifAttribute.bytes), (int) exifAttribute.bytesOffset, 5);
        }
        ExifAttribute exifAttribute2 = this.mAttributes[0].get("ISO");
        ExifAttribute exifAttribute3 = this.mAttributes[1].get("PhotographicSensitivity");
        if (exifAttribute2 != null && exifAttribute3 == null) {
            this.mAttributes[1].put("PhotographicSensitivity", exifAttribute2);
        }
    }

    private boolean getStandaloneAttributes(SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream) {
        byte[] bArr = IDENTIFIER_EXIF_APP1;
        byte[] bArr2 = new byte[bArr.length];
        seekableByteOrderedDataInputStream.readFully(bArr2);
        if (!Arrays.equals(bArr2, bArr)) {
            Log.w("ExifInterface", "Given data is not EXIF-only.");
            return false;
        }
        byte[] readToEnd = seekableByteOrderedDataInputStream.readToEnd();
        this.mOffsetToExifData = bArr.length;
        readExifSegment(readToEnd, 0);
        return true;
    }

    private void getWebpAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        if (DEBUG) {
            Log.d("ExifInterface", "getWebpAttributes starting with: " + byteOrderedDataInputStream);
        }
        byteOrderedDataInputStream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
        byteOrderedDataInputStream.skipFully(WEBP_SIGNATURE_1.length);
        int readInt = byteOrderedDataInputStream.readInt() + 8;
        byte[] bArr = WEBP_SIGNATURE_2;
        byteOrderedDataInputStream.skipFully(bArr.length);
        int length = bArr.length + 8;
        while (true) {
            try {
                byte[] bArr2 = new byte[4];
                byteOrderedDataInputStream.readFully(bArr2);
                int readInt2 = byteOrderedDataInputStream.readInt();
                int i2 = length + 8;
                if (Arrays.equals(WEBP_CHUNK_TYPE_EXIF, bArr2)) {
                    byte[] bArr3 = new byte[readInt2];
                    byteOrderedDataInputStream.readFully(bArr3);
                    byte[] bArr4 = IDENTIFIER_EXIF_APP1;
                    if (ExifInterfaceUtils.startsWith(bArr3, bArr4)) {
                        bArr3 = Arrays.copyOfRange(bArr3, bArr4.length, readInt2);
                    }
                    this.mOffsetToExifData = i2;
                    readExifSegment(bArr3, 0);
                    setThumbnailData(new ByteOrderedDataInputStream(bArr3));
                    return;
                }
                if (readInt2 % 2 == 1) {
                    readInt2++;
                }
                length = i2 + readInt2;
                if (length != readInt) {
                    if (length <= readInt) {
                        byteOrderedDataInputStream.skipFully(readInt2);
                    } else {
                        throw new IOException("Encountered WebP file with invalid chunk size");
                    }
                } else {
                    return;
                }
            } catch (EOFException e) {
                throw new IOException("Encountered corrupt WebP file.", e);
            }
        }
    }

    private static int getXmpHandlingForImageType(int i2) {
        if (i2 == 4) {
            return 3;
        }
        if (i2 == 9 || i2 == 15 || i2 == 12 || i2 == 13) {
            return 2;
        }
        return 1;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:67|68|69) */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        java.lang.Double.parseDouble(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x013e, code lost:
        return new android.util.Pair<>(12, -1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0144, code lost:
        return new android.util.Pair<>(2, -1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x0130 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.util.Pair<java.lang.Integer, java.lang.Integer> guessDataFormat(java.lang.String r13) {
        /*
            r0 = 4
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1 = 5
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2 = 10
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3 = 2
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            r5 = -1
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            java.lang.String r7 = ","
            boolean r8 = r13.contains(r7)
            r9 = 0
            r10 = 1
            if (r8 == 0) goto L_0x00b6
            java.lang.String[] r13 = r13.split(r7, r5)
            r0 = r13[r9]
            android.util.Pair r0 = guessDataFormat(r0)
            java.lang.Object r1 = r0.first
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r1 != r3) goto L_0x0039
            return r0
        L_0x0039:
            int r1 = r13.length
            if (r10 >= r1) goto L_0x00b5
            r1 = r13[r10]
            android.util.Pair r1 = guessDataFormat(r1)
            java.lang.Object r2 = r1.first
            java.lang.Integer r2 = (java.lang.Integer) r2
            java.lang.Object r3 = r0.first
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x005d
            java.lang.Object r2 = r1.second
            java.lang.Integer r2 = (java.lang.Integer) r2
            java.lang.Object r3 = r0.first
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x005b
            goto L_0x005d
        L_0x005b:
            r2 = r5
            goto L_0x0065
        L_0x005d:
            java.lang.Object r2 = r0.first
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
        L_0x0065:
            java.lang.Object r3 = r0.second
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            if (r3 == r5) goto L_0x0090
            java.lang.Object r3 = r1.first
            java.lang.Integer r3 = (java.lang.Integer) r3
            java.lang.Object r7 = r0.second
            boolean r3 = r3.equals(r7)
            if (r3 != 0) goto L_0x0087
            java.lang.Object r1 = r1.second
            java.lang.Integer r1 = (java.lang.Integer) r1
            java.lang.Object r3 = r0.second
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0090
        L_0x0087:
            java.lang.Object r1 = r0.second
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            goto L_0x0091
        L_0x0090:
            r1 = r5
        L_0x0091:
            if (r2 != r5) goto L_0x009b
            if (r1 != r5) goto L_0x009b
            android.util.Pair r13 = new android.util.Pair
            r13.<init>(r4, r6)
            return r13
        L_0x009b:
            if (r2 != r5) goto L_0x00a7
            android.util.Pair r0 = new android.util.Pair
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.<init>(r1, r6)
            goto L_0x00b2
        L_0x00a7:
            if (r1 != r5) goto L_0x00b2
            android.util.Pair r0 = new android.util.Pair
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r0.<init>(r1, r6)
        L_0x00b2:
            int r10 = r10 + 1
            goto L_0x0039
        L_0x00b5:
            return r0
        L_0x00b6:
            java.lang.String r7 = "/"
            boolean r8 = r13.contains(r7)
            r11 = 0
            if (r8 == 0) goto L_0x0102
            java.lang.String[] r13 = r13.split(r7, r5)
            int r0 = r13.length
            if (r0 != r3) goto L_0x00fc
            r0 = r13[r9]     // Catch:{ NumberFormatException -> 0x00fc }
            double r7 = java.lang.Double.parseDouble(r0)     // Catch:{ NumberFormatException -> 0x00fc }
            long r7 = (long) r7     // Catch:{ NumberFormatException -> 0x00fc }
            r13 = r13[r10]     // Catch:{ NumberFormatException -> 0x00fc }
            double r9 = java.lang.Double.parseDouble(r13)     // Catch:{ NumberFormatException -> 0x00fc }
            long r9 = (long) r9     // Catch:{ NumberFormatException -> 0x00fc }
            int r13 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r13 < 0) goto L_0x00f6
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 >= 0) goto L_0x00de
            goto L_0x00f6
        L_0x00de:
            r11 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r13 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r13 > 0) goto L_0x00f0
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 <= 0) goto L_0x00ea
            goto L_0x00f0
        L_0x00ea:
            android.util.Pair r13 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x00fc }
            r13.<init>(r2, r1)     // Catch:{ NumberFormatException -> 0x00fc }
            return r13
        L_0x00f0:
            android.util.Pair r13 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x00fc }
            r13.<init>(r1, r6)     // Catch:{ NumberFormatException -> 0x00fc }
            return r13
        L_0x00f6:
            android.util.Pair r13 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x00fc }
            r13.<init>(r2, r6)     // Catch:{ NumberFormatException -> 0x00fc }
            return r13
        L_0x00fc:
            android.util.Pair r13 = new android.util.Pair
            r13.<init>(r4, r6)
            return r13
        L_0x0102:
            long r1 = java.lang.Long.parseLong(r13)     // Catch:{ NumberFormatException -> 0x0130 }
            int r3 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r3 < 0) goto L_0x011c
            r7 = 65535(0xffff, double:3.23786E-319)
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 > 0) goto L_0x011c
            android.util.Pair r1 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x0130 }
            r2 = 3
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ NumberFormatException -> 0x0130 }
            r1.<init>(r2, r0)     // Catch:{ NumberFormatException -> 0x0130 }
            return r1
        L_0x011c:
            if (r3 >= 0) goto L_0x012a
            android.util.Pair r0 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x0130 }
            r1 = 9
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ NumberFormatException -> 0x0130 }
            r0.<init>(r1, r6)     // Catch:{ NumberFormatException -> 0x0130 }
            return r0
        L_0x012a:
            android.util.Pair r1 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x0130 }
            r1.<init>(r0, r6)     // Catch:{ NumberFormatException -> 0x0130 }
            return r1
        L_0x0130:
            java.lang.Double.parseDouble(r13)     // Catch:{ NumberFormatException -> 0x013f }
            android.util.Pair r13 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x013f }
            r0 = 12
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ NumberFormatException -> 0x013f }
            r13.<init>(r0, r6)     // Catch:{ NumberFormatException -> 0x013f }
            return r13
        L_0x013f:
            android.util.Pair r13 = new android.util.Pair
            r13.<init>(r4, r6)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.guessDataFormat(java.lang.String):android.util.Pair");
    }

    private void handleThumbnailFromJfif(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap<String, ExifAttribute> hashMap) {
        ExifAttribute exifAttribute = hashMap.get("JPEGInterchangeFormat");
        ExifAttribute exifAttribute2 = hashMap.get("JPEGInterchangeFormatLength");
        if (exifAttribute != null && exifAttribute2 != null) {
            int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
            int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
            if (this.mMimeType == 7) {
                intValue += this.mOrfMakerNoteOffset;
            }
            if (intValue > 0 && intValue2 > 0) {
                this.mHasThumbnail = true;
                if (this.mFilename == null && this.mAssetInputStream == null && this.mSeekableFileDescriptor == null) {
                    byte[] bArr = new byte[intValue2];
                    byteOrderedDataInputStream.skipFully(intValue);
                    byteOrderedDataInputStream.readFully(bArr);
                    this.mThumbnailBytes = bArr;
                }
                this.mThumbnailOffset = intValue;
                this.mThumbnailLength = intValue2;
            }
            if (DEBUG) {
                Log.d("ExifInterface", "Setting thumbnail attributes with offset: " + intValue + ", length: " + intValue2);
            }
        }
    }

    private void handleThumbnailFromStrips(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap<String, ExifAttribute> hashMap) {
        int i2;
        ByteOrderedDataInputStream byteOrderedDataInputStream2 = byteOrderedDataInputStream;
        HashMap<String, ExifAttribute> hashMap2 = hashMap;
        ExifAttribute exifAttribute = hashMap2.get("StripOffsets");
        ExifAttribute exifAttribute2 = hashMap2.get("StripByteCounts");
        if (exifAttribute != null && exifAttribute2 != null) {
            long[] convertToLongArray = ExifInterfaceUtils.convertToLongArray(exifAttribute.getValue(this.mExifByteOrder));
            long[] convertToLongArray2 = ExifInterfaceUtils.convertToLongArray(exifAttribute2.getValue(this.mExifByteOrder));
            if (convertToLongArray == null || convertToLongArray.length == 0) {
                Log.w("ExifInterface", "stripOffsets should not be null or have zero length.");
            } else if (convertToLongArray2 == null || convertToLongArray2.length == 0) {
                Log.w("ExifInterface", "stripByteCounts should not be null or have zero length.");
            } else if (convertToLongArray.length != convertToLongArray2.length) {
                Log.w("ExifInterface", "stripOffsets and stripByteCounts should have same length.");
            } else {
                long j2 = 0;
                for (long j3 : convertToLongArray2) {
                    j2 += j3;
                }
                int i7 = (int) j2;
                byte[] bArr = new byte[i7];
                int i8 = 1;
                this.mAreThumbnailStripsConsecutive = true;
                this.mHasThumbnailStrips = true;
                this.mHasThumbnail = true;
                int i10 = 0;
                int i11 = 0;
                int i12 = 0;
                while (i10 < convertToLongArray.length) {
                    int i13 = (int) convertToLongArray[i10];
                    int i14 = (int) convertToLongArray2[i10];
                    if (i10 < convertToLongArray.length - i8) {
                        i2 = i10;
                        if (((long) (i13 + i14)) != convertToLongArray[i2 + 1]) {
                            this.mAreThumbnailStripsConsecutive = false;
                        }
                    } else {
                        i2 = i10;
                    }
                    int i15 = i13 - i11;
                    if (i15 < 0) {
                        Log.d("ExifInterface", "Invalid strip offset value");
                        return;
                    }
                    try {
                        byteOrderedDataInputStream2.skipFully(i15);
                        int i16 = i11 + i15;
                        byte[] bArr2 = new byte[i14];
                        try {
                            byteOrderedDataInputStream2.readFully(bArr2);
                            i11 = i16 + i14;
                            System.arraycopy(bArr2, 0, bArr, i12, i14);
                            i12 += i14;
                            i10 = i2 + 1;
                            i8 = 1;
                        } catch (EOFException unused) {
                            Log.d("ExifInterface", "Failed to read " + i14 + " bytes.");
                            return;
                        }
                    } catch (EOFException unused2) {
                        Log.d("ExifInterface", "Failed to skip " + i15 + " bytes.");
                        return;
                    }
                }
                this.mThumbnailBytes = bArr;
                if (this.mAreThumbnailStripsConsecutive) {
                    this.mThumbnailOffset = (int) convertToLongArray[0];
                    this.mThumbnailLength = i7;
                }
            }
        }
    }

    private void initForFilename(String str) {
        if (str != null) {
            FileInputStream fileInputStream = null;
            this.mAssetInputStream = null;
            this.mFilename = str;
            try {
                FileInputStream fileInputStream2 = new FileInputStream(str);
                try {
                    if (isSeekableFD(fileInputStream2.getFD())) {
                        this.mSeekableFileDescriptor = fileInputStream2.getFD();
                    } else {
                        this.mSeekableFileDescriptor = null;
                    }
                    loadAttributes(fileInputStream2);
                    ExifInterfaceUtils.closeQuietly(fileInputStream2);
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    ExifInterfaceUtils.closeQuietly(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                ExifInterfaceUtils.closeQuietly(fileInputStream);
                throw th;
            }
        } else {
            throw new NullPointerException("filename cannot be null");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x00ae A[Catch:{ all -> 0x00a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int isHeicOrAvifFormat(byte[] r14) {
        /*
            r13 = this;
            r13 = 0
            r0 = 0
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r1 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream     // Catch:{ Exception -> 0x00a9 }
            r1.<init>((byte[]) r14)     // Catch:{ Exception -> 0x00a9 }
            int r0 = r1.readInt()     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            long r2 = (long) r0     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            r0 = 4
            byte[] r4 = new byte[r0]     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            r1.readFully(r4)     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            byte[] r5 = HEIF_TYPE_FTYP     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            boolean r4 = java.util.Arrays.equals(r4, r5)     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            if (r4 != 0) goto L_0x001e
            r1.close()
            return r13
        L_0x001e:
            r4 = 1
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r7 = 8
            if (r6 != 0) goto L_0x003c
            long r2 = r1.readLong()     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            r9 = 16
            int r6 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r6 >= 0) goto L_0x003d
            r1.close()
            return r13
        L_0x0034:
            r13 = move-exception
            r0 = r1
            goto L_0x00bb
        L_0x0038:
            r14 = move-exception
            r0 = r1
            goto L_0x00aa
        L_0x003c:
            r9 = r7
        L_0x003d:
            int r6 = r14.length     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            long r11 = (long) r6     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            int r6 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r6 <= 0) goto L_0x0045
            int r14 = r14.length     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            long r2 = (long) r14
        L_0x0045:
            long r2 = r2 - r9
            int r14 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r14 >= 0) goto L_0x004e
            r1.close()
            return r13
        L_0x004e:
            byte[] r14 = new byte[r0]     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            r6 = 0
            r0 = r13
            r8 = r0
            r9 = r8
        L_0x0055:
            r10 = 4
            long r10 = r2 / r10
            int r10 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x00a3
            r1.readFully(r14)     // Catch:{ EOFException -> 0x009f }
            int r10 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r10 != 0) goto L_0x0065
            goto L_0x009d
        L_0x0065:
            byte[] r10 = HEIF_BRAND_MIF1     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            boolean r10 = java.util.Arrays.equals(r14, r10)     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            r11 = 1
            if (r10 == 0) goto L_0x0070
            r0 = r11
            goto L_0x008b
        L_0x0070:
            byte[] r10 = HEIF_BRAND_HEIC     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            boolean r10 = java.util.Arrays.equals(r14, r10)     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            if (r10 == 0) goto L_0x007a
            r8 = r11
            goto L_0x008b
        L_0x007a:
            byte[] r10 = HEIF_BRAND_AVIF     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            boolean r10 = java.util.Arrays.equals(r14, r10)     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            if (r10 != 0) goto L_0x008a
            byte[] r10 = HEIF_BRAND_AVIS     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            boolean r10 = java.util.Arrays.equals(r14, r10)     // Catch:{ Exception -> 0x0038, all -> 0x0034 }
            if (r10 == 0) goto L_0x008b
        L_0x008a:
            r9 = r11
        L_0x008b:
            if (r0 == 0) goto L_0x009d
            if (r8 == 0) goto L_0x0095
            r1.close()
            r13 = 12
            return r13
        L_0x0095:
            if (r9 == 0) goto L_0x009d
            r1.close()
            r13 = 15
            return r13
        L_0x009d:
            long r6 = r6 + r4
            goto L_0x0055
        L_0x009f:
            r1.close()
            return r13
        L_0x00a3:
            r1.close()
            goto L_0x00ba
        L_0x00a7:
            r13 = move-exception
            goto L_0x00bb
        L_0x00a9:
            r14 = move-exception
        L_0x00aa:
            boolean r1 = DEBUG     // Catch:{ all -> 0x00a7 }
            if (r1 == 0) goto L_0x00b5
            java.lang.String r1 = "ExifInterface"
            java.lang.String r2 = "Exception parsing HEIF file type box."
            android.util.Log.d(r1, r2, r14)     // Catch:{ all -> 0x00a7 }
        L_0x00b5:
            if (r0 == 0) goto L_0x00ba
            r0.close()
        L_0x00ba:
            return r13
        L_0x00bb:
            if (r0 == 0) goto L_0x00c0
            r0.close()
        L_0x00c0:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.isHeicOrAvifFormat(byte[]):int");
    }

    private static boolean isJpegFormat(byte[] bArr) {
        int i2 = 0;
        while (true) {
            byte[] bArr2 = JPEG_SIGNATURE;
            if (i2 >= bArr2.length) {
                return true;
            }
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
            i2++;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isOrfFormat(byte[] r4) {
        /*
            r3 = this;
            r0 = 0
            r1 = 0
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r2 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream     // Catch:{ Exception -> 0x002d, all -> 0x0026 }
            r2.<init>((byte[]) r4)     // Catch:{ Exception -> 0x002d, all -> 0x0026 }
            java.nio.ByteOrder r4 = r3.readByteOrder(r2)     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            r3.mExifByteOrder = r4     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            r2.setByteOrder(r4)     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            short r3 = r2.readShort()     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            r4 = 20306(0x4f52, float:2.8455E-41)
            if (r3 == r4) goto L_0x001c
            r4 = 21330(0x5352, float:2.989E-41)
            if (r3 != r4) goto L_0x001d
        L_0x001c:
            r0 = 1
        L_0x001d:
            r2.close()
            return r0
        L_0x0021:
            r3 = move-exception
            r1 = r2
            goto L_0x0027
        L_0x0024:
            r1 = r2
            goto L_0x002d
        L_0x0026:
            r3 = move-exception
        L_0x0027:
            if (r1 == 0) goto L_0x002c
            r1.close()
        L_0x002c:
            throw r3
        L_0x002d:
            if (r1 == 0) goto L_0x0032
            r1.close()
        L_0x0032:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.isOrfFormat(byte[]):boolean");
    }

    private boolean isPngFormat(byte[] bArr) {
        int i2 = 0;
        while (true) {
            byte[] bArr2 = PNG_SIGNATURE;
            if (i2 >= bArr2.length) {
                return true;
            }
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
            i2++;
        }
    }

    private boolean isRafFormat(byte[] bArr) {
        byte[] bytes = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
        for (int i2 = 0; i2 < bytes.length; i2++) {
            if (bArr[i2] != bytes[i2]) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isRw2Format(byte[] r4) {
        /*
            r3 = this;
            r0 = 0
            r1 = 0
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r2 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream     // Catch:{ Exception -> 0x0029, all -> 0x0022 }
            r2.<init>((byte[]) r4)     // Catch:{ Exception -> 0x0029, all -> 0x0022 }
            java.nio.ByteOrder r4 = r3.readByteOrder(r2)     // Catch:{ Exception -> 0x0020, all -> 0x001d }
            r3.mExifByteOrder = r4     // Catch:{ Exception -> 0x0020, all -> 0x001d }
            r2.setByteOrder(r4)     // Catch:{ Exception -> 0x0020, all -> 0x001d }
            short r3 = r2.readShort()     // Catch:{ Exception -> 0x0020, all -> 0x001d }
            r4 = 85
            if (r3 != r4) goto L_0x0019
            r0 = 1
        L_0x0019:
            r2.close()
            return r0
        L_0x001d:
            r3 = move-exception
            r1 = r2
            goto L_0x0023
        L_0x0020:
            r1 = r2
            goto L_0x0029
        L_0x0022:
            r3 = move-exception
        L_0x0023:
            if (r1 == 0) goto L_0x0028
            r1.close()
        L_0x0028:
            throw r3
        L_0x0029:
            if (r1 == 0) goto L_0x002e
            r1.close()
        L_0x002e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.isRw2Format(byte[]):boolean");
    }

    private static boolean isSeekableFD(FileDescriptor fileDescriptor) {
        try {
            Os.lseek(fileDescriptor, 0, OsConstants.SEEK_CUR);
            return true;
        } catch (Exception unused) {
            if (!DEBUG) {
                return false;
            }
            Log.d("ExifInterface", "The file descriptor for the given input is not seekable");
            return false;
        }
    }

    private boolean isSupportedDataType(HashMap<String, ExifAttribute> hashMap) {
        ExifAttribute exifAttribute;
        int intValue;
        ExifAttribute exifAttribute2 = hashMap.get("BitsPerSample");
        if (exifAttribute2 != null) {
            int[] iArr = (int[]) exifAttribute2.getValue(this.mExifByteOrder);
            int[] iArr2 = BITS_PER_SAMPLE_RGB;
            if (Arrays.equals(iArr2, iArr)) {
                return true;
            }
            if (this.mMimeType == 3 && (exifAttribute = hashMap.get("PhotometricInterpretation")) != null && (((intValue = exifAttribute.getIntValue(this.mExifByteOrder)) == 1 && Arrays.equals(iArr, BITS_PER_SAMPLE_GREYSCALE_2)) || (intValue == 6 && Arrays.equals(iArr, iArr2)))) {
                return true;
            }
        }
        if (!DEBUG) {
            return false;
        }
        Log.d("ExifInterface", "Unsupported data type value");
        return false;
    }

    private static boolean isSupportedFormatForSavingAttributes(int i2) {
        if (i2 == 4 || i2 == 13 || i2 == 14) {
            return true;
        }
        return false;
    }

    private boolean isThumbnail(HashMap<String, ExifAttribute> hashMap) {
        ExifAttribute exifAttribute = hashMap.get("ImageLength");
        ExifAttribute exifAttribute2 = hashMap.get("ImageWidth");
        if (exifAttribute == null || exifAttribute2 == null) {
            return false;
        }
        int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
        int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
        if (intValue > 512 || intValue2 > 512) {
            return false;
        }
        return true;
    }

    private boolean isWebpFormat(byte[] bArr) {
        int i2 = 0;
        while (true) {
            byte[] bArr2 = WEBP_SIGNATURE_1;
            if (i2 >= bArr2.length) {
                int i7 = 0;
                while (true) {
                    byte[] bArr3 = WEBP_SIGNATURE_2;
                    if (i7 >= bArr3.length) {
                        return true;
                    }
                    if (bArr[WEBP_SIGNATURE_1.length + i7 + 4] != bArr3[i7]) {
                        return false;
                    }
                    i7++;
                }
            } else if (bArr[i2] != bArr2[i2]) {
                return false;
            } else {
                i2++;
            }
        }
    }

    private void loadAttributes(InputStream inputStream) {
        int i2 = 0;
        while (i2 < EXIF_TAGS.length) {
            try {
                this.mAttributes[i2] = new HashMap<>();
                i2++;
            } catch (IOException | UnsupportedOperationException e) {
                boolean z = DEBUG;
                if (z) {
                    Log.w("ExifInterface", "Invalid image: ExifInterface got an unsupported image format file (ExifInterface supports JPEG and some RAW image formats only) or a corrupted JPEG file to ExifInterface.", e);
                }
                addDefaultValuesForCompatibility();
                if (z) {
                    printAttributes();
                    return;
                }
                return;
            } catch (Throwable th) {
                addDefaultValuesForCompatibility();
                if (DEBUG) {
                    printAttributes();
                }
                throw th;
            }
        }
        if (!this.mIsExifDataOnly) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 5000);
            this.mMimeType = getMimeType(bufferedInputStream);
            inputStream = bufferedInputStream;
        }
        if (shouldSupportSeek(this.mMimeType)) {
            SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream = new SeekableByteOrderedDataInputStream(inputStream);
            if (!this.mIsExifDataOnly) {
                int i7 = this.mMimeType;
                if (i7 != 12) {
                    if (i7 != 15) {
                        if (i7 == 7) {
                            getOrfAttributes(seekableByteOrderedDataInputStream);
                        } else if (i7 == 10) {
                            getRw2Attributes(seekableByteOrderedDataInputStream);
                        } else {
                            getRawAttributes(seekableByteOrderedDataInputStream);
                        }
                    }
                }
                getHeifAttributes(seekableByteOrderedDataInputStream, i7);
            } else if (!getStandaloneAttributes(seekableByteOrderedDataInputStream)) {
                addDefaultValuesForCompatibility();
                if (DEBUG) {
                    printAttributes();
                    return;
                }
                return;
            }
            seekableByteOrderedDataInputStream.seek((long) this.mOffsetToExifData);
            setThumbnailData(seekableByteOrderedDataInputStream);
        } else {
            ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(inputStream);
            int i8 = this.mMimeType;
            if (i8 == 4) {
                getJpegAttributes(byteOrderedDataInputStream, 0, 0);
            } else if (i8 == 13) {
                getPngAttributes(byteOrderedDataInputStream);
            } else if (i8 == 9) {
                getRafAttributes(byteOrderedDataInputStream);
            } else if (i8 == 14) {
                getWebpAttributes(byteOrderedDataInputStream);
            }
        }
        addDefaultValuesForCompatibility();
        if (DEBUG) {
            printAttributes();
        }
    }

    private void parseTiffHeaders(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        ByteOrder readByteOrder = readByteOrder(byteOrderedDataInputStream);
        this.mExifByteOrder = readByteOrder;
        byteOrderedDataInputStream.setByteOrder(readByteOrder);
        int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
        int i2 = this.mMimeType;
        if (i2 == 7 || i2 == 10 || readUnsignedShort == 42) {
            int readInt = byteOrderedDataInputStream.readInt();
            if (readInt >= 8) {
                int i7 = readInt - 8;
                if (i7 > 0) {
                    byteOrderedDataInputStream.skipFully(i7);
                    return;
                }
                return;
            }
            throw new IOException(C0086a.i(readInt, "Invalid first Ifd offset: "));
        }
        throw new IOException("Invalid start code: " + Integer.toHexString(readUnsignedShort));
    }

    private void printAttributes() {
        for (int i2 = 0; i2 < this.mAttributes.length; i2++) {
            StringBuilder o2 = C0086a.o(i2, "The size of tag group[", "]: ");
            o2.append(this.mAttributes[i2].size());
            Log.d("ExifInterface", o2.toString());
            for (Map.Entry next : this.mAttributes[i2].entrySet()) {
                ExifAttribute exifAttribute = (ExifAttribute) next.getValue();
                Log.d("ExifInterface", "tagName: " + ((String) next.getKey()) + ", tagType: " + exifAttribute.toString() + ", tagValue: '" + exifAttribute.getStringValue(this.mExifByteOrder) + "'");
            }
        }
    }

    private ByteOrder readByteOrder(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        short readShort = byteOrderedDataInputStream.readShort();
        if (readShort == 18761) {
            if (DEBUG) {
                Log.d("ExifInterface", "readExifSegment: Byte Align II");
            }
            return ByteOrder.LITTLE_ENDIAN;
        } else if (readShort == 19789) {
            if (DEBUG) {
                Log.d("ExifInterface", "readExifSegment: Byte Align MM");
            }
            return ByteOrder.BIG_ENDIAN;
        } else {
            throw new IOException("Invalid byte order: " + Integer.toHexString(readShort));
        }
    }

    private void readExifSegment(byte[] bArr, int i2) {
        SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream = new SeekableByteOrderedDataInputStream(bArr);
        parseTiffHeaders(seekableByteOrderedDataInputStream);
        readImageFileDirectory(seekableByteOrderedDataInputStream, i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01f7  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0230  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readImageFileDirectory(androidx.exifinterface.media.ExifInterface.SeekableByteOrderedDataInputStream r26, int r27) {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            r2 = r27
            java.util.Set<java.lang.Integer> r3 = r0.mAttributesOffsets
            int r4 = r1.position()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3.add(r4)
            short r3 = r1.readShort()
            boolean r4 = DEBUG
            java.lang.String r5 = "ExifInterface"
            if (r4 == 0) goto L_0x0022
            java.lang.String r4 = "numberOfDirectoryEntry: "
            c0.C0086a.C(r3, r4, r5)
        L_0x0022:
            if (r3 > 0) goto L_0x0026
            goto L_0x032e
        L_0x0026:
            r6 = 0
        L_0x0027:
            if (r6 >= r3) goto L_0x02d2
            int r10 = r1.readUnsignedShort()
            int r11 = r1.readUnsignedShort()
            int r14 = r1.readInt()
            int r12 = r1.position()
            long r12 = (long) r12
            r15 = 4
            long r12 = r12 + r15
            java.util.HashMap<java.lang.Integer, androidx.exifinterface.media.ExifInterface$ExifTag>[] r17 = sExifTagMapsForReading
            r4 = r17[r2]
            r18 = 0
            java.lang.Integer r7 = java.lang.Integer.valueOf(r10)
            java.lang.Object r4 = r4.get(r7)
            androidx.exifinterface.media.ExifInterface$ExifTag r4 = (androidx.exifinterface.media.ExifInterface.ExifTag) r4
            boolean r7 = DEBUG
            if (r7 == 0) goto L_0x007e
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)
            r20 = r15
            java.lang.Integer r15 = java.lang.Integer.valueOf(r10)
            r16 = 4
            if (r4 == 0) goto L_0x0064
            java.lang.String r9 = r4.name
        L_0x0061:
            r22 = r3
            goto L_0x0066
        L_0x0064:
            r9 = 0
            goto L_0x0061
        L_0x0066:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r11)
            r23 = r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r14)
            java.lang.Object[] r3 = new java.lang.Object[]{r8, r15, r9, r3, r6}
            java.lang.String r6 = "ifdType: %d, tagNumber: %d, tagName: %s, dataFormat: %d, numberOfComponents: %d"
            java.lang.String r3 = java.lang.String.format(r6, r3)
            android.util.Log.d(r5, r3)
            goto L_0x0086
        L_0x007e:
            r22 = r3
            r23 = r6
            r20 = r15
            r16 = 4
        L_0x0086:
            r3 = 7
            if (r4 != 0) goto L_0x0093
            if (r7 == 0) goto L_0x0090
            java.lang.String r6 = "Skip the tag entry since tag number is not defined: "
            c0.C0086a.C(r10, r6, r5)
        L_0x0090:
            r24 = r4
            goto L_0x00ee
        L_0x0093:
            if (r11 <= 0) goto L_0x009a
            int[] r6 = IFD_FORMAT_BYTES_PER_FORMAT
            int r8 = r6.length
            if (r11 < r8) goto L_0x009d
        L_0x009a:
            r24 = r4
            goto L_0x00e7
        L_0x009d:
            boolean r8 = r4.isFormatCompatible(r11)
            if (r8 != 0) goto L_0x00c5
            if (r7 == 0) goto L_0x0090
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r8 = "Skip the tag entry since data format ("
            r6.<init>(r8)
            java.lang.String[] r8 = IFD_FORMAT_NAMES
            r8 = r8[r11]
            r6.append(r8)
            java.lang.String r8 = ") is unexpected for tag: "
            r6.append(r8)
            java.lang.String r8 = r4.name
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            android.util.Log.d(r5, r6)
            goto L_0x0090
        L_0x00c5:
            if (r11 != r3) goto L_0x00c9
            int r11 = r4.primaryFormat
        L_0x00c9:
            long r8 = (long) r14
            r6 = r6[r11]
            r24 = r4
            long r3 = (long) r6
            long r8 = r8 * r3
            int r3 = (r8 > r18 ? 1 : (r8 == r18 ? 0 : -1))
            if (r3 < 0) goto L_0x00de
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x00dc
            goto L_0x00de
        L_0x00dc:
            r3 = 1
            goto L_0x00f1
        L_0x00de:
            if (r7 == 0) goto L_0x00e5
            java.lang.String r3 = "Skip the tag entry since the number of components is invalid: "
            c0.C0086a.C(r14, r3, r5)
        L_0x00e5:
            r3 = 0
            goto L_0x00f1
        L_0x00e7:
            if (r7 == 0) goto L_0x00ee
            java.lang.String r3 = "Skip the tag entry since data format is invalid: "
            c0.C0086a.C(r11, r3, r5)
        L_0x00ee:
            r8 = r18
            goto L_0x00e5
        L_0x00f1:
            if (r3 != 0) goto L_0x00f8
            r1.seek(r12)
            goto L_0x02c9
        L_0x00f8:
            int r3 = (r8 > r20 ? 1 : (r8 == r20 ? 0 : -1))
            java.lang.String r4 = "Compression"
            if (r3 <= 0) goto L_0x0172
            int r3 = r1.readInt()
            if (r7 == 0) goto L_0x0109
            java.lang.String r6 = "seek to data offset: "
            c0.C0086a.C(r3, r6, r5)
        L_0x0109:
            int r6 = r0.mMimeType
            r15 = 7
            if (r6 != r15) goto L_0x0167
            java.lang.String r6 = "MakerNote"
            r17 = r7
            r15 = r24
            java.lang.String r7 = r15.name
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0121
            r0.mOrfMakerNoteOffset = r3
        L_0x011e:
            r20 = r12
            goto L_0x016d
        L_0x0121:
            r6 = 6
            if (r2 != r6) goto L_0x011e
            java.lang.String r7 = "ThumbnailImage"
            java.lang.String r6 = r15.name
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x011e
            r0.mOrfThumbnailOffset = r3
            r0.mOrfThumbnailLength = r14
            java.nio.ByteOrder r6 = r0.mExifByteOrder
            r7 = 6
            androidx.exifinterface.media.ExifInterface$ExifAttribute r6 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createUShort((int) r7, (java.nio.ByteOrder) r6)
            int r7 = r0.mOrfThumbnailOffset
            r20 = r12
            long r12 = (long) r7
            java.nio.ByteOrder r7 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r7 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r12, (java.nio.ByteOrder) r7)
            int r12 = r0.mOrfThumbnailLength
            long r12 = (long) r12
            java.nio.ByteOrder r2 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r2 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r12, (java.nio.ByteOrder) r2)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r12 = r0.mAttributes
            r12 = r12[r16]
            r12.put(r4, r6)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r6 = r0.mAttributes
            r6 = r6[r16]
            java.lang.String r12 = "JPEGInterchangeFormat"
            r6.put(r12, r7)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r6 = r0.mAttributes
            r6 = r6[r16]
            java.lang.String r7 = "JPEGInterchangeFormatLength"
            r6.put(r7, r2)
            goto L_0x016d
        L_0x0167:
            r17 = r7
            r20 = r12
            r15 = r24
        L_0x016d:
            long r2 = (long) r3
            r1.seek(r2)
            goto L_0x0178
        L_0x0172:
            r17 = r7
            r20 = r12
            r15 = r24
        L_0x0178:
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r2 = sExifPointerTagMap
            java.lang.Integer r3 = java.lang.Integer.valueOf(r10)
            java.lang.Object r2 = r2.get(r3)
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r17 == 0) goto L_0x019f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r6 = "nextIfdType: "
            r3.<init>(r6)
            r3.append(r2)
            java.lang.String r6 = " byteCount: "
            r3.append(r6)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r5, r3)
        L_0x019f:
            r3 = 8
            r6 = 3
            if (r2 == 0) goto L_0x0255
            if (r11 == r6) goto L_0x01c7
            r4 = r16
            if (r11 == r4) goto L_0x01c2
            if (r11 == r3) goto L_0x01bd
            r3 = 9
            if (r11 == r3) goto L_0x01b7
            r3 = 13
            if (r11 == r3) goto L_0x01b7
            r3 = -1
            goto L_0x01cc
        L_0x01b7:
            int r3 = r1.readInt()
        L_0x01bb:
            long r3 = (long) r3
            goto L_0x01cc
        L_0x01bd:
            short r3 = r1.readShort()
            goto L_0x01bb
        L_0x01c2:
            long r3 = r1.readUnsignedInt()
            goto L_0x01cc
        L_0x01c7:
            int r3 = r1.readUnsignedShort()
            goto L_0x01bb
        L_0x01cc:
            if (r17 == 0) goto L_0x01e1
            java.lang.Long r6 = java.lang.Long.valueOf(r3)
            java.lang.String r7 = r15.name
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r7}
            java.lang.String r7 = "Offset: %d, tagName: %s"
            java.lang.String r6 = java.lang.String.format(r7, r6)
            android.util.Log.d(r5, r6)
        L_0x01e1:
            int r6 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            java.lang.String r7 = ")"
            r8 = -1
            if (r6 <= 0) goto L_0x0230
            int r6 = r1.length()
            if (r6 == r8) goto L_0x01f7
            int r6 = r1.length()
            long r9 = (long) r6
            int r6 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r6 >= 0) goto L_0x0230
        L_0x01f7:
            java.util.Set<java.lang.Integer> r6 = r0.mAttributesOffsets
            int r8 = (int) r3
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            boolean r6 = r6.contains(r8)
            if (r6 != 0) goto L_0x0211
            r1.seek(r3)
            int r2 = r2.intValue()
            r0.readImageFileDirectory(r1, r2)
        L_0x020e:
            r12 = r20
            goto L_0x0250
        L_0x0211:
            if (r17 == 0) goto L_0x020e
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r8 = "Skip jump into the IFD since it has already been read: IfdType "
            r6.<init>(r8)
            r6.append(r2)
            java.lang.String r2 = " (at "
            r6.append(r2)
            r6.append(r3)
            r6.append(r7)
            java.lang.String r2 = r6.toString()
            android.util.Log.d(r5, r2)
            goto L_0x020e
        L_0x0230:
            if (r17 == 0) goto L_0x020e
            java.lang.String r2 = "Skip jump into the IFD since its offset is invalid: "
            java.lang.String r2 = A.a.f(r2, r3)
            int r3 = r1.length()
            if (r3 == r8) goto L_0x024c
            java.lang.String r3 = " (total length: "
            java.lang.StringBuilder r2 = i.C0212a.t(r2, r3)
            int r3 = r1.length()
            java.lang.String r2 = c0.C0086a.l(r2, r3, r7)
        L_0x024c:
            android.util.Log.d(r5, r2)
            goto L_0x020e
        L_0x0250:
            r1.seek(r12)
            goto L_0x02c9
        L_0x0255:
            r12 = r20
            int r2 = r1.position()
            int r7 = r0.mOffsetToExifData
            int r2 = r2 + r7
            int r7 = (int) r8
            byte[] r7 = new byte[r7]
            r1.readFully(r7)
            androidx.exifinterface.media.ExifInterface$ExifAttribute r12 = new androidx.exifinterface.media.ExifInterface$ExifAttribute
            long r8 = (long) r2
            r17 = r7
            r13 = r11
            r2 = r15
            r15 = r8
            r7 = r20
            r12.<init>(r13, r14, r15, r17)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r9 = r0.mAttributes
            r9 = r9[r27]
            java.lang.String r10 = r2.name
            r9.put(r10, r12)
            java.lang.String r9 = "DNGVersion"
            java.lang.String r10 = r2.name
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0286
            r0.mMimeType = r6
        L_0x0286:
            java.lang.String r6 = "Make"
            java.lang.String r9 = r2.name
            boolean r6 = r6.equals(r9)
            if (r6 != 0) goto L_0x029a
            java.lang.String r6 = "Model"
            java.lang.String r9 = r2.name
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x02a8
        L_0x029a:
            java.nio.ByteOrder r6 = r0.mExifByteOrder
            java.lang.String r6 = r12.getStringValue(r6)
            java.lang.String r9 = "PENTAX"
            boolean r6 = r6.contains(r9)
            if (r6 != 0) goto L_0x02bb
        L_0x02a8:
            java.lang.String r2 = r2.name
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x02bd
            java.nio.ByteOrder r2 = r0.mExifByteOrder
            int r2 = r12.getIntValue(r2)
            r4 = 65535(0xffff, float:9.1834E-41)
            if (r2 != r4) goto L_0x02bd
        L_0x02bb:
            r0.mMimeType = r3
        L_0x02bd:
            int r2 = r1.position()
            long r2 = (long) r2
            int r2 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r2 == 0) goto L_0x02c9
            r1.seek(r7)
        L_0x02c9:
            int r6 = r23 + 1
            short r6 = (short) r6
            r2 = r27
            r3 = r22
            goto L_0x0027
        L_0x02d2:
            r18 = 0
            int r2 = r1.readInt()
            boolean r3 = DEBUG
            if (r3 == 0) goto L_0x02ed
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            java.lang.String r6 = "nextIfdOffset: %d"
            java.lang.String r4 = java.lang.String.format(r6, r4)
            android.util.Log.d(r5, r4)
        L_0x02ed:
            long r6 = (long) r2
            int r4 = (r6 > r18 ? 1 : (r6 == r18 ? 0 : -1))
            if (r4 <= 0) goto L_0x0327
            java.util.Set<java.lang.Integer> r4 = r0.mAttributesOffsets
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)
            boolean r4 = r4.contains(r8)
            if (r4 != 0) goto L_0x031f
            r1.seek(r6)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r4 = 4
            r2 = r2[r4]
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0310
            r0.readImageFileDirectory(r1, r4)
            return
        L_0x0310:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r3 = 5
            r2 = r2[r3]
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x032e
            r0.readImageFileDirectory(r1, r3)
            return
        L_0x031f:
            if (r3 == 0) goto L_0x032e
            java.lang.String r0 = "Stop reading file since re-reading an IFD may cause an infinite loop: "
            c0.C0086a.C(r2, r0, r5)
            return
        L_0x0327:
            if (r3 == 0) goto L_0x032e
            java.lang.String r0 = "Stop reading file since a wrong offset may cause an infinite loop: "
            c0.C0086a.C(r2, r0, r5)
        L_0x032e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.readImageFileDirectory(androidx.exifinterface.media.ExifInterface$SeekableByteOrderedDataInputStream, int):void");
    }

    private void removeAttribute(String str) {
        for (int i2 = 0; i2 < EXIF_TAGS.length; i2++) {
            this.mAttributes[i2].remove(str);
        }
    }

    private void replaceInvalidTags(int i2, String str, String str2) {
        if (!this.mAttributes[i2].isEmpty() && this.mAttributes[i2].get(str) != null) {
            HashMap<String, ExifAttribute> hashMap = this.mAttributes[i2];
            hashMap.put(str2, hashMap.get(str));
            this.mAttributes[i2].remove(str);
        }
    }

    private void retrieveJpegImageSize(SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream, int i2) {
        ExifAttribute exifAttribute = this.mAttributes[i2].get("ImageLength");
        ExifAttribute exifAttribute2 = this.mAttributes[i2].get("ImageWidth");
        if (exifAttribute == null || exifAttribute2 == null) {
            ExifAttribute exifAttribute3 = this.mAttributes[i2].get("JPEGInterchangeFormat");
            ExifAttribute exifAttribute4 = this.mAttributes[i2].get("JPEGInterchangeFormatLength");
            if (exifAttribute3 != null && exifAttribute4 != null) {
                int intValue = exifAttribute3.getIntValue(this.mExifByteOrder);
                int intValue2 = exifAttribute3.getIntValue(this.mExifByteOrder);
                seekableByteOrderedDataInputStream.seek((long) intValue);
                byte[] bArr = new byte[intValue2];
                seekableByteOrderedDataInputStream.readFully(bArr);
                getJpegAttributes(new ByteOrderedDataInputStream(bArr), intValue, i2);
            }
        }
    }

    private void saveJpegAttributes(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr;
        if (DEBUG) {
            Log.d("ExifInterface", "saveJpegAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(inputStream);
        ByteOrderedDataOutputStream byteOrderedDataOutputStream = new ByteOrderedDataOutputStream(outputStream, ByteOrder.BIG_ENDIAN);
        if (byteOrderedDataInputStream.readByte() == -1) {
            byteOrderedDataOutputStream.writeByte(-1);
            if (byteOrderedDataInputStream.readByte() == -40) {
                byteOrderedDataOutputStream.writeByte(-40);
                byteOrderedDataOutputStream.writeByte(-1);
                byteOrderedDataOutputStream.writeByte(-31);
                this.mOffsetToExifData = writeExifSegment(byteOrderedDataOutputStream);
                if (this.mXmpFromSeparateMarker != null) {
                    byteOrderedDataOutputStream.write(-1);
                    byteOrderedDataOutputStream.writeByte(-31);
                    byte[] bArr2 = IDENTIFIER_XMP_APP1;
                    byteOrderedDataOutputStream.writeUnsignedShort(bArr2.length + 2 + this.mXmpFromSeparateMarker.bytes.length);
                    byteOrderedDataOutputStream.write(bArr2);
                    byteOrderedDataOutputStream.write(this.mXmpFromSeparateMarker.bytes);
                    this.mFileOnDiskContainsSeparateXmpMarker = true;
                }
                byte[] bArr3 = new byte[4096];
                while (byteOrderedDataInputStream.readByte() == -1) {
                    byte readByte = byteOrderedDataInputStream.readByte();
                    if (readByte == -39 || readByte == -38) {
                        byteOrderedDataOutputStream.writeByte(-1);
                        byteOrderedDataOutputStream.writeByte(readByte);
                        ExifInterfaceUtils.copy(byteOrderedDataInputStream, byteOrderedDataOutputStream);
                        return;
                    } else if (readByte != -31) {
                        byteOrderedDataOutputStream.writeByte(-1);
                        byteOrderedDataOutputStream.writeByte(readByte);
                        int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
                        byteOrderedDataOutputStream.writeUnsignedShort(readUnsignedShort);
                        int i2 = readUnsignedShort - 2;
                        if (i2 >= 0) {
                            while (i2 > 0) {
                                int read = byteOrderedDataInputStream.read(bArr3, 0, Math.min(i2, 4096));
                                if (read < 0) {
                                    break;
                                }
                                byteOrderedDataOutputStream.write(bArr3, 0, read);
                                i2 -= read;
                            }
                        } else {
                            throw new IOException("Invalid length");
                        }
                    } else {
                        int readUnsignedShort2 = byteOrderedDataInputStream.readUnsignedShort();
                        int i7 = readUnsignedShort2 - 2;
                        if (i7 >= 0) {
                            byte[] bArr4 = IDENTIFIER_XMP_APP1;
                            if (i7 >= bArr4.length) {
                                bArr = new byte[bArr4.length];
                            } else {
                                byte[] bArr5 = IDENTIFIER_EXIF_APP1;
                                if (i7 >= bArr5.length) {
                                    bArr = new byte[bArr5.length];
                                } else {
                                    bArr = null;
                                }
                            }
                            if (bArr != null) {
                                byteOrderedDataInputStream.readFully(bArr);
                                if (ExifInterfaceUtils.startsWith(bArr, IDENTIFIER_EXIF_APP1) || ExifInterfaceUtils.startsWith(bArr, bArr4)) {
                                    byteOrderedDataInputStream.skipFully(i7 - bArr.length);
                                }
                            }
                            byteOrderedDataOutputStream.writeByte(-1);
                            byteOrderedDataOutputStream.writeByte(readByte);
                            byteOrderedDataOutputStream.writeUnsignedShort(readUnsignedShort2);
                            if (bArr != null) {
                                i7 -= bArr.length;
                                byteOrderedDataOutputStream.write(bArr);
                            }
                            while (i7 > 0) {
                                int read2 = byteOrderedDataInputStream.read(bArr3, 0, Math.min(i7, 4096));
                                if (read2 < 0) {
                                    break;
                                }
                                byteOrderedDataOutputStream.write(bArr3, 0, read2);
                                i7 -= read2;
                            }
                        } else {
                            throw new IOException("Invalid length");
                        }
                    }
                }
                throw new IOException("Invalid marker");
            }
            throw new IOException("Invalid marker");
        }
        throw new IOException("Invalid marker");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0043 A[ADDED_TO_REGION] */
    private void savePngAttributes(java.io.InputStream r9, java.io.OutputStream r10) {
        /*
            r8 = this;
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x0024
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "savePngAttributes starting with (inputStream: "
            r0.<init>(r1)
            r0.append(r9)
            java.lang.String r1 = ", outputStream: "
            r0.append(r1)
            r0.append(r10)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "ExifInterface"
            android.util.Log.d(r1, r0)
        L_0x0024:
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r0 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream
            r0.<init>((java.io.InputStream) r9)
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataOutputStream r9 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataOutputStream
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            r9.<init>(r10, r1)
            byte[] r10 = PNG_SIGNATURE
            int r10 = r10.length
            androidx.exifinterface.media.ExifInterfaceUtils.copy(r0, r9, r10)
            androidx.exifinterface.media.ExifInterface$ExifAttribute r10 = r8.mXmpFromSeparateMarker
            r1 = 1
            r2 = 0
            if (r10 != 0) goto L_0x0040
            boolean r10 = r8.mFileOnDiskContainsSeparateXmpMarker
            if (r10 == 0) goto L_0x0075
        L_0x0040:
            r10 = r1
        L_0x0041:
            if (r1 != 0) goto L_0x004a
            if (r10 == 0) goto L_0x0046
            goto L_0x004a
        L_0x0046:
            androidx.exifinterface.media.ExifInterfaceUtils.copy(r0, r9)
            return
        L_0x004a:
            int r3 = r0.readInt()
            int r4 = r0.readInt()
            r5 = 1229472850(0x49484452, float:820293.1)
            if (r4 != r5) goto L_0x0077
            r9.writeInt(r3)
            r9.writeInt(r4)
            int r3 = r3 + 4
            androidx.exifinterface.media.ExifInterfaceUtils.copy(r0, r9, r3)
            int r3 = r8.mOffsetToExifData
            if (r3 != 0) goto L_0x006a
            r8.writePngExifChunk(r9)
            r1 = r2
        L_0x006a:
            androidx.exifinterface.media.ExifInterface$ExifAttribute r3 = r8.mXmpFromSeparateMarker
            if (r3 == 0) goto L_0x0041
            boolean r3 = r8.mFileOnDiskContainsSeparateXmpMarker
            if (r3 != 0) goto L_0x0041
            r8.writePngXmpItxtChunk(r9)
        L_0x0075:
            r10 = r2
            goto L_0x0041
        L_0x0077:
            r5 = 1700284774(0x65584966, float:6.383657E22)
            if (r4 != r5) goto L_0x0088
            if (r1 == 0) goto L_0x0088
            r8.writePngExifChunk(r9)
            int r3 = r3 + 4
            r0.skipFully(r3)
            r1 = r2
            goto L_0x0041
        L_0x0088:
            r5 = 1767135348(0x69545874, float:1.6044374E25)
            if (r4 != r5) goto L_0x00ba
            byte[] r5 = PNG_ITXT_XMP_KEYWORD
            int r6 = r5.length
            if (r3 < r6) goto L_0x00ba
            int r6 = r5.length
            byte[] r7 = new byte[r6]
            r0.readFully(r7)
            int r6 = r3 - r6
            int r6 = r6 + 4
            boolean r5 = java.util.Arrays.equals(r7, r5)
            if (r5 == 0) goto L_0x00ad
            androidx.exifinterface.media.ExifInterface$ExifAttribute r10 = r8.mXmpFromSeparateMarker
            if (r10 == 0) goto L_0x00a9
            r8.writePngXmpItxtChunk(r9)
        L_0x00a9:
            r0.skipFully(r6)
            goto L_0x0075
        L_0x00ad:
            r9.writeInt(r3)
            r9.writeInt(r4)
            r9.write(r7)
            androidx.exifinterface.media.ExifInterfaceUtils.copy(r0, r9, r6)
            goto L_0x0041
        L_0x00ba:
            r9.writeInt(r3)
            r9.writeInt(r4)
            int r3 = r3 + 4
            androidx.exifinterface.media.ExifInterfaceUtils.copy(r0, r9, r3)
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.savePngAttributes(java.io.InputStream, java.io.OutputStream):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:85:0x0210 A[Catch:{ Exception -> 0x007f, all -> 0x007b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void saveWebpAttributes(java.io.InputStream r22, java.io.OutputStream r23) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r23
            boolean r3 = DEBUG
            if (r3 == 0) goto L_0x002a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "saveWebpAttributes starting with (inputStream: "
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r4 = ", outputStream: "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r4 = ")"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "ExifInterface"
            android.util.Log.d(r4, r3)
        L_0x002a:
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r3 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream
            java.nio.ByteOrder r4 = java.nio.ByteOrder.LITTLE_ENDIAN
            r3.<init>(r1, r4)
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataOutputStream r1 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataOutputStream
            r1.<init>(r2, r4)
            byte[] r2 = WEBP_SIGNATURE_1
            int r5 = r2.length
            androidx.exifinterface.media.ExifInterfaceUtils.copy(r3, r1, r5)
            int r5 = r3.readInt()
            byte[] r6 = WEBP_SIGNATURE_2
            int r7 = r6.length
            r3.skipFully(r7)
            r7 = 0
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0225 }
            r8.<init>()     // Catch:{ Exception -> 0x0225 }
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataOutputStream r9 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataOutputStream     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r9.<init>(r8, r4)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r4 = r0.mOffsetToExifData     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r11 = 4
            r12 = 8
            if (r4 == 0) goto L_0x0083
            int r2 = r2.length     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r2 = r2 + r11
            int r6 = r6.length     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r2 = r2 + r6
            int r4 = r4 - r2
            int r4 = r4 - r12
            androidx.exifinterface.media.ExifInterfaceUtils.copy(r3, r9, r4)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r3.skipFully(r11)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r2 = r3.readInt()     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r4 = r2 % 2
            if (r4 == 0) goto L_0x006e
            int r2 = r2 + 1
        L_0x006e:
            r3.skipFully(r2)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r2 = r0.writeExifSegment(r9)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
        L_0x0075:
            r23 = r12
        L_0x0077:
            r16 = -1
            goto L_0x01f4
        L_0x007b:
            r0 = move-exception
            r7 = r8
            goto L_0x022e
        L_0x007f:
            r0 = move-exception
            r7 = r8
            goto L_0x0226
        L_0x0083:
            byte[] r2 = new byte[r11]     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r3.readFully(r2)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            byte[] r4 = WEBP_CHUNK_TYPE_VP8X     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            boolean r6 = java.util.Arrays.equals(r2, r4)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r13 = 0
            r14 = 1
            if (r6 == 0) goto L_0x00e3
            int r2 = r3.readInt()     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r6 = r2 % 2
            if (r6 != r14) goto L_0x009d
            int r6 = r2 + 1
            goto L_0x009e
        L_0x009d:
            r6 = r2
        L_0x009e:
            byte[] r6 = new byte[r6]     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r3.readFully(r6)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            byte r15 = r6[r13]     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r15 = r15 | r12
            byte r15 = (byte) r15     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r6[r13] = r15     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r15 = r15 >> r14
            r15 = r15 & r14
            if (r15 != r14) goto L_0x00ae
            r13 = r14
        L_0x00ae:
            r9.write(r4)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r9.writeInt(r2)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r9.write(r6)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            if (r13 == 0) goto L_0x00d7
            byte[] r2 = WEBP_CHUNK_TYPE_ANIM     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r0.copyChunksUpToGivenChunkType(r3, r9, r2, r7)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
        L_0x00be:
            byte[] r2 = new byte[r11]     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r3.readFully(r2)     // Catch:{ EOFException -> 0x00cb }
            byte[] r4 = WEBP_CHUNK_TYPE_ANMF     // Catch:{ EOFException -> 0x00cb }
            boolean r4 = java.util.Arrays.equals(r2, r4)     // Catch:{ EOFException -> 0x00cb }
            r4 = r4 ^ r14
            goto L_0x00cc
        L_0x00cb:
            r4 = r14
        L_0x00cc:
            if (r4 == 0) goto L_0x00d3
            int r2 = r0.writeExifSegment(r9)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            goto L_0x0075
        L_0x00d3:
            r0.copyWebPChunk(r3, r9, r2)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            goto L_0x00be
        L_0x00d7:
            byte[] r2 = WEBP_CHUNK_TYPE_VP8     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            byte[] r4 = WEBP_CHUNK_TYPE_VP8L     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r0.copyChunksUpToGivenChunkType(r3, r9, r2, r4)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r2 = r0.writeExifSegment(r9)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            goto L_0x0075
        L_0x00e3:
            byte[] r6 = WEBP_CHUNK_TYPE_VP8     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            boolean r7 = java.util.Arrays.equals(r2, r6)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            if (r7 != 0) goto L_0x00f9
            byte[] r7 = WEBP_CHUNK_TYPE_VP8L     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            boolean r7 = java.util.Arrays.equals(r2, r7)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            if (r7 == 0) goto L_0x00f4
            goto L_0x00f9
        L_0x00f4:
            r23 = r12
            r2 = -1
            goto L_0x0077
        L_0x00f9:
            int r7 = r3.readInt()     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r15 = r7 % 2
            if (r15 != r14) goto L_0x0106
            int r15 = r7 + 1
        L_0x0103:
            r22 = r11
            goto L_0x0108
        L_0x0106:
            r15 = r7
            goto L_0x0103
        L_0x0108:
            r11 = 3
            r23 = r12
            byte[] r12 = new byte[r11]     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            boolean r16 = java.util.Arrays.equals(r2, r6)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r17 = r13
            r13 = 47
            if (r16 == 0) goto L_0x0143
            r3.readFully(r12)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            byte[] r11 = new byte[r11]     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r3.readFully(r11)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            byte[] r14 = WEBP_VP8_SIGNATURE     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            boolean r11 = java.util.Arrays.equals(r14, r11)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            if (r11 == 0) goto L_0x013b
            int r11 = r3.readInt()     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r14 = r11 & 16383(0x3fff, float:2.2957E-41)
            r16 = -1
            int r10 = r11 >> 16
            r10 = r10 & 16383(0x3fff, float:2.2957E-41)
            int r15 = r15 + -10
            r20 = r15
            r15 = r14
            r14 = r17
            goto L_0x0183
        L_0x013b:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            java.lang.String r1 = "Error checking VP8 signature"
            r0.<init>(r1)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            throw r0     // Catch:{ Exception -> 0x007f, all -> 0x007b }
        L_0x0143:
            r16 = -1
            byte[] r10 = WEBP_CHUNK_TYPE_VP8L     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            boolean r10 = java.util.Arrays.equals(r2, r10)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            if (r10 == 0) goto L_0x017c
            byte r10 = r3.readByte()     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            if (r10 != r13) goto L_0x0174
            int r11 = r3.readInt()     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r10 = r11 & 16383(0x3fff, float:2.2957E-41)
            int r10 = r10 + r14
            r18 = 268419072(0xfffc000, float:2.5218897E-29)
            r18 = r11 & r18
            int r18 = r18 >>> 14
            int r18 = r18 + 1
            r19 = 268435456(0x10000000, float:2.5243549E-29)
            r19 = r11 & r19
            if (r19 == 0) goto L_0x016a
            goto L_0x016c
        L_0x016a:
            r14 = r17
        L_0x016c:
            int r15 = r15 + -5
            r20 = r15
            r15 = r10
            r10 = r18
            goto L_0x0183
        L_0x0174:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            java.lang.String r1 = "Error checking VP8L signature"
            r0.<init>(r1)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            throw r0     // Catch:{ Exception -> 0x007f, all -> 0x007b }
        L_0x017c:
            r20 = r15
            r10 = r17
            r11 = r10
            r14 = r11
            r15 = r14
        L_0x0183:
            r9.write(r4)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r4 = 10
            r9.writeInt(r4)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            if (r14 == 0) goto L_0x0196
            byte r14 = r4[r17]     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r14 = r14 | 16
            byte r14 = (byte) r14     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r4[r17] = r14     // Catch:{ Exception -> 0x007f, all -> 0x007b }
        L_0x0196:
            byte r14 = r4[r17]     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r14 = r14 | 8
            byte r14 = (byte) r14     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r4[r17] = r14     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r15 = r15 + -1
            int r10 = r10 + -1
            byte r14 = (byte) r15     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r4[r22] = r14     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r14 = r15 >> 8
            byte r14 = (byte) r14     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r17 = 5
            r4[r17] = r14     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r14 = r15 >> 16
            byte r14 = (byte) r14     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r15 = 6
            r4[r15] = r14     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r14 = 7
            byte r15 = (byte) r10     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r4[r14] = r15     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r14 = r10 >> 8
            byte r14 = (byte) r14     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r4[r23] = r14     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r10 = r10 >> 16
            byte r10 = (byte) r10     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r14 = 9
            r4[r14] = r10     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r9.write(r4)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r9.write(r2)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r9.writeInt(r7)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            boolean r4 = java.util.Arrays.equals(r2, r6)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            if (r4 == 0) goto L_0x01de
            r9.write(r12)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            byte[] r2 = WEBP_VP8_SIGNATURE     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r9.write(r2)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r9.writeInt(r11)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
        L_0x01db:
            r15 = r20
            goto L_0x01ed
        L_0x01de:
            byte[] r4 = WEBP_CHUNK_TYPE_VP8L     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            boolean r2 = java.util.Arrays.equals(r2, r4)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            if (r2 == 0) goto L_0x01db
            r9.write(r13)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r9.writeInt(r11)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            goto L_0x01db
        L_0x01ed:
            androidx.exifinterface.media.ExifInterfaceUtils.copy(r3, r9, r15)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r2 = r0.writeExifSegment(r9)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
        L_0x01f4:
            int r5 = r5 + 8
            int r4 = r3.position()     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r5 = r5 - r4
            androidx.exifinterface.media.ExifInterfaceUtils.copy(r3, r9, r5)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r4 = r8.size()     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            byte[] r5 = WEBP_SIGNATURE_2     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r6 = r5.length     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r4 = r4 + r6
            r1.writeInt(r4)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r1.write(r5)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            r4 = r16
            if (r2 == r4) goto L_0x0219
            java.io.DataOutputStream r4 = r1.mOutputStream     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r4 = r4.size()     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            int r4 = r4 + r2
            r0.mOffsetToExifData = r4     // Catch:{ Exception -> 0x007f, all -> 0x007b }
        L_0x0219:
            r8.writeTo(r1)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            androidx.exifinterface.media.ExifInterfaceUtils.copy(r3, r1)     // Catch:{ Exception -> 0x007f, all -> 0x007b }
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r8)
            return
        L_0x0223:
            r0 = move-exception
            goto L_0x022e
        L_0x0225:
            r0 = move-exception
        L_0x0226:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x0223 }
            java.lang.String r2 = "Failed to save WebP file"
            r1.<init>(r2, r0)     // Catch:{ all -> 0x0223 }
            throw r1     // Catch:{ all -> 0x0223 }
        L_0x022e:
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.saveWebpAttributes(java.io.InputStream, java.io.OutputStream):void");
    }

    private void setThumbnailData(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        HashMap<String, ExifAttribute> hashMap = this.mAttributes[4];
        ExifAttribute exifAttribute = hashMap.get("Compression");
        if (exifAttribute != null) {
            int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
            this.mThumbnailCompression = intValue;
            if (intValue != 1) {
                if (intValue == 6) {
                    handleThumbnailFromJfif(byteOrderedDataInputStream, hashMap);
                    return;
                } else if (intValue != 7) {
                    return;
                }
            }
            if (isSupportedDataType(hashMap)) {
                handleThumbnailFromStrips(byteOrderedDataInputStream, hashMap);
                return;
            }
            return;
        }
        this.mThumbnailCompression = 6;
        handleThumbnailFromJfif(byteOrderedDataInputStream, hashMap);
    }

    private static boolean shouldSupportSeek(int i2) {
        if (i2 == 4 || i2 == 9 || i2 == 13 || i2 == 14) {
            return false;
        }
        return true;
    }

    private void swapBasedOnImageSize(int i2, int i7) {
        if (!this.mAttributes[i2].isEmpty() && !this.mAttributes[i7].isEmpty()) {
            ExifAttribute exifAttribute = this.mAttributes[i2].get("ImageLength");
            ExifAttribute exifAttribute2 = this.mAttributes[i2].get("ImageWidth");
            ExifAttribute exifAttribute3 = this.mAttributes[i7].get("ImageLength");
            ExifAttribute exifAttribute4 = this.mAttributes[i7].get("ImageWidth");
            if (exifAttribute == null || exifAttribute2 == null) {
                if (DEBUG) {
                    Log.d("ExifInterface", "First image does not contain valid size information");
                }
            } else if (exifAttribute3 != null && exifAttribute4 != null) {
                int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
                int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
                int intValue3 = exifAttribute3.getIntValue(this.mExifByteOrder);
                int intValue4 = exifAttribute4.getIntValue(this.mExifByteOrder);
                if (intValue < intValue3 && intValue2 < intValue4) {
                    HashMap<String, ExifAttribute>[] hashMapArr = this.mAttributes;
                    HashMap<String, ExifAttribute> hashMap = hashMapArr[i2];
                    hashMapArr[i2] = hashMapArr[i7];
                    hashMapArr[i7] = hashMap;
                }
            } else if (DEBUG) {
                Log.d("ExifInterface", "Second image does not contain valid size information");
            }
        } else if (DEBUG) {
            Log.d("ExifInterface", "Cannot perform swap since only one image data exists");
        }
    }

    private static void updateCrcWithInt(CRC32 crc32, int i2) {
        crc32.update(i2 >>> 24);
        crc32.update(i2 >>> 16);
        crc32.update(i2 >>> 8);
        crc32.update(i2);
    }

    private void updateImageSizeValues(SeekableByteOrderedDataInputStream seekableByteOrderedDataInputStream, int i2) {
        ExifAttribute exifAttribute;
        ExifAttribute exifAttribute2;
        ExifAttribute exifAttribute3 = this.mAttributes[i2].get("DefaultCropSize");
        ExifAttribute exifAttribute4 = this.mAttributes[i2].get("SensorTopBorder");
        ExifAttribute exifAttribute5 = this.mAttributes[i2].get("SensorLeftBorder");
        ExifAttribute exifAttribute6 = this.mAttributes[i2].get("SensorBottomBorder");
        ExifAttribute exifAttribute7 = this.mAttributes[i2].get("SensorRightBorder");
        if (exifAttribute3 != null) {
            if (exifAttribute3.format == 5) {
                Rational[] rationalArr = (Rational[]) exifAttribute3.getValue(this.mExifByteOrder);
                if (rationalArr == null || rationalArr.length != 2) {
                    Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(rationalArr));
                    return;
                }
                exifAttribute2 = ExifAttribute.createURational(rationalArr[0], this.mExifByteOrder);
                exifAttribute = ExifAttribute.createURational(rationalArr[1], this.mExifByteOrder);
            } else {
                int[] iArr = (int[]) exifAttribute3.getValue(this.mExifByteOrder);
                if (iArr == null || iArr.length != 2) {
                    Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(iArr));
                    return;
                }
                exifAttribute2 = ExifAttribute.createUShort(iArr[0], this.mExifByteOrder);
                exifAttribute = ExifAttribute.createUShort(iArr[1], this.mExifByteOrder);
            }
            this.mAttributes[i2].put("ImageWidth", exifAttribute2);
            this.mAttributes[i2].put("ImageLength", exifAttribute);
        } else if (exifAttribute4 == null || exifAttribute5 == null || exifAttribute6 == null || exifAttribute7 == null) {
            retrieveJpegImageSize(seekableByteOrderedDataInputStream, i2);
        } else {
            int intValue = exifAttribute4.getIntValue(this.mExifByteOrder);
            int intValue2 = exifAttribute6.getIntValue(this.mExifByteOrder);
            int intValue3 = exifAttribute7.getIntValue(this.mExifByteOrder);
            int intValue4 = exifAttribute5.getIntValue(this.mExifByteOrder);
            if (intValue2 > intValue && intValue3 > intValue4) {
                ExifAttribute createUShort = ExifAttribute.createUShort(intValue2 - intValue, this.mExifByteOrder);
                ExifAttribute createUShort2 = ExifAttribute.createUShort(intValue3 - intValue4, this.mExifByteOrder);
                this.mAttributes[i2].put("ImageLength", createUShort);
                this.mAttributes[i2].put("ImageWidth", createUShort2);
            }
        }
    }

    private void validateImages() {
        swapBasedOnImageSize(0, 5);
        swapBasedOnImageSize(0, 4);
        swapBasedOnImageSize(5, 4);
        ExifAttribute exifAttribute = this.mAttributes[1].get("PixelXDimension");
        ExifAttribute exifAttribute2 = this.mAttributes[1].get("PixelYDimension");
        if (!(exifAttribute == null || exifAttribute2 == null)) {
            this.mAttributes[0].put("ImageWidth", exifAttribute);
            this.mAttributes[0].put("ImageLength", exifAttribute2);
        }
        if (this.mAttributes[4].isEmpty() && isThumbnail(this.mAttributes[5])) {
            HashMap<String, ExifAttribute>[] hashMapArr = this.mAttributes;
            hashMapArr[4] = hashMapArr[5];
            hashMapArr[5] = new HashMap<>();
        }
        if (!isThumbnail(this.mAttributes[4])) {
            Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
        }
        replaceInvalidTags(0, "ThumbnailOrientation", "Orientation");
        replaceInvalidTags(0, "ThumbnailImageLength", "ImageLength");
        replaceInvalidTags(0, "ThumbnailImageWidth", "ImageWidth");
        replaceInvalidTags(5, "ThumbnailOrientation", "Orientation");
        replaceInvalidTags(5, "ThumbnailImageLength", "ImageLength");
        replaceInvalidTags(5, "ThumbnailImageWidth", "ImageWidth");
        replaceInvalidTags(4, "Orientation", "ThumbnailOrientation");
        replaceInvalidTags(4, "ImageLength", "ThumbnailImageLength");
        replaceInvalidTags(4, "ImageWidth", "ThumbnailImageWidth");
    }

    /* JADX WARNING: Removed duplicated region for block: B:123:0x0357  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01df  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01ff  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x023b  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0255  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x026e  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0271  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x028b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int writeExifSegment(androidx.exifinterface.media.ExifInterface.ByteOrderedDataOutputStream r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            androidx.exifinterface.media.ExifInterface$ExifTag[][] r2 = EXIF_TAGS
            int r3 = r2.length
            int[] r3 = new int[r3]
            int r2 = r2.length
            int[] r2 = new int[r2]
            androidx.exifinterface.media.ExifInterface$ExifTag[] r4 = EXIF_POINTER_TAGS
            int r5 = r4.length
            r6 = 0
            r7 = r6
        L_0x0011:
            if (r7 >= r5) goto L_0x001d
            r8 = r4[r7]
            java.lang.String r8 = r8.name
            r0.removeAttribute(r8)
            int r7 = r7 + 1
            goto L_0x0011
        L_0x001d:
            boolean r4 = r0.mHasThumbnail
            java.lang.String r5 = "JPEGInterchangeFormatLength"
            java.lang.String r7 = "StripByteCounts"
            java.lang.String r8 = "JPEGInterchangeFormat"
            java.lang.String r9 = "StripOffsets"
            if (r4 == 0) goto L_0x003a
            boolean r4 = r0.mHasThumbnailStrips
            if (r4 == 0) goto L_0x0034
            r0.removeAttribute(r9)
            r0.removeAttribute(r7)
            goto L_0x003a
        L_0x0034:
            r0.removeAttribute(r8)
            r0.removeAttribute(r5)
        L_0x003a:
            r4 = r6
        L_0x003b:
            androidx.exifinterface.media.ExifInterface$ExifTag[][] r10 = EXIF_TAGS
            int r10 = r10.length
            if (r4 >= r10) goto L_0x0065
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r10 = r0.mAttributes
            r10 = r10[r4]
            java.util.Set r10 = r10.entrySet()
            java.util.Iterator r10 = r10.iterator()
        L_0x004c:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x0062
            java.lang.Object r11 = r10.next()
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11
            java.lang.Object r11 = r11.getValue()
            if (r11 != 0) goto L_0x004c
            r10.remove()
            goto L_0x004c
        L_0x0062:
            int r4 = r4 + 1
            goto L_0x003b
        L_0x0065:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r10 = 1
            r4 = r4[r10]
            boolean r4 = r4.isEmpty()
            r11 = 0
            if (r4 != 0) goto L_0x0085
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r4 = r4[r6]
            androidx.exifinterface.media.ExifInterface$ExifTag[] r13 = EXIF_POINTER_TAGS
            r13 = r13[r10]
            java.lang.String r13 = r13.name
            java.nio.ByteOrder r14 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r14 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r11, (java.nio.ByteOrder) r14)
            r4.put(r13, r14)
        L_0x0085:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r13 = 2
            r4 = r4[r13]
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x00a3
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r4 = r4[r6]
            androidx.exifinterface.media.ExifInterface$ExifTag[] r14 = EXIF_POINTER_TAGS
            r14 = r14[r13]
            java.lang.String r14 = r14.name
            java.nio.ByteOrder r15 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r15 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r11, (java.nio.ByteOrder) r15)
            r4.put(r14, r15)
        L_0x00a3:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r14 = 3
            r4 = r4[r14]
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x00c4
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r4 = r4[r10]
            androidx.exifinterface.media.ExifInterface$ExifTag[] r15 = EXIF_POINTER_TAGS
            r15 = r15[r14]
            java.lang.String r15 = r15.name
            r16 = r13
            java.nio.ByteOrder r13 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r13 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r11, (java.nio.ByteOrder) r13)
            r4.put(r15, r13)
            goto L_0x00c6
        L_0x00c4:
            r16 = r13
        L_0x00c6:
            boolean r4 = r0.mHasThumbnail
            r13 = 4
            if (r4 == 0) goto L_0x00eb
            boolean r4 = r0.mHasThumbnailStrips
            if (r4 == 0) goto L_0x00ee
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r4 = r4[r13]
            java.nio.ByteOrder r5 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r5 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createUShort((int) r6, (java.nio.ByteOrder) r5)
            r4.put(r9, r5)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r4 = r4[r13]
            int r5 = r0.mThumbnailLength
            java.nio.ByteOrder r15 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r5 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createUShort((int) r5, (java.nio.ByteOrder) r15)
            r4.put(r7, r5)
        L_0x00eb:
            r17 = r14
            goto L_0x010d
        L_0x00ee:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r4 = r4[r13]
            java.nio.ByteOrder r7 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r7 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r11, (java.nio.ByteOrder) r7)
            r4.put(r8, r7)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r4 = r4[r13]
            int r7 = r0.mThumbnailLength
            r17 = r14
            long r14 = (long) r7
            java.nio.ByteOrder r7 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r7 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r14, (java.nio.ByteOrder) r7)
            r4.put(r5, r7)
        L_0x010d:
            r4 = r6
        L_0x010e:
            androidx.exifinterface.media.ExifInterface$ExifTag[][] r5 = EXIF_TAGS
            int r5 = r5.length
            if (r4 >= r5) goto L_0x0142
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r5 = r0.mAttributes
            r5 = r5[r4]
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
            r7 = r6
        L_0x0120:
            boolean r14 = r5.hasNext()
            if (r14 == 0) goto L_0x013a
            java.lang.Object r14 = r5.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            java.lang.Object r14 = r14.getValue()
            androidx.exifinterface.media.ExifInterface$ExifAttribute r14 = (androidx.exifinterface.media.ExifInterface.ExifAttribute) r14
            int r14 = r14.size()
            if (r14 <= r13) goto L_0x0120
            int r7 = r7 + r14
            goto L_0x0120
        L_0x013a:
            r5 = r2[r4]
            int r5 = r5 + r7
            r2[r4] = r5
            int r4 = r4 + 1
            goto L_0x010e
        L_0x0142:
            r4 = 8
            r5 = r6
        L_0x0145:
            androidx.exifinterface.media.ExifInterface$ExifTag[][] r7 = EXIF_TAGS
            int r7 = r7.length
            if (r5 >= r7) goto L_0x016a
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r7 = r0.mAttributes
            r7 = r7[r5]
            boolean r7 = r7.isEmpty()
            if (r7 != 0) goto L_0x0167
            r3[r5] = r4
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r7 = r0.mAttributes
            r7 = r7[r5]
            int r7 = r7.size()
            int r7 = r7 * 12
            int r7 = r7 + 6
            r14 = r2[r5]
            int r7 = r7 + r14
            int r7 = r7 + r4
            r4 = r7
        L_0x0167:
            int r5 = r5 + 1
            goto L_0x0145
        L_0x016a:
            boolean r5 = r0.mHasThumbnail
            if (r5 == 0) goto L_0x0193
            boolean r5 = r0.mHasThumbnailStrips
            if (r5 == 0) goto L_0x0180
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r5 = r0.mAttributes
            r5 = r5[r13]
            java.nio.ByteOrder r7 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r7 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createUShort((int) r4, (java.nio.ByteOrder) r7)
            r5.put(r9, r7)
            goto L_0x018e
        L_0x0180:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r5 = r0.mAttributes
            r5 = r5[r13]
            long r14 = (long) r4
            java.nio.ByteOrder r7 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r7 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r14, (java.nio.ByteOrder) r7)
            r5.put(r8, r7)
        L_0x018e:
            r0.mThumbnailOffset = r4
            int r5 = r0.mThumbnailLength
            int r4 = r4 + r5
        L_0x0193:
            int r5 = r0.mMimeType
            if (r5 != r13) goto L_0x0199
            int r4 = r4 + 8
        L_0x0199:
            boolean r5 = DEBUG
            if (r5 == 0) goto L_0x01d5
            r5 = r6
        L_0x019e:
            androidx.exifinterface.media.ExifInterface$ExifTag[][] r7 = EXIF_TAGS
            int r7 = r7.length
            if (r5 >= r7) goto L_0x01d5
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
            r8 = r3[r5]
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r9 = r0.mAttributes
            r9 = r9[r5]
            int r9 = r9.size()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r14 = r2[r5]
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            java.lang.Integer r15 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r8, r9, r14, r15}
            java.lang.String r8 = "index: %d, offsets: %d, tag count: %d, data sizes: %d, total size: %d"
            java.lang.String r7 = java.lang.String.format(r8, r7)
            java.lang.String r8 = "ExifInterface"
            android.util.Log.d(r8, r7)
            int r5 = r5 + 1
            goto L_0x019e
        L_0x01d5:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r2 = r2[r10]
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x01f5
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r2 = r2[r6]
            androidx.exifinterface.media.ExifInterface$ExifTag[] r5 = EXIF_POINTER_TAGS
            r5 = r5[r10]
            java.lang.String r5 = r5.name
            r7 = r3[r10]
            long r7 = (long) r7
            java.nio.ByteOrder r9 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r7 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r7, (java.nio.ByteOrder) r9)
            r2.put(r5, r7)
        L_0x01f5:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r2 = r2[r16]
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x0215
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r2 = r2[r6]
            androidx.exifinterface.media.ExifInterface$ExifTag[] r5 = EXIF_POINTER_TAGS
            r5 = r5[r16]
            java.lang.String r5 = r5.name
            r7 = r3[r16]
            long r7 = (long) r7
            java.nio.ByteOrder r9 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r7 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r7, (java.nio.ByteOrder) r9)
            r2.put(r5, r7)
        L_0x0215:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r2 = r2[r17]
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x0235
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r2 = r2[r10]
            androidx.exifinterface.media.ExifInterface$ExifTag[] r5 = EXIF_POINTER_TAGS
            r5 = r5[r17]
            java.lang.String r5 = r5.name
            r7 = r3[r17]
            long r7 = (long) r7
            java.nio.ByteOrder r9 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r7 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r7, (java.nio.ByteOrder) r9)
            r2.put(r5, r7)
        L_0x0235:
            int r2 = r0.mMimeType
            r5 = 14
            if (r2 == r13) goto L_0x0255
            r7 = 13
            if (r2 == r7) goto L_0x024b
            if (r2 == r5) goto L_0x0242
            goto L_0x0262
        L_0x0242:
            byte[] r2 = WEBP_CHUNK_TYPE_EXIF
            r1.write(r2)
            r1.writeInt(r4)
            goto L_0x0262
        L_0x024b:
            r1.writeInt(r4)
            r2 = 1700284774(0x65584966, float:6.383657E22)
            r1.writeInt(r2)
            goto L_0x0262
        L_0x0255:
            r2 = 65535(0xffff, float:9.1834E-41)
            if (r4 > r2) goto L_0x0370
            r1.writeUnsignedShort(r4)
            byte[] r2 = IDENTIFIER_EXIF_APP1
            r1.write(r2)
        L_0x0262:
            java.io.DataOutputStream r2 = r1.mOutputStream
            int r2 = r2.size()
            java.nio.ByteOrder r7 = r0.mExifByteOrder
            java.nio.ByteOrder r8 = java.nio.ByteOrder.BIG_ENDIAN
            if (r7 != r8) goto L_0x0271
            r7 = 19789(0x4d4d, float:2.773E-41)
            goto L_0x0273
        L_0x0271:
            r7 = 18761(0x4949, float:2.629E-41)
        L_0x0273:
            r1.writeShort(r7)
            java.nio.ByteOrder r7 = r0.mExifByteOrder
            r1.setByteOrder(r7)
            r7 = 42
            r1.writeUnsignedShort(r7)
            r7 = 8
            r1.writeUnsignedInt(r7)
            r7 = r6
        L_0x0286:
            androidx.exifinterface.media.ExifInterface$ExifTag[][] r8 = EXIF_TAGS
            int r8 = r8.length
            if (r7 >= r8) goto L_0x0353
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r8 = r0.mAttributes
            r8 = r8[r7]
            boolean r8 = r8.isEmpty()
            if (r8 != 0) goto L_0x034c
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r8 = r0.mAttributes
            r8 = r8[r7]
            int r8 = r8.size()
            r1.writeUnsignedShort(r8)
            r8 = r3[r7]
            int r8 = r8 + 2
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r9 = r0.mAttributes
            r9 = r9[r7]
            int r9 = r9.size()
            int r9 = r9 * 12
            int r9 = r9 + r8
            int r9 = r9 + r13
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r8 = r0.mAttributes
            r8 = r8[r7]
            java.util.Set r8 = r8.entrySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x02bc:
            boolean r14 = r8.hasNext()
            if (r14 == 0) goto L_0x030a
            java.lang.Object r14 = r8.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifTag>[] r15 = sExifTagMapsForWriting
            r15 = r15[r7]
            java.lang.Object r10 = r14.getKey()
            java.lang.Object r10 = r15.get(r10)
            androidx.exifinterface.media.ExifInterface$ExifTag r10 = (androidx.exifinterface.media.ExifInterface.ExifTag) r10
            int r10 = r10.number
            java.lang.Object r14 = r14.getValue()
            androidx.exifinterface.media.ExifInterface$ExifAttribute r14 = (androidx.exifinterface.media.ExifInterface.ExifAttribute) r14
            int r15 = r14.size()
            r1.writeUnsignedShort(r10)
            int r10 = r14.format
            r1.writeUnsignedShort(r10)
            int r10 = r14.numberOfComponents
            r1.writeInt(r10)
            if (r15 <= r13) goto L_0x02f7
            long r11 = (long) r9
            r1.writeUnsignedInt(r11)
            int r9 = r9 + r15
            goto L_0x0306
        L_0x02f7:
            byte[] r10 = r14.bytes
            r1.write(r10)
            if (r15 >= r13) goto L_0x0306
        L_0x02fe:
            if (r15 >= r13) goto L_0x0306
            r1.writeByte(r6)
            int r15 = r15 + 1
            goto L_0x02fe
        L_0x0306:
            r10 = 1
            r11 = 0
            goto L_0x02bc
        L_0x030a:
            if (r7 != 0) goto L_0x031f
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r8 = r0.mAttributes
            r8 = r8[r13]
            boolean r8 = r8.isEmpty()
            if (r8 != 0) goto L_0x031f
            r8 = r3[r13]
            long r8 = (long) r8
            r1.writeUnsignedInt(r8)
            r8 = 0
            goto L_0x0324
        L_0x031f:
            r8 = 0
            r1.writeUnsignedInt(r8)
        L_0x0324:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r10 = r0.mAttributes
            r10 = r10[r7]
            java.util.Set r10 = r10.entrySet()
            java.util.Iterator r10 = r10.iterator()
        L_0x0330:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x034d
            java.lang.Object r11 = r10.next()
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11
            java.lang.Object r11 = r11.getValue()
            androidx.exifinterface.media.ExifInterface$ExifAttribute r11 = (androidx.exifinterface.media.ExifInterface.ExifAttribute) r11
            byte[] r11 = r11.bytes
            int r12 = r11.length
            if (r12 <= r13) goto L_0x0330
            int r12 = r11.length
            r1.write(r11, r6, r12)
            goto L_0x0330
        L_0x034c:
            r8 = r11
        L_0x034d:
            int r7 = r7 + 1
            r11 = r8
            r10 = 1
            goto L_0x0286
        L_0x0353:
            boolean r3 = r0.mHasThumbnail
            if (r3 == 0) goto L_0x035e
            byte[] r3 = r0.getThumbnailBytes()
            r1.write(r3)
        L_0x035e:
            int r0 = r0.mMimeType
            if (r0 != r5) goto L_0x036a
            int r4 = r4 % 2
            r0 = 1
            if (r4 != r0) goto L_0x036a
            r1.writeByte(r6)
        L_0x036a:
            java.nio.ByteOrder r0 = java.nio.ByteOrder.BIG_ENDIAN
            r1.setByteOrder(r0)
            return r2
        L_0x0370:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Size of exif data ("
            java.lang.String r2 = " bytes) exceeds the max size of a JPEG APP1 segment (65536 bytes)"
            java.lang.String r1 = i.C0212a.j(r4, r1, r2)
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.writeExifSegment(androidx.exifinterface.media.ExifInterface$ByteOrderedDataOutputStream):int");
    }

    private void writePngExifChunk(ByteOrderedDataOutputStream byteOrderedDataOutputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.mOffsetToExifData = byteOrderedDataOutputStream.mOutputStream.size() + writeExifSegment(new ByteOrderedDataOutputStream(byteArrayOutputStream, ByteOrder.BIG_ENDIAN));
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteOrderedDataOutputStream.write(byteArray);
        CRC32 crc32 = new CRC32();
        crc32.update(byteArray, 4, byteArray.length - 4);
        byteOrderedDataOutputStream.writeInt((int) crc32.getValue());
    }

    private void writePngXmpItxtChunk(ByteOrderedDataOutputStream byteOrderedDataOutputStream) {
        byteOrderedDataOutputStream.writeInt(this.mXmpFromSeparateMarker.bytes.length + 22);
        CRC32 crc32 = new CRC32();
        byteOrderedDataOutputStream.writeInt(1767135348);
        updateCrcWithInt(crc32, 1767135348);
        byte[] bArr = PNG_ITXT_XMP_KEYWORD;
        byteOrderedDataOutputStream.write(bArr);
        crc32.update(bArr);
        byteOrderedDataOutputStream.write(this.mXmpFromSeparateMarker.bytes);
        crc32.update(this.mXmpFromSeparateMarker.bytes);
        byteOrderedDataOutputStream.writeInt((int) crc32.getValue());
        this.mFileOnDiskContainsSeparateXmpMarker = true;
    }

    public String getAttribute(String str) {
        if (str != null) {
            ExifAttribute exifAttribute = getExifAttribute(str);
            if (exifAttribute == null) {
                return null;
            }
            if (str.equals("GPSTimeStamp")) {
                int i2 = exifAttribute.format;
                if (i2 == 5 || i2 == 10) {
                    Rational[] rationalArr = (Rational[]) exifAttribute.getValue(this.mExifByteOrder);
                    if (rationalArr == null || rationalArr.length != 3) {
                        Log.w("ExifInterface", "Invalid GPS Timestamp array. array=" + Arrays.toString(rationalArr));
                        return null;
                    }
                    Rational rational = rationalArr[0];
                    Integer valueOf = Integer.valueOf((int) (((float) rational.numerator) / ((float) rational.denominator)));
                    Rational rational2 = rationalArr[1];
                    Integer valueOf2 = Integer.valueOf((int) (((float) rational2.numerator) / ((float) rational2.denominator)));
                    Rational rational3 = rationalArr[2];
                    return String.format("%02d:%02d:%02d", new Object[]{valueOf, valueOf2, Integer.valueOf((int) (((float) rational3.numerator) / ((float) rational3.denominator)))});
                }
                Log.w("ExifInterface", "GPS Timestamp format is not rational. format=" + exifAttribute.format);
                return null;
            } else if (!RATIONAL_TAGS_HANDLED_AS_DECIMALS_FOR_COMPATIBILITY.contains(str)) {
                return exifAttribute.getStringValue(this.mExifByteOrder);
            } else {
                try {
                    return Double.toString(exifAttribute.getDoubleValue(this.mExifByteOrder));
                } catch (NumberFormatException unused) {
                    return null;
                }
            }
        } else {
            throw new NullPointerException("tag shouldn't be null");
        }
    }

    public byte[] getAttributeBytes(String str) {
        if (str != null) {
            ExifAttribute exifAttribute = getExifAttribute(str);
            if (exifAttribute != null) {
                return exifAttribute.bytes;
            }
            return null;
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    public double getAttributeDouble(String str, double d) {
        if (str != null) {
            ExifAttribute exifAttribute = getExifAttribute(str);
            if (exifAttribute != null) {
                try {
                    return exifAttribute.getDoubleValue(this.mExifByteOrder);
                } catch (NumberFormatException unused) {
                }
            }
            return d;
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    public int getAttributeInt(String str, int i2) {
        if (str != null) {
            ExifAttribute exifAttribute = getExifAttribute(str);
            if (exifAttribute != null) {
                try {
                    return exifAttribute.getIntValue(this.mExifByteOrder);
                } catch (NumberFormatException unused) {
                }
            }
            return i2;
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    public double[] getLatLong() {
        String attribute = getAttribute("GPSLatitude");
        String attribute2 = getAttribute("GPSLatitudeRef");
        String attribute3 = getAttribute("GPSLongitude");
        String attribute4 = getAttribute("GPSLongitudeRef");
        if (attribute == null || attribute2 == null || attribute3 == null || attribute4 == null) {
            return null;
        }
        try {
            return new double[]{convertRationalLatLonToDouble(attribute, attribute2), convertRationalLatLonToDouble(attribute3, attribute4)};
        } catch (IllegalArgumentException unused) {
            StringBuilder q = C0086a.q("latValue=", attribute, ", latRef=", attribute2, ", lngValue=");
            q.append(attribute3);
            q.append(", lngRef=");
            q.append(attribute4);
            Log.w("ExifInterface", "Latitude/longitude values are not parsable. ".concat(q.toString()));
            return null;
        }
    }

    public int getRotationDegrees() {
        switch (getAttributeInt("Orientation", 1)) {
            case 3:
            case 4:
                return MOCRLang.KHMER;
            case 5:
            case 8:
                return 270;
            case 6:
            case 7:
                return 90;
            default:
                return 0;
        }
    }

    public byte[] getThumbnail() {
        int i2 = this.mThumbnailCompression;
        if (i2 == 6 || i2 == 7) {
            return getThumbnailBytes();
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getThumbnailBytes() {
        /*
            r8 = this;
            java.lang.String r0 = "ExifInterface"
            boolean r1 = r8.mHasThumbnail
            r2 = 0
            if (r1 != 0) goto L_0x0008
            return r2
        L_0x0008:
            byte[] r1 = r8.mThumbnailBytes
            if (r1 == 0) goto L_0x000d
            return r1
        L_0x000d:
            android.content.res.AssetManager$AssetInputStream r1 = r8.mAssetInputStream     // Catch:{ Exception -> 0x003c, all -> 0x0039 }
            if (r1 == 0) goto L_0x002d
            boolean r3 = r1.markSupported()     // Catch:{ Exception -> 0x0021, all -> 0x001c }
            if (r3 == 0) goto L_0x0024
            r1.reset()     // Catch:{ Exception -> 0x0021, all -> 0x001c }
        L_0x001a:
            r3 = r2
            goto L_0x0055
        L_0x001c:
            r8 = move-exception
            r3 = r2
        L_0x001e:
            r2 = r1
            goto L_0x008c
        L_0x0021:
            r8 = move-exception
            r3 = r2
            goto L_0x007e
        L_0x0024:
            java.lang.String r8 = "Cannot read thumbnail from inputstream without mark/reset support"
            android.util.Log.d(r0, r8)     // Catch:{ Exception -> 0x0021, all -> 0x001c }
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r1)
            return r2
        L_0x002d:
            java.lang.String r1 = r8.mFilename     // Catch:{ Exception -> 0x003c, all -> 0x0039 }
            if (r1 == 0) goto L_0x0040
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x003c, all -> 0x0039 }
            java.lang.String r3 = r8.mFilename     // Catch:{ Exception -> 0x003c, all -> 0x0039 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x003c, all -> 0x0039 }
            goto L_0x001a
        L_0x0039:
            r8 = move-exception
            r3 = r2
            goto L_0x008c
        L_0x003c:
            r8 = move-exception
            r1 = r2
            r3 = r1
            goto L_0x007e
        L_0x0040:
            java.io.FileDescriptor r1 = r8.mSeekableFileDescriptor     // Catch:{ Exception -> 0x003c, all -> 0x0039 }
            java.io.FileDescriptor r1 = android.system.Os.dup(r1)     // Catch:{ Exception -> 0x003c, all -> 0x0039 }
            int r3 = android.system.OsConstants.SEEK_SET     // Catch:{ Exception -> 0x007b, all -> 0x0078 }
            r4 = 0
            android.system.Os.lseek(r1, r4, r3)     // Catch:{ Exception -> 0x007b, all -> 0x0078 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x007b, all -> 0x0078 }
            r3.<init>(r1)     // Catch:{ Exception -> 0x007b, all -> 0x0078 }
            r7 = r3
            r3 = r1
            r1 = r7
        L_0x0055:
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r4 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream     // Catch:{ Exception -> 0x0076 }
            r4.<init>((java.io.InputStream) r1)     // Catch:{ Exception -> 0x0076 }
            int r5 = r8.mThumbnailOffset     // Catch:{ Exception -> 0x0076 }
            int r6 = r8.mOffsetToExifData     // Catch:{ Exception -> 0x0076 }
            int r5 = r5 + r6
            r4.skipFully(r5)     // Catch:{ Exception -> 0x0076 }
            int r5 = r8.mThumbnailLength     // Catch:{ Exception -> 0x0076 }
            byte[] r5 = new byte[r5]     // Catch:{ Exception -> 0x0076 }
            r4.readFully(r5)     // Catch:{ Exception -> 0x0076 }
            r8.mThumbnailBytes = r5     // Catch:{ Exception -> 0x0076 }
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r1)
            if (r3 == 0) goto L_0x0073
            androidx.exifinterface.media.ExifInterfaceUtils.closeFileDescriptor(r3)
        L_0x0073:
            return r5
        L_0x0074:
            r8 = move-exception
            goto L_0x001e
        L_0x0076:
            r8 = move-exception
            goto L_0x007e
        L_0x0078:
            r8 = move-exception
            r3 = r1
            goto L_0x008c
        L_0x007b:
            r8 = move-exception
            r3 = r1
            r1 = r2
        L_0x007e:
            java.lang.String r4 = "Encountered exception while getting thumbnail"
            android.util.Log.d(r0, r4, r8)     // Catch:{ all -> 0x0074 }
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r1)
            if (r3 == 0) goto L_0x008b
            androidx.exifinterface.media.ExifInterfaceUtils.closeFileDescriptor(r3)
        L_0x008b:
            return r2
        L_0x008c:
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r2)
            if (r3 == 0) goto L_0x0094
            androidx.exifinterface.media.ExifInterfaceUtils.closeFileDescriptor(r3)
        L_0x0094:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.getThumbnailBytes():byte[]");
    }

    public boolean hasAttribute(String str) {
        if (getExifAttribute(str) != null) {
            return true;
        }
        return false;
    }

    public boolean hasThumbnail() {
        return this.mHasThumbnail;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: java.io.FileInputStream} */
    /* JADX WARNING: type inference failed for: r7v8, types: [java.io.OutputStream, java.io.Closeable, java.io.FileOutputStream] */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0153, code lost:
        r3.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0088, code lost:
        r14 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0089, code lost:
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008c, code lost:
        r8 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x008d, code lost:
        r9 = null;
        r10 = null;
        r2 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00e2, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00e3, code lost:
        r10 = null;
        r2 = r7;
        r7 = r9;
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00f5, code lost:
        r2 = new java.io.FileOutputStream(r14.mFilename);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0105, code lost:
        android.system.Os.lseek(r14.mSeekableFileDescriptor, 0, android.system.OsConstants.SEEK_SET);
        r2 = new java.io.FileOutputStream(r14.mSeekableFileDescriptor);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0088 A[Catch:{ Exception -> 0x008c, all -> 0x0088 }, ExcHandler: all (th java.lang.Throwable), Splitter:B:31:0x0077] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00f5 A[Catch:{ Exception -> 0x0102, all -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0105 A[Catch:{ Exception -> 0x0102, all -> 0x00fe }] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveAttributes() {
        /*
            r14 = this;
            java.lang.String r0 = "Failed to save new file. Original file is stored in "
            int r1 = r14.mMimeType
            boolean r1 = isSupportedFormatForSavingAttributes(r1)
            if (r1 == 0) goto L_0x0173
            java.io.FileDescriptor r1 = r14.mSeekableFileDescriptor
            if (r1 != 0) goto L_0x001b
            java.lang.String r1 = r14.mFilename
            if (r1 == 0) goto L_0x0013
            goto L_0x001b
        L_0x0013:
            java.io.IOException r14 = new java.io.IOException
            java.lang.String r0 = "ExifInterface does not support saving attributes for the current input."
            r14.<init>(r0)
            throw r14
        L_0x001b:
            boolean r1 = r14.mHasThumbnail
            if (r1 == 0) goto L_0x0030
            boolean r1 = r14.mHasThumbnailStrips
            if (r1 == 0) goto L_0x0030
            boolean r1 = r14.mAreThumbnailStripsConsecutive
            if (r1 == 0) goto L_0x0028
            goto L_0x0030
        L_0x0028:
            java.io.IOException r14 = new java.io.IOException
            java.lang.String r0 = "ExifInterface does not support saving attributes when the image file has non-consecutive thumbnail strips"
            r14.<init>(r0)
            throw r14
        L_0x0030:
            r1 = 1
            r14.mModified = r1
            byte[] r2 = r14.getThumbnail()
            r14.mThumbnailBytes = r2
            r2 = 0
            java.lang.String r3 = "temp"
            java.lang.String r4 = "tmp"
            java.io.File r3 = java.io.File.createTempFile(r3, r4)     // Catch:{ Exception -> 0x0056, all -> 0x0052 }
            java.lang.String r4 = r14.mFilename     // Catch:{ Exception -> 0x0056, all -> 0x0052 }
            r5 = 0
            if (r4 == 0) goto L_0x005a
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0056, all -> 0x0052 }
            java.lang.String r7 = r14.mFilename     // Catch:{ Exception -> 0x0056, all -> 0x0052 }
            r4.<init>(r7)     // Catch:{ Exception -> 0x0056, all -> 0x0052 }
            goto L_0x0068
        L_0x0052:
            r14 = move-exception
            r7 = r2
            goto L_0x016c
        L_0x0056:
            r14 = move-exception
            r7 = r2
            goto L_0x0163
        L_0x005a:
            java.io.FileDescriptor r4 = r14.mSeekableFileDescriptor     // Catch:{ Exception -> 0x0056, all -> 0x0052 }
            int r7 = android.system.OsConstants.SEEK_SET     // Catch:{ Exception -> 0x0056, all -> 0x0052 }
            android.system.Os.lseek(r4, r5, r7)     // Catch:{ Exception -> 0x0056, all -> 0x0052 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0056, all -> 0x0052 }
            java.io.FileDescriptor r7 = r14.mSeekableFileDescriptor     // Catch:{ Exception -> 0x0056, all -> 0x0052 }
            r4.<init>(r7)     // Catch:{ Exception -> 0x0056, all -> 0x0052 }
        L_0x0068:
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0160, all -> 0x015d }
            r7.<init>(r3)     // Catch:{ Exception -> 0x0160, all -> 0x015d }
            androidx.exifinterface.media.ExifInterfaceUtils.copy(r4, r7)     // Catch:{ Exception -> 0x015a, all -> 0x0157 }
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r4)
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r7)
            r4 = 0
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00e8, all -> 0x0088 }
            r7.<init>(r3)     // Catch:{ Exception -> 0x00e8, all -> 0x0088 }
            java.lang.String r8 = r14.mFilename     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            if (r8 == 0) goto L_0x0094
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r9 = r14.mFilename     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            goto L_0x00a2
        L_0x0088:
            r14 = move-exception
            r10 = r2
            goto L_0x014b
        L_0x008c:
            r8 = move-exception
            r9 = r2
            r10 = r9
            r2 = r7
        L_0x0090:
            r7 = r8
            r8 = r10
            goto L_0x00ec
        L_0x0094:
            java.io.FileDescriptor r8 = r14.mSeekableFileDescriptor     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            int r9 = android.system.OsConstants.SEEK_SET     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            android.system.Os.lseek(r8, r5, r9)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.io.FileDescriptor r9 = r14.mSeekableFileDescriptor     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
        L_0x00a2:
            java.io.BufferedInputStream r9 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x00e2, all -> 0x0088 }
            r9.<init>(r7)     // Catch:{ Exception -> 0x00e2, all -> 0x0088 }
            java.io.BufferedOutputStream r10 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x00dc, all -> 0x00d9 }
            r10.<init>(r8)     // Catch:{ Exception -> 0x00dc, all -> 0x00d9 }
            int r11 = r14.mMimeType     // Catch:{ Exception -> 0x00b9 }
            r12 = 4
            if (r11 != r12) goto L_0x00be
            r14.saveJpegAttributes(r9, r10)     // Catch:{ Exception -> 0x00b9 }
            goto L_0x00cd
        L_0x00b5:
            r14 = move-exception
        L_0x00b6:
            r2 = r9
            goto L_0x014b
        L_0x00b9:
            r2 = move-exception
            r13 = r7
            r7 = r2
            r2 = r13
            goto L_0x00ec
        L_0x00be:
            r12 = 13
            if (r11 != r12) goto L_0x00c6
            r14.savePngAttributes(r9, r10)     // Catch:{ Exception -> 0x00b9 }
            goto L_0x00cd
        L_0x00c6:
            r12 = 14
            if (r11 != r12) goto L_0x00cd
            r14.saveWebpAttributes(r9, r10)     // Catch:{ Exception -> 0x00b9 }
        L_0x00cd:
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r9)
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r10)
            r3.delete()
            r14.mThumbnailBytes = r2
            return
        L_0x00d9:
            r14 = move-exception
            r10 = r2
            goto L_0x00b6
        L_0x00dc:
            r10 = move-exception
            r13 = r10
            r10 = r2
            r2 = r7
            r7 = r13
            goto L_0x00ec
        L_0x00e2:
            r9 = move-exception
            r10 = r2
            r2 = r7
            r7 = r9
            r9 = r10
            goto L_0x00ec
        L_0x00e8:
            r8 = move-exception
            r9 = r2
            r10 = r9
            goto L_0x0090
        L_0x00ec:
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0128, all -> 0x0125 }
            r11.<init>(r3)     // Catch:{ Exception -> 0x0128, all -> 0x0125 }
            java.lang.String r2 = r14.mFilename     // Catch:{ Exception -> 0x0102, all -> 0x00fe }
            if (r2 == 0) goto L_0x0105
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0102, all -> 0x00fe }
            java.lang.String r14 = r14.mFilename     // Catch:{ Exception -> 0x0102, all -> 0x00fe }
            r2.<init>(r14)     // Catch:{ Exception -> 0x0102, all -> 0x00fe }
        L_0x00fc:
            r8 = r2
            goto L_0x0114
        L_0x00fe:
            r14 = move-exception
            r1 = r4
            r2 = r11
            goto L_0x0140
        L_0x0102:
            r14 = move-exception
            r2 = r11
            goto L_0x0129
        L_0x0105:
            java.io.FileDescriptor r2 = r14.mSeekableFileDescriptor     // Catch:{ Exception -> 0x0102, all -> 0x00fe }
            int r12 = android.system.OsConstants.SEEK_SET     // Catch:{ Exception -> 0x0102, all -> 0x00fe }
            android.system.Os.lseek(r2, r5, r12)     // Catch:{ Exception -> 0x0102, all -> 0x00fe }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0102, all -> 0x00fe }
            java.io.FileDescriptor r14 = r14.mSeekableFileDescriptor     // Catch:{ Exception -> 0x0102, all -> 0x00fe }
            r2.<init>(r14)     // Catch:{ Exception -> 0x0102, all -> 0x00fe }
            goto L_0x00fc
        L_0x0114:
            androidx.exifinterface.media.ExifInterfaceUtils.copy(r11, r8)     // Catch:{ Exception -> 0x0102, all -> 0x00fe }
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r11)     // Catch:{ all -> 0x00b5 }
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r8)     // Catch:{ all -> 0x00b5 }
            java.io.IOException r14 = new java.io.IOException     // Catch:{ all -> 0x00b5 }
            java.lang.String r0 = "Failed to save new file"
            r14.<init>(r0, r7)     // Catch:{ all -> 0x00b5 }
            throw r14     // Catch:{ all -> 0x00b5 }
        L_0x0125:
            r14 = move-exception
            r1 = r4
            goto L_0x0140
        L_0x0128:
            r14 = move-exception
        L_0x0129:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x013f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x013f }
            r5.<init>(r0)     // Catch:{ all -> 0x013f }
            java.lang.String r0 = r3.getAbsolutePath()     // Catch:{ all -> 0x013f }
            r5.append(r0)     // Catch:{ all -> 0x013f }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x013f }
            r4.<init>(r0, r14)     // Catch:{ all -> 0x013f }
            throw r4     // Catch:{ all -> 0x013f }
        L_0x013f:
            r14 = move-exception
        L_0x0140:
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r2)     // Catch:{ all -> 0x0147 }
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r8)     // Catch:{ all -> 0x0147 }
            throw r14     // Catch:{ all -> 0x0147 }
        L_0x0147:
            r14 = move-exception
            r4 = r1
            goto L_0x00b6
        L_0x014b:
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r2)
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r10)
            if (r4 != 0) goto L_0x0156
            r3.delete()
        L_0x0156:
            throw r14
        L_0x0157:
            r14 = move-exception
        L_0x0158:
            r2 = r4
            goto L_0x016c
        L_0x015a:
            r14 = move-exception
        L_0x015b:
            r2 = r4
            goto L_0x0163
        L_0x015d:
            r14 = move-exception
            r7 = r2
            goto L_0x0158
        L_0x0160:
            r14 = move-exception
            r7 = r2
            goto L_0x015b
        L_0x0163:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x016b }
            java.lang.String r1 = "Failed to copy original file to temp file"
            r0.<init>(r1, r14)     // Catch:{ all -> 0x016b }
            throw r0     // Catch:{ all -> 0x016b }
        L_0x016b:
            r14 = move-exception
        L_0x016c:
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r2)
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r7)
            throw r14
        L_0x0173:
            java.io.IOException r14 = new java.io.IOException
            java.lang.String r0 = "ExifInterface only supports saving attributes for JPEG, PNG, and WebP formats."
            r14.<init>(r0)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.saveAttributes():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02c8, code lost:
        r22 = r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setAttribute(java.lang.String r24, java.lang.String r25) {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            r2 = r25
            if (r1 == 0) goto L_0x03c3
            java.lang.String r3 = "ISOSpeedRatings"
            boolean r3 = r3.equals(r1)
            java.lang.String r4 = "ExifInterface"
            if (r3 == 0) goto L_0x001d
            boolean r1 = DEBUG
            if (r1 == 0) goto L_0x001b
            java.lang.String r1 = "setAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY."
            android.util.Log.d(r4, r1)
        L_0x001b:
            java.lang.String r1 = "PhotographicSensitivity"
        L_0x001d:
            r3 = 3
            r5 = 2
            java.lang.String r6 = "/"
            r7 = 1
            if (r2 == 0) goto L_0x0116
            java.util.Set<java.lang.String> r8 = RATIONAL_TAGS_HANDLED_AS_DECIMALS_FOR_COMPATIBILITY
            boolean r8 = r8.contains(r1)
            java.lang.String r9 = " : "
            java.lang.String r10 = "Invalid value for "
            if (r8 == 0) goto L_0x005a
            boolean r8 = r2.contains(r6)
            if (r8 != 0) goto L_0x005a
            double r11 = java.lang.Double.parseDouble(r2)     // Catch:{ NumberFormatException -> 0x0044 }
            androidx.exifinterface.media.ExifInterface$Rational r8 = androidx.exifinterface.media.ExifInterface.Rational.createFromDouble(r11)     // Catch:{ NumberFormatException -> 0x0044 }
            java.lang.String r2 = r8.toString()     // Catch:{ NumberFormatException -> 0x0044 }
            goto L_0x0116
        L_0x0044:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r10)
            r0.append(r1)
            r0.append(r9)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r4, r0)
            return
        L_0x005a:
            java.lang.String r8 = "GPSTimeStamp"
            boolean r8 = r1.equals(r8)
            if (r8 == 0) goto L_0x00bc
            java.util.regex.Pattern r8 = GPS_TIMESTAMP_PATTERN
            java.util.regex.Matcher r8 = r8.matcher(r2)
            boolean r11 = r8.find()
            if (r11 != 0) goto L_0x0084
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r10)
            r0.append(r1)
            r0.append(r9)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r4, r0)
            return
        L_0x0084:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r9 = r8.group(r7)
            int r9 = java.lang.Integer.parseInt(r9)
            r2.append(r9)
            java.lang.String r9 = "/1,"
            r2.append(r9)
            java.lang.String r10 = r8.group(r5)
            int r10 = java.lang.Integer.parseInt(r10)
            r2.append(r10)
            r2.append(r9)
            java.lang.String r8 = r8.group(r3)
            int r8 = java.lang.Integer.parseInt(r8)
            r2.append(r8)
            java.lang.String r8 = "/1"
            r2.append(r8)
            java.lang.String r2 = r2.toString()
            goto L_0x0116
        L_0x00bc:
            java.lang.String r8 = "DateTime"
            boolean r8 = r8.equals(r1)
            if (r8 != 0) goto L_0x00d4
            java.lang.String r8 = "DateTimeOriginal"
            boolean r8 = r8.equals(r1)
            if (r8 != 0) goto L_0x00d4
            java.lang.String r8 = "DateTimeDigitized"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x0116
        L_0x00d4:
            java.util.regex.Pattern r8 = DATETIME_PRIMARY_FORMAT_PATTERN
            java.util.regex.Matcher r8 = r8.matcher(r2)
            boolean r8 = r8.find()
            java.util.regex.Pattern r11 = DATETIME_SECONDARY_FORMAT_PATTERN
            java.util.regex.Matcher r11 = r11.matcher(r2)
            boolean r11 = r11.find()
            int r12 = r2.length()
            r13 = 19
            if (r12 != r13) goto L_0x0100
            if (r8 != 0) goto L_0x00f5
            if (r11 != 0) goto L_0x00f5
            goto L_0x0100
        L_0x00f5:
            if (r11 == 0) goto L_0x0116
            java.lang.String r8 = "-"
            java.lang.String r9 = ":"
            java.lang.String r2 = r2.replaceAll(r8, r9)
            goto L_0x0116
        L_0x0100:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r10)
            r0.append(r1)
            r0.append(r9)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r4, r0)
            return
        L_0x0116:
            java.lang.String r8 = "Xmp"
            boolean r9 = r8.equals(r1)
            r10 = 0
            if (r9 == 0) goto L_0x0155
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r9 = r0.mAttributes
            r9 = r9[r10]
            boolean r9 = r9.containsKey(r8)
            if (r9 != 0) goto L_0x0137
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r9 = r0.mAttributes
            r11 = 5
            r9 = r9[r11]
            boolean r8 = r9.containsKey(r8)
            if (r8 == 0) goto L_0x0135
            goto L_0x0137
        L_0x0135:
            r8 = r10
            goto L_0x0138
        L_0x0137:
            r8 = r7
        L_0x0138:
            int r9 = r0.mMimeType
            int r9 = getXmpHandlingForImageType(r9)
            if (r9 != r5) goto L_0x0146
            androidx.exifinterface.media.ExifInterface$ExifAttribute r11 = r0.mXmpFromSeparateMarker
            if (r11 != 0) goto L_0x014a
            if (r8 == 0) goto L_0x014a
        L_0x0146:
            if (r9 != r3) goto L_0x0155
            if (r8 != 0) goto L_0x0155
        L_0x014a:
            if (r2 == 0) goto L_0x0151
            androidx.exifinterface.media.ExifInterface$ExifAttribute r1 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createByte(r2)
            goto L_0x0152
        L_0x0151:
            r1 = 0
        L_0x0152:
            r0.mXmpFromSeparateMarker = r1
            return
        L_0x0155:
            r3 = r10
        L_0x0156:
            androidx.exifinterface.media.ExifInterface$ExifTag[][] r8 = EXIF_TAGS
            int r8 = r8.length
            if (r3 >= r8) goto L_0x03c2
            r8 = 4
            if (r3 != r8) goto L_0x016a
            boolean r8 = r0.mHasThumbnail
            if (r8 != 0) goto L_0x016a
        L_0x0162:
            r22 = r6
            r24 = r7
            r20 = r10
            goto L_0x03b7
        L_0x016a:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifTag>[] r8 = sExifTagMapsForWriting
            r8 = r8[r3]
            java.lang.Object r8 = r8.get(r1)
            androidx.exifinterface.media.ExifInterface$ExifTag r8 = (androidx.exifinterface.media.ExifInterface.ExifTag) r8
            if (r8 == 0) goto L_0x0162
            if (r2 != 0) goto L_0x0180
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r8 = r0.mAttributes
            r8 = r8[r3]
            r8.remove(r1)
            goto L_0x0162
        L_0x0180:
            android.util.Pair r9 = guessDataFormat(r2)
            int r11 = r8.primaryFormat
            java.lang.Object r12 = r9.first
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r13 = -1
            if (r11 == r12) goto L_0x0244
            int r11 = r8.primaryFormat
            java.lang.Object r12 = r9.second
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            if (r11 != r12) goto L_0x019f
            goto L_0x0244
        L_0x019f:
            int r11 = r8.secondaryFormat
            if (r11 == r13) goto L_0x01bd
            java.lang.Object r12 = r9.first
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            if (r11 == r12) goto L_0x01b9
            int r11 = r8.secondaryFormat
            java.lang.Object r12 = r9.second
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            if (r11 != r12) goto L_0x01bd
        L_0x01b9:
            int r8 = r8.secondaryFormat
            goto L_0x0246
        L_0x01bd:
            int r11 = r8.primaryFormat
            if (r11 == r7) goto L_0x0242
            r12 = 7
            if (r11 == r12) goto L_0x0242
            if (r11 != r5) goto L_0x01c8
            goto L_0x0242
        L_0x01c8:
            boolean r11 = DEBUG
            if (r11 == 0) goto L_0x0162
            java.lang.String r11 = "Given tag ("
            java.lang.String r12 = ") value didn't match with one of expected formats: "
            java.lang.StringBuilder r11 = N2.j.k(r11, r1, r12)
            java.lang.String[] r12 = IFD_FORMAT_NAMES
            int r14 = r8.primaryFormat
            r14 = r12[r14]
            r11.append(r14)
            int r14 = r8.secondaryFormat
            java.lang.String r15 = ", "
            java.lang.String r16 = ""
            if (r14 != r13) goto L_0x01e8
            r8 = r16
            goto L_0x01f8
        L_0x01e8:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>(r15)
            int r8 = r8.secondaryFormat
            r8 = r12[r8]
            r14.append(r8)
            java.lang.String r8 = r14.toString()
        L_0x01f8:
            r11.append(r8)
            java.lang.String r8 = " (guess: "
            r11.append(r8)
            java.lang.Object r8 = r9.first
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            r8 = r12[r8]
            r11.append(r8)
            java.lang.Object r8 = r9.second
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            if (r8 != r13) goto L_0x021a
        L_0x0217:
            r8 = r16
            goto L_0x0231
        L_0x021a:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r15)
            java.lang.Object r9 = r9.second
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r9 = r12[r9]
            r8.append(r9)
            java.lang.String r16 = r8.toString()
            goto L_0x0217
        L_0x0231:
            r11.append(r8)
            java.lang.String r8 = ")"
            r11.append(r8)
            java.lang.String r8 = r11.toString()
            android.util.Log.d(r4, r8)
            goto L_0x0162
        L_0x0242:
            r8 = r11
            goto L_0x0246
        L_0x0244:
            int r8 = r8.primaryFormat
        L_0x0246:
            java.lang.String r9 = ","
            switch(r8) {
                case 1: goto L_0x03a6;
                case 2: goto L_0x0394;
                case 3: goto L_0x0369;
                case 4: goto L_0x033e;
                case 5: goto L_0x02f5;
                case 6: goto L_0x024b;
                case 7: goto L_0x0394;
                case 8: goto L_0x024b;
                case 9: goto L_0x02cc;
                case 10: goto L_0x027b;
                case 11: goto L_0x024b;
                case 12: goto L_0x0256;
                default: goto L_0x024b;
            }
        L_0x024b:
            boolean r9 = DEBUG
            if (r9 == 0) goto L_0x0162
            java.lang.String r9 = "Data format isn't one of expected formats: "
            c0.C0086a.C(r8, r9, r4)
            goto L_0x0162
        L_0x0256:
            java.lang.String[] r8 = r2.split(r9, r13)
            int r9 = r8.length
            double[] r9 = new double[r9]
            r11 = r10
        L_0x025e:
            int r12 = r8.length
            if (r11 >= r12) goto L_0x026c
            r12 = r8[r11]
            double r12 = java.lang.Double.parseDouble(r12)
            r9[r11] = r12
            int r11 = r11 + 1
            goto L_0x025e
        L_0x026c:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r8 = r0.mAttributes
            r8 = r8[r3]
            java.nio.ByteOrder r11 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r9 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createDouble(r9, r11)
            r8.put(r1, r9)
            goto L_0x0162
        L_0x027b:
            java.lang.String[] r8 = r2.split(r9, r13)
            int r9 = r8.length
            androidx.exifinterface.media.ExifInterface$Rational[] r9 = new androidx.exifinterface.media.ExifInterface.Rational[r9]
            r11 = r10
        L_0x0283:
            int r12 = r8.length
            if (r11 >= r12) goto L_0x02b7
            r12 = r8[r11]
            java.lang.String[] r12 = r12.split(r6, r13)
            androidx.exifinterface.media.ExifInterface$Rational r14 = new androidx.exifinterface.media.ExifInterface$Rational
            r15 = r12[r10]
            r24 = r7
            r25 = r8
            double r7 = java.lang.Double.parseDouble(r15)
            long r7 = (long) r7
            r12 = r12[r24]
            r20 = r10
            r21 = r11
            double r10 = java.lang.Double.parseDouble(r12)
            long r10 = (long) r10
            r19 = 0
            r15 = r7
            r17 = r10
            r14.<init>(r15, r17)
            r9[r21] = r14
            int r11 = r21 + 1
            r7 = r24
            r8 = r25
            r10 = r20
            goto L_0x0283
        L_0x02b7:
            r24 = r7
            r20 = r10
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r7 = r0.mAttributes
            r7 = r7[r3]
            java.nio.ByteOrder r8 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r8 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createSRational(r9, r8)
            r7.put(r1, r8)
        L_0x02c8:
            r22 = r6
            goto L_0x03b7
        L_0x02cc:
            r24 = r7
            r20 = r10
            java.lang.String[] r7 = r2.split(r9, r13)
            int r8 = r7.length
            int[] r8 = new int[r8]
            r9 = r20
        L_0x02d9:
            int r10 = r7.length
            if (r9 >= r10) goto L_0x02e7
            r10 = r7[r9]
            int r10 = java.lang.Integer.parseInt(r10)
            r8[r9] = r10
            int r9 = r9 + 1
            goto L_0x02d9
        L_0x02e7:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r7 = r0.mAttributes
            r7 = r7[r3]
            java.nio.ByteOrder r9 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r8 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createSLong(r8, r9)
            r7.put(r1, r8)
            goto L_0x02c8
        L_0x02f5:
            r24 = r7
            r20 = r10
            java.lang.String[] r7 = r2.split(r9, r13)
            int r8 = r7.length
            androidx.exifinterface.media.ExifInterface$Rational[] r8 = new androidx.exifinterface.media.ExifInterface.Rational[r8]
            r9 = r20
        L_0x0302:
            int r10 = r7.length
            if (r9 >= r10) goto L_0x032d
            r10 = r7[r9]
            java.lang.String[] r10 = r10.split(r6, r13)
            androidx.exifinterface.media.ExifInterface$Rational r14 = new androidx.exifinterface.media.ExifInterface$Rational
            r11 = r10[r20]
            double r11 = java.lang.Double.parseDouble(r11)
            long r11 = (long) r11
            r10 = r10[r24]
            r22 = r6
            double r5 = java.lang.Double.parseDouble(r10)
            long r5 = (long) r5
            r19 = 0
            r17 = r5
            r15 = r11
            r14.<init>(r15, r17)
            r8[r9] = r14
            int r9 = r9 + 1
            r6 = r22
            r5 = 2
            goto L_0x0302
        L_0x032d:
            r22 = r6
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r5 = r0.mAttributes
            r5 = r5[r3]
            java.nio.ByteOrder r6 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r6 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createURational((androidx.exifinterface.media.ExifInterface.Rational[]) r8, (java.nio.ByteOrder) r6)
            r5.put(r1, r6)
            goto L_0x03b7
        L_0x033e:
            r22 = r6
            r24 = r7
            r20 = r10
            java.lang.String[] r5 = r2.split(r9, r13)
            int r6 = r5.length
            long[] r6 = new long[r6]
            r7 = r20
        L_0x034d:
            int r8 = r5.length
            if (r7 >= r8) goto L_0x035b
            r8 = r5[r7]
            long r8 = java.lang.Long.parseLong(r8)
            r6[r7] = r8
            int r7 = r7 + 1
            goto L_0x034d
        L_0x035b:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r5 = r0.mAttributes
            r5 = r5[r3]
            java.nio.ByteOrder r7 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r6 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long[]) r6, (java.nio.ByteOrder) r7)
            r5.put(r1, r6)
            goto L_0x03b7
        L_0x0369:
            r22 = r6
            r24 = r7
            r20 = r10
            java.lang.String[] r5 = r2.split(r9, r13)
            int r6 = r5.length
            int[] r6 = new int[r6]
            r7 = r20
        L_0x0378:
            int r8 = r5.length
            if (r7 >= r8) goto L_0x0386
            r8 = r5[r7]
            int r8 = java.lang.Integer.parseInt(r8)
            r6[r7] = r8
            int r7 = r7 + 1
            goto L_0x0378
        L_0x0386:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r5 = r0.mAttributes
            r5 = r5[r3]
            java.nio.ByteOrder r7 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r6 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createUShort((int[]) r6, (java.nio.ByteOrder) r7)
            r5.put(r1, r6)
            goto L_0x03b7
        L_0x0394:
            r22 = r6
            r24 = r7
            r20 = r10
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r5 = r0.mAttributes
            r5 = r5[r3]
            androidx.exifinterface.media.ExifInterface$ExifAttribute r6 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createString(r2)
            r5.put(r1, r6)
            goto L_0x03b7
        L_0x03a6:
            r22 = r6
            r24 = r7
            r20 = r10
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r5 = r0.mAttributes
            r5 = r5[r3]
            androidx.exifinterface.media.ExifInterface$ExifAttribute r6 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createByte(r2)
            r5.put(r1, r6)
        L_0x03b7:
            int r3 = r3 + 1
            r7 = r24
            r10 = r20
            r6 = r22
            r5 = 2
            goto L_0x0156
        L_0x03c2:
            return
        L_0x03c3:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "tag shouldn't be null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.setAttribute(java.lang.String, java.lang.String):void");
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ByteOrderedDataInputStream extends InputStream implements DataInput {
        private ByteOrder mByteOrder;
        protected final DataInputStream mDataInputStream;
        private int mLength;
        protected int mPosition;
        private byte[] mSkipBuffer;

        public ByteOrderedDataInputStream(byte[] bArr) {
            this(new ByteArrayInputStream(bArr), ByteOrder.BIG_ENDIAN);
            this.mLength = bArr.length;
        }

        public int available() {
            return this.mDataInputStream.available();
        }

        public int length() {
            return this.mLength;
        }

        public void mark(int i2) {
            throw new UnsupportedOperationException("Mark is currently unsupported");
        }

        public int position() {
            return this.mPosition;
        }

        public int read() {
            this.mPosition++;
            return this.mDataInputStream.read();
        }

        public boolean readBoolean() {
            this.mPosition++;
            return this.mDataInputStream.readBoolean();
        }

        public byte readByte() {
            this.mPosition++;
            int read = this.mDataInputStream.read();
            if (read >= 0) {
                return (byte) read;
            }
            throw new EOFException();
        }

        public char readChar() {
            this.mPosition += 2;
            return this.mDataInputStream.readChar();
        }

        public double readDouble() {
            return Double.longBitsToDouble(readLong());
        }

        public float readFloat() {
            return Float.intBitsToFloat(readInt());
        }

        public void readFully(byte[] bArr, int i2, int i7) {
            this.mPosition += i7;
            this.mDataInputStream.readFully(bArr, i2, i7);
        }

        public int readInt() {
            this.mPosition += 4;
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            if ((read | read2 | read3 | read4) >= 0) {
                ByteOrder byteOrder = this.mByteOrder;
                if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                    return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
                }
                if (byteOrder == ByteOrder.BIG_ENDIAN) {
                    return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
            throw new EOFException();
        }

        public String readLine() {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        public long readLong() {
            this.mPosition += 8;
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            int read5 = this.mDataInputStream.read();
            int read6 = this.mDataInputStream.read();
            int read7 = this.mDataInputStream.read();
            int read8 = this.mDataInputStream.read();
            if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) >= 0) {
                ByteOrder byteOrder = this.mByteOrder;
                if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                    return (((long) read8) << 56) + (((long) read7) << 48) + (((long) read6) << 40) + (((long) read5) << 32) + (((long) read4) << 24) + (((long) read3) << 16) + (((long) read2) << 8) + ((long) read);
                }
                if (byteOrder == ByteOrder.BIG_ENDIAN) {
                    return (((long) read) << 56) + (((long) read2) << 48) + (((long) read3) << 40) + (((long) read4) << 32) + (((long) read5) << 24) + (((long) read6) << 16) + (((long) read7) << 8) + ((long) read8);
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
            throw new EOFException();
        }

        public short readShort() {
            int i2;
            this.mPosition += 2;
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) >= 0) {
                ByteOrder byteOrder = this.mByteOrder;
                if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                    i2 = (read2 << 8) + read;
                } else if (byteOrder == ByteOrder.BIG_ENDIAN) {
                    i2 = (read << 8) + read2;
                } else {
                    throw new IOException("Invalid byte order: " + this.mByteOrder);
                }
                return (short) i2;
            }
            throw new EOFException();
        }

        public byte[] readToEnd() {
            byte[] bArr = new byte[1024];
            int i2 = 0;
            while (true) {
                if (i2 == bArr.length) {
                    bArr = Arrays.copyOf(bArr, bArr.length * 2);
                }
                int read = this.mDataInputStream.read(bArr, i2, bArr.length - i2);
                if (read == -1) {
                    return Arrays.copyOf(bArr, i2);
                }
                i2 += read;
                this.mPosition += read;
            }
        }

        public String readUTF() {
            this.mPosition += 2;
            return this.mDataInputStream.readUTF();
        }

        public int readUnsignedByte() {
            this.mPosition++;
            return this.mDataInputStream.readUnsignedByte();
        }

        public long readUnsignedInt() {
            return ((long) readInt()) & 4294967295L;
        }

        public int readUnsignedShort() {
            this.mPosition += 2;
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) >= 0) {
                ByteOrder byteOrder = this.mByteOrder;
                if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                    return (read2 << 8) + read;
                }
                if (byteOrder == ByteOrder.BIG_ENDIAN) {
                    return (read << 8) + read2;
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
            throw new EOFException();
        }

        public void reset() {
            throw new UnsupportedOperationException("Reset is currently unsupported");
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public int skipBytes(int i2) {
            throw new UnsupportedOperationException("skipBytes is currently unsupported");
        }

        public void skipFully(int i2) {
            int i7 = 0;
            while (i7 < i2) {
                int i8 = i2 - i7;
                int skip = (int) this.mDataInputStream.skip((long) i8);
                if (skip <= 0) {
                    if (this.mSkipBuffer == null) {
                        this.mSkipBuffer = new byte[SerializeOptions.SORT];
                    }
                    skip = this.mDataInputStream.read(this.mSkipBuffer, 0, Math.min(SerializeOptions.SORT, i8));
                    if (skip == -1) {
                        throw new EOFException(C0212a.j(i2, "Reached EOF while skipping ", " bytes."));
                    }
                }
                i7 += skip;
            }
            this.mPosition += i7;
        }

        public ByteOrderedDataInputStream(InputStream inputStream) {
            this(inputStream, ByteOrder.BIG_ENDIAN);
        }

        public int read(byte[] bArr, int i2, int i7) {
            int read = this.mDataInputStream.read(bArr, i2, i7);
            this.mPosition += read;
            return read;
        }

        public void readFully(byte[] bArr) {
            this.mPosition += bArr.length;
            this.mDataInputStream.readFully(bArr);
        }

        public ByteOrderedDataInputStream(InputStream inputStream, ByteOrder byteOrder) {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            this.mDataInputStream = dataInputStream;
            dataInputStream.mark(0);
            this.mPosition = 0;
            this.mByteOrder = byteOrder;
            this.mLength = inputStream instanceof ByteOrderedDataInputStream ? ((ByteOrderedDataInputStream) inputStream).length() : -1;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SeekableByteOrderedDataInputStream extends ByteOrderedDataInputStream {
        public SeekableByteOrderedDataInputStream(byte[] bArr) {
            super(bArr);
            this.mDataInputStream.mark(Integer.MAX_VALUE);
        }

        public void seek(long j2) {
            int i2 = this.mPosition;
            if (((long) i2) > j2) {
                this.mPosition = 0;
                this.mDataInputStream.reset();
            } else {
                j2 -= (long) i2;
            }
            skipFully((int) j2);
        }

        public SeekableByteOrderedDataInputStream(InputStream inputStream) {
            super(inputStream);
            if (inputStream.markSupported()) {
                this.mDataInputStream.mark(Integer.MAX_VALUE);
                return;
            }
            throw new IllegalArgumentException("Cannot create SeekableByteOrderedDataInputStream with stream that does not support mark/reset");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ExifTag {
        public final String name;
        public final int number;
        public final int primaryFormat;
        public final int secondaryFormat;

        public ExifTag(String str, int i2, int i7) {
            this.name = str;
            this.number = i2;
            this.primaryFormat = i7;
            this.secondaryFormat = -1;
        }

        public boolean isFormatCompatible(int i2) {
            int i7;
            int i8 = this.primaryFormat;
            if (i8 == 7 || i2 == 7 || i8 == i2 || (i7 = this.secondaryFormat) == i2) {
                return true;
            }
            if ((i8 == 4 || i7 == 4) && i2 == 3) {
                return true;
            }
            if ((i8 == 9 || i7 == 9) && i2 == 8) {
                return true;
            }
            if ((i8 == 12 || i7 == 12) && i2 == 11) {
                return true;
            }
            return false;
        }

        public ExifTag(String str, int i2, int i7, int i8) {
            this.name = str;
            this.number = i2;
            this.primaryFormat = i7;
            this.secondaryFormat = i8;
        }
    }

    public ExifInterface(String str) {
        ExifTag[][] exifTagArr = EXIF_TAGS;
        this.mAttributes = new HashMap[exifTagArr.length];
        this.mAttributesOffsets = new HashSet(exifTagArr.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (str != null) {
            initForFilename(str);
            return;
        }
        throw new NullPointerException("filename cannot be null");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ExifInterface(java.io.FileDescriptor r4) {
        /*
            r3 = this;
            r3.<init>()
            androidx.exifinterface.media.ExifInterface$ExifTag[][] r0 = EXIF_TAGS
            int r1 = r0.length
            java.util.HashMap[] r1 = new java.util.HashMap[r1]
            r3.mAttributes = r1
            java.util.HashSet r1 = new java.util.HashSet
            int r0 = r0.length
            r1.<init>(r0)
            r3.mAttributesOffsets = r1
            java.nio.ByteOrder r0 = java.nio.ByteOrder.BIG_ENDIAN
            r3.mExifByteOrder = r0
            if (r4 == 0) goto L_0x0055
            r0 = 0
            r3.mAssetInputStream = r0
            r3.mFilename = r0
            boolean r1 = isSeekableFD(r4)
            if (r1 == 0) goto L_0x0034
            r3.mSeekableFileDescriptor = r4
            java.io.FileDescriptor r4 = android.system.Os.dup(r4)     // Catch:{ Exception -> 0x002b }
            r1 = 1
            goto L_0x0037
        L_0x002b:
            r3 = move-exception
            java.io.IOException r4 = new java.io.IOException
            java.lang.String r0 = "Failed to duplicate file descriptor"
            r4.<init>(r0, r3)
            throw r4
        L_0x0034:
            r3.mSeekableFileDescriptor = r0
            r1 = 0
        L_0x0037:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x004b }
            r2.<init>(r4)     // Catch:{ all -> 0x004b }
            r3.loadAttributes(r2)     // Catch:{ all -> 0x0048 }
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r2)
            if (r1 == 0) goto L_0x0047
            androidx.exifinterface.media.ExifInterfaceUtils.closeFileDescriptor(r4)
        L_0x0047:
            return
        L_0x0048:
            r3 = move-exception
            r0 = r2
            goto L_0x004c
        L_0x004b:
            r3 = move-exception
        L_0x004c:
            androidx.exifinterface.media.ExifInterfaceUtils.closeQuietly(r0)
            if (r1 == 0) goto L_0x0054
            androidx.exifinterface.media.ExifInterfaceUtils.closeFileDescriptor(r4)
        L_0x0054:
            throw r3
        L_0x0055:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r4 = "fileDescriptor cannot be null"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.<init>(java.io.FileDescriptor):void");
    }

    public ExifInterface(InputStream inputStream) {
        this(inputStream, 0);
    }

    public ExifInterface(InputStream inputStream, int i2) {
        ExifTag[][] exifTagArr = EXIF_TAGS;
        this.mAttributes = new HashMap[exifTagArr.length];
        this.mAttributesOffsets = new HashSet(exifTagArr.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (inputStream != null) {
            this.mFilename = null;
            boolean z = i2 != 1 ? false : true;
            this.mIsExifDataOnly = z;
            if (z) {
                this.mAssetInputStream = null;
                this.mSeekableFileDescriptor = null;
            } else if (inputStream instanceof AssetManager.AssetInputStream) {
                this.mAssetInputStream = (AssetManager.AssetInputStream) inputStream;
                this.mSeekableFileDescriptor = null;
            } else {
                if (inputStream instanceof FileInputStream) {
                    FileInputStream fileInputStream = (FileInputStream) inputStream;
                    if (isSeekableFD(fileInputStream.getFD())) {
                        this.mAssetInputStream = null;
                        this.mSeekableFileDescriptor = fileInputStream.getFD();
                    }
                }
                this.mAssetInputStream = null;
                this.mSeekableFileDescriptor = null;
            }
            loadAttributes(inputStream);
            return;
        }
        throw new NullPointerException("inputStream cannot be null");
    }
}
