package com.samsung.android.gallery.app.ui.list.stories.pictures.header;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesHeaderView;
import com.samsung.android.gallery.database.dbtype.StoryCategoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHeaderSummary extends StoryHeaderBasic implements HeaderDataListener {
    private HeaderSimpleData mData;
    private TextView mDaysDataView;
    private TextView mPhotosDataView;
    ViewGroup mSummaryDaysContainer;
    ViewGroup mSummaryPhotosContainer;
    ViewGroup mSummaryVideosContainer;
    private TextView mVideosDataView;

    private void bindContentsData() {
        int[] mediaTypeCount = this.mData.getMediaTypeCount();
        setPhotos(Integer.toString(mediaTypeCount[0]));
        setVideos(Integer.toString(mediaTypeCount[1]));
    }

    private void bindDaysData() {
        long[] storyTimeDurationArray = MediaItemStory.getStoryTimeDurationArray(this.mMediaItem);
        ViewUtils.setText(this.mDaysDataView, TimeUtil.getDatePeriodDays(storyTimeDurationArray[1], storyTimeDurationArray[0]));
    }

    private ViewGroup getSummaryRootView(ViewGroup viewGroup) {
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.story_pictures_summary_feed);
        if (viewGroup2 != null) {
            return viewGroup2;
        }
        ViewGroup viewGroup3 = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false);
        viewGroup.addView(viewGroup3);
        return viewGroup3;
    }

    private void initSummaryView() {
        ViewGroup viewGroup = this.mSummaryPhotosContainer;
        if (viewGroup != null) {
            this.mPhotosDataView = (TextView) viewGroup.findViewById(R.id.story_pictures_summary_count);
            ((TextView) this.mSummaryPhotosContainer.findViewById(R.id.story_pictures_summary_category)).setText(R.string.pictures_summary);
        }
        ViewGroup viewGroup2 = this.mSummaryVideosContainer;
        if (viewGroup2 != null) {
            this.mVideosDataView = (TextView) viewGroup2.findViewById(R.id.story_pictures_summary_count);
            ((TextView) this.mSummaryVideosContainer.findViewById(R.id.story_pictures_summary_category)).setText(R.string.videos_summary);
        }
        int storyCategoryType = MediaItemStory.getStoryCategoryType(this.mMediaItem);
        if (this.mSummaryDaysContainer == null) {
            return;
        }
        if (supportSummaryDays(storyCategoryType)) {
            this.mSummaryDaysContainer.setVisibility(0);
            this.mDaysDataView = (TextView) this.mSummaryDaysContainer.findViewById(R.id.story_pictures_summary_count);
            ((TextView) this.mSummaryDaysContainer.findViewById(R.id.story_pictures_summary_category)).setText(R.string.days_summary);
            return;
        }
        this.mSummaryDaysContainer.setVisibility(8);
    }

    private void setPhotos(String str) {
        ViewUtils.setText(this.mPhotosDataView, str);
    }

    private void setVideos(String str) {
        ViewUtils.setText(this.mVideosDataView, str);
    }

    private boolean supportSummaryDays(int i2) {
        if (i2 == StoryCategoryType.TRIP.ordinal()) {
            return true;
        }
        return false;
    }

    public boolean accept(HeaderType headerType) {
        if (headerType == HeaderType.SUMMARY) {
            return true;
        }
        return false;
    }

    public void bindView(ViewGroup viewGroup) {
        this.mRootView = getSummaryRootView(viewGroup);
        this.mSummaryPhotosContainer = (ViewGroup) viewGroup.findViewById(R.id.summary_photos_layout);
        this.mSummaryVideosContainer = (ViewGroup) viewGroup.findViewById(R.id.summary_video_layout);
        this.mSummaryDaysContainer = (ViewGroup) viewGroup.findViewById(R.id.summary_days_layout);
        initSummaryView();
    }

    public void clear() {
        this.mData.unregister(this);
        this.mSummaryPhotosContainer = null;
        this.mSummaryVideosContainer = null;
        this.mSummaryDaysContainer = null;
    }

    public int getContainerResId() {
        return R.id.stories_pictures_summary_container;
    }

    public int getLayoutId() {
        return R.layout.story_pictures_header_summary_feed_layout;
    }

    public void init(IStoryPicturesHeaderView iStoryPicturesHeaderView, ViewGroup viewGroup, MediaItem mediaItem, HeaderSimpleData headerSimpleData) {
        super.init(iStoryPicturesHeaderView, viewGroup, mediaItem, headerSimpleData);
        this.mData = headerSimpleData;
        headerSimpleData.register(this);
    }

    public void loadData(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
        bindDaysData();
    }

    public void notifyDataChanged() {
        bindDaysData();
        bindContentsData();
    }
}
