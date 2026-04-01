package com.samsung.android.gallery.app.ui.list.albums.mx;

import A.a;
import A4.I;
import Ad.C0720a;
import Fb.h;
import I4.g;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.mx.IMxAlbumsView;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.IAlbumsHeaderView;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderView;
import com.samsung.android.gallery.app.ui.viewholders.MxAlbumsDividerViewHolder;
import com.samsung.android.gallery.module.badge.BadgeHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.pinch.IPinchViewHolder;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsViewAdapter<V extends IMxAlbumsView> extends AlbumsViewAdapter<V> {
    private final ArrayList<Integer> mAlbumExpiryMargin;
    private MxAlbumCluster mCluster;
    private final int mDividerHeight;
    private final int mFirstDividerHeight;
    private IAlbumsHeaderView mHeaderView;
    private boolean mPendingScrollToEnd;
    private final int mSideGap;
    private final int mViewAllMinusEndMargin;

    public MxAlbumsViewAdapter(V v, String str) {
        super(v, str);
        createCluster();
        this.mFirstDividerHeight = v.getResources().getDimensionPixelOffset(R.dimen.mx_albums_divider_height);
        this.mDividerHeight = v.getResources().getDimensionPixelOffset(R.dimen.mx_albums_divider_height);
        this.mSideGap = v.getResources().getDimensionPixelOffset(R.dimen.album_grid_view_side_gap);
        this.mViewAllMinusEndMargin = v.getResources().getDimensionPixelOffset(R.dimen.mx_albums_divider_view_all_minus_end_margin);
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.mAlbumExpiryMargin = arrayList;
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            arrayList.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.album_type_margin_start_list_mx)));
            arrayList.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.album_type_margin_start_grid_mx)));
            arrayList.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.album_type_margin_start_grid_max_mx)));
            return;
        }
        arrayList.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.sharing_view_grid_expiry_icon_margin_3x)));
        arrayList.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.sharing_view_grid_expiry_icon_margin_3x)));
        arrayList.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.sharing_view_grid_expiry_icon_margin_2x)));
    }

    private void attachHeaderViewToHolder(ListViewHolder listViewHolder) {
        if (this.mHeaderView != null) {
            ViewUtils.removeSelf(this.mHeaderView.getView());
            ((ViewGroup) listViewHolder.getRootView()).addView(this.mHeaderView.getView(), 0);
        }
    }

    private boolean checkFirstTipCard() {
        return PreferenceCache.MxAlbumTipCard.getBoolean();
    }

    private boolean checkSecondTipCard() {
        if (PreferenceCache.MxAlbumTipCardCount.getInt() >= 5) {
            return true;
        }
        return false;
    }

    private void createCluster() {
        this.mCluster = new MxAlbumCluster(this.mMediaData, getGridSize(), ((IMxAlbumsView) this.mView).getSharingLimitCount());
    }

    private IAlbumsHeaderView createHeaderView() {
        MxAlbumsHeaderView mxAlbumsHeaderView = new MxAlbumsHeaderView(((IMxAlbumsView) this.mView).getEventContext());
        mxAlbumsHeaderView.setOnDataChangedListener(new h(21, this));
        return mxAlbumsHeaderView;
    }

    private void enableAlbumExpiryIcon(ListViewHolder listViewHolder) {
        if (PreferenceFeatures.OneUi41.SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE) {
            Optional.ofNullable(listViewHolder.getMediaItem()).ifPresent(new g(listViewHolder, 0));
        }
    }

    private void enableViewAllText(ListViewHolder listViewHolder) {
        if (((IMxAlbumsView) this.mView).isEssentialViewMode() && ViewHolderValue.isDivider(listViewHolder.getViewType())) {
            enableViewAll((MxAlbumsDividerViewHolder) listViewHolder);
        }
    }

    private int getDividerHeight(int i2) {
        if (ViewHolderValue.isFirstDivider(getItemViewType(i2))) {
            return this.mFirstDividerHeight;
        }
        return this.mDividerHeight;
    }

    private MediaItem getDividerItem(int i2) {
        int i7;
        AlbumKind albumKind = getAlbumKind(i2);
        if (isAlbumData(Integer.valueOf(i2))) {
            if (((IMxAlbumsView) this.mView).isEssentialViewMode()) {
                i7 = R.string.essential_albums;
            } else {
                i7 = R.string.all_albums_title;
            }
            albumKind.updateTitle(i7);
        }
        MediaItem mediaItem = new MediaItem();
        mediaItem.setTitle(getContext().getString(albumKind.getTitleResId()));
        mediaItem.setCount(this.mCluster.getCount(albumKind));
        return mediaItem;
    }

    /* access modifiers changed from: private */
    public boolean isAlbumData(Integer num) {
        if (getAlbumKind(num.intValue()).getIndex() == AlbumKind.REPRESENTATIVE_ALBUMS.getIndex()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createHeaderView$0() {
        notifyChanged(0);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$enableAlbumExpiryIcon$1(ListViewHolder listViewHolder, MediaItem mediaItem) {
        boolean z;
        if (mediaItem.isSharing()) {
            View decoView = listViewHolder.getDecoView(45);
            if (MediaItemMde.getAlbumExpiry(mediaItem) != 0) {
                z = true;
            } else {
                z = false;
            }
            ViewUtils.setVisibleOrGone(decoView, z);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPrepareDelete$2(Integer num) {
        MediaItem mediaItemFromCache = getMediaItemFromCache(num.intValue());
        if (mediaItemFromCache != null && !mediaItemFromCache.isSystemMergedAlbum()) {
            this.mDeletePositions.add(num);
        }
    }

    private void notifyChanged(int i2) {
        long j2;
        RecyclerView.ItemAnimator itemAnimator = this.mGalleryListView.getItemAnimator();
        if (itemAnimator != null) {
            j2 = itemAnimator.getChangeDuration();
            itemAnimator.setChangeDuration(1000);
        } else {
            j2 = 0;
        }
        notifyItemChanged(i2);
        if (itemAnimator != null) {
            itemAnimator.setChangeDuration(j2);
        }
    }

    private boolean supportTipGuideTipCard() {
        if (!((IMxAlbumsView) this.mView).isEssentialViewMode()) {
            return false;
        }
        if (checkFirstTipCard() || checkSecondTipCard()) {
            return true;
        }
        return false;
    }

    private void updateAlbumExpiryMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        View decoView = listViewHolder.getDecoView(45);
        if (decoView != null) {
            boolean z3 = true;
            Integer num = (Integer) a.c(1, i2, this.mAlbumExpiryMargin);
            if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
                SeApiCompat.setMarginsRelative(decoView, 0, 0, num.intValue(), 0);
            } else {
                SeApiCompat.setMarginsRelative(decoView, 0, num.intValue(), num.intValue(), 0);
            }
            if (!z && MediaItemMde.getAlbumExpiry(listViewHolder.getMediaItem()) == 0) {
                z3 = false;
            }
            ViewUtils.setVisibleOrGone(decoView, z3);
        }
    }

    private boolean updateBadge(ListViewHolder listViewHolder, int i2) {
        if (((IMxAlbumsView) this.mView).isEssentialViewMode() && ViewHolderValue.isDivider(listViewHolder.getItemViewType())) {
            if (isAlbumData(Integer.valueOf(i2))) {
                listViewHolder.updateDecoration(1, Boolean.valueOf(needBadgeOnViewAllOfEssentialAlbums()));
            } else {
                listViewHolder.updateDecoration(1, Boolean.valueOf(BadgeHelper.hasNewSharedAlbums()));
            }
        }
        return true;
    }

    public boolean checkIfEmpty() {
        if (this.mCluster.getViewCount() == 0) {
            return true;
        }
        return false;
    }

    public void destroy() {
        super.destroy();
        IAlbumsHeaderView iAlbumsHeaderView = this.mHeaderView;
        if (iAlbumsHeaderView != null) {
            iAlbumsHeaderView.destroy();
        }
    }

    public void enableHeaderView(boolean z) {
        IAlbumsHeaderView iAlbumsHeaderView = this.mHeaderView;
        if (iAlbumsHeaderView != null) {
            iAlbumsHeaderView.onEnableHeaderView(z);
        }
    }

    public void enableViewAll(MxAlbumsDividerViewHolder mxAlbumsDividerViewHolder) {
        mxAlbumsDividerViewHolder.setViewAllTextEnabled();
    }

    public AlbumKind getAlbumKind(int i2) {
        return this.mCluster.getAlbumKind(i2 - getHeaderCount());
    }

    public Bitmap getBitmapForEmptyAlbum(MediaItem mediaItem, boolean z) {
        if (mediaItem == null || !mediaItem.isSharing() || mediaItem.getCount() != 0) {
            return super.getBitmapForEmptyAlbum(mediaItem, z);
        }
        return ThumbnailLoader.getInstance().getReplacedThumbnail(getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    public Bitmap getBrokenMediaItem(MediaItem mediaItem) {
        if (mediaItem != null && mediaItem.isSharing() && TextUtils.isEmpty(mediaItem.getPath())) {
            if (mediaItem.getCount() != 0 && Features.isEnabled(Features.SUPPORT_DOWNLOAD_BROKEN_SHARED_COVER)) {
                this.mBlackBoard.post("data://download_shared_cover", mediaItem);
            }
            return ThumbnailLoader.getInstance().getReplacedThumbnail(getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
        } else if (mediaItem == null) {
            return null;
        } else {
            return super.getBrokenMediaItem(mediaItem);
        }
    }

    public ArrayList<MediaItem> getFullData() {
        MediaData mdeData = this.mCluster.getMdeData("location://sharing/albums/spaces");
        if (mdeData != null) {
            return mdeData.getFullData();
        }
        return null;
    }

    public int getHeaderCount() {
        return 1;
    }

    public TextView getHeaderDescriptionView() {
        IAlbumsHeaderView iAlbumsHeaderView = this.mHeaderView;
        if (iAlbumsHeaderView != null) {
            return (TextView) iAlbumsHeaderView.getView().findViewById(R.id.headerContent);
        }
        return null;
    }

    public int getHeaderDescriptionWidthOffset() {
        IAlbumsHeaderView iAlbumsHeaderView = this.mHeaderView;
        if (iAlbumsHeaderView == null) {
            return 0;
        }
        int dimensionPixelOffset = this.mHeaderView.getView().getResources().getDimensionPixelOffset(R.dimen.tip_card_padding_end) + iAlbumsHeaderView.getView().getResources().getDimensionPixelOffset(R.dimen.tip_card_padding_start);
        return this.mHeaderView.getView().getResources().getDimensionPixelOffset(R.dimen.tip_card_text_layout_padding_end) + this.mHeaderView.getView().getResources().getDimensionPixelOffset(R.dimen.tip_card_text_layout_padding_start) + dimensionPixelOffset;
    }

    public View getHeaderView() {
        IAlbumsHeaderView iAlbumsHeaderView = this.mHeaderView;
        if (iAlbumsHeaderView != null) {
            return iAlbumsHeaderView.getView();
        }
        return null;
    }

    public int getHeaderViewHeight() {
        IAlbumsHeaderView iAlbumsHeaderView = this.mHeaderView;
        if (iAlbumsHeaderView != null) {
            return iAlbumsHeaderView.getHeight();
        }
        return 0;
    }

    public int getHintItemViewHeight(int i2, int i7, int i8) {
        if (isHeader(i2)) {
            return getHeaderViewHeight();
        }
        if (isDivider(i2)) {
            return getDividerHeight(i2);
        }
        return i7 / i8;
    }

    public int getHintItemViewType(int i2, int i7) {
        if (i2 == 0) {
            return -3;
        }
        return this.mCluster.getItemViewType(i2 - getHeaderCount(), i7);
    }

    public int getHintSpanSize(int i2, int i7) {
        if (isData(i2)) {
            return this.mCluster.getColumnSpan(i2 - getHeaderCount(), i7);
        }
        return i7;
    }

    public int getHintStartSpan(int i2, int i7) {
        if (isData(i2)) {
            return this.mCluster.getStartSpan(i2 - getHeaderCount(), i7);
        }
        return 0;
    }

    public int getHintViewPosition(int i2) {
        return getHeaderCount() + this.mCluster.getViewPosition(i2);
    }

    public int getItemCount() {
        return getHeaderCount() + this.mCluster.getViewCount();
    }

    public int getItemHeight(int i2) {
        if (isHeader(i2)) {
            return getHeaderViewHeight();
        }
        if (isDivider(i2)) {
            return getDividerHeight(i2);
        }
        return super.getItemHeight(i2);
    }

    public int getItemViewType(int i2) {
        return getHintItemViewType(i2, getGridSize());
    }

    public int getMaxScroll() {
        int gridSize = getGridSize();
        int i2 = this.mItemHeight;
        if (i2 < 0) {
            i2 = ((IMxAlbumsView) this.mView).getLayoutManager().getHintWidthSpace(gridSize) / gridSize;
        }
        int maxScroll = this.mCluster.getMaxScroll(gridSize, i2, this.mFirstDividerHeight, this.mDividerHeight);
        return this.mGalleryListView.getPaddingBottom() + getHeaderViewHeight() + maxScroll;
    }

    public MediaItem getMediaItemFromCache(int i2) {
        if (isDivider(i2)) {
            return null;
        }
        return super.getMediaItemFromCache(i2);
    }

    public int getMediaItemPosition(int i2) {
        return getMediaItemPosition(i2, getGridSize());
    }

    public int getNextRowIndex(int i2, int i7) {
        int i8;
        if (isData(i2)) {
            i8 = getHeaderCount() + this.mCluster.getNextRowIndex(i2 - getHeaderCount(), getGridSize(), i7);
        } else {
            i8 = i2 + 1;
        }
        return Math.min(i8, i7 - 1);
    }

    public int getPrevRowIndex(int i2) {
        if (!isData(i2)) {
            return i2 - 1;
        }
        return Math.max(0, getHeaderCount() + this.mCluster.getPrevRowIndex(i2 - getHeaderCount(), getGridSize()));
    }

    public int getSelectedDataCount(ArrayList<Integer> arrayList) {
        return (int) arrayList.stream().filter(new I(6, this)).count();
    }

    public int getSpanSize(int i2) {
        if (isHeader(i2)) {
            return ((IMxAlbumsView) this.mView).getLayoutManager().getSpanCount();
        }
        return getHintSpanSize(i2, getGridSize());
    }

    public int getViewHolderMargin(int i2) {
        return ((this.mSideGap - ((IMxAlbumsView) this.mView).getLayoutManager().getSpacing(i2)) * 2) - this.mViewAllMinusEndMargin;
    }

    public int getViewPosition(int i2) {
        return getHintViewPosition(i2);
    }

    public GalleryListAdapter.SelectableType isItemSelectable(int i2) {
        if (!isAlbumData(Integer.valueOf(i2))) {
            return GalleryListAdapter.SelectableType.UNSUPPORTED;
        }
        return super.isItemSelectable(i2);
    }

    public boolean isNeedToUpdateDecoView(int i2) {
        return isAlbumData(Integer.valueOf(i2));
    }

    public boolean needToInflateHeaderView() {
        if (Features.isEnabled(Features.IS_RDU_MODE) || this.mHeaderView != null) {
            return false;
        }
        if (this.mCluster.getInvitationCount() > 0 || supportTipGuideTipCard()) {
            return true;
        }
        return false;
    }

    public void onDataChanged() {
        super.onDataChanged();
        createCluster();
        if (needToInflateHeaderView()) {
            notifyChanged(0);
        }
        if (this.mPendingScrollToEnd) {
            smoothScrollToPosition(getItemCount() - 1);
            this.mPendingScrollToEnd = false;
        }
    }

    public void onFamilyAlbumCreated() {
        this.mPendingScrollToEnd = true;
    }

    public void onPrepareDelete(LinearLayoutManager linearLayoutManager) {
        this.mDeletePositions.clear();
        ArrayList<Integer> selectedList = this.mSelectionManager.getSelectedList();
        if (selectedList != null && !selectedList.isEmpty()) {
            selectedList.forEach(new E9.a(27, this));
        }
        if (!this.mDeletePositions.isEmpty()) {
            this.mDeletePositions.sort(Comparator.reverseOrder());
        }
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        while (findFirstVisibleItemPosition <= findLastVisibleItemPosition) {
            if (!this.mDeletePositions.contains(Integer.valueOf(findFirstVisibleItemPosition))) {
                findFirstVisibleItemPosition++;
            } else {
                return;
            }
        }
        this.mDeletePositions.clear();
    }

    public void onStartDelete() {
        if (!this.mDeletePositions.isEmpty()) {
            Iterator<Integer> it = this.mDeletePositions.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                this.mMediaData.removeItemAt(getMediaItemPosition(intValue));
                updateCluster();
                notifyItemRemoved(intValue);
            }
            this.mDeletePositions.clear();
        }
    }

    public void refreshHeaderView(boolean z) {
        IAlbumsHeaderView iAlbumsHeaderView = this.mHeaderView;
        if (iAlbumsHeaderView != null) {
            iAlbumsHeaderView.refreshHeader(z, false);
        }
    }

    public boolean selectAll() {
        int i2;
        boolean selectAll = super.selectAll();
        if (this.mSelectionManager != null) {
            if (getAllAlbumData() != null) {
                i2 = getAllAlbumData().size();
            } else {
                i2 = 0;
            }
            this.mSelectionManager.replaceSelection(i2, (List) Arrays.stream(IntStream.range(0, i2).map(new I4.h(this)).toArray()).boxed().collect(Collectors.toCollection(new C0720a(1))));
        }
        return selectAll;
    }

    public void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2) {
        int spacing = this.mSideGap - ((IMxAlbumsView) this.mView).getLayoutManager().getSpacing(i2);
        ViewMarginUtils.setStartMargin(iPinchViewHolder.getDividerView(), spacing);
        ViewMarginUtils.setEndMargin(iPinchViewHolder.getDividerView(), spacing - this.mViewAllMinusEndMargin);
    }

    public boolean supportHeader() {
        return true;
    }

    public void updateAlbumTypeMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        super.updateAlbumTypeMargin(listViewHolder, i2, z);
        updateAlbumExpiryMargin(listViewHolder, i2, z);
    }

    public void updateCluster() {
        createCluster();
    }

    public void updateExtraViewHolderMargin() {
        int findFirstVisibleItemPositionCompat;
        GalleryListView galleryListView = this.mGalleryListView;
        if (galleryListView != null && (findFirstVisibleItemPositionCompat = galleryListView.findFirstVisibleItemPositionCompat()) > 1) {
            notifyItemRangeChanged(0, findFirstVisibleItemPositionCompat);
        }
    }

    public void updateForMoveMode(ListViewHolder listViewHolder) {
        if (!ViewHolderValue.isHeader(listViewHolder.getViewType()) && !ViewHolderValue.isDivider(listViewHolder.getViewType())) {
            super.updateForMoveMode(listViewHolder);
        }
    }

    public void updateGridSize() {
        this.mCluster.updateGridSize(getGridSize(), ((IMxAlbumsView) this.mView).getSharingLimitCount());
    }

    public void updateSharingBadge() {
        notifyItemRangeChanged("sharedBadge");
    }

    public MxAlbumsViewHolderFactory createViewHolderFactory(Context context) {
        return new MxAlbumsViewHolderFactory(context);
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        if (isDivider(i2)) {
            return null;
        }
        return super.getMediaItemFromCache(i2, i7);
    }

    public int getMediaItemPosition(int i2, int i7) {
        return this.mCluster.getDataPosition(i2 - getHeaderCount());
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        IAlbumsHeaderView iAlbumsHeaderView;
        if (list.contains("header")) {
            if (ViewHolderValue.isHeader(listViewHolder.getViewType()) && (iAlbumsHeaderView = this.mHeaderView) != null) {
                iAlbumsHeaderView.refreshHeader(true, false);
            }
        } else if (!updateBadge(listViewHolder, i2) || !list.contains("sharedBadge")) {
            if (ViewHolderValue.isHeader(listViewHolder.getViewType()) && needToInflateHeaderView()) {
                this.mHeaderView = createHeaderView();
            }
            if (!list.contains("checkBox") || !isData(i2) || isAlbumData(Integer.valueOf(i2))) {
                super.onBindViewHolder(listViewHolder, i2, list);
                enableViewAllText(listViewHolder);
                enableAlbumExpiryIcon(listViewHolder);
                return;
            }
            setHolderEnabled(listViewHolder, !isSelectionMode());
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        if (isHeader(i2)) {
            attachHeaderViewToHolder(listViewHolder);
        } else if (isData(i2)) {
            super.onBindViewHolder(listViewHolder, i2);
            if (isSelectionMode() && !isAlbumData(Integer.valueOf(i2))) {
                setHolderEnabled(listViewHolder, false);
            }
        } else if (isDivider(i2)) {
            listViewHolder.bind(getDividerItem(i2));
            setListeners(listViewHolder);
            setViewHolderMargin(listViewHolder, getGridSize());
            updateBadge(listViewHolder, i2);
        } else {
            setListeners(listViewHolder);
            setViewHolderMargin(listViewHolder, getGridSize());
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, int i7) {
        if (isDivider(i2)) {
            onBindViewHolder(listViewHolder, i2);
        } else {
            super.onBindViewHolder(listViewHolder, i2, i7);
        }
    }
}
