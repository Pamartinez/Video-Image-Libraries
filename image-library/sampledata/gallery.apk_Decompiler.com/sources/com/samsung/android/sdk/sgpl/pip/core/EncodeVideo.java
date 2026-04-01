package com.samsung.android.sdk.sgpl.pip.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaMuxer;
import android.net.Uri;
import android.opengl.GLES20;
import android.os.Build;
import android.util.Log;
import android.view.Surface;
import c0.C0086a;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import com.samsung.android.sdk.sgpl.pip.renderer.RenderTexture_GL_2d;
import com.samsung.android.sdk.sgpl.pip.surfaces.InputSurface;
import com.samsung.android.sdk.sgpl.pip.surfaces.MainOutputSurface;
import com.samsung.android.sdk.sgpl.pip.surfaces.OutputSurface;
import com.samsung.android.sdk.sgpl.pip.util.CodecsHelper;
import com.samsung.android.sdk.sgpl.pip.util.Constants;
import com.samsung.android.sum.core.message.Message;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EncodeVideo extends Encode {
    private static final int IMAGE_WAIT_TIMEOUT_MS = 1000;
    private static final String KEY_ERROR_TYPE = "error-type";
    private static final long TIMEOUT_USEC = 10000;
    private int frameBufferID;
    private MediaExtractor mAudioExtractor;
    private RenderTexture_GL_2d mBGImageRenderer;
    private Context mContext;
    private boolean mCopyAudio;
    private boolean mCustomTrim = false;
    private volatile boolean mEncoding = false;
    private long mFullPlayTime = 0;
    private int mInputOrientationDegrees = 0;
    private InputSurface mInputSurface;
    private Uri mInputUri;
    private Bitmap mLogoBitmap;
    private int mOutputOrientationDegrees = 0;
    private OutputSurface mOutputSurface;
    private int mRotation = 0;
    private int mRotationOffset;
    private final Object mStopLock = new Object();
    private long mTrimAudioEndUs = 0;
    private long mTrimAudioStartUs = 0;
    private MediaExtractor mVideoExtractor;
    private final PointF mVideoRelatedPos = new PointF();
    private final Point mVideoSize = new Point();
    private final List<Encode.TrimInfo> mVideoTrims = new ArrayList();
    private MainOutputSurface mainOutputSurface;

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean CheckVideoFormat(android.content.Context r4, android.net.Uri r5) {
        /*
            r3 = this;
            r0 = 0
            r1 = 0
            android.media.MediaExtractor r2 = com.samsung.android.sdk.sgpl.pip.util.CodecsHelper.createExtractor(r4, r5)     // Catch:{ IOException -> 0x0035, all -> 0x0032 }
            android.media.MediaMetadataRetriever r1 = com.samsung.android.sdk.sgpl.pip.util.CodecsHelper.createMediaMetadataRetriever(r4, r5)     // Catch:{ IOException -> 0x001e, all -> 0x001a }
            int r4 = com.samsung.android.sdk.sgpl.pip.util.CodecsHelper.getAndSelectVideoTrackIndex(r2)     // Catch:{ IOException -> 0x001e, all -> 0x001a }
            r5 = -1
            if (r4 == r5) goto L_0x0022
            boolean r4 = com.samsung.android.sdk.sgpl.pip.util.CodecsHelper.isSupportedFormat(r1)     // Catch:{ IOException -> 0x001e, all -> 0x001a }
            if (r4 != 0) goto L_0x0018
            goto L_0x0022
        L_0x0018:
            r0 = 1
            goto L_0x0029
        L_0x001a:
            r4 = move-exception
            r5 = r1
            r1 = r2
            goto L_0x0044
        L_0x001e:
            r4 = move-exception
            r5 = r1
            r1 = r2
            goto L_0x0037
        L_0x0022:
            java.lang.String r4 = "PIP"
            java.lang.String r5 = "Video Format is not supported"
            android.util.Log.d(r4, r5)     // Catch:{ IOException -> 0x001e, all -> 0x001a }
        L_0x0029:
            if (r2 == 0) goto L_0x002e
            r2.release()
        L_0x002e:
            r3.release(r1)
            return r0
        L_0x0032:
            r4 = move-exception
            r5 = r1
            goto L_0x0044
        L_0x0035:
            r4 = move-exception
            r5 = r1
        L_0x0037:
            r4.printStackTrace()     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x003f
            r1.release()
        L_0x003f:
            r3.release(r5)
            return r0
        L_0x0043:
            r4 = move-exception
        L_0x0044:
            if (r1 == 0) goto L_0x0049
            r1.release()
        L_0x0049:
            r3.release(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.pip.core.EncodeVideo.CheckVideoFormat(android.content.Context, android.net.Uri):boolean");
    }

    private void bindFrameBuffer(int i2) {
        GLES20.glBindFramebuffer(36160, i2);
    }

    private int genFrameBuffer() {
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        return iArr[0];
    }

    public static boolean isSupportedFormat(String str, Context context, Uri uri) {
        return CodecsHelper.isSupportedFormat(context, uri);
    }

    private Bitmap saveTexture(int i2, int i7) {
        int i8 = i2 * i7;
        int[] iArr = new int[i8];
        int[] iArr2 = new int[i8];
        IntBuffer wrap = IntBuffer.wrap(iArr);
        wrap.position(0);
        int i10 = i2;
        int i11 = i7;
        try {
            GLES20.glReadPixels(0, 0, i10, i11, 6408, 5121, wrap);
            for (int i12 = 0; i12 < i11; i12++) {
                int i13 = i12 * i10;
                int i14 = ((i11 - i12) - 1) * i10;
                for (int i15 = 0; i15 < i10; i15++) {
                    int i16 = iArr[i13 + i15];
                    iArr2[i14 + i15] = (i16 & -16711936) | ((i16 << 16) & 16711680) | ((i16 >> 16) & ScoverState.TYPE_NFC_SMART_COVER);
                }
            }
            return Bitmap.createBitmap(iArr2, i10, i11, Bitmap.Config.ARGB_8888);
        } catch (Exception unused) {
            return null;
        }
    }

    public void initialize(String str, Context context, Uri uri) {
        if (CheckVideoFormat(context, uri)) {
            this.mOutputFilePath = str;
            this.mInputUri = uri;
            this.mContext = context;
            return;
        }
        throw new IOException("Not a valid video format.");
    }

    public void prepare() {
        this.mEncoding = true;
        prepareVideoCodec();
        prepareAudioCodec();
    }

    public void prepareAudioCodec() {
        Uri uri;
        int integer;
        Context context = this.mContext;
        if (context == null || (uri = this.mInputUri) == null) {
            throw new IOException("mInputUri or mContext  is NULL");
        }
        MediaExtractor createExtractor = CodecsHelper.createExtractor(context, uri);
        this.mAudioExtractor = createExtractor;
        int andSelectAudioTrackIndex = CodecsHelper.getAndSelectAudioTrackIndex(createExtractor);
        int i2 = 0;
        if (andSelectAudioTrackIndex == -1) {
            this.mCopyAudio = false;
            return;
        }
        MediaFormat trackFormat = this.mAudioExtractor.getTrackFormat(andSelectAudioTrackIndex);
        if ("audio/unknown".equals(trackFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX))) {
            Log.d(Constants.TAG, "Audio mime type is unknown. Ignore audio track.");
            this.mCopyAudio = false;
        } else if (!trackFormat.containsKey(KEY_ERROR_TYPE) || (integer = trackFormat.getInteger(KEY_ERROR_TYPE)) == 0) {
            this.mCopyAudio = true;
            if (this.mTrimAudioEndUs == 0) {
                this.mTrimAudioEndUs = trackFormat.getLong("durationUs");
                Log.d(Constants.TAG, "mTrimAudioEndUs was 0 but updated mTrimAudioEndUs :" + this.mTrimAudioEndUs);
            }
            Log.d(Constants.TAG, "Audio input format " + trackFormat);
            this.mOutputAudioSampleRateHZ = trackFormat.getInteger("sample-rate");
            this.mOutputAudioChannelCount = trackFormat.getInteger("channel-count");
            try {
                i2 = trackFormat.getInteger("max-input-size");
            } catch (NullPointerException unused) {
                Log.d(Constants.TAG, "Audio max input size not defined");
            }
            MediaFormat createAudioFormat = MediaFormat.createAudioFormat(this.mOutputAudioMimeType, this.mOutputAudioSampleRateHZ, this.mOutputAudioChannelCount);
            if (i2 != 0) {
                createAudioFormat.setInteger("max-input-size", i2);
            }
            createAudioFormat.setInteger("bitrate", this.mOutputAudioBitRate);
            createAudioFormat.setInteger("aac-profile", this.mOutputAudioAACProfile);
            Log.d(Constants.TAG, "Audio output format " + createAudioFormat);
            this.mOutputAudioEncoder = CodecsHelper.createAudioEncoder(CodecsHelper.getEncoderCodec(this.mOutputAudioMimeType), createAudioFormat);
            String string = trackFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
            if (Encode.CodecsMime.AUDIO_CODEC_AAC.equals(string)) {
                this.mInputAudioDecoder = CodecsHelper.createAudioDecoder(CodecsHelper.getDecoderCodec(string), trackFormat);
            } else {
                this.mInputAudioDecoder = CodecsHelper.createAudioDecoder(trackFormat);
            }
        } else {
            C0086a.C(integer, "Audio codec error appear : ", Constants.TAG);
            this.mCopyAudio = false;
        }
    }

    public void prepareVideoCodec() {
        int i2;
        int i7;
        int i8;
        int i10;
        if (this.mContext == null || this.mInputUri == null) {
            throw new IOException("mInputUri or mContext  is NULL");
        }
        setBGImage(this.mLogoBitmap);
        this.mBGImagePresent = true;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(this.mContext, this.mInputUri);
            String extractMetadata = mediaMetadataRetriever.extractMetadata(24);
            mediaMetadataRetriever.extractMetadata(12);
            if (extractMetadata != null) {
                try {
                    i10 = Integer.parseInt(extractMetadata);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    i10 = 0;
                }
                int i11 = (i10 + this.mRotation) % 360;
                if (i11 == 0) {
                    this.mInputOrientationDegrees = 0;
                } else if (i11 == 90) {
                    this.mInputOrientationDegrees = 90;
                } else if (i11 == 180) {
                    this.mInputOrientationDegrees = MOCRLang.KHMER;
                } else if (i11 == 270) {
                    this.mInputOrientationDegrees = 270;
                }
            } else {
                this.mInputOrientationDegrees = 0;
            }
        } catch (IllegalArgumentException e7) {
            e7.printStackTrace();
        } catch (Throwable th) {
            Throwable th2 = th;
            mediaMetadataRetriever.release();
            throw th2;
        }
        mediaMetadataRetriever.release();
        MediaExtractor createExtractor = CodecsHelper.createExtractor(this.mContext, this.mInputUri);
        this.mVideoExtractor = createExtractor;
        MediaFormat trackFormat = this.mVideoExtractor.getTrackFormat(CodecsHelper.getAndSelectVideoTrackIndex(createExtractor));
        Log.d(Constants.TAG, "input video format: " + trackFormat);
        if (!this.mCustomTrim) {
            this.mVideoTrims.add(Encode.TrimInfo.create(0, trackFormat.getLong("durationUs")));
        }
        this.mOutputWidth = this.mLogoBitmap.getWidth();
        int height = this.mLogoBitmap.getHeight();
        this.mOutputHeight = height;
        this.mOutputVideoBitRate = CodecsHelper.suggestBitRate(this.mOutputWidth, height) * 1000;
        try {
            i2 = trackFormat.getInteger("frame-rate");
        } catch (Exception unused) {
            i2 = 0;
        }
        if (i2 > 0) {
            this.mOutputVideoFrameRate = i2;
        }
        StringBuilder o2 = C0086a.o(i2, "SourceFrameRate :", ", mOutputVideoFrameRate :");
        o2.append(this.mOutputVideoFrameRate);
        Log.d(Constants.TAG, o2.toString());
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(this.mOutputVideoMimeType, this.mOutputWidth, this.mOutputHeight);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", this.mOutputVideoBitRate);
        createVideoFormat.setInteger("frame-rate", this.mOutputVideoFrameRate);
        createVideoFormat.setInteger("i-frame-interval", this.mOutputVideoIFrameInterval);
        createVideoFormat.setInteger("priority", 1);
        createVideoFormat.setInteger(Message.KEY_ROTATION, this.mOutputOrientationDegrees);
        Log.d(Constants.TAG, "output video format " + createVideoFormat);
        MediaCodec createEncoderByType = MediaCodec.createEncoderByType(this.mOutputVideoMimeType);
        this.mOutputVideoEncoder = createEncoderByType;
        createEncoderByType.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mInputSurface = new InputSurface(this.mOutputVideoEncoder.createInputSurface());
        this.mOutputVideoEncoder.start();
        this.mInputSurface.makeCurrent();
        this.mainOutputSurface = new MainOutputSurface(0, 0, 0, this.mOutputWidth, this.mOutputHeight);
        this.frameBufferID = genFrameBuffer();
        Log.d("framebuffer", "prepareVideoCodec: generated framebuffer" + this.frameBufferID);
        bindFrameBuffer(this.frameBufferID);
        StringBuilder sb2 = new StringBuilder("Build.VERSION.SEM_PLATFORM_INT: ");
        int i12 = Build.VERSION.SEM_PLATFORM_INT;
        sb2.append(i12);
        Log.d(Constants.TAG, sb2.toString());
        if (i12 > 80200) {
            try {
                int integer = trackFormat.getInteger("width");
                int integer2 = trackFormat.getInteger("height");
                int i13 = this.mOutputWidth;
                int i14 = this.mOutputHeight;
                if (((float) integer) / ((float) integer2) > ((float) i13) / ((float) i14)) {
                    i7 = (i14 - ((i13 * integer2) / integer)) / 2;
                    i8 = 0;
                } else {
                    i8 = (i13 - ((i14 * integer) / integer2)) / 2;
                    i7 = 0;
                }
                this.mRotationOffset = this.mInputOrientationDegrees - this.mBGImage.mOrientation;
                OutputSurface outputSurface = new OutputSurface(this.mOutputOrientationDegrees, i8, i7, i13, i14, integer, integer2);
                this.mOutputSurface = outputSurface;
                int i15 = this.mRotationOffset;
                if (i15 < 0) {
                    this.mRotationOffset = i15 + 360;
                }
                int i16 = this.mInputOrientationDegrees;
                Point point = this.mVideoSize;
                outputSurface.initSurfaceAspectRatio(i16, (float) point.x, (float) point.y);
                OutputSurface outputSurface2 = this.mOutputSurface;
                PointF pointF = this.mVideoRelatedPos;
                outputSurface2.setPipVideoRelatedPosition(pointF.x, pointF.y);
            } catch (Exception unused2) {
                Log.d(Constants.TAG, "Can't get input video resolution");
                this.mOutputSurface = new OutputSurface(this.mInputOrientationDegrees);
            }
        } else {
            this.mOutputSurface = new OutputSurface(this.mInputOrientationDegrees);
        }
        if (this.mBGImagePresent) {
            RenderTexture_GL_2d renderTexture_GL_2d = new RenderTexture_GL_2d();
            this.mBGImageRenderer = renderTexture_GL_2d;
            renderTexture_GL_2d.prepare();
        }
        MediaCodec createVideoDecoder = CodecsHelper.createVideoDecoder(trackFormat, this.mOutputSurface.getSurface());
        this.mInputVideoDecoder = createVideoDecoder;
        if (createVideoDecoder == null) {
            throw new IOException("can't set VideoDecoder");
        } else if (this.mBGImagePresent) {
            RenderTexture_GL_2d renderTexture_GL_2d2 = this.mBGImageRenderer;
            Encode.BGImage bGImage = this.mBGImage;
            renderTexture_GL_2d2.loadTexture(bGImage.mBitmap, bGImage.mOrientation, this.mOutputWidth, this.mOutputHeight);
            this.mBGImage.mBitmap.recycle();
            this.mBGImage.mBitmap = null;
            this.mBGImageRenderer.setProjectionMatrixTranslate(0.0f, 0.0f);
            this.mBGImageRenderer.setProjectionMatrixScale(1.0f, 1.0f);
        }
    }

    public void release() {
        Bitmap bitmap;
        try {
            Log.d(Constants.TAG, "releasing encoder objects");
            MediaCodec mediaCodec = this.mOutputVideoEncoder;
            if (mediaCodec != null) {
                try {
                    mediaCodec.stop();
                    this.mOutputVideoEncoder.release();
                    this.mOutputVideoEncoder = null;
                } catch (Exception e) {
                    Log.d(Constants.TAG, "Exception in releasing output video encoder.");
                    e.printStackTrace();
                }
            }
            MediaCodec mediaCodec2 = this.mInputVideoDecoder;
            if (mediaCodec2 != null) {
                try {
                    mediaCodec2.stop();
                    this.mInputVideoDecoder.release();
                    this.mInputVideoDecoder = null;
                } catch (Exception e7) {
                    Log.d(Constants.TAG, "Exception in releasing input video decoder.");
                    e7.printStackTrace();
                }
            }
            MainOutputSurface mainOutputSurface2 = this.mainOutputSurface;
            if (mainOutputSurface2 != null) {
                try {
                    mainOutputSurface2.release();
                    this.mainOutputSurface = null;
                } catch (Exception e8) {
                    Log.d(Constants.TAG, "Exception in releasing outputSurface.");
                    e8.printStackTrace();
                }
            }
            MediaExtractor mediaExtractor = this.mVideoExtractor;
            if (mediaExtractor != null) {
                try {
                    mediaExtractor.release();
                    this.mVideoExtractor = null;
                } catch (Exception e9) {
                    Log.d(Constants.TAG, "Exception in releasing video extractor.");
                    e9.printStackTrace();
                }
            }
            OutputSurface outputSurface = this.mOutputSurface;
            if (outputSurface != null) {
                try {
                    outputSurface.release();
                    this.mOutputSurface = null;
                } catch (Exception e10) {
                    Log.d(Constants.TAG, "Exception in releasing outputSurface.");
                    e10.printStackTrace();
                }
            }
            RenderTexture_GL_2d renderTexture_GL_2d = this.mBGImageRenderer;
            if (renderTexture_GL_2d != null) {
                try {
                    renderTexture_GL_2d.release();
                    this.mBGImageRenderer = null;
                } catch (Exception e11) {
                    Log.d(Constants.TAG, "Exception in releasing logo renderer.");
                    e11.printStackTrace();
                }
            }
            Encode.BGImage bGImage = this.mBGImage;
            if (!(bGImage == null || (bitmap = bGImage.mBitmap) == null || bitmap.isRecycled())) {
                this.mBGImage.mBitmap.recycle();
                this.mBGImage.mBitmap = null;
                this.mBGImage = null;
                this.mBGImagePresent = false;
            }
            InputSurface inputSurface = this.mInputSurface;
            if (inputSurface != null) {
                try {
                    inputSurface.release();
                    this.mInputSurface = null;
                } catch (Exception e12) {
                    Log.d(Constants.TAG, "Exception in releasing input surface.");
                    e12.printStackTrace();
                }
            }
            MediaCodec mediaCodec3 = this.mOutputAudioEncoder;
            if (mediaCodec3 != null) {
                try {
                    mediaCodec3.stop();
                    this.mOutputAudioEncoder.release();
                    this.mOutputAudioEncoder = null;
                } catch (Exception e13) {
                    Log.d(Constants.TAG, "Exception in releasing output audio encoder.");
                    e13.printStackTrace();
                }
            }
            MediaCodec mediaCodec4 = this.mInputAudioDecoder;
            if (mediaCodec4 != null) {
                try {
                    mediaCodec4.stop();
                    this.mInputAudioDecoder.release();
                    this.mInputAudioDecoder = null;
                } catch (Exception e14) {
                    Log.d(Constants.TAG, "Exception in releasing input audio decoder.");
                    e14.printStackTrace();
                }
            }
            MediaExtractor mediaExtractor2 = this.mAudioExtractor;
            if (mediaExtractor2 != null) {
                try {
                    mediaExtractor2.release();
                    this.mAudioExtractor = null;
                } catch (Exception e15) {
                    Log.d(Constants.TAG, "Exception in releasing audio extractor.");
                    e15.printStackTrace();
                }
            }
            MediaMuxer mediaMuxer = this.mMuxer;
            if (mediaMuxer != null) {
                try {
                    if (this.mMuxerStarted) {
                        mediaMuxer.stop();
                    }
                    this.mMuxer.release();
                    this.mMuxer = null;
                } catch (Exception e16) {
                    Log.d(Constants.TAG, "Exception in releasing muxer.");
                    e16.printStackTrace();
                }
            }
            synchronized (this.mStopLock) {
                this.mEncoding = false;
                this.mStopLock.notifyAll();
            }
        } catch (Throwable th) {
            synchronized (this.mStopLock) {
                this.mEncoding = false;
                this.mStopLock.notifyAll();
                throw th;
            }
        }
    }

    public void setLogoBitmap(Bitmap bitmap) {
        this.mLogoBitmap = bitmap;
    }

    public void setTrims(List<Encode.TrimInfo> list) {
        if (list.size() == 1) {
            this.mTrimAudioStartUs = list.get(0).getStartTime();
            this.mTrimAudioEndUs = list.get(0).getEndTime();
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.mFullPlayTime = (list.get(i2).getEndTime() - list.get(i2).getStartTime()) + this.mFullPlayTime;
        }
        this.mVideoTrims.addAll(list);
        this.mCustomTrim = true;
    }

    public void setVideoLayoutInfo(float f, float f5, int i2, int i7) {
        this.mVideoRelatedPos.set(f, f5);
        this.mVideoSize.set(i2, i7);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01d5, code lost:
        if (r38 > (((com.samsung.android.sdk.sgpl.pip.core.Encode.TrimInfo) c0.C0086a.f(1, r1.mVideoTrims)).getEndTime() - r26)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01d7, code lost:
        if (r37 < 0) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01d9, code lost:
        r1.mInputVideoDecoder.queueInputBuffer(r35, 0, r37, r38, r1.mVideoExtractor.getSampleFlags());
        r1.mVideoExtractor.advance();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01ee, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01ef, code lost:
        if (r0 == false) goto L_0x0206;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01f1, code lost:
        android.util.Log.d(com.samsung.android.sdk.sgpl.pip.util.Constants.TAG, "video extractor: EOS");
        r1.mInputVideoDecoder.queueInputBuffer(r35, 0, 0, 0, 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x023a, code lost:
        r35 = r8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0362 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0456  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x0554  */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x055a  */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x05dc  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x05e2  */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x06a7  */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x06ee A[LOOP:1: B:40:0x00f2->B:279:0x06ee, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x06e8 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startEncoding() {
        /*
            r46 = this;
            r1 = r46
            boolean r0 = r1.mUserStop
            java.lang.String r2 = "PIP"
            if (r0 == 0) goto L_0x000e
            java.lang.String r0 = "Not starting encoding because it is stopped by user."
            android.util.Log.d(r2, r0)
            return
        L_0x000e:
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r0 = r1.mVideoTrims
            int r0 = r0.size()
            r3 = 1
            r4 = 0
            if (r0 <= r3) goto L_0x001a
            r1.mCopyAudio = r4
        L_0x001a:
            java.lang.String r0 = "startEncoding"
            android.util.Log.d(r2, r0)
            android.media.MediaCodec r0 = r1.mOutputVideoEncoder
            java.nio.ByteBuffer[] r0 = r0.getOutputBuffers()
            android.media.MediaCodec r5 = r1.mInputVideoDecoder
            java.nio.ByteBuffer[] r5 = r5.getInputBuffers()
            android.media.MediaCodec r6 = r1.mInputVideoDecoder
            java.nio.ByteBuffer[] r6 = r6.getOutputBuffers()
            boolean r7 = r1.mCopyAudio
            if (r7 == 0) goto L_0x003d
            android.media.MediaCodec r7 = r1.mOutputAudioEncoder
            java.nio.ByteBuffer[] r7 = r7.getOutputBuffers()
            goto L_0x003e
        L_0x003d:
            r7 = 0
        L_0x003e:
            boolean r9 = r1.mCopyAudio
            if (r9 == 0) goto L_0x0049
            android.media.MediaCodec r9 = r1.mOutputAudioEncoder
            java.nio.ByteBuffer[] r9 = r9.getInputBuffers()
            goto L_0x004a
        L_0x0049:
            r9 = 0
        L_0x004a:
            boolean r10 = r1.mCopyAudio
            if (r10 == 0) goto L_0x0055
            android.media.MediaCodec r10 = r1.mInputAudioDecoder
            java.nio.ByteBuffer[] r10 = r10.getOutputBuffers()
            goto L_0x0056
        L_0x0055:
            r10 = 0
        L_0x0056:
            boolean r11 = r1.mCopyAudio
            if (r11 == 0) goto L_0x0061
            android.media.MediaCodec r11 = r1.mInputAudioDecoder
            java.nio.ByteBuffer[] r11 = r11.getInputBuffers()
            goto L_0x0062
        L_0x0061:
            r11 = 0
        L_0x0062:
            android.media.MediaCodec$BufferInfo r12 = new android.media.MediaCodec$BufferInfo
            r12.<init>()
            android.media.MediaCodec$BufferInfo r13 = new android.media.MediaCodec$BufferInfo
            r13.<init>()
            android.media.MediaCodec$BufferInfo r14 = new android.media.MediaCodec$BufferInfo
            r14.<init>()
            android.media.MediaCodec$BufferInfo r15 = new android.media.MediaCodec$BufferInfo
            r15.<init>()
            boolean r8 = r1.mCopyAudio
            r17 = r8 ^ 1
            r18 = r8 ^ 1
            r8 = r8 ^ r3
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r3 = r1.mVideoTrims
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x009d
            android.media.MediaExtractor r3 = r1.mVideoExtractor
            r19 = r0
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r0 = r1.mVideoTrims
            java.lang.Object r0 = r0.get(r4)
            com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo r0 = (com.samsung.android.sdk.sgpl.pip.core.Encode.TrimInfo) r0
            r20 = r5
            r21 = r6
            long r5 = r0.getStartTime()
            r3.seekTo(r5, r4)
            goto L_0x00a3
        L_0x009d:
            r19 = r0
            r20 = r5
            r21 = r6
        L_0x00a3:
            boolean r0 = r1.mCopyAudio
            r22 = 0
            r24 = -1
            if (r0 == 0) goto L_0x00dd
            long r5 = r1.mTrimAudioStartUs
            int r0 = (r5 > r22 ? 1 : (r5 == r22 ? 0 : -1))
            if (r0 == 0) goto L_0x00dd
            android.media.MediaExtractor r0 = r1.mAudioExtractor
            r0.seekTo(r5, r4)
        L_0x00b6:
            android.media.MediaExtractor r0 = r1.mAudioExtractor
            long r5 = r0.getSampleTime()
            r26 = r5
            long r4 = r1.mTrimAudioStartUs
            int r0 = (r26 > r4 ? 1 : (r26 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x00dd
            android.media.MediaExtractor r0 = r1.mAudioExtractor
            long r4 = r0.getSampleTime()
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x00d5
            android.media.MediaExtractor r0 = r1.mAudioExtractor
            r0.advance()
            r4 = 0
            goto L_0x00b6
        L_0x00d5:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Invalid File!"
            r0.<init>(r1)
            throw r0
        L_0x00dd:
            r26 = r22
            r28 = r24
            r0 = 0
            r3 = -1
            r5 = 0
            r6 = 0
            r16 = 0
            r24 = r17
            r25 = r18
            r17 = 0
            r18 = r10
            r10 = r8
            r8 = r7
            r7 = 0
        L_0x00f2:
            if (r5 == 0) goto L_0x00f6
            if (r10 != 0) goto L_0x06ed
        L_0x00f6:
            boolean r4 = r1.mUserStop
            r31 = r4
            r30 = r5
            if (r31 != 0) goto L_0x0107
            if (r0 != 0) goto L_0x0107
            if (r16 == 0) goto L_0x010d
            boolean r4 = r1.mMuxerStarted
            if (r4 == 0) goto L_0x0107
            goto L_0x010d
        L_0x0107:
            r41 = r7
            r33 = r8
            goto L_0x0206
        L_0x010d:
            android.media.MediaCodec r4 = r1.mInputVideoDecoder
            r5 = r7
            r33 = r8
            r7 = 10000(0x2710, double:4.9407E-320)
            int r4 = r4.dequeueInputBuffer(r7)
            r7 = -1
            if (r4 != r7) goto L_0x0124
            java.lang.String r4 = "no video decoder input buffer"
            android.util.Log.d(r2, r4)
            r41 = r5
            goto L_0x0206
        L_0x0124:
            r7 = r20[r4]
            android.media.MediaExtractor r8 = r1.mVideoExtractor
            r35 = r4
            r4 = 0
            int r37 = r8.readSampleData(r7, r4)
            android.media.MediaExtractor r4 = r1.mVideoExtractor
            long r7 = r4.getSampleTime()
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r4 = r1.mVideoTrims
            int r4 = r4.size()
            r41 = r5
            r5 = 1
            if (r4 <= r5) goto L_0x01c2
            int r4 = r6 + 1
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r5 = r1.mVideoTrims
            int r5 = r5.size()
            if (r4 >= r5) goto L_0x01c2
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r5 = r1.mVideoTrims
            java.lang.Object r5 = r5.get(r6)
            com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo r5 = (com.samsung.android.sdk.sgpl.pip.core.Encode.TrimInfo) r5
            boolean r5 = r5.isActive()
            if (r5 == 0) goto L_0x01c2
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r5 = r1.mVideoTrims
            java.lang.Object r5 = r5.get(r6)
            com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo r5 = (com.samsung.android.sdk.sgpl.pip.core.Encode.TrimInfo) r5
            long r38 = r5.getEndTime()
            int r5 = (r7 > r38 ? 1 : (r7 == r38 ? 0 : -1))
            if (r5 < 0) goto L_0x01c2
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r5 = r1.mVideoTrims
            java.lang.Object r5 = r5.get(r6)
            com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo r5 = (com.samsung.android.sdk.sgpl.pip.core.Encode.TrimInfo) r5
            long r31 = r5.getEndTime()
            long r7 = r7 - r31
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r5 = r1.mVideoTrims
            java.lang.Object r5 = r5.get(r4)
            com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo r5 = (com.samsung.android.sdk.sgpl.pip.core.Encode.TrimInfo) r5
            long r31 = r5.getStartTime()
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r5 = r1.mVideoTrims
            java.lang.Object r5 = r5.get(r6)
            com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo r5 = (com.samsung.android.sdk.sgpl.pip.core.Encode.TrimInfo) r5
            long r34 = r5.getEndTime()
            long r31 = r31 - r34
            long r31 = r31 + r7
            long r26 = r31 + r26
            android.media.MediaExtractor r5 = r1.mVideoExtractor
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r7 = r1.mVideoTrims
            java.lang.Object r7 = r7.get(r4)
            com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo r7 = (com.samsung.android.sdk.sgpl.pip.core.Encode.TrimInfo) r7
            long r7 = r7.getStartTime()
            r34 = r4
            r4 = 0
            r5.seekTo(r7, r4)
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r4 = r1.mVideoTrims
            java.lang.Object r4 = r4.get(r6)
            com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo r4 = (com.samsung.android.sdk.sgpl.pip.core.Encode.TrimInfo) r4
            r4.complete()
            android.media.MediaCodec r4 = r1.mInputVideoDecoder
            r4.flush()
            r5 = r30
            r8 = r33
            r6 = r34
            r7 = r41
            goto L_0x00f6
        L_0x01c2:
            long r38 = r7 - r26
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r4 = r1.mVideoTrims
            r5 = 1
            java.lang.Object r4 = c0.C0086a.f(r5, r4)
            com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo r4 = (com.samsung.android.sdk.sgpl.pip.core.Encode.TrimInfo) r4
            long r4 = r4.getEndTime()
            long r4 = r4 - r26
            int r4 = (r38 > r4 ? 1 : (r38 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x01ee
            if (r37 < 0) goto L_0x01ee
            android.media.MediaCodec r4 = r1.mInputVideoDecoder
            android.media.MediaExtractor r5 = r1.mVideoExtractor
            int r40 = r5.getSampleFlags()
            r36 = 0
            r34 = r4
            r34.queueInputBuffer(r35, r36, r37, r38, r40)
            android.media.MediaExtractor r4 = r1.mVideoExtractor
            r4.advance()
            goto L_0x01ef
        L_0x01ee:
            r0 = 1
        L_0x01ef:
            if (r0 == 0) goto L_0x0206
            java.lang.String r4 = "video extractor: EOS"
            android.util.Log.d(r2, r4)
            android.media.MediaCodec r4 = r1.mInputVideoDecoder
            r38 = 0
            r40 = 4
            r36 = 0
            r37 = 0
            r34 = r4
            r34.queueInputBuffer(r35, r36, r37, r38, r40)
        L_0x0206:
            r4 = r0
        L_0x0207:
            boolean r0 = r1.mUserStop
            r7 = -3
            if (r0 != 0) goto L_0x0215
            if (r41 != 0) goto L_0x0215
            if (r16 == 0) goto L_0x0218
            boolean r0 = r1.mMuxerStarted
            if (r0 == 0) goto L_0x0215
            goto L_0x0218
        L_0x0215:
            r35 = r6
            goto L_0x023c
        L_0x0218:
            android.media.MediaCodec r0 = r1.mInputVideoDecoder
            r8 = r6
            r5 = 10000(0x2710, double:4.9407E-320)
            int r0 = r0.dequeueOutputBuffer(r13, r5)
            r5 = -1
            if (r0 != r5) goto L_0x022a
            java.lang.String r0 = "no video decoder output buffer"
            android.util.Log.d(r2, r0)
            goto L_0x023a
        L_0x022a:
            if (r0 != r7) goto L_0x0240
            java.lang.String r0 = "video decoder: output buffers changed"
            android.util.Log.d(r2, r0)
            android.media.MediaCodec r0 = r1.mInputVideoDecoder
            java.nio.ByteBuffer[] r0 = r0.getOutputBuffers()
            r21 = r0
        L_0x023a:
            r35 = r8
        L_0x023c:
            r7 = r41
            goto L_0x035e
        L_0x0240:
            r5 = -2
            if (r0 != r5) goto L_0x025c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r5 = "video decoder: codec info format changed"
            r0.<init>(r5)
            android.media.MediaCodec r5 = r1.mInputVideoDecoder
            android.media.MediaFormat r5 = r5.getOutputFormat()
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r2, r0)
            goto L_0x023a
        L_0x025c:
            r5 = r21[r0]
            int r5 = r13.flags
            r5 = r5 & 2
            if (r5 == 0) goto L_0x0271
            java.lang.String r5 = "video decoder: codec config buffer"
            android.util.Log.d(r2, r5)
            android.media.MediaCodec r5 = r1.mInputVideoDecoder
            r6 = 0
            r5.releaseOutputBuffer(r0, r6)
            goto L_0x023a
        L_0x0271:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "video decoder: returned buffer for time "
            r5.<init>(r6)
            r35 = r8
            long r7 = r13.presentationTimeUs
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r2, r5)
            int r5 = r13.size
            if (r5 == 0) goto L_0x028d
            r5 = 1
            goto L_0x028e
        L_0x028d:
            r5 = 0
        L_0x028e:
            android.media.MediaCodec r7 = r1.mInputVideoDecoder
            r7.releaseOutputBuffer(r0, r5)
            if (r5 == 0) goto L_0x034c
            java.lang.String r0 = "output surface: await new image"
            android.util.Log.d(r2, r0)
            com.samsung.android.sdk.sgpl.pip.surfaces.OutputSurface r0 = r1.mOutputSurface     // Catch:{ RuntimeException -> 0x02bf }
            r5 = 1000(0x3e8, float:1.401E-42)
            boolean r0 = r0.checkForNewImage(r5)     // Catch:{ RuntimeException -> 0x02bf }
            if (r0 != 0) goto L_0x02c1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02bf }
            r0.<init>()     // Catch:{ RuntimeException -> 0x02bf }
            java.lang.String r5 = "video decoder: checkForNewImage return false!!  mUserStop : "
            r0.append(r5)     // Catch:{ RuntimeException -> 0x02bf }
            boolean r5 = r1.mUserStop     // Catch:{ RuntimeException -> 0x02bf }
            r0.append(r5)     // Catch:{ RuntimeException -> 0x02bf }
            java.lang.String r0 = r0.toString()     // Catch:{ RuntimeException -> 0x02bf }
            android.util.Log.d(r2, r0)     // Catch:{ RuntimeException -> 0x02bf }
            r6 = r35
            goto L_0x0207
        L_0x02bf:
            r0 = move-exception
            goto L_0x0333
        L_0x02c1:
            java.lang.String r0 = "output surface: draw image"
            android.util.Log.d(r2, r0)     // Catch:{ RuntimeException -> 0x02bf }
            r0 = 16384(0x4000, float:2.2959E-41)
            android.opengl.GLES20.glClear(r0)     // Catch:{ RuntimeException -> 0x02bf }
            int r0 = r1.frameBufferID     // Catch:{ RuntimeException -> 0x02bf }
            r1.bindFrameBuffer(r0)     // Catch:{ RuntimeException -> 0x02bf }
            com.samsung.android.sdk.sgpl.pip.surfaces.MainOutputSurface r0 = r1.mainOutputSurface     // Catch:{ RuntimeException -> 0x02bf }
            r0.setFrameBufferTarget()     // Catch:{ RuntimeException -> 0x02bf }
            boolean r0 = r1.mBGImagePresent     // Catch:{ RuntimeException -> 0x02bf }
            if (r0 == 0) goto L_0x02e4
            com.samsung.android.sdk.sgpl.pip.renderer.RenderTexture_GL_2d r0 = r1.mBGImageRenderer     // Catch:{ RuntimeException -> 0x02bf }
            int r5 = r1.mOutputWidth     // Catch:{ RuntimeException -> 0x02bf }
            float r5 = (float) r5     // Catch:{ RuntimeException -> 0x02bf }
            int r7 = r1.mOutputHeight     // Catch:{ RuntimeException -> 0x02bf }
            float r7 = (float) r7     // Catch:{ RuntimeException -> 0x02bf }
            r0.draw(r5, r7)     // Catch:{ RuntimeException -> 0x02bf }
        L_0x02e4:
            com.samsung.android.sdk.sgpl.pip.surfaces.OutputSurface r0 = r1.mOutputSurface     // Catch:{ RuntimeException -> 0x02bf }
            int r5 = r1.mRotationOffset     // Catch:{ RuntimeException -> 0x02bf }
            r0.drawImage(r5)     // Catch:{ RuntimeException -> 0x02bf }
            r5 = 0
            r1.bindFrameBuffer(r5)     // Catch:{ RuntimeException -> 0x02bf }
            com.samsung.android.sdk.sgpl.pip.surfaces.MainOutputSurface r0 = r1.mainOutputSurface     // Catch:{ RuntimeException -> 0x02bf }
            r0.drawImage()     // Catch:{ RuntimeException -> 0x02bf }
            long r7 = r13.presentationTimeUs     // Catch:{ RuntimeException -> 0x02bf }
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r0 = r1.mVideoTrims     // Catch:{ RuntimeException -> 0x02bf }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ RuntimeException -> 0x02bf }
            com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo r0 = (com.samsung.android.sdk.sgpl.pip.core.Encode.TrimInfo) r0     // Catch:{ RuntimeException -> 0x02bf }
            long r36 = r0.getStartTime()     // Catch:{ RuntimeException -> 0x02bf }
            int r0 = (r7 > r36 ? 1 : (r7 == r36 ? 0 : -1))
            if (r0 < 0) goto L_0x034c
            com.samsung.android.sdk.sgpl.pip.surfaces.InputSurface r0 = r1.mInputSurface     // Catch:{ RuntimeException -> 0x02bf }
            long r7 = r13.presentationTimeUs     // Catch:{ RuntimeException -> 0x02bf }
            r36 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 * r36
            r0.setPresentationTime(r7)     // Catch:{ RuntimeException -> 0x02bf }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02bf }
            r0.<init>()     // Catch:{ RuntimeException -> 0x02bf }
            java.lang.String r5 = "input surface: swap buffers time:"
            r0.append(r5)     // Catch:{ RuntimeException -> 0x02bf }
            long r7 = r13.presentationTimeUs     // Catch:{ RuntimeException -> 0x02bf }
            r0.append(r7)     // Catch:{ RuntimeException -> 0x02bf }
            java.lang.String r0 = r0.toString()     // Catch:{ RuntimeException -> 0x02bf }
            android.util.Log.d(r2, r0)     // Catch:{ RuntimeException -> 0x02bf }
            com.samsung.android.sdk.sgpl.pip.surfaces.InputSurface r0 = r1.mInputSurface     // Catch:{ RuntimeException -> 0x02bf }
            r0.swapBuffers()     // Catch:{ RuntimeException -> 0x02bf }
            java.lang.String r0 = "video encoder: notified of new frame"
            android.util.Log.d(r2, r0)     // Catch:{ RuntimeException -> 0x02bf }
            goto L_0x034c
        L_0x0333:
            java.lang.String r5 = r0.getMessage()
            boolean r7 = r1.mUserStop
            if (r7 == 0) goto L_0x0346
            if (r5 == 0) goto L_0x0346
            java.lang.String r7 = "Surface frame wait timed out"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0346
            goto L_0x034c
        L_0x0346:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x034c:
            int r0 = r13.flags
            r0 = r0 & 4
            if (r0 == 0) goto L_0x023c
            java.lang.String r0 = "video decoder: EOS"
            android.util.Log.d(r2, r0)
            android.media.MediaCodec r0 = r1.mOutputVideoEncoder
            r0.signalEndOfInputStream()
            r7 = 1
        L_0x035e:
            boolean r0 = r1.mUserStop
            if (r0 != 0) goto L_0x036b
            if (r30 != 0) goto L_0x036b
            if (r16 == 0) goto L_0x036e
            boolean r0 = r1.mMuxerStarted
            if (r0 == 0) goto L_0x036b
            goto L_0x036e
        L_0x036b:
            r38 = r7
            goto L_0x0395
        L_0x036e:
            android.media.MediaCodec r0 = r1.mOutputVideoEncoder
            r8 = r7
            r6 = 10000(0x2710, double:4.9407E-320)
            int r0 = r0.dequeueOutputBuffer(r12, r6)
            r7 = -1
            if (r0 != r7) goto L_0x0382
            java.lang.String r0 = "no video encoder output buffer"
            android.util.Log.d(r2, r0)
        L_0x037f:
            r38 = r8
            goto L_0x0395
        L_0x0382:
            r6 = -3
            if (r0 != r6) goto L_0x039d
            java.lang.String r0 = "video encoder: output buffers changed"
            android.util.Log.d(r2, r0)
            android.media.MediaCodec r0 = r1.mOutputVideoEncoder
            java.nio.ByteBuffer[] r0 = r0.getOutputBuffers()
            r19 = r0
            r38 = r8
        L_0x0395:
            r37 = r9
            r0 = r16
        L_0x0399:
            r5 = r30
            goto L_0x0452
        L_0x039d:
            r5 = -2
            if (r0 != r5) goto L_0x03cf
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r5 = "video encoder: output format changed "
            r0.<init>(r5)
            android.media.MediaCodec r5 = r1.mOutputVideoEncoder
            android.media.MediaFormat r5 = r5.getOutputFormat()
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r2, r0)
            int r0 = r1.mVideoTrackIndex
            if (r0 >= 0) goto L_0x03c7
            android.media.MediaCodec r0 = r1.mOutputVideoEncoder
            android.media.MediaFormat r0 = r0.getOutputFormat()
            r38 = r8
            r37 = r9
            goto L_0x0399
        L_0x03c7:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Video encoder output format changed after muxer has started"
            r0.<init>(r1)
            throw r0
        L_0x03cf:
            if (r0 >= 0) goto L_0x03d7
            java.lang.String r0 = "Unexpected result from video encoder dequeue output format."
            android.util.Log.d(r2, r0)
            goto L_0x037f
        L_0x03d7:
            r5 = r19[r0]
            int r7 = r12.flags
            r7 = r7 & 2
            if (r7 == 0) goto L_0x03ec
            java.lang.String r5 = "video encoder: codec config buffer"
            android.util.Log.d(r2, r5)
            android.media.MediaCodec r5 = r1.mOutputVideoEncoder
            r7 = 0
            r5.releaseOutputBuffer(r0, r7)
            goto L_0x037f
        L_0x03ec:
            int r7 = r12.size
            if (r7 == 0) goto L_0x0436
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r6 = "video encoder: writing sample data timestamp "
            r7.<init>(r6)
            r38 = r8
            r37 = r9
            long r8 = r12.presentationTimeUs
            r7.append(r8)
            java.lang.String r6 = r7.toString()
            android.util.Log.d(r2, r6)
            android.media.MediaMuxer r6 = r1.mMuxer
            int r7 = r1.mVideoTrackIndex
            r6.writeSampleData(r7, r5, r12)
            long r5 = r1.mFullPlayTime
            int r5 = (r5 > r22 ? 1 : (r5 == r22 ? 0 : -1))
            if (r5 <= 0) goto L_0x042d
            long r5 = r12.presentationTimeUs
            java.util.List<com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo> r7 = r1.mVideoTrims
            r8 = 0
            java.lang.Object r7 = r7.get(r8)
            com.samsung.android.sdk.sgpl.pip.core.Encode$TrimInfo r7 = (com.samsung.android.sdk.sgpl.pip.core.Encode.TrimInfo) r7
            long r7 = r7.getStartTime()
            long r5 = r5 - r7
            r7 = 100
            long r5 = r5 * r7
            long r7 = r1.mFullPlayTime
            long r5 = r5 / r7
            int r5 = (int) r5
            goto L_0x042e
        L_0x042d:
            r5 = 0
        L_0x042e:
            com.samsung.android.sdk.sgpl.pip.core.Encode$EncodeProgressListener r6 = r1.mEncodeProgressListener
            if (r6 == 0) goto L_0x043a
            r6.onProgressChanged(r5)
            goto L_0x043a
        L_0x0436:
            r38 = r8
            r37 = r9
        L_0x043a:
            int r5 = r12.flags
            r5 = r5 & 4
            if (r5 == 0) goto L_0x0448
            java.lang.String r5 = "video encoder: EOS"
            android.util.Log.d(r2, r5)
            r5 = 1
            goto L_0x044a
        L_0x0448:
            r5 = r30
        L_0x044a:
            android.media.MediaCodec r6 = r1.mOutputVideoEncoder
            r7 = 0
            r6.releaseOutputBuffer(r0, r7)
            r0 = r16
        L_0x0452:
            boolean r6 = r1.mCopyAudio
            if (r6 == 0) goto L_0x06a7
            boolean r6 = r1.mUserStop
            if (r6 != 0) goto L_0x0463
            if (r24 != 0) goto L_0x0463
            if (r17 == 0) goto L_0x0468
            boolean r6 = r1.mMuxerStarted
            if (r6 == 0) goto L_0x0463
            goto L_0x0468
        L_0x0463:
            r9 = r4
            r16 = r5
            goto L_0x04e9
        L_0x0468:
            android.media.MediaExtractor r6 = r1.mAudioExtractor
            long r6 = r6.getSampleTime()
            android.media.MediaCodec r8 = r1.mInputAudioDecoder
            r9 = r4
            r16 = r5
            r4 = 10000(0x2710, double:4.9407E-320)
            int r8 = r8.dequeueInputBuffer(r4)
            r5 = -1
            if (r8 != r5) goto L_0x0482
            java.lang.String r4 = "audio decoder input try again later"
            android.util.Log.d(r2, r4)
            goto L_0x04e9
        L_0x0482:
            r4 = r11[r8]
            android.media.MediaExtractor r5 = r1.mAudioExtractor
            r40 = r8
            r8 = 0
            int r4 = r5.readSampleData(r4, r8)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r8 = "audioDecoderInput  presentationTimeUs :"
            r5.<init>(r8)
            r5.append(r6)
            java.lang.String r8 = ", size:"
            r5.append(r8)
            r5.append(r4)
            java.lang.String r8 = ", mTrimAudioEndUs:"
            r5.append(r8)
            r43 = r6
            long r6 = r1.mTrimAudioEndUs
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r2, r5)
            long r5 = r1.mTrimAudioEndUs
            int r5 = (r43 > r5 ? 1 : (r43 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x04d1
            if (r4 < 0) goto L_0x04d1
            android.media.MediaCodec r5 = r1.mInputAudioDecoder
            android.media.MediaExtractor r6 = r1.mAudioExtractor
            int r45 = r6.getSampleFlags()
            r41 = 0
            r42 = r4
            r39 = r5
            r39.queueInputBuffer(r40, r41, r42, r43, r45)
            android.media.MediaExtractor r4 = r1.mAudioExtractor
            r4.advance()
            goto L_0x04d3
        L_0x04d1:
            r24 = 1
        L_0x04d3:
            if (r24 == 0) goto L_0x04e9
            java.lang.String r4 = "audio decoder sending EOS"
            android.util.Log.d(r2, r4)
            android.media.MediaCodec r4 = r1.mInputAudioDecoder
            r43 = 0
            r45 = 4
            r41 = 0
            r42 = 0
            r39 = r4
            r39.queueInputBuffer(r40, r41, r42, r43, r45)
        L_0x04e9:
            boolean r4 = r1.mUserStop
            if (r4 != 0) goto L_0x0516
            if (r25 != 0) goto L_0x0516
            r5 = -1
            if (r3 != r5) goto L_0x0516
            if (r17 == 0) goto L_0x04f8
            boolean r4 = r1.mMuxerStarted
            if (r4 == 0) goto L_0x0516
        L_0x04f8:
            android.media.MediaCodec r4 = r1.mInputAudioDecoder
            r6 = 10000(0x2710, double:4.9407E-320)
            int r4 = r4.dequeueOutputBuffer(r15, r6)
            if (r4 != r5) goto L_0x0508
            java.lang.String r4 = "audio decoder output buffer try again later while decoding"
            android.util.Log.d(r2, r4)
            goto L_0x0516
        L_0x0508:
            r6 = -3
            if (r4 != r6) goto L_0x0518
            java.lang.String r4 = "audio decoder: output buffers changed"
            android.util.Log.d(r2, r4)
            android.media.MediaCodec r4 = r1.mInputAudioDecoder
            java.nio.ByteBuffer[] r18 = r4.getOutputBuffers()
        L_0x0516:
            r4 = r3
            goto L_0x053b
        L_0x0518:
            r5 = -2
            if (r4 != r5) goto L_0x0521
            java.lang.String r4 = "audio decoder: output format changed: "
            android.util.Log.d(r2, r4)
            goto L_0x0516
        L_0x0521:
            if (r4 >= 0) goto L_0x0529
            java.lang.String r4 = "Unexpected result from audio decoder dequeue output format."
            android.util.Log.d(r2, r4)
            goto L_0x0516
        L_0x0529:
            int r5 = r15.flags
            r5 = r5 & 2
            if (r5 == 0) goto L_0x053b
            java.lang.String r5 = "audio decoder: codec config buffer"
            android.util.Log.d(r2, r5)
            android.media.MediaCodec r5 = r1.mInputAudioDecoder
            r7 = 0
            r5.releaseOutputBuffer(r4, r7)
            goto L_0x0516
        L_0x053b:
            boolean r3 = r1.mUserStop
            if (r3 != 0) goto L_0x05bf
            if (r25 != 0) goto L_0x05bf
            r5 = -1
            if (r4 == r5) goto L_0x05bf
            int r3 = r15.size
            long r7 = r15.presentationTimeUs
            android.media.MediaCodec r6 = r1.mOutputAudioEncoder
            r43 = r7
            r7 = 10000(0x2710, double:4.9407E-320)
            int r6 = r6.dequeueInputBuffer(r7)
            if (r6 != r5) goto L_0x055a
            java.lang.String r3 = "audio encoder input buffer try again later"
            android.util.Log.d(r2, r3)
            goto L_0x05bf
        L_0x055a:
            if (r3 < 0) goto L_0x05a9
            int r5 = (r43 > r22 ? 1 : (r43 == r22 ? 0 : -1))
            if (r5 < 0) goto L_0x05a9
            r5 = r37[r6]
            r7 = r18[r4]
            java.nio.ByteBuffer r7 = r7.duplicate()
            int r8 = r15.offset
            r7.position(r8)
            int r8 = r15.offset
            int r8 = r8 + r3
            r7.limit(r8)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r40 = r6
            java.lang.String r6 = "encoderInputBuffer capacity :"
            r8.<init>(r6)
            int r6 = r5.capacity()
            r8.append(r6)
            java.lang.String r6 = ", audioDecoderOutputBuffer size : "
            r8.append(r6)
            r8.append(r3)
            java.lang.String r6 = r8.toString()
            android.util.Log.d(r2, r6)
            r8 = 0
            r5.position(r8)
            r5.put(r7)
            android.media.MediaCodec r5 = r1.mOutputAudioEncoder
            r41 = 0
            int r6 = r15.flags
            r42 = r3
            r39 = r5
            r45 = r6
            r39.queueInputBuffer(r40, r41, r42, r43, r45)
            goto L_0x05aa
        L_0x05a9:
            r8 = 0
        L_0x05aa:
            android.media.MediaCodec r3 = r1.mInputAudioDecoder
            r3.releaseOutputBuffer(r4, r8)
            int r4 = r15.flags
            r4 = r4 & 4
            if (r4 == 0) goto L_0x05be
            java.lang.String r4 = "audio decoder: EOS"
            android.util.Log.d(r2, r4)
            r4 = -1
            r25 = 1
            goto L_0x05bf
        L_0x05be:
            r4 = -1
        L_0x05bf:
            boolean r5 = r1.mUserStop
            if (r5 != 0) goto L_0x05cc
            if (r10 != 0) goto L_0x05cc
            if (r17 == 0) goto L_0x05d1
            boolean r5 = r1.mMuxerStarted
            if (r5 == 0) goto L_0x05cc
            goto L_0x05d1
        L_0x05cc:
            r30 = r4
            r7 = 0
            goto L_0x06a0
        L_0x05d1:
            android.media.MediaCodec r5 = r1.mOutputAudioEncoder
            r6 = 10000(0x2710, double:4.9407E-320)
            int r5 = r5.dequeueOutputBuffer(r14, r6)
            r7 = -1
            if (r5 != r7) goto L_0x05e2
            java.lang.String r5 = "audio encoder output buffer try again later"
            android.util.Log.d(r2, r5)
            goto L_0x05cc
        L_0x05e2:
            r6 = -3
            if (r5 != r6) goto L_0x05f7
            java.lang.String r5 = "audio encoder: output buffers changed"
            android.util.Log.d(r2, r5)
            android.media.MediaCodec r5 = r1.mOutputAudioEncoder
            java.nio.ByteBuffer[] r5 = r5.getOutputBuffers()
            r3 = r4
            r8 = r5
            r5 = r17
        L_0x05f4:
            r7 = 0
            goto L_0x06ae
        L_0x05f7:
            r6 = -2
            if (r5 != r6) goto L_0x0621
            int r5 = r1.mAudioTrackIndex
            if (r5 >= 0) goto L_0x0619
            android.media.MediaCodec r5 = r1.mOutputAudioEncoder
            android.media.MediaFormat r5 = r5.getOutputFormat()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r8 = "audio encoder: output format changed "
            r6.<init>(r8)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            android.util.Log.d(r2, r6)
            r3 = r4
            r8 = r33
            goto L_0x05f4
        L_0x0619:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Audio encoder output format changed after muxer is started."
            r0.<init>(r1)
            throw r0
        L_0x0621:
            if (r5 >= 0) goto L_0x0629
            java.lang.String r5 = "Unexpected result from audio encoder dequeue output format."
            android.util.Log.d(r2, r5)
            goto L_0x05cc
        L_0x0629:
            r6 = r33[r5]
            int r8 = r14.flags
            r8 = r8 & 2
            if (r8 == 0) goto L_0x063d
            java.lang.String r6 = "audio encoder ignoring BUFFER_FLAG_CODEC_CONFIG"
            android.util.Log.d(r2, r6)
            android.media.MediaCodec r6 = r1.mOutputAudioEncoder
            r3 = 0
            r6.releaseOutputBuffer(r5, r3)
            goto L_0x05cc
        L_0x063d:
            int r8 = r14.size
            if (r8 == 0) goto L_0x068c
            r8 = r4
            long r3 = r14.presentationTimeUs
            r30 = r8
            r7 = r28
            int r3 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r3 > 0) goto L_0x066b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "audio encoder writing sample data to muxer  time: "
            r3.<init>(r4)
            long r7 = r14.presentationTimeUs
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r2, r3)
            long r3 = r14.presentationTimeUs
            android.media.MediaMuxer r7 = r1.mMuxer
            int r8 = r1.mAudioTrackIndex
            r7.writeSampleData(r8, r6, r14)
            r28 = r3
            goto L_0x068e
        L_0x066b:
            java.lang.String r3 = "lastAudioSampleWrittenTime : "
            java.lang.String r4 = " > presentationTime :"
            java.lang.StringBuilder r3 = N2.j.j(r7, r3, r4)
            r28 = r7
            long r6 = r14.presentationTimeUs
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r2, r3)
            if (r25 == 0) goto L_0x0684
            goto L_0x068e
        L_0x0684:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Audio time stamps are not in increasing order."
            r0.<init>(r1)
            throw r0
        L_0x068c:
            r30 = r4
        L_0x068e:
            int r3 = r14.flags
            r3 = r3 & 4
            if (r3 == 0) goto L_0x069a
            java.lang.String r3 = "audio encoder: EOS"
            android.util.Log.d(r2, r3)
            r10 = 1
        L_0x069a:
            android.media.MediaCodec r3 = r1.mOutputAudioEncoder
            r7 = 0
            r3.releaseOutputBuffer(r5, r7)
        L_0x06a0:
            r5 = r17
            r3 = r30
        L_0x06a4:
            r8 = r33
            goto L_0x06ae
        L_0x06a7:
            r9 = r4
            r16 = r5
            r7 = 0
            r5 = r17
            goto L_0x06a4
        L_0x06ae:
            boolean r4 = r1.mUserStop
            if (r4 != 0) goto L_0x06bf
            boolean r4 = r1.mMuxerStarted
            if (r4 != 0) goto L_0x06bf
            if (r0 == 0) goto L_0x06bf
            boolean r4 = r1.mCopyAudio
            if (r4 == 0) goto L_0x06c1
            if (r5 == 0) goto L_0x06bf
            goto L_0x06c1
        L_0x06bf:
            r4 = 1
            goto L_0x06e4
        L_0x06c1:
            android.media.MediaMuxer r4 = r1.mMuxer
            int r4 = r4.addTrack(r0)
            r1.mVideoTrackIndex = r4
            boolean r4 = r1.mCopyAudio
            if (r4 == 0) goto L_0x06d5
            android.media.MediaMuxer r4 = r1.mMuxer
            int r4 = r4.addTrack(r5)
            r1.mAudioTrackIndex = r4
        L_0x06d5:
            android.media.MediaMuxer r4 = r1.mMuxer
            int r6 = r1.mOutputOrientationDegrees
            r4.setOrientationHint(r6)
            android.media.MediaMuxer r4 = r1.mMuxer
            r4.start()
            r4 = 1
            r1.mMuxerStarted = r4
        L_0x06e4:
            boolean r6 = r1.mUserStop
            if (r6 == 0) goto L_0x06ee
            java.lang.String r0 = "Encoding abruptly stopped."
            android.util.Log.d(r2, r0)
        L_0x06ed:
            return
        L_0x06ee:
            r17 = r5
            r5 = r16
            r6 = r35
            r7 = r38
            r16 = r0
            r0 = r9
            r9 = r37
            goto L_0x00f2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.pip.core.EncodeVideo.startEncoding():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        if (r7.mEncoding != false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0073, code lost:
        if (r7.mEncoding == false) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0075, code lost:
        release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0079, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stop() {
        /*
            r7 = this;
            java.lang.String r0 = "Stop method finally  mEncoding :"
            java.lang.String r1 = "Stop method finally  mEncoding :"
            java.lang.String r2 = "Stop method finally  mEncoding :"
            java.lang.String r3 = "PIP"
            java.lang.String r4 = "Stop method called "
            android.util.Log.d(r3, r4)
            java.lang.Object r3 = r7.mStopLock
            monitor-enter(r3)
            com.samsung.android.sdk.sgpl.pip.surfaces.OutputSurface r4 = r7.mOutputSurface     // Catch:{ all -> 0x0018 }
            if (r4 == 0) goto L_0x001b
            r4.notifyFrameSyncObject()     // Catch:{ all -> 0x0018 }
            goto L_0x001b
        L_0x0018:
            r7 = move-exception
            goto L_0x0095
        L_0x001b:
            r4 = 1
            r7.mUserStop = r4     // Catch:{ all -> 0x0018 }
            java.lang.String r4 = "PIP"
            java.lang.String r5 = "mUserStop - true"
            android.util.Log.d(r4, r5)     // Catch:{ all -> 0x0018 }
            boolean r4 = r7.mEncoding     // Catch:{ all -> 0x0018 }
            if (r4 != 0) goto L_0x002b
            monitor-exit(r3)     // Catch:{ all -> 0x0018 }
            return
        L_0x002b:
            java.lang.String r4 = "PIP"
            java.lang.String r5 = "Calling wait on stop lock."
            android.util.Log.d(r4, r5)     // Catch:{ InterruptedException -> 0x0053 }
            java.lang.Object r4 = r7.mStopLock     // Catch:{ InterruptedException -> 0x0053 }
            r5 = 5000(0x1388, double:2.4703E-320)
            r4.wait(r5)     // Catch:{ InterruptedException -> 0x0053 }
            java.lang.String r0 = "PIP"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0018 }
            r1.<init>(r2)     // Catch:{ all -> 0x0018 }
            boolean r2 = r7.mEncoding     // Catch:{ all -> 0x0018 }
            r1.append(r2)     // Catch:{ all -> 0x0018 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0018 }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x0018 }
            boolean r0 = r7.mEncoding     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x0078
            goto L_0x0075
        L_0x0051:
            r1 = move-exception
            goto L_0x007a
        L_0x0053:
            r2 = move-exception
            java.lang.String r4 = "PIP"
            java.lang.String r5 = "Stop lock interrupted."
            android.util.Log.d(r4, r5)     // Catch:{ all -> 0x0051 }
            r2.printStackTrace()     // Catch:{ all -> 0x0051 }
            java.lang.String r0 = "PIP"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0018 }
            r2.<init>(r1)     // Catch:{ all -> 0x0018 }
            boolean r1 = r7.mEncoding     // Catch:{ all -> 0x0018 }
            r2.append(r1)     // Catch:{ all -> 0x0018 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0018 }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x0018 }
            boolean r0 = r7.mEncoding     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x0078
        L_0x0075:
            r7.release()     // Catch:{ all -> 0x0018 }
        L_0x0078:
            monitor-exit(r3)     // Catch:{ all -> 0x0018 }
            return
        L_0x007a:
            java.lang.String r2 = "PIP"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0018 }
            r4.<init>(r0)     // Catch:{ all -> 0x0018 }
            boolean r0 = r7.mEncoding     // Catch:{ all -> 0x0018 }
            r4.append(r0)     // Catch:{ all -> 0x0018 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0018 }
            android.util.Log.d(r2, r0)     // Catch:{ all -> 0x0018 }
            boolean r0 = r7.mEncoding     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x0094
            r7.release()     // Catch:{ all -> 0x0018 }
        L_0x0094:
            throw r1     // Catch:{ all -> 0x0018 }
        L_0x0095:
            monitor-exit(r3)     // Catch:{ all -> 0x0018 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.pip.core.EncodeVideo.stop():void");
    }

    private void release(MediaMetadataRetriever mediaMetadataRetriever) {
        if (mediaMetadataRetriever != null) {
            try {
                mediaMetadataRetriever.release();
            } catch (Exception unused) {
            }
        }
    }
}
