package com.samsung.android.gallery.widget.animations.photostacking;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageInfo {
    final Bitmap bitmap;
    final FileItemInterface item;
    final Matrix matrix;

    public ImageInfo(Bitmap bitmap2, FileItemInterface fileItemInterface, Matrix matrix2) {
        this.bitmap = bitmap2;
        this.item = fileItemInterface;
        this.matrix = matrix2;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public FileItemInterface getItem() {
        return this.item;
    }

    public Matrix getMatrix() {
        return this.matrix;
    }

    public ImageInfo(Bitmap bitmap2, FileItemInterface fileItemInterface) {
        this.bitmap = bitmap2;
        this.item = fileItemInterface;
        this.matrix = null;
    }
}
