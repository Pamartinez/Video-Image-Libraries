package com.samsung.android.gallery.app.activity.external.launcher;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuickViewItemLoader {
    protected final String TAG = getClass().getSimpleName();
    private final Context mContext;
    private LaunchIntent mLaunchIntent;

    public QuickViewItemLoader(Context context) {
        this.mContext = context;
    }

    private String getDbKeyForGroupShot(int i2) {
        if (GroupType.BURST.value == i2) {
            return DbKey.FILES_BURSTSHOT;
        }
        if (GroupType.SINGLE_TAKEN.value == i2) {
            return DbKey.FILES_SINGLETAKE;
        }
        return DbKey.FILES_BURSTSHOT;
    }

    private void setDataType(MediaItem mediaItem) {
        if (mediaItem != null && mediaItem.isUriItem() && mediaItem.getMimeType() == null) {
            String type = this.mLaunchIntent.getType();
            mediaItem.setMimeType(type);
            mediaItem.setMediaType(UriItemLoader.getMediaType(type, this.mLaunchIntent.isActionView()));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b A[SYNTHETIC, Splitter:B:12:0x002b] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0031 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.gallery.module.data.MediaItem getBestItem(com.samsung.android.gallery.module.data.MediaItem r4) {
        /*
            r3 = this;
            int r0 = r4.getGroupType()
            java.lang.String r0 = r3.getDbKeyForGroupShot(r0)
            E3.h r1 = new E3.h     // Catch:{ Exception -> 0x002f }
            r2 = 0
            r1.<init>(r0, r4, r2)     // Catch:{ Exception -> 0x002f }
            android.database.Cursor r0 = com.samsung.android.gallery.database.dal.DbCompat.query((java.util.function.Consumer<com.samsung.android.gallery.database.dal.abstraction.query.QueryParams>) r1)     // Catch:{ Exception -> 0x002f }
            if (r0 == 0) goto L_0x0022
            int r1 = r0.getCount()     // Catch:{ all -> 0x0020 }
            if (r1 <= 0) goto L_0x0022
            r1 = 0
            com.samsung.android.gallery.module.data.MediaItem r4 = com.samsung.android.gallery.module.data.MediaItemLoader.load(r0, r1)     // Catch:{ all -> 0x0020 }
            goto L_0x0029
        L_0x0020:
            r1 = move-exception
            goto L_0x0032
        L_0x0022:
            java.lang.String r1 = r3.TAG     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = "getBestItem empty"
            com.samsung.android.gallery.support.utils.Log.w(r1, r2)     // Catch:{ all -> 0x0020 }
        L_0x0029:
            if (r0 == 0) goto L_0x0031
            r0.close()     // Catch:{ Exception -> 0x002f }
            return r4
        L_0x002f:
            r0 = move-exception
            goto L_0x003d
        L_0x0031:
            return r4
        L_0x0032:
            if (r0 == 0) goto L_0x003c
            r0.close()     // Catch:{ all -> 0x0038 }
            goto L_0x003c
        L_0x0038:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ Exception -> 0x002f }
        L_0x003c:
            throw r1     // Catch:{ Exception -> 0x002f }
        L_0x003d:
            java.lang.String r3 = r3.TAG
            java.lang.String r1 = "getBestItem failed e"
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r3, (java.lang.String) r1, (java.lang.Throwable) r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.external.launcher.QuickViewItemLoader.getBestItem(com.samsung.android.gallery.module.data.MediaItem):com.samsung.android.gallery.module.data.MediaItem");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0057 A[SYNTHETIC, Splitter:B:17:0x0057] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.gallery.module.data.MediaItem loadAlbumItem(boolean r5, android.net.Uri r6) {
        /*
            r4 = this;
            com.samsung.android.gallery.module.abstraction.LaunchIntent r0 = r4.mLaunchIntent
            boolean r0 = r0.needToCheckCloudContentIncluded()
            r1 = 1
            r0 = r0 ^ r1
            com.samsung.android.gallery.module.abstraction.LaunchIntent r2 = r4.mLaunchIntent
            boolean r2 = r2.isImageOnly()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r3 = new com.samsung.android.gallery.database.dal.abstraction.query.QueryParams     // Catch:{ Exception -> 0x0066 }
            r3.<init>(r0, r5, r2)     // Catch:{ Exception -> 0x0066 }
            java.lang.String r5 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALL_PICTURES     // Catch:{ Exception -> 0x0066 }
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r5 = r3.setDbKey(r5)     // Catch:{ Exception -> 0x0066 }
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r5 = r5.addUri((android.net.Uri) r6)     // Catch:{ Exception -> 0x0066 }
            android.database.Cursor r5 = com.samsung.android.gallery.database.dal.DbCompat.query((com.samsung.android.gallery.database.dal.abstraction.query.QueryParams) r5)     // Catch:{ Exception -> 0x0066 }
            if (r5 == 0) goto L_0x003f
            int r0 = r5.getCount()     // Catch:{ all -> 0x003d }
            if (r0 <= 0) goto L_0x003f
            r6 = 0
            com.samsung.android.gallery.module.data.MediaItem r6 = com.samsung.android.gallery.module.data.MediaItemLoader.load(r5, r6)     // Catch:{ all -> 0x003d }
            long r0 = r6.getGroupMediaId()     // Catch:{ all -> 0x003d }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x0055
            com.samsung.android.gallery.module.data.MediaItem r6 = r4.getBestItem(r6)     // Catch:{ all -> 0x003d }
            goto L_0x0055
        L_0x003d:
            r4 = move-exception
            goto L_0x005b
        L_0x003f:
            com.samsung.android.gallery.support.config.SdkConfig$GED r0 = com.samsung.android.gallery.support.config.SdkConfig.GED.Q     // Catch:{ all -> 0x003d }
            boolean r0 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.GED) r0)     // Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x0054
            android.content.Context r0 = r4.mContext     // Catch:{ all -> 0x003d }
            com.samsung.android.gallery.module.abstraction.LaunchIntent r4 = r4.mLaunchIntent     // Catch:{ all -> 0x003d }
            java.lang.String r4 = r4.getType()     // Catch:{ all -> 0x003d }
            com.samsung.android.gallery.module.data.MediaItem r6 = com.samsung.android.gallery.module.data.UriItemLoader.createMediaItem(r0, r6, r4, r1)     // Catch:{ all -> 0x003d }
            goto L_0x0055
        L_0x0054:
            r6 = 0
        L_0x0055:
            if (r5 == 0) goto L_0x005a
            r5.close()     // Catch:{ Exception -> 0x0066 }
        L_0x005a:
            return r6
        L_0x005b:
            if (r5 == 0) goto L_0x0065
            r5.close()     // Catch:{ all -> 0x0061 }
            goto L_0x0065
        L_0x0061:
            r5 = move-exception
            r4.addSuppressed(r5)     // Catch:{ Exception -> 0x0066 }
        L_0x0065:
            throw r4     // Catch:{ Exception -> 0x0066 }
        L_0x0066:
            r4 = move-exception
            java.lang.Exception r5 = new java.lang.Exception
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.external.launcher.QuickViewItemLoader.loadAlbumItem(boolean, android.net.Uri):com.samsung.android.gallery.module.data.MediaItem");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e A[Catch:{ all -> 0x0031, all -> 0x0036, Exception -> 0x0042 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.gallery.module.data.MediaItem loadMirroringItem() {
        /*
            r3 = this;
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r0 = new com.samsung.android.gallery.database.dal.abstraction.query.QueryParams     // Catch:{ Exception -> 0x0042 }
            java.lang.String r1 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALL_PICTURES     // Catch:{ Exception -> 0x0042 }
            r0.<init>((java.lang.String) r1)     // Catch:{ Exception -> 0x0042 }
            com.samsung.android.gallery.module.abstraction.LaunchIntent r1 = r3.mLaunchIntent     // Catch:{ Exception -> 0x0042 }
            android.net.Uri r1 = r1.getUriData()     // Catch:{ Exception -> 0x0042 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0042 }
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r0 = r0.addUri((java.lang.String) r1)     // Catch:{ Exception -> 0x0042 }
            android.database.Cursor r0 = com.samsung.android.gallery.database.dal.DbCompat.query((com.samsung.android.gallery.database.dal.abstraction.query.QueryParams) r0)     // Catch:{ Exception -> 0x0042 }
            if (r0 == 0) goto L_0x003b
            int r1 = r0.getCount()     // Catch:{ all -> 0x0031 }
            if (r1 <= 0) goto L_0x003b
            r1 = 0
            com.samsung.android.gallery.module.data.MediaItem r1 = com.samsung.android.gallery.module.data.MediaItemLoader.load(r0, r1)     // Catch:{ all -> 0x0031 }
            boolean r2 = r1.isBurstShot()     // Catch:{ all -> 0x0031 }
            if (r2 == 0) goto L_0x003c
            com.samsung.android.gallery.module.data.MediaItem r1 = r3.getBestItem(r1)     // Catch:{ all -> 0x0031 }
            goto L_0x003c
        L_0x0031:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ Exception -> 0x0042 }
        L_0x003a:
            throw r3     // Catch:{ Exception -> 0x0042 }
        L_0x003b:
            r1 = 0
        L_0x003c:
            if (r0 == 0) goto L_0x0041
            r0.close()     // Catch:{ Exception -> 0x0042 }
        L_0x0041:
            return r1
        L_0x0042:
            r3 = move-exception
            java.lang.Exception r0 = new java.lang.Exception
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.external.launcher.QuickViewItemLoader.loadMirroringItem():com.samsung.android.gallery.module.data.MediaItem");
    }

    public MediaItem loadQuickViewItem(int i2) {
        Cursor query = DbCompat.query(new QueryParams(DbKey.ALL_PICTURES).addUri(this.mLaunchIntent.getUriData().toString()));
        if (query != null) {
            try {
                if (query.getCount() != 0) {
                    MediaItem load = MediaItemLoader.load(query, 0);
                    if (load.getGroupMediaId() > 0) {
                        load = getBestItem(load);
                    }
                    if (i2 > 0) {
                        VideoPropData.of(load).videoResumePos = Integer.valueOf(i2);
                    }
                    query.close();
                    return load;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        return null;
        throw th;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.samsung.android.gallery.database.dal.abstraction.query.QueryParams} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean loadQuickViewItemsByQueryParams(java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem> r5) {
        /*
            r4 = this;
            boolean r0 = com.samsung.android.gallery.support.utils.PocFeatures.TBD.OPEN_IN_OTHER_WINDOW
            if (r0 == 0) goto L_0x0064
            com.samsung.android.gallery.module.abstraction.LaunchIntent r0 = r4.mLaunchIntent
            java.lang.String r1 = "QueryParams"
            r2 = 0
            java.lang.Object r0 = r0.getExtra(r1, r2)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L_0x001c
            com.samsung.android.gallery.support.blackboard.Blackboard r1 = com.samsung.android.gallery.support.blackboard.Blackboard.getApplicationInstance()
            java.lang.Object r0 = r1.read(r0)
            r2 = r0
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r2 = (com.samsung.android.gallery.database.dal.abstraction.query.QueryParams) r2
        L_0x001c:
            if (r2 == 0) goto L_0x0064
            long r0 = java.lang.System.currentTimeMillis()
            android.database.Cursor r2 = com.samsung.android.gallery.database.dal.DbCompat.query((com.samsung.android.gallery.database.dal.abstraction.query.QueryParams) r2)
            if (r2 == 0) goto L_0x0046
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x003c }
            if (r3 == 0) goto L_0x0046
        L_0x002e:
            com.samsung.android.gallery.module.data.MediaItem r3 = com.samsung.android.gallery.module.data.MediaItemLoader.load(r2)     // Catch:{ all -> 0x003c }
            r5.add(r3)     // Catch:{ all -> 0x003c }
            boolean r3 = r2.moveToNext()     // Catch:{ all -> 0x003c }
            if (r3 != 0) goto L_0x002e
            goto L_0x0046
        L_0x003c:
            r4 = move-exception
            r2.close()     // Catch:{ all -> 0x0041 }
            goto L_0x0045
        L_0x0041:
            r5 = move-exception
            r4.addSuppressed(r5)
        L_0x0045:
            throw r4
        L_0x0046:
            if (r2 == 0) goto L_0x004b
            r2.close()
        L_0x004b:
            java.lang.String r4 = r4.TAG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r2 = "loadQuickViewItemsByQueryParams"
            r5.<init>(r2)
            java.lang.String r0 = com.samsung.android.gallery.support.utils.Logger.vt((long) r0)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.samsung.android.gallery.support.utils.Log.d(r4, r5)
            r4 = 1
            return r4
        L_0x0064:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.external.launcher.QuickViewItemLoader.loadQuickViewItemsByQueryParams(java.util.ArrayList):boolean");
    }

    public MediaItem loadQuickViewMt2(Cursor cursor, String str) {
        int resumePos;
        MediaItem load = MediaItemLoader.load(cursor, 0);
        if (load.getGroupMediaId() > 0) {
            load = getBestItem(load);
        }
        if (load.isVideo() && (resumePos = this.mLaunchIntent.getResumePos(str)) > 0) {
            VideoPropData.of(load).videoResumePos = Integer.valueOf(resumePos);
        }
        return load;
    }

    public MediaItem loadQuickViewUriItem(Uri uri, ArrayList<MediaItem> arrayList) {
        MediaItem mediaItem;
        try {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(uri);
            if (UriItemLoader.loadMediaItemFromUris(arrayList2, arrayList)) {
                if (arrayList.size() > 0) {
                    mediaItem = arrayList.get(0);
                } else {
                    mediaItem = null;
                }
                setDataType(mediaItem);
                return mediaItem;
            }
        } catch (SecurityException e) {
            Log.e(this.TAG, "loadMediaItemFromUri e=" + e.getMessage());
        }
        return null;
    }

    public boolean loadQuickViewUriItems(boolean z, ArrayList<MediaItem> arrayList, ArrayList<Uri> arrayList2) {
        MediaItem mediaItem;
        if (z) {
            return UriItemLoader.loadMediaItemFromUrisWithGroup(arrayList2, arrayList);
        }
        if (!UriItemLoader.loadMediaItemFromUris(arrayList2, arrayList)) {
            return false;
        }
        if (arrayList.size() == 1) {
            mediaItem = arrayList.get(0);
        } else {
            mediaItem = null;
        }
        setDataType(mediaItem);
        return true;
    }

    public void setLaunchIntent(LaunchIntent launchIntent) {
        this.mLaunchIntent = launchIntent;
    }
}
