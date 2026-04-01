package com.samsung.android.vexfwk.sdk.docscan;

import com.google.android.appfunctions.schema.photos.v1.Album;
import com.samsung.android.app.sdk.deepsky.textextraction.entity.OcrEntityExtractor;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.textclassification.TextClassificationHelper;
import com.samsung.android.gallery.crossapp.function.AlbumFunctions;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.sdk.bokeheffect.VexFwkPortraitBokehEffect;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor;
import com.samsung.android.vexfwk.sdk.focallength.VexFwkFocalLengthProcessor;
import com.samsung.android.vexfwk.sdk.imageeffect.VexFwkImageEffectProcessor;
import com.samsung.android.vexfwk.sdk.imageenhancer.VexFwkImageEnhancer;
import com.samsung.android.vexfwk.sdk.imageremovalsuggestion.VexFwkImageRemovalSuggestion;
import com.samsung.android.vexfwk.sdk.imagesegmentation.VexFwkHumanSegmentor;
import com.samsung.android.vexfwk.sdk.imagetagger.VexFwkImageTagger;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Ae.b {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return VexFwkDocDewarper.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 1:
                return VexFwkDocRefiner.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 2:
                return VexFwkDocRefiner.configure$lambda$1((VexFwkDocRefiner) obj);
            case 3:
                return AlbumFunctions.queryAlbum$lambda$3$lambda$2((Album) obj);
            case 4:
                return VexFwkPortraitBokehEffect.configure$lambda$1((VexFwkPortraitBokehEffect) obj);
            case 5:
                return VexFwkPortraitBokehEffect.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 6:
                return VexFwkDepthMapProcessor.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 7:
                return VexFwkDepthMapProcessor.configure$lambda$1((VexFwkDepthMapProcessor) obj);
            case 8:
                return OcrEntityExtractor.toCharacterSequence$lambda$16((OcrEntityExtractor.Character) obj);
            case 9:
                return VexFwkFocalLengthProcessor.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 10:
                return VexFwkFocalLengthProcessor.configure$lambda$1((VexFwkFocalLengthProcessor) obj);
            case 11:
                return OcrData.joinToStringWithSeparator$lambda$9$lambda$8$lambda$7$lambda$6((OcrData.CharInfo) obj);
            case 12:
                return OcrData.toBlockStringList$lambda$5$lambda$4$lambda$3$lambda$2((OcrData.CharInfo) obj);
            case 13:
                return OcrData.BlockInfo.toString$lambda$1((OcrData.LineInfo) obj);
            case 14:
                return OcrData.LineInfo.toString$lambda$1((OcrData.WordInfo) obj);
            case 15:
                return OcrData.WordInfo.toString$lambda$1((OcrData.CharInfo) obj);
            case 16:
                return VexFwkImageEffectProcessor.configure$lambda$1((VexFwkImageEffectProcessor) obj);
            case 17:
                return VexFwkImageEffectProcessor.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 18:
                return VexFwkImageEnhancer.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 19:
                return VexFwkImageEnhancer.configure$lambda$1((VexFwkImageEnhancer) obj);
            case 20:
                return VexFwkImageRemovalSuggestion.configure$lambda$1((VexFwkImageRemovalSuggestion) obj);
            case 21:
                return VexFwkImageRemovalSuggestion.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 22:
                return VexFwkHumanSegmentor.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 23:
                return VexFwkHumanSegmentor.configure$lambda$1((VexFwkHumanSegmentor) obj);
            case 24:
                return VexFwkImageTagger.configure$lambda$1$lambda$0((VexFwkSessionConfigRequest.Builder) obj);
            case 25:
                return VexFwkImageTagger.configure$lambda$1((VexFwkImageTagger) obj);
            case 26:
                return TextClassificationHelper.classifyTextWithTimeout$lambda$2((Throwable) obj);
            case 27:
                return VexFwkImageTranslator.configure$lambda$2$lambda$1((VexFwkSessionConfigRequest.Builder) obj);
            case 28:
                return VexFwkImageTranslator.getAvailableLanguages$lambda$55$lambda$48((VexFwkMetadataNative) obj);
            default:
                return VexFwkImageTranslator.configure$lambda$2((VexFwkImageTranslator) obj);
        }
    }
}
