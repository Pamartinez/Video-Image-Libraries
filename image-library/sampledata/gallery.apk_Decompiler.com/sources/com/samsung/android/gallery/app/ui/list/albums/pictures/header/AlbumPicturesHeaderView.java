package com.samsung.android.gallery.app.ui.list.albums.pictures.header;

import A.a;
import Fa.C0571z;
import M4.j;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumPicturesHeaderView extends FrameLayout {
    private LinearLayoutManager mLinearLayoutManager;
    private AlbumPicturesHeaderListAdapter mListAdapter;
    RecyclerView mRecyclerView;
    private View mScreenShotFilterView;
    private View mTipCardView;

    public AlbumPicturesHeaderView(EventContext eventContext) {
        super(eventContext.getContext());
        inflateLayout();
        initListView();
        initListAdapter(eventContext);
    }

    private MediaItem getCurrentItem(EventContext eventContext) {
        if (eventContext.getCurrentItem() != null) {
            return eventContext.getCurrentItem();
        }
        return new MediaItem();
    }

    private void inflateLayout() {
        View.inflate(getContext(), R.layout.tip_card_album_pictures, this);
        this.mTipCardView = findViewById(R.id.tip_card_layout);
        this.mScreenShotFilterView = findViewById(R.id.screen_shot_filter_container);
    }

    private void initListAdapter(EventContext eventContext) {
        if (this.mListAdapter == null) {
            AlbumPicturesHeaderListAdapter albumPicturesHeaderListAdapter = new AlbumPicturesHeaderListAdapter(getCurrentItem(eventContext));
            this.mListAdapter = albumPicturesHeaderListAdapter;
            this.mRecyclerView.setAdapter(albumPicturesHeaderListAdapter);
            this.mLinearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
        }
        this.mRecyclerView.setLayoutManager(this.mLinearLayoutManager);
        this.mRecyclerView.setNestedScrollingEnabled(false);
    }

    private void initListView() {
        if (this.mRecyclerView == null) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            this.mRecyclerView = recyclerView;
            recyclerView.setNestedScrollingEnabled(false);
            this.mRecyclerView.setHorizontalFadingEdgeEnabled(true);
        }
    }

    private boolean needScreenShotFilterView(MediaItem mediaItem) {
        if (mediaItem.getAlbumType() == AlbumType.Merged) {
            boolean anyMatch = Arrays.stream(mediaItem.getAlbumsInFolder()).filter(new C0571z(4)).anyMatch(new j(17));
            a.v("needScreenShotFilter {isMergedScreenShotAlbum=", "}", "AlbumPicturesHeaderView", anyMatch);
            return anyMatch;
        }
        boolean isScreenshot = BucketUtils.isScreenshot(mediaItem.getAlbumID());
        a.v("needScreenShotFilter {isScreenShotAlbum=", "}", "AlbumPicturesHeaderView", isScreenshot);
        return isScreenshot;
    }

    private void updateHeaderViewForMerged(EventContext eventContext) {
        if (eventContext.getCurrentItem() != null) {
            updateMergedCount(eventContext.getCurrentItem().getItemCount());
            if (this.mListAdapter == null) {
                initListAdapter(eventContext);
            }
            this.mListAdapter.setMediaItem(eventContext.getCurrentItem());
            this.mListAdapter.notifyDataSetChanged();
        }
    }

    private void updateMergedCount(int i2) {
        ViewUtils.setText((TextView) findViewById(R.id.headerContent), getResources().getQuantityString(R.plurals.n_albums_have_been_merged_here, i2, new Object[]{Integer.valueOf(i2)}));
    }

    public void destroy() {
        this.mRecyclerView = null;
    }

    public void handleOrientationChange(EventContext eventContext) {
        initListAdapter(eventContext);
        this.mListAdapter.notifyDataSetChanged();
    }

    public boolean isListViewScrolling() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.getScrollState() == 0) {
            return false;
        }
        return true;
    }

    public void updateHeaderView(EventContext eventContext, MediaItem mediaItem) {
        if (mediaItem.getAlbumType() != AlbumType.Merged || !mediaItem.isAlbumShowInfo()) {
            ViewUtils.setVisibility(this.mTipCardView, 8);
        } else {
            ViewUtils.setVisibility(this.mTipCardView, 0);
            updateHeaderViewForMerged(eventContext);
        }
        if (PreferenceFeatures.OneUi6x.SUPPORT_SCREEN_SHOT_FILTER) {
            ViewUtils.setVisibleOrGone(this.mScreenShotFilterView, needScreenShotFilterView(mediaItem));
        }
    }
}
