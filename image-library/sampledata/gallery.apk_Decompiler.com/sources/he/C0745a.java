package He;

import com.adobe.internal.xmp.XMPConst;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import kotlin.jvm.internal.j;

/* renamed from: He.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0745a implements GenericArrayType, Type {
    public final Type d;

    public C0745a(Type type) {
        j.e(type, "elementType");
        this.d = type;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType)) {
            return false;
        }
        if (j.a(this.d, ((GenericArrayType) obj).getGenericComponentType())) {
            return true;
        }
        return false;
    }

    public final Type getGenericComponentType() {
        return this.d;
    }

    public final String getTypeName() {
        return F.d(this.d) + XMPConst.ARRAY_ITEM_NAME;
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    public final String toString() {
        return getTypeName();
    }
}
