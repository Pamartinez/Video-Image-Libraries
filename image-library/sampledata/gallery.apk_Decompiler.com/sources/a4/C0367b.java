package A4;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.activity.GalleryPrivateActivity;
import com.samsung.android.gallery.app.activity.external.GalleryExternalActivity;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListBottomHandler;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListDelegate;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.RecommendationFragment;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegate;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderMapView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedDataHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedInfo;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.StoryRelatedView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryCoverViewHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.dynamicview.DynamicViewPlayerHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverViewerHandler;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.analyticsquery.SasCount;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.map.clustering.QuadTreeClusterAlgorithm;
import com.samsung.android.gallery.module.map.clustering.StaticCluster;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mde.MdeSpaceGroupCursor;
import com.samsung.android.gallery.module.mde.abstraction.ConnectListener;
import com.samsung.android.gallery.plugins.motionphoto.Functions;
import com.samsung.android.gallery.plugins.motionphoto.VideoCtrlView;
import com.samsung.android.gallery.plugins.portrait.LiveEffectActivity;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;
import com.samsung.android.gallery.widget.fastoption2.FastOptionMenuItem;
import com.samsung.android.gallery.widget.fastoption2.FastOptionMoreMenu;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripGroupViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripLayoutManager;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripMotionPhotoViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripProgressBarDelegate;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.flexboxlist.FlexBoxListView;
import com.samsung.android.gallery.widget.floatingui.FloatingViewsInvalidator;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Consumer;

/* renamed from: A4.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0367b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0367b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((BaseListBottomHandler) obj2).lambda$updateListViewBottomPadding$3((GalleryListView) obj);
                return;
            case 1:
                ((BaseListDelegate) obj2).onEnterTransitionEnd(((Boolean) obj).booleanValue());
                return;
            case 2:
                ((SelectableChecker) obj).done((MediaItem[]) obj2);
                return;
            case 3:
                ((BaseListViewAdapter) obj2).lambda$requestXLargeForAllVisible$2((ListViewHolder) obj);
                return;
            case 4:
                ((LinkedList) obj2).add((PreviewViewHolder) obj);
                return;
            case 5:
                ((DynamicViewPlayerHandler) obj2).onBgmLoaded(((Boolean) obj).booleanValue());
                return;
            case 6:
                ((FastOptionMoreMenu) obj2).addItem((FastOptionMenuItem) obj);
                return;
            case 7:
                ((FastOptionView) obj2).lambda$updateItemLayoutParams$2((FastOptionItemView) obj);
                return;
            case 8:
                ((StoryHeaderMapView) obj2).onMapPlaced(obj);
                return;
            case 9:
                ((FlipCoverViewerHandler) obj2).lambda$onViewAttached$0((PhotoView) obj);
                return;
            case 10:
                ((StaticCluster) obj).remove(((QuadTreeClusterAlgorithm.QuadItem) obj2).getTopItem());
                return;
            case 11:
                ((Functions) obj2).lambda$capture$2((Bitmap) obj);
                return;
            case 12:
                ((VideoCtrlView) obj2).updateSeekHandler(((Float) obj).floatValue());
                return;
            case 13:
                ((FilmStripGroupViewHolder) obj2).lambda$loadFrameBitmap$3((MediaItem) obj);
                return;
            case 14:
                ((FilmStripLayoutManager) obj2).lambda$centerAdjustmentOnMeasure$0((View) obj);
                return;
            case 15:
                ((FilmStripMotionPhotoViewHolder) obj2).lambda$loadFrameBitmap$5((MediaItem) obj);
                return;
            case 16:
                ((FilmStripViewHolder) obj2).lambda$onFocused$0((FilmStripProgressBarDelegate) obj);
                return;
            case 17:
                ((GalleryPrivateActivity) obj2).lambda$onCreate$3((Integer) obj);
                return;
            case 18:
                ((PrintWriter) obj2).println(" " + ((String) obj));
                return;
            case 19:
                ((RecommendationFragment) obj2).lambda$initializeTagView$0((FlexBoxListView) obj);
                return;
            case 20:
                ((GalleryExternalActivity) obj2).lambda$onSaveInstanceState$1((Clipboard) obj);
                return;
            case 21:
                ((FloatingRecommendationDelegate) obj2).setHistoryItem((ArrayList) obj);
                return;
            case 22:
                ((RelatedInfo) obj).prepare((RelatedInfo) obj2);
                return;
            case 23:
                ((StoryRelatedView) obj2).lambda$loadData$1((RelatedDataHolder) obj);
                return;
            case 24:
                ((SasCount) obj2).lambda$apply$18((SharedPreferences.Editor) obj);
                return;
            case 25:
                ((MdeSharingService) obj2).lambda$connectSessionAsync$2((ConnectListener) obj);
                return;
            case 26:
                ((MdeSpaceGroupCursor) obj2).lambda$parseStr$0((String) obj);
                return;
            case 27:
                ((LiveEffectActivity) obj2).lambda$onCreate$0((FoldStateManager) obj);
                return;
            case 28:
                ((FloatingViewsInvalidator) obj2).lambda$run$0((Integer) obj);
                return;
            default:
                ((StoryCoverViewHolder) obj2).lambda$bindView$0(obj);
                return;
        }
    }
}
