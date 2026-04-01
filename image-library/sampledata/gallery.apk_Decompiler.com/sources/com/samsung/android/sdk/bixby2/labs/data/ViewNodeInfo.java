package com.samsung.android.sdk.bixby2.labs.data;

import android.graphics.Rect;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0017\b\u0001\u0018\u00002\u00020\u0001B\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00000\u0016¢\u0006\u0002\u0010\u0017R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u0014\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00000\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001bR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001bR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\"R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001bR\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001bR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\"R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001e¨\u0006-"}, d2 = {"Lcom/samsung/android/sdk/bixby2/labs/data/ViewNodeInfo;", "", "checkable", "", "checked", "className", "", "clickable", "contentDescription", "boundsInScreen", "Landroid/graphics/Rect;", "enabled", "focusable", "focused", "hintText", "longClickable", "selected", "text", "visibility", "", "childCount", "children", "", "(ZZLjava/lang/String;ZLjava/lang/String;Landroid/graphics/Rect;ZZZLjava/lang/String;ZZLjava/lang/String;IILjava/util/List;)V", "getBoundsInScreen", "()Landroid/graphics/Rect;", "getCheckable", "()Z", "getChecked", "getChildCount", "()I", "getChildren", "()Ljava/util/List;", "getClassName", "()Ljava/lang/String;", "getClickable", "getContentDescription", "getEnabled", "getFocusable", "getFocused", "getHintText", "getLongClickable", "getSelected", "getText", "getVisibility", "bixbyappsdk_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ViewNodeInfo {
    private final Rect boundsInScreen;
    private final boolean checkable;
    private final boolean checked;
    private final int childCount;
    private final List<ViewNodeInfo> children;
    private final String className;
    private final boolean clickable;
    private final String contentDescription;
    private final boolean enabled;
    private final boolean focusable;
    private final boolean focused;
    private final String hintText;
    private final boolean longClickable;
    private final boolean selected;
    private final String text;
    private final int visibility;

    public ViewNodeInfo(boolean z, boolean z3, String str, boolean z7, String str2, Rect rect, boolean z9, boolean z10, boolean z11, String str3, boolean z12, boolean z13, String str4, int i2, int i7, List<ViewNodeInfo> list) {
        List<ViewNodeInfo> list2 = list;
        j.e(str, "className");
        j.e(rect, "boundsInScreen");
        j.e(list2, "children");
        this.checkable = z;
        this.checked = z3;
        this.className = str;
        this.clickable = z7;
        this.contentDescription = str2;
        this.boundsInScreen = rect;
        this.enabled = z9;
        this.focusable = z10;
        this.focused = z11;
        this.hintText = str3;
        this.longClickable = z12;
        this.selected = z13;
        this.text = str4;
        this.visibility = i2;
        this.childCount = i7;
        this.children = list2;
    }

    public final Rect getBoundsInScreen() {
        return this.boundsInScreen;
    }

    public final boolean getCheckable() {
        return this.checkable;
    }

    public final boolean getChecked() {
        return this.checked;
    }

    public final int getChildCount() {
        return this.childCount;
    }

    public final List<ViewNodeInfo> getChildren() {
        return this.children;
    }

    public final String getClassName() {
        return this.className;
    }

    public final boolean getClickable() {
        return this.clickable;
    }

    public final String getContentDescription() {
        return this.contentDescription;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final boolean getFocusable() {
        return this.focusable;
    }

    public final boolean getFocused() {
        return this.focused;
    }

    public final String getHintText() {
        return this.hintText;
    }

    public final boolean getLongClickable() {
        return this.longClickable;
    }

    public final boolean getSelected() {
        return this.selected;
    }

    public final String getText() {
        return this.text;
    }

    public final int getVisibility() {
        return this.visibility;
    }
}
