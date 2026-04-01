package Ne;

import D0.f;
import Df.l;
import Ef.a;
import Ef.c;
import Ef.d;
import Gf.e;
import Gf.h;
import Gf.m;
import Hf.B;
import Hf.C0754c;
import Hf.C0774x;
import Hf.G;
import Hf.M;
import Hf.P;
import Hf.a0;
import Hf.c0;
import Hf.d0;
import If.k;
import Qe.C;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0822l;
import Qe.C0831v;
import Qe.C0833x;
import Qe.O;
import Re.g;
import Se.b;
import Te.I;
import Te.J;
import Te.z;
import com.samsung.android.ocr.MOCRLang;
import i.C0212a;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1196n;
import qf.C1235b;
import qf.C1236c;
import qf.C1238e;
import qf.C1240g;
import tf.C1301e;
import x2.C0338e;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class i {
    public static final C1240g e = C1240g.g("<built-ins module>");

    /* renamed from: a  reason: collision with root package name */
    public z f3547a;
    public final Gf.i b;

    /* renamed from: c  reason: collision with root package name */
    public final e f3548c;
    public final m d;

    /* JADX WARNING: type inference failed for: r1v2, types: [Gf.h, Gf.i] */
    public i(m mVar) {
        this.d = mVar;
        mVar.a(new f(this, 0));
        this.b = new h(mVar, new f(this, 1));
        this.f3548c = mVar.b(new g(this, 0));
    }

    public static boolean A(C0774x xVar, C1238e eVar) {
        if (xVar == null) {
            a(98);
            throw null;
        } else if (eVar != null) {
            return H(xVar.s0(), eVar);
        } else {
            a(99);
            throw null;
        }
    }

    public static boolean B(C0774x xVar, C1238e eVar) {
        if (eVar == null) {
            a(136);
            throw null;
        } else if (!A(xVar, eVar) || xVar.u0()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean C(C0831v vVar) {
        if (vVar.a().getAnnotations().g(p.m)) {
            return true;
        }
        if (!(vVar instanceof O)) {
            return false;
        }
        O o2 = (O) vVar;
        boolean G5 = o2.G();
        I getter = o2.getGetter();
        J setter = o2.getSetter();
        if (getter == null || !C(getter)) {
            return false;
        }
        if (!G5) {
            return true;
        }
        if (setter == null || !C(setter)) {
            return false;
        }
        return true;
    }

    public static boolean D(C0774x xVar, C1238e eVar) {
        if (xVar == null) {
            a(106);
            throw null;
        } else if (eVar == null) {
            a(107);
            throw null;
        } else if (xVar.u0() || !A(xVar, eVar)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean E(C0774x xVar) {
        if (xVar == null) {
            a(137);
            throw null;
        } else if (xVar == null) {
            a(139);
            throw null;
        } else if (!A(xVar, p.b) || a0.e(xVar)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean F(C0774x xVar) {
        if (xVar.u0()) {
            return false;
        }
        C0819i g = xVar.s0().g();
        if (!(g instanceof C0816f) || t((C0816f) g) == null) {
            return false;
        }
        return true;
    }

    public static boolean G(C0774x xVar) {
        if (D(xVar, p.f)) {
            return true;
        }
        return false;
    }

    public static boolean H(M m, C1238e eVar) {
        if (m == null) {
            a(102);
            throw null;
        } else if (eVar != null) {
            C0819i g = m.g();
            if (!(g instanceof C0816f) || !b((C0816f) g, eVar)) {
                return false;
            }
            return true;
        } else {
            a(103);
            throw null;
        }
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Incorrect type for immutable var: ssa=Qe.i, code=Qe.l, for r1v0, types: [Qe.i] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean I(Qe.C0822l r1) {
        /*
            if (r1 == 0) goto L_0x001c
        L_0x0002:
            if (r1 == 0) goto L_0x001a
            boolean r0 = r1 instanceof Qe.H
            if (r0 == 0) goto L_0x0015
            Qe.H r1 = (Qe.H) r1
            Te.B r1 = (Te.B) r1
            qf.c r1 = r1.f3743i
            qf.g r0 = Ne.q.k
            boolean r1 = r1.h(r0)
            return r1
        L_0x0015:
            Qe.l r1 = r1.g()
            goto L_0x0002
        L_0x001a:
            r1 = 0
            return r1
        L_0x001c:
            r1 = 10
            a(r1)
            r1 = 0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: Ne.i.I(Qe.i):boolean");
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        switch (i2) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 69:
            case 70:
            case 71:
            case 75:
            case 82:
            case 85:
            case 87:
            case 88:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i2) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 69:
            case 70:
            case 71:
            case 75:
            case 82:
            case 85:
            case 87:
            case 88:
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 73:
                objArr[0] = "module";
                break;
            case 2:
                objArr[0] = "computation";
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 69:
            case 70:
            case 71:
            case 75:
            case 82:
            case 85:
            case 87:
            case 88:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/builtins/KotlinBuiltIns";
                break;
            case 9:
            case 10:
            case 77:
            case 78:
            case 90:
            case 97:
            case 104:
            case 108:
            case 109:
            case 144:
            case 147:
            case 148:
            case 150:
            case 158:
            case 159:
            case MOCRLang.GURMUKHI:
            case MOCRLang.PUNJABI:
                objArr[0] = "descriptor";
                break;
            case 12:
            case 99:
            case 101:
            case 103:
            case 105:
            case 107:
            case 136:
                objArr[0] = "fqName";
                break;
            case 14:
                objArr[0] = "simpleName";
                break;
            case 16:
            case 17:
            case 54:
            case 89:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 98:
            case 100:
            case 106:
            case 110:
            case 111:
            case 112:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 145:
            case 146:
            case 149:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 163:
                objArr[0] = "type";
                break;
            case 47:
                objArr[0] = "classSimpleName";
                break;
            case 68:
                objArr[0] = "arrayType";
                break;
            case 72:
                objArr[0] = "notNullArrayType";
                break;
            case 74:
                objArr[0] = "primitiveType";
                break;
            case 76:
                objArr[0] = "kotlinType";
                break;
            case 79:
            case 83:
                objArr[0] = "projectionType";
                break;
            case 80:
            case 84:
            case 86:
                objArr[0] = "argument";
                break;
            case 81:
                objArr[0] = "annotations";
                break;
            case 102:
                objArr[0] = "typeConstructor";
                break;
            case 113:
                objArr[0] = "classDescriptor";
                break;
            case 162:
                objArr[0] = "declarationDescriptor";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        switch (i2) {
            case 3:
                objArr[1] = "getAdditionalClassPartsProvider";
                break;
            case 4:
                objArr[1] = "getPlatformDependentDeclarationFilter";
                break;
            case 5:
                objArr[1] = "getClassDescriptorFactories";
                break;
            case 6:
                objArr[1] = "getStorageManager";
                break;
            case 7:
                objArr[1] = "getBuiltInsModule";
                break;
            case 8:
                objArr[1] = "getBuiltInPackagesImportedByDefault";
                break;
            case 11:
                objArr[1] = "getBuiltInsPackageScope";
                break;
            case 13:
                objArr[1] = "getBuiltInClassByFqName";
                break;
            case 15:
                objArr[1] = "getBuiltInClassByName";
                break;
            case 18:
                objArr[1] = "getSuspendFunction";
                break;
            case 19:
                objArr[1] = "getKFunction";
                break;
            case 20:
                objArr[1] = "getKSuspendFunction";
                break;
            case 21:
                objArr[1] = "getKClass";
                break;
            case 22:
                objArr[1] = "getKType";
                break;
            case 23:
                objArr[1] = "getKCallable";
                break;
            case 24:
                objArr[1] = "getKProperty";
                break;
            case 25:
                objArr[1] = "getKProperty0";
                break;
            case 26:
                objArr[1] = "getKProperty1";
                break;
            case 27:
                objArr[1] = "getKProperty2";
                break;
            case 28:
                objArr[1] = "getKMutableProperty0";
                break;
            case 29:
                objArr[1] = "getKMutableProperty1";
                break;
            case 30:
                objArr[1] = "getKMutableProperty2";
                break;
            case 31:
                objArr[1] = "getIterator";
                break;
            case 32:
                objArr[1] = "getIterable";
                break;
            case 33:
                objArr[1] = "getMutableIterable";
                break;
            case 34:
                objArr[1] = "getMutableIterator";
                break;
            case 35:
                objArr[1] = "getCollection";
                break;
            case 36:
                objArr[1] = "getMutableCollection";
                break;
            case 37:
                objArr[1] = "getList";
                break;
            case 38:
                objArr[1] = "getMutableList";
                break;
            case 39:
                objArr[1] = "getSet";
                break;
            case 40:
                objArr[1] = "getMutableSet";
                break;
            case 41:
                objArr[1] = "getMap";
                break;
            case 42:
                objArr[1] = "getMutableMap";
                break;
            case 43:
                objArr[1] = "getMapEntry";
                break;
            case 44:
                objArr[1] = "getMutableMapEntry";
                break;
            case 45:
                objArr[1] = "getListIterator";
                break;
            case 46:
                objArr[1] = "getMutableListIterator";
                break;
            case 48:
                objArr[1] = "getBuiltInTypeByClassName";
                break;
            case 49:
                objArr[1] = "getNothingType";
                break;
            case 50:
                objArr[1] = "getNullableNothingType";
                break;
            case 51:
                objArr[1] = "getAnyType";
                break;
            case 52:
                objArr[1] = "getNullableAnyType";
                break;
            case 53:
                objArr[1] = "getDefaultBound";
                break;
            case 55:
                objArr[1] = "getPrimitiveKotlinType";
                break;
            case 56:
                objArr[1] = "getNumberType";
                break;
            case 57:
                objArr[1] = "getByteType";
                break;
            case 58:
                objArr[1] = "getShortType";
                break;
            case 59:
                objArr[1] = "getIntType";
                break;
            case 60:
                objArr[1] = "getLongType";
                break;
            case 61:
                objArr[1] = "getFloatType";
                break;
            case 62:
                objArr[1] = "getDoubleType";
                break;
            case 63:
                objArr[1] = "getCharType";
                break;
            case 64:
                objArr[1] = "getBooleanType";
                break;
            case 65:
                objArr[1] = "getUnitType";
                break;
            case 66:
                objArr[1] = "getStringType";
                break;
            case 67:
                objArr[1] = "getIterableType";
                break;
            case 69:
            case 70:
            case 71:
                objArr[1] = "getArrayElementType";
                break;
            case 75:
                objArr[1] = "getPrimitiveArrayKotlinType";
                break;
            case 82:
            case 85:
                objArr[1] = "getArrayType";
                break;
            case 87:
                objArr[1] = "getEnumType";
                break;
            case 88:
                objArr[1] = "getAnnotationType";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/builtins/KotlinBuiltIns";
                break;
        }
        switch (i2) {
            case 1:
                objArr[2] = "setBuiltInsModule";
                break;
            case 2:
                objArr[2] = "setPostponedBuiltinsModuleComputation";
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 69:
            case 70:
            case 71:
            case 75:
            case 82:
            case 85:
            case 87:
            case 88:
                break;
            case 9:
                objArr[2] = "isBuiltIn";
                break;
            case 10:
                objArr[2] = "isUnderKotlinPackage";
                break;
            case 12:
                objArr[2] = "getBuiltInClassByFqName";
                break;
            case 14:
                objArr[2] = "getBuiltInClassByName";
                break;
            case 16:
                objArr[2] = "getPrimitiveClassDescriptor";
                break;
            case 17:
                objArr[2] = "getPrimitiveArrayClassDescriptor";
                break;
            case 47:
                objArr[2] = "getBuiltInTypeByClassName";
                break;
            case 54:
                objArr[2] = "getPrimitiveKotlinType";
                break;
            case 68:
                objArr[2] = "getArrayElementType";
                break;
            case 72:
            case 73:
                objArr[2] = "getElementTypeForUnsignedArray";
                break;
            case 74:
                objArr[2] = "getPrimitiveArrayKotlinType";
                break;
            case 76:
                objArr[2] = "getPrimitiveArrayKotlinTypeByPrimitiveKotlinType";
                break;
            case 77:
            case 94:
                objArr[2] = "getPrimitiveType";
                break;
            case 78:
                objArr[2] = "getPrimitiveArrayType";
                break;
            case 79:
            case 80:
            case 81:
            case 83:
            case 84:
                objArr[2] = "getArrayType";
                break;
            case 86:
                objArr[2] = "getEnumType";
                break;
            case 89:
                objArr[2] = "isArray";
                break;
            case 90:
            case 91:
                objArr[2] = "isArrayOrPrimitiveArray";
                break;
            case 92:
                objArr[2] = "isPrimitiveArray";
                break;
            case 93:
                objArr[2] = "getPrimitiveArrayElementType";
                break;
            case 95:
                objArr[2] = "isPrimitiveType";
                break;
            case 96:
                objArr[2] = "isPrimitiveTypeOrNullablePrimitiveType";
                break;
            case 97:
                objArr[2] = "isPrimitiveClass";
                break;
            case 98:
            case 99:
            case 100:
            case 101:
                objArr[2] = "isConstructedFromGivenClass";
                break;
            case 102:
            case 103:
                objArr[2] = "isTypeConstructorForGivenClass";
                break;
            case 104:
            case 105:
                objArr[2] = "classFqNameEquals";
                break;
            case 106:
            case 107:
                objArr[2] = "isNotNullConstructedFromGivenClass";
                break;
            case 108:
                objArr[2] = "isSpecialClassWithNoSupertypes";
                break;
            case 109:
            case 110:
                objArr[2] = "isAny";
                break;
            case 111:
            case 113:
                objArr[2] = "isBoolean";
                break;
            case 112:
                objArr[2] = "isBooleanOrNullableBoolean";
                break;
            case 114:
                objArr[2] = "isNumber";
                break;
            case 115:
                objArr[2] = "isChar";
                break;
            case 116:
                objArr[2] = "isCharOrNullableChar";
                break;
            case 117:
                objArr[2] = "isInt";
                break;
            case 118:
                objArr[2] = "isByte";
                break;
            case 119:
                objArr[2] = "isLong";
                break;
            case 120:
                objArr[2] = "isLongOrNullableLong";
                break;
            case 121:
                objArr[2] = "isShort";
                break;
            case 122:
                objArr[2] = "isFloat";
                break;
            case 123:
                objArr[2] = "isFloatOrNullableFloat";
                break;
            case 124:
                objArr[2] = "isDouble";
                break;
            case 125:
                objArr[2] = "isUByte";
                break;
            case 126:
                objArr[2] = "isUShort";
                break;
            case 127:
                objArr[2] = "isUInt";
                break;
            case 128:
                objArr[2] = "isULong";
                break;
            case 129:
                objArr[2] = "isUByteArray";
                break;
            case 130:
                objArr[2] = "isUShortArray";
                break;
            case 131:
                objArr[2] = "isUIntArray";
                break;
            case 132:
                objArr[2] = "isULongArray";
                break;
            case 133:
                objArr[2] = "isUnsignedArrayType";
                break;
            case 134:
                objArr[2] = "isDoubleOrNullableDouble";
                break;
            case 135:
            case 136:
                objArr[2] = "isConstructedFromGivenClassAndNotNullable";
                break;
            case 137:
                objArr[2] = "isNothing";
                break;
            case 138:
                objArr[2] = "isNullableNothing";
                break;
            case 139:
                objArr[2] = "isNothingOrNullableNothing";
                break;
            case 140:
                objArr[2] = "isAnyOrNullableAny";
                break;
            case 141:
                objArr[2] = "isNullableAny";
                break;
            case 142:
                objArr[2] = "isDefaultBound";
                break;
            case 143:
                objArr[2] = "isUnit";
                break;
            case 144:
                objArr[2] = "mayReturnNonUnitValue";
                break;
            case 145:
                objArr[2] = "isUnitOrNullableUnit";
                break;
            case 146:
                objArr[2] = "isBooleanOrSubtype";
                break;
            case 147:
                objArr[2] = "isMemberOfAny";
                break;
            case 148:
            case 149:
                objArr[2] = "isEnum";
                break;
            case 150:
            case 151:
                objArr[2] = "isComparable";
                break;
            case 152:
                objArr[2] = "isCollectionOrNullableCollection";
                break;
            case 153:
                objArr[2] = "isListOrNullableList";
                break;
            case 154:
                objArr[2] = "isSetOrNullableSet";
                break;
            case 155:
                objArr[2] = "isMapOrNullableMap";
                break;
            case 156:
                objArr[2] = "isIterableOrNullableIterable";
                break;
            case 157:
                objArr[2] = "isThrowableOrNullableThrowable";
                break;
            case 158:
                objArr[2] = "isThrowable";
                break;
            case 159:
                objArr[2] = "isKClass";
                break;
            case MOCRLang.GURMUKHI:
                objArr[2] = "isNonPrimitiveArray";
                break;
            case MOCRLang.PUNJABI:
                objArr[2] = "isCloneable";
                break;
            case 162:
                objArr[2] = "isDeprecated";
                break;
            case 163:
                objArr[2] = "isNotNullOrNullableFunctionSupertype";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 69:
            case 70:
            case 71:
            case 75:
            case 82:
            case 85:
            case 87:
            case 88:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public static boolean b(C0816f fVar, C1238e eVar) {
        if (fVar == null) {
            a(104);
            throw null;
        } else if (eVar == null) {
            a(105);
            throw null;
        } else if (!fVar.getName().equals(eVar.f()) || !eVar.equals(C1301e.g(fVar))) {
            return false;
        } else {
            return true;
        }
    }

    public static l r(C0819i iVar) {
        if (iVar == null) {
            a(78);
            throw null;
        } else if (p.b0.contains(iVar.getName())) {
            return (l) p.d0.get(C1301e.g(iVar));
        } else {
            return null;
        }
    }

    public static l t(C0816f fVar) {
        if (p.a0.contains(fVar.getName())) {
            return (l) p.f3567c0.get(C1301e.g(fVar));
        }
        return null;
    }

    public static boolean x(C0774x xVar) {
        if (xVar != null) {
            return A(xVar, p.f3565a);
        }
        a(140);
        throw null;
    }

    public static boolean y(C0774x xVar) {
        if (xVar != null) {
            return A(xVar, p.g);
        }
        a(89);
        throw null;
    }

    public static boolean z(C0822l lVar) {
        if (lVar == null) {
            a(9);
            throw null;
        } else if (C1301e.i(lVar, d.class, false) != null) {
            return true;
        } else {
            return false;
        }
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [me.f, java.lang.Object] */
    public final void c() {
        C1240g gVar = e;
        j.e(gVar, "moduleName");
        m mVar = this.d;
        z zVar = new z(gVar, mVar, this, 48);
        this.f3547a = zVar;
        c.f3544a.getClass();
        z zVar2 = this.f3547a;
        Iterable l = l();
        Se.d p6 = p();
        b d2 = d();
        c cVar = (c) ((c) b.b.getValue());
        cVar.getClass();
        j.e(zVar2, "builtInsModule");
        j.e(l, "classDescriptorFactories");
        j.e(p6, "platformDependentDeclarationFilter");
        j.e(d2, "additionalClassPartsProvider");
        Set<C1236c> set = q.q;
        Ef.b bVar = new Ef.b(1, cVar.b, 0);
        j.e(set, "packageFqNames");
        ArrayList arrayList = new ArrayList(C1196n.w0(set, 10));
        for (C1236c cVar2 : set) {
            a.m.getClass();
            String a7 = a.a(cVar2);
            InputStream inputStream = (InputStream) bVar.invoke(a7);
            if (inputStream != null) {
                arrayList.add(com.samsung.context.sdk.samsunganalytics.internal.sender.c.o(cVar2, mVar, zVar2, inputStream));
            } else {
                throw new IllegalStateException(C0212a.l("Resource not found in classpath: ", a7));
            }
        }
        Qe.J j2 = new Qe.J(arrayList);
        f fVar = new f(mVar, (C) zVar2);
        G0.c cVar3 = new G0.c(2, (Object) j2);
        a aVar = a.m;
        l lVar = new l(mVar, zVar2, cVar3, new D0.e((C) zVar2, fVar, (Cf.a) aVar), j2, l, fVar, d2, p6, aVar.f3325a, (k) null, new C0338e(mVar), 851968);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((d) it.next()).F0(lVar);
        }
        zVar.l = j2;
        z zVar3 = this.f3547a;
        zVar3.getClass();
        zVar3.k = new G0.e((Object) C1192j.x0(new z[]{zVar3}));
    }

    public b d() {
        return Se.a.b;
    }

    public final B e() {
        B i2 = j("Any").i();
        if (i2 != null) {
            return i2;
        }
        a(51);
        throw null;
    }

    public final C0774x f(C0774x xVar) {
        C c5;
        C1235b f;
        C1235b bVar;
        C0816f d2;
        B b5 = null;
        if (xVar == null) {
            a(68);
            throw null;
        } else if (!y(xVar)) {
            c0 g = a0.g(xVar, false);
            C0774x xVar2 = (C0774x) ((h) this.b.invoke()).b.get(g);
            if (xVar2 != null) {
                return xVar2;
            }
            int i2 = C1301e.f5137a;
            C0819i g3 = g.s0().g();
            if (g3 == null) {
                c5 = null;
            } else {
                c5 = C1301e.e(g3);
            }
            if (c5 != null) {
                C0819i g10 = g.s0().g();
                if (g10 != null) {
                    Set set = u.f3582a;
                    C1240g name = g10.getName();
                    j.e(name, "name");
                    if (!(!u.d.contains(name) || (f = C1353d.f(g10)) == null || (bVar = (C1235b) u.b.get(f)) == null || (d2 = C0833x.d(c5, bVar)) == null)) {
                        b5 = d2.i();
                    }
                }
                if (b5 != null) {
                    return b5;
                }
            }
            throw new IllegalStateException("not array: " + xVar);
        } else if (xVar.e0().size() == 1) {
            C0774x b8 = ((P) xVar.e0().get(0)).b();
            if (b8 != null) {
                return b8;
            }
            a(69);
            throw null;
        } else {
            throw new IllegalStateException();
        }
    }

    public final B g(d0 d0Var, C0774x xVar, Re.h hVar) {
        if (d0Var == null) {
            a(79);
            throw null;
        } else if (xVar != null) {
            return C0754c.t(C0754c.C(hVar), j("Array"), Collections.singletonList(new G(xVar, d0Var)));
        } else {
            a(80);
            throw null;
        }
    }

    public final B h(d0 d0Var, c0 c0Var) {
        if (d0Var == null) {
            a(83);
            throw null;
        } else if (c0Var != null) {
            return g(d0Var, c0Var, g.f3692a);
        } else {
            a(84);
            throw null;
        }
    }

    public final C0816f i(C1236c cVar) {
        if (cVar != null) {
            C0816f j2 = C0833x.j(k(), cVar, Ye.c.FROM_BUILTINS);
            if (j2 != null) {
                return j2;
            }
            a(13);
            throw null;
        }
        a(12);
        throw null;
    }

    public final C0816f j(String str) {
        if (str != null) {
            return (C0816f) this.f3548c.invoke(C1240g.e(str));
        }
        a(14);
        throw null;
    }

    public final z k() {
        this.f3547a.getClass();
        z zVar = this.f3547a;
        if (zVar != null) {
            return zVar;
        }
        a(7);
        throw null;
    }

    public Iterable l() {
        List singletonList = Collections.singletonList(new Oe.a(this.d, k()));
        if (singletonList != null) {
            return singletonList;
        }
        a(5);
        throw null;
    }

    public final B m() {
        B o2 = o();
        if (o2 != null) {
            return o2;
        }
        a(53);
        throw null;
    }

    public final B n() {
        B i2 = j("Nothing").i();
        if (i2 != null) {
            return i2;
        }
        a(49);
        throw null;
    }

    public final B o() {
        B B02 = e().y0(true);
        if (B02 != null) {
            return B02;
        }
        a(52);
        throw null;
    }

    public Se.d p() {
        return Se.a.d;
    }

    public final B q(l lVar) {
        if (lVar != null) {
            B b5 = (B) ((h) this.b.invoke()).f3546a.get(lVar);
            if (b5 != null) {
                return b5;
            }
            a(75);
            throw null;
        }
        a(74);
        throw null;
    }

    public final B s(l lVar) {
        if (lVar != null) {
            B i2 = j(lVar.f().b()).i();
            if (i2 != null) {
                return i2;
            }
            a(55);
            throw null;
        }
        a(54);
        throw null;
    }

    public final B u() {
        B i2 = j("String").i();
        if (i2 != null) {
            return i2;
        }
        a(66);
        throw null;
    }

    public final C0816f v(int i2) {
        C1236c cVar = q.f;
        C0816f i7 = i(cVar.c(C1240g.e(Oe.k.f3621c.b + i2)));
        if (i7 != null) {
            return i7;
        }
        a(18);
        throw null;
    }

    public final B w() {
        B i2 = j("Unit").i();
        if (i2 != null) {
            return i2;
        }
        a(65);
        throw null;
    }
}
