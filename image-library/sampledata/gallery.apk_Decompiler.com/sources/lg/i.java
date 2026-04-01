package lg;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f4901a;
    public final boolean b;

    /* renamed from: c  reason: collision with root package name */
    public final boolean f4902c;
    public final String d;
    public final boolean e;
    public final String f;
    public final boolean g;

    public i(boolean z, boolean z3, boolean z7, String str, boolean z9, String str2, boolean z10) {
        j.e(str, "prettyPrintIndent");
        j.e(str2, "classDiscriminator");
        this.f4901a = z;
        this.b = z3;
        this.f4902c = z7;
        this.d = str;
        this.e = z9;
        this.f = str2;
        this.g = z10;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("JsonConfiguration(encodeDefaults=");
        sb2.append(this.f4901a);
        sb2.append(", ignoreUnknownKeys=");
        sb2.append(this.b);
        sb2.append(", isLenient=false, allowStructuredMapKeys=false, prettyPrint=false, explicitNulls=");
        sb2.append(this.f4902c);
        sb2.append(", prettyPrintIndent='");
        sb2.append(this.d);
        sb2.append("', coerceInputValues=");
        sb2.append(this.e);
        sb2.append(", useArrayPolymorphism=false, classDiscriminator='");
        sb2.append(this.f);
        sb2.append("', allowSpecialFloatingPointValues=false, useAlternativeNames=");
        return N2.j.h(sb2, this.g, ", namingStrategy=null, decodeEnumsCaseInsensitive=false, allowTrailingComma=false)");
    }
}
