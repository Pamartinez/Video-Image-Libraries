package com.samsung.android.app.sdk.deepsky.objectcapture.base;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import i.C0212a;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b&\b\b\u0018\u0000 52\u00020\u0001:\u00015BA\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\fHÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJR\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\fHÆ\u0001¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b!\u0010\u001eJ\u0010\u0010\"\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\"\u0010\u001cJ\u001a\u0010$\u001a\u00020\u00022\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b\u0003\u0010\u0014R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010'\u001a\u0004\b(\u0010\u0016R$\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010)\u001a\u0004\b*\u0010\u0018\"\u0004\b+\u0010,R$\u0010\t\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\t\u0010-\u001a\u0004\b.\u0010\u001a\"\u0004\b/\u00100R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00101\u001a\u0004\b2\u0010\u001cR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u00103\u001a\u0004\b4\u0010\u001e¨\u00066"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult;", "", "", "isCaptured", "Landroid/graphics/Bitmap;", "maskedObject", "", "mask", "Landroid/graphics/Rect;", "boundingBox", "", "errorCode", "", "solutionInfo", "<init>", "(ZLandroid/graphics/Bitmap;[ILandroid/graphics/Rect;ILjava/lang/String;)V", "Lme/x;", "release", "()V", "component1", "()Z", "component2", "()Landroid/graphics/Bitmap;", "component3", "()[I", "component4", "()Landroid/graphics/Rect;", "component5", "()I", "component6", "()Ljava/lang/String;", "copy", "(ZLandroid/graphics/Bitmap;[ILandroid/graphics/Rect;ILjava/lang/String;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult;", "toString", "hashCode", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "Landroid/graphics/Bitmap;", "getMaskedObject", "[I", "getMask", "setMask", "([I)V", "Landroid/graphics/Rect;", "getBoundingBox", "setBoundingBox", "(Landroid/graphics/Rect;)V", "I", "getErrorCode", "Ljava/lang/String;", "getSolutionInfo", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MaskedObjectResult {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "MaskedObjectResult";
    private Rect boundingBox;
    private final int errorCode;
    private final boolean isCaptured;
    private int[] mask;
    private final Bitmap maskedObject;
    private final String solutionInfo;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/MaskedObjectResult$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public MaskedObjectResult(boolean z, Bitmap bitmap, int[] iArr, Rect rect, int i2, String str) {
        j.e(str, "solutionInfo");
        this.isCaptured = z;
        this.maskedObject = bitmap;
        this.mask = iArr;
        this.boundingBox = rect;
        this.errorCode = i2;
        this.solutionInfo = str;
    }

    public static /* synthetic */ MaskedObjectResult copy$default(MaskedObjectResult maskedObjectResult, boolean z, Bitmap bitmap, int[] iArr, Rect rect, int i2, String str, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            z = maskedObjectResult.isCaptured;
        }
        if ((i7 & 2) != 0) {
            bitmap = maskedObjectResult.maskedObject;
        }
        if ((i7 & 4) != 0) {
            iArr = maskedObjectResult.mask;
        }
        if ((i7 & 8) != 0) {
            rect = maskedObjectResult.boundingBox;
        }
        if ((i7 & 16) != 0) {
            i2 = maskedObjectResult.errorCode;
        }
        if ((i7 & 32) != 0) {
            str = maskedObjectResult.solutionInfo;
        }
        int i8 = i2;
        String str2 = str;
        Rect rect2 = rect;
        Bitmap bitmap2 = bitmap;
        return maskedObjectResult.copy(z, bitmap2, iArr, rect2, i8, str2);
    }

    public final boolean component1() {
        return this.isCaptured;
    }

    public final Bitmap component2() {
        return this.maskedObject;
    }

    public final int[] component3() {
        return this.mask;
    }

    public final Rect component4() {
        return this.boundingBox;
    }

    public final int component5() {
        return this.errorCode;
    }

    public final String component6() {
        return this.solutionInfo;
    }

    public final MaskedObjectResult copy(boolean z, Bitmap bitmap, int[] iArr, Rect rect, int i2, String str) {
        j.e(str, "solutionInfo");
        return new MaskedObjectResult(z, bitmap, iArr, rect, i2, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MaskedObjectResult)) {
            return false;
        }
        MaskedObjectResult maskedObjectResult = (MaskedObjectResult) obj;
        if (this.isCaptured == maskedObjectResult.isCaptured && j.a(this.maskedObject, maskedObjectResult.maskedObject) && j.a(this.mask, maskedObjectResult.mask) && j.a(this.boundingBox, maskedObjectResult.boundingBox) && this.errorCode == maskedObjectResult.errorCode && j.a(this.solutionInfo, maskedObjectResult.solutionInfo)) {
            return true;
        }
        return false;
    }

    public final Rect getBoundingBox() {
        return this.boundingBox;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final int[] getMask() {
        return this.mask;
    }

    public final Bitmap getMaskedObject() {
        return this.maskedObject;
    }

    public final String getSolutionInfo() {
        return this.solutionInfo;
    }

    public int hashCode() {
        int i2;
        int i7;
        int hashCode = Boolean.hashCode(this.isCaptured) * 31;
        Bitmap bitmap = this.maskedObject;
        int i8 = 0;
        if (bitmap == null) {
            i2 = 0;
        } else {
            i2 = bitmap.hashCode();
        }
        int i10 = (hashCode + i2) * 31;
        int[] iArr = this.mask;
        if (iArr == null) {
            i7 = 0;
        } else {
            i7 = Arrays.hashCode(iArr);
        }
        int i11 = (i10 + i7) * 31;
        Rect rect = this.boundingBox;
        if (rect != null) {
            i8 = rect.hashCode();
        }
        return this.solutionInfo.hashCode() + C0212a.b(this.errorCode, (i11 + i8) * 31, 31);
    }

    public final boolean isCaptured() {
        return this.isCaptured;
    }

    public final void release() {
        LibLogger.d(TAG, "release MaskedObjectResult");
        Bitmap bitmap = this.maskedObject;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.mask = null;
        this.boundingBox = null;
    }

    public final void setBoundingBox(Rect rect) {
        this.boundingBox = rect;
    }

    public final void setMask(int[] iArr) {
        this.mask = iArr;
    }

    public String toString() {
        boolean z = this.isCaptured;
        Bitmap bitmap = this.maskedObject;
        String arrays = Arrays.toString(this.mask);
        Rect rect = this.boundingBox;
        int i2 = this.errorCode;
        String str = this.solutionInfo;
        return "MaskedObjectResult(isCaptured=" + z + ", maskedObject=" + bitmap + ", mask=" + arrays + ", boundingBox=" + rect + ", errorCode=" + i2 + ", solutionInfo=" + str + ")";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MaskedObjectResult(boolean z, Bitmap bitmap, int[] iArr, Rect rect, int i2, String str, int i7, e eVar) {
        this(z, bitmap, iArr, rect, (i7 & 16) != 0 ? 0 : i2, (i7 & 32) != 0 ? "" : str);
    }
}
