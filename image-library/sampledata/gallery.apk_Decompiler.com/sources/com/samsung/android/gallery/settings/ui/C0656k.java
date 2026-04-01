package com.samsung.android.gallery.settings.ui;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.LabsBaseFragment;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.settings.ui.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0656k implements Consumer {
    public final /* synthetic */ int d = 1;
    public final /* synthetic */ String e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ C0656k(LabsBaseFragment.MultiChoiceDialogBuilder multiChoiceDialogBuilder, GalleryPreference galleryPreference, String str) {
        this.f = multiChoiceDialogBuilder;
        this.g = galleryPreference;
        this.e = str;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                new AlertDialog.Builder((Context) obj).setTitle((CharSequence) this.e).setMessage((CharSequence) (String) this.f).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new C0654i(0, (Runnable) this.g)).create().show();
                return;
            default:
                ((LabsBaseFragment.MultiChoiceDialogBuilder) this.f).lambda$new$0((GalleryPreference) this.g, this.e, (String) obj);
                return;
        }
    }

    public /* synthetic */ C0656k(String str, String str2, Runnable runnable) {
        this.e = str;
        this.f = str2;
        this.g = runnable;
    }
}
