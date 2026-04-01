package com.samsung.android.sdk.moneta.event.entity;

import gg.a;
import i.C0212a;
import ig.f;
import java.util.List;
import jg.b;
import kg.C1122c;
import kg.Q;
import kg.a0;
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;
import og.k;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\f\b\b\u0018\u0000 02\u00020\u0001:\u000210B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0004\b\t\u0010\nBS\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\t\u0010\u000fJ'\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001aJ\u0010\u0010\u001d\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001aJ\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJH\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007HÆ\u0001¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\"\u0010\u001aJ\u0010\u0010#\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b#\u0010$J\u001a\u0010'\u001a\u00020&2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b'\u0010(R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b*\u0010\u001aR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010)\u001a\u0004\b+\u0010\u001aR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010)\u001a\u0004\b,\u0010\u001aR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010)\u001a\u0004\b-\u0010\u001aR\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010.\u001a\u0004\b/\u0010\u001f¨\u00062"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/What;", "", "", "title", "sourcePackage", "sourceUri", "extras", "", "tags", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkg/a0;)V", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/event/entity/What;Ljg/b;Lig/f;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "()Ljava/util/List;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Lcom/samsung/android/sdk/moneta/event/entity/What;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getTitle", "getSourcePackage", "getSourceUri", "getExtras", "Ljava/util/List;", "getTags", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class What {
    /* access modifiers changed from: private */
    public static final a[] $childSerializers = {null, null, null, null, new C1122c(e0.f4693a)};
    public static final Companion Companion = new Companion((e) null);
    private final String extras;
    private final String sourcePackage;
    private final String sourceUri;
    private final List<String> tags;
    private final String title;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/What$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/event/entity/What;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return What$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    public /* synthetic */ What(int i2, String str, String str2, String str3, String str4, List list, a0 a0Var) {
        if (7 == (i2 & 7)) {
            this.title = str;
            this.sourcePackage = str2;
            this.sourceUri = str3;
            if ((i2 & 8) == 0) {
                this.extras = "";
            } else {
                this.extras = str4;
            }
            if ((i2 & 16) == 0) {
                this.tags = C1202t.d;
            } else {
                this.tags = list;
            }
        } else {
            Q.f(i2, 7, What$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ What copy$default(What what, String str, String str2, String str3, String str4, List<String> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = what.title;
        }
        if ((i2 & 2) != 0) {
            str2 = what.sourcePackage;
        }
        if ((i2 & 4) != 0) {
            str3 = what.sourceUri;
        }
        if ((i2 & 8) != 0) {
            str4 = what.extras;
        }
        if ((i2 & 16) != 0) {
            list = what.tags;
        }
        String str5 = str4;
        List<String> list2 = list;
        return what.copy(str, str2, str3, str5, list2);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(What what, b bVar, f fVar) {
        a[] aVarArr = $childSerializers;
        k kVar = (k) bVar;
        kVar.u(fVar, 0, what.title);
        kVar.u(fVar, 1, what.sourcePackage);
        kVar.u(fVar, 2, what.sourceUri);
        if (kVar.d(fVar) || !j.a(what.extras, "")) {
            kVar.u(fVar, 3, what.extras);
        }
        if (kVar.d(fVar) || !j.a(what.tags, C1202t.d)) {
            kVar.t(fVar, 4, aVarArr[4], what.tags);
        }
    }

    public final String component1() {
        return this.title;
    }

    public final String component2() {
        return this.sourcePackage;
    }

    public final String component3() {
        return this.sourceUri;
    }

    public final String component4() {
        return this.extras;
    }

    public final List<String> component5() {
        return this.tags;
    }

    public final What copy(String str, String str2, String str3, String str4, List<String> list) {
        j.e(str, "title");
        j.e(str2, "sourcePackage");
        j.e(str3, "sourceUri");
        j.e(str4, "extras");
        j.e(list, "tags");
        return new What(str, str2, str3, str4, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof What)) {
            return false;
        }
        What what = (What) obj;
        if (j.a(this.title, what.title) && j.a(this.sourcePackage, what.sourcePackage) && j.a(this.sourceUri, what.sourceUri) && j.a(this.extras, what.extras) && j.a(this.tags, what.tags)) {
            return true;
        }
        return false;
    }

    public final String getExtras() {
        return this.extras;
    }

    public final String getSourcePackage() {
        return this.sourcePackage;
    }

    public final String getSourceUri() {
        return this.sourceUri;
    }

    public final List<String> getTags() {
        return this.tags;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.tags.hashCode() + C0212a.d(C0212a.d(C0212a.d(this.title.hashCode() * 31, 31, this.sourcePackage), 31, this.sourceUri), 31, this.extras);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("What(title=");
        sb2.append(this.title);
        sb2.append(", sourcePackage=");
        sb2.append(this.sourcePackage);
        sb2.append(", sourceUri=");
        sb2.append(this.sourceUri);
        sb2.append(", extras=");
        sb2.append(this.extras);
        sb2.append(", tags=");
        return C0212a.r(sb2, this.tags, ')');
    }

    public What(String str, String str2, String str3, String str4, List<String> list) {
        j.e(str, "title");
        j.e(str2, "sourcePackage");
        j.e(str3, "sourceUri");
        j.e(str4, "extras");
        j.e(list, "tags");
        this.title = str;
        this.sourcePackage = str2;
        this.sourceUri = str3;
        this.extras = str4;
        this.tags = list;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ What(String str, String str2, String str3, String str4, List list, int i2, e eVar) {
        this(str, str2, str3, (i2 & 8) != 0 ? "" : str4, (i2 & 16) != 0 ? C1202t.d : list);
    }
}
