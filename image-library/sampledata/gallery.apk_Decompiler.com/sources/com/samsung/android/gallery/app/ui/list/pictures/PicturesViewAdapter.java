package com.samsung.android.gallery.app.ui.list.pictures;

import Ad.C0720a;
import Bb.g;
import Bd.C0726b;
import J5.c;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.ConcatThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.hover.HoverHandler;
import com.samsung.android.gallery.app.ui.list.hover.HoverParams;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.MultiClusterAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.MultiClusterAdapterFactory;
import com.samsung.android.gallery.app.ui.viewholders.DateLocationViewHolder;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.abstraction.GridSpans;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.AtomicBooleanCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.abstraction.ContextThemeWrapperCompat;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryPinchView;
import com.samsung.android.gallery.widget.listview.selection.SelectionManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.pinch.IPinchViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.a;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;
import e5.C0451a;
import e5.j;
import e5.k;
import e5.l;
import e5.m;
import e5.n;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PicturesViewAdapter<V extends IPicturesView> extends BaseListViewAdapter<V> {
    static final boolean SUPPORT_LOCATION = Features.isEnabled(Features.SUPPORT_LOCATION);
    private int mCreateVhCounter;
    private final DeleteAnimationHelper mDeleteAnimHelper = new DeleteAnimationHelper();
    private int mFirstTitleHeight;
    private int mFirstTitleIndex = -1;
    private int mFooterHeight = 0;
    private final AtomicBooleanCompat mLocationAuthEnabled = new AtomicBooleanCompat(false);
    protected int mMaxGridSize;
    protected int mMaxMonthGridSize;
    protected MultiClusterAdapter mMultiClusterAdapter;
    private List<Integer> mSelectedDataPositionsBackup;
    private final boolean mSupportRealRatio = this.mGalleryListView.getGridSpans().hasRealRatio;
    private final boolean mSupportYearTimeline = this.mGalleryListView.getGridSpans().hasYear;
    private final PicturesViewHolderFactory mViewHolderFactory;

    public PicturesViewAdapter(V v, String str, boolean z) {
        super(v, str);
        initData();
        initDefaultDividerHeight(v.getResources());
        updateCluster(getGridSize());
        this.mGalleryListView.addOnLayoutChangeListener(new g(7, this));
        Context context = getContext();
        this.mViewHolderFactory = createViewHolderFactory(new ContextThemeWrapperCompat(context, context.getTheme()));
    }

    private void backupSelectedPositions(int i2) {
        ArrayList<Integer> arrayList;
        if (((IPicturesView) this.mView).isDrawerStateChanged()) {
            i2 = getGridSize();
        }
        ((IPicturesView) this.mView).setDrawerStateChanged(false);
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager != null) {
            arrayList = selectionManager.getSelectedList();
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            Stream<Integer> filter = arrayList.stream().filter(new a(this, i2, 1));
            MultiClusterAdapter multiClusterAdapter = this.mMultiClusterAdapter;
            Objects.requireNonNull(multiClusterAdapter);
            this.mSelectedDataPositionsBackup = (List) filter.map(new b(16, multiClusterAdapter)).collect(Collectors.toList());
        }
    }

    private MediaItem getDividerMediaItem(int i2) {
        return MediaItemUtil.toMediaItem(this.mMultiClusterAdapter.getClusterItem(i2));
    }

    private List<MediaItem> getHintMonthXsItemList(int i2, int i7) {
        Stream<Integer> stream = this.mMultiClusterAdapter.getHintDataPositionList(i2, i7).stream();
        MediaData mediaData = this.mMediaData;
        Objects.requireNonNull(mediaData);
        return (List) stream.map(new b(17, mediaData)).collect(Collectors.toList());
    }

    private int getListViewWidth(GridLayoutManager gridLayoutManager) {
        GalleryListView galleryListView = this.mGalleryListView;
        if (galleryListView != null) {
            return (galleryListView.getWidth() - gridLayoutManager.getPaddingRight()) - gridLayoutManager.getPaddingLeft();
        }
        return 1024;
    }

    private String getMapUrl(Blackboard blackboard, MediaItem mediaItem, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        MediaItem mediaItem2 = null;
        try {
            mediaItem2 = (MediaItem) getCluster(i2).stream().map(new k(this, 1)).filter(new com.samsung.android.gallery.module.dynamicview.a(20)).findFirst().orElse((Object) null);
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "getMapUrl", e.getMessage());
        }
        String str = this.TAG;
        Log.d(str, "getMapUrl : " + Logger.vt(currentTimeMillis));
        publishInitialLocation(blackboard, mediaItem2);
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "mergedAlbumId", "");
        String argValue2 = ArgumentsUtil.getArgValue(getLocationKey(), "ids", "");
        String argValue3 = ArgumentsUtil.getArgValue(getLocationKey(), "id", "");
        int i7 = 12;
        if (!TextUtils.isEmpty(argValue) && !TextUtils.isEmpty(argValue2)) {
            i7 = GalleryPreference.getInstance().loadSortBy(argValue, 12);
        } else if (!TextUtils.isEmpty(argValue3)) {
            i7 = GalleryPreference.getInstance().loadSortBy(argValue3, 12);
        }
        return new UriBuilder(getMapLocationTarget()).appendArg("ids", argValue2).appendArg("id", argValue3).appendArg("sort_by", String.valueOf(i7)).appendArg("cluster_map_date", getSortedDateRange(mediaItem, i7)).appendArg("title", mediaItem.getLocation()).build();
    }

    private int getMonth(int i2, int i7) {
        ClusterItem hintClusterItem = this.mMultiClusterAdapter.getHintClusterItem(i2 - 1, i7);
        if (hintClusterItem != null) {
            return UnsafeCast.toInt(hintClusterItem.getDateRaw().split("-")[1]);
        }
        return -1;
    }

    private int getRealGridSize(int i2) {
        return GridValue.revert(i2);
    }

    private String getSortedDateRange(MediaItem mediaItem, int i2) {
        boolean z;
        long j2;
        Pair<Long, Long> dateRange = getDateRange(mediaItem);
        if (SortByType.getSortBy(i2) == 20) {
            z = true;
        } else {
            z = false;
        }
        long longValue = ((Long) dateRange.first).longValue();
        if (z) {
            longValue /= 1000;
        }
        Long l = (Long) dateRange.second;
        if (z) {
            j2 = l.longValue() / 1000;
        } else {
            j2 = l.longValue();
        }
        return longValue + "-" + j2;
    }

    private String getTimelineGridInfoTextForVoiceAssistant() {
        int i2;
        int gridSize = getGridSize();
        int timelineModeLookup = getTimelineModeLookup(gridSize);
        if (timelineModeLookup != 1) {
            if (timelineModeLookup == 2) {
                return getContext().getString(R.string.year_view);
            }
            if (timelineModeLookup == 3) {
                return getContext().getString(R.string.real_ratio_day_view);
            }
            if (timelineModeLookup != 4) {
                int realGridSize = getRealGridSize(gridSize);
                int[] spanDays = this.mGalleryListView.getGridSpans().spanDays();
                Context context = getContext();
                if (realGridSize < spanDays[spanDays.length - 1]) {
                    i2 = R.string.large_thumbnail_day_view;
                } else {
                    i2 = R.string.day_view;
                }
                return context.getString(i2);
            }
        }
        return getContext().getString(R.string.month_view);
    }

    private void initDefaultDividerHeight(Resources resources) {
        this.mFirstTitleHeight = resources.getDimensionPixelOffset(R.dimen.date_location_header_divider_height);
    }

    private boolean isAutoUpdateAlbum(String str) {
        if (!LocationKey.isAlbumPictures(str) || ArgumentsUtil.getArgValue(str, "type", 0) != AlbumType.AutoUpdated.toInt()) {
            return false;
        }
        return true;
    }

    private boolean isConcatThumbnailType(int i2) {
        if (!PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL || !isMonthForViewingData(i2)) {
            return false;
        }
        return true;
    }

    private boolean isDay(int i2) {
        int timelineModeLookup = getTimelineModeLookup(i2);
        if (timelineModeLookup == 0 || timelineModeLookup == 3) {
            return true;
        }
        return false;
    }

    private boolean isDayToDay(int i2) {
        if (!isDay(getRealGridSize(getGridSize())) || !isDay(i2)) {
            return false;
        }
        return true;
    }

    private boolean isLocationAuthEnabled() {
        if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR) || PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$backupSelectedPositions$2(int i2, Integer num) {
        if (getClusterHintItemViewType(num.intValue(), i2) >= 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getMapUrl$14(MediaItem mediaItem) {
        if (mediaItem == null || !MapUtil.isValidLocation(mediaItem.getLatitude(), mediaItem.getLongitude()) || TextUtils.isEmpty(mediaItem.getLocation())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleDataUpdateAnimation$8() {
        notifyDataSetChanged();
        checkPreviewCandidate();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Integer lambda$recalculatePosition$10(RecyclerView.LayoutManager layoutManager) {
        return Integer.valueOf(((GalleryGridLayoutManager) layoutManager).getHintHorizontalPadding(this.mGalleryListView.getMaxColumnCount()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$recalculatePosition$11() {
        return 0;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$recalculatePosition$12() {
        return 0;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Integer lambda$requestThumbnail$0(int i2) {
        return Integer.valueOf(GridMarginHelper.getMargin(((IPicturesView) this.mView).getListView(), i2));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$updateAllDividerCheckbox$3(boolean z, Integer num) {
        int i2;
        if (z) {
            i2 = num.intValue() + 2;
        } else {
            i2 = num.intValue() + 1;
        }
        return Integer.valueOf(i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateAllDividerCheckbox$4(int i2, Integer num) {
        if (num.intValue() < i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateViewSize$6(ListViewHolder listViewHolder) {
        this.mFirstTitleHeight = listViewHolder.itemView.getHeight();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateViewSize$7(ListViewHolder listViewHolder) {
        this.mFooterHeight = listViewHolder.itemView.getHeight();
    }

    /* access modifiers changed from: private */
    public void onAccessibilityMonthClicked(View view, int i2) {
        String str;
        int gridSize = getGridSize();
        ClusterItem hintClusterItem = this.mMultiClusterAdapter.getHintClusterItem(i2 - 1, gridSize);
        if (hintClusterItem == null || hintClusterItem.getCount() == 1) {
            str = AppResources.getString(R.string.speak_folder_name_1_item);
        } else {
            str = AppResources.getString(R.string.speak_folder_name_n_items, Integer.valueOf(hintClusterItem.getCount()));
        }
        view.setContentDescription(getMonth(i2, gridSize) + ArcCommonLog.TAG_COMMA + str + ArcCommonLog.TAG_COMMA + AppResources.getString(R.string.speak_button));
    }

    /* access modifiers changed from: private */
    public void onLayoutChanged(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (i13 - i11 != i8 - i2) {
            onLayoutChanged(view, i2, i7, i8, i10);
        }
    }

    /* access modifiers changed from: private */
    public void onMonthClicked(int i2, float f, float f5, float f8, float f10) {
        Object obj;
        int gridSize = getGridSize();
        int spanSize = getSpanSize(i2) / gridSize;
        int i7 = (int) (f / ((float) spanSize));
        String str = this.TAG;
        StringBuilder h5 = A.a.h(i2, i7, "onMonthClicked {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        h5.append(spanSize);
        h5.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        h5.append(f);
        h5.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        h5.append(f5);
        h5.append("}");
        Log.d(str, h5.toString());
        if (i7 < 0) {
            i7 = 0;
        }
        ArrayList hintDataPositionList = this.mMultiClusterAdapter.getHintDataPositionList(i2, gridSize);
        if (i7 >= hintDataPositionList.size()) {
            obj = C0212a.i(hintDataPositionList, 1);
        } else {
            obj = hintDataPositionList.get(i7);
        }
        int intValue = ((Integer) obj).intValue();
        float f11 = f8;
        float f12 = f5;
        this.mGalleryListView.startMonthForViewingClickedAnimation(intValue, getViewRect(spanSize, f11, f10, f12, hintDataPositionList.size()));
    }

    private void onMonthForViewingClicked(ListViewHolder listViewHolder, int i2) {
        String str = this.TAG;
        Log.d(str, "onMonthForViewingClicked {" + i2 + "}");
        View rootView = listViewHolder.getRootView();
        if (i2 < 0) {
            i2 = 0;
        }
        ((GalleryPinchView) this.mGalleryListView).startMonthForViewingClickedAnimation(i2, rootView.getX(), rootView.getY());
    }

    private void restoreSelectedPosition() {
        List<Integer> list = this.mSelectedDataPositionsBackup;
        if (list != null) {
            this.mSelectedDataPositionsBackup = null;
            replaceSelection(getItemCount(), (List) list.stream().map(new k(this, 0)).collect(Collectors.toList()));
            updateAllDividerCheckbox();
        }
    }

    private void updateAllDividerCheckbox() {
        if (isSelectionMode()) {
            getDividers().stream().map(new l(supportHeader())).filter(new B8.b(getItemCount(), 24)).forEach(new com.samsung.android.sum.core.descriptor.b(17, this));
        }
    }

    private void updateTimelineDivider() {
        Log.d(this.TAG, "updateTimelineDivider", 0, Integer.valueOf(getItemCount()), "deco_timeline");
        notifyItemRangeChanged(0, getItemCount(), "deco_timeline");
    }

    public boolean bindViewHolderOnScrollIdle(ListViewHolder listViewHolder, int i2, int i7) {
        if (!this.mGalleryListView.isScrollIdle() || !isConcatThumbnailType(listViewHolder.getViewType())) {
            return super.bindViewHolderOnScrollIdle(listViewHolder, i2, i7);
        }
        return ConcatThumbnailRequestHolder.requestAndBindImage(listViewHolder, getHintMonthXsItemList(i2, i7), i7);
    }

    public void calculateToPositionOnCluster(int i2, int i7) {
        this.mMultiClusterAdapter.recalculateMonthForViewingPosition(i2, i7);
    }

    public boolean checkLocationAuthChanged() {
        return this.mLocationAuthEnabled.compareAndSet(isLocationAuthEnabled());
    }

    public ListViewHolder createHintViewHolder(ViewGroup viewGroup, int i2, int i7) {
        return this.mViewHolderFactory.createListViewHolder(viewGroup, i2, i7);
    }

    public void createMonthXsFakeCluster(int i2, int i7) {
        this.mMultiClusterAdapter.createMonthXsFakeCluster(i2, i7);
    }

    public MultiClusterAdapter.TimelineModeLookup createTimelineModeLookup() {
        return new m(this);
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new PicturesViewHolderFactory(context);
    }

    public void destroy() {
        this.mMultiClusterAdapter.clear();
        super.destroy();
    }

    public void enableLocationText(ListViewHolder listViewHolder, boolean z, boolean z3) {
        if (ViewHolderValue.isDivider(listViewHolder.getViewType())) {
            ((DateLocationViewHolder) listViewHolder).setLocationTextEnabled(this.mLocationAuthEnabled.get(), z, z3);
        }
    }

    public int getClusterHintItemViewType(int i2, int i7) {
        int hintItemViewType = this.mMultiClusterAdapter.getHintItemViewType(i2, i7);
        if (hintItemViewType != 0 || !GridValue.isMonthXs(this.mGalleryListView.getGridSpans().valueOf(i7))) {
            return hintItemViewType;
        }
        return 3;
    }

    public Pair<Long, Long> getDateRange(MediaItem mediaItem) {
        return mediaItem.getDateTimeRange();
    }

    public int getDecoItemViewType() {
        if (this.mHideDecoIcons || !isSelectionMode() || isRealRatio() || ((IPicturesView) this.mView).getLayoutManager().getHintWidthSpace(getGridSize()) / ((IPicturesView) this.mView).getLayoutManager().getSpanCount() >= getContext().getResources().getDimensionPixelSize(R.dimen.storage_type_icon_display_min_width)) {
            return super.getDecoItemViewType();
        }
        return (super.getDecoItemViewType() & -17) | 128;
    }

    public int getDividerIndex(long j2) {
        return this.mMultiClusterAdapter.getDividerIndex(j2);
    }

    public ArrayList<Pair<String, Integer>> getDividerList() {
        int gridSize = getGridSize();
        return this.mMultiClusterAdapter.getDividerScroll(gridSize, getListViewWidth(((IPicturesView) this.mView).getLayoutManager()) / gridSize, getTitleHeight(gridSize), this.mFirstTitleHeight, getHeaderViewHeight());
    }

    public ArrayList<Integer> getDividers() {
        return this.mMultiClusterAdapter.getDividers();
    }

    public String getGridInfoTextForVoiceAssistant(int i2) {
        if (((IPicturesView) this.mView).supportTimeline()) {
            return getTimelineGridInfoTextForVoiceAssistant();
        }
        if (i2 == 1) {
            return AppResources.getString(R.string.speak_fully_zoomed_in);
        }
        return AppResources.getQuantityString(R.plurals.grid_view_with_thumbnails_in_each_row, i2, Integer.valueOf(i2));
    }

    public int getGridMargin(int i2) {
        return GridMarginHelper.getMargin(this.mGalleryListView, i2);
    }

    public int getHintColumnSpan(int i2, int i7) {
        return this.mMultiClusterAdapter.getHintColumnSpan(i2, i7);
    }

    public int getHintDataCountInView(int i2, int i7) {
        return this.mMultiClusterAdapter.getHintDataCount(i2, i7);
    }

    public int getHintDataPosition(int i2, int i7) {
        return this.mMultiClusterAdapter.getHintDataPosition(i2, i7);
    }

    public int getHintExtraOffset(int i2, int i7, int i8) {
        return this.mMultiClusterAdapter.getHintExtraOffset(i2, i7, i8);
    }

    public int getHintItemCount(int i2) {
        return this.mMultiClusterAdapter.getHintItemCount(i2) + this.mDataCount;
    }

    public int getHintItemViewType(int i2, int i7) {
        return getClusterHintItemViewType(i2, i7);
    }

    public int getHintNextDividerIndex(int i2, int i7) {
        return this.mMultiClusterAdapter.getHintNextDividerIndex(i2, i7);
    }

    public int getHintNextRowIndex(int i2, int i7, int i8) {
        return this.mMultiClusterAdapter.getHintNextRowIndex(i2, i7, i8);
    }

    public int getHintPrevDividerIndex(int i2, int i7) {
        return this.mMultiClusterAdapter.getHintPrevDividerIndex(i2, i7);
    }

    public int getHintPrevRowIndex(int i2, int i7) {
        return this.mMultiClusterAdapter.getHintPrevRowIndex(i2, i7);
    }

    public Integer[] getHintRowInfo(int i2, int i7) {
        return this.mMultiClusterAdapter.getHintRowInfo(i2, i7);
    }

    public int getHintRowSpan(int i2, int i7) {
        return this.mMultiClusterAdapter.getHintRowSpan(i2, i7);
    }

    public int getHintSpanCount(int i2) {
        if (isRealRatio(i2)) {
            return this.mMultiClusterAdapter.getSpanCount(i2);
        }
        return getRealGridSize(i2);
    }

    public SpanInfo getHintSpanInfo(int i2, int i7) {
        return this.mMultiClusterAdapter.getHintSpanInfo(i2, i7);
    }

    public int getHintStartSpan(int i2, int i7) {
        return this.mMultiClusterAdapter.getStartSpan(i2, i7);
    }

    public int getHintViewCount(int i2) {
        int valueOf = this.mGalleryListView.getGridSpans().valueOf(i2);
        if ((!PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL || !GridValue.isMonthXs(valueOf)) && !GridValue.isYear(valueOf)) {
            return getHintItemCount(i2);
        }
        return this.mMultiClusterAdapter.getHintViewCount(i2);
    }

    public int getHintViewHeight(int i2, int i7, int i8) {
        if (isDivider(i2, i7)) {
            if (ViewHolderValue.isFirstDivider(getClusterHintItemViewType(i2, i7))) {
                return this.mFirstTitleHeight;
            }
            return getTitleHeight(i7);
        } else if (supportCustomViewSize(i7)) {
            return this.mMultiClusterAdapter.getHintItemHeight(i2, i7);
        } else {
            if (!isSpannable()) {
                return i8 / getRealGridSize(i7);
            }
            return this.mMultiClusterAdapter.getHintRowSpan(i2, i7) * (i8 / getRealGridSize(i7));
        }
    }

    public int getHintViewPosition(int i2, int i7) {
        return this.mMultiClusterAdapter.getHintViewPosition(i2, i7);
    }

    public int getHintYearDataPosition(int i2, float f, float f5, int i7) {
        return this.mMultiClusterAdapter.getHintDataPosition(i2, i7);
    }

    public int getItemCount() {
        if (isYear()) {
            return (this.mMultiClusterAdapter.getCount() * 2) - 1;
        }
        if (PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL && isMonthForViewing()) {
            return this.mMultiClusterAdapter.getHintViewCount(getGridSize());
        }
        return this.mMultiClusterAdapter.getCount() + this.mDataCount;
    }

    public int getItemHeight(int i2) {
        if (isDivider(i2)) {
            if (i2 == this.mFirstTitleIndex) {
                return this.mFirstTitleHeight;
            }
            return getTitleHeight(getGridSize());
        } else if (isFooter(i2)) {
            return this.mFooterHeight;
        } else {
            if (supportCustomViewSize(getGridSize())) {
                return this.mMultiClusterAdapter.getHintItemHeight(i2, getGridSize());
            }
            IBaseListView iBaseListView = this.mView;
            if (iBaseListView == null) {
                return 0;
            }
            int listViewWidth = getListViewWidth(((IPicturesView) iBaseListView).getLayoutManager()) / this.mGalleryListView.getColumnCount();
            if (isSpannable()) {
                return this.mMultiClusterAdapter.getHintRowSpan(i2, getGridSize()) * listViewWidth;
            }
            return listViewWidth;
        }
    }

    public int getItemViewType(int i2) {
        return getClusterHintItemViewType(i2, getGridSize());
    }

    public String getMapLocationTarget() {
        return "location://map/filtered";
    }

    public int getMaxScroll() {
        if (this.mGalleryListView == null) {
            return 0;
        }
        int gridSize = getGridSize();
        return this.mGalleryListView.getPaddingBottom() + this.mMultiClusterAdapter.getMaxScroll(gridSize, ((float) getListViewWidth(((IPicturesView) this.mView).getLayoutManager())) / ((float) gridSize), getTitleHeight(gridSize), this.mFirstTitleHeight);
    }

    public MediaItem getMediaItemFromCache(int i2) {
        return isDivider(i2) ? getDividerMediaItem(i2) : super.getMediaItemFromCache(i2);
    }

    public int getMediaItemPosition(int i2) {
        return this.mMultiClusterAdapter.getDataPosition(i2);
    }

    public int getNextRowIndex(int i2, int i7) {
        if (this.mDataCount <= 0) {
            return -1;
        }
        return getHintNextRowIndex(i2, getGridSize(), i7);
    }

    public int getPrevRowIndex(int i2) {
        return getHintPrevRowIndex(i2, getGridSize());
    }

    public int getRowCount(int i2) {
        return this.mMultiClusterAdapter.getHintRowCount(i2);
    }

    public int getScrollIndex(int i2) {
        return this.mMultiClusterAdapter.getScrollIndex(i2);
    }

    public int getSelectedDataCount(ArrayList<Integer> arrayList) {
        return dataOnlyListCount(arrayList);
    }

    public int getSpanSize(int i2) {
        return this.mMultiClusterAdapter.getHintColumnSpan(i2, getGridSize());
    }

    @Deprecated
    public int getStartDepth() {
        return this.mGalleryListView.getStartDepth();
    }

    public int getTimelineModeLookup(int i2) {
        IBaseListView iBaseListView = this.mView;
        if (iBaseListView == null) {
            return 0;
        }
        return ((IPicturesView) iBaseListView).getCluster(i2);
    }

    public int getTitleHeight(int i2) {
        return GridMarginHelper.getDividerHeight(this.mGalleryListView, i2);
    }

    public V getView() {
        return (IPicturesView) this.mView;
    }

    public int getViewPosition(int i2) {
        return this.mMultiClusterAdapter.getViewPosition(i2);
    }

    public RectF getViewRect(int i2, float f, float f5, float f8, int i7) {
        float f10 = (float) i2;
        float min = Math.min((float) ((i7 - 1) * i2), f - (f % f10));
        float f11 = (f5 + f8) - (f8 % f10);
        return new RectF(min, f11, min + f10, f10 + f11);
    }

    public boolean handleDataUpdateAnimation() {
        if (!((IPicturesView) this.mView).supportDeleteAnimation() || !this.mDeleteAnimHelper.handleDeleteAnimation(((IPicturesView) this.mView).getListView(), this, getItemCount())) {
            return false;
        }
        ThreadUtil.postOnUiThreadDelayed(new C0451a(4, this), 500);
        return true;
    }

    public boolean hasCluster(int i2) {
        MultiClusterAdapter multiClusterAdapter = this.mMultiClusterAdapter;
        if (multiClusterAdapter == null || multiClusterAdapter.getCluster(i2) == null) {
            return false;
        }
        return true;
    }

    public void initData() {
        this.mMultiClusterAdapter = MultiClusterAdapterFactory.create(this.mGalleryListView, this.mMediaData, this.mSupportRealRatio, supportSpannable(), createTimelineModeLookup(), supportHeader(), hasFooter());
        GridSpans gridSpans = this.mGalleryListView.getGridSpans();
        updateGridSize(gridSpans.spanMonthMax(), gridSpans.spanMax());
    }

    public boolean isDivider(int i2) {
        return this.mMultiClusterAdapter.isDivider(i2);
    }

    public boolean isFullyLoaded() {
        return this.mMediaData.isFullyLoaded();
    }

    public boolean isHideDecoItem(int i2) {
        return this.mGalleryListView.getGridSpans().isDecoHide(i2);
    }

    public boolean isInvisibleViewHolder(ListViewHolder listViewHolder) {
        GalleryListView galleryListView = this.mGalleryListView;
        if (galleryListView == null || ((float) (galleryListView.getHeight() - this.mGalleryListView.getPaddingBottom())) >= listViewHolder.itemView.getY()) {
            return false;
        }
        return true;
    }

    public boolean isLocationTextDimmed() {
        return isAutoUpdateAlbum(getLocationKey());
    }

    public boolean isLocationTextDisplay() {
        return true;
    }

    public boolean isMonthForViewing() {
        return GridValue.isMonthXs(this.mGalleryListView.getGridSpans().valueOf());
    }

    public boolean isPreviewAvailable() {
        GalleryListView galleryListView = this.mGalleryListView;
        if (galleryListView == null || !galleryListView.getGridSpans().inDayByIndex(this.mGalleryListView.getDepth())) {
            return false;
        }
        return true;
    }

    public boolean isRealRatio() {
        return GridValue.isRealRatio(this.mGalleryListView.getGridSpans().valueOf());
    }

    public boolean isSpannable() {
        return isSpannable(getGridSize());
    }

    public boolean isYear() {
        return GridValue.isYear(this.mGalleryListView.getGridSpans().valueOf());
    }

    public void notifyClusterChanged() {
        this.mMultiClusterAdapter.notifyDataChanged(this.mGalleryListView, this.mSupportRealRatio, supportSpannable());
    }

    public void onAbortDelete() {
        this.mDeleteAnimHelper.onAbortDelete();
    }

    public boolean onCheckBoxClicked(ListViewHolder listViewHolder, int i2) {
        GalleryListAdapter.SelectableType isItemSelectable = isItemSelectable(i2);
        if (isItemSelectable == GalleryListAdapter.SelectableType.SUPPORT) {
            super.onCheckBoxClicked(listViewHolder, i2);
            return true;
        }
        showToastForSelectionError(isItemSelectable);
        listViewHolder.setChecked(false);
        return false;
    }

    public void onClusterChanged(int i2) {
        if (isRealRatio(getGridSize())) {
            resetThumbKind();
        }
        if (isDayToDay(i2)) {
            updateCluster(getGridSize());
            return;
        }
        backupSelectedPositions(i2);
        updateCluster(getGridSize());
        restoreSelectedPosition();
        notifyDataSetChanged();
    }

    public void onDataChanged() {
        notifyClusterChanged();
        updateCluster(getGridSize());
        super.onDataChanged();
        if (isFullyLoaded()) {
            ((IPicturesView) this.mView).handleInitializeScroll();
        }
    }

    public void onDataRangeInserted(int i2, int i7) {
        notifyClusterChanged();
        updateCluster(getGridSize());
        ((IPicturesView) this.mView).handleInitializeScroll();
        updateFirstTimelineDivider(i2);
        super.onDataRangeInserted(i2, i7);
    }

    public void onGridSizeChanged() {
        if (PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL && isMonthForViewing()) {
            this.mMultiClusterAdapter.onGridSizeChanged(getGridSize());
        }
        if (getLocationKey() != null && getLocationKey().equals(this.mBlackBoard.read("location://variable/currentv1"))) {
            SeApiCompat.announceVoiceAssistant(getContext(), getGridInfoTextForVoiceAssistant(getGridSize()));
        }
    }

    public void onHoverInternal(ListViewHolder listViewHolder, MotionEvent motionEvent) {
        ((HoverHandler) this.mHoverHandler.get()).onHover(listViewHolder, motionEvent, new HoverParams.HoverParamsBuilder(getLocationKey()).setContainer((ViewGroup) ((IPicturesView) this.mView).getView()).setItem(listViewHolder.getMediaItem()).setDataPosition(getMediaItemPosition(listViewHolder.getViewPosition())).setAlbumType(false).setHideOption(false).build());
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (i7 == 3) {
            if (SUPPORT_LOCATION && i2 >= 0) {
                ((IPicturesView) this.mView).onLocationItemClick(getMapUrl(this.mBlackBoard, mediaItem, i2));
            }
        } else if (i7 == 2) {
            ((IPicturesView) this.mView).onExpandItemClick(listViewHolder, i2, mediaItem);
        } else if (i7 == 5) {
            ((IPicturesView) this.mView).onFooterItemClick();
        } else if (isMonthForViewing()) {
            onMonthForViewingClicked(listViewHolder, i2);
        } else {
            super.onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
        }
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (isMonthForViewing() || !super.onListItemLongClickInternal(listViewHolder, i2, mediaItem, i7)) {
            return false;
        }
        return true;
    }

    public void onPrepareDelete() {
        if (((IPicturesView) this.mView).supportDeleteAnimation() && isSelectionMode()) {
            this.mDeleteAnimHelper.onPrepareDelete(((IPicturesView) this.mView).getLayoutManager(), this.mSelectionManager, getItemCount());
        }
    }

    public void publishInitialLocation(Blackboard blackboard, MediaItem mediaItem) {
        if (mediaItem != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("latitude", mediaItem.getLatitude());
                jSONObject.put("longitude", mediaItem.getLongitude());
                jSONObject.put("entryItem", mediaItem.getFileId());
                jSONObject.put(FileApiContract.Parameter.PATH, mediaItem.getPath());
                blackboard.publish("data://user/map/InitialLocation", jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public final void rebindMediaItemToHolder(ListViewHolder listViewHolder) {
        MediaItem loadMediaItemSync = loadMediaItemSync(getMediaItemPosition(listViewHolder.getAdapterPosition()));
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (loadMediaItemSync != null && mediaItem != null && loadMediaItemSync != mediaItem && loadMediaItemSync.getFileId() == mediaItem.getFileId()) {
            listViewHolder.bind(loadMediaItemSync);
        }
    }

    public void recalculatePosition() {
        recalculatePosition(this.mGalleryListView.getWidth());
    }

    public void requestThumbnail(ListViewHolder listViewHolder, int i2) {
        int viewPosition = listViewHolder.getViewPosition();
        if (viewPosition < 0 || !isConcatThumbnailType(listViewHolder.getViewType())) {
            super.requestThumbnail(listViewHolder, i2);
        } else {
            ConcatThumbnailRequestHolder.request(listViewHolder, getHintMonthXsItemList(viewPosition, i2), i2, new j(this, i2), new m(this));
        }
    }

    public void resetFakeCluster() {
        this.mMultiClusterAdapter.resetFakeCluster();
    }

    public void resume() {
        if (checkLocationAuthChanged()) {
            Log.d(this.TAG, "LocationAuthChanged", Boolean.valueOf(this.mLocationAuthEnabled.get()));
            updateTimelineDivider();
        }
    }

    public void setListeners(ListViewHolder listViewHolder) {
        super.setListeners(listViewHolder);
        if (ViewHolderValue.useConcatThumbnail(listViewHolder.getViewType())) {
            listViewHolder.setOnConcatThumbListener(new ListViewHolder.OnConcatThumbListener() {
                public void onAccessibilityClicked(View view, int i2, int i7) {
                    if (ViewHolderValue.isYear(i2)) {
                        PicturesViewAdapter.this.onAccessibilityYearClicked(view, i7);
                    } else if (ViewHolderValue.isMonthForViewing(i2)) {
                        PicturesViewAdapter.this.onAccessibilityMonthClicked(view, i7);
                    }
                }

                public void onClicked(int i2, int i7, float f, float f5, float f8, float f10) {
                    if (ViewHolderValue.isYear(i2)) {
                        PicturesViewAdapter.this.onYearClicked(i7, f, f5, f8, f10);
                    } else if (ViewHolderValue.isMonthForViewing(i2)) {
                        PicturesViewAdapter.this.onMonthClicked(i7, f, f5, f8, f10);
                    }
                }
            });
        }
    }

    public void setViewHolderMargin(ListViewHolder listViewHolder) {
        setViewHolderMargin(listViewHolder, getGridSize());
    }

    public boolean supportCustomViewSize(int i2) {
        int valueOf = this.mGalleryListView.getGridSpans().valueOf(i2);
        if (GridValue.isRealRatio(valueOf) || GridValue.isYear(valueOf)) {
            return true;
        }
        if (!PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL || !GridValue.isMonthXs(valueOf)) {
            return false;
        }
        return true;
    }

    public boolean supportHighQuality() {
        return GridValue.isRealRatio(this.mGalleryListView.getGridSpans().valueOf());
    }

    public boolean supportHover() {
        return true;
    }

    public boolean supportPinchInternal(boolean z) {
        if ((!z || !isSelectionMode()) || this.mGalleryListView.getGridSpans().canPinch(true, true)) {
            return true;
        }
        Log.e(this.TAG, "block to pinch-in in selection mode");
        return false;
    }

    public synchronized void updateCluster(int i2) {
        this.mMultiClusterAdapter.updateCluster(i2);
        onClusterUpdated();
    }

    public void updateFirstTimelineDivider(int i2) {
        int dividerIndex = this.mMultiClusterAdapter.getDividerIndex(getViewPosition(i2) - 1);
        if (dividerIndex >= 0) {
            Log.d(this.TAG, "update cluster by insert", Integer.valueOf(dividerIndex), "deco_timeline");
            notifyItemChanged(dividerIndex, "deco_timeline");
        }
    }

    public void updateGridSize(int i2, int i7) {
        updateMaxGridSize(i7);
        if (!this.mSupportYearTimeline) {
            i2 = this.mMaxGridSize;
        }
        this.mMaxMonthGridSize = i2;
    }

    public void updateMaxGridSize(int i2) {
        this.mMaxGridSize = i2;
    }

    public void updateMutableContent() {
        Log.d(this.TAG, "updateMutableContentType", 0, Integer.valueOf(getItemCount()), "deco_mutable_content");
        notifyItemRangeChanged(0, getItemCount(), "deco_mutable_content");
    }

    public void updateViewSize(ListViewHolder listViewHolder, int i2) {
        if (this.mFirstTitleIndex < 0 && ViewHolderValue.isFirstDivider(listViewHolder.getViewType())) {
            this.mFirstTitleIndex = i2;
            listViewHolder.itemView.post(new n(this, listViewHolder, 0));
        } else if (this.mFooterHeight == 0 && ViewHolderValue.isFooter(listViewHolder.getViewType())) {
            listViewHolder.itemView.post(new n(this, listViewHolder, 1));
        } else if ((listViewHolder.getViewType() == 0 || listViewHolder.getViewType() == 4 || (PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL && listViewHolder.getViewType() == 3)) && supportCustomViewSize(getGridSize())) {
            PicturesViewHolderFactory.updateViewSize(listViewHolder, i2, new k(this, 2), new k(this, 3));
        }
        setViewHolderMargin(listViewHolder, getGridSize());
    }

    public boolean useConcatThumbnail() {
        int valueOf = this.mGalleryListView.getGridSpans().valueOf();
        if (GridValue.isYear(valueOf)) {
            return true;
        }
        if (!PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL || !GridValue.isMonthXs(valueOf)) {
            return false;
        }
        return true;
    }

    private MediaItem getDividerMediaItem(int i2, int i7) {
        return MediaItemUtil.toMediaItem(this.mMultiClusterAdapter.getHintClusterItem(i2, i7));
    }

    public int getDividerIndex(int i2) {
        return this.mMultiClusterAdapter.getDividerIndex(i2);
    }

    public int getHintDataPosition(int i2, float f, float f5, int i7) {
        return this.mMultiClusterAdapter.getHintDataPosition(i2, f, f5, i7);
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        if (isDivider(i2, i7)) {
            return getDividerMediaItem(i2, i7);
        }
        return super.getMediaItemFromCache(i2, i7);
    }

    public int getMediaItemPosition(int i2, int i7) {
        return getHintDataPosition(i2, i7);
    }

    public boolean isDivider(int i2, int i7) {
        return this.mMultiClusterAdapter.isDivider(i2, i7);
    }

    public boolean isRealRatio(int i2) {
        return GridValue.isRealRatio(this.mGalleryListView.getGridSpans().valueOf(i2));
    }

    public boolean isSpannable(int i2) {
        return supportSpannable() && !supportCustomViewSize(i2);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (ThreadUtil.isMainThread()) {
            int i7 = this.mCreateVhCounter + 1;
            this.mCreateVhCounter = i7;
            if (i7 > 50) {
                String str = this.TAG;
                Log.w(str, "onCreateViewHolder#ui {#" + this.mCreateVhCounter + ",typ=" + i2 + "} unexpected");
            }
        }
        return this.mViewHolderFactory.createListViewHolder(viewGroup, i2, getGridSize());
    }

    public void onLayoutChanged(View view, int i2, int i7, int i8, int i10) {
        PicturesLayoutManager layoutManager = ((IPicturesView) this.mView).getLayoutManager();
        if (layoutManager != null) {
            recalculatePosition(i8 - i2);
            if (isRealRatio()) {
                layoutManager.setSpanCount(getGridSize());
                checkPreviewCandidate();
                GalleryListView galleryListView = this.mGalleryListView;
                if (galleryListView != null) {
                    galleryListView.scrollBy(0, 0);
                }
            }
        }
    }

    public void recalculatePosition(int i2) {
        RecyclerView.LayoutManager layoutManager = this.mGalleryListView.getLayoutManager();
        if (layoutManager instanceof GalleryGridLayoutManager) {
            GalleryGridLayoutManager galleryGridLayoutManager = (GalleryGridLayoutManager) layoutManager;
            recalculatePosition(i2, (Supplier<Integer>) new c(27, galleryGridLayoutManager), (Supplier<Integer>) new C0726b(12, this, galleryGridLayoutManager));
            return;
        }
        recalculatePosition(i2, (Supplier<Integer>) new C0720a(23), (Supplier<Integer>) new C0720a(24));
    }

    public void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2) {
        setViewHolderMargin(iPinchViewHolder, i2, getGridMargin(i2));
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("deco_timeline")) {
            MediaItem dividerMediaItem = getDividerMediaItem(i2, getGridSize());
            if (dividerMediaItem != null) {
                listViewHolder.bind(dividerMediaItem);
            }
            if (listViewHolder.updateDecoration(256, Boolean.valueOf(this.mLocationAuthEnabled.get()), Boolean.valueOf(isLocationTextDimmed()), Boolean.valueOf(isLocationTextDisplay()))) {
                return;
            }
        }
        if (!list.contains("deco_mutable_content") || !listViewHolder.updateDecoration(4096, new Object[0])) {
            super.onBindViewHolder(listViewHolder, i2, list);
            enableLocationText(listViewHolder, isLocationTextDimmed(), isLocationTextDisplay());
        }
    }

    public void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2, int i7) {
        Context context = iPinchViewHolder.getRootView().getContext();
        if (context != null) {
            if (iPinchViewHolder.getScalableView() != null) {
                ViewMarginUtils.setMargin(iPinchViewHolder.getScalableView(), i7);
                ViewMarginUtils.setMargin(iPinchViewHolder.getDecoView(11), i7);
            } else if (ViewHolderValue.isHeader(iPinchViewHolder.getViewType())) {
                ViewMarginUtils.setHorizontalMargin(iPinchViewHolder.getItemView(), (-((IPicturesView) this.mView).getLayoutManager().getDefaultHorizontalPadding(i2)) / 2);
            } else if (iPinchViewHolder.getDividerView() != null) {
                ViewMarginUtils.setHorizontalMargin(iPinchViewHolder.getDividerView(), i7);
                ViewMarginUtils.setBottomMargin(iPinchViewHolder.getDividerView(), context.getResources().getDimensionPixelOffset(R.dimen.date_location_divider_padding_bottom) - i7);
            }
        }
    }

    public void recalculatePosition(int i2, int i7, int i8) {
        if (this.mGalleryListView.getLayoutManager() instanceof GalleryGridLayoutManager) {
            if (this.mSupportRealRatio) {
                this.mMultiClusterAdapter.recalculateRealRatioPosition(i2 - i7);
            }
            if (PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL && ((IPicturesView) this.mView).supportMonthForViewing()) {
                this.mMultiClusterAdapter.recalculateMonthForViewingPosition(i2 - i8);
            }
            if (this.mSupportYearTimeline) {
                this.mMultiClusterAdapter.recalculateYearPosition(i2 - i8);
            }
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        updateViewSize(listViewHolder, i2);
        listViewHolder.itemView.setTag(this.mMultiClusterAdapter.getScrollText(i2));
    }

    public void recalculatePosition(int i2, Supplier<Integer> supplier, Supplier<Integer> supplier2) {
        if (this.mGalleryListView.getLayoutManager() instanceof GalleryGridLayoutManager) {
            if (this.mSupportRealRatio) {
                this.mMultiClusterAdapter.recalculateRealRatioPosition(i2 - supplier.get().intValue());
            }
            if (PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL && ((IPicturesView) this.mView).supportMonthForViewing()) {
                this.mMultiClusterAdapter.recalculateMonthForViewingPosition(i2 - supplier2.get().intValue());
            }
            if (this.mSupportYearTimeline) {
                this.mMultiClusterAdapter.recalculateYearPosition(i2 - supplier2.get().intValue());
            }
        }
    }

    public void onClusterUpdated() {
    }

    public void onAccessibilityYearClicked(View view, int i2) {
    }

    public void onYearClicked(int i2, float f, float f5, float f8, float f10) {
    }
}
