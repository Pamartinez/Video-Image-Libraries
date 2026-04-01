package com.samsung.android.motionphoto.utils.v2;

import Ae.b;
import Tf.v;
import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPIterator;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.options.IteratorOptions;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.adobe.internal.xmp.properties.XMPProperty;
import com.adobe.internal.xmp.properties.XMPPropertyInfo;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sum.core.SLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import me.i;
import ne.C1194l;
import ne.C1196n;
import ne.z;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 H2\u00020\u0001:\u0001HB\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\f\u0010\rJ)\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u00110\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0017\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J!\u0010\u001b\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u001b\u0010\u0018J\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0011\u0010 \u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\"H\u0016¢\u0006\u0004\b%\u0010$J\u0017\u0010'\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0012H\u0016¢\u0006\u0004\b'\u0010(J\u0017\u0010)\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0012H\u0016¢\u0006\u0004\b)\u0010(J\u001f\u0010,\u001a\u00020\"2\u0006\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u0012H\u0016¢\u0006\u0004\b,\u0010-J\u0015\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00120\u0010H\u0016¢\u0006\u0004\b.\u0010/J!\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u00110\u0010H\u0000¢\u0006\u0004\b0\u0010/J\u0015\u00101\u001a\b\u0012\u0004\u0012\u00020\u00120\u0010H\u0016¢\u0006\u0004\b1\u0010/J\u0017\u00105\u001a\u00020\t2\u0006\u00102\u001a\u00020\u0001H\u0000¢\u0006\u0004\b3\u00104R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0004¢\u0006\f\n\u0004\b\u0003\u00106\u001a\u0004\b7\u00108R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0005\u00109R\u001b\u0010\u000f\u001a\u00020\u000e8FX\u0002¢\u0006\f\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=R\"\u0010>\u001a\u00020\"8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b>\u0010$\"\u0004\b@\u0010AR&\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020C0B8\u0000X\u0004¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G¨\u0006I"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/XMPInfoImpl;", "Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "mediaFile", "", "xmpArray", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;[B)V", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "Lme/x;", "updateXMPItems", "()V", "createEmptyXMPBuffer", "()[B", "Lcom/adobe/internal/xmp/XMPMeta;", "xmp", "", "Lme/i;", "", "getProperties", "(Lcom/adobe/internal/xmp/XMPMeta;)Ljava/util/List;", "schemaNS", "propertyName", "getProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "itemName", "fieldName", "getItemField", "", "index", "getItemNameAt", "(I)Ljava/lang/String;", "getLastItem", "()Ljava/lang/String;", "", "isEmpty", "()Z", "isNotEmpty", "name", "containsProperty", "(Ljava/lang/String;)Z", "containsItem", "item", "field", "containsItemField", "(Ljava/lang/String;Ljava/lang/String;)Z", "getPropertyNames", "()Ljava/util/List;", "getProperties$motionphoto_utils_v2_0_17_release", "getItemNames", "other", "copyTo$motionphoto_utils_v2_0_17_release", "(Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;)V", "copyTo", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "getMediaFile$motionphoto_utils_v2_0_17_release", "()Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "[B", "xmp$delegate", "Lme/f;", "getXmp", "()Lcom/adobe/internal/xmp/XMPMeta;", "isDirty", "Z", "setDirty", "(Z)V", "", "Lcom/samsung/android/motionphoto/utils/v2/XMPItemInfo;", "items", "Ljava/util/Map;", "getItems$motionphoto_utils_v2_0_17_release", "()Ljava/util/Map;", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class XMPInfoImpl implements XMPInfo {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    /* access modifiers changed from: private */
    public static final Map<String, String> namespaceMap = z.b0(new i("GCamera", MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS), new i("Container", MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS), new i("Item", MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS), new i("hdrgm", MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS));
    private boolean isDirty;
    private final Map<String, XMPItemInfo> items;
    private final MediaFile mediaFile;
    private final f xmp$delegate;
    private byte[] xmpArray;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/XMPInfoImpl$Companion;", "", "<init>", "()V", "TAG", "", "namespaceMap", "", "getNamespaceMap$motionphoto_utils_v2_0_17_release", "()Ljava/util/Map;", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final Map<String, String> getNamespaceMap$motionphoto_utils_v2_0_17_release() {
            return XMPInfoImpl.namespaceMap;
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(XMPInfo.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0046, code lost:
        r4 = r4.getData();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XMPInfoImpl(com.samsung.android.motionphoto.utils.v2.MediaFile r4, byte[] r5) {
        /*
            r3 = this;
            java.lang.String r0 = "mediaFile"
            kotlin.jvm.internal.j.e(r4, r0)
            r3.<init>()
            r3.mediaFile = r4
            r3.xmpArray = r5
            Sf.q r5 = new Sf.q
            r0 = 7
            r5.<init>(r0, r3)
            me.m r5 = L1.d.q(r5)
            r3.xmp$delegate = r5
            java.util.LinkedHashMap r5 = new java.util.LinkedHashMap
            r5.<init>()
            r3.items = r5
            byte[] r5 = r3.xmpArray
            if (r5 != 0) goto L_0x0052
            java.lang.String r5 = TAG
            java.lang.String r0 = r4.path()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "no xmp given, parse by meta-reader: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.samsung.android.sum.core.SLog.i((java.lang.String) r5, (java.lang.String) r0)
            com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader$Companion r5 = com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader.Companion
            com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader r4 = r5.of(r4)
            com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader$Box r4 = r4.getXMP()
            if (r4 == 0) goto L_0x004c
            byte[] r4 = r4.getData()
            if (r4 != 0) goto L_0x0050
        L_0x004c:
            byte[] r4 = r3.createEmptyXMPBuffer()
        L_0x0050:
            r3.xmpArray = r4
        L_0x0052:
            r3.updateXMPItems()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.XMPInfoImpl.<init>(com.samsung.android.motionphoto.utils.v2.MediaFile, byte[]):void");
    }

    private final byte[] createEmptyXMPBuffer() {
        XMPMeta create = XMPMetaFactory.create();
        SerializeOptions serializeOptions = new SerializeOptions();
        serializeOptions.setOmitPacketWrapper(true);
        serializeOptions.setUseCompactFormat(true);
        byte[] serializeToBuffer = XMPMetaFactory.serializeToBuffer(create, serializeOptions);
        j.d(serializeToBuffer, "let(...)");
        return serializeToBuffer;
    }

    private final List<i> getProperties(XMPMeta xMPMeta) {
        ArrayList arrayList = new ArrayList();
        try {
            IteratorOptions iteratorOptions = new IteratorOptions();
            iteratorOptions.setJustLeafnodes(true);
            iteratorOptions.setOmitQualifiers(true);
            XMPIterator it = xMPMeta.iterator(iteratorOptions);
            while (it.hasNext()) {
                Object next = it.next();
                j.c(next, "null cannot be cast to non-null type com.adobe.internal.xmp.properties.XMPPropertyInfo");
                XMPPropertyInfo xMPPropertyInfo = (XMPPropertyInfo) next;
                String path = xMPPropertyInfo.getPath();
                j.d(path, "getPath(...)");
                if (!v.t0(path, "Container")) {
                    arrayList.add(new i(xMPPropertyInfo.getNamespace(), xMPPropertyInfo.getPath()));
                }
            }
        } catch (XMPException e) {
            String str = TAG;
            SLog.w(str, "fail to get properties: " + e);
        }
        return C1194l.k1(arrayList);
    }

    private final void updateXMPItems() {
        String str;
        try {
            int countArrayItems = getXmp().countArrayItems(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory");
            int i2 = 0;
            while (i2 < countArrayItems) {
                int i7 = i2 + 1;
                XMPProperty structField = getXmp().getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory[" + i7 + "]/Container:Item", MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:Semantic");
                if (structField != null) {
                    str = structField.getValue();
                } else {
                    str = null;
                }
                if (str != null) {
                    this.items.put(str, new XMPItemInfo(getXmp(), i7));
                    XMPItemInfo xMPItemInfo = this.items.get(str);
                    j.b(xMPItemInfo);
                    if (xMPItemInfo.contains("Mime")) {
                        i2 = i7;
                    } else {
                        throw new IllegalArgumentException(("Mime should be given for " + i2 + "-item").toString());
                    }
                } else {
                    throw new IllegalArgumentException(("Semantic should be given for " + i2 + "-item").toString());
                }
            }
            String str2 = TAG;
            String R02 = C1194l.R0(getItemNames(), (String) null, (String) null, (String) null, (b) null, 63);
            SLog.d(str2, "found xmp items: " + R02);
        } catch (XMPException e) {
            XMPException xMPException = e;
            String str3 = TAG;
            SLog.w(str3, "fail to parse xmp: " + xMPException);
        }
    }

    /* access modifiers changed from: private */
    public static final XMPMeta xmp_delegate$lambda$0(XMPInfoImpl xMPInfoImpl) {
        return XMPMetaFactory.parseFromBuffer(xMPInfoImpl.xmpArray);
    }

    public boolean containsItem(String str) {
        j.e(str, "name");
        return getItemNames().contains(str);
    }

    public boolean containsItemField(String str, String str2) {
        j.e(str, "item");
        j.e(str2, "field");
        if (getItemField(str, str2) != null) {
            return true;
        }
        return false;
    }

    public boolean containsProperty(String str) {
        j.e(str, "name");
        return getPropertyNames().contains(str);
    }

    public final void copyTo$motionphoto_utils_v2_0_17_release(XMPInfo xMPInfo) {
        j.e(xMPInfo, "other");
        XMPEdit edit = xMPInfo.edit();
        for (i iVar : getProperties$motionphoto_utils_v2_0_17_release()) {
            String str = (String) iVar.d;
            String str2 = (String) iVar.e;
            String property = getProperty(str, str2);
            j.b(property);
            edit.setProperty(str, str2, property);
        }
        for (XMPItemInfo xMPItemInfo : this.items.values()) {
            edit.setItem(xMPItemInfo.getName(), MimeType.Companion.of(xMPItemInfo.getMime()));
            for (String str3 : xMPItemInfo.getFieldsWith(MediaDefs.Meta.XMP.XMP_KEY_LENGTH, MediaDefs.Meta.XMP.XMP_KEY_PADDING)) {
                String name = xMPItemInfo.getName();
                String str4 = xMPItemInfo.get(str3);
                j.b(str4);
                edit.setItemField(name, str3, str4);
            }
        }
        XMPInfoImpl xMPInfoImpl = (XMPInfoImpl) xMPInfo;
        xMPInfoImpl.updateXMPItems();
        xMPInfoImpl.isDirty = true;
    }

    public String getItemField(String str, String str2) {
        j.e(str, "itemName");
        j.e(str2, "fieldName");
        XMPItemInfo xMPItemInfo = this.items.get(str);
        if (xMPItemInfo != null) {
            return xMPItemInfo.get(str2);
        }
        return null;
    }

    public String getItemNameAt(int i2) {
        Object obj;
        Iterator it = this.items.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((XMPItemInfo) obj).getIndex() == i2) {
                break;
            }
        }
        XMPItemInfo xMPItemInfo = (XMPItemInfo) obj;
        if (xMPItemInfo != null) {
            return xMPItemInfo.getName();
        }
        return null;
    }

    public List<String> getItemNames() {
        return C1194l.k1(this.items.keySet());
    }

    public final Map<String, XMPItemInfo> getItems$motionphoto_utils_v2_0_17_release() {
        return this.items;
    }

    public String getLastItem() {
        return getItemNameAt(getXmp().countArrayItems(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory"));
    }

    public final MediaFile getMediaFile$motionphoto_utils_v2_0_17_release() {
        return this.mediaFile;
    }

    public final List<i> getProperties$motionphoto_utils_v2_0_17_release() {
        return getProperties(getXmp());
    }

    public String getProperty(String str, String str2) {
        j.e(str, "schemaNS");
        j.e(str2, "propertyName");
        try {
            XMPProperty property = getXmp().getProperty(str, str2);
            if (property != null) {
                return property.getValue();
            }
            return null;
        } catch (XMPException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getPropertyNames() {
        Iterable<i> properties = getProperties(getXmp());
        ArrayList arrayList = new ArrayList(C1196n.w0(properties, 10));
        for (i iVar : properties) {
            arrayList.add((String) iVar.e);
        }
        return C1194l.k1(arrayList);
    }

    public final XMPMeta getXmp() {
        Object value = this.xmp$delegate.getValue();
        j.d(value, "getValue(...)");
        return (XMPMeta) value;
    }

    public final boolean isDirty() {
        return this.isDirty;
    }

    public boolean isEmpty() {
        if (!getPropertyNames().isEmpty() || !getItemNames().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public final void setDirty(boolean z) {
        this.isDirty = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public XMPInfoImpl(MediaFile mediaFile2) {
        this(mediaFile2, (byte[]) null);
        j.e(mediaFile2, "mediaFile");
    }
}
