package com.samsung.android.gallery.module.drm;

import android.content.Context;
import android.drm.DrmInfo;
import android.drm.DrmManagerClient;
import android.text.TextUtils;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.DrmStoreCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrmManager {
    private static volatile DrmManager sInstance;
    private final DrmManagerClient mDrmClient = new DrmManagerClient(AppResources.getAppContext());
    private final DrmStoreCompat mDrmStoreCompat = SeApiCompat.getDrmStoreCompat();

    private DrmManager() {
    }

    private DrmInfo getDecryptedImage(String str, DrmManagerClient drmManagerClient) {
        return drmManagerClient.acquireDrmInfo(this.mDrmStoreCompat.createDrmDecryptRequest(str));
    }

    public static DrmManager getInstance() {
        if (sInstance == null) {
            synchronized (DrmManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new DrmManager();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private boolean hasValidRights(String str, DrmManagerClient drmManagerClient) {
        if (drmManagerClient.checkRightsStatus(str, 7) == 0) {
            return true;
        }
        return false;
    }

    public byte[] getByteArray(String str) {
        boolean z;
        DrmManagerClient drmManagerClient = new DrmManagerClient((Context) null);
        if (!hasValidRights(str, drmManagerClient)) {
            Log.d("DrmManager", "RightsStatus is invalid");
            return null;
        }
        DrmInfo decryptedImage = getDecryptedImage(str, drmManagerClient);
        if (decryptedImage != null && this.mDrmStoreCompat.isDrmInfoSuccess(decryptedImage)) {
            return decryptedImage.getData();
        }
        StringBuilder sb2 = new StringBuilder("DrmInfo decrypt failed. drmInfo=");
        if (decryptedImage != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        Log.e("DrmManager", sb2.toString());
        return null;
    }

    public boolean isValidRights(String str) {
        if (TextUtils.isEmpty(str) || this.mDrmClient.checkRightsStatus(str) != 0) {
            return false;
        }
        return true;
    }
}
