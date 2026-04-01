package com.samsung.android.gallery.app.controller.externals;

import E3.e;
import K4.a;
import N3.c;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SetAsWallpaperChooserDialogCmd extends BaseCommand {
    private final ArrayList<WallpaperType> mItems = new ArrayList<>();
    private MediaItem[] mMediaItems;

    private void executeWallpaperMultiPack(EventContext eventContext, MediaItem[] mediaItemArr) {
        new SetWallpaperCmd().execute(eventContext, mediaItemArr);
        if (eventContext.isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    private String[] getItemsArray() {
        Resources resources = getActivity().getResources();
        int size = this.mItems.size();
        String[] strArr = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = resources.getString(this.mItems.get(i2).value());
        }
        return strArr;
    }

    private boolean isMultiPack() {
        if (this.mMediaItems.length > 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$0(MediaItem[] mediaItemArr, long j2, EventContext eventContext, Integer num) {
        String str = this.TAG;
        Log.d(str, "onPreExecute#PppChecker" + Logger.vt(Integer.valueOf(mediaItemArr.length), num, Long.valueOf(j2)));
        if (num.intValue() > 0) {
            mediaItemArr = replacePppItem(eventContext, mediaItemArr);
        }
        super.onPreExecute(eventContext, mediaItemArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$1(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (GppmHelper.SUPPORT_DONATION_MULTIPLE) {
            EventContext eventContext2 = eventContext;
            MediaItem[] mediaItemArr2 = mediaItemArr;
            executePppChecker(eventContext2, mediaItemArr2, new e(this, mediaItemArr2, System.currentTimeMillis(), eventContext2, 1));
            return;
        }
        super.onPreExecute(eventContext, mediaItemArr);
    }

    private void loadItems() {
        if (isMultiPack()) {
            this.mItems.add(WallpaperType.LOCK_SCREEN);
            this.mItems.add(WallpaperType.COVER_SCREEN);
            return;
        }
        if (!this.mMediaItems[0].isVideo()) {
            this.mItems.add(WallpaperType.LOCK_SCREEN);
            this.mItems.add(WallpaperType.HOME_SCREEN);
            this.mItems.add(WallpaperType.LOCK_AND_HOME_SCREEN);
        } else if (Features.isEnabled(Features.USE_SET_AS_VIDEO_WALLPAPER) && !isDexMode()) {
            this.mItems.add(WallpaperType.LOCK_SCREEN);
        }
        if (supportCoverScreen(this.mMediaItems[0])) {
            this.mItems.add(WallpaperType.COVER_SCREEN);
        }
        if (supportClearCoverScreen(this.mMediaItems[0])) {
            this.mItems.add(WallpaperType.CLEAR_COVER_SCREEN);
        }
        if (supportAOD(this.mMediaItems[0])) {
            this.mItems.add(WallpaperType.ALWAYS_ON_DISPLAY);
        }
        if (supportWatchFace(this.mMediaItems[0])) {
            this.mItems.add(WallpaperType.WATCH_FACE);
        }
        if (supportCallBackground()) {
            this.mItems.add(WallpaperType.CALL_BACKGROUND);
        }
        if (PreferenceFeatures.OneUi6x.IS_ONE_UI_61) {
            this.mItems.add(WallpaperType.ALARM);
        }
    }

    /* access modifiers changed from: private */
    public void onItemSelected(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList == null) {
            Log.d(this.TAG, "selected data null");
            return;
        }
        this.mItems.get(((Integer) arrayList.get(0)).intValue()).onExecuteCmd(eventContext, this.mMediaItems);
        if (eventContext.isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    private boolean supportAOD(MediaItem mediaItem) {
        if (!mediaItem.isImage() || !Features.isEnabled(Features.IS_AOD_ENABLED)) {
            return false;
        }
        return true;
    }

    private boolean supportCallBackground() {
        if (Features.isEnabled(Features.IS_MUM_MODE) || !Features.isEnabled(Features.SUPPORT_CALL_BG_PROVIDER_PACKAGE)) {
            return false;
        }
        return true;
    }

    private boolean supportClearCoverScreen(MediaItem mediaItem) {
        if (((!mediaItem.isImage() || !Features.isEnabled(Features.IS_CLEAR_COVER_SCREEN_ENABLED)) && (!mediaItem.isVideo() || !Features.isEnabled(Features.SUPPORT_WALLPAPER_VIDEO_COVER))) || !SeApiCompat.isClearCoverAttached(getContext())) {
            return false;
        }
        return true;
    }

    private boolean supportCoverScreen(MediaItem mediaItem) {
        if (mediaItem.isImage() && Features.isEnabled(Features.IS_COVER_SCREEN_ENABLED)) {
            return true;
        }
        if (!mediaItem.isVideo() || !Features.isEnabled(Features.USE_SET_AS_COVER_VIDEO_WALLPAPER)) {
            return false;
        }
        return true;
    }

    private boolean supportMultiPackCoverScreen() {
        if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR5) || !Features.isEnabled(Features.USE_SET_AS_COVER_VIDEO_WALLPAPER)) {
            return false;
        }
        return true;
    }

    private boolean supportWatchFace(MediaItem mediaItem) {
        Intent intent = new Intent("com.samsung.android.set.image.myphoto");
        Uri uri = ContentUri.getUri(mediaItem);
        if (PocFeatures.DUAL_PHOTO_PREVIEW && mediaItem.isStream()) {
            uri = ContentUri.getStreamUri(getContext(), mediaItem);
        }
        intent.setDataAndType(uri, mediaItem.getMimeType());
        if (!mediaItem.isImage() || PackageMonitorCompat.getInstance().resolveActivity(intent, 65536) == null) {
            return false;
        }
        return true;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_SET_AS_WALLPAPER_CHOOSER.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr = objArr[0];
        this.mMediaItems = mediaItemArr;
        if (mediaItemArr == null || mediaItemArr.length == 0 || mediaItemArr[0] == null) {
            Log.e(this.TAG, "Failed to execute -> item is null");
        } else if (!isMultiPack() || supportMultiPackCoverScreen()) {
            loadItems();
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/ViewerListChooser").appendArg("list_chooser_title", (int) R.string.set_as_wallpaper).appendArg("list_chooser_items", getItemsArray()).build()).setOnDataCompleteListener(new a(12, this)).start();
        } else {
            executeWallpaperMultiPack(eventContext, this.mMediaItems);
        }
    }

    public void onPreExecute(EventContext eventContext, Object... objArr) {
        loadAndExecute(eventContext, new c(0, this, eventContext));
    }
}
