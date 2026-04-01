package com.sec.longexposure;

import N2.j;
import com.samsung.android.media.SemExtendedFormat;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoInfo;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LongExposureSef {
    public static boolean addMotionPhotoSef(String str, String str2, int i2) {
        File file = new File(str);
        byte[] longExposureSefData = getLongExposureSefData(i2);
        long retrieveDuration = LongExposureInfo.retrieveDuration(str2);
        if (retrieveDuration >= 400) {
            try {
                MotionPhotoInfo.edit(file).addSEFData((int) MediaDefs.Meta.SEF.SEF_KEY_TYPE_LONG_EXPOSURE_EFFECT_INFO, MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO, longExposureSefData).addSEFData(2608, "MotionPhoto_Data", new MediaFile(new File(str2))).setCaptureTimestampUs(retrieveDuration * 1000).commit();
                return true;
            } catch (Exception e) {
                j.C(e, new StringBuilder("add sef failed: "), "LongExposureSef");
                return false;
            }
        } else {
            MotionPhotoInfo.edit(file).addSEFData((int) MediaDefs.Meta.SEF.SEF_KEY_TYPE_LONG_EXPOSURE_EFFECT_INFO, MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO, longExposureSefData).commit();
            return true;
        }
    }

    public static int appendSEF(String str, int i2) {
        byte[] longExposureSefData = getLongExposureSefData(i2);
        if (longExposureSefData == null) {
            return -20;
        }
        try {
            SemExtendedFormat.addData(new File(str), MediaDefs.Meta.SEF.SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO, longExposureSefData, MediaDefs.Meta.SEF.SEF_KEY_TYPE_LONG_EXPOSURE_EFFECT_INFO, 1);
            return 0;
        } catch (IOException unused) {
            return -20;
        }
    }

    private static byte[] getLongExposureSefData(int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeInt(1);
            dataOutputStream.writeInt(i2);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            return null;
        }
    }
}
