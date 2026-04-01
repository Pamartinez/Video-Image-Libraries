package He;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.jvm.internal.j;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B implements ParameterizedType, Type {
    public final Class d;
    public final Type e;
    public final Type[] f;

    public B(Class cls, Type type, ArrayList arrayList) {
        this.d = cls;
        this.e = type;
        this.f = (Type[]) arrayList.toArray(new Type[0]);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ParameterizedType)) {
            return false;
        }
        ParameterizedType parameterizedType = (ParameterizedType) obj;
        if (!j.a(this.d, parameterizedType.getRawType()) || !j.a(this.e, parameterizedType.getOwnerType()) || !Arrays.equals(this.f, parameterizedType.getActualTypeArguments())) {
            return false;
        }
        return true;
    }

    public final Type[] getActualTypeArguments() {
        return this.f;
    }

    public final Type getOwnerType() {
        return this.e;
    }

    public final Type getRawType() {
        return this.d;
    }

    public final String getTypeName() {
        StringBuilder sb2 = new StringBuilder();
        Class cls = this.d;
        Type type = this.e;
        if (type != null) {
            sb2.append(F.d(type));
            sb2.append("$");
            sb2.append(cls.getSimpleName());
        } else {
            sb2.append(F.d(cls));
        }
        Type[] typeArr = this.f;
        if (typeArr.length != 0) {
            C1192j.r0(typeArr, sb2, ArcCommonLog.TAG_COMMA, "<", ">", "...", A.d);
        }
        return sb2.toString();
    }

    public final int hashCode() {
        int i2;
        int hashCode = this.d.hashCode();
        Type type = this.e;
        if (type != null) {
            i2 = type.hashCode();
        } else {
            i2 = 0;
        }
        return Arrays.hashCode(this.f) ^ (hashCode ^ i2);
    }

    public final String toString() {
        return getTypeName();
    }
}
