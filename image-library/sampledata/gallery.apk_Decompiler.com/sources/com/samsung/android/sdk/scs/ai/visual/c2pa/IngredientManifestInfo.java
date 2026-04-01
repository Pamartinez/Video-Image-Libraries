package com.samsung.android.sdk.scs.ai.visual.c2pa;

import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/IngredientManifestInfo;", "", "manifestKey", "", "isParent", "", "<init>", "(Ljava/lang/String;Z)V", "getManifestKey", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class IngredientManifestInfo {
    private final boolean isParent;
    private final String manifestKey;

    public IngredientManifestInfo(String str, boolean z) {
        j.e(str, "manifestKey");
        this.manifestKey = str;
        this.isParent = z;
    }

    public static /* synthetic */ IngredientManifestInfo copy$default(IngredientManifestInfo ingredientManifestInfo, String str, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = ingredientManifestInfo.manifestKey;
        }
        if ((i2 & 2) != 0) {
            z = ingredientManifestInfo.isParent;
        }
        return ingredientManifestInfo.copy(str, z);
    }

    public final String component1() {
        return this.manifestKey;
    }

    public final boolean component2() {
        return this.isParent;
    }

    public final IngredientManifestInfo copy(String str, boolean z) {
        j.e(str, "manifestKey");
        return new IngredientManifestInfo(str, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IngredientManifestInfo)) {
            return false;
        }
        IngredientManifestInfo ingredientManifestInfo = (IngredientManifestInfo) obj;
        if (j.a(this.manifestKey, ingredientManifestInfo.manifestKey) && this.isParent == ingredientManifestInfo.isParent) {
            return true;
        }
        return false;
    }

    public final String getManifestKey() {
        return this.manifestKey;
    }

    public int hashCode() {
        return Boolean.hashCode(this.isParent) + (this.manifestKey.hashCode() * 31);
    }

    public final boolean isParent() {
        return this.isParent;
    }

    public String toString() {
        String str = this.manifestKey;
        boolean z = this.isParent;
        return "IngredientManifestInfo(manifestKey=" + str + ", isParent=" + z + ")";
    }
}
