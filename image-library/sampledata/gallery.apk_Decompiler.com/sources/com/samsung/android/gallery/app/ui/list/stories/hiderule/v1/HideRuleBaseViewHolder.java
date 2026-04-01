package com.samsung.android.gallery.app.ui.list.stories.hiderule.v1;

import android.content.res.Resources;
import android.view.View;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class HideRuleBaseViewHolder extends ListViewHolder {
    public HideRuleBaseViewHolder(View view, int i2) {
        super(view, i2);
    }

    public final Resources getResources() {
        return this.itemView.getResources();
    }

    public abstract void onConfigurationChanged();

    public abstract void setDividerVisibility(boolean z);
}
