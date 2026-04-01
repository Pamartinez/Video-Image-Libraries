package com.samsung.android.app.sdk.deepsky.textextraction.entity;

import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u000bR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0013\u001a\u0004\b\u0018\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityExtractionResult;", "", "", "id", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityItem;", "items", "languageTags", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Ljava/util/List;", "getItems", "()Ljava/util/List;", "getLanguageTags", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EntityExtractionResult {
    private final String id;
    private final List<EntityItem> items;
    private final String languageTags;

    public EntityExtractionResult(String str, List<EntityItem> list, String str2) {
        j.e(str, "id");
        j.e(list, "items");
        j.e(str2, "languageTags");
        this.id = str;
        this.items = list;
        this.languageTags = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EntityExtractionResult)) {
            return false;
        }
        EntityExtractionResult entityExtractionResult = (EntityExtractionResult) obj;
        if (j.a(this.id, entityExtractionResult.id) && j.a(this.items, entityExtractionResult.items) && j.a(this.languageTags, entityExtractionResult.languageTags)) {
            return true;
        }
        return false;
    }

    public final List<EntityItem> getItems() {
        return this.items;
    }

    public int hashCode() {
        return this.languageTags.hashCode() + C0212a.f(this.items, this.id.hashCode() * 31, 31);
    }

    public String toString() {
        String str = this.id;
        List<EntityItem> list = this.items;
        String str2 = this.languageTags;
        StringBuilder sb2 = new StringBuilder("EntityExtractionResult(id=");
        sb2.append(str);
        sb2.append(", items=");
        sb2.append(list);
        sb2.append(", languageTags=");
        return C0212a.p(sb2, str2, ")");
    }
}
