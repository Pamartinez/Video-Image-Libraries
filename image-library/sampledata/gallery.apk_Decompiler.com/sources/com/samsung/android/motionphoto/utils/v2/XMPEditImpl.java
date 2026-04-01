package com.samsung.android.motionphoto.utils.v2;

import Ad.f;
import Tf.n;
import Tf.o;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.XMPPathFactory;
import com.adobe.internal.xmp.XMPSchemaRegistry;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.motionphoto.utils.v2.io.ImageMetaWriter;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 Y2\u00020\u00012\u00020\u0002:\u0001YB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\u000e\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0017\u0010\u000fJ\u001f\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001c\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001c\u0010\u0019J\u000f\u0010\u001d\u001a\u00020\u0001H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ'\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001f\u0010\u000fJ\u0015\u0010 \u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\t¢\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010$J\u0017\u0010#\u001a\u00020\"2\u0006\u0010&\u001a\u00020%H\u0016¢\u0006\u0004\b#\u0010'J\u0010\u0010(\u001a\u00020\u0006H\u0001¢\u0006\u0004\b(\u0010\bJ\u0010\u0010)\u001a\u00020\u0006H\u0001¢\u0006\u0004\b)\u0010\bJ\u001f\u0010,\u001a\u00020\u00062\r\b\u0001\u0010+\u001a\u00070\t¢\u0006\u0002\b*H\u0001¢\u0006\u0004\b,\u0010!J\u001f\u0010-\u001a\u00020\u00062\r\b\u0001\u0010+\u001a\u00070\t¢\u0006\u0002\b*H\u0001¢\u0006\u0004\b-\u0010!J.\u00100\u001a\u00020\u00062\r\b\u0001\u0010.\u001a\u00070\t¢\u0006\u0002\b*2\r\b\u0001\u0010/\u001a\u00070\t¢\u0006\u0002\b*H\u0001¢\u0006\u0004\b0\u00101J4\u00105\u001a&\u0012\f\u0012\n 3*\u0004\u0018\u00010\t0\t 3*\u0012\u0012\f\u0012\n 3*\u0004\u0018\u00010\t0\t\u0018\u00010402H\u0001¢\u0006\u0004\b5\u00106J4\u00107\u001a&\u0012\f\u0012\n 3*\u0004\u0018\u00010\t0\t 3*\u0012\u0012\f\u0012\n 3*\u0004\u0018\u00010\t0\t\u0018\u00010402H\u0001¢\u0006\u0004\b7\u00106J6\u00108\u001a\n 3*\u0004\u0018\u00010\t0\t2\r\b\u0001\u0010\n\u001a\u00070\t¢\u0006\u0002\b*2\r\b\u0001\u0010\u000b\u001a\u00070\t¢\u0006\u0002\b*H\u0001¢\u0006\u0004\b8\u00109J6\u0010:\u001a\n 3*\u0004\u0018\u00010\t0\t2\r\b\u0001\u0010\u0010\u001a\u00070\t¢\u0006\u0002\b*2\r\b\u0001\u0010\u0015\u001a\u00070\t¢\u0006\u0002\b*H\u0001¢\u0006\u0004\b:\u00109J \u0010=\u001a\n 3*\u0004\u0018\u00010\t0\t2\u0006\u0010<\u001a\u00020;H\u0001¢\u0006\u0004\b=\u0010>J\u0018\u0010?\u001a\n 3*\u0004\u0018\u00010\t0\tH\u0001¢\u0006\u0004\b?\u0010@J\u000f\u0010A\u001a\u00020\"H\u0002¢\u0006\u0004\bA\u0010$J\u000f\u0010B\u001a\u00020\"H\u0002¢\u0006\u0004\bB\u0010$J\u000f\u0010C\u001a\u00020\"H\u0002¢\u0006\u0004\bC\u0010$J\u000f\u0010D\u001a\u00020\u0006H\u0002¢\u0006\u0004\bD\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0004¢\u0006\f\n\u0004\b\u0003\u0010E\u001a\u0004\bF\u0010GR\u0018\u0010H\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010L\u001a\u00020%8@X\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0014\u0010N\u001a\u00020%8@X\u0004¢\u0006\u0006\u001a\u0004\bM\u0010KR\u0014\u0010\u0007\u001a\u00020\u00068@X\u0004¢\u0006\u0006\u001a\u0004\bO\u0010\bR\u0014\u0010S\u001a\u00020P8BX\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010RR \u0010X\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020U0T8BX\u0004¢\u0006\u0006\u001a\u0004\bV\u0010W¨\u0006Z"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/XMPEditImpl;", "Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "xmpInfo", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;)V", "", "isDirty", "()Z", "", "schemaNS", "propertyName", "", "propertyValue", "setProperty", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "itemName", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "mime", "setItem", "(Ljava/lang/String;Lcom/samsung/android/motionphoto/utils/v2/MimeType;)Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "fieldName", "fieldValue", "setItemField", "removeItemField", "(Ljava/lang/String;Ljava/lang/String;)Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "removeItem", "(Ljava/lang/String;)Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "removeProperty", "removeAll", "()Lcom/samsung/android/motionphoto/utils/v2/XMPEdit;", "replaceItemField", "contains", "(Ljava/lang/String;)Z", "Lme/x;", "commit", "()V", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "file", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "isEmpty", "isNotEmpty", "Lkotlin/jvm/internal/EnhancedNullability;", "name", "containsProperty", "containsItem", "item", "field", "containsItemField", "(Ljava/lang/String;Ljava/lang/String;)Z", "", "kotlin.jvm.PlatformType", "", "getPropertyNames", "()Ljava/util/List;", "getItemNames", "getProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "getItemField", "", "index", "getItemNameAt", "(I)Ljava/lang/String;", "getLastItem", "()Ljava/lang/String;", "makeDirty", "clearDirty", "addArrayItem", "isOneIOFiles", "Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "getXmpInfo$motionphoto_utils_v2_0_17_release", "()Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "_outputFile", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "getInputFile$motionphoto_utils_v2_0_17_release", "()Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "inputFile", "getOutputFile$motionphoto_utils_v2_0_17_release", "outputFile", "isDirty$motionphoto_utils_v2_0_17_release", "Lcom/adobe/internal/xmp/XMPMeta;", "getXmp", "()Lcom/adobe/internal/xmp/XMPMeta;", "xmp", "", "Lcom/samsung/android/motionphoto/utils/v2/XMPItemInfo;", "getItems", "()Ljava/util/Map;", "items", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class XMPEditImpl implements XMPEdit, XMPInfo {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private MediaFile _outputFile;
    private final XMPInfo xmpInfo;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/XMPEditImpl$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(XMPEditImpl.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
        XMPSchemaRegistry schemaRegistry = XMPMetaFactory.getSchemaRegistry();
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_GOOGLE_CAMERA_NS, "GCamera");
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container");
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item");
        schemaRegistry.registerNamespace(MediaDefs.Meta.XMP.XMP_HDR_GAINMAP_NS, "hdrgm");
    }

    public XMPEditImpl(XMPInfo xMPInfo) {
        j.e(xMPInfo, "xmpInfo");
        this.xmpInfo = xMPInfo;
    }

    private final void addArrayItem() {
        PropertyOptions propertyOptions = new PropertyOptions();
        propertyOptions.setArrayOrdered(true);
        PropertyOptions propertyOptions2 = new PropertyOptions();
        propertyOptions2.setStruct(true);
        getXmp().appendArrayItem(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Directory", propertyOptions, (String) null, propertyOptions2);
    }

    private final void clearDirty() {
        XMPInfo xMPInfo = this.xmpInfo;
        j.c(xMPInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.XMPInfoImpl");
        ((XMPInfoImpl) xMPInfo).setDirty(false);
    }

    /* access modifiers changed from: private */
    public static final long commit$lambda$10(XMPEditImpl xMPEditImpl, FileChannel fileChannel) {
        j.e(fileChannel, "ifc");
        return ((Number) xMPEditImpl.getOutputFile$motionphoto_utils_v2_0_17_release().useOutputFileChannel(new e(fileChannel, 3))).longValue();
    }

    /* access modifiers changed from: private */
    public static final long commit$lambda$10$lambda$9(FileChannel fileChannel, FileChannel fileChannel2) {
        j.e(fileChannel2, "ofc");
        return fileChannel.transferTo(0, fileChannel.size(), fileChannel2);
    }

    private final Map<String, XMPItemInfo> getItems() {
        XMPInfo xMPInfo = this.xmpInfo;
        j.c(xMPInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.XMPInfoImpl");
        return ((XMPInfoImpl) xMPInfo).getItems$motionphoto_utils_v2_0_17_release();
    }

    private final XMPMeta getXmp() {
        XMPInfo xMPInfo = this.xmpInfo;
        j.c(xMPInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.XMPInfoImpl");
        return ((XMPInfoImpl) xMPInfo).getXmp();
    }

    private final boolean isOneIOFiles() {
        return j.a(getInputFile$motionphoto_utils_v2_0_17_release(), getOutputFile$motionphoto_utils_v2_0_17_release());
    }

    private final void makeDirty() {
        XMPInfo xMPInfo = this.xmpInfo;
        j.c(xMPInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.XMPInfoImpl");
        ((XMPInfoImpl) xMPInfo).setDirty(true);
    }

    public void commit() {
        SLog.i(TAG, "commit");
        ImageMetaWriter.Companion.of(getOutputFile$motionphoto_utils_v2_0_17_release()).writeXMP(this.xmpInfo);
        clearDirty();
    }

    public final boolean contains(String str) {
        j.e(str, "itemName");
        return getItems().containsKey(str);
    }

    public boolean containsItem(String str) {
        j.e(str, "name");
        return this.xmpInfo.containsItem(str);
    }

    public boolean containsItemField(String str, String str2) {
        j.e(str, "item");
        j.e(str2, "field");
        return this.xmpInfo.containsItemField(str, str2);
    }

    public boolean containsProperty(String str) {
        j.e(str, "name");
        return this.xmpInfo.containsProperty(str);
    }

    public final MediaFile getInputFile$motionphoto_utils_v2_0_17_release() {
        XMPInfo xMPInfo = this.xmpInfo;
        j.c(xMPInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.XMPInfoImpl");
        return ((XMPInfoImpl) xMPInfo).getMediaFile$motionphoto_utils_v2_0_17_release();
    }

    public String getItemField(String str, String str2) {
        j.e(str, "itemName");
        j.e(str2, "fieldName");
        return this.xmpInfo.getItemField(str, str2);
    }

    public String getItemNameAt(int i2) {
        return this.xmpInfo.getItemNameAt(i2);
    }

    public List<String> getItemNames() {
        return this.xmpInfo.getItemNames();
    }

    public String getLastItem() {
        return this.xmpInfo.getLastItem();
    }

    public final MediaFile getOutputFile$motionphoto_utils_v2_0_17_release() {
        MediaFile mediaFile = this._outputFile;
        if (mediaFile == null) {
            return getInputFile$motionphoto_utils_v2_0_17_release();
        }
        return mediaFile;
    }

    public String getProperty(String str, String str2) {
        j.e(str, "schemaNS");
        j.e(str2, "propertyName");
        return this.xmpInfo.getProperty(str, str2);
    }

    public List<String> getPropertyNames() {
        return this.xmpInfo.getPropertyNames();
    }

    public final XMPInfo getXmpInfo$motionphoto_utils_v2_0_17_release() {
        return this.xmpInfo;
    }

    public boolean isDirty() {
        return isDirty$motionphoto_utils_v2_0_17_release();
    }

    public final boolean isDirty$motionphoto_utils_v2_0_17_release() {
        XMPInfo xMPInfo = this.xmpInfo;
        j.c(xMPInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.XMPInfoImpl");
        return ((XMPInfoImpl) xMPInfo).isDirty();
    }

    public boolean isEmpty() {
        return this.xmpInfo.isEmpty();
    }

    public boolean isNotEmpty() {
        return this.xmpInfo.isNotEmpty();
    }

    public XMPEdit removeAll() {
        for (String removeItem : C1194l.Z0(getItems().keySet())) {
            removeItem(removeItem);
        }
        List<String> propertyNames = this.xmpInfo.getPropertyNames();
        j.d(propertyNames, "getPropertyNames(...)");
        for (String str : propertyNames) {
            j.b(str);
            List K02 = n.K0(str, new String[]{NumericEnum.SEP});
            Object L02 = C1194l.L0(K02);
            String str2 = XMPInfoImpl.Companion.getNamespaceMap$motionphoto_utils_v2_0_17_release().get((String) L02);
            j.b(str2);
            removeProperty(str2, (String) C1194l.T0(K02));
        }
        makeDirty();
        return this;
    }

    public XMPEdit removeItem(String str) {
        j.e(str, "itemName");
        XMPItemInfo remove = getItems().remove(str);
        if (remove != null) {
            remove.getXmp().deleteArrayItem(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Container:Directory", remove.getIndex());
            makeDirty();
        }
        return this;
    }

    public XMPEdit removeItemField(String str, String str2) {
        j.e(str, "itemName");
        j.e(str2, "fieldName");
        XMPItemInfo xMPItemInfo = getItems().get(str);
        if (xMPItemInfo != null) {
            xMPItemInfo.remove(str2);
            makeDirty();
        }
        return this;
    }

    public XMPEdit removeProperty(String str, String str2) {
        j.e(str, "schemaNS");
        j.e(str2, "propertyName");
        getXmp().deleteProperty(str, str2);
        makeDirty();
        return this;
    }

    public XMPEdit replaceItemField(String str, String str2, Object obj) {
        j.e(str, "itemName");
        j.e(str2, "fieldName");
        j.e(obj, "fieldValue");
        XMPItemInfo xMPItemInfo = getItems().get(str);
        if (xMPItemInfo != null) {
            xMPItemInfo.set(str2, obj);
            makeDirty();
        }
        return this;
    }

    public XMPEdit setItem(String str, MimeType mimeType) {
        j.e(str, "itemName");
        j.e(mimeType, MediaDefs.Image.HEIF.HEIF_MIME_BOX);
        if (getItems().containsKey(str)) {
            SLog.i(TAG, "already exist item, use exist one");
            return this;
        }
        addArrayItem();
        int countArrayItems = getXmp().countArrayItems(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, "Directory");
        String composeArrayItemPath = XMPPathFactory.composeArrayItemPath("Directory", countArrayItems);
        String l0 = o.l0("\n            " + composeArrayItemPath + "/Container:Item\n        ");
        getXmp().setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, l0, MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Mime", mimeType.getValue());
        String str2 = str;
        getXmp().setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, l0, MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Semantic", str2);
        getItems().put(str2, new XMPItemInfo(getXmp(), countArrayItems));
        makeDirty();
        return this;
    }

    public XMPEdit setItemField(String str, String str2, Object obj) {
        j.e(str, "itemName");
        j.e(str2, "fieldName");
        j.e(obj, "fieldValue");
        if (getItems().containsKey(str)) {
            XMPItemInfo xMPItemInfo = getItems().get(str);
            j.b(xMPItemInfo);
            xMPItemInfo.set(str2, obj);
            makeDirty();
            return this;
        }
        throw new IllegalArgumentException(C0212a.m("no such item(", str, ") exist, add item first w/ addItem").toString());
    }

    public XMPEdit setProperty(String str, String str2, Object obj) {
        j.e(str, "schemaNS");
        j.e(str2, "propertyName");
        j.e(obj, "propertyValue");
        getXmp().setProperty(str, str2, obj);
        makeDirty();
        return this;
    }

    public void commit(MediaFile mediaFile) {
        j.e(mediaFile, "file");
        this._outputFile = mediaFile;
        if (!j.a(getInputFile$motionphoto_utils_v2_0_17_release(), getOutputFile$motionphoto_utils_v2_0_17_release())) {
            getInputFile$motionphoto_utils_v2_0_17_release().useInputFileChannel(new f(10, (Object) this));
        }
        commit();
    }
}
