package com.samsung.android.gallery.app.ui.list.timeline;

import A.a;
import A4.M;
import L5.b;
import N2.j;
import O3.y;
import Qb.c;
import Qb.e;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.abstraction.YearThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesHeaderViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.Cluster;
import com.samsung.android.gallery.app.ui.list.timeline.ITimelineView;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.list.YearQueryCache;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.thumbnail.YearThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryPinchView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.android.gallery3d.R;
import e5.C0451a;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimelineViewAdapter<V extends ITimelineView> extends PicturesHeaderViewAdapter<V> implements SectionIndexer {
    private TimelineSelectionModeAnimator mSelectionModeAnimator;
    private Cursor mYearCursor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PreviousScrollData {
        /* access modifiers changed from: private */
        public final ArrayList<Integer> mDividerOffsets;
        /* access modifiers changed from: private */
        public final ArrayList<MediaItem> mDividers;
        /* access modifiers changed from: private */
        public MediaItem mFirstItem;
        /* access modifiers changed from: private */
        public final ArrayList<Long> mItemIds;
        /* access modifiers changed from: private */
        public final ArrayList<Integer> mItemOffsets;

        public /* synthetic */ PreviousScrollData(int i2) {
            this();
        }

        private PreviousScrollData() {
            this.mDividerOffsets = new ArrayList<>();
            this.mItemOffsets = new ArrayList<>();
            this.mItemIds = new ArrayList<>();
            this.mDividers = new ArrayList<>();
            this.mFirstItem = null;
        }
    }

    public TimelineViewAdapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
    }

    private boolean bindYearThumbnail(ListViewHolder listViewHolder, ArrayList<MediaItem> arrayList, int i2) {
        if (listViewHolder.getImage() == null) {
            return false;
        }
        listViewHolder.getImage().setTag(arrayList.get(0).getPath());
        YearThumbnailRequestHolder yearThumbnailRequestHolder = new YearThumbnailRequestHolder(listViewHolder, arrayList, (ThumbnailLoadedListener) null);
        Bitmap yearThumbnailCache = yearThumbnailRequestHolder.getYearThumbnailCache(i2);
        if (yearThumbnailCache == null) {
            Bitmap yearThumbnailCacheAny = yearThumbnailRequestHolder.getYearThumbnailCacheAny(i2);
            if (yearThumbnailCacheAny == null) {
                yearThumbnailCacheAny = yearThumbnailRequestHolder.getPartialYearThumbnail(i2);
            }
            if (yearThumbnailCacheAny != null) {
                listViewHolder.bindImage(yearThumbnailCacheAny);
            }
            return false;
        }
        listViewHolder.bindImage(yearThumbnailCache);
        return true;
    }

    private View createMultipleHeaderContainer(Context context) {
        if (context != null) {
            return LayoutInflater.from(context).inflate(R.layout.multiple_header_view, (ViewGroup) null);
        }
        return null;
    }

    private QueryParams createQueryParams() {
        QueryParams queryParams = new QueryParams();
        if (SimilarPhotoHelper.isSimilarPhotoMode()) {
            queryParams.addGroupType(GroupType.SINGLE_TAKEN).addGroupType(GroupType.SIMILAR);
            return queryParams;
        }
        if (PickerUtil.isNormalLaunchMode(this.mBlackBoard)) {
            queryParams.addGroupType(GroupType.SINGLE_TAKEN);
        }
        return queryParams;
    }

    private int findViewPositionInMonthCluster(Cluster cluster, MediaItem mediaItem) {
        int viewPosition;
        long currentTimeMillis = System.currentTimeMillis();
        int[] findDataPositionRange = cluster.findDataPositionRange(mediaItem);
        long fileId = mediaItem.getFileId();
        int i2 = findDataPositionRange[0];
        while (true) {
            if (i2 > findDataPositionRange[1]) {
                String str = this.TAG;
                StringBuilder j2 = j.j(fileId, "findViewPositionInMonthCluster failed {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                j2.append(findDataPositionRange[0] + 2);
                j2.append(",n/a} +");
                j2.append(System.currentTimeMillis() - currentTimeMillis);
                Log.w(str, j2.toString());
                viewPosition = cluster.getViewPosition(findDataPositionRange[0]);
                break;
            }
            MediaItem loadMediaItemSync = loadMediaItemSync(i2);
            if (loadMediaItemSync != null && loadMediaItemSync.getFileId() == fileId) {
                viewPosition = cluster.getViewPosition(i2);
                break;
            }
            i2++;
        }
        return viewPosition + 1;
    }

    private ArrayList<MediaItem> getHintYearItemList(int i2, int i7) {
        int year = getYear(i2, i7);
        if (year > 0) {
            return this.mMultiClusterAdapter.getHintYearItemList(i7, year);
        }
        return null;
    }

    private PreviousScrollData getPreviousScrollInfo() {
        if (this.mBlackBoard.pop("data://keep_divider_position") == null) {
            return null;
        }
        PreviousScrollData previousScrollData = new PreviousScrollData(0);
        int findLastCompletelyVisibleItemPositionCompat = this.mGalleryListView.findLastCompletelyVisibleItemPositionCompat();
        for (int findFirstCompletelyVisibleItemPositionCompat = this.mGalleryListView.findFirstCompletelyVisibleItemPositionCompat(); findFirstCompletelyVisibleItemPositionCompat <= findLastCompletelyVisibleItemPositionCompat; findFirstCompletelyVisibleItemPositionCompat++) {
            ListViewHolder listViewHolder = (ListViewHolder) this.mGalleryListView.findViewHolderForAdapterPosition(findFirstCompletelyVisibleItemPositionCompat);
            if (listViewHolder != null) {
                int y = (int) listViewHolder.itemView.getY();
                MediaItem mediaItem = listViewHolder.getMediaItem();
                if (mediaItem != null) {
                    if (isDivider(findFirstCompletelyVisibleItemPositionCompat)) {
                        previousScrollData.mDividers.add(mediaItem);
                        previousScrollData.mDividerOffsets.add(Integer.valueOf(y));
                    } else {
                        if (previousScrollData.mFirstItem == null) {
                            previousScrollData.mFirstItem = mediaItem;
                        }
                        previousScrollData.mItemIds.add(Long.valueOf(mediaItem.getFileId()));
                        previousScrollData.mItemOffsets.add(Integer.valueOf(y));
                    }
                }
            }
        }
        return previousScrollData;
    }

    private View getSubHeaderView(int i2) {
        if (getHeaderView() == null) {
            return null;
        }
        if (i2 == 0) {
            return getHeaderView().findViewById(R.id.header_0);
        }
        View headerView = getHeaderView();
        if (i2 == 1) {
            return headerView.findViewById(R.id.header_1);
        }
        return headerView.findViewById(R.id.header_2);
    }

    private int getTargetMonthViewPosition(int i2, int i7, int i8) {
        int year = getYear(i2, i7);
        if (year < 0) {
            return 0;
        }
        ArrayList<MediaItem> hintYearItemList = this.mMultiClusterAdapter.getHintYearItemList(i7, year);
        return findViewPositionInMonthCluster(this.mMultiClusterAdapter.getCluster(this.mMaxMonthGridSize), hintYearItemList.get(Math.min(hintYearItemList.size() - 1, i8)));
    }

    private TimelineSelectionModeAnimator getTimelineSelectionModeAnimator() {
        if (this.mSelectionModeAnimator == null) {
            this.mSelectionModeAnimator = new TimelineSelectionModeAnimator(getContext().getResources());
        }
        return this.mSelectionModeAnimator;
    }

    private int[] getValidRange(MediaItem mediaItem) {
        int i2;
        int[] clusterItemRange = this.mMultiClusterAdapter.getClusterItemRange(mediaItem);
        if (clusterItemRange == null) {
            return new int[]{0, 0, 0};
        }
        boolean isHeader = this.mMultiClusterAdapter.isHeader(0);
        clusterItemRange[0] = clusterItemRange[0] + (isHeader ? 1 : 0);
        int i7 = clusterItemRange[1];
        if (i7 < 0) {
            i2 = getViewPosition(getDataCount() - 1);
        } else {
            i2 = i7 + isHeader;
        }
        clusterItemRange[1] = i2;
        return clusterItemRange;
    }

    private int getViewPosition(int i2, float f, float f5) {
        float f8 = (float) i2;
        return (getGridSize() * ((int) (f5 / f8))) + ((int) (f / f8));
    }

    private int getYear(int i2, int i7) {
        ClusterItem hintClusterItem = this.mMultiClusterAdapter.getHintClusterItem(i2 - 1, i7);
        if (hintClusterItem != null) {
            return UnsafeCast.toInt(hintClusterItem.getDateRaw().split("-")[0]);
        }
        return -1;
    }

    private boolean hasInvalidYearBitmapSize(Bitmap bitmap) {
        if (bitmap.getWidth() != this.mMaxGridSize * 32) {
            return true;
        }
        return false;
    }

    private boolean hasInvalidYearCount(ListViewHolder listViewHolder, int i2) {
        int i7;
        if (listViewHolder != null) {
            i7 = listViewHolder.getChildItemCount();
        } else {
            i7 = 0;
        }
        if (i7 == 0 || i7 == i2) {
            return false;
        }
        return true;
    }

    private boolean isYearViewType(ThumbnailRequestHolder thumbnailRequestHolder) {
        if (thumbnailRequestHolder == null || thumbnailRequestHolder.getViewHolder() == null || !isYearData(thumbnailRequestHolder.getViewHolder().getViewType())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataChanged$1(MediaData mediaData) {
        if (!YearQueryCache.getInstance().syncIfDataChanged(mediaData.getDataHash(), new C0451a(10, mediaData))) {
            notifyItemRangeChanged(0, getItemCount(), "update_year_bitmap");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataChanged$2() {
        if (this.mGalleryListView != null && isYear()) {
            Optional.ofNullable(this.mMediaData).ifPresent(new c(3, this));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onUpdateViewHolder$0(int i2, int i7, ListViewHolder listViewHolder) {
        ClusterItem hintClusterItem = this.mMultiClusterAdapter.getHintClusterItem(i2 - 1, i7);
        if (hintClusterItem != null) {
            int i8 = 0;
            int i10 = UnsafeCast.toInt(hintClusterItem.getDateRaw().split("-")[0]);
            ArrayList<MediaItem> hintYearItemList = this.mMultiClusterAdapter.getHintYearItemList(i7, i10);
            if (hintYearItemList != null) {
                i8 = hintYearItemList.size();
            }
            YearThumbnailLoader.getInstance().removeYearBitmap(YearThumbnailLoader.getYearThumbnailKey(String.valueOf(i10), i8, i7));
        }
        requestThumbnail(listViewHolder, i7);
    }

    private void restoreScrollData(PreviousScrollData previousScrollData) {
        if (previousScrollData != null) {
            int isHeader = this.mMultiClusterAdapter.isHeader(0);
            ArrayList b = previousScrollData.mDividers;
            for (int i2 = 0; i2 < b.size(); i2++) {
                int dividerIndex = this.mMultiClusterAdapter.getDividerIndex((MediaItem) b.get(i2));
                if (dividerIndex >= isHeader) {
                    this.mGalleryListView.scrollToPositionWithOffset(dividerIndex, ((Integer) previousScrollData.mDividerOffsets.get(i2)).intValue());
                    return;
                }
            }
            MediaItem c5 = previousScrollData.mFirstItem;
            if (c5 != null) {
                int[] validRange = getValidRange(c5);
                int i7 = validRange[0];
                while (i7 < validRange[1]) {
                    MediaItem loadMediaItemSync = loadMediaItemSync((i7 - validRange[2]) - isHeader);
                    if (loadMediaItemSync == null || !previousScrollData.mItemIds.contains(Long.valueOf(loadMediaItemSync.getFileId()))) {
                        i7++;
                    } else {
                        this.mGalleryListView.scrollToPositionWithOffset(i7, ((Integer) previousScrollData.mItemOffsets.get(previousScrollData.mItemIds.indexOf(Long.valueOf(loadMediaItemSync.getFileId())))).intValue());
                        return;
                    }
                }
            }
        }
    }

    public boolean bindViewHolderOnScrollIdle(ListViewHolder listViewHolder, int i2, int i7) {
        if (this.mGalleryListView.isScrollIdle() && isYearData(listViewHolder.getViewType())) {
            if (listViewHolder.isFake()) {
                return false;
            }
            ArrayList<MediaItem> hintYearItemList = getHintYearItemList(i2, i7);
            if (hintYearItemList == null || hintYearItemList.isEmpty()) {
                Log.d(this.TAG, "fail load year thumb sync");
            } else {
                listViewHolder.bind(hintYearItemList.get(0));
                return bindYearThumbnail(listViewHolder, hintYearItemList, i7);
            }
        }
        return super.bindViewHolderOnScrollIdle(listViewHolder, i2, i7);
    }

    public boolean checkIfEmpty() {
        Object obj;
        if (!super.checkIfEmpty()) {
            return false;
        }
        if (getHeaderView() == null) {
            return true;
        }
        View tipCardView = getTipCardView();
        if (tipCardView == null || (!"NO_MEDIA".equals(tipCardView.getTag()) && !"SecMPPermissionTipCard".equals(tipCardView.getTag()))) {
            String str = this.TAG;
            Boolean bool = Boolean.TRUE;
            if (tipCardView != null) {
                obj = tipCardView.getTag();
            } else {
                obj = "n/a";
            }
            Log.d(str, "checkIfEmpty", bool, obj);
            return true;
        }
        String str2 = this.TAG;
        Log.d(str2, "checkIfEmpty {false," + tipCardView.getTag() + "}");
        return false;
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new TimelineViewHolderFactory(context);
    }

    public void destroy() {
        Cursor cursor = this.mYearCursor;
        if (cursor != null) {
            cursor.close();
            this.mYearCursor = null;
        }
        super.destroy();
    }

    @Deprecated
    public Cursor getFilesIdsCursor(long j2, boolean z) {
        int i2;
        QueryParams maxFileId = createQueryParams().setDbKey(DbKey.TIMELINE_FILE_IDS).setMaxFileId(j2);
        if (z) {
            i2 = 200000;
        } else {
            i2 = -1;
        }
        return DbCompat.query(maxFileId.setLimit(i2));
    }

    public int getFirstVisibleIndex() {
        if (isSpannable()) {
            return ((ITimelineView) this.mView).getLayoutManager().getFirstVisibleRowIndex();
        }
        return super.getFirstVisibleIndex();
    }

    public int getFullLoadedCount() {
        if (isFullyLoaded()) {
            return super.getFullLoadedCount();
        }
        return new MpHelper(createQueryParams()).getTimelineFileCount();
    }

    public View getHeaderView(int i2) {
        View subHeaderView = getSubHeaderView(i2);
        if (subHeaderView != null) {
            return ((LinearLayout) subHeaderView).getChildAt(0);
        }
        return null;
    }

    public int getHintYearDataPosition(int i2, float f, float f5, int i7) {
        int max = Math.max(0, getViewPosition(getSpanSize(i2) / i7, f, f5));
        int year = getYear(i2, i7);
        if (year < 0) {
            return 0;
        }
        ArrayList<MediaItem> hintYearItemList = this.mMultiClusterAdapter.getHintYearItemList(i7, year);
        if (hintYearItemList.isEmpty()) {
            return 0;
        }
        return findViewPositionInMonthCluster(this.mMultiClusterAdapter.getCluster(this.mMaxMonthGridSize), hintYearItemList.get(Math.min(hintYearItemList.size() - 1, max)));
    }

    public int getItemHeight(int i2) {
        if (isSpannable()) {
            return ((ITimelineView) this.mView).getLayoutManager().getItemHeight(i2);
        }
        return super.getItemHeight(i2);
    }

    public long getItemId(int i2) {
        return -1;
    }

    public int getNextRowIndex(int i2, int i7) {
        if (isSpannable()) {
            return i2 + 1;
        }
        return super.getNextRowIndex(i2, i7);
    }

    public int getPositionForSection(int i2) {
        return 0;
    }

    public int getPrevRowIndex(int i2) {
        if (isSpannable()) {
            return i2 - 1;
        }
        return super.getPrevRowIndex(i2);
    }

    public int getRowHeight(int i2) {
        if (isSpannable()) {
            return ((ITimelineView) this.mView).getLayoutManager().getRowHeight(i2);
        }
        return super.getRowHeight(i2);
    }

    public int getSectionForPosition(int i2) {
        return getScrollIndex(i2);
    }

    public Object[] getSections() {
        return this.mMultiClusterAdapter.getScrollIndexDates();
    }

    public View getTipCardView() {
        View subHeaderView = getSubHeaderView(0);
        if (subHeaderView != null) {
            return ((ViewGroup) subHeaderView).getChildAt(0);
        }
        return null;
    }

    public boolean handleDataUpdateAnimation() {
        boolean z;
        GalleryListView galleryListView = this.mGalleryListView;
        if (galleryListView == null || !galleryListView.isComputingLayout()) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            Log.e(this.TAG, "Try to call notifyChanged while recyclerview is computing layout, it will cause IllegalStateException");
        }
        if (z || super.handleDataUpdateAnimation()) {
            return true;
        }
        return false;
    }

    public boolean isCheckingTargetCluster() {
        return ((ITimelineView) this.mView).isCheckingTargetCluster();
    }

    public void onAccessibilityYearClicked(View view, int i2) {
        String str;
        int gridSize = getGridSize();
        Integer num = (Integer) Optional.ofNullable(getHintYearItemList(i2, gridSize)).map(new b(16)).orElse(0);
        if (num.intValue() == 1) {
            str = AppResources.getString(R.string.speak_folder_name_1_item);
        } else {
            str = AppResources.getString(R.string.speak_folder_name_n_items, num);
        }
        view.setContentDescription(getYear(i2, gridSize) + ArcCommonLog.TAG_COMMA + str + ArcCommonLog.TAG_COMMA + AppResources.getString(R.string.speak_button));
    }

    public void onClusterUpdated() {
        GalleryListView galleryListView = this.mGalleryListView;
        if (galleryListView != null) {
            galleryListView.seslUpdateIndexTipSections();
        }
    }

    public void onDataChanged() {
        PreviousScrollData previousScrollInfo = getPreviousScrollInfo();
        super.onDataChanged();
        restoreScrollData(previousScrollInfo);
        if (PreferenceFeatures.OneUi30.YEAR_CLUSTERING && isYear()) {
            Log.d(this.TAG, "onDataChanged#update_year_bitmap", Integer.valueOf(getItemCount()));
            ThreadUtil.postOnUiThreadDelayed(new e(4, this), 300);
        }
    }

    public void onUpdateViewHolder(int i2) {
        int viewPosition = this.mMultiClusterAdapter.getViewPosition(i2);
        ListViewHolder listViewHolder = (ListViewHolder) this.mGalleryListView.findViewHolderForAdapterPosition(viewPosition);
        int gridSize = getGridSize();
        if (listViewHolder != null) {
            ThreadUtil.postOnBgThread(new M((Object) this, viewPosition, gridSize, (Object) listViewHolder, 7));
        }
    }

    public void onYearClicked(int i2, float f, float f5, float f8, float f10) {
        int gridSize = getGridSize();
        int spanSize = getSpanSize(i2) / gridSize;
        int viewPosition = getViewPosition(spanSize, f, f5);
        String str = this.TAG;
        StringBuilder h5 = a.h(i2, viewPosition, "onYearClick {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        h5.append(spanSize);
        h5.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        h5.append(f);
        h5.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        h5.append(f5);
        h5.append("}");
        Log.d(str, h5.toString());
        if (viewPosition < 0) {
            viewPosition = 0;
        }
        float f11 = f8;
        float f12 = f5;
        ((GalleryPinchView) this.mGalleryListView).startYearClickedAnimation(getTargetMonthViewPosition(i2, gridSize, viewPosition), getViewRect(spanSize, f11, f10, f12, this.mMultiClusterAdapter.getHintDataCount(i2, gridSize)));
    }

    public void requestThumbnail(ListViewHolder listViewHolder, int i2) {
        if (isYearData(listViewHolder.getViewType())) {
            listViewHolder.setThumbKind(getThumbnailKind());
            ClusterItem hintClusterItem = this.mMultiClusterAdapter.getHintClusterItem(listViewHolder.getViewPosition() - 1, i2);
            if (hintClusterItem != null) {
                int i7 = UnsafeCast.toInt(hintClusterItem.getDateRaw().split("-")[0]);
                ArrayList<MediaItem> hintYearItemList = this.mMultiClusterAdapter.getHintYearItemList(i2, i7);
                if (hintYearItemList == null) {
                    Log.e(this.TAG, "requestThumbnail : yearItemList null");
                    return;
                }
                String str = this.TAG;
                StringBuilder o2 = C0086a.o(i7, "requestThumbnail {", NumericEnum.SEP);
                o2.append(hintClusterItem.getCount());
                o2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                o2.append(hintYearItemList.size());
                o2.append("}");
                Log.d(str, o2.toString());
                if (!hintYearItemList.isEmpty() && hintYearItemList.get(0) != null) {
                    listViewHolder.bind(hintYearItemList.get(0));
                    listViewHolder.setChildItemCount(hintYearItemList.size());
                    listViewHolder.getImage().setTag(hintYearItemList.get(0).getPath());
                    new YearThumbnailRequestHolder(listViewHolder, hintYearItemList, new y(14, this)).loadYearThumbnailAsync(i2);
                    return;
                }
                return;
            }
            return;
        }
        super.requestThumbnail(listViewHolder, i2);
    }

    public void scrollToIndexWithOffset(int i2, int i7) {
        ((ITimelineView) this.mView).getLayoutManager().scrollToRowWithOffset(i2, i7);
    }

    public void setBitmapWithBind(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        try {
            ThumbnailRequestHolder thumbnailRequestHolder = (ThumbnailRequestHolder) uniqueKey;
            if (isYearViewType(thumbnailRequestHolder)) {
                boolean hasInvalidYearCount = hasInvalidYearCount(getViewHolderForAdapterPosition(thumbnailRequestHolder.getPosition()), thumbnailRequestHolder.getChildItemCount());
                boolean hasInvalidYearBitmapSize = hasInvalidYearBitmapSize(bitmap);
                if (hasInvalidYearCount || hasInvalidYearBitmapSize) {
                    String str = this.TAG;
                    Log.w(str, "fail setBitmapWithBind : invalidYearCount : " + hasInvalidYearCount + ", invalidBitmapSize" + hasInvalidYearBitmapSize);
                    return;
                }
            }
            super.setBitmapWithBind(bitmap, uniqueKey, thumbKind);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            j.u(e, new StringBuilder("fail to set bitmap with bind : e="), this.TAG);
        }
    }

    public void setHeaderView(View view, int i2) {
        if (!(getHeaderView() == null || getHeaderView().getParent() == null)) {
            ViewUtils.removeSelf(getHeaderView());
        }
        a.k(i2, "setHeaderView position = ", this.TAG);
        if (view != null) {
            if (getHeaderView() == null) {
                setHeaderView(createMultipleHeaderContainer(view.getContext()));
            }
            View subHeaderView = getSubHeaderView(i2);
            if (subHeaderView != null) {
                LinearLayout linearLayout = (LinearLayout) subHeaderView;
                ViewUtils.removeAllViews(linearLayout);
                linearLayout.addView(view);
                subHeaderView.setVisibility(0);
            }
        } else if (getHeaderView() != null) {
            View findViewById = getHeaderView().findViewById(R.id.header_0);
            View findViewById2 = getHeaderView().findViewById(R.id.header_1);
            View findViewById3 = getHeaderView().findViewById(R.id.header_2);
            if (i2 == 0) {
                ViewUtils.removeAllViews((LinearLayout) findViewById);
                findViewById.setVisibility(8);
            } else if (i2 == 1) {
                ViewUtils.removeAllViews((LinearLayout) findViewById2);
                findViewById2.setVisibility(8);
            } else {
                ViewUtils.removeAllViews((LinearLayout) findViewById3);
                findViewById3.setVisibility(8);
            }
            if (((LinearLayout) findViewById).getChildCount() == 0 && ((LinearLayout) findViewById2).getChildCount() == 0 && ((LinearLayout) findViewById3).getChildCount() == 0) {
                setHeaderView((View) null);
            }
        }
    }

    public void setThumbnailKind() {
        super.setThumbnailKind();
        PreferenceCache.LastUseSmallThumbKind.setBoolean(ThumbKind.isSmallKind(getThumbnailKind()));
    }

    public boolean supportSpannable() {
        return false;
    }

    public void syncClusterDivider(int i2) {
        if (!isSpannable()) {
            super.lambda$updateCheckboxOnBindMediaItem$0(i2);
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (!PreferenceFeatures.OneUi30.YEAR_CLUSTERING || !list.contains("update_year_bitmap") || getItemViewType(i2) != 4) {
            if (list.contains("checkBox") && ((ITimelineView) this.mView).isViewResumed() && ViewHolderValue.isDivider(getItemViewType(i2))) {
                getTimelineSelectionModeAnimator().startAnimation(listViewHolder, isSelectionMode());
            }
            super.onBindViewHolder(listViewHolder, i2, list);
            return;
        }
        requestThumbnail(listViewHolder, getGridSize());
    }
}
