package com.samsung.android.gallery.app.ui.list.search.keyword.stories;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchResultStoriesContainer implements ISearchResultStoriesContainer {
    private final View mMainLayout;
    private SearchResultStoriesPresenter mPresenter;
    private GalleryListView mRecyclerView;

    public SearchResultStoriesContainer(View view, EventContext eventContext) {
        this.mMainLayout = view;
        this.mPresenter = new SearchResultStoriesPresenter(eventContext, this);
    }

    private void initListView() {
        GalleryListView galleryListView = (GalleryListView) this.mMainLayout.findViewById(R.id.story_recycler_view);
        this.mRecyclerView = galleryListView;
        galleryListView.setLayoutManager(new LinearLayoutManager(galleryListView.getContext(), 1, false));
        this.mRecyclerView.setAdapter(new SearchResultStoriesViewAdapter(this, this.mPresenter.getBlackboard()));
    }

    private void setVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mMainLayout, z);
    }

    private void updateTitleInfo(int i2) {
        ViewUtils.setText((TextView) this.mMainLayout.findViewById(R.id.search_keyword_divider_title), (int) R.string.stories);
        ViewUtils.setVisibleOrGone(this.mMainLayout.findViewById(R.id.search_keyword_divider_arrow), false);
        ViewUtils.setText((TextView) this.mMainLayout.findViewById(R.id.search_keyword_divider_count), String.valueOf(i2));
    }

    public void destroy() {
        GalleryListView galleryListView = this.mRecyclerView;
        if (galleryListView != null) {
            galleryListView.destroy();
            this.mRecyclerView = null;
        }
        SearchResultStoriesPresenter searchResultStoriesPresenter = this.mPresenter;
        if (searchResultStoriesPresenter != null) {
            searchResultStoriesPresenter.destroy();
            this.mPresenter = null;
        }
    }

    public MediaData getMediaData() {
        return this.mPresenter.getMediaData();
    }

    public void onDataChangedOnUi(MediaData mediaData) {
        if (this.mPresenter != null) {
            initListView();
            updateTitleInfo(mediaData.getCount());
            setVisibility(true);
        }
    }

    public void onStoriesClicked(ListViewHolder listViewHolder, MediaItem mediaItem) {
        this.mPresenter.onStoriesClicked(listViewHolder, mediaItem);
    }

    public void update(String str) {
        SearchResultStoriesPresenter searchResultStoriesPresenter = this.mPresenter;
        if (searchResultStoriesPresenter != null) {
            searchResultStoriesPresenter.loadStories(str);
        }
    }
}
