package com.samsung.android.gallery.module.service.support;

import N2.j;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.support.utils.FileUtils;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteAlbumInfo {
    private int mAlbumCount = 0;
    private int mAutoAlbumCount = 0;
    private int mAutoAlbumItemCount = 0;
    private boolean mCloudInvolved = false;
    private int mEmptyAlbumCount = 0;
    private int mEmptyGroupCount = 0;
    private boolean mFavoriteAlbumInvolved = false;
    private int mGroupCount = 0;
    private ArrayList<Integer> mIds = new ArrayList<>();
    private final IDeleteInfo mInfo;
    private int mItemCount = 0;
    private boolean mRecentAlbumInvolved = false;
    private boolean mSdCardInvolved = false;

    public DeleteAlbumInfo(IDeleteInfo iDeleteInfo) {
        this.mInfo = iDeleteInfo;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCloudInvolved$0(QueryParams queryParams) {
        queryParams.addAlbumIds((Collection<Integer>) this.mIds).setStorageTypes(QueryParams.DbStorageType.includeCloud);
    }

    public void addAlbumId(int i2) {
        if (!this.mIds.contains(Integer.valueOf(i2))) {
            this.mIds.add(Integer.valueOf(i2));
        }
    }

    public int getAlbumCount() {
        return this.mAlbumCount;
    }

    public int getAutoAlbumCount() {
        return this.mAutoAlbumCount;
    }

    public int getAutoAlbumItemCount() {
        return this.mAutoAlbumItemCount;
    }

    public int getEmptyAlbumCount() {
        return this.mEmptyAlbumCount;
    }

    public int getEmptyGroupCount() {
        return this.mEmptyGroupCount;
    }

    public int getGroupCount() {
        return this.mGroupCount;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    public int getTotalCount() {
        return this.mAlbumCount + this.mGroupCount;
    }

    public void increaseAlbum() {
        this.mAlbumCount++;
    }

    public void increaseAutoAlbum() {
        this.mAutoAlbumCount++;
    }

    public void increaseAutoAlbumItem(int i2) {
        this.mAutoAlbumItemCount += i2;
    }

    public void increaseEmptyAlbum() {
        this.mEmptyAlbumCount++;
    }

    public void increaseEmptyGroup() {
        this.mEmptyGroupCount++;
    }

    public void increaseGroup() {
        this.mGroupCount++;
    }

    public void increaseItem(int i2) {
        this.mItemCount += i2;
    }

    public boolean isCloudInvolved() {
        return this.mCloudInvolved;
    }

    public boolean isFavoriteAlbumInvolved() {
        return this.mFavoriteAlbumInvolved;
    }

    public boolean isOnlyAutoNEmptyAlbumSelected() {
        int i2 = this.mAutoAlbumCount;
        if (i2 <= 0 || i2 + this.mEmptyAlbumCount != this.mAlbumCount) {
            return false;
        }
        return true;
    }

    public boolean isRecentAlbumInvolved() {
        return this.mRecentAlbumInvolved;
    }

    public boolean isSdCardInvolved() {
        return this.mSdCardInvolved;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0027 A[SYNTHETIC, Splitter:B:14:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCloudInvolved() {
        /*
            r3 = this;
            java.util.ArrayList<java.lang.Integer> r0 = r3.mIds
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0009
            goto L_0x002a
        L_0x0009:
            java.lang.String r0 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALBUMS     // Catch:{ Exception -> 0x0036 }
            com.samsung.android.sum.core.descriptor.b r1 = new com.samsung.android.sum.core.descriptor.b     // Catch:{ Exception -> 0x0036 }
            r2 = 25
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x0036 }
            android.database.Cursor r0 = com.samsung.android.gallery.database.dal.DbCompat.query(r0, r1)     // Catch:{ Exception -> 0x0036 }
            if (r0 == 0) goto L_0x0022
            int r1 = r0.getCount()     // Catch:{ all -> 0x0020 }
            if (r1 <= 0) goto L_0x0022
            r1 = 1
            goto L_0x0023
        L_0x0020:
            r3 = move-exception
            goto L_0x002b
        L_0x0022:
            r1 = 0
        L_0x0023:
            r3.mCloudInvolved = r1     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x002a
            r0.close()     // Catch:{ Exception -> 0x0036 }
        L_0x002a:
            return
        L_0x002b:
            if (r0 == 0) goto L_0x0035
            r0.close()     // Catch:{ all -> 0x0031 }
            goto L_0x0035
        L_0x0031:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ Exception -> 0x0036 }
        L_0x0035:
            throw r3     // Catch:{ Exception -> 0x0036 }
        L_0x0036:
            r3 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "unable to query cloud album. "
            r0.<init>(r1)
            java.lang.String r1 = "DeleteAlbumInfo"
            A.a.s(r3, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.service.support.DeleteAlbumInfo.setCloudInvolved():void");
    }

    public void setFavoriteAlbumInvolved() {
        this.mFavoriteAlbumInvolved = true;
    }

    public void setRecentAlbumInvolved() {
        this.mRecentAlbumInvolved = true;
    }

    public void setSdCardInvolved(boolean z, String str) {
        if (!this.mSdCardInvolved && z && FileUtils.isInRemovableStorage(str)) {
            this.mSdCardInvolved = true;
        }
    }

    public boolean showDeleteAllWarning() {
        return this.mInfo.showDeleteAllWarning();
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mInfo.getBaseInfo());
        sb2.append("\n AlbumCount{a:");
        sb2.append(this.mAlbumCount);
        sb2.append(",g:");
        sb2.append(this.mGroupCount);
        sb2.append(",ea:");
        sb2.append(this.mEmptyAlbumCount);
        sb2.append(",eg:");
        sb2.append(this.mEmptyGroupCount);
        sb2.append(",i:");
        sb2.append(this.mItemCount);
        sb2.append(",sd:");
        return j.h(sb2, this.mSdCardInvolved, "}");
    }

    public boolean useTrash() {
        return this.mInfo.useTrash();
    }
}
