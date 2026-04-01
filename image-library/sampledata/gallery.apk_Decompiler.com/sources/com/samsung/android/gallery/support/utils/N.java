package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.config.SdkConfig;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class N implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3171a;

    public /* synthetic */ N(int i2) {
        this.f3171a = i2;
    }

    public final boolean getAsBoolean() {
        switch (this.f3171a) {
            case 0:
                return SdkConfig.atLeast(SdkConfig.GED.Q);
            case 1:
                return PreferenceFeatures.isEnabled(PreferenceFeatures.VisualSearch71);
            case 2:
                return SdkConfig.atLeast(SdkConfig.SEM.B);
            case 3:
                return SdkConfig.atLeast(SdkConfig.SEM.Q_MR1);
            case 4:
                return SdkConfig.atLeast(SdkConfig.SEM.B);
            case 5:
                return PreferenceFeatures.lambda$static$63();
            case 6:
                return SdkConfig.atLeast(SdkConfig.SEM.B_MR5);
            case 7:
                return SdkConfig.atLeast(SdkConfig.SEM.B_MR5);
            case 8:
                return SdkConfig.atLeast(SdkConfig.SEM.B_MR5);
            case 9:
                return SdkConfig.atLeast(SdkConfig.SEM.B_MR5);
            case 10:
                return SdkConfig.atLeast(SdkConfig.SEM.S);
            case 11:
                return SdkConfig.atLeast(SdkConfig.SEM.S_MR1);
            case 12:
                return PreferenceFeatures.lambda$static$8();
            case 13:
                return SdkConfig.atLeast(SdkConfig.SEM.S);
            case 14:
                return SdkConfig.atLeast(SdkConfig.SEM.R);
            case 15:
                return SdkConfig.atLeast(SdkConfig.SEM.T);
            case 16:
                return PreferenceFeatures.lambda$static$15();
            default:
                return SdkConfig.atLeast(SdkConfig.SEM.T);
        }
    }
}
