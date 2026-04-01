package com.samsung.android.gallery.app.ui.list.suggestions.remaster;

import O3.y;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.suggestions.remaster.IRemasterView;
import com.samsung.android.gallery.module.abstraction.RemasterSuggestGroup;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterPicturesViewAdapter<V extends IRemasterView> extends BaseListViewAdapter<IRemasterView> {
    public RemasterPicturesViewAdapter(V v, String str) {
        super(v, str);
    }

    private void bindInfo(ListViewHolder listViewHolder, int i2, MediaData mediaData) {
        ((RemasterPicturesViewHolder) listViewHolder).bindInfo(RemasterSuggestGroup.getTitle(i2), String.valueOf(mediaData.getCount()));
    }

    private void bindList(ListViewHolder listViewHolder, MediaData mediaData) {
        RemasterItemView remasterItemView = new RemasterItemView(getContext(), mediaData);
        remasterItemView.setOnItemClickListener(new y(5, this));
        ((RemasterPicturesViewHolder) listViewHolder).bindList(remasterItemView);
    }

    private MediaData getChildMediaData(int i2) {
        return ((IRemasterView) this.mView).getMediaData((String) null).getChildMediaData(i2);
    }

    public RecyclerView getSuggestionRemasterHorizontalListView(long j2) {
        if (((IRemasterView) this.mView).getMediaData(getLocationKey()) == null) {
            return null;
        }
        int findLastVisibleItemPositionCompat = this.mGalleryListView.findLastVisibleItemPositionCompat();
        for (int findFirstVisibleItemPositionCompat = this.mGalleryListView.findFirstVisibleItemPositionCompat(); findFirstVisibleItemPositionCompat <= findLastVisibleItemPositionCompat; findFirstVisibleItemPositionCompat++) {
            ListViewHolder listViewHolder = (ListViewHolder) this.mGalleryListView.findViewHolderForLayoutPosition(findFirstVisibleItemPositionCompat);
            if ((listViewHolder instanceof RemasterPicturesViewHolder) && ((long) ((IRemasterView) this.mView).getSuggestionRemasterGroupType(listViewHolder.getMediaItem())) == j2) {
                return ((RemasterPicturesViewHolder) listViewHolder).getListView();
            }
        }
        return null;
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    public void updateLayout() {
        notifyItemRangeChanged("payload_update");
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new RemasterPicturesViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_remaster_pictures_layout, viewGroup, false), i2);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        MediaData open;
        super.onBindViewHolder(listViewHolder, i2);
        int suggestionRemasterGroupType = ((IRemasterView) this.mView).getSuggestionRemasterGroupType(listViewHolder.getMediaItem());
        MediaData childMediaData = getChildMediaData(suggestionRemasterGroupType);
        if (childMediaData != null && (open = childMediaData.open(((IRemasterView) this.mView).getLocationKey())) != null) {
            bindInfo(listViewHolder, suggestionRemasterGroupType, open);
            bindList(listViewHolder, open);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001b, code lost:
        r5 = ((com.samsung.android.gallery.app.ui.list.suggestions.remaster.IRemasterView) r3.mView).getSuggestionRemasterGroupType(r4.getMediaItem());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(com.samsung.android.gallery.widget.listviewholder.ListViewHolder r4, int r5, java.util.List<java.lang.Object> r6) {
        /*
            r3 = this;
            java.lang.String r0 = "payload_item_info"
            boolean r1 = r6.contains(r0)
            java.lang.String r2 = "payload_update"
            if (r1 != 0) goto L_0x0015
            boolean r1 = r6.contains(r2)
            if (r1 == 0) goto L_0x0011
            goto L_0x0015
        L_0x0011:
            super.onBindViewHolder((com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r4, (int) r5, (java.util.List<java.lang.Object>) r6)
            return
        L_0x0015:
            boolean r5 = r6.contains(r0)
            if (r5 == 0) goto L_0x0030
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r5 = r3.mView
            com.samsung.android.gallery.app.ui.list.suggestions.remaster.IRemasterView r5 = (com.samsung.android.gallery.app.ui.list.suggestions.remaster.IRemasterView) r5
            com.samsung.android.gallery.module.data.MediaItem r0 = r4.getMediaItem()
            int r5 = r5.getSuggestionRemasterGroupType(r0)
            com.samsung.android.gallery.module.dataset.MediaData r0 = r3.getChildMediaData(r5)
            if (r0 == 0) goto L_0x0030
            r3.bindInfo(r4, r5, r0)
        L_0x0030:
            boolean r5 = r6.contains(r2)
            if (r5 == 0) goto L_0x0043
            com.samsung.android.gallery.app.ui.list.suggestions.remaster.RemasterPicturesViewHolder r4 = (com.samsung.android.gallery.app.ui.list.suggestions.remaster.RemasterPicturesViewHolder) r4
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r3 = r3.mView
            com.samsung.android.gallery.app.ui.list.suggestions.remaster.IRemasterView r3 = (com.samsung.android.gallery.app.ui.list.suggestions.remaster.IRemasterView) r3
            android.content.Context r3 = r3.getContext()
            r4.updateLayout(r3)
        L_0x0043:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.suggestions.remaster.RemasterPicturesViewAdapter.onBindViewHolder(com.samsung.android.gallery.widget.listviewholder.ListViewHolder, int, java.util.List):void");
    }
}
