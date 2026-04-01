package o6;

import android.view.Window;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabView;
import com.samsung.android.gallery.app.ui.dialog.RenameStoryDialog;
import com.samsung.android.gallery.app.ui.dialog.RequestGroupSharingEnableDialog;
import com.samsung.android.gallery.app.ui.dialog.ShowLowStorageDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.RemoveCreatureDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureAdapter;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureDialog;
import com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice.CreatureCoverChoiceFragment;
import com.samsung.android.gallery.app.ui.list.search.editcreature.IEditCreatureNameView;
import com.samsung.android.gallery.app.ui.list.search.editcreature.RegisteredCreatureAdapter;
import com.samsung.android.gallery.app.ui.list.search.editcreature.TagCreatureAdapter;
import com.samsung.android.gallery.app.ui.list.search.keyword.stories.SearchResultStoriesPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.LastPageDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.StoryLiveEffectDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.VideoPreviewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ViewPagerDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.WindowDecoDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.RecyclerPageScrollHelper;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.CollagePage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.OnDemandPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.PageHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.SaveLayoutBinder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.SaveWithRewriteStoryPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularCollageViewHolder;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.GroupPanelTransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.RemasterTransitionDelegate;
import com.samsung.android.gallery.widget.details.DetailsDragHelper;
import com.samsung.android.gallery.widget.details.DetailsListAdapter;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ t(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((LastPageDelegate) obj).lambda$handleEvent$6();
                return;
            case 1:
                ((StoryLiveEffectDelegate) obj).lambda$setVisible$0();
                return;
            case 2:
                ((VideoPreviewDelegate) obj).lambda$handleResolutionChange$0();
                return;
            case 3:
                ((ViewPagerDelegate) obj).setVisible(new Object[0]);
                return;
            case 4:
                ((WindowDecoDelegate.WindowDecoController) obj).lambda$setToolbarTranslucent$0();
                return;
            case 5:
                ((DrawerTabView) obj).completeAnimation();
                return;
            case 6:
                ((CreatureCoverChoiceFragment) obj).lambda$initAutoSelect$2();
                return;
            case 7:
                FloatingToolbarLayout floatingToolbarLayout = (FloatingToolbarLayout) obj;
                int i7 = FloatingToolbarLayout.f1480f0;
                j.e(floatingToolbarLayout, "this$0");
                AppBarLayout appBarLayout$material_release = floatingToolbarLayout.getAppBarLayout$material_release();
                if (appBarLayout$material_release != null) {
                    floatingToolbarLayout.u(appBarLayout$material_release, true);
                    return;
                }
                return;
            case 8:
                ((Window) obj).setSoftInputMode(32);
                return;
            case 9:
                ((RenameStoryDialog) obj).lambda$postInitDialog$0();
                return;
            case 10:
                ((RequestGroupSharingEnableDialog) obj).startGroupSharingSettingInternal();
                return;
            case 11:
                ((ShowLowStorageDialog) obj).startMyFilesInternal();
                return;
            case 12:
                ((IEditCreatureNameView) obj).updateContactLinkView();
                return;
            case 13:
                ((RegisteredCreatureAdapter) obj).notifyDataSetChanged();
                return;
            case 14:
                ((TagCreatureAdapter) obj).notifyDataSetChanged();
                return;
            case 15:
                ((RecyclerPageScrollHelper) obj).invalidateScrollState();
                return;
            case 16:
                ((CollagePage) obj).lambda$startPreview$2();
                return;
            case 17:
                ((OnDemandPage) obj).lambda$onRewritePrompt$1();
                return;
            case 18:
                ((PageHolder) obj).onBindItem();
                return;
            case 19:
                ((SaveLayoutBinder) obj).lambda$initView$1();
                return;
            case 20:
                ((SaveWithRewriteStoryPage) obj).lambda$onRewritePromptClicked$0();
                return;
            case 21:
                ((RemoveCreatureDialog) obj).lambda$removeAndPost$3();
                return;
            case 22:
                ((IrregularCollageViewHolder) obj).lambda$setViewMatrix$0();
                return;
            case 23:
                ((GroupPanelTransitionDelegate) obj).lambda$onReadyView$0();
                return;
            case 24:
                ((RemasterTransitionDelegate) obj).lambda$onReadyRemasterView$0();
                return;
            case 25:
                ((DetailsDragHelper) obj).lambda$new$0();
                return;
            case 26:
                ((DetailsListAdapter) obj).notifyDataSetChanged();
                return;
            case 27:
                ((MergeCreatureAdapter) obj).lambda$load$0();
                return;
            case 28:
                ((MergeCreatureDialog) obj).lambda$bindHeaderImage$0();
                return;
            default:
                ((SearchResultStoriesPresenter) obj).onDataChangedOnUi();
                return;
        }
    }
}
