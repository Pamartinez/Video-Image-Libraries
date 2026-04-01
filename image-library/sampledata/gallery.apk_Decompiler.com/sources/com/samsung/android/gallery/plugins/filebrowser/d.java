package com.samsung.android.gallery.plugins.filebrowser;

import android.view.View;
import com.samsung.android.gallery.plugins.filebrowser.LogViewFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ d(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((FileBaseFragment) obj).lambda$onBindToolbar$0(view);
                return;
            case 1:
                ((FileViewHolder) obj).lambda$new$0(view);
                return;
            case 2:
                ((LogViewFragment.LineViewHolder) obj).lambda$new$1(view);
                return;
            default:
                ((PathViewHolder) obj).lambda$new$0(view);
                return;
        }
    }
}
