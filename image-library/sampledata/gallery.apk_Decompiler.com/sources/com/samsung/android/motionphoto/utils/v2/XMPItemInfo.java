package com.samsung.android.motionphoto.utils.v2;

import Tf.o;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPPathFactory;
import com.adobe.internal.xmp.properties.XMPProperty;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1182C;
import ne.C1194l;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010#\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ$\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\tHÖ\u0001¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\"\u0010\u001dJ\u001a\u0010$\u001a\u00020\u000f2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010\u001bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010(\u001a\u0004\b)\u0010\u001dR\u001a\u0010+\u001a\b\u0012\u0004\u0012\u00020\t0*8\u0002X\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020\t8BX\u0004¢\u0006\u0006\u001a\u0004\b-\u0010!R\u0011\u00100\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b/\u0010!R\u0011\u00102\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b1\u0010!¨\u00063"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/XMPItemInfo;", "", "Lcom/adobe/internal/xmp/XMPMeta;", "xmp", "", "index", "<init>", "(Lcom/adobe/internal/xmp/XMPMeta;I)V", "", "", "names", "", "getFieldsWith", "([Ljava/lang/String;)Ljava/util/List;", "fieldName", "", "contains", "(Ljava/lang/String;)Z", "get", "(Ljava/lang/String;)Ljava/lang/String;", "fieldValue", "Lme/x;", "set", "(Ljava/lang/String;Ljava/lang/Object;)V", "remove", "(Ljava/lang/String;)V", "component1", "()Lcom/adobe/internal/xmp/XMPMeta;", "component2", "()I", "copy", "(Lcom/adobe/internal/xmp/XMPMeta;I)Lcom/samsung/android/motionphoto/utils/v2/XMPItemInfo;", "toString", "()Ljava/lang/String;", "hashCode", "other", "equals", "(Ljava/lang/Object;)Z", "Lcom/adobe/internal/xmp/XMPMeta;", "getXmp", "I", "getIndex", "", "fields", "Ljava/util/Set;", "getStruct", "struct", "getName", "name", "getMime", "mime", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class XMPItemInfo {
    private final Set<String> fields = new LinkedHashSet();
    private final int index;
    private final XMPMeta xmp;

    public XMPItemInfo(XMPMeta xMPMeta, int i2) {
        j.e(xMPMeta, "xmp");
        this.xmp = xMPMeta;
        this.index = i2;
    }

    public static /* synthetic */ XMPItemInfo copy$default(XMPItemInfo xMPItemInfo, XMPMeta xMPMeta, int i2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            xMPMeta = xMPItemInfo.xmp;
        }
        if ((i7 & 2) != 0) {
            i2 = xMPItemInfo.index;
        }
        return xMPItemInfo.copy(xMPMeta, i2);
    }

    private final String getStruct() {
        String composeArrayItemPath = XMPPathFactory.composeArrayItemPath("Directory", this.index);
        return o.l0("\n        " + composeArrayItemPath + "/Container:Item\n    ");
    }

    public final XMPMeta component1() {
        return this.xmp;
    }

    public final int component2() {
        return this.index;
    }

    public final boolean contains(String str) {
        j.e(str, "fieldName");
        return this.xmp.doesStructFieldExist(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, getStruct(), MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:".concat(str));
    }

    public final XMPItemInfo copy(XMPMeta xMPMeta, int i2) {
        j.e(xMPMeta, "xmp");
        return new XMPItemInfo(xMPMeta, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof XMPItemInfo)) {
            return false;
        }
        XMPItemInfo xMPItemInfo = (XMPItemInfo) obj;
        if (j.a(this.xmp, xMPItemInfo.xmp) && this.index == xMPItemInfo.index) {
            return true;
        }
        return false;
    }

    public final String get(String str) {
        j.e(str, "fieldName");
        XMPProperty structField = this.xmp.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, getStruct(), MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:".concat(str));
        if (structField == null) {
            return null;
        }
        this.fields.add(str);
        return structField.getValue();
    }

    public final List<String> getFieldsWith(String... strArr) {
        Iterable iterable;
        j.e(strArr, "names");
        if (strArr.length == 0) {
            iterable = this.fields;
        } else {
            Set<String> set = this.fields;
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                if (!this.fields.contains(str)) {
                    arrayList.add(str);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (contains((String) next)) {
                    arrayList2.add(next);
                }
            }
            iterable = C1182C.b0(set, arrayList2);
        }
        return C1194l.k1(iterable);
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getMime() {
        XMPProperty structField = this.xmp.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, getStruct(), MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:Mime");
        j.b(structField);
        String value = structField.getValue();
        j.b(value);
        return value;
    }

    public final String getName() {
        XMPProperty structField = this.xmp.getStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, getStruct(), MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, "Item:Semantic");
        j.b(structField);
        String value = structField.getValue();
        j.b(value);
        return value;
    }

    public final XMPMeta getXmp() {
        return this.xmp;
    }

    public int hashCode() {
        return Integer.hashCode(this.index) + (this.xmp.hashCode() * 31);
    }

    public final void remove(String str) {
        j.e(str, "fieldName");
        this.xmp.deleteStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, getStruct(), MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, str);
        this.fields.remove(str);
    }

    public final void set(String str, Object obj) {
        j.e(str, "fieldName");
        j.e(obj, "fieldValue");
        String str2 = str;
        this.xmp.setStructField(MediaDefs.Meta.XMP.XMP_GOOGLE_CONTAINER_NS, getStruct(), MediaDefs.Meta.XMP.XMP_GOOGLE_ITEM_NS, str2, String.valueOf(obj));
        this.fields.add(str2);
    }

    public String toString() {
        XMPMeta xMPMeta = this.xmp;
        int i2 = this.index;
        return "XMPItemInfo(xmp=" + xMPMeta + ", index=" + i2 + ")";
    }
}
