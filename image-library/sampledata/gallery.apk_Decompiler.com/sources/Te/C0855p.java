package Te;

import Ae.b;
import Af.f;
import Af.g;
import Af.q;
import Gf.e;
import Gf.h;
import Gf.i;
import Gf.m;
import Gf.p;
import Hf.C0758g;
import Hf.C0774x;
import Ye.a;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import qf.C1240g;
import tf.C1311o;

/* renamed from: Te.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0855p extends q {
    public final e b;

    /* renamed from: c  reason: collision with root package name */
    public final e f3786c;
    public final i d;
    public final /* synthetic */ C0856q e;

    /* JADX WARNING: type inference failed for: r0v3, types: [Gf.h, Gf.i] */
    public C0855p(C0856q qVar, p pVar) {
        if (pVar != null) {
            this.e = qVar;
            m mVar = (m) pVar;
            this.b = mVar.b(new C0854o(this, 0));
            this.f3786c = mVar.b(new C0854o(this, 1));
            this.d = new h(mVar, new g(22, this));
            return;
        }
        h(0);
        throw null;
    }

    public static /* synthetic */ void h(int i2) {
        String str;
        int i7;
        Throwable th;
        if (!(i2 == 3 || i2 == 7 || i2 == 9 || i2 == 12)) {
            switch (i2) {
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        }
        str = "@NotNull method %s.%s must not return null";
        if (!(i2 == 3 || i2 == 7 || i2 == 9 || i2 == 12)) {
            switch (i2) {
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                    break;
                default:
                    i7 = 3;
                    break;
            }
        }
        i7 = 2;
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 4:
            case 5:
            case 8:
            case 10:
                objArr[0] = "name";
                break;
            case 2:
            case 6:
                objArr[0] = "location";
                break;
            case 3:
            case 7:
            case 9:
            case 12:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor$EnumEntryScope";
                break;
            case 11:
                objArr[0] = "fromSupertypes";
                break;
            case 13:
                objArr[0] = "kindFilter";
                break;
            case 14:
                objArr[0] = "nameFilter";
                break;
            case 20:
                objArr[0] = "p";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        if (i2 == 3) {
            objArr[1] = "getContributedVariables";
        } else if (i2 == 7) {
            objArr[1] = "getContributedFunctions";
        } else if (i2 == 9) {
            objArr[1] = "getSupertypeScope";
        } else if (i2 != 12) {
            switch (i2) {
                case 15:
                    objArr[1] = "getContributedDescriptors";
                    break;
                case 16:
                    objArr[1] = "computeAllDeclarations";
                    break;
                case 17:
                    objArr[1] = "getFunctionNames";
                    break;
                case 18:
                    objArr[1] = "getClassifierNames";
                    break;
                case 19:
                    objArr[1] = "getVariableNames";
                    break;
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor$EnumEntryScope";
                    break;
            }
        } else {
            objArr[1] = "resolveFakeOverrides";
        }
        switch (i2) {
            case 1:
            case 2:
                objArr[2] = "getContributedVariables";
                break;
            case 3:
            case 7:
            case 9:
            case 12:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                break;
            case 4:
                objArr[2] = "computeProperties";
                break;
            case 5:
            case 6:
                objArr[2] = "getContributedFunctions";
                break;
            case 8:
                objArr[2] = "computeFunctions";
                break;
            case 10:
            case 11:
                objArr[2] = "resolveFakeOverrides";
                break;
            case 13:
            case 14:
                objArr[2] = "getContributedDescriptors";
                break;
            case 20:
                objArr[2] = "printScopeStructure";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (!(i2 == 3 || i2 == 7 || i2 == 9 || i2 == 12)) {
            switch (i2) {
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                    break;
                default:
                    th = new IllegalArgumentException(format);
                    break;
            }
        }
        th = new IllegalStateException(format);
        throw th;
    }

    public final Collection a(C1240g gVar, a aVar) {
        if (gVar == null) {
            h(5);
            throw null;
        } else if (aVar != null) {
            return (Collection) this.b.invoke(gVar);
        } else {
            h(6);
            throw null;
        }
    }

    public final Set b() {
        Set set = (Set) this.e.l.invoke();
        if (set != null) {
            return set;
        }
        h(17);
        throw null;
    }

    public final Collection d(f fVar, b bVar) {
        if (fVar != null) {
            Collection collection = (Collection) this.d.invoke();
            if (collection != null) {
                return collection;
            }
            h(15);
            throw null;
        }
        h(13);
        throw null;
    }

    public final Set e() {
        Set set = Collections.EMPTY_SET;
        if (set != null) {
            return set;
        }
        h(18);
        throw null;
    }

    public final Collection f(C1240g gVar, a aVar) {
        if (gVar == null) {
            h(1);
            throw null;
        } else if (aVar != null) {
            return (Collection) this.f3786c.invoke(gVar);
        } else {
            h(2);
            throw null;
        }
    }

    public final Set g() {
        Set set = (Set) this.e.l.invoke();
        if (set != null) {
            return set;
        }
        h(19);
        throw null;
    }

    public final Af.p i() {
        Af.p A10 = ((C0774x) ((C0758g) this.e.q()).h().iterator().next()).A();
        if (A10 != null) {
            return A10;
        }
        h(9);
        throw null;
    }

    public final LinkedHashSet j(C1240g gVar, Collection collection) {
        if (gVar == null) {
            h(10);
            throw null;
        } else if (collection != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            C1240g gVar2 = gVar;
            Collection collection2 = collection;
            C1311o.f5142c.h(gVar2, collection2, Collections.EMPTY_SET, this.e, new Ff.g(linkedHashSet, 1));
            return linkedHashSet;
        } else {
            h(11);
            throw null;
        }
    }
}
