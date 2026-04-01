package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.options.ParseOptions;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XMPMetaParser {
    private static final Object XMP_RDF = new Object();
    private static DocumentBuilderFactory factory = createDocumentBuilderFactory();

    private XMPMetaParser() {
    }

    private static DocumentBuilderFactory createDocumentBuilderFactory() {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        newInstance.setIgnoringComments(true);
        newInstance.setExpandEntityReferences(false);
        try {
            newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            newInstance.setFeature("http://xerces.apache.org/xerces2-j/features.html#disallow-doctype-decl", false);
            newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            newInstance.setFeature("http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities", false);
            newInstance.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            newInstance.setXIncludeAware(false);
            newInstance.setExpandEntityReferences(false);
        } catch (Throwable unused) {
        }
        return newInstance;
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object[] findRootNode(org.w3c.dom.Node r7, boolean r8, java.lang.Object[] r9) {
        /*
            org.w3c.dom.NodeList r7 = r7.getChildNodes()
            r0 = 0
            r1 = r0
        L_0x0006:
            int r2 = r7.getLength()
            if (r1 >= r2) goto L_0x008c
            org.w3c.dom.Node r2 = r7.item(r1)
            short r3 = r2.getNodeType()
            r4 = 7
            if (r4 != r3) goto L_0x0031
            r3 = r2
            org.w3c.dom.ProcessingInstruction r3 = (org.w3c.dom.ProcessingInstruction) r3
            java.lang.String r5 = r3.getTarget()
            java.lang.String r6 = "xpacket"
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L_0x0031
            if (r9 == 0) goto L_0x0088
            r2 = 2
            java.lang.String r3 = r3.getData()
            r9[r2] = r3
            goto L_0x0088
        L_0x0031:
            r3 = 3
            short r5 = r2.getNodeType()
            if (r3 == r5) goto L_0x0088
            short r3 = r2.getNodeType()
            if (r4 == r3) goto L_0x0088
            java.lang.String r3 = r2.getNamespaceURI()
            java.lang.String r4 = r2.getLocalName()
            java.lang.String r5 = "xmpmeta"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x0058
            java.lang.String r5 = "xapmeta"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0065
        L_0x0058:
            java.lang.String r5 = "adobe:ns:meta/"
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L_0x0065
            java.lang.Object[] r7 = findRootNode(r2, r0, r9)
            return r7
        L_0x0065:
            if (r8 != 0) goto L_0x0081
            java.lang.String r5 = "RDF"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0081
            java.lang.String r4 = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0081
            if (r9 == 0) goto L_0x0080
            r9[r0] = r2
            java.lang.Object r7 = XMP_RDF
            r8 = 1
            r9[r8] = r7
        L_0x0080:
            return r9
        L_0x0081:
            java.lang.Object[] r2 = findRootNode(r2, r8, r9)
            if (r2 == 0) goto L_0x0088
            return r2
        L_0x0088:
            int r1 = r1 + 1
            goto L_0x0006
        L_0x008c:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.XMPMetaParser.findRootNode(org.w3c.dom.Node, boolean, java.lang.Object[]):java.lang.Object[]");
    }

    public static XMPMeta parse(Object obj, ParseOptions parseOptions) {
        ParameterAsserts.assertNotNull(obj);
        if (parseOptions == null) {
            parseOptions = new ParseOptions();
        }
        Object[] findRootNode = findRootNode(parseXml(obj, parseOptions), parseOptions.getRequireXMPMeta(), new Object[3]);
        if (findRootNode == null || findRootNode[1] != XMP_RDF) {
            return new XMPMetaImpl();
        }
        XMPMetaImpl parse = ParseRDF.parse((Node) findRootNode[0], parseOptions);
        parse.setPacketHeader((String) findRootNode[2]);
        if (!parseOptions.getOmitNormalization()) {
            return XMPNormalizer.process(parse, parseOptions);
        }
        return parse;
    }

    private static Document parseInputSource(InputSource inputSource) {
        try {
            DocumentBuilder newDocumentBuilder = factory.newDocumentBuilder();
            newDocumentBuilder.setErrorHandler((ErrorHandler) null);
            return newDocumentBuilder.parse(inputSource);
        } catch (SAXException e) {
            throw new XMPException("XML parsing failure", 201, e);
        } catch (ParserConfigurationException e7) {
            throw new XMPException("XML Parser not correctly configured", 0, e7);
        } catch (IOException e8) {
            throw new XMPException("Error reading the XML-file", 204, e8);
        }
    }

    private static Document parseXml(Object obj, ParseOptions parseOptions) {
        if (obj instanceof InputStream) {
            return parseXmlFromInputStream((InputStream) obj, parseOptions);
        }
        if (obj instanceof byte[]) {
            return parseXmlFromBytebuffer(new ByteBuffer((byte[]) obj), parseOptions);
        }
        return parseXmlFromString((String) obj, parseOptions);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|(2:5|6)|7|8|9) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0017 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.w3c.dom.Document parseXmlFromBytebuffer(com.adobe.internal.xmp.impl.ByteBuffer r4, com.adobe.internal.xmp.options.ParseOptions r5) {
        /*
            org.xml.sax.InputSource r0 = new org.xml.sax.InputSource     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            java.io.InputStream r1 = r4.getByteStream()     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            r0.<init>(r1)     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            boolean r1 = r5.getDisallowDoctype()     // Catch:{ XMPException -> 0x001c }
            if (r1 == 0) goto L_0x0017
            javax.xml.parsers.DocumentBuilderFactory r1 = factory     // Catch:{ all -> 0x0017 }
            java.lang.String r2 = "http://apache.org/xml/features/disallow-doctype-decl"
            r3 = 1
            r1.setFeature(r2, r3)     // Catch:{ all -> 0x0017 }
        L_0x0017:
            org.w3c.dom.Document r4 = parseInputSource(r0)     // Catch:{ XMPException -> 0x001c }
            return r4
        L_0x001c:
            r0 = move-exception
            java.lang.String r1 = "DOCTYPE is disallowed"
            java.lang.Throwable r2 = r0.getCause()     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            boolean r1 = r1.equals(r2)     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            r2 = 201(0xc9, float:2.82E-43)
            if (r1 != 0) goto L_0x0079
            int r1 = r0.getErrorCode()     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            if (r1 == r2) goto L_0x003f
            int r1 = r0.getErrorCode()     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            r2 = 204(0xcc, float:2.86E-43)
            if (r1 != r2) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            throw r0     // Catch:{ UnsupportedEncodingException -> 0x0087 }
        L_0x003f:
            boolean r0 = r5.getAcceptLatin1()     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            if (r0 == 0) goto L_0x0049
            com.adobe.internal.xmp.impl.ByteBuffer r4 = com.adobe.internal.xmp.impl.Latin1Converter.convert(r4)     // Catch:{ UnsupportedEncodingException -> 0x0087 }
        L_0x0049:
            boolean r5 = r5.getFixControlChars()     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            if (r5 == 0) goto L_0x006b
            java.lang.String r5 = r4.getEncoding()     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            com.adobe.internal.xmp.impl.FixASCIIControlsReader r0 = new com.adobe.internal.xmp.impl.FixASCIIControlsReader     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            java.io.InputStream r4 = r4.getByteStream()     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            r1.<init>(r4, r5)     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            r0.<init>(r1)     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            org.xml.sax.InputSource r4 = new org.xml.sax.InputSource     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            r4.<init>(r0)     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            org.w3c.dom.Document r4 = parseInputSource(r4)     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            return r4
        L_0x006b:
            org.xml.sax.InputSource r5 = new org.xml.sax.InputSource     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            java.io.InputStream r4 = r4.getByteStream()     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            r5.<init>(r4)     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            org.w3c.dom.Document r4 = parseInputSource(r5)     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            return r4
        L_0x0079:
            com.adobe.internal.xmp.XMPException r4 = new com.adobe.internal.xmp.XMPException     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            java.lang.Throwable r5 = r0.getCause()     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            java.lang.String r5 = r5.getMessage()     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            r4.<init>(r5, r2)     // Catch:{ UnsupportedEncodingException -> 0x0087 }
            throw r4     // Catch:{ UnsupportedEncodingException -> 0x0087 }
        L_0x0087:
            r4 = move-exception
            com.adobe.internal.xmp.XMPException r5 = new com.adobe.internal.xmp.XMPException
            java.lang.String r0 = "Unsupported Encoding"
            r1 = 9
            r5.<init>(r0, r1, r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.XMPMetaParser.parseXmlFromBytebuffer(com.adobe.internal.xmp.impl.ByteBuffer, com.adobe.internal.xmp.options.ParseOptions):org.w3c.dom.Document");
    }

    private static Document parseXmlFromInputStream(InputStream inputStream, ParseOptions parseOptions) {
        if (!parseOptions.getAcceptLatin1() && !parseOptions.getFixControlChars() && !parseOptions.getDisallowDoctype()) {
            return parseInputSource(new InputSource(inputStream));
        }
        try {
            return parseXmlFromBytebuffer(new ByteBuffer(inputStream), parseOptions);
        } catch (IOException e) {
            throw new XMPException("Error reading the XML-file", 204, e);
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.w3c.dom.Document parseXmlFromString(java.lang.String r3, com.adobe.internal.xmp.options.ParseOptions r4) {
        /*
            boolean r0 = r4.getDisallowDoctype()     // Catch:{ XMPException -> 0x001d }
            if (r0 == 0) goto L_0x000e
            javax.xml.parsers.DocumentBuilderFactory r0 = factory     // Catch:{ all -> 0x000e }
            java.lang.String r1 = "http://apache.org/xml/features/disallow-doctype-decl"
            r2 = 1
            r0.setFeature(r1, r2)     // Catch:{ all -> 0x000e }
        L_0x000e:
            org.xml.sax.InputSource r0 = new org.xml.sax.InputSource     // Catch:{ XMPException -> 0x001d }
            java.io.StringReader r1 = new java.io.StringReader     // Catch:{ XMPException -> 0x001d }
            r1.<init>(r3)     // Catch:{ XMPException -> 0x001d }
            r0.<init>(r1)     // Catch:{ XMPException -> 0x001d }
            org.w3c.dom.Document r3 = parseInputSource(r0)     // Catch:{ XMPException -> 0x001d }
            return r3
        L_0x001d:
            r0 = move-exception
            int r1 = r0.getErrorCode()
            r2 = 201(0xc9, float:2.82E-43)
            if (r1 != r2) goto L_0x0040
            boolean r4 = r4.getFixControlChars()
            if (r4 == 0) goto L_0x0040
            org.xml.sax.InputSource r4 = new org.xml.sax.InputSource
            com.adobe.internal.xmp.impl.FixASCIIControlsReader r0 = new com.adobe.internal.xmp.impl.FixASCIIControlsReader
            java.io.StringReader r1 = new java.io.StringReader
            r1.<init>(r3)
            r0.<init>(r1)
            r4.<init>(r0)
            org.w3c.dom.Document r3 = parseInputSource(r4)
            return r3
        L_0x0040:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.XMPMetaParser.parseXmlFromString(java.lang.String, com.adobe.internal.xmp.options.ParseOptions):org.w3c.dom.Document");
    }
}
