package com.samsung.android.gallery.app.ui.list.albums.hide;

import E9.a;
import android.content.Context;
import android.view.View;
import androidx.appcompat.widget.SwitchCompat;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.hide.IHideAlbumsView;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsHideViewAdapter<V extends IHideAlbumsView> extends AlbumsBaseViewAdapter<V> {
    public AlbumsHideViewAdapter(V v, String str) {
        super(v, str);
    }

    private String getTag() {
        return "default_value";
    }

    private void setHolderEnabled(ListViewHolder listViewHolder, boolean z) {
        float f;
        int i2;
        listViewHolder.getRootView().setEnabled(z);
        SwitchCompat switchCompat = (SwitchCompat) listViewHolder.getDecoView(25);
        if (switchCompat != null) {
            switchCompat.setEnabled(z);
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            switchCompat.setVisibility(i2);
        }
        if (z) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        setViewAlpha(listViewHolder.getImage(), f);
        setViewAlpha(listViewHolder.getTitleView(), f);
        setViewAlpha(listViewHolder.getCountView(), f);
        setViewAlpha(listViewHolder.getDecoView(21), f);
        setViewAlpha(listViewHolder.getDecoView(1), f);
        setViewAlpha(listViewHolder.getDecoView(22), f);
    }

    private void setViewAlpha(View view, float f) {
        if (view != null) {
            view.setAlpha(f);
        }
    }

    public AlbumsViewHolderFactory createViewHolderFactory(Context context) {
        return new AlbumsHideViewHolderFactory(context);
    }

    public int getHeaderCount() {
        return 1;
    }

    public int getItemCount() {
        return getHeaderCount() + super.getItemCount();
    }

    public int getItemViewType(int i2) {
        if (i2 == 0) {
            return -3;
        }
        return 1;
    }

    public MediaItem getMediaItemFromCache(int i2) {
        if (i2 <= 0) {
            return MediaItemBuilder.createEmpty();
        }
        return super.getMediaItemFromCache(i2);
    }

    public int getMediaItemPosition(int i2) {
        if (i2 == 0) {
            return 0;
        }
        return super.getMediaItemPosition(i2) - 1;
    }

    public void handleItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    public boolean isSupportListView() {
        return true;
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        boolean z;
        if (i7 == 4 || i7 == 0) {
            SwitchCompat switchCompat = (SwitchCompat) listViewHolder.getDecoView(25);
            if (switchCompat != null && ((IHideAlbumsView) this.mView).onSwitchClick(listViewHolder, i2, mediaItem, new a(22, switchCompat))) {
                boolean z3 = false;
                switchCompat.setEnabled(false);
                if (i7 == 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z != switchCompat.isChecked()) {
                    z3 = true;
                }
                switchCompat.setChecked(z3);
                return;
            }
            return;
        }
        super.onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        return false;
    }

    public boolean supportHover() {
        return false;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        MediaItem mediaItem;
        if (i2 == 0) {
            mediaItem = null;
        } else {
            super.onBindViewHolder(listViewHolder, i2);
            mediaItem = getMediaItemSync(i2);
        }
        if (mediaItem == null || AlbumHelper.getInstance().isSystemAlbum(mediaItem)) {
            setHolderEnabled(listViewHolder, false);
            return;
        }
        setHolderEnabled(listViewHolder, true);
        SwitchCompat switchCompat = (SwitchCompat) listViewHolder.getDecoView(25);
        if (switchCompat != null) {
            switchCompat.setTag(getTag());
            switchCompat.setChecked(mediaItem.isAlbumHide());
            switchCompat.setTag((Object) null);
        }
    }

    public void bindNewAlbumLabel(ListViewHolder listViewHolder) {
    }

    public void updateFolderViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
    }
}
