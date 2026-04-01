package o6;

import android.animation.Animator;
import android.view.Window;
import android.widget.PopupMenu;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerFakeViewManager;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabController;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.EmptyTouchHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageRecyclerAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.PageHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ViewPagerDelegate;
import com.samsung.android.gallery.module.idleworker.jobs.CleanUpMdeJob;
import com.samsung.android.gallery.module.thumbnail.YearThumbnailLoader;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.widget.clip.objectcapture.ObjectCaptureView;
import com.samsung.android.gallery.widget.clip.textextraction.TextExtractionView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import java.io.File;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class B implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ B(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((ViewPagerHolder) obj).onViewIn();
                return;
            case 1:
                ((ViewPagerHolder) obj).onViewOut();
                return;
            case 2:
                ((PopupMenu) obj).dismiss();
                return;
            case 3:
                ((Animator) obj).cancel();
                return;
            case 4:
                ((GalleryListView) obj).addDefaultItemAnimator();
                return;
            case 5:
                ((DrawerFakeViewManager) obj).updateDeco();
                return;
            case 6:
                DrawerTabController.lambda$updateFakeView$0((DrawerFakeViewManager) obj);
                return;
            case 7:
                ((IBaseListView) obj).stopScroll();
                return;
            case 8:
                DrawerTabController.lambda$updateItemDecorations$19((GalleryListView) obj);
                return;
            case 9:
                ((MvpBaseFragment) obj).postAnalyticsLog(AnalyticsEventId.EVENT_NAVIGATION_DRAWER);
                return;
            case 10:
                CleanUpMdeJob.lambda$deleteRemainedEditedThumbnail$1((String) obj);
                return;
            case 11:
                CleanUpMdeJob.lambda$deleteRemainedEditedFile$0((String) obj);
                return;
            case 12:
                ((TextExtractionView) obj).notifyToggleConsumed();
                return;
            case 13:
                ((ObjectCaptureView) obj).notifyToggleConsumed();
                return;
            case 14:
                ((Blackboard) obj).post("data://user/dialog/AlbumName", (Object) null);
                return;
            case 15:
                ((Blackboard) obj).post("data://user/dialog/FolderName", (Object) null);
                return;
            case 16:
                ((Blackboard) obj).post("data://user/dialog/DateTimePicker", (Object) null);
                return;
            case 17:
                ((Window) obj).setSoftInputMode(16);
                return;
            case 18:
                ((Blackboard) obj).post("data://user/dialog/RangeDatePicker", (Object) null);
                return;
            case 19:
                ((PageHolder) obj).clear();
                return;
            case 20:
                ((EmptyTouchHandler) obj).destroy();
                return;
            case 21:
                ((PageRecyclerAdapter) obj).reset();
                return;
            case 22:
                ((Consumer) obj).accept(Boolean.TRUE);
                return;
            case 23:
                ((PageHolder) obj).resume();
                return;
            case 24:
                ((PageHolder) obj).stopPreview();
                return;
            case 25:
                ((PageHolder) obj).pause();
                return;
            case 26:
                ((PageHolder) obj).startPreview();
                return;
            case 27:
                ((ViewPagerDelegate) obj).setDefaultViewPagerTransformer();
                return;
            case 28:
                YearThumbnailLoader.lambda$deleteAllYearData$2((File) obj);
                return;
            default:
                FileUtils.delete((File) obj);
                return;
        }
    }
}
