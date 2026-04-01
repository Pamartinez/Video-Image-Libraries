package androidx.window.embedding;

import i.C0212a;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001BY\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\n¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0016\u001a\u00020\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010\u0019\u001a\u00020\nH\u0016J\u0016\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0004H\u0002¢\u0006\u0002\b\u001cR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011¨\u0006\u001d"}, d2 = {"Landroidx/window/embedding/SplitPairRule;", "Landroidx/window/embedding/SplitRule;", "filters", "", "Landroidx/window/embedding/SplitPairFilter;", "finishPrimaryWithSecondary", "", "finishSecondaryWithPrimary", "clearTop", "minWidth", "", "minSmallestWidth", "splitRatio", "", "layoutDir", "(Ljava/util/Set;ZZZIIFI)V", "getClearTop", "()Z", "getFilters", "()Ljava/util/Set;", "getFinishPrimaryWithSecondary", "getFinishSecondaryWithPrimary", "equals", "other", "", "hashCode", "plus", "filter", "plus$window_release", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SplitPairRule extends SplitRule {
    private final boolean clearTop;
    private final Set<SplitPairFilter> filters;
    private final boolean finishPrimaryWithSecondary;
    private final boolean finishSecondaryWithPrimary;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SplitPairRule(Set set, boolean z, boolean z3, boolean z7, int i2, int i7, float f, int i8, int i10, e eVar) {
        this(set, (i10 & 2) != 0 ? false : z, (i10 & 4) != 0 ? true : z3, (i10 & 8) != 0 ? false : z7, (i10 & 16) != 0 ? 0 : i2, (i10 & 32) != 0 ? 0 : i7, (i10 & 64) != 0 ? 0.5f : f, (i10 & 128) != 0 ? 3 : i8);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SplitPairRule) || !super.equals(obj)) {
            return false;
        }
        SplitPairRule splitPairRule = (SplitPairRule) obj;
        if (j.a(this.filters, splitPairRule.filters) && this.finishPrimaryWithSecondary == splitPairRule.finishPrimaryWithSecondary && this.finishSecondaryWithPrimary == splitPairRule.finishSecondaryWithPrimary && this.clearTop == splitPairRule.clearTop) {
            return true;
        }
        return false;
    }

    public final boolean getClearTop() {
        return this.clearTop;
    }

    public final Set<SplitPairFilter> getFilters() {
        return this.filters;
    }

    public final boolean getFinishPrimaryWithSecondary() {
        return this.finishPrimaryWithSecondary;
    }

    public final boolean getFinishSecondaryWithPrimary() {
        return this.finishSecondaryWithPrimary;
    }

    public int hashCode() {
        return Boolean.hashCode(this.clearTop) + C0212a.e(C0212a.e((this.filters.hashCode() + (super.hashCode() * 31)) * 31, 31, this.finishPrimaryWithSecondary), 31, this.finishSecondaryWithPrimary);
    }

    public final SplitPairRule plus$window_release(SplitPairFilter splitPairFilter) {
        j.e(splitPairFilter, "filter");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(this.filters);
        linkedHashSet.add(splitPairFilter);
        return new SplitPairRule(C1194l.p1(linkedHashSet), this.finishPrimaryWithSecondary, this.finishSecondaryWithPrimary, this.clearTop, getMinWidth(), getMinSmallestWidth(), getSplitRatio(), getLayoutDirection());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SplitPairRule(Set<SplitPairFilter> set, boolean z, boolean z3, boolean z7, int i2, int i7, float f, int i8) {
        super(i2, i7, f, i8);
        j.e(set, "filters");
        this.finishPrimaryWithSecondary = z;
        this.finishSecondaryWithPrimary = z3;
        this.clearTop = z7;
        this.filters = C1194l.p1(set);
    }
}
