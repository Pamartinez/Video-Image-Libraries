package tf;

import Hf.C0774x;
import Hf.M;
import Jf.l;
import Qe.A;
import Qe.C;
import Qe.C0812b;
import Qe.C0813c;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0817g;
import Qe.C0819i;
import Qe.C0822l;
import Qe.C0823m;
import Qe.C0825o;
import Qe.C0827q;
import Qe.H;
import Qe.L;
import Qe.S;
import Te.B;
import Te.J;
import Te.w;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import java.util.Collection;
import java.util.LinkedHashSet;
import qf.C1236c;
import qf.C1238e;
import qf.C1242i;

/* renamed from: tf.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1301e {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f5137a = 0;

    static {
        new C1236c("kotlin.jvm.JvmName");
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        switch (i2) {
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 63:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case 89:
            case 94:
            case 96:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i2) {
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 63:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case 89:
            case 94:
            case 96:
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
            case 13:
            case 14:
            case 15:
            case 21:
            case 23:
            case 24:
            case 34:
            case 35:
            case 36:
            case 57:
            case 58:
            case 59:
            case 61:
            case 64:
            case 82:
            case 95:
            case 97:
                objArr[0] = "descriptor";
                break;
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 63:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case 89:
            case 94:
            case 96:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorUtils";
                break;
            case 16:
                objArr[0] = "first";
                break;
            case 17:
                objArr[0] = "second";
                break;
            case 18:
            case 19:
                objArr[0] = "aClass";
                break;
            case 20:
                objArr[0] = "kotlinType";
                break;
            case 25:
                objArr[0] = "declarationDescriptor";
                break;
            case 26:
            case 28:
                objArr[0] = "subClass";
                break;
            case 27:
            case 29:
            case 33:
                objArr[0] = "superClass";
                break;
            case 30:
            case 32:
            case 45:
            case 67:
                objArr[0] = "type";
                break;
            case 31:
                objArr[0] = "other";
                break;
            case 37:
                objArr[0] = "classKind";
                break;
            case 38:
            case 39:
            case 41:
            case 44:
            case 48:
            case 54:
            case 68:
            case 69:
            case 70:
            case 77:
            case 78:
                objArr[0] = "classDescriptor";
                break;
            case 46:
                objArr[0] = "typeConstructor";
                break;
            case 55:
                objArr[0] = "innerClassName";
                break;
            case 56:
                objArr[0] = "location";
                break;
            case 66:
                objArr[0] = Contract.VARIABLE;
                break;
            case 71:
                objArr[0] = "f";
                break;
            case 73:
                objArr[0] = "current";
                break;
            case 74:
                objArr[0] = "result";
                break;
            case 75:
                objArr[0] = "memberDescriptor";
                break;
            case 79:
            case 80:
            case 81:
                objArr[0] = "annotated";
                break;
            case 85:
            case 87:
            case 90:
            case 92:
                objArr[0] = "scope";
                break;
            case 88:
            case 91:
            case 93:
                objArr[0] = "name";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i2) {
            case 4:
                objArr[1] = "getFqNameSafe";
                break;
            case 7:
                objArr[1] = "getFqNameUnsafe";
                break;
            case 9:
            case 10:
                objArr[1] = "getFqNameFromTopLevelClass";
                break;
            case 12:
                objArr[1] = "getClassIdForNonLocalClass";
                break;
            case 22:
                objArr[1] = "getContainingModule";
                break;
            case 40:
                objArr[1] = "getSuperclassDescriptors";
                break;
            case 42:
            case 43:
                objArr[1] = "getSuperClassType";
                break;
            case 47:
                objArr[1] = "getClassDescriptorForTypeConstructor";
                break;
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
                objArr[1] = "getDefaultConstructorVisibility";
                break;
            case 60:
                objArr[1] = "unwrapFakeOverride";
                break;
            case 62:
            case 63:
                objArr[1] = "unwrapSubstitutionOverride";
                break;
            case 65:
                objArr[1] = "unwrapFakeOverrideToAnyDeclaration";
                break;
            case 72:
                objArr[1] = "getAllOverriddenDescriptors";
                break;
            case 76:
                objArr[1] = "getAllOverriddenDeclarations";
                break;
            case 83:
            case 84:
                objArr[1] = "getContainingSourceFile";
                break;
            case 86:
                objArr[1] = "getAllDescriptors";
                break;
            case 89:
                objArr[1] = "getFunctionByName";
                break;
            case 94:
                objArr[1] = "getPropertyByName";
                break;
            case 96:
                objArr[1] = "getDirectMember";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorUtils";
                break;
        }
        switch (i2) {
            case 1:
                objArr[2] = "isLocal";
                break;
            case 2:
                objArr[2] = "getFqName";
                break;
            case 3:
                objArr[2] = "getFqNameSafe";
                break;
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 63:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case 89:
            case 94:
            case 96:
                break;
            case 5:
                objArr[2] = "getFqNameSafeIfPossible";
                break;
            case 6:
                objArr[2] = "getFqNameUnsafe";
                break;
            case 8:
                objArr[2] = "getFqNameFromTopLevelClass";
                break;
            case 11:
                objArr[2] = "getClassIdForNonLocalClass";
                break;
            case 13:
                objArr[2] = "isExtension";
                break;
            case 14:
                objArr[2] = "isOverride";
                break;
            case 15:
                objArr[2] = "isStaticDeclaration";
                break;
            case 16:
            case 17:
                objArr[2] = "areInSameModule";
                break;
            case 18:
            case 19:
                objArr[2] = "getParentOfType";
                break;
            case 20:
            case 23:
                objArr[2] = "getContainingModuleOrNull";
                break;
            case 21:
                objArr[2] = "getContainingModule";
                break;
            case 24:
                objArr[2] = "getContainingClass";
                break;
            case 25:
                objArr[2] = "isAncestor";
                break;
            case 26:
            case 27:
                objArr[2] = "isDirectSubclass";
                break;
            case 28:
            case 29:
                objArr[2] = "isSubclass";
                break;
            case 30:
            case 31:
                objArr[2] = "isSameClass";
                break;
            case 32:
            case 33:
                objArr[2] = "isSubtypeOfClass";
                break;
            case 34:
                objArr[2] = "isAnonymousObject";
                break;
            case 35:
                objArr[2] = "isAnonymousFunction";
                break;
            case 36:
                objArr[2] = "isEnumEntry";
                break;
            case 37:
                objArr[2] = "isKindOf";
                break;
            case 38:
                objArr[2] = "hasAbstractMembers";
                break;
            case 39:
                objArr[2] = "getSuperclassDescriptors";
                break;
            case 41:
                objArr[2] = "getSuperClassType";
                break;
            case 44:
                objArr[2] = "getSuperClassDescriptor";
                break;
            case 45:
                objArr[2] = "getClassDescriptorForType";
                break;
            case 46:
                objArr[2] = "getClassDescriptorForTypeConstructor";
                break;
            case 48:
                objArr[2] = "getDefaultConstructorVisibility";
                break;
            case 54:
            case 55:
            case 56:
                objArr[2] = "getInnerClassByName";
                break;
            case 57:
                objArr[2] = "isStaticNestedClass";
                break;
            case 58:
                objArr[2] = "isTopLevelOrInnerClass";
                break;
            case 59:
                objArr[2] = "unwrapFakeOverride";
                break;
            case 61:
                objArr[2] = "unwrapSubstitutionOverride";
                break;
            case 64:
                objArr[2] = "unwrapFakeOverrideToAnyDeclaration";
                break;
            case 66:
            case 67:
                objArr[2] = "shouldRecordInitializerForProperty";
                break;
            case 68:
                objArr[2] = "classCanHaveAbstractFakeOverride";
                break;
            case 69:
                objArr[2] = "classCanHaveAbstractDeclaration";
                break;
            case 70:
                objArr[2] = "classCanHaveOpenMembers";
                break;
            case 71:
                objArr[2] = "getAllOverriddenDescriptors";
                break;
            case 73:
            case 74:
                objArr[2] = "collectAllOverriddenDescriptors";
                break;
            case 75:
                objArr[2] = "getAllOverriddenDeclarations";
                break;
            case 77:
                objArr[2] = "isSingletonOrAnonymousObject";
                break;
            case 78:
                objArr[2] = "canHaveDeclaredConstructors";
                break;
            case 79:
                objArr[2] = "getJvmName";
                break;
            case 80:
                objArr[2] = "findJvmNameAnnotation";
                break;
            case 81:
                objArr[2] = "hasJvmNameAnnotation";
                break;
            case 82:
                objArr[2] = "getContainingSourceFile";
                break;
            case 85:
                objArr[2] = "getAllDescriptors";
                break;
            case 87:
            case 88:
                objArr[2] = "getFunctionByName";
                break;
            case 90:
            case 91:
                objArr[2] = "getFunctionByNameOrNull";
                break;
            case 92:
            case 93:
                objArr[2] = "getPropertyByName";
                break;
            case 95:
                objArr[2] = "getDirectMember";
                break;
            case 97:
                objArr[2] = "isMethodOfAny";
                break;
            default:
                objArr[2] = "getDispatchReceiverParameterIfNeeded";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 63:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case 89:
            case 94:
            case 96:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public static void b(C0812b bVar, LinkedHashSet linkedHashSet) {
        if (bVar == null) {
            a(73);
            throw null;
        } else if (!linkedHashSet.contains(bVar)) {
            for (C0812b a7 : bVar.a().h()) {
                C0812b a10 = a7.a();
                b(a10, linkedHashSet);
                linkedHashSet.add(a10);
            }
        }
    }

    public static C0816f c(C0774x xVar) {
        if (xVar != null) {
            M s0 = xVar.s0();
            if (s0 != null) {
                C0816f fVar = (C0816f) s0.g();
                if (fVar != null) {
                    return fVar;
                }
                a(47);
                throw null;
            }
            a(46);
            throw null;
        }
        a(45);
        throw null;
    }

    public static C d(C0822l lVar) {
        if (lVar != null) {
            C e = e(lVar);
            if (e != null) {
                return e;
            }
            a(22);
            throw null;
        }
        a(21);
        throw null;
    }

    public static C e(C0822l lVar) {
        if (lVar != null) {
            while (lVar != null) {
                if (lVar instanceof C) {
                    return (C) lVar;
                }
                if (lVar instanceof L) {
                    return ((w) ((L) lVar)).g;
                }
                lVar = lVar.g();
            }
            return null;
        }
        a(23);
        throw null;
    }

    public static S f(C0822l lVar) {
        S s = S.e;
        if (lVar != null) {
            if (lVar instanceof J) {
                lVar = ((J) lVar).E0();
            }
            if (lVar instanceof C0823m) {
                ((C0823m) lVar).getSource().getClass();
            }
            return s;
        }
        a(82);
        throw null;
    }

    public static C1238e g(C0822l lVar) {
        if (lVar != null) {
            C1236c h5 = h(lVar);
            if (h5 != null) {
                return h5.i();
            }
            return g(lVar.g()).b(lVar.getName());
        }
        a(2);
        throw null;
    }

    public static C1236c h(C0822l lVar) {
        if (lVar == null) {
            a(5);
            throw null;
        } else if ((lVar instanceof C) || l.f(lVar)) {
            return C1236c.f5035c;
        } else {
            if (lVar instanceof L) {
                return ((w) ((L) lVar)).f3806h;
            }
            if (lVar instanceof H) {
                return ((B) ((H) lVar)).f3743i;
            }
            return null;
        }
    }

    public static C0822l i(C0822l lVar, Class cls, boolean z) {
        if (lVar == null) {
            return null;
        }
        if (z) {
            lVar = lVar.g();
        }
        while (lVar != null) {
            if (cls.isInstance(lVar)) {
                return lVar;
            }
            lVar = lVar.g();
        }
        return null;
    }

    public static C0816f j(C0816f fVar) {
        if (fVar != null) {
            for (C0774x c5 : fVar.q().h()) {
                C0816f c6 = c(c5);
                if (c6.b() != C0817g.INTERFACE) {
                    return c6;
                }
            }
            return null;
        }
        a(44);
        throw null;
    }

    public static boolean k(C0822l lVar) {
        if (!n(lVar, C0817g.CLASS) || !lVar.getName().equals(C1242i.f5041a)) {
            return false;
        }
        return true;
    }

    public static boolean l(C0822l lVar) {
        if (!n(lVar, C0817g.OBJECT) || !((C0816f) lVar).T()) {
            return false;
        }
        return true;
    }

    public static boolean m(C0822l lVar) {
        if (lVar != null) {
            return n(lVar, C0817g.ENUM_ENTRY);
        }
        a(36);
        throw null;
    }

    public static boolean n(C0822l lVar, C0817g gVar) {
        if (gVar == null) {
            a(37);
            throw null;
        } else if (!(lVar instanceof C0816f) || ((C0816f) lVar).b() != gVar) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean o(C0822l lVar) {
        if (lVar != null) {
            while (lVar != null) {
                if (k(lVar) || ((lVar instanceof C0825o) && ((C0825o) lVar).getVisibility() == C0827q.f)) {
                    return true;
                }
                lVar = lVar.g();
            }
            return false;
        }
        a(1);
        throw null;
    }

    public static boolean p(C0774x xVar, C0822l lVar) {
        if (xVar == null) {
            a(30);
            throw null;
        } else if (lVar != null) {
            C0819i g = xVar.s0().g();
            if (g == null) {
                return false;
            }
            C0822l a7 = g.a();
            if (!(a7 instanceof C0819i) || !(lVar instanceof C0819i) || !((C0819i) lVar).q().equals(((C0819i) a7).q())) {
                return false;
            }
            return true;
        } else {
            a(31);
            throw null;
        }
    }

    public static boolean q(C0822l lVar) {
        if ((n(lVar, C0817g.CLASS) || n(lVar, C0817g.INTERFACE)) && ((C0816f) lVar).k() == A.SEALED) {
            return true;
        }
        return false;
    }

    public static boolean r(C0774x xVar, C0822l lVar) {
        if (xVar == null) {
            a(32);
            throw null;
        } else if (lVar == null) {
            a(33);
            throw null;
        } else if (p(xVar, lVar)) {
            return true;
        } else {
            for (C0774x r : xVar.s0().h()) {
                if (r(r, lVar)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean s(C0822l lVar) {
        if (lVar == null || !(lVar.g() instanceof H)) {
            return false;
        }
        return true;
    }

    public static C0814d t(C0814d dVar) {
        if (dVar != null) {
            while (dVar.b() == C0813c.FAKE_OVERRIDE) {
                Collection h5 = dVar.h();
                if (!h5.isEmpty()) {
                    dVar = (C0814d) h5.iterator().next();
                } else {
                    throw new IllegalStateException("Fake override should have at least one overridden descriptor: " + dVar);
                }
            }
            return dVar;
        }
        a(59);
        throw null;
    }
}
