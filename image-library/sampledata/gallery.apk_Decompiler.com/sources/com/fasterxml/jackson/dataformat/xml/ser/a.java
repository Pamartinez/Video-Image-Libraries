package com.fasterxml.jackson.dataformat.xml.ser;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum a {
    WRITE_XML_DECLARATION,
    WRITE_XML_1_1,
    WRITE_NULLS_AS_XSI_NIL;
    
    final boolean _defaultState;
    final int _mask;

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
