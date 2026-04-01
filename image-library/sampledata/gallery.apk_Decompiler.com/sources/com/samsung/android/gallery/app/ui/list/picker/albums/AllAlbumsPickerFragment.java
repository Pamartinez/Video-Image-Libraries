package com.samsung.android.gallery.app.ui.list.picker.albums;

import W8.C0579a;
import android.view.View;
import android.view.WindowInsets;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.picker.albums.AlbumsPickerPresenter;
import com.samsung.android.gallery.app.ui.list.picker.albums.IAlbumsPickerView;
import com.samsung.android.gallery.module.utils.PickerUtil;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AllAlbumsPickerFragment<V extends IAlbumsPickerView, P extends AlbumsPickerPresenter> extends AlbumsPickerFragment<V, P> implements IAlbumsPickerView {
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        Optional.ofNullable(getListView()).ifPresent(new C0579a(6));
        return windowInsets;
    }

    public boolean supportTabLayout() {
        return false;
    }

    public AlbumsPickerPresenter createPresenter(IAlbumsPickerView iAlbumsPickerView) {
        return new AlbumsPickerPresenter(this.mBlackboard, iAlbumsPickerView) {
            public void updateToolbar(Toolbar toolbar) {
                setNavigationUpButton(toolbar);
                toolbar.setTitle((CharSequence) PickerUtil.getUsageTitle(getBlackboard()));
                toolbar.setSubtitle((CharSequence) null);
            }
        };
    }
}
