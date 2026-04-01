package e5;

import android.view.View;
import androidx.arch.core.util.Function;
import androidx.core.util.Supplier;
import androidx.work.impl.model.WorkSpec;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.RelatedViewAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterrupter;
import com.samsung.android.gallery.widget.animations.LoadAfterShrinkView;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.vexfwk.param.VexFwkImageTaggerV2Result;
import com.samsung.android.vexfwk.sdk.bokeheffect.VexFwkPortraitBokehEffect;
import com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor;
import com.samsung.android.vexfwk.sdk.focallength.VexFwkFocalLengthProcessor;
import com.samsung.android.vexfwk.sdk.frcrunner.VexFwkFrcRunner;
import com.samsung.android.vexfwk.sdk.imageeffect.VexFwkImageEffectProcessor;
import com.samsung.android.vexfwk.sdk.imagetagger.VexFwkImageTagger;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import com.samsung.android.vexfwk.sdk.imgstyletransfer.VexFwkImageStyleTransfer;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjRemover;
import com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr;
import java.util.HashSet;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements PropertyAnimator.PropertyAnimationListener, WidthAnimator.WidthAnimationCallback, LoadAfterShrinkView.TranslateChecker, Supplier, ThumbnailInterrupter, ListViewHolder.OnItemLongClickListener, Function {
    public final /* synthetic */ int d;

    public /* synthetic */ d(int i2) {
        this.d = i2;
    }

    public Object apply(Object obj) {
        return WorkSpec.WORK_INFO_MAPPER$lambda$1((List) obj);
    }

    public Object get() {
        switch (this.d) {
            case 5:
                return VexFwkPortraitBokehEffect.isSegmentationMapSupported_delegate$lambda$21$lambda$20();
            case 6:
                return VexFwkPortraitBokehEffect.bokehEffectVersion_delegate$lambda$18$lambda$17();
            case 7:
                return VexFwkDepthMapProcessor.Companion.getSupportedFeatures$lambda$1$lambda$0();
            case 8:
                return VexFwkFocalLengthProcessor.Companion.getSupportedFeatures$lambda$1$lambda$0();
            case 9:
                return VexFwkFrcRunner.availableUpsampleFactors_delegate$lambda$10$lambda$9();
            case 11:
                return VexFwkImageEffectProcessor.Companion.getSupportedFilters$lambda$1$lambda$0();
            case 14:
                return VexFwkImageTagger.detectImageTagsImpl$lambda$10$lambda$7$lambda$6$lambda$5$lambda$4();
            case 15:
                return new VexFwkImageTaggerV2Result(new HashSet());
            case 16:
                return VexFwkImageTranslator.doesSupportV2$lambda$61$lambda$59();
            case 17:
                return VexFwkImageTranslator.isAvailableSize$lambda$58$lambda$56();
            case 18:
                return VexFwkImageStyleTransfer.Companion.capabilities$lambda$1$lambda$0();
            case 19:
                return VexFwkImageObjectRemover.supportedModes_delegate$lambda$13$lambda$12();
            case 20:
                return VexFwkObjRemover.supportedModes_delegate$lambda$11$lambda$10();
            default:
                return VexFwkImageOcr.capabilities_delegate$lambda$18$lambda$16$lambda$15();
        }
    }

    public boolean isInterrupted() {
        return ThumbnailLoader.lambda$static$0();
    }

    public boolean isWidthAnimationNeeded(View view, int i2) {
        return PicturesPinchAnimationManager.lambda$createNDaysViewAnim$4(view, i2);
    }

    public void onAnimationEnd(View view) {
        switch (this.d) {
            case 0:
                PicturesPinchAnimationManager.lambda$createTitleViewAnim$8(view);
                return;
            case 2:
                PicturesPinchAnimationManager.lambda$createNDaysViewAnim$5(view);
                return;
            default:
                view.setAlpha(1.0f);
                return;
        }
    }

    public boolean onItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        return RelatedViewAdapter.lambda$onBindViewHolder$0(listViewHolder, i2, mediaItem, i7);
    }
}
