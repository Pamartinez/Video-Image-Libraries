package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPConst;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPSchemaRegistry;
import com.adobe.internal.xmp.options.AliasOptions;
import com.adobe.internal.xmp.properties.XMPAliasInfo;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class XMPSchemaRegistryImpl implements XMPSchemaRegistry, XMPConst {
    private Map aliasMap = new HashMap();
    private Map namespaceToPrefixMap = new HashMap();

    /* renamed from: p  reason: collision with root package name */
    private Pattern f1098p = Pattern.compile("[/*?\\[\\]]");
    private Map prefixToNamespaceMap = new HashMap();

    public XMPSchemaRegistryImpl() {
        try {
            registerStandardNamespaces();
            registerStandardAliases();
        } catch (XMPException unused) {
            throw new RuntimeException("The XMPSchemaRegistry cannot be initialized!");
        }
    }

    private void registerStandardAliases() {
        AliasOptions arrayOrdered = new AliasOptions().setArrayOrdered(true);
        AliasOptions arrayAltText = new AliasOptions().setArrayAltText(true);
        registerAlias(XMPConst.NS_XMP, "Author", XMPConst.NS_DC, "creator", arrayOrdered);
        registerAlias(XMPConst.NS_XMP, "Authors", XMPConst.NS_DC, "creator", (AliasOptions) null);
        registerAlias(XMPConst.NS_XMP, "Description", XMPConst.NS_DC, "description", (AliasOptions) null);
        registerAlias(XMPConst.NS_XMP, "Format", XMPConst.NS_DC, "format", (AliasOptions) null);
        registerAlias(XMPConst.NS_XMP, "Keywords", XMPConst.NS_DC, "subject", (AliasOptions) null);
        registerAlias(XMPConst.NS_XMP, "Locale", XMPConst.NS_DC, "language", (AliasOptions) null);
        registerAlias(XMPConst.NS_XMP, "Title", XMPConst.NS_DC, "title", (AliasOptions) null);
        registerAlias(XMPConst.NS_XMP_RIGHTS, "Copyright", XMPConst.NS_DC, "rights", (AliasOptions) null);
        registerAlias(XMPConst.NS_PDF, "Author", XMPConst.NS_DC, "creator", arrayOrdered);
        registerAlias(XMPConst.NS_PDF, "BaseURL", XMPConst.NS_XMP, "BaseURL", (AliasOptions) null);
        registerAlias(XMPConst.NS_PDF, "CreationDate", XMPConst.NS_XMP, "CreateDate", (AliasOptions) null);
        registerAlias(XMPConst.NS_PDF, "Creator", XMPConst.NS_XMP, "CreatorTool", (AliasOptions) null);
        registerAlias(XMPConst.NS_PDF, "ModDate", XMPConst.NS_XMP, "ModifyDate", (AliasOptions) null);
        registerAlias(XMPConst.NS_PDF, "Subject", XMPConst.NS_DC, "description", arrayAltText);
        registerAlias(XMPConst.NS_PDF, "Title", XMPConst.NS_DC, "title", arrayAltText);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Author", XMPConst.NS_DC, "creator", arrayOrdered);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Caption", XMPConst.NS_DC, "description", arrayAltText);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Copyright", XMPConst.NS_DC, "rights", arrayAltText);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Keywords", XMPConst.NS_DC, "subject", (AliasOptions) null);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Marked", XMPConst.NS_XMP_RIGHTS, "Marked", (AliasOptions) null);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Title", XMPConst.NS_DC, "title", arrayAltText);
        registerAlias(XMPConst.NS_PHOTOSHOP, "WebStatement", XMPConst.NS_XMP_RIGHTS, "WebStatement", (AliasOptions) null);
        registerAlias(XMPConst.NS_TIFF, "Artist", XMPConst.NS_DC, "creator", arrayOrdered);
        registerAlias(XMPConst.NS_TIFF, "Copyright", XMPConst.NS_DC, "rights", (AliasOptions) null);
        registerAlias(XMPConst.NS_TIFF, "DateTime", XMPConst.NS_XMP, "ModifyDate", (AliasOptions) null);
        registerAlias(XMPConst.NS_EXIF, "DateTimeDigitized", XMPConst.NS_XMP, "CreateDate", (AliasOptions) null);
        registerAlias(XMPConst.NS_TIFF, "ImageDescription", XMPConst.NS_DC, "description", (AliasOptions) null);
        registerAlias(XMPConst.NS_TIFF, "Software", XMPConst.NS_XMP, "CreatorTool", (AliasOptions) null);
        registerAlias(XMPConst.NS_PNG, "Author", XMPConst.NS_DC, "creator", arrayOrdered);
        registerAlias(XMPConst.NS_PNG, "Copyright", XMPConst.NS_DC, "rights", arrayAltText);
        registerAlias(XMPConst.NS_PNG, "CreationTime", XMPConst.NS_XMP, "CreateDate", (AliasOptions) null);
        registerAlias(XMPConst.NS_PNG, "Description", XMPConst.NS_DC, "description", arrayAltText);
        registerAlias(XMPConst.NS_PNG, "ModificationTime", XMPConst.NS_XMP, "ModifyDate", (AliasOptions) null);
        registerAlias(XMPConst.NS_PNG, "Software", XMPConst.NS_XMP, "CreatorTool", (AliasOptions) null);
        registerAlias(XMPConst.NS_PNG, "Title", XMPConst.NS_DC, "title", arrayAltText);
    }

    private void registerStandardNamespaces() {
        registerNamespace(XMPConst.NS_XML, "xml");
        registerNamespace(XMPConst.NS_RDF, "rdf");
        registerNamespace(XMPConst.NS_DC, "dc");
        registerNamespace(XMPConst.NS_IPTCCORE, "Iptc4xmpCore");
        registerNamespace(XMPConst.NS_IPTCEXT, "Iptc4xmpExt");
        registerNamespace(XMPConst.NS_DICOM, "DICOM");
        registerNamespace(XMPConst.NS_PLUS, "plus");
        registerNamespace(XMPConst.NS_X, "x");
        registerNamespace(XMPConst.NS_IX, "iX");
        registerNamespace(XMPConst.NS_XMP, "xmp");
        registerNamespace(XMPConst.NS_XMP_RIGHTS, "xmpRights");
        registerNamespace(XMPConst.NS_XMP_MM, "xmpMM");
        registerNamespace(XMPConst.NS_XMP_BJ, "xmpBJ");
        registerNamespace(XMPConst.NS_XMP_NOTE, "xmpNote");
        registerNamespace(XMPConst.NS_PDF, "pdf");
        registerNamespace(XMPConst.NS_PDFX, "pdfx");
        registerNamespace(XMPConst.NS_PDFX_ID, "pdfxid");
        registerNamespace(XMPConst.NS_PDFA_SCHEMA, "pdfaSchema");
        registerNamespace(XMPConst.NS_PDFA_PROPERTY, "pdfaProperty");
        registerNamespace(XMPConst.NS_PDFA_TYPE, "pdfaType");
        registerNamespace(XMPConst.NS_PDFA_FIELD, "pdfaField");
        registerNamespace(XMPConst.NS_PDFA_ID, "pdfaid");
        registerNamespace(XMPConst.NS_PDFA_EXTENSION, "pdfaExtension");
        registerNamespace(XMPConst.NS_PHOTOSHOP, "photoshop");
        registerNamespace(XMPConst.NS_PSALBUM, "album");
        registerNamespace(XMPConst.NS_EXIF, "exif");
        registerNamespace(XMPConst.NS_EXIFX, "exifEX");
        registerNamespace(XMPConst.NS_EXIF_AUX, "aux");
        registerNamespace(XMPConst.NS_TIFF, "tiff");
        registerNamespace(XMPConst.NS_PNG, "png");
        registerNamespace(XMPConst.NS_JPEG, IFormat.FORMAT_JPEG);
        registerNamespace(XMPConst.NS_JP2K, "jp2k");
        registerNamespace(XMPConst.NS_CAMERARAW, "crs");
        registerNamespace(XMPConst.NS_ADOBESTOCKPHOTO, "bmsp");
        registerNamespace(XMPConst.NS_CREATOR_ATOM, "creatorAtom");
        registerNamespace(XMPConst.NS_ASF, "asf");
        registerNamespace(XMPConst.NS_WAV, "wav");
        registerNamespace(XMPConst.NS_BWF, "bext");
        registerNamespace(XMPConst.NS_RIFFINFO, "riffinfo");
        registerNamespace(XMPConst.NS_SCRIPT, "xmpScript");
        registerNamespace(XMPConst.NS_TXMP, "txmp");
        registerNamespace(XMPConst.NS_SWF, "swf");
        registerNamespace(XMPConst.NS_CCV, "ccv");
        registerNamespace(XMPConst.NS_DM, "xmpDM");
        registerNamespace(XMPConst.NS_TRANSIENT, "xmpx");
        registerNamespace(XMPConst.TYPE_TEXT, "xmpT");
        registerNamespace(XMPConst.TYPE_PAGEDFILE, "xmpTPg");
        registerNamespace(XMPConst.TYPE_GRAPHICS, "xmpG");
        registerNamespace(XMPConst.TYPE_IMAGE, "xmpGImg");
        registerNamespace(XMPConst.TYPE_FONT, "stFnt");
        registerNamespace(XMPConst.TYPE_DIMENSIONS, "stDim");
        registerNamespace(XMPConst.TYPE_RESOURCEEVENT, "stEvt");
        registerNamespace(XMPConst.TYPE_RESOURCEREF, "stRef");
        registerNamespace(XMPConst.TYPE_ST_VERSION, "stVer");
        registerNamespace(XMPConst.TYPE_ST_JOB, "stJob");
        registerNamespace(XMPConst.TYPE_MANIFESTITEM, "stMfs");
        registerNamespace(XMPConst.TYPE_IDENTIFIERQUAL, "xmpidq");
    }

    public synchronized void deleteNamespace(String str) {
        String namespacePrefix = getNamespacePrefix(str);
        if (namespacePrefix != null) {
            this.namespaceToPrefixMap.remove(str);
            this.prefixToNamespaceMap.remove(namespacePrefix);
        }
    }

    public synchronized XMPAliasInfo findAlias(String str) {
        return (XMPAliasInfo) this.aliasMap.get(str);
    }

    public synchronized XMPAliasInfo[] findAliases(String str) {
        ArrayList arrayList;
        try {
            String namespacePrefix = getNamespacePrefix(str);
            arrayList = new ArrayList();
            if (namespacePrefix != null) {
                for (String str2 : this.aliasMap.keySet()) {
                    if (str2.startsWith(namespacePrefix)) {
                        arrayList.add(findAlias(str2));
                    }
                }
            }
        } finally {
            while (true) {
            }
        }
        return (XMPAliasInfo[]) arrayList.toArray(new XMPAliasInfo[arrayList.size()]);
    }

    public synchronized Map getAliases() {
        return Collections.unmodifiableMap(new TreeMap(this.aliasMap));
    }

    public synchronized String getNamespacePrefix(String str) {
        return (String) this.namespaceToPrefixMap.get(str);
    }

    public synchronized String getNamespaceURI(String str) {
        if (str != null) {
            try {
                if (!str.endsWith(NumericEnum.SEP)) {
                    str = str.concat(NumericEnum.SEP);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return (String) this.prefixToNamespaceMap.get(str);
    }

    public synchronized Map getNamespaces() {
        return Collections.unmodifiableMap(new TreeMap(this.namespaceToPrefixMap));
    }

    public synchronized Map getPrefixes() {
        return Collections.unmodifiableMap(new TreeMap(this.prefixToNamespaceMap));
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void registerAlias(java.lang.String r5, java.lang.String r6, java.lang.String r7, final java.lang.String r8, com.adobe.internal.xmp.options.AliasOptions r9) {
        /*
            r4 = this;
            monitor-enter(r4)
            com.adobe.internal.xmp.impl.ParameterAsserts.assertSchemaNS(r5)     // Catch:{ all -> 0x00a0 }
            com.adobe.internal.xmp.impl.ParameterAsserts.assertPropName(r6)     // Catch:{ all -> 0x00a0 }
            com.adobe.internal.xmp.impl.ParameterAsserts.assertSchemaNS(r7)     // Catch:{ all -> 0x00a0 }
            com.adobe.internal.xmp.impl.ParameterAsserts.assertPropName(r8)     // Catch:{ all -> 0x00a0 }
            if (r9 == 0) goto L_0x002a
            com.adobe.internal.xmp.options.AliasOptions r0 = new com.adobe.internal.xmp.options.AliasOptions     // Catch:{ all -> 0x0023 }
            com.adobe.internal.xmp.options.PropertyOptions r9 = r9.toPropertyOptions()     // Catch:{ all -> 0x0023 }
            r1 = 0
            com.adobe.internal.xmp.options.PropertyOptions r9 = com.adobe.internal.xmp.impl.XMPNodeUtils.verifySetOptions(r9, r1)     // Catch:{ all -> 0x0023 }
            int r9 = r9.getOptions()     // Catch:{ all -> 0x0023 }
            r0.<init>(r9)     // Catch:{ all -> 0x0023 }
        L_0x0021:
            r9 = r0
            goto L_0x0030
        L_0x0023:
            r0 = move-exception
            r5 = r0
            r3 = r5
            r5 = r4
            r4 = r3
            goto L_0x00c9
        L_0x002a:
            com.adobe.internal.xmp.options.AliasOptions r0 = new com.adobe.internal.xmp.options.AliasOptions     // Catch:{ all -> 0x00a0 }
            r0.<init>()     // Catch:{ all -> 0x00a0 }
            goto L_0x0021
        L_0x0030:
            java.util.regex.Pattern r0 = r4.f1098p     // Catch:{ all -> 0x00a0 }
            java.util.regex.Matcher r0 = r0.matcher(r6)     // Catch:{ all -> 0x00a0 }
            boolean r0 = r0.find()     // Catch:{ all -> 0x00a0 }
            if (r0 != 0) goto L_0x00be
            java.util.regex.Pattern r0 = r4.f1098p     // Catch:{ all -> 0x00a0 }
            java.util.regex.Matcher r0 = r0.matcher(r8)     // Catch:{ all -> 0x00a0 }
            boolean r0 = r0.find()     // Catch:{ all -> 0x00a0 }
            if (r0 != 0) goto L_0x00be
            java.lang.String r5 = r4.getNamespacePrefix(r5)     // Catch:{ all -> 0x00a0 }
            r0 = r6
            r6 = r7
            java.lang.String r7 = r4.getNamespacePrefix(r6)     // Catch:{ all -> 0x00a0 }
            r1 = 101(0x65, float:1.42E-43)
            if (r5 == 0) goto L_0x00b5
            if (r7 == 0) goto L_0x00ac
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a0 }
            r1.<init>()     // Catch:{ all -> 0x00a0 }
            r1.append(r5)     // Catch:{ all -> 0x00a0 }
            r1.append(r0)     // Catch:{ all -> 0x00a0 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x00a0 }
            java.util.Map r5 = r4.aliasMap     // Catch:{ all -> 0x00a0 }
            boolean r5 = r5.containsKey(r0)     // Catch:{ all -> 0x00a0 }
            r1 = 4
            if (r5 != 0) goto L_0x00a3
            java.util.Map r5 = r4.aliasMap     // Catch:{ all -> 0x00a0 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a0 }
            r2.<init>()     // Catch:{ all -> 0x00a0 }
            r2.append(r7)     // Catch:{ all -> 0x00a0 }
            r2.append(r8)     // Catch:{ all -> 0x00a0 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00a0 }
            boolean r5 = r5.containsKey(r2)     // Catch:{ all -> 0x00a0 }
            if (r5 != 0) goto L_0x0097
            r5 = r4
            com.adobe.internal.xmp.impl.XMPSchemaRegistryImpl$1 r4 = new com.adobe.internal.xmp.impl.XMPSchemaRegistryImpl$1     // Catch:{ all -> 0x0094 }
            r4.<init>(r6, r7, r8, r9)     // Catch:{ all -> 0x0094 }
            java.util.Map r6 = r5.aliasMap     // Catch:{ all -> 0x0094 }
            r6.put(r0, r4)     // Catch:{ all -> 0x0094 }
            monitor-exit(r5)
            return
        L_0x0094:
            r0 = move-exception
        L_0x0095:
            r4 = r0
            goto L_0x00c9
        L_0x0097:
            r5 = r4
            com.adobe.internal.xmp.XMPException r4 = new com.adobe.internal.xmp.XMPException     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = "Actual property is already an alias, use the base property"
            r4.<init>(r6, r1)     // Catch:{ all -> 0x0094 }
            throw r4     // Catch:{ all -> 0x0094 }
        L_0x00a0:
            r0 = move-exception
            r5 = r4
            goto L_0x0095
        L_0x00a3:
            r5 = r4
            com.adobe.internal.xmp.XMPException r4 = new com.adobe.internal.xmp.XMPException     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = "Alias is already existing"
            r4.<init>(r6, r1)     // Catch:{ all -> 0x0094 }
            throw r4     // Catch:{ all -> 0x0094 }
        L_0x00ac:
            r5 = r4
            com.adobe.internal.xmp.XMPException r4 = new com.adobe.internal.xmp.XMPException     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = "Actual namespace is not registered"
            r4.<init>(r6, r1)     // Catch:{ all -> 0x0094 }
            throw r4     // Catch:{ all -> 0x0094 }
        L_0x00b5:
            r5 = r4
            com.adobe.internal.xmp.XMPException r4 = new com.adobe.internal.xmp.XMPException     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = "Alias namespace is not registered"
            r4.<init>(r6, r1)     // Catch:{ all -> 0x0094 }
            throw r4     // Catch:{ all -> 0x0094 }
        L_0x00be:
            r5 = r4
            com.adobe.internal.xmp.XMPException r4 = new com.adobe.internal.xmp.XMPException     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = "Alias and actual property names must be simple"
            r7 = 102(0x66, float:1.43E-43)
            r4.<init>(r6, r7)     // Catch:{ all -> 0x0094 }
            throw r4     // Catch:{ all -> 0x0094 }
        L_0x00c9:
            monitor-exit(r5)     // Catch:{ all -> 0x0094 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.XMPSchemaRegistryImpl.registerAlias(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.adobe.internal.xmp.options.AliasOptions):void");
    }

    public synchronized String registerNamespace(String str, String str2) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPrefix(str2);
            if (str2.charAt(str2.length() - 1) != ':') {
                str2 = str2.concat(NumericEnum.SEP);
            }
            if (Utils.isXMLNameNS(str2.substring(0, str2.length() - 1))) {
                String str3 = (String) this.namespaceToPrefixMap.get(str);
                String str4 = (String) this.prefixToNamespaceMap.get(str2);
                if (str3 != null) {
                    return str3;
                }
                if (str4 != null) {
                    String str5 = str2;
                    int i2 = 1;
                    while (this.prefixToNamespaceMap.containsKey(str5)) {
                        str5 = str2.substring(0, str2.length() - 1) + "_" + i2 + "_:";
                        i2++;
                    }
                    str2 = str5;
                }
                this.prefixToNamespaceMap.put(str2, str);
                this.namespaceToPrefixMap.put(str, str2);
                return str2;
            }
            throw new XMPException("The prefix is a bad XML name", 201);
        } finally {
        }
    }

    public synchronized XMPAliasInfo resolveAlias(String str, String str2) {
        String namespacePrefix = getNamespacePrefix(str);
        if (namespacePrefix == null) {
            return null;
        }
        Map map = this.aliasMap;
        return (XMPAliasInfo) map.get(namespacePrefix + str2);
    }
}
