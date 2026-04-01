package com.samsung.android.gallery.plugins.filebrowser;

import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SearchLog;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FolderInfo extends FileInfo {
    HashMap<String, FolderInfo> dirs = new HashMap<>();
    ArrayList<FileInfo> files = new ArrayList<>();
    HashMap<String, FolderInfo> hidden = new HashMap<>();
    private volatile List<FileInfo> mSortedList;

    public FolderInfo(String str) {
        super(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$computeIfFolder$0(FolderInfo folderInfo, File file) {
        if (file.isDirectory()) {
            folderInfo.add(file.getName(), new FolderInfo(file));
        } else {
            folderInfo.add(new FileInfo(file));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$toString$3(StringBuilder sb2, FolderInfo folderInfo) {
        sb2.append("\n/");
        sb2.append(this.name);
        sb2.append(folderInfo);
    }

    public void add(FileInfo fileInfo) {
        this.files.add(fileInfo);
    }

    public FolderInfo computeIfFolder() {
        if (this.name.startsWith(StorageInfo.getDefault().root)) {
            File[] listFiles = new File(this.name).listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (file.isFile()) {
                        add(new FileInfo(file));
                    } else {
                        FolderInfo folderInfo = new FolderInfo(file);
                        Stream.of(file.listFiles()).forEach(new p(0, folderInfo));
                        add(file.getName(), folderInfo);
                    }
                }
                if (PocFeatures.isEnabled(PocFeatures.ShowSearchLog) && "/storage/emulated/0/Android/media/com.sec.android.gallery3d/log".equals(this.name)) {
                    add(new FileInfo("/storage/emulated/0/Android/media/com.sec.android.gallery3d/log/[DEBUG] search.log", SearchLog.length(), 0));
                }
            }
            list();
        }
        return this;
    }

    public int count() {
        return this.files.size() + this.dirs.size();
    }

    public boolean isDir() {
        return true;
    }

    public boolean isVirtual() {
        return false;
    }

    public List<FileInfo> list() {
        if (this.mSortedList == null) {
            NaturalOrderComparator naturalOrderComparator = new NaturalOrderComparator();
            List<FileInfo> list = (List) this.dirs.values().stream().sorted(naturalOrderComparator).collect(Collectors.toList());
            this.files.sort(naturalOrderComparator);
            list.addAll(this.files);
            this.mSortedList = list;
        }
        return this.mSortedList;
    }

    public long size() {
        return 0;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("/" + this.name + "(" + this.dirs.size() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.files.size() + ")");
        if (!this.dirs.isEmpty()) {
            sb2.append(" [");
            sb2.append(StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.dirs.values().stream().limit(3).map(new e(1)).iterator()));
            sb2.append("]");
        }
        if (!this.files.isEmpty()) {
            sb2.append(" [");
            sb2.append(StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.files.stream().map(new e(2)).limit(3).iterator()));
            sb2.append("]");
        }
        this.dirs.values().forEach(new m(1, this, sb2));
        return sb2.toString();
    }

    public void add(String str, FolderInfo folderInfo) {
        if (str.startsWith(".")) {
            this.hidden.put(str, folderInfo);
        } else {
            this.dirs.put(str, folderInfo);
        }
    }

    public FolderInfo(File file) {
        super(file);
    }
}
