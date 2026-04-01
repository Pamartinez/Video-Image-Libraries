package com.samsung.android.gallery.app.ui.viewer2.grouppanel.list;

import A4.Q;
import A6.a;
import B8.e;
import C3.i;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.VuAnalytics;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.SingleTakenImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsDetailKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenLabel;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupPanelListAdapter extends GalleryListAdapter<SingleTakenImageViewHolder> {
    private static final Runnable DRAG_UPDATER = new i(12);
    private final ActionInvoker mActionInvoker;
    private GroupPanelListAdapterListener mAdapterListener;
    private int mCount = -1;
    private final EventContext mEventContext;
    private int mFocusedPosition;
    private BooleanSupplier mIsTableModeSupplier;
    private LayoutInflater mLayoutInflater;
    private final GalleryListView mListView;
    private ArrayList<MediaItem> mMediaItems;
    private GroupPanelVideoPreview mVideoPreview;

    public GroupPanelListAdapter(Blackboard blackboard, EventContext eventContext, GalleryListView galleryListView, ActionInvoker actionInvoker) {
        super(blackboard);
        this.mListView = galleryListView;
        this.mEventContext = eventContext;
        this.mActionInvoker = actionInvoker;
        setDragUpdater(DRAG_UPDATER);
    }

    private void drawFocusedBorder(int i2) {
        RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.mListView.findViewHolderForAdapterPosition(i2);
        if (findViewHolderForAdapterPosition != null) {
            ((SingleTakenImageViewHolder) findViewHolderForAdapterPosition).drawFocusedBorder();
        }
    }

    private int getBottomPadding(Resources resources, boolean z) {
        if (z) {
            return resources.getDimensionPixelSize(R.dimen.group_panel_item_bottom_padding_multi_window);
        }
        if (!Features.isEnabled(Features.IS_FOLDABLE_TYPE_FOLD) || !isTableMode()) {
            return resources.getDimensionPixelSize(R.dimen.group_panel_item_bottom_padding);
        }
        return resources.getDimensionPixelSize(R.dimen.group_panel_item_bottom_padding_table_mode);
    }

    private Pair<String, String>[] getDetails(String str, MediaItem mediaItem) {
        boolean isSuperSlowGroupPanelView = LocationKey.isSuperSlowGroupPanelView(str);
        String str2 = "";
        if (!isSuperSlowGroupPanelView) {
            return VuAnalytics.getViewerCustomDimensions(mediaItem, str2);
        }
        int superSlowClipEffect = MediaItemUtil.getSuperSlowClipEffect(mediaItem);
        if (superSlowClipEffect == 1) {
            str2 = AnalyticsDetail.EVENT_DETAIL_FORWARD.toString();
        } else if (superSlowClipEffect == 2) {
            str2 = AnalyticsDetail.EVENT_DETAIL_BACKWARD.toString();
        } else if (superSlowClipEffect == 3) {
            str2 = AnalyticsDetail.EVENT_DETAIL_SWING.toString();
        }
        return new Pair[]{new Pair(AnalyticsDetailKey.DEFAULT.toString(), str2)};
    }

    private String getEventId(String str) {
        if (LocationKey.isHighlightGroupPanelView(str)) {
            return AnalyticsEventId.EVENT_DETAILS_SELECT_DYNAMIC_VIEW.toString();
        }
        if (LocationKey.isSuperSlowGroupPanelView(str)) {
            return AnalyticsEventId.EVENT_SUPER_SLOW_ITEM_VIEW.toString();
        }
        return AnalyticsEventId.EVENT_SINGLE_TAKE_VIEW_ITEM.toString();
    }

    private int getFoldThumbHeight(Resources resources, boolean z) {
        if (isTableMode()) {
            return (((DeviceInfo.getDisplayHeight(this.mEventContext.getContext()) / 2) - DeviceInfo.getNavigationBarHeight()) - SystemUi.getToolBarHeightWithPadding(this.mEventContext.getContext())) - resources.getDimensionPixelSize(R.dimen.group_panel_item_bottom_margin_fold_flex);
        }
        if (z) {
            return resources.getDimensionPixelSize(R.dimen.group_panel_item_height_multi_window);
        }
        return resources.getDimensionPixelSize(R.dimen.group_panel_item_height_for_fold);
    }

    private int getFoldThumbWidth(Resources resources, int i2) {
        if (isTableMode()) {
            return (int) (((float) i2) * 0.75f);
        }
        return resources.getDimensionPixelSize(R.dimen.group_panel_item_width_for_fold);
    }

    private String getScreenId(String str) {
        if (LocationKey.isAiEditGroupPanelViewer(str)) {
            return AnalyticsScreenId.SCREEN_DETAILS_NORMAL.toString();
        }
        return AnalyticsScreenId.SCREEN_DETAIL_VIEW_SINGLE_TAKE_EXPANDED.toString();
    }

    private GroupPanelVideoPreview getVideoPreview() {
        if (this.mVideoPreview == null) {
            this.mVideoPreview = new GroupPanelVideoPreview(this.mAdapterListener);
        }
        return this.mVideoPreview;
    }

    private boolean isTableMode() {
        BooleanSupplier booleanSupplier = this.mIsTableModeSupplier;
        if (booleanSupplier == null || !booleanSupplier.getAsBoolean()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadOrDecode$2(MediaItem mediaItem, ImageViewHolder imageViewHolder, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        Bitmap bitmap2 = bitmap;
        ImageViewHolder imageViewHolder2 = imageViewHolder;
        ThreadUtil.postOnUiThread(new a((RecyclerView.Adapter) this, (Object) mediaItem, (Object) imageViewHolder2, bitmap2, 16));
    }

    private void loadOrDecode(ImageViewHolder imageViewHolder, MediaItem mediaItem) {
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        imageViewHolder.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            lambda$loadOrDecode$1(mediaItem, imageViewHolder, memCache);
            return;
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        Objects.requireNonNull(mediaItem);
        instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new Q((Object) this, mediaItem, (Object) imageViewHolder, 10));
    }

    /* access modifiers changed from: private */
    /* renamed from: onBindImage */
    public void lambda$loadOrDecode$1(MediaItem mediaItem, ImageViewHolder imageViewHolder, Bitmap bitmap) {
        if (mediaItem == imageViewHolder.getMediaItem()) {
            imageViewHolder.bindImage(bitmap);
        }
    }

    private void postSaLog(MediaItem mediaItem) {
        String str;
        String str2;
        EventContext eventContext = this.mEventContext;
        if (eventContext != null) {
            str = eventContext.getLocationKey();
        } else {
            str = "";
        }
        AnalyticsLogger instance = AnalyticsLogger.getInstance();
        String screenId = getScreenId(str);
        String eventId = getEventId(str);
        if (LocationKey.isAiEditGroupPanelViewer(str)) {
            str2 = null;
        } else {
            str2 = AnalyticsScreenLabel.SCREEN_LABEL_SINGLE_TAKE.toString();
        }
        instance.postCustomDimension(screenId, eventId, str2, (Pair<String, String>[]) getDetails(str, mediaItem));
    }

    private void removeFocusedBorder(int i2) {
        RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.mListView.findViewHolderForAdapterPosition(i2);
        if (findViewHolderForAdapterPosition != null) {
            ((SingleTakenImageViewHolder) findViewHolderForAdapterPosition).restoreThumbnailBorder();
        }
    }

    private void updateViewSize(SingleTakenImageViewHolder singleTakenImageViewHolder) {
        int i2;
        int i7;
        int i8;
        int i10;
        View findViewById = singleTakenImageViewHolder.itemView.findViewById(R.id.shot_type_text);
        boolean isInMultiWindowMode = SystemUi.isInMultiWindowMode(this.mEventContext.getActivity());
        Resources resources = this.mListView.getResources();
        if (isInMultiWindowMode) {
            i2 = R.dimen.group_panel_item_height_multi_window;
        } else {
            i2 = R.dimen.group_panel_item_height;
        }
        int dimensionPixelSize = resources.getDimensionPixelSize(i2);
        if (!Features.isEnabled(Features.IS_FOLDABLE_TYPE_FOLD) || DeviceInfo.isDexMode(this.mEventContext.getContext())) {
            ViewUtils.setHeight(singleTakenImageViewHolder.getDecoView(11), dimensionPixelSize);
            ViewUtils.setHeight(singleTakenImageViewHolder.getScalableView(), dimensionPixelSize);
            ViewUtils.setWidth(findViewById, resources.getDimensionPixelSize(R.dimen.group_panel_item_text_width));
        } else {
            int foldThumbHeight = getFoldThumbHeight(resources, isInMultiWindowMode);
            int foldThumbWidth = getFoldThumbWidth(resources, foldThumbHeight);
            ViewUtils.resize(singleTakenImageViewHolder.getDecoView(11), foldThumbWidth, foldThumbHeight);
            ViewUtils.resize(singleTakenImageViewHolder.getScalableView(), foldThumbWidth, foldThumbHeight);
            ViewUtils.setWidth(findViewById, foldThumbWidth);
        }
        ViewMarginUtils.setBottomPadding(singleTakenImageViewHolder.itemView, getBottomPadding(resources, isInMultiWindowMode));
        TextView textView = singleTakenImageViewHolder.getTextView();
        if (isInMultiWindowMode) {
            i7 = R.dimen.group_panel_thumbnail_text_view_height_multi_window;
        } else {
            i7 = R.dimen.group_panel_thumbnail_text_view_height;
        }
        ViewUtils.setHeight(textView, resources.getDimensionPixelSize(i7));
        if (isInMultiWindowMode || ResourceCompat.isLandscape((Context) this.mEventContext.getActivity())) {
            i8 = 1;
        } else {
            i8 = 2;
        }
        ViewUtils.setMaxLines(textView, i8);
        if (isInMultiWindowMode) {
            i10 = R.dimen.group_panel_item_text_top_margin_multi_window;
        } else {
            i10 = R.dimen.group_panel_item_text_top_margin;
        }
        ViewMarginUtils.setTopMargin(textView, resources.getDimensionPixelSize(i10));
    }

    public ArrayList<MediaItem> getAllMediaItems() {
        ArrayList<MediaItem> arrayList = this.mMediaItems;
        if (arrayList != null) {
            return arrayList;
        }
        return new ArrayList<>();
    }

    public List<Long> getDataIds() {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaItem> it = this.mMediaItems.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(it.next().getFileId()));
        }
        return arrayList;
    }

    public int getItemCount() {
        if (this.mCount < 0) {
            this.mCount = this.mMediaItems.size();
        }
        return this.mCount;
    }

    public int getItemViewType(int i2) {
        String str;
        EventContext eventContext = this.mEventContext;
        if (eventContext != null) {
            str = eventContext.getLocationKey();
        } else {
            str = "";
        }
        if (LocationKey.isSuperSlowGroupPanelView(str)) {
            return 1;
        }
        if (LocationKey.isHighlightGroupPanelView(str)) {
            return 2;
        }
        return 0;
    }

    public final LayoutInflater getLayoutInflater(Context context) {
        if (this.mLayoutInflater == null) {
            this.mLayoutInflater = LayoutInflater.from(context);
        }
        return this.mLayoutInflater;
    }

    public MediaItem getMediaItemSync(int i2) {
        try {
            ArrayList<MediaItem> arrayList = this.mMediaItems;
            if (arrayList != null) {
                return arrayList.get(i2);
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
            Log.w((CharSequence) "SingleTakenListAdapter", "invalid index : ", e.getMessage());
            return null;
        }
    }

    public boolean isDragSelectSupported() {
        return false;
    }

    public void onExitSelectMode(boolean z) {
        GroupPanelListAdapterListener groupPanelListAdapterListener = this.mAdapterListener;
        if (groupPanelListAdapterListener != null) {
            groupPanelListAdapterListener.onExitSelectModeListener();
        }
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        updateFocusedBorder(i2);
        postSaLog(mediaItem);
        GroupPanelListAdapterListener groupPanelListAdapterListener = this.mAdapterListener;
        if (groupPanelListAdapterListener != null) {
            groupPanelListAdapterListener.onItemClickListener(i2, mediaItem);
        }
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        GroupPanelListAdapterListener groupPanelListAdapterListener = this.mAdapterListener;
        if (groupPanelListAdapterListener == null || !groupPanelListAdapterListener.isSupportSelectMode()) {
            return false;
        }
        return super.onListItemLongClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    public void onListItemSecondaryClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
        String str;
        EventContext eventContext = this.mEventContext;
        if (eventContext != null) {
            str = eventContext.getLocationKey();
        } else {
            str = "";
        }
        if (!LocationKey.isAiEditGroupPanelViewer(str)) {
            this.mActionInvoker.invoke(ViewerAction.SINGLE_TAKEN_SECONDARY_CLICK, mediaItem, motionEvent);
        }
    }

    public void onSelected(int i2, boolean z, boolean z3) {
        super.onSelected(i2, z, z3);
        GroupPanelListAdapterListener groupPanelListAdapterListener = this.mAdapterListener;
        if (groupPanelListAdapterListener != null) {
            groupPanelListAdapterListener.onSelectedListener(getSelectedItemCount(), getMediaItemSync(i2), z);
        }
    }

    public void onStartSelectMode() {
        GroupPanelListAdapterListener groupPanelListAdapterListener = this.mAdapterListener;
        if (groupPanelListAdapterListener != null) {
            groupPanelListAdapterListener.onStartSelectModeListener();
        }
    }

    public void setAdapterListener(GroupPanelListAdapterListener groupPanelListAdapterListener) {
        this.mAdapterListener = groupPanelListAdapterListener;
    }

    public void setDataList(List<MediaItem> list) {
        this.mMediaItems = new ArrayList<>(list);
        this.mCount = -1;
        notifyDataSetChanged();
    }

    public void setFocusedPosition(int i2) {
        this.mFocusedPosition = i2;
    }

    public void setTableModeSupplier(BooleanSupplier booleanSupplier) {
        this.mIsTableModeSupplier = booleanSupplier;
    }

    public void setVisibleArea(int i2, int i7) {
        GroupPanelVideoPreview videoPreview = getVideoPreview();
        if (videoPreview != null) {
            videoPreview.setVisibleArea(i2, i7);
        }
    }

    public void updateFocusedBorder(int i2) {
        removeFocusedBorder(this.mFocusedPosition);
        this.mFocusedPosition = i2;
        drawFocusedBorder(i2);
        this.mListView.smoothScrollToPosition(i2);
    }

    public void updateFocusedItemThumbnail() {
        try {
            MediaItem mediaItemSync = getMediaItemSync(this.mFocusedPosition);
            if (mediaItemSync != null) {
                ThumbnailLoader.getInstance().removeCache(mediaItemSync);
                SingleTakenImageViewHolder singleTakenImageViewHolder = (SingleTakenImageViewHolder) this.mListView.findViewHolderForAdapterPosition(this.mFocusedPosition);
                if (singleTakenImageViewHolder != null) {
                    loadOrDecode(singleTakenImageViewHolder, mediaItemSync);
                }
            }
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("fail to update current thumbnail : e="), "SingleTakenListAdapter");
        }
    }

    public SingleTakenImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new SingleTakenImageViewHolder(getLayoutInflater(viewGroup.getContext()).inflate(R.layout.recycelr_item_group_panel_layout, viewGroup, false), i2);
    }

    public void onViewAttachedToWindow(SingleTakenImageViewHolder singleTakenImageViewHolder) {
        super.onViewAttachedToWindow(singleTakenImageViewHolder);
        if (singleTakenImageViewHolder.getViewPosition() == this.mFocusedPosition) {
            singleTakenImageViewHolder.drawFocusedBorder();
        } else {
            singleTakenImageViewHolder.restoreThumbnailBorder();
        }
    }

    public void onViewDetachedFromWindow(SingleTakenImageViewHolder singleTakenImageViewHolder) {
        super.onViewDetachedFromWindow(singleTakenImageViewHolder);
        if (singleTakenImageViewHolder.isPreviewing()) {
            singleTakenImageViewHolder.stopPreviewForever();
        }
    }

    public void onViewRecycled(SingleTakenImageViewHolder singleTakenImageViewHolder) {
        singleTakenImageViewHolder.recycle();
        super.onViewRecycled(singleTakenImageViewHolder);
    }

    public void onBindViewHolder(SingleTakenImageViewHolder singleTakenImageViewHolder, int i2) {
        super.onBindViewHolder(singleTakenImageViewHolder, i2);
        updateViewSize(singleTakenImageViewHolder);
        MediaItem mediaItem = this.mMediaItems.get(i2);
        singleTakenImageViewHolder.bind(mediaItem);
        loadOrDecode(singleTakenImageViewHolder, mediaItem);
    }

    public void setAdvancedMouseFocusManager() {
    }
}
