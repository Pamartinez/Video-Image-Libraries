package He;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G implements WildcardType, Type {
    public static final G f = new G((Type) null, (Type) null);
    public final Type d;
    public final Type e;

    public G(Type type, Type type2) {
        this.d = type;
        this.e = type2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof WildcardType)) {
            return false;
        }
        WildcardType wildcardType = (WildcardType) obj;
        if (!Arrays.equals(getUpperBounds(), wildcardType.getUpperBounds()) || !Arrays.equals(getLowerBounds(), wildcardType.getLowerBounds())) {
            return false;
        }
        return true;
    }

    public final Type[] getLowerBounds() {
        Type type = this.e;
        if (type == null) {
            return new Type[0];
        }
        return new Type[]{type};
    }

    public final String getTypeName() {
        Type type = this.e;
        if (type != null) {
            return "? super " + F.d(type);
        }
        Type type2 = this.d;
        if (type2 == null || j.a(type2, Object.class)) {
            return "?";
        }
        return "? extends " + F.d(type2);
    }

    public final Type[] getUpperBounds() {
        Type type = this.d;
        if (type == null) {
            type = Object.class;
        }
        return new Type[]{type};
    }

    public final int hashCode() {
        return Arrays.hashCode(getLowerBounds()) ^ Arrays.hashCode(getUpperBounds());
    }

    public final String toString() {
        return getTypeName();
    }
}
