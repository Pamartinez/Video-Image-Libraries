package com.adobe.internal.xmp;

import com.adobe.internal.xmp.impl.Utils;
import com.adobe.internal.xmp.impl.xpath.XMPPath;
import com.adobe.internal.xmp.impl.xpath.XMPPathParser;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class XMPPathFactory {
    private XMPPathFactory() {
    }

    private static void assertFieldNS(String str) {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty field namespace URI", 101);
        }
    }

    private static void assertFieldName(String str) {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty f name", 102);
        }
    }

    private static void assertQualNS(String str) {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty qualifier namespace URI", 101);
        }
    }

    private static void assertQualName(String str) {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty qualifier name", 102);
        }
    }

    public static String composeArrayItemPath(String str, int i2) {
        if (i2 > 0) {
            return str + '[' + i2 + ']';
        } else if (i2 == -1) {
            return C0212a.A(str, "[last()]");
        } else {
            throw new XMPException("Array index must be larger than zero", 104);
        }
    }

    public static String composeFieldSelector(String str, String str2, String str3, String str4) {
        XMPPath expandXPath = XMPPathParser.expandXPath(str2, str3);
        if (expandXPath.size() == 2) {
            return str + '[' + expandXPath.getSegment(1).getName() + "=\"" + str4 + "\"]";
        }
        throw new XMPException("The fieldName name must be simple", 102);
    }

    public static String composeLangSelector(String str, String str2) {
        StringBuilder t = C0212a.t(str, "[?xml:lang=\"");
        t.append(Utils.normalizeLangValue(str2));
        t.append("\"]");
        return t.toString();
    }

    public static String composeQualifierPath(String str, String str2) {
        assertQualNS(str);
        assertQualName(str2);
        XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
        if (expandXPath.size() == 2) {
            return "/?" + expandXPath.getSegment(1).getName();
        }
        throw new XMPException("The qualifier name must be simple", 102);
    }

    public static String composeStructFieldPath(String str, String str2) {
        assertFieldNS(str);
        assertFieldName(str2);
        XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
        if (expandXPath.size() == 2) {
            return "/" + expandXPath.getSegment(1).getName();
        }
        throw new XMPException("The field name must be simple", 102);
    }
}
