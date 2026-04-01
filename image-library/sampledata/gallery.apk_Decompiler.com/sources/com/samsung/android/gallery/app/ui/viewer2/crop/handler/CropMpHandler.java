package com.samsung.android.gallery.app.ui.viewer2.crop.handler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CropMpHandler extends CropUriHandler {
    public CropMpHandler(Intent intent, MediaItem mediaItem) {
        super(intent, mediaItem);
    }

    private String createUniqueFilename(String str, String str2, String str3) {
        for (int i2 = 1; i2 < 1000; i2++) {
            SecureFile secureFile = new SecureFile(str, str2 + "-" + i2 + "." + str3);
            if (!secureFile.exists()) {
                return secureFile.getPath();
            }
        }
        return null;
    }

    private Uri saveGenericImage(Bitmap bitmap) {
        long currentTimeMillis = System.currentTimeMillis();
        String createUniqueFilename = createUniqueFilename(StorageInfo.getDefault().download, "IMG_" + TimeUtil.getFileTimestamp(currentTimeMillis), getOutputExtension());
        if (createUniqueFilename == null) {
            return null;
        }
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            return saveMediaFile(bitmap, createUniqueFilename);
        }
        File saveMedia = saveMedia(bitmap, createUniqueFilename);
        if (saveMedia == null) {
            return null;
        }
        return new FilesApi().insertImage(bitmap, saveMedia, currentTimeMillis, MapUtil.INVALID_LOCATION, MapUtil.INVALID_LOCATION, 0, getOutputMimeType().toString());
    }

    private Uri saveLocalImage(Bitmap bitmap) {
        String createUniqueFilename = createUniqueFilename(StorageInfo.getDefault().camera, FileUtils.getBaseName(this.mMediaItem.getPath()), getOutputExtension());
        if (createUniqueFilename == null) {
            return null;
        }
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            return saveMediaFile(bitmap, createUniqueFilename);
        }
        File saveMedia = saveMedia(bitmap, createUniqueFilename);
        if (saveMedia == null) {
            return null;
        }
        return new FilesApi().insertImage(bitmap, saveMedia, this.mMediaItem.getDateTaken(), this.mMediaItem.getLatitude(), this.mMediaItem.getLongitude(), 0, getOutputMimeType().toString());
    }

    private File saveMedia(Bitmap bitmap, String str) {
        FileOutputStream fileOutputStream;
        File createNewFile = FileUtils.createNewFile(str);
        if (createNewFile == null || !createNewFile.exists() || !createNewFile.isFile()) {
            String str2 = this.TAG;
            Log.e(str2, "saveMedia fails to create file " + Logger.getEncodedString(str));
            return null;
        }
        try {
            createNewFile.setReadable(true, false);
            createNewFile.setWritable(true, false);
            try {
                fileOutputStream = new FileOutputStream(createNewFile);
                saveBitmapToOutputStream(bitmap, getOutputCompressFormat(), fileOutputStream);
                fileOutputStream.close();
                return createNewFile;
            } catch (IOException e) {
                String str3 = this.TAG;
                Log.e(str3, "fail to save image: " + createNewFile.getAbsolutePath() + " e=" + e);
                createNewFile.delete();
                return null;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } catch (SecurityException e7) {
            String str4 = this.TAG;
            Log.e(str4, "saveMedia failed e=" + e7.getMessage());
            createNewFile.delete();
            return null;
        }
        throw th;
    }

    private Uri saveMediaFile(Bitmap bitmap, String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            saveBitmapToOutputStream(bitmap, getOutputCompressFormat(), byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return new FilesApi().saveMediaFile(str, byteArray);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private Uri saveToMediaProvider(Bitmap bitmap) {
        if (this.mMediaItem.isLocal()) {
            return saveLocalImage(bitmap);
        }
        return saveGenericImage(bitmap);
    }

    public Intent buildIntent() {
        if (this.mOutputUri == null) {
            return null;
        }
        Intent intent = new Intent();
        intent.setData(this.mOutputUri);
        return intent;
    }

    public boolean process(Rect rect) {
        Bitmap croppedImage = getCroppedImage(rect, this.mIntent.getExtras());
        if (croppedImage != null) {
            this.mOutputUri = saveToMediaProvider(croppedImage);
        }
        if (this.mOutputUri != null) {
            return true;
        }
        return false;
    }

    public void recycle() {
    }
}
