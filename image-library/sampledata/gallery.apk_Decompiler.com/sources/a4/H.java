package A4;

import J5.e;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.preference.Preference;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.DelegateHelper;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.creature.people.RelationshipPickerCmd;
import com.samsung.android.gallery.app.controller.externals.FetchContentsForKnoxCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseBlurPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.RelationshipView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.StoryRelatedAdapter;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.SubItemLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaViewPlayerHandler;
import com.samsung.android.gallery.module.album.hide.AlbumsHideManager;
import com.samsung.android.gallery.module.c2pa.C2paScsImpl;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewPlayer;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import com.samsung.android.gallery.settings.ui.LabsUserTrialFragment;
import com.samsung.android.gallery.settings.ui.SharingServiceFragment;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.LiveMotionAdapter;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.social.group.GroupApi;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupInvitationResult;
import com.samsung.android.sdk.scs.base.tasks.OnCompleteListener;
import com.samsung.android.sdk.scs.base.tasks.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class H implements EventContext.OnSelectionListener, DynamicViewPlayInfo.CallBack, PropertyAnimator.PropertyAnimationListener, ThumbnailLoadedListener, GroupApi.GroupResultCallback, DataCollectionDelegate.OnDataCompletionListener, Preference.OnPreferenceChangeListener, AlbumsHideManager.OnAlbumHideListener, FutureListener, ListenerSet.Event, ListenerSet.IterationFinishedEvent, OnCompleteListener, Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ H(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public void accept(Object obj) {
        ((MediaSourceEventListener.EventDispatcher) this.e).lambda$downstreamFormatChanged$5((MediaLoadData) this.f, (MediaSourceEventListener) obj);
    }

    public void invoke(Object obj) {
        switch (this.d) {
            case 16:
                ((AnalyticsListener) obj).onPlaybackParametersChanged((AnalyticsListener.EventTime) this.e, (PlaybackParameters) this.f);
                return;
            case 17:
                ((AnalyticsListener) obj).onTracksChanged((AnalyticsListener.EventTime) this.e, (Tracks) this.f);
                return;
            case 18:
                ((AnalyticsListener) obj).onDownstreamFormatChanged((AnalyticsListener.EventTime) this.e, (MediaLoadData) this.f);
                return;
            case 19:
                ((AnalyticsListener) obj).onDeviceInfoChanged((AnalyticsListener.EventTime) this.e, (DeviceInfo) this.f);
                return;
            case 20:
                ((AnalyticsListener) obj).onDrmSessionManagerError((AnalyticsListener.EventTime) this.e, (Exception) this.f);
                return;
            case 21:
                ((AnalyticsListener) obj).onMediaMetadataChanged((AnalyticsListener.EventTime) this.e, (MediaMetadata) this.f);
                return;
            default:
                ((AnalyticsListener) obj).onAvailableCommandsChanged((AnalyticsListener.EventTime) this.e, (Player.Commands) this.f);
                return;
        }
    }

    public void onAnimationEnd(View view) {
        ((AlbumsBaseBlurPinchAnimationManager) this.e).lambda$prepareSubViewAnimation$4((View) this.f, view);
    }

    public void onComplete(Task task) {
        ((C2paScsImpl) this.e).lambda$executeIDpsC2pa$3((java.util.function.Consumer) this.f, task);
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        switch (this.d) {
            case 8:
                DelegateHelper.lambda$startAppInfo$0((String) this.f, (Context) this.e, eventContext, arrayList);
                return;
            case 12:
                ((FileOpCmd) this.e).lambda$startAddToAlbumDialogCmd$5((MediaItem) this.f, eventContext, arrayList);
                return;
            case 25:
                ((RelationshipPickerCmd) this.e).lambda$onExecute$0((String) this.f, eventContext, arrayList);
                return;
            default:
                ((FetchContentsForKnoxCmd) this.e).lambda$showCloudSyncConfirmDialog$4((java.util.function.Consumer) this.f, eventContext, arrayList);
                return;
        }
    }

    public void onFutureDone(Future future) {
        ((RelationshipView) this.e).lambda$bind$2((String) this.f, future);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 3:
                ((AlbumsBaseViewAdapter) this.e).lambda$setFolderViewBitmaps$3((ThumbnailRequestHolder) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 4:
                ((SubItemLoader) this.e).lambda$setGroupItemImage$2((MediaItem) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 5:
                ((StoryRelatedAdapter) this.e).lambda$bindThumbnail$2((ListViewHolder) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 14:
                ((MediaViewPlayerHandler) this.e).lambda$eraseVideoBackupInfo$24((MediaItem) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 24:
                Optional.ofNullable((FilterResultsEntity) ((HashMap) this.e).get(((MediaItem) this.f).getSubCategory())).ifPresent(new e(bitmap, 0));
                return;
            default:
                ((LiveMotionAdapter) this.e).lambda$preloadBitmap$0((MediaItem) this.f, bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public void onPlayComplete() {
        ((MotionPhotoViewPlayer) this.e).lambda$initDynamicViewPlayback$10((MediaPlayerCompat) this.f);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        switch (this.d) {
            case 9:
                return ((LabsFragment) this.e).lambda$initPreferenceForOneUi5x$18((Preference) this.f, preference, obj);
            case 10:
                return ((LabsUserTrialFragment) this.e).lambda$addCategoryCommon$10((Preference) this.f, preference, obj);
            default:
                return ((SharingServiceFragment) this.e).lambda$loadPreference$4((Context) this.f, preference, obj);
        }
    }

    public void onResult(Object obj) {
        switch (this.d) {
            case 6:
                MdeGroupHelper.lambda$requestGroupInvitationAcceptance$1((Blackboard) this.e, (Context) this.f, (BooleanResult) obj);
                return;
            default:
                MdeGroupHelper.lambda$requestGroupCreationForShare$6((String) this.f, (BiConsumer) this.e, (GroupInvitationResult) obj);
                return;
        }
    }

    public boolean onSelectionCompleted(EventContext eventContext, MediaItem[] mediaItemArr) {
        return ((BaseListPresenter) this.e).lambda$onSelectionRequested$6((String) this.f, eventContext, mediaItemArr);
    }

    public /* synthetic */ H(int i2, Object obj, String str) {
        this.d = i2;
        this.f = str;
        this.e = obj;
    }

    public void invoke(Object obj, FlagSet flagSet) {
        ((DefaultAnalyticsCollector) this.e).lambda$setPlayer$1((Player) this.f, (AnalyticsListener) obj, flagSet);
    }
}
