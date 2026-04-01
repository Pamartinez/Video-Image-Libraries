package com.samsung.android.gallery.app.ui.list.timeline.quicksearch;

import B8.e;
import E7.c;
import O3.y;
import S6.b;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.quicksearch.QuickSearchManager;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.Optional;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsQuickSearchFilterListViewAdapter extends RecyclerView.Adapter<ImageTitleViewHolder> {
    private MediaData mMediaData;
    private BiConsumer<String, Boolean> mOnItemClickListener;
    final QuickSearchManager mQuickSearchManager;

    public AbsQuickSearchFilterListViewAdapter(Blackboard blackboard) {
        this.mQuickSearchManager = QuickSearchManager.getInstance(blackboard);
    }

    private void bindThumbnail(ImageTitleViewHolder imageTitleViewHolder, MediaItem mediaItem) {
        ThumbnailLoader.getInstance().loadThumbnail(mediaItem, ThumbKind.MEDIUM_KIND, new e(mediaItem, 2), new b(imageTitleViewHolder, 0));
    }

    private MediaItem getItem(int i2) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            return mediaData.read(i2);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(ImageTitleViewHolder imageTitleViewHolder, MediaItem mediaItem) {
        imageTitleViewHolder.bind(mediaItem);
        imageTitleViewHolder.setOnItemClickListener(new y(27, this));
        bindThumbnail(imageTitleViewHolder, mediaItem);
        updateVisualCue(imageTitleViewHolder, getChecked(mediaItem));
    }

    private void updateVisualCue(ImageViewHolder imageViewHolder, boolean z) {
        if (z) {
            imageViewHolder.drawVisualCue();
        } else {
            imageViewHolder.eraseVisualCue();
        }
    }

    public abstract boolean getChecked(MediaItem mediaItem);

    public int getItemCount() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            return mediaData.getCount();
        }
        return 0;
    }

    public abstract int getLayoutId();

    public ImageTitleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ImageTitleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false), i2);
    }

    public void onItemClicked(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        boolean z = !getChecked(mediaItem);
        updateVisualCue((ImageViewHolder) listViewHolder, z);
        Optional.ofNullable(this.mOnItemClickListener).ifPresent(new c(mediaItem, z, 3));
    }

    public void release() {
        if (this.mMediaData != null) {
            this.mMediaData = null;
        }
        if (this.mOnItemClickListener != null) {
            this.mOnItemClickListener = null;
        }
    }

    public void setMediaData(MediaData mediaData) {
        this.mMediaData = mediaData;
    }

    public void setOnItemClickListener(BiConsumer<String, Boolean> biConsumer) {
        this.mOnItemClickListener = biConsumer;
    }

    public void onBindViewHolder(ImageTitleViewHolder imageTitleViewHolder, int i2) {
        Optional.ofNullable(getItem(i2)).ifPresent(new N3.c(19, this, imageTitleViewHolder));
    }

    public void onViewRecycled(ImageTitleViewHolder imageTitleViewHolder) {
        imageTitleViewHolder.recycle();
    }
}
