package Gf;

import Af.g;
import D0.e;
import Df.C0736b;
import Hf.C0757f;
import Jf.l;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends h implements n {
    public volatile e g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ C0736b f3412h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(m mVar, g gVar, C0736b bVar) {
        super(mVar, gVar);
        this.f3412h = bVar;
        if (mVar != null) {
            this.g = null;
            return;
        }
        d(0);
        throw null;
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 != 2) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 != 2) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        if (i2 != 2) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$5";
        } else {
            objArr[0] = "value";
        }
        if (i2 != 2) {
            objArr[1] = "recursionDetected";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$5";
        }
        if (i2 == 2) {
            objArr[2] = "doPostCompute";
        }
        String format = String.format(str, objArr);
        if (i2 != 2) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public static /* synthetic */ void d(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 != 2) {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i2 != 2) {
            i7 = 3;
        } else {
            i7 = 2;
        }
        Object[] objArr = new Object[i7];
        if (i2 == 1) {
            objArr[0] = "computable";
        } else if (i2 != 2) {
            objArr[0] = "storageManager";
        } else {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValueWithPostCompute";
        }
        if (i2 != 2) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValueWithPostCompute";
        } else {
            objArr[1] = "invoke";
        }
        if (i2 != 2) {
            objArr[2] = "<init>";
        }
        String format = String.format(str, objArr);
        if (i2 != 2) {
            th = new IllegalArgumentException(format);
        } else {
            th = new IllegalStateException(format);
        }
        throw th;
    }

    public final void b(Object obj) {
        this.g = new e(obj);
        if (obj != null) {
            try {
                this.f3412h.invoke(obj);
            } finally {
                this.g = null;
            }
        } else {
            a(2);
            throw null;
        }
    }

    public final l c(boolean z) {
        return new l(new C0757f(C0246a.e0(l.d)), false, 0);
    }

    public final Object invoke() {
        Object obj;
        e eVar = this.g;
        if (eVar == null || ((Thread) eVar.f) != Thread.currentThread()) {
            obj = super.invoke();
        } else if (((Thread) eVar.f) == Thread.currentThread()) {
            obj = eVar.e;
        } else {
            throw new IllegalStateException("No value in this thread (hasValue should be checked before)");
        }
        if (obj != null) {
            return obj;
        }
        d(2);
        throw null;
    }
}
