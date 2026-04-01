package com.samsung.android.gallery.app.provider;

import A.a;
import E3.h;
import N2.j;
import X3.b;
import X3.c;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.SharingItemLoader;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeMediaItemHelper;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.scsp.media.file.FileApiContract;
import i.C0212a;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedItemUploader {
    private Bitmap createInstantThumbnail(String str, MediaItem mediaItem) {
        FileInputStream fileInputStream;
        MediaMetadataRetriever mediaMetadataRetriever;
        if (mediaItem.isImage()) {
            return MdeMediaItemHelper.getEditedImageBitmap(str, mediaItem);
        }
        MediaHelper.VideoInfo videoInfo = MetadataManager.getInstance().get(mediaItem);
        if (videoInfo == null) {
            try {
                fileInputStream = new FileInputStream(str);
                mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(fileInputStream.getFD());
                MediaHelper.VideoInfo extractMetadata = MediaHelper.extractMetadata(mediaMetadataRetriever);
                MetadataManager.getInstance().put(mediaItem, extractMetadata);
                mediaMetadataRetriever.close();
                fileInputStream.close();
                videoInfo = extractMetadata;
            } catch (IOException unused) {
                return null;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return MdeMediaItemHelper.getEditedVideoBitmap(str, mediaItem, videoInfo);
        throw th;
        throw th;
    }

    /* access modifiers changed from: private */
    /* renamed from: deleteInstantData */
    public void lambda$run$0(String str, String str2) {
        FileUtils.delete(str);
        FileUtils.delete(str2);
    }

    private String getEditedDirPath(MediaItem mediaItem) {
        String str = FileUtils.getDirectoryFromPath(mediaItem.getPath()) + "edited";
        if (!FileUtils.exists(str)) {
            FileUtils.makeDirectoryIfAbsent(str);
        }
        return str;
    }

    private Pair<String, MediaItem> getEditedSharedItem(MediaItem mediaItem, Bundle bundle) {
        String string = bundle.getString(FileApiContract.Parameter.PATH);
        if (TextUtils.isEmpty(string)) {
            string = MediaItemMde.getHiddenFilePath(mediaItem);
        }
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        setDisplayName(mediaItem, string);
        if (bundle.containsKey("mime_type")) {
            setMimeType(mediaItem, bundle);
        }
        setSize(mediaItem, string);
        mediaItem.setCropRectRatioList((ArrayList) null);
        if (mediaItem.isImage()) {
            setImageMetaData(mediaItem, string);
        } else {
            setVideoMetaData(mediaItem, string);
        }
        return new Pair<>(string, mediaItem);
    }

    private String getInstantThumbnailPath(Bitmap bitmap, MediaItem mediaItem) {
        String editedDirPath = getEditedDirPath(mediaItem);
        String p6 = C0212a.p(C0212a.s(editedDirPath), File.separator, FileUtils.getBaseName(mediaItem.getPath()));
        Optional.ofNullable(bitmap).ifPresent(new h(p6, mediaItem, 1));
        return p6;
    }

    private String getNewName(MediaItem mediaItem, String str) {
        String hiddenFilePath = MediaItemMde.getHiddenFilePath(mediaItem, "");
        String baseName = FileUtils.getBaseName(hiddenFilePath);
        int i2 = 1;
        String extension = FileUtils.getExtension(str, true);
        if (TextUtils.equals(extension, ".")) {
            extension = FileUtils.getExtension(hiddenFilePath, true);
        }
        int length = baseName.length();
        if (length > 4) {
            int i7 = length - 4;
            if (baseName.substring(i7, length).startsWith("_")) {
                int i8 = length - 3;
                if (isValidVersion(baseName.substring(i8, length))) {
                    i2 = 1 + Integer.parseInt(baseName.substring(i8, length));
                    baseName = baseName.substring(0, i7);
                }
            }
        }
        return C0212a.p(C0212a.t(baseName, "_"), getVersion(i2), extension);
    }

    private MediaItem getOriginalSharedItem(String str, String str2) {
        Cursor sharedItemCursor;
        try {
            MdeDatabase mdeDatabase = new MdeDatabase();
            sharedItemCursor = mdeDatabase.getSharedItemCursor(str, "itemId like '" + str2 + "'");
            if (sharedItemCursor != null) {
                if (sharedItemCursor.moveToFirst()) {
                    MediaItem loadItem = SharingItemLoader.loadItem(sharedItemCursor);
                    sharedItemCursor.close();
                    return loadItem;
                }
            }
            if (sharedItemCursor == null) {
                return null;
            }
            sharedItemCursor.close();
            return null;
        } catch (Exception e) {
            Log.e("SharedItemUploader", e.getMessage());
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private String getVersion(int i2) {
        return String.format(Locale.getDefault(), "%03d", new Object[]{Integer.valueOf(i2)});
    }

    private boolean isSefTypeChanged(int i2, String str) {
        try {
            int[] dataTypeArray = SeApiCompat.getSefFileCompat().getDataTypeArray(new File(str));
            if (dataTypeArray == null) {
                return false;
            }
            if (dataTypeArray.length == 0) {
                return false;
            }
            return Arrays.stream(dataTypeArray).noneMatch(new c(i2, 0));
        } catch (IOException unused) {
            Log.w("SharedItemUploader", "isSefTypeChanged exception");
            return false;
        }
    }

    private boolean isValidVersion(String str) {
        if (str == null || !str.matches("[-+]?\\d*\\.?\\d+")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isSefTypeChanged$5(int i2, int i7) {
        if (i7 == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$1(Context context, String str, String str2, Integer num) {
        boolean isSuccess = MdeResultCode.isSuccess(num.intValue());
        Log.shv("SharedItemUploader", "requestShareItemUpdate result :" + isSuccess);
        if (!isSuccess) {
            MdeAlbumHelper.handleMdeFailResultCode(context, num.intValue());
        }
        ThreadUtil.postOnBgThreadDelayed(new R6.c(this, str, str2, 22), 500);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$2(String str, String str2, Context context, MediaItem mediaItem) {
        String str3 = str;
        Context context2 = context;
        MediaItem mediaItem2 = mediaItem;
        MdeSharingHelper.requestShareItemUpdate(context2, str3, getNewName(mediaItem, str), mediaItem2, MdeMediaItemHelper.createInstantMetaDataMap(mediaItem, str, str2), new b(this, context, str, str2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$3(String str, String str2, Bundle bundle, Context context) {
        MediaItem originalSharedItem = getOriginalSharedItem(str, str2);
        if (originalSharedItem == null) {
            Log.w("SharedItemUploader", "Unable to get item");
            return;
        }
        try {
            Pair<String, MediaItem> editedSharedItem = getEditedSharedItem(originalSharedItem.clone(), bundle);
            if (editedSharedItem == null) {
                Log.e("SharedItemUploader", "upload failed with null pair");
                return;
            }
            String str3 = (String) editedSharedItem.first;
            MediaItem mediaItem = (MediaItem) editedSharedItem.second;
            String instantThumbnailPath = getInstantThumbnailPath(createInstantThumbnail(str3, mediaItem), mediaItem);
            postEditedItemInfo(mediaItem);
            Optional.of(mediaItem).ifPresent(new b(this, str3, instantThumbnailPath, context));
        } catch (Exception e) {
            a.s(e, new StringBuilder("Exception while working uploadEditedItem. "), "SharedItemUploader");
        }
    }

    private void postEditedItemInfo(MediaItem mediaItem) {
        Blackboard.postEventGlobal(EventMessage.obtain(6016, mediaItem));
    }

    private void setDisplayName(MediaItem mediaItem, String str) {
        String nameFromPath = FileUtils.getNameFromPath(str);
        if (!TextUtils.isEmpty(nameFromPath)) {
            mediaItem.setTitle(nameFromPath);
            mediaItem.setDisplayName(nameFromPath);
        }
    }

    private void setImageMetaData(MediaItem mediaItem, String str) {
        BitmapOptions parse = BitmapOptionsFactory.parse(str);
        mediaItem.setSize(parse.outWidth, parse.outHeight);
        ExifInterface exif = ExifUtils.getExif(str);
        mediaItem.setOrientation(ExifUtils.getOrientation(exif));
        if (Features.isEnabled(Features.SUPPORT_SEC_MP_ORIENTATION_TAG)) {
            mediaItem.setOrientationTag(ExifUtils.getOrientationTag(exif));
        }
        if (mediaItem.getSefFileType() > 0 && isSefTypeChanged(mediaItem.getSefFileType(), str)) {
            mediaItem.setSefFileType(0, 0);
        }
    }

    private void setMimeType(MediaItem mediaItem, Bundle bundle) {
        if (!TextUtils.isEmpty(bundle.getString("mime_type"))) {
            mediaItem.setMimeType(bundle.getString("mime_type"));
        }
    }

    private void setSize(MediaItem mediaItem, String str) {
        mediaItem.setFileSize(new SecureFile(str).length());
    }

    private void setVideoMetaData(MediaItem mediaItem, String str) {
        MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(str);
        mediaItem.setSize(videoInfo.width, videoInfo.height);
        mediaItem.setOrientation(videoInfo.orientation);
        mediaItem.setFileDuration(videoInfo.duration);
    }

    public Bundle run(Context context, Bundle bundle) {
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
            Log.w("SharedItemUploader", "not support updating shared item.");
            return null;
        } else if (bundle != null) {
            String string = bundle.getString("mde_space_id");
            String string2 = bundle.getString("mde_item_id");
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                Log.w("SharedItemUploader", j.d("Invalid spaceId[", string, "], itemId[", string2, "]"));
                return null;
            }
            SimpleThreadPool.getInstance().execute(new B5.c(this, string, string2, bundle, context, 17));
            return null;
        } else {
            throw new IllegalArgumentException("upload edited item failed, invalid extras={null}");
        }
    }
}
