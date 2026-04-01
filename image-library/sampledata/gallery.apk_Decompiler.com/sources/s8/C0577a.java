package S8;

import Sd.A;
import Sd.C;
import Sd.e;
import Sd.w;
import Sd.y;
import com.samsung.scsp.common.Holder;
import java.util.function.Consumer;

/* renamed from: S8.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0577a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Holder e;

    public /* synthetic */ C0577a(Holder holder, int i2) {
        this.d = i2;
        this.e = holder;
    }

    public final void accept(Object obj) {
        Object obj2;
        int i2 = this.d;
        Holder holder = this.e;
        switch (i2) {
            case 0:
                obj2 = (A) obj;
                break;
            case 1:
                obj2 = (w) obj;
                break;
            case 2:
                obj2 = (y) obj;
                break;
            case 3:
                obj2 = (e) obj;
                break;
            default:
                obj2 = (C) obj;
                break;
        }
        holder.hold(obj2);
    }
}
