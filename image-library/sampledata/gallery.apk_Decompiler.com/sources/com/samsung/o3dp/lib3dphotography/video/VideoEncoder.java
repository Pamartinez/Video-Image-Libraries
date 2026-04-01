package com.samsung.o3dp.lib3dphotography.video;

import android.graphics.Bitmap;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.view.Surface;
import bc.C0584a;
import com.samsung.o3dp.lib3dphotography.nativelib.JNI;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoEncoder {
    private static final byte[] ABORT_OF_ENCODING;
    private static final int BIT_RATE = 12000000;
    private static final int BUFFER_POOL_SIZE = 3;
    private static final byte[] END_OF_VIDEO;
    private static final long INPUT_BUFFER_TIMEOUT_US = 500000;
    private static final int I_FRAME_INTERVAL = 1;
    private static final String MIME_TYPE = "video/avc";
    private static final long OUTPUT_BUFFER_TIMEOUT_US = 500000;
    private static final int PROCESSING_QUEUE_SIZE = 3;
    private static final String TAG = "VideoEncoder";
    private final BlockingQueue<byte[]> mBufferPool = new ArrayBlockingQueue(3);
    private final VideoEncoderCallback mCallback;
    private int mGenerateIndex = 0;
    private final int mHeight;
    private File mOutputFile;
    private final BlockingQueue<byte[]> mProcessingQueue = new ArrayBlockingQueue(3);
    private int mTrackIndex;
    private final int mWidth;
    private MediaCodec mediaCodec;
    private MediaMuxer mediaMuxer;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface VideoEncoderCallback {
        void onEncodingComplete(File file);
    }

    static {
        Charset charset = StandardCharsets.UTF_8;
        END_OF_VIDEO = "EOV".getBytes(charset);
        ABORT_OF_ENCODING = "AOE".getBytes(charset);
    }

    public VideoEncoder(VideoEncoderCallback videoEncoderCallback, int i2, int i7) {
        this.mWidth = i2;
        this.mHeight = i7;
        this.mCallback = videoEncoderCallback;
    }

    private static long computePresentationTime(long j2) {
        return (j2 * 1000000) / 30;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r1.mediaCodec.queueInputBuffer(r15, 0, 0, 0, 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x015d, code lost:
        com.samsung.o3dp.lib3dphotography.utils.LogUtil.i(TAG, "encode: Meet ".concat(new java.lang.String(r4, java.nio.charset.StandardCharsets.UTF_8)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x016f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0170, code lost:
        r4 = ABORT_OF_ENCODING;
        com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(TAG, r3 + r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void encode() {
        /*
            r21 = this;
            r1 = r21
            java.lang.String r0 = "Encoder started"
            java.lang.String r2 = "VideoEncoder"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.d(r2, r0)
            int r0 = r1.mWidth
            int r3 = r1.mHeight
            int r4 = r0 * r3
            int r0 = r0 + 1
            int r0 = r0 / 2
            int r3 = r3 + 1
            int r3 = r3 / 2
            int r3 = r3 * r0
            int r3 = r3 * 2
            int r8 = r3 + r4
            byte[] r0 = new byte[r8]
            r3 = 0
        L_0x001f:
            java.lang.String r4 = "mProcessingQueue.take()"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.d(r2, r4)     // Catch:{ InterruptedException -> 0x0182 }
            java.util.concurrent.BlockingQueue<byte[]> r4 = r1.mProcessingQueue     // Catch:{ InterruptedException -> 0x0182 }
            java.lang.Object r4 = r4.take()     // Catch:{ InterruptedException -> 0x0182 }
            byte[] r4 = (byte[]) r4     // Catch:{ InterruptedException -> 0x0182 }
            if (r4 != 0) goto L_0x0035
            java.lang.String r0 = "encode: Unexpected null is returned"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r0)
            goto L_0x016d
        L_0x0035:
            android.media.MediaCodec r3 = r1.mediaCodec
            r12 = 500000(0x7a120, double:2.47033E-318)
            int r15 = r3.dequeueInputBuffer(r12)
            byte[] r3 = END_OF_VIDEO
            java.lang.String r14 = "CodecException at mediaCodec.queueInputBuffer(), "
            if (r4 == r3) goto L_0x0048
            byte[] r3 = ABORT_OF_ENCODING
            if (r4 != r3) goto L_0x004b
        L_0x0048:
            r3 = r14
            goto L_0x0150
        L_0x004b:
            int r5 = r1.mWidth
            int r6 = r1.mHeight
            boolean r5 = com.samsung.o3dp.lib3dphotography.nativelib.JNI.convertBgrToNv21(r4, r0, r5, r6)
            if (r5 != 0) goto L_0x005c
            java.lang.String r0 = "Failed to JNI.convertBgrToNv21()"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r0)
            goto L_0x016d
        L_0x005c:
            java.util.concurrent.BlockingQueue<byte[]> r5 = r1.mBufferPool     // Catch:{ InterruptedException -> 0x0138 }
            r5.put(r4)     // Catch:{ InterruptedException -> 0x0138 }
            if (r15 < 0) goto L_0x00b8
            int r5 = r1.mGenerateIndex
            long r5 = (long) r5
            long r9 = computePresentationTime(r5)
            android.media.MediaCodec r5 = r1.mediaCodec     // Catch:{ CodecException -> 0x00a2 }
            java.nio.ByteBuffer r5 = r5.getInputBuffer(r15)     // Catch:{ CodecException -> 0x00a2 }
            if (r5 != 0) goto L_0x0079
            java.lang.String r0 = "inputBuffer is null"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r0)
            goto L_0x016d
        L_0x0079:
            r5.clear()
            r5.put(r0)
            android.media.MediaCodec r5 = r1.mediaCodec     // Catch:{ CodecException -> 0x008e }
            r7 = 0
            r11 = 0
            r6 = r15
            r5.queueInputBuffer(r6, r7, r8, r9, r11)     // Catch:{ CodecException -> 0x008e }
            int r5 = r1.mGenerateIndex
            int r5 = r5 + 1
            r1.mGenerateIndex = r5
            goto L_0x00b8
        L_0x008e:
            r0 = move-exception
            byte[] r3 = ABORT_OF_ENCODING
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r14)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r0)
            goto L_0x019b
        L_0x00a2:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "CodecException at mediaCodec.getInputBuffer(), "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r0)
            byte[] r3 = ABORT_OF_ENCODING
            goto L_0x019b
        L_0x00b8:
            android.media.MediaCodec$BufferInfo r5 = new android.media.MediaCodec$BufferInfo
            r5.<init>()
            android.media.MediaCodec r6 = r1.mediaCodec
            int r6 = r6.dequeueOutputBuffer(r5, r12)
            r7 = -1
            if (r6 != r7) goto L_0x00cd
            java.lang.String r0 = "No output from encoder available"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r0)
            goto L_0x019b
        L_0x00cd:
            r3 = -2
            if (r6 != r3) goto L_0x00e4
            android.media.MediaCodec r3 = r1.mediaCodec
            android.media.MediaFormat r3 = r3.getOutputFormat()
            android.media.MediaMuxer r5 = r1.mediaMuxer
            int r3 = r5.addTrack(r3)
            r1.mTrackIndex = r3
            android.media.MediaMuxer r3 = r1.mediaMuxer
            r3.start()
            goto L_0x0135
        L_0x00e4:
            if (r6 >= 0) goto L_0x00f8
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "unexpected result from encoder.dequeueOutputBuffer: "
            r3.<init>(r5)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r3)
            goto L_0x0135
        L_0x00f8:
            int r3 = r5.size
            if (r3 == 0) goto L_0x0135
            android.media.MediaCodec r3 = r1.mediaCodec
            java.nio.ByteBuffer r3 = r3.getOutputBuffer(r6)
            if (r3 != 0) goto L_0x011b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "encoderOutputBuffer "
            r3.<init>(r5)
            r3.append(r6)
            java.lang.String r5 = " was null"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r3)
            goto L_0x0135
        L_0x011b:
            int r7 = r5.offset
            r3.position(r7)
            int r7 = r5.offset
            int r9 = r5.size
            int r7 = r7 + r9
            r3.limit(r7)
            android.media.MediaMuxer r7 = r1.mediaMuxer
            int r9 = r1.mTrackIndex
            r7.writeSampleData(r9, r3, r5)
            android.media.MediaCodec r3 = r1.mediaCodec
            r5 = 0
            r3.releaseOutputBuffer(r6, r5)
        L_0x0135:
            r3 = r4
            goto L_0x001f
        L_0x0138:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Failed to mBufferPool.put() "
            r1.<init>(r3)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r1)
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x0150:
            android.media.MediaCodec r14 = r1.mediaCodec     // Catch:{ CodecException -> 0x016f }
            r18 = 0
            r20 = 4
            r16 = 0
            r17 = 0
            r14.queueInputBuffer(r15, r16, r17, r18, r20)     // Catch:{ CodecException -> 0x016f }
            java.lang.String r0 = new java.lang.String
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8
            r0.<init>(r4, r3)
            java.lang.String r3 = "encode: Meet "
            java.lang.String r0 = r3.concat(r0)
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.i(r2, r0)
        L_0x016d:
            r3 = r4
            goto L_0x019b
        L_0x016f:
            r0 = move-exception
            byte[] r4 = ABORT_OF_ENCODING
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r3)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r0)
            goto L_0x016d
        L_0x0182:
            r0 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "encode: Failed to mProcessingQueue.take() "
            r4.<init>(r5)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r2, r0)
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
        L_0x019b:
            r1.release()
            byte[] r0 = ABORT_OF_ENCODING
            if (r3 != r0) goto L_0x01b0
            java.io.File r0 = r1.mOutputFile
            boolean r0 = r0.delete()
            if (r0 != 0) goto L_0x01b7
            java.lang.String r0 = "Output video file was not deleted"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.w(r2, r0)
            goto L_0x01b7
        L_0x01b0:
            com.samsung.o3dp.lib3dphotography.video.VideoEncoder$VideoEncoderCallback r0 = r1.mCallback
            java.io.File r1 = r1.mOutputFile
            r0.onEncodingComplete(r1)
        L_0x01b7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.video.VideoEncoder.encode():void");
    }

    private void release() {
        MediaCodec mediaCodec2 = this.mediaCodec;
        if (mediaCodec2 != null) {
            mediaCodec2.stop();
            this.mediaCodec.release();
            this.mediaCodec = null;
            LogUtil.d(TAG, "mediaCodec is released");
        }
        MediaMuxer mediaMuxer2 = this.mediaMuxer;
        if (mediaMuxer2 != null) {
            try {
                mediaMuxer2.stop();
            } catch (IllegalStateException e) {
                LogUtil.w(TAG, "Failed to stop muxer, possibly due to an abort request : " + e);
            }
            this.mediaMuxer.release();
            this.mediaMuxer = null;
            LogUtil.d(TAG, "mediaMuxer is released");
        }
        this.mGenerateIndex = 0;
        this.mProcessingQueue.clear();
        this.mBufferPool.clear();
    }

    public Thread getEncodingConsumer(File file) {
        this.mOutputFile = file;
        LogUtil.i(TAG, "Start encoding " + file + " (" + this.mWidth + "x" + this.mHeight + ")");
        for (int i2 = 0; i2 < 3; i2++) {
            this.mBufferPool.add(new byte[(this.mWidth * this.mHeight * 3)]);
        }
        try {
            String canonicalPath = file.getCanonicalPath();
            try {
                this.mediaCodec = MediaCodec.createEncoderByType("video/avc");
                MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", this.mWidth, this.mHeight);
                createVideoFormat.setInteger("bitrate", BIT_RATE);
                createVideoFormat.setInteger("frame-rate", 30);
                createVideoFormat.setInteger("color-format", 2135033992);
                createVideoFormat.setInteger("i-frame-interval", 1);
                this.mediaCodec.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
                this.mediaCodec.start();
                try {
                    this.mediaMuxer = new MediaMuxer(canonicalPath, 0);
                    LogUtil.i(TAG, "Initialization complete. Starting encoder...");
                    return new Thread(new C0584a(27, this), "ProcessingQueue Consumer");
                } catch (IOException e) {
                    LogUtil.e(TAG, "MediaMuxer creation failed : " + e);
                    throw e;
                }
            } catch (IOException e7) {
                LogUtil.e(TAG, "Unable to create MediaCodec " + e7);
                throw e7;
            }
        } catch (IOException e8) {
            LogUtil.e(TAG, "Unable to get path for " + file + " : " + e8);
            throw e8;
        }
    }

    public void queueFrame(Bitmap bitmap) {
        Bitmap bitmap2;
        LogUtil.d(TAG, "Queueing frame");
        if (this.mediaCodec == null || this.mediaMuxer == null) {
            LogUtil.d(TAG, "Failed to queue frame. Encoding not started");
            return;
        }
        try {
            byte[] take = this.mBufferPool.take();
            if (bitmap.getWidth() == this.mWidth && bitmap.getHeight() == this.mHeight) {
                bitmap2 = bitmap;
            } else {
                bitmap2 = Bitmap.createScaledBitmap(bitmap, this.mWidth, this.mHeight, true);
            }
            if (!JNI.convertBitmapToBgr(bitmap2, take)) {
                LogUtil.e(TAG, "Failed to JNI.resizeBitmapToBgr()");
                return;
            }
            bitmap.recycle();
            bitmap2.recycle();
            try {
                LogUtil.d(TAG, "mProcessingQueue.put()");
                this.mProcessingQueue.put(take);
            } catch (InterruptedException e) {
                LogUtil.e(TAG, "queueFrame: Failed to mProcessingQueue.put() " + e);
                Thread.currentThread().interrupt();
            }
        } catch (InterruptedException e7) {
            LogUtil.e(TAG, "queueFrame: Failed to mBufferPool.take() " + e7);
            throw new RuntimeException(e7);
        }
    }

    public void stopEncoding(boolean z) {
        byte[] bArr;
        LogUtil.d(TAG, "stopEncoding");
        if (this.mediaCodec == null || this.mediaMuxer == null) {
            LogUtil.d(TAG, "Failed to stop encoding since it never started");
            return;
        }
        if (z) {
            try {
                bArr = ABORT_OF_ENCODING;
            } catch (InterruptedException e) {
                LogUtil.e(TAG, "Failed to mProcessingQueue.put() : " + e);
                throw new RuntimeException(e);
            }
        } else {
            bArr = END_OF_VIDEO;
        }
        LogUtil.i(TAG, "mProcessingQueue.put : ".concat(new String(bArr, StandardCharsets.UTF_8)));
        this.mProcessingQueue.put(bArr);
        LogUtil.d(TAG, "stopEncoding");
    }
}
