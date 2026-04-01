package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import k6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestedCreatureHeaderView implements ISuggestedCreatureHeaderView {
    private CreatureSelectItemAdapter mAdapter;
    private View mDivider;
    private View mHeaderView;
    private GalleryListView mListView;
    private TextView mNormalFacesTitle;
    private CategoryPropertyHelper mPropertyHelper;
    private TextView mSuggestedFacesTitle;
    private final ISuggestedCreatureSelectView mView;

    public SuggestedCreatureHeaderView(ISuggestedCreatureSelectView iSuggestedCreatureSelectView) {
        this.mView = iSuggestedCreatureSelectView;
    }

    private void bindSuggestedCreatureView() {
        this.mListView = (GalleryListView) this.mHeaderView.findViewById(R.id.suggested_creature_recycler_view);
        initListView();
        GalleryListView galleryListView = this.mListView;
        if (galleryListView != null) {
            galleryListView.setClipToPadding(true);
            this.mListView.disableSelectMode(true);
            this.mListView.setNestedScrollingEnabled(false);
        }
        this.mSuggestedFacesTitle = (TextView) this.mHeaderView.findViewById(R.id.suggested_faces_title);
        this.mDivider = this.mHeaderView.findViewById(R.id.divider);
        initNormalFacesTitleView();
    }

    private void createCategoryPropertyHelper() {
        if (this.mPropertyHelper == null) {
            this.mPropertyHelper = CategoryPropertyHelper.getInstance("location://search/fileList/SuggestedCreature", true);
        }
    }

    private int getLayoutId() {
        return R.layout.suggested_creature_select_header_layout;
    }

    private void initListView() {
        SuggestedCreatureHeaderView suggestedCreatureHeaderView;
        if (this.mListView != null) {
            if (this.mAdapter == null) {
                suggestedCreatureHeaderView = this;
                AnonymousClass1 r1 = new CreatureSelectItemAdapter(this.mView, "location://search/fileList/SuggestedCreature", this.mListView, this.mPropertyHelper, true) {
                    public int getViewPosition(int i2) {
                        return i2;
                    }
                };
                suggestedCreatureHeaderView.mAdapter = r1;
                suggestedCreatureHeaderView.mListView.setAdapter(r1);
                suggestedCreatureHeaderView.mListView.setLayoutManager(new GridLayoutManager(suggestedCreatureHeaderView.mView.getActivity(), suggestedCreatureHeaderView.mView.getHeaderListColumnSize()));
            } else {
                suggestedCreatureHeaderView = this;
            }
            ThreadUtil.postOnUiThread(new b(17, suggestedCreatureHeaderView));
        }
    }

    private void initNormalFacesTitleView() {
        this.mNormalFacesTitle = (TextView) this.mHeaderView.findViewById(R.id.normal_faces_title);
    }

    private boolean isDataAvailable() {
        CreatureSelectItemAdapter creatureSelectItemAdapter = this.mAdapter;
        if (creatureSelectItemAdapter == null || creatureSelectItemAdapter.getItemCount() <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void updateViewVisibility() {
        ViewUtils.setVisibleOrGone(this.mSuggestedFacesTitle, isDataAvailable());
        ViewUtils.setVisibleOrGone(this.mListView, isDataAvailable());
        ViewUtils.setVisibleOrGone(this.mDivider, isDataAvailable());
        ViewUtils.setVisibility(this.mNormalFacesTitle, 0);
    }

    public View get() {
        return this.mHeaderView;
    }

    public MediaItem[] getAllItems() {
        if (this.mAdapter == null) {
            return new MediaItem[0];
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mAdapter.getItemCount(); i2++) {
            arrayList.add(this.mAdapter.getMediaItemFromCache(i2));
        }
        return (MediaItem[]) arrayList.toArray(new MediaItem[0]);
    }

    public void initView() {
        this.mHeaderView = LayoutInflater.from(this.mView.getActivity()).inflate(getLayoutId(), (ViewGroup) null);
        createCategoryPropertyHelper();
        bindSuggestedCreatureView();
    }

    public void onDataChangedOnUI() {
        CreatureSelectItemAdapter creatureSelectItemAdapter = this.mAdapter;
        if (creatureSelectItemAdapter != null) {
            creatureSelectItemAdapter.notifyDataSetChanged();
            updateViewVisibility();
        }
    }

    public void onListItemClick(int i2) {
        CreatureSelectItemAdapter creatureSelectItemAdapter = this.mAdapter;
        if (creatureSelectItemAdapter != null) {
            creatureSelectItemAdapter.onUpdateViewHolder(i2);
        }
    }

    public void updateListColumn() {
        int[] columnCount = this.mPropertyHelper.getColumnCount(this.mListView.getContext());
        this.mListView.setColumnCount(columnCount);
        RecyclerView.LayoutManager layoutManager = this.mListView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int depth = this.mListView.getDepth();
            if (depth >= columnCount.length) {
                depth = columnCount.length - 1;
            }
            this.mListView.changeDepth(gridLayoutManager, depth);
        }
    }
}
