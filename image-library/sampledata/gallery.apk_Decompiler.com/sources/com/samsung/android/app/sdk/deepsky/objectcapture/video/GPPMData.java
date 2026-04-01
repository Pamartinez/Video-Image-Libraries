package com.samsung.android.app.sdk.deepsky.objectcapture.video;

import android.net.Uri;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003J\t\u0010\u001f\u001a\u00020\fHÆ\u0003JE\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\bHÖ\u0001J\t\u0010%\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006&"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMData;", "", "imageUri", "Landroid/net/Uri;", "x", "", "y", "videoFrameIndex", "", "dstPath", "", "panelString", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMPanelString;", "<init>", "(Landroid/net/Uri;FFILjava/lang/String;Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMPanelString;)V", "getImageUri", "()Landroid/net/Uri;", "getX", "()F", "getY", "getVideoFrameIndex", "()I", "getDstPath", "()Ljava/lang/String;", "getPanelString", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMPanelString;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GPPMData {
    private final String dstPath;
    private final Uri imageUri;
    private final GPPMPanelString panelString;
    private final int videoFrameIndex;

    /* renamed from: x  reason: collision with root package name */
    private final float f1634x;
    private final float y;

    public GPPMData(Uri uri, float f, float f5, int i2, String str, GPPMPanelString gPPMPanelString) {
        j.e(uri, "imageUri");
        j.e(str, "dstPath");
        j.e(gPPMPanelString, "panelString");
        this.imageUri = uri;
        this.f1634x = f;
        this.y = f5;
        this.videoFrameIndex = i2;
        this.dstPath = str;
        this.panelString = gPPMPanelString;
    }

    public static /* synthetic */ GPPMData copy$default(GPPMData gPPMData, Uri uri, float f, float f5, int i2, String str, GPPMPanelString gPPMPanelString, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            uri = gPPMData.imageUri;
        }
        if ((i7 & 2) != 0) {
            f = gPPMData.f1634x;
        }
        if ((i7 & 4) != 0) {
            f5 = gPPMData.y;
        }
        if ((i7 & 8) != 0) {
            i2 = gPPMData.videoFrameIndex;
        }
        if ((i7 & 16) != 0) {
            str = gPPMData.dstPath;
        }
        if ((i7 & 32) != 0) {
            gPPMPanelString = gPPMData.panelString;
        }
        String str2 = str;
        GPPMPanelString gPPMPanelString2 = gPPMPanelString;
        int i8 = i2;
        float f8 = f;
        return gPPMData.copy(uri, f8, f5, i8, str2, gPPMPanelString2);
    }

    public final Uri component1() {
        return this.imageUri;
    }

    public final float component2() {
        return this.f1634x;
    }

    public final float component3() {
        return this.y;
    }

    public final int component4() {
        return this.videoFrameIndex;
    }

    public final String component5() {
        return this.dstPath;
    }

    public final GPPMPanelString component6() {
        return this.panelString;
    }

    public final GPPMData copy(Uri uri, float f, float f5, int i2, String str, GPPMPanelString gPPMPanelString) {
        j.e(uri, "imageUri");
        j.e(str, "dstPath");
        j.e(gPPMPanelString, "panelString");
        return new GPPMData(uri, f, f5, i2, str, gPPMPanelString);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GPPMData)) {
            return false;
        }
        GPPMData gPPMData = (GPPMData) obj;
        if (j.a(this.imageUri, gPPMData.imageUri) && Float.compare(this.f1634x, gPPMData.f1634x) == 0 && Float.compare(this.y, gPPMData.y) == 0 && this.videoFrameIndex == gPPMData.videoFrameIndex && j.a(this.dstPath, gPPMData.dstPath) && j.a(this.panelString, gPPMData.panelString)) {
            return true;
        }
        return false;
    }

    public final String getDstPath() {
        return this.dstPath;
    }

    public final Uri getImageUri() {
        return this.imageUri;
    }

    public final GPPMPanelString getPanelString() {
        return this.panelString;
    }

    public final int getVideoFrameIndex() {
        return this.videoFrameIndex;
    }

    public final float getX() {
        return this.f1634x;
    }

    public final float getY() {
        return this.y;
    }

    public int hashCode() {
        return this.panelString.hashCode() + C0212a.d(C0212a.b(this.videoFrameIndex, N2.j.a(this.y, N2.j.a(this.f1634x, this.imageUri.hashCode() * 31, 31), 31), 31), 31, this.dstPath);
    }

    public String toString() {
        Uri uri = this.imageUri;
        float f = this.f1634x;
        float f5 = this.y;
        int i2 = this.videoFrameIndex;
        String str = this.dstPath;
        GPPMPanelString gPPMPanelString = this.panelString;
        return "GPPMData(imageUri=" + uri + ", x=" + f + ", y=" + f5 + ", videoFrameIndex=" + i2 + ", dstPath=" + str + ", panelString=" + gPPMPanelString + ")";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GPPMData(Uri uri, float f, float f5, int i2, String str, GPPMPanelString gPPMPanelString, int i7, e eVar) {
        this(uri, f, f5, i2, (i7 & 16) != 0 ? "" : str, gPPMPanelString);
    }
}
