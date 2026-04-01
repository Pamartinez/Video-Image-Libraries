package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPConst;
import com.adobe.internal.xmp.XMPDateTime;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPIterator;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPPathFactory;
import com.adobe.internal.xmp.XMPUtils;
import com.adobe.internal.xmp.impl.xpath.XMPPath;
import com.adobe.internal.xmp.impl.xpath.XMPPathParser;
import com.adobe.internal.xmp.options.IteratorOptions;
import com.adobe.internal.xmp.options.ParseOptions;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.adobe.internal.xmp.properties.XMPProperty;
import i.C0212a;
import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XMPMetaImpl implements XMPMeta, XMPConst {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int VALUE_BASE64 = 7;
    private static final int VALUE_BOOLEAN = 1;
    private static final int VALUE_CALENDAR = 6;
    private static final int VALUE_DATE = 5;
    private static final int VALUE_DOUBLE = 4;
    private static final int VALUE_INTEGER = 2;
    private static final int VALUE_LONG = 3;
    private static final int VALUE_STRING = 0;
    private String packetHeader;
    private XMPNode tree;

    public XMPMetaImpl() {
        this.packetHeader = null;
        this.tree = new XMPNode((String) null, (String) null, (PropertyOptions) null);
    }

    private void doSetArrayItem(XMPNode xMPNode, int i2, String str, PropertyOptions propertyOptions, boolean z) {
        XMPNode xMPNode2 = new XMPNode(XMPConst.ARRAY_ITEM_NAME, (PropertyOptions) null);
        PropertyOptions verifySetOptions = XMPNodeUtils.verifySetOptions(propertyOptions, str);
        int childrenLength = xMPNode.getChildrenLength();
        if (z) {
            childrenLength++;
        }
        if (i2 == -1) {
            i2 = childrenLength;
        }
        if (1 > i2 || i2 > childrenLength) {
            throw new XMPException("Array index out of bounds", 104);
        }
        if (!z) {
            xMPNode.removeChild(i2);
        }
        xMPNode.addChild(i2, xMPNode2);
        setNode(xMPNode2, str, verifySetOptions, false);
    }

    private Object evaluateNodeValue(int i2, XMPNode xMPNode) {
        String value = xMPNode.getValue();
        switch (i2) {
            case 1:
                return new Boolean(XMPUtils.convertToBoolean(value));
            case 2:
                return new Integer(XMPUtils.convertToInteger(value));
            case 3:
                return new Long(XMPUtils.convertToLong(value));
            case 4:
                return new Double(XMPUtils.convertToDouble(value));
            case 5:
                return XMPUtils.convertToDate(value);
            case 6:
                return XMPUtils.convertToDate(value).getCalendar();
            case 7:
                return XMPUtils.decodeBase64(value);
            default:
                if (value != null || xMPNode.getOptions().isCompositeProperty()) {
                    return value;
                }
                return "";
        }
    }

    public void appendArrayItem(String str, String str2, PropertyOptions propertyOptions, String str3, PropertyOptions propertyOptions2) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        if (propertyOptions == null) {
            propertyOptions = new PropertyOptions();
        }
        if (propertyOptions.isOnlyArrayOptions()) {
            PropertyOptions verifySetOptions = XMPNodeUtils.verifySetOptions(propertyOptions, (Object) null);
            XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
            XMPNode findNode = XMPNodeUtils.findNode(this.tree, expandXPath, false, (PropertyOptions) null);
            if (findNode != null) {
                if (!findNode.getOptions().isArray()) {
                    throw new XMPException("The named property is not an array", 102);
                }
            } else if (verifySetOptions.isArray()) {
                findNode = XMPNodeUtils.findNode(this.tree, expandXPath, true, verifySetOptions);
                if (findNode == null) {
                    throw new XMPException("Failure creating array node", 102);
                }
            } else {
                throw new XMPException("Explicit arrayOptions required to create new array", 103);
            }
            doSetArrayItem(findNode, -1, str3, propertyOptions2, true);
            return;
        }
        throw new XMPException("Only array form flags allowed for arrayOptions", 103);
    }

    public Object clone() {
        return new XMPMetaImpl((XMPNode) this.tree.clone());
    }

    public int countArrayItems(String str, String str2) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode == null) {
            return 0;
        }
        if (findNode.getOptions().isArray()) {
            return findNode.getChildrenLength();
        }
        throw new XMPException("The named property is not an array", 102);
    }

    public void deleteArrayItem(String str, String str2, int i2) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertArrayName(str2);
            deleteProperty(str, XMPPathFactory.composeArrayItemPath(str2, i2));
        } catch (XMPException unused) {
        }
    }

    public void deleteProperty(String str, String str2) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPropName(str2);
            XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
            if (findNode != null) {
                XMPNodeUtils.deleteNode(findNode);
            }
        } catch (XMPException unused) {
        }
    }

    public void deleteQualifier(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPropName(str2);
            deleteProperty(str, str2 + XMPPathFactory.composeQualifierPath(str3, str4));
        } catch (XMPException unused) {
        }
    }

    public void deleteStructField(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertStructName(str2);
            deleteProperty(str, str2 + XMPPathFactory.composeStructFieldPath(str3, str4));
        } catch (XMPException unused) {
        }
    }

    public boolean doesArrayItemExist(String str, String str2, int i2) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertArrayName(str2);
            return doesPropertyExist(str, XMPPathFactory.composeArrayItemPath(str2, i2));
        } catch (XMPException unused) {
            return false;
        }
    }

    public boolean doesPropertyExist(String str, String str2) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPropName(str2);
            if (XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null) != null) {
                return true;
            }
            return false;
        } catch (XMPException unused) {
        }
    }

    public boolean doesQualifierExist(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPropName(str2);
            String composeQualifierPath = XMPPathFactory.composeQualifierPath(str3, str4);
            return doesPropertyExist(str, str2 + composeQualifierPath);
        } catch (XMPException unused) {
            return false;
        }
    }

    public boolean doesStructFieldExist(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertStructName(str2);
            String composeStructFieldPath = XMPPathFactory.composeStructFieldPath(str3, str4);
            return doesPropertyExist(str, str2 + composeStructFieldPath);
        } catch (XMPException unused) {
            return false;
        }
    }

    public String dumpObject() {
        return getRoot().dumpNode(true);
    }

    public XMPProperty getArrayItem(String str, String str2, int i2) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        return getProperty(str, XMPPathFactory.composeArrayItemPath(str2, i2));
    }

    public XMPProperty getLocalizedText(String str, String str2, String str3, String str4) {
        String str5;
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        ParameterAsserts.assertSpecificLang(str4);
        if (str3 != null) {
            str5 = Utils.normalizeLangValue(str3);
        } else {
            str5 = null;
        }
        String normalizeLangValue = Utils.normalizeLangValue(str4);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode == null) {
            return null;
        }
        Object[] chooseLocalizedText = XMPNodeUtils.chooseLocalizedText(findNode, str5, normalizeLangValue);
        int intValue = ((Integer) chooseLocalizedText[0]).intValue();
        final XMPNode xMPNode = (XMPNode) chooseLocalizedText[1];
        if (intValue != 0) {
            return new XMPProperty() {
                public String getLanguage() {
                    return xMPNode.getQualifier(1).getValue();
                }

                public PropertyOptions getOptions() {
                    return xMPNode.getOptions();
                }

                public String getValue() {
                    return xMPNode.getValue();
                }

                public String toString() {
                    return xMPNode.getValue().toString();
                }
            };
        }
        return null;
    }

    public String getObjectName() {
        if (this.tree.getName() != null) {
            return this.tree.getName();
        }
        return "";
    }

    public String getPacketHeader() {
        return this.packetHeader;
    }

    public XMPProperty getProperty(String str, String str2) {
        return getProperty(str, str2, 0);
    }

    public byte[] getPropertyBase64(String str, String str2) {
        return (byte[]) getPropertyObject(str, str2, 7);
    }

    public Boolean getPropertyBoolean(String str, String str2) {
        return (Boolean) getPropertyObject(str, str2, 1);
    }

    public Calendar getPropertyCalendar(String str, String str2) {
        return (Calendar) getPropertyObject(str, str2, 6);
    }

    public XMPDateTime getPropertyDate(String str, String str2) {
        return (XMPDateTime) getPropertyObject(str, str2, 5);
    }

    public Double getPropertyDouble(String str, String str2) {
        return (Double) getPropertyObject(str, str2, 4);
    }

    public Integer getPropertyInteger(String str, String str2) {
        return (Integer) getPropertyObject(str, str2, 2);
    }

    public Long getPropertyLong(String str, String str2) {
        return (Long) getPropertyObject(str, str2, 3);
    }

    public Object getPropertyObject(String str, String str2, int i2) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode == null) {
            return null;
        }
        if (i2 == 0 || !findNode.getOptions().isCompositeProperty()) {
            return evaluateNodeValue(i2, findNode);
        }
        throw new XMPException("Property must be simple when a value type is requested", 102);
    }

    public String getPropertyString(String str, String str2) {
        return (String) getPropertyObject(str, str2, 0);
    }

    public XMPProperty getQualifier(String str, String str2, String str3, String str4) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        StringBuilder s = C0212a.s(str2);
        s.append(XMPPathFactory.composeQualifierPath(str3, str4));
        return getProperty(str, s.toString());
    }

    public XMPNode getRoot() {
        return this.tree;
    }

    public XMPProperty getStructField(String str, String str2, String str3, String str4) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertStructName(str2);
        StringBuilder s = C0212a.s(str2);
        s.append(XMPPathFactory.composeStructFieldPath(str3, str4));
        return getProperty(str, s.toString());
    }

    public void insertArrayItem(String str, String str2, int i2, String str3, PropertyOptions propertyOptions) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode != null) {
            doSetArrayItem(findNode, i2, str3, propertyOptions, true);
            return;
        }
        throw new XMPException("Specified array does not exist", 102);
    }

    public XMPIterator iterator() {
        return iterator((String) null, (String) null, (IteratorOptions) null);
    }

    public void normalize(ParseOptions parseOptions) {
        if (parseOptions == null) {
            parseOptions = new ParseOptions();
        }
        XMPNormalizer.process(this, parseOptions);
    }

    public void setArrayItem(String str, String str2, int i2, String str3, PropertyOptions propertyOptions) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode != null) {
            doSetArrayItem(findNode, i2, str3, propertyOptions, false);
            return;
        }
        throw new XMPException("Specified array does not exist", 102);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:102:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009b, code lost:
        if (r1 == null) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a1, code lost:
        if (r6.getChildrenLength() <= 1) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a3, code lost:
        r6.removeChild(r1);
        r6.addChild(1, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a9, code lost:
        r8 = com.adobe.internal.xmp.impl.XMPNodeUtils.chooseLocalizedText(r6, r9, r10);
        r9 = ((java.lang.Integer) r8[0]).intValue();
        r8 = (com.adobe.internal.xmp.impl.XMPNode) r8[1];
        r2 = com.adobe.internal.xmp.XMPConst.X_DEFAULT.equals(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00bd, code lost:
        if (r9 == 0) goto L_0x015c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00bf, code lost:
        if (r9 == 1) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c2, code lost:
        if (r9 == 2) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c5, code lost:
        if (r9 == 3) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c8, code lost:
        if (r9 == 4) goto L_0x00df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00cb, code lost:
        if (r9 != 5) goto L_0x00d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00cd, code lost:
        com.adobe.internal.xmp.impl.XMPNodeUtils.appendLangItem(r6, r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d0, code lost:
        if (r2 == false) goto L_0x0166;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d2, code lost:
        r7 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00de, code lost:
        throw new com.adobe.internal.xmp.XMPException("Unexpected result from ChooseLocalizedText", 9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00df, code lost:
        if (r1 == null) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e5, code lost:
        if (r6.getChildrenLength() != 1) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e7, code lost:
        r1.setValue(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ea, code lost:
        com.adobe.internal.xmp.impl.XMPNodeUtils.appendLangItem(r6, r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00ef, code lost:
        com.adobe.internal.xmp.impl.XMPNodeUtils.appendLangItem(r6, r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f2, code lost:
        if (r2 == false) goto L_0x0166;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f5, code lost:
        if (r7 == false) goto L_0x010c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f7, code lost:
        if (r1 == r8) goto L_0x010c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00f9, code lost:
        if (r1 == null) goto L_0x010c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0107, code lost:
        if (r1.getValue().equals(r8.getValue()) == false) goto L_0x010c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0109, code lost:
        r1.setValue(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x010c, code lost:
        r8.setValue(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0110, code lost:
        if (r2 != false) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0112, code lost:
        if (r7 == false) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0114, code lost:
        if (r1 == r8) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0116, code lost:
        if (r1 == null) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0124, code lost:
        if (r1.getValue().equals(r8.getValue()) == false) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0126, code lost:
        r1.setValue(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0129, code lost:
        r8.setValue(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x012d, code lost:
        r8 = r6.iterateChildren();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0135, code lost:
        if (r8.hasNext() == false) goto L_0x0156;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0137, code lost:
        r9 = (com.adobe.internal.xmp.impl.XMPNode) r8.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x013d, code lost:
        if (r9 == r1) goto L_0x0131;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x013f, code lost:
        r10 = r9.getValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0143, code lost:
        if (r1 == null) goto L_0x014a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0145, code lost:
        r2 = r1.getValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x014a, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x014f, code lost:
        if (r10.equals(r2) != false) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0152, code lost:
        r9.setValue(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0156, code lost:
        if (r1 == null) goto L_0x0166;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0158, code lost:
        r1.setValue(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x015c, code lost:
        com.adobe.internal.xmp.impl.XMPNodeUtils.appendLangItem(r6, com.adobe.internal.xmp.XMPConst.X_DEFAULT, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x015f, code lost:
        if (r2 != false) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0161, code lost:
        com.adobe.internal.xmp.impl.XMPNodeUtils.appendLangItem(r6, r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0166, code lost:
        if (r7 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x016c, code lost:
        if (r6.getChildrenLength() != 1) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x016e, code lost:
        com.adobe.internal.xmp.impl.XMPNodeUtils.appendLangItem(r6, com.adobe.internal.xmp.XMPConst.X_DEFAULT, r11);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setLocalizedText(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, com.adobe.internal.xmp.options.PropertyOptions r12) {
        /*
            r6 = this;
            com.adobe.internal.xmp.impl.ParameterAsserts.assertSchemaNS(r7)
            com.adobe.internal.xmp.impl.ParameterAsserts.assertArrayName(r8)
            com.adobe.internal.xmp.impl.ParameterAsserts.assertSpecificLang(r10)
            r12 = 0
            if (r9 == 0) goto L_0x0011
            java.lang.String r9 = com.adobe.internal.xmp.impl.Utils.normalizeLangValue(r9)
            goto L_0x0012
        L_0x0011:
            r9 = r12
        L_0x0012:
            java.lang.String r10 = com.adobe.internal.xmp.impl.Utils.normalizeLangValue(r10)
            com.adobe.internal.xmp.impl.xpath.XMPPath r7 = com.adobe.internal.xmp.impl.xpath.XMPPathParser.expandXPath(r7, r8)
            com.adobe.internal.xmp.impl.XMPNode r6 = r6.tree
            com.adobe.internal.xmp.options.PropertyOptions r8 = new com.adobe.internal.xmp.options.PropertyOptions
            r0 = 7680(0x1e00, float:1.0762E-41)
            r8.<init>(r0)
            r0 = 1
            com.adobe.internal.xmp.impl.XMPNode r6 = com.adobe.internal.xmp.impl.XMPNodeUtils.findNode(r6, r7, r0, r8)
            r7 = 102(0x66, float:1.43E-43)
            if (r6 == 0) goto L_0x0172
            com.adobe.internal.xmp.options.PropertyOptions r8 = r6.getOptions()
            boolean r8 = r8.isArrayAltText()
            if (r8 != 0) goto L_0x0056
            boolean r8 = r6.hasChildren()
            if (r8 != 0) goto L_0x004e
            com.adobe.internal.xmp.options.PropertyOptions r8 = r6.getOptions()
            boolean r8 = r8.isArrayAlternate()
            if (r8 == 0) goto L_0x004e
            com.adobe.internal.xmp.options.PropertyOptions r8 = r6.getOptions()
            r8.setArrayAltText(r0)
            goto L_0x0056
        L_0x004e:
            com.adobe.internal.xmp.XMPException r6 = new com.adobe.internal.xmp.XMPException
            java.lang.String r8 = "Specified property is no alt-text array"
            r6.<init>(r8, r7)
            throw r6
        L_0x0056:
            java.util.Iterator r8 = r6.iterateChildren()
        L_0x005a:
            boolean r1 = r8.hasNext()
            r2 = 0
            java.lang.String r3 = "x-default"
            if (r1 == 0) goto L_0x0099
            java.lang.Object r1 = r8.next()
            com.adobe.internal.xmp.impl.XMPNode r1 = (com.adobe.internal.xmp.impl.XMPNode) r1
            boolean r4 = r1.hasQualifier()
            if (r4 == 0) goto L_0x0091
            com.adobe.internal.xmp.impl.XMPNode r4 = r1.getQualifier(r0)
            java.lang.String r4 = r4.getName()
            java.lang.String r5 = "xml:lang"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0091
            com.adobe.internal.xmp.impl.XMPNode r4 = r1.getQualifier(r0)
            java.lang.String r4 = r4.getValue()
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x005a
            r7 = r0
            goto L_0x009b
        L_0x0091:
            com.adobe.internal.xmp.XMPException r6 = new com.adobe.internal.xmp.XMPException
            java.lang.String r8 = "Language qualifier must be first"
            r6.<init>(r8, r7)
            throw r6
        L_0x0099:
            r1 = r12
            r7 = r2
        L_0x009b:
            if (r1 == 0) goto L_0x00a9
            int r8 = r6.getChildrenLength()
            if (r8 <= r0) goto L_0x00a9
            r6.removeChild((com.adobe.internal.xmp.impl.XMPNode) r1)
            r6.addChild(r0, r1)
        L_0x00a9:
            java.lang.Object[] r8 = com.adobe.internal.xmp.impl.XMPNodeUtils.chooseLocalizedText(r6, r9, r10)
            r9 = r8[r2]
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r0]
            com.adobe.internal.xmp.impl.XMPNode r8 = (com.adobe.internal.xmp.impl.XMPNode) r8
            boolean r2 = r3.equals(r10)
            if (r9 == 0) goto L_0x015c
            if (r9 == r0) goto L_0x0110
            r12 = 2
            if (r9 == r12) goto L_0x00f5
            r8 = 3
            if (r9 == r8) goto L_0x00ef
            r8 = 4
            if (r9 == r8) goto L_0x00df
            r8 = 5
            if (r9 != r8) goto L_0x00d5
            com.adobe.internal.xmp.impl.XMPNodeUtils.appendLangItem(r6, r10, r11)
            if (r2 == 0) goto L_0x0166
        L_0x00d2:
            r7 = r0
            goto L_0x0166
        L_0x00d5:
            com.adobe.internal.xmp.XMPException r6 = new com.adobe.internal.xmp.XMPException
            java.lang.String r7 = "Unexpected result from ChooseLocalizedText"
            r8 = 9
            r6.<init>(r7, r8)
            throw r6
        L_0x00df:
            if (r1 == 0) goto L_0x00ea
            int r8 = r6.getChildrenLength()
            if (r8 != r0) goto L_0x00ea
            r1.setValue(r11)
        L_0x00ea:
            com.adobe.internal.xmp.impl.XMPNodeUtils.appendLangItem(r6, r10, r11)
            goto L_0x0166
        L_0x00ef:
            com.adobe.internal.xmp.impl.XMPNodeUtils.appendLangItem(r6, r10, r11)
            if (r2 == 0) goto L_0x0166
            goto L_0x00d2
        L_0x00f5:
            if (r7 == 0) goto L_0x010c
            if (r1 == r8) goto L_0x010c
            if (r1 == 0) goto L_0x010c
            java.lang.String r9 = r1.getValue()
            java.lang.String r10 = r8.getValue()
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x010c
            r1.setValue(r11)
        L_0x010c:
            r8.setValue(r11)
            goto L_0x0166
        L_0x0110:
            if (r2 != 0) goto L_0x012d
            if (r7 == 0) goto L_0x0129
            if (r1 == r8) goto L_0x0129
            if (r1 == 0) goto L_0x0129
            java.lang.String r9 = r1.getValue()
            java.lang.String r10 = r8.getValue()
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0129
            r1.setValue(r11)
        L_0x0129:
            r8.setValue(r11)
            goto L_0x0166
        L_0x012d:
            java.util.Iterator r8 = r6.iterateChildren()
        L_0x0131:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0156
            java.lang.Object r9 = r8.next()
            com.adobe.internal.xmp.impl.XMPNode r9 = (com.adobe.internal.xmp.impl.XMPNode) r9
            if (r9 == r1) goto L_0x0131
            java.lang.String r10 = r9.getValue()
            if (r1 == 0) goto L_0x014a
            java.lang.String r2 = r1.getValue()
            goto L_0x014b
        L_0x014a:
            r2 = r12
        L_0x014b:
            boolean r10 = r10.equals(r2)
            if (r10 != 0) goto L_0x0152
            goto L_0x0131
        L_0x0152:
            r9.setValue(r11)
            goto L_0x0131
        L_0x0156:
            if (r1 == 0) goto L_0x0166
            r1.setValue(r11)
            goto L_0x0166
        L_0x015c:
            com.adobe.internal.xmp.impl.XMPNodeUtils.appendLangItem(r6, r3, r11)
            if (r2 != 0) goto L_0x00d2
            com.adobe.internal.xmp.impl.XMPNodeUtils.appendLangItem(r6, r10, r11)
            goto L_0x00d2
        L_0x0166:
            if (r7 != 0) goto L_0x0171
            int r7 = r6.getChildrenLength()
            if (r7 != r0) goto L_0x0171
            com.adobe.internal.xmp.impl.XMPNodeUtils.appendLangItem(r6, r3, r11)
        L_0x0171:
            return
        L_0x0172:
            com.adobe.internal.xmp.XMPException r6 = new com.adobe.internal.xmp.XMPException
            java.lang.String r8 = "Failed to find or create array node"
            r6.<init>(r8, r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.XMPMetaImpl.setLocalizedText(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.adobe.internal.xmp.options.PropertyOptions):void");
    }

    public void setNode(XMPNode xMPNode, Object obj, PropertyOptions propertyOptions, boolean z) {
        if (z) {
            xMPNode.clear();
        }
        xMPNode.getOptions().mergeWith(propertyOptions);
        if ((xMPNode.getOptions().getOptions() & 7936) == 0) {
            XMPNodeUtils.setNodeValue(xMPNode, obj);
        } else if (obj != null && obj.toString().length() > 0) {
            throw new XMPException("Composite nodes can't have values", 102);
        } else if ((xMPNode.getOptions().getOptions() & 7936) == 0 || (propertyOptions.getOptions() & 7936) == (xMPNode.getOptions().getOptions() & 7936)) {
            xMPNode.removeChildren();
        } else {
            throw new XMPException("Requested and existing composite form mismatch", 102);
        }
    }

    public void setObjectName(String str) {
        this.tree.setName(str);
    }

    public void setPacketHeader(String str) {
        this.packetHeader = str;
    }

    public void setProperty(String str, String str2, Object obj, PropertyOptions propertyOptions) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        PropertyOptions verifySetOptions = XMPNodeUtils.verifySetOptions(propertyOptions, obj);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), true, verifySetOptions);
        if (findNode != null) {
            setNode(findNode, obj, verifySetOptions, false);
            return;
        }
        throw new XMPException("Specified property does not exist", 102);
    }

    public void setPropertyBase64(String str, String str2, byte[] bArr, PropertyOptions propertyOptions) {
        setProperty(str, str2, bArr, propertyOptions);
    }

    public void setPropertyBoolean(String str, String str2, boolean z, PropertyOptions propertyOptions) {
        setProperty(str, str2, z ? XMPConst.TRUESTR : XMPConst.FALSESTR, propertyOptions);
    }

    public void setPropertyCalendar(String str, String str2, Calendar calendar, PropertyOptions propertyOptions) {
        setProperty(str, str2, calendar, propertyOptions);
    }

    public void setPropertyDate(String str, String str2, XMPDateTime xMPDateTime, PropertyOptions propertyOptions) {
        setProperty(str, str2, xMPDateTime, propertyOptions);
    }

    public void setPropertyDouble(String str, String str2, double d, PropertyOptions propertyOptions) {
        setProperty(str, str2, new Double(d), propertyOptions);
    }

    public void setPropertyInteger(String str, String str2, int i2, PropertyOptions propertyOptions) {
        setProperty(str, str2, new Integer(i2), propertyOptions);
    }

    public void setPropertyLong(String str, String str2, long j2, PropertyOptions propertyOptions) {
        setProperty(str, str2, new Long(j2), propertyOptions);
    }

    public void setQualifier(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        if (doesPropertyExist(str, str2)) {
            StringBuilder s = C0212a.s(str2);
            s.append(XMPPathFactory.composeQualifierPath(str3, str4));
            setProperty(str, s.toString(), str5, propertyOptions);
            return;
        }
        throw new XMPException("Specified property does not exist!", 102);
    }

    public void setStructField(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertStructName(str2);
        StringBuilder s = C0212a.s(str2);
        s.append(XMPPathFactory.composeStructFieldPath(str3, str4));
        setProperty(str, s.toString(), str5, propertyOptions);
    }

    public void sort() {
        this.tree.sort();
    }

    public XMPProperty getProperty(String str, String str2, int i2) {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        final XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, (PropertyOptions) null);
        if (findNode == null) {
            return null;
        }
        if (i2 == 0 || !findNode.getOptions().isCompositeProperty()) {
            final Object evaluateNodeValue = evaluateNodeValue(i2, findNode);
            return new XMPProperty() {
                public String getLanguage() {
                    return null;
                }

                public PropertyOptions getOptions() {
                    return findNode.getOptions();
                }

                public String getValue() {
                    Object obj = evaluateNodeValue;
                    if (obj != null) {
                        return obj.toString();
                    }
                    return null;
                }

                public String toString() {
                    return evaluateNodeValue.toString();
                }
            };
        }
        throw new XMPException("Property must be simple when a value type is requested", 102);
    }

    public XMPIterator iterator(IteratorOptions iteratorOptions) {
        return iterator((String) null, (String) null, iteratorOptions);
    }

    public void setPropertyBase64(String str, String str2, byte[] bArr) {
        setProperty(str, str2, bArr, (PropertyOptions) null);
    }

    public void setPropertyBoolean(String str, String str2, boolean z) {
        setProperty(str, str2, z ? XMPConst.TRUESTR : XMPConst.FALSESTR, (PropertyOptions) null);
    }

    public void setPropertyCalendar(String str, String str2, Calendar calendar) {
        setProperty(str, str2, calendar, (PropertyOptions) null);
    }

    public void setPropertyDate(String str, String str2, XMPDateTime xMPDateTime) {
        setProperty(str, str2, xMPDateTime, (PropertyOptions) null);
    }

    public void setPropertyDouble(String str, String str2, double d) {
        setProperty(str, str2, new Double(d), (PropertyOptions) null);
    }

    public void setPropertyInteger(String str, String str2, int i2) {
        setProperty(str, str2, new Integer(i2), (PropertyOptions) null);
    }

    public void setPropertyLong(String str, String str2, long j2) {
        setProperty(str, str2, new Long(j2), (PropertyOptions) null);
    }

    public XMPIterator iterator(String str, String str2, IteratorOptions iteratorOptions) {
        return new XMPIteratorImpl(this, str, str2, iteratorOptions);
    }

    public XMPMetaImpl(XMPNode xMPNode) {
        this.packetHeader = null;
        this.tree = xMPNode;
    }

    public void insertArrayItem(String str, String str2, int i2, String str3) {
        insertArrayItem(str, str2, i2, str3, (PropertyOptions) null);
    }

    public void setArrayItem(String str, String str2, int i2, String str3) {
        setArrayItem(str, str2, i2, str3, (PropertyOptions) null);
    }

    public void setProperty(String str, String str2, Object obj) {
        setProperty(str, str2, obj, (PropertyOptions) null);
    }

    public void setStructField(String str, String str2, String str3, String str4, String str5) {
        setStructField(str, str2, str3, str4, str5, (PropertyOptions) null);
    }

    public void setQualifier(String str, String str2, String str3, String str4, String str5) {
        setQualifier(str, str2, str3, str4, str5, (PropertyOptions) null);
    }

    public void appendArrayItem(String str, String str2, String str3) {
        appendArrayItem(str, str2, (PropertyOptions) null, str3, (PropertyOptions) null);
    }

    public void setLocalizedText(String str, String str2, String str3, String str4, String str5) {
        setLocalizedText(str, str2, str3, str4, str5, (PropertyOptions) null);
    }
}
