package h;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.background.greedy.TimeLimiter;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.picker.MultiPickFragment;
import com.samsung.android.gallery.app.ui.container.tablet.ListContainerDebugger;
import com.samsung.android.gallery.app.ui.container.tablet.ListContainerManager;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItemViewHolder;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import com.samsung.android.gallery.app.ui.dialog.RenameStoryDialog;
import com.samsung.android.gallery.app.ui.dialog.SimpleSpinnerDialog;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemAdapterV2;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryLocationHeaderViewHolder;
import com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice.CreatureCoverChoiceFragment;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNameFragment;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior.StoryHighlightBehavior;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.VideoPreviewDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.ContentViewHolderFactory;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DlnaDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.HdrContentsDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.MirroringDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripSeekerDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.input.DexNavigationDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.transcode.HighlightEncoder;
import com.samsung.android.gallery.module.story.transcode.config.EncodeInfo;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.videoview.mediaplayer.CaptureDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerDelegate;
import java.util.ArrayList;
import java.util.Stack;

/* renamed from: h.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0199b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0199b(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((Toolbar) this.e).lambda$seslSetTouchDelegateForToolbar$0((Toolbar) this.f);
                return;
            case 1:
                ((HighlightEncoder) this.e).lambda$initialize$0((EncodeInfo) this.f);
                return;
            case 2:
                CaptureDelegate.lambda$capturePlayback$1((Bitmap[]) this.e, (MediaPlayerDelegate) this.f);
                return;
            case 3:
                ((CaptureDelegate) this.e).lambda$capturePlayback$2((Bitmap[]) this.f);
                return;
            case 4:
                ((CategoryItemAdapterV2) this.e).lambda$onBindViewHolder$0((ListViewHolder) this.f);
                return;
            case 5:
                ContentViewHolderFactory.lambda$prepareCache$0((Context) this.e, (ViewGroup) this.f);
                return;
            case 6:
                ((DlnaDelegate) this.e).lambda$connectOriginalContents$3((MediaItem) this.f);
                return;
            case 7:
                ((HdrContentsDelegate) this.e).lambda$onHandleEvent$4((ContentModel) this.f);
                return;
            case 8:
                ((MirroringDelegate) this.e).lambda$onPresentationPlaying$2((MediaItem) this.f);
                return;
            case 9:
                ((View) this.e).setLayoutParams((ViewGroup.LayoutParams) this.f);
                return;
            case 10:
                ((StoryHighlightBehavior) this.e).lambda$setWindowInsetsListener$3((View) this.f);
                return;
            case 11:
                ((FilmStripSeekerDelegate) this.e).lambda$updateSeeker$7((FilmStripViewHolder) this.f);
                return;
            case 12:
                ((MultiPickFragment) this.e).lambda$onBindFragment$0((MvpBaseFragment) this.f);
                return;
            case 13:
                ListContainerDebugger.lambda$printChildFragments$0((FragmentManager) this.e, (Stack) this.f);
                return;
            case 14:
                ((ListContainerManager) this.e).lambda$selectView$4((String) this.f);
                return;
            case 15:
                TimeLimiter.track$lambda$0((TimeLimiter) this.e, (StartStopToken) this.f);
                return;
            case 16:
                ((CategoryLocationHeaderViewHolder) this.e).lambda$setMarkerImageViewMatrix$2((MediaItem) this.f);
                return;
            case 17:
                ((VideoPreviewDelegate) this.e).lambda$requestVideoPreview$2((IStoryHighlightView) this.f);
                return;
            case 18:
                ((DexNavigationDelegate) this.e).lambda$updateLayout$4((Resources) this.f);
                return;
            case 19:
                ((DrawerTabViewAdapter) this.e).lambda$setSuggestionBadge$6((DrawerTabItemViewHolder) this.f);
                return;
            case 20:
                ((CreatureCoverChoiceFragment) this.e).lambda$onApplyWindowInsets$1((WindowInsets) this.f);
                return;
            case 21:
                ((ViewerMenuDelegate) this.f).lambda$requestLayoutForMenu$13((Toolbar) this.e);
                return;
            case 22:
                ((ViewerMenuDelegate) this.e).lambda$onEnterTransitionStart$7((Menu) this.f);
                return;
            case 23:
                ((RenameStoryDialog) this.e).lambda$postInitDialog$1((String) this.f);
                return;
            case 24:
                ((SimpleSpinnerDialog) this.e).lambda$onUpdateMessage$0((Bundle) this.f);
                return;
            case 25:
                ((EditCreatureNameFragment) this.e).lambda$createSuggestionNameView$1((CreatureNameData.ContactType[]) this.f);
                return;
            case 26:
                ((EditCreatureNameFragment) this.e).lambda$setHeaderImage$12((Bitmap) this.f);
                return;
            case 27:
                ((EditCreatureNameFragment) this.e).lambda$initSyncWithInsetsAnimation$3((WindowInsets) this.f);
                return;
            case 28:
                ((EditCreatureNamePresenter) this.e).lambda$finish$26((Boolean) this.f);
                return;
            default:
                ((EditCreatureNamePresenter) this.e).lambda$onContactPicked$1((ArrayList) this.f);
                return;
        }
    }

    public /* synthetic */ C0199b(ViewerMenuDelegate viewerMenuDelegate, Toolbar toolbar) {
        this.d = 21;
        this.f = viewerMenuDelegate;
        this.e = toolbar;
    }
}
