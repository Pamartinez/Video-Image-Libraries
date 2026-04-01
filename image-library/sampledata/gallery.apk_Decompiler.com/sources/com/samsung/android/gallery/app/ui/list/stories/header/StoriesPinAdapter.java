package com.samsung.android.gallery.app.ui.list.stories.header;

import B8.e;
import R6.c;
import U5.b;
import a6.g;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StorySharedTransitionHelper;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.PinViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoryPinViewHolderFactory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinAdapter extends RecyclerView.Adapter<PinViewHolder> {
    private Consumer<Boolean> mDataChangedListener;
    private boolean mIsSelectMode;
    private IModel mModel;
    private ListViewHolder.OnItemClickListener mOnItemClickListener;
    private ListViewHolder.OnItemLongClickListener mOnItemLongClickListener;
    private ListViewHolder.OnItemSecondaryClickListener mOnItemSecondaryClickListener;
    private final ArrayList<Integer> mPinDirtyPositions = new ArrayList<>();
    private final StoryPinViewHolderFactory mPinViewHolderFactory;
    private SelectionHelper<Integer> mSelectionHelper;

    public StoriesPinAdapter(Context context, Consumer<Boolean> consumer) {
        this.mDataChangedListener = consumer;
        this.mPinViewHolderFactory = new StoryPinViewHolderFactory(context);
    }

    private void bindItemInfo(ListViewHolder listViewHolder, MediaItem mediaItem) {
        if (isData(listViewHolder) && !PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            Optional.ofNullable(listViewHolder.getTitleView()).ifPresent(new b(17, this, mediaItem));
        }
    }

    private void bindThumbnail(ListViewHolder listViewHolder, MediaItem mediaItem) {
        ThumbKind thumbKind;
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            thumbKind = ThumbKind.LARGE_KIND;
        } else {
            thumbKind = ThumbKind.MEDIUM_KIND;
        }
        listViewHolder.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            lambda$bindThumbnail$1(listViewHolder, memCache);
            return;
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        Objects.requireNonNull(mediaItem);
        instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new O3.b(18, this, listViewHolder));
    }

    private int getDecoItemViewType() {
        if (this.mIsSelectMode) {
            return 95;
        }
        return 76;
    }

    private MediaItem getItem(int i2) {
        IModel iModel = this.mModel;
        if (iModel != null) {
            return iModel.getMediaItem(i2);
        }
        return null;
    }

    private String getTitle(MediaItem mediaItem) {
        Integer num;
        IModel iModel = this.mModel;
        if (iModel != null) {
            num = iModel.getYear(mediaItem);
        } else {
            num = null;
        }
        if (num != null) {
            return AppResources.getQuantityString(R.plurals.rediscover_this_day, num.intValue(), num);
        }
        return mediaItem.getTitle();
    }

    private boolean isData(ListViewHolder listViewHolder) {
        return listViewHolder.getItemViewType() == 0;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindItemInfo$0(MediaItem mediaItem, TextView textView) {
        textView.setText(getTitle(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnail$2(ListViewHolder listViewHolder, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.postOnUiThread(new c(this, listViewHolder, bitmap, 28));
    }

    /* access modifiers changed from: private */
    /* renamed from: onBindThumbnail */
    public void lambda$bindThumbnail$1(ListViewHolder listViewHolder, Bitmap bitmap) {
        listViewHolder.bindImage(bitmap);
    }

    /* access modifiers changed from: private */
    public boolean onItemLongClicked(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ListViewHolder.OnItemLongClickListener onItemLongClickListener = this.mOnItemLongClickListener;
        if (onItemLongClickListener == null || !onItemLongClickListener.onItemLongClick(listViewHolder, i2, mediaItem, i7)) {
            return false;
        }
        return true;
    }

    private void setChecked(PinViewHolder pinViewHolder, int i2) {
        boolean z;
        if (this.mSelectionHelper != null) {
            pinViewHolder.setCheckboxEnabled(getDecoItemViewType(), this.mIsSelectMode);
            MediaItem item = getItem(i2);
            if (item == null || !this.mSelectionHelper.isSelected(Integer.valueOf(item.getAlbumID()))) {
                z = false;
            } else {
                z = true;
            }
            pinViewHolder.setChecked(z);
        }
    }

    public void destroy() {
        this.mDataChangedListener = null;
        IModel iModel = this.mModel;
        if (iModel != null) {
            iModel.setDataChangeListener((PinModelListener) null);
            this.mModel = null;
        }
    }

    public List<Integer> getAllAlbumIds() {
        IModel iModel = this.mModel;
        if (iModel != null) {
            return iModel.getAllAlbumIds();
        }
        return new ArrayList();
    }

    public int getDividerPosition() {
        IModel iModel = this.mModel;
        if (iModel != null) {
            return iModel.getDividerPosition();
        }
        return -1;
    }

    public int getItemCount() {
        IModel iModel = this.mModel;
        if (iModel != null) {
            return iModel.getCount();
        }
        return -1;
    }

    public int getItemViewType(int i2) {
        if (isData(i2)) {
            return 0;
        }
        return -100;
    }

    public MediaItem getMediaItemByAlbumId(int i2) {
        IModel iModel = this.mModel;
        if (iModel != null) {
            return iModel.getMediaItemByAlbumId(i2);
        }
        return null;
    }

    public ArrayList<Integer> getPinDirtyPositions() {
        return this.mPinDirtyPositions;
    }

    public void onDataChanged() {
        notifyDataSetChanged();
        this.mPinDirtyPositions.clear();
        Consumer<Boolean> consumer = this.mDataChangedListener;
        if (consumer != null) {
            consumer.accept(Boolean.TRUE);
        }
    }

    public void onPinDataDirty(ArrayList<Integer> arrayList) {
        this.mPinDirtyPositions.clear();
        this.mPinDirtyPositions.addAll(arrayList);
        Consumer<Boolean> consumer = this.mDataChangedListener;
        if (consumer != null) {
            consumer.accept(Boolean.TRUE);
        }
    }

    public void resetPinAnimation() {
        this.mPinDirtyPositions.clear();
    }

    public void setModel(IModel iModel) {
        this.mModel = iModel;
        iModel.setDataChangeListener(new PinModelListener() {
            public void onDataChanged() {
                StoriesPinAdapter.this.onDataChanged();
            }

            public void onDataRangeChanged(int i2, int i7) {
                StoriesPinAdapter.this.notifyItemRangeChanged(i2, i7);
            }

            public void onPinDataDirty(ArrayList<Integer> arrayList) {
                StoriesPinAdapter.this.onPinDataDirty(arrayList);
            }
        });
        this.mModel.open();
    }

    public void setOnItemClickListener(ListViewHolder.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(ListViewHolder.OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemSecondaryClickListener(ListViewHolder.OnItemSecondaryClickListener onItemSecondaryClickListener) {
        this.mOnItemSecondaryClickListener = onItemSecondaryClickListener;
    }

    public void setSelectMode(boolean z) {
        this.mIsSelectMode = z;
    }

    public void setSelectionHelper(SelectionHelper<Integer> selectionHelper) {
        this.mSelectionHelper = selectionHelper;
    }

    public boolean isData(int i2) {
        IModel iModel = this.mModel;
        return iModel != null && !iModel.isDivider(i2);
    }

    public PinViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mPinViewHolderFactory.createViewHolder(viewGroup, i2);
    }

    public void onViewAttachedToWindow(PinViewHolder pinViewHolder) {
        super.onViewAttachedToWindow(pinViewHolder);
        if (this.mPinDirtyPositions.isEmpty()) {
            pinViewHolder.resetPinItemAnim();
        }
    }

    public void onViewRecycled(PinViewHolder pinViewHolder) {
        StorySharedTransitionHelper.resetTransitionName(pinViewHolder);
        pinViewHolder.recycle();
    }

    public void onBindViewHolder(PinViewHolder pinViewHolder, int i2) {
        MediaItem item = getItem(i2);
        if (item != null) {
            pinViewHolder.bind(item);
            if (isData((ListViewHolder) pinViewHolder)) {
                pinViewHolder.setOnItemClickListener(this.mOnItemClickListener);
                pinViewHolder.setOnSecondaryClickListener(this.mOnItemSecondaryClickListener);
                bindThumbnail(pinViewHolder, item);
                bindItemInfo(pinViewHolder, item);
            }
            if (!this.mPinDirtyPositions.contains(Integer.valueOf(i2))) {
                pinViewHolder.resetPinItemAnim();
            }
            pinViewHolder.setOnItemLongClickListener(new g(8, this));
            setChecked(pinViewHolder, i2);
            StorySharedTransitionHelper.setTransitionName(pinViewHolder);
        }
    }

    public void onBindViewHolder(PinViewHolder pinViewHolder, int i2, List<Object> list) {
        if (list.contains("select_mode")) {
            setChecked(pinViewHolder, i2);
        } else {
            super.onBindViewHolder(pinViewHolder, i2, list);
        }
    }
}
