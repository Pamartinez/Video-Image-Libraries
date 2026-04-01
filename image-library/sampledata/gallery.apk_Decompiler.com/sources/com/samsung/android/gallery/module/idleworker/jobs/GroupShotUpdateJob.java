package com.samsung.android.gallery.module.idleworker.jobs;

import A.a;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.local.ReferenceDatabaseManager;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefFileCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupShotUpdateJob extends IdleWorkerJob {
    private static final String BEST_IMAGE_VALUE = null;
    private final boolean USE_SEC_MP = SdkConfig.atLeast(SdkConfig.GED.R);
    private ReferenceDatabaseManager mReferenceManager;
    protected SefFileCompat mSefFileHandler;

    public GroupShotUpdateJob(int i2, IdleWorkerJob.Type type) {
        super(i2, type);
    }

    private String getWhere(int i2) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        for (int i7 = 0; i7 < i2; i7++) {
            stringJoiner.add("?");
        }
        return "_id IN (" + stringJoiner.toString() + ")";
    }

    private void updateDB(ArrayList<String> arrayList, int i2) {
        Uri uri;
        if (!arrayList.isEmpty()) {
            int size = arrayList.size();
            ContentValues contentValues = new ContentValues();
            contentValues.put("best_image", BEST_IMAGE_VALUE);
            ReferenceDatabaseManager referenceDatabaseManager = this.mReferenceManager;
            if (this.USE_SEC_MP) {
                uri = MediaUri.getInstance().getSecMediaUri();
            } else {
                uri = MediaUri.getInstance().getFilesUri();
            }
            referenceDatabaseManager.update(uri, contentValues, getWhere(size), (String[]) arrayList.toArray(new String[0]));
        }
    }

    private void updateGroupShot() {
        Cursor rawQuery;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery("select burst_group_id, group_type, bucket_id,sum(best_image) as sum_best from files where group_type in (1,3) group by burst_group_id, bucket_id having sum_best>1", "wrongGroup");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    int count = rawQuery.getCount();
                    do {
                        long j2 = rawQuery.getLong(0);
                        int i2 = rawQuery.getInt(1);
                        int i7 = rawQuery.getInt(2);
                        rawQuery.getInt(3);
                        if (i2 == 1) {
                            updateGroupShotInternal(i7, j2, SefInfo.BURST_SHOT_BEST_PHOTO_INFO.keyName);
                        } else if (i2 == 3) {
                            updateGroupShotInternal(i7, j2, SefInfo.SINGLE_TAKE_BEST_PHOTO_INFO.keyName);
                        }
                    } while (rawQuery.moveToNext());
                    String str = this.TAG;
                    Log.d(str, "updateGroupShot" + Logger.vt(Integer.valueOf(count), Long.valueOf(currentTimeMillis)));
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
                return;
            }
            return;
        } catch (Exception e) {
            a.s(e, new StringBuilder("update group shot failed. e="), this.TAG);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void updateGroupShotInternal(int i2, long j2, String str) {
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        try {
            cursor = getCursor(i2, j2, str);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemBuilder.createSimple(cursor));
                    } while (cursor.moveToNext());
                }
            }
            removeBestPhotoInfo(getRestExceptBest(arrayList, str), str);
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        } catch (Error | Exception unused) {
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void execute(Context context) {
        if (Features.isEnabled(Features.SUPPORT_CHANGE_BEST_ITEM)) {
            this.mReferenceManager = new ReferenceDatabaseManager(context.getContentResolver());
            this.mSefFileHandler = SeApiCompat.getSefFileCompat();
            updateGroupShot();
        }
    }

    public Cursor getCursor(int i2, long j2, String str) {
        if (SefInfo.BURST_SHOT_BEST_PHOTO_INFO.equals(str)) {
            return DbCompat.query(DbKey.FILES_BURSTSHOT, new ha.a(i2, j2, 1));
        }
        if (SefInfo.SINGLE_TAKE_BEST_PHOTO_INFO.equals(str)) {
            return DbCompat.query(DbKey.FILES_SINGLETAKE, new ha.a(i2, j2, 2));
        }
        return null;
    }

    public ArrayList<MediaItem> getRestExceptBest(ArrayList<MediaItem> arrayList, String str) {
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        Iterator<MediaItem> it = arrayList.iterator();
        boolean z = false;
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (next.getBestImage() == 1) {
                if (!z) {
                    z = true;
                } else {
                    arrayList2.add(next);
                }
            }
        }
        return arrayList2;
    }

    public void removeBestPhotoInfo(ArrayList<MediaItem> arrayList, String str) {
        if (!arrayList.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<MediaItem> it = arrayList.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                MediaItem next = it.next();
                try {
                    if (this.USE_SEC_MP) {
                        arrayList2.add(String.valueOf(next.getFileId()));
                    } else {
                        arrayList2.add(String.valueOf(next.getMediaId()));
                    }
                    String path = next.getPath();
                    if (path != null) {
                        SecureFile secureFile = new SecureFile(path);
                        if (this.mSefFileHandler.hasData((File) secureFile, str)) {
                            i2 += this.mSefFileHandler.deleteData(secureFile, str) ? 1 : 0;
                        }
                    }
                } catch (Error | Exception unused) {
                }
            }
            updateDB(arrayList2, i2);
        }
    }
}
