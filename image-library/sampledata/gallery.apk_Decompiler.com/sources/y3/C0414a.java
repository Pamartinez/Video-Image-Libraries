package Y3;

import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.app.receiver.SharedAlbumNotificationReceiver;

/* renamed from: Y3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0414a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharedAlbumNotificationReceiver e;
    public final /* synthetic */ Context f;
    public final /* synthetic */ Intent g;

    public /* synthetic */ C0414a(SharedAlbumNotificationReceiver sharedAlbumNotificationReceiver, Context context, Intent intent, int i2) {
        this.d = i2;
        this.e = sharedAlbumNotificationReceiver;
        this.f = context;
        this.g = intent;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$handleNewAlbumPush$5(this.f, this.g);
                return;
            case 1:
                this.e.lambda$handleGroupDelegateAuthorityPush$7(this.f, this.g);
                return;
            case 2:
                this.e.lambda$handleGroupMemberRemovedPush$0(this.f, this.g);
                return;
            case 3:
                this.e.lambda$handleNewPostPush$3(this.f, this.g);
                return;
            case 4:
                this.e.lambda$handleGroupMemberJoinedPush$2(this.f, this.g);
                return;
            default:
                this.e.lambda$handleGroupMemberLeftPush$1(this.f, this.g);
                return;
        }
    }
}
