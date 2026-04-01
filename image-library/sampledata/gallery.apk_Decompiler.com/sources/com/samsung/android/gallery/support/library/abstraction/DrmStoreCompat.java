package com.samsung.android.gallery.support.library.abstraction;

import N2.j;
import android.drm.DrmInfo;
import android.drm.DrmInfoRequest;
import android.util.Log;
import c0.C0086a;
import com.samsung.scsp.media.file.FileApiContract;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrmStoreCompat {
    public DrmStoreCompat() {
        Log.v("DrmStoreCompat", "constructor");
    }

    private DrmInfoRequest createDrmInfoRequest(int i2, String str) {
        try {
            DrmInfoRequest drmInfoRequest = new DrmInfoRequest(i2, "application/vnd.oma.drm.content");
            drmInfoRequest.put("drm_path", str);
            if (i2 != 10) {
                return drmInfoRequest;
            }
            drmInfoRequest.put(FileApiContract.Parameter.LENGTH, Long.valueOf(new File(str).length()).toString());
            return drmInfoRequest;
        } catch (Exception e) {
            StringBuilder o2 = C0086a.o(i2, "createDrmInfoRequest + {", "} failed e=");
            o2.append(e.getMessage());
            Log.e("DrmStoreCompat", o2.toString());
            return new DrmInfoRequest(4, "application/vnd.oma.drm.content");
        }
    }

    public DrmInfoRequest createDrmDecryptRequest(String str) {
        return createDrmInfoRequest(10, str);
    }

    public boolean isDrmInfoSuccess(DrmInfo drmInfo) {
        if (drmInfo != null) {
            try {
                if ("success".equals(drmInfo.get("status").toString())) {
                    return true;
                }
            } catch (Exception e) {
                j.D(e, new StringBuilder("isDrmSuccess failed e="), "DrmStoreCompat");
            }
        }
        return false;
    }
}
