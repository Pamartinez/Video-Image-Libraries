package t0;

import I0.c;
import I0.d;
import androidx.work.impl.utils.IdGenerator;
import com.samsung.android.gallery.image360.activity.viewer.Image360Fragment;
import java.io.ByteArrayInputStream;
import java.util.concurrent.Callable;
import og.e;
import og.i;
import x0.n;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1918a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a(int i2, Object obj) {
        this.f1918a = i2;
        this.b = obj;
    }

    public final Object call() {
        int i2 = this.f1918a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                return IdGenerator.nextAlarmManagerId$lambda$1((IdGenerator) obj);
            case 1:
                return ((Image360Fragment) obj).lambda$new$1();
            case 2:
                return n.c((String) null, (ByteArrayInputStream) obj);
            default:
                i iVar = new i(e.a(new ByteArrayInputStream(((String) obj).getBytes())));
                String[] strArr = c.f341h;
                return n.d(new d(iVar), (String) null, true);
        }
    }
}
