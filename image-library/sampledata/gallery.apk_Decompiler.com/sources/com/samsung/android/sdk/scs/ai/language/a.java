package com.samsung.android.sdk.scs.ai.language;

import android.view.MenuItem;
import android.view.View;
import androidx.core.widget.SeslGoToTopController;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabMenu;
import com.samsung.android.gallery.app.ui.list.pictures.ClusterPositionFinder;
import com.samsung.android.gallery.app.ui.list.search.category.myquery.SearchMyQueryPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureHidePresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.HiddenCreaturePresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.PeopleSelectPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.SuggestedCreatureSelectFragment;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.v1.HideRuleViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgm.BgmPlayerV2;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.StoryHighlightListV2Fragment;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.groupshot.BurstShotFormat;
import com.samsung.android.gallery.module.groupshot.GroupShotFormat;
import com.samsung.android.gallery.module.location.LocationUpdater;
import com.samsung.android.gallery.module.service.abstraction.IProgressJob;
import com.samsung.android.gallery.module.settings.MarketUpgradeManager;
import com.samsung.android.gallery.module.settings.UpgradeManager;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.widget.animations.photostacking.PhotoStackingAnimView;
import com.samsung.android.gallery.widget.animations.photostacking.ThrowingViHandler;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listview.pinch.PinchItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.scs.ai.visual.ImageEditorGenerator;
import com.samsung.android.sdk.scs.ai.visual.WallpaperGenerator;
import com.samsung.android.sdk.scs.base.tasks.OnCompleteListener;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.filter.NNFWFilter;
import com.samsung.android.sum.core.functional.BiBufferProcessor;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBaseFuture;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcessFuture;
import com.samsung.scsp.common.PreferenceItem;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.DefaultErrorListener;
import com.samsung.scsp.framework.core.decorator.AbstractDecorator;
import java.util.Map;
import p2.C0262c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements OnCompleteListener, BiBufferProcessor, VexFwkHelperBaseFuture.OnCompletedCallback, FaultBarrier.ThrowableSupplier, IProgressJob, ClusterPositionFinder.OnCompletedListener, PropertyAnimator.PropertyAnimationListener, SeslGoToTopController.OnGoToTopClickListener, ListViewHolder.OnItemClickListener, ThrowingViHandler.OnViEndListener, UpgradeManager.OnUpdateCheckListener, MediaPlayerCompat.OnErrorListener, C0262c, FutureListener, LocationUpdater.OnUpdateCompletionListener, MediaScannerListener, BottomTabMenu.BottomTabMenuClickListener, StoryHighlightListV2Fragment.Callback {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public void a() {
        ((AbsProgressService) this.e).next();
    }

    public void b(View view) {
        ((PhotoStackingAnimView) this.e).onLastStackingFinished(view);
    }

    public Object get() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 10:
                return ((PreferenceItem) obj).lambda$get$6();
            case 11:
                return DefaultErrorListener.lambda$onError$0((Map) obj);
            default:
                return ((AbstractDecorator) obj).lambda$new$0();
        }
    }

    public void onAnimationEnd(View view) {
        ((PinchItem) this.e).getView().setAlpha(1.0f);
    }

    public void onComplete(Task task) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((Suggester) obj).lambda$release$5(task);
                return;
            case 1:
                ((SuggesterForExternal) obj).lambda$release$1(task);
                return;
            case 2:
                ((Summarizer) obj).lambda$release$4(task);
                return;
            case 3:
                ((ToneConverter) obj).lambda$release$3(task);
                return;
            case 4:
                ((WritingComposer) obj).lambda$release$3(task);
                return;
            case 5:
                ((ImageEditorGenerator) obj).lambda$release$0(task);
                return;
            default:
                ((WallpaperGenerator) obj).lambda$release$0(task);
                return;
        }
    }

    public void onCompleted() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 8:
                VexFwkHelperConfigurationFuture.onCompletedCallback$lambda$0((VexFwkHelperConfigurationFuture) obj);
                return;
            case 9:
                VexFwkHelperProcessFuture.onCompletedCallback$lambda$0((VexFwkHelperProcessFuture) obj);
                return;
            default:
                ((GroupShotFormat) obj).notifyCompletion();
                return;
        }
    }

    public boolean onError(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        return ((BgmPlayerV2) this.e).onError(mediaPlayerCompat, i2, i7);
    }

    public void onFutureDone(Future future) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 22:
                ((CreatureHidePresenter) obj).lambda$saveChangedCreature$1(future);
                return;
            case 23:
                ((HiddenCreaturePresenter) obj).lambda$selectCreatures$1(future);
                return;
            default:
                ((PeopleSelectPresenter) obj).lambda$saveSelectedPeople$2(future);
                return;
        }
    }

    public boolean onGoToTopClick() {
        return ((RecyclerView) this.e).lambda$seslSetGoToTopEnabled$4();
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ((HideRuleViewHolder) this.e).onClickHideRuleSubItem(listViewHolder, i2, mediaItem, i7);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 21:
                return ((SearchMyQueryPresenter) obj).onOptionsItemSelected(menuItem);
            default:
                return ((SuggestedCreatureSelectFragment) obj).onMenuItemSelected(menuItem);
        }
    }

    public void onUpdateCheckCompleted(int i2) {
        ((MarketUpgradeManager) this.e).onUpgradeCheckCompleted(i2);
    }

    public void process(MediaBuffer mediaBuffer, MediaBuffer mediaBuffer2) {
        ((NNFWFilter) this.e).runAdapter(mediaBuffer, mediaBuffer2);
    }

    public void onCompleted(boolean z) {
        ((BurstShotFormat) this.e).lambda$updateLocation$0(z);
    }
}
