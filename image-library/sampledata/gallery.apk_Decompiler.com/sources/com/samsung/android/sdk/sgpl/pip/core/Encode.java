package com.samsung.android.sdk.sgpl.pip.core;

import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.media.MediaCodec;
import android.media.MediaMuxer;
import android.util.Log;
import com.samsung.android.sdk.sgpl.pip.util.Constants;
import java.io.File;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Encode {
    private static final long ONE_BILLION = 1000000000;
    protected static final int ORIENTATION_0 = 0;
    protected static final int ORIENTATION_180 = 180;
    protected static final int ORIENTATION_270 = 270;
    protected static final int ORIENTATION_90 = 90;
    private static final String VERSION = "1.00";
    protected int mAudioTrackIndex = -1;
    protected BGImage mBGImage;
    protected boolean mBGImagePresent;
    protected EncodeEventListener mEncodeEventListener;
    protected EncodeProgressListener mEncodeProgressListener;
    protected MediaCodec mInputAudioDecoder;
    protected MediaCodec mInputVideoDecoder;
    protected MediaMuxer mMuxer;
    protected boolean mMuxerStarted;
    protected int mOutputAudioAACProfile = 2;
    protected int mOutputAudioBitRate = 128000;
    protected int mOutputAudioChannelCount = 2;
    protected MediaCodec mOutputAudioEncoder;
    protected String mOutputAudioMimeType = CodecsMime.AUDIO_CODEC_AAC;
    protected int mOutputAudioSampleRateHZ = 44100;
    protected String mOutputFilePath;
    protected int mOutputFormat = 0;
    protected int mOutputHeight;
    protected int mOutputVideoBitRate;
    protected MediaCodec mOutputVideoEncoder;
    protected int mOutputVideoFrameRate = 30;
    protected int mOutputVideoIFrameInterval = 1;
    protected String mOutputVideoMimeType = CodecsMime.VIDEO_CODEC_H264;
    protected int mOutputWidth;
    protected volatile boolean mPrepared = false;
    protected volatile boolean mUserStop = false;
    protected int mVideoTrackIndex = -1;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BGImage {
        public static final int LEFT_MARGIN = 20;
        public static final int TOP_MARGIN = 20;
        public Bitmap mBitmap;
        public int mDrawHeight;
        public int mDrawWidth;
        public int mOrientation;
        public int mTopX;
        public int mTopY;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BitRate {
        public static final int AUDIO_AAC_BITRATE = 128;
        public static final int MID_AUDIO_AAC_BITRATE = 64;
        public static final int MIN_AUDIO_AAC_BITRATE = 32;
        public static final int MIN_AUDIO_AMR_BITRATE = 8;
        public static final int VIDEO_D1_BITRATE = 5000;
        public static final int VIDEO_FHD_BITRATE = 13000;
        public static final int VIDEO_HD_BITRATE = 8000;
        public static final int VIDEO_QCIF_BITRATE = 280;
        public static final int VIDEO_QHD_BITRATE = 18000;
        public static final int VIDEO_QVGA_BITRATE = 512;
        public static final int VIDEO_UHD_BITRATE = 35000;
        public static final int VIDEO_VGA_BITRATE = 5000;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CodecType {
        public static final int AUDIO_CODEC_AAC = 2;
        public static final int AUDIO_CODEC_AMR = 1;
        public static final int VIDEO_CODEC_H263 = 3;
        public static final int VIDEO_CODEC_H264 = 4;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CodecsMime {
        public static final String AUDIO_CODEC_AAC = "audio/mp4a-latm";
        public static final String AUDIO_CODEC_AMR = "audio/3gpp";
        public static final String VIDEO_CODEC_H263 = "video/3gpp";
        public static final String VIDEO_CODEC_H264 = "video/avc";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ContentType {
        public static final String VIDEO_3G2 = "video/3gpp2";
        public static final String VIDEO_3GP = "video/3gp";
        public static final String VIDEO_3GPP = "video/3gpp";
        public static final String VIDEO_ASF = "video/x-ms-asf";
        public static final String VIDEO_AVI = "video/avi";
        public static final String VIDEO_DIVX = "video/divx";
        public static final String VIDEO_FLV = "video/flv";
        public static final String VIDEO_MKV = "video/x-matroska";
        public static final String VIDEO_MP4 = "video/mp4";
        public static final String VIDEO_MP4V_ES = "video/mp4v-es";
        public static final String VIDEO_MPEG = "video/mpeg";
        public static final String VIDEO_MPEG2TS = "video/mp2ts";
        public static final String VIDEO_WEBM = "video/webm";
        public static final String VIDEO_WMV = "video/x-ms-wmv";
        public static final ArrayList<String> sSupportedVideoTypes;

        static {
            ArrayList<String> arrayList = new ArrayList<>();
            sSupportedVideoTypes = arrayList;
            arrayList.add("video/3gpp");
            arrayList.add(VIDEO_3G2);
            arrayList.add(VIDEO_MP4);
            arrayList.add(VIDEO_MP4V_ES);
            arrayList.add(VIDEO_3GP);
            arrayList.add(VIDEO_AVI);
            arrayList.add(VIDEO_WMV);
            arrayList.add(VIDEO_ASF);
            arrayList.add(VIDEO_DIVX);
            arrayList.add(VIDEO_MPEG);
            arrayList.add(VIDEO_MKV);
            arrayList.add(VIDEO_FLV);
            arrayList.add(VIDEO_MPEG2TS);
            arrayList.add(VIDEO_WEBM);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface EncodeEventListener {
        void onCompleted();

        void onStarted();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EncodeResolution {

        /* renamed from: D1  reason: collision with root package name */
        public static final int f1676D1 = 4;
        public static final int FULL_HD = 6;
        public static final int HD = 5;
        public static final int QCIF = 1;
        public static final int QHD = 7;
        public static final int QVGA = 2;
        public static final int UHD = 8;
        public static final int VGA = 3;

        public static boolean isSupportedResolution(int i2) {
            if (i2 < 1 || i2 > 6) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TrimInfo {
        private boolean mActive = true;
        private long mEndTimeUs;
        private long mStartTimeUs;

        public TrimInfo(long j2, long j3) {
            this.mStartTimeUs = j2;
            this.mEndTimeUs = j3;
        }

        public static TrimInfo create(long j2, long j3) {
            return new TrimInfo(j2, j3);
        }

        public void complete() {
            this.mActive = false;
        }

        public long getEndTime() {
            return this.mEndTimeUs;
        }

        public long getStartTime() {
            return this.mStartTimeUs;
        }

        public boolean isActive() {
            return this.mActive;
        }

        public void setEndTime(long j2) {
            this.mEndTimeUs = j2;
        }

        public void setStartTime(long j2) {
            this.mStartTimeUs = j2;
        }
    }

    private static int exifToDegrees(int i2) {
        if (i2 == 6) {
            return 90;
        }
        if (i2 == 3) {
            return 180;
        }
        if (i2 == 8) {
            return ORIENTATION_270;
        }
        return 0;
    }

    private int getImageOrientation(String str) {
        try {
            return exifToDegrees(new ExifInterface(str).getAttributeInt("Orientation", 1));
        } catch (Exception e) {
            Log.d(Constants.TAG, "Exception in releasing input audio decoder.");
            e.printStackTrace();
            return -1;
        }
    }

    private void printVersionInfo() {
        Log.e(Constants.TAG, "Transcode Framework v.1.00");
    }

    private void setBGImageData(Bitmap bitmap, int i2, int i7) {
        BGImage bGImage = new BGImage();
        this.mBGImage = bGImage;
        bGImage.mBitmap = bitmap;
        bGImage.mDrawWidth = i2;
        bGImage.mDrawHeight = i7;
        bGImage.mTopX = 20;
        bGImage.mTopY = 20;
        this.mBGImagePresent = true;
    }

    private void setImageOrientation(int i2) {
        this.mBGImage.mOrientation = i2;
    }

    public void encode() {
        printVersionInfo();
        try {
            prepare();
            Log.d(Constants.TAG, "encoder preparation done.");
            this.mMuxer = new MediaMuxer(this.mOutputFilePath, this.mOutputFormat);
            this.mMuxerStarted = false;
            this.mVideoTrackIndex = -1;
            this.mAudioTrackIndex = -1;
            Log.d(Constants.TAG, "starting to encode");
            EncodeEventListener encodeEventListener = this.mEncodeEventListener;
            if (encodeEventListener != null) {
                encodeEventListener.onStarted();
            }
            EncodeProgressListener encodeProgressListener = this.mEncodeProgressListener;
            if (encodeProgressListener != null) {
                encodeProgressListener.onStarted();
            }
            startEncoding();
            Log.d(Constants.TAG, "encoding finished.");
            release();
            long length = new File(this.mOutputFilePath).length();
            Log.d(Constants.TAG, "generated output file size after muxer close " + length);
            if (this.mEncodeEventListener != null) {
                if (!this.mUserStop) {
                    Log.d(Constants.TAG, "calling onCompleted");
                    this.mEncodeEventListener.onCompleted();
                } else {
                    Log.d(Constants.TAG, "user stopped. Not calling onCompleted");
                }
                this.mEncodeEventListener = null;
            }
            if (this.mEncodeProgressListener != null) {
                if (!this.mUserStop) {
                    Log.d(Constants.TAG, "calling onCompleted");
                    this.mEncodeProgressListener.onCompleted();
                } else {
                    Log.d(Constants.TAG, "user stopped. Not calling onCompleted");
                }
                this.mEncodeProgressListener = null;
            }
        } catch (Throwable th) {
            release();
            throw th;
        }
    }

    public abstract void prepare();

    public abstract void release();

    public void setBGImage(Bitmap bitmap) {
        if (bitmap != null) {
            setBGImageData(bitmap, bitmap.getWidth(), bitmap.getHeight());
            setImageOrientation(0);
        }
    }

    public void setEncodeProgressListener(EncodeProgressListener encodeProgressListener) {
        this.mEncodeProgressListener = encodeProgressListener;
    }

    public void setProgressUpdateListener(EncodeEventListener encodeEventListener) {
        this.mEncodeEventListener = encodeEventListener;
    }

    public abstract void startEncoding();

    public abstract void stop();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface EncodeProgressListener {
        void onCompleted() {
        }

        void onStarted() {
        }

        void onProgressChanged(int i2) {
        }
    }
}
