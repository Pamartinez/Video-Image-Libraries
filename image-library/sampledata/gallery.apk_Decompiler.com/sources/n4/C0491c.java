package n4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ImageView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.c;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator;
import com.samsung.android.gallery.app.ui.container.tablet.ListContainerManager;
import com.samsung.android.gallery.app.ui.container.tablet.bottomtab.BottomTabController;
import com.samsung.android.gallery.app.ui.dialog.CreateNewDialogV2;
import com.samsung.android.gallery.app.ui.dialog.creature.RemoveCreatureDialog;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryHeaderContainer;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectPresenterV2;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesMultipleHeaderContainer;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterHeaderView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.DragItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.LastPagePresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.IrregularCollageLayoutBinder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.IrregularCollagePage;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.database.dal.local.table.LocalTable;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.knox.KnoxOperationTask;
import com.samsung.android.gallery.module.logger.SamsungAnalyticsPrefs;
import com.samsung.android.gallery.plugins.filebrowser.LogViewFragment;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.bottom.BottomMoveBar;
import com.samsung.android.gallery.widget.dragdrop.SplitDnDAnimation;
import com.samsung.android.gallery.widget.dragdrop.dragshadow.DragShadowCreator;
import com.samsung.android.gallery.widget.effects.RenderEffectBlur;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* renamed from: n4.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0491c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0491c(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((ListContainerManager) this.e).lambda$switchFragment$0((FragmentManager) this.f, (String) obj);
                return;
            case 1:
                ((CreatureCategoryHeaderContainer) this.e).lambda$bindSuggestionMeTagViewIfNeeded$6((ArrayList) this.f, (SuggesterView) obj);
                return;
            case 2:
                ((CreatureCategoryHeaderContainer) this.e).lambda$bindFaceMergeSuggestionIfNeeded$2((List) this.f, (SuggesterView) obj);
                return;
            case 3:
                CreatureSelectPresenterV2.lambda$getCreatureList$10((String) this.e, (List) this.f, (MediaItem) obj);
                return;
            case 4:
                ((CreatureSelectPresenterV2) this.e).lambda$getCreatureList$11((ArrayList) this.f, (String) obj);
                return;
            case 5:
                ((DragItem) this.e).lambda$dropComplete$1((RecyclerView.ViewHolder) this.f, (RecyclerView.ViewHolder) obj);
                return;
            case 6:
                ((BottomMoveBar) this.e).lambda$bindImage$1((Bitmap) this.f, (ImageView) obj);
                return;
            case 7:
                ((BottomTabController) this.e).lambda$publishDataRequest$2((c) this.f, (Blackboard) obj);
                return;
            case 8:
                ((Delegate) obj).onApplyWindowInsets((View) this.e, (WindowInsets) this.f);
                return;
            case 9:
                ((LocalTable) obj).recreateTableOnTransaction((SQLiteDatabase) this.e, (String) this.f);
                return;
            case 10:
                ((ViewerMenuDelegate) this.e).lambda$onMenuItemSelectedInternal$19((ViewerMenuItem) this.f, (ViewerMenuItem) obj);
                return;
            case 11:
                ((CreateNewDialogV2) this.e).lambda$inflateView$0((View) this.f, (RecyclerView) obj);
                return;
            case 12:
                ((LastPagePresenter) this.e).lambda$loadData$0((Runnable) this.f, (Boolean) obj);
                return;
            case 13:
                ((KnoxOperationTask) this.e).lambda$addFilePath$5((MediaItem) this.f, (String) obj);
                return;
            case 14:
                ((IrregularCollagePage) this.e).lambda$handleSaveInternal$0((IrregularCollageLayoutBinder) this.f, (View) obj);
                return;
            case 15:
                ((RemoveCreatureDialog) this.e).lambda$createBodyView$0((Context) this.f, (MediaItem) obj);
                return;
            case 16:
                SamsungAnalyticsPrefs.lambda$writeSettingPrefValue$0((Map) this.e, (GalleryPreference) this.f, (PreferenceName) obj);
                return;
            case 17:
                ((SplitDnDAnimation) this.e).lambda$createAnimViews$2((AtomicInteger) this.f, (RecyclerView.ViewHolder) obj);
                return;
            case 18:
                ((SearchHeaderView) this.e).bind((MediaItem) this.f);
                return;
            case 19:
                ((SearchPicturesMultipleHeaderContainer) this.e).lambda$updateFuzzyResult$7((String) this.f, (SearchHeaderView) obj);
                return;
            case 20:
                TextTranslator.translateText$lambda$4((StringBuilder) this.e, (CountDownLatch) this.f, (NeuralTranslationResult) obj);
                return;
            case 21:
                SearchClusterHeaderView.lambda$updatePreviousClusterVisible$1((HashMap) this.e, (HashMap) this.f, (ClusterResultType) obj);
                return;
            case 22:
                ((ListViewHolder) this.e).addThumbnailBorder((Drawable) this.f);
                return;
            case 23:
                ((DragShadowCreator) this.e).lambda$getBitmapFromRef$4((Bitmap[]) this.f, (Uri) obj);
                return;
            case 24:
                ((RenderEffectBlur) this.e).lambda$new$0((RenderEffectBlur.Builder) this.f, (View) obj);
                return;
            default:
                LogViewFragment.lambda$readFile$3((StringBuilder) this.e, (LinkedList) this.f, (String) obj);
                return;
        }
    }
}
