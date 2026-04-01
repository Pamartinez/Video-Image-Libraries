package Ad;

import B2.C;
import C3.i;
import E2.h;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.core.util.Supplier;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.FlagSet;
import androidx.media3.common.Format;
import androidx.media3.common.Label;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.effect.GlShaderProgram;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.preference.Preference;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.StoryRelatedAdapter;
import com.samsung.android.gallery.app.ui.viewer2.crop.CropMenuDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.remotegallery.IThumbnailLoader;
import com.samsung.android.gallery.module.remotegallery.RemoteClient;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.logic.RemoteThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import com.samsung.android.gallery.support.utils.SafeMode;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.listview.pinch.v3.DividerItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.mobileservice.social.datasync.DataSyncApi;
import com.samsung.android.sdk.mobileservice.social.datasync.result.DataSyncResult;
import com.samsung.android.sdk.mobileservice.social.group.GroupApi;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupInvitationListResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupMemberResult;
import com.samsung.android.vexfwk.param.VexFwkUnifiedDetectorResult;
import com.samsung.android.vexfwk.sdk.winedetector.VexFwkWineDetector;
import h2.s;
import h2.t;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Supplier, C, DebugViewProvider, h, PropertyAnimator.PropertyAnimationListener, ListViewHolder.OnItemLongClickListener, GroupApi.GroupResultCallback, DataSyncApi.DataSyncResultCallback, Preference.OnPreferenceClickListener, IThumbnailLoader, RemoteThumbnailLoader, Preference.OnPreferenceChangeListener, GlShaderProgram.ErrorListener, WidthAnimator.WidthAnimationCallback, s, ListenerSet.IterationFinishedEvent, Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ j(int i2) {
        this.d = i2;
    }

    public void accept(Object obj) {
        ((DrmSessionEventListener.EventDispatcher) obj).drmKeysRemoved();
    }

    public Object apply(Object obj) {
        switch (this.d) {
            case 4:
                return Format.lambda$toLogString$0((Label) obj);
            case 11:
                return Integer.valueOf(((Cue) obj).zIndex);
            default:
                return new DefaultAnalyticsCollector((Clock) obj);
        }
    }

    public WindowInsetsCompat b(View view, WindowInsetsCompat windowInsetsCompat, t tVar) {
        return CropMenuDelegate.lambda$initBottomNavigation$0(view, windowInsetsCompat, tVar);
    }

    public Object get() {
        switch (this.d) {
            case 0:
                return new VexFwkUnifiedDetectorResult(new ArrayList());
            default:
                return VexFwkWineDetector.detectWineImpl$lambda$8$lambda$5$lambda$4$lambda$3();
        }
    }

    public Bitmap getThumbnail(MediaItem mediaItem) {
        return ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.MEDIUM_KIND);
    }

    public void invoke(Object obj, FlagSet flagSet) {
        DefaultAnalyticsCollector.lambda$new$0((AnalyticsListener) obj, flagSet);
    }

    public boolean isWidthAnimationNeeded(View view, int i2) {
        return MxAlbumsPinchAnimationManager.lambda$prepareDividerViewHolder$3(view, i2);
    }

    public byte[] load(String str, long j2) {
        return new RemoteClient(str).getThumb(j2);
    }

    public void onAnimationEnd(View view) {
        switch (this.d) {
            case 5:
                AlbumsBasePinchAnimationManager.lambda$prepareCheckBoxAnimation$5(view);
                return;
            case 6:
                AlbumsBasePinchAnimationManager.lambda$prepareTitleAnimation$4(view);
                return;
            case 25:
                MxAlbumsPinchAnimationManager.lambda$prepareDividerViewHolder$4(view);
                return;
            default:
                DividerItem.lambda$getHeightAnimator$0(view);
                return;
        }
    }

    public void onError(VideoFrameProcessingException videoFrameProcessingException) {
        switch (this.d) {
            case 21:
                Log.e("BaseGlShaderProgram", "Exception caught by default BaseGlShaderProgram errorListener.", videoFrameProcessingException);
                return;
            default:
                Log.e("DebugViewShaderProgram", "Exception caught by errorListener.", videoFrameProcessingException);
                return;
        }
    }

    public boolean onItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        return StoryRelatedAdapter.lambda$onBindViewHolder$0(listViewHolder, i2, mediaItem, i7);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        return SafeMode.setEnabled(((Boolean) obj).booleanValue());
    }

    public boolean onPreferenceClick(Preference preference) {
        switch (this.d) {
            case 12:
                return new AlertDialog.Builder(preference.getContext()).setTitle((CharSequence) "Signatures").setMessage((CharSequence) LabsDevManageFragment.buildSignatureInfo()).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) null).show();
            case 13:
                return SimpleThreadPool.getInstance().execute(new i(6));
            case 14:
                return LabsDevManageFragment.lambda$addCategoryStatus$51(preference);
            case 18:
                return LabsFragment.lambda$initPreferenceForUtilities$11(preference);
            case 19:
                return LabsFragment.lambda$initPreferenceForUtilities$14(preference);
            default:
                return new FixFaceTable().fix(preference.getContext());
        }
    }

    public void onResult(DataSyncResult dataSyncResult) {
        MdeSharingService.lambda$requestSpaceListSortSync$4(dataSyncResult);
    }

    public void onResult(Object obj) {
        switch (this.d) {
            case 8:
                MdeGroupHelper.lambda$requestMyInvitationList$0((GroupInvitationListResult) obj);
                return;
            default:
                MdeGroupHelper.lambda$requestGroupMemberSync$10((GroupMemberResult) obj);
                return;
        }
    }
}
