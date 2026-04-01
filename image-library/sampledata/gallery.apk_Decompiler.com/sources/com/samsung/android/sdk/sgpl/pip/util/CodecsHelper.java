package com.samsung.android.sdk.sgpl.pip.util;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.Surface;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.io.IOException;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CodecsHelper {
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

    public static MediaExtractor createExtractor(Context context, Uri uri) {
        MediaExtractor mediaExtractor = new MediaExtractor();
        mediaExtractor.semSetRunningMode(1);
        mediaExtractor.setDataSource(context, uri, (Map) null);
        return mediaExtractor;
    }

    public static MediaMetadataRetriever createMediaMetadataRetriever(Context context, Uri uri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, uri);
        return mediaMetadataRetriever;
    }

    public static MediaCodec createVideoDecoder(MediaFormat mediaFormat, Surface surface) {
        MediaCodec createDecoderByType = MediaCodec.createDecoderByType(getMimeTypeFor(mediaFormat));
        try {
            createDecoderByType.configure(mediaFormat, surface, (MediaCrypto) null, 0);
            createDecoderByType.start();
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
            for (int i2 = 0; i2 < codecCount; i2++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i2);
                if (!codecInfoAt.isEncoder()) {
                    String[] supportedTypes = codecInfoAt.getSupportedTypes();
                    int i7 = 0;
                    while (true) {
                        if (i7 >= supportedTypes.length) {
                            break;
                        } else if (supportedTypes[i7].equalsIgnoreCase(str)) {
                            isSecCodecAvailable = codecInfoAt;
                            break;
                        } else {
                            i7++;
                        }
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
            for (int i2 = 0; i2 < codecCount; i2++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i2);
                if (codecInfoAt.isEncoder()) {
                    String[] supportedTypes = codecInfoAt.getSupportedTypes();
                    int i7 = 0;
                    while (true) {
                        if (i7 >= supportedTypes.length) {
                            break;
                        } else if (supportedTypes[i7].equalsIgnoreCase(str)) {
                            isSecCodecAvailable = codecInfoAt;
                            break;
                        } else {
                            i7++;
                        }
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
            for (int i2 = 0; i2 < MediaCodecList.getCodecCount(); i2++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i2);
                String name = codecInfoAt.getName();
                if (!z || !codecInfoAt.isEncoder()) {
                    if (!z && !codecInfoAt.isEncoder() && name.equals("OMX.SEC.aac.dec")) {
                        String[] supportedTypes = codecInfoAt.getSupportedTypes();
                        for (String equalsIgnoreCase : supportedTypes) {
                            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                                mediaCodecInfo = codecInfoAt;
                            }
                        }
                    }
                } else if (name.equals("OMX.SEC.naac.enc") || name.equals("OMX.SEC.aac.enc")) {
                    String[] supportedTypes2 = codecInfoAt.getSupportedTypes();
                    for (String equalsIgnoreCase2 : supportedTypes2) {
                        if (equalsIgnoreCase2.equalsIgnoreCase(str)) {
                            mediaCodecInfo = codecInfoAt;
                        }
                    }
                }
            }
        }
        return mediaCodecInfo;
    }

    public static boolean isSupportedFormat(MediaMetadataRetriever mediaMetadataRetriever) {
        return Encode.ContentType.sSupportedVideoTypes.contains(mediaMetadataRetriever.extractMetadata(12));
    }

    private static boolean isVideoFormat(MediaFormat mediaFormat) {
        return getMimeTypeFor(mediaFormat).startsWith("video/");
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
        if (i8 >= 345600 || i8 > 76800) {
            return 5000;
        }
        if (i8 >= 40000) {
            return 512;
        }
        return Encode.BitRate.VIDEO_QCIF_BITRATE;
    }

    public static boolean isSupportedFormat(Context context, Uri uri) {
        boolean z = false;
        if (context == null || uri == null) {
            return false;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(context, uri);
            z = mediaMetadataRetriever.extractMetadata(12).contains(Encode.ContentType.VIDEO_MP4);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            try {
                mediaMetadataRetriever.release();
            } catch (Exception unused) {
            }
            throw th;
        }
        try {
            mediaMetadataRetriever.release();
        } catch (Exception unused2) {
        }
        return z;
    }

    public static MediaCodec createAudioDecoder(MediaCodecInfo mediaCodecInfo, MediaFormat mediaFormat) {
        MediaCodec createByCodecName = MediaCodec.createByCodecName(mediaCodecInfo.getName());
        createByCodecName.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 0);
        createByCodecName.start();
        return createByCodecName;
    }
}
