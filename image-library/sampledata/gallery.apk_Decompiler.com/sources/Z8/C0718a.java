package z8;

import android.content.SyncStatusObserver;
import com.samsung.android.gallery.module.account.SamsungAccountManager;

/* renamed from: z8.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0718a implements SyncStatusObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SamsungAccountManager f3302a;

    public /* synthetic */ C0718a(SamsungAccountManager samsungAccountManager) {
        this.f3302a = samsungAccountManager;
    }

    public final void onStatusChanged(int i2) {
        this.f3302a.notifyChange(i2);
    }
}
