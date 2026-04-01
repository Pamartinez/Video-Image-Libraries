package com.samsung.android.gallery.module.story.transcode.util;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.net.Uri;
import android.view.Surface;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.ocr.MOCRLang;
import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TranscodingHelper {
    private static MediaExtractor generateExtractor(String str) {
        try {
            return CodecsHelper.createExtractor(str);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long generateTimestampUS(int i2, int i7) {
        return (1000000 / ((long) i2)) * ((long) i7);
    }

    public static int getHdrType(boolean z, int i2, int i7) {
        if ((i2 == 0 || i2 == 8) && i7 == 10) {
            return 2;
        }
        if (z) {
            return 1;
        }
        return 0;
    }

    public static int getInputVideoOrientation(String str, int i2) {
        int i7;
        if (str != null) {
            try {
                i7 = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                i7 = 0;
            }
            int i8 = (i7 + i2) % 360;
            int i10 = 90;
            if (i8 != 90) {
                i10 = MOCRLang.KHMER;
                if (i8 != 180) {
                    i10 = 270;
                    if (i8 != 270) {
                        return 0;
                    }
                }
            }
            return i10;
        }
        return 0;
    }

    public static int getVideoFrameRate(String str, int i2) {
        MediaExtractor generateExtractor = generateExtractor(str);
        return generateExtractor != null ? getVideoFrameRate(generateExtractor, i2) : i2;
    }

    public static boolean supportHdrToSdr() {
        return SeApiCompat.getFloatingFeatureBoolean("SEC_FLOATING_FEATURE_MMFW_SUPPORT_HDR2SDR");
    }

    private static MediaExtractor generateExtractor(Uri uri) {
        try {
            return CodecsHelper.createExtractor(uri);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getVideoFrameRate(Uri uri, int i2) {
        MediaExtractor generateExtractor = generateExtractor(uri);
        return generateExtractor != null ? getVideoFrameRate(generateExtractor, i2) : i2;
    }

    private static int getVideoFrameRate(MediaExtractor mediaExtractor, int i2) {
        int i7;
        int i8;
        int i10;
        int i11;
        MediaExtractor mediaExtractor2 = mediaExtractor;
        int andSelectVideoTrackIndex = CodecsHelper.getAndSelectVideoTrackIndex(mediaExtractor2);
        int i12 = -1;
        if (andSelectVideoTrackIndex == -1) {
            Log.e("TranscodingHelper", "Valid video track absent");
            return i2;
        }
        MediaCodec createVideoDecoder = CodecsHelper.createVideoDecoder(mediaExtractor2.getTrackFormat(andSelectVideoTrackIndex), (Surface) null);
        ByteBuffer[] inputBuffers = createVideoDecoder.getInputBuffers();
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int i13 = 0;
        int i14 = i2;
        long j2 = 0;
        boolean z = false;
        int i15 = 0;
        int i16 = 0;
        boolean z3 = false;
        while (true) {
            if (z && z3) {
                break;
            }
            int dequeueInputBuffer = createVideoDecoder.dequeueInputBuffer(1000000);
            if (dequeueInputBuffer != i12) {
                int i17 = i16;
                int readSampleData = mediaExtractor2.readSampleData(inputBuffers[dequeueInputBuffer], i13);
                i7 = i17;
                i8 = 5;
                int i18 = i15;
                createVideoDecoder.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, mediaExtractor2.getSampleTime(), mediaExtractor2.getSampleFlags());
                mediaExtractor2.advance();
                z = i18 >= 5 || readSampleData < 0;
                i15 = i18 + 1;
            } else {
                int i19 = i15;
                i7 = i16;
                i8 = 5;
            }
            int dequeueOutputBuffer = createVideoDecoder.dequeueOutputBuffer(bufferInfo, 10000);
            if (dequeueOutputBuffer == -1 || dequeueOutputBuffer == -3 || dequeueOutputBuffer == -2) {
                i10 = i7;
                Log.d("TranscodingHelper", "getVideoFrameRate, no output buffer ", Integer.valueOf(dequeueOutputBuffer));
                i16 = i10;
            } else if ((bufferInfo.flags & 2) != 0) {
                Log.d("TranscodingHelper", "getVideoFrameRate, codec config buffer ");
                createVideoDecoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                i10 = i7;
                i16 = i10;
            } else {
                long j3 = bufferInfo.presentationTimeUs;
                createVideoDecoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                Log.d("TranscodingHelper", "getVideoFrameRate, output buffer", Integer.valueOf(dequeueOutputBuffer), Long.valueOf(j3), Long.valueOf(j2), Integer.valueOf(i7));
                if (i7 > 0) {
                    try {
                        i11 = i7;
                        i14 = (int) (1000 / (((j3 - j2) / 1000) / ((long) i11)));
                    } catch (Exception e) {
                        Log.e("TranscodingHelper", "getVideoFrameRate, invalid presentationTime, use default fps");
                        e.printStackTrace();
                        return i2;
                    }
                } else {
                    i11 = i7;
                    j2 = j3;
                }
                z3 = i11 >= i8;
                i16 = i11 + 1;
            }
            i12 = -1;
            i13 = 0;
        }
        createVideoDecoder.release();
        mediaExtractor2.release();
        return i14 > 0 ? i14 : i2;
    }
}
