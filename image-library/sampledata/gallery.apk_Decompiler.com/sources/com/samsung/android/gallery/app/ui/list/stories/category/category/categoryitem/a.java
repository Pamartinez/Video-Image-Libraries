package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.OnDemandSuggestionItemAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListViewHolder e;

    public /* synthetic */ a(ListViewHolder listViewHolder, int i2) {
        this.d = i2;
        this.e = listViewHolder;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        ListViewHolder listViewHolder = this.e;
        switch (i2) {
            case 0:
                ((OnDemandSuggestionItemAdapter.OnDemandItemViewHolder) listViewHolder).lambda$new$0(view);
                return;
            default:
                ((StoriesCatItemBaseViewHolder) listViewHolder).lambda$bindView$0(view);
                return;
        }
    }
}
