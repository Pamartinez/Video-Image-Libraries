package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import C4.i;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import z5.m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchScreenShotHeaderAdapter extends RecyclerView.Adapter<SearchScreenShotHeaderViewHolder> {
    private final int mDepth;
    private boolean mEnabled = true;
    private Consumer<MediaItem> mListener;
    private final Supplier<String> mLocationKey;
    private MediaData mMediaData;

    public SearchScreenShotHeaderAdapter(int i2, Supplier<String> supplier) {
        this.mDepth = i2;
        this.mLocationKey = supplier;
    }

    private MediaItem getItem(int i2) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            return mediaData.read(i2);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(int i2, SearchScreenShotHeaderViewHolder searchScreenShotHeaderViewHolder, MediaItem mediaItem, View view) {
        Consumer<MediaItem> consumer = this.mListener;
        if (consumer != null) {
            consumer.accept(getItem(i2));
            updateFocus(searchScreenShotHeaderViewHolder, mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(SearchScreenShotHeaderViewHolder searchScreenShotHeaderViewHolder, int i2, MediaItem mediaItem) {
        searchScreenShotHeaderViewHolder.bind(mediaItem);
        searchScreenShotHeaderViewHolder.setOnItemClickListener(new m(i2, this, searchScreenShotHeaderViewHolder, mediaItem));
        updateFocus(searchScreenShotHeaderViewHolder, mediaItem);
    }

    private void updateFocus(SearchScreenShotHeaderViewHolder searchScreenShotHeaderViewHolder, MediaItem mediaItem) {
        ArrayList arrayList;
        boolean z;
        String argValue = ArgumentsUtil.getArgValue(this.mLocationKey.get(), "sub_sub");
        if (!TextUtils.isEmpty(argValue)) {
            arrayList = new ArrayList(Arrays.asList(argValue.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
        } else {
            arrayList = new ArrayList();
        }
        if (arrayList.size() <= this.mDepth) {
            z = mediaItem.getSubCategory().equals(ScreenShotFilterType.All.key());
        } else {
            z = mediaItem.getSubCategory().equals(arrayList.get(this.mDepth));
        }
        searchScreenShotHeaderViewHolder.updateFocus(z);
    }

    public int getItemCount() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            return mediaData.getCount();
        }
        return 0;
    }

    public SearchScreenShotHeaderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new SearchScreenShotHeaderViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_screen_shot_filter_button_layout, viewGroup, false));
    }

    public void setEnable(boolean z) {
        String str;
        this.mEnabled = z;
        int itemCount = getItemCount();
        if (z) {
            str = "enable";
        } else {
            str = "disable";
        }
        notifyItemRangeChanged(0, itemCount, str);
    }

    public void setMediaData(MediaData mediaData) {
        this.mMediaData = mediaData;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(Consumer<MediaItem> consumer) {
        this.mListener = consumer;
    }

    public void onBindViewHolder(SearchScreenShotHeaderViewHolder searchScreenShotHeaderViewHolder, int i2) {
        ViewUtils.setViewEnabled(searchScreenShotHeaderViewHolder.itemView, this.mEnabled);
        Optional.ofNullable(getItem(i2)).ifPresent(new i((Object) this, (Object) searchScreenShotHeaderViewHolder, i2, 11));
    }

    public void onBindViewHolder(SearchScreenShotHeaderViewHolder searchScreenShotHeaderViewHolder, int i2, List<Object> list) {
        if (list.contains("enable") || list.contains("disable")) {
            ViewUtils.setViewEnabled(searchScreenShotHeaderViewHolder.itemView, list.contains("enable"));
        } else {
            super.onBindViewHolder(searchScreenShotHeaderViewHolder, i2, list);
        }
    }
}
