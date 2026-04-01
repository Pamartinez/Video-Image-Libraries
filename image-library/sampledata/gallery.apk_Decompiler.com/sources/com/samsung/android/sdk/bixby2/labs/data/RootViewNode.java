package com.samsung.android.sdk.bixby2.labs.data;

import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\tHÆ\u0003J<\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/sdk/bixby2/labs/data/RootViewNode;", "", "rootViewName", "", "viewNodeInfo", "Lcom/samsung/android/sdk/bixby2/labs/data/ViewNodeInfo;", "windowType", "", "error", "Lcom/samsung/android/sdk/bixby2/labs/data/ErrorInfo;", "(Ljava/lang/String;Lcom/samsung/android/sdk/bixby2/labs/data/ViewNodeInfo;Ljava/lang/Integer;Lcom/samsung/android/sdk/bixby2/labs/data/ErrorInfo;)V", "getError", "()Lcom/samsung/android/sdk/bixby2/labs/data/ErrorInfo;", "getRootViewName", "()Ljava/lang/String;", "getViewNodeInfo", "()Lcom/samsung/android/sdk/bixby2/labs/data/ViewNodeInfo;", "getWindowType", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Lcom/samsung/android/sdk/bixby2/labs/data/ViewNodeInfo;Ljava/lang/Integer;Lcom/samsung/android/sdk/bixby2/labs/data/ErrorInfo;)Lcom/samsung/android/sdk/bixby2/labs/data/RootViewNode;", "equals", "", "other", "hashCode", "toString", "bixbyappsdk_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RootViewNode {
    private final ErrorInfo error;
    private final String rootViewName;
    private final ViewNodeInfo viewNodeInfo;
    private final Integer windowType;

    public RootViewNode(String str, ViewNodeInfo viewNodeInfo2, Integer num, ErrorInfo errorInfo) {
        j.e(str, "rootViewName");
        this.rootViewName = str;
        this.viewNodeInfo = viewNodeInfo2;
        this.windowType = num;
        this.error = errorInfo;
    }

    public static /* synthetic */ RootViewNode copy$default(RootViewNode rootViewNode, String str, ViewNodeInfo viewNodeInfo2, Integer num, ErrorInfo errorInfo, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = rootViewNode.rootViewName;
        }
        if ((i2 & 2) != 0) {
            viewNodeInfo2 = rootViewNode.viewNodeInfo;
        }
        if ((i2 & 4) != 0) {
            num = rootViewNode.windowType;
        }
        if ((i2 & 8) != 0) {
            errorInfo = rootViewNode.error;
        }
        return rootViewNode.copy(str, viewNodeInfo2, num, errorInfo);
    }

    public final String component1() {
        return this.rootViewName;
    }

    public final ViewNodeInfo component2() {
        return this.viewNodeInfo;
    }

    public final Integer component3() {
        return this.windowType;
    }

    public final ErrorInfo component4() {
        return this.error;
    }

    public final RootViewNode copy(String str, ViewNodeInfo viewNodeInfo2, Integer num, ErrorInfo errorInfo) {
        j.e(str, "rootViewName");
        return new RootViewNode(str, viewNodeInfo2, num, errorInfo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RootViewNode)) {
            return false;
        }
        RootViewNode rootViewNode = (RootViewNode) obj;
        if (j.a(this.rootViewName, rootViewNode.rootViewName) && j.a(this.viewNodeInfo, rootViewNode.viewNodeInfo) && j.a(this.windowType, rootViewNode.windowType) && j.a(this.error, rootViewNode.error)) {
            return true;
        }
        return false;
    }

    public final ErrorInfo getError() {
        return this.error;
    }

    public final String getRootViewName() {
        return this.rootViewName;
    }

    public final ViewNodeInfo getViewNodeInfo() {
        return this.viewNodeInfo;
    }

    public final Integer getWindowType() {
        return this.windowType;
    }

    public int hashCode() {
        int i2;
        int i7;
        int hashCode = this.rootViewName.hashCode() * 31;
        ViewNodeInfo viewNodeInfo2 = this.viewNodeInfo;
        int i8 = 0;
        if (viewNodeInfo2 == null) {
            i2 = 0;
        } else {
            i2 = viewNodeInfo2.hashCode();
        }
        int i10 = (hashCode + i2) * 31;
        Integer num = this.windowType;
        if (num == null) {
            i7 = 0;
        } else {
            i7 = num.hashCode();
        }
        int i11 = (i10 + i7) * 31;
        ErrorInfo errorInfo = this.error;
        if (errorInfo != null) {
            i8 = errorInfo.hashCode();
        }
        return i11 + i8;
    }

    public String toString() {
        return "RootViewNode(rootViewName=" + this.rootViewName + ", viewNodeInfo=" + this.viewNodeInfo + ", windowType=" + this.windowType + ", error=" + this.error + ')';
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RootViewNode(String str, ViewNodeInfo viewNodeInfo2, Integer num, ErrorInfo errorInfo, int i2, e eVar) {
        this(str, (i2 & 2) != 0 ? null : viewNodeInfo2, (i2 & 4) != 0 ? null : num, (i2 & 8) != 0 ? null : errorInfo);
    }
}
