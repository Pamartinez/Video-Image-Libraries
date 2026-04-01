package cc;

import android.view.View;
import com.samsung.android.gallery.widget.tag.MyTagViewHolder2;

/* renamed from: cc.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0589a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MyTagViewHolder2 e;

    public /* synthetic */ C0589a(MyTagViewHolder2 myTagViewHolder2, int i2) {
        this.d = i2;
        this.e = myTagViewHolder2;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        MyTagViewHolder2 myTagViewHolder2 = this.e;
        switch (i2) {
            case 0:
                myTagViewHolder2.lambda$bindView$0(view);
                return;
            default:
                myTagViewHolder2.lambda$bindView$1(view);
                return;
        }
    }
}
