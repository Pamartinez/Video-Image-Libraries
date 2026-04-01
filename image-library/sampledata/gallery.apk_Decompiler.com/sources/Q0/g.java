package Q0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum g {
    AUTO_CLOSE_SOURCE(true),
    ALLOW_COMMENTS(false),
    ALLOW_YAML_COMMENTS(false),
    ALLOW_UNQUOTED_FIELD_NAMES(false),
    ALLOW_SINGLE_QUOTES(false),
    ALLOW_UNQUOTED_CONTROL_CHARS(false),
    ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
    ALLOW_NUMERIC_LEADING_ZEROS(false),
    ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS(false),
    ALLOW_NON_NUMERIC_NUMBERS(false),
    ALLOW_MISSING_VALUES(false),
    ALLOW_TRAILING_COMMA(false),
    STRICT_DUPLICATE_DETECTION(false),
    IGNORE_UNDEFINED(false),
    INCLUDE_SOURCE_IN_LOCATION(true);
    
    private final boolean _defaultState;
    private final int _mask;

    /* access modifiers changed from: public */
    g(boolean z) {
        this._mask = 1 << ordinal();
        this._defaultState = z;
    }

    public static int a() {
        int i2 = 0;
        for (g gVar : values()) {
            if (gVar._defaultState) {
                i2 |= gVar._mask;
            }
        }
        return i2;
    }
}
