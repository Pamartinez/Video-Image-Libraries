package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPConst;
import java.util.HashSet;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Utils implements XMPConst {
    static Set<String> EXTERNAL_XMPDM_PROPS = new HashSet<String>() {
        {
            add("xmpDM:album");
            add("xmpDM:altTapeName");
            add("xmpDM:altTimecode");
            add("xmpDM:artist");
            add("xmpDM:cameraAngle");
            add("xmpDM:cameraLabel");
            add("xmpDM:cameraModel");
            add("xmpDM:cameraMove");
            add("xmpDM:client");
            add("xmpDM:comment");
            add("xmpDM:composer");
            add("xmpDM:director");
            add("xmpDM:directorPhotography");
            add("xmpDM:engineer");
            add("xmpDM:genre");
            add("xmpDM:good");
            add("xmpDM:instrument");
            add("xmpDM:logComment");
            add("xmpDM:projectName");
            add("xmpDM:releaseDate");
            add("xmpDM:scene");
            add("xmpDM:shotDate");
            add("xmpDM:shotDay");
            add("xmpDM:shotLocation");
            add("xmpDM:shotName");
            add("xmpDM:shotNumber");
            add("xmpDM:shotSize");
            add("xmpDM:speakerPlacement");
            add("xmpDM:takeNumber");
            add("xmpDM:tapeName");
            add("xmpDM:trackNumber");
            add("xmpDM:videoAlphaMode");
            add("xmpDM:videoAlphaPremultipleColor");
        }
    };
    public static final int UUID_LENGTH = 36;
    public static final int UUID_SEGMENT_COUNT = 4;
    private static boolean[] xmlNameChars;
    private static boolean[] xmlNameStartChars;

    static {
        initCharTables();
    }

    private Utils() {
    }

    public static boolean checkUUIDFormat(String str) {
        if (str == null) {
            return false;
        }
        int i2 = 0;
        int i7 = 0;
        boolean z = true;
        while (i2 < str.length()) {
            if (str.charAt(i2) == '-') {
                i7++;
                if (!z || !(i2 == 8 || i2 == 13 || i2 == 18 || i2 == 23)) {
                    z = false;
                } else {
                    z = true;
                }
            }
            i2++;
        }
        if (z && 4 == i7 && 36 == i2) {
            return true;
        }
        return false;
    }

    public static String escapeXML(String str, boolean z, boolean z3) {
        String str2;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '<' || charAt == '>' || charAt == '&' || ((z3 && (charAt == 9 || charAt == 10 || charAt == 13)) || (z && charAt == '\"'))) {
                StringBuffer stringBuffer = new StringBuffer((str.length() * 4) / 3);
                for (int i7 = 0; i7 < str.length(); i7++) {
                    char charAt2 = str.charAt(i7);
                    if (z3 && (charAt2 == 9 || charAt2 == 10 || charAt2 == 13)) {
                        stringBuffer.append("&#x");
                        stringBuffer.append(Integer.toHexString(charAt2).toUpperCase());
                        stringBuffer.append(';');
                    } else if (charAt2 == '\"') {
                        if (z) {
                            str2 = "&quot;";
                        } else {
                            str2 = "\"";
                        }
                        stringBuffer.append(str2);
                    } else if (charAt2 == '&') {
                        stringBuffer.append("&amp;");
                    } else if (charAt2 == '<') {
                        stringBuffer.append("&lt;");
                    } else if (charAt2 != '>') {
                        stringBuffer.append(charAt2);
                    } else {
                        stringBuffer.append("&gt;");
                    }
                }
                return stringBuffer.toString();
            }
        }
        return str;
    }

    private static void initCharTables() {
        boolean z;
        xmlNameChars = new boolean[256];
        xmlNameStartChars = new boolean[256];
        char c5 = 0;
        while (true) {
            boolean[] zArr = xmlNameChars;
            if (c5 < zArr.length) {
                boolean[] zArr2 = xmlNameStartChars;
                boolean z3 = true;
                if (c5 == ':' || (('A' <= c5 && c5 <= 'Z') || c5 == '_' || (('a' <= c5 && c5 <= 'z') || ((192 <= c5 && c5 <= 214) || ((216 <= c5 && c5 <= 246) || (248 <= c5 && c5 <= 255)))))) {
                    z = true;
                } else {
                    z = false;
                }
                zArr2[c5] = z;
                if (!z && c5 != '-' && c5 != '.' && (('0' > c5 || c5 > '9') && c5 != 183)) {
                    z3 = false;
                }
                zArr[c5] = z3;
                c5 = (char) (c5 + 1);
            } else {
                return;
            }
        }
    }

    public static boolean isControlChar(char c5) {
        if ((c5 > 31 && c5 != 127) || c5 == 9 || c5 == 10 || c5 == 13) {
            return false;
        }
        return true;
    }

    public static boolean isInternalProperty(String str, String str2) {
        if (XMPConst.NS_DC.equals(str)) {
            if ("dc:format".equals(str2) || "dc:language".equals(str2)) {
                return true;
            }
        } else if (XMPConst.NS_XMP.equals(str)) {
            if ("xmp:BaseURL".equals(str2) || "xmp:CreatorTool".equals(str2) || "xmp:Format".equals(str2) || "xmp:Locale".equals(str2) || "xmp:MetadataDate".equals(str2) || "xmp:ModifyDate".equals(str2)) {
                return true;
            }
        } else if (XMPConst.NS_PDF.equals(str)) {
            if ("pdf:BaseURL".equals(str2) || "pdf:Creator".equals(str2) || "pdf:ModDate".equals(str2) || "pdf:PDFVersion".equals(str2) || "pdf:Producer".equals(str2)) {
                return true;
            }
        } else if (XMPConst.NS_TIFF.equals(str)) {
            if ("tiff:ImageDescription".equals(str2) || "tiff:Artist".equals(str2) || "tiff:Copyright".equals(str2)) {
                return false;
            }
            return true;
        } else if (XMPConst.NS_EXIF.equals(str)) {
            if ("exif:UserComment".equals(str2)) {
                return false;
            }
            return true;
        } else if (XMPConst.NS_EXIF_AUX.equals(str)) {
            return true;
        } else {
            if (XMPConst.NS_PHOTOSHOP.equals(str)) {
                if ("photoshop:ICCProfile".equals(str2) || "photoshop:TextLayers".equals(str2)) {
                    return true;
                }
            } else if (XMPConst.NS_DM.equals(str)) {
                return !EXTERNAL_XMPDM_PROPS.contains(str2);
            } else {
                if (XMPConst.NS_CAMERARAW.equals(str)) {
                    return true;
                }
                if (XMPConst.NS_SCRIPT.equals(str)) {
                    if ("xmpScript:action".equals(str2) || "xmpScript:character".equals(str2) || "xmpScript:dialog".equals(str2) || "xmpScript:sceneSetting".equals(str2) || "xmpScript:sceneTimeOfDay".equals(str2)) {
                        return false;
                    }
                    return true;
                } else if (XMPConst.NS_BWF.equals(str)) {
                    if ("bext:version".equals(str2)) {
                        return true;
                    }
                } else if (!XMPConst.NS_ADOBESTOCKPHOTO.equals(str) && !XMPConst.NS_XMP_MM.equals(str) && !XMPConst.TYPE_TEXT.equals(str) && !XMPConst.TYPE_PAGEDFILE.equals(str) && !XMPConst.TYPE_GRAPHICS.equals(str) && !XMPConst.TYPE_IMAGE.equals(str) && !XMPConst.TYPE_FONT.equals(str)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isNameChar(char c5) {
        if ((c5 <= 255 && xmlNameChars[c5]) || isNameStartChar(c5)) {
            return true;
        }
        if (c5 >= 768 && c5 <= 879) {
            return true;
        }
        if (c5 < 8255 || c5 > 8256) {
            return false;
        }
        return true;
    }

    private static boolean isNameStartChar(char c5) {
        if (c5 <= 255 && xmlNameStartChars[c5]) {
            return true;
        }
        if (c5 >= 256 && c5 <= 767) {
            return true;
        }
        if (c5 >= 880 && c5 <= 893) {
            return true;
        }
        if (c5 >= 895 && c5 <= 8191) {
            return true;
        }
        if (c5 >= 8204 && c5 <= 8205) {
            return true;
        }
        if (c5 >= 8304 && c5 <= 8591) {
            return true;
        }
        if (c5 >= 11264 && c5 <= 12271) {
            return true;
        }
        if (c5 >= 12289 && c5 <= 55295) {
            return true;
        }
        if (c5 >= 63744 && c5 <= 64975) {
            return true;
        }
        if (c5 >= 65008 && c5 <= 65533) {
            return true;
        }
        if (c5 < 0 || c5 > 65535) {
            return false;
        }
        return true;
    }

    public static boolean isXMLName(String str) {
        if (str.length() > 0 && !isNameStartChar(str.charAt(0))) {
            return false;
        }
        for (int i2 = 1; i2 < str.length(); i2++) {
            if (!isNameChar(str.charAt(i2))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isXMLNameNS(String str) {
        if (str.length() > 0 && (!isNameStartChar(str.charAt(0)) || str.charAt(0) == ':')) {
            return false;
        }
        for (int i2 = 1; i2 < str.length(); i2++) {
            if (!isNameChar(str.charAt(i2)) || str.charAt(i2) == ':') {
                return false;
            }
        }
        return true;
    }

    public static String normalizeLangValue(String str) {
        if (XMPConst.X_DEFAULT.equals(str)) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = 1;
        for (int i7 = 0; i7 < str.length(); i7++) {
            char charAt = str.charAt(i7);
            if (charAt != ' ') {
                if (charAt == '-' || charAt == '_') {
                    stringBuffer.append('-');
                    i2++;
                } else if (i2 != 2) {
                    stringBuffer.append(Character.toLowerCase(str.charAt(i7)));
                } else {
                    stringBuffer.append(Character.toUpperCase(str.charAt(i7)));
                }
            }
        }
        return stringBuffer.toString();
    }

    public static String removeControlChars(String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        for (int i2 = 0; i2 < stringBuffer.length(); i2++) {
            if (isControlChar(stringBuffer.charAt(i2))) {
                stringBuffer.setCharAt(i2, ' ');
            }
        }
        return stringBuffer.toString();
    }

    public static String[] splitNameAndValue(String str) {
        int indexOf = str.indexOf(61);
        int i2 = 1;
        if (str.charAt(1) == '?') {
            i2 = 2;
        }
        String substring = str.substring(i2, indexOf);
        char charAt = str.charAt(indexOf + 1);
        int i7 = indexOf + 2;
        int length = str.length() - 2;
        StringBuffer stringBuffer = new StringBuffer(length - indexOf);
        while (i7 < length) {
            stringBuffer.append(str.charAt(i7));
            int i8 = i7 + 1;
            if (str.charAt(i8) == charAt) {
                i7 += 2;
            } else {
                i7 = i8;
            }
        }
        return new String[]{substring, stringBuffer.toString()};
    }
}
