package com.samsung.android.gallery.module.dataset;

import A4.C0375j;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.comparator.Comparators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FolderItem extends MediaItem {
    private final Object LOCK;
    private final Comparator<MediaItem> mComparator;
    private boolean mCustomCover;
    private final PriorityQueue<MediaItem> mItemList;
    private String mTranslatedName;

    public FolderItem() {
        this(Comparators.getComparator("location://folder/root"));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addItem(com.samsung.android.gallery.module.data.MediaItem r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.LOCK
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0019
            boolean r1 = r4.isFolder()     // Catch:{ all -> 0x0017 }
            if (r1 == 0) goto L_0x0019
            int r1 = r4.getAlbumID()     // Catch:{ all -> 0x0017 }
            int r2 = r3.getAlbumID()     // Catch:{ all -> 0x0017 }
            if (r1 != r2) goto L_0x0019
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            return
        L_0x0017:
            r3 = move-exception
            goto L_0x0031
        L_0x0019:
            java.util.PriorityQueue<com.samsung.android.gallery.module.data.MediaItem> r1 = r3.mItemList     // Catch:{ all -> 0x0017 }
            java.lang.Object r1 = r1.peek()     // Catch:{ all -> 0x0017 }
            com.samsung.android.gallery.module.data.MediaItem r1 = (com.samsung.android.gallery.module.data.MediaItem) r1     // Catch:{ all -> 0x0017 }
            java.util.PriorityQueue<com.samsung.android.gallery.module.data.MediaItem> r2 = r3.mItemList     // Catch:{ all -> 0x0017 }
            r2.add(r4)     // Catch:{ all -> 0x0017 }
            boolean r2 = r3.isMergedAlbum()     // Catch:{ all -> 0x0017 }
            if (r2 == 0) goto L_0x002f
            r3.updateMergedAlbum(r1, r4)     // Catch:{ all -> 0x0017 }
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            return
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.FolderItem.addItem(com.samsung.android.gallery.module.data.MediaItem):void");
    }

    public String buildThumbCacheKey() {
        if (isMergedAlbum()) {
            if (this.mCustomCover) {
                MediaItem peek = this.mItemList.peek();
                if (peek instanceof CoverItem) {
                    return peek.getThumbCacheKey();
                }
            }
            if (super.getPath() == null) {
                updateMergedAlbum((MediaItem) null, (MediaItem) null);
            }
        }
        return super.buildThumbCacheKey();
    }

    public int getAlbumID() {
        return getFolderID();
    }

    public MediaItem[] getAlbumsInFolder() {
        return getAlbumsInFolder(true);
    }

    public List<MediaItem> getChildAlbums() {
        ArrayList arrayList = new ArrayList();
        for (MediaItem next : getChildItems()) {
            if (next.isFolder()) {
                arrayList.addAll(next.getChildAlbums());
            } else {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public List<MediaItem> getChildItems() {
        ArrayList arrayList = new ArrayList(this.mItemList);
        try {
            arrayList.sort(this.mComparator);
            return arrayList;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public List<MediaItem> getChildList() {
        return new ArrayList(this.mItemList);
    }

    public String getCloudServerPath() {
        if (!isMergedAlbum()) {
            return null;
        }
        String cloudServerPath = super.getCloudServerPath();
        if (cloudServerPath != null) {
            return cloudServerPath;
        }
        MediaItem peek = this.mItemList.peek();
        if (peek != null) {
            return peek.getCloudServerPath();
        }
        return null;
    }

    public int getCount() {
        int i2;
        synchronized (this.LOCK) {
            try {
                Iterator<MediaItem> it = this.mItemList.iterator();
                i2 = 0;
                while (it.hasNext()) {
                    i2 += it.next().getCount();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2;
    }

    public long getDateModified() {
        long j2;
        synchronized (this.LOCK) {
            try {
                Iterator<MediaItem> it = this.mItemList.iterator();
                j2 = 0;
                while (it.hasNext()) {
                    j2 = Long.max(j2, it.next().getDateModified());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return j2;
    }

    public String getDisplayName() {
        String str = this.mTranslatedName;
        if (str != null) {
            return str;
        }
        return getFolderName();
    }

    public MediaItem getFirst() {
        MediaItem peek;
        synchronized (this.LOCK) {
            peek = this.mItemList.peek();
            if (peek == null) {
                peek = new MediaItem();
            }
        }
        return peek;
    }

    public int getItemCount() {
        int size;
        synchronized (this.LOCK) {
            size = this.mItemList.size();
        }
        return size;
    }

    public MediaItem[] getItemsInFolder() {
        return getItemsInFolder(true);
    }

    public String getPath() {
        String str = null;
        if (!isMergedAlbum()) {
            return null;
        }
        if (this.mCustomCover) {
            MediaItem peek = this.mItemList.peek();
            if (peek != null) {
                str = peek.getPath();
            }
            if (str != null) {
                return str;
            }
            return super.getPath();
        }
        String path = super.getPath();
        if (path != null) {
            return path;
        }
        MediaItem peek2 = this.mItemList.peek();
        if (peek2 != null) {
            return peek2.getPath();
        }
        return null;
    }

    public StorageType getStorageType() {
        StorageType storageType = super.getStorageType();
        if (storageType != null || !isMergedAlbum()) {
            return storageType;
        }
        MediaItem peek = this.mItemList.peek();
        if (peek != null) {
            return peek.getStorageType();
        }
        return null;
    }

    public String getTitle() {
        return getFolderName();
    }

    public boolean isEmpty() {
        return this.mItemList.isEmpty();
    }

    public boolean isFolder() {
        return !isMergedAlbum();
    }

    public boolean isFolderItem() {
        return true;
    }

    public void removeItem(MediaItem mediaItem) {
        synchronized (this.LOCK) {
            try {
                MediaItem peek = this.mItemList.peek();
                this.mItemList.remove(mediaItem);
                if (isMergedAlbum()) {
                    updateMergedAlbum(peek, mediaItem);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void removeVirtual() {
        if (isFolder()) {
            synchronized (this.LOCK) {
                try {
                    this.mItemList.removeIf(new C0375j(6));
                    Iterator<MediaItem> it = this.mItemList.iterator();
                    while (it.hasNext()) {
                        MediaItem next = it.next();
                        if (next.isFolder()) {
                            ((FolderItem) next).removeVirtual();
                        }
                    }
                } finally {
                }
            }
        }
    }

    public void setAlbumLevel(int i2) {
        super.setAlbumLevel(i2);
        if (isMergedAlbum()) {
            getChildList().forEach(new C0592b(i2));
        }
    }

    public void setDisplayName(String str) {
        setFolderName(str);
    }

    public void setTranslatedName(String str) {
        this.mTranslatedName = str;
    }

    public void updateMergedAlbum(MediaItem mediaItem, MediaItem mediaItem2) {
        boolean z;
        MediaItem peek = this.mItemList.peek();
        if ((mediaItem == null || mediaItem != peek) && peek != null) {
            super.cloneBasicInfo(peek);
            super.cloneExtraInfo(peek);
            if (!(peek instanceof CoverItem) || !peek.isCustomCover()) {
                z = false;
            } else {
                z = true;
            }
            this.mCustomCover = z;
        }
    }

    public FolderItem(Comparator<MediaItem> comparator) {
        this.LOCK = new Object();
        this.mComparator = comparator;
        this.mItemList = new PriorityQueue<>(comparator);
    }

    public MediaItem[] getAlbumsInFolder(boolean z) {
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : getItemsInFolder(z)) {
            if (mediaItem.isFolder() || mediaItem.isMergedAlbum()) {
                arrayList.addAll(Arrays.asList(mediaItem.getAlbumsInFolder(z)));
            } else {
                arrayList.add(mediaItem);
            }
        }
        return (MediaItem[]) arrayList.toArray(new MediaItem[0]);
    }

    public MediaItem[] getItemsInFolder(boolean z) {
        MediaItem[] mediaItemArr;
        synchronized (this.LOCK) {
            mediaItemArr = new MediaItem[this.mItemList.size()];
            this.mItemList.toArray(mediaItemArr);
        }
        if (z) {
            try {
                Arrays.sort(mediaItemArr, this.mComparator);
                return mediaItemArr;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return mediaItemArr;
    }
}
