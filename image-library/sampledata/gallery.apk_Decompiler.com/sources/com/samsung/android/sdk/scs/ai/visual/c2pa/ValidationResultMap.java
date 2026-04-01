package com.samsung.android.sdk.scs.ai.visual.c2pa;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B7\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J?\u0010\u0010\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationResultMap;", "", "success", "", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationStatus;", "informational", "failure", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getSuccess", "()Ljava/util/List;", "getInformational", "getFailure", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ValidationResultMap {
    private final List<ValidationStatus> failure;
    private final List<ValidationStatus> informational;
    private final List<ValidationStatus> success;

    public ValidationResultMap(List<ValidationStatus> list, List<ValidationStatus> list2, List<ValidationStatus> list3) {
        this.success = list;
        this.informational = list2;
        this.failure = list3;
    }

    public static /* synthetic */ ValidationResultMap copy$default(ValidationResultMap validationResultMap, List<ValidationStatus> list, List<ValidationStatus> list2, List<ValidationStatus> list3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = validationResultMap.success;
        }
        if ((i2 & 2) != 0) {
            list2 = validationResultMap.informational;
        }
        if ((i2 & 4) != 0) {
            list3 = validationResultMap.failure;
        }
        return validationResultMap.copy(list, list2, list3);
    }

    public final List<ValidationStatus> component1() {
        return this.success;
    }

    public final List<ValidationStatus> component2() {
        return this.informational;
    }

    public final List<ValidationStatus> component3() {
        return this.failure;
    }

    public final ValidationResultMap copy(List<ValidationStatus> list, List<ValidationStatus> list2, List<ValidationStatus> list3) {
        return new ValidationResultMap(list, list2, list3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ValidationResultMap)) {
            return false;
        }
        ValidationResultMap validationResultMap = (ValidationResultMap) obj;
        if (j.a(this.success, validationResultMap.success) && j.a(this.informational, validationResultMap.informational) && j.a(this.failure, validationResultMap.failure)) {
            return true;
        }
        return false;
    }

    public final List<ValidationStatus> getFailure() {
        return this.failure;
    }

    public final List<ValidationStatus> getInformational() {
        return this.informational;
    }

    public final List<ValidationStatus> getSuccess() {
        return this.success;
    }

    public int hashCode() {
        int i2;
        int i7;
        List<ValidationStatus> list = this.success;
        int i8 = 0;
        if (list == null) {
            i2 = 0;
        } else {
            i2 = list.hashCode();
        }
        int i10 = i2 * 31;
        List<ValidationStatus> list2 = this.informational;
        if (list2 == null) {
            i7 = 0;
        } else {
            i7 = list2.hashCode();
        }
        int i11 = (i10 + i7) * 31;
        List<ValidationStatus> list3 = this.failure;
        if (list3 != null) {
            i8 = list3.hashCode();
        }
        return i11 + i8;
    }

    public String toString() {
        List<ValidationStatus> list = this.success;
        List<ValidationStatus> list2 = this.informational;
        List<ValidationStatus> list3 = this.failure;
        return "ValidationResultMap(success=" + list + ", informational=" + list2 + ", failure=" + list3 + ")";
    }
}
