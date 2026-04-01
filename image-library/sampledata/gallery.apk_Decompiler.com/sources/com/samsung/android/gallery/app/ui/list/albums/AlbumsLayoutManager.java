package com.samsung.android.gallery.app.ui.list.albums;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AlbumsLayoutManager extends AlbumsBaseLayoutManager {
    public AlbumsLayoutManager(Context context, int i2) {
        super(context, i2);
        setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                return AlbumsLayoutManager.this.getHintStartSpan(i2, i7);
            }

            public int getSpanSize(int i2) {
                AlbumsLayoutManager albumsLayoutManager = AlbumsLayoutManager.this;
                return albumsLayoutManager.getHintColumnSpan(i2, albumsLayoutManager.getSpanCount());
            }
        });
    }

    private boolean isFooterPosition(int i2) {
        if (!hasFooter() || i2 != getItemCount() - 1) {
            return false;
        }
        return true;
    }

    public int getHintColumnSpan(int i2, int i7) {
        if (!hasFooter() || !isFooterPosition(i2)) {
            return super.getHintColumnSpan(i2, i7);
        }
        return i7;
    }

    public int getHintStartSpan(int i2, int i7) {
        if (!hasFooter() || !isFooterPosition(i2)) {
            return super.getHintStartSpan(i2, i7);
        }
        return 0;
    }

    public int getHintViewHeight(int i2, int i7) {
        if (!hasFooter() || !isFooterPosition(i2)) {
            return super.getHintViewHeight(i2, i7);
        }
        return getAdapter().getFooterViewHeight();
    }

    public boolean hasFooter() {
        return false;
    }

    public void updateViewSize(View view, int i2, int i7) {
        if (!hasFooter() || !ViewHolderValue.isFooter(i2)) {
            super.updateViewSize(view, i2, i7);
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int hintWidthSpace = getHintWidthSpace(getCurrentSpanCount());
        if (hintWidthSpace > 0 && hintWidthSpace != marginLayoutParams.width) {
            marginLayoutParams.width = getWidth() - getExtraStartPadding(i7);
            view.setLayoutParams(marginLayoutParams);
        }
        marginLayoutParams.setMarginStart(-getSpacing(i7));
    }
}
