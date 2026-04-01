package Hb;

import com.samsung.android.gallery.widget.NoItemView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ NoItemView e;

    public /* synthetic */ a(NoItemView noItemView, int i2) {
        this.d = i2;
        this.e = noItemView;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        NoItemView noItemView = this.e;
        String str = (String) obj;
        switch (i2) {
            case 0:
                noItemView.setLabel(str);
                return;
            default:
                noItemView.setDescription(str);
                return;
        }
    }
}
