package com.samsung.android.gallery.app.controller.story;

import E5.b;
import O3.o;
import U3.d;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveNotifiedContentCmd extends EditNotifiedContentCmd {
    private String getAlbumPath(int i2) {
        if (kindOfCollage(i2)) {
            return StorageInfo.getDefault().collage + File.separator;
        } else if (i2 != StoryType.AGIF.getValue()) {
            return null;
        } else {
            return StorageInfo.getDefault().gif + File.separator;
        }
    }

    private boolean kindOfCollage(int i2) {
        if (i2 == StoryType.COLLAGE.getValue() || i2 == StoryType.VIDEO_COLLAGE.getValue() || i2 == StoryType.COLLAGE_THEN_AND_NOW.getValue() || i2 == StoryType.REDISCOVER_DAY.getValue()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$saveAsFile$2(Context context, MediaItem mediaItem, ArrayList arrayList, String str, Uri uri) {
        if (uri != null) {
            updateStoryDB(context, mediaItem, arrayList, uri);
        }
    }

    private void makeToast(MediaItem mediaItem, boolean z) {
        if (!z) {
            showToast(getContext().getResources().getString(R.string.unable_to_save));
        } else if (MediaItemStory.getStoryType(mediaItem) == StoryType.AGIF.getValue()) {
            showToast(getContext().getResources().getString(R.string.toast_image_saved_in, new Object[]{Optional.of(StorageInfo.getDefault()).map(new o(23)).get()}));
        } else {
            showToast(getContext().getResources().getString(R.string.toast_image_saved_in, new Object[]{Optional.of(StorageInfo.getDefault()).map(new o(24)).get()}));
        }
    }

    private boolean saveAsFile(Context context, MediaItem mediaItem, ArrayList<MediaItem> arrayList) {
        String path;
        String nameFromPath = FileUtils.getNameFromPath(mediaItem.getPath());
        if (TextUtils.isEmpty(nameFromPath)) {
            return false;
        }
        String albumPath = getAlbumPath(MediaItemStory.getStoryType(mediaItem));
        if (TextUtils.isEmpty(albumPath)) {
            return false;
        }
        FileUtils.makeDirectoryIfAbsent(albumPath);
        String str = albumPath + nameFromPath;
        if (FileUtils.exists(str) || (path = mediaItem.getPath()) == null || !FileUtils.move(path, str)) {
            return false;
        }
        MediaScannerConnection.scanFile(context, new String[]{str}, (String[]) null, new d(context, mediaItem, arrayList));
        return true;
    }

    public static void updateStoryDB(Context context, MediaItem mediaItem, ArrayList<MediaItem> arrayList, Uri uri) {
        int storyType = MediaItemStory.getStoryType(mediaItem);
        long[] array = arrayList.stream().mapToLong(new b(5)).toArray();
        if (uri != null) {
            DbCompat.storyNotificationApi().insertVisualArt(array, uri.toString(), StoryHelper.isCollage(storyType));
        }
        DbCompat.storyApi().deleteStory(new FileItemInterface[]{mediaItem}, false);
        StoryUpdateNotifier.getInstance().notifyStory(context, true);
    }

    public void executeInternal(Context context, MediaItem mediaItem, ArrayList<MediaItem> arrayList, int i2) {
        if (saveAsFile(context, mediaItem, arrayList)) {
            makeToast(mediaItem, true);
        } else {
            makeToast(mediaItem, false);
        }
    }

    public String getEventId() {
        if (kindOfCollage(this.mStoryType)) {
            return AnalyticsEventId.EVENT_NOTIFICATION_PREVIEW_COLLAGE_SAVE.toString();
        }
        if (this.mStoryType == StoryType.AGIF.getValue()) {
            return AnalyticsEventId.EVENT_NOTIFICATION_PREVIEW_ANIMATE_SAVE.toString();
        }
        return null;
    }
}
