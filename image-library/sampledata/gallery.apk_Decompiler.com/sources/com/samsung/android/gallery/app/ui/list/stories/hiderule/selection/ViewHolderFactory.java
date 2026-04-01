package com.samsung.android.gallery.app.ui.list.stories.hiderule.selection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewHolderFactory extends PicturesViewHolderFactory {
    public ViewHolderFactory(Context context) {
        super(context);
    }

    public int getDataLayoutId() {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_NEW_HIDE_SCENE_SELECTION) {
            return R.layout.recycler_item_scene_selection_image_layout;
        }
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_85) {
            return R.layout.recycler_item_hide_rule_creature_selection_image_layout;
        }
        return R.layout.recycler_item_scene_selection_image_layout_v2;
    }

    public ListViewHolder getDataViewHolder(View view, int i2) {
        if (PreferenceFeatures.OneUi6x.SUPPORT_NEW_HIDE_SCENE_SELECTION) {
            return new HideSceneSelectionViewHolder(view, i2);
        }
        return super.getDataViewHolder(view, i2);
    }

    public ViewHolderFactory(LayoutInflater layoutInflater) {
        super(layoutInflater);
    }
}
