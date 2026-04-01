package a6;

import android.view.View;
import androidx.appcompat.widget.SeslIndicator;
import c2.q;
import com.google.android.material.chip.SeslExpandableContainer;
import com.google.android.material.datepicker.s;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup.PopupMenuHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.SimpleCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ToggleCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ExpandableCapsuleLayout;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.SimpleCapsuleLayout;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ToggleCapsuleLayout;
import com.samsung.android.gallery.app.service.EmptyService;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardFragmentV2;
import com.samsung.android.gallery.app.ui.container.tablet.bottomtab.BottomTabController;
import com.samsung.android.gallery.app.ui.list.search.analysis.SearchAnalysisTipView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.Top5HeaderDelegate;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryMyQueryItemViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandFloatingViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.v1.HideRuleDateItemViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.v1.HideRuleSceneItemViewHolder;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.NaviUpDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.SelectModeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover.FlipCoverNaviUpDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel.GroupItemPanelDelegate;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.app.ui.viewholders.SearchPicturesDateLocationViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.StoryImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.bottom.BottomMoveBar;
import com.samsung.android.gallery.widget.story.DebugHelper;
import com.samsung.android.gallery.widget.toolbar.SelectInfoView;

/* renamed from: a6.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0419b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0419b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((OnDemandFloatingViewHolder) obj).lambda$bind$0(view);
                return;
            case 1:
                ((SearchPicturesDateLocationViewHolder) obj).onExpandButtonClicked(view);
                return;
            case 2:
                ((StoryImageViewHolder) obj).lambda$inflateTagView$1(view);
                return;
            case 3:
                Utils.showToast(AppResources.getAppContext(), DebugHelper.getDebugInfo((MediaItem) obj, false));
                return;
            case 4:
                SeslExpandableContainer seslExpandableContainer = (SeslExpandableContainer) obj;
                seslExpandableContainer.f1446h = !seslExpandableContainer.f1446h;
                seslExpandableContainer.a();
                seslExpandableContainer.post(new q(seslExpandableContainer, 1));
                return;
            case 5:
                ((EmptyService) obj).onGoToOneDriveClicked(view);
                return;
            case 6:
                ((s) obj).c();
                throw null;
            case 7:
                PopupMenuHelper.menuItemButtonOnClickListener$lambda$0((PopupMenuHelper) obj, view);
                return;
            case 8:
                ((MvpBasePresenter) obj).onNavigationPressed(view);
                return;
            case 9:
                ((SelectInfoView) obj).onCheckClick(view);
                return;
            case 10:
                ((HideRuleDateItemViewHolder) obj).onClickRemoveDate(view);
                return;
            case 11:
                ((HideRuleSceneItemViewHolder) obj).onClickThumbnailView(view);
                return;
            case 12:
                ((AiEditItem) obj).onMenuSelect(view, false);
                return;
            case 13:
                Utils.showToast(((View) obj).getContext(), "OT : Origin Thumbnail (not applied orientation)\nRT : Rotated Thumbnail\nOR : Origin Rect\nRR : Rotated Rect\n(ex) OTOR : Origin Thumbnail and Origin Rect", 1);
                return;
            case 14:
                SeslIndicator.addIndicator$lambda$5$lambda$4((SeslIndicator) obj, view);
                return;
            case 15:
                ((SearchAnalysisTipView) obj).onButtonClick(view);
                return;
            case 16:
                ((ClipboardFragmentV2) obj).onTouchToggleButton(view);
                return;
            case 17:
                ((CategoryFragment) obj).lambda$setCreatureCategoryEmptyView$0(view);
                return;
            case 18:
                ((NaviUpDelegate) obj).lambda$initNaviUp$0(view);
                return;
            case 19:
                ((SelectModeDelegate) obj).onClicked(view);
                return;
            case 20:
                ExpandableCapsuleLayout.bindIconView$lambda$2((ExpandableCapsule) obj, view);
                return;
            case 21:
                SimpleCapsuleLayout.bind$lambda$2((SimpleCapsule) obj, view);
                return;
            case 22:
                ToggleCapsuleLayout.bind$lambda$2((ToggleCapsule) obj, view);
                return;
            case 23:
                ((ViewerMenuItem) obj).onMenuSelect(view);
                return;
            case 24:
                ((FlipCoverNaviUpDelegate) obj).lambda$initNavigationUpButton$2(view);
                return;
            case 25:
                ((Top5HeaderDelegate) obj).lambda$init$0(view);
                return;
            case 26:
                ((GroupItemPanelDelegate) obj).lambda$new$8(view);
                return;
            case 27:
                ((BottomMoveBar) obj).onButtonClick(view);
                return;
            case 28:
                ((BottomTabController) obj).onNavigationIconClicked(view);
                return;
            default:
                ((CategoryMyQueryItemViewHolder) obj).onClick(view);
                return;
        }
    }
}
