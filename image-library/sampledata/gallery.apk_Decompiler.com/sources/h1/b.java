package H1;

import F1.a;
import android.os.IBinder;
import android.os.IInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class b extends a implements c {
    /* JADX WARNING: type inference failed for: r1v1, types: [E1.a, H1.c] */
    public static c d(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        if (queryLocalInterface instanceof c) {
            return (c) queryLocalInterface;
        }
        return new E1.a(iBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate", 2);
    }
}
