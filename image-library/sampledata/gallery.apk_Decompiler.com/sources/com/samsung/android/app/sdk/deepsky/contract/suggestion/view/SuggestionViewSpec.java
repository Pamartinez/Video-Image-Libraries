package com.samsung.android.app.sdk.deepsky.contract.suggestion.view;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;
import i.C0212a;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0018\b\b\u0018\u0000 N2\u00020\u0001:\u0001NB\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0014\u0010\u0015B\u0011\b\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0014\u0010\u0018J\u001f\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001eJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b \u0010!J\u0012\u0010\"\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\"\u0010!J\u0012\u0010#\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b#\u0010!J\u0012\u0010$\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b$\u0010!J\u0012\u0010%\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b%\u0010!J\u0010\u0010&\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b&\u0010\u001eJ\u0018\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b'\u0010(J\u0012\u0010)\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b)\u0010*J\u0010\u0010+\u001a\u00020\fHÆ\u0003¢\u0006\u0004\b+\u0010*J\u0012\u0010,\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b,\u0010!J\u0012\u0010-\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0004\b-\u0010.J\u0012\u0010/\u001a\u0004\u0018\u00010\u0012HÆ\u0003¢\u0006\u0004\b/\u00100J¬\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÆ\u0001¢\u0006\u0004\b1\u00102J\u0010\u00104\u001a\u000203HÖ\u0001¢\u0006\u0004\b4\u00105J\u0010\u00106\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b6\u0010\u001eJ\u001a\u00109\u001a\u00020\u00102\b\u00108\u001a\u0004\u0018\u000107HÖ\u0003¢\u0006\u0004\b9\u0010:R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010;\u001a\u0004\b<\u0010\u001eR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010=\u001a\u0004\b>\u0010!R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010=\u001a\u0004\b?\u0010!R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010=\u001a\u0004\b@\u0010!R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010=\u001a\u0004\bA\u0010!R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010=\u001a\u0004\bB\u0010!R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010;\u001a\u0004\bC\u0010\u001eR\u001f\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010D\u001a\u0004\bE\u0010(R\u0019\u0010\r\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\r\u0010F\u001a\u0004\bG\u0010*R\u0017\u0010\u000e\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u000e\u0010F\u001a\u0004\bH\u0010*R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010=\u001a\u0004\bI\u0010!R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010J\u001a\u0004\bK\u0010.R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010L\u001a\u0004\bM\u00100¨\u0006O"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewSpec;", "Landroid/os/Parcelable;", "", "revision", "titleId", "descriptionId", "iconId", "suggestionFromId", "listViewId", "listViewItemId", "", "clickableIdList", "Landroid/widget/RemoteViews;", "listView", "listItemView", "maxListItemVisibleCount", "", "enableSwipeDismiss", "Landroid/os/Bundle;", "extras", "<init>", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;ILjava/util/List;Landroid/widget/RemoteViews;Landroid/widget/RemoteViews;Ljava/lang/Integer;Ljava/lang/Boolean;Landroid/os/Bundle;)V", "Landroid/os/Parcel;", "parcel", "(Landroid/os/Parcel;)V", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "component2", "()Ljava/lang/Integer;", "component3", "component4", "component5", "component6", "component7", "component8", "()Ljava/util/List;", "component9", "()Landroid/widget/RemoteViews;", "component10", "component11", "component12", "()Ljava/lang/Boolean;", "component13", "()Landroid/os/Bundle;", "copy", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;ILjava/util/List;Landroid/widget/RemoteViews;Landroid/widget/RemoteViews;Ljava/lang/Integer;Ljava/lang/Boolean;Landroid/os/Bundle;)Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewSpec;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "I", "getRevision", "Ljava/lang/Integer;", "getTitleId", "getDescriptionId", "getIconId", "getSuggestionFromId", "getListViewId", "getListViewItemId", "Ljava/util/List;", "getClickableIdList", "Landroid/widget/RemoteViews;", "getListView", "getListItemView", "getMaxListItemVisibleCount", "Ljava/lang/Boolean;", "getEnableSwipeDismiss", "Landroid/os/Bundle;", "getExtras", "CREATOR", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SuggestionViewSpec implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR((e) null);
    private final List<Integer> clickableIdList;
    private final Integer descriptionId;
    private final Boolean enableSwipeDismiss;
    private final Bundle extras;
    private final Integer iconId;
    private final RemoteViews listItemView;
    private final RemoteViews listView;
    private final Integer listViewId;
    private final int listViewItemId;
    private final Integer maxListItemVisibleCount;
    private final int revision;
    private final Integer suggestionFromId;
    private final Integer titleId;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u0004\u0018\u0001H\u000e\"\u0006\b\u0000\u0010\u000e\u0018\u0001*\u00020\u000fH\b¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewSpec$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewSpec;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/samsung/android/app/sdk/deepsky/contract/suggestion/view/SuggestionViewSpec;", "toListOrNull", "T", "Ljava/io/Serializable;", "(Ljava/io/Serializable;)Ljava/lang/Object;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CREATOR implements Parcelable.Creator<SuggestionViewSpec> {
        public /* synthetic */ CREATOR(e eVar) {
            this();
        }

        private final /* synthetic */ <T> T toListOrNull(Serializable serializable) {
            j.h();
            throw null;
        }

        private CREATOR() {
        }

        public SuggestionViewSpec createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new SuggestionViewSpec(parcel);
        }

        public SuggestionViewSpec[] newArray(int i2) {
            return new SuggestionViewSpec[i2];
        }
    }

    public SuggestionViewSpec(int i2, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, int i7, List<Integer> list, RemoteViews remoteViews, RemoteViews remoteViews2, Integer num6, Boolean bool, Bundle bundle) {
        j.e(remoteViews2, "listItemView");
        this.revision = i2;
        this.titleId = num;
        this.descriptionId = num2;
        this.iconId = num3;
        this.suggestionFromId = num4;
        this.listViewId = num5;
        this.listViewItemId = i7;
        this.clickableIdList = list;
        this.listView = remoteViews;
        this.listItemView = remoteViews2;
        this.maxListItemVisibleCount = num6;
        this.enableSwipeDismiss = bool;
        this.extras = bundle;
    }

    public static /* synthetic */ SuggestionViewSpec copy$default(SuggestionViewSpec suggestionViewSpec, int i2, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, int i7, List list, RemoteViews remoteViews, RemoteViews remoteViews2, Integer num6, Boolean bool, Bundle bundle, int i8, Object obj) {
        int i10 = i8;
        if ((i10 & 1) != 0) {
            i2 = suggestionViewSpec.revision;
        }
        return suggestionViewSpec.copy(i2, (i10 & 2) != 0 ? suggestionViewSpec.titleId : num, (i10 & 4) != 0 ? suggestionViewSpec.descriptionId : num2, (i10 & 8) != 0 ? suggestionViewSpec.iconId : num3, (i10 & 16) != 0 ? suggestionViewSpec.suggestionFromId : num4, (i10 & 32) != 0 ? suggestionViewSpec.listViewId : num5, (i10 & 64) != 0 ? suggestionViewSpec.listViewItemId : i7, (i10 & 128) != 0 ? suggestionViewSpec.clickableIdList : list, (i10 & 256) != 0 ? suggestionViewSpec.listView : remoteViews, (i10 & 512) != 0 ? suggestionViewSpec.listItemView : remoteViews2, (i10 & 1024) != 0 ? suggestionViewSpec.maxListItemVisibleCount : num6, (i10 & 2048) != 0 ? suggestionViewSpec.enableSwipeDismiss : bool, (i10 & 4096) != 0 ? suggestionViewSpec.extras : bundle);
    }

    public final int component1() {
        return this.revision;
    }

    public final RemoteViews component10() {
        return this.listItemView;
    }

    public final Integer component11() {
        return this.maxListItemVisibleCount;
    }

    public final Boolean component12() {
        return this.enableSwipeDismiss;
    }

    public final Bundle component13() {
        return this.extras;
    }

    public final Integer component2() {
        return this.titleId;
    }

    public final Integer component3() {
        return this.descriptionId;
    }

    public final Integer component4() {
        return this.iconId;
    }

    public final Integer component5() {
        return this.suggestionFromId;
    }

    public final Integer component6() {
        return this.listViewId;
    }

    public final int component7() {
        return this.listViewItemId;
    }

    public final List<Integer> component8() {
        return this.clickableIdList;
    }

    public final RemoteViews component9() {
        return this.listView;
    }

    public final SuggestionViewSpec copy(int i2, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, int i7, List<Integer> list, RemoteViews remoteViews, RemoteViews remoteViews2, Integer num6, Boolean bool, Bundle bundle) {
        RemoteViews remoteViews3 = remoteViews2;
        j.e(remoteViews3, "listItemView");
        return new SuggestionViewSpec(i2, num, num2, num3, num4, num5, i7, list, remoteViews, remoteViews3, num6, bool, bundle);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuggestionViewSpec)) {
            return false;
        }
        SuggestionViewSpec suggestionViewSpec = (SuggestionViewSpec) obj;
        if (this.revision == suggestionViewSpec.revision && j.a(this.titleId, suggestionViewSpec.titleId) && j.a(this.descriptionId, suggestionViewSpec.descriptionId) && j.a(this.iconId, suggestionViewSpec.iconId) && j.a(this.suggestionFromId, suggestionViewSpec.suggestionFromId) && j.a(this.listViewId, suggestionViewSpec.listViewId) && this.listViewItemId == suggestionViewSpec.listViewItemId && j.a(this.clickableIdList, suggestionViewSpec.clickableIdList) && j.a(this.listView, suggestionViewSpec.listView) && j.a(this.listItemView, suggestionViewSpec.listItemView) && j.a(this.maxListItemVisibleCount, suggestionViewSpec.maxListItemVisibleCount) && j.a(this.enableSwipeDismiss, suggestionViewSpec.enableSwipeDismiss) && j.a(this.extras, suggestionViewSpec.extras)) {
            return true;
        }
        return false;
    }

    public final List<Integer> getClickableIdList() {
        return this.clickableIdList;
    }

    public final Integer getDescriptionId() {
        return this.descriptionId;
    }

    public final Boolean getEnableSwipeDismiss() {
        return this.enableSwipeDismiss;
    }

    public final Bundle getExtras() {
        return this.extras;
    }

    public final Integer getIconId() {
        return this.iconId;
    }

    public final RemoteViews getListItemView() {
        return this.listItemView;
    }

    public final RemoteViews getListView() {
        return this.listView;
    }

    public final Integer getListViewId() {
        return this.listViewId;
    }

    public final int getListViewItemId() {
        return this.listViewItemId;
    }

    public final Integer getMaxListItemVisibleCount() {
        return this.maxListItemVisibleCount;
    }

    public final int getRevision() {
        return this.revision;
    }

    public final Integer getSuggestionFromId() {
        return this.suggestionFromId;
    }

    public final Integer getTitleId() {
        return this.titleId;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int hashCode = Integer.hashCode(this.revision) * 31;
        Integer num = this.titleId;
        int i16 = 0;
        if (num == null) {
            i2 = 0;
        } else {
            i2 = num.hashCode();
        }
        int i17 = (hashCode + i2) * 31;
        Integer num2 = this.descriptionId;
        if (num2 == null) {
            i7 = 0;
        } else {
            i7 = num2.hashCode();
        }
        int i18 = (i17 + i7) * 31;
        Integer num3 = this.iconId;
        if (num3 == null) {
            i8 = 0;
        } else {
            i8 = num3.hashCode();
        }
        int i19 = (i18 + i8) * 31;
        Integer num4 = this.suggestionFromId;
        if (num4 == null) {
            i10 = 0;
        } else {
            i10 = num4.hashCode();
        }
        int i20 = (i19 + i10) * 31;
        Integer num5 = this.listViewId;
        if (num5 == null) {
            i11 = 0;
        } else {
            i11 = num5.hashCode();
        }
        int b = C0212a.b(this.listViewItemId, (i20 + i11) * 31, 31);
        List<Integer> list = this.clickableIdList;
        if (list == null) {
            i12 = 0;
        } else {
            i12 = list.hashCode();
        }
        int i21 = (b + i12) * 31;
        RemoteViews remoteViews = this.listView;
        if (remoteViews == null) {
            i13 = 0;
        } else {
            i13 = remoteViews.hashCode();
        }
        int hashCode2 = (this.listItemView.hashCode() + ((i21 + i13) * 31)) * 31;
        Integer num6 = this.maxListItemVisibleCount;
        if (num6 == null) {
            i14 = 0;
        } else {
            i14 = num6.hashCode();
        }
        int i22 = (hashCode2 + i14) * 31;
        Boolean bool = this.enableSwipeDismiss;
        if (bool == null) {
            i15 = 0;
        } else {
            i15 = bool.hashCode();
        }
        int i23 = (i22 + i15) * 31;
        Bundle bundle = this.extras;
        if (bundle != null) {
            i16 = bundle.hashCode();
        }
        return i23 + i16;
    }

    public String toString() {
        int i2 = this.revision;
        Integer num = this.titleId;
        Integer num2 = this.descriptionId;
        Integer num3 = this.iconId;
        Integer num4 = this.suggestionFromId;
        Integer num5 = this.listViewId;
        int i7 = this.listViewItemId;
        List<Integer> list = this.clickableIdList;
        RemoteViews remoteViews = this.listView;
        RemoteViews remoteViews2 = this.listItemView;
        Integer num6 = this.maxListItemVisibleCount;
        Boolean bool = this.enableSwipeDismiss;
        Bundle bundle = this.extras;
        return "SuggestionViewSpec(revision=" + i2 + ", titleId=" + num + ", descriptionId=" + num2 + ", iconId=" + num3 + ", suggestionFromId=" + num4 + ", listViewId=" + num5 + ", listViewItemId=" + i7 + ", clickableIdList=" + list + ", listView=" + remoteViews + ", listItemView=" + remoteViews2 + ", maxListItemVisibleCount=" + num6 + ", enableSwipeDismiss=" + bool + ", extras=" + bundle + ")";
    }

    public void writeToParcel(Parcel parcel, int i2) {
        ArrayList arrayList;
        j.e(parcel, "parcel");
        parcel.writeInt(this.revision);
        parcel.writeValue(this.titleId);
        parcel.writeValue(this.descriptionId);
        parcel.writeValue(this.iconId);
        parcel.writeValue(this.suggestionFromId);
        parcel.writeValue(this.listViewId);
        parcel.writeInt(this.listViewItemId);
        if (this.clickableIdList != null) {
            arrayList = new ArrayList(this.clickableIdList);
        } else {
            arrayList = null;
        }
        parcel.writeSerializable(arrayList);
        parcel.writeParcelable(this.listView, i2);
        parcel.writeParcelable(this.listItemView, i2);
        parcel.writeValue(this.maxListItemVisibleCount);
        parcel.writeValue(this.enableSwipeDismiss);
        parcel.writeBundle(this.extras);
    }

    public /* synthetic */ SuggestionViewSpec(int i2, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, int i7, List list, RemoteViews remoteViews, RemoteViews remoteViews2, Integer num6, Boolean bool, Bundle bundle, int i8, e eVar) {
        Bundle bundle2;
        RemoteViews remoteViews3;
        int i10;
        SuggestionViewSpec suggestionViewSpec;
        int i11 = i8;
        int i12 = (i11 & 1) != 0 ? 1 : i2;
        Integer num7 = (i11 & 2) != 0 ? null : num;
        Integer num8 = (i11 & 4) != 0 ? null : num2;
        Integer num9 = (i11 & 8) != 0 ? null : num3;
        Integer num10 = (i11 & 16) != 0 ? null : num4;
        Integer num11 = (i11 & 32) != 0 ? null : num5;
        List list2 = (i11 & 128) != 0 ? null : list;
        RemoteViews remoteViews4 = (i11 & 256) != 0 ? null : remoteViews;
        Integer num12 = (i11 & 1024) != 0 ? null : num6;
        Boolean bool2 = (i11 & 2048) != 0 ? null : bool;
        if ((i11 & 4096) != 0) {
            bundle2 = null;
            i10 = i7;
            remoteViews3 = remoteViews2;
            suggestionViewSpec = this;
        } else {
            bundle2 = bundle;
            suggestionViewSpec = this;
            i10 = i7;
            remoteViews3 = remoteViews2;
        }
        new SuggestionViewSpec(i12, num7, num8, num9, num10, num11, i10, list2, remoteViews4, remoteViews3, num12, bool2, bundle2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Boolean} */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SuggestionViewSpec(android.os.Parcel r17) {
        /*
            r16 = this;
            r0 = r17
            java.lang.String r1 = "parcel"
            kotlin.jvm.internal.j.e(r0, r1)
            int r3 = r0.readInt()
            java.lang.Class r1 = java.lang.Integer.TYPE
            java.lang.ClassLoader r2 = r1.getClassLoader()
            java.lang.Object r2 = r0.readValue(r2)
            boolean r4 = r2 instanceof java.lang.Integer
            r5 = 0
            if (r4 == 0) goto L_0x001e
            java.lang.Integer r2 = (java.lang.Integer) r2
            r4 = r2
            goto L_0x001f
        L_0x001e:
            r4 = r5
        L_0x001f:
            java.lang.ClassLoader r2 = r1.getClassLoader()
            java.lang.Object r2 = r0.readValue(r2)
            boolean r6 = r2 instanceof java.lang.Integer
            if (r6 == 0) goto L_0x002e
            java.lang.Integer r2 = (java.lang.Integer) r2
            goto L_0x002f
        L_0x002e:
            r2 = r5
        L_0x002f:
            java.lang.ClassLoader r6 = r1.getClassLoader()
            java.lang.Object r6 = r0.readValue(r6)
            boolean r7 = r6 instanceof java.lang.Integer
            if (r7 == 0) goto L_0x003e
            java.lang.Integer r6 = (java.lang.Integer) r6
            goto L_0x003f
        L_0x003e:
            r6 = r5
        L_0x003f:
            java.lang.ClassLoader r7 = r1.getClassLoader()
            java.lang.Object r7 = r0.readValue(r7)
            boolean r8 = r7 instanceof java.lang.Integer
            if (r8 == 0) goto L_0x004e
            java.lang.Integer r7 = (java.lang.Integer) r7
            goto L_0x004f
        L_0x004e:
            r7 = r5
        L_0x004f:
            java.lang.ClassLoader r8 = r1.getClassLoader()
            java.lang.Object r8 = r0.readValue(r8)
            boolean r9 = r8 instanceof java.lang.Integer
            if (r9 == 0) goto L_0x005e
            java.lang.Integer r8 = (java.lang.Integer) r8
            goto L_0x005f
        L_0x005e:
            r8 = r5
        L_0x005f:
            int r9 = r0.readInt()
            java.io.Serializable r10 = r0.readSerializable()
            if (r10 == 0) goto L_0x006c
            java.util.List r10 = (java.util.List) r10
            goto L_0x006d
        L_0x006c:
            r10 = r5
        L_0x006d:
            java.lang.Class<android.widget.RemoteViews> r11 = android.widget.RemoteViews.class
            java.lang.ClassLoader r12 = r11.getClassLoader()
            android.os.Parcelable r12 = r0.readParcelable(r12)
            android.widget.RemoteViews r12 = (android.widget.RemoteViews) r12
            java.lang.ClassLoader r11 = r11.getClassLoader()
            android.os.Parcelable r11 = r0.readParcelable(r11)
            if (r11 == 0) goto L_0x00bc
            android.widget.RemoteViews r11 = (android.widget.RemoteViews) r11
            java.lang.ClassLoader r1 = r1.getClassLoader()
            java.lang.Object r1 = r0.readValue(r1)
            boolean r13 = r1 instanceof java.lang.Integer
            if (r13 == 0) goto L_0x0095
            java.lang.Integer r1 = (java.lang.Integer) r1
            r13 = r1
            goto L_0x0096
        L_0x0095:
            r13 = r5
        L_0x0096:
            java.lang.Class r1 = java.lang.Boolean.TYPE
            java.lang.ClassLoader r1 = r1.getClassLoader()
            java.lang.Object r1 = r0.readValue(r1)
            boolean r14 = r1 instanceof java.lang.Boolean
            if (r14 == 0) goto L_0x00a7
            r5 = r1
            java.lang.Boolean r5 = (java.lang.Boolean) r5
        L_0x00a7:
            r14 = r5
            java.lang.Class<android.os.Bundle> r1 = android.os.Bundle.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            android.os.Bundle r15 = r0.readBundle(r1)
            r5 = r12
            r12 = r11
            r11 = r5
            r5 = r2
            r2 = r16
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return
        L_0x00bc:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "listItemView is null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.contract.suggestion.view.SuggestionViewSpec.<init>(android.os.Parcel):void");
    }
}
