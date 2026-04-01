package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPConst;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.XMPSchemaRegistry;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XMPSerializerRDF {
    private static final int DEFAULT_PAD = 2048;
    private static final String PACKET_HEADER = "<?xpacket begin=\"﻿\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?>";
    private static final String PACKET_TRAILER = "<?xpacket end=\"";
    private static final String PACKET_TRAILER2 = "\"?>";
    static final Set RDF_ATTR_QUALIFIER = new HashSet(Arrays.asList(new String[]{XMPConst.XML_LANG, "rdf:resource", "rdf:ID", "rdf:bagID", "rdf:nodeID"}));
    private static final String RDF_EMPTY_STRUCT = "<rdf:Description/>";
    private static final String RDF_RDF_END = "</rdf:RDF>";
    private static final String RDF_RDF_START = "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">";
    private static final String RDF_SCHEMA_END = "</rdf:Description>";
    private static final String RDF_SCHEMA_START = "<rdf:Description rdf:about=";
    private static final String RDF_STRUCT_END = "</rdf:Description>";
    private static final String RDF_STRUCT_START = "<rdf:Description";
    private static final String RDF_XMPMETA_END = "</x:xmpmeta>";
    private static final String RDF_XMPMETA_START = "<x:xmpmeta xmlns:x=\"adobe:ns:meta/\" x:xmptk=\"";
    private SerializeOptions options;
    private CountOutputStream outputStream;
    private int padding;
    private int unicodeSize = 1;
    private OutputStreamWriter writer;
    private XMPMetaImpl xmp;

    private void addPadding(int i2) {
        if (this.options.getExactPacketLength()) {
            int bytesWritten = (i2 * this.unicodeSize) + this.outputStream.getBytesWritten();
            int i7 = this.padding;
            if (bytesWritten <= i7) {
                this.padding = i7 - bytesWritten;
            } else {
                throw new XMPException("Can't fit into specified packet size", 107);
            }
        }
        this.padding /= this.unicodeSize;
        int length = this.options.getNewline().length();
        int i8 = this.padding;
        if (i8 >= length) {
            this.padding = i8 - length;
            while (true) {
                int i10 = this.padding;
                int i11 = length + 100;
                if (i10 >= i11) {
                    writeChars(100, ' ');
                    writeNewline();
                    this.padding -= i11;
                } else {
                    writeChars(i10, ' ');
                    writeNewline();
                    return;
                }
            }
        } else {
            writeChars(i8, ' ');
        }
    }

    private void appendNodeValue(String str, boolean z) {
        if (str == null) {
            str = "";
        }
        write(Utils.escapeXML(str, z, true));
    }

    private boolean canBeRDFAttrProp(XMPNode xMPNode) {
        if (xMPNode.hasQualifier() || xMPNode.getOptions().isURI() || xMPNode.getOptions().isCompositeProperty() || XMPConst.ARRAY_ITEM_NAME.equals(xMPNode.getName())) {
            return false;
        }
        return true;
    }

    private void declareNamespace(String str, String str2, Set set, int i2) {
        if (str2 == null) {
            QName qName = new QName(str);
            if (qName.hasPrefix()) {
                str = qName.getPrefix();
                XMPSchemaRegistry schemaRegistry = XMPMetaFactory.getSchemaRegistry();
                str2 = schemaRegistry.getNamespaceURI(str + NumericEnum.SEP);
                declareNamespace(str, str2, set, i2);
            } else {
                return;
            }
        }
        if (!set.contains(str)) {
            writeNewline();
            writeIndent(i2);
            write("xmlns:");
            write(str);
            write("=\"");
            write(str2);
            write(34);
            set.add(str);
        }
    }

    private void declareUsedNamespaces(XMPNode xMPNode, Set set, int i2) {
        if (xMPNode.getOptions().isSchemaNode()) {
            declareNamespace(xMPNode.getValue().substring(0, xMPNode.getValue().length() - 1), xMPNode.getName(), set, i2);
        } else if (xMPNode.getOptions().isStruct()) {
            Iterator iterateChildren = xMPNode.iterateChildren();
            while (iterateChildren.hasNext()) {
                declareNamespace(((XMPNode) iterateChildren.next()).getName(), (String) null, set, i2);
            }
        }
        Iterator iterateChildren2 = xMPNode.iterateChildren();
        while (iterateChildren2.hasNext()) {
            declareUsedNamespaces((XMPNode) iterateChildren2.next(), set, i2);
        }
        Iterator iterateQualifier = xMPNode.iterateQualifier();
        while (iterateQualifier.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) iterateQualifier.next();
            declareNamespace(xMPNode2.getName(), (String) null, set, i2);
            declareUsedNamespaces(xMPNode2, set, i2);
        }
    }

    private void emitRDFArrayTag(XMPNode xMPNode, boolean z, int i2) {
        String str;
        if (z || xMPNode.hasChildren()) {
            writeIndent(i2);
            if (z) {
                str = "<rdf:";
            } else {
                str = "</rdf:";
            }
            write(str);
            if (xMPNode.getOptions().isArrayAlternate()) {
                write("Alt");
            } else if (xMPNode.getOptions().isArrayOrdered()) {
                write("Seq");
            } else {
                write("Bag");
            }
            if (!z || xMPNode.hasChildren()) {
                write(">");
            } else {
                write("/>");
            }
            writeNewline();
        }
    }

    private void endOuterRDFDescription(int i2) {
        writeIndent(i2 + 1);
        write("</rdf:Description>");
        writeNewline();
    }

    private String serializeAsRDF() {
        char c5;
        int i2 = 0;
        if (!this.options.getOmitPacketWrapper()) {
            writeIndent(0);
            write(PACKET_HEADER);
            writeNewline();
        }
        if (!this.options.getOmitXmpMetaElement()) {
            writeIndent(0);
            write(RDF_XMPMETA_START);
            if (!this.options.getOmitVersionAttribute()) {
                write(XMPMetaFactory.getVersionInfo().getMessage());
            }
            write("\">");
            writeNewline();
            i2 = 1;
        }
        writeIndent(i2);
        write(RDF_RDF_START);
        writeNewline();
        if (this.options.getUseCanonicalFormat()) {
            serializeCanonicalRDFSchemas(i2);
        } else {
            serializeCompactRDFSchemas(i2);
        }
        writeIndent(i2);
        write(RDF_RDF_END);
        writeNewline();
        if (!this.options.getOmitXmpMetaElement()) {
            writeIndent(i2 - 1);
            write(RDF_XMPMETA_END);
            writeNewline();
        }
        String str = "";
        if (this.options.getOmitPacketWrapper()) {
            return str;
        }
        for (int baseIndent = this.options.getBaseIndent(); baseIndent > 0; baseIndent--) {
            StringBuilder s = C0212a.s(str);
            s.append(this.options.getIndent());
            str = s.toString();
        }
        StringBuilder s5 = C0212a.s(C0212a.A(str, PACKET_TRAILER));
        if (this.options.getReadOnlyPacket()) {
            c5 = 'r';
        } else {
            c5 = 'w';
        }
        s5.append(c5);
        return C0212a.A(s5.toString(), PACKET_TRAILER2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:81:0x0220  */
    /* JADX WARNING: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void serializeCanonicalRDFProperty(com.adobe.internal.xmp.impl.XMPNode r18, boolean r19, boolean r20, int r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r21
            java.lang.String r4 = r1.getName()
            if (r20 == 0) goto L_0x0011
            java.lang.String r4 = "rdf:value"
            goto L_0x001b
        L_0x0011:
            java.lang.String r5 = "[]"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x001b
            java.lang.String r4 = "rdf:li"
        L_0x001b:
            r0.writeIndent(r3)
            r5 = 60
            r0.write((int) r5)
            r0.write((java.lang.String) r4)
            java.util.Iterator r5 = r1.iterateQualifier()
            r6 = 0
            r7 = r6
            r8 = r7
        L_0x002d:
            boolean r9 = r5.hasNext()
            r10 = 34
            java.lang.String r11 = "=\""
            r12 = 32
            r13 = 1
            if (r9 == 0) goto L_0x0072
            java.lang.Object r9 = r5.next()
            com.adobe.internal.xmp.impl.XMPNode r9 = (com.adobe.internal.xmp.impl.XMPNode) r9
            java.util.Set r14 = RDF_ATTR_QUALIFIER
            java.lang.String r15 = r9.getName()
            boolean r14 = r14.contains(r15)
            if (r14 != 0) goto L_0x004e
            r7 = r13
            goto L_0x002d
        L_0x004e:
            java.lang.String r8 = "rdf:resource"
            java.lang.String r14 = r9.getName()
            boolean r8 = r8.equals(r14)
            if (r20 != 0) goto L_0x002d
            r0.write((int) r12)
            java.lang.String r12 = r9.getName()
            r0.write((java.lang.String) r12)
            r0.write((java.lang.String) r11)
            java.lang.String r9 = r9.getValue()
            r0.appendNodeValue(r9, r13)
            r0.write((int) r10)
            goto L_0x002d
        L_0x0072:
            java.lang.String r5 = "</rdf:Description>"
            java.lang.String r9 = " rdf:parseType=\"Resource\">"
            java.lang.String r14 = "<rdf:Description"
            r15 = 202(0xca, float:2.83E-43)
            r10 = 62
            java.lang.String r12 = ">"
            if (r7 == 0) goto L_0x00dc
            if (r20 != 0) goto L_0x00dc
            if (r8 != 0) goto L_0x00d4
            if (r2 == 0) goto L_0x0098
            r0.write((java.lang.String) r12)
            r0.writeNewline()
            int r3 = r3 + 1
            r0.writeIndent(r3)
            r0.write((java.lang.String) r14)
            r0.write((java.lang.String) r12)
            goto L_0x009b
        L_0x0098:
            r0.write((java.lang.String) r9)
        L_0x009b:
            r0.writeNewline()
            int r7 = r3 + 1
            r0.serializeCanonicalRDFProperty(r1, r2, r13, r7)
            java.util.Iterator r1 = r1.iterateQualifier()
        L_0x00a7:
            boolean r8 = r1.hasNext()
            if (r8 == 0) goto L_0x00c3
            java.lang.Object r8 = r1.next()
            com.adobe.internal.xmp.impl.XMPNode r8 = (com.adobe.internal.xmp.impl.XMPNode) r8
            java.util.Set r9 = RDF_ATTR_QUALIFIER
            java.lang.String r11 = r8.getName()
            boolean r9 = r9.contains(r11)
            if (r9 != 0) goto L_0x00a7
            r0.serializeCanonicalRDFProperty(r8, r2, r6, r7)
            goto L_0x00a7
        L_0x00c3:
            if (r2 == 0) goto L_0x00d1
            r0.writeIndent(r3)
            r0.write((java.lang.String) r5)
            r0.writeNewline()
        L_0x00ce:
            int r1 = r3 + -1
            r3 = r1
        L_0x00d1:
            r6 = r13
            goto L_0x021e
        L_0x00d4:
            com.adobe.internal.xmp.XMPException r0 = new com.adobe.internal.xmp.XMPException
            java.lang.String r1 = "Can't mix rdf:resource and general qualifiers"
            r0.<init>(r1, r15)
            throw r0
        L_0x00dc:
            com.adobe.internal.xmp.options.PropertyOptions r7 = r1.getOptions()
            boolean r7 = r7.isCompositeProperty()
            java.lang.String r15 = "/>"
            if (r7 != 0) goto L_0x0134
            com.adobe.internal.xmp.options.PropertyOptions r2 = r1.getOptions()
            boolean r2 = r2.isURI()
            if (r2 == 0) goto L_0x0108
            java.lang.String r2 = " rdf:resource=\""
            r0.write((java.lang.String) r2)
            java.lang.String r1 = r1.getValue()
            r0.appendNodeValue(r1, r13)
            java.lang.String r1 = "\"/>"
            r0.write((java.lang.String) r1)
            r0.writeNewline()
            goto L_0x021e
        L_0x0108:
            java.lang.String r2 = r1.getValue()
            if (r2 == 0) goto L_0x012c
            java.lang.String r2 = ""
            java.lang.String r5 = r1.getValue()
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x011b
            goto L_0x012c
        L_0x011b:
            r0.write((int) r10)
            java.lang.String r1 = r1.getValue()
            r0.appendNodeValue(r1, r6)
            r16 = r13
            r13 = r6
            r6 = r16
            goto L_0x021e
        L_0x012c:
            r0.write((java.lang.String) r15)
            r0.writeNewline()
            goto L_0x021e
        L_0x0134:
            com.adobe.internal.xmp.options.PropertyOptions r7 = r1.getOptions()
            boolean r7 = r7.isArray()
            if (r7 == 0) goto L_0x0171
            r0.write((int) r10)
            r0.writeNewline()
            int r5 = r3 + 1
            r0.emitRDFArrayTag(r1, r13, r5)
            com.adobe.internal.xmp.options.PropertyOptions r7 = r1.getOptions()
            boolean r7 = r7.isArrayAltText()
            if (r7 == 0) goto L_0x0156
            com.adobe.internal.xmp.impl.XMPNodeUtils.normalizeLangArray(r1)
        L_0x0156:
            java.util.Iterator r7 = r1.iterateChildren()
        L_0x015a:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x016c
            java.lang.Object r8 = r7.next()
            com.adobe.internal.xmp.impl.XMPNode r8 = (com.adobe.internal.xmp.impl.XMPNode) r8
            int r9 = r3 + 2
            r0.serializeCanonicalRDFProperty(r8, r2, r6, r9)
            goto L_0x015a
        L_0x016c:
            r0.emitRDFArrayTag(r1, r6, r5)
            goto L_0x00d1
        L_0x0171:
            if (r8 != 0) goto L_0x01d4
            boolean r7 = r1.hasChildren()
            if (r7 != 0) goto L_0x0197
            if (r2 == 0) goto L_0x018d
            r0.write((java.lang.String) r12)
            r0.writeNewline()
            int r1 = r3 + 1
            r0.writeIndent(r1)
            java.lang.String r1 = "<rdf:Description/>"
            r0.write((java.lang.String) r1)
            r6 = r13
            goto L_0x0192
        L_0x018d:
            java.lang.String r1 = " rdf:parseType=\"Resource\"/>"
            r0.write((java.lang.String) r1)
        L_0x0192:
            r0.writeNewline()
            goto L_0x021e
        L_0x0197:
            if (r2 == 0) goto L_0x01ab
            r0.write((java.lang.String) r12)
            r0.writeNewline()
            int r3 = r3 + 1
            r0.writeIndent(r3)
            r0.write((java.lang.String) r14)
            r0.write((java.lang.String) r12)
            goto L_0x01ae
        L_0x01ab:
            r0.write((java.lang.String) r9)
        L_0x01ae:
            r0.writeNewline()
            java.util.Iterator r1 = r1.iterateChildren()
        L_0x01b5:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L_0x01c7
            java.lang.Object r7 = r1.next()
            com.adobe.internal.xmp.impl.XMPNode r7 = (com.adobe.internal.xmp.impl.XMPNode) r7
            int r8 = r3 + 1
            r0.serializeCanonicalRDFProperty(r7, r2, r6, r8)
            goto L_0x01b5
        L_0x01c7:
            if (r2 == 0) goto L_0x00d1
            r0.writeIndent(r3)
            r0.write((java.lang.String) r5)
            r0.writeNewline()
            goto L_0x00ce
        L_0x01d4:
            java.util.Iterator r1 = r1.iterateChildren()
        L_0x01d8:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0218
            java.lang.Object r2 = r1.next()
            com.adobe.internal.xmp.impl.XMPNode r2 = (com.adobe.internal.xmp.impl.XMPNode) r2
            boolean r5 = r0.canBeRDFAttrProp(r2)
            if (r5 == 0) goto L_0x020e
            r0.writeNewline()
            int r5 = r3 + 1
            r0.writeIndent(r5)
            r5 = 32
            r0.write((int) r5)
            java.lang.String r7 = r2.getName()
            r0.write((java.lang.String) r7)
            r0.write((java.lang.String) r11)
            java.lang.String r2 = r2.getValue()
            r0.appendNodeValue(r2, r13)
            r2 = 34
            r0.write((int) r2)
            goto L_0x01d8
        L_0x020e:
            com.adobe.internal.xmp.XMPException r0 = new com.adobe.internal.xmp.XMPException
            java.lang.String r1 = "Can't mix rdf:resource and complex fields"
            r2 = 202(0xca, float:2.83E-43)
            r0.<init>(r1, r2)
            throw r0
        L_0x0218:
            r0.write((java.lang.String) r15)
            r0.writeNewline()
        L_0x021e:
            if (r6 == 0) goto L_0x0233
            if (r13 == 0) goto L_0x0225
            r0.writeIndent(r3)
        L_0x0225:
            java.lang.String r1 = "</"
            r0.write((java.lang.String) r1)
            r0.write((java.lang.String) r4)
            r0.write((int) r10)
            r0.writeNewline()
        L_0x0233:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.XMPSerializerRDF.serializeCanonicalRDFProperty(com.adobe.internal.xmp.impl.XMPNode, boolean, boolean, int):void");
    }

    private void serializeCanonicalRDFSchema(XMPNode xMPNode, int i2) {
        Iterator iterateChildren = xMPNode.iterateChildren();
        while (iterateChildren.hasNext()) {
            serializeCanonicalRDFProperty((XMPNode) iterateChildren.next(), this.options.getUseCanonicalFormat(), false, i2 + 2);
        }
    }

    private void serializeCanonicalRDFSchemas(int i2) {
        if (this.xmp.getRoot().getChildrenLength() > 0) {
            startOuterRDFDescription(this.xmp.getRoot(), i2);
            Iterator iterateChildren = this.xmp.getRoot().iterateChildren();
            while (iterateChildren.hasNext()) {
                serializeCanonicalRDFSchema((XMPNode) iterateChildren.next(), i2);
            }
            endOuterRDFDescription(i2);
            return;
        }
        writeIndent(i2 + 1);
        write(RDF_SCHEMA_START);
        writeTreeName();
        write("/>");
        writeNewline();
    }

    private void serializeCompactRDFArrayProp(XMPNode xMPNode, int i2) {
        write(62);
        writeNewline();
        int i7 = i2 + 1;
        emitRDFArrayTag(xMPNode, true, i7);
        if (xMPNode.getOptions().isArrayAltText()) {
            XMPNodeUtils.normalizeLangArray(xMPNode);
        }
        serializeCompactRDFElementProps(xMPNode, i2 + 2);
        emitRDFArrayTag(xMPNode, false, i7);
    }

    private boolean serializeCompactRDFAttrProps(XMPNode xMPNode, int i2) {
        Iterator iterateChildren = xMPNode.iterateChildren();
        boolean z = true;
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) iterateChildren.next();
            if (canBeRDFAttrProp(xMPNode2)) {
                writeNewline();
                writeIndent(i2);
                write(xMPNode2.getName());
                write("=\"");
                appendNodeValue(xMPNode2.getValue(), true);
                write(34);
            } else {
                z = false;
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0004 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void serializeCompactRDFElementProps(com.adobe.internal.xmp.impl.XMPNode r12, int r13) {
        /*
            r11 = this;
            java.util.Iterator r12 = r12.iterateChildren()
        L_0x0004:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x00d0
            java.lang.Object r0 = r12.next()
            com.adobe.internal.xmp.impl.XMPNode r0 = (com.adobe.internal.xmp.impl.XMPNode) r0
            boolean r1 = r11.canBeRDFAttrProp(r0)
            if (r1 == 0) goto L_0x0017
            goto L_0x0004
        L_0x0017:
            java.lang.String r1 = r0.getName()
            java.lang.String r2 = "[]"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0025
            java.lang.String r1 = "rdf:li"
        L_0x0025:
            r11.writeIndent(r13)
            r2 = 60
            r11.write((int) r2)
            r11.write((java.lang.String) r1)
            java.util.Iterator r2 = r0.iterateQualifier()
            r3 = 0
            r4 = r3
            r5 = r4
        L_0x0037:
            boolean r6 = r2.hasNext()
            r7 = 1
            if (r6 == 0) goto L_0x007a
            java.lang.Object r6 = r2.next()
            com.adobe.internal.xmp.impl.XMPNode r6 = (com.adobe.internal.xmp.impl.XMPNode) r6
            java.util.Set r8 = RDF_ATTR_QUALIFIER
            java.lang.String r9 = r6.getName()
            boolean r8 = r8.contains(r9)
            if (r8 != 0) goto L_0x0052
            r4 = r7
            goto L_0x0037
        L_0x0052:
            java.lang.String r5 = "rdf:resource"
            java.lang.String r8 = r6.getName()
            boolean r5 = r5.equals(r8)
            r8 = 32
            r11.write((int) r8)
            java.lang.String r8 = r6.getName()
            r11.write((java.lang.String) r8)
            java.lang.String r8 = "=\""
            r11.write((java.lang.String) r8)
            java.lang.String r6 = r6.getValue()
            r11.appendNodeValue(r6, r7)
            r6 = 34
            r11.write((int) r6)
            goto L_0x0037
        L_0x007a:
            if (r4 == 0) goto L_0x0080
            r11.serializeCompactRDFGeneralQualifier(r13, r0)
            goto L_0x00ae
        L_0x0080:
            com.adobe.internal.xmp.options.PropertyOptions r2 = r0.getOptions()
            boolean r2 = r2.isCompositeProperty()
            if (r2 != 0) goto L_0x00a1
            java.lang.Object[] r0 = r11.serializeCompactRDFSimpleProp(r0)
            r2 = r0[r3]
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            r0 = r0[r7]
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r7 = r0.booleanValue()
            r0 = r7
            r7 = r2
            goto L_0x00b7
        L_0x00a1:
            com.adobe.internal.xmp.options.PropertyOptions r2 = r0.getOptions()
            boolean r2 = r2.isArray()
            if (r2 == 0) goto L_0x00b0
            r11.serializeCompactRDFArrayProp(r0, r13)
        L_0x00ae:
            r0 = r7
            goto L_0x00b7
        L_0x00b0:
            boolean r0 = r11.serializeCompactRDFStructProp(r0, r13, r5)
            r10 = r7
            r7 = r0
            r0 = r10
        L_0x00b7:
            if (r7 == 0) goto L_0x0004
            if (r0 == 0) goto L_0x00be
            r11.writeIndent(r13)
        L_0x00be:
            java.lang.String r0 = "</"
            r11.write((java.lang.String) r0)
            r11.write((java.lang.String) r1)
            r0 = 62
            r11.write((int) r0)
            r11.writeNewline()
            goto L_0x0004
        L_0x00d0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.XMPSerializerRDF.serializeCompactRDFElementProps(com.adobe.internal.xmp.impl.XMPNode, int):void");
    }

    private void serializeCompactRDFGeneralQualifier(int i2, XMPNode xMPNode) {
        write(" rdf:parseType=\"Resource\">");
        writeNewline();
        int i7 = i2 + 1;
        serializeCanonicalRDFProperty(xMPNode, false, true, i7);
        Iterator iterateQualifier = xMPNode.iterateQualifier();
        while (iterateQualifier.hasNext()) {
            serializeCanonicalRDFProperty((XMPNode) iterateQualifier.next(), false, false, i7);
        }
    }

    private void serializeCompactRDFSchemas(int i2) {
        int i7 = i2 + 1;
        writeIndent(i7);
        write(RDF_SCHEMA_START);
        writeTreeName();
        HashSet hashSet = new HashSet();
        hashSet.add("xml");
        hashSet.add("rdf");
        Iterator iterateChildren = this.xmp.getRoot().iterateChildren();
        while (iterateChildren.hasNext()) {
            declareUsedNamespaces((XMPNode) iterateChildren.next(), hashSet, i2 + 3);
        }
        Iterator iterateChildren2 = this.xmp.getRoot().iterateChildren();
        boolean z = true;
        while (iterateChildren2.hasNext()) {
            z &= serializeCompactRDFAttrProps((XMPNode) iterateChildren2.next(), i2 + 2);
        }
        if (!z) {
            write(62);
            writeNewline();
            Iterator iterateChildren3 = this.xmp.getRoot().iterateChildren();
            while (iterateChildren3.hasNext()) {
                serializeCompactRDFElementProps((XMPNode) iterateChildren3.next(), i2 + 2);
            }
            writeIndent(i7);
            write("</rdf:Description>");
            writeNewline();
            return;
        }
        write("/>");
        writeNewline();
    }

    private Object[] serializeCompactRDFSimpleProp(XMPNode xMPNode) {
        Boolean bool;
        Boolean bool2;
        Boolean bool3 = Boolean.TRUE;
        if (xMPNode.getOptions().isURI()) {
            write(" rdf:resource=\"");
            appendNodeValue(xMPNode.getValue(), true);
            write("\"/>");
            writeNewline();
            bool2 = Boolean.FALSE;
        } else if (xMPNode.getValue() == null || xMPNode.getValue().length() == 0) {
            write("/>");
            writeNewline();
            bool2 = Boolean.FALSE;
        } else {
            write(62);
            appendNodeValue(xMPNode.getValue(), false);
            bool = Boolean.FALSE;
            return new Object[]{bool3, bool};
        }
        Boolean bool4 = bool3;
        bool3 = bool2;
        bool = bool4;
        return new Object[]{bool3, bool};
    }

    private boolean serializeCompactRDFStructProp(XMPNode xMPNode, int i2, boolean z) {
        Iterator iterateChildren = xMPNode.iterateChildren();
        boolean z3 = false;
        boolean z7 = false;
        while (iterateChildren.hasNext()) {
            if (canBeRDFAttrProp((XMPNode) iterateChildren.next())) {
                z3 = true;
            } else {
                z7 = true;
            }
            if (z3 && z7) {
                break;
            }
        }
        if (z && z7) {
            throw new XMPException("Can't mix rdf:resource qualifier and element fields", 202);
        } else if (!xMPNode.hasChildren()) {
            write(" rdf:parseType=\"Resource\"/>");
            writeNewline();
            return false;
        } else if (!z7) {
            serializeCompactRDFAttrProps(xMPNode, i2 + 1);
            write("/>");
            writeNewline();
            return false;
        } else if (!z3) {
            write(" rdf:parseType=\"Resource\">");
            writeNewline();
            serializeCompactRDFElementProps(xMPNode, i2 + 1);
            return true;
        } else {
            write(62);
            writeNewline();
            int i7 = i2 + 1;
            writeIndent(i7);
            write(RDF_STRUCT_START);
            serializeCompactRDFAttrProps(xMPNode, i2 + 2);
            write(">");
            writeNewline();
            serializeCompactRDFElementProps(xMPNode, i7);
            writeIndent(i7);
            write("</rdf:Description>");
            writeNewline();
            return true;
        }
    }

    private void startOuterRDFDescription(XMPNode xMPNode, int i2) {
        writeIndent(i2 + 1);
        write(RDF_SCHEMA_START);
        writeTreeName();
        HashSet hashSet = new HashSet();
        hashSet.add("xml");
        hashSet.add("rdf");
        declareUsedNamespaces(xMPNode, hashSet, i2 + 3);
        write(62);
        writeNewline();
    }

    private void write(int i2) {
        this.writer.write(i2);
    }

    private void writeChars(int i2, char c5) {
        while (i2 > 0) {
            this.writer.write(c5);
            i2--;
        }
    }

    private void writeIndent(int i2) {
        for (int baseIndent = this.options.getBaseIndent() + i2; baseIndent > 0; baseIndent--) {
            this.writer.write(this.options.getIndent());
        }
    }

    private void writeNewline() {
        this.writer.write(this.options.getNewline());
    }

    private void writeTreeName() {
        write(34);
        String name = this.xmp.getRoot().getName();
        if (name != null) {
            appendNodeValue(name, true);
        }
        write(34);
    }

    public void checkOptionsConsistence() {
        if (this.options.getEncodeUTF16BE() || this.options.getEncodeUTF16LE()) {
            this.unicodeSize = 2;
        }
        if (this.options.getExactPacketLength()) {
            if (!this.options.getOmitPacketWrapper() && !this.options.getIncludeThumbnailPad()) {
                if (((this.unicodeSize - 1) & this.options.getPadding()) != 0) {
                    throw new XMPException("Exact size must be a multiple of the Unicode element", 103);
                }
                return;
            }
            throw new XMPException("Inconsistent options for exact size serialize", 103);
        } else if (this.options.getReadOnlyPacket()) {
            if (!this.options.getOmitPacketWrapper() && !this.options.getIncludeThumbnailPad()) {
                this.padding = 0;
                return;
            }
            throw new XMPException("Inconsistent options for read-only packet", 103);
        } else if (!this.options.getOmitPacketWrapper()) {
            if (this.padding == 0) {
                this.padding = this.unicodeSize * 2048;
            }
            if (this.options.getIncludeThumbnailPad() && !this.xmp.doesPropertyExist(XMPConst.NS_XMP, "Thumbnails")) {
                this.padding = (this.unicodeSize * 10000) + this.padding;
            }
        } else if (!this.options.getIncludeThumbnailPad()) {
            this.padding = 0;
        } else {
            throw new XMPException("Inconsistent options for non-packet serialize", 103);
        }
    }

    public void serialize(XMPMeta xMPMeta, OutputStream outputStream2, SerializeOptions serializeOptions) {
        try {
            this.outputStream = new CountOutputStream(outputStream2);
            this.writer = new OutputStreamWriter(this.outputStream, serializeOptions.getEncoding());
            this.xmp = (XMPMetaImpl) xMPMeta;
            this.options = serializeOptions;
            this.padding = serializeOptions.getPadding();
            this.writer = new OutputStreamWriter(this.outputStream, serializeOptions.getEncoding());
            checkOptionsConsistence();
            String serializeAsRDF = serializeAsRDF();
            this.writer.flush();
            addPadding(serializeAsRDF.length());
            write(serializeAsRDF);
            this.writer.flush();
            this.outputStream.close();
        } catch (IOException unused) {
            throw new XMPException("Error writing to the OutputStream", 0);
        }
    }

    private void write(String str) {
        this.writer.write(str);
    }
}
