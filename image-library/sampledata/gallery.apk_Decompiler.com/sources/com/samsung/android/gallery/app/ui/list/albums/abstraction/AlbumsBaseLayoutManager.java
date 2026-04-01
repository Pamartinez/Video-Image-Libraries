package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsBaseLayoutManager extends PinchLayoutManager {
    protected int mDefaultGridItemPadding = 0;
    protected int[] mDefaultItemPadding;
    protected int mDividerHeight;
    protected int mFirstDividerHeight;
    protected IAlbumBaseViewAdapter mListAdapter;
    protected int mSpacingForGrid = 0;

    public AlbumsBaseLayoutManager(Context context, int i2) {
        super(context, i2);
        initDimens(context);
    }

    private void initDimens(Context context) {
        if (context != null && context.getResources() != null) {
            this.mDefaultGridItemPadding = context.getResources().getDimensionPixelOffset(R.dimen.album_grid_item_side_offset_horizontal);
            this.mDefaultItemPadding = getDefaultItemPadding(context);
            this.mDividerHeight = context.getResources().getDimensionPixelOffset(R.dimen.mx_albums_divider_height);
            this.mFirstDividerHeight = context.getResources().getDimensionPixelOffset(R.dimen.mx_albums_first_divider_height);
            this.mSpacingForGrid = context.getResources().getDimensionPixelOffset(R.dimen.album_grid_view_side_gap) - this.mDefaultGridItemPadding;
        }
    }

    public void addView(View view, int i2) {
        updateViewSize(view, getItemViewType(view), getSpanCount());
        super.addView(view, i2);
    }

    public void bindFakeHolder(ListViewHolder listViewHolder, int i2, int i7) {
        getAdapter().onBindViewHolder(listViewHolder, i2, i7);
    }

    public void bindHolder(ListViewHolder listViewHolder, int i2) {
        getAdapter().onBindViewHolder(listViewHolder, i2);
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        return getAdapter().createViewHolder(viewGroup, i2);
    }

    public IAlbumBaseViewAdapter getAdapter() {
        return this.mListAdapter;
    }

    public int[] getDefaultItemPadding(Context context) {
        int i2 = this.mDefaultGridItemPadding;
        return new int[]{i2, i2, i2, i2};
    }

    public int getHintItemViewType(int i2, int i7) {
        return getAdapter().getHintItemViewType(i2, getRealGridSize(i7));
    }

    public int getHintWidthSpace(int i2) {
        return (getWidth() - (getSpacing(i2) * 2)) - getExtraStartPadding(i2);
    }

    public int[] getItemPadding(int i2) {
        return this.mDefaultItemPadding;
    }

    public int getSpacing(int i2) {
        if (isListView(i2)) {
            return 0;
        }
        return this.mSpacingForGrid;
    }

    public void handleDensityChange(Context context) {
        initDimens(context);
    }

    public boolean hasHeader() {
        return getAdapter().supportHeader();
    }

    public boolean isAlbum() {
        return true;
    }

    public boolean isListView(int i2) {
        if (getRealGridSize(i2) == 1) {
            return true;
        }
        return false;
    }

    public boolean isSelectionMode() {
        return getAdapter().isSelectionMode();
    }

    public boolean isSingleSelectionMode() {
        return getAdapter().isSingleSelectionMode();
    }

    public void onAdapterChangedInternal(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        this.mListAdapter = (IAlbumBaseViewAdapter) adapter2;
    }

    public void updateAlbumBlurInfo(ListViewHolder listViewHolder, int i2, int i7) {
        getAdapter().updateAlbumBlurInfo(listViewHolder, i2, i7);
    }

    public void updateAlbumSyncMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        getAdapter().updateAlbumSyncMargin(listViewHolder, i2, z);
    }

    public void updateAlbumTypeMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        getAdapter().updateAlbumTypeMargin(listViewHolder, i2, z);
    }

    public void updateEmptyAlbumViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
        getAdapter().updateEmptyAlbumViewSize(listViewHolder, i2, z);
    }

    public void updateFolderChildViewSize(ListViewHolder[] listViewHolderArr, int i2, int i7) {
        getAdapter().updateFolderChildViewSize(listViewHolderArr, i2, i7);
    }

    public void updateFolderViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
        getAdapter().updateFolderViewSize(listViewHolder, i2, z);
    }

    public void updateTitleContainerMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        getAdapter().updateTitleContainerMargin(listViewHolder, i2, z);
    }

    public void updateViewBorders(ListViewHolder listViewHolder, int i2) {
        getAdapter().updateBorders(listViewHolder, i2);
    }

    public void updateViewSize(View view, int i2, int i7) {
        if (i2 == 2) {
            int[] itemPadding = getItemPadding(i7);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = getHintWidthSpace(i7) / getRealGridSize(i7);
            layoutParams.height = -2;
            view.setLayoutParams(layoutParams);
            view.setPadding(itemPadding[0], itemPadding[1], itemPadding[2], itemPadding[3]);
        }
    }

    public void updateVirtualAlbumViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
        getAdapter().updateVirtualAlbumViewSize(listViewHolder, i2, z);
    }
}
