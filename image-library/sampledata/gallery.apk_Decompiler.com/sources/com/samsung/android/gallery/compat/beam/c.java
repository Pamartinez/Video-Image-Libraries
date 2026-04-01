package com.samsung.android.gallery.compat.beam;

import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements NfcAdapter.OnNdefPushCompleteCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AndroidBeamProvider f2617a;

    public /* synthetic */ c(AndroidBeamProvider androidBeamProvider) {
        this.f2617a = androidBeamProvider;
    }

    public final void onNdefPushComplete(NfcEvent nfcEvent) {
        this.f2617a.lambda$registerCallback$2(nfcEvent);
    }
}
