package com.samsung.android.gallery.app.ui.list.sharings.invitations;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.sharing.RequestSetupWizardType;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.ShowSharingInvitationsJoinDialogCmd;
import com.samsung.android.gallery.app.controller.sharing.StartSharingServiceSetupWizardCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.sharings.invitations.IInvitationsView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.MdeAuthHelper;
import com.samsung.android.gallery.module.mde.MdeSocialHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class InvitationsPresenter<V extends IInvitationsView> extends BaseListPresenter<V> {
    public InvitationsPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    /* access modifiers changed from: private */
    public void onSharingInvitationsDataChanged(Object obj, Bundle bundle) {
        if (!MdeSocialHelper.isSocialServiceEnabled() || MdeAuthHelper.isReadyToUseShareService()) {
            Object[] objArr = (Object[]) obj;
            new ShowSharingInvitationsJoinDialogCmd().execute(this, objArr[0], objArr[1]);
            return;
        }
        new StartSharingServiceSetupWizardCmd().execute(this, RequestSetupWizardType.SETUP_SHARING_SERVICE);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("data://user/dialog/SharingInvitationsJoinDialog", new b(this)).setTriggerPreloadedData());
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 6004) {
            ((IInvitationsView) this.mView).showProgressDialog();
            return true;
        } else if (i2 != 6005) {
            return super.handleEvent(eventMessage);
        } else {
            ((IInvitationsView) this.mView).dismissProgressDialog();
            return true;
        }
    }

    public void onViewResume() {
        super.onViewResume();
        new RequestSharedAlbumCmd().execute(this, RequestCmdType.REQUEST_INVITATION_SYNC);
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((int) R.string.invitations);
        toolbar.setSubtitle((CharSequence) null);
        setNavigationUpButton(toolbar);
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
    }
}
