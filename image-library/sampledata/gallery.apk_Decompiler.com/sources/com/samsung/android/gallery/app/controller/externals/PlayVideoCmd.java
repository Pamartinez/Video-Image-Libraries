package com.samsung.android.gallery.app.controller.externals;

import K5.a;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayVideoCmd extends BaseCommand {
    private boolean mIsAnalyticsEnabled = true;
    private MediaItem mMediaItem;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StreamingVideoTask extends AsyncTask<Void, Void, String> {
        private WeakReference<Context> mContext;
        private WeakReference<EventContext> mEventContext;
        private final MediaItem mMediaItem;
        private Dialog mProgressDialog;

        public StreamingVideoTask(EventContext eventContext, MediaItem mediaItem) {
            this.mEventContext = new WeakReference<>(eventContext);
            this.mContext = new WeakReference<>(eventContext.getActivity());
            this.mMediaItem = mediaItem;
            this.mProgressDialog = new ProgressCircleBuilder(eventContext.getContext()).create();
        }

        private Context getContext() {
            return this.mContext.get();
        }

        private EventContext getEventContext() {
            return this.mEventContext.get();
        }

        private String getGdprPlayVideoFailure(Context context) {
            return context.getString(R.string.can_not_play_video_gdpr, new Object[]{StringResources.getCloudBrand()});
        }

        private String getPlayCloudVideoFailure(Context context) {
            return context.getString(R.string.could_not_connect_to_cloud, new Object[]{StringResources.getCloudBrand()});
        }

        private boolean isGdpr(String str) {
            if (CloudErrorType.parseOf(str) == CloudErrorType.GDPR) {
                return true;
            }
            return false;
        }

        private void showToast(Context context, String str) {
            Toast.makeText(context, str, 0).show();
        }

        public void onPreExecute() {
            this.mProgressDialog.show();
        }

        public String doInBackground(Void... voidArr) {
            Context context;
            if (!isCancelled() && (context = getContext()) != null) {
                return SamsungCloudCompat.getStreamingUrl(context, this.mMediaItem.getCloudId(), this.mMediaItem.getCloudServerId());
            }
            return null;
        }

        public void onPostExecute(String str) {
            try {
                this.mProgressDialog.dismiss();
                if (!isCancelled()) {
                    Context context = getContext();
                    EventContext eventContext = getEventContext();
                    if (eventContext != null && context != null) {
                        if (str == null) {
                            showToast(context, getPlayCloudVideoFailure(context));
                        } else if (isGdpr(str)) {
                            SamsungCloudCompat.changeSyncState(context, false);
                            showToast(context, getGdprPlayVideoFailure(context));
                        } else {
                            new PlayGenericVideoCmd().execute(eventContext, this.mMediaItem, Uri.parse(str));
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                String simpleName = StreamingVideoTask.class.getSimpleName();
                Log.e(simpleName, "onPostExecute failed e=" + e.getMessage());
            }
        }
    }

    private void assertSharingVideo() {
        if (this.mMediaItem.isSharing()) {
            String str = this.TAG;
            Log.e(str, toString() + "#onExecute not permitted " + this.mMediaItem);
            new InternalException("WrongPlayWithSharingVideo ", toString() + "#onExecute not permitted with sharing of " + this.mMediaItem).post();
        }
    }

    /* access modifiers changed from: private */
    public void requestStreamingVideoUrl(boolean z) {
        if (z) {
            new StreamingVideoTask(getHandler(), this.mMediaItem).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    public String getEventId() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || mediaItem.getShotMode() == null) {
            return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_VIDEO.toString();
        }
        if (mediaItem.isCloudOnly()) {
            return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_CLOUD_VIDEO.toString();
        }
        return ShotMode.getShotModeEventId(mediaItem.getShotMode());
    }

    public boolean handleShotMode() {
        if (!RemoteDisplayState.getInstance().isDlnaConnected()) {
            return false;
        }
        if (RemoteDisplayState.getInstance().isVideoPlayingOnRemote()) {
            showToast((int) R.string.unable_to_play_presentation);
            return true;
        }
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || !mediaItem.isVideo()) {
            return false;
        }
        showToast((int) R.string.open_video_player_to_play_video_dlna);
        return true;
    }

    public boolean isAnalyticsEnabled() {
        return this.mIsAnalyticsEnabled;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        this.mMediaItem = mediaItem;
        if (mediaItem == null) {
            Log.e(this.TAG, "unable to play video. item is null.");
        } else if (!handleShotMode()) {
            assertSharingVideo();
            if (!this.mMediaItem.isCloudOnly() || MediaItemUtil.supportCloudPreviewPlay(this.mMediaItem)) {
                if (this.mMediaItem.isBroken()) {
                    new PlayVideoChooserCmd().execute(eventContext, this.mMediaItem);
                    return;
                }
                this.mIsAnalyticsEnabled = false;
                new PlayGenericVideoCmd().execute(eventContext, this.mMediaItem);
            } else if (!this.mMediaItem.is8K() || !SdkConfig.atLeast(SdkConfig.GED.S)) {
                checkNetworkStatus(eventContext, new a(13, this));
            } else {
                PlayGenericVideoCmd playGenericVideoCmd = new PlayGenericVideoCmd();
                MediaItem mediaItem2 = this.mMediaItem;
                playGenericVideoCmd.execute(eventContext, mediaItem2, ContentUri.getSecUri(mediaItem2));
            }
        }
    }
}
