package com.samsung.android.gallery.app.controller.externals;

import A.a;
import A8.C0545b;
import Ka.c;
import L5.b;
import M4.j;
import android.content.Intent;
import android.database.Cursor;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.abstraction.ShareData;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.utils.ShareList;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareAlbumCmd extends BaseCommand {
    private int mAlbumCount;
    private int mFolderCount;

    private void addAutoAlbumItems(ArrayList<MediaItem> arrayList, HashMap<Integer, String> hashMap, ArrayList<ShareData> arrayList2, HashSet<Long> hashSet) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS && arrayList.size() > 0) {
            Cursor autoAlbumPicturesCursor = DbCompat.autoAlbumApi().getAutoAlbumPicturesCursor((Collection<Integer>) (List) arrayList.stream().mapToInt(new C0545b(2)).boxed().collect(Collectors.toList()), 0);
            if (autoAlbumPicturesCursor != null) {
                try {
                    if (autoAlbumPicturesCursor.moveToFirst()) {
                        String str = this.TAG;
                        Log.d(str, "share items (auto) size=" + autoAlbumPicturesCursor.getCount());
                        addToSharedList(hashMap, arrayList2, hashSet, autoAlbumPicturesCursor);
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (autoAlbumPicturesCursor != null) {
                autoAlbumPicturesCursor.close();
                return;
            }
            return;
        }
        return;
        throw th;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addFavoriteAlbumItems(java.util.HashMap<java.lang.Integer, java.lang.String> r5, java.util.ArrayList<com.samsung.android.gallery.module.abstraction.ShareData> r6, java.util.HashSet<java.lang.Long> r7) {
        /*
            r4 = this;
            long r0 = java.lang.System.currentTimeMillis()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r2 = new com.samsung.android.gallery.database.dal.abstraction.query.QueryParams
            java.lang.String r3 = com.samsung.android.gallery.database.dal.abstraction.DbKey.VIRTUAL_ALBUM_FAVORITE
            r2.<init>((java.lang.String) r3)
            android.database.Cursor r2 = com.samsung.android.gallery.database.dal.DbCompat.query((com.samsung.android.gallery.database.dal.abstraction.query.QueryParams) r2)
            if (r2 == 0) goto L_0x0029
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x001f }
            if (r3 == 0) goto L_0x0029
            int r3 = r2.getCount()     // Catch:{ all -> 0x001f }
            r4.addToSharedList(r5, r6, r7, r2)     // Catch:{ all -> 0x001f }
            goto L_0x002a
        L_0x001f:
            r4 = move-exception
            r2.close()     // Catch:{ all -> 0x0024 }
            goto L_0x0028
        L_0x0024:
            r5 = move-exception
            r4.addSuppressed(r5)
        L_0x0028:
            throw r4
        L_0x0029:
            r3 = 0
        L_0x002a:
            if (r2 == 0) goto L_0x002f
            r2.close()
        L_0x002f:
            java.lang.String r4 = r4.TAG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "share items (favorite)"
            r5.<init>(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)
            java.lang.Long r7 = java.lang.Long.valueOf(r0)
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r7}
            A.a.A(r6, r5, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.externals.ShareAlbumCmd.addFavoriteAlbumItems(java.util.HashMap, java.util.ArrayList, java.util.HashSet):void");
    }

    private void addNormalAlbumItems(HashMap<Integer, String> hashMap, int[] iArr, ArrayList<ShareData> arrayList, HashSet<Long> hashSet) {
        Cursor query = DbCompat.query(new QueryParams(DbKey.ALBUM_FILES).addAlbumIds(iArr));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    String str = this.TAG;
                    Log.d(str, "share items size=" + query.getCount());
                    do {
                        MediaItem createShareData = MediaItemBuilder.createShareData(query);
                        if (MediaItemUtil.isSharableItem(createShareData, true)) {
                            arrayList.add(new ShareData(ContentUri.getUriString(createShareData), createShareData.getBucketID(), hashMap.get(Integer.valueOf(createShareData.getBucketID()))));
                            hashSet.add(Long.valueOf(createShareData.getFileId()));
                        }
                    } while (query.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
            return;
        }
        return;
        throw th;
    }

    private void addRecentAlbumItems(HashMap<Integer, String> hashMap, ArrayList<ShareData> arrayList, HashSet<Long> hashSet) {
        TimeTickLog timeTickLog = new TimeTickLog();
        int i2 = 0;
        Cursor query = DbCompat.query(new QueryParams(DbKey.VIRTUAL_ALBUM_RECENT).setGroupTypes(new GroupType[0]).setWithEmptyAlbums(false));
        try {
            timeTickLog.tick();
            if (query != null && query.moveToFirst()) {
                i2 = query.getCount();
                addToSharedList(hashMap, arrayList, hashSet, query);
                timeTickLog.tick();
            }
            if (query != null) {
                query.close();
            }
            String str = this.TAG;
            a.A(new Object[]{Integer.valueOf(i2), timeTickLog}, new StringBuilder("share items (recent)"), str);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void addToSharedList(HashMap<Integer, String> hashMap, ArrayList<ShareData> arrayList, HashSet<Long> hashSet, Cursor cursor) {
        do {
            MediaItem createShareData = MediaItemBuilder.createShareData(cursor);
            if ((hashSet == null || !hashSet.contains(Long.valueOf(createShareData.getFileId()))) && MediaItemUtil.isSharableItem(createShareData, true)) {
                String str = hashMap.get(Integer.valueOf(createShareData.getBucketID()));
                if (str == null) {
                    try {
                        str = new File(createShareData.getPath()).getParentFile().getName();
                        hashMap.put(Integer.valueOf(createShareData.getBucketID()), str);
                    } catch (Exception unused) {
                        str = "";
                    }
                }
                arrayList.add(new ShareData(ContentUri.getUriString(createShareData), createShareData.getBucketID(), str));
                if (hashSet != null) {
                    hashSet.add(Long.valueOf(createShareData.getFileId()));
                }
            }
        } while (cursor.moveToNext());
    }

    private void addVirtualAlbumItems(ArrayList<MediaItem> arrayList, HashMap<Integer, String> hashMap, ArrayList<ShareData> arrayList2, HashSet<Long> hashSet) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS && arrayList.size() > 0) {
            Iterator<MediaItem> it = arrayList.iterator();
            while (it.hasNext()) {
                MediaItem next = it.next();
                if (BucketUtils.isFavourite(next.getBucketID())) {
                    addFavoriteAlbumItems(hashMap, arrayList2, hashSet);
                } else if (BucketUtils.isRecent(next.getBucketID())) {
                    addRecentAlbumItems(hashMap, arrayList2, hashSet);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$share$1(ArrayList arrayList, AlbumType albumType, ArrayList arrayList2) {
        if (albumType == AlbumType.None || albumType == AlbumType.MyAlbum) {
            arrayList.addAll(arrayList2);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: share */
    public void lambda$onExecute$0(MediaItem[] mediaItemArr) {
        boolean z;
        int i2;
        String str;
        String str2;
        MediaItem[] mediaItemArr2 = mediaItemArr;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            HashMap<AlbumType, ArrayList<MediaItem>> allChildAlbums = MediaItemUtil.getAllChildAlbums(List.of(mediaItemArr2));
            arrayList = allChildAlbums.getOrDefault(AlbumType.AutoUpdated, new ArrayList());
            arrayList3 = allChildAlbums.getOrDefault(AlbumType.Virtual, new ArrayList());
            allChildAlbums.forEach(new c(arrayList2, 1));
            this.mFolderCount = ((Integer) Optional.ofNullable(allChildAlbums.get(AlbumType.Folder)).map(new b(16)).orElse(0)).intValue();
            this.mAlbumCount = arrayList3.size() + arrayList.size() + arrayList2.size();
            arrayList3.size();
            Optional findAny = arrayList3.stream().filter(new j(8)).findAny();
            z = findAny.isPresent();
            int intValue = ((Integer) findAny.map(new b(17)).orElse(0)).intValue();
            String str3 = this.TAG;
            String str4 = "size=" + this.mAlbumCount;
            String g = N2.j.g(new StringBuilder("album="), arrayList2);
            String str5 = "folder=" + this.mFolderCount;
            String g3 = N2.j.g(new StringBuilder("auto="), arrayList);
            StringBuilder sb2 = new StringBuilder("recent=");
            sb2.append(z);
            if (z) {
                str2 = C0086a.i(intValue, "#");
            } else {
                str2 = "";
            }
            sb2.append(str2);
            Log.d(str3, "share albums", str4, g, str5, g3, sb2.toString(), "virtual=" + arrayList3.size() + "");
        } else {
            for (MediaItem mediaItem : mediaItemArr2) {
                if (mediaItem.isFolder()) {
                    this.mFolderCount++;
                    arrayList2.addAll(Arrays.asList(mediaItem.getAlbumsInFolder()));
                } else {
                    this.mAlbumCount++;
                    arrayList2.add(mediaItem);
                }
            }
            z = false;
        }
        HashMap hashMap = new HashMap();
        int[] iArr = new int[arrayList2.size()];
        Iterator it = arrayList2.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            MediaItem mediaItem2 = (MediaItem) it.next();
            hashMap.put(Integer.valueOf(mediaItem2.getBucketID()), mediaItem2.getTitle());
            iArr[i7] = mediaItem2.getBucketID();
            i7++;
        }
        String str6 = this.TAG;
        StringBuilder sb3 = new StringBuilder("share albums size=");
        sb3.append(arrayList2.size());
        sb3.append(" {");
        sb3.append(StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, Arrays.stream(iArr).limit(6).iterator()));
        N2.j.y(sb3, "}", str6);
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !z) {
            ArrayList arrayList4 = new ArrayList();
            HashSet hashSet = new HashSet();
            addNormalAlbumItems(hashMap, iArr, arrayList4, hashSet);
            addAutoAlbumItems(arrayList, hashMap, arrayList4, hashSet);
            addVirtualAlbumItems(arrayList3, hashMap, arrayList4, hashSet);
            i2 = arrayList4.size();
            if (i2 == 0) {
                Log.e(this.TAG, "share list failed. empty list");
                showToast();
                return;
            }
            ShareList.setShareList(arrayList4);
            Log.d(this.TAG, "share list", Integer.valueOf(i2), arrayList4.get(0), C0212a.i(arrayList4, 1));
        } else {
            i2 = new MpHelper().getVirtualRecentShareCount();
            if (i2 == 0) {
                Log.e(this.TAG, "share recent failed. empty recent");
                showToast();
                return;
            }
            ShareList.setShareBucketList(List.of(Integer.valueOf(BucketUtils.VirtualBucketHolder.recent)));
            Log.d(this.TAG, "share recent", Integer.valueOf(i2));
        }
        if (z) {
            str = "all";
        } else {
            str = "album";
        }
        try {
            Intent buildIntent = buildIntent(i2, str);
            Log.d(this.TAG, "share " + buildIntent + "\n" + buildIntent.getExtras());
            getContext().startActivity(buildIntent);
            getBlackboard().postEvent(EventMessage.obtain(1003));
        } catch (Exception e) {
            a.s(e, new StringBuilder("performUnlimitedShareVia failed e="), this.TAG);
        }
    }

    private void showToast() {
        int i2;
        int i7 = this.mAlbumCount;
        if (i7 == 1 && this.mFolderCount == 0) {
            i2 = R.string.cannot_share_an_empty_album;
        } else if (i7 > 1 && this.mFolderCount == 0) {
            i2 = R.string.cannot_share_empty_albums;
        } else if (i7 == 0 && this.mFolderCount == 1) {
            i2 = R.string.cannot_share_an_empty_group;
        } else if (i7 != 0 || this.mFolderCount <= 1) {
            i2 = R.string.cannot_share_empty_albums_groups;
        } else {
            i2 = R.string.cannot_share_empty_groups;
        }
        showToast(i2);
    }

    public Intent buildIntent(int i2, String str) {
        Intent intent = new Intent("com.samsung.android.gallery.action.UNLIMITED_SHARE");
        intent.putExtra("share-version", 1);
        intent.putExtra("share-from", "gallery");
        intent.putExtra("share-count", i2);
        intent.putExtra("share-category", str);
        intent.putExtra("share-projection", new String[]{"share_uri", "share_bucket_id", "share_bucket_name"});
        return Intent.createChooser(intent, getContext().getString(R.string.share_album));
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_BOTTOM_MENU_SHARE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            Log.e(this.TAG, "skip due to empty object");
            return;
        }
        MediaItem[] mediaItemArr = objArr[0];
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.e(this.TAG, "skip due to empty item");
            return;
        }
        this.mAlbumCount = 0;
        this.mFolderCount = 0;
        String str = this.TAG;
        Log.d(str, "ShareAlbumCmd " + mediaItemArr.length + ArcCommonLog.TAG_COMMA + MediaItemUtil.getSimpleLog(mediaItemArr[0]));
        SimpleThreadPool.getInstance().execute(new M5.a(6, this, mediaItemArr));
    }
}
