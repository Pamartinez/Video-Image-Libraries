package com.samsung.android.gallery.module.data;

import android.graphics.RectF;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.abstraction.StoryCoverData;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.io.File;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class EffectItemBuilder {
    public static MediaItem build(MediaItem mediaItem) {
        String effectFilePath = getEffectFilePath(MediaItemStory.getEffectFilePaths(mediaItem));
        if (!isValidPath(effectFilePath)) {
            return null;
        }
        if (isImage(effectFilePath)) {
            return getEffectImageItem(mediaItem, effectFilePath);
        }
        return getEffectVideoItem(mediaItem, effectFilePath);
    }

    public static MediaItem buildAiEditItem(MediaItem mediaItem) {
        String effectFilePath = getEffectFilePath(mediaItem, "jpg");
        if (verify(effectFilePath)) {
            return getEffectImageItem(mediaItem, effectFilePath);
        }
        return null;
    }

    public static MediaItem buildEffectItem(MediaItem mediaItem) {
        if (MediaItemStory.isAiEditEffect(mediaItem)) {
            return buildAiEditItem(mediaItem);
        }
        return null;
    }

    public static MediaItem buildLiveEffectItem(FileItemInterface fileItemInterface) {
        MediaItem mediaItem = (MediaItem) fileItemInterface;
        String effectFilePath = getEffectFilePath(mediaItem, IFormat.FORMAT_MP4);
        if (!new SecureFile(effectFilePath).exists()) {
            return mediaItem;
        }
        AnonymousClass1 r1 = new MediaItem() {
            final Supplier<Uri> uriSupplier = new b(this, 0);

            /* access modifiers changed from: private */
            public /* synthetic */ Uri lambda$$0() {
                return MediaItemStory.getLiveEffectFileUri(this);
            }

            public Supplier<Uri> getCustomUriProvider() {
                return this.uriSupplier;
            }
        };
        r1.cloneBasicInfo(mediaItem);
        r1.cloneTags(mediaItem);
        MediaItemStory.setLiveEffectFileUri(r1, FileProviderUtil.getUri(Blackboard.getContext(), effectFilePath));
        MediaItemStory.setEffectType(r1, 0);
        MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(effectFilePath);
        r1.setAlbumID(mediaItem.getAlbumID());
        r1.setFileId(mediaItem.getFileId());
        r1.setPath(effectFilePath);
        r1.setSize(videoInfo.width, videoInfo.height);
        r1.setOrientation(videoInfo.orientation);
        r1.setFileDuration(videoInfo.duration);
        r1.setMimeType(Encode.ContentType.VIDEO_MP4);
        r1.setMediaType(MediaType.Video);
        r1.setCropRect(new RectF());
        r1.setMediaId(-1);
        r1.setDisplayName(mediaItem.getTitle());
        MediaItemStory.setStoryOriginCoverData(r1, new StoryCoverData(mediaItem));
        resetSmartCropInfo(r1);
        return r1;
    }

    public static MediaItem buildRecapItem(FileItemInterface fileItemInterface) {
        AnonymousClass2 r0 = new MediaItem() {
            final Supplier<Uri> uriSupplier = new b(this, 1);

            /* access modifiers changed from: private */
            public /* synthetic */ Uri lambda$$0() {
                return FileProviderUtil.getUri(Blackboard.getContext(), getPath());
            }

            public Supplier<Uri> getCustomUriProvider() {
                return this.uriSupplier;
            }
        };
        r0.cloneBasicInfo((MediaItem) fileItemInterface);
        r0.cloneTags(fileItemInterface);
        String recapPath = MediaItemStory.getRecapPath(fileItemInterface);
        MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(recapPath);
        r0.setAlbumID(fileItemInterface.getAlbumID());
        r0.setPath(recapPath);
        r0.setDisplayName(FileUtils.getBaseName(recapPath));
        r0.setSize(videoInfo.width, videoInfo.height);
        r0.setOrientation(videoInfo.orientation);
        r0.setFileDuration(videoInfo.duration);
        r0.setFileSize(FileUtils.length(recapPath));
        r0.setMimeType(Encode.ContentType.VIDEO_MP4);
        r0.setMediaType(MediaType.Video);
        r0.setStorageType(StorageType.UriItem);
        r0.setCropRect(new RectF());
        return r0;
    }

    private static String getEffectFilePath(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return null;
    }

    private static MediaItem getEffectImageItem(MediaItem mediaItem, String str) {
        MediaItem clone = mediaItem.clone();
        clone.setPath(str);
        BitmapOptions parse = BitmapOptionsFactory.parse(str);
        clone.setSize(parse.outWidth, parse.outHeight);
        clone.setMimeType("image/jpeg");
        clone.setFileSize(FileUtils.length(str));
        return clone;
    }

    public static String getEffectType(MediaItem mediaItem) {
        String str;
        if (mediaItem != null) {
            str = mediaItem.getPath();
        } else {
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            if (str.contains("/data/sec/com.samsung.cmh/remaster/")) {
                return "remaster";
            }
            if (str.contains("/data/sec/com.samsung.cmh/portrait/")) {
                return "portrait";
            }
        }
        return "";
    }

    private static MediaItem getEffectVideoItem(MediaItem mediaItem, String str) {
        MediaItem clone = mediaItem.clone();
        MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(str);
        clone.setPath(str);
        clone.setSize(videoInfo.width, videoInfo.height);
        clone.setOrientation(videoInfo.orientation);
        clone.setFileDuration(videoInfo.duration);
        clone.setFileSize(FileUtils.length(str));
        clone.setMimeType(Encode.ContentType.VIDEO_MP4);
        clone.setMediaType(MediaType.Video);
        clone.setCropRect(new RectF());
        return clone;
    }

    private static boolean isImage(String str) {
        return "jpg".equals(FileUtils.getExtension(str));
    }

    private static boolean isValidPath(String str) {
        if (str == null || !new SecureFile(str).exists()) {
            return false;
        }
        String extension = FileUtils.getExtension(str);
        if ("jpg".equals(extension) || IFormat.FORMAT_MP4.equals(extension)) {
            return true;
        }
        return false;
    }

    private static void resetSmartCropInfo(MediaItem mediaItem) {
        if (mediaItem != null) {
            mediaItem.setCropRectRatio(new RectF());
            MediaItemStory.setTotalSmartCropRatio(mediaItem, "");
            MediaItemStory.setTotalSmartCropDeviceRatio(mediaItem, "");
        }
    }

    private static boolean verify(String str) {
        return new SecureFile(str).exists();
    }

    private static String getEffectFilePath(MediaItem mediaItem, String str) {
        return "/data/sec/com.samsung.cmh/liveeffect" + File.separator + mediaItem.getFileId() + "." + str;
    }
}
