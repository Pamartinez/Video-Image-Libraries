package com.samsung.android.gallery.app.ui.list.stories.hiderule.v1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.abstraction.IHideRuleView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideRuleAdapter<V extends IHideRuleView> extends BaseListViewAdapter<V> {
    public HideRuleAdapter(V v, String str) {
        super(v, str);
    }

    private boolean isLastItem(int i2) {
        if (i2 == getItemCount() - 1) {
            return true;
        }
        return false;
    }

    public void bindViewHolderInternal(ListViewHolder listViewHolder, int i2) {
        HideRuleBaseViewHolder hideRuleBaseViewHolder = (HideRuleBaseViewHolder) listViewHolder;
        MediaItem item = getItem(i2);
        if (item != null) {
            hideRuleBaseViewHolder.bind(item);
            hideRuleBaseViewHolder.setDividerVisibility(!isLastItem(i2));
            if (i2 == 0) {
                ViewMarginUtils.setTopMargin(listViewHolder.getRootView(), 0);
            }
        }
    }

    public ListViewHolder createViewHolder(View view, int i2) {
        return new HideRuleViewHolder(view, i2);
    }

    public final MediaItem getItem(int i2) {
        ArrayList<MediaItem> allData = this.mMediaData.getAllData();
        if (i2 < 0 || i2 >= allData.size()) {
            return null;
        }
        return allData.get(i2);
    }

    public int getItemLayoutId(int i2) {
        return R.layout.recycler_card_hide_rule_layout;
    }

    public int getItemViewType(int i2) {
        return 0;
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.XLARGE_NC_KIND;
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        switch (i7) {
            case 1001:
                ((IHideRuleView) this.mView).launchDatePicker();
                return;
            case 1002:
                ((IHideRuleView) this.mView).removeDate(i2, mediaItem);
                return;
            case 1003:
                ((IHideRuleView) this.mView).launchHideSceneSelection(i2, mediaItem);
                return;
            default:
                super.onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
                return;
        }
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return createViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemLayoutId(i2), viewGroup, false), i2);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        bindViewHolderInternal(listViewHolder, i2);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("onConfigurationChanged")) {
            ((HideRuleBaseViewHolder) listViewHolder).onConfigurationChanged();
        }
        super.onBindViewHolder(listViewHolder, i2, list);
    }
}
