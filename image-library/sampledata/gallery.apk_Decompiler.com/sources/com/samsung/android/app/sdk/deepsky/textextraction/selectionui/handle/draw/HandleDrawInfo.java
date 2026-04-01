package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw;

import android.graphics.Point;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\b\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0014\u001a\u00020\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u0005\u0010\u001aR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u0016\u001a\u0004\b!\u0010\u0018¨\u0006\""}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/draw/HandleDrawInfo;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "selectedChar", "", "isLeftToRight", "Landroid/graphics/Point;", "touchPoint", "Landroid/graphics/Rect;", "boundingRect", "charForAnimation", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;ZLandroid/graphics/Point;Landroid/graphics/Rect;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "getSelectedChar", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "Z", "()Z", "Landroid/graphics/Point;", "getTouchPoint", "()Landroid/graphics/Point;", "Landroid/graphics/Rect;", "getBoundingRect", "()Landroid/graphics/Rect;", "getCharForAnimation", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HandleDrawInfo {
    private final Rect boundingRect;
    private final SelectableCharacter charForAnimation;
    private final boolean isLeftToRight;
    private final SelectableCharacter selectedChar;
    private final Point touchPoint;

    public HandleDrawInfo(SelectableCharacter selectableCharacter, boolean z, Point point, Rect rect, SelectableCharacter selectableCharacter2) {
        j.e(selectableCharacter, "selectedChar");
        j.e(point, "touchPoint");
        j.e(rect, "boundingRect");
        this.selectedChar = selectableCharacter;
        this.isLeftToRight = z;
        this.touchPoint = point;
        this.boundingRect = rect;
        this.charForAnimation = selectableCharacter2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HandleDrawInfo)) {
            return false;
        }
        HandleDrawInfo handleDrawInfo = (HandleDrawInfo) obj;
        if (j.a(this.selectedChar, handleDrawInfo.selectedChar) && this.isLeftToRight == handleDrawInfo.isLeftToRight && j.a(this.touchPoint, handleDrawInfo.touchPoint) && j.a(this.boundingRect, handleDrawInfo.boundingRect) && j.a(this.charForAnimation, handleDrawInfo.charForAnimation)) {
            return true;
        }
        return false;
    }

    public final Rect getBoundingRect() {
        return this.boundingRect;
    }

    public final SelectableCharacter getCharForAnimation() {
        return this.charForAnimation;
    }

    public final SelectableCharacter getSelectedChar() {
        return this.selectedChar;
    }

    public final Point getTouchPoint() {
        return this.touchPoint;
    }

    public int hashCode() {
        int i2;
        int e = C0212a.e(this.selectedChar.hashCode() * 31, 31, this.isLeftToRight);
        int hashCode = (this.boundingRect.hashCode() + ((this.touchPoint.hashCode() + e) * 31)) * 31;
        SelectableCharacter selectableCharacter = this.charForAnimation;
        if (selectableCharacter == null) {
            i2 = 0;
        } else {
            i2 = selectableCharacter.hashCode();
        }
        return hashCode + i2;
    }

    public final boolean isLeftToRight() {
        return this.isLeftToRight;
    }

    public String toString() {
        SelectableCharacter selectableCharacter = this.selectedChar;
        boolean z = this.isLeftToRight;
        Point point = this.touchPoint;
        Rect rect = this.boundingRect;
        SelectableCharacter selectableCharacter2 = this.charForAnimation;
        return "HandleDrawInfo(selectedChar=" + selectableCharacter + ", isLeftToRight=" + z + ", touchPoint=" + point + ", boundingRect=" + rect + ", charForAnimation=" + selectableCharacter2 + ")";
    }
}
