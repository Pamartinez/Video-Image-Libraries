package Q0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum f {
    AUTO_CLOSE_TARGET(true),
    AUTO_CLOSE_JSON_CONTENT(true),
    FLUSH_PASSED_TO_STREAM(true),
    QUOTE_FIELD_NAMES(true),
    QUOTE_NON_NUMERIC_NUMBERS(true),
    ESCAPE_NON_ASCII(false),
    WRITE_NUMBERS_AS_STRINGS(false),
    WRITE_BIGDECIMAL_AS_PLAIN(false),
    STRICT_DUPLICATE_DETECTION(false),
    IGNORE_UNKNOWN(false);
    
    private final boolean _defaultState;
    private final int _mask;

    /* access modifiers changed from: public */
    f(boolean z) {
        this._defaultState = z;
        this._mask = 1 << ordinal();
    }

    public static int a() {
        int i2 = 0;
        for (f fVar : values()) {
            if (fVar._defaultState) {
                i2 |= fVar._mask;
            }
        }
        return i2;
    }
}
