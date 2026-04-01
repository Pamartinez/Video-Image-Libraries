package com.samsung.android.gallery.app.ui.list.stories;

import A4.N;
import Qb.e;
import S5.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailPreview;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesHeaderViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesViewHolderFactory;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesViewAdapter<V extends IStoriesView> extends StoriesHeaderViewAdapter<V> {
    protected int mCreateVhCounter;
    private final DeleteAnimDelegate<V> mDeleteAnimDelegate;
    private long mLastItemMenuClickedTime = 0;
    private boolean mLocationAuthEnabled;
    private StoriesViewHolderFactory mViewHolderFactory;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StoriesThumbnailPreview extends ThumbnailPreview<IStoriesView> {
        public StoriesThumbnailPreview(IStoriesView iStoriesView) {
            super(iStoriesView);
        }

        public int getViewHolderPosition(PreviewViewHolder previewViewHolder) {
            if (((IStoriesView) this.mView).isDestroyed()) {
                return 0;
            }
            View decoView = previewViewHolder.getDecoView(56);
            if (decoView == null) {
                return super.getViewHolderPosition(previewViewHolder);
            }
            int top = decoView.getTop() + previewViewHolder.itemView.getTop();
            int height = decoView.getHeight() + top;
            int height2 = (int) ((((float) decoView.getHeight()) * 0.3f) + ((float) top));
            int height3 = (int) (((float) height) - (((float) decoView.getHeight()) * 0.3f));
            if (height2 >= upperLimit() && height2 <= lowerLimit()) {
                return height2;
            }
            if (height3 < upperLimit() || height3 > lowerLimit()) {
                return super.getViewHolderPosition(previewViewHolder);
            }
            return height3;
        }

        public boolean limitDuration() {
            return true;
        }
    }

    public StoriesViewAdapter(V v, String str, View view) {
        super(v, str, view);
        checkLocationAuthChanged();
        this.mViewHolderFactory = createViewHolderFactory(getContext());
        this.mDeleteAnimDelegate = new DeleteAnimDelegate<>(v);
    }

    private boolean checkLocationAuthChanged() {
        boolean z = this.mLocationAuthEnabled;
        boolean isLocationAuthEnabled = isLocationAuthEnabled();
        this.mLocationAuthEnabled = isLocationAuthEnabled;
        if (z != isLocationAuthEnabled) {
            return true;
        }
        return false;
    }

    private void enableLocationText(ListViewHolder listViewHolder) {
        listViewHolder.updateDecoration(1024, Boolean.valueOf(this.mLocationAuthEnabled));
    }

    private boolean isLocationAuthEnabled() {
        if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR) || PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleDataUpdateAnimation$3() {
        notifyDataSetChanged();
        checkPreviewCandidate();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$requestThumbnail$1(ListViewHolder listViewHolder, ThumbnailRequestHolder thumbnailRequestHolder) {
        return !BaseListViewAdapter.isViewSame(listViewHolder, thumbnailRequestHolder.getPosition());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$seOnItemMenuClickListener$2(ListViewHolder listViewHolder, PointF pointF, MediaItem mediaItem) {
        long uptimeMillis = SystemClock.uptimeMillis();
        this.mLastItemMenuClickedTime = uptimeMillis;
        if (uptimeMillis - this.mLastItemMenuClickedTime > 500) {
            ((IStoriesView) this.mView).onListItemMenuClick(listViewHolder.getDecoView(52), pointF, mediaItem);
        }
    }

    private void seOnItemMenuClickListener(ListViewHolder listViewHolder) {
        if (listViewHolder.getViewType() == 0) {
            ((StoriesViewHolder) listViewHolder).setOnItemMenuClickListener(new a(this));
        }
    }

    private void updateLocationInfo() {
        String str = this.TAG;
        Integer valueOf = Integer.valueOf(getItemCount());
        Log.d(str, "updateLocationInfo", 0, valueOf, "stories_location", "enable=" + this.mLocationAuthEnabled);
        notifyItemRangeChanged(0, getItemCount(), "stories_location");
    }

    public void checkVisibleViewHoldersForXLarge() {
        if (supportHighQuality()) {
            requestXLargeForAllVisible();
        }
    }

    public ThumbnailPreview<?> createThumbnailPreview() {
        return new StoriesThumbnailPreview((IStoriesView) this.mView);
    }

    public StoriesViewHolderFactory createViewHolderFactory(Context context) {
        return new StoriesViewHolderFactory(context);
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.LARGE_KIND;
    }

    public boolean handleDataUpdateAnimation() {
        if (!this.mDeleteAnimDelegate.handleDeleteWithItemAnimator()) {
            return false;
        }
        ThreadUtil.postOnUiThreadDelayed(new e(10, this), 500);
        return true;
    }

    public boolean isPreviewAvailable() {
        return !((IStoriesView) this.mView).isDrawerSliding();
    }

    public void onAbortDelete() {
        this.mDeleteAnimDelegate.onAbortDelete();
    }

    public void onPostLongPressSelectMode() {
        ((IStoriesView) this.mView).postAnalyticsLog(getPrevScreenId(), AnalyticsEventId.EVENT_STORIES_ITEM_LONG_PRESS);
    }

    public void onPrepareDelete(GridLayoutManager gridLayoutManager) {
        this.mDeleteAnimDelegate.onPrepareDelete(gridLayoutManager, this.mSelectionManager.getSelectedList());
    }

    public void requestThumbnail(ListViewHolder listViewHolder, int i2) {
        ThumbKind thumbKind;
        ThumbnailRequestHolder thumbnailRequestHolder = new ThumbnailRequestHolder(listViewHolder);
        MediaItem mediaItem = thumbnailRequestHolder.getMediaItem();
        if (mediaItem == null || !mediaItem.isHeif()) {
            super.requestThumbnail(listViewHolder, i2);
        } else if (thumbnailRequestHolder.getPosition() == -1) {
            String str = this.TAG;
            Log.e(str, "Fatal : no media item or invalid view position to request Thumb : " + listViewHolder);
        } else {
            if (mediaItem.isHeif()) {
                thumbKind = ThumbKind.MEDIUM_KIND;
            } else {
                thumbKind = getThumbnailKind();
            }
            listViewHolder.setThumbKind(thumbKind);
            Bitmap brokenMediaItem = getBrokenMediaItem(mediaItem);
            if (brokenMediaItem != null) {
                setBitmapWithBind(brokenMediaItem, thumbnailRequestHolder, getThumbnailKind());
            } else {
                ThumbnailLoader.getInstance().loadThumbnail(mediaItem, listViewHolder.getThumbKind(), thumbnailRequestHolder, new a(this), new N(listViewHolder, thumbnailRequestHolder, 2));
            }
        }
    }

    public void requestXLargeThumbnail(int i2) {
        ListViewHolder listViewHolder = (ListViewHolder) this.mGalleryListView.findViewHolderForAdapterPosition(i2);
        if (listViewHolder != null && listViewHolder.getMediaItem() != null && listViewHolder.getMediaItem().isHeif()) {
            requestThumbnail(listViewHolder, getGridSize());
        }
    }

    public void resetItemHeight() {
        this.mItemHeight = -1;
    }

    public void resume() {
        super.resume();
        if (checkLocationAuthChanged()) {
            updateLocationInfo();
        }
    }

    public void setListeners(ListViewHolder listViewHolder) {
        super.setListeners(listViewHolder);
        if (!((IStoriesView) this.mView).supportLongClick()) {
            listViewHolder.setOnItemLongClickListener((ListViewHolder.OnItemLongClickListener) null);
            listViewHolder.setOnSecondaryClickListener((ListViewHolder.OnItemSecondaryClickListener) null);
        }
    }

    public boolean supportHighQuality() {
        return true;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (!list.contains("stories_location") || !listViewHolder.updateDecoration(1024, Boolean.valueOf(this.mLocationAuthEnabled))) {
            if (!PreferenceFeatures.OneUi40.SUPPORT_MEMORY_COVER_ACTION_ON_SELECTION_MODE) {
                seOnItemMenuClickListener(listViewHolder);
            }
            this.mDeleteAnimDelegate.listSlideAnimation(listViewHolder);
            super.onBindViewHolder(listViewHolder, i2, list);
            enableLocationText(listViewHolder);
        }
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        int i7 = this.mCreateVhCounter + 1;
        this.mCreateVhCounter = i7;
        if (i7 > 10) {
            String str = this.TAG;
            Log.w(str, "onCreateViewHolder#ui {#" + this.mCreateVhCounter + ",typ=" + i2 + "} unexpected");
        }
        return this.mViewHolderFactory.createViewHolder(viewGroup, i2);
    }

    public void onPrepareDelete(GridLayoutManager gridLayoutManager, ArrayList<Integer> arrayList) {
        this.mDeleteAnimDelegate.onPrepareDelete(gridLayoutManager, arrayList);
    }
}
