package l9;

import android.graphics.Bitmap;
import android.graphics.Gainmap;
import java.util.function.Consumer;

/* renamed from: l9.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0698a implements Consumer {
    public final /* synthetic */ Bitmap d;

    public /* synthetic */ C0698a(Bitmap bitmap) {
        this.d = bitmap;
    }

    public final void accept(Object obj) {
        this.d.setGainmap((Gainmap) obj);
    }
}
