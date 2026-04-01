package com.samsung.android.gallery.module.thumbnail.type;

import android.graphics.RectF;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ThumbnailInterface extends FileItemInterface {
    RectF getCropRect();

    String getDngVersion();

    String getMdeDownloadedPath();

    RectF getRawCropRectRatio();

    String getThumbCacheKey();

    long getVideoThumbStartTime();

    boolean isQuramDng();

    boolean isStories();

    void setFileSize(long j2);

    void setOrientation(int i2);

    void setOrientationTag(int i2);

    void setSize(int i2, int i7);

    void update(ExifInterface exifInterface);
}
