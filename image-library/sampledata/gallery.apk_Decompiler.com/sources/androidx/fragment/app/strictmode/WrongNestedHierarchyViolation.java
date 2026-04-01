package androidx.fragment.app.strictmode;

import androidx.fragment.app.Fragment;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/fragment/app/strictmode/WrongNestedHierarchyViolation;", "Landroidx/fragment/app/strictmode/Violation;", "Landroidx/fragment/app/Fragment;", "fragment", "expectedParentFragment", "", "containerId", "<init>", "(Landroidx/fragment/app/Fragment;Landroidx/fragment/app/Fragment;I)V", "Landroidx/fragment/app/Fragment;", "getExpectedParentFragment", "()Landroidx/fragment/app/Fragment;", "I", "getContainerId", "()I", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WrongNestedHierarchyViolation extends Violation {
    private final int containerId;
    private final Fragment expectedParentFragment;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WrongNestedHierarchyViolation(androidx.fragment.app.Fragment r3, androidx.fragment.app.Fragment r4, int r5) {
        /*
            r2 = this;
            java.lang.String r0 = "fragment"
            kotlin.jvm.internal.j.e(r3, r0)
            java.lang.String r0 = "expectedParentFragment"
            kotlin.jvm.internal.j.e(r4, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Attempting to nest fragment "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r1 = " within the view of parent fragment "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r1 = " via container with ID "
            r0.append(r1)
            java.lang.String r1 = " without using parent's childFragmentManager"
            java.lang.String r0 = c0.C0086a.l(r0, r5, r1)
            r2.<init>(r3, r0)
            r2.expectedParentFragment = r4
            r2.containerId = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.strictmode.WrongNestedHierarchyViolation.<init>(androidx.fragment.app.Fragment, androidx.fragment.app.Fragment, int):void");
    }
}
