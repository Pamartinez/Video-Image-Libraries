package com.samsung.android.gallery.module.clip.objectcapture;

import A.a;
import B5.c;
import D8.b;
import F9.e;
import M4.d;
import N8.C0574a;
import N8.C0575b;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.PersistableBundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.c2pa.C2paWrapper;
import com.samsung.android.gallery.module.clip.IClipInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.fileio.redact.FileRedactorBuilder;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectCaptureFileHandler {
    private final Blackboard mBlackboard;
    private final IClipInfo mClipInfo;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DataHolder {
        static final String STICKER_PATH_PREFIX;

        static {
            String str;
            if (Features.isEnabled(Features.IS_REPAIR_MODE)) {
                str = "/data/sec_maintenance/gppm/sticker/MySticker_";
            } else {
                str = "/data/sec/gppm/sticker/MySticker_";
            }
            STICKER_PATH_PREFIX = str;
        }
    }

    public ObjectCaptureFileHandler(IClipInfo iClipInfo) {
        this.mClipInfo = iClipInfo;
        this.mBlackboard = iClipInfo.getBlackboard();
    }

    public static void clearTempFiles(Context context) {
        if (context != null) {
            deletePreviousFiles(new SecureFile(context.getFilesDir(), "captured"));
        }
    }

    private static void deletePreviousFiles(File file) {
        if (file.exists() && file.isDirectory()) {
            Optional.ofNullable(file.listFiles()).ifPresent(new d(23));
        }
    }

    private PersistableBundle getClipDataExtra(String str, MediaItem mediaItem, boolean z) {
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putBoolean("gallery_object_capture", true);
        persistableBundle.putString("captured_file_path", str);
        persistableBundle.putInt("captured_position", this.mClipInfo.getPosition());
        if (isSaveCaptureInfoSupported(mediaItem)) {
            persistableBundle.putString("capture_info_path", mediaItem.getPath());
        }
        if (!z) {
            persistableBundle.putBoolean("com.samsung.android.content.clipdescription.extra.IGNORE_LEFT_EDGE", true);
            persistableBundle.putBoolean("com.samsung.android.content.clipdescription.extra.IGNORE_RIGHT_EDGE", true);
        }
        return persistableBundle;
    }

    private String getFileName(String str, String str2, String str3) {
        return new FileNameBuilder(str2).setDirectoryPath(str).setExtension(str3).buildUnique();
    }

    private void handleCopy(Context context, int i2, MediaItem mediaItem, Bitmap bitmap) {
        DeepSkyHelper.post(new C0574a(this, context, mediaItem, bitmap, i2, 0));
    }

    private void handleEdit(Context context, int i2, MediaItem mediaItem, Bitmap bitmap) {
        DeepSkyHelper.post(new C0574a(this, context, mediaItem, bitmap, i2, 2));
    }

    private void handleErase(int i2, MediaItem mediaItem) {
        String str;
        if (mediaItem == null || TextUtils.isEmpty(mediaItem.getPath())) {
            Log.e("ObjectCaptureFileHandler", "origin item or path is null");
            return;
        }
        boolean isMotionPhotoVideoMode = this.mClipInfo.isMotionPhotoVideoMode();
        String path = mediaItem.getPath();
        String parent = FileUtils.getParent(path);
        if (isMotionPhotoVideoMode) {
            str = "jpg";
        } else {
            str = FileUtils.getExtension(path);
        }
        this.mBlackboard.postEvent(EventMessage.obtain(3009, i2, new ObjectCaptureEraseInfo(false, isMotionPhotoVideoMode, this.mClipInfo.getCurrentFrame(), path, getFileName(parent, path, str), getFileName(parent, path, IFormat.FORMAT_MP4))));
    }

    private void handleSaveAsGif(int i2, Consumer<Object> consumer) {
        boolean z;
        if (i2 == 6) {
            z = true;
        } else {
            z = false;
        }
        consumer.accept(Boolean.valueOf(z));
    }

    private void handleSaveAsImage(Context context, int i2, MediaItem mediaItem, Bitmap bitmap) {
        int i7 = i2;
        DeepSkyHelper.post(new C0574a(i7, context, bitmap, this, mediaItem));
    }

    private void handleShare(Context context, int i2, MediaItem mediaItem, Bitmap bitmap) {
        DeepSkyHelper.post(new C0574a(this, context, mediaItem, bitmap, i2, 3));
    }

    private boolean isSaveCaptureInfoSupported(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isCloudOnly() || mediaItem.isSharing() || TextUtils.isEmpty(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$deletePreviousFiles$2(File file) {
        if (!file.isDirectory()) {
            file.delete();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getClipData$1(File file, Bitmap bitmap, File file2, MediaItem mediaItem) {
        deletePreviousFiles(file);
        saveBitmapToFile(bitmap, file2);
        MediaItem mediaItem2 = new MediaItem();
        mediaItem2.setPath(file2.getAbsolutePath());
        mediaItem2.setMimeType(MimeType.getMimeType(file2.getAbsolutePath()).mimeType);
        runC2PA(mediaItem, mediaItem2, new d(25));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleCopy$4(int i2, MediaItem mediaItem, long j2, Boolean bool) {
        this.mBlackboard.postEvent(EventMessage.obtain(3009, i2, mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleCopy$5(Context context, MediaItem mediaItem, Bitmap bitmap, int i2) {
        SecureFile secureFile = new SecureFile(context.getFilesDir(), "captured/copy");
        deletePreviousFiles(secureFile);
        File saveBitmapWithCreateDir = saveBitmapWithCreateDir(mediaItem, bitmap, secureFile);
        if (saveBitmapWithCreateDir != null) {
            MediaItem createUriItem = UriItemLoader.createUriItem(context, saveBitmapWithCreateDir);
            MediaItem clone = createUriItem.clone();
            clone.setPath(saveBitmapWithCreateDir.getAbsolutePath());
            runC2PA(mediaItem, clone, new C0575b(this, i2, createUriItem, 1));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEdit$6(Context context, MediaItem mediaItem, Bitmap bitmap, int i2) {
        String str;
        File saveBitmap = saveBitmap(context, mediaItem, bitmap);
        if (saveBitmap != null) {
            MediaItem createUriItem = UriItemLoader.createUriItem(context, saveBitmap);
            ExtrasID extrasID = ExtrasID.ORIGIN_PATH;
            if (isSaveCaptureInfoSupported(mediaItem)) {
                str = mediaItem.getPath();
            } else {
                str = "";
            }
            createUriItem.setExtra(extrasID, str);
            this.mBlackboard.postEvent(EventMessage.obtain(3009, i2, createUriItem));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSaveAsImage$9(MediaItem mediaItem, Bitmap bitmap, Context context, int i2) {
        File saveBitmapWithCreateDir = saveBitmapWithCreateDir(mediaItem, bitmap, new SecureFile(StorageInfo.getDefault().clippedImages));
        if (saveBitmapWithCreateDir != null) {
            MediaItem createUriItem = UriItemLoader.createUriItem(context, saveBitmapWithCreateDir);
            createUriItem.setPath(saveBitmapWithCreateDir.getAbsolutePath());
            runC2PA(mediaItem, createUriItem, new d(26), new b(this, i2, mediaItem));
            return;
        }
        this.mBlackboard.postEvent(EventMessage.obtain(3009, i2, (Object) null));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleShare$10(int i2, MediaItem mediaItem, long j2, Boolean bool) {
        this.mBlackboard.postEvent(EventMessage.obtain(3009, i2, mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleShare$11(Context context, MediaItem mediaItem, Bitmap bitmap, int i2) {
        File saveBitmap = saveBitmap(context, mediaItem, bitmap);
        if (saveBitmap != null) {
            MediaItem createUriItem = UriItemLoader.createUriItem(context, saveBitmap);
            MediaItem clone = createUriItem.clone();
            clone.setPath(saveBitmap.getAbsolutePath());
            runC2PA(mediaItem, clone, new C0575b(this, i2, createUriItem, 0));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSaveFileScanDone$12(MediaItem mediaItem, Uri uri, int i2) {
        saveCaptureInfo(mediaItem, uri);
        this.mBlackboard.postEvent(EventMessage.obtain(3009, i2, uri));
    }

    /* access modifiers changed from: private */
    /* renamed from: onSaveFileScanDone */
    public void lambda$handleSaveAsImage$8(int i2, MediaItem mediaItem, String str, Uri uri) {
        Log.d("ObjectCaptureFileHandler", "saved file scan complete " + Logger.v(Logger.getEncodedString(str), Logger.getEncodedString((Object) uri)));
        if (this.mClipInfo.getContext() != null) {
            DeepSkyHelper.postDelayed(new e((Object) this, (Object) mediaItem, (Object) uri, i2, 1), 300);
            return;
        }
        this.mBlackboard.postEvent(EventMessage.obtain(3009, i2, (Object) null));
    }

    private void runC2PA(MediaItem mediaItem, MediaItem mediaItem2, Consumer<Boolean> consumer) {
        FileRedactorBuilder.create((FileItemInterface) mediaItem2, (FileItemInterface) mediaItem).setUseFileDescriptor(true).setCallback(consumer).setManifest(C2paWrapper.Manifest.actionEdit).build().run();
    }

    private File saveBitmap(Context context, MediaItem mediaItem, Bitmap bitmap) {
        SecureFile secureFile = new SecureFile(context.getFilesDir(), "captured");
        deletePreviousFiles(secureFile);
        return saveBitmapWithCreateDir(mediaItem, bitmap, secureFile);
    }

    private File saveBitmapToFile(Bitmap bitmap, File file) {
        FileOutputStream fileOutputStream;
        long currentTimeMillis = System.currentTimeMillis();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            MediaItem mediaItem = this.mClipInfo.getMediaItem();
            String absolutePath = file.getAbsolutePath();
            if (mediaItem != null) {
                currentTimeMillis = mediaItem.getDateTaken();
            }
            updateDateTime(absolutePath, currentTimeMillis);
            fileOutputStream.close();
            byteArrayOutputStream.close();
            return file;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Error | Exception e) {
                Log.e("ObjectCaptureFileHandler", "can not make temp file, e=" + e.getMessage());
                return null;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
        throw th;
    }

    private File saveBitmapWithCreateDir(MediaItem mediaItem, Bitmap bitmap, File file) {
        if (mediaItem == null) {
            Log.e("ObjectCaptureFileHandler", "origin item is null");
            return null;
        } else if (file.exists() || file.mkdirs()) {
            return saveBitmapToFile(bitmap, new SecureFile(getFileName(file.getAbsolutePath(), mediaItem.getPath(), "png")));
        } else {
            Log.e((CharSequence) "ObjectCaptureFileHandler", "can not make directory", Logger.getEncodedString(file.getAbsolutePath()));
            return null;
        }
    }

    private void saveCaptureInfo(MediaItem mediaItem, Uri uri) {
        if (!isSaveCaptureInfoSupported(mediaItem)) {
            Log.e("ObjectCaptureFileHandler", "save capture info failed, invalid item");
            return;
        }
        try {
            String lastPathSegment = uri.getLastPathSegment();
            if (lastPathSegment == null) {
                Log.e("ObjectCaptureFileHandler", "save capture info failed, invalid media id");
                return;
            }
            if (!new TagEditApi().updateCapturedFilePath(Long.parseLong(lastPathSegment), mediaItem.getPath())) {
                Log.e("ObjectCaptureFileHandler", "save capture info to db failed");
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("save capture info failed, e="), "ObjectCaptureFileHandler");
        }
    }

    private void updateDateTime(String str, long j2) {
        ExifUtils.changeDateTime(str, j2);
    }

    public ClipData getClipData(MediaItem mediaItem, Bitmap bitmap) {
        Activity readActivity = BlackboardUtils.readActivity(this.mBlackboard);
        if (readActivity == null) {
            Log.e("ObjectCaptureFileHandler", "failed to get clip data, null context");
            return null;
        } else if (mediaItem == null) {
            Log.e("ObjectCaptureFileHandler", "origin item is null");
            return null;
        } else {
            SecureFile secureFile = new SecureFile(readActivity.getFilesDir(), "captured/drag");
            if (secureFile.exists() || secureFile.mkdirs()) {
                SecureFile secureFile2 = new SecureFile(getFileName(secureFile.getAbsolutePath(), mediaItem.getPath(), "png"));
                try {
                    ClipData newUri = ClipData.newUri(readActivity.getContentResolver(), "galleryUri", FileProviderUtil.getUri((Context) readActivity, (File) secureFile2));
                    newUri.getDescription().setExtras(getClipDataExtra(secureFile2.getAbsolutePath(), mediaItem, readActivity.isInMultiWindowMode()));
                    Blackboard.getApplicationInstance().publish("data://user/CapturedFileWriting", Boolean.TRUE);
                    DeepSkyHelper.post(new c(this, secureFile, bitmap, secureFile2, mediaItem, 5));
                    return newUri;
                } catch (Throwable th) {
                    Bitmap bitmap2 = bitmap;
                    Throwable th2 = th;
                    Blackboard.getApplicationInstance().publish("data://user/CapturedFileWriting", Boolean.TRUE);
                    DeepSkyHelper.post(new c(this, secureFile, bitmap2, secureFile2, mediaItem, 5));
                    throw th2;
                }
            } else {
                Log.e((CharSequence) "ObjectCaptureFileHandler", "can not make directory", Logger.getEncodedString(secureFile.getAbsolutePath()));
                return null;
            }
        }
    }

    public String getGifTargetPath(MediaItem mediaItem, boolean z) {
        if (!z) {
            return DataHolder.STICKER_PATH_PREFIX + System.currentTimeMillis() + ".gif";
        }
        SecureFile secureFile = new SecureFile(StorageInfo.getDefault().clippedImages);
        if (secureFile.exists() || secureFile.mkdirs()) {
            return getFileName(secureFile.getAbsolutePath(), mediaItem.getPath(), "gif");
        }
        Log.e((CharSequence) "ObjectCaptureFileHandler", "can not make directory", Logger.getEncodedString(secureFile.getAbsolutePath()));
        return null;
    }

    public void handleMenu(int i2, MediaItem mediaItem, Bitmap bitmap, Consumer<Object> consumer) {
        Context context = this.mClipInfo.getContext();
        if (context == null) {
            Log.e("ObjectCaptureFileHandler", "failed to handle menu, null context");
            return;
        }
        Log.d("ObjectCaptureFileHandler", "handle menu {" + i2 + "}");
        if (i2 == 0) {
            handleCopy(context, i2, mediaItem, bitmap);
        } else if (i2 == 1) {
            handleShare(context, i2, mediaItem, bitmap);
        } else if (i2 == 2) {
            handleSaveAsImage(context, i2, mediaItem, bitmap);
        } else if (i2 == 4) {
            handleEdit(context, i2, mediaItem, bitmap);
        } else if (i2 == 3 || i2 == 6) {
            handleSaveAsGif(i2, consumer);
        } else if (i2 == 100) {
            handleErase(i2, mediaItem);
        }
    }

    private void runC2PA(MediaItem mediaItem, MediaItem mediaItem2, Consumer<Boolean> consumer, BiConsumer<String, Uri> biConsumer) {
        FileRedactorBuilder.create((FileItemInterface) mediaItem2, (FileItemInterface) mediaItem).setUseFileDescriptor(false).setCallback(consumer).setManifest(C2paWrapper.Manifest.actionEdit).withMediaScan(biConsumer).build().run();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleSaveAsImage$7(long j2, Boolean bool) {
    }
}
