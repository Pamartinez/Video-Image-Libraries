package m7;

import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewParent;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerPopupMenu;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryItemAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICategoryHeaderView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.AutoScroller;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.ReorderListener;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.ReplayView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover.FlipCoverMenuHandler;
import com.samsung.android.gallery.module.media.GifPlayer;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.filmstrip3.stories.StoriesFilmStripViewAdapter;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ c(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((FlipCoverMenuHandler) obj).onDestroy();
                return;
            case 1:
                ((FlipCoverMenuHandler) obj).onPageInvalidate();
                return;
            case 2:
                ((FlipCoverMenuHandler) obj).updateVisibility();
                return;
            case 3:
                ((FlipCoverMenuHandler) obj).onPageSelected();
                return;
            case 4:
                ((DrawerPopupMenu) obj).dismiss();
                return;
            case 5:
                ((View) obj).getParent().requestLayout();
                return;
            case 6:
                ((View) obj).getParent().requestLayout();
                return;
            case 7:
                ((Blackboard) obj).post("command:///OnBackPressInvokableStateChanged", (Object) null);
                return;
            case 8:
                ((ICategoryHeaderView) obj).onSelectMeDone();
                return;
            case 9:
                ((CreatureCategoryItemAdapter) obj).editModeChanged();
                return;
            case 10:
                ((SuggesterView) obj).hide();
                return;
            case 11:
                ((AutoScroller) obj).setActive(false);
                return;
            case 12:
                ((ReorderListener) obj).dragStart();
                return;
            case 13:
                ((ReorderListener) obj).begin();
                return;
            case 14:
                ((CountDownTimer) obj).cancel();
                return;
            case 15:
                ((ReorderListener) obj).end();
                return;
            case 16:
                ((GalleryToolbar) obj).setFloatingMode(true);
                return;
            case 17:
                ((ViewParent) obj).requestLayout();
                return;
            case 18:
                ((MvpBaseFragment) obj).postAnalyticsLog(AnalyticsEventId.EVENT_BOTTOM_TAB_MENU);
                return;
            case 19:
                ((Delegate) obj).setScreenMode();
                return;
            case 20:
                ((Delegate) obj).onDestroyView();
                return;
            case 21:
                ((Delegate) obj).onDataChangedOnUi();
                return;
            case 22:
                ((Delegate) obj).onAttach();
                return;
            case 23:
                ((Delegate) obj).onPause();
                return;
            case 24:
                ((Delegate) obj).onDestroy();
                return;
            case 25:
                ((Delegate) obj).onHeaderUpdated();
                return;
            case 26:
                ((Delegate) obj).onResume();
                return;
            case 27:
                ((StoriesFilmStripViewAdapter) obj).onDestroy();
                return;
            case 28:
                ((GifPlayer) obj).stopMovie();
                return;
            default:
                ((ReplayView) obj).handleResolutionChange();
                return;
        }
    }
}
