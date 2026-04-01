package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.dataset.comparator.Comparators;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.File;
import java.util.Comparator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumDataHelper {
    static final Comparator<MediaItem> sNameMergedComparator = new C0607l(1);
    private final String mLocationKey;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CropRectInfo {
        private int mHeight;
        private final MediaItem mItem;
        private int mMediaType;
        private int mOrientation;
        private int mOrientationTag;
        private int mWidth;

        public CropRectInfo(MediaItem mediaItem) {
            this.mItem = mediaItem;
            this.mWidth = mediaItem.getWidth();
            this.mHeight = mediaItem.getHeight();
            this.mOrientation = mediaItem.getOrientation();
            this.mOrientationTag = mediaItem.getOrientationTag();
            this.mMediaType = MediaType.toInt(mediaItem.getMediaType(), MediaType.Image.toInt());
        }

        /* access modifiers changed from: private */
        public String getRectString() {
            return this.mItem.getFileId() + ";" + this.mOrientation + ";" + StorageType.toInt(this.mItem.getStorageType(), StorageType.Local.toInt()) + ";" + this.mMediaType + ";" + this.mWidth + ";" + this.mHeight + ";" + this.mItem.getDateModified() + ";" + StringCompat.valueOf(this.mItem.getCloudServerPath(), "") + ";" + this.mItem.getFileDuration() + ";" + this.mItem.getFileSize() + ";" + this.mItem.getDateTaken() + ";" + this.mItem.getCloudOriginalSize() + ";" + this.mOrientationTag;
        }

        public void setHeight(int i2) {
            this.mHeight = i2;
        }

        public void setMediaType(int i2) {
            this.mMediaType = i2;
        }

        public void setOrientation(int i2) {
            this.mOrientation = i2;
        }

        public void setWidth(int i2) {
            this.mWidth = i2;
        }
    }

    public AlbumDataHelper(String str) {
        this.mLocationKey = str;
    }

    private void addAlbumToFolder(MediaItem mediaItem, FolderItem folderItem) {
        MediaItemBuilder.updateAlbumOrder(mediaItem, 1000000000000000007L);
        folderItem.addItem(mediaItem);
    }

    private MediaItem clearCoverItem(MediaItem mediaItem, String str) {
        if (!(mediaItem instanceof CoverItem)) {
            return mediaItem;
        }
        MediaItem mediaItem2 = new MediaItem();
        MediaItemBuilder.copyAlbumData(mediaItem, mediaItem2);
        MediaItemBuilder.setAlbumCover(mediaItem2, (String) null, str);
        return mediaItem2;
    }

    private FolderItem clearFolderCoverItem(FolderItem folderItem, String str) {
        FolderItem folderItem2;
        if (!(folderItem instanceof FolderCoverItem)) {
            return folderItem;
        }
        if (folderItem.isMergedAlbum()) {
            folderItem2 = createNameMergedItem(folderItem.getFolderID(), folderItem.getFolderName(), folderItem.getAlbumOrder());
        } else {
            folderItem2 = createFolderItem(folderItem.getFolderID(), folderItem.getFolderName(), folderItem.getAlbumOrder());
        }
        MediaItemBuilder.copyAlbumData(folderItem, folderItem2);
        MediaItemBuilder.setAlbumCover(folderItem2, (String) null, str);
        if (folderItem.getItemCount() != 0) {
            for (MediaItem addItem : folderItem.getChildList()) {
                folderItem2.addItem(addItem);
            }
        }
        return folderItem2;
    }

    public static MediaItem createCoverItem(MediaItem mediaItem, String str, String str2) {
        if (!FileUtils.exists(str)) {
            return mediaItem;
        }
        CoverItem coverItem = new CoverItem();
        MediaItemBuilder.copyAlbumData(mediaItem, coverItem);
        MediaItemBuilder.setAlbumCover(coverItem, str, str2);
        return coverItem;
    }

    public static String getStringForCropRect(String str, MediaItem mediaItem) {
        return getStringForCropRect(str, new CropRectInfo(mediaItem));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$0(MediaItem mediaItem, MediaItem mediaItem2) {
        int count = mediaItem.getCount();
        int count2 = mediaItem2.getCount();
        if (!(count == 0 && count2 == 0)) {
            if (count == 0) {
                return 1;
            }
            if (count2 == 0) {
                return -1;
            }
        }
        long dateTaken = mediaItem.getDateTaken();
        long dateTaken2 = mediaItem2.getDateTaken();
        if (dateTaken == dateTaken2) {
            return Long.compare(mediaItem2.getFileId(), mediaItem.getFileId());
        }
        return Long.compare(dateTaken2, dateTaken);
    }

    public static String toDebugLog(MediaItem mediaItem) {
        if (mediaItem == null) {
            return "AlbumData{null}";
        }
        try {
            return "AlbumData{" + mediaItem.getAlbumID() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getCount() + NumericEnum.SEP + mediaItem.getDateTaken() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getDateModified() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getAlbumType() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + (mediaItem.isAlbumLvFirst() ? 1 : 0) + NumericEnum.SEP + new CropRectInfo(mediaItem).getRectString() + NumericEnum.SEP + Logger.getEncodedString(mediaItem.getDisplayName()) + "}";
        } catch (Exception unused) {
            return "AlbumData{empty}";
        }
    }

    public MediaItem createEmptyItem(Cursor cursor) {
        MediaItem mediaItem = new MediaItem("image/jpeg", MediaType.Image, StorageType.Local);
        int i2 = cursor.getInt(cursor.getColumnIndex("folder_id"));
        String string = cursor.getString(cursor.getColumnIndex("folder_name"));
        int i7 = cursor.getInt(cursor.getColumnIndex("__bucketID"));
        boolean z = false;
        mediaItem.setSefFileType(0, 0);
        mediaItem.setFolderInfo(i2, string);
        mediaItem.setAlbumID(i7);
        mediaItem.setTitle(cursor.getString(cursor.getColumnIndex("__Title")));
        if (cursor.getInt(cursor.getColumnIndex("__ishide")) == 1) {
            z = true;
        }
        mediaItem.setAlbumHide(z);
        mediaItem.setDateModified(cursor.getLong(cursor.getColumnIndex("__dateModified")));
        mediaItem.setDisplayName(mediaItem.getTitle());
        mediaItem.setPath(cursor.getString(cursor.getColumnIndex("default_cover_path")));
        MediaItemBuilder.updateAlbumOrder(mediaItem, cursor.getLong(cursor.getColumnIndex("album_order")));
        return mediaItem;
    }

    public long createFolderAt(List<MediaItem> list, int i2, MediaItem mediaItem, int i7, String str) {
        int i8;
        if (list != null) {
            i8 = list.size();
        } else {
            i8 = 0;
        }
        if (list == null || i2 >= i8) {
            return -1;
        }
        MediaItem mediaItem2 = list.get(i2);
        long albumOrder = mediaItem2.getAlbumOrder();
        if (mediaItem2.isFolder()) {
            addAlbumToFolder(mediaItem, (FolderItem) mediaItem2);
            return albumOrder;
        }
        FolderItem folderItem = new FolderItem();
        folderItem.setFolderInfo(i7, str);
        addAlbumToFolder(mediaItem, folderItem);
        addAlbumToFolder(mediaItem2, folderItem);
        list.remove(i2);
        list.add(i2, folderItem);
        return albumOrder;
    }

    public FolderItem createFolderCoverItem(FolderItem folderItem, String str, String str2) {
        return createFolderCoverItem(folderItem, str, str2, false);
    }

    public FolderItem createFolderItem(int i2, String str, long j2) {
        FolderItem folderItem = new FolderItem();
        folderItem.setFolderInfo(i2, str);
        MediaItemBuilder.updateAlbumOrder(folderItem, j2);
        return folderItem;
    }

    public FolderItem createNameMergedItem(int i2, String str, long j2) {
        FolderItem folderItem = new FolderItem(sNameMergedComparator);
        folderItem.setFolderInfo(i2, str);
        MediaItemBuilder.updateAlbumOrder(folderItem, j2);
        folderItem.setAlbumType(AlbumType.Merged);
        folderItem.setAlbumShowInfo(true);
        return folderItem;
    }

    public int createNewItem(List<MediaItem> list, String str, String str2, boolean z, int i2) {
        boolean z3;
        long j2;
        MediaItem mediaItem;
        int i7 = 0;
        boolean z7 = true;
        if (!TextUtils.isEmpty(str2) || i2 != -1) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (i2 == -1) {
            z7 = false;
        }
        if (z) {
            j2 = 1000000000000000007L;
        } else {
            j2 = 0;
        }
        if (z3) {
            mediaItem = createFolderItem(FileUtils.getBucketId(str), str, j2);
        } else if (z7) {
            mediaItem = MediaItemBuilder.createEmptyAlbum(i2, str, (String) null);
            MediaItemBuilder.updateAlbumOrder(mediaItem, j2);
        } else {
            mediaItem = MediaItemBuilder.createEmptyAlbum(FileUtils.getBucketId(str2), str, C0212a.p(C0212a.s(str2), File.separator, "!$&Welcome@#Image.jpg"));
            MediaItemBuilder.updateAlbumOrder(mediaItem, j2);
        }
        Comparator<MediaItem> comparator = getComparator();
        while (i7 < list.size() && comparator.compare(mediaItem, list.get(i7)) > 0) {
            i7++;
        }
        list.add(i7, mediaItem);
        return i7;
    }

    public MediaItem createVirtualEmptyItem(int i2, String str) {
        MediaItem mediaItem = new MediaItem("image/jpeg", MediaType.Image, StorageType.Local);
        mediaItem.setAlbumID(i2);
        mediaItem.setTitle(str);
        mediaItem.setDisplayName(str);
        mediaItem.setAlbumType(AlbumType.Virtual);
        mediaItem.setAlbumLevel(1);
        mediaItem.setVolumeName("external_primary");
        return mediaItem;
    }

    public void findMinMaxOrder(List<MediaItem> list, long[] jArr) {
        if (list.isEmpty()) {
            jArr[0] = 0;
            jArr[1] = 0;
            return;
        }
        jArr[1] = ((MediaItem) C0086a.f(1, list)).getAlbumOrder();
        jArr[0] = list.get(0).getAlbumOrder();
    }

    public Comparator<MediaItem> getComparator() {
        return Comparators.getComparator(this.mLocationKey);
    }

    public void insertItemAt(List<MediaItem> list, int i2, MediaItem mediaItem, boolean z) {
        int i7;
        long j2;
        long j3;
        if (list != null) {
            i7 = list.size();
        } else {
            i7 = 0;
        }
        if (list != null && i2 <= i7 && mediaItem != null) {
            list.add(i2, mediaItem);
            if (z) {
                MediaItem mediaItem2 = list.get(i2);
                if (i2 == 0) {
                    j2 = 0;
                } else {
                    j2 = list.get(i2 - 1).getAlbumOrder();
                }
                if (i2 == i7) {
                    j3 = mediaItem2.getAlbumOrder() + list.get(0).getAlbumOrder();
                } else {
                    j3 = list.get(i2 + 1).getAlbumOrder();
                }
                MediaItemBuilder.updateAlbumOrder(mediaItem2, (j2 + j3) / 2);
            }
        }
    }

    public boolean isOrderUpdated(List<MediaItem> list) {
        long[] jArr = new long[2];
        findMinMaxOrder(list, jArr);
        if (jArr[1] != 0) {
            return true;
        }
        return false;
    }

    public void removeItemAt(List<MediaItem> list, int i2) {
        int i7;
        if (list != null) {
            i7 = list.size();
        } else {
            i7 = 0;
        }
        if (list != null && i2 < i7) {
            list.remove(i2);
        }
    }

    public void reorderData(List<MediaItem> list, int i2, int i7, boolean z) {
        int i8;
        long j2;
        long j3;
        if (list != null) {
            i8 = list.size();
        } else {
            i8 = 0;
        }
        if (list != null && i2 < i8 && i7 < i8) {
            int i10 = i8 - 1;
            long albumOrder = list.get(i10).getAlbumOrder();
            list.add(i7, list.remove(i2));
            if (z) {
                MediaItem mediaItem = list.get(i7);
                if (i7 == 0) {
                    j2 = 0;
                } else {
                    j2 = list.get(i7 - 1).getAlbumOrder();
                }
                if (i7 == i10) {
                    j3 = albumOrder + 1000000000;
                } else {
                    j3 = list.get(i7 + 1).getAlbumOrder();
                }
                MediaItemBuilder.updateAlbumOrder(mediaItem, (j2 + j3) / 2);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        r12 = r4;
        r4 = r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int updateCoverItem(com.samsung.android.gallery.support.blackboard.Blackboard r10, java.util.List<com.samsung.android.gallery.module.data.MediaItem> r11, int r12, java.lang.String r13, java.lang.String r14) {
        /*
            r9 = this;
            if (r11 == 0) goto L_0x00a9
            java.util.Iterator r0 = r11.iterator()
            r1 = 0
            r2 = r1
        L_0x0008:
            boolean r3 = r0.hasNext()
            r4 = 0
            if (r3 == 0) goto L_0x0057
            java.lang.Object r3 = r0.next()
            com.samsung.android.gallery.module.data.MediaItem r3 = (com.samsung.android.gallery.module.data.MediaItem) r3
            boolean r5 = r3.isFolder()
            if (r5 == 0) goto L_0x004b
            com.samsung.android.gallery.module.data.MediaItem[] r4 = r3.getAlbumsInFolder()
            int r5 = r4.length
            r6 = r1
        L_0x0021:
            if (r6 >= r5) goto L_0x0034
            r7 = r4[r6]
            int r8 = r7.getAlbumID()
            if (r12 != r8) goto L_0x0031
            r4 = r3
            com.samsung.android.gallery.module.dataset.FolderItem r4 = (com.samsung.android.gallery.module.dataset.FolderItem) r4
        L_0x002e:
            r12 = r4
            r4 = r7
            goto L_0x0058
        L_0x0031:
            int r6 = r6 + 1
            goto L_0x0021
        L_0x0034:
            com.samsung.android.gallery.module.data.MediaItem[] r4 = r3.getItemsInFolder()
            int r5 = r4.length
            r6 = r1
        L_0x003a:
            if (r6 >= r5) goto L_0x0054
            r7 = r4[r6]
            int r8 = r7.getAlbumID()
            if (r12 != r8) goto L_0x0048
            r4 = r3
            com.samsung.android.gallery.module.dataset.FolderItem r4 = (com.samsung.android.gallery.module.dataset.FolderItem) r4
            goto L_0x002e
        L_0x0048:
            int r6 = r6 + 1
            goto L_0x003a
        L_0x004b:
            int r5 = r3.getAlbumID()
            if (r12 != r5) goto L_0x0054
            r12 = r4
            r4 = r3
            goto L_0x0058
        L_0x0054:
            int r2 = r2 + 1
            goto L_0x0008
        L_0x0057:
            r12 = r4
        L_0x0058:
            if (r4 == 0) goto L_0x00a9
            java.lang.String r0 = "data://albums/current"
            java.lang.Object r1 = r10.read(r0)
            com.samsung.android.gallery.module.data.MediaItem r1 = (com.samsung.android.gallery.module.data.MediaItem) r1
            if (r13 != 0) goto L_0x0077
            boolean r13 = r4.isMergedAlbum()
            if (r13 == 0) goto L_0x0072
            r13 = r4
            com.samsung.android.gallery.module.dataset.FolderItem r13 = (com.samsung.android.gallery.module.dataset.FolderItem) r13
            com.samsung.android.gallery.module.dataset.FolderItem r9 = r9.clearFolderCoverItem(r13, r14)
            goto L_0x008a
        L_0x0072:
            com.samsung.android.gallery.module.data.MediaItem r9 = r9.clearCoverItem(r4, r14)
            goto L_0x008a
        L_0x0077:
            boolean r3 = r4.isMergedAlbum()
            if (r3 == 0) goto L_0x0086
            r3 = r4
            com.samsung.android.gallery.module.dataset.FolderItem r3 = (com.samsung.android.gallery.module.dataset.FolderItem) r3
            r5 = 1
            com.samsung.android.gallery.module.dataset.FolderItem r9 = r9.createFolderCoverItem(r3, r13, r14, r5)
            goto L_0x008a
        L_0x0086:
            com.samsung.android.gallery.module.data.MediaItem r9 = createCoverItem(r4, r13, r14)
        L_0x008a:
            if (r12 == 0) goto L_0x0093
            r12.removeItem(r4)
            r12.addItem(r9)
            goto L_0x0099
        L_0x0093:
            r11.remove(r4)
            r11.add(r2, r9)
        L_0x0099:
            if (r1 == 0) goto L_0x00a8
            int r11 = r1.getAlbumID()
            int r12 = r9.getAlbumID()
            if (r11 != r12) goto L_0x00a8
            r10.publish(r0, r9)
        L_0x00a8:
            return r2
        L_0x00a9:
            r9 = -1
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.AlbumDataHelper.updateCoverItem(com.samsung.android.gallery.support.blackboard.Blackboard, java.util.List, int, java.lang.String, java.lang.String):int");
    }

    public int updateFolderAt(List<MediaItem> list, int i2, int i7, String str) {
        int i8;
        if (list != null) {
            i8 = list.size();
        } else {
            i8 = 0;
        }
        if (list != null && i2 < i8) {
            MediaItem mediaItem = list.get(i2);
            if (mediaItem.isFolder()) {
                mediaItem.setFolderInfo(i7, str);
                if (!SortByType.isSortByCustom()) {
                    Comparator<MediaItem> comparator = getComparator();
                    MediaItem mediaItem2 = list.get(i2);
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        if (comparator.compare(mediaItem2, list.get(i10)) <= 0) {
                            return i10;
                        }
                    }
                }
            }
        }
        return i2;
    }

    public static String getStringForCropRect(String str, CropRectInfo cropRectInfo) {
        StringBuilder t = C0212a.t(str, ";");
        t.append(cropRectInfo.getRectString());
        return t.toString();
    }

    public FolderItem createFolderCoverItem(FolderItem folderItem, String str, String str2, boolean z) {
        if (!FileUtils.exists(str)) {
            return folderItem;
        }
        FolderCoverItem folderCoverItem = folderItem.isMergedAlbum() ? new FolderCoverItem(sNameMergedComparator) : new FolderCoverItem();
        MediaItemBuilder.copyAlbumData(folderItem, folderCoverItem);
        MediaItemBuilder.setAlbumCover(folderCoverItem, str, str2);
        folderCoverItem.setFolderInfo(folderItem.getFolderID(), folderItem.getFolderName());
        folderCoverItem.setAlbumLevel(folderItem.isAlbumLvFirst() ? 1 : 0);
        folderCoverItem.setAlbumShowInfo(folderItem.isAlbumShowInfo());
        if (z && folderItem.getItemCount() != 0 && folderCoverItem.getItemCount() == 0) {
            for (MediaItem addItem : folderItem.getChildList()) {
                folderCoverItem.addItem(addItem);
            }
        }
        return folderCoverItem;
    }

    public FolderItem createNameMergedItem(int i2, Cursor cursor) {
        FolderItem createNameMergedItem = createNameMergedItem(i2, "", 0);
        do {
            createNameMergedItem.addItem(MediaItemLoader.load(cursor));
        } while (cursor.moveToNext());
        createNameMergedItem.cloneBasicInfo((MediaItem) null);
        MediaItem first = createNameMergedItem.getFirst();
        createNameMergedItem.setDisplayName(AlbumTitleHelper.getAlbumTitle(first.getAlbumID(), first.getDisplayName()));
        createNameMergedItem.setFolderName(createNameMergedItem.getDisplayName());
        return createNameMergedItem;
    }
}
