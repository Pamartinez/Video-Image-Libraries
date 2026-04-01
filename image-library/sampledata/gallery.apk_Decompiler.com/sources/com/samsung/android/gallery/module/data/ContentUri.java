package com.samsung.android.gallery.module.data;

import A.a;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.io.InputStream;
import java.util.Optional;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ContentUri {
    static final Function<FileItemInterface, Uri> cloudUriBuilder;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GenericImpl implements Function<FileItemInterface, Uri> {
        public Uri apply(FileItemInterface fileItemInterface) {
            return Uri.withAppendedPath(fileItemInterface.isImage() ? MediaStore.Images.Media.EXTERNAL_CONTENT_URI : MediaStore.Video.Media.EXTERNAL_CONTENT_URI, String.valueOf(fileItemInterface.getMediaId()));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NewMpImpl implements Function<FileItemInterface, Uri> {
        public Uri apply(FileItemInterface fileItemInterface) {
            return MediaUri.getInstance().getSecPickerUri().buildUpon().appendEncodedPath(fileItemInterface.isImage() ? "image" : "video").appendEncodedPath(fileItemInterface.getCloudServerId()).appendEncodedPath(String.valueOf(fileItemInterface.getMediaId())).build();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SecMpImpl implements Function<FileItemInterface, Uri> {
        public Uri apply(FileItemInterface fileItemInterface) {
            return MediaUri.getInstance().getSecPickerUri().buildUpon().appendEncodedPath(fileItemInterface.isImage() ? "image" : "video").appendEncodedPath(fileItemInterface.getCloudServerId()).appendEncodedPath(String.valueOf(fileItemInterface.getFileId())).build();
        }
    }

    static {
        Function<FileItemInterface, Uri> function;
        if (Features.isEnabled(Features.USE_SEC_MP)) {
            function = new SecMpImpl();
        } else if (Features.isEnabled(Features.USE_NEWMP)) {
            function = new NewMpImpl();
        } else {
            function = new GenericImpl();
        }
        cloudUriBuilder = function;
    }

    public static Uri build(FileItemInterface fileItemInterface, boolean z) {
        Uri uri;
        Uri uri2;
        long mediaId = fileItemInterface.getMediaId();
        if (mediaId < 0 || fileItemInterface.isSharing()) {
            if (PocFeatures.SUPPORT_PRIVATE_ALBUM && fileItemInterface.isPrivateItem()) {
                return FileProviderUtil.getUri(AppResources.getAppContext(), fileItemInterface.getPath());
            }
            if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT) && (uri = MdeData.of(fileItemInterface).uriInHiddenFolder) != null) {
                return uri;
            }
            if (fileItemInterface.getStorageType() == StorageType.UriItem && MediaUri.isFileContentUri(fileItemInterface.getDataPath())) {
                return Uri.parse(fileItemInterface.getDataPath());
            }
            Log.e((CharSequence) "ContentUri", "build failed", Long.valueOf(fileItemInterface.getFileId()), Long.valueOf(mediaId), fileItemInterface.getStorageType(), fileItemInterface.getDataPath());
            return null;
        } else if (!z && fileItemInterface.isCloudOnly()) {
            return cloudUriBuilder.apply(fileItemInterface);
        } else {
            String dataPath = fileItemInterface.getDataPath();
            if (SdkConfig.atLeast(SdkConfig.GED.Q) && z && FileUtils.isInRemovableStorage(dataPath)) {
                return Uri.withAppendedPath(MediaUri.getInstance().getFilesUri(FileUtils.getVolumeName(dataPath)), String.valueOf(mediaId));
            }
            if (fileItemInterface.isImage()) {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else {
                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            }
            return Uri.withAppendedPath(uri2, String.valueOf(mediaId));
        }
    }

    public static Uri getSecUri(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return null;
        }
        long fileId = fileItemInterface.getFileId();
        if (fileId >= 0 && !fileItemInterface.isSharing()) {
            return MediaUri.getInstance().getSecMediaUri().buildUpon().appendEncodedPath(String.valueOf(fileId)).build();
        }
        Log.e((CharSequence) "ContentUri", "getSecContentUri skip", Long.valueOf(fileId), Boolean.valueOf(fileItemInterface.isSharing()));
        return null;
    }

    public static Uri getStreamUri(Context context, FileItemInterface fileItemInterface) {
        InputStream inputStream;
        if (fileItemInterface.getStorageType() == StorageType.Stream) {
            String path = new File(FileUtils.getExternalFilesDir(".share"), (String) Optional.ofNullable((String) fileItemInterface.getTag("data-stream-name")).orElse(fileItemInterface.getTitle())).getPath();
            long currentTimeMillis = System.currentTimeMillis();
            if (FileUtils.exists(path) && (FileUtils.length(path) != ((Long) Optional.ofNullable((Long) fileItemInterface.getTag("file-provider-url-length")).orElse(-1L)).longValue() || !path.equals(fileItemInterface.getTag("file-provider-uri")))) {
                FileUtils.delete(path);
            }
            if (!FileUtils.exists(path)) {
                byte[] bArr = (byte[]) fileItemInterface.getTag("data-stream");
                if (bArr != null) {
                    FileUtils.writeFile(path, bArr);
                } else {
                    try {
                        inputStream = fileItemInterface.getInputStream();
                        FileUtils.writeFile(path, inputStream);
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (Error | Exception e) {
                        a.z(e, new StringBuilder("getStreamUri#failed. e="), "ContentUri");
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                }
            }
            if (FileUtils.exists(path)) {
                long length = FileUtils.length(path);
                fileItemInterface.setTag("file-provider-uri", path);
                fileItemInterface.setTag("file-provider-url-length", Long.valueOf(length));
                Uri uri = FileProviderUtil.getUri(context, path);
                Log.d("ContentUri", "getStreamUri {" + fileItemInterface.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + length + "} +" + (System.currentTimeMillis() - currentTimeMillis) + "");
                return uri;
            }
        }
        return getUri(fileItemInterface);
        throw th;
    }

    public static Uri getUri(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return null;
        }
        if (fileItemInterface.getCustomUriProvider() != null) {
            return fileItemInterface.getCustomUriProvider().get();
        }
        return build(fileItemInterface, false);
    }

    public static String getUriString(FileItemInterface fileItemInterface) {
        Uri uri = getUri(fileItemInterface);
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }

    public static Uri getWritableUri(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return null;
        }
        return build(fileItemInterface, true);
    }
}
