package srib.vizinsight.dvs;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Size;
import com.samsung.android.media.SemAsyncVideoFrameDecoder;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SegmentAsyncTask extends AsyncTask<String, Integer, Void> {
    private static final String TAG = "DVS_SegmentAyncTask";
    Context mContext;
    int mCurrentIndex;
    DVSGIFConfig mDvsGifConfig;
    SegmentResultFileCreared mListener;
    String mResultPath;
    Boolean mResultSaved;
    float mTouchX;
    float mTouchY;
    Uri mUri;
    SemAsyncVideoFrameDecoder vfd = null;

    public SegmentAsyncTask(Context context, DVSGIFConfig dVSGIFConfig, Boolean bool, SegmentResultFileCreared segmentResultFileCreared) {
        Boolean bool2 = Boolean.FALSE;
        this.mResultSaved = bool2;
        this.mContext = context;
        this.mTouchX = ((float) dVSGIFConfig.getTouchX()) / ((float) dVSGIFConfig.getScreenWidth());
        this.mTouchY = ((float) dVSGIFConfig.getTouchY()) / ((float) dVSGIFConfig.getScreenHeight());
        if (bool.booleanValue() || (dVSGIFConfig.getTouchX() == -1 && dVSGIFConfig.getTouchY() == -1)) {
            this.mTouchX = -1.0f;
            this.mTouchY = -1.0f;
        }
        this.mUri = dVSGIFConfig.getUrl();
        this.mCurrentIndex = dVSGIFConfig.getCurrentIndex();
        if (dVSGIFConfig.getSavePath().equalsIgnoreCase("")) {
            this.mResultPath = Utils.getResultFilePath(context, dVSGIFConfig.getUrl());
        } else {
            String savePath = dVSGIFConfig.getSavePath();
            this.mResultPath = savePath;
            Log.d("SHARAD", savePath);
            File file = new File(this.mResultPath);
            Log.d("SHARAD", file.getParent());
            file.getParentFile().mkdirs();
        }
        this.mListener = segmentResultFileCreared;
        this.mDvsGifConfig = dVSGIFConfig;
        this.mResultSaved = bool2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$doInBackground$0(ArrayList arrayList, Size size, long j2, SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder) {
        Log.d(TAG, "onInitCompletingForward");
        try {
            startSemVideoDecoder(semAsyncVideoFrameDecoder, arrayList, size);
        } catch (IllegalStateException unused) {
            Log.d(TAG, "VFD onInitCompletedForward IllegalStateException");
        }
        Log.d(TAG, "onInitCompletedForward. Time taken : " + (System.currentTimeMillis() - j2));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$doInBackground$1(BlockingDeque blockingDeque, long j2, SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, Bitmap bitmap, int i2, int i7) {
        Log.d(TAG, "onVideoFrameForward. bitmap width(" + bitmap.getWidth() + "), height(" + bitmap.getHeight() + "), requestedTimeMs(" + i2 + "), frameTimeMs(" + i7 + ")");
        blockingDeque.add(bitmap);
        StringBuilder sb2 = new StringBuilder("onVideoFrameForward. Time taken for decode until this frame : ");
        sb2.append(System.currentTimeMillis() - j2);
        Log.d(TAG, sb2.toString());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$doInBackground$2(ArrayList arrayList, Size size, long j2, SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder) {
        Log.d(TAG, "onInitCompletingBackward");
        try {
            startSemVideoDecoder(semAsyncVideoFrameDecoder, arrayList, size);
        } catch (IllegalStateException unused) {
            Log.d(TAG, "VFD onInitCompletingBackward IllegalStateException");
        }
        Log.d(TAG, "onInitCompletedBackward. Time taken : " + (System.currentTimeMillis() - j2));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$doInBackground$3(Stack stack, long j2, SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, Bitmap bitmap, int i2, int i7) {
        Log.d(TAG, "onVideoFrameForwardBackward. bitmap width(" + bitmap.getWidth() + "), height(" + bitmap.getHeight() + "), requestedTimeMs(" + i2 + "), frameTimeMs(" + i7 + ")");
        stack.push(bitmap);
        StringBuilder sb2 = new StringBuilder("onVideoFrameForwardBackward. Time taken for decode until this frame : ");
        sb2.append(System.currentTimeMillis() - j2);
        Log.d(TAG, sb2.toString());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$doInBackground$4(ArrayList arrayList, Size size, long j2, SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder) {
        Log.d(TAG, "onInitCompletingBackward1");
        try {
            startSemVideoDecoder(semAsyncVideoFrameDecoder, arrayList, size);
        } catch (IllegalStateException unused) {
            Log.d(TAG, "VFD onInitCompletingBackward1 IllegalStateException");
        }
        Log.d(TAG, "onInitCompletedBackward1. Time taken : " + (System.currentTimeMillis() - j2));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$doInBackground$5(Stack stack, long j2, SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, Bitmap bitmap, int i2, int i7) {
        Log.d(TAG, "onVideoFrameForwardBackward1. bitmap width(" + bitmap.getWidth() + "), height(" + bitmap.getHeight() + "), requestedTimeMs(" + i2 + "), frameTimeMs(" + i7 + ")");
        stack.push(bitmap);
        StringBuilder sb2 = new StringBuilder("onVideoFrameForwardBackward1. Time taken for decode until this frame : ");
        sb2.append(System.currentTimeMillis() - j2);
        Log.d(TAG, sb2.toString());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$doInBackground$6(CountDownLatch countDownLatch, SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, int i2) {
        Log.d(TAG, "onDecodingCompletionBackward1. decoded frame count(" + i2 + ")");
        countDownLatch.countDown();
        try {
            semAsyncVideoFrameDecoder.reset();
        } catch (IllegalStateException unused) {
            Log.d(TAG, "VFD onDecodingCompletionBackward1 IllegalStateException");
        }
        semAsyncVideoFrameDecoder.release();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$doInBackground$7(CountDownLatch countDownLatch, ArrayList arrayList, CountDownLatch countDownLatch2, Size size, long j2, Stack stack, File file, boolean z, MotionPhotoVideoUtils.VideoInfo videoInfo, SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, int i2) {
        SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder2 = semAsyncVideoFrameDecoder;
        Log.d(TAG, "onDecodingCompletionBackward. decoded frame count(" + i2 + ")");
        countDownLatch.countDown();
        try {
            semAsyncVideoFrameDecoder2.reset();
            if (arrayList.isEmpty()) {
                countDownLatch2.countDown();
                return;
            }
            System.currentTimeMillis();
            long j3 = j2;
            semAsyncVideoFrameDecoder2.setOnInitCompleteListener(new g(this, arrayList, size, j3));
            semAsyncVideoFrameDecoder2.setOnVideoFrameListener(new h(stack, j3));
            semAsyncVideoFrameDecoder2.setOnDecodingCompleteListener(new i(countDownLatch2));
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                FileDescriptor fd2 = fileInputStream.getFD();
                Log.d(TAG, "onInitStartingBackward1");
                Log.d(TAG, "FD 3" + fd2.toString() + ", is valid - " + fd2.valid());
                if (z) {
                    try {
                        semAsyncVideoFrameDecoder2.init(fd2);
                    } catch (IllegalStateException unused) {
                        Log.d(TAG, "VFD onInitStartingBackward1 IllegalStateException");
                    }
                } else {
                    semAsyncVideoFrameDecoder2.init(fd2, videoInfo.getVideoOffset(), videoInfo.getVideoLength());
                }
                Log.d(TAG, "onInitStartedBackward1");
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IllegalStateException unused2) {
            Log.d(TAG, "VFD onDecodingCompletionBackward IllegalStateException");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$doInBackground$8(int[] iArr, ArrayList arrayList, CountDownLatch countDownLatch, CountDownLatch countDownLatch2, Size size, long j2, Stack stack, ArrayList arrayList2, Stack stack2, File file, boolean z, MotionPhotoVideoUtils.VideoInfo videoInfo, SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, int i2) {
        SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder2 = semAsyncVideoFrameDecoder;
        int i7 = i2;
        Log.d(TAG, "onDecodingCompletionForward. decoded frame count(" + i7 + ")");
        iArr[0] = i7;
        try {
            semAsyncVideoFrameDecoder2.reset();
            if (arrayList.isEmpty()) {
                countDownLatch.countDown();
                countDownLatch2.countDown();
                return;
            }
            System.currentTimeMillis();
            long j3 = j2;
            semAsyncVideoFrameDecoder2.setOnInitCompleteListener(new a(this, arrayList, size, j3));
            semAsyncVideoFrameDecoder2.setOnVideoFrameListener(new b(stack, j3));
            long j8 = j3;
            ArrayList arrayList3 = arrayList2;
            semAsyncVideoFrameDecoder2.setOnDecodingCompleteListener(new c(this, countDownLatch, arrayList3, countDownLatch2, size, j8, stack2, file, z, videoInfo));
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                FileDescriptor fd2 = fileInputStream.getFD();
                Log.d(TAG, "onInitStartingBackward");
                Log.d(TAG, "FD 2" + fd2.toString() + ", is valid - " + fd2.valid());
                if (z) {
                    try {
                        semAsyncVideoFrameDecoder2.init(fd2);
                    } catch (IllegalStateException unused) {
                        Log.d(TAG, "VFD onInitStartingBackward IllegalStateException");
                    }
                } else {
                    semAsyncVideoFrameDecoder2.init(fd2, videoInfo.getVideoOffset(), videoInfo.getVideoLength());
                }
                Log.d(TAG, "onInitStartedBackward");
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IllegalStateException unused2) {
            Log.d(TAG, "VFD onDecodingCompletionForward IllegalStateException");
        }
    }

    private void startSemVideoDecoder(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, ArrayList<Integer> arrayList, Size size) {
        semAsyncVideoFrameDecoder.setTargetFrameTimeList(arrayList);
        semAsyncVideoFrameDecoder.setSeekOption(3);
        semAsyncVideoFrameDecoder.setPreferredCodec(1);
        semAsyncVideoFrameDecoder.setOutputImageSize(size.getWidth(), size.getHeight(), true);
        semAsyncVideoFrameDecoder.start();
    }

    private void stopDecoder(SemAsyncVideoFrameDecoder semAsyncVideoFrameDecoder, String str) {
        Log.w(TAG, str);
        if (semAsyncVideoFrameDecoder != null) {
            try {
                Log.w(TAG, str + "decoder stop called");
                semAsyncVideoFrameDecoder.stop();
            } catch (IllegalStateException unused) {
                Log.d(TAG, "Video decode not running.");
            }
            semAsyncVideoFrameDecoder.release();
        }
        DVSegmenter.cancellationFinished.countDown();
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x04c8  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x04d0  */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x0477 A[EDGE_INSN: B:261:0x0477->B:105:0x0477 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x042c  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x044e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Void doInBackground(java.lang.String... r41) {
        /*
            r40 = this;
            r1 = r40
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "DVSGenerateGIF URL : "
            r0.<init>(r2)
            android.net.Uri r2 = r1.mUri
            r0.append(r2)
            java.lang.String r2 = " touchX: "
            r0.append(r2)
            float r2 = r1.mTouchX
            r0.append(r2)
            java.lang.String r2 = " touchY :"
            r0.append(r2)
            float r2 = r1.mTouchY
            r0.append(r2)
            java.lang.String r2 = " currentFrameIndex : "
            r0.append(r2)
            int r2 = r1.mCurrentIndex
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r15 = "DVS_SegmentAyncTask"
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "DVSGenerateGIF screenWidth : "
            r0.<init>(r2)
            srib.vizinsight.dvs.DVSGIFConfig r2 = r1.mDvsGifConfig
            int r2 = r2.getScreenWidth()
            r0.append(r2)
            java.lang.String r2 = " screenHeight : "
            r0.append(r2)
            srib.vizinsight.dvs.DVSGIFConfig r2 = r1.mDvsGifConfig
            int r2 = r2.getScreenHeight()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            long r16 = java.lang.System.currentTimeMillis()
            srib.vizinsight.dvs.DVSConfig r0 = new srib.vizinsight.dvs.DVSConfig
            r0.<init>()
            srib.vizinsight.dvs.DVS r6 = srib.vizinsight.dvs.DVSegmenter.getSegmenter(r0)
            boolean r0 = r1.isCancelled()
            r18 = 0
            if (r0 == 0) goto L_0x0077
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, not starting task."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x0077:
            srib.vizinsight.dvs.DVSGIFConfig r0 = r1.mDvsGifConfig
            int r0 = r0.getMax_resolution()
            r6.DVSSetMaxResolution(r0)
            r6.reset_frame_counter()
            android.content.Context r0 = r1.mContext     // Catch:{ URISyntaxException -> 0x0989 }
            android.net.Uri r2 = r1.mUri     // Catch:{ URISyntaxException -> 0x0989 }
            java.lang.String r0 = srib.vizinsight.dvs.Utils.getFilePath(r0, r2)     // Catch:{ URISyntaxException -> 0x0989 }
            if (r0 == 0) goto L_0x0988
            boolean r2 = r0.isEmpty()     // Catch:{ URISyntaxException -> 0x0989 }
            if (r2 == 0) goto L_0x0095
            goto L_0x0988
        L_0x0095:
            r2 = 46
            int r2 = r0.lastIndexOf(r2)
            r7 = 1
            if (r2 <= 0) goto L_0x00a4
            int r2 = r2 + r7
            java.lang.String r2 = r0.substring(r2)
            goto L_0x00a6
        L_0x00a4:
            java.lang.String r2 = ""
        L_0x00a6:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "File Extension is : "
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r15, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "DVSGenerateGIF : filePath : "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r4 = "URI Parse : "
            r3.append(r4)
            android.net.Uri r4 = android.net.Uri.parse(r0)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r15, r3)
            java.io.File r12 = new java.io.File
            r12.<init>(r0)
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0981 }
            r3.<init>(r12)     // Catch:{ IOException -> 0x0981 }
            java.io.FileDescriptor r4 = r3.getFD()     // Catch:{ IOException -> 0x0981 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r8 = "FD 0"
            r5.<init>(r8)
            java.lang.String r8 = r4.toString()
            r5.append(r8)
            java.lang.String r8 = ", is valid - "
            r5.append(r8)
            boolean r9 = r4.valid()
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r15, r5)
            android.media.MediaMetadataRetriever r5 = new android.media.MediaMetadataRetriever
            r5.<init>()
            android.media.MediaExtractor r9 = new android.media.MediaExtractor
            r9.<init>()
            java.lang.String r10 = "mov"
            boolean r10 = r2.equalsIgnoreCase(r10)
            r11 = 0
            if (r10 != 0) goto L_0x0121
            java.lang.String r10 = "mp4"
            boolean r2 = r2.equalsIgnoreCase(r10)
            if (r2 == 0) goto L_0x011f
            goto L_0x0121
        L_0x011f:
            r13 = r11
            goto L_0x0122
        L_0x0121:
            r13 = r7
        L_0x0122:
            r25 = 1000(0x3e8, double:4.94E-321)
            java.lang.String r2 = "durationUs"
            if (r13 == 0) goto L_0x014a
            r5.setDataSource(r4)
            r9.setDataSource(r4)     // Catch:{ IOException -> 0x0143 }
            android.media.MediaFormat r0 = r9.getTrackFormat(r11)     // Catch:{ IOException -> 0x0143 }
            long r9 = r0.getLong(r2)     // Catch:{ IOException -> 0x0143 }
            long r9 = r9 / r25
            int r0 = (int) r9
            r19 = r7
            r14 = r18
            r7 = r0
            r0 = r5
        L_0x013f:
            r20 = r3
            goto L_0x021b
        L_0x0143:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x014a:
            com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils$VideoInfo r10 = com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils.getVideoDataPosition((java.io.File) r12)
            java.lang.String r14 = java.lang.String.valueOf(r10)
            java.lang.String r11 = "VideoInfo object : "
            java.lang.String r11 = r11.concat(r14)
            android.util.Log.d(r15, r11)
            if (r10 != 0) goto L_0x015f
            goto L_0x0988
        L_0x015f:
            java.io.File r11 = new java.io.File     // Catch:{ Exception -> 0x0170 }
            r11.<init>(r0)     // Catch:{ Exception -> 0x0170 }
            com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils$MotionPhotoInfo r0 = srib.vizinsight.dvs.Utils.getSEFDataPosition(r11)     // Catch:{ Exception -> 0x0170 }
            if (r0 != 0) goto L_0x0174
            java.lang.String r0 = "Motion photo info null.Invalid MP"
            android.util.Log.e(r15, r0)     // Catch:{ Exception -> 0x0170 }
            return r18
        L_0x0170:
            r0 = move-exception
            r7 = r15
            goto L_0x0976
        L_0x0174:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r11 = "isMotionPhotoV2: "
            r0.<init>(r11)
            int r11 = r10.isMotionPhotoV2()
            java.lang.String r11 = java.lang.String.valueOf(r11)
            r0.append(r11)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            long r19 = r10.getVideoLength()
            java.lang.String r0 = java.lang.String.valueOf(r19)
            android.util.Log.d(r15, r0)
            long r19 = r10.getVideoOffset()
            java.lang.String r0 = java.lang.String.valueOf(r19)
            android.util.Log.d(r15, r0)
            long r21 = r10.getVideoOffset()     // Catch:{ Exception -> 0x0208 }
            long r23 = r10.getVideoLength()     // Catch:{ Exception -> 0x0208 }
            r20 = r4
            r19 = r5
            r19.setDataSource(r20, r21, r23)     // Catch:{ Exception -> 0x0208 }
            r0 = r19
            java.lang.String r4 = "data source set for retriever"
            android.util.Log.d(r15, r4)     // Catch:{ Exception -> 0x0208 }
            long r21 = r10.getVideoOffset()     // Catch:{ Exception -> 0x0208 }
            long r23 = r10.getVideoLength()     // Catch:{ Exception -> 0x0208 }
            r19 = r9
            r19.setDataSource(r20, r21, r23)     // Catch:{ Exception -> 0x0208 }
            r4 = r19
            java.lang.String r5 = "data source set for extractor"
            android.util.Log.d(r15, r5)     // Catch:{ Exception -> 0x0208 }
            int r5 = r4.getTrackCount()     // Catch:{ Exception -> 0x0208 }
            r9 = 0
            r11 = 0
        L_0x01d3:
            if (r11 >= r5) goto L_0x0215
            android.media.MediaFormat r14 = r4.getTrackFormat(r11)     // Catch:{ Exception -> 0x0208 }
            r19 = r7
            java.lang.String r7 = "mime"
            java.lang.String r7 = r14.getString(r7)     // Catch:{ Exception -> 0x0208 }
            r20 = r3
            java.lang.String r3 = "video/"
            boolean r3 = r7.startsWith(r3)     // Catch:{ Exception -> 0x0208 }
            if (r3 == 0) goto L_0x020c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0208 }
            r3.<init>()     // Catch:{ Exception -> 0x0208 }
            java.lang.String r7 = "Track format: "
            r3.append(r7)     // Catch:{ Exception -> 0x0208 }
            r3.append(r11)     // Catch:{ Exception -> 0x0208 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0208 }
            android.util.Log.d(r15, r3)     // Catch:{ Exception -> 0x0208 }
            long r21 = r14.getLong(r2)     // Catch:{ Exception -> 0x0208 }
            r7 = r2
            long r2 = r21 / r25
            int r9 = (int) r2
            goto L_0x020d
        L_0x0208:
            r0 = move-exception
            r7 = r15
            goto L_0x0964
        L_0x020c:
            r7 = r2
        L_0x020d:
            int r11 = r11 + 1
            r2 = r7
            r7 = r19
            r3 = r20
            goto L_0x01d3
        L_0x0215:
            r19 = r7
            r7 = r9
            r14 = r10
            goto L_0x013f
        L_0x021b:
            r2 = 32
            java.lang.String r2 = r0.extractMetadata(r2)     // Catch:{ NullPointerException -> 0x094f }
            java.util.Objects.requireNonNull(r2)     // Catch:{ NullPointerException -> 0x094f }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NullPointerException -> 0x094f }
            r9 = 9
            java.lang.String r3 = r0.extractMetadata(r9)     // Catch:{ NullPointerException -> 0x094f }
            java.util.Objects.requireNonNull(r3)     // Catch:{ NullPointerException -> 0x094f }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NullPointerException -> 0x094f }
            r4 = 19
            java.lang.String r4 = r0.extractMetadata(r4)     // Catch:{ NullPointerException -> 0x094f }
            java.util.Objects.requireNonNull(r4)     // Catch:{ NullPointerException -> 0x094f }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ NullPointerException -> 0x094f }
            r5 = 18
            java.lang.String r5 = r0.extractMetadata(r5)     // Catch:{ NullPointerException -> 0x094f }
            java.util.Objects.requireNonNull(r5)     // Catch:{ NullPointerException -> 0x094f }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ NullPointerException -> 0x094f }
            r10 = 24
            java.lang.String r10 = r0.extractMetadata(r10)     // Catch:{ NullPointerException -> 0x094f }
            java.util.Objects.requireNonNull(r10)     // Catch:{ NullPointerException -> 0x094f }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ NullPointerException -> 0x094f }
            float r11 = (float) r5
            float r9 = (float) r4
            r22 = r0
            srib.vizinsight.dvs.DVSGIFConfig r0 = r1.mDvsGifConfig
            int r0 = r0.getMax_resolution()
            android.util.Size r0 = srib.vizinsight.dvs.Utils.getLowerResolution(r11, r9, r10, r0)
            r22.release()     // Catch:{ IOException -> 0x0948 }
            r20.close()     // Catch:{ IOException -> 0x0948 }
            r11 = r8
            double r8 = (double) r2
            r22 = r8
            double r8 = (double) r7
            r24 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r8 = r8 / r24
            double r8 = r22 / r8
            int r8 = (int) r8
            srib.vizinsight.dvs.DVSGIFConfig r9 = r1.mDvsGifConfig
            int r9 = r9.getFps()
            r20 = r6
            r6 = -1
            r24 = r11
            r11 = 3
            if (r9 != r6) goto L_0x028f
        L_0x028d:
            r9 = 0
            goto L_0x029a
        L_0x028f:
            srib.vizinsight.dvs.DVSGIFConfig r9 = r1.mDvsGifConfig
            int r9 = r9.getFps()
            int r9 = r9 * r11
            if (r2 <= r9) goto L_0x028d
            r9 = r19
        L_0x029a:
            if (r9 == 0) goto L_0x02b2
            r25 = r6
            float r6 = (float) r2
            r26 = r11
            srib.vizinsight.dvs.DVSGIFConfig r11 = r1.mDvsGifConfig
            int r11 = r11.getFps()
            int r11 = r11 * 3
            int r11 = r2 - r11
            float r11 = (float) r11
            float r6 = r6 / r11
            int r6 = java.lang.Math.round(r6)
            goto L_0x02b6
        L_0x02b2:
            r25 = r6
            r26 = r11
        L_0x02b6:
            if (r9 == 0) goto L_0x02c9
            float r11 = (float) r2
            r27 = r11
            int r11 = r6 + -1
            float r11 = (float) r11
            r28 = r11
            float r11 = (float) r6
            float r11 = r28 / r11
            float r11 = r11 * r27
            int r11 = (int) r11
        L_0x02c6:
            r27 = r12
            goto L_0x02cb
        L_0x02c9:
            r11 = r2
            goto L_0x02c6
        L_0x02cb:
            int r12 = r1.mCurrentIndex
            int r12 = srib.vizinsight.dvs.Utils.getIndexFromTime(r12, r2, r7)
            r1.mCurrentIndex = r12
            r28 = 4617315517961601024(0x4014000000000000, double:5.0)
            double r22 = r22 / r28
            r28 = 4616189618054758400(0x4010000000000000, double:4.0)
            r30 = r13
            r31 = r14
            double r13 = r22 * r28
            int r13 = (int) r13
            if (r12 < r13) goto L_0x02e5
            r12 = r19
            goto L_0x02e6
        L_0x02e5:
            r12 = 0
        L_0x02e6:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r22 = r0
            java.lang.String r0 = "DECODE-CONFIG : skip = "
            r14.<init>(r0)
            r14.append(r9)
            java.lang.String r0 = r14.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r14 = "DECODE-CONFIG : skip multiple = "
            r0.<init>(r14)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r14 = "DECODE-CONFIG : frames to process = "
            r0.<init>(r14)
            r0.append(r11)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r14 = "DECODE-CONFIG : selected index = "
            r0.<init>(r14)
            int r14 = r1.mCurrentIndex
            r0.append(r14)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r14 = "DECODE-CONFIG : number of frames = "
            r0.<init>(r14)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r14 = "DECODE-CONFIG : split index = "
            r0.<init>(r14)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r14 = "DECODE-CONFIG : using second stack = "
            r0.<init>(r14)
            r0.append(r12)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r14 = "DECODE-CONFIG : duration from retriever = "
            r0.<init>(r14)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "DECODE-CONFIG : duration from extractor = "
            r0.<init>(r3)
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "DECODE-CONFIG : frame rate = "
            r0.<init>(r3)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "DECODE-CONFIG : height = "
            r0.<init>(r3)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "DECODE-CONFIG : width = "
            r0.<init>(r3)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "DECODE-CONFIG : orientation = "
            r0.<init>(r3)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "DECODE-CONFIG : smaller size = "
            r0.<init>(r3)
            r3 = r22
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            r0 = 90
            if (r2 > r0) goto L_0x03e1
            r0 = r2
        L_0x03df:
            r5 = 0
            goto L_0x03f6
        L_0x03e1:
            int r4 = r1.mCurrentIndex
            int r5 = r4 + -45
            int r10 = r4 + 45
            if (r5 >= 0) goto L_0x03ea
            goto L_0x03df
        L_0x03ea:
            if (r10 <= r2) goto L_0x03f3
            int r5 = r2 - r4
            int r0 = r0 - r5
            int r5 = r4 - r0
            r0 = r2
            goto L_0x03f6
        L_0x03f3:
            int r0 = r4 - r5
            int r0 = r0 + r4
        L_0x03f6:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r10 = "leftIndex - "
            r4.<init>(r10)
            java.lang.String r10 = java.lang.String.valueOf(r5)
            r4.append(r10)
            java.lang.String r10 = ", rightIndex - "
            r4.append(r10)
            java.lang.String r10 = java.lang.String.valueOf(r0)
            r4.append(r10)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r15, r4)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            r22 = r3
            int r3 = r1.mCurrentIndex
        L_0x042a:
            if (r3 >= r0) goto L_0x044a
            r23 = r0
            if (r9 == 0) goto L_0x043a
            int r0 = r1.mCurrentIndex
            int r0 = r3 - r0
            int r0 = r0 + 1
            int r0 = r0 % r6
            if (r0 != 0) goto L_0x043a
            goto L_0x0445
        L_0x043a:
            int r0 = srib.vizinsight.dvs.Utils.getTimeFromIndex(r3, r2, r7, r8)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r4.add(r0)
        L_0x0445:
            int r3 = r3 + 1
            r0 = r23
            goto L_0x042a
        L_0x044a:
            int r0 = r1.mCurrentIndex
            if (r5 >= r0) goto L_0x0477
            if (r9 == 0) goto L_0x0458
            int r0 = r5 - r0
            int r0 = r0 + 1
            int r0 = r0 % r6
            if (r0 != 0) goto L_0x0458
            goto L_0x0474
        L_0x0458:
            if (r12 == 0) goto L_0x0469
            if (r5 < r13) goto L_0x045d
            goto L_0x0469
        L_0x045d:
            int r0 = srib.vizinsight.dvs.Utils.getTimeFromIndex(r5, r2, r7, r8)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r14.add(r0)
            goto L_0x0474
        L_0x0469:
            int r0 = srib.vizinsight.dvs.Utils.getTimeFromIndex(r5, r2, r7, r8)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r10.add(r0)
        L_0x0474:
            int r5 = r5 + 1
            goto L_0x044a
        L_0x0477:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "DECODE-CONFIG : forward list size - "
            r0.<init>(r2)
            int r2 = r4.size()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "DECODE-CONFIG : first backward list size - "
            r0.<init>(r2)
            int r2 = r10.size()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "DECODE-CONFIG : second backward list size - "
            r0.<init>(r2)
            int r2 = r14.size()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            boolean r0 = r1.isCancelled()
            if (r0 == 0) goto L_0x04d0
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, not starting selected frame decoding."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x04d0:
            r2 = r4
            long r4 = java.lang.System.currentTimeMillis()
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = new com.samsung.android.media.SemAsyncVideoFrameDecoder
            r0.<init>()
            r1.vfd = r0
            int[] r6 = new int[]{r25}
            java.util.concurrent.CountDownLatch r8 = new java.util.concurrent.CountDownLatch
            r9 = r19
            r8.<init>(r9)
            java.util.concurrent.CountDownLatch r12 = new java.util.concurrent.CountDownLatch
            r12.<init>(r9)
            java.util.concurrent.LinkedBlockingDeque r13 = new java.util.concurrent.LinkedBlockingDeque
            r13.<init>()
            java.util.Stack r9 = new java.util.Stack
            r9.<init>()
            r23 = r11
            java.util.Stack r11 = new java.util.Stack
            r11.<init>()
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            r3 = r0
            srib.vizinsight.dvs.d r0 = new srib.vizinsight.dvs.d
            r28 = r6
            r6 = r3
            r3 = r22
            r0.<init>(r1, r2, r3, r4)
            r6.setOnInitCompleteListener(r0)
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            srib.vizinsight.dvs.e r2 = new srib.vizinsight.dvs.e
            r2.<init>(r13, r4)
            r0.setOnVideoFrameListener(r2)
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            r2 = r0
            srib.vizinsight.dvs.f r0 = new srib.vizinsight.dvs.f
            r6 = r3
            r33 = r7
            r3 = r10
            r35 = r13
            r10 = r14
            r41 = r15
            r32 = r20
            r34 = r23
            r36 = r24
            r13 = r30
            r14 = r31
            r21 = 9
            r15 = r2
            r2 = r28
            r38 = r4
            r4 = r8
            r7 = r38
            r5 = r12
            r12 = r27
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r9, r10, r11, r12, r13, r14)
            r15.setOnDecodingCompleteListener(r0)
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0941 }
            r0.<init>(r12)     // Catch:{ IOException -> 0x0941 }
            java.io.FileDescriptor r3 = r0.getFD()     // Catch:{ IOException -> 0x0941 }
            java.lang.String r6 = "onInitStartingForward"
            r7 = r41
            android.util.Log.d(r7, r6)     // Catch:{ IOException -> 0x0941 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0941 }
            r6.<init>()     // Catch:{ IOException -> 0x0941 }
            java.lang.String r8 = "FD 1"
            r6.append(r8)     // Catch:{ IOException -> 0x0941 }
            java.lang.String r8 = r3.toString()     // Catch:{ IOException -> 0x0941 }
            r6.append(r8)     // Catch:{ IOException -> 0x0941 }
            r8 = r36
            r6.append(r8)     // Catch:{ IOException -> 0x0941 }
            boolean r8 = r3.valid()     // Catch:{ IOException -> 0x0941 }
            r6.append(r8)     // Catch:{ IOException -> 0x0941 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0941 }
            android.util.Log.d(r7, r6)     // Catch:{ IOException -> 0x0941 }
            if (r30 == 0) goto L_0x057e
            com.samsung.android.media.SemAsyncVideoFrameDecoder r6 = r1.vfd     // Catch:{ IllegalStateException -> 0x0590 }
            r6.init(r3)     // Catch:{ IllegalStateException -> 0x0590 }
            goto L_0x0595
        L_0x057e:
            com.samsung.android.media.SemAsyncVideoFrameDecoder r6 = r1.vfd     // Catch:{ IllegalStateException -> 0x0590 }
            long r24 = r14.getVideoOffset()     // Catch:{ IllegalStateException -> 0x0590 }
            long r26 = r14.getVideoLength()     // Catch:{ IllegalStateException -> 0x0590 }
            r23 = r3
            r22 = r6
            r22.init(r23, r24, r26)     // Catch:{ IllegalStateException -> 0x0590 }
            goto L_0x0595
        L_0x0590:
            java.lang.String r3 = "VFD onInitStartingForward IllegalStateException"
            android.util.Log.d(r7, r3)     // Catch:{ IOException -> 0x0941 }
        L_0x0595:
            java.lang.String r3 = "onInitStartedForward"
            android.util.Log.d(r7, r3)     // Catch:{ IOException -> 0x0941 }
            r0.close()     // Catch:{ IOException -> 0x0941 }
            boolean r3 = r1.isCancelled()
            if (r3 == 0) goto L_0x05ab
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, not starting forward loop."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x05ab:
            r3 = 0
            r6 = 0
            r8 = 0
        L_0x05ae:
            r10 = r2[r8]
            r12 = 1
            java.lang.String r14 = "Too many missing frames in a row. Stopping."
            r15 = -1
            if (r10 == r15) goto L_0x05b9
            if (r3 >= r10) goto L_0x05bb
        L_0x05b9:
            r10 = 3
            goto L_0x05be
        L_0x05bb:
            r15 = r34
            goto L_0x0622
        L_0x05be:
            if (r6 <= r10) goto L_0x05c4
            android.util.Log.d(r7, r14)
            goto L_0x05bb
        L_0x05c4:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r10 = "Running forward loop for index - "
            r15.<init>(r10)
            java.lang.String r10 = java.lang.String.valueOf(r3)
            r15.append(r10)
            java.lang.String r10 = r15.toString()
            android.util.Log.d(r7, r10)
            boolean r10 = r1.isCancelled()
            if (r10 == 0) goto L_0x05e7
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, breaking forward loop before decode."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x05e7:
            int r10 = r3 * 100
            float r10 = (float) r10
            r15 = r34
            float r8 = (float) r15
            float r10 = r10 / r8
            int r8 = (int) r10
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            java.lang.Integer[] r8 = new java.lang.Integer[]{r8}
            r1.publishProgress(r8)
            long r19 = java.lang.System.currentTimeMillis()
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x093a }
            r10 = r35
            java.lang.Object r8 = r10.poll(r12, r8)     // Catch:{ InterruptedException -> 0x093a }
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8     // Catch:{ InterruptedException -> 0x093a }
            if (r8 != 0) goto L_0x08b7
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x093a }
            r2.<init>()     // Catch:{ InterruptedException -> 0x093a }
            java.lang.String r6 = "Video frame forward decoding timeout for index : "
            r2.append(r6)     // Catch:{ InterruptedException -> 0x093a }
            java.lang.String r6 = java.lang.String.valueOf(r3)     // Catch:{ InterruptedException -> 0x093a }
            r2.append(r6)     // Catch:{ InterruptedException -> 0x093a }
            java.lang.String r2 = r2.toString()     // Catch:{ InterruptedException -> 0x093a }
            android.util.Log.d(r7, r2)     // Catch:{ InterruptedException -> 0x093a }
        L_0x0622:
            boolean r2 = r1.isCancelled()
            if (r2 == 0) goto L_0x0630
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, not waiting for stack to fill."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x0630:
            long r19 = java.lang.System.currentTimeMillis()
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x08b0 }
            r12 = 2
            r4.await(r12, r2)     // Catch:{ InterruptedException -> 0x08b0 }
            r12 = r32
            r8 = 0
            r12.reset_frame_counter(r8)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "Backward stack wait time : "
            r2.<init>(r4)
            long r24 = java.lang.System.currentTimeMillis()
            r13 = r3
            long r3 = r24 - r19
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r7, r2)
            boolean r2 = r1.isCancelled()
            if (r2 == 0) goto L_0x0667
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, not starting backward loop."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x0667:
            r3 = r13
            r2 = 0
        L_0x0669:
            boolean r4 = r9.empty()
            if (r4 != 0) goto L_0x0675
            r10 = 3
            if (r2 <= r10) goto L_0x067c
            android.util.Log.d(r7, r14)
        L_0x0675:
            r24 = r0
            r10 = r2
            r8 = r3
            r13 = 1
            goto L_0x0722
        L_0x067c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "Running backward loop for index - "
            r4.<init>(r6)
            java.lang.String r6 = java.lang.String.valueOf(r3)
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r7, r4)
            boolean r4 = r1.isCancelled()
            if (r4 == 0) goto L_0x069f
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, breaking backward loop before decode."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x069f:
            int r4 = r3 * 100
            float r4 = (float) r4
            float r6 = (float) r15
            float r4 = r4 / r6
            int r4 = (int) r4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Integer[] r4 = new java.lang.Integer[]{r4}
            r1.publishProgress(r4)
            long r19 = java.lang.System.currentTimeMillis()
            java.lang.Object r4 = r9.pop()
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r8 = "Frame to Bitmap backward decode time : "
            r6.<init>(r8)
            long r24 = java.lang.System.currentTimeMillis()
            r10 = r2
            r8 = r3
            long r2 = r24 - r19
            r6.append(r2)
            java.lang.String r2 = r6.toString()
            android.util.Log.d(r7, r2)
            boolean r2 = r1.isCancelled()
            if (r2 == 0) goto L_0x06e1
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, breaking backward loop before segmentation."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x06e1:
            long r2 = java.lang.System.currentTimeMillis()
            float r6 = r1.mTouchX
            int r13 = r4.getWidth()
            float r13 = (float) r13
            float r6 = r6 * r13
            int r6 = (int) r6
            float r13 = r1.mTouchY
            r24 = r0
            int r0 = r4.getHeight()
            float r0 = (float) r0
            float r13 = r13 * r0
            int r0 = (int) r13
            r13 = 1
            boolean r0 = r12.DVSGetSegment(r4, r6, r0, r13)
            if (r0 == 0) goto L_0x0702
            r0 = 0
            goto L_0x0704
        L_0x0702:
            int r0 = r10 + 1
        L_0x0704:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "Frame backward Segment time : "
            r4.<init>(r6)
            long r19 = java.lang.System.currentTimeMillis()
            long r2 = r19 - r2
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            android.util.Log.d(r7, r2)
            int r3 = r8 + 1
            r2 = r0
            r0 = r24
            goto L_0x0669
        L_0x0722:
            boolean r0 = r1.isCancelled()
            if (r0 == 0) goto L_0x0730
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, not waiting for stack 2 to fill."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x0730:
            long r2 = java.lang.System.currentTimeMillis()
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x08a9 }
            r27 = r14
            r13 = 1
            r5.await(r13, r0)     // Catch:{ InterruptedException -> 0x08a9 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r4 = "Backward stack 2 wait time : "
            r0.<init>(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r2
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r7, r0)
            boolean r0 = r1.isCancelled()
            if (r0 == 0) goto L_0x0761
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, not starting backward 2 loop."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x0761:
            r3 = r8
            r2 = r10
        L_0x0763:
            boolean r0 = r11.empty()
            if (r0 != 0) goto L_0x0814
            r0 = 3
            if (r2 <= r0) goto L_0x0773
            r4 = r27
            android.util.Log.d(r7, r4)
            goto L_0x0814
        L_0x0773:
            r4 = r27
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Running backward 2 loop for index - "
            r5.<init>(r6)
            java.lang.String r6 = java.lang.String.valueOf(r3)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r7, r5)
            boolean r5 = r1.isCancelled()
            if (r5 == 0) goto L_0x0798
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, breaking backward 2 loop before decode."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x0798:
            int r5 = r3 * 100
            float r5 = (float) r5
            float r6 = (float) r15
            float r5 = r5 / r6
            int r5 = (int) r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Integer[] r5 = new java.lang.Integer[]{r5}
            r1.publishProgress(r5)
            long r5 = java.lang.System.currentTimeMillis()
            java.lang.Object r8 = r11.pop()
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Frame to Bitmap backward 2 decode time : "
            r9.<init>(r10)
            long r13 = java.lang.System.currentTimeMillis()
            long r13 = r13 - r5
            r9.append(r13)
            java.lang.String r5 = r9.toString()
            android.util.Log.d(r7, r5)
            boolean r5 = r1.isCancelled()
            if (r5 == 0) goto L_0x07d7
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, breaking backward 2 loop before segmentation."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x07d7:
            long r5 = java.lang.System.currentTimeMillis()
            float r9 = r1.mTouchX
            int r10 = r8.getWidth()
            float r10 = (float) r10
            float r9 = r9 * r10
            int r9 = (int) r9
            float r10 = r1.mTouchY
            int r13 = r8.getHeight()
            float r13 = (float) r13
            float r10 = r10 * r13
            int r10 = (int) r10
            r13 = 1
            boolean r8 = r12.DVSGetSegment(r8, r9, r10, r13)
            if (r8 == 0) goto L_0x07f6
            r2 = 0
            goto L_0x07f8
        L_0x07f6:
            int r2 = r2 + 1
        L_0x07f8:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Frame backward 2 Segment time : "
            r8.<init>(r9)
            long r9 = java.lang.System.currentTimeMillis()
            long r9 = r9 - r5
            r8.append(r9)
            java.lang.String r5 = r8.toString()
            android.util.Log.d(r7, r5)
            int r3 = r3 + 1
            r27 = r4
            goto L_0x0763
        L_0x0814:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Number of total frames processed - "
            r0.<init>(r2)
            java.lang.String r2 = java.lang.String.valueOf(r3)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r7, r0)
            r24.close()     // Catch:{ IOException -> 0x08a2 }
            long r4 = java.lang.System.currentTimeMillis()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = " Segment processing time : "
            r0.<init>(r2)
            long r4 = r4 - r16
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r7, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "doInBackground : mResultPath : "
            r0.<init>(r2)
            java.lang.String r2 = r1.mResultPath
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r7, r0)
            long r4 = java.lang.System.currentTimeMillis()
            r13 = 1
            if (r3 <= r13) goto L_0x086b
            r14 = r33
            float r0 = (float) r14
            int r3 = r3 - r13
            float r2 = (float) r3
            r3 = 1092616192(0x41200000, float:10.0)
            float r2 = r2 * r3
            float r0 = r0 / r2
            int r9 = java.lang.Math.round(r0)
            goto L_0x086d
        L_0x086b:
            r9 = r21
        L_0x086d:
            java.lang.String r0 = "CSPF : "
            c0.C0086a.C(r9, r0, r7)
            java.lang.String r0 = r1.mResultPath
            boolean r0 = r12.DVSQuramGenerateGIF(r0, r9)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r1.mResultSaved = r0
            long r2 = java.lang.System.currentTimeMillis()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r6 = " GIF Creation time : "
            r0.<init>(r6)
            long r2 = r2 - r4
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r7, r0)
            boolean r0 = r1.isCancelled()
            if (r0 == 0) goto L_0x0988
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, but GIF is generated."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x08a2:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x08a9:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x08b0:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x08b7:
            r24 = r0
            r13 = r3
            r12 = r32
            r14 = r33
            r0 = 3
            r37 = 1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "Frame to Bitmap forward decode time : "
            r3.<init>(r0)
            long r22 = java.lang.System.currentTimeMillis()
            r0 = r4
            r27 = r5
            long r4 = r22 - r19
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r7, r3)
            boolean r3 = r1.isCancelled()
            if (r3 == 0) goto L_0x08e9
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Task is cancelled, breaking forward loop before segmentation."
            r1.stopDecoder(r0, r2)
            return r18
        L_0x08e9:
            long r3 = java.lang.System.currentTimeMillis()
            float r5 = r1.mTouchX
            r19 = r0
            int r0 = r8.getWidth()
            float r0 = (float) r0
            float r5 = r5 * r0
            int r0 = (int) r5
            float r5 = r1.mTouchY
            r28 = r2
            int r2 = r8.getHeight()
            float r2 = (float) r2
            float r5 = r5 * r2
            int r2 = (int) r5
            r5 = 0
            boolean r0 = r12.DVSGetSegment(r8, r0, r2, r5)
            if (r0 == 0) goto L_0x090c
            r6 = r5
            goto L_0x090e
        L_0x090c:
            int r6 = r6 + 1
        L_0x090e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Frame forward Segment time : "
            r0.<init>(r2)
            long r22 = java.lang.System.currentTimeMillis()
            long r2 = r22 - r3
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r7, r0)
            int r3 = r13 + 1
            r8 = r5
            r35 = r10
            r32 = r12
            r33 = r14
            r34 = r15
            r4 = r19
            r0 = r24
            r5 = r27
            r2 = r28
            goto L_0x05ae
        L_0x093a:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x0941:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x0948:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x094f:
            r0 = move-exception
            com.samsung.android.media.SemAsyncVideoFrameDecoder r2 = r1.vfd
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Error in getting video metadata from MediaMetadataRetriever: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.stopDecoder(r2, r0)
            return r18
        L_0x0964:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Extractor error:"
            r2.<init>(r3)
            N2.j.D(r0, r2, r7)
            com.samsung.android.media.SemAsyncVideoFrameDecoder r0 = r1.vfd
            java.lang.String r2 = "Error in getting video metadata from MediaMetadataRetriever"
            r1.stopDecoder(r0, r2)
            return r18
        L_0x0976:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "MotionPhotoInfo error:"
            r1.<init>(r2)
            N2.j.D(r0, r1, r7)
            return r18
        L_0x0981:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x0988:
            return r18
        L_0x0989:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: srib.vizinsight.dvs.SegmentAsyncTask.doInBackground(java.lang.String[]):java.lang.Void");
    }

    public void onCancelled(Void voidR) {
        super.onCancelled(voidR);
        Log.d(TAG, "onCancelled");
    }

    public void onPostExecute(Void voidR) {
        Log.d(TAG, "onPostExecute");
        super.onPostExecute(voidR);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setGifPath(this.mResultPath);
        resultInfo.setResult(this.mResultSaved.booleanValue());
        this.mListener.onResultFileCreated(resultInfo);
    }

    public void onProgressUpdate(Integer... numArr) {
        this.mListener.onProgressUpdate(numArr[0]);
    }
}
