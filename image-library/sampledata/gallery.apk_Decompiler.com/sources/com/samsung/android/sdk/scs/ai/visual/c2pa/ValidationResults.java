package com.samsung.android.sdk.scs.ai.visual.c2pa;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationResults;", "", "activeManifest", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationResultMap;", "<init>", "(Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationResultMap;)V", "getActiveManifest", "()Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationResultMap;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ValidationResults {
    @SerializedName("activeManifest")
    private final ValidationResultMap activeManifest;

    public ValidationResults(ValidationResultMap validationResultMap) {
        this.activeManifest = validationResultMap;
    }

    public static /* synthetic */ ValidationResults copy$default(ValidationResults validationResults, ValidationResultMap validationResultMap, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            validationResultMap = validationResults.activeManifest;
        }
        return validationResults.copy(validationResultMap);
    }

    public final ValidationResultMap component1() {
        return this.activeManifest;
    }

    public final ValidationResults copy(ValidationResultMap validationResultMap) {
        return new ValidationResults(validationResultMap);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof ValidationResults) && j.a(this.activeManifest, ((ValidationResults) obj).activeManifest)) {
            return true;
        }
        return false;
    }

    public final ValidationResultMap getActiveManifest() {
        return this.activeManifest;
    }

    public int hashCode() {
        ValidationResultMap validationResultMap = this.activeManifest;
        if (validationResultMap == null) {
            return 0;
        }
        return validationResultMap.hashCode();
    }

    public String toString() {
        ValidationResultMap validationResultMap = this.activeManifest;
        return "ValidationResults(activeManifest=" + validationResultMap + ")";
    }
}
