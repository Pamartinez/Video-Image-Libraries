package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import java.util.function.BooleanSupplier;

/* renamed from: com.samsung.android.gallery.support.utils.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0671i implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3178a;

    public /* synthetic */ C0671i(int i2) {
        this.f3178a = i2;
    }

    public final boolean getAsBoolean() {
        switch (this.f3178a) {
            case 0:
                return MemoryUtils.isLowRamDevice(AppResources.getAppContext());
            case 1:
                return Features.ScsVersionHolder.lambda$supportHint$0();
            case 2:
                return SdkConfig.atLeast(SdkConfig.SEM.B);
            case 3:
                return PocFeatures.lambda$static$0();
            case 4:
                return Features.isEnabled(Features.IS_GED);
            case 5:
                return PocFeatures.isEnabled(PocFeatures.GalleryLabsDev);
            case 6:
                return SdkConfig.atLeast(SdkConfig.GED.V);
            case 7:
                return SdkConfig.atLeast(SdkConfig.SEM.V);
            case 8:
                return SdkConfig.atLeast(SdkConfig.SEM.V);
            case 9:
                return PocFeatures.lambda$static$6();
            case 10:
                return SdkConfig.atLeast(SdkConfig.SEM.S_MR5);
            case 11:
                return PreferenceFeatures.lambda$static$18();
            case 12:
                return Features.isEnabled(Features.SUPPORT_SEM_IMAGE_FILTER);
            case 13:
                return Features.isEnabled(Features.SUPPORT_TRASH);
            case 14:
                return PreferenceFeatures.lambda$static$20();
            case 15:
                return PreferenceFeatures.lambda$static$21();
            case 16:
                return SdkConfig.atLeast(SdkConfig.SEM.T);
            case 17:
                return SdkConfig.atLeast(SdkConfig.SEM.T_MR1);
            case 18:
                return SdkConfig.atLeast(SdkConfig.SEM.T_MR1);
            case 19:
                return SdkConfig.atLeast(SdkConfig.SEM.T_MR1);
            case 20:
                return PreferenceFeatures.lambda$static$26();
            case 21:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR1);
            case 22:
                return SdkConfig.atLeast(SdkConfig.SEM.T_MR1);
            case 23:
                return Features.isEnabled(Features.SUPPORT_PEOPLE_FACE_SCORE);
            case 24:
                return SdkConfig.atLeast(SdkConfig.SEM.Q_MR1);
            case 25:
                return SdkConfig.atLeast(SdkConfig.SEM.U);
            case 26:
                return SdkConfig.atLeast(SdkConfig.SEM.U_MR1);
            case 27:
                return SdkConfig.atLeast(SdkConfig.SEM.T);
            case 28:
                return Features.isEnabled(Features.SUPPORT_PHOTO_HDR);
            default:
                return PreferenceFeatures.lambda$static$34();
        }
    }
}
