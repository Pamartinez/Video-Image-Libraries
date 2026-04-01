package com.samsung.o3dp.jpeg3dcontainer.model;

import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.adobe.internal.xmp.properties.XMPProperty;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sum.core.filter.n;
import com.samsung.o3dp.jpeg3dcontainer.util.LogUtil;
import com.samsung.o3dp.jpeg3dcontainer.util.XmpUtil;
import i.C0212a;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GContainer {
    public static final String MIME_APPLICATION_ANIMATION_JSON = "application/animation+json";
    public static final String MIME_MODEL_GLB_BINARY = "model/glb+binary";
    private static final String MODEL_3D_VERSION = "1.0";
    private static final String NAMESPACE_URL_CONTAINER = "http://ns.google.com/photos/1.0/container/";
    private static final String NAMESPACE_URL_ITEM = "http://ns.google.com/photos/1.0/container/item/";
    private static final String NAMESPACE_URL_MODEL3D = "http://ns.samsung.com/model3dimage";
    public static final String SEMANTIC_CAMERA_ANIMATION = "CameraAnimation";
    public static final String SEMANTIC_GAIN_MAP = "GainMap";
    public static final String SEMANTIC_MODEL_3D = "Model3D";
    private static final String TAG = "GContainer";
    private static final String XMP_NAMESPACE = "Container";
    private static final String XMP_TAG = "Directory";
    private final List<Item> items;
    private final XMPMeta xmpMeta;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Item {
        private final String label;
        private final String length;
        private final String mime;
        private final String padding;
        private final String semantic;
        private final String uri;

        public Item(String str, String str2, String str3, String str4, String str5, String str6) {
            this.mime = str2;
            this.semantic = str;
            this.length = str3;
            this.label = str4;
            this.padding = str5;
            this.uri = str6;
        }

        public String getLabel() {
            return this.label;
        }

        public String getLengthStr() {
            return this.length;
        }

        public String getMime() {
            return this.mime;
        }

        public String getPadding() {
            return this.padding;
        }

        public String getSemantic() {
            return this.semantic;
        }

        public String getUri() {
            return this.uri;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ItemProperty {
        SEMANTIC("Semantic"),
        MIME("Mime"),
        LENGTH(MediaDefs.Meta.XMP.XMP_KEY_LENGTH),
        LABEL("Label"),
        PADDING(MediaDefs.Meta.XMP.XMP_KEY_PADDING),
        URI("URI");
        
        private final String property;

        private ItemProperty(String str) {
            this.property = str;
        }

        public String getProperty() {
            return this.property;
        }
    }

    private GContainer(XMPMeta xMPMeta, List<Item> list) {
        this.xmpMeta = xMPMeta;
        this.items = list;
        registerNamespace();
    }

    private void addItemToXmp(Item item, int i2) {
        XMPMeta xMPMeta = this.xmpMeta;
        String j2 = C0212a.j(i2, "Directory[", "]");
        Locale locale = Locale.US;
        String A10 = C0212a.A(j2, "/Container:Item");
        appendNewItemField(xMPMeta, new PropertyOptions(1024), new PropertyOptions(256), j2);
        setItemField(item, xMPMeta, A10);
    }

    private static void appendNewItemField(XMPMeta xMPMeta, PropertyOptions propertyOptions, PropertyOptions propertyOptions2, String str) {
        XMPMeta xMPMeta2 = xMPMeta;
        PropertyOptions propertyOptions3 = propertyOptions2;
        xMPMeta2.appendArrayItem("http://ns.google.com/photos/1.0/container/", XMP_TAG, propertyOptions, (String) null, propertyOptions3);
        PropertyOptions propertyOptions4 = propertyOptions3;
        xMPMeta2.setStructField("http://ns.google.com/photos/1.0/container/", str, "http://ns.google.com/photos/1.0/container/", "Item", (String) null, propertyOptions4);
    }

    public static GContainer create() {
        LogUtil.i(TAG, "Create a new GContainer");
        try {
            return createFromXmp(XmpUtil.parse(String.format(Locale.US, "  <x:xmpmeta xmlns:x=\"adobe:ns:meta/\" x:xmptk=\"Adobe XMP Core 6.1.11\">%n\n  <rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">%n\n    <rdf:Description %n\n      xmlns:Container=\"http://ns.google.com/photos/1.0/container/\" %n\n      xmlns:Item=\"http://ns.google.com/photos/1.0/container/item/\" %n\n      xmlns:hdrgm=\"http://ns.adobe.com/hdr-gain-map/1.0/\" %n\n      xmlns:model3dimage=\"http://ns.samsung.com/model3dimage\" %n\n      hdrgm:Version=\"1.0\" %n\n      model3dimage:version=\"%s\" %n\n      > %n\n      <Container:Directory> %n\n        <rdf:Seq> %n\n          <rdf:li rdf:parseType=\"Resource\"> %n\n            <Container:Item %n\n              Item:Semantic=\"Primary\" %n\n              Item:Mime=\"image/jpeg\"/> %n\n          </rdf:li> %n\n        </rdf:Seq> %n\n      </Container:Directory> %n\n    </rdf:Description> %n\n  </rdf:RDF> %n\n</x:xmpmeta> %n\n", new Object[]{MODEL_3D_VERSION}).getBytes(StandardCharsets.UTF_8)));
        } catch (XMPException e) {
            throw new RuntimeException(e);
        }
    }

    public static GContainer createFromXmp(XMPMeta xMPMeta) {
        ArrayList arrayList = new ArrayList();
        if (xMPMeta.getProperty("http://ns.google.com/photos/1.0/container/", XMP_TAG) != null) {
            int countArrayItems = xMPMeta.countArrayItems("http://ns.google.com/photos/1.0/container/", XMP_TAG);
            for (int i2 = 1; i2 <= countArrayItems; i2++) {
                arrayList.add(getItem(xMPMeta, i2));
            }
            GContainer gContainer = new GContainer(xMPMeta, arrayList);
            gContainer.setModel3DVersion(MODEL_3D_VERSION);
            return gContainer;
        }
        throw new XMPException("No container found in xmp", 101);
    }

    private void deleteItemFromXmp(int i2) {
        this.xmpMeta.deleteArrayItem("http://ns.google.com/photos/1.0/container/", "Container:Directory", i2);
    }

    private static String getContainerProperty(XMPMeta xMPMeta, int i2, ItemProperty itemProperty) {
        XMPProperty property = xMPMeta.getProperty("http://ns.google.com/photos/1.0/container/", getItemPath(i2, itemProperty));
        if (property != null) {
            return property.getValue();
        }
        return null;
    }

    private static Item getItem(XMPMeta xMPMeta, int i2) {
        return new Item(getContainerProperty(xMPMeta, i2, ItemProperty.SEMANTIC), getContainerProperty(xMPMeta, i2, ItemProperty.MIME), getContainerProperty(xMPMeta, i2, ItemProperty.LENGTH), getContainerProperty(xMPMeta, i2, ItemProperty.LABEL), getContainerProperty(xMPMeta, i2, ItemProperty.PADDING), getContainerProperty(xMPMeta, i2, ItemProperty.URI));
    }

    private static String getItemPath(int i2, ItemProperty itemProperty) {
        Locale locale = Locale.US;
        return C0212a.k(i2, "Container:Directory[", "]/Container:Item/Item:", itemProperty.getProperty());
    }

    public static boolean isGContainerXmp(XMPMeta xMPMeta) {
        return xMPMeta.doesPropertyExist("http://ns.google.com/photos/1.0/container/", XMP_TAG);
    }

    private static void registerNamespace() {
        try {
            XMPMetaFactory.getSchemaRegistry().registerNamespace(NAMESPACE_URL_MODEL3D, "model3dimage");
        } catch (XMPException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setItemField(Item item, XMPMeta xMPMeta, String str) {
        String str2;
        XMPMeta xMPMeta2;
        for (Map.Entry entry : Map.of(ItemProperty.MIME, new n(13), ItemProperty.SEMANTIC, new n(14), ItemProperty.LENGTH, new n(15), ItemProperty.PADDING, new n(16), ItemProperty.LABEL, new n(17), ItemProperty.URI, new n(18)).entrySet()) {
            String property = ((ItemProperty) entry.getKey()).getProperty();
            String str3 = (String) ((Function) entry.getValue()).apply(item);
            if (str3 != null) {
                xMPMeta2 = xMPMeta;
                str2 = str;
                xMPMeta2.setStructField("http://ns.google.com/photos/1.0/container/", str2, "http://ns.google.com/photos/1.0/container/item/", property, str3);
            } else {
                xMPMeta2 = xMPMeta;
                str2 = str;
            }
            xMPMeta = xMPMeta2;
            str = str2;
        }
    }

    public void addItem(Item item) {
        LogUtil.i(TAG, "Add item to GContainer");
        this.items.add(item);
        addItemToXmp(item, this.items.size());
    }

    public void deleteItem(String str) {
        LogUtil.i(TAG, "Delete item of gContainer, semantic: " + str);
        Iterator<Item> it = this.items.iterator();
        while (it.hasNext()) {
            Item next = it.next();
            if (str.equals(next.getSemantic())) {
                int indexOf = this.items.indexOf(next);
                it.remove();
                deleteItemFromXmp(indexOf + 1);
            }
        }
    }

    public String get3DModelVersion() {
        return this.xmpMeta.getPropertyString(NAMESPACE_URL_MODEL3D, "version");
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setModel3DVersion(String str) {
        this.xmpMeta.setProperty(NAMESPACE_URL_MODEL3D, "version", str);
    }

    public XMPMeta toXmp() {
        return this.xmpMeta;
    }

    public String toXmpStr() {
        return XMPMetaFactory.serializeToString(this.xmpMeta, new SerializeOptions());
    }
}
