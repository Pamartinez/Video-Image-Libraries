package bf;

/* renamed from: bf.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C0921f {
    NON_STABLE_DECLARED(false, false),
    STABLE_DECLARED(true, false),
    NON_STABLE_SYNTHESIZED(false, true),
    STABLE_SYNTHESIZED(true, true);
    
    public final boolean isStable;
    public final boolean isSynthesized;

    /* access modifiers changed from: public */
    C0921f(boolean z, boolean z3) {
        this.isStable = z;
        this.isSynthesized = z3;
    }
}
