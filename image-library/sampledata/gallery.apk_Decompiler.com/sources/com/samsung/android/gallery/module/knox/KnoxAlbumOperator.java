package com.samsung.android.gallery.module.knox;

import A8.C0545b;
import C3.p;
import Ka.c;
import L5.b;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import h4.C0464a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import k7.l;
import n0.C0235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KnoxAlbumOperator extends AbsKnoxOperator {
    private int mAlbumCount;
    private SubscriberListener mDataListener;
    private final String mDataLocationKey = getDataLocationKey();
    private int mFolderCount;
    private final onCompleteListener mOnCompleteListener = new a(this);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface onCompleteListener {
    }

    public KnoxAlbumOperator(Blackboard blackboard, KnoxUtil.MoveType moveType, int i2, KnoxOperationCallback knoxOperationCallback) {
        super(blackboard, moveType, i2, knoxOperationCallback);
    }

    private HashMap<String, int[]> composeMxAlbumIdsMap(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2, ArrayList<MediaItem> arrayList3) {
        HashMap<String, int[]> hashMap = new HashMap<>();
        if (!arrayList.isEmpty()) {
            hashMap.put("location://albums/fileList", arrayList.stream().mapToInt(new C0545b(2)).toArray());
        }
        if (!arrayList2.isEmpty()) {
            hashMap.put("location://auto/album/fileList", arrayList2.stream().mapToInt(new C0545b(2)).toArray());
        }
        if (!arrayList3.isEmpty()) {
            hashMap.put("location://virtual/album/favorite/fileList", arrayList3.stream().mapToInt(new C0545b(2)).toArray());
        }
        return hashMap;
    }

    private void dataObtained(Context context) {
        this.mBlackboard.unsubscribe(DataKey.DATA_CURSOR(this.mDataLocationKey), this.mDataListener);
        this.mBlackboard.erase(DataKey.DATA_CURSOR(this.mDataLocationKey));
        SimpleThreadPool.getInstance().execute(new C0235b(this, (Cursor[]) this.mBlackboard.read(DataKey.DATA_CURSOR(this.mDataLocationKey)), context, 12));
    }

    private void fetchMediaItemsFromAlbums(Context context, MediaItem[] mediaItemArr) {
        if (mediaItemArr == null) {
            Log.e("KnoxAlbumOperator", "fetchMediaItemsFromAlbums failed. no items");
            return;
        }
        this.mAlbumCount = 0;
        this.mFolderCount = 0;
        HashMap<String, int[]> albumIdsMap = getAlbumIdsMap(mediaItemArr);
        if (albumIdsMap.isEmpty()) {
            Log.e("KnoxAlbumOperator", "fetchMediaItemsFromAlbums failed. no album");
            showNoItemToast();
            return;
        }
        int sum = albumIdsMap.values().stream().mapToInt(new C0545b(29)).sum();
        Log.d("KnoxAlbumOperator", "fetchMediaItemsFromAlbums size{" + sum + "}");
        if (sum > 500) {
            this.mCallback.showProgressDialog();
        }
        this.mDataListener = new l(1, this, context);
        this.mBlackboard.subscribe(DataKey.DATA_CURSOR(this.mDataLocationKey), this.mDataListener);
        this.mBlackboard.publish(CommandKey.DATA_REQUEST(this.mDataLocationKey), albumIdsMap);
    }

    private HashMap<String, int[]> getAlbumIdsMap(MediaItem[] mediaItemArr) {
        HashMap<String, int[]> recentlyAlbumIdIfExist = getRecentlyAlbumIdIfExist(mediaItemArr);
        if (recentlyAlbumIdIfExist != null) {
            return recentlyAlbumIdIfExist;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            HashMap<AlbumType, ArrayList<MediaItem>> allChildAlbums = MediaItemUtil.getAllChildAlbums(List.of(mediaItemArr));
            arrayList2 = allChildAlbums.getOrDefault(AlbumType.AutoUpdated, new ArrayList());
            arrayList3 = allChildAlbums.getOrDefault(AlbumType.Virtual, new ArrayList());
            allChildAlbums.forEach(new c(arrayList, 2));
            this.mFolderCount = ((Integer) Optional.ofNullable(allChildAlbums.get(AlbumType.Folder)).map(new b(16)).orElse(0)).intValue();
            this.mAlbumCount = arrayList3.size() + arrayList2.size() + arrayList.size();
        } else {
            for (MediaItem mediaItem : mediaItemArr) {
                if (mediaItem.isFolder()) {
                    this.mFolderCount++;
                    arrayList.addAll(Arrays.asList(mediaItem.getAlbumsInFolder()));
                } else {
                    this.mAlbumCount++;
                    arrayList.add(mediaItem);
                }
            }
        }
        return composeMxAlbumIdsMap(arrayList, arrayList2, arrayList3);
    }

    private String getDataLocationKey() {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return "location://albums/fileList/mx/multiple";
        }
        return "location://albums/fileList/multiple";
    }

    private List<MediaItem> getItemsFromAlbums(Cursor[] cursorArr) {
        if (cursorArr.length == 0) {
            return Collections.EMPTY_LIST;
        }
        HashMap hashMap = new HashMap();
        Arrays.stream(cursorArr).forEach(new p(10, hashMap));
        return new ArrayList(hashMap.values());
    }

    private HashMap<String, int[]> getRecentlyAlbumIdIfExist(MediaItem[] mediaItemArr) {
        HashMap<String, int[]> hashMap = new HashMap<>();
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return null;
        }
        Arrays.stream(mediaItemArr).filter(new C0464a(28)).findFirst().ifPresent(new p(9, hashMap));
        if (!hashMap.containsKey("location://virtual/album/recently/fileList")) {
            return null;
        }
        for (MediaItem isGroupedAlbum : mediaItemArr) {
            if (MediaItemUtil.isGroupedAlbum(isGroupedAlbum)) {
                this.mFolderCount++;
            } else {
                this.mAlbumCount++;
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public void lambda$dataObtained$5(Context context, List list) {
        ((a) this.mOnCompleteListener).f3028a.onComplete(context, list);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$dataObtained$6(Cursor[] cursorArr, Context context) {
        ThreadUtil.postOnUiThread(new C0235b(this, context, getItemsFromAlbums(cursorArr), 13));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$fetchMediaItemsFromAlbums$0(int[] iArr) {
        return iArr.length;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$fetchMediaItemsFromAlbums$1(Context context, Object obj, Bundle bundle) {
        dataObtained(context);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getAlbumIdsMap$2(ArrayList arrayList, AlbumType albumType, ArrayList arrayList2) {
        if (albumType == AlbumType.None || albumType == AlbumType.MyAlbum) {
            arrayList.addAll(arrayList2);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getItemsFromAlbums$7(HashMap hashMap, Cursor cursor) {
        if (cursor != null && cursor.getCount() != 0 && cursor.moveToFirst()) {
            do {
                MediaItem createSimple = MediaItemBuilder.createSimple(cursor);
                hashMap.putIfAbsent(Long.valueOf(createSimple.getMediaId()), createSimple);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

    /* access modifiers changed from: private */
    public void onComplete(Context context, List<MediaItem> list) {
        if (list == null || list.isEmpty()) {
            showNoItemToast();
            this.mCallback.dismissProgressDialog();
            Log.d("KnoxAlbumOperator", "onComplete: media items are null");
            return;
        }
        operateInternal(context, list);
        Log.d("KnoxAlbumOperator", "onComplete: delivered count[" + list.size() + "]");
    }

    private void showNoItemToast() {
        Utils.showToast(AppResources.getAppContext(), KnoxOperatorString.getNoItemMessage(this.mMoveType, this.mAlbumCount, this.mFolderCount));
    }

    public void operate(Context context, MediaItem[] mediaItemArr) {
        fetchMediaItemsFromAlbums(context, mediaItemArr);
    }
}
