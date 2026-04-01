package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.View;
import android.widget.LinearLayout;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ViewHolderTagAdd extends ListViewHolder {
    public ViewHolderTagAdd(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onItemClickInternal(1002);
    }

    public void bindView(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.moreinfo_item_linearlayout);
        linearLayout.setPaddingRelative(linearLayout.getPaddingEnd(), 0, 0, 0);
        linearLayout.setVisibility(8);
        View findViewById = view.findViewById(R.id.moreinfo_empty_add_icon);
        findViewById.setContentDescription(findViewById.getResources().getString(R.string.add_tag) + ArcCommonLog.TAG_COMMA + findViewById.getResources().getString(R.string.speak_button));
        findViewById.setOnClickListener(new g(5, this));
        findViewById.setVisibility(0);
    }
}
