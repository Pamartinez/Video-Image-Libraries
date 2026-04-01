package md;

import Ae.a;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.LockScreenHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.util.Rune;
import com.samsung.android.vexfwk.sdk.bokeheffect.VexFwkPortraitBokehEffect;
import com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor;
import com.samsung.android.vexfwk.sdk.focallength.VexFwkFocalLengthProcessor;
import com.samsung.android.vexfwk.sdk.frcrunner.VexFwkFrcRunner;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjRemover;
import com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements a {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                return Boolean.valueOf(VexFwkPortraitBokehEffect.isAvailable_delegate$lambda$19());
            case 1:
                return Boolean.valueOf(VexFwkPortraitBokehEffect.isSegmentationMapSupported_delegate$lambda$21());
            case 2:
                return Boolean.valueOf(VexFwkDepthMapProcessor.isAvailable_delegate$lambda$8());
            case 3:
                return Boolean.valueOf(VexFwkFocalLengthProcessor.isAvailable_delegate$lambda$8());
            case 4:
                return Boolean.valueOf(VexFwkFrcRunner.isAvailable_delegate$lambda$8());
            case 5:
                return VexFwkFrcRunner.availableUpsampleFactors_delegate$lambda$10();
            case 6:
                return LockScreenHelper.requestDismissKeyguard$lambda$0();
            case 7:
                return Boolean.valueOf(VexFwkImageObjectRemover.isAvailable_delegate$lambda$11());
            case 8:
                return VexFwkImageObjectRemover.supportedModes_delegate$lambda$13();
            case 9:
                return Boolean.valueOf(VexFwkObjRemover.isAvailable_delegate$lambda$9());
            case 10:
                return VexFwkObjRemover.supportedModes_delegate$lambda$11();
            case 11:
                return Integer.valueOf(Rune.FEATURE_CONFIG_IMAGE_TRANSLATION_OVERLAY_VERSION_delegate$lambda$3());
            case 12:
                return Boolean.valueOf(Rune.FEATURE_DISABLE_NATIVE_AI_delegate$lambda$6());
            case 13:
                return Integer.valueOf(Rune.FEATURE_CONFIG_AI_VERSION_delegate$lambda$9());
            default:
                return VexFwkImageOcr.capabilities_delegate$lambda$18();
        }
    }
}
