package com.samsung.android.sdk.scs.ai.visual.c2pa;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u0015B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paAssertion;", "", "label", "", "data", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Data;", "<init>", "(Ljava/lang/String;Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Data;)V", "getLabel", "()Ljava/lang/String;", "getData", "()Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Data;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Builder", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C2paAssertion {
    private final Data data;
    private final String label;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\u00020\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005XD¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paAssertion$Builder;", "", "<init>", "()V", "label", "", "actions", "", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Action;", "build", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paAssertion;", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private List<Action> actions;
        private final String label = "c2pa.actions";

        public final Builder actions(List<Action> list) {
            j.e(list, "actions");
            this.actions = list;
            return this;
        }

        public final C2paAssertion build() {
            return new C2paAssertion(this.label, new Data(this.actions));
        }
    }

    public C2paAssertion(String str, Data data2) {
        this.label = str;
        this.data = data2;
    }

    public static /* synthetic */ C2paAssertion copy$default(C2paAssertion c2paAssertion, String str, Data data2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = c2paAssertion.label;
        }
        if ((i2 & 2) != 0) {
            data2 = c2paAssertion.data;
        }
        return c2paAssertion.copy(str, data2);
    }

    public final String component1() {
        return this.label;
    }

    public final Data component2() {
        return this.data;
    }

    public final C2paAssertion copy(String str, Data data2) {
        return new C2paAssertion(str, data2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2paAssertion)) {
            return false;
        }
        C2paAssertion c2paAssertion = (C2paAssertion) obj;
        if (j.a(this.label, c2paAssertion.label) && j.a(this.data, c2paAssertion.data)) {
            return true;
        }
        return false;
    }

    public final Data getData() {
        return this.data;
    }

    public final String getLabel() {
        return this.label;
    }

    public int hashCode() {
        int i2;
        String str = this.label;
        int i7 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int i8 = i2 * 31;
        Data data2 = this.data;
        if (data2 != null) {
            i7 = data2.hashCode();
        }
        return i8 + i7;
    }

    public String toString() {
        String str = this.label;
        Data data2 = this.data;
        return "C2paAssertion(label=" + str + ", data=" + data2 + ")";
    }
}
