package com.samsung.android.gallery.app.ui.list.stories.hiderule.v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideRuleDateItemAdapter extends HideRuleSceneItemAdapter {
    public HideRuleDateItemAdapter(Context context, MediaItem mediaItem) {
        super(context, mediaItem);
    }

    public int getItemCount() {
        return this.mHideRuleItem.getCount();
    }

    public int getItemLayoutId() {
        return R.layout.recycler_item_hide_rule_date_item_layout;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        MediaItem[] itemsInFolder = this.mHideRuleItem.getItemsInFolder();
        if (itemsInFolder.length <= i2 || i2 < 0) {
            Log.e("HideDateAdapter", "no item to bind");
        } else {
            listViewHolder.bind(itemsInFolder[i2]);
        }
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        HideRuleDateItemViewHolder hideRuleDateItemViewHolder = new HideRuleDateItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemLayoutId(), viewGroup, false), 0);
        hideRuleDateItemViewHolder.setOnItemClickListener(this.mOnItemClickListener);
        return hideRuleDateItemViewHolder;
    }
}
