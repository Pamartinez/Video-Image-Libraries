package com.samsung.android.sdk.scs.ai.language;

import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J=\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013¨\u0006%"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/language/ExtractionMultiInputParams;", "", "inputText", "", "dataList", "", "", "category", "Lcom/samsung/android/sdk/scs/ai/language/ExtractionCategory;", "dataMimeTypeList", "<init>", "(Ljava/lang/String;Ljava/util/List;Lcom/samsung/android/sdk/scs/ai/language/ExtractionCategory;Ljava/util/List;)V", "getInputText", "()Ljava/lang/String;", "setInputText", "(Ljava/lang/String;)V", "getDataList", "()Ljava/util/List;", "setDataList", "(Ljava/util/List;)V", "getCategory", "()Lcom/samsung/android/sdk/scs/ai/language/ExtractionCategory;", "setCategory", "(Lcom/samsung/android/sdk/scs/ai/language/ExtractionCategory;)V", "getDataMimeTypeList", "setDataMimeTypeList", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExtractionMultiInputParams {
    private ExtractionCategory category;
    private List<byte[]> dataList;
    private List<String> dataMimeTypeList;
    private String inputText;

    public ExtractionMultiInputParams(String str, List<byte[]> list, ExtractionCategory extractionCategory, List<String> list2) {
        j.e(str, "inputText");
        j.e(list, "dataList");
        j.e(extractionCategory, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
        j.e(list2, "dataMimeTypeList");
        this.inputText = str;
        this.dataList = list;
        this.category = extractionCategory;
        this.dataMimeTypeList = list2;
    }

    public static /* synthetic */ ExtractionMultiInputParams copy$default(ExtractionMultiInputParams extractionMultiInputParams, String str, List<byte[]> list, ExtractionCategory extractionCategory, List<String> list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = extractionMultiInputParams.inputText;
        }
        if ((i2 & 2) != 0) {
            list = extractionMultiInputParams.dataList;
        }
        if ((i2 & 4) != 0) {
            extractionCategory = extractionMultiInputParams.category;
        }
        if ((i2 & 8) != 0) {
            list2 = extractionMultiInputParams.dataMimeTypeList;
        }
        return extractionMultiInputParams.copy(str, list, extractionCategory, list2);
    }

    public final String component1() {
        return this.inputText;
    }

    public final List<byte[]> component2() {
        return this.dataList;
    }

    public final ExtractionCategory component3() {
        return this.category;
    }

    public final List<String> component4() {
        return this.dataMimeTypeList;
    }

    public final ExtractionMultiInputParams copy(String str, List<byte[]> list, ExtractionCategory extractionCategory, List<String> list2) {
        j.e(str, "inputText");
        j.e(list, "dataList");
        j.e(extractionCategory, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
        j.e(list2, "dataMimeTypeList");
        return new ExtractionMultiInputParams(str, list, extractionCategory, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExtractionMultiInputParams)) {
            return false;
        }
        ExtractionMultiInputParams extractionMultiInputParams = (ExtractionMultiInputParams) obj;
        if (j.a(this.inputText, extractionMultiInputParams.inputText) && j.a(this.dataList, extractionMultiInputParams.dataList) && this.category == extractionMultiInputParams.category && j.a(this.dataMimeTypeList, extractionMultiInputParams.dataMimeTypeList)) {
            return true;
        }
        return false;
    }

    public final ExtractionCategory getCategory() {
        return this.category;
    }

    public final List<byte[]> getDataList() {
        return this.dataList;
    }

    public final List<String> getDataMimeTypeList() {
        return this.dataMimeTypeList;
    }

    public final String getInputText() {
        return this.inputText;
    }

    public int hashCode() {
        int f = C0212a.f(this.dataList, this.inputText.hashCode() * 31, 31);
        return this.dataMimeTypeList.hashCode() + ((this.category.hashCode() + f) * 31);
    }

    public final void setCategory(ExtractionCategory extractionCategory) {
        j.e(extractionCategory, "<set-?>");
        this.category = extractionCategory;
    }

    public final void setDataList(List<byte[]> list) {
        j.e(list, "<set-?>");
        this.dataList = list;
    }

    public final void setDataMimeTypeList(List<String> list) {
        j.e(list, "<set-?>");
        this.dataMimeTypeList = list;
    }

    public final void setInputText(String str) {
        j.e(str, "<set-?>");
        this.inputText = str;
    }

    public String toString() {
        String str = this.inputText;
        List<byte[]> list = this.dataList;
        ExtractionCategory extractionCategory = this.category;
        List<String> list2 = this.dataMimeTypeList;
        return "ExtractionMultiInputParams(inputText=" + str + ", dataList=" + list + ", category=" + extractionCategory + ", dataMimeTypeList=" + list2 + ")";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ExtractionMultiInputParams(String str, List list, ExtractionCategory extractionCategory, List list2, int i2, e eVar) {
        this((i2 & 1) != 0 ? "" : str, list, extractionCategory, list2);
    }
}
