package com.samsung.android.gallery.plugins.filebrowser;

import N2.j;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ZipData implements Closeable {
    private ZipFile mZipFile;
    public String name;
    public String path;
    public FolderInfo root = new FolderInfo("");

    private void add(ZipEntry zipEntry) {
        String[] splitPathAndName = FileUtils.splitPathAndName(zipEntry.getName());
        findFolder(splitPathAndName[0]).add(new FileInfo(splitPathAndName[1], new ZipEntryHolder(this, zipEntry)));
    }

    public void close() {
        ZipFile zipFile = this.mZipFile;
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.mZipFile = null;
        }
    }

    public FolderInfo findFolder(String str) {
        String[] split = str.split("/");
        FolderInfo folderInfo = this.root;
        for (String str2 : split) {
            if (!folderInfo.name.equals(str2)) {
                folderInfo = folderInfo.dirs.computeIfAbsent(str2, new e(5));
            }
        }
        return folderInfo;
    }

    public InputStream getInputStream(ZipEntry zipEntry) {
        return this.mZipFile.getInputStream(zipEntry);
    }

    public ZipData open(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.path = str;
            this.name = FileUtils.getNameFromPath(str);
            ZipFile zipFile = new ZipFile(str);
            this.mZipFile = zipFile;
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                if (!zipEntry.isDirectory()) {
                    add(zipEntry);
                }
            }
            Log.d("ZipData", "open" + Logger.vt(str, Long.valueOf(currentTimeMillis)));
            return this;
        } catch (Error | Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(j.i(e, new StringBuilder("unsupported file. error with ")));
        }
    }

    public String toString() {
        return this.root.toString();
    }
}
