package com.samsung.android.sdk.scs.ai.visual.c2pa;

import c0.C0086a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B?\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\nHÆ\u0003JK\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Ingredients;", "", "title", "", "activeManifest", "relationship", "validationStatus", "", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationStatus;", "validationResults", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationResults;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationResults;)V", "getTitle", "()Ljava/lang/String;", "getActiveManifest", "getRelationship", "getValidationStatus", "()Ljava/util/List;", "getValidationResults", "()Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationResults;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Ingredients {
    private final String activeManifest;
    private final String relationship;
    private final String title;
    private final ValidationResults validationResults;
    private final List<ValidationStatus> validationStatus;

    public Ingredients(String str, String str2, String str3, List<ValidationStatus> list, ValidationResults validationResults2) {
        this.title = str;
        this.activeManifest = str2;
        this.relationship = str3;
        this.validationStatus = list;
        this.validationResults = validationResults2;
    }

    public static /* synthetic */ Ingredients copy$default(Ingredients ingredients, String str, String str2, String str3, List<ValidationStatus> list, ValidationResults validationResults2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = ingredients.title;
        }
        if ((i2 & 2) != 0) {
            str2 = ingredients.activeManifest;
        }
        if ((i2 & 4) != 0) {
            str3 = ingredients.relationship;
        }
        if ((i2 & 8) != 0) {
            list = ingredients.validationStatus;
        }
        if ((i2 & 16) != 0) {
            validationResults2 = ingredients.validationResults;
        }
        List<ValidationStatus> list2 = list;
        ValidationResults validationResults3 = validationResults2;
        return ingredients.copy(str, str2, str3, list2, validationResults3);
    }

    public final String component1() {
        return this.title;
    }

    public final String component2() {
        return this.activeManifest;
    }

    public final String component3() {
        return this.relationship;
    }

    public final List<ValidationStatus> component4() {
        return this.validationStatus;
    }

    public final ValidationResults component5() {
        return this.validationResults;
    }

    public final Ingredients copy(String str, String str2, String str3, List<ValidationStatus> list, ValidationResults validationResults2) {
        return new Ingredients(str, str2, str3, list, validationResults2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Ingredients)) {
            return false;
        }
        Ingredients ingredients = (Ingredients) obj;
        if (j.a(this.title, ingredients.title) && j.a(this.activeManifest, ingredients.activeManifest) && j.a(this.relationship, ingredients.relationship) && j.a(this.validationStatus, ingredients.validationStatus) && j.a(this.validationResults, ingredients.validationResults)) {
            return true;
        }
        return false;
    }

    public final String getActiveManifest() {
        return this.activeManifest;
    }

    public final String getRelationship() {
        return this.relationship;
    }

    public final String getTitle() {
        return this.title;
    }

    public final ValidationResults getValidationResults() {
        return this.validationResults;
    }

    public final List<ValidationStatus> getValidationStatus() {
        return this.validationStatus;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        String str = this.title;
        int i11 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i12 = i2 * 31;
        String str2 = this.activeManifest;
        if (str2 == null) {
            i7 = 0;
        } else {
            i7 = str2.hashCode();
        }
        int i13 = (i12 + i7) * 31;
        String str3 = this.relationship;
        if (str3 == null) {
            i8 = 0;
        } else {
            i8 = str3.hashCode();
        }
        int i14 = (i13 + i8) * 31;
        List<ValidationStatus> list = this.validationStatus;
        if (list == null) {
            i10 = 0;
        } else {
            i10 = list.hashCode();
        }
        int i15 = (i14 + i10) * 31;
        ValidationResults validationResults2 = this.validationResults;
        if (validationResults2 != null) {
            i11 = validationResults2.hashCode();
        }
        return i15 + i11;
    }

    public String toString() {
        String str = this.title;
        String str2 = this.activeManifest;
        String str3 = this.relationship;
        List<ValidationStatus> list = this.validationStatus;
        ValidationResults validationResults2 = this.validationResults;
        StringBuilder q = C0086a.q("Ingredients(title=", str, ", activeManifest=", str2, ", relationship=");
        q.append(str3);
        q.append(", validationStatus=");
        q.append(list);
        q.append(", validationResults=");
        q.append(validationResults2);
        q.append(")");
        return q.toString();
    }
}
