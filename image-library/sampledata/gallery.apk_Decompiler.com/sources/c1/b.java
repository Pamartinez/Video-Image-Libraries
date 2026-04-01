package C1;

import F1.a;
import android.os.IBinder;
import android.os.IInterface;
import c0.C0086a;
import java.lang.reflect.Field;
import w1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends a implements a {
    public final Object b;

    public b(Object obj) {
        super("com.google.android.gms.dynamic.IObjectWrapper", 0);
        this.b = obj;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [C1.a, E1.a] */
    public static a d(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
        if (queryLocalInterface instanceof a) {
            return (a) queryLocalInterface;
        }
        return new E1.a(iBinder, "com.google.android.gms.dynamic.IObjectWrapper", 1);
    }

    public static Object e(a aVar) {
        if (aVar instanceof b) {
            return ((b) aVar).b;
        }
        IBinder asBinder = aVar.asBinder();
        Field[] declaredFields = asBinder.getClass().getDeclaredFields();
        Field field = null;
        int i2 = 0;
        for (Field field2 : declaredFields) {
            if (!field2.isSynthetic()) {
                i2++;
                field = field2;
            }
        }
        if (i2 == 1) {
            r.b(field);
            if (!field.isAccessible()) {
                field.setAccessible(true);
                try {
                    return field.get(asBinder);
                } catch (NullPointerException e) {
                    throw new IllegalArgumentException("Binder object is null.", e);
                } catch (IllegalAccessException e7) {
                    throw new IllegalArgumentException("Could not access the field in remoteBinder.", e7);
                }
            } else {
                throw new IllegalArgumentException("IObjectWrapper declared field not private!");
            }
        } else {
            throw new IllegalArgumentException(C0086a.i(declaredFields.length, "Unexpected number of IObjectWrapper declared fields: "));
        }
    }
}
