package com.samsung.android.sdk.moneta.event.entity.event;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b\u0012\b\b\u0018\u00002\u00020\u0001Bq\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0010\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0015¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001dJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001dJ\u0010\u0010 \u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b \u0010\u001dJ\u0010\u0010!\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b!\u0010\u001dJ\u0010\u0010\"\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\"\u0010\u001dJ\u0010\u0010#\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b#\u0010\u001dJ\u0010\u0010$\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b$\u0010\u001dJ\u0010\u0010%\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b%\u0010\u001dJ\u0010\u0010&\u001a\u00020\fHÆ\u0003¢\u0006\u0004\b&\u0010'J\u0010\u0010(\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b(\u0010\u001dJ\u0010\u0010)\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b)\u0010\u001dJ\u0010\u0010*\u001a\u00020\fHÆ\u0003¢\u0006\u0004\b*\u0010'J\u0001\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\fHÆ\u0001¢\u0006\u0004\b+\u0010,J\u0010\u0010-\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b-\u0010\u001dJ\u0010\u0010.\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b.\u0010\u001bJ\u001a\u00101\u001a\u00020\f2\b\u00100\u001a\u0004\u0018\u00010/HÖ\u0003¢\u0006\u0004\b1\u00102R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00103\u001a\u0004\b4\u0010\u001dR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u00103\u001a\u0004\b5\u0010\u001dR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u00103\u001a\u0004\b6\u0010\u001dR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u00103\u001a\u0004\b7\u0010\u001dR\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u00103\u001a\u0004\b8\u0010\u001dR\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u00103\u001a\u0004\b9\u0010\u001dR\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u00103\u001a\u0004\b:\u0010\u001dR\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u00103\u001a\u0004\b;\u0010\u001dR\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u00103\u001a\u0004\b<\u0010\u001dR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010=\u001a\u0004\b\r\u0010'R\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u00103\u001a\u0004\b>\u0010\u001dR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u00103\u001a\u0004\b?\u0010\u001dR\u0017\u0010\u0010\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010=\u001a\u0004\b@\u0010'¨\u0006A"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/event/Who;", "Landroid/os/Parcelable;", "", "name", "phoneNumber", "contactId", "email", "groupName", "nickName", "snsName", "relation", "category", "", "isContributor", "sourcePackage", "sourceUri", "augmented", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Z)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "()Z", "component11", "component12", "component13", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Z)Lcom/samsung/android/sdk/moneta/event/entity/event/Who;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getName", "getPhoneNumber", "getContactId", "getEmail", "getGroupName", "getNickName", "getSnsName", "getRelation", "getCategory", "Z", "getSourcePackage", "getSourceUri", "getAugmented", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Who implements Parcelable {
    public static final Parcelable.Creator<Who> CREATOR = new Creator();
    private final /* synthetic */ boolean augmented;
    private final /* synthetic */ String category;
    private final /* synthetic */ String contactId;
    private final /* synthetic */ String email;
    private final /* synthetic */ String groupName;
    private final /* synthetic */ boolean isContributor;
    private final /* synthetic */ String name;
    private final /* synthetic */ String nickName;
    private final /* synthetic */ String phoneNumber;
    private final /* synthetic */ String relation;
    private final /* synthetic */ String snsName;
    private final /* synthetic */ String sourcePackage;
    private final /* synthetic */ String sourceUri;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Who> {
        public final Who createFromParcel(Parcel parcel) {
            boolean z;
            boolean z3;
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readString6 = parcel.readString();
            String readString7 = parcel.readString();
            String readString8 = parcel.readString();
            String readString9 = parcel.readString();
            boolean z7 = false;
            if (parcel.readInt() != 0) {
                z = false;
                z7 = true;
                z3 = true;
            } else {
                z = false;
                z3 = true;
            }
            String readString10 = parcel.readString();
            boolean z9 = z3;
            String readString11 = parcel.readString();
            if (parcel.readInt() == 0) {
                z9 = z;
            }
            return new Who(readString, readString2, readString3, readString4, readString5, readString6, readString7, readString8, readString9, z7, readString10, readString11, z9);
        }

        public final Who[] newArray(int i2) {
            return new Who[i2];
        }
    }

    public Who(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, String str10, String str11, boolean z3) {
        j.e(str, "name");
        j.e(str2, "phoneNumber");
        j.e(str3, PersonBundleWrapper.BUNDLE_KEY_CONTACT_ID);
        j.e(str4, "email");
        j.e(str5, "groupName");
        j.e(str6, "nickName");
        j.e(str7, "snsName");
        j.e(str8, "relation");
        j.e(str9, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
        j.e(str10, "sourcePackage");
        j.e(str11, "sourceUri");
        this.name = str;
        this.phoneNumber = str2;
        this.contactId = str3;
        this.email = str4;
        this.groupName = str5;
        this.nickName = str6;
        this.snsName = str7;
        this.relation = str8;
        this.category = str9;
        this.isContributor = z;
        this.sourcePackage = str10;
        this.sourceUri = str11;
        this.augmented = z3;
    }

    public static /* synthetic */ Who copy$default(Who who, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, String str10, String str11, boolean z3, int i2, Object obj) {
        int i7 = i2;
        if ((i7 & 1) != 0) {
            str = who.name;
        }
        return who.copy(str, (i7 & 2) != 0 ? who.phoneNumber : str2, (i7 & 4) != 0 ? who.contactId : str3, (i7 & 8) != 0 ? who.email : str4, (i7 & 16) != 0 ? who.groupName : str5, (i7 & 32) != 0 ? who.nickName : str6, (i7 & 64) != 0 ? who.snsName : str7, (i7 & 128) != 0 ? who.relation : str8, (i7 & 256) != 0 ? who.category : str9, (i7 & 512) != 0 ? who.isContributor : z, (i7 & 1024) != 0 ? who.sourcePackage : str10, (i7 & 2048) != 0 ? who.sourceUri : str11, (i7 & 4096) != 0 ? who.augmented : z3);
    }

    public final String component1() {
        return this.name;
    }

    public final boolean component10() {
        return this.isContributor;
    }

    public final String component11() {
        return this.sourcePackage;
    }

    public final String component12() {
        return this.sourceUri;
    }

    public final boolean component13() {
        return this.augmented;
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

    public final String component9() {
        return this.category;
    }

    public final Who copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, String str10, String str11, boolean z3) {
        j.e(str, "name");
        String str12 = str2;
        j.e(str12, "phoneNumber");
        String str13 = str3;
        j.e(str13, PersonBundleWrapper.BUNDLE_KEY_CONTACT_ID);
        String str14 = str4;
        j.e(str14, "email");
        String str15 = str5;
        j.e(str15, "groupName");
        String str16 = str6;
        j.e(str16, "nickName");
        String str17 = str7;
        j.e(str17, "snsName");
        String str18 = str8;
        j.e(str18, "relation");
        String str19 = str9;
        j.e(str19, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
        String str20 = str10;
        j.e(str20, "sourcePackage");
        String str21 = str11;
        j.e(str21, "sourceUri");
        return new Who(str, str12, str13, str14, str15, str16, str17, str18, str19, z, str20, str21, z3);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Who)) {
            return false;
        }
        Who who = (Who) obj;
        if (j.a(this.name, who.name) && j.a(this.phoneNumber, who.phoneNumber) && j.a(this.contactId, who.contactId) && j.a(this.email, who.email) && j.a(this.groupName, who.groupName) && j.a(this.nickName, who.nickName) && j.a(this.snsName, who.snsName) && j.a(this.relation, who.relation) && j.a(this.category, who.category) && this.isContributor == who.isContributor && j.a(this.sourcePackage, who.sourcePackage) && j.a(this.sourceUri, who.sourceUri) && this.augmented == who.augmented) {
            return true;
        }
        return false;
    }

    public final boolean getAugmented() {
        return this.augmented;
    }

    public final String getCategory() {
        return this.category;
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

    public int hashCode() {
        return Boolean.hashCode(this.augmented) + C0212a.d(C0212a.d(C0212a.e(C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(C0212a.d(this.name.hashCode() * 31, 31, this.phoneNumber), 31, this.contactId), 31, this.email), 31, this.groupName), 31, this.nickName), 31, this.snsName), 31, this.relation), 31, this.category), 31, this.isContributor), 31, this.sourcePackage), 31, this.sourceUri);
    }

    public final boolean isContributor() {
        return this.isContributor;
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
        sb2.append(", category=");
        sb2.append(this.category);
        sb2.append(", isContributor=");
        sb2.append(this.isContributor);
        sb2.append(", sourcePackage=");
        sb2.append(this.sourcePackage);
        sb2.append(", sourceUri=");
        sb2.append(this.sourceUri);
        sb2.append(", augmented=");
        return C0086a.n(sb2, this.augmented, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.name);
        parcel.writeString(this.phoneNumber);
        parcel.writeString(this.contactId);
        parcel.writeString(this.email);
        parcel.writeString(this.groupName);
        parcel.writeString(this.nickName);
        parcel.writeString(this.snsName);
        parcel.writeString(this.relation);
        parcel.writeString(this.category);
        parcel.writeInt(this.isContributor ? 1 : 0);
        parcel.writeString(this.sourcePackage);
        parcel.writeString(this.sourceUri);
        parcel.writeInt(this.augmented ? 1 : 0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Who(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, String str10, String str11, boolean z3, int i2, e eVar) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, z, str10, str11, (i2 & 4096) != 0 ? false : z3);
    }
}
