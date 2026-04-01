package com.samsung.android.gallery.module.dataset;

import A.a;
import Ad.C0720a;
import Fa.C0571z;
import N2.j;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.local.CacheProviderHelper;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.SharingItemLoader;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.comparator.Comparators;
import com.samsung.android.gallery.module.dataset.comparator.IAlbumComparator;
import com.samsung.android.gallery.module.mde.MdeCacheHelper;
import com.samsung.android.gallery.module.mde.MdeMediaItemHelper;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.mobileservice.social.group.Group;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import r6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataMdeSpace extends MediaDataCursor {
    boolean SHARING_INVITATION_ON_ALBUMS = PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS;
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<String, ArrayList<MediaItem>> mChildDataMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, MediaData> mChildMediaData = new ConcurrentHashMap<>();
    private ArrayList<MediaItem> mData = new ArrayList<>();
    private MediaItem mFamilyItem;
    private HashMap<String, MediaItem> mGroupMap = new HashMap<>();
    private Integer mItemIdColumnIndex;
    private final MdeDatabase mMdeDatabase = new MdeDatabase();
    private Integer mMetaDataColumnIndex;
    private Integer mMimeTypeColumnIndex;
    private Integer mPathColumnIndex;
    private int mRequestPendingCount;
    private HashMap<String, MediaItem> mSpaceMap = new HashMap<>();

    public MediaDataMdeSpace(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private boolean checkEqualsGroupItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!PreferenceFeatures.OneUi41.SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE) {
            return equalsGroupItem(mediaItem, mediaItem2);
        }
        if (!equalsGroupItem(mediaItem, mediaItem2) || MediaItemMde.getAlbumExpiry(mediaItem) != MediaItemMde.getAlbumExpiry(mediaItem2)) {
            return false;
        }
        return true;
    }

    private boolean checkEqualsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE || !getLocationKey().startsWith("location://sharing/albums/storageUse")) {
            return equalsItem(mediaItem, mediaItem2);
        }
        if (!checkSpaceItem(mediaItem, mediaItem2) || MediaItemMde.getMyUsage(mediaItem) != MediaItemMde.getMyUsage(mediaItem2)) {
            return false;
        }
        return true;
    }

    private boolean checkInvitationsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || MediaItemMde.getInvitationExpiredTime(mediaItem) != MediaItemMde.getInvitationExpiredTime(mediaItem2) || MediaItemMde.getInvitationRequestedTime(mediaItem) != MediaItemMde.getInvitationRequestedTime(mediaItem2) || !TextUtils.equals(MediaItemMde.getInvitationSpaceName(mediaItem), MediaItemMde.getInvitationSpaceName(mediaItem2)) || !TextUtils.equals(MediaItemMde.getInvitationGroupId(mediaItem), MediaItemMde.getInvitationGroupId(mediaItem2)) || !TextUtils.equals(MediaItemMde.getInvitationRequesterName(mediaItem), MediaItemMde.getInvitationRequesterName(mediaItem2))) {
            return false;
        }
        return true;
    }

    private boolean checkSpaceItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || !TextUtils.equals(MediaItemMde.getSpaceId(mediaItem), MediaItemMde.getSpaceId(mediaItem2)) || !TextUtils.equals(mediaItem.getTitle(), mediaItem2.getTitle()) || !TextUtils.equals(mediaItem.getPath(), mediaItem2.getPath()) || !TextUtils.equals(MediaItemMde.getSpaceCoverRectRatio(mediaItem), MediaItemMde.getSpaceCoverRectRatio(mediaItem2)) || mediaItem.getOrientation() != mediaItem2.getOrientation() || !TextUtils.equals(MediaItemMde.getSpaceCoverId(mediaItem), MediaItemMde.getSpaceCoverId(mediaItem2)) || MediaItemMde.getUnreadCount(mediaItem) != MediaItemMde.getUnreadCount(mediaItem2) || !TextUtils.equals(MediaItemMde.getCreatorId(mediaItem), MediaItemMde.getCreatorId(mediaItem2)) || !TextUtils.equals(MediaItemMde.getGroupId(mediaItem), MediaItemMde.getGroupId(mediaItem2)) || !checkSpaceWebLink(mediaItem, mediaItem2)) {
            return false;
        }
        return true;
    }

    private boolean checkSpaceWebLink(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK) {
            return true;
        }
        if (mediaItem == null || mediaItem2 == null || !TextUtils.equals(MediaItemMde.getWebLinkUrl(mediaItem), MediaItemMde.getWebLinkUrl(mediaItem2)) || MediaItemMde.getWebLinkExpiry(mediaItem) != MediaItemMde.getWebLinkExpiry(mediaItem2)) {
            return false;
        }
        return true;
    }

    private boolean equalsData(ArrayList<MediaItem> arrayList) {
        int size = arrayList.size();
        if (this.mData.size() != size) {
            return false;
        }
        for (int i2 = 0; i2 < size; i2++) {
            if (!checkEqualsItem(this.mData.get(i2), arrayList.get(i2))) {
                return false;
            }
        }
        return true;
    }

    private boolean equalsGroupData(HashMap<String, MediaItem> hashMap) {
        int size = hashMap.size();
        HashMap<String, MediaItem> hashMap2 = this.mGroupMap;
        if (hashMap2 == null || hashMap2.size() != size) {
            return false;
        }
        for (Map.Entry next : this.mGroupMap.entrySet()) {
            if (!checkEqualsGroupItem((MediaItem) next.getValue(), hashMap.get(next.getKey()))) {
                return false;
            }
        }
        return true;
    }

    private boolean equalsGroupItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || mediaItem.getCount() != mediaItem2.getCount() || !TextUtils.equals(mediaItem.getTitle(), mediaItem2.getTitle()) || !TextUtils.equals(MediaItemMde.getGroupId(mediaItem), MediaItemMde.getGroupId(mediaItem2)) || !TextUtils.equals(mediaItem.getPath(), mediaItem2.getPath())) {
            return false;
        }
        return true;
    }

    private boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!this.SHARING_INVITATION_ON_ALBUMS) {
            return checkSpaceItem(mediaItem, mediaItem2);
        }
        if (!checkSpaceItem(mediaItem, mediaItem2) || !checkInvitationsItem(mediaItem, mediaItem2)) {
            return false;
        }
        return true;
    }

    private int getCursorCount(Cursor[] cursorArr) {
        Cursor cursor;
        int i2 = 0;
        if (cursorArr.length > 0) {
            i2 = getCursorCount(cursorArr[0]);
        }
        if (!this.SHARING_INVITATION_ON_ALBUMS || cursorArr.length <= 1 || (cursor = cursorArr[1]) == null) {
            return i2;
        }
        return i2 + getCursorCount(cursor);
    }

    private int getFirstLoadingCount() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbums)) {
            return 8;
        }
        if (!LocationKey.isAlbumsMatch((String) this.mBlackboard.read("location://variable/currentv1", null)) || !PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums)) {
            return 12;
        }
        return 3;
    }

    private int getInvitationCount(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2, Cursor[] cursorArr, TimeTickLog timeTickLog) {
        Cursor cursor;
        if (this.SHARING_INVITATION_ON_ALBUMS) {
            try {
                if (cursorArr.length > 1) {
                    cursor = cursorArr[1];
                } else {
                    cursor = null;
                }
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        MediaItem loadInvitationListItem = SharingItemLoader.loadInvitationListItem(cursor);
                        arrayList.add(loadInvitationListItem);
                        arrayList2.add(loadInvitationListItem);
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                a.r(e, new StringBuilder("loadData#invitation failed. e="), this.TAG);
            }
            timeTickLog.tick();
        }
        return arrayList2.size();
    }

    private int getPreloadThumbCount() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbums)) {
            return 6;
        }
        if (!LocationKey.isAlbumsMatch((String) this.mBlackboard.read("location://variable/currentv1", null))) {
            return 12;
        }
        int intValue = ((Integer) this.mBlackboard.pop("data://top/albums/count", 0)).intValue();
        if (intValue > 9) {
            return 0;
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums)) {
            return 3;
        }
        return Math.max(12 - intValue, 0);
    }

    private boolean isFirstLoading() {
        if (this.mCursors == null) {
            return true;
        }
        return false;
    }

    private boolean isStorageUseWithFamily() {
        if (!getLocationKey().startsWith("location://sharing/albums/storageUse") || !Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaData lambda$getChildMediaData$0(String str) {
        return new MediaDataChild(this.mBlackboard, str, this);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$loadData$7(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$loadData$8(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$readAsync$1(MediaData.OnDataReadListener onDataReadListener, int i2, ThreadPool.JobContext jobContext) {
        onDataReadListener.onDataReadCompleted(loadInternal(i2));
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$3(Cursor[] cursorArr, Cursor[] cursorArr2, ArrayList arrayList, HashMap hashMap, HashMap hashMap2, HashMap hashMap3, int i2, boolean z, long j2) {
        if (isDataCursorAvailable(cursorArr)) {
            synchronized (cursorArr[0]) {
                try {
                    swapInternal(cursorArr2, arrayList, hashMap, hashMap2, hashMap3, i2);
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else {
            swapInternal(cursorArr2, arrayList, hashMap, hashMap2, hashMap3, i2);
        }
        this.mLock.releaseWriteLock();
        StringCompat stringCompat = this.TAG;
        C0212a.z(new Object[]{Boolean.valueOf(z), Integer.valueOf(i2), Long.valueOf(j2)}, new StringBuilder("swap"), stringCompat);
        if (z) {
            notifyChanged();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
        if (equalsGroupData(r14) != false) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001b, code lost:
        if (r13.size() == r12.mData.size()) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$swap$4(java.util.ArrayList r13, java.util.HashMap r14, android.database.Cursor[] r15, android.database.Cursor[] r16, java.util.HashMap r17, java.util.HashMap r18, int r19, long r20, java.lang.Boolean r22) {
        /*
            r12 = this;
            boolean r0 = r22.booleanValue()
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0021
            r12.preloadThumbnail(r13)
            int r0 = r13.size()
            if (r0 == 0) goto L_0x001f
            int r0 = r13.size()
            java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem> r4 = r12.mData
            int r4 = r4.size()
            if (r0 == r4) goto L_0x001e
            goto L_0x001f
        L_0x001e:
            r2 = r3
        L_0x001f:
            r9 = r2
            goto L_0x002e
        L_0x0021:
            boolean r0 = r12.equalsData(r13)
            if (r0 == 0) goto L_0x001f
            boolean r0 = r12.equalsGroupData(r14)
            if (r0 != 0) goto L_0x001e
            goto L_0x001f
        L_0x002e:
            com.samsung.android.gallery.module.dataset.M r0 = new com.samsung.android.gallery.module.dataset.M
            r1 = r12
            r4 = r13
            r6 = r14
            r2 = r15
            r3 = r16
            r5 = r17
            r7 = r18
            r8 = r19
            r10 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            r12.runOnUiThread(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataMdeSpace.lambda$swap$4(java.util.ArrayList, java.util.HashMap, android.database.Cursor[], android.database.Cursor[], java.util.HashMap, java.util.HashMap, int, long, java.lang.Boolean):void");
    }

    private void loadData(ArrayList<MediaItem> arrayList, HashMap<String, MediaItem> hashMap, HashMap<String, MediaItem> hashMap2, HashMap<String, ArrayList<MediaItem>> hashMap3, Cursor[] cursorArr, Consumer<Boolean> consumer) {
        int i2;
        MediaItem mediaItem;
        String str;
        String str2;
        MediaItem mediaItem2;
        Cursor cursor;
        Cursor cursor2;
        ArrayList<MediaItem> arrayList2 = arrayList;
        HashMap<String, MediaItem> hashMap4 = hashMap2;
        HashMap<String, ArrayList<MediaItem>> hashMap5 = hashMap3;
        Cursor[] cursorArr2 = cursorArr;
        boolean isFirstLoading = isFirstLoading();
        if (cursorArr2 == null) {
            Log.e(this.TAG, "loadData failed. null cursor");
            consumer.accept(Boolean.valueOf(isFirstLoading));
            return;
        }
        Consumer<Boolean> consumer2 = consumer;
        ArrayList computeIfAbsent = hashMap5.computeIfAbsent("location://sharing/albums/spaces", new K(2));
        TimeTickLog timeTickLog = new TimeTickLog((String) null);
        int i7 = 0;
        int cursorCount = getCursorCount(cursorArr2[0]);
        int invitationCount = getInvitationCount(arrayList2, hashMap5.computeIfAbsent("location://sharing/albums/invitations", new K(1)), cursorArr2, timeTickLog);
        if (cursorArr2.length > 2 && (cursor2 = cursorArr2[2]) != null) {
            loadGroupInfo(cursor2, hashMap4);
        }
        int i8 = 0;
        while (i8 < cursorCount) {
            int i10 = i7;
            MediaItem loadMediaItem = loadMediaItem(cursorArr2[i10], i8);
            if (loadMediaItem != null) {
                arrayList2.add(loadMediaItem);
                computeIfAbsent.add(loadMediaItem);
                hashMap.put(MediaItemMde.getSpaceId(loadMediaItem), loadMediaItem);
                MdeMediaItemHelper.applyGroupDataToSpace(loadMediaItem, hashMap4.get(MediaItemMde.getGroupId(loadMediaItem)));
            } else {
                HashMap<String, MediaItem> hashMap6 = hashMap;
            }
            if (isFirstLoading && reachPreloadDataCount(i8)) {
                timeTickLog.tick();
                if (consumer2 != null) {
                    consumer2.accept(Boolean.TRUE);
                    consumer2 = null;
                }
                Log.d(this.TAG, "loadData {" + invitationCount + ',' + i8 + ",init} +" + timeTickLog.summary());
                isFirstLoading = i10;
            }
            i8++;
            i7 = i10;
        }
        int i11 = i7;
        if (PreferenceFeatures.OneUi6x.SUPPORT_SHARING_SORT_BY && ((LocationKey.isSharings(this.mLocationKey) || LocationKey.isSharingChoice(this.mLocationKey)) && SortByType.getSortBy(SortByType.getSharingOrder()) == 30)) {
            sortByName(arrayList2, computeIfAbsent, invitationCount);
        }
        timeTickLog.tick();
        if (consumer2 != null) {
            consumer2.accept(Boolean.valueOf(isFirstLoading));
        }
        if (cursorArr2.length <= 2 || cursorArr2[i11] == null || cursorArr2[2] == null) {
            i2 = i11;
        } else {
            i2 = 1;
        }
        if (i2 != 0) {
            mediaItem = null;
            CacheProviderHelper.update("location://sharing/albums", MdeCacheHelper.makeSpaceGroupString(cursorArr2[i11], cursorArr2[2]), (byte[]) null);
            timeTickLog.tick();
        } else {
            mediaItem = null;
        }
        if (isStorageUseWithFamily()) {
            if (cursorArr2.length <= 3 || (cursor = cursorArr2[3]) == null || !cursor.moveToFirst()) {
                mediaItem2 = mediaItem;
            } else {
                mediaItem2 = SharingItemLoader.loadSpace(cursorArr2[3]);
            }
            this.mFamilyItem = mediaItem2;
        }
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("loadData {");
        sb2.append(invitationCount);
        sb2.append(',');
        sb2.append(cursorCount);
        sb2.append(',');
        if (isFirstLoading) {
            str = "init";
        } else {
            str = "update";
        }
        sb2.append(str);
        if (i2 != 0) {
            str2 = ",cached";
        } else {
            str2 = "";
        }
        sb2.append(str2);
        sb2.append("} +");
        sb2.append(timeTickLog.summary());
        Log.d(stringCompat, sb2.toString());
        int length = cursorArr2.length;
        for (int i12 = i11; i12 < length; i12++) {
            Utils.closeSilently(cursorArr2[i12]);
        }
    }

    private void loadGroupInfo(Cursor cursor, HashMap<String, MediaItem> hashMap) {
        if (cursor != null && cursor.moveToFirst()) {
            do {
                MediaItem loadGroup = SharingItemLoader.loadGroup(cursor);
                hashMap.put(MediaItemMde.getGroupId(loadGroup), loadGroup);
            } while (cursor.moveToNext());
        }
    }

    private void preloadThumbnail(ArrayList<MediaItem> arrayList) {
        if (!arrayList.isEmpty()) {
            this.mBlackboard.publishIfEmpty("lifecycle://on_thumbnail_load_start", Long.valueOf(System.currentTimeMillis()));
            C0612q qVar = new C0612q(3);
            arrayList.stream().limit((long) getPreloadThumbCount()).filter(new C0571z(4)).forEach(new I(1, ThumbnailLoader.getInstance(), qVar));
        }
    }

    private boolean reachPreloadDataCount(int i2) {
        if (i2 >= getFirstLoadingCount()) {
            return true;
        }
        return false;
    }

    private void sortByName(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2, int i2) {
        boolean anyMatch = arrayList.stream().map(new K(8)).anyMatch(new C0598e(4));
        IAlbumComparator comparatorForSharings = Comparators.getComparatorForSharings("location://sharing/albums");
        arrayList.subList(i2 + (anyMatch ? 1 : 0), arrayList.size()).sort(comparatorForSharings);
        arrayList2.subList(anyMatch, arrayList2.size()).sort(comparatorForSharings);
    }

    private void swapInternal(Cursor[] cursorArr, ArrayList<MediaItem> arrayList, HashMap<String, MediaItem> hashMap, HashMap<String, MediaItem> hashMap2, HashMap<String, ArrayList<MediaItem>> hashMap3, int i2) {
        this.mCursors = cursorArr;
        this.mData = arrayList;
        this.mSpaceMap = hashMap;
        this.mGroupMap = hashMap2;
        this.mChildDataMap.clear();
        this.mChildDataMap.putAll(hashMap3);
        this.mDataCount = i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ce  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.samsung.android.gallery.module.data.MediaItem updateCoverPath(com.samsung.android.gallery.module.data.MediaItem r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x00dd
            java.lang.String r0 = r4.getPath()
            if (r0 != 0) goto L_0x00dd
            com.samsung.android.gallery.module.mdebase.db.MdeDatabase r0 = r3.mMdeDatabase
            java.lang.String r1 = com.samsung.android.gallery.module.data.MediaItemMde.getSpaceId(r4)
            java.lang.String r2 = com.samsung.android.gallery.module.data.MediaItemMde.getSpaceCoverId(r4)
            android.database.Cursor r0 = r0.getSpaceCoverItemCursor(r1, r2)
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x00c9
            boolean r2 = r0.moveToFirst()     // Catch:{ all -> 0x0032 }
            if (r2 == 0) goto L_0x00c9
            java.lang.Integer r2 = r3.mPathColumnIndex     // Catch:{ all -> 0x0032 }
            if (r2 != 0) goto L_0x0035
            java.lang.String r2 = "thumbnail_local_path"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ all -> 0x0032 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0032 }
            r3.mPathColumnIndex = r2     // Catch:{ all -> 0x0032 }
            goto L_0x0035
        L_0x0032:
            r3 = move-exception
            goto L_0x00d2
        L_0x0035:
            java.lang.Integer r2 = r3.mMetaDataColumnIndex     // Catch:{ all -> 0x0032 }
            if (r2 != 0) goto L_0x0045
            java.lang.String r2 = "meta_data"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ all -> 0x0032 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0032 }
            r3.mMetaDataColumnIndex = r2     // Catch:{ all -> 0x0032 }
        L_0x0045:
            java.lang.Integer r2 = r3.mMimeTypeColumnIndex     // Catch:{ all -> 0x0032 }
            if (r2 != 0) goto L_0x0055
            java.lang.String r2 = "mime_type"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ all -> 0x0032 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0032 }
            r3.mMimeTypeColumnIndex = r2     // Catch:{ all -> 0x0032 }
        L_0x0055:
            java.lang.Integer r2 = r3.mItemIdColumnIndex     // Catch:{ all -> 0x0032 }
            if (r2 != 0) goto L_0x0065
            java.lang.String r2 = "itemId"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ all -> 0x0032 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0032 }
            r3.mItemIdColumnIndex = r2     // Catch:{ all -> 0x0032 }
        L_0x0065:
            java.lang.Integer r2 = r3.mPathColumnIndex     // Catch:{ all -> 0x0032 }
            int r2 = r2.intValue()     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = r0.getString(r2)     // Catch:{ all -> 0x0032 }
            if (r2 == 0) goto L_0x0072
            r1 = r2
        L_0x0072:
            r4.setPath(r1)     // Catch:{ all -> 0x0032 }
            java.lang.Integer r1 = r3.mMetaDataColumnIndex     // Catch:{ all -> 0x0032 }
            int r1 = r1.intValue()     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = r0.getString(r1)     // Catch:{ all -> 0x0032 }
            int r1 = com.samsung.android.gallery.module.mdebase.utils.MdeMetadataParser.getOrientationFromMetadata(r1)     // Catch:{ all -> 0x0032 }
            r4.setOrientation(r1)     // Catch:{ all -> 0x0032 }
            java.lang.Integer r1 = r3.mMimeTypeColumnIndex     // Catch:{ all -> 0x0032 }
            int r1 = r1.intValue()     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = r0.getString(r1)     // Catch:{ all -> 0x0032 }
            r4.setMimeType(r1)     // Catch:{ all -> 0x0032 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0032 }
            if (r2 != 0) goto L_0x00a5
            java.lang.String r2 = "image"
            boolean r1 = r1.startsWith(r2)     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x00a2
            goto L_0x00a5
        L_0x00a2:
            com.samsung.android.gallery.database.dbtype.MediaType r1 = com.samsung.android.gallery.database.dbtype.MediaType.Video     // Catch:{ all -> 0x0032 }
            goto L_0x00a7
        L_0x00a5:
            com.samsung.android.gallery.database.dbtype.MediaType r1 = com.samsung.android.gallery.database.dbtype.MediaType.Image     // Catch:{ all -> 0x0032 }
        L_0x00a7:
            r4.setMediaType(r1)     // Catch:{ all -> 0x0032 }
            java.lang.Integer r3 = r3.mItemIdColumnIndex     // Catch:{ all -> 0x0032 }
            int r3 = r3.intValue()     // Catch:{ all -> 0x0032 }
            java.lang.String r3 = r0.getString(r3)     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = com.samsung.android.gallery.module.data.MediaItemMde.getSpaceCoverId(r4)     // Catch:{ all -> 0x0032 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0032 }
            if (r2 != 0) goto L_0x00cc
            boolean r3 = r1.equals(r3)     // Catch:{ all -> 0x0032 }
            if (r3 != 0) goto L_0x00cc
            r3 = 0
            com.samsung.android.gallery.module.data.MediaItemMde.setUserCoverItem(r4, r3)     // Catch:{ all -> 0x0032 }
            goto L_0x00cc
        L_0x00c9:
            r4.setPath(r1)     // Catch:{ all -> 0x0032 }
        L_0x00cc:
            if (r0 == 0) goto L_0x00dd
            r0.close()
            return r4
        L_0x00d2:
            if (r0 == 0) goto L_0x00dc
            r0.close()     // Catch:{ all -> 0x00d8 }
            goto L_0x00dc
        L_0x00d8:
            r4 = move-exception
            r3.addSuppressed(r4)
        L_0x00dc:
            throw r3
        L_0x00dd:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataMdeSpace.updateCoverPath(com.samsung.android.gallery.module.data.MediaItem):com.samsung.android.gallery.module.data.MediaItem");
    }

    public int findPositionBy(Object obj) {
        if (!(obj instanceof String)) {
            return -1;
        }
        String str = (String) obj;
        for (int i2 = 0; i2 < this.mData.size(); i2++) {
            if (str.equals(MediaItemMde.getSpaceId(this.mData.get(i2)))) {
                return i2;
            }
        }
        return -1;
    }

    public ArrayList<MediaItem> getAllData() {
        return this.mData;
    }

    public MediaData getChildMediaData(String str) {
        return this.mChildMediaData.computeIfAbsent(str, new C0608m(1, this));
    }

    public boolean isFilteredEvent(EventMessage eventMessage) {
        return false;
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        if (this.SHARING_INVITATION_ON_ALBUMS && eventMessage.what == 108) {
            return "location://sharing/albums".equals(this.mLocationKey);
        }
        if (Features.isEnabled(Features.SUPPORT_SHARED_GROUP_RAW_QUERY) && eventMessage.what == 107) {
            return true;
        }
        if ((!Features.isEnabled(Features.SUPPORT_SORT_SHARINGS) || eventMessage.what != 112) && eventMessage.what != 106) {
            return false;
        }
        return true;
    }

    public MediaItem loadInternal(int i2) {
        Cursor[] cursorArr = this.mCursors;
        if (cursorArr == null) {
            return MediaItemBuilder.createEmpty();
        }
        MediaItem loadMediaItem = loadMediaItem(cursorArr[0], i2);
        if (loadMediaItem != null) {
            this.mData.add(i2, loadMediaItem);
            this.mSpaceMap.put(MediaItemMde.getSpaceId(loadMediaItem), loadMediaItem);
        }
        return loadMediaItem;
    }

    public MediaItem loadMediaItem(Cursor cursor, int i2) {
        MediaItem loadSpace = SharingItemLoader.loadSpace(cursor, i2);
        if (Features.isEnabled(Features.SUPPORT_SHARED_RAW_QUERY)) {
            return loadSpace;
        }
        return updateCoverPath(loadSpace);
    }

    public void onDestroy() {
        Optional.ofNullable(this.mData).ifPresent(new e(26));
        Optional.ofNullable(this.mSpaceMap).ifPresent(new C0596d(6));
        Optional.ofNullable(this.mGroupMap).ifPresent(new C0596d(6));
        this.mChildDataMap.clear();
        super.onDestroy();
    }

    public MediaItem read(int i2) {
        if (i2 < 0 || i2 >= this.mData.size()) {
            return null;
        }
        MediaItem mediaItem = this.mData.get(i2);
        if (mediaItem != null) {
            return mediaItem;
        }
        return loadInternal(i2);
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        MediaItem readCache = readCache(i2);
        if (readCache != null) {
            onDataReadListener.onDataReadCompleted(readCache);
        } else {
            getThreadPool().submit(new J(i2, 0, onDataReadListener, this));
        }
    }

    public MediaItem readByKey(String str) {
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM) || !TextUtils.equals(str, Group.GROUP_TYPE_SA_FAMILY_GROUP)) {
            return this.mSpaceMap.get(str);
        }
        return this.mFamilyItem;
    }

    public MediaItem readCache(int i2) {
        if (i2 < 0 || i2 >= this.mData.size()) {
            return null;
        }
        return this.mData.get(i2);
    }

    public void register(MediaData.OnDataChangeListener onDataChangeListener) {
        super.register(onDataChangeListener);
        if (this.mRequestPendingCount > 0) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "  >> register (" + this.mLocationKey + ") > request pending data " + this.mRequestPendingCount);
            this.mRequestPendingCount = 0;
            requestData(this.mLocationReference);
        }
    }

    public void requestData(String str, EventMessage eventMessage) {
        if (this.mListener.size() == 0) {
            this.mRequestPendingCount++;
            StringCompat stringCompat = this.TAG;
            StringBuilder k = j.k("  >> requestData (", str, ") > pending=");
            k.append(this.mRequestPendingCount);
            k.append(ArcCommonLog.TAG_COMMA);
            k.append(eventMessage);
            Log.e(stringCompat, k.toString());
            return;
        }
        super.requestData(str, eventMessage);
    }

    public void swap(Cursor[] cursorArr) {
        if (this.mLock.acquireWriteLock()) {
            Cursor[] cursorArr2 = this.mCursors;
            int cursorCount = getCursorCount(cursorArr);
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            L l = new L(this, arrayList, hashMap2, cursorArr2, cursorArr, hashMap, hashMap3, cursorCount, currentTimeMillis);
            try {
                loadData(arrayList, hashMap, hashMap2, hashMap3, cursorArr, l);
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "loadData failed", (Throwable) e);
                this.mLock.releaseWriteLock();
            }
        } else {
            Log.e(this.TAG, "fail to get lock");
        }
    }

    public ArrayList<Long> getFileIds() {
        return (ArrayList) this.mData.stream().map(new K(0)).collect(Collectors.toCollection(new C0720a(1)));
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaDataChild extends MediaDataRef {
        final ConcurrentHashMap<String, ArrayList<MediaItem>> childDataMap;
        final MediaData parent;

        public MediaDataChild(Blackboard blackboard, String str, MediaDataMdeSpace mediaDataMdeSpace) {
            super(blackboard, str);
            this.parent = mediaDataMdeSpace;
            this.childDataMap = mediaDataMdeSpace.mChildDataMap;
        }

        private ArrayList<MediaItem> getData() {
            return this.childDataMap.computeIfAbsent(this.mLocationKey, new K(3));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ ArrayList lambda$getData$0(String str) {
            return new ArrayList();
        }

        public int getCount() {
            return getData().size();
        }

        public int getDataVersion() {
            return this.parent.getDataVersion();
        }

        public ArrayList<MediaItem> getFullData() {
            if (this.childDataMap.get(this.mLocationKey) == null) {
                return new ArrayList<>();
            }
            return this.childDataMap.get(this.mLocationKey);
        }

        public int getRealCount() {
            return getCount();
        }

        public MediaItem read(int i2) {
            return readCache(i2);
        }

        public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
            onDataReadListener.onDataReadCompleted(readCache(i2));
        }

        public MediaItem readCache(int i2) {
            try {
                return getData().get(i2);
            } catch (Exception unused) {
                Log.e((CharSequence) "MediaDataChild", "readCache failed", this.mLocationKey, Integer.valueOf(getCount()), Integer.valueOf(i2));
                return null;
            }
        }

        public void requestData(String str) {
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$preloadThumbnail$5(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
    }
}
