package a6;

import K1.c;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import androidx.core.util.Consumer;
import androidx.core.widget.NestedScrollView;
import androidx.core.widget.SeslGoToTopController;
import androidx.core.widget.SeslGoToTopImageView;
import androidx.media3.transformer.WatchdogTimer;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.remote.v2.IPresentationView;
import com.samsung.android.gallery.app.service.FileOperationService;
import com.samsung.android.gallery.app.service.MediaCaptureService;
import com.samsung.android.gallery.app.ui.list.picker.search.SearchClusterResultPickerPresenter;
import com.samsung.android.gallery.app.ui.list.picker.search.SearchPicturesPickerPresenter;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinAdapter;
import com.samsung.android.gallery.app.ui.map.picker.google.GoogleMapPickerContainer;
import com.samsung.android.gallery.app.ui.map.search.SearchMapPresenter;
import com.samsung.android.gallery.app.ui.map.staticmarker.SnapShotMapFragment;
import com.samsung.android.gallery.app.ui.map.staticmarker.StaticMarkerMapFragment;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.IPhotoEditorBehavior;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.VideoEditorReceiveHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.abstraction.FileOperation;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.map.transition.AddMarkerTask;
import com.samsung.android.gallery.module.map.transition.DrawMarkerTask;
import com.samsung.android.gallery.module.media.GifAnimation;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.SystemEnvironment;
import com.samsung.android.gallery.widget.animations.photostacking.PhotoStackingAnimView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.story.transitory.RecapEventListener;
import com.samsung.android.sdk.scs.ai.core.ActionParamExtraction;
import com.samsung.android.sdk.scs.ai.core.GenericInference;
import com.samsung.android.sdk.scs.ai.core.IntentQueryGeneration;
import com.samsung.android.sdk.scs.ai.language.Corrector;
import com.samsung.android.sdk.scs.ai.language.EmojiAugmentor;
import com.samsung.android.sdk.scs.ai.language.Extractor;
import com.samsung.android.sdk.scs.ai.language.SmartCoverer;
import com.samsung.android.sdk.scs.ai.language.SmartReplyer;
import com.samsung.android.sdk.scs.base.tasks.OnCompleteListener;
import com.samsung.android.sdk.scs.base.tasks.Task;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements ThumbnailLoadedListener, PhotoStackingAnimView.onAnimationEndListener, c, SeslGoToTopController.OnGoToTopClickListener, SeslGoToTopImageView.WindowLocationProvider, GifAnimation.AnimationFrameUpdateListener, EventContext.OnSelectionListener, ListViewHolder.OnItemLongClickListener, MapUtil.OnLocationChanged, SystemEnvironment.EnvironmentChangeListener, RecapEventListener, WatchdogTimer.Listener, FileOperation.OnProgressListener, MediaCaptureCompat.OnErrorListener, MapContainer.OnSnapshotReadyListener, ObjectConstructor, IPhotoEditorBehavior.OnHidePhotoEditor, VideoEditorReceiveHandler.OnVideoEditListener, OnCompleteListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public void a(LatLng latLng) {
        ((GoogleMapPickerContainer) this.e).lambda$new$0(latLng);
    }

    public void b(long j2) {
        ((FileOperationService) this.e).onUpdate(j2);
    }

    public Object construct() {
        return ConstructorConstructor.lambda$newUnsafeAllocator$19((Class) this.e);
    }

    public void onAnimationFrameUpdated(Bitmap bitmap) {
        ((IPresentationView) this.e).onAnimationFrameUpdated(bitmap);
    }

    public void onComplete(Task task) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 21:
                ((ActionParamExtraction) obj).lambda$release$0(task);
                return;
            case 22:
                ((GenericInference) obj).lambda$release$0(task);
                return;
            case 23:
                ((IntentQueryGeneration) obj).lambda$release$0(task);
                return;
            case 24:
                ((CountDownLatch) obj).countDown();
                return;
            case 25:
                ((Corrector) obj).lambda$release$2(task);
                return;
            case 26:
                ((EmojiAugmentor) obj).lambda$release$2(task);
                return;
            case 27:
                ((Extractor) obj).lambda$release$4(task);
                return;
            case 28:
                ((SmartCoverer) obj).lambda$release$2(task);
                return;
            default:
                ((SmartReplyer) obj).lambda$release$4(task);
                return;
        }
    }

    public void onEnvironmentChange(Context context) {
        ((TranslationManager) this.e).onLocaleChanged(context);
    }

    public boolean onError(MediaCaptureCompat mediaCaptureCompat, int i2, int i7) {
        return ((MediaCaptureService) this.e).onMediaCaptureError(mediaCaptureCompat, i2, i7);
    }

    public boolean onGoToTopClick() {
        return ((NestedScrollView) this.e).lambda$seslSetGoToTopEnabled$4();
    }

    public boolean onItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        return ((StoriesPinAdapter) this.e).onItemLongClicked(listViewHolder, i2, mediaItem, i7);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((Consumer) obj).accept(bitmap);
                return;
            case 16:
                ((StaticMarkerMapFragment) obj).loadFinish(bitmap, uniqueKey, thumbKind);
                return;
            default:
                AddMarkerTask.lambda$perform$0((DrawMarkerTask) obj, bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public void onLocationChanged(Location location) {
        ((SearchMapPresenter) this.e).lambda$moveToCurrentLocation$2(location);
    }

    public boolean onSelectionCompleted(EventContext eventContext, MediaItem[] mediaItemArr) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 6:
                return ((SearchClusterResultPickerPresenter) obj).onSelectionCompleted(eventContext, mediaItemArr);
            default:
                return ((SearchPicturesPickerPresenter) obj).onSelectionCompleted(eventContext, mediaItemArr);
        }
    }

    public void onSnapshotReady(Bitmap bitmap) {
        ((SnapShotMapFragment) this.e).snapshotReady(bitmap);
    }
}
