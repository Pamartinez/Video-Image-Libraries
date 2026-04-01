package L5;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsInvitationViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingsInvitationViewHolder e;

    public /* synthetic */ c(SharingsInvitationViewHolder sharingsInvitationViewHolder, int i2) {
        this.d = i2;
        this.e = sharingsInvitationViewHolder;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        SharingsInvitationViewHolder sharingsInvitationViewHolder = this.e;
        switch (i2) {
            case 0:
                sharingsInvitationViewHolder.lambda$new$0(view);
                return;
            default:
                sharingsInvitationViewHolder.lambda$new$1(view);
                return;
        }
    }
}
