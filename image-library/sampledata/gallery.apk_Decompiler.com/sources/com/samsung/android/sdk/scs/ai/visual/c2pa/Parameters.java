package com.samsung.android.sdk.scs.ai.visual.c2pa;

import c0.C0086a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B?\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003JK\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Parameters;", "", "ingredient", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Ingredient;", "type", "", "version", "value", "author", "", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Author;", "<init>", "(Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Ingredient;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getIngredient", "()Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Ingredient;", "getType", "()Ljava/lang/String;", "getVersion", "getValue", "getAuthor", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Parameters {
    private final List<Author> author;
    private final Ingredient ingredient;
    private final String type;
    private final String value;
    private final String version;

    public Parameters(Ingredient ingredient2, String str, String str2, String str3, List<Author> list) {
        this.ingredient = ingredient2;
        this.type = str;
        this.version = str2;
        this.value = str3;
        this.author = list;
    }

    public static /* synthetic */ Parameters copy$default(Parameters parameters, Ingredient ingredient2, String str, String str2, String str3, List<Author> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            ingredient2 = parameters.ingredient;
        }
        if ((i2 & 2) != 0) {
            str = parameters.type;
        }
        if ((i2 & 4) != 0) {
            str2 = parameters.version;
        }
        if ((i2 & 8) != 0) {
            str3 = parameters.value;
        }
        if ((i2 & 16) != 0) {
            list = parameters.author;
        }
        String str4 = str3;
        List<Author> list2 = list;
        return parameters.copy(ingredient2, str, str2, str4, list2);
    }

    public final Ingredient component1() {
        return this.ingredient;
    }

    public final String component2() {
        return this.type;
    }

    public final String component3() {
        return this.version;
    }

    public final String component4() {
        return this.value;
    }

    public final List<Author> component5() {
        return this.author;
    }

    public final Parameters copy(Ingredient ingredient2, String str, String str2, String str3, List<Author> list) {
        return new Parameters(ingredient2, str, str2, str3, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Parameters)) {
            return false;
        }
        Parameters parameters = (Parameters) obj;
        if (j.a(this.ingredient, parameters.ingredient) && j.a(this.type, parameters.type) && j.a(this.version, parameters.version) && j.a(this.value, parameters.value) && j.a(this.author, parameters.author)) {
            return true;
        }
        return false;
    }

    public final List<Author> getAuthor() {
        return this.author;
    }

    public final Ingredient getIngredient() {
        return this.ingredient;
    }

    public final String getType() {
        return this.type;
    }

    public final String getValue() {
        return this.value;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        Ingredient ingredient2 = this.ingredient;
        int i11 = 0;
        if (ingredient2 == null) {
            i2 = 0;
        } else {
            i2 = ingredient2.hashCode();
        }
        int i12 = i2 * 31;
        String str = this.type;
        if (str == null) {
            i7 = 0;
        } else {
            i7 = str.hashCode();
        }
        int i13 = (i12 + i7) * 31;
        String str2 = this.version;
        if (str2 == null) {
            i8 = 0;
        } else {
            i8 = str2.hashCode();
        }
        int i14 = (i13 + i8) * 31;
        String str3 = this.value;
        if (str3 == null) {
            i10 = 0;
        } else {
            i10 = str3.hashCode();
        }
        int i15 = (i14 + i10) * 31;
        List<Author> list = this.author;
        if (list != null) {
            i11 = list.hashCode();
        }
        return i15 + i11;
    }

    public String toString() {
        Ingredient ingredient2 = this.ingredient;
        String str = this.type;
        String str2 = this.version;
        String str3 = this.value;
        List<Author> list = this.author;
        StringBuilder sb2 = new StringBuilder("Parameters(ingredient=");
        sb2.append(ingredient2);
        sb2.append(", type=");
        sb2.append(str);
        sb2.append(", version=");
        C0086a.z(sb2, str2, ", value=", str3, ", author=");
        sb2.append(list);
        sb2.append(")");
        return sb2.toString();
    }
}
