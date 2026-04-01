package com.samsung.android.gallery.app.ui.dialog.tag;

import com.samsung.android.gallery.app.ui.dialog.tag.AddTagAdapter;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ CharSequence d;
    public final /* synthetic */ ArrayList e;

    public /* synthetic */ a(CharSequence charSequence, ArrayList arrayList) {
        this.d = charSequence;
        this.e = arrayList;
    }

    public final void accept(Object obj) {
        AddTagAdapter.AnonymousClass1.lambda$performFiltering$0(this.d, this.e, (String) obj);
    }
}
