package com.samsung.android.gallery.widget.animations;

import android.widget.ImageView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Consumer {
    public final /* synthetic */ SimpleHighlightShrinkHandler d;
    public final /* synthetic */ ImageView e;

    public /* synthetic */ m(SimpleHighlightShrinkHandler simpleHighlightShrinkHandler, ImageView imageView) {
        this.d = simpleHighlightShrinkHandler;
        this.e = imageView;
    }

    public final void accept(Object obj) {
        this.d.lambda$createAnimatorSet$0(this.e, (ListViewHolder) obj);
    }
}
