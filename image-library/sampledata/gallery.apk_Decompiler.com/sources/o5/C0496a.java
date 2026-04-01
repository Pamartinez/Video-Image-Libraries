package o5;

import android.view.View;
import android.widget.LinearLayout;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.LanguageSelectFragment;
import com.samsung.android.gallery.app.ui.dialog.AlbumCreatePopupDialog;
import com.samsung.android.gallery.app.ui.dialog.AppRatingDialog;
import com.samsung.android.gallery.app.ui.dialog.CustomChooserDialog;
import com.samsung.android.gallery.app.ui.dialog.FileOperationDialog;
import com.samsung.android.gallery.app.ui.dialog.SimpleConfirmProgressDialog;
import com.samsung.android.gallery.app.ui.dialog.SimpleProgressDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.RemoveCreatureDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.CreaturePickerDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.relationship.RelationshipChoiceDialog;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryShortcutButtonItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryShotModeItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryShotModeItemViewHolderV2;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryStoriesItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryTagItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.keyword.KeywordResultFragment;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResult;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BgmPickerDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.GuideDecoViewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.StoryLiveEffectDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.MapPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.SaveStoryPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.SummaryPage;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ContentViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DlnaUi2;
import com.samsung.android.gallery.image360.activity.viewer.Image360Fragment;
import com.samsung.android.gallery.widget.dialog.PlaceGdprDialog;
import com.samsung.android.gallery.widget.editdetails.EditDetailsEditText;

/* renamed from: o5.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0496a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0496a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((CategoryShortcutButtonItemViewHolder) obj).lambda$bindView$0(view);
                return;
            case 1:
                ((CategoryShotModeItemViewHolder) obj).onClick(view);
                return;
            case 2:
                ((CategoryShotModeItemViewHolderV2) obj).onClick(view);
                return;
            case 3:
                ((CategoryStoriesItemViewHolder) obj).onFavoriteViewClicked(view);
                return;
            case 4:
                ((CategoryTagItemViewHolder) obj).onClick(view);
                return;
            case 5:
                ((BgmPickerDelegate) obj).lambda$initBgmPickerIcon$2(view);
                return;
            case 6:
                ((GuideDecoViewDelegate) obj).lambda$initView$0(view);
                return;
            case 7:
                ((StoryLiveEffectDelegate) obj).onDownloadClicked(view);
                return;
            case 8:
                ((AlbumCreatePopupDialog) obj).onItemClicked(view);
                return;
            case 9:
                ((AppRatingDialog) obj).onRateItNowClicked(view);
                return;
            case 10:
                ((CustomChooserDialog) obj).itemClicked(view);
                return;
            case 11:
                ((FileOperationDialog) obj).onClickPositive(view);
                return;
            case 12:
                ((SimpleConfirmProgressDialog) obj).lambda$setPositiveButtonClickListener$1(view);
                return;
            case 13:
                ((SimpleProgressDialog) obj).onPositiveClicked(view);
                return;
            case 14:
                ((MapPage) obj).lambda$initMap$0(view);
                return;
            case 15:
                ((SaveStoryPage) obj).onSaveAsClicked(view);
                return;
            case 16:
                ((SummaryPage) obj).onTitleClicked(view);
                return;
            case 17:
                ((RemoveCreatureDialog) obj).lambda$new$4(view);
                return;
            case 18:
                ((KeywordResultFragment) obj).lambda$bindKeywordResultLayout$0(view);
                return;
            case 19:
                ((PlaceGdprDialog) obj).onLinkClicked(view);
                return;
            case 20:
                ((CreaturePickerDialog) obj).lambda$initToolbar$0(view);
                return;
            case 21:
                ((PdcSearchDelegate) obj).lambda$bindView$0(view);
                return;
            case 22:
                ((ContentViewerHolder) obj).toggleOsd(view);
                return;
            case 23:
                ((RelationshipChoiceDialog) obj).lambda$new$0(view);
                return;
            case 24:
                ((DlnaUi2) obj).onButtonClicked(view);
                return;
            case 25:
                ((SearchClusterResultFragment) obj).lambda$bindClusterResultLayout$0(view);
                return;
            case 26:
                ((Image360Fragment) obj).lambda$initAutoPlay$2(view);
                return;
            case 27:
                LanguageSelectFragment.addAddLanguageButton$lambda$8$lambda$7((LinearLayout) obj, view);
                return;
            case 28:
                ((ClusterResult) obj).onExpansionArrowClicked(view);
                return;
            default:
                ((EditDetailsEditText) obj).lambda$init$0(view);
                return;
        }
    }
}
