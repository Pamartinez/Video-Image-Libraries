package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MdeData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.SharingItemLoader;
import com.samsung.android.gallery.module.dataset.tables.CursorReader;
import com.samsung.android.gallery.module.dataset.tables.DataTable;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.provider.SharedItemContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataMdeTimeline extends MediaDataTimeline {
    final Consumer<MediaItem> mDataUpdater = new O(0, this);
    private String mGroupId;
    protected String mGroupMemberLocationKey;
    protected final HashMap<String, String> mGroupMemberMap = new HashMap<>();
    private Integer mOwnerIdColumnIndex;
    private Integer mOwnerNameColumnIndex;
    private String mSpaceId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MdeDataTable extends DataTable {
        public MdeDataTable(Cursor cursor) {
            super(cursor);
        }

        public CursorReader createCursorReader(Cursor cursor) {
            return new MdeCursorReader(cursor);
        }

        public MdeDataTable setDataUpdater(Consumer<MediaItem> consumer) {
            Optional.ofNullable((MdeCursorReader) getCursorReader()).ifPresent(new O(1, consumer));
            return this;
        }

        public String tag() {
            return "MdeDataTable";
        }

        public MdeDataTable(Cursor cursor, DefaultTable.OnLoadDoneListener onLoadDoneListener, int i2) {
            super(cursor, onLoadDoneListener, i2);
        }
    }

    public MediaDataMdeTimeline(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private String getSpaceIdFromUri(Uri uri) {
        if (uri != null) {
            return uri.getLastPathSegment();
        }
        return "invalid";
    }

    private boolean isEqual(HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        if (hashMap == null) {
            return false;
        }
        if (hashMap.size() != hashMap2.size()) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "newGroupMemberMapSize " + hashMap.size() + " oldGroupMemberMapSize " + hashMap2.size());
            return false;
        }
        for (Map.Entry next : hashMap.entrySet()) {
            String str = (String) next.getValue();
            String str2 = hashMap2.get(next.getKey());
            if (str2 == null) {
                Log.d(this.TAG, "there is no item");
                return false;
            } else if (!TextUtils.equals(str2, str)) {
                Log.d(this.TAG, "name is changed");
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(MediaItem mediaItem) {
        if (mediaItem != null) {
            MdeData.of(mediaItem).groupId = this.mGroupId;
        }
    }

    /* access modifiers changed from: private */
    public void onGroupMemberDataCursorChanged(Object obj, Bundle bundle) {
        Cursor[] cursorArr = (Cursor[]) obj;
        this.mGroupMemberMap.clear();
        Cursor cursor = cursorArr[0];
        if (cursor != null && !cursor.isClosed()) {
            Cursor cursor2 = cursorArr[0];
            StringCompat stringCompat = this.TAG;
            Log.i(stringCompat, "onDataCursorChanged {" + this.mGroupMemberLocationKey + GlobalPostProcInternalPPInterface.SPLIT_REGEX + cursorArr.length + ',' + getCursorCount(cursorArr[0]) + '}');
            if (cursor2.moveToFirst()) {
                if (this.mOwnerIdColumnIndex == null) {
                    this.mOwnerIdColumnIndex = Integer.valueOf(cursor2.getColumnIndex("id"));
                }
                if (this.mOwnerNameColumnIndex == null) {
                    this.mOwnerNameColumnIndex = Integer.valueOf(cursor2.getColumnIndex("name"));
                }
                do {
                    this.mGroupMemberMap.put(cursor2.getString(this.mOwnerIdColumnIndex.intValue()), cursor2.getString(this.mOwnerNameColumnIndex.intValue()));
                } while (cursor2.moveToNext());
                if (!cursor2.isClosed()) {
                    cursor2.close();
                }
            }
        }
        releaseReadLock("getGroupMemberData");
    }

    /* access modifiers changed from: private */
    public void onGroupMemberDataSynced(Object obj, Bundle bundle) {
        if (!isEqual((HashMap) obj, this.mGroupMemberMap)) {
            Log.d(this.TAG, "not equal");
            this.mBlackboard.post("command://SharingPicturesGroupMemberChanged", (Object) null);
        }
    }

    private void updateName(MediaItem mediaItem) {
        if (mediaItem != null) {
            MediaItemMde.setCreatorName(mediaItem, this.mGroupMemberMap.get(MediaItemMde.getCreatorId(mediaItem)));
            if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ITEM_STATUS)) {
                MediaItemMde.setLastModifierName(mediaItem, this.mGroupMemberMap.get(MediaItemMde.getLastModifier(mediaItem)));
            }
        }
    }

    public DataTable createDataTable(Cursor cursor) {
        return new MdeDataTable(cursor).setDataUpdater(this.mDataUpdater);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo(DataKey.DATA_CURSOR(this.mGroupMemberLocationKey), new N(this, 0)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo("command://SharingPicturesGroupMemberSynced", new N(this, 1)).setWorkingCurrent());
    }

    public void getGroupMemberData() {
        try {
            acquireReadLock("getGroupMemberData");
            BlackboardUtils.publishDataRequestForce(this.mBlackboard, this.mGroupMemberLocationKey);
        } finally {
            releaseReadLock("getGroupMemberData");
        }
    }

    public boolean isFilteredEvent(EventMessage eventMessage) {
        String spaceIdFromUri = getSpaceIdFromUri((Uri) eventMessage.obj);
        String str = this.mSpaceId;
        if (str == null || str.equals(spaceIdFromUri)) {
            return false;
        }
        StringCompat stringCompat = this.TAG;
        Log.e(stringCompat, "  >> isFilteredEvent(filtered), l=" + this.mSpaceId + ", u=" + spaceIdFromUri);
        return true;
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 106 || i2 == 109) {
            return true;
        }
        return false;
    }

    public void onCreate() {
        super.onCreate();
        getGroupMemberData();
    }

    public MediaItem read(int i2) {
        MediaItem read = super.read(i2);
        updateName(read);
        return read;
    }

    public MediaItem readCache(int i2) {
        MediaItem readCache = super.readCache(i2);
        updateName(readCache);
        return readCache;
    }

    public void reopen(String str) {
        getGroupMemberData();
        super.reopen(str);
    }

    public void setLocationKey(String str) {
        super.setLocationKey(str);
        this.mSpaceId = ArgumentsUtil.getArgValue(this.mLocationReference, "id");
        this.mGroupId = ArgumentsUtil.getArgValue(this.mLocationReference, "groupId");
        this.mGroupMemberLocationKey = new UriBuilder("location://sharing/groupMembers").appendArg("id", this.mGroupId).build();
    }

    public boolean supportDayCluster() {
        return false;
    }

    public boolean supportMonthCluster() {
        return false;
    }

    public DataTable createDataTable(Cursor cursor, DefaultTable.OnLoadDoneListener onLoadDoneListener, int i2) {
        return new MdeDataTable(cursor, onLoadDoneListener, i2).setDataUpdater(this.mDataUpdater);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MdeCursorReader extends CursorReader {
        Consumer<MediaItem> mDataUpdater = new C0596d(1);

        public MdeCursorReader(Cursor cursor) {
            super(cursor);
            boolean z;
            String str = this.TAG;
            if (cursor.getColumnIndex(SharedItemContract.Item.ITEM_ID) != -1) {
                z = true;
            } else {
                z = false;
            }
            Log.d(str, "construct", Boolean.valueOf(z));
        }

        public MediaItem getMediaItem(Cursor cursor, int i2) {
            MediaItem loadItem = SharingItemLoader.loadItem(cursor, i2);
            this.mDataUpdater.accept(loadItem);
            return loadItem;
        }

        public MediaItem getPrimitiveMediaItem(Cursor cursor) {
            MediaItem loadPrimitiveItem = SharingItemLoader.loadPrimitiveItem(cursor);
            this.mDataUpdater.accept(loadPrimitiveItem);
            return loadPrimitiveItem;
        }

        public void setDataUpdater(Consumer<MediaItem> consumer) {
            this.mDataUpdater = consumer;
        }

        public String tag() {
            return "MdeCursorReader";
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$new$0(MediaItem mediaItem) {
        }
    }
}
