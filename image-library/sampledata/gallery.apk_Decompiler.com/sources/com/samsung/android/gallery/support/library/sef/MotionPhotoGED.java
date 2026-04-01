package com.samsung.android.gallery.support.library.sef;

import android.util.Log;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MotionPhotoGED {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MotionPhotoInfoGED {
        public final boolean isMotionPhotoV2;
        public final long length;
        public final long offset;

        public MotionPhotoInfoGED(long j2, long j3, boolean z) {
            this.offset = j2;
            this.length = j3;
            this.isMotionPhotoV2 = z;
        }
    }

    private static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                Log.w("MotionPhotoGED", "close failed " + e);
            }
        }
    }

    public static MotionPhotoInfoGED getSEFDataPositionGED(File file, String str) {
        boolean z;
        long j2;
        long j3;
        try {
            long[] dataPosition = SefParser.getDataPosition(file.getAbsolutePath(), str);
            if (dataPosition != null) {
                long j8 = dataPosition[0];
                long j10 = dataPosition[1];
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    fileInputStream.getChannel().position(j8);
                    byte[] bArr = new byte[4];
                    fileInputStream.read(bArr, 0, 4);
                    z = Arrays.equals(bArr, "mpv2".getBytes(StandardCharsets.UTF_8));
                    if (z) {
                        fileInputStream.getChannel().position(4 + j8);
                        fileInputStream.read(bArr, 0, 4);
                        long j11 = (((long) bArr[3]) & 255) | ((((long) bArr[2]) << 8) & 65280) | ((((long) bArr[1]) << 16) & 16711680) | (((long) bArr[0]) << 24);
                        fileInputStream.getChannel().position(j8 + 8);
                        fileInputStream.read(bArr, 0, 4);
                        long j12 = (((long) bArr[3]) & 255) | ((((long) bArr[2]) << 8) & 65280) | ((((long) bArr[1]) << 16) & 16711680) | (((long) bArr[0]) << 24);
                        Log.i(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, "This file is a MotionPhoto V2 format - offset:" + j11 + " length:" + j12);
                        j10 = j12;
                        j8 = j11;
                    } else {
                        Log.i(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, "This file is not a MotionPhoto V2 format - offset:" + j8 + " length:" + j10);
                    }
                    closeSilently(fileInputStream);
                    j2 = j10;
                    j3 = j8;
                } catch (Throwable th) {
                    closeSilently(fileInputStream);
                    throw th;
                }
            } else {
                z = false;
                j3 = 0;
                j2 = 0;
            }
            return new MotionPhotoInfoGED(j3, j2, z);
        } catch (Exception unused) {
            Log.i(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, "position is not valid");
            return null;
        }
    }
}
