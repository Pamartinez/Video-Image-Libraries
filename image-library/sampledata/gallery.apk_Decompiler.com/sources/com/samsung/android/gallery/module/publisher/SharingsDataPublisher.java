package com.samsung.android.gallery.module.publisher;

import A.a;
import J6.c;
import M9.j;
import M9.k;
import M9.l;
import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.local.CacheProviderHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mde.MdeSpaceGroupCursor;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.CursorCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.Utils;
import java.io.Closeable;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingsDataPublisher extends CursorPublisher {
    private static final Cursor EMPTY_CURSOR = CursorCompat.EMPTY_CURSOR;

    public SharingsDataPublisher(Blackboard blackboard) {
        super(blackboard);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishSharingsData$4(Cursor[] cursorArr, MdeDatabase mdeDatabase) {
        cursorArr[0] = mdeDatabase.getSharingsCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishSharingsData$5(Cursor[] cursorArr, TimeTickLog timeTickLog) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            PreferenceCache preferenceCache = PreferenceCache.MxAlbumSharedCount;
            int i2 = 0;
            Cursor cursor = cursorArr[0];
            if (cursor != null) {
                i2 = cursor.getCount();
            }
            preferenceCache.setInt(i2);
        }
        timeTickLog.tick();
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishSharingsData " + Logger.toString(cursorArr) + " +" + timeTickLog.summary());
        this.mBlackboard.erase(DataKey.DATA_CURSOR("location://sharing/albums"));
        publishCursorArray("location://sharing/albums", cursorArr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishSharingsData$6(Cursor[] cursorArr, MdeDatabase mdeDatabase) {
        cursorArr[1] = mdeDatabase.getSharingInvitationListCursor();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishSharingsData$7(Cursor[] cursorArr, MdeDatabase mdeDatabase) {
        cursorArr[2] = mdeDatabase.getSharedGroupCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishSharingsDataCache$1(Object obj, Bundle bundle) {
        SimpleThreadPool.getInstance().execute(new l(this, obj, bundle, 3));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishSharingsDataCache$3(Object obj, Bundle bundle) {
        SimpleThreadPool.getInstance().execute(new l(this, obj, bundle, 2));
    }

    /* access modifiers changed from: private */
    public void publishFamilySharedTrashData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor familySharedTrashCursor = new MdeDatabase().getFamilySharedTrashCursor();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "publishFamilySharedTrashData : " + getCursorInfo(familySharedTrashCursor, currentTimeMillis));
        publishCursorArray("location://family/shared/trash", new Cursor[]{familySharedTrashCursor});
    }

    /* access modifiers changed from: private */
    public void publishSharingChoiceData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor cursor = EMPTY_CURSOR;
        try {
            cursor = new MdeDatabase().getSharingsCursor();
            StringCompat stringCompat = this.TAG;
            Log.p(stringCompat, "publishSharingChoiceData " + getCursorInfo(cursor, currentTimeMillis));
        } catch (Exception e) {
            StringCompat stringCompat2 = this.TAG;
            Log.e(stringCompat2, "publishSharingChoiceData failed " + Logger.toString(bundle) + " e=" + e.getMessage());
        }
        publishCursorArray("location://sharing/choice", new Cursor[]{cursor});
    }

    /* access modifiers changed from: private */
    public void publishSharingGroupMembersData(Object obj, Bundle bundle) {
        Cursor cursor;
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            MdeDatabase mdeDatabase = new MdeDatabase();
            assertArgumentId(bundle);
            if (obj != null) {
                str = obj.toString();
            } else {
                str = null;
            }
            cursor = mdeDatabase.getSharedGroupMembersCursor(String.valueOf(bundle.getString("id", str)));
            Log.p(this.TAG, "publishSharingGroupMembersData : " + getCursorInfo(cursor, currentTimeMillis));
        } catch (Exception e) {
            Log.e(this.TAG, "publishSharingGroupMembersData failed e=" + e.getMessage());
            Utils.closeSilently((Closeable) null);
            cursor = EMPTY_CURSOR;
        }
        publishCursorArray("location://sharing/groupMembers", new Cursor[]{cursor});
    }

    /* access modifiers changed from: private */
    public void publishSharingGroupsData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor cursor = null;
        try {
            cursor = new MdeDatabase().getSharedGroupCursor();
            StringCompat stringCompat = this.TAG;
            Log.p(stringCompat, "publishSharingGroupsData : " + getCursorInfo(cursor, currentTimeMillis));
        } catch (Exception e) {
            StringCompat stringCompat2 = this.TAG;
            Log.e(stringCompat2, "publishSharingGroupsData failed e=" + e.getMessage());
            Utils.closeSilently(cursor);
            cursor = EMPTY_CURSOR;
        }
        publishCursorArray("location://sharing/groups", new Cursor[]{cursor});
    }

    /* access modifiers changed from: private */
    public void publishSharingInvitationsData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor cursor = null;
        try {
            cursor = new MdeDatabase().getSharingInvitationListCursor();
            StringCompat stringCompat = this.TAG;
            Log.p(stringCompat, "getSharedInvitationListCursor : " + getCursorInfo(cursor, currentTimeMillis));
        } catch (Exception e) {
            StringCompat stringCompat2 = this.TAG;
            Log.e(stringCompat2, "getSharedInvitationListCursor failed e=" + e.getMessage());
            Utils.closeSilently(cursor);
            cursor = EMPTY_CURSOR;
        }
        publishCursorArray("location://sharing/albums/invitations", new Cursor[]{cursor});
    }

    /* access modifiers changed from: private */
    public void publishSharingPicturesData(Object obj, Bundle bundle) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        MdeDatabase mdeDatabase = new MdeDatabase();
        assertArgumentId(bundle);
        if (obj != null) {
            str = obj.toString();
        } else {
            str = null;
        }
        Cursor sharedItemCursor = mdeDatabase.getSharedItemCursor(String.valueOf(bundle.getString("id", str)), (String) null);
        Log.d(this.TAG, "publishSharingPicturesData : " + getCursorInfo(sharedItemCursor, currentTimeMillis));
        publishCursorArray("location://sharing/albums/fileList", new Cursor[]{sharedItemCursor});
    }

    /* access modifiers changed from: private */
    public void publishSharingPicturesHeaderDataMediaCount(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor sharedItemMimeTypeCursor = new MdeDatabase().getSharedItemMimeTypeCursor(obj.toString());
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishSharingPicturesHeaderDataMediaCount : " + getCursorInfo(sharedItemMimeTypeCursor, currentTimeMillis));
        publishCursorArray("sharingPicturesHeader/mediaCount", new Cursor[]{sharedItemMimeTypeCursor});
    }

    /* access modifiers changed from: private */
    public void publishSharingPicturesStorageUsageData(Object obj, Bundle bundle) {
        String str;
        String str2;
        long currentTimeMillis = System.currentTimeMillis();
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        assertArgumentId(bundle);
        String str3 = null;
        if (obj != null) {
            str = obj.toString();
        } else {
            str = null;
        }
        if (MdeGroupHelper.isSAFamilyGroup(String.valueOf(bundle.getString("groupId", str)))) {
            str2 = "";
        } else {
            str2 = "(is_owned_by_me > 0)";
        }
        if (obj != null) {
            str3 = obj.toString();
        }
        Cursor sharedItemCursorFromStorageUsage = new MdeDatabase().getSharedItemCursorFromStorageUsage(String.valueOf(bundle.getString("id", str3)), str2);
        Log.d(this.TAG, "publishSharingPicturesStorageUsageData : " + getCursorInfo(sharedItemCursorFromStorageUsage, currentTimeMillis));
        publishCursorArray("location://sharing/albums/fileList/storageUsage", new Cursor[]{sharedItemCursorFromStorageUsage});
    }

    /* access modifiers changed from: private */
    public void publishSharingStorageUsageData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor[] cursorArr = new Cursor[4];
        try {
            MdeDatabase mdeDatabase = new MdeDatabase();
            cursorArr[0] = mdeDatabase.getSharingStorageUsageCursor();
            if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM)) {
                cursorArr[3] = mdeDatabase.getSharingFamilyStorageUsageCursor();
            }
            StringCompat stringCompat = this.TAG;
            Log.p(stringCompat, "publishSharingStorageUsageData : " + getCursorListInfo(cursorArr, currentTimeMillis));
        } catch (Exception e) {
            a.r(e, new StringBuilder("publishSharingStorageUsageData failed e="), this.TAG);
            Utils.closeSilently(cursorArr[0]);
            cursorArr[0] = EMPTY_CURSOR;
        }
        publishCursorArray("location://sharing/albums/storageUse", cursorArr);
    }

    /* access modifiers changed from: private */
    /* renamed from: publishSharingsData */
    public void lambda$publishSharingsDataCache$2(Object obj, Bundle bundle) {
        String str;
        if (!MdeSharingService.getInstance().isServiceSupported()) {
            StringCompat stringCompat = this.TAG;
            if (!PreferenceFeatures.CHINA.SHARING_SERVICE_ENABLER || PreferenceFeatures.isEnabled(PreferenceFeatures.SharingServiceEnabled)) {
                str = "package unavailable";
            } else {
                str = "service disabled";
            }
            Log.e((CharSequence) stringCompat, "publishSharingsData skip", str);
            publishCursorArray("location://sharing/albums", new Cursor[]{null, null, null});
        } else if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !BundleWrapper.getBoolean(bundle, "sharing_cache_preferred", false)) {
            Cursor[] cursorArr = {EMPTY_CURSOR, null, null};
            if (MdeSharingService.getInstance().showSemsPermissionPopup()) {
                Log.e(this.TAG, "publishSharingsData skip. service disabled");
                if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                    PreferenceCache.MxAlbumSharedCount.setInt(0);
                }
                this.mBlackboard.erase(DataKey.DATA_CURSOR("location://sharing/albums"));
                publishCursorArray("location://sharing/albums", cursorArr);
                return;
            }
            try {
                Trace.beginSection("publishSharingsData");
                TimeTickLog timeTickLog = new TimeTickLog();
                MdeDatabase mdeDatabase = new MdeDatabase();
                LatchBuilder postExecutor = new LatchBuilder("publishSharingsData").setCurrent(new k(cursorArr, mdeDatabase, 0)).setPostExecutor((Runnable) new c(this, cursorArr, timeTickLog, 13));
                if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS) {
                    postExecutor.addWorker(new k(cursorArr, mdeDatabase, 1));
                }
                if (Features.isEnabled(Features.SUPPORT_SHARED_GROUP_RAW_QUERY)) {
                    postExecutor.addWorker(new k(cursorArr, mdeDatabase, 2));
                }
                timeTickLog.tick();
                postExecutor.start();
            } catch (Exception e) {
                StringCompat stringCompat2 = this.TAG;
                Log.e(stringCompat2, "publishSharingsData failed e=" + e.getMessage());
            } finally {
                Trace.endSection();
            }
        } else {
            bundle.remove("sharing_cache_preferred");
            publishSharingsDataCache(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void publishSharingsFamilySuggestedData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = BundleWrapper.getInt(bundle, "id", 0);
        Cursor autoAlbumPicturesCursor = DbCompat.autoAlbumApi().getAutoAlbumPicturesCursor(i2, GalleryPreference.getInstance().loadSortBy(String.valueOf(i2), 12));
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishSharingsFamilySuggestedData " + getCursorInfo(autoAlbumPicturesCursor, currentTimeMillis));
        publishCursorArray("location://family/shared/suggested/fileList", new Cursor[]{autoAlbumPicturesCursor});
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://sharing/albums"), new j(this, 0)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://sharing/albums/fileList"), new j(this, 3)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("sharingPicturesHeader/mediaCount"), new j(this, 4)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://sharing/groups"), new j(this, 5)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://sharing/groupMembers"), new j(this, 6)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://sharing/choice"), new j(this, 7)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://sharing/albums/invitations"), new j(this, 8)));
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://sharing/albums/storageUse"), new j(this, 9)));
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://sharing/albums/fileList/storageUsage"), new j(this, 10)));
        }
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://family/shared/suggested/fileList"), new j(this, 1)));
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH)) {
            arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://family/shared/trash"), new j(this, 2)));
        }
    }

    public void publishSharingsDataCache(Object obj, Bundle bundle) {
        Cursor cursor = null;
        try {
            Trace.beginSection("publishSharingsData#Cache");
            TimeTickLog timeTickLog = new TimeTickLog();
            if (PreferenceCache.MxAlbumSharedCount.getInt() > 0) {
                Cursor query = CacheProviderHelper.query("location://sharing/albums");
                try {
                    timeTickLog.tick();
                    if (query != null && query.moveToFirst()) {
                        String data = new CacheProviderHelper.CacheReader(query).getData();
                        timeTickLog.tick();
                        if (data == null || data.isEmpty()) {
                            Log.w(this.TAG, "publishSharingsData(cache) : cacheStr is empty, ignore cache publish");
                        } else {
                            MdeSpaceGroupCursor mdeSpaceGroupCursor = new MdeSpaceGroupCursor(query, data);
                            timeTickLog.tick();
                            StringCompat stringCompat = this.TAG;
                            Log.p(stringCompat, "publishSharingsData(Cache) " + Logger.toString((Cursor) mdeSpaceGroupCursor) + Logger.vt(timeTickLog));
                            this.mBlackboard.erase(DataKey.DATA_CURSOR("location://sharing/albums"));
                            publishCursorArray("location://sharing/albums", new Cursor[]{mdeSpaceGroupCursor}, (String) null);
                            ThreadUtil.postOnBgThreadDelayed(new l(this, obj, bundle, 0), 400);
                        }
                    }
                    cursor = query;
                    ThreadUtil.postOnBgThreadDelayed(new l(this, obj, bundle, 0), 400);
                } catch (Exception e) {
                    e = e;
                    cursor = query;
                    try {
                        StringCompat stringCompat2 = this.TAG;
                        Log.e(stringCompat2, "publishSharingsData(cache) failed e=" + e.getMessage());
                        this.mBlackboard.erase(DataKey.DATA_CURSOR("location://sharing/albums"));
                        lambda$publishSharingsDataCache$2(obj, bundle);
                        Utils.closeSilently(cursor);
                        Trace.endSection();
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    Utils.closeSilently(cursor);
                    Trace.endSection();
                    throw th;
                }
            } else {
                ThreadUtil.postOnBgThreadDelayed(new l(this, obj, bundle, 1), 800);
            }
        } catch (Exception e7) {
            e = e7;
        }
        Utils.closeSilently(cursor);
        Trace.endSection();
    }
}
