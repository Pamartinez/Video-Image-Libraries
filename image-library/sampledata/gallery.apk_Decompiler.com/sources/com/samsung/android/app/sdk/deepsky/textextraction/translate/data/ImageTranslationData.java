package com.samsung.android.app.sdk.deepsky.textextraction.translate.data;

import android.graphics.Point;
import c0.C0086a;
import i.C0212a;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\b\b\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u0019\u0010\rR\"\u0010\u0007\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u001cR\"\u0010\b\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u0018\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u001cR\"\u0010\t\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0018\u001a\u0004\b\u001f\u0010\r\"\u0004\b \u0010\u001c¨\u0006!"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationData;", "", "", "Landroid/graphics/Point;", "poly", "", "sourceText", "sourceLang", "targetText", "targetLang", "<init>", "([Landroid/graphics/Point;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "Ljava/lang/String;", "getSourceText", "getSourceLang", "setSourceLang", "(Ljava/lang/String;)V", "getTargetText", "setTargetText", "getTargetLang", "setTargetLang", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ImageTranslationData {
    private final Point[] poly;
    private String sourceLang;
    private final String sourceText;
    private String targetLang;
    private String targetText;

    public ImageTranslationData(Point[] pointArr, String str, String str2, String str3, String str4) {
        j.e(pointArr, "poly");
        j.e(str, "sourceText");
        j.e(str2, "sourceLang");
        j.e(str3, "targetText");
        j.e(str4, "targetLang");
        this.poly = pointArr;
        this.sourceText = str;
        this.sourceLang = str2;
        this.targetText = str3;
        this.targetLang = str4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageTranslationData)) {
            return false;
        }
        ImageTranslationData imageTranslationData = (ImageTranslationData) obj;
        if (j.a(this.poly, imageTranslationData.poly) && j.a(this.sourceText, imageTranslationData.sourceText) && j.a(this.sourceLang, imageTranslationData.sourceLang) && j.a(this.targetText, imageTranslationData.targetText) && j.a(this.targetLang, imageTranslationData.targetLang)) {
            return true;
        }
        return false;
    }

    public final String getSourceLang() {
        return this.sourceLang;
    }

    public final String getSourceText() {
        return this.sourceText;
    }

    public final String getTargetLang() {
        return this.targetLang;
    }

    public final String getTargetText() {
        return this.targetText;
    }

    public int hashCode() {
        return this.targetLang.hashCode() + C0212a.d(C0212a.d(C0212a.d(Arrays.hashCode(this.poly) * 31, 31, this.sourceText), 31, this.sourceLang), 31, this.targetText);
    }

    public final void setSourceLang(String str) {
        j.e(str, "<set-?>");
        this.sourceLang = str;
    }

    public final void setTargetLang(String str) {
        j.e(str, "<set-?>");
        this.targetLang = str;
    }

    public final void setTargetText(String str) {
        j.e(str, "<set-?>");
        this.targetText = str;
    }

    public String toString() {
        String arrays = Arrays.toString(this.poly);
        String str = this.sourceText;
        String str2 = this.sourceLang;
        String str3 = this.targetText;
        String str4 = this.targetLang;
        StringBuilder q = C0086a.q("ImageTranslationData(poly=", arrays, ", sourceText=", str, ", sourceLang=");
        C0086a.z(q, str2, ", targetText=", str3, ", targetLang=");
        return C0212a.p(q, str4, ")");
    }
}
