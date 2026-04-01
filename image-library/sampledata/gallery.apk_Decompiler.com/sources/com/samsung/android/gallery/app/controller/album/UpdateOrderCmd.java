package com.samsung.android.gallery.app.controller.album;

import A4.C0371f;
import A5.a;
import B8.b;
import Fa.C0571z;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.local.LocalAlbumsApi;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UpdateOrderCmd extends BaseCommand {
    OrderUpdateCallback mCallback;
    private int mHighlightIndex = -1;
    private int mHighlightOrgIndex = -1;
    private boolean mIsMxEssentialView = false;
    protected boolean mIsMxLegacyView = false;
    private int mTargetIndex = -1;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OrderUpdateCallback {
        void onOrderUpdated();
    }

    private void addOperation(ContentProviderOperation contentProviderOperation, ArrayList<ContentProviderOperation> arrayList) {
        arrayList.add(contentProviderOperation);
        if (arrayList.size() >= 500) {
            updateInBatch(arrayList);
            arrayList.clear();
        }
    }

    private List<MediaItem> findNewFolderItemIndex(List<MediaItem> list, ArrayList<MediaItem> arrayList) {
        return Arrays.asList(new MediaItem[]{getCandidateFolder(list), getHighlightedMediaItem(arrayList)});
    }

    private List<MediaItem> getAlbumsData(EventContext eventContext) {
        List<MediaItem> asList = Arrays.asList(eventContext.getAllItems());
        if (this.mIsMxEssentialView || this.mIsMxLegacyView) {
            return (List) asList.stream().filter(new C0571z(15)).collect(Collectors.toList());
        }
        return asList;
    }

    private List<MediaItem> getAllItems(EventContext eventContext) {
        List<MediaItem> albumsData = getAlbumsData(eventContext);
        if (!this.mIsMxEssentialView) {
            return albumsData;
        }
        MediaData open = MediaDataFactory.getInstance(getBlackboard()).open("location://albums", true);
        if (open == null) {
            try {
                Log.e(this.TAG, "getAllItems: albumData is null");
                if (open != null) {
                    open.close();
                }
                return null;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            ArrayList arrayList = new ArrayList(open.getFullData());
            if (this.mTargetIndex != -1) {
                rearrangeTargetItem(albumsData, arrayList);
            } else {
                List<MediaItem> findNewFolderItemIndex = findNewFolderItemIndex(albumsData, arrayList);
                if (findNewFolderItemIndex.get(0) == null) {
                    List<MediaItem> asList = Arrays.asList((MediaItem[]) arrayList.toArray(new MediaItem[open.getCount()]));
                    open.close();
                    return asList;
                }
                rearrangeFolderItem(arrayList, findNewFolderItemIndex);
            }
            List<MediaItem> asList2 = Arrays.asList((MediaItem[]) arrayList.toArray(new MediaItem[open.getCount()]));
            open.close();
            return asList2;
        }
        throw th;
    }

    private static MediaItem getCandidateFolder(List<MediaItem> list) {
        Optional<MediaItem> findFirst = list.stream().filter(new C0571z(14)).findFirst();
        if (findFirst.isPresent()) {
            return findFirst.get();
        }
        return null;
    }

    private MediaItem getHighlightedMediaItem(ArrayList<MediaItem> arrayList) {
        Exception e;
        MediaItem mediaItem;
        HashMap hashMap = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        arrayList.stream().filter(new C0571z(16)).forEach(new C0371f((Object) hashMap, (Object) arrayList, (Object) arrayList2, 4));
        try {
            int i2 = this.mHighlightIndex;
            if (i2 == -1) {
                return null;
            }
            mediaItem = (MediaItem) arrayList2.get(i2);
            try {
                this.mHighlightOrgIndex = ((Integer) hashMap.get(Integer.valueOf(mediaItem.getAlbumID()))).intValue();
                return mediaItem;
            } catch (Exception e7) {
                e = e7;
                Log.e(this.TAG, e.getMessage());
                return mediaItem;
            }
        } catch (Exception e8) {
            mediaItem = null;
            e = e8;
            Log.e(this.TAG, e.getMessage());
            return mediaItem;
        }
    }

    private MediaItem getItem(List<MediaItem> list, int i2) {
        if (i2 == -1 || list.size() <= i2) {
            return null;
        }
        return list.get(i2);
    }

    private int getOrgIndex(ArrayList<MediaItem> arrayList, int i2) {
        return ((Integer) arrayList.stream().filter(new b(i2, 3)).findFirst().map(new a(13, arrayList)).orElse(-1)).intValue();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getAlbumsData$1(MediaItem mediaItem) {
        return !mediaItem.isSharing();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getCandidateFolder$3(MediaItem mediaItem) {
        if (!mediaItem.isFolder() || mediaItem.getAlbumID() != -1) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getHighlightedMediaItem$4(HashMap hashMap, ArrayList arrayList, ArrayList arrayList2, MediaItem mediaItem) {
        hashMap.put(Integer.valueOf(mediaItem.getAlbumID()), Integer.valueOf(arrayList.indexOf(mediaItem)));
        arrayList2.add(mediaItem);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getOrgIndex$2(int i2, MediaItem mediaItem) {
        if (mediaItem.getAlbumID() == i2) {
            return true;
        }
        return false;
    }

    private void rearrangeFolderItem(ArrayList<MediaItem> arrayList, List<MediaItem> list) {
        Log.d(this.TAG, "find item # create group ");
        int i2 = -1;
        for (MediaItem mediaItem : list.get(0).getItemsInFolder()) {
            if (i2 == -1) {
                i2 = arrayList.indexOf(mediaItem);
            }
            int indexOf = arrayList.indexOf(mediaItem);
            int i7 = this.mHighlightOrgIndex;
            if (indexOf < i7) {
                this.mHighlightOrgIndex = i7 - 1;
            }
            arrayList.remove(indexOf);
        }
        int i8 = this.mHighlightOrgIndex;
        if (i8 >= 0) {
            i2 = i8;
        } else if (i2 == -1 || i2 >= arrayList.size()) {
            i2 = arrayList.size();
        }
        arrayList.add(i2, list.get(0));
    }

    private void rearrangeTargetItem(List<MediaItem> list, ArrayList<MediaItem> arrayList) {
        int i2 = this.mTargetIndex;
        int i7 = i2 - 1;
        MediaItem item = getItem(list, i2);
        int i8 = 0;
        MediaItem item2 = getItem(list, Math.max(i7, 0));
        if (item2 != null && item != null) {
            int orgIndex = getOrgIndex(arrayList, item2.getAlbumID());
            int orgIndex2 = getOrgIndex(arrayList, item.getAlbumID());
            if (!(orgIndex == -1 || orgIndex2 == -1)) {
                MediaItem remove = arrayList.remove(orgIndex2);
                int i10 = this.mTargetIndex;
                if (orgIndex2 < i10) {
                    i8 = orgIndex;
                } else if (i10 != 0) {
                    i8 = orgIndex + 1;
                }
                arrayList.add(Math.min(i8, arrayList.size()), remove);
            }
            Log.d(this.TAG, "prevOrgIdx = " + orgIndex + " targetOrgIndex = " + orgIndex2);
        }
    }

    private void updateInBatch(ArrayList<ContentProviderOperation> arrayList) {
        Log.d(this.TAG, "UpdateOrderCmd DB started batch update");
        try {
            ContentProviderResult[] applyBatch = getContext().getContentResolver().applyBatch(new LocalAlbumsApi().getTableUri().getAuthority(), arrayList);
            String str = this.TAG;
            Log.d(str, "UpdateOrderCmd DB updated : " + applyBatch.length);
        } catch (OperationApplicationException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public int addCommonOp(ArrayList<ContentProviderOperation> arrayList, int i2, MediaItem mediaItem) {
        addOperation(createUpdateOp(mediaItem, ((long) i2) * 1000000000), arrayList);
        return i2 + 1;
    }

    public int addMergedAlbumOp(ArrayList<ContentProviderOperation> arrayList, int i2, MediaItem mediaItem) {
        for (MediaItem addCommonOp : mediaItem.getItemsInFolder()) {
            i2 = addCommonOp(arrayList, i2, addCommonOp);
        }
        return i2;
    }

    public void addRecentAndFavoriteOp(ArrayList<ContentProviderOperation> arrayList) {
        if (this.mIsMxLegacyView) {
            addOperation(createVirtualUpdateOp(BucketUtils.VirtualBucketHolder.favorite, 500000000), arrayList);
            addOperation(createVirtualUpdateOp(BucketUtils.VirtualBucketHolder.recent, 250000000), arrayList);
        }
    }

    public int addSubMergedAlbumOp(ArrayList<ContentProviderOperation> arrayList, int i2, MediaItem mediaItem) {
        for (MediaItem next : mediaItem.getChildList()) {
            if (next.isMergedAlbum()) {
                for (MediaItem mediaItem2 : next.getItemsInFolder()) {
                    if (mediaItem2.getAlbumOrder() == 0) {
                        i2 = addCommonOp(arrayList, i2, mediaItem2);
                    }
                }
            }
        }
        return i2;
    }

    public ContentProviderOperation createUpdateOp(MediaItem mediaItem, long j2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(mediaItem.getAlbumID()));
        ContentValues contentValues = new ContentValues();
        contentValues.put("album_order", Long.valueOf(j2));
        return new LocalAlbumsApi().getUpdateOperation(contentValues, arrayList);
    }

    public ContentProviderOperation createVirtualUpdateOp(int i2, long j2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i2));
        ContentValues contentValues = new ContentValues();
        contentValues.put("album_order", Long.valueOf(j2));
        return new LocalAlbumsApi().getUpdateOperation(contentValues, arrayList);
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        int i2;
        boolean z;
        boolean z3 = false;
        this.mCallback = objArr[0];
        int i7 = -1;
        if (objArr.length > 1) {
            i2 = objArr[1].intValue();
        } else {
            i2 = -1;
        }
        this.mTargetIndex = i2;
        if (objArr.length > 2) {
            i7 = objArr[2].intValue();
        }
        this.mHighlightIndex = i7;
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            PreferenceFeatures preferenceFeatures = PreferenceFeatures.EssentialAlbums;
            if (!preferenceFeatures.isEnabled() || !"location://albums".equals(eventContext.getLocationKey())) {
                z = false;
            } else {
                z = true;
            }
            this.mIsMxEssentialView = z;
            if (!preferenceFeatures.isEnabled() && "location://albums".equals(eventContext.getLocationKey())) {
                z3 = true;
            }
            this.mIsMxLegacyView = z3;
        }
        Log.d(this.TAG, "mIsMxEssentialView#" + this.mIsMxEssentialView + " mIsMxLegacyView#" + this.mIsMxLegacyView + " targetIndex#" + this.mTargetIndex);
        ThreadUtil.postOnBgThread(new H.a(4, this, eventContext));
    }

    /* renamed from: updateAllAlbumOrder */
    public void lambda$onExecute$0(EventContext eventContext) {
        List<MediaItem> allItems = getAllItems(eventContext);
        if (allItems == null || allItems.isEmpty()) {
            Log.e(this.TAG, "updateAllAlbumOrder: lists is null or zero");
            return;
        }
        Blackboard blackboard = getBlackboard();
        BlackboardUtils.collectExternalDataChangedEvent(blackboard, true);
        blackboard.publish("local_db_updating", Boolean.TRUE);
        ArrayList arrayList = new ArrayList();
        updateOp(allItems, arrayList);
        addRecentAndFavoriteOp(arrayList);
        if (!arrayList.isEmpty()) {
            updateInBatch(arrayList);
        }
        AlbumHelper.getInstance().clearAlbumOrderForHidden();
        OrderUpdateCallback orderUpdateCallback = this.mCallback;
        if (orderUpdateCallback != null) {
            orderUpdateCallback.onOrderUpdated();
        }
        blackboard.publish("local_db_updating", Boolean.FALSE);
        BlackboardUtils.collectExternalDataChangedEvent(blackboard, false);
    }

    public void updateOp(List<MediaItem> list, ArrayList<ContentProviderOperation> arrayList) {
        int i2 = 1;
        for (MediaItem next : list) {
            i2 = addCommonOp(arrayList, i2, next);
            if (next.isMergedAlbum()) {
                i2 = addMergedAlbumOp(arrayList, i2, next);
            } else if (next.isFolder() && next.getFolderID() == -1) {
                i2 = addSubMergedAlbumOp(arrayList, i2, next);
            }
        }
    }
}
