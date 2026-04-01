package Fa;

import android.content.Context;
import android.content.DialogInterface;
import com.samsung.android.gallery.settings.ui.LabsAlbumBnRFragment;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Fa.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0559m implements DialogInterface.OnClickListener {
    public final /* synthetic */ LabsAlbumBnRFragment d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ Context f;
    public final /* synthetic */ ArrayList g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ AtomicInteger f2810h;

    public /* synthetic */ C0559m(LabsAlbumBnRFragment labsAlbumBnRFragment, ArrayList arrayList, Context context, ArrayList arrayList2, AtomicInteger atomicInteger) {
        this.d = labsAlbumBnRFragment;
        this.e = arrayList;
        this.f = context;
        this.g = arrayList2;
        this.f2810h = atomicInteger;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        this.d.lambda$onRestoreAlbumDbClicked$5(this.e, this.f, this.g, this.f2810h, dialogInterface, i2);
    }
}
