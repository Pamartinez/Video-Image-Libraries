package kg;

import L1.d;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import gg.a;
import ig.f;
import jg.c;
import kotlin.jvm.internal.j;
import me.m;

/* renamed from: kg.w  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1141w implements a {

    /* renamed from: a  reason: collision with root package name */
    public final Enum[] f4721a;
    public C1139u b;

    /* renamed from: c  reason: collision with root package name */
    public final m f4722c;

    public C1141w(String str, Enum[] enumArr) {
        j.e(enumArr, StateHandler.VALUES);
        this.f4721a = enumArr;
        this.f4722c = d.q(new C1140v(0, this, str));
    }

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        int c5 = cVar.c(getDescriptor());
        Enum[] enumArr = this.f4721a;
        if (c5 >= 0 && c5 < enumArr.length) {
            return enumArr[c5];
        }
        throw new IllegalArgumentException(c5 + " is not among valid " + getDescriptor().i() + " enum values, values size is " + enumArr.length);
    }

    public final f getDescriptor() {
        return (f) this.f4722c.getValue();
    }

    public final String toString() {
        return "kotlinx.serialization.internal.EnumSerializer<" + getDescriptor().i() + '>';
    }
}
