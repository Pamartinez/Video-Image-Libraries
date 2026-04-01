package com.samsung.android.gallery.support.library.v12.remster;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.media.photoremaster.SemPhotoRemasterManager;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.o3dp.lib3dphotography.utils.RemasterUtil;
import java.util.Collections;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemRemasterManagerCompat extends VslMesDetectorCompat {
    protected final SemPhotoRemasterManager mPhotoRemasterManager = new SemPhotoRemasterManager();

    public void deInit(boolean z) {
        this.mPhotoRemasterManager.deinit();
        if (z) {
            this.mPhotoRemasterManager.setProgressUpdateListener((SemPhotoRemasterManager.ProgressUpdateListener) null);
        }
    }

    public boolean detectImage(Uri uri, String str, long j2, String str2) {
        this.mPhotoRemasterManager.setParameter(1001, uri);
        this.mPhotoRemasterManager.setParameter(1002, str);
        this.mPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_PARAMETER, 10);
        this.mPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, RemasterUtil.REMASTER_SERVICE_PURPOSE_FOR_3D_PHOTO);
        SemPhotoRemasterManager semPhotoRemasterManager = this.mPhotoRemasterManager;
        semPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_NOT_USER_OWNER, "{\"include_list\":" + str2 + "}");
        return this.mPhotoRemasterManager.processImage(1, Collections.singletonList(0));
    }

    public long getEnumEnhanceType() {
        return getLongParameter(2201, Integer.MIN_VALUE);
    }

    public String getFullPathRevitalized() {
        return getParameter(1003);
    }

    public String getParameter(int i2) {
        return this.mPhotoRemasterManager.getParameter(i2);
    }

    public String getTagAnalyzedFull() {
        return getParameter(2101);
    }

    public void init(Context context) {
        this.mPhotoRemasterManager.init(context);
    }

    public boolean isAiEdit(long j2) {
        if (j2 == 19 || j2 == 9 || j2 == 15 || j2 == 17) {
            return true;
        }
        return false;
    }

    public boolean processImage(Uri uri, String str, long j2, int i2, long j3) {
        this.mPhotoRemasterManager.setParameter(1001, uri);
        this.mPhotoRemasterManager.setParameter(1002, str);
        this.mPhotoRemasterManager.setParameter(1004, j2);
        this.mPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_PARAMETER, 10);
        if (isAiEdit(j3)) {
            this.mPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, RemasterUtil.REMASTER_SERVICE_PURPOSE_FOR_3D_PHOTO);
        }
        return this.mPhotoRemasterManager.processImage(i2, VslMesDetectorCompat.decodeEnhances(j3));
    }

    public void setProgressUpdateListener(final Consumer<Double> consumer) {
        this.mPhotoRemasterManager.setProgressUpdateListener(new SemPhotoRemasterManager.ProgressUpdateListener() {
            public void onUpdateProgress(double d, int i2, int i7) {
                consumer.accept(Double.valueOf(d));
            }

            public void onUpdateMetadata(String str) {
            }
        });
    }

    public void setServicePurpose(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mPhotoRemasterManager.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, str);
        }
    }

    public void stop() {
        this.mPhotoRemasterManager.stop();
    }

    public boolean support() {
        return true;
    }

    public String tag() {
        return "SemRemasterManagerCompat";
    }

    public boolean tryInit(Context context) {
        if (this.mPhotoRemasterManager.tryInit(context)) {
            return true;
        }
        Log.w(this.TAG, "fail init");
        return false;
    }
}
