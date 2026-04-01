package com.adobe.internal.xmp.impl.xpath;

import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XMPPath {
    public static final int ARRAY_INDEX_STEP = 3;
    public static final int ARRAY_LAST_STEP = 4;
    public static final int FIELD_SELECTOR_STEP = 6;
    public static final int QUALIFIER_STEP = 2;
    public static final int QUAL_SELECTOR_STEP = 5;
    public static final int SCHEMA_NODE = Integer.MIN_VALUE;
    public static final int STEP_ROOT_PROP = 1;
    public static final int STEP_SCHEMA = 0;
    public static final int STRUCT_FIELD_STEP = 1;
    private List segments = new ArrayList(5);

    public void add(XMPPathSegment xMPPathSegment) {
        this.segments.add(xMPPathSegment);
    }

    public XMPPathSegment getSegment(int i2) {
        return (XMPPathSegment) this.segments.get(i2);
    }

    public int size() {
        return this.segments.size();
    }

    public String toString() {
        int kind;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 1; i2 < size(); i2++) {
            stringBuffer.append(getSegment(i2));
            if (i2 < size() - 1 && ((kind = getSegment(i2 + 1).getKind()) == 1 || kind == 2)) {
                stringBuffer.append('/');
            }
        }
        return stringBuffer.toString();
    }
}
