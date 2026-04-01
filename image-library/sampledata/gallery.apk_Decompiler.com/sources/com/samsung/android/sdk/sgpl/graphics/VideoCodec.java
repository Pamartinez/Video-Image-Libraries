package com.samsung.android.sdk.sgpl.graphics;

import N2.j;
import W4.b;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.sgpl.graphics.Build;
import com.samsung.android.sdk.sgpl.graphics.CodecConfig;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.FileInputStream;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoCodec {
    final boolean DEBUG;
    final String TAG;
    final VideoCodecImpl codec;

    public VideoCodec(CodecConfig.Builder builder) {
        String str;
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        VideoCodecImpl videoCodecImpl = builder.videoCodecs[0];
        this.codec = videoCodecImpl;
        boolean z = builder.inDebug;
        this.DEBUG = z;
        StringBuilder sb2 = new StringBuilder("codec [");
        sb2.append(videoCodecImpl.getClass().getSimpleName());
        sb2.append("] ");
        if (z) {
            str = " debug";
        } else {
            str = "";
        }
        sb2.append(str);
        Log.i(simpleName, sb2.toString());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ long lambda$calCoverFrameTime$0(long[] jArr) {
        return jArr[1];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$calCoverFrameTime$1(long j2, long[] jArr) {
        if (jArr[1] > j2) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [java.lang.Object, java.util.function.ToLongFunction] */
    public long calCoverFrameTime(MediaMetadataRetriever mediaMetadataRetriever) {
        long j2;
        String str = null;
        try {
            String extractMetadata = mediaMetadataRetriever.extractMetadata(ErrorCodeConvertor.TEMP_AGENT_WIFI_ONLY_MODEL);
            try {
                if (!TextUtils.isEmpty(extractMetadata)) {
                    String[] split = extractMetadata.split(";");
                    if (split.length > 0) {
                        ArrayList arrayList = new ArrayList();
                        for (String split2 : split) {
                            String[] split3 = split2.split(NumericEnum.SEP);
                            arrayList.add(new long[]{Long.parseLong(split3[0]), Long.parseLong(split3[1])});
                        }
                        long[] jArr = (long[]) arrayList.stream().filter(new b((long) ((((float) arrayList.stream().mapToLong(new Object()).sum()) / ((float) arrayList.size())) * 0.2f), 7)).findFirst().orElse((Object) null);
                        if (jArr != null) {
                            j2 = Math.max(0, jArr[0]);
                        } else {
                            j2 = 0;
                        }
                        if (this.DEBUG) {
                            Log.v(this.TAG, "calCoverFrameTime {" + j2 + "}");
                        }
                        return j2;
                    }
                }
                return 0;
            } catch (Exception e) {
                e = e;
                str = extractMetadata;
                Log.e(this.TAG, "calCoverFrameTime failed. e=" + e.getMessage() + " " + str);
                return 0;
            }
        } catch (Exception e7) {
            e = e7;
            Log.e(this.TAG, "calCoverFrameTime failed. e=" + e.getMessage() + " " + str);
            return 0;
        }
    }

    public Bitmap decodeEmbeddedInVideo(MediaMetadataRetriever mediaMetadataRetriever, int i2) {
        long j2;
        Bitmap decodeEmbeddedThumbnail;
        try {
            if (this.DEBUG) {
                j2 = System.currentTimeMillis();
            } else {
                j2 = 0;
            }
            byte[] embeddedPicture = mediaMetadataRetriever.getEmbeddedPicture();
            if (embeddedPicture == null || embeddedPicture.length <= 0 || (decodeEmbeddedThumbnail = new ImageCodecImpl().decodeEmbeddedThumbnail(embeddedPicture, i2)) == null) {
                return null;
            }
            int orientationTag = new ExifReader(embeddedPicture, 0, embeddedPicture.length).getOrientationTag();
            if (orientationTag > 1) {
                decodeEmbeddedThumbnail = BitmapToolkit.rotate(decodeEmbeddedThumbnail, ExifReader.toOrientation(orientationTag), orientationTag, i2);
            } else if (i2 > 0) {
                float max = ((float) i2) / ((float) Math.max(decodeEmbeddedThumbnail.getWidth(), decodeEmbeddedThumbnail.getHeight()));
                if (max < 1.0f) {
                    decodeEmbeddedThumbnail = BitmapToolkit.resize(decodeEmbeddedThumbnail, max);
                }
            }
            if (this.DEBUG) {
                String str = this.TAG;
                Log.v(str, "decodeEmbeddedInVideo" + Log.vars(decodeEmbeddedThumbnail, Integer.valueOf(orientationTag), Integer.valueOf(i2)) + " +" + (System.currentTimeMillis() - j2));
            }
            return decodeEmbeddedThumbnail;
        } catch (Error | Exception e) {
            Log.e(this.TAG, j.i(e, new StringBuilder("decodeEmbeddedInVideo failed. e=")));
            return null;
        }
    }

    public Bitmap decodeFrame(String str, long j2) {
        return decodeFrame(str, j2, 0);
    }

    public Bitmap decodeFrame(String str, long j2, int i2) {
        FileInputStream fileInputStream;
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            fileInputStream = new FileInputStream(str);
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(fileInputStream.getFD());
            Bitmap decodeFrame = decodeFrame(mediaMetadataRetriever, j2, i2);
            mediaMetadataRetriever.close();
            fileInputStream.close();
            return decodeFrame;
            throw th;
            throw th;
        } catch (Error | Exception e) {
            Log.e(this.TAG, j.i(e, new StringBuilder("decodeFrame(file) failed. e=")));
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    public Bitmap decodeFrame(byte[] bArr, long j2) {
        return decodeFrame(bArr, j2, 0);
    }

    public Bitmap decodeFrame(byte[] bArr, long j2, int i2) {
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(new MediaDataSourceImpl(bArr));
            Bitmap decodeFrame = decodeFrame(mediaMetadataRetriever, j2, i2);
            mediaMetadataRetriever.close();
            return decodeFrame;
        } catch (Error | Exception e) {
            Log.e(this.TAG, j.i(e, new StringBuilder("decodeFrame(byte) failed. e=")));
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public Bitmap decodeFrame(MediaMetadataRetriever mediaMetadataRetriever, long j2, int i2) {
        Bitmap decodeEmbeddedInVideo;
        if (Build.SEM.SDK_INT > 150000 && j2 <= 0 && (decodeEmbeddedInVideo = decodeEmbeddedInVideo(mediaMetadataRetriever, i2)) != null) {
            return decodeEmbeddedInVideo;
        }
        if (j2 < 0) {
            j2 = calCoverFrameTime(mediaMetadataRetriever);
        }
        if (i2 > 0) {
            this.codec.adjustVideoSize(mediaMetadataRetriever, i2);
        }
        return this.codec.decodeFrame(mediaMetadataRetriever, j2, 2);
    }
}
