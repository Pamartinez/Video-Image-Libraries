package com.samsung.android.sdk.scs.ai.language;

import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J\t\u0010&\u001a\u00020\u000bHÆ\u0003J\t\u0010'\u001a\u00020\rHÆ\u0003JQ\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\rHÖ\u0001J\t\u0010-\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006."}, d2 = {"Lcom/samsung/android/sdk/scs/ai/language/WritingComposerMultiInputParams;", "", "inputText", "", "dataMimeTypeList", "", "dataList", "", "format", "Lcom/samsung/android/sdk/scs/ai/language/WritingComposerFormat;", "tone", "Lcom/samsung/android/sdk/scs/ai/language/WritingComposerTone;", "outputSentenceCount", "", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/samsung/android/sdk/scs/ai/language/WritingComposerFormat;Lcom/samsung/android/sdk/scs/ai/language/WritingComposerTone;I)V", "getInputText", "()Ljava/lang/String;", "getDataMimeTypeList", "()Ljava/util/List;", "setDataMimeTypeList", "(Ljava/util/List;)V", "getDataList", "setDataList", "getFormat", "()Lcom/samsung/android/sdk/scs/ai/language/WritingComposerFormat;", "setFormat", "(Lcom/samsung/android/sdk/scs/ai/language/WritingComposerFormat;)V", "getTone", "()Lcom/samsung/android/sdk/scs/ai/language/WritingComposerTone;", "getOutputSentenceCount", "()I", "setOutputSentenceCount", "(I)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WritingComposerMultiInputParams {
    private List<byte[]> dataList;
    private List<String> dataMimeTypeList;
    private WritingComposerFormat format;
    private final String inputText;
    private int outputSentenceCount;
    private final WritingComposerTone tone;

    public WritingComposerMultiInputParams(String str, List<String> list, List<byte[]> list2, WritingComposerFormat writingComposerFormat, WritingComposerTone writingComposerTone, int i2) {
        j.e(str, "inputText");
        j.e(list, "dataMimeTypeList");
        j.e(list2, "dataList");
        j.e(writingComposerFormat, "format");
        j.e(writingComposerTone, "tone");
        this.inputText = str;
        this.dataMimeTypeList = list;
        this.dataList = list2;
        this.format = writingComposerFormat;
        this.tone = writingComposerTone;
        this.outputSentenceCount = i2;
    }

    public static /* synthetic */ WritingComposerMultiInputParams copy$default(WritingComposerMultiInputParams writingComposerMultiInputParams, String str, List<String> list, List<byte[]> list2, WritingComposerFormat writingComposerFormat, WritingComposerTone writingComposerTone, int i2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            str = writingComposerMultiInputParams.inputText;
        }
        if ((i7 & 2) != 0) {
            list = writingComposerMultiInputParams.dataMimeTypeList;
        }
        if ((i7 & 4) != 0) {
            list2 = writingComposerMultiInputParams.dataList;
        }
        if ((i7 & 8) != 0) {
            writingComposerFormat = writingComposerMultiInputParams.format;
        }
        if ((i7 & 16) != 0) {
            writingComposerTone = writingComposerMultiInputParams.tone;
        }
        if ((i7 & 32) != 0) {
            i2 = writingComposerMultiInputParams.outputSentenceCount;
        }
        WritingComposerTone writingComposerTone2 = writingComposerTone;
        int i8 = i2;
        WritingComposerFormat writingComposerFormat2 = writingComposerFormat;
        List<String> list3 = list;
        return writingComposerMultiInputParams.copy(str, list3, list2, writingComposerFormat2, writingComposerTone2, i8);
    }

    public final String component1() {
        return this.inputText;
    }

    public final List<String> component2() {
        return this.dataMimeTypeList;
    }

    public final List<byte[]> component3() {
        return this.dataList;
    }

    public final WritingComposerFormat component4() {
        return this.format;
    }

    public final WritingComposerTone component5() {
        return this.tone;
    }

    public final int component6() {
        return this.outputSentenceCount;
    }

    public final WritingComposerMultiInputParams copy(String str, List<String> list, List<byte[]> list2, WritingComposerFormat writingComposerFormat, WritingComposerTone writingComposerTone, int i2) {
        j.e(str, "inputText");
        j.e(list, "dataMimeTypeList");
        j.e(list2, "dataList");
        j.e(writingComposerFormat, "format");
        j.e(writingComposerTone, "tone");
        return new WritingComposerMultiInputParams(str, list, list2, writingComposerFormat, writingComposerTone, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WritingComposerMultiInputParams)) {
            return false;
        }
        WritingComposerMultiInputParams writingComposerMultiInputParams = (WritingComposerMultiInputParams) obj;
        if (j.a(this.inputText, writingComposerMultiInputParams.inputText) && j.a(this.dataMimeTypeList, writingComposerMultiInputParams.dataMimeTypeList) && j.a(this.dataList, writingComposerMultiInputParams.dataList) && this.format == writingComposerMultiInputParams.format && this.tone == writingComposerMultiInputParams.tone && this.outputSentenceCount == writingComposerMultiInputParams.outputSentenceCount) {
            return true;
        }
        return false;
    }

    public final List<byte[]> getDataList() {
        return this.dataList;
    }

    public final List<String> getDataMimeTypeList() {
        return this.dataMimeTypeList;
    }

    public final WritingComposerFormat getFormat() {
        return this.format;
    }

    public final String getInputText() {
        return this.inputText;
    }

    public final int getOutputSentenceCount() {
        return this.outputSentenceCount;
    }

    public final WritingComposerTone getTone() {
        return this.tone;
    }

    public int hashCode() {
        int f = C0212a.f(this.dataList, C0212a.f(this.dataMimeTypeList, this.inputText.hashCode() * 31, 31), 31);
        int hashCode = this.tone.hashCode();
        return Integer.hashCode(this.outputSentenceCount) + ((hashCode + ((this.format.hashCode() + f) * 31)) * 31);
    }

    public final void setDataList(List<byte[]> list) {
        j.e(list, "<set-?>");
        this.dataList = list;
    }

    public final void setDataMimeTypeList(List<String> list) {
        j.e(list, "<set-?>");
        this.dataMimeTypeList = list;
    }

    public final void setFormat(WritingComposerFormat writingComposerFormat) {
        j.e(writingComposerFormat, "<set-?>");
        this.format = writingComposerFormat;
    }

    public final void setOutputSentenceCount(int i2) {
        this.outputSentenceCount = i2;
    }

    public String toString() {
        String str = this.inputText;
        List<String> list = this.dataMimeTypeList;
        List<byte[]> list2 = this.dataList;
        WritingComposerFormat writingComposerFormat = this.format;
        WritingComposerTone writingComposerTone = this.tone;
        int i2 = this.outputSentenceCount;
        return "WritingComposerMultiInputParams(inputText=" + str + ", dataMimeTypeList=" + list + ", dataList=" + list2 + ", format=" + writingComposerFormat + ", tone=" + writingComposerTone + ", outputSentenceCount=" + i2 + ")";
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ WritingComposerMultiInputParams(java.lang.String r1, java.util.List r2, java.util.List r3, com.samsung.android.sdk.scs.ai.language.WritingComposerFormat r4, com.samsung.android.sdk.scs.ai.language.WritingComposerTone r5, int r6, int r7, kotlin.jvm.internal.e r8) {
        /*
            r0 = this;
            r8 = r7 & 1
            if (r8 == 0) goto L_0x0006
            java.lang.String r1 = ""
        L_0x0006:
            r8 = r7 & 8
            if (r8 == 0) goto L_0x000c
            com.samsung.android.sdk.scs.ai.language.WritingComposerFormat r4 = com.samsung.android.sdk.scs.ai.language.WritingComposerFormat.WRITINGCOMPOSER_STANDARD
        L_0x000c:
            r8 = r7 & 16
            if (r8 == 0) goto L_0x0012
            com.samsung.android.sdk.scs.ai.language.WritingComposerTone r5 = com.samsung.android.sdk.scs.ai.language.WritingComposerTone.WRITINGCOMPOSER_PROFESSIONAL
        L_0x0012:
            r7 = r7 & 32
            if (r7 == 0) goto L_0x0017
            r6 = 1
        L_0x0017:
            r7 = r5
            r8 = r6
            r5 = r3
            r6 = r4
            r3 = r1
            r4 = r2
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.language.WritingComposerMultiInputParams.<init>(java.lang.String, java.util.List, java.util.List, com.samsung.android.sdk.scs.ai.language.WritingComposerFormat, com.samsung.android.sdk.scs.ai.language.WritingComposerTone, int, int, kotlin.jvm.internal.e):void");
    }
}
