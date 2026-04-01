package C3;

import android.os.Bundle;
import com.samsung.android.gallery.app.activity.DlnaActivity;
import com.samsung.android.gallery.app.activity.external.launcher.UriItemViewLauncher;
import com.samsung.android.gallery.app.controller.creature.EditCreatureNameCmd;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureMultipleCmd;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.app.service.HighlightEncodeService;
import com.samsung.android.gallery.app.service.MediaCaptureService;
import com.samsung.android.gallery.app.service.StoryBaseService;
import com.samsung.android.gallery.app.service.VideoConversionService;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumChoicePresenter;
import com.samsung.android.gallery.app.ui.list.albums.mx.all.MxAllAlbumsPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumsPanePresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.AlbumSettingPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.ScreenshotFilterCustomPresenter;
import com.samsung.android.gallery.app.ui.list.picker.albums.AlbumsPickerPresenter;
import com.samsung.android.gallery.app.ui.list.picker.search.suggestion.SuggestionContainerPickerPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.SearchAutoCompletePresenter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegate;
import com.samsung.android.gallery.app.ui.list.sharings.choice.SharingAlbumChoicePresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.StoryHighlightPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.StoryHighlightListV2Presenter;
import com.samsung.android.gallery.app.ui.list.timeline.ITimelineView;
import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerPresenter;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaViewPrivateAlbumPlayerHandler;
import com.samsung.android.gallery.app.ui.viewer2.crop.CropViewPresenter;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLoadHandler;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.SlideshowPresenter;
import com.samsung.android.gallery.settings.ui.SharingServicePresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: C3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0391a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0391a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((DlnaActivity) obj2).onFinish(obj, bundle);
                return;
            case 1:
                ((AlbumsBaseViewAdapter) obj2).lambda$new$1(obj, bundle);
                return;
            case 2:
                ((AlbumChoicePresenter) obj2).copyMoveToNewAlbum(obj, bundle);
                return;
            case 3:
                ((FloatingRecommendationDelegate) obj2).lambda$createSubscriberList$1(obj, bundle);
                return;
            case 4:
                ((UriItemViewLauncher) obj2).lambda$new$0(obj, bundle);
                return;
            case 5:
                ((SharingServicePresenter) obj2).onSharingNotificationChanged(obj, bundle);
                return;
            case 6:
                ((MediaViewPrivateAlbumPlayerHandler) obj2).lambda$onBind$0(obj, bundle);
                return;
            case 7:
                ((EditCreatureNameCmd) obj2).lambda$new$0(obj, bundle);
                return;
            case 8:
                ((MergeCreatureMultipleCmd) obj2).lambda$new$0(obj, bundle);
                return;
            case 9:
                ((CropViewPresenter) obj2).onImageLoaded(obj, bundle);
                return;
            case 10:
                ((MxAllAlbumsPresenter) obj2).onEssentialAlbumsChanged(obj, bundle);
                return;
            case 11:
                ((DetailsLoadHandler) obj2).lambda$registerSubscriber$5(obj, bundle);
                return;
            case 12:
                ((AlbumsPanePresenter) obj2).onMoveToNextAlbum(obj, bundle);
                return;
            case 13:
                ((SharingAlbumChoicePresenter) obj2).finish(obj, bundle);
                return;
            case 14:
                ((FixDateTimeCmd) obj2).lambda$new$0(obj, bundle);
                return;
            case 15:
                ((AlbumSettingPresenter) obj2).onDataChanged(obj, bundle);
                return;
            case 16:
                ((ScreenshotFilterCustomPresenter) obj2).saveData(obj, bundle);
                return;
            case 17:
                ((ITimelineView) obj2).startScrollAndShrink(obj, bundle);
                return;
            case 18:
                ((SlideshowPresenter) obj2).onAppCastChanged(obj, bundle);
                return;
            case 19:
                ((AlbumsPickerPresenter) obj2).onViewChanged(obj, bundle);
                return;
            case 20:
                ((HighlightEncodeService) obj2).onDialogCancelled(obj, bundle);
                return;
            case 21:
                ((MediaCaptureService) obj2).onDialogCancelled(obj, bundle);
                return;
            case 22:
                ((StoryBaseService) obj2).onDialogCancelled(obj, bundle);
                return;
            case 23:
                ((VideoConversionService) obj2).onDialogCancelled(obj, bundle);
                return;
            case 24:
                ((SuggestionContainerPickerPresenter) obj2).operateClipboard(obj, bundle);
                return;
            case 25:
                ((PicturesPresenter) obj2).lambda$createGlobalSubscriberList$0(obj, bundle);
                return;
            case 26:
                ((StoryHighlightPresenter) obj2).onThemeUpdatedFromOther(obj, bundle);
                return;
            case 27:
                ((SearchAutoCompletePresenter) obj2).onKeywordChanged(obj, bundle);
                return;
            case 28:
                ((VuContainerPresenter) obj2).lambda$createSubscriberList$1(obj, bundle);
                return;
            default:
                ((StoryHighlightListV2Presenter) obj2).onViewChanged(obj, bundle);
                return;
        }
    }
}
