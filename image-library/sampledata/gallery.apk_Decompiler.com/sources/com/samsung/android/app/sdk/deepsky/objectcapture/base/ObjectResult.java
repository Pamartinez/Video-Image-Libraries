package com.samsung.android.app.sdk.deepsky.objectcapture.base;

import L2.a;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000e\b\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\nJ\u000e\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\nJ\u001e\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#J \u0010$\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#J\u0006\u0010%\u001a\u00020\u0003J\u0006\u0010&\u001a\u00020\u0000J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\bHÆ\u0003J\t\u0010+\u001a\u00020\nHÆ\u0003J\t\u0010,\u001a\u00020\fHÆ\u0003JK\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010-\u001a\u00020\u00032\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u00020\nHÖ\u0001J\t\u00100\u001a\u00020\fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u00061"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "", "isCaptured", "", "output", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectInfo;", "singleOutput", "objects", "", "errorCode", "", "solutionInfo", "", "<init>", "(ZLcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectInfo;Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectInfo;Ljava/util/List;ILjava/lang/String;)V", "()Z", "getOutput", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectInfo;", "setOutput", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectInfo;)V", "getSingleOutput", "getObjects", "()Ljava/util/List;", "setObjects", "(Ljava/util/List;)V", "getErrorCode", "()I", "getSolutionInfo", "()Ljava/lang/String;", "findObjectIndexByPosition", "x", "y", "getObjectResult", "position", "clippingRect", "Landroid/graphics/RectF;", "getObjectByPositionOrNull", "isSingleObject", "copy", "component1", "component2", "component3", "component4", "component5", "component6", "equals", "other", "hashCode", "toString", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectResult {
    private final int errorCode;
    private final boolean isCaptured;
    private List<ObjectInfo> objects;
    private ObjectInfo output;
    private final ObjectInfo singleOutput;
    private final String solutionInfo;

    public ObjectResult(boolean z, ObjectInfo objectInfo, ObjectInfo objectInfo2, List<ObjectInfo> list, int i2, String str) {
        j.e(objectInfo, "output");
        j.e(objectInfo2, "singleOutput");
        j.e(list, "objects");
        j.e(str, "solutionInfo");
        this.isCaptured = z;
        this.output = objectInfo;
        this.singleOutput = objectInfo2;
        this.objects = list;
        this.errorCode = i2;
        this.solutionInfo = str;
    }

    public static /* synthetic */ ObjectResult copy$default(ObjectResult objectResult, boolean z, ObjectInfo objectInfo, ObjectInfo objectInfo2, List<ObjectInfo> list, int i2, String str, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            z = objectResult.isCaptured;
        }
        if ((i7 & 2) != 0) {
            objectInfo = objectResult.output;
        }
        if ((i7 & 4) != 0) {
            objectInfo2 = objectResult.singleOutput;
        }
        if ((i7 & 8) != 0) {
            list = objectResult.objects;
        }
        if ((i7 & 16) != 0) {
            i2 = objectResult.errorCode;
        }
        if ((i7 & 32) != 0) {
            str = objectResult.solutionInfo;
        }
        int i8 = i2;
        String str2 = str;
        List<ObjectInfo> list2 = list;
        ObjectInfo objectInfo3 = objectInfo;
        return objectResult.copy(z, objectInfo3, objectInfo2, list2, i8, str2);
    }

    public final boolean component1() {
        return this.isCaptured;
    }

    public final ObjectInfo component2() {
        return this.output;
    }

    public final ObjectInfo component3() {
        return this.singleOutput;
    }

    public final List<ObjectInfo> component4() {
        return this.objects;
    }

    public final int component5() {
        return this.errorCode;
    }

    public final String component6() {
        return this.solutionInfo;
    }

    public final ObjectResult copy(boolean z, ObjectInfo objectInfo, ObjectInfo objectInfo2, List<ObjectInfo> list, int i2, String str) {
        j.e(objectInfo, "output");
        j.e(objectInfo2, "singleOutput");
        j.e(list, "objects");
        j.e(str, "solutionInfo");
        return new ObjectResult(z, objectInfo, objectInfo2, list, i2, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ObjectResult)) {
            return false;
        }
        ObjectResult objectResult = (ObjectResult) obj;
        if (this.isCaptured == objectResult.isCaptured && j.a(this.output, objectResult.output) && j.a(this.singleOutput, objectResult.singleOutput) && j.a(this.objects, objectResult.objects) && this.errorCode == objectResult.errorCode && j.a(this.solutionInfo, objectResult.solutionInfo)) {
            return true;
        }
        return false;
    }

    public final int findObjectIndexByPosition(int i2, int i7) {
        Object obj;
        ArrayList arrayList = new ArrayList();
        for (Object next : this.objects) {
            if (((ObjectInfo) next).getBoundRect().contains(i2, i7)) {
                arrayList.add(next);
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (j.a(((ObjectInfo) obj).isNotEmptyPixelOrNull$deepsky_sdk_objectcapture_8_5_9_release(new Point(i2, i7)), Boolean.TRUE)) {
                break;
            }
        }
        ObjectInfo objectInfo = (ObjectInfo) obj;
        if (objectInfo != null) {
            return this.objects.indexOf(objectInfo);
        }
        return -1;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final ObjectInfo getObjectByPositionOrNull(int i2, int i7, RectF rectF) {
        Object obj;
        j.e(rectF, "clippingRect");
        try {
            obj = this.objects.get(findObjectIndexByPosition(i2, i7, rectF));
        } catch (Throwable th) {
            obj = a.l(th);
        }
        if (obj instanceof me.j) {
            obj = null;
        }
        return (ObjectInfo) obj;
    }

    public final ObjectInfo getObjectResult(int i2) {
        if (i2 == -1) {
            return this.output;
        }
        return this.objects.get(i2);
    }

    public final List<ObjectInfo> getObjects() {
        return this.objects;
    }

    public final ObjectInfo getOutput() {
        return this.output;
    }

    public final ObjectInfo getSingleOutput() {
        return this.singleOutput;
    }

    public final String getSolutionInfo() {
        return this.solutionInfo;
    }

    public int hashCode() {
        int hashCode = this.output.hashCode();
        int hashCode2 = this.singleOutput.hashCode();
        return this.solutionInfo.hashCode() + C0212a.b(this.errorCode, C0212a.f(this.objects, (hashCode2 + ((hashCode + (Boolean.hashCode(this.isCaptured) * 31)) * 31)) * 31, 31), 31);
    }

    public final boolean isCaptured() {
        return this.isCaptured;
    }

    public final boolean isSingleObject() {
        if (this.objects.size() == 1) {
            return true;
        }
        return false;
    }

    public final void setObjects(List<ObjectInfo> list) {
        j.e(list, "<set-?>");
        this.objects = list;
    }

    public final void setOutput(ObjectInfo objectInfo) {
        j.e(objectInfo, "<set-?>");
        this.output = objectInfo;
    }

    public String toString() {
        boolean z = this.isCaptured;
        ObjectInfo objectInfo = this.output;
        ObjectInfo objectInfo2 = this.singleOutput;
        List<ObjectInfo> list = this.objects;
        int i2 = this.errorCode;
        String str = this.solutionInfo;
        return "ObjectResult(isCaptured=" + z + ", output=" + objectInfo + ", singleOutput=" + objectInfo2 + ", objects=" + list + ", errorCode=" + i2 + ", solutionInfo=" + str + ")";
    }

    public final ObjectResult copy() {
        boolean z = this.isCaptured;
        ObjectInfo copy$default = ObjectInfo.copy$default(this.output, (Bitmap) null, (Rect) null, 3, (Object) null);
        ObjectInfo copy$default2 = ObjectInfo.copy$default(this.singleOutput, (Bitmap) null, (Rect) null, 3, (Object) null);
        Iterable<ObjectInfo> iterable = this.objects;
        ObjectInfo objectInfo = copy$default2;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (ObjectInfo copy$default3 : iterable) {
            arrayList.add(ObjectInfo.copy$default(copy$default3, (Bitmap) null, (Rect) null, 3, (Object) null));
        }
        return new ObjectResult(z, copy$default, objectInfo, arrayList, this.errorCode, this.solutionInfo);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ObjectResult(boolean z, ObjectInfo objectInfo, ObjectInfo objectInfo2, List list, int i2, String str, int i7, e eVar) {
        this(z, objectInfo, objectInfo2, list, (i7 & 16) != 0 ? 0 : i2, (i7 & 32) != 0 ? "" : str);
    }

    public final int findObjectIndexByPosition(int i2, int i7, RectF rectF) {
        j.e(rectF, "clippingRect");
        int findObjectIndexByPosition = findObjectIndexByPosition(i2, i7);
        StringBuilder h5 = A.a.h(i2, i7, "xy:(", ArcCommonLog.TAG_COMMA, "), [");
        h5.append(rectF);
        h5.append("] clipping is not considered yet.");
        LibLogger.w("ObjectResult", h5.toString());
        return findObjectIndexByPosition;
    }
}
