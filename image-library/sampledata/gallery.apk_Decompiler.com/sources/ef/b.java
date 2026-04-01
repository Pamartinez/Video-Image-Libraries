package Ef;

import Df.H;
import Ff.h;
import Ff.k;
import He.C0750f;
import If.e;
import If.f;
import Kf.d;
import df.C0951n;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import lf.Q;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b extends g implements Ae.b {
    public final /* synthetic */ int d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(int i2, Object obj, int i7) {
        super(i2, obj);
        this.d = i7;
    }

    public final String getName() {
        switch (this.d) {
            case 0:
                return "loadResource";
            case 1:
                return "simpleType";
            case 2:
                return "getValueClassPropertyType";
            case 3:
                return "<init>";
            case 4:
                return "prepareType";
            case 5:
                return "searchMethodsByNameWithoutBuiltinMagic";
            default:
                return "searchMethodsInSupertypesWithoutBuiltinMagic";
        }
    }

    public final C0750f getOwner() {
        switch (this.d) {
            case 0:
                return v.f4727a.b(e.class);
            case 1:
                return v.f4727a.b(i.class);
            case 2:
                return v.f4727a.b(k.class);
            case 3:
                return v.f4727a.b(h.class);
            case 4:
                return v.f4727a.b(e.class);
            case 5:
                return v.f4727a.b(C0951n.class);
            default:
                return v.f4727a.b(C0951n.class);
        }
    }

    public final String getSignature() {
        switch (this.d) {
            case 0:
                return "loadResource(Ljava/lang/String;)Ljava/io/InputStream;";
            case 1:
                return "computeValueClassRepresentation$simpleType(Lorg/jetbrains/kotlin/serialization/deserialization/TypeDeserializer;Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;)Lorg/jetbrains/kotlin/types/SimpleType;";
            case 2:
                return "getValueClassPropertyType(Lorg/jetbrains/kotlin/name/Name;)Lorg/jetbrains/kotlin/types/SimpleType;";
            case 3:
                return "<init>(Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedClassDescriptor;Lorg/jetbrains/kotlin/types/checker/KotlinTypeRefiner;)V";
            case 4:
                return "prepareType(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)Lorg/jetbrains/kotlin/types/UnwrappedType;";
            case 5:
                return "searchMethodsByNameWithoutBuiltinMagic(Lorg/jetbrains/kotlin/name/Name;)Ljava/util/Collection;";
            default:
                return "searchMethodsInSupertypesWithoutBuiltinMagic(Lorg/jetbrains/kotlin/name/Name;)Ljava/util/Collection;";
        }
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                String str = (String) obj;
                j.e(str, "p0");
                ((e) this.receiver).getClass();
                return e.a(str);
            case 1:
                Q q = (Q) obj;
                j.e(q, "p0");
                return ((H) this.receiver).d(q, true);
            case 2:
                C1240g gVar = (C1240g) obj;
                j.e(gVar, "p0");
                return ((k) this.receiver).p0(gVar);
            case 3:
                f fVar = (f) obj;
                j.e(fVar, "p0");
                return new h((k) this.receiver, fVar);
            case 4:
                d dVar = (d) obj;
                j.e(dVar, "p0");
                return ((e) this.receiver).a(dVar);
            case 5:
                C1240g gVar2 = (C1240g) obj;
                j.e(gVar2, "p0");
                return ((C0951n) this.receiver).N(gVar2);
            default:
                C1240g gVar3 = (C1240g) obj;
                j.e(gVar3, "p0");
                return ((C0951n) this.receiver).O(gVar3);
        }
    }
}
