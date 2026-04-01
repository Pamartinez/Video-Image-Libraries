package com.samsung.android.gallery.module.service.support;

import a6.g;
import android.content.Context;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.cloud.SCloudHelper;
import com.samsung.android.gallery.module.data.GroupItemLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.fileio.FileOperationFactory;
import com.samsung.android.gallery.module.fileio.abstraction.FileOpResult;
import com.samsung.android.gallery.module.fileio.abstraction.FileOperation;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOperation;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import com.samsung.android.gallery.module.fileio.util.FileApiUtils;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import fa.C0691a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileOperationHelper {
    private final boolean IS_POS = SdkConfig.atLeast(SdkConfig.GED.P);
    protected FileOperation mFileOperation;
    private FileOperation.OnProgressListener mProgressListener;

    private boolean renameNewEmptyAlbum(Context context, int i2, String str) {
        if (AlbumHelper.getInstance().renameNewEmptyAlbum(context, i2, str) <= 0) {
            return false;
        }
        FileUtils.deleteEmptyDirectory(str);
        return true;
    }

    private void setFileMode(FileInfo fileInfo) {
        int i2;
        if (isDifferentStorage(fileInfo.getSrcPath(), fileInfo.getDestPath())) {
            i2 = 16;
        } else {
            i2 = 0;
        }
        fileInfo.setFileMode(i2);
    }

    private void setGroupMediaInfo(Context context, FileInfo fileInfo) {
        if (fileInfo.isGroupShot()) {
            fileInfo.setGroupSubItems(FileApiUtils.getGroupSubItems(fileInfo.getMediaItem()));
            fileInfo.setNewGroupId(FileApiUtils.makeBurstShotGroupId(context));
        }
    }

    public void cancelOperation() {
        FileOperation fileOperation = this.mFileOperation;
        if (fileOperation != null) {
            fileOperation.cancel();
        }
    }

    public FileOpResult copy(Context context, FileInfo fileInfo) {
        setFileMode(fileInfo);
        FileOperation fileOperation = this.mFileOperation;
        if (fileOperation == null) {
            return FileOpResult.OP_NONE;
        }
        return fileOperation.copy(context, fileInfo);
    }

    public FileOpResult execute(Context context, FileInfo fileInfo) {
        if (fileInfo.isOperationValid()) {
            setGroupMediaInfo(context, fileInfo);
            if (fileInfo.getOperation() == 1) {
                return copy(context, fileInfo);
            }
            if (fileInfo.getOperation() == 2) {
                return move(context, fileInfo);
            }
            return rename(context, fileInfo);
        }
        throw new IllegalArgumentException("wrong operation:" + fileInfo.getOperation());
    }

    public boolean existData(Context context, MediaItem mediaItem, String str) {
        if ((!mediaItem.isCloudOnly() || !SCloudHelper.hasSameCloudItem(context, mediaItem, str)) && !FileUtils.exists(str)) {
            return false;
        }
        return true;
    }

    public void finishOperation(Context context, String str) {
        DatabaseOperation.getInstance(context).quit();
        MediaScanner.scanFolder(str, (MediaScannerListener) null);
    }

    public void finishPreloadMyTag(Context context, String str) {
        if (this.IS_POS && "copy".equals(str)) {
            DatabaseOperation.getInstance(context).finishPreloadMyTag();
        }
    }

    public long getGroupItemSize(MediaItem mediaItem) {
        return GroupItemLoader.queryTotalSize(String.valueOf(mediaItem.getFileId()), mediaItem.isSimilarShot());
    }

    public MediaItem[] getItemListForRename(int i2, boolean z) {
        Cursor query;
        ArrayList arrayList = new ArrayList();
        try {
            query = DbCompat.query(DbKey.ALBUM_FILES, new C0691a(i2, z, 0));
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemBuilder.create(query));
                    } while (query.moveToNext());
                }
            }
            MediaItem[] mediaItemArr = (MediaItem[]) arrayList.toArray(new MediaItem[0]);
            if (query != null) {
                query.close();
            }
            return mediaItemArr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String getRenamedPath(Context context, MediaItem mediaItem, String str) {
        if (mediaItem.isCloudOnly()) {
            return SCloudHelper.getNewFileFullPath(context, mediaItem, str);
        }
        return FileUtils.getNewFilePath(str);
    }

    public long getSize(MediaItem mediaItem) {
        if (mediaItem.isGroupShot()) {
            return getGroupItemSize(mediaItem);
        }
        if (mediaItem.isEmptyAlbum()) {
            return 1;
        }
        if (mediaItem.isCloudOnly()) {
            return mediaItem.getCloudOriginalSize();
        }
        return mediaItem.getFileSize();
    }

    public void init(Context context, FileOperation.OnProgressListener onProgressListener) {
        this.mProgressListener = onProgressListener;
        FileOperation instance = FileOperationFactory.getInstance();
        this.mFileOperation = instance;
        instance.setProgressListener(onProgressListener);
    }

    public boolean isDifferentStorage(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return !FileUtils.isInSameStorage(str, str2);
    }

    public FileOpResult move(Context context, FileInfo fileInfo) {
        setFileMode(fileInfo);
        FileOperation fileOperation = this.mFileOperation;
        if (fileOperation == null) {
            return FileOpResult.OP_NONE;
        }
        return fileOperation.move(context, fileInfo);
    }

    public FileOpResult rename(Context context, FileInfo fileInfo) {
        if (fileInfo.isEmptyAlbum()) {
            ((g) this.mProgressListener).b(getSize(fileInfo.getMediaItem()));
            if (renameNewEmptyAlbum(context, fileInfo.getAlbumID(), FileUtils.getDirectoryFromPath(fileInfo.getDestPath(), false))) {
                return FileOpResult.OP_LOCAL_OK;
            }
            return FileOpResult.OP_LOCAL_FAIL;
        }
        setFileMode(fileInfo);
        FileOperation fileOperation = this.mFileOperation;
        if (fileOperation == null) {
            return FileOpResult.OP_NONE;
        }
        return fileOperation.move(context, fileInfo);
    }

    public void startPreloadMyTag(Context context, MediaItem mediaItem, String str) {
        if (this.IS_POS && "copy".equals(str)) {
            DatabaseOperation.getInstance(context).startPreloadMyTag(mediaItem);
        }
    }
}
