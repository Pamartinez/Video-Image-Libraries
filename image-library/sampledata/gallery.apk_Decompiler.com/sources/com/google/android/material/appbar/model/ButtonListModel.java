package com.google.android.material.appbar.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/google/android/material/appbar/model/ButtonListModel;", "", "Lcom/google/android/material/appbar/model/ButtonStyle;", "buttonStyle", "", "Lcom/google/android/material/appbar/model/ButtonModel;", "buttonModels", "<init>", "(Lcom/google/android/material/appbar/model/ButtonStyle;Ljava/util/List;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ButtonListModel {

    /* renamed from: a  reason: collision with root package name */
    public final ButtonStyle f1395a;
    public final List b;

    public ButtonListModel(ButtonStyle buttonStyle, List<? extends ButtonModel> list) {
        j.e(buttonStyle, "buttonStyle");
        j.e(list, "buttonModels");
        this.f1395a = buttonStyle;
        this.b = list;
    }
}
