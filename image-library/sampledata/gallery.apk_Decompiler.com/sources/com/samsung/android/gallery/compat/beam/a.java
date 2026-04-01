package com.samsung.android.gallery.compat.beam;

import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements NfcAdapter.CreateBeamUrisCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AndroidBeamProvider f2615a;
    public final /* synthetic */ Supplier b;

    public /* synthetic */ a(AndroidBeamProvider androidBeamProvider, Supplier supplier) {
        this.f2615a = androidBeamProvider;
        this.b = supplier;
    }

    public final Uri[] createBeamUris(NfcEvent nfcEvent) {
        return this.f2615a.lambda$registerCallback$0(this.b, nfcEvent);
    }
}
