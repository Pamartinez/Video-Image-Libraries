package androidx.appfunctions.metadata;

import i.C0212a;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import ne.z;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001e\b\b\u0018\u00002\u00020\u0001B­\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0000\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\b\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\b¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u001a\u0010\u001e\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\u001aR\u001a\u0010\u0004\u001a\u00020\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0004\u0010 \u001a\u0004\b\"\u0010\u001aR\u001a\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\f\n\u0004\b\u0006\u0010#\u001a\u0004\b$\u0010\u001cR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00008\u0006X\u0004¢\u0006\f\n\u0004\b\u0007\u0010%\u001a\u0004\b&\u0010'R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006X\u0004¢\u0006\f\n\u0004\b\n\u0010(\u001a\u0004\b)\u0010*R \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\b8\u0006X\u0004¢\u0006\f\n\u0004\b\u000b\u0010(\u001a\u0004\b+\u0010*R \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\b8\u0006X\u0004¢\u0006\f\n\u0004\b\f\u0010(\u001a\u0004\b,\u0010*R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\b8\u0006X\u0004¢\u0006\f\n\u0004\b\r\u0010(\u001a\u0004\b-\u0010*R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u000e\u0010 \u001a\u0004\b.\u0010\u001aR\u001a\u0010\u0010\u001a\u00020\u000f8\u0006X\u0004¢\u0006\f\n\u0004\b\u0010\u0010/\u001a\u0004\b\u0010\u00100R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0011\u0010 \u001a\u0004\b1\u0010\u001aR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\f\n\u0004\b\u0012\u0010 \u001a\u0004\b2\u0010\u001aR \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\b8\u0006X\u0004¢\u0006\f\n\u0004\b\u0013\u0010(\u001a\u0004\b3\u0010*¨\u00064"}, d2 = {"Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;", "", "", "namespace", "id", "", "type", "itemType", "", "Landroidx/appfunctions/metadata/AppFunctionNamedDataTypeMetadataDocument;", "properties", "allOf", "oneOf", "required", "dataTypeReference", "", "isNullable", "objectQualifiedName", "description", "enumValues", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILandroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "toAppFunctionDataTypeMetadata", "()Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getNamespace", "getId", "I", "getType", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;", "getItemType", "()Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadataDocument;", "Ljava/util/List;", "getProperties", "()Ljava/util/List;", "getAllOf", "getOneOf", "getRequired", "getDataTypeReference", "Z", "()Z", "getObjectQualifiedName", "getDescription", "getEnumValues", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionDataTypeMetadataDocument {
    private final List<AppFunctionDataTypeMetadataDocument> allOf;
    private final String dataTypeReference;
    private final String description;
    private final List<String> enumValues;
    private final String id;
    private final boolean isNullable;
    private final AppFunctionDataTypeMetadataDocument itemType;
    private final String namespace;
    private final String objectQualifiedName;
    private final List<AppFunctionDataTypeMetadataDocument> oneOf;
    private final List<AppFunctionNamedDataTypeMetadataDocument> properties;
    private final List<String> required;
    private final int type;

    public AppFunctionDataTypeMetadataDocument(String str, String str2, int i2, AppFunctionDataTypeMetadataDocument appFunctionDataTypeMetadataDocument, List<AppFunctionNamedDataTypeMetadataDocument> list, List<AppFunctionDataTypeMetadataDocument> list2, List<AppFunctionDataTypeMetadataDocument> list3, List<String> list4, String str3, boolean z, String str4, String str5, List<String> list5) {
        j.e(str, "namespace");
        j.e(str2, "id");
        j.e(list, "properties");
        j.e(list2, "allOf");
        j.e(list3, "oneOf");
        j.e(list4, "required");
        j.e(list5, "enumValues");
        this.namespace = str;
        this.id = str2;
        this.type = i2;
        this.itemType = appFunctionDataTypeMetadataDocument;
        this.properties = list;
        this.allOf = list2;
        this.oneOf = list3;
        this.required = list4;
        this.dataTypeReference = str3;
        this.isNullable = z;
        this.objectQualifiedName = str4;
        this.description = str5;
        this.enumValues = list5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppFunctionDataTypeMetadataDocument)) {
            return false;
        }
        AppFunctionDataTypeMetadataDocument appFunctionDataTypeMetadataDocument = (AppFunctionDataTypeMetadataDocument) obj;
        if (j.a(this.namespace, appFunctionDataTypeMetadataDocument.namespace) && j.a(this.id, appFunctionDataTypeMetadataDocument.id) && this.type == appFunctionDataTypeMetadataDocument.type && j.a(this.itemType, appFunctionDataTypeMetadataDocument.itemType) && j.a(this.properties, appFunctionDataTypeMetadataDocument.properties) && j.a(this.allOf, appFunctionDataTypeMetadataDocument.allOf) && j.a(this.oneOf, appFunctionDataTypeMetadataDocument.oneOf) && j.a(this.required, appFunctionDataTypeMetadataDocument.required) && j.a(this.dataTypeReference, appFunctionDataTypeMetadataDocument.dataTypeReference) && this.isNullable == appFunctionDataTypeMetadataDocument.isNullable && j.a(this.objectQualifiedName, appFunctionDataTypeMetadataDocument.objectQualifiedName) && j.a(this.description, appFunctionDataTypeMetadataDocument.description) && j.a(this.enumValues, appFunctionDataTypeMetadataDocument.enumValues)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int b = C0212a.b(this.type, C0212a.d(this.namespace.hashCode() * 31, 31, this.id), 31);
        AppFunctionDataTypeMetadataDocument appFunctionDataTypeMetadataDocument = this.itemType;
        int i10 = 0;
        if (appFunctionDataTypeMetadataDocument == null) {
            i2 = 0;
        } else {
            i2 = appFunctionDataTypeMetadataDocument.hashCode();
        }
        int f = C0212a.f(this.required, C0212a.f(this.oneOf, C0212a.f(this.allOf, C0212a.f(this.properties, (b + i2) * 31, 31), 31), 31), 31);
        String str = this.dataTypeReference;
        if (str == null) {
            i7 = 0;
        } else {
            i7 = str.hashCode();
        }
        int e = C0212a.e((f + i7) * 31, 31, this.isNullable);
        String str2 = this.objectQualifiedName;
        if (str2 == null) {
            i8 = 0;
        } else {
            i8 = str2.hashCode();
        }
        int i11 = (e + i8) * 31;
        String str3 = this.description;
        if (str3 != null) {
            i10 = str3.hashCode();
        }
        return this.enumValues.hashCode() + ((i11 + i10) * 31);
    }

    public final AppFunctionDataTypeMetadata toAppFunctionDataTypeMetadata() {
        String str;
        Set set = null;
        String str2 = "";
        switch (this.type) {
            case 0:
                boolean z = this.isNullable;
                String str3 = this.description;
                if (str3 != null) {
                    str2 = str3;
                }
                return new AppFunctionUnitTypeMetadata(z, str2);
            case 1:
                boolean z3 = this.isNullable;
                String str4 = this.description;
                if (str4 != null) {
                    str2 = str4;
                }
                return new AppFunctionBooleanTypeMetadata(z3, str2);
            case 2:
                boolean z7 = this.isNullable;
                String str5 = this.description;
                if (str5 != null) {
                    str2 = str5;
                }
                return new AppFunctionBytesTypeMetadata(z7, str2);
            case 3:
                if (!this.properties.isEmpty()) {
                    Iterable<AppFunctionNamedDataTypeMetadataDocument> iterable = this.properties;
                    int Z = z.Z(C1196n.w0(iterable, 10));
                    if (Z < 16) {
                        Z = 16;
                    }
                    LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
                    for (AppFunctionNamedDataTypeMetadataDocument appFunctionNamedDataTypeMetadataDocument : iterable) {
                        linkedHashMap.put(appFunctionNamedDataTypeMetadataDocument.getName(), appFunctionNamedDataTypeMetadataDocument.getDataTypeMetadata().toAppFunctionDataTypeMetadata());
                    }
                    List<String> list = this.required;
                    String str6 = this.objectQualifiedName;
                    boolean z9 = this.isNullable;
                    String str7 = this.description;
                    if (str7 == null) {
                        str = str2;
                    } else {
                        str = str7;
                    }
                    return new AppFunctionObjectTypeMetadata(linkedHashMap, list, str6, z9, str);
                }
                throw new IllegalStateException("Properties must be present for object type can't be empty");
            case 4:
                boolean z10 = this.isNullable;
                String str8 = this.description;
                if (str8 != null) {
                    str2 = str8;
                }
                return new AppFunctionDoubleTypeMetadata(z10, str2);
            case 5:
                boolean z11 = this.isNullable;
                String str9 = this.description;
                if (str9 != null) {
                    str2 = str9;
                }
                return new AppFunctionFloatTypeMetadata(z11, str2);
            case 6:
                boolean z12 = this.isNullable;
                String str10 = this.description;
                if (str10 != null) {
                    str2 = str10;
                }
                return new AppFunctionLongTypeMetadata(z12, str2);
            case 7:
                boolean z13 = this.isNullable;
                String str11 = this.description;
                if (str11 != null) {
                    str2 = str11;
                }
                Iterable<String> iterable2 = this.enumValues;
                ArrayList arrayList = new ArrayList(C1196n.w0(iterable2, 10));
                for (String parseInt : iterable2) {
                    arrayList.add(Integer.valueOf(Integer.parseInt(parseInt)));
                }
                Set p12 = C1194l.p1(arrayList);
                if (!p12.isEmpty()) {
                    set = p12;
                }
                return new AppFunctionIntTypeMetadata(z13, str2, set);
            case 8:
                boolean z14 = this.isNullable;
                String str12 = this.description;
                if (str12 != null) {
                    str2 = str12;
                }
                Set p13 = C1194l.p1(this.enumValues);
                if (!p13.isEmpty()) {
                    set = p13;
                }
                return new AppFunctionStringTypeMetadata(z14, str2, set);
            case 10:
                AppFunctionDataTypeMetadataDocument appFunctionDataTypeMetadataDocument = this.itemType;
                if (appFunctionDataTypeMetadataDocument != null) {
                    AppFunctionDataTypeMetadata appFunctionDataTypeMetadata = appFunctionDataTypeMetadataDocument.toAppFunctionDataTypeMetadata();
                    boolean z15 = this.isNullable;
                    String str13 = this.description;
                    if (str13 != null) {
                        str2 = str13;
                    }
                    return new AppFunctionArrayTypeMetadata(appFunctionDataTypeMetadata, z15, str2);
                }
                throw new IllegalStateException("Item type must be present for array type");
            case 11:
                String str14 = this.dataTypeReference;
                if (str14 != null) {
                    boolean z16 = this.isNullable;
                    String str15 = this.description;
                    if (str15 != null) {
                        str2 = str15;
                    }
                    return new AppFunctionReferenceTypeMetadata(str14, z16, str2);
                }
                throw new IllegalStateException("Data type reference must be present for reference type");
            case 12:
                Iterable<AppFunctionDataTypeMetadataDocument> iterable3 = this.allOf;
                ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable3, 10));
                for (AppFunctionDataTypeMetadataDocument appFunctionDataTypeMetadata2 : iterable3) {
                    arrayList2.add(appFunctionDataTypeMetadata2.toAppFunctionDataTypeMetadata());
                }
                String str16 = this.objectQualifiedName;
                boolean z17 = this.isNullable;
                String str17 = this.description;
                if (str17 != null) {
                    str2 = str17;
                }
                return new AppFunctionAllOfTypeMetadata(arrayList2, str16, z17, str2);
            case 13:
                boolean z18 = this.isNullable;
                String str18 = this.description;
                if (str18 != null) {
                    str2 = str18;
                }
                return new AppFunctionPendingIntentTypeMetadata(z18, str2);
            case 14:
                Iterable<AppFunctionDataTypeMetadataDocument> iterable4 = this.oneOf;
                ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable4, 10));
                for (AppFunctionDataTypeMetadataDocument appFunctionDataTypeMetadata3 : iterable4) {
                    arrayList3.add(appFunctionDataTypeMetadata3.toAppFunctionDataTypeMetadata());
                }
                String str19 = this.objectQualifiedName;
                if (str19 != null) {
                    boolean z19 = this.isNullable;
                    String str20 = this.description;
                    if (str20 != null) {
                        str2 = str20;
                    }
                    return new AppFunctionOneOfTypeMetadata(arrayList3, str19, z19, str2);
                }
                throw new IllegalStateException("Required value was null.");
            default:
                throw new IllegalArgumentException("Unknown type: " + this.type);
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppFunctionDataTypeMetadataDocument(namespace=");
        sb2.append(this.namespace);
        sb2.append(", id=");
        sb2.append(this.id);
        sb2.append(", type=");
        sb2.append(this.type);
        sb2.append(", itemType=");
        sb2.append(this.itemType);
        sb2.append(", properties=");
        sb2.append(this.properties);
        sb2.append(", allOf=");
        sb2.append(this.allOf);
        sb2.append(", oneOf=");
        sb2.append(this.oneOf);
        sb2.append(", required=");
        sb2.append(this.required);
        sb2.append(", dataTypeReference=");
        sb2.append(this.dataTypeReference);
        sb2.append(", isNullable=");
        sb2.append(this.isNullable);
        sb2.append(", objectQualifiedName=");
        sb2.append(this.objectQualifiedName);
        sb2.append(", description=");
        sb2.append(this.description);
        sb2.append(", enumValues=");
        return C0212a.r(sb2, this.enumValues, ')');
    }
}
