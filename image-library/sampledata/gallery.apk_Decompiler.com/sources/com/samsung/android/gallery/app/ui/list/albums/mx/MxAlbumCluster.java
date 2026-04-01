package com.samsung.android.gallery.app.ui.list.albums.mx;

import A4.C0384t;
import Gb.a;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.module.data.MxClusterItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumCluster {
    ArrayList<MxClusterItem> mClusterItems = new ArrayList<>();
    ArrayList<Integer> mDividerIndex = new ArrayList<>();
    private int mGridSize;
    private final MediaData mMediaData;
    private int mSharingLimitCount;
    private Integer mViewCount;

    public MxAlbumCluster(MediaData mediaData, int i2, int i7) {
        this.mMediaData = mediaData;
        this.mGridSize = i2;
        this.mSharingLimitCount = i7;
        composeMxClusterItems();
    }

    private void clearDividerIndexList() {
        this.mDividerIndex.clear();
    }

    private void composeMxClusterItems() {
        int myAlbumCount = getMyAlbumCount();
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums) || myAlbumCount > 0) {
            this.mClusterItems.add(new MxClusterItem.Builder().setClusterIndex(AlbumKind.REPRESENTATIVE_ALBUMS.getIndex()).setDataVersion(this.mMediaData.getDataVersion()).setCount(myAlbumCount).build());
        }
        int sharingCount = getSharingCount();
        if (sharingCount > 0) {
            this.mClusterItems.add(new MxClusterItem.Builder().setClusterIndex(AlbumKind.SHARED_ALBUMS.getIndex()).setDataVersion(getSharingDataVersion()).setCount(sharingCount).build());
        }
    }

    private int getClusterIndex(int i2) {
        ArrayList<Integer> dividerIndexList = getDividerIndexList();
        if (dividerIndexList.size() > 1 && dividerIndexList.get(1).intValue() <= i2) {
            return 1;
        }
        return 0;
    }

    private MxClusterItem getClusterItem(AlbumKind albumKind) {
        Iterator<MxClusterItem> it = this.mClusterItems.iterator();
        while (it.hasNext()) {
            MxClusterItem next = it.next();
            if (next.getClusterIndex() == albumKind.getIndex()) {
                return next;
            }
        }
        return null;
    }

    private int getClusterItemCount(MxClusterItem mxClusterItem) {
        return Math.max(mxClusterItem.getCount(), 0);
    }

    private ArrayList<Integer> getDividerIndexList() {
        if (this.mDividerIndex.size() == 0) {
            setDividerTypePositionMap();
        }
        return this.mDividerIndex;
    }

    private int getMyAlbumCount() {
        return this.mMediaData.getCount();
    }

    private int getRowCount(int i2, int i7) {
        int i8;
        int i10 = i7 / i2;
        if (i7 % i2 == 0) {
            i8 = 0;
        } else {
            i8 = 1;
        }
        return i10 + i8;
    }

    private int getSharingCount() {
        int intValue = ((Integer) Optional.ofNullable(getMdeData("location://sharing/albums/spaces")).map(new C0384t(22)).orElse(0)).intValue();
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums)) {
            return Math.min(intValue, this.mSharingLimitCount);
        }
        return intValue;
    }

    private int getSharingDataVersion() {
        return ((Integer) Optional.ofNullable(getMdeData("location://sharing/albums/spaces")).map(new a(16)).orElse(0)).intValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getViewCount$2(AtomicInteger atomicInteger, MxClusterItem mxClusterItem) {
        atomicInteger.addAndGet(getClusterItemCount(mxClusterItem) + 1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDividerTypePositionMap$3(AtomicInteger atomicInteger, MxClusterItem mxClusterItem) {
        this.mDividerIndex.add(Integer.valueOf(atomicInteger.getAndAdd(getClusterItemCount(mxClusterItem) + 1)));
    }

    private boolean needFullSpan(int i2, int i7) {
        int itemViewType = getItemViewType(i2, i7);
        if (ViewHolderValue.isDivider(itemViewType) || itemViewType == -5) {
            return true;
        }
        return false;
    }

    private void resetViewCount() {
        this.mViewCount = null;
    }

    private void setDividerTypePositionMap() {
        this.mClusterItems.forEach(new I4.a(this, new AtomicInteger(0), 1));
    }

    public AlbumKind getAlbumKind(int i2) {
        return AlbumKind.value(this.mClusterItems.get(getClusterIndex(i2)).getClusterIndex());
    }

    public int getColumnSpan(int i2, int i7) {
        if (needFullSpan(i2, i7)) {
            return i7;
        }
        return 1;
    }

    public int getCount(AlbumKind albumKind) {
        MxClusterItem clusterItem = getClusterItem(albumKind);
        if (clusterItem != null) {
            return clusterItem.getCount();
        }
        return 0;
    }

    public int getDataPosition(int i2) {
        int clusterIndex = getClusterIndex(i2);
        for (int i7 = 0; i7 < clusterIndex; i7++) {
            i2 -= this.mClusterItems.get(i7).getCount() + 1;
        }
        return (this.mClusterItems.get(clusterIndex).getClusterIndex() * 10000000) + (i2 - 1);
    }

    public int getInvitationCount() {
        return ((Integer) Optional.ofNullable(getMdeData("location://sharing/albums/invitations")).map(new C0384t(22)).orElse(0)).intValue();
    }

    public int getItemViewType(int i2, int i7) {
        if (i2 == 0) {
            return -1;
        }
        if (getDividerIndexList().contains(Integer.valueOf(i2))) {
            return -2;
        }
        if (this.mClusterItems.get(getClusterIndex(i2)).isEmpty()) {
            return -5;
        }
        if (i7 == 1) {
            return 1;
        }
        return 2;
    }

    public int getMaxScroll(int i2, int i7, int i8, int i10) {
        int i11;
        int size = this.mClusterItems.size();
        int i12 = 0;
        for (int i13 = 0; i13 < size; i13++) {
            int rowCount = getRowCount(i2, this.mClusterItems.get(i13).getCount()) * i7;
            if (i13 == 0) {
                i11 = i8;
            } else {
                i11 = i10;
            }
            i12 += rowCount + i11;
        }
        return i12;
    }

    public MediaData getMdeData(String str) {
        return (MediaData) Optional.ofNullable(this.mMediaData.getChildMediaData("location://sharing/albums")).map(new C9.a(str, 2)).orElse((Object) null);
    }

    public int getNextRowIndex(int i2, int i7, int i8) {
        if (needFullSpan(i2, i7)) {
            return i2 + 1;
        }
        for (int i10 = 1; i10 <= i7; i10++) {
            int i11 = i2 + i10;
            if (i11 < i8 && getStartSpan(i11, i7) == 0) {
                return i11;
            }
        }
        return Math.min(i2 + i7, i8 - 1);
    }

    public int getPrevRowIndex(int i2, int i7) {
        if (needFullSpan(i2, i7)) {
            return i2 - 1;
        }
        for (int i8 = 1; i8 <= i7; i8++) {
            int i10 = i2 - i8;
            if (i10 > 0 && needFullSpan(i10, i7)) {
                return i10;
            }
        }
        return Math.max(0, i2 - i7);
    }

    public int getStartSpan(int i2, int i7) {
        if (needFullSpan(i2, i7)) {
            return 0;
        }
        return ((i2 - getDividerIndexList().get(getClusterIndex(i2)).intValue()) - 1) % GridValue.revert(i7);
    }

    public int getViewCount() {
        if (this.mViewCount == null) {
            AtomicInteger atomicInteger = new AtomicInteger();
            this.mClusterItems.forEach(new I4.a(this, atomicInteger, 0));
            this.mViewCount = Integer.valueOf(atomicInteger.get());
        }
        return this.mViewCount.intValue();
    }

    public int getViewPosition(int i2) {
        int i7 = i2 / 10000000;
        int i8 = i2 % 10000000;
        Iterator<MxClusterItem> it = this.mClusterItems.iterator();
        while (it.hasNext()) {
            MxClusterItem next = it.next();
            if (next.getClusterIndex() >= i7) {
                break;
            }
            i8 += next.getCount() + 1;
        }
        return i8 + 1;
    }

    public void updateGridSize(int i2, int i7) {
        if (this.mGridSize != i2 || this.mSharingLimitCount != i7) {
            this.mGridSize = i2;
            this.mSharingLimitCount = i7;
            resetViewCount();
            clearDividerIndexList();
            MxClusterItem clusterItem = getClusterItem(AlbumKind.SHARED_ALBUMS);
            if (clusterItem != null) {
                clusterItem.setCount(getSharingCount());
            }
        }
    }
}
