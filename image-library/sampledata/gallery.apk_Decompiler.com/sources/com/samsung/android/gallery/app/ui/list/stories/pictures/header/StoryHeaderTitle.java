package com.samsung.android.gallery.app.ui.list.stories.pictures.header;

import A2.d;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHeaderTitle extends StoryHeaderBasic {
    TextView mSubTitleView;
    TextView mTitleView;

    private ViewGroup getTitleRootView(ViewGroup viewGroup) {
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.story_pictures_title);
        if (viewGroup2 != null) {
            return viewGroup2;
        }
        ViewGroup viewGroup3 = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false);
        viewGroup.addView(viewGroup3);
        return viewGroup3;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSubTitle$0(String str) {
        TextView textView = this.mSubTitleView;
        if (textView != null) {
            textView.setText(str);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSubTitle$1(MediaItem mediaItem) {
        long[] storyTimeDurationArray = MediaItemStory.getStoryTimeDurationArray(mediaItem);
        ThreadUtil.postOnUiThread(new d(14, this, TimeUtil.getEventDatePeriod(storyTimeDurationArray[0], storyTimeDurationArray[1], 50)));
        DbCompat.storyApi().updateStoryTime(mediaItem.getAlbumID());
    }

    private void loadTitleData(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
        updateTitle(mediaItem);
        updateSubTitle(this.mMediaItem);
    }

    private void updateSubTitle(MediaItem mediaItem) {
        if (mediaItem != null) {
            ThreadUtil.postOnBgThread(new d(13, this, mediaItem));
        } else {
            Log.e(this.TAG, "fail update duration");
        }
    }

    private void updateTitle(MediaItem mediaItem) {
        if (this.mTitleView != null) {
            String title = mediaItem.getTitle();
            if (!TextUtils.isEmpty(title)) {
                this.mTitleView.setText(title);
            }
        }
    }

    public void bindView(ViewGroup viewGroup) {
        this.mRootView = getTitleRootView(viewGroup);
        this.mTitleView = (TextView) viewGroup.findViewById(R.id.story_title);
        this.mSubTitleView = (TextView) viewGroup.findViewById(R.id.story_subtitle);
        updateTitle(this.mMediaItem);
    }

    public void clear() {
        this.mTitleView = null;
        this.mSubTitleView = null;
    }

    public int getContainerResId() {
        return R.id.stories_pictures_title_container;
    }

    public int getLayoutId() {
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return R.layout.story_highlight_list_header_title_layout;
        }
        return R.layout.story_pictures_header_title_layout;
    }

    public void loadData(MediaItem mediaItem) {
        loadTitleData(mediaItem);
    }
}
