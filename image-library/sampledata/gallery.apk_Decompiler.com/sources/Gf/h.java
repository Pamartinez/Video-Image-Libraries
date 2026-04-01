package Gf;

import Ae.a;
import Qf.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class h implements a {
    public final m d;
    public final a e;
    public volatile Object f;

    public h(m mVar, a aVar) {
        if (mVar != null) {
            this.f = k.NOT_COMPUTED;
            this.d = mVar;
            this.e = aVar;
            return;
        }
        a(0);
        throw null;
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 2 || i2 == 3) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 2 || i2 == 3) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        if (i2 == 1) {
            objArr[0] = "computable";
        } else if (i2 == 2 || i2 == 3) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValue";
        } else {
            objArr[0] = "storageManager";
        }
        if (i2 == 2) {
            objArr[1] = "recursionDetected";
        } else if (i2 != 3) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValue";
        } else {
            objArr[1] = "renderDebugInformation";
        }
        if (!(i2 == 2 || i2 == 3)) {
            objArr[2] = "<init>";
        }
        String format = String.format(str, objArr);
        if (i2 == 2 || i2 == 3) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public l c(boolean z) {
        l d2 = this.d.d((Object) null, "in a lazy value");
        if (d2 != null) {
            return d2;
        }
        a(2);
        throw null;
    }

    public Object invoke() {
        Object obj = this.f;
        if (!(obj instanceof k)) {
            k.k(obj);
            return obj;
        }
        this.d.f3416a.lock();
        try {
            Object obj2 = this.f;
            if (!(obj2 instanceof k)) {
                k.k(obj2);
            } else {
                k kVar = k.COMPUTING;
                if (obj2 == kVar) {
                    this.f = k.RECURSION_WAS_DETECTED;
                    l c5 = c(true);
                    if (!c5.b) {
                        obj2 = c5.f3415c;
                    }
                }
                if (obj2 == k.RECURSION_WAS_DETECTED) {
                    l c6 = c(false);
                    if (!c6.b) {
                        obj2 = c6.f3415c;
                    }
                }
                this.f = kVar;
                obj2 = this.e.invoke();
                b(obj2);
                this.f = obj2;
            }
            this.d.f3416a.unlock();
            return obj2;
        } catch (Throwable th) {
            this.d.f3416a.unlock();
            throw th;
        }
    }

    public void b(Object obj) {
    }
}
