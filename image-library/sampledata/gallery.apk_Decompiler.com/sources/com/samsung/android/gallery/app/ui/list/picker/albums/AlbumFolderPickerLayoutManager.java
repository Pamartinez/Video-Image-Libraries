package com.samsung.android.gallery.app.ui.list.picker.albums;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFolderPickerLayoutManager extends AlbumsBaseLayoutManager {
    public AlbumFolderPickerLayoutManager(Context context, int i2) {
        super(context, i2);
        setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                return AlbumFolderPickerLayoutManager.this.getHintStartSpan(i2, i7);
            }

            public int getSpanSize(int i2) {
                AlbumFolderPickerLayoutManager albumFolderPickerLayoutManager = AlbumFolderPickerLayoutManager.this;
                return albumFolderPickerLayoutManager.getHintColumnSpan(i2, albumFolderPickerLayoutManager.getSpanCount());
            }
        });
    }

    public int getHintColumnSpan(int i2, int i7) {
        return getAdapter().getHintSpanSize(i2, i7);
    }

    public int getHintStartSpan(int i2, int i7) {
        return getAdapter().getHintStartSpan(i2, i7);
    }

    public int getHintViewHeight(int i2, int i7) {
        return getAdapter().getHintItemViewHeight(i2, 0, i7);
    }
}
