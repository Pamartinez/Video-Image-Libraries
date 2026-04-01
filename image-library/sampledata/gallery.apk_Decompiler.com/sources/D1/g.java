package D1;

import android.graphics.Path;
import android.graphics.PathMeasure;
import java.util.Random;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends ThreadLocal {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f121a;

    public /* synthetic */ g(int i2) {
        this.f121a = i2;
    }

    public final Object initialValue() {
        switch (this.f121a) {
            case 0:
                return 0L;
            case 1:
                return new Random();
            case 2:
                return new PathMeasure();
            case 3:
                return new Path();
            case 4:
                return new Path();
            default:
                return new float[4];
        }
    }
}
