package com.samsung.android.gallery.app.ui.viewer2.crop.handler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SecureFile;
import i.C0212a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CropCallerIdHandler extends CropUriHandler {
    public CropCallerIdHandler(Intent intent, MediaItem mediaItem) {
        super(intent, mediaItem);
    }

    private String getCallerIdPath() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(FileUtils.getFilesDir());
        return C0212a.p(sb2, File.separator, "tempCropPicture");
    }

    public void recycle() {
        SecureFile secureFile = new SecureFile(getCallerIdPath());
        if (secureFile.exists()) {
            secureFile.delete();
        }
    }

    public boolean saveBitmapToUri(Bitmap bitmap, Uri uri) {
        FileOutputStream fileOutputStream;
        File createNewFile = FileUtils.createNewFile(getCallerIdPath());
        if (createNewFile == null) {
            Log.e(this.TAG, "saveBitmapToUri invalid file");
            return false;
        }
        try {
            fileOutputStream = new FileOutputStream(createNewFile);
            boolean saveBitmapToOutputStream = saveBitmapToOutputStream(bitmap, Bitmap.CompressFormat.JPEG, fileOutputStream);
            fileOutputStream.close();
            return saveBitmapToOutputStream;
        } catch (IOException e) {
            String str = this.TAG;
            Log.e(str, "saveBitmapToUri failed e=" + e.getMessage());
            createNewFile.delete();
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
