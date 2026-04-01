package com.samsung.android.gallery.app.ui.viewer2.details.items;

import H7.A;
import android.view.View;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewHolderCreaturesRemove extends ListViewHolder {
    public ViewHolderCreaturesRemove(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onItemClickInternal(1001);
    }

    public void bindView(View view) {
        View findViewById = view.findViewById(R.id.moreinfo_remove_icon);
        findViewById.setOnClickListener(new A(10, this));
        ViewUtils.setContentDescription(findViewById, view.getContext().getString(R.string.edit_tags) + ArcCommonLog.TAG_COMMA + view.getContext().getString(R.string.speak_button));
    }
}
