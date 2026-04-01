package com.samsung.android.gallery.support.config;

import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.sdk.moneta.common.Constants;
import com.samsung.android.sdk.scs.base.utils.BaseConstants;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Component$SamsungSearch {
    public static final String AUTHORITY;
    public static final String PACKAGE;
    public static final boolean SDK_B_MR5;

    static {
        String str;
        String str2;
        boolean atLeastSystem = SdkConfig.atLeastSystem(SdkConfig.SEM.B_MR5);
        SDK_B_MR5 = atLeastSystem;
        if (atLeastSystem) {
            str = Constants.SMART_SUGGESTIONS_PACKAGE_NAME;
        } else {
            str = BaseConstants.PACKAGE_INFO.PACKAGE_CORE_SERVICES;
        }
        PACKAGE = str;
        StringBuilder s = C0212a.s(str);
        if (atLeastSystem) {
            str2 = ".search";
        } else {
            str2 = ".ai.search";
        }
        s.append(str2);
        AUTHORITY = s.toString();
    }
}
