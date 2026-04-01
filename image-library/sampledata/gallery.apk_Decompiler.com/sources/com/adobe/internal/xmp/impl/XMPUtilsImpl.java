package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPConst;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.impl.xpath.XMPPath;
import com.adobe.internal.xmp.impl.xpath.XMPPathParser;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.adobe.internal.xmp.options.TemplateOptions;
import com.adobe.internal.xmp.properties.XMPAliasInfo;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XMPUtilsImpl implements XMPConst {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String COMMAS = ",，､﹐﹑、،՝";
    private static final String CONTROLS = "  ";
    private static final String QUOTES = "\"«»〝〞〟―‹›";
    private static final String SEMICOLA = ";；﹔؛;";
    private static final String SPACES = " 　〿";
    private static final int UCK_COMMA = 2;
    private static final int UCK_CONTROL = 5;
    private static final int UCK_NORMAL = 0;
    private static final int UCK_QUOTE = 4;
    private static final int UCK_SEMICOLON = 3;
    private static final int UCK_SPACE = 1;

    private XMPUtilsImpl() {
    }

    public static void appendProperties(XMPMeta xMPMeta, XMPMeta xMPMeta2, boolean z, boolean z3, boolean z7) {
        boolean z9;
        boolean z10;
        boolean z11;
        ParameterAsserts.assertImplementation(xMPMeta);
        ParameterAsserts.assertImplementation(xMPMeta2);
        XMPMetaImpl xMPMetaImpl = (XMPMetaImpl) xMPMeta2;
        Iterator iterateChildren = ((XMPMetaImpl) xMPMeta).getRoot().iterateChildren();
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode = (XMPNode) iterateChildren.next();
            XMPNode findSchemaNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.getRoot(), xMPNode.getName(), false);
            if (findSchemaNode == null) {
                findSchemaNode = new XMPNode(xMPNode.getName(), xMPNode.getValue(), new PropertyOptions().setSchemaNode(true));
                xMPMetaImpl.getRoot().addChild(findSchemaNode);
                z9 = true;
            } else {
                z9 = false;
            }
            XMPNode xMPNode2 = findSchemaNode;
            Iterator iterateChildren2 = xMPNode.iterateChildren();
            while (iterateChildren2.hasNext()) {
                XMPNode xMPNode3 = (XMPNode) iterateChildren2.next();
                if (z || !Utils.isInternalProperty(xMPNode.getName(), xMPNode3.getName())) {
                    z11 = z3;
                    z10 = z7;
                    appendSubtree(xMPMetaImpl, xMPNode3, xMPNode2, false, z11, z10);
                } else {
                    z11 = z3;
                    z10 = z7;
                }
                z3 = z11;
                z7 = z10;
            }
            boolean z12 = z3;
            boolean z13 = z7;
            if (!xMPNode2.hasChildren() && (z9 || z13)) {
                xMPMetaImpl.getRoot().removeChild(xMPNode2);
            }
            z3 = z12;
            z7 = z13;
        }
    }

    private static void appendSubtree(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, XMPNode xMPNode2, boolean z, boolean z3, boolean z7) {
        boolean z9;
        boolean z10;
        XMPNode xMPNode3;
        XMPNode findChildNode = XMPNodeUtils.findChildNode(xMPNode2, xMPNode.getName(), false);
        if (!xMPNode.getOptions().isSimple() ? xMPNode.hasChildren() : !(xMPNode.getValue() == null || xMPNode.getValue().length() == 0)) {
            if (findChildNode == null) {
                XMPNode xMPNode4 = (XMPNode) xMPNode.clone(true);
                if (xMPNode4 != null) {
                    xMPNode2.addChild(xMPNode4);
                    return;
                }
                return;
            }
            PropertyOptions options = xMPNode.getOptions();
            if (!z || options.isSimple()) {
                z9 = z3;
            } else {
                z9 = false;
            }
            if (z9) {
                xMPNode2.removeChild(findChildNode);
                XMPNode xMPNode5 = (XMPNode) xMPNode.clone(true);
                if (xMPNode5 != null) {
                    xMPNode2.addChild(xMPNode5);
                    return;
                }
                return;
            }
            if (options.getOptions() == findChildNode.getOptions().getOptions() && !options.isSimple()) {
                if (options.isStruct()) {
                    Iterator iterateChildren = xMPNode.iterateChildren();
                    while (iterateChildren.hasNext()) {
                        XMPMetaImpl xMPMetaImpl2 = xMPMetaImpl;
                        boolean z11 = z;
                        boolean z12 = z3;
                        boolean z13 = z7;
                        appendSubtree(xMPMetaImpl2, (XMPNode) iterateChildren.next(), findChildNode, z11, z12, z13);
                        if (z13 && !findChildNode.hasChildren()) {
                            xMPNode2.removeChild(findChildNode);
                        }
                        xMPMetaImpl = xMPMetaImpl2;
                        z = z11;
                        z3 = z12;
                        z7 = z13;
                    }
                    return;
                }
                boolean z14 = z3;
                boolean z15 = z7;
                if (options.isArrayAltText()) {
                    Iterator iterateChildren2 = xMPNode.iterateChildren();
                    while (iterateChildren2.hasNext()) {
                        XMPNode xMPNode6 = (XMPNode) iterateChildren2.next();
                        if (xMPNode6.hasQualifier() && XMPConst.XML_LANG.equals(xMPNode6.getQualifier(1).getName())) {
                            int lookupLanguageItem = XMPNodeUtils.lookupLanguageItem(findChildNode, xMPNode6.getQualifier(1).getValue());
                            if (xMPNode6.getValue() == null || xMPNode6.getValue().length() == 0) {
                                if (z15 && lookupLanguageItem != -1) {
                                    findChildNode.removeChild(lookupLanguageItem);
                                    if (!findChildNode.hasChildren()) {
                                        xMPNode2.removeChild(findChildNode);
                                    }
                                }
                            } else if (lookupLanguageItem == -1) {
                                if (!XMPConst.X_DEFAULT.equals(xMPNode6.getQualifier(1).getValue()) || !findChildNode.hasChildren()) {
                                    XMPNode xMPNode7 = (XMPNode) xMPNode6.clone(true);
                                    if (xMPNode7 != null) {
                                        findChildNode.addChild(xMPNode7);
                                    }
                                } else {
                                    XMPNode xMPNode8 = new XMPNode(xMPNode6.getName(), xMPNode6.getValue(), xMPNode6.getOptions());
                                    xMPNode6.cloneSubtree(xMPNode8, true);
                                    findChildNode.addChild(1, xMPNode8);
                                }
                            } else if (z14) {
                                findChildNode.getChild(lookupLanguageItem).setValue(xMPNode6.getValue());
                            }
                        }
                    }
                } else if (options.isArray()) {
                    Iterator iterateChildren3 = xMPNode.iterateChildren();
                    while (iterateChildren3.hasNext()) {
                        XMPNode xMPNode9 = (XMPNode) iterateChildren3.next();
                        Iterator iterateChildren4 = findChildNode.iterateChildren();
                        while (true) {
                            if (iterateChildren4.hasNext()) {
                                if (itemValuesMatch(xMPNode9, (XMPNode) iterateChildren4.next())) {
                                    z10 = true;
                                    break;
                                }
                            } else {
                                z10 = false;
                                break;
                            }
                        }
                        if (!z10 && (xMPNode3 = (XMPNode) xMPNode9.clone(true)) != null) {
                            findChildNode.addChild(xMPNode3);
                        }
                    }
                }
            }
        } else if (z7 && findChildNode != null) {
            xMPNode2.removeChild(findChildNode);
        }
    }

    private static String applyQuotes(String str, char c5, char c6, boolean z) {
        if (str == null) {
            str = "";
        }
        int i2 = 0;
        boolean z3 = false;
        while (i2 < str.length()) {
            int classifyCharacter = classifyCharacter(str.charAt(i2));
            if (i2 == 0 && classifyCharacter == 4) {
                break;
            }
            if (classifyCharacter != 1) {
                if (classifyCharacter == 3 || classifyCharacter == 5 || (classifyCharacter == 2 && !z)) {
                    break;
                }
                z3 = false;
            } else if (z3) {
                break;
            } else {
                z3 = true;
            }
            i2++;
        }
        if (i2 >= str.length()) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 2);
        int i7 = 0;
        while (i7 <= i2 && classifyCharacter(str.charAt(i2)) != 4) {
            i7++;
        }
        stringBuffer.append(c5);
        stringBuffer.append(str.substring(0, i7));
        while (i7 < str.length()) {
            stringBuffer.append(str.charAt(i7));
            if (classifyCharacter(str.charAt(i7)) == 4 && isSurroundingQuote(str.charAt(i7), c5, c6)) {
                stringBuffer.append(str.charAt(i7));
            }
            i7++;
        }
        stringBuffer.append(c6);
        return stringBuffer.toString();
    }

    public static void applyTemplate(XMPMeta xMPMeta, XMPMeta xMPMeta2, TemplateOptions templateOptions) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        XMPMetaImpl xMPMetaImpl = (XMPMetaImpl) xMPMeta;
        XMPMetaImpl xMPMetaImpl2 = (XMPMetaImpl) xMPMeta2;
        boolean z11 = false;
        if ((templateOptions.getOptions() & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        if ((templateOptions.getOptions() & 64) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        if ((templateOptions.getOptions() & 16) != 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        if ((templateOptions.getOptions() & 128) != 0) {
            z9 = true;
        } else {
            z9 = false;
        }
        boolean z12 = z7 | z9;
        boolean z13 = (!z) & z9;
        if ((templateOptions.getOptions() & 32) != 0) {
            z10 = true;
        } else {
            z10 = false;
        }
        if (z) {
            for (int childrenLength = xMPMetaImpl.getRoot().getChildrenLength(); childrenLength > 0; childrenLength--) {
                XMPNode child = xMPMetaImpl.getRoot().getChild(childrenLength);
                XMPNode findSchemaNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl2.getRoot(), child.getName(), false);
                if (findSchemaNode != null) {
                    for (int childrenLength2 = child.getChildrenLength(); childrenLength2 > 0; childrenLength2--) {
                        XMPNode child2 = child.getChild(childrenLength2);
                        if ((z10 || !Utils.isInternalProperty(child.getName(), child2.getName())) && XMPNodeUtils.findChildNode(findSchemaNode, child2.getName(), false) == null) {
                            child.removeChild(childrenLength2);
                        }
                    }
                } else if (z10) {
                    child.removeChildren();
                } else {
                    for (int childrenLength3 = child.getChildrenLength(); childrenLength3 > 0; childrenLength3--) {
                        if (!Utils.isInternalProperty(child.getName(), child.getChild(childrenLength3).getName())) {
                            child.removeChild(childrenLength3);
                        }
                    }
                }
                if (!child.hasChildren()) {
                    xMPMetaImpl.getRoot().removeChild(childrenLength);
                }
            }
        }
        if (z3 || z12) {
            int childrenLength4 = xMPMetaImpl2.getRoot().getChildrenLength();
            int i2 = 0;
            while (i2 < childrenLength4) {
                int i7 = i2 + 1;
                XMPNode child3 = xMPMetaImpl2.getRoot().getChild(i7);
                XMPNode findSchemaNode2 = XMPNodeUtils.findSchemaNode(xMPMetaImpl.getRoot(), child3.getName(), z11);
                if (findSchemaNode2 == null) {
                    findSchemaNode2 = new XMPNode(child3.getName(), child3.getValue(), new PropertyOptions(Integer.MIN_VALUE));
                    xMPMetaImpl.getRoot().addChild(findSchemaNode2);
                    findSchemaNode2.setParent(xMPMetaImpl.getRoot());
                }
                int childrenLength5 = child3.getChildrenLength();
                int i8 = 1;
                while (i8 <= childrenLength5) {
                    boolean z14 = z12;
                    XMPNode xMPNode = findSchemaNode2;
                    XMPNode child4 = child3.getChild(i8);
                    if (z10 || !Utils.isInternalProperty(child3.getName(), child4.getName())) {
                        appendSubtree(xMPMetaImpl, child4, xMPNode, z3, z14, z13);
                    }
                    i8++;
                    findSchemaNode2 = xMPNode;
                    z12 = z14;
                }
                boolean z15 = z12;
                XMPNode xMPNode2 = findSchemaNode2;
                if (!xMPNode2.hasChildren()) {
                    xMPMetaImpl.getRoot().removeChild(xMPNode2);
                }
                z12 = z15;
                i2 = i7;
                z11 = false;
            }
        }
    }

    public static String catenateArrayItems(XMPMeta xMPMeta, String str, String str2, String str3, String str4, boolean z) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        ParameterAsserts.assertImplementation(xMPMeta);
        if (str3 == null || str3.length() == 0) {
            str3 = "; ";
        }
        if (str4 == null || str4.length() == 0) {
            str4 = "\"";
        }
        XMPNode findNode = XMPNodeUtils.findNode(((XMPMetaImpl) xMPMeta).getRoot(), XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode == null) {
            return "";
        }
        if (!findNode.getOptions().isArray() || findNode.getOptions().isArrayAlternate()) {
            throw new XMPException("Named property must be non-alternate array", 4);
        }
        checkSeparator(str3);
        char charAt = str4.charAt(0);
        char checkQuotes = checkQuotes(str4, charAt);
        StringBuffer stringBuffer = new StringBuffer();
        Iterator iterateChildren = findNode.iterateChildren();
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode = (XMPNode) iterateChildren.next();
            if (!xMPNode.getOptions().isCompositeProperty()) {
                stringBuffer.append(applyQuotes(xMPNode.getValue(), charAt, checkQuotes, z));
                if (iterateChildren.hasNext()) {
                    stringBuffer.append(str3);
                }
            } else {
                throw new XMPException("Array items must be simple", 4);
            }
        }
        return stringBuffer.toString();
    }

    private static char checkQuotes(String str, char c5) {
        char c6;
        if (classifyCharacter(c5) == 4) {
            if (str.length() == 1) {
                c6 = c5;
            } else {
                c6 = str.charAt(1);
                if (classifyCharacter(c6) != 4) {
                    throw new XMPException("Invalid quoting character", 4);
                }
            }
            if (c6 == getClosingQuote(c5)) {
                return c6;
            }
            throw new XMPException("Mismatched quote pair", 4);
        }
        throw new XMPException("Invalid quoting character", 4);
    }

    private static void checkSeparator(String str) {
        boolean z = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            int classifyCharacter = classifyCharacter(str.charAt(i2));
            if (classifyCharacter == 3) {
                if (!z) {
                    z = true;
                } else {
                    throw new XMPException("Separator can have only one semicolon", 4);
                }
            } else if (classifyCharacter != 1) {
                throw new XMPException("Separator can have only spaces and one semicolon", 4);
            }
        }
        if (!z) {
            throw new XMPException("Separator must have one semicolon", 4);
        }
    }

    private static int classifyCharacter(char c5) {
        if (SPACES.indexOf(c5) >= 0) {
            return 1;
        }
        if (8192 <= c5 && c5 <= 8203) {
            return 1;
        }
        if (COMMAS.indexOf(c5) >= 0) {
            return 2;
        }
        if (SEMICOLA.indexOf(c5) >= 0) {
            return 3;
        }
        if (QUOTES.indexOf(c5) >= 0) {
            return 4;
        }
        if (12296 <= c5 && c5 <= 12303) {
            return 4;
        }
        if (8216 <= c5 && c5 <= 8223) {
            return 4;
        }
        if (c5 < ' ' || CONTROLS.indexOf(c5) >= 0) {
            return 5;
        }
        return 0;
    }

    public static void createEstimatedSizeMap(XMPMetaImpl xMPMetaImpl, Map<Integer, List<List<String>>> map) {
        for (int childrenLength = xMPMetaImpl.getRoot().getChildrenLength(); childrenLength > 0; childrenLength--) {
            XMPNode child = xMPMetaImpl.getRoot().getChild(childrenLength);
            for (int childrenLength2 = child.getChildrenLength(); childrenLength2 > 0; childrenLength2--) {
                XMPNode child2 = child.getChild(childrenLength2);
                if (!child.getName().equals(XMPConst.NS_XMP_NOTE) || !child2.getName().equals("xmpNote:HasExtendedXMP")) {
                    int estimateSizeForJPEG = estimateSizeForJPEG(child2);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(child.getName());
                    arrayList.add(child2.getName());
                    putObjectsInMultiMap(map, Integer.valueOf(estimateSizeForJPEG), arrayList);
                }
            }
        }
    }

    public static void duplicateSubtree(XMPMeta xMPMeta, XMPMeta xMPMeta2, String str, String str2, String str3, String str4, PropertyOptions propertyOptions) {
        ParameterAsserts.assertNotNull(xMPMeta);
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertSchemaNS(str2);
        ParameterAsserts.assertNotNull(xMPMeta2);
        ParameterAsserts.assertNotNull(str3);
        ParameterAsserts.assertNotNull(str4);
        if (str3.length() == 0) {
            str3 = str;
        }
        if (str4.length() == 0) {
            str4 = str2;
        }
        boolean equals = str.equals("*");
        boolean equals2 = str3.equals("*");
        if (xMPMeta == xMPMeta2 && (equals || equals2)) {
            throw new XMPException("Can't duplicate tree onto itself", 4);
        } else if (equals && equals2) {
            throw new XMPException("Use Clone for full tree to full tree", 4);
        } else if (equals) {
            XMPNode findNode = XMPNodeUtils.findNode(((XMPMetaImpl) xMPMeta2).getRoot(), XMPPathParser.expandXPath(str3, str4), false, (PropertyOptions) null);
            if (findNode == null || !findNode.getOptions().isStruct()) {
                throw new XMPException("Destination must be an existing struct", 102);
            }
            if (findNode.hasChildren()) {
                if (propertyOptions == null || (propertyOptions.getOptions() & PropertyOptions.DELETE_EXISTING) == 0) {
                    throw new XMPException("Destination must be an empty struct", 102);
                }
                findNode.removeChildren();
            }
            XMPMetaImpl xMPMetaImpl = (XMPMetaImpl) xMPMeta;
            int childrenLength = xMPMetaImpl.getRoot().getChildrenLength();
            for (int i2 = 1; i2 <= childrenLength; i2++) {
                XMPNode child = xMPMetaImpl.getRoot().getChild(i2);
                int childrenLength2 = child.getChildrenLength();
                for (int i7 = 1; i7 <= childrenLength2; i7++) {
                    findNode.addChild((XMPNode) child.getChild(i7).clone(false));
                }
            }
        } else if (equals2) {
            XMPMetaImpl xMPMetaImpl2 = (XMPMetaImpl) xMPMeta2;
            XMPNode findNode2 = XMPNodeUtils.findNode(((XMPMetaImpl) xMPMeta).getRoot(), XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
            if (findNode2 == null || !findNode2.getOptions().isStruct()) {
                throw new XMPException("Source must be an existing struct", 102);
            }
            XMPNode root = xMPMetaImpl2.getRoot();
            if (root.hasChildren()) {
                if (propertyOptions == null || (propertyOptions.getOptions() & PropertyOptions.DELETE_EXISTING) == 0) {
                    throw new XMPException("Source must be an existing struct", 102);
                }
                root.removeChildren();
            }
            int childrenLength3 = findNode2.getChildrenLength();
            for (int i8 = 1; i8 <= childrenLength3; i8++) {
                XMPNode child2 = findNode2.getChild(i8);
                int indexOf = child2.getName().indexOf(58);
                if (indexOf != -1) {
                    String namespaceURI = XMPMetaFactory.getSchemaRegistry().getNamespaceURI(new String(child2.getName().substring(0, indexOf + 1)));
                    if (namespaceURI != null) {
                        XMPNode findSchemaNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl2.getRoot(), namespaceURI, true);
                        if (findSchemaNode != null) {
                            findSchemaNode.addChild((XMPNode) child2.clone(false));
                        } else {
                            throw new XMPException("Failed to find destination schema", 101);
                        }
                    } else {
                        throw new XMPException("Source field namespace is not global", 101);
                    }
                }
            }
        } else {
            XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
            XMPPath expandXPath2 = XMPPathParser.expandXPath(str3, str4);
            XMPMetaImpl xMPMetaImpl3 = (XMPMetaImpl) xMPMeta2;
            XMPNode findNode3 = XMPNodeUtils.findNode(((XMPMetaImpl) xMPMeta).getRoot(), expandXPath, false, (PropertyOptions) null);
            if (findNode3 == null) {
                throw new XMPException("Can't find source subtree", 102);
            } else if (XMPNodeUtils.findNode(xMPMetaImpl3.getRoot(), expandXPath2, false, (PropertyOptions) null) == null) {
                XMPNode findNode4 = XMPNodeUtils.findNode(xMPMetaImpl3.getRoot(), expandXPath2, true, (PropertyOptions) null);
                if (findNode4 != null) {
                    if (xMPMeta == xMPMeta2) {
                        XMPNode xMPNode = findNode4;
                        while (xMPNode != null) {
                            if (xMPNode != findNode3) {
                                xMPNode = xMPNode.getParent();
                            } else {
                                throw new XMPException("Destination subtree is within the source subtree", 102);
                            }
                        }
                    }
                    findNode4.setValue(findNode3.getValue());
                    findNode4.setOptions(findNode3.getOptions());
                    findNode3.cloneSubtree(findNode4, false);
                    return;
                }
                throw new XMPException("Can't create destination root node", 102);
            } else {
                throw new XMPException("Destination subtree must not exist", 102);
            }
        }
    }

    public static int estimateSizeForJPEG(XMPNode xMPNode) {
        int length = xMPNode.getName().length();
        boolean isArray = xMPNode.getOptions().isArray();
        int i2 = 0;
        if (xMPNode.getOptions().isSimple()) {
            if (!isArray) {
                i2 = length + 3;
            }
            return xMPNode.getValue().length() + i2;
        }
        int i7 = 1;
        if (xMPNode.getOptions().isArray()) {
            if (!isArray) {
                i2 = (length * 2) + 5;
            }
            int childrenLength = xMPNode.getChildrenLength();
            int i8 = (childrenLength * 17) + i2 + 19;
            while (i7 <= childrenLength) {
                i8 += estimateSizeForJPEG(xMPNode.getChild(i7));
                i7++;
            }
            return i8;
        }
        if (!isArray) {
            i2 = (length * 2) + 5;
        }
        int i10 = i2 + 25;
        int childrenLength2 = xMPNode.getChildrenLength();
        while (i7 <= childrenLength2) {
            i10 += estimateSizeForJPEG(xMPNode.getChild(i7));
            i7++;
        }
        return i10;
    }

    private static List<String> getBiggestEntryInMultiMap(Map<Integer, List<List<String>>> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        TreeMap treeMap = (TreeMap) map;
        List list = map.get(treeMap.lastKey());
        List<String> list2 = (List) list.get(0);
        list.remove(0);
        if (list.isEmpty()) {
            map.remove(treeMap.lastKey());
        }
        return list2;
    }

    private static char getClosingQuote(char c5) {
        switch (c5) {
            case '\"':
                return '\"';
            case 171:
                return 187;
            case 187:
                return 171;
            case 8213:
                return 8213;
            case 8216:
                return 8217;
            case 8218:
                return 8219;
            case 8220:
                return 8221;
            case 8222:
                return 8223;
            case 8249:
                return 8250;
            case 8250:
                return 8249;
            case 12296:
                return 12297;
            case 12298:
                return 12299;
            case 12300:
                return 12301;
            case 12302:
                return 12303;
            case 12317:
                return 12319;
            default:
                return 0;
        }
    }

    private static boolean isClosingingQuote(char c5, char c6, char c8) {
        if (c5 == c8) {
            return true;
        }
        if ((c6 == 12317 && c5 == 12318) || c5 == 12319) {
            return true;
        }
        return false;
    }

    private static boolean isSurroundingQuote(char c5, char c6, char c8) {
        if (c5 == c6 || isClosingingQuote(c5, c6, c8)) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean itemValuesMatch(com.adobe.internal.xmp.impl.XMPNode r5, com.adobe.internal.xmp.impl.XMPNode r6) {
        /*
            com.adobe.internal.xmp.options.PropertyOptions r0 = r5.getOptions()
            com.adobe.internal.xmp.options.PropertyOptions r1 = r6.getOptions()
            boolean r1 = r0.equals(r1)
            r2 = 0
            if (r1 != 0) goto L_0x0010
            return r2
        L_0x0010:
            boolean r1 = r0.isSimple()
            r3 = 1
            if (r1 == 0) goto L_0x005a
            java.lang.String r0 = r5.getValue()
            java.lang.String r1 = r6.getValue()
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0026
            return r2
        L_0x0026:
            com.adobe.internal.xmp.options.PropertyOptions r0 = r5.getOptions()
            boolean r0 = r0.getHasLanguage()
            com.adobe.internal.xmp.options.PropertyOptions r1 = r6.getOptions()
            boolean r1 = r1.getHasLanguage()
            if (r0 == r1) goto L_0x0039
            return r2
        L_0x0039:
            com.adobe.internal.xmp.options.PropertyOptions r0 = r5.getOptions()
            boolean r0 = r0.getHasLanguage()
            if (r0 == 0) goto L_0x00b4
            com.adobe.internal.xmp.impl.XMPNode r5 = r5.getQualifier(r3)
            java.lang.String r5 = r5.getValue()
            com.adobe.internal.xmp.impl.XMPNode r6 = r6.getQualifier(r3)
            java.lang.String r6 = r6.getValue()
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x00b4
            return r2
        L_0x005a:
            boolean r0 = r0.isStruct()
            if (r0 == 0) goto L_0x008c
            int r0 = r5.getChildrenLength()
            int r1 = r6.getChildrenLength()
            if (r0 == r1) goto L_0x006b
            return r2
        L_0x006b:
            java.util.Iterator r5 = r5.iterateChildren()
        L_0x006f:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x00b4
            java.lang.Object r0 = r5.next()
            com.adobe.internal.xmp.impl.XMPNode r0 = (com.adobe.internal.xmp.impl.XMPNode) r0
            java.lang.String r1 = r0.getName()
            com.adobe.internal.xmp.impl.XMPNode r1 = com.adobe.internal.xmp.impl.XMPNodeUtils.findChildNode(r6, r1, r2)
            if (r1 == 0) goto L_0x008b
            boolean r0 = itemValuesMatch(r0, r1)
            if (r0 != 0) goto L_0x006f
        L_0x008b:
            return r2
        L_0x008c:
            java.util.Iterator r5 = r5.iterateChildren()
        L_0x0090:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x00b4
            java.lang.Object r0 = r5.next()
            com.adobe.internal.xmp.impl.XMPNode r0 = (com.adobe.internal.xmp.impl.XMPNode) r0
            java.util.Iterator r1 = r6.iterateChildren()
        L_0x00a0:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x00b3
            java.lang.Object r4 = r1.next()
            com.adobe.internal.xmp.impl.XMPNode r4 = (com.adobe.internal.xmp.impl.XMPNode) r4
            boolean r4 = itemValuesMatch(r0, r4)
            if (r4 == 0) goto L_0x00a0
            goto L_0x0090
        L_0x00b3:
            return r2
        L_0x00b4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.XMPUtilsImpl.itemValuesMatch(com.adobe.internal.xmp.impl.XMPNode, com.adobe.internal.xmp.impl.XMPNode):boolean");
    }

    public static void mergeFromJPEG(XMPMeta xMPMeta, XMPMeta xMPMeta2) {
        applyTemplate((XMPMetaImpl) xMPMeta, (XMPMetaImpl) xMPMeta2, new TemplateOptions(48));
        xMPMeta.deleteProperty(XMPConst.NS_XMP_NOTE, "HasExtendedXMP");
    }

    public static int moveLargestProperty(XMPMetaImpl xMPMetaImpl, XMPMetaImpl xMPMetaImpl2, Map<Integer, List<List<String>>> map) {
        int intValue = ((Integer) ((TreeMap) map).lastKey()).intValue();
        List<String> biggestEntryInMultiMap = getBiggestEntryInMultiMap(map);
        moveOneProperty(xMPMetaImpl, xMPMetaImpl2, biggestEntryInMultiMap.get(0), biggestEntryInMultiMap.get(1));
        return intValue;
    }

    public static boolean moveOneProperty(XMPMetaImpl xMPMetaImpl, XMPMetaImpl xMPMetaImpl2, String str, String str2) {
        XMPNode xMPNode;
        XMPNode findSchemaNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.getRoot(), str, false);
        if (findSchemaNode != null) {
            xMPNode = XMPNodeUtils.findChildNode(findSchemaNode, str2, false);
        } else {
            xMPNode = null;
        }
        if (xMPNode == null) {
            return false;
        }
        XMPNode findSchemaNode2 = XMPNodeUtils.findSchemaNode(xMPMetaImpl2.getRoot(), str, true);
        xMPNode.setParent(findSchemaNode2);
        findSchemaNode2.setImplicit(false);
        findSchemaNode2.addChild(xMPNode);
        findSchemaNode.removeChild(xMPNode);
        if (!findSchemaNode.hasChildren()) {
            findSchemaNode.getParent().removeChild(findSchemaNode);
        }
        return true;
    }

    public static void packageForJPEG(XMPMeta xMPMeta, StringBuilder sb2, StringBuilder sb3, StringBuilder sb4) {
        XMPMetaImpl xMPMetaImpl = (XMPMetaImpl) xMPMeta;
        XMPMetaImpl xMPMetaImpl2 = new XMPMetaImpl();
        XMPMetaImpl xMPMetaImpl3 = new XMPMetaImpl();
        SerializeOptions serializeOptions = new SerializeOptions(64);
        serializeOptions.setPadding(0);
        serializeOptions.setIndent("");
        serializeOptions.setBaseIndent(0);
        serializeOptions.setNewline(" ");
        String serializeToString = XMPMetaFactory.serializeToString(xMPMetaImpl, serializeOptions);
        if (serializeToString.length() > 65000) {
            xMPMetaImpl2.getRoot().setOptions(xMPMetaImpl.getRoot().getOptions());
            xMPMetaImpl2.getRoot().setName(xMPMetaImpl.getRoot().getName());
            xMPMetaImpl2.getRoot().setValue(xMPMetaImpl.getRoot().getValue());
            xMPMetaImpl.getRoot().cloneSubtree(xMPMetaImpl2.getRoot(), false);
            if (xMPMetaImpl2.doesPropertyExist(XMPConst.NS_XMP, "Thumbnails")) {
                xMPMetaImpl2.deleteProperty(XMPConst.NS_XMP, "Thumbnails");
                serializeToString = XMPMetaFactory.serializeToString(xMPMetaImpl2, serializeOptions);
            }
        }
        if (serializeToString.length() > 65000) {
            xMPMetaImpl2.setProperty(XMPConst.NS_XMP_NOTE, "HasExtendedXMP", "123456789-123456789-123456789-12", new PropertyOptions(0));
            XMPNode findSchemaNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl2.getRoot(), XMPConst.NS_CAMERARAW, false);
            if (findSchemaNode != null) {
                findSchemaNode.setParent(xMPMetaImpl3.getRoot());
                xMPMetaImpl3.getRoot().addChild(findSchemaNode);
                xMPMetaImpl2.getRoot().removeChild(findSchemaNode);
                serializeToString = XMPMetaFactory.serializeToString(xMPMetaImpl2, serializeOptions);
            }
        }
        if (serializeToString.length() > 65000 && moveOneProperty(xMPMetaImpl2, xMPMetaImpl3, XMPConst.NS_PHOTOSHOP, "photoshop:History")) {
            serializeToString = XMPMetaFactory.serializeToString(xMPMetaImpl2, serializeOptions);
        }
        if (serializeToString.length() > 65000) {
            TreeMap treeMap = new TreeMap();
            createEstimatedSizeMap(xMPMetaImpl2, treeMap);
            while (serializeToString.length() > 65000 && !treeMap.isEmpty()) {
                int length = serializeToString.length();
                while (length > 65000 && !treeMap.isEmpty()) {
                    int moveLargestProperty = moveLargestProperty(xMPMetaImpl2, xMPMetaImpl3, treeMap);
                    if (moveLargestProperty > length) {
                        moveLargestProperty = length;
                    }
                    length -= moveLargestProperty;
                }
                serializeToString = XMPMetaFactory.serializeToString(xMPMetaImpl2, serializeOptions);
            }
        }
        if (serializeToString.length() <= 65000) {
            if (xMPMetaImpl3.getRoot().getChildrenLength() == 0) {
                sb2.append(serializeToString);
            } else {
                String serializeToString2 = XMPMetaFactory.serializeToString(xMPMetaImpl3, new SerializeOptions(80));
                sb3.append(serializeToString2);
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(serializeToString2.getBytes());
                byte[] digest = instance.digest();
                for (byte b : digest) {
                    sb4.append(Integer.toString((b & 255) + 256, 16).substring(1));
                }
                xMPMetaImpl2.setProperty(XMPConst.NS_XMP_NOTE, "HasExtendedXMP", sb4.toString(), new PropertyOptions(0));
                sb2.append(XMPMetaFactory.serializeToString(xMPMetaImpl2, serializeOptions));
            }
            int length2 = 65000 - sb2.length();
            if (length2 > 2047) {
                length2 = 2047;
            }
            sb2.delete(sb2.toString().indexOf("<?xpacket end=\"w\"?>"), sb2.length());
            for (int i2 = 0; i2 < length2; i2++) {
                sb2.append(' ');
            }
            sb2.append("<?xpacket end=\"w\"?>");
            return;
        }
        throw new XMPException("Can't reduce XMP enough for JPEG file", 9);
    }

    private static void putObjectsInMultiMap(Map<Integer, List<List<String>>> map, Integer num, List<String> list) {
        if (map != null) {
            List list2 = map.get(num);
            if (list2 == null) {
                list2 = new ArrayList();
                map.put(num, list2);
            }
            list2.add(list);
        }
    }

    public static void removeProperties(XMPMeta xMPMeta, String str, String str2, boolean z, boolean z3) {
        ParameterAsserts.assertImplementation(xMPMeta);
        XMPMetaImpl xMPMetaImpl = (XMPMetaImpl) xMPMeta;
        if (str2 == null || str2.length() <= 0) {
            if (str == null || str.length() <= 0) {
                Iterator iterateChildren = xMPMetaImpl.getRoot().iterateChildren();
                while (iterateChildren.hasNext()) {
                    if (removeSchemaChildren((XMPNode) iterateChildren.next(), z)) {
                        iterateChildren.remove();
                    }
                }
                return;
            }
            XMPNode findSchemaNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.getRoot(), str, false);
            if (findSchemaNode != null && removeSchemaChildren(findSchemaNode, z)) {
                xMPMetaImpl.getRoot().removeChild(findSchemaNode);
            }
            if (z3) {
                XMPAliasInfo[] findAliases = XMPMetaFactory.getSchemaRegistry().findAliases(str);
                for (XMPAliasInfo xMPAliasInfo : findAliases) {
                    XMPNode findNode = XMPNodeUtils.findNode(xMPMetaImpl.getRoot(), XMPPathParser.expandXPath(xMPAliasInfo.getNamespace(), xMPAliasInfo.getPropName()), false, (PropertyOptions) null);
                    if (findNode != null) {
                        findNode.getParent().removeChild(findNode);
                    }
                }
            }
        } else if (str == null || str.length() == 0) {
            throw new XMPException("Property name requires schema namespace", 4);
        } else {
            XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
            XMPNode findNode2 = XMPNodeUtils.findNode(xMPMetaImpl.getRoot(), expandXPath, false, (PropertyOptions) null);
            if (findNode2 == null) {
                return;
            }
            if (z || !Utils.isInternalProperty(expandXPath.getSegment(0).getName(), expandXPath.getSegment(1).getName())) {
                XMPNode parent = findNode2.getParent();
                parent.removeChild(findNode2);
                if (parent.getOptions().isSchemaNode() && !parent.hasChildren()) {
                    parent.getParent().removeChild(parent);
                }
            }
        }
    }

    private static boolean removeSchemaChildren(XMPNode xMPNode, boolean z) {
        Iterator iterateChildren = xMPNode.iterateChildren();
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) iterateChildren.next();
            if (z || !Utils.isInternalProperty(xMPNode.getName(), xMPNode2.getName())) {
                iterateChildren.remove();
            }
        }
        return !xMPNode.hasChildren();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0068, code lost:
        r3 = r13.charAt(r7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void separateArrayItems(com.adobe.internal.xmp.XMPMeta r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, com.adobe.internal.xmp.options.PropertyOptions r14, boolean r15) {
        /*
            com.adobe.internal.xmp.impl.ParameterAsserts.assertSchemaNS(r11)
            com.adobe.internal.xmp.impl.ParameterAsserts.assertArrayName(r12)
            r0 = 4
            if (r13 == 0) goto L_0x0100
            com.adobe.internal.xmp.impl.ParameterAsserts.assertImplementation(r10)
            com.adobe.internal.xmp.impl.XMPMetaImpl r10 = (com.adobe.internal.xmp.impl.XMPMetaImpl) r10
            com.adobe.internal.xmp.impl.XMPNode r10 = separateFindCreateArray(r11, r12, r14, r10)
            r11 = -1
            r12 = 2147483647(0x7fffffff, float:NaN)
            if (r10 == 0) goto L_0x0022
            if (r14 == 0) goto L_0x0022
            int r14 = r14.getArrayElementsLimit()
            if (r14 != r11) goto L_0x0021
            goto L_0x0022
        L_0x0021:
            r12 = r14
        L_0x0022:
            int r14 = r13.length()
            r1 = 0
            r2 = r1
            r3 = r2
        L_0x0029:
            if (r1 >= r14) goto L_0x00ff
            int r4 = r10.getChildrenLength()
            if (r4 < r12) goto L_0x0033
            goto L_0x00ff
        L_0x0033:
            if (r1 >= r14) goto L_0x0045
            char r3 = r13.charAt(r1)
            int r2 = classifyCharacter(r3)
            if (r2 == 0) goto L_0x0045
            if (r2 != r0) goto L_0x0042
            goto L_0x0045
        L_0x0042:
            int r1 = r1 + 1
            goto L_0x0033
        L_0x0045:
            if (r1 < r14) goto L_0x0049
            goto L_0x00ff
        L_0x0049:
            r4 = 1
            if (r2 == r0) goto L_0x0086
            r5 = r1
        L_0x004d:
            if (r5 >= r14) goto L_0x007b
            char r3 = r13.charAt(r5)
            int r2 = classifyCharacter(r3)
            if (r2 == 0) goto L_0x0078
            if (r2 == r0) goto L_0x0078
            r6 = 2
            if (r2 != r6) goto L_0x0061
            if (r15 == 0) goto L_0x0061
            goto L_0x0078
        L_0x0061:
            if (r2 == r4) goto L_0x0064
            goto L_0x007b
        L_0x0064:
            int r7 = r5 + 1
            if (r7 >= r14) goto L_0x007b
            char r3 = r13.charAt(r7)
            int r7 = classifyCharacter(r3)
            if (r7 == 0) goto L_0x0078
            if (r7 == r0) goto L_0x0078
            if (r7 != r6) goto L_0x007b
            if (r15 == 0) goto L_0x007b
        L_0x0078:
            int r5 = r5 + 1
            goto L_0x004d
        L_0x007b:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r1 = r13.substring(r1, r5)
            r6.<init>(r1)
            r1 = r5
            goto L_0x00cf
        L_0x0086:
            char r5 = getClosingQuote(r3)
            int r1 = r1 + 1
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = ""
            r6.<init>(r7)
            r7 = r3
        L_0x0094:
            if (r1 >= r14) goto L_0x00ce
            char r7 = r13.charAt(r1)
            int r2 = classifyCharacter(r7)
            if (r2 != r0) goto L_0x00c9
            boolean r8 = isSurroundingQuote(r7, r3, r5)
            if (r8 != 0) goto L_0x00a7
            goto L_0x00c9
        L_0x00a7:
            int r8 = r1 + 1
            if (r8 >= r14) goto L_0x00b3
            char r9 = r13.charAt(r8)
            classifyCharacter(r9)
            goto L_0x00b5
        L_0x00b3:
            r9 = 59
        L_0x00b5:
            if (r7 != r9) goto L_0x00bc
            r6.append(r7)
            r1 = r8
            goto L_0x00cc
        L_0x00bc:
            boolean r9 = isClosingingQuote(r7, r3, r5)
            if (r9 != 0) goto L_0x00c6
            r6.append(r7)
            goto L_0x00cc
        L_0x00c6:
            r3 = r7
            r1 = r8
            goto L_0x00cf
        L_0x00c9:
            r6.append(r7)
        L_0x00cc:
            int r1 = r1 + r4
            goto L_0x0094
        L_0x00ce:
            r3 = r7
        L_0x00cf:
            int r5 = r10.getChildrenLength()
            if (r4 > r5) goto L_0x00eb
            java.lang.String r5 = r6.toString()
            com.adobe.internal.xmp.impl.XMPNode r7 = r10.getChild(r4)
            java.lang.String r7 = r7.getValue()
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x00e8
            goto L_0x00ec
        L_0x00e8:
            int r4 = r4 + 1
            goto L_0x00cf
        L_0x00eb:
            r4 = r11
        L_0x00ec:
            if (r4 >= 0) goto L_0x0029
            com.adobe.internal.xmp.impl.XMPNode r4 = new com.adobe.internal.xmp.impl.XMPNode
            java.lang.String r5 = r6.toString()
            r6 = 0
            java.lang.String r7 = "[]"
            r4.<init>(r7, r5, r6)
            r10.addChild(r4)
            goto L_0x0029
        L_0x00ff:
            return
        L_0x0100:
            com.adobe.internal.xmp.XMPException r10 = new com.adobe.internal.xmp.XMPException
            java.lang.String r11 = "Parameter must not be null"
            r10.<init>(r11, r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.XMPUtilsImpl.separateArrayItems(com.adobe.internal.xmp.XMPMeta, java.lang.String, java.lang.String, java.lang.String, com.adobe.internal.xmp.options.PropertyOptions, boolean):void");
    }

    private static XMPNode separateFindCreateArray(String str, String str2, PropertyOptions propertyOptions, XMPMetaImpl xMPMetaImpl) {
        PropertyOptions verifySetOptions = XMPNodeUtils.verifySetOptions(propertyOptions, (Object) null);
        if (verifySetOptions.isOnlyArrayOptions()) {
            XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
            XMPNode findNode = XMPNodeUtils.findNode(xMPMetaImpl.getRoot(), expandXPath, false, (PropertyOptions) null);
            if (findNode != null) {
                PropertyOptions options = findNode.getOptions();
                if (!options.isArray() || options.isArrayAlternate()) {
                    throw new XMPException("Named property must be non-alternate array", 102);
                } else if (!verifySetOptions.equalArrayTypes(options)) {
                    return findNode;
                } else {
                    throw new XMPException("Mismatch of specified and existing array form", 102);
                }
            } else {
                XMPNode findNode2 = XMPNodeUtils.findNode(xMPMetaImpl.getRoot(), expandXPath, true, verifySetOptions.setArray(true));
                if (findNode2 != null) {
                    return findNode2;
                }
                throw new XMPException("Failed to create named array", 102);
            }
        } else {
            throw new XMPException("Options can only provide array form", 103);
        }
    }
}
