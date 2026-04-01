package com.samsung.android.gallery.module.fileio.type;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.FileUtils;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FileInfo {
    private String mDestPath;
    private int mFileMode;
    private final String mFileName;
    ArrayList<FileItemInterface> mGroupSubItems;
    private final MediaItem mMediaItem;
    private int mNewGroupId = 0;
    private int mOperation = 0;
    private final String mSrcPath;

    public FileInfo(MediaItem mediaItem, String str, String str2) {
        int i2;
        this.mMediaItem = mediaItem;
        String referencePath = mediaItem.getReferencePath();
        this.mSrcPath = referencePath;
        String nameFromPath = FileUtils.getNameFromPath(referencePath);
        this.mFileName = nameFromPath;
        this.mDestPath = C0212a.p(C0212a.s(str), File.separator, nameFromPath);
        int operation = getOperation(str2);
        this.mOperation = operation;
        if (operation == 1) {
            i2 = 4;
        } else {
            i2 = 2;
        }
        this.mFileMode = i2;
    }

    public int getAlbumID() {
        return this.mMediaItem.getAlbumID();
    }

    public String getDestPath() {
        return this.mDestPath;
    }

    public int getFileMode() {
        return this.mFileMode;
    }

    public String getFileName() {
        return this.mFileName;
    }

    public ArrayList<FileItemInterface> getGroupSubItems() {
        return this.mGroupSubItems;
    }

    public int getGroupType() {
        return this.mMediaItem.getGroupType();
    }

    public MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public int getNewGroupId() {
        return this.mNewGroupId;
    }

    public int getOperation(String str) {
        if (str.equals("move")) {
            return 2;
        }
        if (str.equals("rename")) {
            return 3;
        }
        return str.equals("copy") ? 1 : 0;
    }

    public String getSrcPath() {
        return this.mSrcPath;
    }

    public StorageType getStorageType() {
        return this.mMediaItem.getStorageType();
    }

    public String getTitle() {
        return this.mMediaItem.getTitle();
    }

    public boolean isCopyInsteadMoveMode() {
        int i2 = this.mFileMode;
        if ((i2 & 8) != 0) {
            return true;
        }
        if ((i2 & 16) == 0 || (i2 & 2) == 0) {
            return false;
        }
        return true;
    }

    public boolean isDiffStorageMoveMode() {
        int i2 = this.mFileMode;
        if ((i2 & 16) == 0 || (i2 & 2) == 0) {
            return false;
        }
        return true;
    }

    public boolean isEmptyAlbum() {
        return this.mMediaItem.isEmptyAlbum();
    }

    public boolean isGroupShot() {
        return this.mMediaItem.isGroupShot();
    }

    public boolean isMoveMode() {
        if ((this.mFileMode & 2) != 0) {
            return true;
        }
        return false;
    }

    public boolean isOperationValid() {
        int i2 = this.mOperation;
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            return true;
        }
        return false;
    }

    public boolean isOverwriteMode() {
        if ((this.mFileMode & 8) != 0) {
            return true;
        }
        return false;
    }

    public void setDestPath(String str) {
        this.mDestPath = str;
    }

    public void setFileMode(int i2) {
        this.mFileMode = i2 | this.mFileMode;
    }

    public void setGroupSubItems(ArrayList<FileItemInterface> arrayList) {
        this.mGroupSubItems = arrayList;
    }

    public void setNewGroupId(int i2) {
        this.mNewGroupId = i2;
    }

    public int getOperation() {
        return this.mOperation;
    }
}
