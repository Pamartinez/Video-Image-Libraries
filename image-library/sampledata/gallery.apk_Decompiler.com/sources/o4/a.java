package o4;

import Wf.c;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.container.tablet.bottomtab.BottomTabController;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabController;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.StoriesDrawerSlideAnimationManager;
import com.samsung.android.gallery.app.ui.dialog.creature.RemoveCreatureDialog;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterCategoryFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.AudioPermissionDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BottomSheetDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.FilmStripViewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.ReplayView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.OnDemandPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.SaveLayoutBinder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.theme.StoryThemeView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.LargeImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.SubscriberListenerInfo;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.CloudDownloader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DualShotOptionsViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ShrinkToCameraHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.TableModeHandler;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.lottie.recap.template.RecapTemplate;
import com.samsung.android.gallery.module.thumbnail.YearThumbnailLoader;
import com.samsung.android.gallery.module.utils.MediaFileScanner;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.bottom.BottomTabLayout;
import com.samsung.android.gallery.widget.dialog.AppChooserBuilder;
import com.samsung.android.gallery.widget.dialog.CustomPinPrompt;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.photoview.QuickCropPreView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import java.io.File;
import java.util.function.Consumer;
import x0.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((BottomTabController) obj2).lambda$onBindBottomTabView$1((BottomTabLayout) obj);
                return;
            case 1:
                ((AudioPermissionDelegate) obj2).requestAudioRuntimePermission((Object[]) obj);
                return;
            case 2:
                ((BottomSheetDelegate) obj2).lambda$bindBottomSheet$2((MediaItem) obj);
                return;
            case 3:
                ((Delegate) obj).onConfigurationChanged((Configuration) obj2);
                return;
            case 4:
                ((FilmStripViewDelegate) obj2).lambda$addListenEvent$1((Object[]) obj);
                return;
            case 5:
                DrawerTabController.lambda$updateChildStartSpacing$10((IBaseListView) obj2, (GalleryListView) obj);
                return;
            case 6:
                ((StoriesDrawerSlideAnimationManager) obj2).lambda$addHeaderTranslateAnimator$2((View) obj);
                return;
            case 7:
                ((IStoryHighlightView) obj2).finish();
                return;
            case 8:
                ((ReplayView) obj2).lambda$initProgressView$3((Void) obj);
                return;
            case 9:
                ((YearThumbnailLoader) obj2).lambda$moveAllYearData$4((File) obj);
                return;
            case 10:
                ((OnDemandPage) obj2).lambda$onSaveClick$0(obj);
                return;
            case 11:
                ((SaveLayoutBinder) obj2).lambda$initView$0((Consumer) obj);
                return;
            case 12:
                ((RemoveCreatureDialog) obj2).removeTo((MediaItem) obj);
                return;
            case 13:
                ((MpFilesTable) obj2).filterIds((String) obj);
                return;
            case 14:
                ((AppChooserBuilder) obj2).lambda$build$1((ResolveInfo) obj);
                return;
            case 15:
                ((CustomPinPrompt) obj2).lambda$showDialog$3((InputMethodManager) obj);
                return;
            case 16:
                ((ProgressCircleBuilder) obj2).lambda$create$0((TextView) obj);
                return;
            case 17:
                ((ViewerObject) obj2).lambda$clearSubscribe$0((SubscriberListenerInfo) obj);
                return;
            case 18:
                ((MediaFileScanner) obj2).lambda$isCompleted$0((FileItemInterface) obj);
                return;
            case 19:
                ((StoryThemeView) obj2).onItemSelected(((Integer) obj).intValue());
                return;
            case 20:
                ((CloudDownloader) obj2).afterConfirm(((Boolean) obj).booleanValue());
                return;
            case 21:
                ((DualShotOptionsViewHandler) obj2).updateDualPhotoView(((Boolean) obj).booleanValue());
                return;
            case 22:
                ((ShrinkToCameraHandler) obj2).lambda$onHideMainView$2((IMediaPlayerView) obj);
                return;
            case 23:
                ((TableModeHandler) obj2).lambda$updateLayout$4((Void) obj);
                return;
            case 24:
                ((TransitionInfo) obj2).setDisplayRect((RectF) obj);
                return;
            case 25:
                VexFwkImageTranslator.showInstallPopupAsync$lambda$29((c) obj2, obj);
                return;
            case 26:
                ((QuickCropPreView) obj2).setImageBitmap((Bitmap) obj);
                return;
            case 27:
                ((ClusterCategoryFragment) obj2).lambda$updatePaddingHorizontal$0((Context) obj);
                return;
            case 28:
                ((LargeImageLoader) obj2).cancelRequest((MediaItem) obj);
                return;
            default:
                ((RecapTemplate) obj2).lambda$updateTemplateImageResolutionsFromLottie$1((y) obj);
                return;
        }
    }
}
