package E0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum m {
    STAR(1),
    POLYGON(2);
    
    private final int value;

    /* access modifiers changed from: public */
    m(int i2) {
        this.value = i2;
    }

    public static m a(int i2) {
        for (m mVar : values()) {
            if (mVar.value == i2) {
                return mVar;
            }
        }
        return null;
    }
}
