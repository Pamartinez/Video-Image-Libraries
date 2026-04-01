package com.samsung.android.gallery.app.controller.album;

import A4.A;
import A8.C0545b;
import Ab.b;
import E9.a;
import Fb.h;
import H3.C0396a;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.ChangeCoverCmd;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.abstraction.CoverPickType;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.AlbumDataHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChangeAlbumCoverCmd extends ChangeCoverCmd {
    /* access modifiers changed from: private */
    public void changeCoverImage(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null) {
            ThreadUtil.postOnBgThread(new b((Object) this, ((Integer) arrayList.get(0)).intValue(), (Object) eventContext, 18));
        }
    }

    private MediaItem getDefaultCover() {
        int albumID = this.mMediaItem.getAlbumID();
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbums) || !this.mMediaItem.isAutoAlbum()) {
            return getDefaultCoverFromAlbumFilesCursor(albumID);
        }
        return getDefaultCoverFromAutoAlbumPicturesCursor(albumID);
    }

    private MediaItem getDefaultCoverFromAlbumFilesCursor(int i2) {
        Cursor query = DbCompat.query(new QueryParams(DbKey.ALBUM_FILES).addAlbumId(i2).setLimit(1));
        if (query != null) {
            try {
                if (query.getCount() > 0 && query.moveToFirst()) {
                    MediaItem load = MediaItemLoader.load(query);
                    query.close();
                    return load;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query == null) {
            return null;
        }
        query.close();
        return null;
        throw th;
    }

    private MediaItem getDefaultCoverFromAutoAlbumPicturesCursor(int i2) {
        Cursor autoAlbumPicturesCursor = DbCompat.autoAlbumApi().getAutoAlbumPicturesCursor(i2, GalleryPreference.getInstance().loadSortBy(String.valueOf(i2), 12));
        if (autoAlbumPicturesCursor != null) {
            try {
                if (autoAlbumPicturesCursor.getCount() > 0 && autoAlbumPicturesCursor.moveToFirst()) {
                    MediaItem load = MediaItemLoader.load(autoAlbumPicturesCursor);
                    autoAlbumPicturesCursor.close();
                    return load;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (autoAlbumPicturesCursor == null) {
            return null;
        }
        autoAlbumPicturesCursor.close();
        return null;
        throw th;
    }

    private UriBuilder getPickAndCropUriBuilder() {
        long fileId = this.mMediaItem.getFileId();
        UriBuilder makeCommonDataByPickAndCrop = makeCommonDataByPickAndCrop();
        makeCommonDataByPickAndCrop.appendArg("FromAlbumCover", true).appendArg("KEY_COVER_PICKER_TYPE", CoverPickType.FROM_ALBUM.toInt()).appendArg("key-current-cover-item", Long.toString(fileId)).appendArg("key-album-type", this.mMediaItem.getAlbumType().toInt());
        return makeCommonDataByPickAndCrop;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$changeCoverImage$0(int i2, EventContext eventContext) {
        AnalyticsDetail analyticsDetail;
        if (i2 == 0) {
            setDefaultCover(eventContext);
        } else if (i2 == 1) {
            startPickerForCover();
        } else if (i2 == 2) {
            startCoverHistoryPicker();
        }
        AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_MENU_CHANGE_COVER_SECOND;
        if (i2 == 0) {
            analyticsDetail = AnalyticsDetail.USE_MOST_RECENT_IMAGE;
        } else {
            analyticsDetail = AnalyticsDetail.SELECT_AN_IMAGE;
        }
        postAnalyticsLog(analyticsEventId, analyticsDetail.toString());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateCropRectInfoFromCoverResult$1(MediaItem mediaItem, AlbumDataHelper.CropRectInfo cropRectInfo, String str) {
        if (mediaItem.isVideo()) {
            cropRectInfo.setOrientation(0);
        }
        cropRectInfo.setMediaType(Integer.parseInt(str));
    }

    private boolean needHistoryMenu() {
        return false;
    }

    private void setDefaultCover(EventContext eventContext) {
        String str;
        MediaItem defaultCover = getDefaultCover();
        if (defaultCover != null) {
            str = AlbumDataHelper.getStringForCropRect(RectUtils.toString(defaultCover.getCropRectRatio()), defaultCover);
        } else {
            str = null;
        }
        AlbumHelper.getInstance().clearCoverInfo(this.mMediaItem.getAlbumID(), str, true);
        getBlackboard().post("data://useralbum_cover_change", new Object[]{Integer.valueOf(this.mMediaItem.getAlbumID()), null, str});
        getBlackboard().postEvent(EventMessage.obtain(104, 1, 0, "location://albums"));
        if (eventContext.isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    private void startCoverHistoryPicker() {
        launchPickerForCrop(getPickAndCropUriBuilder().appendArg("album_cover_history_pick", true).build());
    }

    private void startPickerForCover() {
        if (PreferenceFeatures.OneUi41.SUPPORT_PERMANENT_ALBUM_COVER || this.mMediaItem.getCount() > 1) {
            setCoverByPickAndCrop();
        } else {
            setCoverByCrop();
        }
    }

    private void updateAlbumCover(AtomicReference<String> atomicReference, String str) {
        MediaItemBuilder.setAlbumCover(this.mMediaItem, atomicReference.get(), str);
        ThumbnailLoader.getInstance().removeMemCache(this.mMediaItem);
        getBlackboard().post("data://useralbum_cover_change", new Object[]{Integer.valueOf(this.mMediaItem.getAlbumID()), atomicReference.get(), str});
        getBlackboard().postEvent(EventMessage.obtain(104, 1, 0, "location://albums"));
        BlackboardUtils.publishDataChangedToOtherActivities(getBlackboard(), true);
    }

    private void updateCropRectInfoFromCoverResult(MediaItem mediaItem, AtomicReference<String> atomicReference, AlbumDataHelper.CropRectInfo cropRectInfo, String[] strArr) {
        Optional ofNullable = Optional.ofNullable(strArr[0]);
        Objects.requireNonNull(atomicReference);
        ofNullable.ifPresent(new a(20, atomicReference));
        Optional.ofNullable(strArr[1]).ifPresent(new A(16, (Object) mediaItem, (Object) cropRectInfo));
        Optional.ofNullable(strArr[2]).ifPresent(new C0396a(cropRectInfo, 0));
        Optional.ofNullable(strArr[3]).ifPresent(new C0396a(cropRectInfo, 1));
    }

    public void changeCover(MediaItem mediaItem, String str) {
        if (mediaItem.getStorageType() != null) {
            AlbumHelper instance = AlbumHelper.getInstance();
            AtomicReference atomicReference = new AtomicReference(mediaItem.getPath());
            AlbumDataHelper.CropRectInfo cropRectInfo = new AlbumDataHelper.CropRectInfo(mediaItem);
            if (PreferenceFeatures.OneUi41.SUPPORT_PERMANENT_ALBUM_COVER && !TextUtils.isEmpty((CharSequence) atomicReference.get())) {
                updateCropRectInfoFromCoverResult(mediaItem, atomicReference, cropRectInfo, instance.saveCoverFile(getApplicationContext(), this.mMediaItem.getAlbumID(), (String) atomicReference.get()));
            }
            String stringForCropRect = AlbumDataHelper.getStringForCropRect(instance.checkCoverRatio(str), cropRectInfo);
            if (instance.updateAlbumCoverDB(this.mMediaItem.getAlbumID(), (String) atomicReference.get(), stringForCropRect)) {
                updateAlbumCover(atomicReference, stringForCropRect);
                updateAlbumCoverHistory(mediaItem, instance, atomicReference, stringForCropRect);
            } else {
                Log.e(this.TAG, "updateAlbumCoverDB is failed");
            }
        } else {
            String str2 = this.TAG;
            Log.e(str2, "changeCover is failed. getStorageType is null " + MediaItemUtil.getDebugLog(mediaItem));
        }
        if (getHandler().isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_CHANGE_COVER_IMAGE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (eventContext.isSelectionMode()) {
            MediaItem[] selectedItems = eventContext.getSelectedItems();
            if (selectedItems.length == 1) {
                this.mMediaItem = selectedItems[0];
            } else {
                String str = this.TAG;
                Log.e(str, "Change album cover supported for one album at a time. Current selected items = " + selectedItems.length);
            }
        } else {
            this.mMediaItem = objArr[0];
        }
        if (this.mMediaItem == null) {
            Log.e(this.TAG, "item is null. stop ChangeAlbumCoverCmd");
        } else if (needHistoryMenu() || this.mMediaItem.isCustomCover()) {
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/ChangeAlbumCover").appendArg("need_history_menu", needHistoryMenu()).appendArg("is_custom_cover", this.mMediaItem.isCustomCover()).build()).setOnDataCompleteListener(new h(3, this)).start();
        } else {
            startPickerForCover();
        }
    }

    public void setCoverByCrop() {
        Uri uri;
        UriBuilder makeCommonDataByCrop = makeCommonDataByCrop(360, 360);
        makeCommonDataByCrop.appendArg("FromAlbumCover", true).appendArg("KEY_COVER_PICKER_TYPE", CoverPickType.FROM_ALBUM.toInt()).appendArg("bucketId", this.mMediaItem.getAlbumID()).appendArg("key-album-type", this.mMediaItem.getAlbumType().toInt());
        if (this.mMediaItem.isCustomCover()) {
            if (this.mMediaItem.isImage()) {
                uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else {
                uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            }
            makeCommonDataByCrop.appendArg("content_uri", Uri.withAppendedPath(uri, Long.toString(this.mMediaItem.getFileId())).toString());
        } else {
            Uri uri2 = ContentUri.getUri(this.mMediaItem);
            if (uri2 != null) {
                makeCommonDataByCrop.appendArg("content_uri", uri2.toString());
            }
        }
        launchCropper(makeCommonDataByCrop.build());
    }

    public void setCoverByPickAndCrop() {
        UriBuilder pickAndCropUriBuilder = getPickAndCropUriBuilder();
        if (this.mMediaItem.isMergedAlbum()) {
            pickAndCropUriBuilder.appendArg("bucketIds", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, (List) Arrays.stream(this.mMediaItem.getAlbumsInFolder()).mapToInt(new C0545b(2)).boxed().collect(Collectors.toList())));
            pickAndCropUriBuilder.appendArg("mergedAlbumId", this.mMediaItem.getAlbumID());
        } else {
            pickAndCropUriBuilder.appendArg("bucketId", this.mMediaItem.getAlbumID());
        }
        launchPickerForCrop(pickAndCropUriBuilder.build());
    }

    private void updateAlbumCoverHistory(MediaItem mediaItem, AlbumHelper albumHelper, AtomicReference<String> atomicReference, String str) {
    }
}
