package com.samsung.android.sdk.scs.ai.visual.c2pa;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\u000b\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\"\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Data;", "", "actions", "", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Action;", "<init>", "(Ljava/util/List;)V", "getActions", "()Ljava/util/List;", "setActions", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Data {
    private List<Action> actions;

    public Data(List<Action> list) {
        this.actions = list;
    }

    public static /* synthetic */ Data copy$default(Data data, List<Action> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = data.actions;
        }
        return data.copy(list);
    }

    public final List<Action> component1() {
        return this.actions;
    }

    public final Data copy(List<Action> list) {
        return new Data(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Data) && j.a(this.actions, ((Data) obj).actions)) {
            return true;
        }
        return false;
    }

    public final List<Action> getActions() {
        return this.actions;
    }

    public int hashCode() {
        List<Action> list = this.actions;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public final void setActions(List<Action> list) {
        this.actions = list;
    }

    public String toString() {
        List<Action> list = this.actions;
        return "Data(actions=" + list + ")";
    }
}
