package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.widget.search.pictures.OnHeaderClickListener;
import com.samsung.android.gallery.widget.search.pictures.SearchCountHeaderView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;
import java.util.function.Supplier;
import z5.n;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchScreenShotHeaderView extends SearchCountHeaderView {
    private SearchScreenShotHeaderAdapter mSubAdapter;
    private RecyclerView mSubListView;
    private SearchScreenShotHeaderAdapter mSubSubAdapter;
    private RecyclerView mSubSubListView;

    public SearchScreenShotHeaderView(ViewGroup viewGroup, Supplier<String> supplier) {
        super(viewGroup);
        bindAdapter(supplier);
    }

    private void bindAdapter(Supplier<String> supplier) {
        this.mSubAdapter = new SearchScreenShotHeaderAdapter(0, supplier);
        this.mSubSubAdapter = new SearchScreenShotHeaderAdapter(1, supplier);
        this.mSubListView.setAdapter(this.mSubAdapter);
        this.mSubSubListView.setAdapter(this.mSubSubAdapter);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setHeaderItemClickListener$0(OnHeaderClickListener onHeaderClickListener, MediaItem mediaItem) {
        onHeaderClickListener.onHeaderClicked(this.mView, 0, mediaItem, (Bitmap) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setHeaderItemClickListener$1(OnHeaderClickListener onHeaderClickListener, MediaItem mediaItem) {
        onHeaderClickListener.onHeaderClicked(this.mView, 1, mediaItem, (Bitmap) null);
    }

    public void bindData(MediaData... mediaDataArr) {
        super.bindData(mediaDataArr);
        this.mSubAdapter.setMediaData(mediaDataArr[0]);
        this.mSubSubAdapter.setMediaData(mediaDataArr[1]);
    }

    public void bindView(View view) {
        super.bindView(view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.search_screen_shot_filter_sub_list);
        this.mSubListView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.search_screen_shot_filter_sub_sub_list);
        this.mSubSubListView = recyclerView2;
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
    }

    public int getLayoutId() {
        return R.layout.recycler_item_search_pictures_header_screen_shot_filter;
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        if (ViewUtils.isTouchedOnView(this.mSubListView, motionEvent) || ViewUtils.isTouchedOnView(this.mSubSubListView, motionEvent)) {
            return false;
        }
        return true;
    }

    public void setEnabled(boolean z) {
        this.mSubAdapter.setEnable(z);
        this.mSubSubAdapter.setEnable(z);
    }

    public void setHeaderItemClickListener(OnHeaderClickListener onHeaderClickListener) {
        if (onHeaderClickListener != null) {
            this.mSubAdapter.setOnItemClickListener(new n(this, onHeaderClickListener, 0));
            this.mSubSubAdapter.setOnItemClickListener(new n(this, onHeaderClickListener, 1));
            return;
        }
        this.mSubAdapter.setOnItemClickListener((Consumer<MediaItem>) null);
        this.mSubSubAdapter.setOnItemClickListener((Consumer<MediaItem>) null);
    }
}
