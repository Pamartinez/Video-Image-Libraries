package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.people.HiddenCreaturePresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HiddenCreatureFragment<V extends ICreatureSelectView, P extends HiddenCreaturePresenter<V>> extends CreatureSelectFragment<V, P> {
    private void bindDescription(View view) {
        int i2;
        TextView textView = (TextView) view.findViewById(R.id.description);
        if (IdentityCreatureUtil.isPetRecognized()) {
            i2 = R.string.add_people_and_pets_header_description;
        } else {
            i2 = R.string.add_people_header_description;
        }
        ViewUtils.setText(textView, i2);
    }

    private View createHeaderView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.hidden_creature_description_header, (ViewGroup) null);
        bindDescription(inflate);
        return inflate;
    }

    private GridLayoutManager.SpanSizeLookup createSpanSizeLookUp(GridLayoutManager gridLayoutManager) {
        return new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                HiddenCreatureAdapter adapter = HiddenCreatureFragment.this.getAdapter();
                if (adapter != null) {
                    return adapter.getStartSpan(i2, i7);
                }
                return 0;
            }

            public int getSpanSize(int i2) {
                HiddenCreatureAdapter adapter = HiddenCreatureFragment.this.getAdapter();
                if (adapter != null) {
                    return adapter.getSpanSize(i2);
                }
                return 1;
            }
        };
    }

    public RecyclerView.LayoutManager createLayoutManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), getMaxColumnSize());
        gridLayoutManager.setSpanSizeLookup(createSpanSizeLookUp(gridLayoutManager));
        return gridLayoutManager;
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new HiddenCreatureAdapter(this, getLocationKey(), createHeaderView(), galleryListView, this.mPropertyHelper, true);
    }

    public int getLayoutId() {
        return R.layout.fragment_hidden_creature_layout;
    }

    public HiddenCreatureAdapter getAdapter() {
        return (HiddenCreatureAdapter) super.getAdapter();
    }

    public HiddenCreaturePresenter createPresenter(ICreatureSelectView iCreatureSelectView) {
        return new HiddenCreaturePresenter(this.mBlackboard, iCreatureSelectView);
    }
}
