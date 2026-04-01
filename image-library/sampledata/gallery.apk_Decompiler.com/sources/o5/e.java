package O5;

import android.accounts.Account;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;
import com.samsung.android.gallery.module.account.SamsungAccountManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements SamsungAccountManager.OnAccountUpdatedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SharingPicturesFragment f2418a;

    public /* synthetic */ e(SharingPicturesFragment sharingPicturesFragment) {
        this.f2418a = sharingPicturesFragment;
    }

    public final void onSyncAccountsUpdated(Account account, boolean z) {
        this.f2418a.lambda$new$0(account, z);
    }
}
