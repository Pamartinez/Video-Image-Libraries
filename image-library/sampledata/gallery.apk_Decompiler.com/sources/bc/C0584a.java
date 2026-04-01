package bc;

import a6.g;
import android.animation.Animator;
import android.os.HandlerThread;
import androidx.media3.transformer.Transformer;
import androidx.media3.transformer.WatchdogTimer;
import com.google.android.material.timepicker.e;
import com.samsung.android.gallery.app.service.DeleteService;
import com.samsung.android.gallery.app.service.FileOpService;
import com.samsung.android.gallery.app.service.HighlightEncodeService;
import com.samsung.android.gallery.app.service.QuickDeleteService;
import com.samsung.android.gallery.app.service.StoryShareService;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;
import com.samsung.android.gallery.app.ui.list.stories.headeritem.StoryHeaderItem2;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.AbsEditorTransitionHandler;
import com.samsung.android.gallery.module.directorsview.DirectorsViewCache;
import com.samsung.android.gallery.module.map.transition.TransitionQueueHandler;
import com.samsung.android.gallery.module.story.ondemand.OnDemandStoryProvider;
import com.samsung.android.gallery.module.story.ondemand.OnDemandSuggestionDataManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.GalleryTextView;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.story.abstraction.IStoryContent;
import com.samsung.android.gallery.widget.story.transitory.AccessibilityState;
import com.samsung.android.gallery.widget.story.transitory.StoriesViewPager;
import com.samsung.android.gallery.widget.story.transitory.ViewPagerDelegate;
import com.samsung.android.gallery.widget.tag.MyTagHelper;
import com.samsung.android.gallery.widget.tag.MyTagView;
import com.samsung.android.sdk.scs.ai.extension.lts.LongTextSummarizer;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightConfig$makeConfigWithAnimator$1$1;
import com.samsung.android.sum.core.filter.DecoderFilter;
import com.samsung.android.sum.core.service.LocalServiceProxy;
import com.samsung.android.sum.core.service.RemoteServiceProxy;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase;
import com.samsung.o3dp.lib3dphotography.video.VideoEncoder;

/* renamed from: bc.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0584a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0584a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((AccessibilityState) obj).lambda$initAccessibilityState$0();
                return;
            case 1:
                ((StoriesViewPager) obj).lambda$setCurrentItem$0();
                return;
            case 2:
                ((IStoryContent) obj).onClear();
                return;
            case 3:
                ((ViewPagerDelegate) obj).updateDecoViewSelf();
                return;
            case 4:
                ((HandlerThread) obj).quitSafely();
                return;
            case 5:
                ((Transformer) ((g) ((WatchdogTimer.Listener) obj)).e).lambda$maybeInitializeExportWatchdogTimer$0();
                return;
            case 6:
                ((DeleteService) obj).lambda$forceRefreshData$0();
                return;
            case 7:
                ((FileOpService) obj).lambda$startService$0();
                return;
            case 8:
                ((HighlightEncodeService) obj).stopSelf();
                return;
            case 9:
                ((QuickDeleteService) obj).lambda$quickDelete$0();
                return;
            case 10:
                ((StoryShareService) obj).stopSelf();
                return;
            case 11:
                ((StoryHeaderItem2) obj).onStoriesDataChangedOnUi();
                return;
            case 12:
                ((DirectorsViewCache) obj).loadData();
                return;
            case 13:
                ((MyTagHelper) obj).showTag();
                return;
            case 14:
                ((MyTagView) obj).updateVisibility();
                return;
            case 15:
                ProcessingLightConfig$makeConfigWithAnimator$1$1.onAnimationRepeat$lambda$0((Animator) obj);
                return;
            case 16:
                ((e) obj).a();
                return;
            case 17:
                ((AbsEditorTransitionHandler) obj).startPostponedEnterTransition();
                return;
            case 18:
                ((TransitionQueueHandler) obj).lambda$handleMessage$0();
                return;
            case 19:
                ((OnDemandStoryProvider) obj).lambda$cancel$0();
                return;
            case 20:
                ((OnDemandSuggestionDataManager) obj).lambda$update$1();
                return;
            case 21:
                SharedTransition.lambda$startPostponedEnterTransition$3((Blackboard) obj);
                return;
            case 22:
                ((LongTextSummarizer) obj).lambda$request$3();
                return;
            case 23:
                ((DecoderFilter) obj).lambda$getBuffer$1();
                return;
            case 24:
                ((LocalServiceProxy) obj).lambda$new$0();
                return;
            case 25:
                ((RemoteServiceProxy) obj).lambda$startRequestThread$0();
                return;
            case 26:
                VexFwkHelperBase.close$lambda$10((VexFwkHelperBase) obj);
                return;
            case 27:
                ((VideoEncoder) obj).encode();
                return;
            case 28:
                ((AbsProgressService) obj).lambda$doJobInThread$3();
                return;
            default:
                ((GalleryTextView) obj).lambda$new$0();
                return;
        }
    }
}
