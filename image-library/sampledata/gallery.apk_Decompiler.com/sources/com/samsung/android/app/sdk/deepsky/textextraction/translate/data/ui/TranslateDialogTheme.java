package com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui;

import A.a;
import N2.j;
import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0010R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0017\u0010\u0010R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0018\u0010\u0010R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0019\u0010\u0010R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0015\u001a\u0004\b\u001a\u0010\u0010R\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u0015\u001a\u0004\b\u001b\u0010\u0010R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u001c\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/TranslateDialogTheme;", "", "", "codeBackground", "textColor", "dialogBackground", "closeButtonBackground", "closeButtonTintColor", "itemBackground", "translationAutoDetectingAnimation", "<init>", "(IIIIIII)V", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getCodeBackground", "getTextColor", "getDialogBackground", "getCloseButtonBackground", "getCloseButtonTintColor", "getItemBackground", "getTranslationAutoDetectingAnimation", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TranslateDialogTheme {
    private final int closeButtonBackground;
    private final int closeButtonTintColor;
    private final int codeBackground;
    private final int dialogBackground;
    private final int itemBackground;
    private final int textColor;
    private final int translationAutoDetectingAnimation;

    public TranslateDialogTheme(int i2, int i7, int i8, int i10, int i11, int i12, int i13) {
        this.codeBackground = i2;
        this.textColor = i7;
        this.dialogBackground = i8;
        this.closeButtonBackground = i10;
        this.closeButtonTintColor = i11;
        this.itemBackground = i12;
        this.translationAutoDetectingAnimation = i13;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TranslateDialogTheme)) {
            return false;
        }
        TranslateDialogTheme translateDialogTheme = (TranslateDialogTheme) obj;
        if (this.codeBackground == translateDialogTheme.codeBackground && this.textColor == translateDialogTheme.textColor && this.dialogBackground == translateDialogTheme.dialogBackground && this.closeButtonBackground == translateDialogTheme.closeButtonBackground && this.closeButtonTintColor == translateDialogTheme.closeButtonTintColor && this.itemBackground == translateDialogTheme.itemBackground && this.translationAutoDetectingAnimation == translateDialogTheme.translationAutoDetectingAnimation) {
            return true;
        }
        return false;
    }

    public final int getCloseButtonBackground() {
        return this.closeButtonBackground;
    }

    public final int getCodeBackground() {
        return this.codeBackground;
    }

    public final int getDialogBackground() {
        return this.dialogBackground;
    }

    public final int getItemBackground() {
        return this.itemBackground;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final int getTranslationAutoDetectingAnimation() {
        return this.translationAutoDetectingAnimation;
    }

    public int hashCode() {
        return Integer.hashCode(this.translationAutoDetectingAnimation) + C0212a.b(this.itemBackground, C0212a.b(this.closeButtonTintColor, C0212a.b(this.closeButtonBackground, C0212a.b(this.dialogBackground, C0212a.b(this.textColor, Integer.hashCode(this.codeBackground) * 31, 31), 31), 31), 31), 31);
    }

    public String toString() {
        int i2 = this.codeBackground;
        int i7 = this.textColor;
        int i8 = this.dialogBackground;
        int i10 = this.closeButtonBackground;
        int i11 = this.closeButtonTintColor;
        int i12 = this.itemBackground;
        int i13 = this.translationAutoDetectingAnimation;
        StringBuilder h5 = a.h(i2, i7, "TranslateDialogTheme(codeBackground=", ", textColor=", ", dialogBackground=");
        j.x(h5, i8, ", closeButtonBackground=", i10, ", closeButtonTintColor=");
        j.x(h5, i11, ", itemBackground=", i12, ", translationAutoDetectingAnimation=");
        return C0086a.l(h5, i13, ")");
    }
}
