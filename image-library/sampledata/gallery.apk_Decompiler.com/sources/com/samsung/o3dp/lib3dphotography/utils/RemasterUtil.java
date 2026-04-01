package com.samsung.o3dp.lib3dphotography.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.SemSystemProperties;
import com.samsung.android.media.photoremaster.SemPhotoRemasterManager;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterUtil {
    public static final int REMASTER_DEPTH_ESTIMATION = 21;
    public static final int REMASTER_FIND_ENHANCEMENT_TYPE_FOR_3D_PHOTO = 1;
    public static final int REMASTER_HUMANDET = 26;
    public static final int REMASTER_INPAINTING = 22;
    public static final int REMASTER_PETDET = 24;
    public static final int REMASTER_PROCESS_TYPE_FOR_3D_PHOTO = 3;
    public static final String REMASTER_SERVICE_PURPOSE_FOR_3D_PHOTO = "AIEDIT";
    private static final String TAG = "RemasterUtil";

    public static boolean isAtLeastOneUi7_0() {
        if (Build.VERSION.SEM_PLATFORM_INT >= 160000 || isVST()) {
            return true;
        }
        return false;
    }

    public static boolean isAtLeastOneUi8_0() {
        if (Build.VERSION.SEM_PLATFORM_INT >= 170000) {
            return true;
        }
        return false;
    }

    public static boolean isAtLeastOneUi8_5() {
        if (Build.VERSION.SEM_PLATFORM_INT >= 170500) {
            return true;
        }
        return false;
    }

    private static boolean isVST() {
        String str = SemSystemProperties.get("ro.build.characteristics");
        if (str == null || !str.contains("HMD")) {
            return false;
        }
        return true;
    }

    public static Bitmap requestRemasterService(Context context, Bitmap bitmap, String str, int i2, int i7) {
        SemPhotoRemasterManager semPhotoRemasterManager = new SemPhotoRemasterManager();
        try {
            semPhotoRemasterManager.init(context);
            semPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_VOLLEY_CANCELED, bitmap);
            semPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, str);
            semPhotoRemasterManager.processImage(i2, Collections.singletonList(Integer.valueOf(i7)));
            Bitmap bitmapParameter = semPhotoRemasterManager.getBitmapParameter(2203);
            try {
                semPhotoRemasterManager.deinit();
                return bitmapParameter;
            } catch (RuntimeException e) {
                LogUtil.w(TAG, "Failed to deinit PhotoRemasterService: engineType " + i7 + " : " + e);
                return bitmapParameter;
            }
        } catch (Exception e7) {
            LogUtil.w(TAG, "Failed to request to PhotoRemasterService: engineType " + i7 + " : " + e7);
            try {
                semPhotoRemasterManager.deinit();
                return null;
            } catch (RuntimeException e8) {
                LogUtil.w(TAG, "Failed to deinit PhotoRemasterService: engineType " + i7 + " : " + e8);
                return null;
            }
        } catch (Throwable th) {
            try {
                semPhotoRemasterManager.deinit();
            } catch (RuntimeException e9) {
                LogUtil.w(TAG, "Failed to deinit PhotoRemasterService: engineType " + i7 + " : " + e9);
            }
            throw th;
        }
    }

    public static String requestRemasterServiceObjDet(Context context, Bitmap bitmap, int i2) {
        SemPhotoRemasterManager semPhotoRemasterManager = new SemPhotoRemasterManager();
        try {
            semPhotoRemasterManager.init(context);
            semPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_VOLLEY_CANCELED, bitmap);
            semPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_NOT_USER_OWNER, StringUtil.format("{\"include_list\":[%d]}", Integer.valueOf(i2)));
            semPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, REMASTER_SERVICE_PURPOSE_FOR_3D_PHOTO);
            semPhotoRemasterManager.processImage(1, Collections.singletonList(0));
            String parameter = semPhotoRemasterManager.getParameter(2101);
            try {
                semPhotoRemasterManager.deinit();
                return parameter;
            } catch (RuntimeException e) {
                LogUtil.w(TAG, "Failed to deinit PhotoRemasterService: engineType " + i2 + " : " + e);
                return parameter;
            }
        } catch (Exception e7) {
            LogUtil.w(TAG, "Failed to requestRemasterServiceObjDet() " + e7);
            try {
                semPhotoRemasterManager.deinit();
                return null;
            } catch (RuntimeException e8) {
                LogUtil.w(TAG, "Failed to deinit PhotoRemasterService: engineType " + i2 + " : " + e8);
                return null;
            }
        } catch (Throwable th) {
            try {
                semPhotoRemasterManager.deinit();
            } catch (RuntimeException e9) {
                LogUtil.w(TAG, "Failed to deinit PhotoRemasterService: engineType " + i2 + " : " + e9);
            }
            throw th;
        }
    }
}
