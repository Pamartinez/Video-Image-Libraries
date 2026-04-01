package com.samsung.android.gallery.support.library.v12.media;

import N2.j;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem141MediaCaptureCompat extends Sem130MediaCaptureCompat {
    public void setDynamicViewFormat(int i2) {
        if (i2 != -1) {
            try {
                this.mMediaCapture.setParameter(ErrorCodeConvertor.TEMP_AGENT_NOT_ACTIVATED, i2);
            } catch (Exception e) {
                j.C(e, new StringBuilder("setParamAndFormat failed. e="), this.TAG);
            }
        }
        super.setDynamicViewFormat(i2);
    }
}
