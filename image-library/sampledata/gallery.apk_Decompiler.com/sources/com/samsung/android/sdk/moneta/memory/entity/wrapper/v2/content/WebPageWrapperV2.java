package com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.WebPageBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.WebPage;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u001b\u0010\u0018R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/WebPageWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ContentWrapper;", "", "id", "title", "url", "searchKeyword", "pageCategory", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/content/WebPage;", "toContent", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/WebPage;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getTitle", "getUrl", "getSearchKeyword", "getPageCategory", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WebPageWrapperV2 extends ContentWrapper {
    public static final Parcelable.Creator<WebPageWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String id;
    private final String pageCategory;
    private final String searchKeyword;
    private final String title;
    private final String url;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/WebPageWrapperV2$Companion;", "", "<init>", "()V", "fromContent", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/WebPageWrapperV2;", "webPage", "Lcom/samsung/android/sdk/moneta/memory/entity/content/WebPage;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ WebPageWrapperV2 fromContent(WebPage webPage) {
            j.e(webPage, "webPage");
            return new WebPageWrapperV2(webPage.getId(), webPage.getTitle(), webPage.getUrl(), webPage.getSearchKeyword(), webPage.getPageCategory());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<WebPageWrapperV2> {
        public final WebPageWrapperV2 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new WebPageWrapperV2(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        public final WebPageWrapperV2[] newArray(int i2) {
            return new WebPageWrapperV2[i2];
        }
    }

    public WebPageWrapperV2(String str, String str2, String str3, String str4, String str5) {
        j.e(str, "id");
        j.e(str2, "title");
        j.e(str3, "url");
        j.e(str4, WebPageBundleWrapper.BUNDLE_KEY_SEARCH_KEYWORD);
        j.e(str5, WebPageBundleWrapper.BUNDLE_KEY_PAGE_CATEGORY);
        this.id = str;
        this.title = str2;
        this.url = str3;
        this.searchKeyword = str4;
        this.pageCategory = str5;
    }

    public final int describeContents() {
        return 0;
    }

    public final String getId() {
        return this.id;
    }

    public final String getPageCategory() {
        return this.pageCategory;
    }

    public final String getSearchKeyword() {
        return this.searchKeyword;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.url);
        parcel.writeString(this.searchKeyword);
        parcel.writeString(this.pageCategory);
    }
}
