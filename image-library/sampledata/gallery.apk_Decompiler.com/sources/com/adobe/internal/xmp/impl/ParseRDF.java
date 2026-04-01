package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPConst;
import com.adobe.internal.xmp.XMPError;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.XMPSchemaRegistry;
import com.adobe.internal.xmp.options.ParseOptions;
import com.adobe.internal.xmp.options.PropertyOptions;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ParseRDF implements XMPError, XMPConst {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String DEFAULT_PREFIX = "_dflt";
    public static final int RDFTERM_ABOUT = 3;
    public static final int RDFTERM_ABOUT_EACH = 10;
    public static final int RDFTERM_ABOUT_EACH_PREFIX = 11;
    public static final int RDFTERM_BAG_ID = 12;
    public static final int RDFTERM_DATATYPE = 7;
    public static final int RDFTERM_DESCRIPTION = 8;
    public static final int RDFTERM_FIRST_CORE = 1;
    public static final int RDFTERM_FIRST_OLD = 10;
    public static final int RDFTERM_FIRST_SYNTAX = 1;
    public static final int RDFTERM_ID = 2;
    public static final int RDFTERM_LAST_CORE = 7;
    public static final int RDFTERM_LAST_OLD = 12;
    public static final int RDFTERM_LAST_SYNTAX = 9;
    public static final int RDFTERM_LI = 9;
    public static final int RDFTERM_NODE_ID = 6;
    public static final int RDFTERM_OTHER = 0;
    public static final int RDFTERM_PARSE_TYPE = 4;
    public static final int RDFTERM_RDF = 1;
    public static final int RDFTERM_RESOURCE = 5;

    private static XMPNode addChildNode(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, String str, boolean z) {
        XMPSchemaRegistry schemaRegistry = XMPMetaFactory.getSchemaRegistry();
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI != null) {
            if (XMPConst.NS_DC_DEPRECATED.equals(namespaceURI)) {
                namespaceURI = XMPConst.NS_DC;
            }
            String namespacePrefix = schemaRegistry.getNamespacePrefix(namespaceURI);
            if (namespacePrefix == null) {
                namespacePrefix = schemaRegistry.registerNamespace(namespaceURI, node.getPrefix() != null ? node.getPrefix() : DEFAULT_PREFIX);
            }
            StringBuilder s = C0212a.s(namespacePrefix);
            s.append(node.getLocalName());
            String sb2 = s.toString();
            PropertyOptions propertyOptions = new PropertyOptions();
            boolean z3 = false;
            if (z) {
                xMPNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.getRoot(), namespaceURI, DEFAULT_PREFIX, true);
                xMPNode.setImplicit(false);
                if (schemaRegistry.findAlias(sb2) != null) {
                    xMPMetaImpl.getRoot().setHasAliases(true);
                    xMPNode.setHasAliases(true);
                    z3 = true;
                }
            }
            boolean isNumberedArrayItemName = isNumberedArrayItemName(sb2);
            boolean equals = "rdf:value".equals(sb2);
            XMPNode xMPNode2 = new XMPNode(sb2, str, propertyOptions);
            xMPNode2.setAlias(z3);
            if (!equals) {
                xMPNode.addChild(xMPNode2);
            } else {
                xMPNode.addChild(1, xMPNode2);
            }
            if (equals) {
                if (z || !xMPNode.getOptions().isStruct()) {
                    throw new XMPException("Misplaced rdf:value element", 202);
                }
                xMPNode.setHasValueChild(true);
            }
            boolean isArray = xMPNode.getOptions().isArray();
            if (isArray && isNumberedArrayItemName) {
                xMPNode2.setName(XMPConst.ARRAY_ITEM_NAME);
                return xMPNode2;
            } else if (!isArray && isNumberedArrayItemName) {
                throw new XMPException("Misplaced rdf:li element", 202);
            } else if (!isArray || isNumberedArrayItemName) {
                return xMPNode2;
            } else {
                throw new XMPException("Arrays cannot have arbitrary child names", 202);
            }
        } else {
            throw new XMPException("XML namespace required for all elements and attributes", 202);
        }
    }

    private static XMPNode addQualifierNode(XMPNode xMPNode, String str, String str2) {
        if (XMPConst.XML_LANG.equals(str)) {
            str2 = Utils.normalizeLangValue(str2);
        }
        XMPNode xMPNode2 = new XMPNode(str, str2, (PropertyOptions) null);
        xMPNode.addQualifier(xMPNode2);
        return xMPNode2;
    }

    private static void fixupQualifiedNode(XMPNode xMPNode) {
        XMPNode child = xMPNode.getChild(1);
        if (child.getOptions().getHasLanguage()) {
            if (!xMPNode.getOptions().getHasLanguage()) {
                XMPNode qualifier = child.getQualifier(1);
                child.removeQualifier(qualifier);
                xMPNode.addQualifier(qualifier);
            } else {
                throw new XMPException("Redundant xml:lang for rdf:value element", 203);
            }
        }
        for (int i2 = 1; i2 <= child.getQualifierLength(); i2++) {
            xMPNode.addQualifier(child.getQualifier(i2));
        }
        for (int i7 = 2; i7 <= xMPNode.getChildrenLength(); i7++) {
            xMPNode.addQualifier(xMPNode.getChild(i7));
        }
        xMPNode.setHasValueChild(false);
        xMPNode.getOptions().setStruct(false);
        xMPNode.getOptions().mergeWith(child.getOptions());
        xMPNode.setValue(child.getValue());
        xMPNode.removeChildren();
        Iterator iterateChildren = child.iterateChildren();
        while (iterateChildren.hasNext()) {
            xMPNode.addChild((XMPNode) iterateChildren.next());
        }
    }

    private static int getRDFTermKind(Node node) {
        String localName = node.getLocalName();
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI == null && (("about".equals(localName) || "ID".equals(localName)) && (node instanceof Attr) && XMPConst.NS_RDF.equals(((Attr) node).getOwnerElement().getNamespaceURI()))) {
            namespaceURI = XMPConst.NS_RDF;
        }
        if (!XMPConst.NS_RDF.equals(namespaceURI)) {
            return 0;
        }
        if ("li".equals(localName)) {
            return 9;
        }
        if ("parseType".equals(localName)) {
            return 4;
        }
        if ("Description".equals(localName)) {
            return 8;
        }
        if ("about".equals(localName)) {
            return 3;
        }
        if ("resource".equals(localName)) {
            return 5;
        }
        if ("RDF".equals(localName)) {
            return 1;
        }
        if ("ID".equals(localName)) {
            return 2;
        }
        if ("nodeID".equals(localName)) {
            return 6;
        }
        if ("datatype".equals(localName)) {
            return 7;
        }
        if ("aboutEach".equals(localName)) {
            return 10;
        }
        if ("aboutEachPrefix".equals(localName)) {
            return 11;
        }
        if ("bagID".equals(localName)) {
            return 12;
        }
        return 0;
    }

    private static boolean isCoreSyntaxTerm(int i2) {
        if (1 > i2 || i2 > 7) {
            return false;
        }
        return true;
    }

    private static boolean isNumberedArrayItemName(String str) {
        boolean equals = "rdf:li".equals(str);
        if (!str.startsWith("rdf:_")) {
            return equals;
        }
        boolean z = true;
        for (int i2 = 5; i2 < str.length(); i2++) {
            if (!z || str.charAt(i2) < '0' || str.charAt(i2) > '9') {
                z = false;
            } else {
                z = true;
            }
        }
        return z;
    }

    private static boolean isOldTerm(int i2) {
        if (10 > i2 || i2 > 12) {
            return false;
        }
        return true;
    }

    private static boolean isPropertyElementName(int i2) {
        if (i2 == 8 || isOldTerm(i2)) {
            return false;
        }
        return !isCoreSyntaxTerm(i2);
    }

    private static boolean isWhitespaceNode(Node node) {
        if (node.getNodeType() != 3) {
            return false;
        }
        String nodeValue = node.getNodeValue();
        for (int i2 = 0; i2 < nodeValue.length(); i2++) {
            if (!Character.isWhitespace(nodeValue.charAt(i2))) {
                return false;
            }
        }
        return true;
    }

    public static XMPMetaImpl parse(Node node, ParseOptions parseOptions) {
        XMPMetaImpl xMPMetaImpl = new XMPMetaImpl();
        rdf_RDF(xMPMetaImpl, node, parseOptions);
        return xMPMetaImpl;
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x00f1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void rdf_EmptyPropertyElement(com.adobe.internal.xmp.impl.XMPMetaImpl r16, com.adobe.internal.xmp.impl.XMPNode r17, org.w3c.dom.Node r18, boolean r19) {
        /*
            r0 = r16
            boolean r1 = r18.hasChildNodes()
            r2 = 202(0xca, float:2.83E-43)
            if (r1 != 0) goto L_0x0160
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x0010:
            org.w3c.dom.NamedNodeMap r9 = r18.getAttributes()
            int r9 = r9.getLength()
            java.lang.String r10 = "Unrecognized attribute of empty property element"
            r11 = 6
            r12 = 5
            r13 = 2
            java.lang.String r14 = "xml:lang"
            java.lang.String r15 = "xmlns"
            if (r4 >= r9) goto L_0x00b5
            org.w3c.dom.NamedNodeMap r9 = r18.getAttributes()
            org.w3c.dom.Node r9 = r9.item(r4)
            java.lang.String r1 = r9.getPrefix()
            boolean r1 = r15.equals(r1)
            if (r1 != 0) goto L_0x00b1
            java.lang.String r1 = r9.getPrefix()
            if (r1 != 0) goto L_0x0049
            java.lang.String r1 = r9.getNodeName()
            boolean r1 = r15.equals(r1)
            if (r1 == 0) goto L_0x0049
            goto L_0x00b1
        L_0x0049:
            int r1 = getRDFTermKind(r9)
            java.lang.String r15 = "Empty property element can't have both rdf:value and rdf:resource"
            if (r1 == 0) goto L_0x0080
            if (r1 == r13) goto L_0x00b1
            java.lang.String r13 = "Empty property element can't have both rdf:resource and rdf:nodeID"
            if (r1 == r12) goto L_0x0069
            if (r1 != r11) goto L_0x0063
            if (r6 != 0) goto L_0x005d
            r8 = 1
            goto L_0x00b1
        L_0x005d:
            com.adobe.internal.xmp.XMPException r0 = new com.adobe.internal.xmp.XMPException
            r0.<init>(r13, r2)
            throw r0
        L_0x0063:
            com.adobe.internal.xmp.XMPException r0 = new com.adobe.internal.xmp.XMPException
            r0.<init>(r10, r2)
            throw r0
        L_0x0069:
            if (r8 != 0) goto L_0x007a
            if (r5 != 0) goto L_0x0072
            if (r5 != 0) goto L_0x0070
            r3 = r9
        L_0x0070:
            r6 = 1
            goto L_0x00b1
        L_0x0072:
            com.adobe.internal.xmp.XMPException r0 = new com.adobe.internal.xmp.XMPException
            r1 = 203(0xcb, float:2.84E-43)
            r0.<init>(r15, r1)
            throw r0
        L_0x007a:
            com.adobe.internal.xmp.XMPException r0 = new com.adobe.internal.xmp.XMPException
            r0.<init>(r13, r2)
            throw r0
        L_0x0080:
            java.lang.String r1 = "value"
            java.lang.String r10 = r9.getLocalName()
            boolean r1 = r1.equals(r10)
            if (r1 == 0) goto L_0x00a6
            java.lang.String r1 = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
            java.lang.String r10 = r9.getNamespaceURI()
            boolean r1 = r1.equals(r10)
            if (r1 == 0) goto L_0x00a6
            if (r6 != 0) goto L_0x009e
            r3 = r9
            r5 = 1
            goto L_0x00b1
        L_0x009e:
            com.adobe.internal.xmp.XMPException r0 = new com.adobe.internal.xmp.XMPException
            r1 = 203(0xcb, float:2.84E-43)
            r0.<init>(r15, r1)
            throw r0
        L_0x00a6:
            java.lang.String r1 = r9.getNodeName()
            boolean r1 = r14.equals(r1)
            if (r1 != 0) goto L_0x00b1
            r7 = 1
        L_0x00b1:
            int r4 = r4 + 1
            goto L_0x0010
        L_0x00b5:
            java.lang.String r1 = ""
            r4 = r17
            r8 = r18
            r9 = r19
            com.adobe.internal.xmp.impl.XMPNode r4 = addChildNode(r0, r4, r8, r1, r9)
            if (r5 != 0) goto L_0x00c5
            if (r6 == 0) goto L_0x00c7
        L_0x00c5:
            r6 = 1
            goto L_0x00d3
        L_0x00c7:
            if (r7 == 0) goto L_0x00e5
            com.adobe.internal.xmp.options.PropertyOptions r1 = r4.getOptions()
            r6 = 1
            r1.setStruct(r6)
            r1 = r6
            goto L_0x00e6
        L_0x00d3:
            if (r3 == 0) goto L_0x00d9
            java.lang.String r1 = r3.getNodeValue()
        L_0x00d9:
            r4.setValue(r1)
            if (r5 != 0) goto L_0x00e5
            com.adobe.internal.xmp.options.PropertyOptions r1 = r4.getOptions()
            r1.setURI(r6)
        L_0x00e5:
            r1 = 0
        L_0x00e6:
            r5 = 0
        L_0x00e7:
            org.w3c.dom.NamedNodeMap r6 = r8.getAttributes()
            int r6 = r6.getLength()
            if (r5 >= r6) goto L_0x015f
            org.w3c.dom.NamedNodeMap r6 = r8.getAttributes()
            org.w3c.dom.Node r6 = r6.item(r5)
            if (r6 == r3) goto L_0x0115
            java.lang.String r7 = r6.getPrefix()
            boolean r7 = r15.equals(r7)
            if (r7 != 0) goto L_0x0115
            java.lang.String r7 = r6.getPrefix()
            if (r7 != 0) goto L_0x0117
            java.lang.String r7 = r6.getNodeName()
            boolean r7 = r15.equals(r7)
            if (r7 == 0) goto L_0x0117
        L_0x0115:
            r9 = 0
            goto L_0x015c
        L_0x0117:
            int r7 = getRDFTermKind(r6)
            if (r7 == 0) goto L_0x0134
            if (r7 == r13) goto L_0x0115
            if (r7 == r12) goto L_0x012a
            if (r7 != r11) goto L_0x0124
            goto L_0x0115
        L_0x0124:
            com.adobe.internal.xmp.XMPException r0 = new com.adobe.internal.xmp.XMPException
            r0.<init>(r10, r2)
            throw r0
        L_0x012a:
            java.lang.String r7 = "rdf:resource"
            java.lang.String r6 = r6.getNodeValue()
            addQualifierNode(r4, r7, r6)
            goto L_0x0115
        L_0x0134:
            if (r1 != 0) goto L_0x0142
            java.lang.String r7 = r6.getNodeName()
            java.lang.String r6 = r6.getNodeValue()
            addQualifierNode(r4, r7, r6)
            goto L_0x0115
        L_0x0142:
            java.lang.String r7 = r6.getNodeName()
            boolean r7 = r14.equals(r7)
            if (r7 == 0) goto L_0x0154
            java.lang.String r6 = r6.getNodeValue()
            addQualifierNode(r4, r14, r6)
            goto L_0x0115
        L_0x0154:
            java.lang.String r7 = r6.getNodeValue()
            r9 = 0
            addChildNode(r0, r4, r6, r7, r9)
        L_0x015c:
            int r5 = r5 + 1
            goto L_0x00e7
        L_0x015f:
            return
        L_0x0160:
            com.adobe.internal.xmp.XMPException r0 = new com.adobe.internal.xmp.XMPException
            java.lang.String r1 = "Nested content not allowed with rdf:resource or property attributes"
            r0.<init>(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.ParseRDF.rdf_EmptyPropertyElement(com.adobe.internal.xmp.impl.XMPMetaImpl, com.adobe.internal.xmp.impl.XMPNode, org.w3c.dom.Node, boolean):void");
    }

    private static void rdf_LiteralPropertyElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) {
        XMPNode addChildNode = addChildNode(xMPMetaImpl, xMPNode, node, (String) null, z);
        int i2 = 0;
        for (int i7 = 0; i7 < node.getAttributes().getLength(); i7++) {
            Node item = node.getAttributes().item(i7);
            if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                String namespaceURI = item.getNamespaceURI();
                String localName = item.getLocalName();
                if (XMPConst.XML_LANG.equals(item.getNodeName())) {
                    addQualifierNode(addChildNode, XMPConst.XML_LANG, item.getNodeValue());
                } else if (!XMPConst.NS_RDF.equals(namespaceURI) || (!"ID".equals(localName) && !"datatype".equals(localName))) {
                    throw new XMPException("Invalid attribute for literal property element", 202);
                }
            }
        }
        String str = "";
        while (i2 < node.getChildNodes().getLength()) {
            Node item2 = node.getChildNodes().item(i2);
            if (item2.getNodeType() == 3) {
                StringBuilder s = C0212a.s(str);
                s.append(item2.getNodeValue());
                str = s.toString();
                i2++;
            } else {
                throw new XMPException("Invalid child of literal property element", 202);
            }
        }
        addChildNode.setValue(str);
    }

    private static void rdf_NodeElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z, ParseOptions parseOptions) {
        int rDFTermKind = getRDFTermKind(node);
        if (rDFTermKind != 8 && rDFTermKind != 0) {
            throw new XMPException("Node element must be rdf:Description or typed node", 202);
        } else if (!z || rDFTermKind != 0) {
            rdf_NodeElementAttrs(xMPMetaImpl, xMPNode, node, z, parseOptions);
            rdf_PropertyElementList(xMPMetaImpl, xMPNode, node, z, parseOptions);
        } else {
            throw new XMPException("Top level typed node not allowed", 203);
        }
    }

    private static void rdf_NodeElementAttrs(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z, ParseOptions parseOptions) {
        int i2 = 0;
        for (int i7 = 0; i7 < node.getAttributes().getLength(); i7++) {
            Node item = node.getAttributes().item(i7);
            if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                int rDFTermKind = getRDFTermKind(item);
                if (rDFTermKind == 0) {
                    addChildNode(xMPMetaImpl, xMPNode, item, item.getNodeValue(), z);
                } else if (rDFTermKind != 6 && rDFTermKind != 2 && rDFTermKind != 3) {
                    throw new XMPException("Invalid nodeElement attribute", 202);
                } else if (i2 <= 0) {
                    i2++;
                    if (z && rDFTermKind == 3) {
                        if (xMPNode.getName() == null || xMPNode.getName().length() <= 0) {
                            xMPNode.setName(item.getNodeValue());
                        } else if (!xMPNode.getName().equals(item.getNodeValue())) {
                            throw new XMPException("Mismatched top level rdf:about values", 203);
                        }
                    }
                } else {
                    throw new XMPException("Mutally exclusive about, ID, nodeID attributes", 202);
                }
            }
        }
    }

    private static void rdf_NodeElementList(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, ParseOptions parseOptions) {
        for (int i2 = 0; i2 < node.getChildNodes().getLength(); i2++) {
            Node item = node.getChildNodes().item(i2);
            if (!isWhitespaceNode(item)) {
                rdf_NodeElement(xMPMetaImpl, xMPNode, item, true, parseOptions);
            }
        }
    }

    private static void rdf_ParseTypeCollectionPropertyElement() {
        throw new XMPException("ParseTypeCollection property element not allowed", 203);
    }

    private static void rdf_ParseTypeLiteralPropertyElement() {
        throw new XMPException("ParseTypeLiteral property element not allowed", 203);
    }

    private static void rdf_ParseTypeOtherPropertyElement() {
        throw new XMPException("ParseTypeOther property element not allowed", 203);
    }

    private static void rdf_ParseTypeResourcePropertyElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z, ParseOptions parseOptions) {
        XMPNode addChildNode = addChildNode(xMPMetaImpl, xMPNode, node, "", z);
        addChildNode.getOptions().setStruct(true);
        for (int i2 = 0; i2 < node.getAttributes().getLength(); i2++) {
            Node item = node.getAttributes().item(i2);
            if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                String localName = item.getLocalName();
                String namespaceURI = item.getNamespaceURI();
                if (XMPConst.XML_LANG.equals(item.getNodeName())) {
                    addQualifierNode(addChildNode, XMPConst.XML_LANG, item.getNodeValue());
                } else if (!XMPConst.NS_RDF.equals(namespaceURI) || (!"ID".equals(localName) && !"parseType".equals(localName))) {
                    throw new XMPException("Invalid attribute for ParseTypeResource property element", 202);
                }
            }
        }
        rdf_PropertyElementList(xMPMetaImpl, addChildNode, node, false, parseOptions);
        if (addChildNode.getHasValueChild()) {
            fixupQualifiedNode(addChildNode);
        }
    }

    private static void rdf_PropertyElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z, ParseOptions parseOptions) {
        if (isPropertyElementName(getRDFTermKind(node))) {
            NamedNodeMap attributes = node.getAttributes();
            ArrayList<String> arrayList = null;
            for (int i2 = 0; i2 < attributes.getLength(); i2++) {
                Node item = attributes.item(i2);
                if ("xmlns".equals(item.getPrefix()) || (item.getPrefix() == null && "xmlns".equals(item.getNodeName()))) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    arrayList.add(item.getNodeName());
                }
            }
            if (arrayList != null) {
                for (String removeNamedItem : arrayList) {
                    attributes.removeNamedItem(removeNamedItem);
                }
            }
            if (attributes.getLength() > 3) {
                rdf_EmptyPropertyElement(xMPMetaImpl, xMPNode, node, z);
                return;
            }
            int i7 = 0;
            while (i7 < attributes.getLength()) {
                Node item2 = attributes.item(i7);
                String localName = item2.getLocalName();
                String namespaceURI = item2.getNamespaceURI();
                String nodeValue = item2.getNodeValue();
                if (XMPConst.XML_LANG.equals(item2.getNodeName()) && (!"ID".equals(localName) || !XMPConst.NS_RDF.equals(namespaceURI))) {
                    i7++;
                } else if ("datatype".equals(localName) && XMPConst.NS_RDF.equals(namespaceURI)) {
                    rdf_LiteralPropertyElement(xMPMetaImpl, xMPNode, node, z);
                    return;
                } else if (!"parseType".equals(localName) || !XMPConst.NS_RDF.equals(namespaceURI)) {
                    rdf_EmptyPropertyElement(xMPMetaImpl, xMPNode, node, z);
                    return;
                } else if ("Literal".equals(nodeValue)) {
                    rdf_ParseTypeLiteralPropertyElement();
                    return;
                } else if ("Resource".equals(nodeValue)) {
                    rdf_ParseTypeResourcePropertyElement(xMPMetaImpl, xMPNode, node, z, parseOptions);
                    return;
                } else if ("Collection".equals(nodeValue)) {
                    rdf_ParseTypeCollectionPropertyElement();
                    return;
                } else {
                    rdf_ParseTypeOtherPropertyElement();
                    return;
                }
            }
            if (node.hasChildNodes()) {
                for (int i8 = 0; i8 < node.getChildNodes().getLength(); i8++) {
                    if (node.getChildNodes().item(i8).getNodeType() != 3) {
                        rdf_ResourcePropertyElement(xMPMetaImpl, xMPNode, node, z, parseOptions);
                        return;
                    }
                }
                rdf_LiteralPropertyElement(xMPMetaImpl, xMPNode, node, z);
                return;
            }
            rdf_EmptyPropertyElement(xMPMetaImpl, xMPNode, node, z);
            return;
        }
        throw new XMPException("Invalid property element name", 202);
    }

    private static void rdf_PropertyElementList(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z, ParseOptions parseOptions) {
        for (int i2 = 0; i2 < node.getChildNodes().getLength(); i2++) {
            Node item = node.getChildNodes().item(i2);
            if (!isWhitespaceNode(item)) {
                if (item.getNodeType() != 1) {
                    throw new XMPException("Expected property element node not found", 202);
                } else if (!xMPNode.getOptions().isArrayLimited() || i2 <= xMPNode.getOptions().getArrayElementsLimit()) {
                    rdf_PropertyElement(xMPMetaImpl, xMPNode, item, z, parseOptions);
                } else {
                    return;
                }
            }
        }
    }

    public static void rdf_RDF(XMPMetaImpl xMPMetaImpl, Node node, ParseOptions parseOptions) {
        if (node.hasAttributes()) {
            rdf_NodeElementList(xMPMetaImpl, xMPMetaImpl.getRoot(), node, parseOptions);
            return;
        }
        throw new XMPException("Invalid attributes of rdf:RDF element", 202);
    }

    private static void rdf_ResourcePropertyElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z, ParseOptions parseOptions) {
        Integer num;
        if (!z || !"iX:changes".equals(node.getNodeName())) {
            XMPNode addChildNode = addChildNode(xMPMetaImpl, xMPNode, node, "", z);
            for (int i2 = 0; i2 < node.getAttributes().getLength(); i2++) {
                Node item = node.getAttributes().item(i2);
                if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                    String localName = item.getLocalName();
                    String namespaceURI = item.getNamespaceURI();
                    if (XMPConst.XML_LANG.equals(item.getNodeName())) {
                        addQualifierNode(addChildNode, XMPConst.XML_LANG, item.getNodeValue());
                    } else if (!"ID".equals(localName) || !XMPConst.NS_RDF.equals(namespaceURI)) {
                        throw new XMPException("Invalid attribute for resource property element", 202);
                    }
                }
            }
            boolean z3 = false;
            for (int i7 = 0; i7 < node.getChildNodes().getLength(); i7++) {
                Node item2 = node.getChildNodes().item(i7);
                if (!isWhitespaceNode(item2)) {
                    if (item2.getNodeType() == 1 && !z3) {
                        boolean equals = XMPConst.NS_RDF.equals(item2.getNamespaceURI());
                        String localName2 = item2.getLocalName();
                        if (equals && "Bag".equals(localName2)) {
                            addChildNode.getOptions().setArray(true);
                        } else if (equals && "Seq".equals(localName2)) {
                            addChildNode.getOptions().setArray(true).setArrayOrdered(true);
                        } else if (!equals || !"Alt".equals(localName2)) {
                            addChildNode.getOptions().setStruct(true);
                            if (!equals && !"Description".equals(localName2)) {
                                String namespaceURI2 = item2.getNamespaceURI();
                                if (namespaceURI2 != null) {
                                    addQualifierNode(addChildNode, XMPConst.RDF_TYPE, namespaceURI2 + ':' + localName2);
                                } else {
                                    throw new XMPException("All XML elements must be in a namespace", 203);
                                }
                            }
                        } else {
                            addChildNode.getOptions().setArray(true).setArrayOrdered(true).setArrayAlternate(true);
                        }
                        if (addChildNode.getOptions().isArray() && (num = parseOptions.getXMPNodesToLimit().get(addChildNode.getName())) != null) {
                            addChildNode.getOptions().setArrayElementLimit(num.intValue());
                        }
                        rdf_NodeElement(xMPMetaImpl, addChildNode, item2, false, parseOptions);
                        if (addChildNode.getHasValueChild()) {
                            fixupQualifiedNode(addChildNode);
                        } else if (addChildNode.getOptions().isArrayAlternate()) {
                            XMPNodeUtils.detectAltText(addChildNode);
                        }
                        z3 = true;
                    } else if (z3) {
                        throw new XMPException("Invalid child of resource property element", 202);
                    } else {
                        throw new XMPException("Children of resource property element must be XML elements", 202);
                    }
                }
            }
            if (!z3) {
                throw new XMPException("Missing child of resource property element", 202);
            }
        }
    }
}
