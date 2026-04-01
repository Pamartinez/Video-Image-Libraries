package com.samsung.android.gallery.app.ui.list.sharings.invitations;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ InvitationsAdapter d;

    public /* synthetic */ a(InvitationsAdapter invitationsAdapter) {
        this.d = invitationsAdapter;
    }

    public final void run() {
        this.d.notifyDataSetChanged();
    }
}
