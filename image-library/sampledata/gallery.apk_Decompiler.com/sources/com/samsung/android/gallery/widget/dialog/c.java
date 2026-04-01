package com.samsung.android.gallery.widget.dialog;

import android.content.DialogInterface;
import android.view.Window;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements DialogInterface.OnShowListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Window f3202a;

    public /* synthetic */ c(Window window) {
        this.f3202a = window;
    }

    public final void onShow(DialogInterface dialogInterface) {
        OverlayTipBuilder.lambda$create$0(this.f3202a, dialogInterface);
    }
}
