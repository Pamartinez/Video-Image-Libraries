package B2;

import android.view.MotionEvent;
import android.view.View;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCapturePopup;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardFragment;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardViewAdapter;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabTouchDelegate;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.SearchAutoCompleteAdapter;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.SearchAutoCompleteAdapterV2;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.SearchAutoCompleteFragment;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNameFragment;
import com.samsung.android.gallery.app.ui.list.search.recommendation.RecommendationFragment;
import com.samsung.android.gallery.app.ui.list.search.recommendation.SearchMyTagAdapter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.abstraction.RecommendationViewListener;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.ForegroundViewController;
import com.samsung.android.gallery.app.ui.list.search.toolbar.FloatingRecommendationLauncher;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatTransitoryItemOnDemandViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.header.BasePinView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.EmptyTouchHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoController;
import com.samsung.android.gallery.app.ui.viewer2.remaster.DivideHandler;
import com.samsung.android.gallery.app.ui.viewholders.ConcatImageViewHolder;
import com.samsung.android.gallery.plugins.motionphoto.VideoCtrlView;
import com.samsung.android.gallery.settings.ui.SearchCustomViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripVideoViewHolder;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewViewHolder;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersView;
import com.samsung.android.gallery.widget.tip.PopupTipBuilder;
import com.samsung.android.gallery.widget.videoview.controller.VideoSpeedControllerCompat;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.TouchInteractionHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements View.OnTouchListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ i(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                l lVar = (l) obj;
                if (motionEvent.getAction() == 1) {
                    long currentTimeMillis = System.currentTimeMillis() - lVar.f51o;
                    if (currentTimeMillis < 0 || currentTimeMillis > 300) {
                        lVar.m = false;
                    }
                    lVar.t();
                    lVar.m = true;
                    lVar.f51o = System.currentTimeMillis();
                }
                return false;
            case 1:
                return ((VideoCtrlView) obj).onSeekTouchEvent(view, motionEvent);
            case 2:
                return ((FilmStripVideoViewHolder) obj).onTouch(view, motionEvent);
            case 3:
                return ((RecommendationViewListener) obj).onViewTouch(view, motionEvent);
            case 4:
                return ((RecommendationFragment) obj).onViewTouch(view, motionEvent);
            case 5:
                return ((SearchMyTagAdapter) obj).lambda$new$2(view, motionEvent);
            case 6:
                return ((ForegroundViewController) obj).onTouch(view, motionEvent);
            case 7:
                return ((HoverPreviewViewHolder) obj).onTouch(view, motionEvent);
            case 8:
                return ((SearchCustomViewHolder) obj).lambda$inflateReorderIcon$0(view, motionEvent);
            case 9:
                return ((GalleryRecyclerView) obj).onEmptySpaceSecondaryClick(view, motionEvent);
            case 10:
                return ((VideoController) obj).onPlayPauseViewOnTouched(view, motionEvent);
            case 11:
                return ((FloatingRecommendationLauncher) obj).lambda$stealSearchEditTextTouchEvent$1(view, motionEvent);
            case 12:
                return ((DivideHandler) obj).onTouchScrollView(view, motionEvent);
            case 13:
                return ((StoriesCatTransitoryItemOnDemandViewHolder) obj).lambda$initSearchToolbar$3(view, motionEvent);
            case 14:
                return ((SearchSelectedFiltersView) obj).lambda$initRecyclerView$0(view, motionEvent);
            case 15:
                return ((ConcatImageViewHolder) obj).onTouch(view, motionEvent);
            case 16:
                return ((BasePinView) obj).onEmptySpaceSecondaryClick(view, motionEvent);
            case 17:
                return TouchInteractionHandler.createTouchListener$lambda$0((TouchInteractionHandler) obj, view, motionEvent);
            case 18:
                return ObjectCapturePopup._init_$lambda$0((ObjectCapturePopup) obj, view, motionEvent);
            case 19:
                return ((PopupTipBuilder) obj).lambda$checkIgnoreRootViewTouch$0(view, motionEvent);
            case 20:
                return ((VideoSpeedControllerCompat) obj).onTouchedItem(view, motionEvent);
            case 21:
                return ((ClipboardFragment) obj).onTouchNoItemView(view, motionEvent);
            case 22:
                return ((ClipboardViewAdapter) obj).lambda$setOnTouchListener$1(view, motionEvent);
            case 23:
                return ((SearchAutoCompleteAdapter) obj).lambda$new$0(view, motionEvent);
            case 24:
                return ((SearchAutoCompleteAdapterV2) obj).lambda$new$1(view, motionEvent);
            case 25:
                return ((SearchAutoCompleteFragment) obj).onTouch(view, motionEvent);
            case 26:
                return ((BottomTabTouchDelegate) obj).onTouched(view, motionEvent);
            case 27:
                return ((DrawerTabItemViewHolder) obj).onTouch(view, motionEvent);
            case 28:
                return ((EditCreatureNameFragment) obj).lambda$setRelationshipViewTouchAndOnClickListener$6(view, motionEvent);
            default:
                return ((EmptyTouchHandler) obj).lambda$new$0(view, motionEvent);
        }
    }
}
