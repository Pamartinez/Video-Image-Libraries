package Hf;

import Ae.b;
import Jf.i;
import Jf.k;
import Jf.l;
import Qe.C0816f;
import Qe.V;
import Qf.h;
import ef.C0993a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import ne.C1194l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a0 {

    /* renamed from: a  reason: collision with root package name */
    public static final i f3439a = l.c(k.DONT_CARE, new String[0]);
    public static final i b = l.c(k.UNINFERRED_LAMBDA_PARAMETER_TYPE, new String[0]);

    /* renamed from: c  reason: collision with root package name */
    public static final Z f3440c = new Z("NO_EXPECTED_TYPE");
    public static final Z d = new Z("UNIT_EXPECTED_TYPE");

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        int i8 = i2;
        if (!(i8 == 4 || i8 == 9 || i8 == 11 || i8 == 15 || i8 == 17 || i8 == 19 || i8 == 26 || i8 == 35 || i8 == 48 || i8 == 53 || i8 == 6 || i8 == 7)) {
            switch (i8) {
                case 56:
                case 57:
                case 58:
                case 59:
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        }
        str = "@NotNull method %s.%s must not return null";
        if (!(i8 == 4 || i8 == 9 || i8 == 11 || i8 == 15 || i8 == 17 || i8 == 19 || i8 == 26 || i8 == 35 || i8 == 48 || i8 == 53 || i8 == 6 || i8 == 7)) {
            switch (i8) {
                case 56:
                case 57:
                case 58:
                case 59:
                    break;
                default:
                    i7 = 3;
                    break;
            }
        }
        i7 = 2;
        Object[] objArr = new Object[i7];
        switch (i8) {
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
            case 15:
            case 17:
            case 19:
            case 26:
            case 35:
            case 48:
            case 53:
            case 56:
            case 57:
            case 58:
            case 59:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils";
                break;
            case 12:
                objArr[0] = "typeConstructor";
                break;
            case 13:
                objArr[0] = "unsubstitutedMemberScope";
                break;
            case 14:
                objArr[0] = "refinedTypeFactory";
                break;
            case 16:
                objArr[0] = "parameters";
                break;
            case 20:
                objArr[0] = "subType";
                break;
            case 21:
                objArr[0] = "superType";
                break;
            case 22:
                objArr[0] = "substitutor";
                break;
            case 24:
                objArr[0] = "result";
                break;
            case 31:
            case 33:
                objArr[0] = "clazz";
                break;
            case 32:
                objArr[0] = "typeArguments";
                break;
            case 34:
                objArr[0] = "projections";
                break;
            case 36:
                objArr[0] = "a";
                break;
            case 37:
                objArr[0] = "b";
                break;
            case 39:
                objArr[0] = "typeParameters";
                break;
            case 41:
                objArr[0] = "typeParameterConstructors";
                break;
            case 42:
                objArr[0] = "specialType";
                break;
            case 43:
            case 44:
                objArr[0] = "isSpecialType";
                break;
            case 45:
            case 46:
                objArr[0] = "parameterDescriptor";
                break;
            case 47:
            case 51:
                objArr[0] = "numberValueTypeConstructor";
                break;
            case 49:
            case 50:
                objArr[0] = "supertypes";
                break;
            case 52:
            case 55:
                objArr[0] = "expectedType";
                break;
            case 54:
                objArr[0] = "literalTypeConstructor";
                break;
            default:
                objArr[0] = "type";
                break;
        }
        if (i8 != 4) {
            if (i8 != 9) {
                if (i8 == 11 || i8 == 15) {
                    objArr[1] = "makeUnsubstitutedType";
                } else if (i8 == 17) {
                    objArr[1] = "getDefaultTypeProjections";
                } else if (i8 == 19) {
                    objArr[1] = "getImmediateSupertypes";
                } else if (i8 == 26) {
                    objArr[1] = "getAllSupertypes";
                } else if (i8 == 35) {
                    objArr[1] = "substituteProjectionsForParameters";
                } else if (i8 != 48) {
                    if (i8 != 53) {
                        if (!(i8 == 6 || i8 == 7)) {
                            switch (i8) {
                                case 56:
                                case 57:
                                case 58:
                                case 59:
                                    break;
                                default:
                                    objArr[1] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils";
                                    break;
                            }
                        }
                    }
                    objArr[1] = "getPrimitiveNumberType";
                } else {
                    objArr[1] = "getDefaultPrimitiveNumberType";
                }
            }
            objArr[1] = "makeNullableIfNeeded";
        } else {
            objArr[1] = "makeNullableAsSpecified";
        }
        switch (i8) {
            case 1:
                objArr[2] = "makeNullable";
                break;
            case 2:
                objArr[2] = "makeNotNullable";
                break;
            case 3:
                objArr[2] = "makeNullableAsSpecified";
                break;
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
            case 15:
            case 17:
            case 19:
            case 26:
            case 35:
            case 48:
            case 53:
            case 56:
            case 57:
            case 58:
            case 59:
                break;
            case 5:
            case 8:
                objArr[2] = "makeNullableIfNeeded";
                break;
            case 10:
                objArr[2] = "canHaveSubtypes";
                break;
            case 12:
            case 13:
            case 14:
                objArr[2] = "makeUnsubstitutedType";
                break;
            case 16:
                objArr[2] = "getDefaultTypeProjections";
                break;
            case 18:
                objArr[2] = "getImmediateSupertypes";
                break;
            case 20:
            case 21:
            case 22:
                objArr[2] = "createSubstitutedSupertype";
                break;
            case 23:
            case 24:
                objArr[2] = "collectAllSupertypes";
                break;
            case 25:
                objArr[2] = "getAllSupertypes";
                break;
            case 27:
                objArr[2] = "isNullableType";
                break;
            case 28:
                objArr[2] = "acceptsNullable";
                break;
            case 29:
                objArr[2] = "hasNullableSuperType";
                break;
            case 30:
                objArr[2] = "getClassDescriptor";
                break;
            case 31:
            case 32:
                objArr[2] = "substituteParameters";
                break;
            case 33:
            case 34:
                objArr[2] = "substituteProjectionsForParameters";
                break;
            case 36:
            case 37:
                objArr[2] = "equalTypes";
                break;
            case 38:
            case 39:
                objArr[2] = "dependsOnTypeParameters";
                break;
            case 40:
            case 41:
                objArr[2] = "dependsOnTypeConstructors";
                break;
            case 42:
            case 43:
            case 44:
                objArr[2] = "contains";
                break;
            case 45:
            case 46:
                objArr[2] = "makeStarProjection";
                break;
            case 47:
            case 49:
                objArr[2] = "getDefaultPrimitiveNumberType";
                break;
            case 50:
                objArr[2] = "findByFqName";
                break;
            case 51:
            case 52:
            case 54:
            case 55:
                objArr[2] = "getPrimitiveNumberType";
                break;
            case 60:
                objArr[2] = "isTypeParameter";
                break;
            case 61:
                objArr[2] = "isReifiedTypeParameter";
                break;
            case 62:
                objArr[2] = "isNonReifiedTypeParameter";
                break;
            case 63:
                objArr[2] = "getTypeParameterDescriptorOrNull";
                break;
            default:
                objArr[2] = "noExpectedType";
                break;
        }
        String format = String.format(str, objArr);
        if (!(i8 == 4 || i8 == 9 || i8 == 11 || i8 == 15 || i8 == 17 || i8 == 19 || i8 == 26 || i8 == 35 || i8 == 48 || i8 == 53 || i8 == 6 || i8 == 7)) {
            switch (i8) {
                case 56:
                case 57:
                case 58:
                case 59:
                    break;
                default:
                    th = new IllegalArgumentException(format);
                    break;
            }
        }
        th = new IllegalStateException(format);
        throw th;
    }

    public static boolean b(C0774x xVar) {
        if (xVar == null) {
            a(28);
            throw null;
        } else if (xVar.u0()) {
            return true;
        } else {
            if (!C0754c.l(xVar) || !b(((C0768q) xVar.x0()).f)) {
                return false;
            }
            return true;
        }
    }

    public static boolean c(C0774x xVar, b bVar, h hVar) {
        C0768q qVar;
        if (xVar == null) {
            return false;
        }
        c0 x02 = xVar.x0();
        if (l(xVar)) {
            return ((Boolean) bVar.invoke(x02)).booleanValue();
        }
        if (hVar != null && hVar.contains(xVar)) {
            return false;
        }
        if (((Boolean) bVar.invoke(x02)).booleanValue()) {
            return true;
        }
        if (hVar == null) {
            int i2 = h.f;
            hVar = Qf.k.e();
        }
        hVar.add(xVar);
        if (x02 instanceof C0768q) {
            qVar = (C0768q) x02;
        } else {
            qVar = null;
        }
        if (qVar != null && (c(qVar.e, bVar, hVar) || c(qVar.f, bVar, hVar))) {
            return true;
        }
        if ((x02 instanceof C0764m) && c(((C0764m) x02).e, bVar, hVar)) {
            return true;
        }
        M s0 = xVar.s0();
        if (s0 instanceof C0773w) {
            for (C0774x c5 : ((C0773w) s0).b) {
                if (c(c5, bVar, hVar)) {
                    return true;
                }
            }
            return false;
        }
        for (P p6 : xVar.e0()) {
            if (!p6.c() && c(p6.b(), bVar, hVar)) {
                return true;
            }
        }
        return false;
    }

    public static List d(List list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new G((C0774x) ((V) it.next()).i()));
            }
            return C1194l.k1(arrayList);
        }
        a(16);
        throw null;
    }

    public static boolean e(C0774x xVar) {
        C0774x xVar2;
        if (xVar == null) {
            a(27);
            throw null;
        } else if (xVar.u0() || (C0754c.l(xVar) && e(((C0768q) xVar.x0()).f))) {
            return true;
        } else {
            if (!(xVar.x0() instanceof C0764m)) {
                if (f(xVar)) {
                    if (!(xVar.s0().g() instanceof C0816f)) {
                        X d2 = X.d(xVar);
                        Collection<C0774x> h5 = xVar.s0().h();
                        ArrayList arrayList = new ArrayList(h5.size());
                        for (C0774x xVar3 : h5) {
                            if (xVar3 != null) {
                                C0774x i2 = d2.i(xVar3, d0.INVARIANT);
                                if (i2 != null) {
                                    xVar2 = h(i2, xVar.u0());
                                } else {
                                    xVar2 = null;
                                }
                                if (xVar2 != null) {
                                    arrayList.add(xVar2);
                                }
                            } else {
                                a(21);
                                throw null;
                            }
                        }
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            if (e((C0774x) it.next())) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
                M s0 = xVar.s0();
                if (s0 instanceof C0773w) {
                    for (C0774x e : ((C0773w) s0).b) {
                        if (e(e)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    public static boolean f(C0774x xVar) {
        V v = null;
        if (xVar != null) {
            if (xVar.s0().g() instanceof V) {
                v = (V) xVar.s0().g();
            }
            if (v != null) {
                return true;
            }
            xVar.s0();
            return false;
        }
        a(60);
        throw null;
    }

    public static c0 g(C0774x xVar, boolean z) {
        if (xVar != null) {
            c0 y0 = xVar.x0().y0(z);
            if (y0 != null) {
                return y0;
            }
            a(4);
            throw null;
        }
        a(3);
        throw null;
    }

    public static C0774x h(C0774x xVar, boolean z) {
        if (xVar == null) {
            a(8);
            throw null;
        } else if (z) {
            return g(xVar, true);
        } else {
            return xVar;
        }
    }

    public static B i(B b5, boolean z) {
        if (b5 == null) {
            a(5);
            throw null;
        } else if (!z) {
            return b5;
        } else {
            B B02 = b5.y0(true);
            if (B02 != null) {
                return B02;
            }
            a(6);
            throw null;
        }
    }

    public static G j(V v) {
        if (v != null) {
            return new G(v);
        }
        a(45);
        throw null;
    }

    public static P k(V v, C0993a aVar) {
        if (v == null) {
            a(46);
            throw null;
        } else if (aVar.f4317a == Y.SUPERTYPE) {
            return new G(C0754c.x(v));
        } else {
            return new G(v);
        }
    }

    public static boolean l(C0774x xVar) {
        if (xVar == null) {
            a(0);
            throw null;
        } else if (xVar == f3440c || xVar == d) {
            return true;
        } else {
            return false;
        }
    }
}
