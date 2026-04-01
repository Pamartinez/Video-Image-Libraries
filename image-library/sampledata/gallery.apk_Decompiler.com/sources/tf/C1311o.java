package tf;

import Ae.b;
import Hf.C0754c;
import Hf.C0756e;
import Hf.C0774x;
import Hf.L;
import If.c;
import If.d;
import If.e;
import If.f;
import Qe.C0812b;
import Qe.C0813c;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0822l;
import Qe.C0827q;
import Qe.C0831v;
import Qe.O;
import Qe.V;
import Qf.h;
import Qf.k;
import Te.J;
import Te.Q;
import Te.u;
import ge.W0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.ServiceLoader;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import qf.C1240g;

/* renamed from: tf.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1311o {
    public static final List b;

    /* renamed from: c  reason: collision with root package name */
    public static final C1311o f5142c;
    public static final C1299c d;

    /* renamed from: a  reason: collision with root package name */
    public final c f5143a;

    /* JADX WARNING: type inference failed for: r0v3, types: [If.c, tf.c, java.lang.Object] */
    static {
        Class<C1304h> cls = C1304h.class;
        b = C1194l.k1(ServiceLoader.load(cls, cls.getClassLoader()));
        ? obj = new Object();
        d = obj;
        f5142c = new C1311o(obj);
    }

    public C1311o(c cVar) {
        if (cVar != null) {
            this.f5143a = cVar;
        } else {
            a(5);
            throw null;
        }
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        int i8 = i2;
        if (!(i8 == 11 || i8 == 12 || i8 == 16 || i8 == 21 || i8 == 93 || i8 == 96 || i8 == 101 || i8 == 42 || i8 == 43)) {
            switch (i8) {
                case 24:
                case 25:
                case 26:
                case 27:
                    break;
                default:
                    switch (i8) {
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                            break;
                        default:
                            switch (i8) {
                                case 78:
                                case 79:
                                case 80:
                                case 81:
                                case 82:
                                    break;
                                default:
                                    switch (i8) {
                                        case 88:
                                        case 89:
                                        case 90:
                                            break;
                                        default:
                                            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                                            break;
                                    }
                            }
                    }
            }
        }
        str = "@NotNull method %s.%s must not return null";
        if (!(i8 == 11 || i8 == 12 || i8 == 16 || i8 == 21 || i8 == 93 || i8 == 96 || i8 == 101 || i8 == 42 || i8 == 43)) {
            switch (i8) {
                case 24:
                case 25:
                case 26:
                case 27:
                    break;
                default:
                    switch (i8) {
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                            break;
                        default:
                            switch (i8) {
                                case 78:
                                case 79:
                                case 80:
                                case 81:
                                case 82:
                                    break;
                                default:
                                    switch (i8) {
                                        case 88:
                                        case 89:
                                        case 90:
                                            break;
                                        default:
                                            i7 = 3;
                                            break;
                                    }
                            }
                    }
            }
        }
        i7 = 2;
        Object[] objArr = new Object[i7];
        switch (i8) {
            case 1:
            case 7:
                objArr[0] = "kotlinTypePreparator";
                break;
            case 2:
                objArr[0] = "customSubtype";
                break;
            case 4:
                objArr[0] = "equalityAxioms";
                break;
            case 5:
                objArr[0] = "axioms";
                break;
            case 8:
            case 9:
                objArr[0] = "candidateSet";
                break;
            case 10:
                objArr[0] = "transformFirst";
                break;
            case 11:
            case 12:
            case 16:
            case 21:
            case 24:
            case 25:
            case 26:
            case 27:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 42:
            case 43:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 88:
            case 89:
            case 90:
            case 93:
            case 96:
            case 101:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil";
                break;
            case 13:
                objArr[0] = "f";
                break;
            case 14:
                objArr[0] = "g";
                break;
            case 15:
            case 17:
                objArr[0] = "descriptor";
                break;
            case 18:
                objArr[0] = "result";
                break;
            case 19:
            case 22:
            case 28:
            case 38:
                objArr[0] = "superDescriptor";
                break;
            case 20:
            case 23:
            case 29:
            case 39:
                objArr[0] = "subDescriptor";
                break;
            case 40:
                objArr[0] = "firstParameters";
                break;
            case 41:
                objArr[0] = "secondParameters";
                break;
            case 44:
                objArr[0] = "typeInSuper";
                break;
            case 45:
                objArr[0] = "typeInSub";
                break;
            case 46:
            case 49:
            case 75:
                objArr[0] = "typeCheckerState";
                break;
            case 47:
                objArr[0] = "superTypeParameter";
                break;
            case 48:
                objArr[0] = "subTypeParameter";
                break;
            case 50:
                objArr[0] = "name";
                break;
            case 51:
                objArr[0] = "membersFromSupertypes";
                break;
            case 52:
                objArr[0] = "membersFromCurrent";
                break;
            case 53:
            case 59:
            case 62:
            case 84:
            case 87:
            case 94:
                objArr[0] = "current";
                break;
            case 54:
            case 60:
            case 64:
            case 85:
            case 104:
                objArr[0] = "strategy";
                break;
            case 55:
                objArr[0] = "overriding";
                break;
            case 56:
                objArr[0] = "fromSuper";
                break;
            case 57:
                objArr[0] = "fromCurrent";
                break;
            case 58:
                objArr[0] = "descriptorsFromSuper";
                break;
            case 61:
            case 63:
                objArr[0] = "notOverridden";
                break;
            case 65:
            case 67:
            case 71:
                objArr[0] = "a";
                break;
            case 66:
            case 68:
            case 73:
                objArr[0] = "b";
                break;
            case 69:
                objArr[0] = "candidate";
                break;
            case 70:
            case 86:
            case 91:
            case 107:
                objArr[0] = "descriptors";
                break;
            case 72:
                objArr[0] = "aReturnType";
                break;
            case 74:
                objArr[0] = "bReturnType";
                break;
            case 76:
            case 83:
                objArr[0] = "overridables";
                break;
            case 77:
            case 99:
                objArr[0] = "descriptorByHandle";
                break;
            case 92:
                objArr[0] = "classModality";
                break;
            case 95:
                objArr[0] = "toFilter";
                break;
            case 97:
            case 102:
                objArr[0] = "overrider";
                break;
            case 98:
            case 103:
                objArr[0] = "extractFrom";
                break;
            case 100:
                objArr[0] = "onConflict";
                break;
            case 105:
            case 106:
                objArr[0] = "memberDescriptor";
                break;
            default:
                objArr[0] = "kotlinTypeRefiner";
                break;
        }
        if (i8 == 11 || i8 == 12) {
            objArr[1] = "filterOverrides";
        } else if (i8 != 16) {
            if (i8 != 21) {
                if (i8 == 93) {
                    objArr[1] = "getMinimalModality";
                } else if (i8 == 96) {
                    objArr[1] = "filterVisibleFakeOverrides";
                } else if (i8 != 101) {
                    if (i8 != 42 && i8 != 43) {
                        switch (i8) {
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                                break;
                            default:
                                switch (i8) {
                                    case 30:
                                    case 31:
                                    case 32:
                                    case 33:
                                    case 34:
                                    case 35:
                                    case 36:
                                    case 37:
                                        objArr[1] = "isOverridableByWithoutExternalConditions";
                                        break;
                                    default:
                                        switch (i8) {
                                            case 78:
                                            case 79:
                                            case 80:
                                            case 81:
                                            case 82:
                                                objArr[1] = "selectMostSpecificMember";
                                                break;
                                            default:
                                                switch (i8) {
                                                    case 88:
                                                    case 89:
                                                    case 90:
                                                        objArr[1] = "determineModalityForFakeOverride";
                                                        break;
                                                    default:
                                                        objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil";
                                                        break;
                                                }
                                        }
                                }
                        }
                    } else {
                        objArr[1] = "createTypeCheckerState";
                    }
                } else {
                    objArr[1] = "extractMembersOverridableInBothWays";
                }
            }
            objArr[1] = "isOverridableBy";
        } else {
            objArr[1] = "getOverriddenDeclarations";
        }
        switch (i8) {
            case 1:
            case 2:
                objArr[2] = "createWithTypePreparatorAndCustomSubtype";
                break;
            case 3:
            case 4:
                objArr[2] = "create";
                break;
            case 5:
            case 6:
            case 7:
                objArr[2] = "<init>";
                break;
            case 8:
                objArr[2] = "filterOutOverridden";
                break;
            case 9:
            case 10:
                objArr[2] = "filterOverrides";
                break;
            case 11:
            case 12:
            case 16:
            case 21:
            case 24:
            case 25:
            case 26:
            case 27:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 42:
            case 43:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 88:
            case 89:
            case 90:
            case 93:
            case 96:
            case 101:
                break;
            case 13:
            case 14:
                objArr[2] = "overrides";
                break;
            case 15:
                objArr[2] = "getOverriddenDeclarations";
                break;
            case 17:
            case 18:
                objArr[2] = "collectOverriddenDeclarations";
                break;
            case 19:
            case 20:
            case 22:
            case 23:
                objArr[2] = "isOverridableBy";
                break;
            case 28:
            case 29:
                objArr[2] = "isOverridableByWithoutExternalConditions";
                break;
            case 38:
            case 39:
                objArr[2] = "getBasicOverridabilityProblem";
                break;
            case 40:
            case 41:
                objArr[2] = "createTypeCheckerState";
                break;
            case 44:
            case 45:
            case 46:
                objArr[2] = "areTypesEquivalent";
                break;
            case 47:
            case 48:
            case 49:
                objArr[2] = "areTypeParametersEquivalent";
                break;
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
                objArr[2] = "generateOverridesInFunctionGroup";
                break;
            case 55:
            case 56:
                objArr[2] = "isVisibleForOverride";
                break;
            case 57:
            case 58:
            case 59:
            case 60:
                objArr[2] = "extractAndBindOverridesForMember";
                break;
            case 61:
                objArr[2] = "allHasSameContainingDeclaration";
                break;
            case 62:
            case 63:
            case 64:
                objArr[2] = "createAndBindFakeOverrides";
                break;
            case 65:
            case 66:
                objArr[2] = "isMoreSpecific";
                break;
            case 67:
            case 68:
                objArr[2] = "isVisibilityMoreSpecific";
                break;
            case 69:
            case 70:
                objArr[2] = "isMoreSpecificThenAllOf";
                break;
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
                objArr[2] = "isReturnTypeMoreSpecific";
                break;
            case 76:
            case 77:
                objArr[2] = "selectMostSpecificMember";
                break;
            case 83:
            case 84:
            case 85:
                objArr[2] = "createAndBindFakeOverride";
                break;
            case 86:
            case 87:
                objArr[2] = "determineModalityForFakeOverride";
                break;
            case 91:
            case 92:
                objArr[2] = "getMinimalModality";
                break;
            case 94:
            case 95:
                objArr[2] = "filterVisibleFakeOverrides";
                break;
            case 97:
            case 98:
            case 99:
            case 100:
            case 102:
            case 103:
            case 104:
                objArr[2] = "extractMembersOverridableInBothWays";
                break;
            case 105:
                objArr[2] = "resolveUnknownVisibilityForMember";
                break;
            case 106:
                objArr[2] = "computeVisibilityToInherit";
                break;
            case 107:
                objArr[2] = "findMaxVisibility";
                break;
            default:
                objArr[2] = "createWithTypeRefiner";
                break;
        }
        String format = String.format(str, objArr);
        if (!(i8 == 11 || i8 == 12 || i8 == 16 || i8 == 21 || i8 == 93 || i8 == 96 || i8 == 101 || i8 == 42 || i8 == 43)) {
            switch (i8) {
                case 24:
                case 25:
                case 26:
                case 27:
                    break;
                default:
                    switch (i8) {
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                            break;
                        default:
                            switch (i8) {
                                case 78:
                                case 79:
                                case 80:
                                case 81:
                                case 82:
                                    break;
                                default:
                                    switch (i8) {
                                        case 88:
                                        case 89:
                                        case 90:
                                            break;
                                        default:
                                            th = new IllegalArgumentException(format);
                                            break;
                                    }
                            }
                    }
            }
        }
        th = new IllegalStateException(format);
        throw th;
    }

    public static boolean b(C0774x xVar, C0774x xVar2, L l) {
        if (xVar == null) {
            a(44);
            throw null;
        } else if (xVar2 == null) {
            a(45);
            throw null;
        } else if (!C0754c.k(xVar) || !C0754c.k(xVar2)) {
            return C0756e.g(l, xVar.x0(), xVar2.x0());
        } else {
            return true;
        }
    }

    public static void c(C0814d dVar, LinkedHashSet linkedHashSet) {
        if (dVar != null) {
            C0813c b5 = dVar.b();
            b5.getClass();
            if (b5 != C0813c.FAKE_OVERRIDE) {
                linkedHashSet.add(dVar);
            } else if (!dVar.h().isEmpty()) {
                for (C0814d c5 : dVar.h()) {
                    c(c5, linkedHashSet);
                }
            } else {
                throw new IllegalStateException("No overridden descriptors found for (fake override) " + dVar);
            }
        } else {
            a(17);
            throw null;
        }
    }

    public static ArrayList d(C0812b bVar) {
        u H5 = bVar.H();
        ArrayList arrayList = new ArrayList();
        if (H5 != null) {
            arrayList.add(H5.getType());
        }
        for (Q type : bVar.B()) {
            arrayList.add(type.getType());
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
        if (r6 != false) goto L_0x0048;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void e(java.util.Collection<Qe.C0814d> r10, Qe.C0816f r11, tf.C1312p r12) {
        /*
            r0 = 0
            if (r10 == 0) goto L_0x01d6
            if (r11 == 0) goto L_0x01d0
            r1 = r10
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x0011:
            boolean r3 = r1.hasNext()
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L_0x004e
            java.lang.Object r3 = r1.next()
            r6 = r3
            Qe.d r6 = (Qe.C0814d) r6
            Qe.p r7 = r6.getVisibility()
            boolean r7 = Qe.C0827q.e(r7)
            if (r7 != 0) goto L_0x0047
            r7 = 0
            if (r6 == 0) goto L_0x0042
            if (r11 == 0) goto L_0x003d
            Qe.S r7 = Qe.C0827q.l
            Qe.o r6 = Qe.C0827q.c(r7, r6, r11)
            if (r6 != 0) goto L_0x0039
            r6 = 1
            goto L_0x003a
        L_0x0039:
            r6 = 0
        L_0x003a:
            if (r6 == 0) goto L_0x0047
            goto L_0x0048
        L_0x003d:
            r10 = 3
            Qe.C0827q.a(r10)
            throw r7
        L_0x0042:
            r10 = 2
            Qe.C0827q.a(r10)
            throw r7
        L_0x0047:
            r4 = r5
        L_0x0048:
            if (r4 == 0) goto L_0x0011
            r2.add(r3)
            goto L_0x0011
        L_0x004e:
            boolean r1 = r2.isEmpty()
            if (r1 == 0) goto L_0x0055
            goto L_0x0056
        L_0x0055:
            r10 = r2
        L_0x0056:
            java.util.Iterator r2 = r10.iterator()
            r3 = r5
            r6 = r3
        L_0x005c:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x00a4
            java.lang.Object r7 = r2.next()
            Qe.d r7 = (Qe.C0814d) r7
            int[] r8 = tf.C1308l.f5139c
            Qe.A r9 = r7.k()
            int r9 = r9.ordinal()
            r8 = r8[r9]
            if (r8 == r4) goto L_0x0098
            r9 = 2
            if (r8 == r9) goto L_0x0084
            r7 = 3
            if (r8 == r7) goto L_0x0082
            r7 = 4
            if (r8 == r7) goto L_0x0080
            goto L_0x005c
        L_0x0080:
            r6 = r4
            goto L_0x005c
        L_0x0082:
            r3 = r4
            goto L_0x005c
        L_0x0084:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "Member cannot have SEALED modality: "
            r11.<init>(r12)
            r11.append(r7)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L_0x0098:
            Qe.A r2 = Qe.A.FINAL
            if (r2 == 0) goto L_0x009e
            goto L_0x01a4
        L_0x009e:
            r10 = 88
            a(r10)
            throw r0
        L_0x00a4:
            boolean r2 = r11.b0()
            if (r2 == 0) goto L_0x00bb
            Qe.A r2 = r11.k()
            Qe.A r7 = Qe.A.ABSTRACT
            if (r2 == r7) goto L_0x00bb
            Qe.A r2 = r11.k()
            Qe.A r7 = Qe.A.SEALED
            if (r2 == r7) goto L_0x00bb
            r5 = r4
        L_0x00bb:
            if (r3 == 0) goto L_0x00cb
            if (r6 != 0) goto L_0x00cb
            Qe.A r2 = Qe.A.OPEN
            if (r2 == 0) goto L_0x00c5
            goto L_0x01a4
        L_0x00c5:
            r10 = 89
            a(r10)
            throw r0
        L_0x00cb:
            if (r3 != 0) goto L_0x00e2
            if (r6 == 0) goto L_0x00e2
            if (r5 == 0) goto L_0x00d6
            Qe.A r2 = r11.k()
            goto L_0x00d8
        L_0x00d6:
            Qe.A r2 = Qe.A.ABSTRACT
        L_0x00d8:
            if (r2 == 0) goto L_0x00dc
            goto L_0x01a4
        L_0x00dc:
            r10 = 90
            a(r10)
            throw r0
        L_0x00e2:
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.Iterator r3 = r10.iterator()
        L_0x00eb:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto L_0x010b
            java.lang.Object r6 = r3.next()
            Qe.d r6 = (Qe.C0814d) r6
            if (r6 == 0) goto L_0x0105
            java.util.LinkedHashSet r7 = new java.util.LinkedHashSet
            r7.<init>()
            c(r6, r7)
            r2.addAll(r7)
            goto L_0x00eb
        L_0x0105:
            r10 = 15
            a(r10)
            throw r0
        L_0x010b:
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x012e
            java.util.Iterator r3 = r2.iterator()
            java.lang.Object r3 = r3.next()
            Qe.l r3 = (Qe.C0822l) r3
            Qe.C r3 = xf.C1353d.j(r3)
            Qe.B r6 = If.g.f3462a
            java.lang.Object r3 = r3.x(r6)
            if (r3 != 0) goto L_0x0128
            goto L_0x012e
        L_0x0128:
            java.lang.ClassCastException r10 = new java.lang.ClassCastException
            r10.<init>()
            throw r10
        L_0x012e:
            int r3 = r2.size()
            if (r3 > r4) goto L_0x0135
            goto L_0x0171
        L_0x0135:
            java.util.LinkedHashSet r3 = new java.util.LinkedHashSet
            r3.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x013e:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0170
            java.lang.Object r4 = r2.next()
            java.util.Iterator r6 = r3.iterator()
        L_0x014c:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x016c
            java.lang.Object r7 = r6.next()
            r8 = r4
            Qe.b r8 = (Qe.C0812b) r8
            Qe.b r7 = (Qe.C0812b) r7
            boolean r9 = q(r8, r7)
            if (r9 == 0) goto L_0x0165
            r6.remove()
            goto L_0x014c
        L_0x0165:
            boolean r7 = q(r7, r8)
            if (r7 == 0) goto L_0x014c
            goto L_0x013e
        L_0x016c:
            r3.add(r4)
            goto L_0x013e
        L_0x0170:
            r2 = r3
        L_0x0171:
            Qe.A r3 = r11.k()
            if (r3 == 0) goto L_0x01ca
            Qe.A r4 = Qe.A.ABSTRACT
            java.util.Iterator r2 = r2.iterator()
        L_0x017d:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto L_0x01a1
            java.lang.Object r6 = r2.next()
            Qe.d r6 = (Qe.C0814d) r6
            if (r5 == 0) goto L_0x0195
            Qe.A r7 = r6.k()
            Qe.A r8 = Qe.A.ABSTRACT
            if (r7 != r8) goto L_0x0195
            r6 = r3
            goto L_0x0199
        L_0x0195:
            Qe.A r6 = r6.k()
        L_0x0199:
            int r7 = r6.compareTo(r4)
            if (r7 >= 0) goto L_0x017d
            r4 = r6
            goto L_0x017d
        L_0x01a1:
            if (r4 == 0) goto L_0x01c4
            r2 = r4
        L_0x01a4:
            if (r1 == 0) goto L_0x01a9
            Qe.p r0 = Qe.C0827q.f3677h
            goto L_0x01ab
        L_0x01a9:
            Qe.p r0 = Qe.C0827q.g
        L_0x01ab:
            tf.k r1 = new tf.k
            r3 = 0
            r1.<init>(r3)
            java.lang.Object r1 = s(r10, r1)
            Qe.d r1 = (Qe.C0814d) r1
            Qe.c r3 = Qe.C0813c.FAKE_OVERRIDE
            Qe.d r11 = r1.S(r11, r2, r0, r3)
            r12.p(r11, r10)
            r12.b(r11)
            return
        L_0x01c4:
            r10 = 93
            a(r10)
            throw r0
        L_0x01ca:
            r10 = 92
            a(r10)
            throw r0
        L_0x01d0:
            r10 = 84
            a(r10)
            throw r0
        L_0x01d6:
            r10 = 83
            a(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: tf.C1311o.e(java.util.Collection, Qe.f, tf.p):void");
    }

    public static ArrayList g(Object obj, LinkedList linkedList, b bVar, b bVar2) {
        if (obj != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(obj);
            C0812b bVar3 = (C0812b) bVar.invoke(obj);
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                C0812b bVar4 = (C0812b) bVar.invoke(next);
                if (obj == next) {
                    it.remove();
                } else {
                    C1309m j2 = j(bVar3, bVar4);
                    if (j2 == C1309m.OVERRIDABLE) {
                        arrayList.add(next);
                        it.remove();
                    } else if (j2 == C1309m.CONFLICT) {
                        bVar2.invoke(next);
                        it.remove();
                    }
                }
            }
            return arrayList;
        }
        a(97);
        throw null;
    }

    public static C1310n i(C0812b bVar, C0812b bVar2) {
        boolean z;
        boolean z3;
        C1310n nVar;
        if (bVar == null) {
            a(38);
            throw null;
        } else if (bVar2 != null) {
            boolean z7 = bVar instanceof C0831v;
            if ((z7 && !(bVar2 instanceof C0831v)) || (((z = bVar instanceof O)) && !(bVar2 instanceof O))) {
                return C1310n.c("Member kind mismatch");
            }
            if (!z7 && !z) {
                throw new IllegalArgumentException("This type of CallableDescriptor cannot be checked for overridability: " + bVar);
            } else if (!bVar.getName().equals(bVar2.getName())) {
                return C1310n.c("Name mismatch");
            } else {
                boolean z9 = false;
                if (bVar.H() == null) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (bVar2.H() == null) {
                    z9 = true;
                }
                if (z3 != z9) {
                    nVar = C1310n.c("Receiver presence mismatch");
                } else if (bVar.B().size() != bVar2.B().size()) {
                    nVar = C1310n.c("Value parameter number mismatch");
                } else {
                    nVar = null;
                }
                if (nVar != null) {
                    return nVar;
                }
                return null;
            }
        } else {
            a(39);
            throw null;
        }
    }

    public static C1309m j(C0812b bVar, C0812b bVar2) {
        C1311o oVar = f5142c;
        C1309m b5 = oVar.l(bVar2, bVar, (C0816f) null).b();
        C1309m b8 = oVar.m(bVar, bVar2, (C0816f) null, false).b();
        C1309m mVar = C1309m.OVERRIDABLE;
        if (b5 == mVar && b8 == mVar) {
            return mVar;
        }
        C1309m mVar2 = C1309m.CONFLICT;
        if (b5 == mVar2 || b8 == mVar2) {
            return mVar2;
        }
        return C1309m.INCOMPATIBLE;
    }

    public static boolean k(C0812b bVar, C0812b bVar2) {
        boolean z;
        if (bVar == null) {
            a(65);
            throw null;
        } else if (bVar2 != null) {
            C0774x returnType = bVar.getReturnType();
            C0774x returnType2 = bVar2.getReturnType();
            if (!p(bVar, bVar2)) {
                return false;
            }
            L f = f5142c.f(bVar.getTypeParameters(), bVar2.getTypeParameters());
            if (bVar instanceof C0831v) {
                return o(bVar, returnType, bVar2, returnType2, f);
            }
            if (bVar instanceof O) {
                O o2 = (O) bVar;
                O o3 = (O) bVar2;
                J setter = o2.getSetter();
                J setter2 = o3.getSetter();
                if (setter == null || setter2 == null) {
                    z = true;
                } else {
                    z = p(setter, setter2);
                }
                if (!z) {
                    return false;
                }
                if (o2.G() && o3.G()) {
                    return C0756e.g(f, returnType.x0(), returnType2.x0());
                }
                if ((o2.G() || !o3.G()) && o(bVar, returnType, bVar2, returnType2, f)) {
                    return true;
                }
                return false;
            }
            throw new IllegalArgumentException("Unexpected callable: " + bVar.getClass());
        } else {
            a(66);
            throw null;
        }
    }

    public static boolean o(C0812b bVar, C0774x xVar, C0812b bVar2, C0774x xVar2, L l) {
        if (bVar == null) {
            a(71);
            throw null;
        } else if (xVar == null) {
            a(72);
            throw null;
        } else if (bVar2 == null) {
            a(73);
            throw null;
        } else if (xVar2 != null) {
            return C0756e.m(C0756e.f3442a, l, xVar.x0(), xVar2.x0());
        } else {
            a(74);
            throw null;
        }
    }

    public static boolean p(C0812b bVar, C0812b bVar2) {
        if (bVar == null) {
            a(67);
            throw null;
        } else if (bVar2 != null) {
            Integer b5 = C0827q.b(bVar.getVisibility(), bVar2.getVisibility());
            if (b5 == null || b5.intValue() >= 0) {
                return true;
            }
            return false;
        } else {
            a(68);
            throw null;
        }
    }

    public static boolean q(C0812b bVar, C0812b bVar2) {
        if (bVar == null) {
            a(13);
            throw null;
        } else if (bVar2 != null) {
            boolean equals = bVar.equals(bVar2);
            C1299c cVar = C1299c.d;
            if (!equals && cVar.c(bVar.a(), bVar2.a(), false)) {
                return true;
            }
            C0812b a7 = bVar2.a();
            int i2 = C1301e.f5137a;
            LinkedHashSet<C0812b> linkedHashSet = new LinkedHashSet<>();
            C1301e.b(bVar.a(), linkedHashSet);
            for (C0812b c5 : linkedHashSet) {
                if (cVar.c(a7, c5, false)) {
                    return true;
                }
            }
            return false;
        } else {
            a(14);
            throw null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00fd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void r(Qe.C0814d r6, Ae.b r7) {
        /*
            r0 = 0
            if (r6 == 0) goto L_0x0126
            java.util.Collection r1 = r6.h()
            java.util.Iterator r1 = r1.iterator()
        L_0x000b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0023
            java.lang.Object r2 = r1.next()
            Qe.d r2 = (Qe.C0814d) r2
            Qe.p r3 = r2.getVisibility()
            Qe.p r4 = Qe.C0827q.g
            if (r3 != r4) goto L_0x000b
            r(r2, r7)
            goto L_0x000b
        L_0x0023:
            Qe.p r1 = r6.getVisibility()
            Qe.p r2 = Qe.C0827q.g
            if (r1 == r2) goto L_0x002d
            goto L_0x011f
        L_0x002d:
            java.util.Collection r1 = r6.h()
            if (r1 == 0) goto L_0x0120
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x003c
            Qe.p r2 = Qe.C0827q.f3679j
            goto L_0x0089
        L_0x003c:
            java.util.Iterator r2 = r1.iterator()
        L_0x0040:
            r3 = r0
        L_0x0041:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0063
            java.lang.Object r4 = r2.next()
            Qe.d r4 = (Qe.C0814d) r4
            Qe.p r4 = r4.getVisibility()
            if (r3 != 0) goto L_0x0055
        L_0x0053:
            r3 = r4
            goto L_0x0041
        L_0x0055:
            java.lang.Integer r5 = Qe.C0827q.b(r4, r3)
            if (r5 != 0) goto L_0x005c
            goto L_0x0040
        L_0x005c:
            int r5 = r5.intValue()
            if (r5 <= 0) goto L_0x0041
            goto L_0x0053
        L_0x0063:
            if (r3 != 0) goto L_0x0067
        L_0x0065:
            r2 = r0
            goto L_0x0089
        L_0x0067:
            java.util.Iterator r2 = r1.iterator()
        L_0x006b:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0088
            java.lang.Object r4 = r2.next()
            Qe.d r4 = (Qe.C0814d) r4
            Qe.p r4 = r4.getVisibility()
            java.lang.Integer r4 = Qe.C0827q.b(r3, r4)
            if (r4 == 0) goto L_0x0065
            int r4 = r4.intValue()
            if (r4 >= 0) goto L_0x006b
            goto L_0x0065
        L_0x0088:
            r2 = r3
        L_0x0089:
            if (r2 != 0) goto L_0x008d
        L_0x008b:
            r2 = r0
            goto L_0x00c2
        L_0x008d:
            Qe.c r3 = r6.b()
            Qe.c r4 = Qe.C0813c.FAKE_OVERRIDE
            if (r3 != r4) goto L_0x00b8
            java.util.Iterator r1 = r1.iterator()
        L_0x0099:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00c2
            java.lang.Object r3 = r1.next()
            Qe.d r3 = (Qe.C0814d) r3
            Qe.A r4 = r3.k()
            Qe.A r5 = Qe.A.ABSTRACT
            if (r4 == r5) goto L_0x0099
            Qe.p r3 = r3.getVisibility()
            boolean r3 = r3.equals(r2)
            if (r3 != 0) goto L_0x0099
            goto L_0x008b
        L_0x00b8:
            Qe.j0 r1 = r2.f3674a
            Qe.j0 r1 = r1.c()
            Qe.p r2 = Qe.C0827q.f(r1)
        L_0x00c2:
            if (r2 != 0) goto L_0x00cc
            if (r7 == 0) goto L_0x00c9
            r7.invoke(r6)
        L_0x00c9:
            Qe.p r1 = Qe.C0827q.e
            goto L_0x00cd
        L_0x00cc:
            r1 = r2
        L_0x00cd:
            boolean r3 = r6 instanceof Te.H
            if (r3 == 0) goto L_0x00fd
            r3 = r6
            Te.H r3 = (Te.H) r3
            if (r1 == 0) goto L_0x00f7
            r3.n = r1
            Qe.O r6 = (Qe.O) r6
            java.util.ArrayList r6 = r6.o()
            java.util.Iterator r6 = r6.iterator()
        L_0x00e2:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x011f
            java.lang.Object r1 = r6.next()
            Qe.N r1 = (Qe.N) r1
            if (r2 != 0) goto L_0x00f2
            r3 = r0
            goto L_0x00f3
        L_0x00f2:
            r3 = r7
        L_0x00f3:
            r(r1, r3)
            goto L_0x00e2
        L_0x00f7:
            r6 = 20
            Te.H.w0(r6)
            throw r0
        L_0x00fd:
            boolean r7 = r6 instanceof Te.t
            if (r7 == 0) goto L_0x010e
            Te.t r6 = (Te.t) r6
            if (r1 == 0) goto L_0x0108
            r6.f3802p = r1
            return
        L_0x0108:
            r6 = 10
            Te.t.w0(r6)
            throw r0
        L_0x010e:
            Te.F r6 = (Te.F) r6
            r6.f3749o = r1
            Qe.O r7 = r6.E0()
            Qe.p r7 = r7.getVisibility()
            if (r1 == r7) goto L_0x011f
            r7 = 0
            r6.f3747i = r7
        L_0x011f:
            return
        L_0x0120:
            r6 = 107(0x6b, float:1.5E-43)
            a(r6)
            throw r0
        L_0x0126:
            r6 = 105(0x69, float:1.47E-43)
            a(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: tf.C1311o.r(Qe.d, Ae.b):void");
    }

    public static Object s(Collection collection, b bVar) {
        Object obj;
        if (collection.size() == 1) {
            Object K02 = C1194l.K0(collection);
            if (K02 != null) {
                return K02;
            }
            a(78);
            throw null;
        }
        ArrayList arrayList = new ArrayList(2);
        ArrayList arrayList2 = new ArrayList(C1196n.w0(collection, 10));
        for (Object invoke : collection) {
            arrayList2.add(bVar.invoke(invoke));
        }
        Object K03 = C1194l.K0(collection);
        C0812b bVar2 = (C0812b) bVar.invoke(K03);
        for (Object next : collection) {
            C0812b bVar3 = (C0812b) bVar.invoke(next);
            if (bVar3 != null) {
                Iterator it = arrayList2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!k(bVar3, (C0812b) it.next())) {
                            break;
                        }
                    } else {
                        arrayList.add(next);
                        break;
                    }
                }
                if (k(bVar3, bVar2) && !k(bVar2, bVar3)) {
                    K03 = next;
                }
            } else {
                a(69);
                throw null;
            }
        }
        if (arrayList.isEmpty()) {
            if (K03 != null) {
                return K03;
            }
            a(79);
            throw null;
        } else if (arrayList.size() == 1) {
            Object K04 = C1194l.K0(arrayList);
            if (K04 != null) {
                return K04;
            }
            a(80);
            throw null;
        } else {
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                if (!C0754c.l(((C0812b) bVar.invoke(obj)).getReturnType())) {
                    break;
                }
            }
            if (obj != null) {
                return obj;
            }
            Object K05 = C1194l.K0(arrayList);
            if (K05 != null) {
                return K05;
            }
            a(82);
            throw null;
        }
    }

    public final L f(List list, List list2) {
        if (list == null) {
            a(40);
            throw null;
        } else if (list2 != null) {
            boolean isEmpty = list.isEmpty();
            e eVar = e.f3460a;
            f fVar = f.f3461a;
            c cVar = this.f5143a;
            if (isEmpty) {
                return new L(true, true, new W0((HashMap) null, cVar), eVar, fVar);
            }
            HashMap hashMap = new HashMap();
            for (int i2 = 0; i2 < list.size(); i2++) {
                hashMap.put(((V) list.get(i2)).q(), ((V) list2.get(i2)).q());
            }
            return new L(true, true, new W0(hashMap, cVar), eVar, fVar);
        } else {
            a(41);
            throw null;
        }
    }

    public final void h(C1240g gVar, Collection collection, Collection collection2, C0816f fVar, C1312p pVar) {
        Integer b5;
        boolean z;
        Collection<C0814d> collection3 = collection;
        C0816f fVar2 = fVar;
        C1312p pVar2 = pVar;
        if (gVar == null) {
            a(50);
            throw null;
        } else if (collection3 == null) {
            a(51);
            throw null;
        } else if (collection2 == null) {
            a(52);
            throw null;
        } else if (fVar2 != null) {
            LinkedHashSet<C0814d> linkedHashSet = new LinkedHashSet<>(collection3);
            Iterator it = collection2.iterator();
            while (it.hasNext()) {
                C0814d dVar = (C0814d) it.next();
                if (dVar != null) {
                    ArrayList arrayList = new ArrayList(collection3.size());
                    int i2 = h.f;
                    h e = k.e();
                    for (C0814d dVar2 : collection3) {
                        C1309m b8 = l(dVar2, dVar, fVar2).b();
                        if (C0827q.e(dVar2.getVisibility()) || C0827q.c(C0827q.l, dVar2, dVar) != null) {
                            z = false;
                        } else {
                            z = true;
                        }
                        int i7 = C1308l.b[b8.ordinal()];
                        if (i7 == 1) {
                            if (z) {
                                e.add(dVar2);
                            }
                            arrayList.add(dVar2);
                        } else if (i7 == 2) {
                            if (z) {
                                pVar2.d(dVar2, dVar);
                            }
                            arrayList.add(dVar2);
                        }
                    }
                    pVar2.p(dVar, e);
                    linkedHashSet.removeAll(arrayList);
                } else {
                    a(57);
                    throw null;
                }
            }
            if (linkedHashSet.size() >= 2) {
                C0822l g = ((C0814d) linkedHashSet.iterator().next()).g();
                if (!linkedHashSet.isEmpty()) {
                    for (C0814d g3 : linkedHashSet) {
                        if (g3.g() != g) {
                            LinkedList<C0814d> linkedList = new LinkedList<>(linkedHashSet);
                            while (!linkedList.isEmpty()) {
                                linkedList.isEmpty();
                                C0814d dVar3 = null;
                                for (C0814d dVar4 : linkedList) {
                                    if (dVar3 == null || ((b5 = C0827q.b(dVar3.getVisibility(), dVar4.getVisibility())) != null && b5.intValue() < 0)) {
                                        dVar3 = dVar4;
                                    }
                                }
                                j.b(dVar3);
                                e(g(dVar3, linkedList, new C1307k(1), new Ff.j(7, pVar2, dVar3)), fVar2, pVar2);
                            }
                            return;
                        }
                    }
                }
            }
            for (C0814d singleton : linkedHashSet) {
                e(Collections.singleton(singleton), fVar2, pVar2);
            }
        } else {
            a(53);
            throw null;
        }
    }

    public final C1310n l(C0812b bVar, C0812b bVar2, C0816f fVar) {
        if (bVar == null) {
            a(19);
            throw null;
        } else if (bVar2 != null) {
            return m(bVar, bVar2, fVar, false);
        } else {
            a(20);
            throw null;
        }
    }

    public final C1310n m(C0812b bVar, C0812b bVar2, C0816f fVar, boolean z) {
        boolean z3;
        if (bVar == null) {
            a(22);
            throw null;
        } else if (bVar2 != null) {
            C1310n n = n(bVar, bVar2, z);
            if (n.b() == C1309m.OVERRIDABLE) {
                z3 = true;
            } else {
                z3 = false;
            }
            List<C1304h> list = b;
            for (C1304h hVar : list) {
                if (hVar.b() != C1302f.CONFLICTS_ONLY && (!z3 || hVar.b() != C1302f.SUCCESS_ONLY)) {
                    int i2 = C1308l.f5138a[hVar.a(bVar, bVar2, fVar).ordinal()];
                    if (i2 == 1) {
                        z3 = true;
                    } else if (i2 == 2) {
                        return C1310n.c("External condition");
                    }
                }
            }
            if (!z3) {
                return n;
            }
            for (C1304h hVar2 : list) {
                if (hVar2.b() == C1302f.CONFLICTS_ONLY) {
                    int i7 = C1308l.f5138a[hVar2.a(bVar, bVar2, fVar).ordinal()];
                    if (i7 == 1) {
                        throw new IllegalStateException("Contract violation in " + hVar2.getClass().getName() + " condition. It's not supposed to end with success");
                    } else if (i7 == 2) {
                        return C1310n.c("External condition");
                    }
                }
            }
            C1310n nVar = C1310n.f5140c;
            if (nVar != null) {
                return nVar;
            }
            C1310n.a(0);
            throw null;
        } else {
            a(23);
            throw null;
        }
    }

    public final C1310n n(C0812b bVar, C0812b bVar2, boolean z) {
        if (bVar == null) {
            a(28);
            throw null;
        } else if (bVar2 != null) {
            C1310n i2 = i(bVar, bVar2);
            if (i2 != null) {
                return i2;
            }
            ArrayList d2 = d(bVar);
            ArrayList d3 = d(bVar2);
            List typeParameters = bVar.getTypeParameters();
            List typeParameters2 = bVar2.getTypeParameters();
            if (typeParameters.size() != typeParameters2.size()) {
                for (int i7 = 0; i7 < d2.size(); i7++) {
                    if (!d.f3459a.a((C0774x) d2.get(i7), (C0774x) d3.get(i7))) {
                        return C1310n.c("Type parameter number mismatch");
                    }
                }
                return new C1310n(C1309m.CONFLICT, "Type parameter number mismatch");
            }
            L f = f(typeParameters, typeParameters2);
            int i8 = 0;
            while (i8 < typeParameters.size()) {
                V v = (V) typeParameters.get(i8);
                V v6 = (V) typeParameters2.get(i8);
                if (v == null) {
                    a(47);
                    throw null;
                } else if (v6 != null) {
                    List<C0774x> upperBounds = v.getUpperBounds();
                    ArrayList arrayList = new ArrayList(v6.getUpperBounds());
                    if (upperBounds.size() == arrayList.size()) {
                        for (C0774x xVar : upperBounds) {
                            ListIterator listIterator = arrayList.listIterator();
                            while (listIterator.hasNext()) {
                                if (b(xVar, (C0774x) listIterator.next(), f)) {
                                    listIterator.remove();
                                }
                            }
                        }
                        i8++;
                    }
                    return C1310n.c("Type parameter bounds mismatch");
                } else {
                    a(48);
                    throw null;
                }
            }
            for (int i10 = 0; i10 < d2.size(); i10++) {
                if (!b((C0774x) d2.get(i10), (C0774x) d3.get(i10), f)) {
                    return C1310n.c("Value parameter type mismatch");
                }
            }
            if ((bVar instanceof C0831v) && (bVar2 instanceof C0831v) && ((C0831v) bVar).isSuspend() != ((C0831v) bVar2).isSuspend()) {
                return new C1310n(C1309m.CONFLICT, "Incompatible suspendability");
            }
            if (z) {
                C0774x returnType = bVar.getReturnType();
                C0774x returnType2 = bVar2.getReturnType();
                if (!(returnType == null || returnType2 == null || (C0754c.k(returnType2) && C0754c.k(returnType)))) {
                    if (!C0756e.m(C0756e.f3442a, f, returnType2.x0(), returnType.x0())) {
                        return new C1310n(C1309m.CONFLICT, "Return type mismatch");
                    }
                }
            }
            C1310n nVar = C1310n.f5140c;
            if (nVar != null) {
                return nVar;
            }
            C1310n.a(0);
            throw null;
        } else {
            a(29);
            throw null;
        }
    }
}
