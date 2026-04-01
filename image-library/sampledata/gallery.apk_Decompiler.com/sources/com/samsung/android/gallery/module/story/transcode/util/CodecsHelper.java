package com.samsung.android.gallery.module.story.transcode.util;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.net.Uri;
import android.view.Surface;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CodecsHelper {
    public static MediaCodec createAudioDecoder(MediaFormat mediaFormat) {
        MediaCodec createDecoderByType = MediaCodec.createDecoderByType(getMimeTypeFor(mediaFormat));
        createDecoderByType.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 0);
        createDecoderByType.start();
        return createDecoderByType;
    }

    public static MediaCodec createAudioEncoder(MediaCodecInfo mediaCodecInfo, MediaFormat mediaFormat) {
        MediaCodec createByCodecName = MediaCodec.createByCodecName(mediaCodecInfo.getName());
        createByCodecName.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 1);
        createByCodecName.start();
        return createByCodecName;
    }

    public static MediaExtractor createExtractor(String str) {
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaExtractor.semSetRunningMode(1);
        mediaExtractor.setDataSource(str);
        return mediaExtractor;
    }

    public static MediaCodec createVideoDecoder(MediaFormat mediaFormat, Surface surface) {
        MediaCodec createDecoderByType = MediaCodec.createDecoderByType(getMimeTypeFor(mediaFormat));
        try {
            createDecoderByType.configure(mediaFormat, surface, (MediaCrypto) null, 0);
            createDecoderByType.start();
            Log.e("CodecsHelper", "createVideoDecoder - start");
            return createDecoderByType;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            if (createDecoderByType != null) {
                createDecoderByType.release();
            }
            throw new IOException("createVideoDecode configure error");
        }
    }

    public static int getAndSelectAudioTrackIndex(MediaExtractor mediaExtractor) {
        for (int i2 = 0; i2 < mediaExtractor.getTrackCount(); i2++) {
            if (isAudioFormat(mediaExtractor.getTrackFormat(i2))) {
                mediaExtractor.selectTrack(i2);
                return i2;
            }
        }
        return -1;
    }

    public static int getAndSelectVideoTrackIndex(MediaExtractor mediaExtractor) {
        for (int i2 = 0; i2 < mediaExtractor.getTrackCount(); i2++) {
            if (isVideoFormat(mediaExtractor.getTrackFormat(i2))) {
                mediaExtractor.selectTrack(i2);
                return i2;
            }
        }
        return -1;
    }

    public static MediaCodecInfo getDecoderCodec(String str) {
        MediaCodecInfo isSecCodecAvailable = isSecCodecAvailable(str, false);
        if (isSecCodecAvailable == null) {
            int codecCount = MediaCodecList.getCodecCount();
            boolean z = false;
            for (int i2 = 0; i2 < codecCount; i2++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i2);
                if (!codecInfoAt.isEncoder()) {
                    String[] supportedTypes = codecInfoAt.getSupportedTypes();
                    for (String equalsIgnoreCase : supportedTypes) {
                        if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                            z = true;
                            isSecCodecAvailable = codecInfoAt;
                        }
                    }
                    if (z) {
                        return isSecCodecAvailable;
                    }
                }
            }
        }
        return isSecCodecAvailable;
    }

    public static MediaCodecInfo getEncoderCodec(String str) {
        MediaCodecInfo isSecCodecAvailable = isSecCodecAvailable(str, true);
        if (isSecCodecAvailable == null) {
            int codecCount = MediaCodecList.getCodecCount();
            boolean z = false;
            for (int i2 = 0; i2 < codecCount; i2++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i2);
                if (codecInfoAt.isEncoder()) {
                    String[] supportedTypes = codecInfoAt.getSupportedTypes();
                    for (String equalsIgnoreCase : supportedTypes) {
                        if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                            z = true;
                            isSecCodecAvailable = codecInfoAt;
                        }
                    }
                    if (z) {
                        return isSecCodecAvailable;
                    }
                }
            }
        }
        return isSecCodecAvailable;
    }

    private static String getMimeTypeFor(MediaFormat mediaFormat) {
        return mediaFormat.getString(MediaDefs.Image.HEIF.HEIF_MIME_BOX);
    }

    private static boolean isAudioFormat(MediaFormat mediaFormat) {
        return getMimeTypeFor(mediaFormat).startsWith("audio/");
    }

    private static MediaCodecInfo isSecCodecAvailable(String str, boolean z) {
        MediaCodecInfo mediaCodecInfo = null;
        if (Encode.CodecsMime.AUDIO_CODEC_AAC.equals(str)) {
            boolean z3 = false;
            for (int i2 = 0; i2 < MediaCodecList.getCodecCount(); i2++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i2);
                String name = codecInfoAt.getName();
                if (!z || !codecInfoAt.isEncoder()) {
                    if (!z && !codecInfoAt.isEncoder() && (name.equals("OMX.SEC.aac.dec") || name.equals("c2.sec.aac.decoder"))) {
                        String[] supportedTypes = codecInfoAt.getSupportedTypes();
                        for (String equalsIgnoreCase : supportedTypes) {
                            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                                mediaCodecInfo = codecInfoAt;
                                z3 = true;
                            }
                        }
                    }
                } else if (name.equals("OMX.SEC.naac.enc") || name.equals("OMX.SEC.aac.enc") || name.equals("c2.sec.aac.encoder")) {
                    String[] supportedTypes2 = codecInfoAt.getSupportedTypes();
                    for (String equalsIgnoreCase2 : supportedTypes2) {
                        if (equalsIgnoreCase2.equalsIgnoreCase(str)) {
                            mediaCodecInfo = codecInfoAt;
                            z3 = true;
                        }
                    }
                }
                if (z3) {
                    return mediaCodecInfo;
                }
            }
        }
        return mediaCodecInfo;
    }

    private static boolean isVideoFormat(MediaFormat mediaFormat) {
        return getMimeTypeFor(mediaFormat).startsWith("video/");
    }

    public static void scheduleAfter(int i2, Runnable runnable) {
        ((ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2)).schedule(runnable, (long) i2, TimeUnit.SECONDS);
    }

    public static int suggestBitRate(int i2, int i7) {
        int i8 = i2 * i7;
        if (i8 >= 8294400) {
            return Encode.BitRate.VIDEO_UHD_BITRATE;
        }
        if (i8 >= 3686400) {
            return Encode.BitRate.VIDEO_QHD_BITRATE;
        }
        if (i8 >= 2073600) {
            return Encode.BitRate.VIDEO_FHD_BITRATE;
        }
        if (i8 >= 921600) {
            return Encode.BitRate.VIDEO_HD_BITRATE;
        }
        if (i8 >= 345600 || i8 >= 307200) {
            return 5000;
        }
        if (i8 >= 76800) {
            return 512;
        }
        return Encode.BitRate.VIDEO_QCIF_BITRATE;
    }

    public static MediaCodec createAudioDecoder(MediaCodecInfo mediaCodecInfo, MediaFormat mediaFormat) {
        MediaCodec createByCodecName = MediaCodec.createByCodecName(mediaCodecInfo.getName());
        createByCodecName.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 0);
        createByCodecName.start();
        return createByCodecName;
    }

    public static MediaExtractor createExtractor(FileDescriptor fileDescriptor) {
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaExtractor.semSetRunningMode(1);
        mediaExtractor.setDataSource(fileDescriptor);
        return mediaExtractor;
    }

    public static MediaExtractor createExtractor(Uri uri) {
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaExtractor.semSetRunningMode(1);
        mediaExtractor.setDataSource(AppResources.getAppContext(), uri, new HashMap());
        return mediaExtractor;
    }

    public static MediaExtractor createExtractor(String str, long j2, long j3) {
        Throwable th;
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaExtractor.semSetRunningMode(1);
        FileInputStream fileInputStream = new FileInputStream(str);
        try {
            mediaExtractor.setDataSource(fileInputStream.getFD(), j2, j3);
            fileInputStream.close();
            return mediaExtractor;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }
}
