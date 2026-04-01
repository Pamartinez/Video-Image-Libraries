package com.samsung.android.gallery.module.album;

import A.a;
import android.content.ContentValues;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.local.LocalAlbumsApi;
import com.samsung.android.gallery.support.utils.FileUtils;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumContentBuilder {
    final ContentValues cv = new ContentValues();

    public ContentValues build() {
        return this.cv;
    }

    public AlbumContentBuilder load(int i2) {
        Cursor albumCursor;
        try {
            albumCursor = new LocalAlbumsApi().getAlbumCursor(true, List.of(Integer.valueOf(i2)));
            if (albumCursor != null) {
                if (albumCursor.moveToFirst()) {
                    loadAndSet(albumCursor, "folder_id", 0);
                    loadAndSet(albumCursor, "folder_name", (Object) null);
                    loadAndSet(albumCursor, "default_cover_path", (Object) null);
                    loadAndSet(albumCursor, "cover_path", (Object) null);
                    loadAndSet(albumCursor, "cover_rect", (Object) null);
                    loadAndSet(albumCursor, "album_order", 0L);
                    loadAndSet(albumCursor, "__albumType", 0);
                    loadAndSet(albumCursor, "__albumLevel", 0);
                }
            }
            if (albumCursor != null) {
                albumCursor.close();
            }
            return this;
        } catch (Exception e) {
            a.s(e, new StringBuilder("load failed. e="), "AlbumContentBuilder");
            return this;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public <T> void loadAndSet(Cursor cursor, String str, T t) {
        try {
            int columnIndex = cursor.getColumnIndex(str);
            if (columnIndex >= 0) {
                if (t != null) {
                    if (!(t instanceof String)) {
                        if (t instanceof Integer) {
                            int intValue = ((Integer) t).intValue();
                            int i2 = cursor.getInt(columnIndex);
                            if (i2 != intValue) {
                                this.cv.put(str, Integer.valueOf(i2));
                                return;
                            }
                            return;
                        } else if (t instanceof Long) {
                            long longValue = ((Long) t).longValue();
                            long j2 = cursor.getLong(columnIndex);
                            if (j2 != longValue) {
                                this.cv.put(str, Long.valueOf(j2));
                                return;
                            }
                            return;
                        } else {
                            return;
                        }
                    }
                }
                String string = cursor.getString(columnIndex);
                if (string != null) {
                    this.cv.put(str, string);
                }
            }
        } catch (Error | Exception unused) {
        }
    }

    public AlbumContentBuilder setPath(String str) {
        this.cv.put("__absPath", str);
        this.cv.put("__bucketID", Integer.valueOf(FileUtils.getBucketId(str)));
        this.cv.put("__Title", FileUtils.getBucketNameFromPath(str));
        this.cv.put("__volumeName", FileUtils.getVolumeName(str));
        return this;
    }
}
