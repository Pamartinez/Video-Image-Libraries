package com.samsung.android.gallery.compat.beam;

import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements NfcAdapter.CreateNdefMessageCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AndroidBeamProvider f2616a;

    public /* synthetic */ b(AndroidBeamProvider androidBeamProvider) {
        this.f2616a = androidBeamProvider;
    }

    public final NdefMessage createNdefMessage(NfcEvent nfcEvent) {
        return this.f2616a.lambda$registerCallback$1(nfcEvent);
    }
}
