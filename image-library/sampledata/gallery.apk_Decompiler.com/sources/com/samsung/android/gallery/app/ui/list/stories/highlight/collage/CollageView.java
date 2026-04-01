package com.samsung.android.gallery.app.ui.list.stories.highlight.collage;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c4.C0438h;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.SpannedGridLayoutManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollageView {
    private CollageAdapter mAdapter;
    private final RecyclerView mCollageList;
    private CollageType mType;

    public CollageView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.collage_list);
        this.mCollageList = recyclerView;
        ViewUtils.setVisibleOrGone(recyclerView, true);
    }

    private CollageViewHolder findFirstVideoItem() {
        int childCount = this.mCollageList.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            CollageViewHolder viewHolder = getViewHolder(i2);
            if (viewHolder != null && StoryHelper.isVideoItem(viewHolder.getMediaItem())) {
                return viewHolder;
            }
        }
        return null;
    }

    private CollageViewHolder getViewHolder(int i2) {
        RecyclerView recyclerView = this.mCollageList;
        return (CollageViewHolder) recyclerView.findContainingViewHolder(recyclerView.getChildAt(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ SpannedGridLayoutManager.SpanInfo lambda$setSpanLookup$0(int i2, float f, int i7) {
        int i8;
        float[] spanRatio = this.mType.getSpanRatio(i7);
        float f5 = (float) i2;
        int i10 = 0;
        int i11 = (int) (spanRatio[0] * f5);
        if (i11 % 2 != 0) {
            i8 = -1;
        } else {
            i8 = 0;
        }
        int i12 = i11 + i8;
        int i13 = (int) (f5 * spanRatio[1] * f);
        if (i13 % 2 != 0) {
            i10 = -1;
        }
        return new SpannedGridLayoutManager.SpanInfo(i12, i13 + i10);
    }

    public RecyclerView getListView() {
        return this.mCollageList;
    }

    public MediaItem getMediaItem(int i2) {
        CollageAdapter collageAdapter = this.mAdapter;
        if (collageAdapter != null) {
            return collageAdapter.getMediaItem(i2);
        }
        return null;
    }

    public int getVideoIndex() {
        CollageAdapter collageAdapter = this.mAdapter;
        if (collageAdapter != null) {
            return collageAdapter.getVideoItemIndex();
        }
        return -1;
    }

    public void initData(CollageType collageType, ArrayList<MediaItem> arrayList) {
        this.mType = collageType;
        this.mAdapter.setCollageType(collageType);
        this.mAdapter.setData(arrayList);
    }

    public void initLayoutManager(ViewGroup viewGroup, RecyclerView recyclerView, float f) {
        int i2 = recyclerView.getLayoutParams().width;
        float f5 = ((float) recyclerView.getLayoutParams().height) / ((float) recyclerView.getLayoutParams().width);
        CollageLayoutManager collageLayoutManager = new CollageLayoutManager(viewGroup, i2);
        collageLayoutManager.setBaseSize(f);
        recyclerView.setLayoutManager(collageLayoutManager);
        setSpanLookup(collageLayoutManager, i2, f5);
    }

    public void initListView(View view, float f) {
        if (this.mAdapter == null) {
            CollageAdapter collageAdapter = new CollageAdapter(this.mCollageList);
            this.mAdapter = collageAdapter;
            this.mCollageList.setAdapter(collageAdapter);
        }
        initLayoutManager((ViewGroup) view, this.mCollageList, f);
    }

    public void setSpanLookup(SpannedGridLayoutManager spannedGridLayoutManager, int i2, float f) {
        Log.d("CollageView", "collage span", Integer.valueOf(i2), Float.valueOf(f));
        spannedGridLayoutManager.setSpanLookup(new b(this, i2, f));
    }

    public void startPreviewInternal() {
        CollageViewHolder findFirstVideoItem = findFirstVideoItem();
        if (findFirstVideoItem != null && !findFirstVideoItem.isPreviewing()) {
            findFirstVideoItem.startPreview();
        }
    }

    public void stopPreviewInternal() {
        int childCount = this.mCollageList.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            Optional.ofNullable(getViewHolder(i2)).ifPresent(new C0438h(4));
        }
    }
}
