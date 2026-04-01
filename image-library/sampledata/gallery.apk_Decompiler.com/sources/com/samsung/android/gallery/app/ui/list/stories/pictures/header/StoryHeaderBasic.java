package com.samsung.android.gallery.app.ui.list.stories.pictures.header;

import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesHeaderView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.chain.Chain;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoryHeaderBasic implements Chain<StoryHeaderBasic> {
    protected final String TAG = getClass().getSimpleName();
    protected Blackboard mBlackBoard;
    protected MediaItem mMediaItem;
    private StoryHeaderBasic mNext;
    protected ViewGroup mRootView;
    protected IStoryPicturesHeaderView mView;

    private void bindViewInternal(ViewGroup viewGroup) {
        ViewUtils.setVisibility(viewGroup, 0);
        bindView(viewGroup);
    }

    public abstract void bindView(ViewGroup viewGroup);

    public abstract void clear();

    public void collect(List<StoryHeaderBasic> list, MediaItem mediaItem) {
        if (supported(mediaItem)) {
            list.add(this);
        }
        StoryHeaderBasic storyHeaderBasic = this.mNext;
        if (storyHeaderBasic != null) {
            storyHeaderBasic.collect(list, mediaItem);
        }
    }

    public abstract int getContainerResId();

    public void init(IStoryPicturesHeaderView iStoryPicturesHeaderView, ViewGroup viewGroup, MediaItem mediaItem, HeaderSimpleData headerSimpleData) {
        this.mView = iStoryPicturesHeaderView;
        this.mMediaItem = mediaItem;
        this.mBlackBoard = Blackboard.getInstance(viewGroup.getContext().toString());
        bindViewInternal((ViewGroup) viewGroup.findViewById(getContainerResId()));
    }

    public abstract void loadData(MediaItem mediaItem);

    public boolean supported(MediaItem mediaItem) {
        return true;
    }

    public void setNext(StoryHeaderBasic storyHeaderBasic) {
        this.mNext = storyHeaderBasic;
    }

    public void layoutAnimationDone() {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void handleOrientationChange(int i2) {
    }

    public void setEnabled(boolean z) {
    }
}
