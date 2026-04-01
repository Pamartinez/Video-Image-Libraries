package com.samsung.android.gallery.app.ui.viewer2.menu;

import A.a;
import android.content.Intent;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class OpenInOtherWindowMenuItem extends ViewerMenuItem {
    public OpenInOtherWindowMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.open_in_other_window);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFileIds$5(MediaData mediaData, long j2, Consumer consumer) {
        long currentTimeMillis = System.currentTimeMillis();
        int findPositionByFileId = mediaData.findPositionByFileId(j2);
        int max = Math.max(0, findPositionByFileId - 100);
        int min = Math.min(max + 200, mediaData.getCount());
        List<Long> fileIds = mediaData.getFileIds();
        List<Long> subList = fileIds.subList(max, min);
        String str = this.TAG;
        Log.d(str, "loadFileIds" + Logger.vt(Integer.valueOf(fileIds.size()), Integer.valueOf(findPositionByFileId), Integer.valueOf(max), Integer.valueOf(min), Long.valueOf(currentTimeMillis)));
        consumer.accept(subList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$2(MediaItem mediaItem, int i2, String str) {
        startViewer(mediaItem, i2, (List<Long>) null, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$3(MediaItem mediaItem, List list, String str) {
        startViewer(mediaItem, 0, list, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$4(long j2, MediaItem mediaItem, String str, List list) {
        ThreadUtil.postOnUiThreadDelayed(new j(this, mediaItem, list, str), Math.max(0, 180 - (System.currentTimeMillis() - j2)));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (LocationKey.isPrivateAlbum(str) || LocationKey.isCleanOut(str) || LocationKey.isSharings(str) || LocationKey.isRevitalizationView(str) || LocationKey.isHighLightPictures(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return !SystemUi.isInMultiWindowMode(this.mEventContext.getActivity());
    }

    public void loadFileIds(MediaData mediaData, long j2, Consumer<List<Long>> consumer) {
        SimpleThreadPool.getInstance().execute(new i(this, mediaData, j2, consumer));
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentItem.isGroupShot()) {
            currentItem = this.mEventContext.getBestItem();
        }
        MediaItem mediaItem = currentItem;
        String locationKey = this.mEventContext.getLocationKey();
        int i2 = 0;
        if (LocationKey.isAlbumPictures(locationKey)) {
            i2 = ArgumentsUtil.getArgValue(locationKey, "id", 0);
        }
        if (i2 != 0) {
            ThreadUtil.postOnUiThreadDelayed(new g(this, mediaItem, i2, locationKey), 180);
            return true;
        }
        loadFileIds(this.mEventContext.getMediaData(), mediaItem.getFileId(), new h(this, currentTimeMillis, mediaItem, locationKey));
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList", "location://trash").validate(ViewerMenuItem.IS_NOT_DRM).validate(new c(10)).validate(new b(this, 13));
    }

    public void startViewer(MediaItem mediaItem, int i2, List<Long> list, String str) {
        boolean z;
        String str2;
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
            intent.setDataAndType(ContentUri.getUri(mediaItem), mediaItem.getMimeType());
            int i7 = 0;
            if (i2 != 0) {
                intent.putExtra("bucketId", i2);
            } else if (list != null) {
                if (!LocationKey.isTimelinePictures(str) || !PreferenceFeatures.isEnabled(PreferenceFeatures.TimelineSimilarPhotoMode)) {
                    z = false;
                } else {
                    z = true;
                }
                intent.putExtra("useUriList", true);
                intent.putExtra("KEY_ITEM_POSITION", list.indexOf(Long.valueOf(mediaItem.getFileId())));
                QueryParams fileIds = new QueryParams(DbKey.ALL_PICTURES).setFileIds(StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, list));
                if (z) {
                    str2 = "BSs";
                } else {
                    str2 = "BS";
                }
                Blackboard.getApplicationInstance().publish("data://query/quick-view-params", fileIds.setGroupTypes(GroupType.arrayOf(str2)).addDataStamp());
                intent.putExtra("QueryParams", "data://query/quick-view-params");
            }
            intent.putExtra("cloud_included", true);
            intent.putExtra("open_in_other_window", true);
            intent.putExtra("location_key", str);
            intent.setFlags(276828160);
            this.mEventContext.getActivity().startActivity(intent);
            String str3 = this.TAG;
            Integer valueOf = Integer.valueOf(i2);
            if (list != null) {
                i7 = list.size();
            }
            Log.d(str3, "startViewer", valueOf, Integer.valueOf(i7), intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("startViewer failed. e="), this.TAG);
        }
    }
}
