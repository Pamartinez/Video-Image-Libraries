package com.samsung.android.gallery.app.ui.list.sharings.pinch;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingPinchLayoutManager extends AlbumsBaseLayoutManager {
    public SharingPinchLayoutManager(Context context, int i2) {
        super(context, i2);
        setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                return SharingPinchLayoutManager.this.getHintStartSpan(i2, i7);
            }

            public int getSpanSize(int i2) {
                SharingPinchLayoutManager sharingPinchLayoutManager = SharingPinchLayoutManager.this;
                return sharingPinchLayoutManager.getHintColumnSpan(i2, sharingPinchLayoutManager.getSpanCount());
            }
        });
    }

    private boolean isData(int i2, int i7) {
        if (this.mListAdapter.getItemViewType(i2, i7) >= 0) {
            return true;
        }
        return false;
    }

    public int[] getDefaultItemPadding(Context context) {
        return new int[]{context.getResources().getDimensionPixelOffset(R.dimen.sharingv2_grid_horizontal_padding), context.getResources().getDimensionPixelOffset(R.dimen.sharingv2_grid_top_padding), context.getResources().getDimensionPixelOffset(R.dimen.sharingv2_grid_horizontal_padding), context.getResources().getDimensionPixelOffset(R.dimen.sharingv2_grid_bottom_padding)};
    }

    public int getHintColumnSpan(int i2, int i7) {
        if (isData(i2, i7)) {
            return 1;
        }
        return i7;
    }

    public int getHintStartSpan(int i2, int i7) {
        return this.mListAdapter.getHintColumnSpan(i2, getRealGridSize(i7));
    }
}
