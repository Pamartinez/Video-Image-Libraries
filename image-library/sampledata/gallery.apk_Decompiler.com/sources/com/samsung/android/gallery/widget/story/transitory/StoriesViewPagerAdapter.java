package com.samsung.android.gallery.widget.story.transitory;

import B8.f;
import O3.l;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import bc.C0584a;
import bc.d;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.LiveMotionAdapter;
import com.samsung.android.gallery.widget.story.abstraction.IStoryContent;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesViewPagerAdapter extends LiveMotionAdapter<ListViewHolder> {
    private int mChildDataCount;
    private final List<Runnable> mClearRunnableList = new ArrayList();
    private int mContentWidth = -1;
    protected int mDataCount;
    ViewPagerItemFactory mItemFactory;
    private ListViewHolder.OnItemClickListener mOnItemClickListener;
    private RecapEventListener mRecapEventListener;
    protected int mSideMargin;
    private boolean mSupportFooter;

    public StoriesViewPagerAdapter(ViewPagerItemFactory viewPagerItemFactory) {
        this.mItemFactory = viewPagerItemFactory;
    }

    private int getContentHeight(View view, int i2) {
        Resources resources = view.getResources();
        return (int) ((((float) resources.getDimensionPixelOffset(R$dimen.stories_category_transitory_content_height_v85)) / ((float) resources.getDimensionPixelOffset(R$dimen.stories_category_transitory_content_width_v85))) * ((float) i2));
    }

    private ThumbKind getDefaultThumbKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    private boolean isOnDemandItem(int i2) {
        if (!supportFooter() || !isLast(getDataPosition(i2))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadThumbnailWithKindCheck$0(ListViewHolder listViewHolder, MediaItem mediaItem, ThumbKind thumbKind, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind2) {
        Bitmap bitmap2;
        if (bitmap != null) {
            UniqueKey uniqueKey2 = uniqueKey;
            bitmap2 = bitmap;
            if (!verifyBindingBitmap(listViewHolder, mediaItem, thumbKind, uniqueKey2, bitmap2)) {
                return;
            }
        } else {
            bitmap2 = bitmap;
        }
        onThumbnailLoadCompleted(listViewHolder, mediaItem, bitmap2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThumbnailLoadCompleted$1(MediaItem mediaItem, ListViewHolder listViewHolder) {
        requestThumbnail(mediaItem, listViewHolder, ThumbKind.XLARGE_NC_KIND);
    }

    private void loadThumbnailWithKindCheck(MediaItem mediaItem, ListViewHolder listViewHolder, ThumbKind thumbKind) {
        MediaItem mediaItem2 = mediaItem;
        ThumbKind thumbKind2 = thumbKind;
        ThumbnailLoader.getInstance().loadThumbnail(mediaItem2, thumbKind2, new f((Object) this, (Object) listViewHolder, mediaItem2, (Object) thumbKind2, 4));
    }

    private void refreshBadgeView(ListViewHolder listViewHolder) {
        View decoView = listViewHolder.getDecoView(1);
        boolean isVisible = ViewUtils.isVisible(decoView);
        boolean isNewStory = StoryHelper.isNewStory(MediaItemStory.getStoryNotifyStatus(listViewHolder.getMediaItem()));
        if (isVisible != isNewStory) {
            ViewUtils.setVisibleOrGone(decoView, isNewStory);
        }
    }

    private void requestThumbnail(MediaItem mediaItem, ListViewHolder listViewHolder, ThumbKind thumbKind) {
        listViewHolder.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            onThumbnailLoadCompleted(listViewHolder, mediaItem, memCache);
        } else {
            loadThumbnailWithKindCheck(mediaItem, listViewHolder, thumbKind);
        }
    }

    private boolean verifyBindingBitmap(ListViewHolder listViewHolder, MediaItem mediaItem, ThumbKind thumbKind, UniqueKey uniqueKey, Bitmap bitmap) {
        Bitmap bitmap2 = listViewHolder.getBitmap();
        if (bitmap2 == null) {
            return true;
        }
        if (listViewHolder.getThumbKind() == thumbKind && uniqueKey.getKey() == mediaItem.getThumbCacheKey().hashCode() && bitmap2.getWidth() < bitmap.getWidth()) {
            return true;
        }
        return false;
    }

    public void bindThumbnail(MediaItem mediaItem, ListViewHolder listViewHolder) {
        if (listViewHolder.hasImageView()) {
            requestThumbnail(mediaItem, listViewHolder, getThumbKind(mediaItem));
        }
    }

    public void destroy() {
        this.mClearRunnableList.forEach(new l(0));
    }

    public MediaItem getBindMediaItem(int i2) {
        MediaItem mediaItem;
        MediaItem bindMediaItem = super.getBindMediaItem(i2);
        if (MediaItemStory.hasCoverDesignData(bindMediaItem) && (mediaItem = (MediaItem) MediaItemStory.getEffectItem(bindMediaItem)) != null) {
            return mediaItem;
        }
        if (!supportFooter() || !isLast(i2)) {
            return bindMediaItem;
        }
        return MediaItemBuilder.createEmpty();
    }

    public int getChildDataCount() {
        ArrayList<MediaItem> arrayList;
        if (!PocFeatures.SUPPORT_RECAP_STACK_UI) {
            return 0;
        }
        MediaData mediaData = this.mData;
        if (mediaData != null) {
            arrayList = mediaData.getAllData();
        } else {
            arrayList = new ArrayList<>();
        }
        Iterator<MediaItem> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (MediaItemStory.isRecap(next)) {
                return next.getCount();
            }
        }
        return 0;
    }

    public int getDataPosition(int i2) {
        MediaData mediaData = this.mData;
        if ((mediaData == null || mediaData.getCount() == 0 || i2 < 0) && !supportFooter()) {
            return -1;
        }
        return i2;
    }

    public int getItemCount() {
        return (supportFooter() ? 1 : 0) + this.mDataCount;
    }

    public int getItemViewType(int i2) {
        return this.mItemFactory.getItemViewType(getMediaItem(i2), isOnDemandItem(i2));
    }

    public ThumbKind getThumbKind(MediaItem mediaItem) {
        return getDefaultThumbKind();
    }

    public int getTotalCountForDisplay() {
        return this.mDataCount;
    }

    public void invalidateData() {
        int i2;
        int i7;
        int i8 = this.mDataCount;
        MediaData mediaData = this.mData;
        if (mediaData != null) {
            i2 = mediaData.getCount();
        } else {
            i2 = 0;
        }
        this.mDataCount = i2;
        this.mChildDataCount = getChildDataCount();
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_80 && i8 != (i7 = this.mDataCount)) {
            notifyItemRangeChanged(0, i7, "PAYLOAD_ON_SIZE_CHANGED");
        }
    }

    public boolean isLast(int i2) {
        if (i2 == getItemCount() - 1) {
            return true;
        }
        return false;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mClearRunnableList.forEach(new l(0));
        this.mClearRunnableList.clear();
    }

    public void onPreBindViewHolder(ListViewHolder listViewHolder, MediaItem mediaItem, int i2) {
        super.onPreBindViewHolder(listViewHolder, mediaItem, i2);
        updateContentView(listViewHolder.itemView);
        if (listViewHolder instanceof IRecapContent) {
            ((IRecapContent) listViewHolder).setContentWidth(this.mContentWidth);
        }
        listViewHolder.handleEvent(1000, Integer.valueOf(getTotalCountForDisplay()), Integer.valueOf(this.mChildDataCount));
    }

    public void onThumbnailLoadCompleted(ListViewHolder listViewHolder, MediaItem mediaItem, Bitmap bitmap) {
        super.onThumbnailLoadCompleted(listViewHolder, mediaItem, bitmap);
        if (listViewHolder.getThumbKind() == getDefaultThumbKind()) {
            ThreadUtil.postOnUiThread(new d((Object) this, (Object) mediaItem, (Object) listViewHolder, 0));
        }
    }

    public boolean prepareNext(int i2) {
        if (i2 >= getItemCount() || !super.prepareNext(i2)) {
            return false;
        }
        return true;
    }

    public void setContentWidth(int i2) {
        this.mContentWidth = i2;
    }

    public void setOnItemClickListener(ListViewHolder.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setRecapEventListener(RecapEventListener recapEventListener) {
        this.mRecapEventListener = recapEventListener;
    }

    public void setSideMargin(int i2) {
        this.mSideMargin = i2;
    }

    public void setSupportFooter(boolean z) {
        this.mSupportFooter = z;
    }

    public boolean supportFooter() {
        return this.mSupportFooter;
    }

    public void updateContentView(View view) {
        View findViewById = view.findViewById(R$id.content_parent);
        ViewMarginUtils.setHorizontalMargin(findViewById, this.mSideMargin);
        if ("recap".equals(view.getTag())) {
            ViewUtils.setWidth(findViewById, this.mContentWidth);
        } else {
            ViewUtils.setViewSize(findViewById, Integer.valueOf(this.mContentWidth), Integer.valueOf(getContentHeight(view, this.mContentWidth)));
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("PAYLOAD_UPDATE_BADGE")) {
            refreshBadgeView(listViewHolder);
        } else if (list.contains("UPDATE_EXTRA_PADDING")) {
            updateContentView(listViewHolder.itemView);
        } else if (list.contains("PAYLOAD_ON_SIZE_CHANGED")) {
            listViewHolder.handleEvent(1000, Integer.valueOf(getTotalCountForDisplay()), Integer.valueOf(this.mChildDataCount));
            listViewHolder.updateDecoration(SerializeOptions.SORT, new Object[0]);
        } else if (list.contains("REDUCED_TRANSPARENCY_CHANGED")) {
            listViewHolder.handleEvent(1001, new Object[0]);
        } else if (list.contains("ON_DATE_TIME_CHANGED")) {
            listViewHolder.handleEvent(1004, new Object[0]);
        } else {
            listViewHolder.setOnItemClickListener(this.mOnItemClickListener);
            if (listViewHolder instanceof IRecapContent) {
                ((IRecapContent) listViewHolder).setRecapEventListener(this.mRecapEventListener);
            }
            super.onBindViewHolder(listViewHolder, i2, list);
        }
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        ListViewHolder createItemViewHolder = this.mItemFactory.createItemViewHolder(viewGroup, i2);
        if (createItemViewHolder instanceof IStoryContent) {
            IStoryContent iStoryContent = (IStoryContent) createItemViewHolder;
            List<Runnable> list = this.mClearRunnableList;
            Objects.requireNonNull(iStoryContent);
            list.add(new C0584a(2, iStoryContent));
        }
        return this.mItemFactory.createItemViewHolder(viewGroup, i2);
    }

    public void onViewAttachedToWindow(ListViewHolder listViewHolder) {
        super.onViewAttachedToWindow(listViewHolder);
        ViewUtils.setAlpha(listViewHolder.itemView, 1.0f);
        listViewHolder.itemView.invalidate();
    }

    public void onViewRecycled(ListViewHolder listViewHolder) {
        listViewHolder.recycle();
    }
}
