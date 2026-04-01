package com.samsung.android.gallery.module.idleworker.jobs;

import A.a;
import android.content.Context;
import android.util.Pair;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteCacheJob extends IdleWorkerJob {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DiskCacheCleaner {
    }

    public DeleteCacheJob(int i2, IdleWorkerJob.Type type) {
        super(i2, type);
    }

    private void deleteOldGalleryCaches(Context context) {
        Pair pair = new Pair[]{new Pair(PreferenceName.CLEAR_SEP11_CACHE.key(), new a(this))}[0];
        if (GalleryPreference.getInstance().loadInt((String) pair.first, 0) != 1) {
            ((a) ((DiskCacheCleaner) pair.second)).f3027a.removeGalleryCacheSep11(context);
            GalleryPreference.getInstance().saveState((String) pair.first, 1);
        }
    }

    /* access modifiers changed from: private */
    public void removeGalleryCacheSep11(Context context) {
        if (context == null) {
            Log.e(this.TAG, "removeGalleryCacheSep11 failed. null context");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            Log.e(this.TAG, "removeGalleryCacheSep11 failed. null cache dir");
            return;
        }
        File[] listFiles = externalCacheDir.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            Log.e(this.TAG, "removeGalleryCacheSep11 failed. no dir");
            return;
        }
        for (File file : listFiles) {
            if (file != null && file.exists()) {
                if (file.isDirectory()) {
                    FileUtils.deleteFilesInDir(file);
                }
                file.delete();
            }
        }
        a.x(new StringBuilder("removeGalleryCacheSep11 finished +"), currentTimeMillis, this.TAG);
    }

    public void execute(Context context) {
        try {
            deleteOldGalleryCaches(context);
        } catch (Exception e) {
            a.s(e, new StringBuilder("delete old cache failed. e="), this.TAG);
        }
    }
}
