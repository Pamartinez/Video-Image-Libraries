package com.samsung.android.gallery.app.ui.dialog;

import com.samsung.android.gallery.app.ui.dialog.CreateNewDialogV2;
import com.samsung.android.gallery.app.ui.dialog.CustomChooserDialog;
import java.util.function.IntFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements IntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2523a;

    public /* synthetic */ a(int i2) {
        this.f2523a = i2;
    }

    public final Object apply(int i2) {
        switch (this.f2523a) {
            case 0:
                return CreateNewDialogV2.CreateNewListAdapter.lambda$onBindViewHolder$0(i2);
            default:
                return CustomChooserDialog.BaseAdapter.lambda$onBindViewHolder$0(i2);
        }
    }
}
