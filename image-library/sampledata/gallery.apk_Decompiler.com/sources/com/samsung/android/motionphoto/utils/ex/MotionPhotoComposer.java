package com.samsung.android.motionphoto.utils.ex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.properties.XMPProperty;
import com.samsung.android.media.SemBitmapFactory;
import com.samsung.android.media.SemExtendedFormat;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sum.core.UniExifInterface;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.format.Shape;
import com.samsung.android.sum.core.types.CodecType;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.solution.filter.UniImgp;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoComposer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "MotionPhotoComposer";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class XMPData {
        /* access modifiers changed from: private */
        public final boolean hasHdr;
        /* access modifiers changed from: private */
        public final int xmpSize;

        public XMPData(int i2, boolean z) {
            this.xmpSize = i2;
            this.hasHdr = z;
        }

        public int getXmpSize() {
            return this.xmpSize;
        }

        public boolean hasHdr() {
            return this.hasHdr;
        }
    }

    public MotionPhotoComposer() {
        Log.i(TAG, "MotionPhotoComposer[2.0.17]");
    }

    private static void copyAllExifTagsToSaveImage(File file, File file2) {
        try {
            if (MotionPhotoUtilsConst.AllExifTags.length > 0) {
                ExifInterface exifInterface = new ExifInterface(file);
                ExifInterface exifInterface2 = new ExifInterface(file2);
                for (String str : MotionPhotoUtilsConst.AllExifTags) {
                    String attribute = exifInterface.getAttribute(str);
                    if (attribute != null) {
                        exifInterface2.setAttribute(str, attribute);
                    }
                }
                exifInterface2.setAttribute("Orientation", String.valueOf(1));
                exifInterface2.saveAttributes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] copyExifBuffer(File file) {
        File file2;
        if (MotionPhotoVideoUtils.isJpeg(file.toString())) {
            return JPEGParser.getExifData(file);
        }
        byte[] exifDataFile = SemBitmapFactory.getExifDataFile(file.getAbsolutePath());
        byte[] bArr = new byte[(exifDataFile.length + 8)];
        System.arraycopy(new byte[]{-1, -40}, 0, bArr, 0, 2);
        System.arraycopy(new byte[]{-1, -31}, 0, bArr, 2, 2);
        bArr[4] = (byte) (((exifDataFile.length + 2) >>> 8) & ScoverState.TYPE_NFC_SMART_COVER);
        bArr[5] = (byte) ((exifDataFile.length + 2) & ScoverState.TYPE_NFC_SMART_COVER);
        System.arraycopy(exifDataFile, 0, bArr, 6, exifDataFile.length);
        System.arraycopy(new byte[]{-1, -39}, 0, bArr, exifDataFile.length + 6, 2);
        try {
            file2 = File.createTempFile("tempJpeg", "tmp");
        } catch (IOException e) {
            e.printStackTrace();
            file2 = null;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        fileOutputStream.write(bArr);
        fileOutputStream.close();
        byte[] readAllBytes = Files.readAllBytes(file2.toPath());
        int length = readAllBytes.length - 8;
        byte[] bArr2 = new byte[length];
        System.arraycopy(readAllBytes, 6, bArr2, 0, length);
        file2.delete();
        return bArr2;
    }

    public static XMPData hasGainmap(File file) {
        byte[] bArr;
        XMPProperty property;
        byte[] attributeBytes = new ExifInterface(file).getAttributeBytes("Xmp");
        if (attributeBytes == null) {
            Log.i(TAG, "No xmp");
            return new XMPData(MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE, false);
        }
        byte[] bArr2 = new byte[29];
        int length = attributeBytes.length;
        Log.i(TAG, "xmp length : : " + length);
        System.arraycopy(attributeBytes, 0, bArr2, 0, 29);
        if (new String(bArr2, "UTF-8").equals(MediaDefs.Meta.XMP.XMP_SIGNATURE)) {
            Log.i(TAG, "Remove header");
            int i2 = length - 29;
            bArr = new byte[i2];
            System.arraycopy(attributeBytes, 29, bArr, 0, i2);
        } else {
            Log.i(TAG, "no header");
            bArr = new byte[length];
            System.arraycopy(attributeBytes, 0, bArr, 0, attributeBytes.length);
            if (MotionPhotoVideoUtils.isJpeg(file.toString())) {
                length += 33;
            }
        }
        try {
            XMPMeta parseFromBuffer = XMPMetaFactory.parseFromBuffer(bArr);
            if (parseFromBuffer == null || ((property = parseFromBuffer.getProperty(MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS, MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_FORMAT_VERSION)) != null && property.getValue().toString().equals("1.0"))) {
                Log.d(TAG, "found gainmap");
                return new XMPData(length, true);
            }
            Log.d(TAG, "no gainmap");
            return new XMPData(MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE, false);
        } catch (XMPException unused) {
            Log.e(TAG, "No gainmap_");
            return new XMPData(MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE, false);
        }
    }

    public static MediaBuffer upscale(Context context, Bitmap bitmap) {
        return null;
    }

    public boolean compose(Context context, File file, MediaBuffer mediaBuffer, Map<String, byte[]> map, File file2, long j2) {
        RandomAccessFile randomAccessFile;
        boolean z;
        int i2;
        int i7;
        int i8;
        byte[] bArr;
        Map<String, byte[]> map2 = map;
        File file3 = file2;
        long j3 = j2;
        if (!map2.containsKey("MotionPhoto_Data")) {
            Log.e(TAG, "not supported since there is no MotionPhoto");
            return false;
        }
        MediaBuffer upscale = upscale(context, mediaBuffer);
        if (upscale == null) {
            Log.e(TAG, "Fail to upscale");
            return false;
        }
        boolean isJpeg = MotionPhotoVideoUtils.isJpeg(file3.toString());
        if (!encodeImage(file, upscale, isJpeg, file3)) {
            Log.e(TAG, "Fail to encodeImage");
            return false;
        }
        XMPData hasGainmap = hasGainmap(file3);
        Log.d(TAG, "hasHDR: " + hasGainmap.hasHdr + " xmp: " + hasGainmap.xmpSize);
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(file3, "rw");
        if (isJpeg) {
            XMPParser create = XMPParser.Companion.create(randomAccessFile2.getFD());
            create.reserveXmp(hasGainmap.xmpSize, hasGainmap.hasHdr);
            randomAccessFile2.length();
            try {
                if (map2.containsKey(MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO)) {
                    SemExtendedFormat.addData(file3, MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO, map2.get(MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO), MediaDefs.Meta.SEF.SEF_KEY_TYPE_LONG_EXPOSURE_EFFECT_INFO, 1);
                }
                if (map2.containsKey("MotionPhoto_Data")) {
                    SemExtendedFormat.addData(file3, "MotionPhoto_Data", map2.get("MotionPhoto_Data"), 2608, 1);
                }
                create.completeXmp(randomAccessFile2.getFD(), j3);
                randomAccessFile = randomAccessFile2;
                z = true;
            } catch (IOException unused) {
                Log.e(TAG, "Fail to add Data for MotionPhoto Data");
                randomAccessFile2.close();
                return false;
            }
        } else {
            XMPParser create2 = XMPParser.Companion.create(randomAccessFile2.getFD());
            create2.reserveXmp(hasGainmap.xmpSize, hasGainmap.hasHdr);
            long length = file3.length();
            int i10 = ((int) length) + 8;
            if (map2.containsKey("MotionPhoto_Data")) {
                byte[] bArr2 = map2.get("MotionPhoto_Data");
                i7 = 0;
                FileOutputStream fileOutputStream = new FileOutputStream(file3.toString(), true);
                i8 = bArr2.length;
                i2 = 1;
                int i11 = i8 + 8;
                randomAccessFile = randomAccessFile2;
                fileOutputStream.write(new byte[]{(byte) (i11 >> 24), (byte) (i11 >> 16), (byte) (i11 >> 8), (byte) i11});
                fileOutputStream.write(MediaDefs.Image.HEIF.HEIF_MPVD_BOX.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write(bArr2);
                fileOutputStream.getChannel().truncate(length + ((long) i11));
                fileOutputStream.close();
                bArr = bArr2;
            } else {
                i7 = 0;
                randomAccessFile = randomAccessFile2;
                i2 = 1;
                bArr = null;
                i8 = 0;
            }
            if (map2.containsKey(MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO)) {
                SemExtendedFormat.addData(file3, MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO, map2.get(MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO), MediaDefs.Meta.SEF.SEF_KEY_TYPE_LONG_EXPOSURE_EFFECT_INFO, i2);
            }
            if (bArr != null) {
                byte[] bArr3 = new byte[12];
                int i12 = i7;
                System.arraycopy("mpv2".getBytes(StandardCharsets.UTF_8), i12, bArr3, i12, 4);
                bArr3[4] = (byte) (i10 >> 24);
                bArr3[5] = (byte) (i10 >> 16);
                bArr3[6] = (byte) (i10 >> 8);
                bArr3[7] = (byte) i10;
                bArr3[8] = (byte) (i8 >> 24);
                bArr3[9] = (byte) (i8 >> 16);
                bArr3[10] = (byte) (i8 >> 8);
                bArr3[11] = (byte) i8;
                z = true;
                SemExtendedFormat.addData(file3, "MotionPhoto_Data", bArr3, 2608, 1);
            } else {
                z = true;
            }
            create2.completeXmp(randomAccessFile.getFD(), j3);
        }
        randomAccessFile.close();
        return z;
    }

    public boolean encodeImage(File file, MediaBuffer mediaBuffer, boolean z, File file2) {
        File file3 = file;
        ExifInterface exifInterface = new ExifInterface(file3);
        int attributeInt = exifInterface.getAttributeInt("ImageWidth", 0);
        int attributeInt2 = exifInterface.getAttributeInt("ImageLength", 0);
        Log.i(TAG, " src width= " + attributeInt + " src height= " + attributeInt2);
        UniImgp.Option option = new UniImgp.Option();
        option.setPersistentInputFormat(MediaFormat.imageOf(new Object[0]));
        ColorFormat colorFormat = ColorFormat.NV12;
        MutableMediaFormat mutableCompressedImageOf = MediaFormat.mutableCompressedImageOf(colorFormat);
        FileOutputStream fileOutputStream = new FileOutputStream(file2.toString());
        FileDescriptor fd2 = fileOutputStream.getFD();
        byte[] copyExifBuffer = copyExifBuffer(file3);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(copyExifBuffer.length);
        allocateDirect.put(copyExifBuffer);
        ArrayList arrayList = new ArrayList();
        arrayList.add(MediaBuffer.metadataBufferOf(1, allocateDirect));
        MediaBuffer allocate = MediaBuffer.newGroupAlloc().setBuffers(MediaBuffer.newImageAlloc().setDataType(DataType.U8C3).setColorFormat(ColorFormat.BGR).setShape(mediaBuffer.getCols(), mediaBuffer.getRows()).setData(mediaBuffer.getTypedData(ByteBuffer.class)).allocate(), (List<MediaBuffer>) arrayList).allocate();
        option.setPreferredColorFormat(colorFormat);
        mutableCompressedImageOf.set("encode-hdr", Boolean.TRUE);
        if (z) {
            mutableCompressedImageOf.set("codec-type", CodecType.JPEG_QURAM);
        } else {
            mutableCompressedImageOf.set("codec-type", CodecType.HEIF);
        }
        mutableCompressedImageOf.setShape(Shape.of(attributeInt2, attributeInt));
        option.setPersistentOutputFormat(mutableCompressedImageOf);
        try {
            UniImgp.of(option).newOperator().run(allocate, MediaBuffer.mutableOf(MediaBuffer.of(MediaFormat.mutableOf(MediaType.COMPRESSED_IMAGE, new Object[0]), fd2)));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Fail to encoding, e = " + e.getMessage());
            return false;
        } finally {
            fileOutputStream.close();
        }
    }

    public Bitmap getResizedBitmap(Bitmap bitmap, int i2, int i7) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i2) / ((float) width), ((float) i7) / ((float) height));
        Bitmap bitmap2 = bitmap;
        Bitmap createBitmap = Bitmap.createBitmap(bitmap2, 0, 0, width, height, matrix, false);
        bitmap2.recycle();
        return createBitmap;
    }

    public static MediaBuffer upscale(Context context, MediaBuffer mediaBuffer) {
        return null;
    }

    public boolean encodeImage(MediaBuffer mediaBuffer, boolean z, File file) {
        int i2;
        int i7;
        MediaBuffer exifBuffer = mediaBuffer.getExifBuffer();
        if (exifBuffer != null) {
            UniExifInterface uniExifInterface = (UniExifInterface) exifBuffer.getTypedData(UniExifInterface.class);
            i2 = uniExifInterface.getAttributeInt("ImageWidth", 0);
            i7 = uniExifInterface.getAttributeInt("ImageLength", 0);
            Log.i(TAG, "width=" + i2 + "height=" + i7);
        } else {
            Log.e(TAG, "No exif");
            i7 = 0;
            i2 = 0;
        }
        UniImgp.Option option = new UniImgp.Option();
        option.setPersistentInputFormat(MediaFormat.imageOf(new Object[0]));
        FileOutputStream fileOutputStream = new FileOutputStream(file.toString());
        FileDescriptor fd2 = fileOutputStream.getFD();
        ColorFormat colorFormat = ColorFormat.NV12;
        MutableMediaFormat mutableCompressedImageOf = MediaFormat.mutableCompressedImageOf(colorFormat);
        option.setPreferredColorFormat(colorFormat);
        if (z) {
            mutableCompressedImageOf.set("codec-type", CodecType.JPEG_QURAM);
        } else {
            mutableCompressedImageOf.set("codec-type", CodecType.HEIF);
        }
        mutableCompressedImageOf.setShape(Shape.of(i7, i2));
        option.setPersistentOutputFormat(mutableCompressedImageOf);
        try {
            UniImgp.of(option).newOperator().run(mediaBuffer, MediaBuffer.mutableOf(MediaBuffer.of(MediaFormat.mutableOf(MediaType.COMPRESSED_IMAGE, new Object[0]), fd2)));
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Fail to encoding, e = " + e.getMessage());
            return false;
        } finally {
            fileOutputStream.close();
        }
    }

    public boolean compose(Context context, MediaBuffer mediaBuffer, Map<String, byte[]> map, File file) {
        RandomAccessFile randomAccessFile;
        boolean z;
        int i2;
        int i7;
        int i8;
        byte[] bArr;
        Map<String, byte[]> map2 = map;
        File file2 = file;
        if (!map2.containsKey("MotionPhoto_Data")) {
            Log.e(TAG, "not supported since there is no MotionPhoto");
            return false;
        }
        MediaBuffer upscale = upscale(context, mediaBuffer);
        if (upscale == null) {
            Log.e(TAG, "Fail to upscale");
            return false;
        }
        boolean isJpeg = MotionPhotoVideoUtils.isJpeg(file2.toString());
        if (!encodeImage(upscale, isJpeg, file2)) {
            Log.e(TAG, "Fail to encodeImage");
            return false;
        }
        XMPData hasGainmap = hasGainmap(file2);
        Log.d(TAG, "hasHDR: " + hasGainmap.hasHdr + " xmp: " + hasGainmap.xmpSize);
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");
        if (isJpeg) {
            XMPParser create = XMPParser.Companion.create(randomAccessFile2.getFD());
            create.reserveXmp(hasGainmap.xmpSize, hasGainmap.hasHdr);
            randomAccessFile2.length();
            try {
                if (map2.containsKey(MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO)) {
                    SemExtendedFormat.addData(file2, MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO, map2.get(MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO), MediaDefs.Meta.SEF.SEF_KEY_TYPE_LONG_EXPOSURE_EFFECT_INFO, 1);
                }
                if (map2.containsKey("MotionPhoto_Data")) {
                    SemExtendedFormat.addData(file2, "MotionPhoto_Data", map2.get("MotionPhoto_Data"), 2608, 1);
                }
                create.completeXmp(randomAccessFile2.getFD(), 0);
                randomAccessFile = randomAccessFile2;
                z = true;
            } catch (IOException unused) {
                Log.e(TAG, "Fail to add Data for MotionPhoto Data");
                randomAccessFile2.close();
                return false;
            }
        } else {
            XMPParser create2 = XMPParser.Companion.create(randomAccessFile2.getFD());
            create2.reserveXmp(hasGainmap.xmpSize, hasGainmap.hasHdr);
            long length = file2.length();
            int i10 = ((int) length) + 8;
            if (map2.containsKey("MotionPhoto_Data")) {
                byte[] bArr2 = map2.get("MotionPhoto_Data");
                FileOutputStream fileOutputStream = new FileOutputStream(file2.toString(), true);
                i8 = bArr2.length;
                i7 = 0;
                int i11 = i8 + 8;
                i2 = 1;
                randomAccessFile = randomAccessFile2;
                fileOutputStream.write(new byte[]{(byte) (i11 >> 24), (byte) (i11 >> 16), (byte) (i11 >> 8), (byte) i11});
                fileOutputStream.write(MediaDefs.Image.HEIF.HEIF_MPVD_BOX.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write(bArr2);
                fileOutputStream.getChannel().truncate(length + ((long) i11));
                fileOutputStream.close();
                bArr = bArr2;
            } else {
                i7 = 0;
                randomAccessFile = randomAccessFile2;
                i2 = 1;
                bArr = null;
                i8 = 0;
            }
            if (map2.containsKey(MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO)) {
                SemExtendedFormat.addData(file2, MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO, map2.get(MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO), MediaDefs.Meta.SEF.SEF_KEY_TYPE_LONG_EXPOSURE_EFFECT_INFO, i2);
            }
            if (bArr != null) {
                byte[] bArr3 = new byte[12];
                int i12 = i7;
                System.arraycopy("mpv2".getBytes(StandardCharsets.UTF_8), i12, bArr3, i12, 4);
                bArr3[4] = (byte) (i10 >> 24);
                bArr3[5] = (byte) (i10 >> 16);
                bArr3[6] = (byte) (i10 >> 8);
                bArr3[7] = (byte) i10;
                bArr3[8] = (byte) (i8 >> 24);
                bArr3[9] = (byte) (i8 >> 16);
                bArr3[10] = (byte) (i8 >> 8);
                bArr3[11] = (byte) i8;
                z = true;
                SemExtendedFormat.addData(file2, "MotionPhoto_Data", bArr3, 2608, 1);
            } else {
                z = true;
            }
            create2.completeXmp(randomAccessFile.getFD(), 0);
        }
        randomAccessFile.close();
        return z;
    }

    public boolean encodeImage(File file, Bitmap bitmap, File file2) {
        ExifInterface exifInterface = new ExifInterface(file);
        int attributeInt = exifInterface.getAttributeInt("ImageWidth", 0);
        int attributeInt2 = exifInterface.getAttributeInt("ImageLength", 0);
        Log.i(TAG, " src width= " + attributeInt + " src height= " + attributeInt2);
        FileOutputStream fileOutputStream = new FileOutputStream(file2.toString());
        FileDescriptor fd2 = fileOutputStream.getFD();
        byte[] copyExifBuffer = copyExifBuffer(file);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(copyExifBuffer.length);
        allocateDirect.put(copyExifBuffer);
        ArrayList arrayList = new ArrayList();
        arrayList.add(MediaBuffer.metadataBufferOf(1, allocateDirect));
        MediaBuffer of2 = MediaBuffer.of(MediaType.IMAGE, bitmap, arrayList);
        MediaType mediaType = MediaType.COMPRESSED_IMAGE;
        MutableMediaFormat mutableOf = MediaFormat.mutableOf(mediaType, new Object[0]);
        if (MotionPhotoVideoUtils.isJpeg(file.toString())) {
            mutableOf.set("codec-type", CodecType.JPEG_QURAM);
        } else {
            mutableOf.set("codec-type", CodecType.HEIF);
        }
        MutableMediaBuffer mutableOf2 = MediaBuffer.mutableOf(MediaBuffer.of(MediaFormat.mutableOf(mediaType, new Object[0]), fd2));
        mutableOf.setShape(Shape.of(attributeInt2, attributeInt));
        UniImgp.Option option = new UniImgp.Option();
        option.setPersistentInputFormat(MediaFormat.imageOf(new Object[0]));
        option.setPreferredColorFormat(ColorFormat.NV12);
        option.setPersistentOutputFormat(mutableOf);
        try {
            UniImgp.of(option).newOperator().run(of2, mutableOf2);
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Fail to encoding, e = " + e.getMessage());
            fileOutputStream.close();
            return false;
        }
    }

    public boolean compose(Context context, MediaBuffer mediaBuffer, Map<String, byte[]> map, File file, long j2) {
        RandomAccessFile randomAccessFile;
        boolean z;
        int i2;
        int i7;
        int i8;
        byte[] bArr;
        Map<String, byte[]> map2 = map;
        File file2 = file;
        long j3 = j2;
        if (!map2.containsKey("MotionPhoto_Data")) {
            Log.e(TAG, "not supported since there is no MotionPhoto");
            return false;
        }
        MediaBuffer upscale = upscale(context, mediaBuffer);
        if (upscale == null) {
            Log.e(TAG, "Fail to upscale");
            return false;
        }
        boolean isJpeg = MotionPhotoVideoUtils.isJpeg(file2.toString());
        if (!encodeImage(upscale, isJpeg, file2)) {
            Log.e(TAG, "Fail to encodeImage");
            return false;
        }
        XMPData hasGainmap = hasGainmap(file2);
        Log.d(TAG, "hasHDR: " + hasGainmap.hasHdr + " xmp: " + hasGainmap.xmpSize);
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");
        if (isJpeg) {
            XMPParser create = XMPParser.Companion.create(randomAccessFile2.getFD());
            create.reserveXmp(hasGainmap.xmpSize, hasGainmap.hasHdr);
            randomAccessFile2.length();
            try {
                if (map2.containsKey(MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO)) {
                    SemExtendedFormat.addData(file2, MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO, map2.get(MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO), MediaDefs.Meta.SEF.SEF_KEY_TYPE_LONG_EXPOSURE_EFFECT_INFO, 1);
                }
                if (map2.containsKey("MotionPhoto_Data")) {
                    SemExtendedFormat.addData(file2, "MotionPhoto_Data", map2.get("MotionPhoto_Data"), 2608, 1);
                }
                create.completeXmp(randomAccessFile2.getFD(), j3);
                randomAccessFile = randomAccessFile2;
                z = true;
            } catch (IOException unused) {
                Log.e(TAG, "Fail to add Data for MotionPhoto Data");
                randomAccessFile2.close();
                return false;
            }
        } else {
            XMPParser create2 = XMPParser.Companion.create(randomAccessFile2.getFD());
            create2.reserveXmp(hasGainmap.xmpSize, hasGainmap.hasHdr);
            long length = file2.length();
            int i10 = ((int) length) + 8;
            if (map2.containsKey("MotionPhoto_Data")) {
                byte[] bArr2 = map2.get("MotionPhoto_Data");
                i7 = 0;
                FileOutputStream fileOutputStream = new FileOutputStream(file2.toString(), true);
                i8 = bArr2.length;
                i2 = 1;
                int i11 = i8 + 8;
                randomAccessFile = randomAccessFile2;
                fileOutputStream.write(new byte[]{(byte) (i11 >> 24), (byte) (i11 >> 16), (byte) (i11 >> 8), (byte) i11});
                fileOutputStream.write(MediaDefs.Image.HEIF.HEIF_MPVD_BOX.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write(bArr2);
                fileOutputStream.getChannel().truncate(length + ((long) i11));
                fileOutputStream.close();
                bArr = bArr2;
            } else {
                i7 = 0;
                randomAccessFile = randomAccessFile2;
                i2 = 1;
                bArr = null;
                i8 = 0;
            }
            if (map2.containsKey(MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO)) {
                SemExtendedFormat.addData(file2, MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO, map2.get(MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO), MediaDefs.Meta.SEF.SEF_KEY_TYPE_LONG_EXPOSURE_EFFECT_INFO, i2);
            }
            if (bArr != null) {
                byte[] bArr3 = new byte[12];
                int i12 = i7;
                System.arraycopy("mpv2".getBytes(StandardCharsets.UTF_8), i12, bArr3, i12, 4);
                bArr3[4] = (byte) (i10 >> 24);
                bArr3[5] = (byte) (i10 >> 16);
                bArr3[6] = (byte) (i10 >> 8);
                bArr3[7] = (byte) i10;
                bArr3[8] = (byte) (i8 >> 24);
                bArr3[9] = (byte) (i8 >> 16);
                bArr3[10] = (byte) (i8 >> 8);
                bArr3[11] = (byte) i8;
                z = true;
                SemExtendedFormat.addData(file2, "MotionPhoto_Data", bArr3, 2608, 1);
            } else {
                z = true;
            }
            create2.completeXmp(randomAccessFile.getFD(), j3);
        }
        randomAccessFile.close();
        return z;
    }

    public boolean compose(Context context, File file, Bitmap bitmap, File file2, long j2) {
        MediaBuffer upscale = upscale(context, bitmap);
        if (upscale == null) {
            Log.e(TAG, "Fail to upscale");
            return false;
        } else if (!encodeImage(file, upscale, MotionPhotoVideoUtils.isJpeg(file2.toString()), file2)) {
            Log.e(TAG, "Fail to encodeImage");
            return false;
        } else {
            XMPData hasGainmap = hasGainmap(file2);
            Log.d(TAG, "hasHDR: " + hasGainmap.hasHdr + " xmp: " + hasGainmap.xmpSize);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
            if (MotionPhotoVideoUtils.isJpeg(file.toString())) {
                XMPParser create = XMPParser.Companion.create(randomAccessFile.getFD());
                create.reserveXmp(hasGainmap.xmpSize, hasGainmap.hasHdr);
                randomAccessFile.length();
                int dataCount = SemExtendedFormat.getDataCount(file);
                String[] keyNameArray = SemExtendedFormat.getKeyNameArray(file);
                if (dataCount <= 0 || keyNameArray == null) {
                    Log.e(TAG, "No sef in file");
                }
                for (String str : keyNameArray) {
                    Log.i(TAG, "numSef : " + dataCount + " SEF Key : " + str);
                    SemExtendedFormat.addData(file2, str, SemExtendedFormat.getData(file, str), SemExtendedFormat.getDataType(file, str), 1);
                }
                create.completeXmp(randomAccessFile.getFD(), 0);
            } else {
                XMPParser create2 = XMPParser.Companion.create(randomAccessFile.getFD());
                create2.reserveXmp(hasGainmap.xmpSize, hasGainmap.hasHdr);
                MotionPhotoVideoUtils.VideoInfo videoDataPosition = MotionPhotoVideoUtils.getVideoDataPosition(file);
                if (videoDataPosition != null && videoDataPosition.getVideoLength() > 0) {
                    long videoOffset = videoDataPosition.getVideoOffset();
                    videoDataPosition.getVideoLength();
                    int isMotionPhotoV2 = videoDataPosition.isMotionPhotoV2();
                    if (isMotionPhotoV2 == 3 || isMotionPhotoV2 == 4) {
                        Log.d(TAG, "MotionPhotoV2");
                        long j3 = videoOffset - 8;
                        long length = file.length() - j3;
                        byte[] bArr = new byte[((int) length)];
                        FileInputStream fileInputStream = new FileInputStream(file.toString());
                        fileInputStream.getChannel().position(j3);
                        fileInputStream.read(bArr);
                        fileInputStream.close();
                        long length2 = file2.length();
                        FileOutputStream fileOutputStream = new FileOutputStream(file2.toString(), true);
                        fileOutputStream.write(bArr);
                        fileOutputStream.getChannel().truncate(length2 + length);
                        fileOutputStream.close();
                        create2.completeXmp(randomAccessFile.getFD(), 0);
                    } else {
                        Log.d(TAG, "Not MotionPhotoV2");
                    }
                }
            }
            randomAccessFile.close();
            return true;
        }
    }
}
