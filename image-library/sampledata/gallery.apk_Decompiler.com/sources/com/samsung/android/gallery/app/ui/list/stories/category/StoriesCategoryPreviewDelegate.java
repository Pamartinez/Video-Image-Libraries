package com.samsung.android.gallery.app.ui.list.stories.category;

import U5.d;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.AbsPreviewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatBaseViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatTransitoryItemViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.utils.Timer;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesCategoryPreviewDelegate extends AbsPreviewDelegate {
    private static final int DURATION_TIMER_ID = Timer.getTimerId();
    StoriesCategory2Header mHeader;

    public StoriesCategoryPreviewDelegate() {
        super(DURATION_TIMER_ID);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onStartInternal$0(PreviewViewHolder previewViewHolder, int i2) {
        setPlayedList(previewViewHolder.getMediaItem());
        stop();
        resetCurrent();
    }

    private void makeCandidatesFromCat(RecyclerView recyclerView, List<PreviewViewHolder> list) {
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            StoriesCatBaseViewHolder storiesCatBaseViewHolder = (StoriesCatBaseViewHolder) recyclerView.findContainingViewHolder(recyclerView.getChildAt(i2));
            if (storiesCatBaseViewHolder != null && !storiesCatBaseViewHolder.getDataKey().startsWith("location://stories/category/transitory")) {
                list.addAll(storiesCatBaseViewHolder.getPlayableVH());
            }
        }
    }

    public PreviewViewHolder findHighPriorityPreviewHolder() {
        GalleryListView galleryListView;
        StoriesCategory2Header storiesCategory2Header = this.mHeader;
        if (storiesCategory2Header != null) {
            galleryListView = storiesCategory2Header.getList();
        } else {
            galleryListView = this.mList;
        }
        if (galleryListView == null) {
            return null;
        }
        int childCount = galleryListView.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            StoriesCatBaseViewHolder storiesCatBaseViewHolder = (StoriesCatBaseViewHolder) galleryListView.findContainingViewHolder(galleryListView.getChildAt(i2));
            if (storiesCatBaseViewHolder == null || !storiesCatBaseViewHolder.getDataKey().startsWith("location://stories/category/transitory")) {
                i2++;
            } else {
                ArrayList<PreviewViewHolder> playableVH = storiesCatBaseViewHolder.getPlayableVH();
                if (!playableVH.isEmpty()) {
                    return playableVH.get(0);
                }
                return null;
            }
        }
        return null;
    }

    public void makeCandidates(RecyclerView recyclerView, List<PreviewViewHolder> list) {
        StoriesCategory2Header storiesCategory2Header = this.mHeader;
        if (storiesCategory2Header != null) {
            makeCandidatesFromCat(storiesCategory2Header.getList(), list);
        }
        if (recyclerView != null) {
            int childCount = recyclerView.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                RecyclerView.ViewHolder findContainingViewHolder = recyclerView.findContainingViewHolder(recyclerView.getChildAt(i2));
                if (findContainingViewHolder instanceof PreviewViewHolder) {
                    PreviewViewHolder previewViewHolder = (PreviewViewHolder) findContainingViewHolder;
                    if (ViewHolderValue.isData(findContainingViewHolder.getItemViewType()) && StoryHelper.isVideoItem(previewViewHolder.getMediaItem()) && ViewUtils.isInVisibleRange(previewViewHolder.itemView, recyclerView, 0.65f)) {
                        list.add(previewViewHolder);
                    }
                }
            }
        }
    }

    public void onStartInternal(PreviewViewHolder previewViewHolder) {
        if (!(previewViewHolder instanceof StoriesCatTransitoryItemViewHolder)) {
            Timer.startTimer(DURATION_TIMER_ID, (long) previewViewHolder.getPreviewDuration(), new d(0, this, previewViewHolder));
        }
    }

    public void setHeader(StoriesCategory2Header storiesCategory2Header) {
        this.mHeader = storiesCategory2Header;
    }
}
