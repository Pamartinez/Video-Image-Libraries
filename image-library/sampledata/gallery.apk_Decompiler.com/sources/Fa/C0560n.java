package Fa;

import android.content.Context;
import android.content.DialogInterface;
import com.samsung.android.gallery.settings.ui.LabsAlbumBnRFragment;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Fa.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0560n implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ AtomicInteger f;
    public final /* synthetic */ Context g;

    public /* synthetic */ C0560n(ArrayList arrayList, AtomicInteger atomicInteger, Context context, int i2) {
        this.d = i2;
        this.e = arrayList;
        this.f = atomicInteger;
        this.g = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        switch (this.d) {
            case 0:
                LabsAlbumBnRFragment.lambda$onRestoreAlbumDbClicked$6(this.e, this.f, this.g, dialogInterface, i2);
                return;
            default:
                LabsAlbumBnRFragment.lambda$onRestoreAlbumDbClicked$7(this.e, this.f, this.g, dialogInterface, i2);
                return;
        }
    }
}
