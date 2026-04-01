package com.sec.longexposure;

import A.a;
import N2.j;
import android.content.Context;
import android.graphics.YuvImage;
import android.util.Log;
import com.samsung.android.feature.SemFloatingFeature;
import com.samsung.android.photoremaster.engine.enhancer.VSWUpscale2xEnhancer;
import com.samsung.android.photoremaster.engine.enhancer.VSWUpscale3xEnhancer;
import com.samsung.android.photoremaster.engine.enhancer.VSWUpscale4xEnhancer;
import com.samsung.android.photoremaster.engine.nativeInterface.VSWEngineNativeInterface;
import com.samsung.android.photoremaster.sdk.enhancer.EnhanceResult;
import com.samsung.android.photoremaster.sdk.enhancer.IEnhancer;
import com.samsung.android.photoremaster.sdk.ip.BgrBuffer;
import com.samsung.android.photoremaster.sdk.ip.IRequestCancelledChecker;
import com.samsung.android.photoremaster.sdk.ip.PrSize;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.CodecType;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.solution.filter.UniImgp;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LongExposureComposer {
    private LongExposureInfo longExposureInfo;
    private Context mContext;
    private int mRotation = 0;
    private YuvImage mYuvImage = null;

    private static void cvtColor(ByteBuffer byteBuffer, int i2, int i7, ByteBuffer byteBuffer2) {
        Log.i("LongExposureComposer", "set i/o media buffer for convert-color");
        try {
            MediaBuffer wrap = MediaBuffer.newImageAlloc().setShape(i2, i7).setColorFormat(ColorFormat.NV21).setDataType(DataType.U8C1).wrap(byteBuffer);
            MediaBuffer wrap2 = MediaBuffer.newImageAlloc().setShape(i2, i7).setColorFormat(ColorFormat.BGR).setDataType(DataType.U8C3).wrap(byteBuffer2);
            UniImgp.ofCvtColor().run(wrap, wrap2);
            wrap.release();
            wrap2.release();
            Log.i("LongExposureComposer", "color convert done");
        } catch (Exception e) {
            Log.e("LongExposureComposer", "error: " + e.getMessage());
            throw new Exception(e);
        }
    }

    private static void upscale(Context context, int i2, BgrBuffer bgrBuffer, BgrBuffer bgrBuffer2) {
        IEnhancer vSWUpscale2xEnhancer;
        if (i2 == 2) {
            vSWUpscale2xEnhancer = new VSWUpscale2xEnhancer();
        } else if (i2 == 3) {
            vSWUpscale2xEnhancer = new VSWUpscale3xEnhancer();
        } else if (i2 == 4) {
            try {
                vSWUpscale2xEnhancer = new VSWUpscale4xEnhancer();
            } catch (Exception e) {
                Exception exc = e;
                Log.e("LongExposureComposer", "error: " + exc.getMessage());
                throw new Exception(exc);
            }
        } else {
            Log.w("LongExposureComposer", "Unsupported scale factor: " + i2);
            throw new UnsupportedOperationException();
        }
        EnhanceResult enhance = vSWUpscale2xEnhancer.enhance(context, bgrBuffer, bgrBuffer2, 1.0d, Collections.EMPTY_LIST, "", new IRequestCancelledChecker() {
            public boolean isRequestCancelled() {
                return false;
            }
        });
        Log.i("LongExposureComposer", "upscale done, result: " + enhance);
    }

    private int upscaleAndEncode(Context context, ByteBuffer byteBuffer, int i2, int i7, int i8, int i10, String str) {
        RandomAccessFile randomAccessFile;
        Throwable th;
        ByteBuffer byteBuffer2 = byteBuffer;
        int i11 = i2;
        int i12 = i7;
        int i13 = i8;
        int i14 = i10;
        String str2 = str;
        if (i11 <= 0 || i13 <= 0 || i12 <= 0 || i14 <= 0 || byteBuffer2 == null || str2.isEmpty()) {
            Log.e("LongExposureComposer", "Invalid input or output parameter");
            return -10;
        }
        int max = Math.max(2, Math.min(4, i13 / i11));
        StringBuilder h5 = a.h(i11, i12, "upscale in: ", "x", ", out: ");
        j.x(h5, i13, "x", i14, ", scale: ");
        h5.append(max);
        Log.i("LongExposureComposer", h5.toString());
        int i15 = i11 * max;
        int i16 = i12 * max;
        CodecType codecType = this.longExposureInfo.getCodecType();
        if (codecType == CodecType.NONE) {
            Log.e("LongExposureComposer", "Unsupported image mime type");
            return -3;
        }
        try {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i11 * i12 * 3);
            String str3 = "encode image done, ";
            ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(i15 * i16 * 3);
            cvtColor(byteBuffer2, i11, i12, allocateDirect);
            ByteBuffer byteBuffer3 = allocateDirect;
            upscale(context, max, new BgrBuffer(new PrSize(i11, i12), byteBuffer3.array()), new BgrBuffer(new PrSize(i15, i16), allocateDirect2.array()));
            try {
                randomAccessFile = new RandomAccessFile(str2, "rw");
                Log.i("LongExposureComposer", "open file: " + randomAccessFile);
                randomAccessFile.close();
            } catch (Exception e) {
                Log.w("LongExposureComposer", "file open error: " + e.getMessage());
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            ByteBuffer exifData = this.longExposureInfo.getExifData(this.mYuvImage, this.mRotation);
            if (exifData == null) {
                return -3;
            }
            MediaBuffer allocate = MediaBuffer.newGroupAlloc().setBuffers(MediaBuffer.newImageAlloc().setShape(i15, i16).setColorFormat(ColorFormat.BGR).setDataType(DataType.U8C3).wrap(allocateDirect2), (List<MediaBuffer>) List.of(MediaBuffer.metadataBufferOf(1, exifData))).allocate();
            MediaBuffer allocate2 = MediaBuffer.newImageAlloc().setShape(i13, i14).setColorFormat(ColorFormat.NV12).setCodecType(codecType).setExtra(Message.KEY_OUT_FILE, str2).asCompressed().allocate();
            if (SemFloatingFeature.getInstance().getBoolean("SEC_FLOATING_FEATURE_MMFW_SUPPORT_PHOTOHDR")) {
                UniImgp.ofEncodeHDR().run(allocate, allocate2);
            } else {
                UniImgp.ofEncode().run(allocate, allocate2);
            }
            Log.i("LongExposureComposer", str3 + codecType);
            allocate.release();
            allocate2.release();
            VSWEngineNativeInterface.releaseByteArray(byteBuffer3.array());
            VSWEngineNativeInterface.releaseByteArray(allocateDirect2.array());
            return 0;
        } catch (Exception e7) {
            j.D(e7, new StringBuilder("error: "), "LongExposureComposer");
            return -100;
        }
        throw th;
    }

    public boolean compose(String str, String str2, int i2) {
        Log.i("LongExposureComposer", "Start to compose motion photo");
        YuvImage yuvImage = this.mYuvImage;
        if (yuvImage == null) {
            Log.e("LongExposureComposer", "No yuv data");
            return false;
        }
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(yuvImage.getYuvData().length);
        allocateDirect.put(this.mYuvImage.getYuvData());
        Log.i("LongExposureComposer", "set yuv image done, length: " + this.mYuvImage.getYuvData().length);
        Context context = this.mContext;
        int width = this.mYuvImage.getWidth();
        int height = this.mYuvImage.getHeight();
        LongExposureInfo longExposureInfo2 = this.longExposureInfo;
        String str3 = str;
        if (upscaleAndEncode(context, allocateDirect, width, height, longExposureInfo2.mWidth, longExposureInfo2.mHeight, str3) != 0 || !LongExposureSef.addMotionPhotoSef(str3, str2, i2)) {
            return false;
        }
        Log.i("LongExposureComposer", "add SEF done");
        return true;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setLongExposureInfo(LongExposureInfo longExposureInfo2) {
        this.longExposureInfo = longExposureInfo2;
    }

    public void setYuvImage(YuvImage yuvImage, int i2) {
        this.mYuvImage = new YuvImage(yuvImage.getYuvData(), yuvImage.getYuvFormat(), yuvImage.getWidth(), yuvImage.getHeight(), yuvImage.getStrides());
        this.mRotation = i2;
    }
}
