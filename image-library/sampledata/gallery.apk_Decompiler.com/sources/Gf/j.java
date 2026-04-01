package Gf;

import Ae.b;
import Qf.k;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class j implements b {
    public final m d;
    public final ConcurrentHashMap e;
    public final b f;

    public j(m mVar, ConcurrentHashMap concurrentHashMap, b bVar) {
        this.d = mVar;
        this.e = concurrentHashMap;
        this.f = bVar;
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 3 || i2 == 4) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 3 || i2 == 4) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        if (i2 == 1) {
            objArr[0] = "map";
        } else if (i2 == 2) {
            objArr[0] = "compute";
        } else if (i2 == 3 || i2 == 4) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunction";
        } else {
            objArr[0] = "storageManager";
        }
        if (i2 == 3) {
            objArr[1] = "recursionDetected";
        } else if (i2 != 4) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunction";
        } else {
            objArr[1] = "raceCondition";
        }
        if (!(i2 == 3 || i2 == 4)) {
            objArr[2] = "<init>";
        }
        String format = String.format(str, objArr);
        if (i2 == 3 || i2 == 4) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final AssertionError b(Object obj, Object obj2) {
        AssertionError assertionError = new AssertionError("Inconsistent key detected. " + k.COMPUTING + " is expected, was: " + obj2 + ", most probably race condition detected on input " + obj + " under " + this.d);
        m.e(assertionError);
        return assertionError;
    }

    public final AssertionError c(Object obj, Object obj2) {
        AssertionError assertionError = new AssertionError("Race condition detected on input " + obj + ". Old value is " + obj2 + " under " + this.d);
        m.e(assertionError);
        return assertionError;
    }

    public final AssertionError d(Object obj, Throwable th) {
        AssertionError assertionError = new AssertionError("Unable to remove " + obj + " under " + this.d, th);
        m.e(assertionError);
        return assertionError;
    }

    public Object invoke(Object obj) {
        m mVar = this.d;
        a aVar = mVar.b;
        o oVar = mVar.f3416a;
        ConcurrentHashMap concurrentHashMap = this.e;
        Object obj2 = concurrentHashMap.get(obj);
        Object obj3 = k.f3686a;
        Object obj4 = null;
        if (obj2 == null || obj2 == k.COMPUTING) {
            oVar.lock();
            try {
                Object obj5 = concurrentHashMap.get(obj);
                k kVar = k.COMPUTING;
                if (obj5 == kVar) {
                    obj5 = k.RECURSION_WAS_DETECTED;
                    l d2 = mVar.d(obj, "");
                    if (d2 == null) {
                        a(3);
                        throw null;
                    } else if (!d2.b) {
                        Object obj6 = d2.f3415c;
                        oVar.unlock();
                        return obj6;
                    }
                }
                if (obj5 == k.RECURSION_WAS_DETECTED) {
                    l d3 = mVar.d(obj, "");
                    if (d3 == null) {
                        a(3);
                        throw null;
                    } else if (!d3.b) {
                        Object obj7 = d3.f3415c;
                        oVar.unlock();
                        return obj7;
                    }
                }
                if (obj5 != null) {
                    k.k(obj5);
                    if (obj5 != obj3) {
                        obj4 = obj5;
                    }
                    oVar.unlock();
                    return obj4;
                }
                concurrentHashMap.put(obj, kVar);
                Object invoke = this.f.invoke(obj);
                if (invoke != null) {
                    obj3 = invoke;
                }
                Object put = concurrentHashMap.put(obj, obj3);
                if (put == kVar) {
                    oVar.unlock();
                    return invoke;
                }
                throw c(obj, put);
            } catch (Throwable th) {
                throw d(obj, th);
            }
        } else {
            k.k(obj2);
            if (obj2 == obj3) {
                return null;
            }
            return obj2;
        }
    }
}
