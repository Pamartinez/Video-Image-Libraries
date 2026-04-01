package Q0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum d {
    INTERN_FIELD_NAMES,
    CANONICALIZE_FIELD_NAMES,
    FAIL_ON_SYMBOL_HASH_OVERFLOW,
    USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING;
    
    private final boolean _defaultState;

    public static int a() {
        int i2 = 0;
        for (d dVar : values()) {
            if (dVar._defaultState) {
                i2 |= 1 << dVar.ordinal();
            }
        }
        return i2;
    }
}
