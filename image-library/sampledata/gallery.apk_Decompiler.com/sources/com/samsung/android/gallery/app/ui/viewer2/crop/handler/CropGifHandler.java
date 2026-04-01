package com.samsung.android.gallery.app.ui.viewer2.crop.handler;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.GifCodec;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CropGifHandler extends CropHandler {
    private Uri mOutputUri;

    public CropGifHandler(Intent intent, MediaItem mediaItem) {
        super(intent, mediaItem);
    }

    private boolean saveCropGif(Uri uri, Uri uri2, Bundle bundle, Rect rect) {
        Throwable th;
        OutputStream openOutputStream;
        Throwable th2;
        if (uri == null || uri2 == null) {
            Log.e(this.TAG, "saveCropGif failed. null uri");
            return false;
        }
        int i2 = bundle.getInt("outputX-gif", 240);
        int i7 = bundle.getInt("outputY-gif", 240);
        long j2 = (long) bundle.getInt("max-file-size", 512000);
        int min = Math.min(rect.width(), i2);
        int min2 = Math.min(rect.height(), i7);
        String externalFilesDir = FileUtils.getExternalFilesDir("tmp");
        String path = new File(externalFilesDir, System.currentTimeMillis() + "-ContactPhoto.gif").getPath();
        if (!GifCodec.crop(uri, path, rect, min, min2, j2)) {
            Log.e(this.TAG, "saveCropGif failed");
            return false;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            try {
                openOutputStream = AppResources.getAppContext().getContentResolver().openOutputStream(uri2);
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    openOutputStream.write(bArr, 0, read);
                }
                if (openOutputStream != null) {
                    openOutputStream.close();
                }
                fileInputStream.close();
                FileUtils.delete(path);
                return true;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream.close();
                throw th;
            }
        } catch (Exception e) {
            try {
                Log.e((CharSequence) this.TAG, "saveCropGif failed", (Throwable) e);
                FileUtils.delete(path);
                return false;
            } catch (Throwable th4) {
                Throwable th5 = th4;
                FileUtils.delete(path);
                throw th5;
            }
        } catch (Throwable th6) {
            th.addSuppressed(th6);
        }
        throw th2;
    }

    public Intent buildIntent() {
        if (this.mOutputUri == null) {
            return null;
        }
        Intent intent = new Intent();
        intent.putExtra("output", this.mOutputUri);
        intent.setDataAndType(this.mOutputUri, "image/gif");
        return intent;
    }

    public boolean process(Rect rect) {
        Uri uri;
        Bundle extras = this.mIntent.getExtras();
        if (extras == null) {
            Log.e(this.TAG, "process failed. no extra");
            return false;
        }
        if (this.mIntent.getData() != null) {
            uri = this.mIntent.getData();
        } else {
            uri = ContentUri.getUri(this.mMediaItem);
        }
        Uri uri2 = (Uri) extras.getParcelable("output");
        if (!saveCropGif(uri, uri2, extras, rect)) {
            Log.e(this.TAG, "process failed");
            return false;
        }
        this.mOutputUri = uri2;
        return true;
    }
}
