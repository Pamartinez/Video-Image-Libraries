package com.samsung.android.gallery.app.ui.list.stories.abstraction;

import Qb.e;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesBaseView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBasePresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoriesBaseFragment<V extends IStoriesBaseView, P extends StoriesBasePresenter> extends BaseListFragment<V, P> implements IStoriesBaseView {
    private ArrayList<ListViewHolder> mBlockedList = new ArrayList<>();

    public StoriesBaseFragment() {
        setLocationKey("location://story/albums");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEnabledItemView$0() {
        setEnabledItemView(true);
    }

    public String getScreenId() {
        if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_EVENT_VIEW_SELECTION.toString();
        }
        return AnalyticsScreenId.SCREEN_EVENT_VIEW_NORMAL.toString();
    }

    public int getSelectionToolbarTitle() {
        return R.string.select_events;
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public boolean isAllowAdvancedMouseEvent() {
        return true;
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
    }

    public void onHighlightVideoButtonClick(MediaItem mediaItem) {
        ((StoriesBasePresenter) this.mPresenter).onHighlightVideoButtonClick(mediaItem);
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        super.onListItemClick(listViewHolder, i2, mediaItem, i7);
        if (StoryHelper.isNewStory(MediaItemStory.getStoryNotifyStatus(mediaItem))) {
            this.mBlackboard.postBroadcastEvent(EventMessage.obtain(1077, Integer.valueOf(mediaItem.getAlbumID())));
        }
    }

    public boolean onPostViewCreated() {
        try {
            LayoutInflater layoutInflater = getLayoutInflater();
            long currentTimeMillis = System.currentTimeMillis();
            int[] iArr = {R.layout.recycler_item_image_title_duration_layout_for_cover, R.layout.recycler_item_story_pictures_header_layout_legacy};
            LayoutCache instance = LayoutCache.getInstance();
            for (int i2 = 0; i2 < 2; i2++) {
                int i7 = iArr[i2];
                if (!instance.hasView(i7)) {
                    instance.putView(i7, layoutInflater.inflate(i7, (ViewGroup) null, false));
                }
            }
            Log.d(this.TAG, "onPostViewCreated +" + (System.currentTimeMillis() - currentTimeMillis));
            return true;
        } catch (IllegalStateException e) {
            Log.w((CharSequence) this.TAG, "onPostViewCreated is not attached", (Throwable) e);
            return true;
        }
    }

    public void setEnabledItemView(boolean z) {
        if (z) {
            for (int i2 = 0; i2 < this.mBlockedList.size(); i2++) {
                this.mBlockedList.get(i2).getRootView().setEnabled(true);
            }
            this.mBlockedList.clear();
            return;
        }
        int childCount = this.mLayoutManager.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            ListViewHolder listViewHolder = (ListViewHolder) this.mRecyclerView.getChildViewHolder(this.mLayoutManager.getChildAt(i7));
            if (listViewHolder != null) {
                listViewHolder.getRootView().setEnabled(false);
                if (!this.mBlockedList.contains(listViewHolder)) {
                    this.mBlockedList.add(listViewHolder);
                }
            }
        }
        ThreadUtil.postOnUiThreadDelayed(new e(17, this), 1000);
    }

    public void startPostponedEnterTransition() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            parentFragment.startPostponedEnterTransition();
        } else {
            super.startPostponedEnterTransition();
        }
    }

    public void startPostponedEnterTransitionV2() {
        MvpBaseFragment mvpBaseFragment = (MvpBaseFragment) getParentFragment();
        if (mvpBaseFragment != null) {
            mvpBaseFragment.startPostponedEnterTransitionV2();
        } else {
            super.startPostponedEnterTransitionV2();
        }
    }

    public void startShrinkAnimation() {
        Log.st(this.TAG, "start ShrinkAnimation");
        this.mBlackboard.post("command://UiEventStartShrinkAnimation", (Object) null);
    }

    public boolean supportFastScroll() {
        return true;
    }

    public boolean supportLayoutCache() {
        return true;
    }

    public boolean supportPostViewCreated() {
        return true;
    }

    public boolean supportTabLayout() {
        return true;
    }

    public void setScreenMode() {
    }
}
