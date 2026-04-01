package T8;

import com.samsung.android.gallery.app.controller.story.DeleteStoryCmd;
import com.samsung.android.gallery.app.controller.story.SaveHideRuleCmd;
import com.samsung.android.gallery.app.controller.story.StoriesPinCmd;
import com.samsung.android.gallery.app.controller.story.UpdateStoryFavoriteCmd;
import com.samsung.android.gallery.app.ui.dialog.CreateNewDialog;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.DividerCluster;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.MonthXsCluster;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.YearQueryCluster;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectPresenterV2;
import com.samsung.android.gallery.app.ui.list.search.category.people.SuggestedCreatureSelectPresenter;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.selection.HideSceneSelectionViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.StoryHighlightPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageDataLoader;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.MapPage;
import com.samsung.android.gallery.app.ui.menu.list.ListMenuHandler;
import com.samsung.android.gallery.database.dal.cmh.helper.CmhStoryApiImpl;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.creature.FacePosRatioHelper;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOperation;
import com.samsung.android.gallery.module.search.engine.MyQueryCreator;
import com.samsung.android.sum.core.types.SocVendor;
import java.util.function.IntFunction;

/* renamed from: T8.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0578a implements IntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2876a;

    public /* synthetic */ C0578a(int i2) {
        this.f2876a = i2;
    }

    public final Object apply(int i2) {
        switch (this.f2876a) {
            case 0:
                return FacePosRatioHelper.lambda$preload$2(i2);
            case 1:
                return DeleteStoryCmd.lambda$onExecute$0(i2);
            case 2:
                return DeleteStoryCmd.lambda$deleteStories$1(i2);
            case 3:
                return SaveHideRuleCmd.lambda$showHideSceneOptionDialog$4(i2);
            case 4:
                return SaveHideRuleCmd.lambda$showHideSceneOptionDialog$5(i2);
            case 5:
                return SaveHideRuleCmd.lambda$showHideSceneOptionDialog$7(i2);
            case 6:
                return SaveHideRuleCmd.lambda$addHideItems$1(i2);
            case 7:
                return StoriesPinCmd.lambda$onExecute$0(i2);
            case 8:
                return UpdateStoryFavoriteCmd.lambda$updateStoryFavoriteInfo$2(i2);
            case 9:
                return MyQueryCreator.lambda$createTop5$0(i2);
            case 10:
                return GroupType.lambda$arrayOf$0(i2);
            case 11:
                return SocVendor.lambda$all$1(i2);
            case 12:
                return HideSceneSelectionViewAdapter.lambda$getAllItems$0(i2);
            case 13:
                return ListMenuHandler.lambda$handleCommonAction$1(i2);
            case 14:
                return DividerCluster.lambda$getScrollIndexDates$0(i2);
            case 15:
                return MonthXsCluster.lambda$getScrollIndexDates$0(i2);
            case 16:
                return YearQueryCluster.lambda$getScrollIndexDates$3(i2);
            case 17:
                return StoryHighlightPresenter.lambda$getAllItems$2(i2);
            case 18:
                return DatabaseOperation.lambda$applyRequest$0(i2);
            case 19:
                return CreatureSelectPresenterV2.lambda$createAutoAlbumTitle$4(i2);
            case 20:
                return CreatureSelectPresenterV2.lambda$getCreatureNamesArray$7(i2);
            case 21:
                return SuggestedCreatureSelectPresenter.lambda$getAllItems$0(i2);
            case 22:
                return SuggestedCreatureSelectPresenter.lambda$setAsRelation$4(i2);
            case 23:
                return CmhStoryApiImpl.lambda$deleteStory$2(i2);
            case 24:
                return CreateNewDialog.lambda$inflateView$1(i2);
            case 25:
                return CreateNewDialog.lambda$inflateView$2(i2);
            case 26:
                return EditCreatureNamePresenter.lambda$getMergedInfo$25(i2);
            case 27:
                return EditCreatureNamePresenter.lambda$getMultiplePrimaryKey$28(i2);
            case 28:
                return PageDataLoader.lambda$loadMapItem$6(i2);
            default:
                return MapPage.lambda$getLocationItems$1(i2);
        }
    }
}
