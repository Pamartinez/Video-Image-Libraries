package fe;

import android.os.Binder;
import android.os.Parcel;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends Binder {
    public static final Logger b = Logger.getLogger(l.class.getName());

    /* renamed from: a  reason: collision with root package name */
    public c f4359a;

    public final boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
        c cVar = this.f4359a;
        if (cVar != null) {
            int i8 = i7 & 1;
            Logger logger = b;
            if (i8 != 0) {
                return cVar.h(parcel, i2);
            }
            try {
                Level level = Level.WARNING;
                logger.log(level, "ignoring non-oneway transaction. flags=" + i7);
                return false;
            } catch (RuntimeException e) {
                Level level2 = Level.WARNING;
                logger.log(level2, "failure sending transaction " + i2, e);
            }
        }
        return false;
    }

    public final boolean pingBinder() {
        if (this.f4359a != null) {
            return true;
        }
        return false;
    }
}
