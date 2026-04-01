package com.samsung.android.gallery.app.ui.list.sharings.family;

import android.content.Context;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.family.IFamilySuggestedPicturesView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FamilySuggestedPicturesViewAdapter<V extends IFamilySuggestedPicturesView> extends PicturesViewAdapter<V> {
    public FamilySuggestedPicturesViewAdapter(V v, String str, boolean z) {
        super(v, str, z);
        if (!((IFamilySuggestedPicturesView) this.mView).isNormalMode()) {
            this.mSelectableChecker = createSelectableChecker();
        }
    }

    private SelectableChecker<MediaItem> createSelectableChecker() {
        return new SelectableChecker<MediaItem>() {
            public int getMaxCount() {
                return ((IFamilySuggestedPicturesView) FamilySuggestedPicturesViewAdapter.this.mView).getMaxUploadedCount();
            }

            public boolean isSupported(MediaItem mediaItem) {
                return true;
            }

            public void showExceedMaxCountToast(Context context) {
                Utils.showToast(FamilySuggestedPicturesViewAdapter.this.getContext(), FamilySuggestedPicturesViewAdapter.this.getContext().getResources().getQuantityString(R.plurals.cant_add_more_than_n_items_at_once, getMaxCount(), new Object[]{Integer.valueOf(getMaxCount())}));
            }
        };
    }
}
