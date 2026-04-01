package com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1;

import com.samsung.android.sdk.bixby2.state.StateHandler;
import com.samsung.android.sdk.moneta.basicdomain.entity.PetType;
import gg.a;
import i.C0212a;
import ig.f;
import java.util.List;
import jg.b;
import kg.C1122c;
import kg.C1141w;
import kg.H;
import kg.Q;
import kg.a0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;
import og.k;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\f\b\b\u0018\u0000 02\u00020\u0001:\u000210B3\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\n\u0010\u000bBI\b\u0010\u0012\u0006\u0010\f\u001a\u00020\b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\n\u0010\u000fJ'\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001aJ\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ>\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00022\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\"\u0010\u001aJ\u0010\u0010#\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b#\u0010$J\u001a\u0010'\u001a\u00020&2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b'\u0010(R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b*\u0010\u001aR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010+\u001a\u0004\b,\u0010\u001cR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010)\u001a\u0004\b-\u0010\u001aR\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010.\u001a\u0004\b/\u0010\u001f¨\u00062"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyPetWrapper;", "", "", "name", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/PetType;", "petType", "source", "", "", "groupIds", "<init>", "(Ljava/lang/String;Lcom/samsung/android/sdk/moneta/basicdomain/entity/PetType;Ljava/lang/String;Ljava/util/List;)V", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILjava/lang/String;Lcom/samsung/android/sdk/moneta/basicdomain/entity/PetType;Ljava/lang/String;Ljava/util/List;Lkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyPetWrapper;Ljg/b;Lig/f;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "()Lcom/samsung/android/sdk/moneta/basicdomain/entity/PetType;", "component3", "component4", "()Ljava/util/List;", "copy", "(Ljava/lang/String;Lcom/samsung/android/sdk/moneta/basicdomain/entity/PetType;Ljava/lang/String;Ljava/util/List;)Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyPetWrapper;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getName", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/PetType;", "getPetType", "getSource", "Ljava/util/List;", "getGroupIds", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MyPetWrapper {
    /* access modifiers changed from: private */
    public static final a[] $childSerializers;
    public static final Companion Companion = new Companion((e) null);
    private final List<Integer> groupIds;
    private final String name;
    private final PetType petType;
    private final String source;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyPetWrapper$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyPetWrapper;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return MyPetWrapper$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    static {
        PetType[] values = PetType.values();
        j.e(values, StateHandler.VALUES);
        $childSerializers = new a[]{null, new C1141w("com.samsung.android.sdk.moneta.basicdomain.entity.PetType", values), null, new C1122c(H.f4669a)};
    }

    public /* synthetic */ MyPetWrapper(int i2, String str, PetType petType2, String str2, List list, a0 a0Var) {
        if (2 == (i2 & 2)) {
            if ((i2 & 1) == 0) {
                this.name = "";
            } else {
                this.name = str;
            }
            this.petType = petType2;
            if ((i2 & 4) == 0) {
                this.source = "";
            } else {
                this.source = str2;
            }
            if ((i2 & 8) == 0) {
                this.groupIds = C1202t.d;
            } else {
                this.groupIds = list;
            }
        } else {
            Q.f(i2, 2, MyPetWrapper$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ MyPetWrapper copy$default(MyPetWrapper myPetWrapper, String str, PetType petType2, String str2, List<Integer> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = myPetWrapper.name;
        }
        if ((i2 & 2) != 0) {
            petType2 = myPetWrapper.petType;
        }
        if ((i2 & 4) != 0) {
            str2 = myPetWrapper.source;
        }
        if ((i2 & 8) != 0) {
            list = myPetWrapper.groupIds;
        }
        return myPetWrapper.copy(str, petType2, str2, list);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(MyPetWrapper myPetWrapper, b bVar, f fVar) {
        a[] aVarArr = $childSerializers;
        if (bVar.d(fVar) || !j.a(myPetWrapper.name, "")) {
            ((k) bVar).u(fVar, 0, myPetWrapper.name);
        }
        k kVar = (k) bVar;
        kVar.t(fVar, 1, aVarArr[1], myPetWrapper.petType);
        if (bVar.d(fVar) || !j.a(myPetWrapper.source, "")) {
            kVar.u(fVar, 2, myPetWrapper.source);
        }
        if (bVar.d(fVar) || !j.a(myPetWrapper.groupIds, C1202t.d)) {
            kVar.t(fVar, 3, aVarArr[3], myPetWrapper.groupIds);
        }
    }

    public final String component1() {
        return this.name;
    }

    public final PetType component2() {
        return this.petType;
    }

    public final String component3() {
        return this.source;
    }

    public final List<Integer> component4() {
        return this.groupIds;
    }

    public final MyPetWrapper copy(String str, PetType petType2, String str2, List<Integer> list) {
        j.e(str, "name");
        j.e(petType2, "petType");
        j.e(str2, "source");
        j.e(list, "groupIds");
        return new MyPetWrapper(str, petType2, str2, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyPetWrapper)) {
            return false;
        }
        MyPetWrapper myPetWrapper = (MyPetWrapper) obj;
        if (j.a(this.name, myPetWrapper.name) && this.petType == myPetWrapper.petType && j.a(this.source, myPetWrapper.source) && j.a(this.groupIds, myPetWrapper.groupIds)) {
            return true;
        }
        return false;
    }

    public final List<Integer> getGroupIds() {
        return this.groupIds;
    }

    public final String getName() {
        return this.name;
    }

    public final PetType getPetType() {
        return this.petType;
    }

    public final String getSource() {
        return this.source;
    }

    public int hashCode() {
        return this.groupIds.hashCode() + C0212a.d((this.petType.hashCode() + (this.name.hashCode() * 31)) * 31, 31, this.source);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("MyPetWrapper(name=");
        sb2.append(this.name);
        sb2.append(", petType=");
        sb2.append(this.petType);
        sb2.append(", source=");
        sb2.append(this.source);
        sb2.append(", groupIds=");
        return C0212a.r(sb2, this.groupIds, ')');
    }

    public MyPetWrapper(String str, PetType petType2, String str2, List<Integer> list) {
        j.e(str, "name");
        j.e(petType2, "petType");
        j.e(str2, "source");
        j.e(list, "groupIds");
        this.name = str;
        this.petType = petType2;
        this.source = str2;
        this.groupIds = list;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MyPetWrapper(String str, PetType petType2, String str2, List list, int i2, e eVar) {
        this((i2 & 1) != 0 ? "" : str, petType2, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? C1202t.d : list);
    }
}
