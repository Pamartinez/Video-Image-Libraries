package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.config.SdkConfig;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class M implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3170a;

    public /* synthetic */ M(int i2) {
        this.f3170a = i2;
    }

    public final boolean getAsBoolean() {
        switch (this.f3170a) {
            case 0:
                return Features.isEnabled(Features.SUPPORT_HEIF_CONVERSION);
            case 1:
                return SdkConfig.lessThan(SdkConfig.SEM.U);
            case 2:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR1);
            case 3:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR1);
            case 4:
                return Features.isEnabled(Features.SUPPORT_SEARCH_CLUSTER);
            case 5:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR1);
            case 6:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR1);
            case 7:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR1);
            case 8:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR1);
            case 9:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR1);
            case 10:
                return Features.isEnabled(Features.SUPPORT_STORY_NOTIFICATION);
            case 11:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR1);
            case 12:
                return Features.isEnabled(Features.SUPPORT_PET_CLUSTER);
            case 13:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR5);
            case 14:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR5);
            case 15:
                return Features.isEnabled(Features.SUPPORT_NEW_HIDE_SCENE_SELECTION);
            case 16:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR5);
            case 17:
                return PreferenceFeatures.lambda$static$49();
            case 18:
                return PreferenceFeatures.lambda$static$4();
            case 19:
                return PreferenceFeatures.lambda$static$50();
            case 20:
                return PreferenceFeatures.lambda$static$51();
            case 21:
                return PreferenceFeatures.isEnabled(PreferenceFeatures.VisualSearch71);
            case 22:
                return SdkConfig.atLeast(SdkConfig.SEM.B);
            case 23:
                return PreferenceFeatures.lambda$static$53();
            case 24:
                return PreferenceFeatures.lambda$static$54();
            case 25:
                return PreferenceFeatures.isEnabled(PreferenceFeatures.VisualSearch71);
            case 26:
                return PreferenceFeatures.isEnabled(PreferenceFeatures.VisualSearch71);
            case 27:
                return PreferenceFeatures.isEnabled(PreferenceFeatures.VisualSearch71);
            case 28:
                return SdkConfig.atLeast(SdkConfig.SEM.V);
            default:
                return PreferenceFeatures.isEnabled(PreferenceFeatures.VisualSearch71);
        }
    }
}
