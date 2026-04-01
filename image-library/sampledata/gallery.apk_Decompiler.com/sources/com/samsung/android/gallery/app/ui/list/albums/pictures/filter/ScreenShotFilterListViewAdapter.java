package com.samsung.android.gallery.app.ui.list.albums.pictures.filter;

import K4.a;
import N4.b;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.screenshotfilter.ScreenShotFilterManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScreenShotFilterListViewAdapter extends RecyclerView.Adapter<ScreenShotFilterListViewHolder> {
    private MediaData mMediaData;
    private Consumer<String> mOnItemClickListener;
    private final ScreenShotFilterManager mScreenShotFilterManager;

    public ScreenShotFilterListViewAdapter(Blackboard blackboard) {
        this.mScreenShotFilterManager = ScreenShotFilterManager.getInstance(blackboard);
    }

    private MediaItem getItem(int i2) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            return mediaData.read(i2);
        }
        return null;
    }

    private boolean isSelected(MediaItem mediaItem) {
        return this.mScreenShotFilterManager.isFiltered(mediaItem.getSubCategory());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(ScreenShotFilterListViewHolder screenShotFilterListViewHolder, MediaItem mediaItem) {
        screenShotFilterListViewHolder.bind(mediaItem);
        screenShotFilterListViewHolder.setOnItemClickListener(new a(15, this));
        screenShotFilterListViewHolder.updateFocus(isSelected(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(ScreenShotFilterListViewHolder screenShotFilterListViewHolder, MediaItem mediaItem) {
        screenShotFilterListViewHolder.updateFocus(isSelected(mediaItem));
    }

    /* access modifiers changed from: private */
    public void onItemClicked(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        Optional.ofNullable(this.mOnItemClickListener).ifPresent(new G6.a(mediaItem, 3));
        notifyItemRangeChanged(0, getItemCount(), "focus_changed");
    }

    public int getItemCount() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            return mediaData.getCount();
        }
        return 0;
    }

    public ScreenShotFilterListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ScreenShotFilterListViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_screen_shot_filter_button_layout, viewGroup, false), i2);
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

    public void setOnItemClickListener(Consumer<String> consumer) {
        this.mOnItemClickListener = consumer;
    }

    public void onViewRecycled(ScreenShotFilterListViewHolder screenShotFilterListViewHolder) {
        screenShotFilterListViewHolder.recycle();
    }

    public void onBindViewHolder(ScreenShotFilterListViewHolder screenShotFilterListViewHolder, int i2) {
        Optional.ofNullable(getItem(i2)).ifPresent(new b(this, screenShotFilterListViewHolder, 0));
    }

    public void onBindViewHolder(ScreenShotFilterListViewHolder screenShotFilterListViewHolder, int i2, List<Object> list) {
        if (list.contains("focus_changed")) {
            Optional.ofNullable(getItem(i2)).ifPresent(new b(this, screenShotFilterListViewHolder, 1));
        }
        super.onBindViewHolder(screenShotFilterListViewHolder, i2, list);
    }
}
