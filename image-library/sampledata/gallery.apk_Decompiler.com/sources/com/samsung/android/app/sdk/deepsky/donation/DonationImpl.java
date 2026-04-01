package com.samsung.android.app.sdk.deepsky.donation;

import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.gson.Gson;
import com.samsung.android.app.sdk.deepsky.common.ContentProviderCaller;
import com.samsung.android.app.sdk.deepsky.donation.schema.DonationContract;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001b2\u00020\u00012\u00020\u0002:\u0001\u001bB\u0011\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002XD¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/donation/DonationImpl;", "Lcom/samsung/android/app/sdk/deepsky/donation/Donation;", "", "Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;", "contentServiceCaller", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;)V", "Lcom/samsung/android/app/sdk/deepsky/donation/ActionDonation;", "actionDonation", "Landroid/os/Bundle;", "sendDonatedActionInfo", "(Lcom/samsung/android/app/sdk/deepsky/donation/ActionDonation;)Landroid/os/Bundle;", "", "getDonatedActionStatus", "(Lcom/samsung/android/app/sdk/deepsky/donation/ActionDonation;)Ljava/lang/Boolean;", "", "donateForResult", "(Lcom/samsung/android/app/sdk/deepsky/donation/ActionDonation;)I", "checkIfAccessAllowed", "()Z", "Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;", "", "TAG", "Ljava/lang/String;", "Ljava/util/concurrent/ExecutorService;", "mExecutorService", "Ljava/util/concurrent/ExecutorService;", "Companion", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DonationImpl implements Donation {
    public static final Companion Companion = new Companion((e) null);
    private final String TAG = "DonationImpl";
    private final ContentProviderCaller contentServiceCaller;
    private final ExecutorService mExecutorService;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/donation/DonationImpl$Companion;", "", "<init>", "()V", "KEY_DONATION_JSON", "", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public DonationImpl(ContentProviderCaller contentProviderCaller) {
        j.e(contentProviderCaller, "contentServiceCaller");
        this.contentServiceCaller = contentProviderCaller;
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        j.d(newSingleThreadExecutor, "newSingleThreadExecutor(...)");
        this.mExecutorService = newSingleThreadExecutor;
    }

    private final Boolean getDonatedActionStatus(ActionDonation actionDonation) {
        Bundle sendDonatedActionInfo = sendDonatedActionInfo(actionDonation);
        if (sendDonatedActionInfo != null) {
            return Boolean.valueOf(sendDonatedActionInfo.getBoolean("db_insert_result", false));
        }
        return null;
    }

    private final Bundle sendDonatedActionInfo(ActionDonation actionDonation) {
        String json = new Gson().toJson((Object) actionDonation);
        ContentProviderCaller contentProviderCaller = this.contentServiceCaller;
        Uri content_uri = DonationContract.INSTANCE.getCONTENT_URI();
        j.d(content_uri, "<get-CONTENT_URI>(...)");
        Bundle bundle = new Bundle();
        bundle.putString("key_donation_json", json);
        return contentProviderCaller.sendCommand(content_uri, "donate_user_action", bundle);
    }

    public boolean checkIfAccessAllowed() {
        return true;
    }

    public int donateForResult(ActionDonation actionDonation) {
        j.e(actionDonation, "actionDonation");
        if (j.a(Looper.myLooper(), Looper.getMainLooper())) {
            Log.e(this.TAG, "This method cannot be called in main thread");
            return 1;
        } else if (j.a(getDonatedActionStatus(actionDonation), Boolean.TRUE)) {
            return 0;
        } else {
            return 2;
        }
    }
}
