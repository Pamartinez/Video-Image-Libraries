package com.samsung.android.gallery.app.activity.external.launcher;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.abstraction.FlipCoverSavedData;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class QuickViewLauncher {
    public static final boolean SUPPORT_FLIP_COVER_GALLERY = Features.isEnabled(Features.SUPPORT_FLIP_COVER_GALLERY);
    protected final String TAG = getClass().getSimpleName();
    protected int[] mAlbumIds;
    protected Blackboard mBlackboard;
    Runnable mFailCallback;
    protected final QuickViewItemLoader mItemLoader;
    protected LaunchIntent mLaunchIntent;
    protected MediaData mMediaData;
    private FlipCoverSavedData mRestartSavedDataFromFlipCover;

    public QuickViewLauncher(Blackboard blackboard, LaunchIntent launchIntent, Runnable runnable) {
        this.mBlackboard = blackboard;
        this.mLaunchIntent = launchIntent;
        this.mFailCallback = runnable;
        QuickViewItemLoader quickViewItemLoader = new QuickViewItemLoader(AppResources.getAppContext());
        this.mItemLoader = quickViewItemLoader;
        quickViewItemLoader.setLaunchIntent(launchIntent);
        if (SUPPORT_FLIP_COVER_GALLERY) {
            this.mRestartSavedDataFromFlipCover = (FlipCoverSavedData) this.mBlackboard.pop("data://viewer_recover_data_from_flip_cover");
        }
    }

    private ThumbKind getThumbKind(MediaItem mediaItem) {
        if (mediaItem.getStorageType() != StorageType.UriItem || !mediaItem.isImage()) {
            return ThumbKind.MEDIUM_KIND;
        }
        return ThumbKind.XLARGE_NC_KIND;
    }

    public void destroy() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.close();
            this.mMediaData = null;
        }
    }

    public abstract void execute();

    public Activity getActivity() {
        return BlackboardUtils.readActivity(this.mBlackboard);
    }

    public int getPositionFromFlipCoverSavedData() {
        FlipCoverSavedData flipCoverSavedData;
        if (!SUPPORT_FLIP_COVER_GALLERY || (flipCoverSavedData = this.mRestartSavedDataFromFlipCover) == null) {
            return 0;
        }
        return flipCoverSavedData.position;
    }

    public Uri getUriFromFlipCoverSavedData() {
        FlipCoverSavedData flipCoverSavedData;
        Uri uri;
        if (!SUPPORT_FLIP_COVER_GALLERY || (flipCoverSavedData = this.mRestartSavedDataFromFlipCover) == null || (uri = flipCoverSavedData.uri) == null) {
            return this.mLaunchIntent.getUriData();
        }
        return uri;
    }

    public boolean hasViewBuckets() {
        return !this.mLaunchIntent.getViewBuckets().isEmpty();
    }

    public boolean isRestartedFromFlipCover() {
        if (!SUPPORT_FLIP_COVER_GALLERY || this.mRestartSavedDataFromFlipCover == null) {
            return false;
        }
        return true;
    }

    public void loadThumbnailSync(MediaItem mediaItem) {
        if (mediaItem != null) {
            long currentTimeMillis = System.currentTimeMillis();
            Bitmap loadThumbnailSync = ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, getThumbKind(mediaItem), true);
            String str = this.TAG;
            Log.d(str, "loadThumbnailSync " + Logger.toSimpleString(loadThumbnailSync) + " +" + (System.currentTimeMillis() - currentTimeMillis));
            this.mBlackboard.publish("data://viewer_first_bitmap", new Object[]{mediaItem, loadThumbnailSync});
        }
    }
}
