package com.samsung.android.gallery.module.album;

import A9.c;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SecureFile;
import java.io.File;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveAlbumCoverTask extends AsyncTask<Void, Void, Boolean> {
    private final WeakReference<Context> mContext;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SaveAlbumCoverJob extends IdleWorkerJob {
        public SaveAlbumCoverJob(int i2, IdleWorkerJob.Type type) {
            super(i2, type);
        }

        public void execute(Context context) {
            new SaveAlbumCoverTask(context).doInBackground(new Void[0]);
            GalleryPreference.getInstance().saveState(PreferenceName.SAVE_ALBUM_COVER, false);
        }
    }

    public SaveAlbumCoverTask(Context context) {
        this.mContext = new WeakReference<>(context);
        Log.d("SaveAlbumCoverTask", "SaveAlbumCoverTask executed.");
    }

    private Cursor getAlbumCoverCursor(ContentResolver contentResolver) {
        return contentResolver.query(LocalDatabase.ALBUM_URI, new String[]{"__bucketID", "cover_path", "default_cover_path", "cover_rect"}, "cover_path is not null", (String[]) null, (String) null);
    }

    public boolean isValidCoverPath(Context context, String str) {
        File externalFilesDir;
        if (TextUtils.isEmpty(str) || !new SecureFile(str).exists() || (externalFilesDir = context.getExternalFilesDir(".album")) == null) {
            return false;
        }
        return !str.startsWith(externalFilesDir.getAbsolutePath());
    }

    public Boolean doInBackground(Void... voidArr) {
        Cursor albumCoverCursor;
        Context context = this.mContext.get();
        if (context == null) {
            return Boolean.FALSE;
        }
        try {
            albumCoverCursor = getAlbumCoverCursor(context.getContentResolver());
            long currentTimeMillis = System.currentTimeMillis();
            if (albumCoverCursor != null && albumCoverCursor.moveToFirst()) {
                do {
                    int i2 = albumCoverCursor.getInt(albumCoverCursor.getColumnIndex("__bucketID"));
                    String string = albumCoverCursor.getString(albumCoverCursor.getColumnIndex("cover_path"));
                    String string2 = albumCoverCursor.getString(albumCoverCursor.getColumnIndex("cover_rect"));
                    if (isValidCoverPath(context, string)) {
                        String[] saveCoverFile = AlbumHelper.getInstance().saveCoverFile(context, i2, string);
                        AlbumHelper.getInstance().updateAlbumCoverDB(i2, saveCoverFile[0], AlbumHelper.getInstance().replaceCoverRect(saveCoverFile[2], saveCoverFile[3], string2));
                    }
                } while (albumCoverCursor.moveToNext());
                Log.d("SaveAlbumCoverTask", "save album cover[count, elapsed] {" + albumCoverCursor.getCount() + ArcCommonLog.TAG_COMMA + (System.currentTimeMillis() - currentTimeMillis) + "}");
            }
            Boolean bool = Boolean.TRUE;
            if (albumCoverCursor == null) {
                return bool;
            }
            albumCoverCursor.close();
            return bool;
        } catch (Exception e) {
            Log.e("SaveAlbumCoverTask", e.getMessage());
            return Boolean.FALSE;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
        if (bool.booleanValue()) {
            Blackboard.getBlackboardMap().forEach(new c(1));
        }
    }
}
