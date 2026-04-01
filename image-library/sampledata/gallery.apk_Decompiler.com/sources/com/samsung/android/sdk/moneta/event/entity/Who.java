package com.samsung.android.sdk.moneta.event.entity;

import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper;
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

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\b\b\u0018\u0000 K2\u00020\u0001:\u0002LKBy\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013B\u0001\b\u0010\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0010\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u0012\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001aJ\u0010\u0010\u001d\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001aJ\u0010\u0010\u001e\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001aJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001aJ\u0010\u0010 \u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b \u0010\u001aJ\u0010\u0010!\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b!\u0010\u001aJ\u0010\u0010\"\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b$\u0010\u001aJ\u0010\u0010%\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b%\u0010\u001aJ\u0010\u0010&\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b&\u0010#J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010HÆ\u0003¢\u0006\u0004\b'\u0010(J\u0001\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010HÆ\u0001¢\u0006\u0004\b)\u0010*J\u0010\u0010+\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b+\u0010\u001aJ\u0010\u0010,\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b,\u0010-J\u001a\u0010/\u001a\u00020\u000b2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b/\u00100J'\u00109\u001a\u0002062\u0006\u00101\u001a\u00020\u00002\u0006\u00103\u001a\u0002022\u0006\u00105\u001a\u000204H\u0001¢\u0006\u0004\b7\u00108R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010:\u001a\u0004\b;\u0010\u001aR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010:\u001a\u0004\b<\u0010\u001aR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010:\u001a\u0004\b=\u0010\u001aR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010:\u001a\u0004\b>\u0010\u001aR\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010:\u001a\u0004\b?\u0010\u001aR\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010:\u001a\u0004\b@\u0010\u001aR\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010:\u001a\u0004\bA\u0010\u001aR\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010:\u001a\u0004\bB\u0010\u001aR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010C\u001a\u0004\b\f\u0010#R\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010:\u001a\u0004\bD\u0010\u001aR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010:\u001a\u0004\bE\u0010\u001aR\u0017\u0010\u000f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\u000f\u0010C\u001a\u0004\bF\u0010#R(\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00108\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010G\u001a\u0004\bH\u0010(\"\u0004\bI\u0010J¨\u0006M"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/Who;", "", "", "name", "phoneNumber", "contactId", "email", "groupName", "nickName", "snsName", "relation", "", "isContributor", "sourcePackage", "sourceUri", "augmented", "", "tags", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;ZLjava/util/List;)V", "", "seen0", "Lkg/a0;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;ZLjava/util/List;Lkg/a0;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "()Z", "component10", "component11", "component12", "component13", "()Ljava/util/List;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;ZLjava/util/List;)Lcom/samsung/android/sdk/moneta/event/entity/Who;", "toString", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "self", "Ljg/b;", "output", "Lig/f;", "serialDesc", "Lme/x;", "write$Self$pde_sdk_1_0_40_release", "(Lcom/samsung/android/sdk/moneta/event/entity/Who;Ljg/b;Lig/f;)V", "write$Self", "Ljava/lang/String;", "getName", "getPhoneNumber", "getContactId", "getEmail", "getGroupName", "getNickName", "getSnsName", "getRelation", "Z", "getSourcePackage", "getSourceUri", "getAugmented", "Ljava/util/List;", "getTags", "setTags", "(Ljava/util/List;)V", "Companion", "$serializer", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Who {
    /* access modifiers changed from: private */
    public static final a[] $childSerializers = {null, null, null, null, null, null, null, null, null, null, null, null, new C1122c(e0.f4693a)};
    public static final Companion Companion = new Companion((e) null);
    private final boolean augmented;
    private final String contactId;
    private final String email;
    private final String groupName;
    private final boolean isContributor;
    private final String name;
    private final String nickName;
    private final String phoneNumber;
    private final String relation;
    private final String snsName;
    private final String sourcePackage;
    private final String sourceUri;
    private List<String> tags;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/Who$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/event/entity/Who;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        public final a serializer() {
            return Who$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    public /* synthetic */ Who(int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, String str10, boolean z3, List list, a0 a0Var) {
        List list2;
        if (2047 == (i2 & 2047)) {
            this.name = str;
            this.phoneNumber = str2;
            this.contactId = str3;
            this.email = str4;
            this.groupName = str5;
            this.nickName = str6;
            this.snsName = str7;
            this.relation = str8;
            this.isContributor = z;
            this.sourcePackage = str9;
            this.sourceUri = str10;
            if ((i2 & 2048) == 0) {
                this.augmented = false;
            } else {
                this.augmented = z3;
            }
            if ((i2 & 4096) == 0) {
                list2 = C1202t.d;
            } else {
                list2 = list;
            }
            this.tags = list2;
            return;
        }
        Q.f(i2, 2047, Who$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ Who copy$default(Who who, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, String str10, boolean z3, List list, int i2, Object obj) {
        int i7 = i2;
        if ((i7 & 1) != 0) {
            str = who.name;
        }
        return who.copy(str, (i7 & 2) != 0 ? who.phoneNumber : str2, (i7 & 4) != 0 ? who.contactId : str3, (i7 & 8) != 0 ? who.email : str4, (i7 & 16) != 0 ? who.groupName : str5, (i7 & 32) != 0 ? who.nickName : str6, (i7 & 64) != 0 ? who.snsName : str7, (i7 & 128) != 0 ? who.relation : str8, (i7 & 256) != 0 ? who.isContributor : z, (i7 & 512) != 0 ? who.sourcePackage : str9, (i7 & 1024) != 0 ? who.sourceUri : str10, (i7 & 2048) != 0 ? who.augmented : z3, (i7 & 4096) != 0 ? who.tags : list);
    }

    public static final /* synthetic */ void write$Self$pde_sdk_1_0_40_release(Who who, b bVar, f fVar) {
        a[] aVarArr = $childSerializers;
        k kVar = (k) bVar;
        kVar.u(fVar, 0, who.name);
        kVar.u(fVar, 1, who.phoneNumber);
        kVar.u(fVar, 2, who.contactId);
        kVar.u(fVar, 3, who.email);
        kVar.u(fVar, 4, who.groupName);
        kVar.u(fVar, 5, who.nickName);
        kVar.u(fVar, 6, who.snsName);
        kVar.u(fVar, 7, who.relation);
        kVar.o(fVar, 8, who.isContributor);
        kVar.u(fVar, 9, who.sourcePackage);
        kVar.u(fVar, 10, who.sourceUri);
        if (kVar.d(fVar) || who.augmented) {
            kVar.o(fVar, 11, who.augmented);
        }
        if (kVar.d(fVar) || !j.a(who.tags, C1202t.d)) {
            kVar.t(fVar, 12, aVarArr[12], who.tags);
        }
    }

    public final String component1() {
        return this.name;
    }

    public final String component10() {
        return this.sourcePackage;
    }

    public final String component11() {
        return this.sourceUri;
    }

    public final boolean component12() {
        return this.augmented;
    }

    public final List<String> component13() {
        return this.tags;
    }

    public final String component2() {
        return this.phoneNumber;
    }

    public final String component3() {
        return this.contactId;
    }

    public final String component4() {
        return this.email;
    }

    public final String component5() {
        return this.groupName;
    }

    public final String component6() {
        return this.nickName;
    }

    public final String component7() {
        return this.snsName;
    }

    public final String component8() {
        return this.relation;
    }

    public final boolean component9() {
        return this.isContributor;
    }

    public final Who copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, String str10, boolean z3, List<String> list) {
        j.e(str, "name");
        String str11 = str2;
        j.e(str11, "phoneNumber");
        String str12 = str3;
        j.e(str12, PersonBundleWrapper.BUNDLE_KEY_CONTACT_ID);
        String str13 = str4;
        j.e(str13, "email");
        String str14 = str5;
        j.e(str14, "groupName");
        String str15 = str6;
        j.e(str15, "nickName");
        String str16 = str7;
        j.e(str16, "snsName");
        String str17 = str8;
        j.e(str17, "relation");
        String str18 = str9;
        j.e(str18, "sourcePackage");
        String str19 = str10;
        j.e(str19, "sourceUri");
        List<String> list2 = list;
        j.e(list2, "tags");
        return new Who(str, str11, str12, str13, str14, str15, str16, str17, z, str18, str19, z3, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Who)) {
            return false;
        }
        Who who = (Who) obj;
        if (j.a(this.name, who.name) && j.a(this.phoneNumber, who.phoneNumber) && j.a(this.contactId, who.contactId) && j.a(this.email, who.email) && j.a(this.groupName, who.groupName) && j.a(this.nickName, who.nickName) && j.a(this.snsName, who.snsName) && j.a(this.relation, who.relation) && this.isContributor == who.isContributor && j.a(this.sourcePackage, who.sourcePackage) && j.a(this.sourceUri, who.sourceUri) && this.augmented == who.augmented && j.a(this.tags, who.tags)) {
            return true;
        }
        return false;
    }

    public final boolean getAugmented() {
        return this.augmented;
    }

    public final String getContactId() {
        return this.contactId;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getGroupName() {
        return this.groupName;
    }

    public final String getName() {
        return this.name;
    }

    public final String getNickName() {
        return this.nickName;
    }

    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public final String getRelation() {
        return this.relation;
    }

    public final String getSnsName() {
        return this.snsName;
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

    public int hashCode() {
        return this.tags.hashCode() + C0212a.e(C0212a.d(C0212a.d(C0212a.e(C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(this.name.hashCode() * 31, 31, this.phoneNumber), 31, this.contactId), 31, this.email), 31, this.groupName), 31, this.nickName), 31, this.snsName), 31, this.relation), 31, this.isContributor), 31, this.sourcePackage), 31, this.sourceUri), 31, this.augmented);
    }

    public final boolean isContributor() {
        return this.isContributor;
    }

    public final void setTags(List<String> list) {
        j.e(list, "<set-?>");
        this.tags = list;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Who(name=");
        sb2.append(this.name);
        sb2.append(", phoneNumber=");
        sb2.append(this.phoneNumber);
        sb2.append(", contactId=");
        sb2.append(this.contactId);
        sb2.append(", email=");
        sb2.append(this.email);
        sb2.append(", groupName=");
        sb2.append(this.groupName);
        sb2.append(", nickName=");
        sb2.append(this.nickName);
        sb2.append(", snsName=");
        sb2.append(this.snsName);
        sb2.append(", relation=");
        sb2.append(this.relation);
        sb2.append(", isContributor=");
        sb2.append(this.isContributor);
        sb2.append(", sourcePackage=");
        sb2.append(this.sourcePackage);
        sb2.append(", sourceUri=");
        sb2.append(this.sourceUri);
        sb2.append(", augmented=");
        sb2.append(this.augmented);
        sb2.append(", tags=");
        return C0212a.r(sb2, this.tags, ')');
    }

    public Who(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, String str10, boolean z3, List<String> list) {
        j.e(str, "name");
        j.e(str2, "phoneNumber");
        j.e(str3, PersonBundleWrapper.BUNDLE_KEY_CONTACT_ID);
        j.e(str4, "email");
        j.e(str5, "groupName");
        j.e(str6, "nickName");
        j.e(str7, "snsName");
        j.e(str8, "relation");
        j.e(str9, "sourcePackage");
        j.e(str10, "sourceUri");
        j.e(list, "tags");
        this.name = str;
        this.phoneNumber = str2;
        this.contactId = str3;
        this.email = str4;
        this.groupName = str5;
        this.nickName = str6;
        this.snsName = str7;
        this.relation = str8;
        this.isContributor = z;
        this.sourcePackage = str9;
        this.sourceUri = str10;
        this.augmented = z3;
        this.tags = list;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Who(java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, boolean r25, java.lang.String r26, java.lang.String r27, boolean r28, java.util.List r29, int r30, kotlin.jvm.internal.e r31) {
        /*
            r16 = this;
            r0 = r30
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x0009
            r1 = 0
            r14 = r1
            goto L_0x000b
        L_0x0009:
            r14 = r28
        L_0x000b:
            r0 = r0 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L_0x002b
            ne.t r0 = ne.C1202t.d
            r15 = r0
        L_0x0012:
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r21
            r8 = r22
            r9 = r23
            r10 = r24
            r11 = r25
            r12 = r26
            r13 = r27
            goto L_0x002e
        L_0x002b:
            r15 = r29
            goto L_0x0012
        L_0x002e:
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.event.entity.Who.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, java.lang.String, boolean, java.util.List, int, kotlin.jvm.internal.e):void");
    }
}
