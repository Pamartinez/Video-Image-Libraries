package com.fasterxml.jackson.dataformat.xml.deser;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum a {
    ;
    
    final boolean _defaultState;
    final int _mask;

    /* access modifiers changed from: public */
    a() {
        this._defaultState = false;
        this._mask = 1 << ordinal();
    }

    public static int a() {
        int i2 = 0;
        for (a aVar : values()) {
            if (aVar._defaultState) {
                i2 |= aVar._mask;
            }
        }
        return i2;
    }
}
