package vd;

import Ae.b;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslator;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.LttImageTranslationEngine;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine;
import com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationCommandType;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import com.samsung.android.vexfwk.sdk.imgclipper.VexFwkImageClipper;
import com.samsung.android.vexfwk.sdk.imgstyletransfer.VexFwkImageStyleTransfer;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjSegmentor;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjectExtractor;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkVideoObjectRemover;
import com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements b {
    public final /* synthetic */ int d;

    public /* synthetic */ d(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return VexFwkImageTranslator.showInstallPopupAsync$lambda$26$lambda$20("translate", (VexFwkMetadataNative) obj);
            case 1:
                return VexFwkImageTranslator.configure$lambda$2$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 2:
                ((VexFwkMetadataNative) obj).set(VexFwkMetadataKey.TRANSLATION_COMMAND_TYPE.INSTANCE, Integer.valueOf(VexFwkTranslationCommandType.COMMAND_TYPE_LANGUAGE_LIST.getValue()));
                return x.f4917a;
            case 3:
                VexFwkMetadataNative vexFwkMetadataNative = (VexFwkMetadataNative) obj;
                vexFwkMetadataNative.set(VexFwkMetadataKey.TRANSLATION_COMMAND_TYPE.INSTANCE, Integer.valueOf(VexFwkTranslationCommandType.COMMAND_TYPE_STRING_RESOURCE.getValue()));
                vexFwkMetadataNative.set(VexFwkMetadataKey.STRING_RESOURCE_NAME.INSTANCE, "translate");
                return x.f4917a;
            case 4:
                return ImageTranslator.showTranslationDialog$lambda$10((Throwable) obj);
            case 5:
                return VexFwkImageClipper.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 6:
                return VexFwkImageClipper.configure$lambda$1((VexFwkImageClipper) obj);
            case 7:
                return VexFwkImageStyleTransfer.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 8:
                return VexFwkImageStyleTransfer.configure$lambda$1((VexFwkImageStyleTransfer) obj);
            case 9:
                return LttImageTranslationEngine.doImageTranslation$lambda$4((x) obj);
            case 10:
                return VexImageTranslationEngine.onImageTranslationComplete$lambda$1$lambda$0((VexFwkOcrResult.BlockInfo) obj);
            case 11:
                return VexFwkObjSegmentor.configure$lambda$1((VexFwkObjSegmentor) obj);
            case 12:
                return VexFwkObjSegmentor.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 13:
                return VexFwkObjectExtractor.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 14:
                return VexFwkObjectExtractor.configure$lambda$1((VexFwkObjectExtractor) obj);
            case 15:
                return VexFwkVideoObjectRemover.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 16:
                return VexFwkVideoObjectRemover.configure$lambda$1((VexFwkVideoObjectRemover) obj);
            case 17:
                return VexFwkImageOcr.hasTextImpl$lambda$8$lambda$2((VexFwkMetadataNative) obj);
            case 18:
                return VexFwkImageOcr.recognizeImpl$lambda$14$lambda$9((VexFwkMetadataNative) obj);
            case 19:
                return VexFwkImageOcr.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            default:
                return VexFwkImageOcr.configure$lambda$1((VexFwkImageOcr) obj);
        }
    }
}
