package com.samsung.android.gallery.module.trash.factory;

import android.content.Context;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TrashFactory {
    public static AbsTrashFactory getInstance(Context context) {
        if (SdkConfig.atLeast(SdkConfig.GED.V)) {
            return new MpVTrashFactory(context);
        }
        if (SdkConfig.atLeast(SdkConfig.GED.U)) {
            return new MpUTrashFactory(context);
        }
        if (SdkConfig.atLeast(SdkConfig.GED.T) && Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
            return new MpTTrashFactory(context);
        }
        if (SdkConfig.atLeast(SdkConfig.GED.S)) {
            return new MpSTrashFactory(context);
        }
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            return new MpRTrashFactory(context);
        }
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            return new MpQTrashFactory(context);
        }
        if (Features.isEnabled(Features.USE_NEWMP)) {
            return new MpTrashFactory(context);
        }
        return new CmhTrashFactory(context);
    }
}
