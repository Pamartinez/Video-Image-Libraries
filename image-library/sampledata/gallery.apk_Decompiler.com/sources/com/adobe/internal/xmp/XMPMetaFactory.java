package com.adobe.internal.xmp;

import com.adobe.internal.xmp.impl.XMPMetaImpl;
import com.adobe.internal.xmp.impl.XMPMetaParser;
import com.adobe.internal.xmp.impl.XMPSchemaRegistryImpl;
import com.adobe.internal.xmp.impl.XMPSerializerHelper;
import com.adobe.internal.xmp.options.ParseOptions;
import com.adobe.internal.xmp.options.SerializeOptions;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class XMPMetaFactory {
    private static XMPSchemaRegistry schema = new XMPSchemaRegistryImpl();
    private static XMPVersionInfo versionInfo = null;

    private XMPMetaFactory() {
    }

    private static void assertImplementation(XMPMeta xMPMeta) {
        if (!(xMPMeta instanceof XMPMetaImpl)) {
            throw new UnsupportedOperationException("The serializing service works onlywith the XMPMeta implementation of this library");
        }
    }

    public static XMPMeta create() {
        return new XMPMetaImpl();
    }

    public static XMPSchemaRegistry getSchemaRegistry() {
        return schema;
    }

    public static synchronized XMPVersionInfo getVersionInfo() {
        XMPVersionInfo xMPVersionInfo;
        final int i2;
        final int i7;
        int i8;
        synchronized (XMPMetaFactory.class) {
            if (versionInfo == null) {
                String str = "Test.SNAPSHOT";
                final int i10 = 0;
                int i11 = 5;
                try {
                    Enumeration<URL> resources = XMPMetaFactory.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
                    while (resources.hasMoreElements()) {
                        Attributes mainAttributes = new Manifest(resources.nextElement().openStream()).getMainAttributes();
                        if ("com.adobe.xmp.xmpcore".equals(mainAttributes.getValue("Bundle-SymbolicName")) && mainAttributes.getValue("Bundle-Version") != null) {
                            str = mainAttributes.getValue("Bundle-Version");
                            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+).*").matcher(str);
                            if (matcher.find()) {
                                i11 = Integer.parseInt(matcher.group(1));
                                i8 = Integer.parseInt(matcher.group(2));
                                try {
                                    i10 = Integer.parseInt(matcher.group(3));
                                } catch (IOException unused) {
                                }
                                int i12 = i11;
                                i7 = i10;
                                i10 = i8;
                                i2 = i12;
                                break;
                            }
                        }
                    }
                    i2 = 5;
                    i7 = 0;
                } catch (IOException unused2) {
                    i8 = 0;
                }
                final String str2 = "Adobe XMP Core " + str;
                versionInfo = new XMPVersionInfo() {
                    public int getBuild() {
                        return 0;
                    }

                    public int getMajor() {
                        return i2;
                    }

                    public String getMessage() {
                        return str2;
                    }

                    public int getMicro() {
                        return i7;
                    }

                    public int getMinor() {
                        return i10;
                    }

                    public boolean isDebug() {
                        return true;
                    }

                    public String toString() {
                        return str2;
                    }
                };
            }
            xMPVersionInfo = versionInfo;
        }
        return xMPVersionInfo;
    }

    public static XMPMeta parse(InputStream inputStream) {
        return parse(inputStream, (ParseOptions) null);
    }

    public static XMPMeta parseFromBuffer(byte[] bArr) {
        return parseFromBuffer(bArr, (ParseOptions) null);
    }

    public static XMPMeta parseFromString(String str) {
        return parseFromString(str, (ParseOptions) null);
    }

    public static void reset() {
        schema = new XMPSchemaRegistryImpl();
    }

    public static void serialize(XMPMeta xMPMeta, OutputStream outputStream) {
        serialize(xMPMeta, outputStream, (SerializeOptions) null);
    }

    public static byte[] serializeToBuffer(XMPMeta xMPMeta, SerializeOptions serializeOptions) {
        assertImplementation(xMPMeta);
        return XMPSerializerHelper.serializeToBuffer((XMPMetaImpl) xMPMeta, serializeOptions);
    }

    public static String serializeToString(XMPMeta xMPMeta, SerializeOptions serializeOptions) {
        assertImplementation(xMPMeta);
        return XMPSerializerHelper.serializeToString((XMPMetaImpl) xMPMeta, serializeOptions);
    }

    public static XMPMeta parse(InputStream inputStream, ParseOptions parseOptions) {
        return XMPMetaParser.parse(inputStream, parseOptions);
    }

    public static XMPMeta parseFromBuffer(byte[] bArr, ParseOptions parseOptions) {
        return XMPMetaParser.parse(bArr, parseOptions);
    }

    public static XMPMeta parseFromString(String str, ParseOptions parseOptions) {
        return XMPMetaParser.parse(str, parseOptions);
    }

    public static void serialize(XMPMeta xMPMeta, OutputStream outputStream, SerializeOptions serializeOptions) {
        assertImplementation(xMPMeta);
        XMPSerializerHelper.serialize((XMPMetaImpl) xMPMeta, outputStream, serializeOptions);
    }
}
