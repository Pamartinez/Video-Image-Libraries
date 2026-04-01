package com.samsung.android.gallery.compat.beam;

import android.app.Activity;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.util.Log;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AndroidBeamProvider {
    static final int STATUS_NONE = 0;
    static final int STATUS_PUSH = 1;
    private static final String TAG = "AndroidBeamProvider";
    private int mNdefStatus = 0;

    /* access modifiers changed from: private */
    public /* synthetic */ Uri[] lambda$registerCallback$0(Supplier supplier, NfcEvent nfcEvent) {
        if (getNdefState() == 1) {
            return (Uri[]) supplier.get();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ NdefMessage lambda$registerCallback$1(NfcEvent nfcEvent) {
        setNdefState(1);
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerCallback$2(NfcEvent nfcEvent) {
        setNdefState(0);
    }

    public int getNdefState() {
        return this.mNdefStatus;
    }

    public void registerCallback(NfcAdapter nfcAdapter, Activity activity, Supplier<Uri[]> supplier) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            nfcAdapter.setBeamPushUrisCallback(new a(this, supplier), activity);
            nfcAdapter.setNdefPushMessageCallback(new b(this), activity, new Activity[0]);
            nfcAdapter.setOnNdefPushCompleteCallback(new c(this), activity, new Activity[0]);
            Log.d(TAG, "registerCallback +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (IllegalStateException | NoClassDefFoundError | NoSuchMethodError e) {
            Log.e(TAG, "registerCallback failed e=" + e.toString());
        }
    }

    public void setNdefState(int i2) {
        this.mNdefStatus = i2;
    }
}
