package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import A.a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001d\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J;\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010!\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0003HÖ\u0001J\t\u0010$\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\t\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0017\"\u0004\b\u001a\u0010\u0019¨\u0006%"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuItem;", "", "id", "", "order", "title", "", "useDefaultOption", "", "isEnabled", "<init>", "(IILjava/lang/String;ZZ)V", "getId", "()I", "setId", "(I)V", "getOrder", "setOrder", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "getUseDefaultOption", "()Z", "setUseDefaultOption", "(Z)V", "setEnabled", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ToolbarMenuItem {
    private int id;
    private boolean isEnabled;
    private int order;
    private String title;
    private boolean useDefaultOption;

    public ToolbarMenuItem(int i2, int i7, String str, boolean z, boolean z3) {
        j.e(str, "title");
        this.id = i2;
        this.order = i7;
        this.title = str;
        this.useDefaultOption = z;
        this.isEnabled = z3;
    }

    public static /* synthetic */ ToolbarMenuItem copy$default(ToolbarMenuItem toolbarMenuItem, int i2, int i7, String str, boolean z, boolean z3, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i2 = toolbarMenuItem.id;
        }
        if ((i8 & 2) != 0) {
            i7 = toolbarMenuItem.order;
        }
        if ((i8 & 4) != 0) {
            str = toolbarMenuItem.title;
        }
        if ((i8 & 8) != 0) {
            z = toolbarMenuItem.useDefaultOption;
        }
        if ((i8 & 16) != 0) {
            z3 = toolbarMenuItem.isEnabled;
        }
        boolean z7 = z;
        boolean z9 = z3;
        return toolbarMenuItem.copy(i2, i7, str, z7, z9);
    }

    public final int component1() {
        return this.id;
    }

    public final int component2() {
        return this.order;
    }

    public final String component3() {
        return this.title;
    }

    public final boolean component4() {
        return this.useDefaultOption;
    }

    public final boolean component5() {
        return this.isEnabled;
    }

    public final ToolbarMenuItem copy(int i2, int i7, String str, boolean z, boolean z3) {
        j.e(str, "title");
        return new ToolbarMenuItem(i2, i7, str, z, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ToolbarMenuItem)) {
            return false;
        }
        ToolbarMenuItem toolbarMenuItem = (ToolbarMenuItem) obj;
        if (this.id == toolbarMenuItem.id && this.order == toolbarMenuItem.order && j.a(this.title, toolbarMenuItem.title) && this.useDefaultOption == toolbarMenuItem.useDefaultOption && this.isEnabled == toolbarMenuItem.isEnabled) {
            return true;
        }
        return false;
    }

    public final int getId() {
        return this.id;
    }

    public final int getOrder() {
        return this.order;
    }

    public final String getTitle() {
        return this.title;
    }

    public final boolean getUseDefaultOption() {
        return this.useDefaultOption;
    }

    public int hashCode() {
        return Boolean.hashCode(this.isEnabled) + C0212a.e(C0212a.d(C0212a.b(this.order, Integer.hashCode(this.id) * 31, 31), 31, this.title), 31, this.useDefaultOption);
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public final void setId(int i2) {
        this.id = i2;
    }

    public final void setOrder(int i2) {
        this.order = i2;
    }

    public final void setTitle(String str) {
        j.e(str, "<set-?>");
        this.title = str;
    }

    public final void setUseDefaultOption(boolean z) {
        this.useDefaultOption = z;
    }

    public String toString() {
        int i2 = this.id;
        int i7 = this.order;
        String str = this.title;
        boolean z = this.useDefaultOption;
        boolean z3 = this.isEnabled;
        StringBuilder h5 = a.h(i2, i7, "ToolbarMenuItem(id=", ", order=", ", title=");
        h5.append(str);
        h5.append(", useDefaultOption=");
        h5.append(z);
        h5.append(", isEnabled=");
        return N2.j.h(h5, z3, ")");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ToolbarMenuItem(int i2, int i7, String str, boolean z, boolean z3, int i8, e eVar) {
        this(i2, i7, str, z, (i8 & 16) != 0 ? true : z3);
    }
}
