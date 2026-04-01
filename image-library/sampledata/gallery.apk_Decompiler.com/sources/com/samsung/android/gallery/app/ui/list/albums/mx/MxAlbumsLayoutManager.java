package com.samsung.android.gallery.app.ui.list.albums.mx;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsLayoutManager extends AlbumsBaseLayoutManager {
    public MxAlbumsLayoutManager(Context context, int i2) {
        super(context, i2);
        setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                return MxAlbumsLayoutManager.this.getHintStartSpan(i2, i7);
            }

            public int getSpanSize(int i2) {
                MxAlbumsLayoutManager mxAlbumsLayoutManager = MxAlbumsLayoutManager.this;
                return mxAlbumsLayoutManager.getHintColumnSpan(i2, mxAlbumsLayoutManager.getSpanCount());
            }
        });
    }

    private void updateChildWidth(View view, ViewGroup.LayoutParams layoutParams, int i2) {
        if (i2 > 0 && i2 != layoutParams.width) {
            layoutParams.width = i2;
            view.setLayoutParams(layoutParams);
        }
    }

    private void updateChildWidthAndHeight(View view, ViewGroup.LayoutParams layoutParams, int i2, int i7) {
        boolean z;
        boolean z3 = true;
        if (i2 <= 0 || i2 == layoutParams.width) {
            z = false;
        } else {
            layoutParams.width = i2;
            z = true;
        }
        if (i7 <= 0 || i7 == layoutParams.height) {
            z3 = z;
        } else {
            layoutParams.height = i7;
        }
        if (z3) {
            view.setLayoutParams(layoutParams);
        }
    }

    public TextView getHeaderDescriptionView() {
        return getAdapter().getHeaderDescriptionView();
    }

    public int getHeaderDescriptionWidthOffset() {
        return getAdapter().getHeaderDescriptionWidthOffset();
    }

    public View getHeaderView() {
        return getAdapter().getHeaderView();
    }

    public int getHintColumnSpan(int i2, int i7) {
        return getAdapter().getHintSpanSize(i2, i7);
    }

    public int getHintStartSpan(int i2, int i7) {
        return getAdapter().getHintStartSpan(i2, i7);
    }

    public int getHintViewHeight(int i2, int i7) {
        return getAdapter().getHintItemViewHeight(i2, getHintWidthSpace(i7), getRealGridSize(i7));
    }

    public void updateViewSize(View view, int i2, int i7) {
        int i8;
        if (ViewHolderValue.isHeader(i2)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            updateChildWidth(view, marginLayoutParams, getWidth() - getExtraStartPadding(i7));
            marginLayoutParams.setMarginStart(-getSpacing(i7));
        } else if (ViewHolderValue.isDivider(i2)) {
            if (i2 == -1) {
                i8 = this.mFirstDividerHeight;
            } else {
                i8 = this.mDividerHeight;
            }
            updateChildWidthAndHeight(view, view.getLayoutParams(), getHintWidthSpace(getCurrentSpanCount()), i8);
        } else if (i2 == -5) {
            updateChildWidth(view, view.getLayoutParams(), getHintWidthSpace(getCurrentSpanCount()));
        } else {
            super.updateViewSize(view, i2, i7);
        }
    }
}
