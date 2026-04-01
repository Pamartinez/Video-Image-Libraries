package L0;

import N2.j;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends a {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal f386a = new ThreadLocal();

    static {
        HashMap hashMap = new HashMap(64);
        j.l(1, hashMap, "javax.xml.stream.isCoalescing", 2, "javax.xml.stream.isNamespaceAware");
        j.l(3, hashMap, "javax.xml.stream.isReplacingEntityReferences", 4, "javax.xml.stream.isSupportingExternalEntities");
        j.l(5, hashMap, "javax.xml.stream.isValidating", 6, "javax.xml.stream.supportDTD");
        j.l(7, hashMap, "javax.xml.stream.allocator", 8, "javax.xml.stream.reporter");
        j.l(9, hashMap, "javax.xml.stream.resolver", 21, "org.codehaus.stax2.internNames");
        j.l(20, hashMap, "org.codehaus.stax2.internNsUris", 22, "http://java.sun.com/xml/stream/properties/report-cdata-event");
        j.l(23, hashMap, "org.codehaus.stax2.reportPrologWhitespace", 24, "org.codehaus.stax2.preserveLocation");
        j.l(25, hashMap, "org.codehaus.stax2.closeInputSource", 26, "org.codehaus.stax2.supportXmlId");
        j.l(27, hashMap, "org.codehaus.stax2.propDtdOverride", 30, "http://javax.xml.XMLConstants/feature/secure-processing");
        j.l(42, hashMap, "com.ctc.wstx.cacheDTDs", 43, "com.ctc.wstx.cacheDTDsByPublicId");
        j.l(44, hashMap, "com.ctc.wstx.lazyParsing", 46, "com.ctc.wstx.treatCharRefsAsEnts");
        j.l(47, hashMap, "com.ctc.wstx.allowXml11EscapedCharsInXml10", 40, "com.ctc.wstx.normalizeLFs");
        j.l(50, hashMap, "com.ctc.wstx.inputBufferLength", 52, "com.ctc.wstx.minTextSegment");
        j.l(60, hashMap, "com.ctc.wstx.maxAttributesPerElement", 65, "com.ctc.wstx.maxAttributeSize");
        j.l(61, hashMap, "com.ctc.wstx.maxChildrenPerElement", 66, "com.ctc.wstx.maxTextLength");
        j.l(62, hashMap, "com.ctc.wstx.maxElementCount", 63, "com.ctc.wstx.maxElementDepth");
        j.l(68, hashMap, "com.ctc.wstx.maxEntityDepth", 67, "com.ctc.wstx.maxEntityCount");
        j.l(64, hashMap, "com.ctc.wstx.maxCharacters", 53, "com.ctc.wstx.customInternalEntities");
        j.l(54, hashMap, "com.ctc.wstx.dtdResolver", 55, "com.ctc.wstx.entityResolver");
        j.l(56, hashMap, "com.ctc.wstx.undeclaredEntityResolver", 57, "com.ctc.wstx.baseURL");
        hashMap.put("com.ctc.wstx.fragmentMode", 58);
    }

    public static void a() {
        new a();
        SoftReference softReference = (SoftReference) f386a.get();
        if (softReference != null && softReference.get() != null) {
            throw new ClassCastException();
        }
    }
}
