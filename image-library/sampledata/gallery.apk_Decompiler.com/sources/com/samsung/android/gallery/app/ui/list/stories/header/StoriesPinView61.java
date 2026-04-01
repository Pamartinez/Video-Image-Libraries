package com.samsung.android.gallery.app.ui.list.stories.header;

import A4.C0371f;
import W8.C0579a;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinView61<V extends IStoriesView> extends BasePinView<IStoriesView> {
    private Consumer<Integer> mEventListener;
    private boolean mIsSelectionMode;
    private final SelectionHelper<Integer> mSelectionHelper;

    public StoriesPinView61(V v) {
        super(v);
        SelectionHelper<Integer> selectionHelper = new SelectionHelper<>();
        this.mSelectionHelper = selectionHelper;
        getAdapter().setSelectionHelper(selectionHelper);
    }

    private void enterSelect() {
        this.mIsSelectionMode = true;
        setSelectMode(true);
    }

    private void exitSelect() {
        this.mIsSelectionMode = false;
        this.mSelectionHelper.clear();
        setSelectMode(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onItemLongClicked$2(ListViewHolder listViewHolder, MediaItem mediaItem, Consumer consumer) {
        consumer.accept(0);
        onSelect(listViewHolder, mediaItem);
    }

    private void notifySelectionChanged() {
        StoriesPinAdapter adapter = getAdapter();
        adapter.notifyItemRangeChanged(0, adapter.getItemCount(), "select_mode");
    }

    private void onSelect(ListViewHolder listViewHolder, MediaItem mediaItem) {
        this.mSelectionHelper.toggle(Integer.valueOf(mediaItem.getAlbumID()));
        listViewHolder.setChecked(this.mSelectionHelper.isSelected(Integer.valueOf(mediaItem.getAlbumID())));
        Optional.ofNullable(this.mEventListener).ifPresent(new C0579a(14));
    }

    private void setSelectMode(boolean z) {
        getAdapter().setSelectMode(z);
        notifySelectionChanged();
    }

    public void destroy() {
        super.destroy();
        this.mEventListener = null;
    }

    public int getSelectCount() {
        return this.mSelectionHelper.getSize();
    }

    public ArrayList<MediaItem> getSelectedItems() {
        if (this.mSelectionHelper.getSize() <= 0) {
            return new ArrayList<>();
        }
        List<Integer> selected = this.mSelectionHelper.getSelected();
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        for (Integer intValue : selected) {
            MediaItem mediaItemByAlbumId = getAdapter().getMediaItemByAlbumId(intValue.intValue());
            if (mediaItemByAlbumId != null) {
                arrayList.add(mediaItemByAlbumId);
            }
        }
        return arrayList;
    }

    public void onDataChangedOnUi(boolean z) {
        super.onDataChangedOnUi(z);
        Optional.ofNullable(this.mEventListener).ifPresent(new C0579a(12));
        if (this.mIsSelectionMode && this.mSelectionHelper.invalidate(getAdapter().getAllAlbumIds())) {
            Optional.ofNullable(this.mEventListener).ifPresent(new C0579a(13));
        }
    }

    public void onItemClicked(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (this.mIsSelectionMode) {
            onSelect(listViewHolder, mediaItem);
        } else {
            super.onItemClicked(listViewHolder, i2, mediaItem, i7);
        }
    }

    public boolean onItemLongClicked(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (this.mIsSelectionMode) {
            onSelect(listViewHolder, mediaItem);
            return true;
        }
        Optional.ofNullable(this.mEventListener).ifPresent(new C0371f((Object) this, (Object) listViewHolder, mediaItem, 21));
        return true;
    }

    public void onSelectAll() {
        this.mSelectionHelper.add(getAdapter().getAllAlbumIds());
        notifySelectionChanged();
    }

    public void onSelectModeChanged(boolean z) {
        if (z) {
            enterSelect();
        } else {
            exitSelect();
        }
    }

    public void onUnselectAll() {
        this.mSelectionHelper.clear();
        notifySelectionChanged();
    }

    public void setEventListener(Consumer<Integer> consumer) {
        this.mEventListener = consumer;
    }

    public boolean supportSelection() {
        return true;
    }
}
