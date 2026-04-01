package com.samsung.android.gallery.module.album;

import A.a;
import A4.C0368c;
import B8.b;
import N2.j;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumNewLabelUpdater {
    private static volatile AlbumNewLabelUpdater sInstance;
    private int mLatestAlbumId = loadLatestAlbumId();

    private static int compare(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null && mediaItem2 == null) {
            return 0;
        }
        if (mediaItem2 == null) {
            return 1;
        }
        if (mediaItem == null) {
            return -1;
        }
        long albumDateModified = mediaItem.getAlbumDateModified();
        long albumDateModified2 = mediaItem2.getAlbumDateModified();
        if (albumDateModified == albumDateModified2) {
            return Long.compare(mediaItem.getAlbumFileId(), mediaItem2.getAlbumFileId());
        }
        return Long.compare(albumDateModified, albumDateModified2);
    }

    private MediaItem findLatestAlbum() {
        Cursor query;
        try {
            query = DbCompat.query(DbKey.ALBUMS_NEW, (Consumer<QueryParams>) null);
            if (query != null) {
                if (query.getCount() > 0 && query.moveToFirst()) {
                    MediaItem createNewAlbumLabel = MediaItemBuilder.createNewAlbumLabel(query);
                    query.close();
                    return createNewAlbumLabel;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            j.s(e, new StringBuilder("findLatestAlbum(DB) failed. e="), "AlbumNewLabelUpdater");
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return null;
        throw th;
    }

    private String getBucket(String str) {
        try {
            return Logger.getEncodedString(new File(str).getParent().replaceFirst("^/storage/(emulated/\\d*/|.{4}-.{4}/)", "/"));
        } catch (Exception unused) {
            return Logger.getEncodedString(str);
        }
    }

    public static AlbumNewLabelUpdater getInstance() {
        if (sInstance == null) {
            synchronized (AlbumNewLabelUpdater.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new AlbumNewLabelUpdater();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private String getReadableName(MediaItem mediaItem) {
        try {
            return (String) Optional.ofNullable(mediaItem.getTitle()).orElse(getBucket(mediaItem.getPath()));
        } catch (Exception unused) {
            return mediaItem.getDisplayName();
        }
    }

    private boolean isAlbumOpen(int i2) {
        return Blackboard.getBlackboardMap().values().stream().anyMatch(new b(i2, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refresh$1() {
        lambda$update$0(loadLatestAlbumId());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refresh$2() {
        lambda$update$0(loadLatestAlbumId());
    }

    private int loadLatestAlbumId() {
        return PreferenceCache.AlbumLatestUpdatedId.getInt();
    }

    private long loadLatestAlbumTime() {
        return PreferenceCache.AlbumLatestUpdatedTime.getLong();
    }

    /* access modifiers changed from: private */
    /* renamed from: notifyIfChanged */
    public void lambda$update$0(int i2) {
        if (this.mLatestAlbumId != i2) {
            this.mLatestAlbumId = i2;
            Blackboard.postGlobal("data://user/latest_album_id_changed", Integer.valueOf(i2));
        }
    }

    private int saveLatestAlbum(int i2, long j2) {
        PreferenceCache.AlbumLatestUpdatedId.setInt(i2);
        PreferenceCache.AlbumLatestUpdatedTime.setLong(j2);
        return i2;
    }

    private int saveLatestAlbumIfChanged(int i2, long j2) {
        int loadLatestAlbumId = loadLatestAlbumId();
        long loadLatestAlbumTime = loadLatestAlbumTime();
        int i7 = (j2 > loadLatestAlbumTime ? 1 : (j2 == loadLatestAlbumTime ? 0 : -1));
        if (i7 > 0 || (i7 == 0 && i2 != loadLatestAlbumId)) {
            Log.i("AlbumNewLabelUpdater", "NewLabel {UPDATE," + loadLatestAlbumId + "(" + loadLatestAlbumTime + ") > " + i2 + "(" + j2 + ")}");
            return saveLatestAlbum(i2, j2);
        }
        Log.d("AlbumNewLabelUpdater", "NewLabel {SKIP," + loadLatestAlbumId + "(" + loadLatestAlbumTime + ")," + (j2 - loadLatestAlbumTime) + "}");
        return loadLatestAlbumId;
    }

    private int updateAlbumNewLabel(ArrayList<MediaItem> arrayList) {
        boolean z;
        int i2;
        String str;
        TimeTickLog timeTickLog = new TimeTickLog();
        boolean z3 = false;
        if (arrayList != null) {
            MediaItem mediaItem = null;
            try {
                mediaItem = findLatestAlbum(arrayList, (MediaItem) null);
            } catch (Exception e) {
                a.s(e, new StringBuilder("findLatestAlbum(list) failed. e="), "AlbumNewLabelUpdater");
            }
            timeTickLog.tick();
            if (mediaItem == null || mediaItem.isCustomCover()) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                mediaItem = findLatestAlbum();
                timeTickLog.tick();
            }
            MediaItem mediaItem2 = mediaItem;
            if (mediaItem2 != null) {
                if (isAlbumOpen(mediaItem2.getAlbumID())) {
                    i2 = 0;
                } else {
                    i2 = mediaItem2.getAlbumID();
                }
                StringBuilder sb2 = new StringBuilder("NewLabel");
                if (z) {
                    str = "DB";
                } else {
                    str = "LIST";
                }
                if (i2 != 0) {
                    z3 = true;
                }
                sb2.append(Logger.vt(str, Boolean.valueOf(z3), mediaItem2.getAlbumID() + "(" + mediaItem2.getDateModified() + ")", Integer.valueOf(mediaItem2.getCount()), Logger.getEncodedString(getReadableName(mediaItem2)), timeTickLog));
                Log.d("AlbumNewLabelUpdater", sb2.toString());
                return saveLatestAlbumIfChanged(i2, mediaItem2.getDateModified());
            }
        }
        Log.e("AlbumNewLabelUpdater", "NewLabel skip. no data" + Logger.vt(timeTickLog));
        return 0;
    }

    public int getLatestAlbumId() {
        return this.mLatestAlbumId;
    }

    public void refresh(EventMessage eventMessage) {
        String str;
        MediaItem mediaItem;
        if (eventMessage == null) {
            ThreadUtil.runOnUiThread(new B8.a(this, 0));
            return;
        }
        int i2 = eventMessage.arg1;
        Object[] objArr = (Object[]) eventMessage.obj;
        String str2 = null;
        if (objArr != null && ((Boolean) objArr[0]).booleanValue()) {
            int i7 = this.mLatestAlbumId;
            if (i7 != 0) {
                if (objArr.length > 2) {
                    mediaItem = (MediaItem) objArr[2];
                } else {
                    mediaItem = null;
                }
                if (i2 != i7 && MediaItemUtil.containsAlbum(mediaItem, i7)) {
                    i2 = this.mLatestAlbumId;
                }
                if (i2 != this.mLatestAlbumId) {
                    Log.d("AlbumNewLabelUpdater", "NewLabel", "not matched", Integer.valueOf(i2), Integer.valueOf(this.mLatestAlbumId));
                    return;
                }
                i2 = 0;
            } else {
                return;
            }
        }
        if (i2 != 0) {
            str = "CLEAR";
        } else {
            str = "REFRESH";
        }
        Integer valueOf = Integer.valueOf(i2);
        Integer valueOf2 = Integer.valueOf(this.mLatestAlbumId);
        if (objArr != null && objArr.length > 1) {
            str2 = (String) objArr[1];
        }
        Log.d("AlbumNewLabelUpdater", "NewLabel", str, valueOf, valueOf2, getBucket(str2));
        saveLatestAlbum(i2, System.currentTimeMillis() / 1000);
        ThreadUtil.runOnUiThread(new B8.a(this, 1));
    }

    public void update(ArrayList<MediaItem> arrayList) {
        ThreadUtil.postOnUiThread(new C0368c(this, updateAlbumNewLabel(arrayList), 1));
    }

    private MediaItem findLatestAlbum(List<MediaItem> list, MediaItem mediaItem) {
        for (MediaItem next : list) {
            if (next.isFolder() || next.isMergedAlbum()) {
                mediaItem = findLatestAlbum(List.of(next.getAlbumsInFolder(false)), mediaItem);
            } else if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !next.isVirtualAlbum()) {
                if (next.isCustomCover()) {
                    try {
                        if (compare(next, mediaItem) <= 0) {
                        }
                    } catch (Error | Exception unused) {
                        throw new IllegalArgumentException("custom cover found " + next.getAlbumID());
                    }
                } else if (compare(next, mediaItem) <= 0) {
                }
                mediaItem = next;
            }
        }
        return mediaItem;
    }
}
