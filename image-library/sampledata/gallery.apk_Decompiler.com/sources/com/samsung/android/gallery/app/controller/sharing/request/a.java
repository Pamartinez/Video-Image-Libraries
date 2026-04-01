package com.samsung.android.gallery.app.controller.sharing.request;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements RequestFactory.CreateRequestInstance {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2509a;

    public /* synthetic */ a(int i2) {
        this.f2509a = i2;
    }

    public final AbsRequest create(EventContext eventContext, Object[] objArr) {
        switch (this.f2509a) {
            case 0:
                return new RequestSync(eventContext, objArr);
            case 1:
                return new RequestDownloadForEditInSharing(eventContext, objArr);
            case 2:
                return new RequestLeaveGroup(eventContext, objArr);
            case 3:
                return new RequestMoveToTrash(eventContext, objArr);
            case 4:
                return new RequestRestoreFromTrash(eventContext, objArr);
            case 5:
                return new RequestDeleteFromTrash(eventContext, objArr);
            case 6:
                return new RequestEmptyFromTrash(eventContext, objArr);
            case 7:
                return new RequestCreateStory(eventContext, objArr);
            case 8:
                return new RequestSpaceListSort(eventContext, objArr);
            case 9:
                return new RequestDeleteGroup(eventContext, objArr);
            case 10:
                return new RequestCreateFamilySpace(eventContext, objArr);
            case 11:
                return new RequestStreamingVideo(eventContext, objArr);
            case 12:
                return new RequestDeleteFamilySpace(eventContext, objArr);
            case 13:
                return new RequestDeleteSpace(eventContext, objArr);
            case 14:
                return new RequestUpdateSpace(eventContext, objArr);
            case 15:
                return new RequestAddContent(eventContext, objArr);
            case 16:
                return new RequestDeleteContent(eventContext, objArr);
            case 17:
                return new RequestDownloadContent(eventContext, objArr);
            case 18:
                return new RequestInvitationSync(eventContext, objArr);
            case 19:
                return new RequestInvitationAcceptance(eventContext, objArr);
            case 20:
                return new RequestInvitationRejection(eventContext, objArr);
            case 21:
                return new RequestSpaceChangeCover(eventContext, objArr);
            case 22:
                return new RequestDownloadForPlayInSharing(eventContext, objArr);
            case 23:
                return new RequestDownloadCover(eventContext, objArr);
            case 24:
                return new RequestMyQuota(eventContext, objArr);
            default:
                return new RequestFamilyQuota(eventContext, objArr);
        }
    }
}
