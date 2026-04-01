package com.samsung.android.gallery.support.library.v9;

import A4.C0386v;
import N2.j;
import android.content.Context;
import android.graphics.Rect;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.media.imagecrop.SemCroppedImageInfo;
import com.samsung.android.media.imagecrop.SemImageCrop;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import h4.C0464a;
import i.C0212a;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem111ApiCompatImpl extends Sem110ApiCompatImpl {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HapticOnDcMotor {
        static Boolean supportTypeA;

        public static boolean isTypeASupported(Vibrator vibrator) {
            if (supportTypeA == null) {
                boolean z = true;
                if (vibrator.semGetSupportedVibrationType() != 1) {
                    z = false;
                }
                supportTypeA = Boolean.valueOf(z);
            }
            return supportTypeA.booleanValue();
        }
    }

    public String getQuickCropFormats() {
        try {
            int[] supportedFormat = SemImageCrop.getInstance().getSupportedFormat();
            if (supportedFormat == null || supportedFormat.length <= 0) {
                return "";
            }
            return (String) Arrays.stream(supportedFormat).mapToObj(new C0386v(2, this)).filter(new C0464a(13)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getQuickCropFormats failed e="), this.TAG);
            return "";
        }
    }

    public ByteBuffer getQuickCropStream(File file, Rect rect) {
        FileInputStream fileInputStream;
        FileChannel channel;
        SemCroppedImageInfo semCroppedImageInfo;
        ByteBuffer byteBuffer;
        try {
            fileInputStream = new FileInputStream(file);
            channel = fileInputStream.getChannel();
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect((int) channel.size());
            channel.read(allocateDirect);
            allocateDirect.flip();
            Method method = Reflector.getMethod(SemImageCrop.class, "crop", ByteBuffer.class, Rect.class);
            if (method == null) {
                semCroppedImageInfo = null;
            } else {
                semCroppedImageInfo = (SemCroppedImageInfo) method.invoke(SemImageCrop.getInstance(), new Object[]{allocateDirect, rect});
            }
            if (semCroppedImageInfo == null) {
                byteBuffer = null;
            } else {
                byteBuffer = semCroppedImageInfo.getByteBuffer();
            }
            channel.close();
            fileInputStream.close();
            return byteBuffer;
            throw th;
            throw th;
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getQuickCropStream failed e="), this.TAG);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    public String getSdcardId(Context context) {
        String str;
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            if (storageManager != null) {
                str = storageManager.semGetExternalSdCardId();
            } else {
                str = "";
            }
            if (str != null) {
                return str;
            }
            return "";
        } catch (Exception e) {
            j.C(e, new StringBuilder("getSdcardId failed e="), this.TAG);
        }
    }

    public boolean isSdcardHealthy(Context context) {
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            if (storageManager == null || storageManager.semGetExternalSdCardHealthState() != 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            j.C(e, new StringBuilder("isSdcardHealthy failed e="), this.TAG);
            return true;
        }
    }

    public void performHaptic(Vibrator vibrator, int i2) {
        if ((i2 == 108 || i2 == 1) && HapticOnDcMotor.isTypeASupported(vibrator)) {
            i2 = 100;
        }
        if (i2 != 100) {
            super.performHaptic(vibrator, i2);
        } else if (HapticOnDcMotor.isTypeASupported(vibrator)) {
            super.performHaptic(vibrator, i2);
        }
    }

    public String toMimeType(int i2) {
        if (i2 == 1) {
            return "image/jpeg";
        }
        if (i2 == 2) {
            return "image/heic";
        }
        return null;
    }
}
