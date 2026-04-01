package com.samsung.android.sdk.moneta.memory.entity.content;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0014J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J4\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u0014J\u0010\u0010\u001b\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\u001b\u0010\u0012J\u001a\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cHÖ\u0003¢\u0006\u0004\b\u001f\u0010 R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010\u0014R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010!\u001a\u0004\b#\u0010\u0014R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b%\u0010\u0017¨\u0006&"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/Keyword;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "", "id", "keyword", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Category;", "category", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "()Ljava/util/List;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Lcom/samsung/android/sdk/moneta/memory/entity/content/Keyword;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getKeyword", "Ljava/util/List;", "getCategory", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Keyword extends Content {
    public static final Parcelable.Creator<Keyword> CREATOR = new Creator();
    private final List<Category> category;
    private final String id;
    private final String keyword;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Keyword> {
        public final Keyword createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            for (int i2 = 0; i2 != readInt; i2++) {
                arrayList.add(Category.valueOf(parcel.readString()));
            }
            return new Keyword(readString, readString2, arrayList);
        }

        public final Keyword[] newArray(int i2) {
            return new Keyword[i2];
        }
    }

    public Keyword(String str, String str2, List<? extends Category> list) {
        j.e(str, "id");
        j.e(str2, "keyword");
        j.e(list, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
        this.id = str;
        this.keyword = str2;
        this.category = list;
    }

    public static /* synthetic */ Keyword copy$default(Keyword keyword2, String str, String str2, List<Category> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = keyword2.id;
        }
        if ((i2 & 2) != 0) {
            str2 = keyword2.keyword;
        }
        if ((i2 & 4) != 0) {
            list = keyword2.category;
        }
        return keyword2.copy(str, str2, list);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.keyword;
    }

    public final List<Category> component3() {
        return this.category;
    }

    public final Keyword copy(String str, String str2, List<? extends Category> list) {
        j.e(str, "id");
        j.e(str2, "keyword");
        j.e(list, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
        return new Keyword(str, str2, list);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Keyword)) {
            return false;
        }
        Keyword keyword2 = (Keyword) obj;
        if (j.a(this.id, keyword2.id) && j.a(this.keyword, keyword2.keyword) && j.a(this.category, keyword2.category)) {
            return true;
        }
        return false;
    }

    public final List<Category> getCategory() {
        return this.category;
    }

    public String getId() {
        return this.id;
    }

    public final String getKeyword() {
        return this.keyword;
    }

    public int hashCode() {
        return this.category.hashCode() + C0212a.d(this.id.hashCode() * 31, 31, this.keyword);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Keyword(id=");
        sb2.append(this.id);
        sb2.append(", keyword=");
        sb2.append(this.keyword);
        sb2.append(", category=");
        return C0212a.r(sb2, this.category, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.keyword);
        Iterator j2 = a.j(parcel, this.category);
        while (j2.hasNext()) {
            parcel.writeString(((Category) j2.next()).name());
        }
    }
}
