package com.samsung.android.gallery.plugins.filebrowser;

import c0.C0086a;
import com.samsung.android.gallery.support.utils.FileType;
import java.io.File;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FileInfo {
    String displayName;
    ZipEntryHolder entry;
    long lastModified;
    MediaInfo mediaInfo;
    int mediaType;
    String mimeType;
    String name;
    long size;

    public FileInfo(String str, ZipEntryHolder zipEntryHolder) {
        this.name = str;
        this.entry = zipEntryHolder;
    }

    public int count() {
        return 1;
    }

    public boolean equals(Object obj) {
        ZipEntryHolder zipEntryHolder;
        if (obj instanceof FileInfo) {
            FileInfo fileInfo = (FileInfo) obj;
            if (!this.name.equals(fileInfo.name) || ((zipEntryHolder = this.entry) != null && zipEntryHolder.hashCode() != fileInfo.entry.hashCode())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String getDisplayName() {
        String str = this.displayName;
        if (str != null) {
            return str;
        }
        return this.name;
    }

    public String getMimeType() {
        int i2;
        if (this.mimeType == null) {
            String mimeType2 = FileType.getMimeType(this.name);
            this.mimeType = mimeType2;
            if (mimeType2.startsWith("image")) {
                i2 = 1;
            } else if (this.mimeType.startsWith("video")) {
                i2 = 3;
            } else {
                i2 = 0;
            }
            this.mediaType = i2;
        }
        return this.mimeType;
    }

    public boolean isCompressed() {
        String str = this.name;
        if (str == null || str.startsWith("/storage/emulated/") || this.name.startsWith("/data/sec/")) {
            return false;
        }
        return true;
    }

    public boolean isDir() {
        return false;
    }

    public boolean isFile() {
        return !isDir();
    }

    public boolean isMedia() {
        getMimeType();
        int i2 = this.mediaType;
        if (i2 == 1 || i2 == 3) {
            return true;
        }
        return false;
    }

    public boolean isSecured() {
        String str = this.name;
        if (str == null || !str.startsWith("/data/sec")) {
            return false;
        }
        return true;
    }

    public boolean isVirtual() {
        return false;
    }

    public boolean isZip() {
        return this.name.toLowerCase().endsWith(".zip");
    }

    public long lastModified() {
        ZipEntryHolder zipEntryHolder = this.entry;
        if (zipEntryHolder != null) {
            return zipEntryHolder.lastModified();
        }
        return this.lastModified;
    }

    public List<FileInfo> list() {
        return List.of(this);
    }

    public FileInfo setMediaInfo(MediaInfo mediaInfo2) {
        this.mediaInfo = mediaInfo2;
        return this;
    }

    public long size() {
        ZipEntryHolder zipEntryHolder = this.entry;
        if (zipEntryHolder != null) {
            return zipEntryHolder.getSize();
        }
        return this.size;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("FileInfo[");
        if (isDir()) {
            str = "folder";
        } else if (isCompressed()) {
            str = "compressed";
        } else {
            str = "file";
        }
        sb2.append(str);
        sb2.append(',');
        sb2.append(this.size);
        sb2.append(',');
        sb2.append(getMimeType());
        sb2.append(',');
        return C0086a.m(sb2, this.name, ']');
    }

    public FileInfo(String str) {
        this(str, (ZipEntryHolder) null);
    }

    public FileInfo(File file) {
        this(file.getPath(), file.length(), file.lastModified());
    }

    public FileInfo(String str, long j2, long j3) {
        this.name = str;
        this.size = j2;
        this.lastModified = j3;
    }
}
