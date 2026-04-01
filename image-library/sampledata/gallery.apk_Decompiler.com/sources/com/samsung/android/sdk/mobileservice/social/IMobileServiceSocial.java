package com.samsung.android.sdk.mobileservice.social;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.samsung.android.sdk.mobileservice.social.activity.IActivityBundlePartialResultCallback;
import com.samsung.android.sdk.mobileservice.social.activity.IActivityBundleResultCallback;
import com.samsung.android.sdk.mobileservice.social.activity.IActivityResultCallback;
import com.samsung.android.sdk.mobileservice.social.activity.IDeleteAllActivityResultCallback;
import com.samsung.android.sdk.mobileservice.social.buddy.IBuddyInfoResultCallback;
import com.samsung.android.sdk.mobileservice.social.buddy.IPublicBuddyInfoResultCallback;
import com.samsung.android.sdk.mobileservice.social.buddy.IServiceActivationResultCallback;
import com.samsung.android.sdk.mobileservice.social.buddy.IServiceDeactivationResultCallback;
import com.samsung.android.sdk.mobileservice.social.buddy.ISyncResultCallback;
import com.samsung.android.sdk.mobileservice.social.common.IBundleProgressResultCallback;
import com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback;
import com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundlePartialResultCallback;
import com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundleResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupCoverImageDownloadingResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupInvitationResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupListResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupListWithInvitationResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupSyncResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IMemberListResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.IDownloadThumbnailResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.IShareBulkItemResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.IShareResultWithFileListCallback;
import com.samsung.android.sdk.mobileservice.social.share.IShareStatusCallback;
import com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISharedItemDeletionResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISharedItemListDeletionResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISharedItemListResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISharedItemResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISharedItemUpdateResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISpaceCoverImageDownloadingResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISpaceDeletionResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISpaceListResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback;
import com.samsung.android.sdk.mobileservice.social.share.ITrashBinResultCallback;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMobileServiceSocial extends IInterface {
    int cancelShare(String str, String str2);

    void clearSpaceUnreadCount(String str, String str2);

    void clearSpaceUnreadCountWithData(String str, Bundle bundle, String str2);

    int dataSyncDelete(String str, Bundle bundle, IBundleResultCallback iBundleResultCallback);

    int dataSyncGetSyncedData(String str, Bundle bundle, IBundleResultCallback iBundleResultCallback);

    int dataSyncUpsert(String str, Bundle bundle, IBundleResultCallback iBundleResultCallback);

    Bundle getBuddyActivityCount(Bundle bundle);

    Bundle getBuddyActivityList(Bundle bundle);

    void getBuddyInfo(Bundle bundle, IBuddyInfoResultCallback iBuddyInfoResultCallback);

    Bundle getBuddySyncTime();

    int getCountryTypeForAgreement();

    Bundle getDeviceAuthInfoCached();

    boolean getDisclaimerAgreementForService(Bundle bundle);

    boolean getDisclaimerAgreementForSocial();

    int getDisclosureScope(Bundle bundle);

    Bundle getFamilyQuotaWithData(Bundle bundle);

    List<Bundle> getGroupList(String str);

    List<Bundle> getGroupListWithData(Bundle bundle);

    Intent getIntentToDisplay(Bundle bundle);

    Bundle getNotification(Bundle bundle);

    Bundle getQuota();

    Bundle getQuotaWithData(Bundle bundle);

    Bundle getServiceState();

    int getShareStatus(String str, String str2);

    Bundle getVerifiedOriginalFileListWithData(Bundle bundle);

    Bundle isPermissionGranted(Bundle bundle);

    int isServiceActivated(int i2);

    boolean isServiceRegistered(Bundle bundle);

    Bundle isSomethingNeeded(Bundle bundle);

    int pauseShare(String str, String str2);

    void requestActivity(Bundle bundle, IActivityBundlePartialResultCallback iActivityBundlePartialResultCallback);

    void requestActivityChanges(IActivityBundlePartialResultCallback iActivityBundlePartialResultCallback);

    void requestActivityContent(Bundle bundle, IActivityBundleResultCallback iActivityBundleResultCallback);

    void requestActivityContentStreamingUrl(Bundle bundle, IActivityBundleResultCallback iActivityBundleResultCallback);

    void requestActivityDeletion(Bundle bundle, IActivityResultCallback iActivityResultCallback);

    void requestActivityImageList(Bundle bundle, IBundleProgressResultCallback iBundleProgressResultCallback);

    void requestActivityList(Bundle bundle, IActivityBundlePartialResultCallback iActivityBundlePartialResultCallback);

    Bundle requestActivityPosting(Bundle bundle, PendingIntent pendingIntent, IBundleProgressResultCallback iBundleProgressResultCallback);

    void requestActivitySync(IActivityBundleResultCallback iActivityBundleResultCallback);

    int requestAllSpaceList(String str, ISpaceListResultCallback iSpaceListResultCallback);

    int requestAllSpaceListWithData(String str, Bundle bundle, ISpaceListResultCallback iSpaceListResultCallback);

    void requestAppUpdateAndLaunchForBuddy(Bundle bundle, IBuddyInfoResultCallback iBuddyInfoResultCallback);

    void requestBuddySync(int i2, ISyncResultCallback iSyncResultCallback);

    String requestBulkItemShare(Bundle bundle, IShareBulkItemResultCallback iShareBulkItemResultCallback);

    void requestCommentCreation(Bundle bundle, IFeedbackBundleResultCallback iFeedbackBundleResultCallback);

    void requestCommentDeletion(Bundle bundle, IFeedbackBundleResultCallback iFeedbackBundleResultCallback);

    void requestCommentList(Bundle bundle, IFeedbackBundlePartialResultCallback iFeedbackBundlePartialResultCallback);

    void requestCommentUpdate(Bundle bundle, IFeedbackBundleResultCallback iFeedbackBundleResultCallback);

    Bundle requestContentsController(Bundle bundle);

    int requestDelegateAuthorityOfOwner(String str, String str2, String str3, IGroupResultCallback iGroupResultCallback);

    int requestDelegateAuthorityOfOwnerWithData(Bundle bundle, IGroupResultCallback iGroupResultCallback);

    void requestDeleteAllActivity(IDeleteAllActivityResultCallback iDeleteAllActivityResultCallback);

    int requestDeleteAllItemsFromTrashBinWithData(Bundle bundle, ITrashBinResultCallback iTrashBinResultCallback);

    int requestDeleteItemListFromTrashBinWithData(Bundle bundle, ITrashBinResultCallback iTrashBinResultCallback);

    int requestDisclosureScope(Bundle bundle, IBundleResultCallback iBundleResultCallback);

    int requestDisclosureScopeUpdate(Bundle bundle, IBundleResultCallback iBundleResultCallback);

    void requestEmotionMemberList(Bundle bundle, IFeedbackBundlePartialResultCallback iFeedbackBundlePartialResultCallback);

    void requestEmotionUpdate(Bundle bundle, IFeedbackBundleResultCallback iFeedbackBundleResultCallback);

    int requestFamilyGroupCreationWithData(Bundle bundle, Bundle bundle2, IGroupResultCallback iGroupResultCallback);

    int requestFamilyGroupDeletionWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestFamilyGroupMemberListWithData(Bundle bundle, IMemberListResultCallback iMemberListResultCallback);

    void requestFeedback(Bundle bundle, IFeedbackBundlePartialResultCallback iFeedbackBundlePartialResultCallback);

    int requestGroup(String str, String str2, IGroupResultCallback iGroupResultCallback);

    int requestGroupAuthorityChange(Bundle bundle, IGroupResultCallback iGroupResultCallback);

    int requestGroupCreation(String str, Bundle bundle, Bundle bundle2, IGroupInvitationResultCallback iGroupInvitationResultCallback);

    int requestGroupCreationWithData(Bundle bundle, Bundle bundle2, Bundle bundle3, IGroupInvitationResultCallback iGroupInvitationResultCallback);

    int requestGroupDeletion(String str, String str2, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestGroupDeletionWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestGroupInvitationAcceptance(String str, String str2, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestGroupInvitationAcceptanceWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestGroupInvitationRejection(String str, String str2, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestGroupInvitationRejectionWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestGroupList(String str, IGroupListResultCallback iGroupListResultCallback);

    int requestGroupListWithData(Bundle bundle, IGroupListResultCallback iGroupListResultCallback);

    int requestGroupMemberInvitation(String str, String str2, Bundle bundle, IGroupInvitationResultCallback iGroupInvitationResultCallback);

    int requestGroupMemberInvitationWithData(Bundle bundle, Bundle bundle2, IGroupInvitationResultCallback iGroupInvitationResultCallback);

    int requestGroupMemberList(String str, String str2, IMemberListResultCallback iMemberListResultCallback);

    int requestGroupMemberListWithData(Bundle bundle, IMemberListResultCallback iMemberListResultCallback);

    int requestGroupMemberRemoval(String str, String str2, String str3, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestGroupMemberRemovalWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestGroupPendingInvitationCancellation(String str, String str2, String str3, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestGroupPendingInvitationCancellationWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestGroupSync(String str, IGroupSyncResultCallback iGroupSyncResultCallback);

    int requestGroupSyncWithData(Bundle bundle, IGroupSyncResultCallback iGroupSyncResultCallback);

    int requestGroupSyncWithoutImage(String str, IGroupSyncResultCallback iGroupSyncResultCallback);

    int requestGroupSyncWithoutImageWithData(Bundle bundle, IGroupSyncResultCallback iGroupSyncResultCallback);

    int requestGroupUpdate(String str, String str2, Bundle bundle, IGroupResultCallback iGroupResultCallback);

    int requestGroupWithData(Bundle bundle, IGroupResultCallback iGroupResultCallback);

    int requestGroupWithInvitationList(String str, IGroupListWithInvitationResultCallback iGroupListWithInvitationResultCallback);

    int requestGroupWithInvitationListWithData(Bundle bundle, IGroupListWithInvitationResultCallback iGroupListWithInvitationResultCallback);

    int requestInstantShare(String str, Bundle bundle, List<Bundle> list, IShareResultCallback iShareResultCallback);

    int requestInvitationLinkCreation(Bundle bundle, IGroupResultCallback iGroupResultCallback);

    int requestInvitationLinkDeletion(Bundle bundle, IGroupResultCallback iGroupResultCallback);

    int requestLeave(String str, String str2, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestLeaveWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback);

    int requestMoveItemListToTrashBinWithData(Bundle bundle, ITrashBinResultCallback iTrashBinResultCallback);

    void requestMyActivityPrivacy(IActivityBundleResultCallback iActivityBundleResultCallback);

    void requestMyActivityPrivacyUpdate(Bundle bundle, IActivityResultCallback iActivityResultCallback);

    int requestOriginalGroupImageDownload(String str, String str2, IGroupCoverImageDownloadingResultCallback iGroupCoverImageDownloadingResultCallback);

    int requestOriginalSharedContentWithFileListDownload(String str, String str2, String str3, List<String> list, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle);

    int requestOriginalSharedContentWithFileListDownloadWithData(String str, String str2, String str3, List<String> list, Bundle bundle, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle2);

    int requestOriginalSharedContentWithItemFileListDownloadWithPath(String str, String str2, String str3, List<String> list, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle, String str4);

    int requestOriginalSharedContentWithItemFileListDownloadWithPathWithData(String str, String str2, String str3, List<String> list, Bundle bundle, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle2, String str4);

    int requestOriginalSharedContentsDownload(String str, String str2, String[] strArr, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle);

    int requestOriginalSharedContentsDownloadToHiddenFolder(Bundle bundle, IContentDownloadingResultCallback iContentDownloadingResultCallback);

    int requestOriginalSharedContentsDownloadWithPath(String str, String str2, String[] strArr, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle, String str3);

    int requestOriginalSpaceImageDownload(String str, String str2, ISpaceCoverImageDownloadingResultCallback iSpaceCoverImageDownloadingResultCallback);

    int requestOriginalSpaceImageDownloadWithData(String str, String str2, Bundle bundle, ISpaceCoverImageDownloadingResultCallback iSpaceCoverImageDownloadingResultCallback);

    void requestProfileImageList(Bundle bundle, IBundleProgressResultCallback iBundleProgressResultCallback);

    void requestPublicBuddyInfo(String str, IPublicBuddyInfoResultCallback iPublicBuddyInfoResultCallback);

    int requestRestoreItemListFromTrashBinWithData(Bundle bundle, ITrashBinResultCallback iTrashBinResultCallback);

    void requestServiceActivation(int i2, IServiceActivationResultCallback iServiceActivationResultCallback);

    void requestServiceDeactivation(int i2, IServiceDeactivationResultCallback iServiceDeactivationResultCallback);

    int requestServiceNumber(IBundleResultCallback iBundleResultCallback);

    String requestShareListUpdateWithItemFileList(String str, String str2, List<Bundle> list, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle);

    String requestShareListUpdateWithItemFileListWithData(String str, String str2, List<Bundle> list, Bundle bundle, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle2);

    int requestShareSync(String str, IShareSyncResultCallback iShareSyncResultCallback);

    int requestShareSyncWithData(String str, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback);

    int requestShareSyncWithOption(String str, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback);

    String requestShareUpdateWithUriList(String str, String str2, String str3, Bundle bundle, IShareResultWithFileListCallback iShareResultWithFileListCallback, PendingIntent pendingIntent, Bundle bundle2);

    String requestShareWithData(String str, String str2, List<Bundle> list, Bundle bundle, IShareResultCallback iShareResultCallback);

    String requestShareWithFileList(String str, String str2, Bundle bundle, IShareResultWithFileListCallback iShareResultWithFileListCallback, PendingIntent pendingIntent, Bundle bundle2);

    String requestShareWithFileListWithData(String str, String str2, Bundle bundle, Bundle bundle2, IShareResultWithFileListCallback iShareResultWithFileListCallback, PendingIntent pendingIntent, Bundle bundle3);

    String requestShareWithItemFileList(String str, String str2, List<Bundle> list, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle);

    String requestShareWithItemFileListWithData(String str, String str2, List<Bundle> list, Bundle bundle, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle2);

    String requestShareWithPendingIntent(String str, String str2, List<Bundle> list, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle);

    int requestSharedItem(String str, String str2, String str3, ISharedItemResultCallback iSharedItemResultCallback);

    int requestSharedItemDeletion(String str, String str2, String str3, ISharedItemDeletionResultCallback iSharedItemDeletionResultCallback);

    int requestSharedItemDeletionWithData(String str, String str2, String str3, Bundle bundle, ISharedItemDeletionResultCallback iSharedItemDeletionResultCallback);

    int requestSharedItemList(String str, String str2, String str3, String str4, ISharedItemListResultCallback iSharedItemListResultCallback);

    int requestSharedItemListDeletion(String str, String str2, List<String> list, ISharedItemListDeletionResultCallback iSharedItemListDeletionResultCallback);

    int requestSharedItemListDeletionWithData(String str, String str2, List<String> list, Bundle bundle, ISharedItemListDeletionResultCallback iSharedItemListDeletionResultCallback);

    int requestSharedItemListInTrashBinWithData(Bundle bundle, ISharedItemListResultCallback iSharedItemListResultCallback);

    String requestSharedItemListUpdate(String str, String str2, List<Bundle> list, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle);

    int requestSharedItemListWithFileList(String str, String str2, String str3, String str4, ISharedItemListResultCallback iSharedItemListResultCallback);

    int requestSharedItemListWithFileListWithData(String str, String str2, String str3, String str4, Bundle bundle, ISharedItemListResultCallback iSharedItemListResultCallback);

    int requestSharedItemSync(String str, String str2, IShareSyncResultCallback iShareSyncResultCallback);

    int requestSharedItemSyncWithData(String str, String str2, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback);

    void requestSharedItemSyncWithResolution(String str, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback);

    int requestSharedItemSyncWithResolutionWithData(String str, Bundle bundle, Bundle bundle2, IShareSyncResultCallback iShareSyncResultCallback);

    String requestSharedItemUpdate(String str, String str2, String str3, Bundle bundle, ISharedItemUpdateResultCallback iSharedItemUpdateResultCallback, PendingIntent pendingIntent, Bundle bundle2);

    int requestSharedItemWithData(String str, String str2, String str3, Bundle bundle, ISharedItemResultCallback iSharedItemResultCallback);

    int requestSharedItemWithGroupId(String str, String str2, String str3, String str4, ISharedItemResultCallback iSharedItemResultCallback);

    int requestSharedItemWithGroupIdWithData(String str, String str2, String str3, String str4, Bundle bundle, ISharedItemResultCallback iSharedItemResultCallback);

    int requestSpace(String str, String str2, ISpaceResultCallback iSpaceResultCallback);

    int requestSpaceCreation(String str, String str2, Bundle bundle, ISpaceResultCallback iSpaceResultCallback);

    int requestSpaceCreationWithData(String str, String str2, Bundle bundle, Bundle bundle2, ISpaceResultCallback iSpaceResultCallback);

    int requestSpaceDeletion(String str, String str2, ISpaceDeletionResultCallback iSpaceDeletionResultCallback);

    int requestSpaceDeletionWithData(String str, String str2, Bundle bundle, ISpaceDeletionResultCallback iSpaceDeletionResultCallback);

    int requestSpaceList(String str, String str2, ISpaceListResultCallback iSpaceListResultCallback);

    int requestSpaceListSync(String str, IShareSyncResultCallback iShareSyncResultCallback);

    int requestSpaceListSyncWithData(String str, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback);

    int requestSpaceListWithData(String str, String str2, Bundle bundle, ISpaceListResultCallback iSpaceListResultCallback);

    int requestSpaceUpdate(String str, String str2, Bundle bundle, ISpaceResultCallback iSpaceResultCallback);

    int requestSpaceUpdateWithData(String str, String str2, Bundle bundle, Bundle bundle2, ISpaceResultCallback iSpaceResultCallback);

    int requestSpaceWithData(String str, String str2, Bundle bundle, ISpaceResultCallback iSpaceResultCallback);

    void requestSync(ISyncResultCallback iSyncResultCallback);

    int requestThumbnailDownload(String str, String str2, String str3, String str4, String str5, String str6, IDownloadThumbnailResultCallback iDownloadThumbnailResultCallback);

    int requestWebLinkEnabled(Bundle bundle, boolean z, IBundleResultCallback iBundleResultCallback);

    int resumeShare(String str, String str2);

    Bundle setBuddyActivityListRead(Bundle bundle);

    boolean setDisclaimerAgreementForSocial(Bundle bundle);

    int setShareStatusListener(String str, String str2, IShareStatusCallback iShareStatusCallback);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IMobileServiceSocial {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial";
        static final int TRANSACTION_cancelShare = 26;
        static final int TRANSACTION_clearSpaceUnreadCount = 29;
        static final int TRANSACTION_clearSpaceUnreadCountWithData = 95;
        static final int TRANSACTION_dataSyncDelete = 158;
        static final int TRANSACTION_dataSyncGetSyncedData = 159;
        static final int TRANSACTION_dataSyncUpsert = 157;
        static final int TRANSACTION_getBuddyActivityCount = 81;
        static final int TRANSACTION_getBuddyActivityList = 46;
        static final int TRANSACTION_getBuddyInfo = 110;
        static final int TRANSACTION_getBuddySyncTime = 163;
        static final int TRANSACTION_getCountryTypeForAgreement = 61;
        static final int TRANSACTION_getDeviceAuthInfoCached = 107;
        static final int TRANSACTION_getDisclaimerAgreementForService = 106;
        static final int TRANSACTION_getDisclaimerAgreementForSocial = 105;
        static final int TRANSACTION_getDisclosureScope = 162;
        static final int TRANSACTION_getFamilyQuotaWithData = 148;
        static final int TRANSACTION_getGroupList = 10;
        static final int TRANSACTION_getGroupListWithData = 124;
        static final int TRANSACTION_getIntentToDisplay = 43;
        static final int TRANSACTION_getNotification = 59;
        static final int TRANSACTION_getQuota = 115;
        static final int TRANSACTION_getQuotaWithData = 118;
        static final int TRANSACTION_getServiceState = 68;
        static final int TRANSACTION_getShareStatus = 27;
        static final int TRANSACTION_getVerifiedOriginalFileListWithData = 143;
        static final int TRANSACTION_isPermissionGranted = 119;
        static final int TRANSACTION_isServiceActivated = 2;
        static final int TRANSACTION_isServiceRegistered = 108;
        static final int TRANSACTION_isSomethingNeeded = 67;
        static final int TRANSACTION_pauseShare = 24;
        static final int TRANSACTION_requestActivity = 51;
        static final int TRANSACTION_requestActivityChanges = 74;
        static final int TRANSACTION_requestActivityContent = 86;
        static final int TRANSACTION_requestActivityContentStreamingUrl = 84;
        static final int TRANSACTION_requestActivityDeletion = 45;
        static final int TRANSACTION_requestActivityImageList = 69;
        static final int TRANSACTION_requestActivityList = 44;
        static final int TRANSACTION_requestActivityPosting = 83;
        static final int TRANSACTION_requestActivitySync = 50;
        static final int TRANSACTION_requestAllSpaceList = 20;
        static final int TRANSACTION_requestAllSpaceListWithData = 94;
        static final int TRANSACTION_requestAppUpdateAndLaunchForBuddy = 155;
        static final int TRANSACTION_requestBuddySync = 109;
        static final int TRANSACTION_requestBulkItemShare = 156;
        static final int TRANSACTION_requestCommentCreation = 54;
        static final int TRANSACTION_requestCommentDeletion = 56;
        static final int TRANSACTION_requestCommentList = 53;
        static final int TRANSACTION_requestCommentUpdate = 55;
        static final int TRANSACTION_requestContentsController = 85;
        static final int TRANSACTION_requestDelegateAuthorityOfOwner = 71;
        static final int TRANSACTION_requestDelegateAuthorityOfOwnerWithData = 135;
        static final int TRANSACTION_requestDeleteAllActivity = 6;
        static final int TRANSACTION_requestDeleteAllItemsFromTrashBinWithData = 153;
        static final int TRANSACTION_requestDeleteItemListFromTrashBinWithData = 152;
        static final int TRANSACTION_requestDisclosureScope = 160;
        static final int TRANSACTION_requestDisclosureScopeUpdate = 161;
        static final int TRANSACTION_requestEmotionMemberList = 58;
        static final int TRANSACTION_requestEmotionUpdate = 57;
        static final int TRANSACTION_requestFamilyGroupCreationWithData = 146;
        static final int TRANSACTION_requestFamilyGroupDeletionWithData = 147;
        static final int TRANSACTION_requestFamilyGroupMemberListWithData = 149;
        static final int TRANSACTION_requestFeedback = 52;
        static final int TRANSACTION_requestGroup = 9;
        static final int TRANSACTION_requestGroupAuthorityChange = 136;
        static final int TRANSACTION_requestGroupCreation = 32;
        static final int TRANSACTION_requestGroupCreationWithData = 125;
        static final int TRANSACTION_requestGroupDeletion = 33;
        static final int TRANSACTION_requestGroupDeletionWithData = 126;
        static final int TRANSACTION_requestGroupInvitationAcceptance = 35;
        static final int TRANSACTION_requestGroupInvitationAcceptanceWithData = 128;
        static final int TRANSACTION_requestGroupInvitationRejection = 36;
        static final int TRANSACTION_requestGroupInvitationRejectionWithData = 129;
        static final int TRANSACTION_requestGroupList = 8;
        static final int TRANSACTION_requestGroupListWithData = 121;
        static final int TRANSACTION_requestGroupMemberInvitation = 34;
        static final int TRANSACTION_requestGroupMemberInvitationWithData = 127;
        static final int TRANSACTION_requestGroupMemberList = 37;
        static final int TRANSACTION_requestGroupMemberListWithData = 130;
        static final int TRANSACTION_requestGroupMemberRemoval = 39;
        static final int TRANSACTION_requestGroupMemberRemovalWithData = 132;
        static final int TRANSACTION_requestGroupPendingInvitationCancellation = 41;
        static final int TRANSACTION_requestGroupPendingInvitationCancellationWithData = 134;
        static final int TRANSACTION_requestGroupSync = 7;
        static final int TRANSACTION_requestGroupSyncWithData = 122;
        static final int TRANSACTION_requestGroupSyncWithoutImage = 87;
        static final int TRANSACTION_requestGroupSyncWithoutImageWithData = 123;
        static final int TRANSACTION_requestGroupUpdate = 73;
        static final int TRANSACTION_requestGroupWithData = 120;
        static final int TRANSACTION_requestGroupWithInvitationList = 38;
        static final int TRANSACTION_requestGroupWithInvitationListWithData = 131;
        static final int TRANSACTION_requestInstantShare = 42;
        static final int TRANSACTION_requestInvitationLinkCreation = 144;
        static final int TRANSACTION_requestInvitationLinkDeletion = 145;
        static final int TRANSACTION_requestLeave = 40;
        static final int TRANSACTION_requestLeaveWithData = 133;
        static final int TRANSACTION_requestMoveItemListToTrashBinWithData = 150;
        static final int TRANSACTION_requestMyActivityPrivacy = 49;
        static final int TRANSACTION_requestMyActivityPrivacyUpdate = 48;
        static final int TRANSACTION_requestOriginalGroupImageDownload = 21;
        static final int TRANSACTION_requestOriginalSharedContentWithFileListDownload = 63;
        static final int TRANSACTION_requestOriginalSharedContentWithFileListDownloadWithData = 102;
        static final int TRANSACTION_requestOriginalSharedContentWithItemFileListDownloadWithPath = 80;
        static final int TRANSACTION_requestOriginalSharedContentWithItemFileListDownloadWithPathWithData = 101;
        static final int TRANSACTION_requestOriginalSharedContentsDownload = 23;
        static final int TRANSACTION_requestOriginalSharedContentsDownloadToHiddenFolder = 113;
        static final int TRANSACTION_requestOriginalSharedContentsDownloadWithPath = 75;
        static final int TRANSACTION_requestOriginalSpaceImageDownload = 22;
        static final int TRANSACTION_requestOriginalSpaceImageDownloadWithData = 139;
        static final int TRANSACTION_requestProfileImageList = 70;
        static final int TRANSACTION_requestPublicBuddyInfo = 5;
        static final int TRANSACTION_requestRestoreItemListFromTrashBinWithData = 151;
        static final int TRANSACTION_requestServiceActivation = 3;
        static final int TRANSACTION_requestServiceDeactivation = 4;
        static final int TRANSACTION_requestServiceNumber = 112;
        static final int TRANSACTION_requestShareListUpdateWithItemFileList = 79;
        static final int TRANSACTION_requestShareListUpdateWithItemFileListWithData = 100;
        static final int TRANSACTION_requestShareSync = 11;
        static final int TRANSACTION_requestShareSyncWithData = 88;
        static final int TRANSACTION_requestShareSyncWithOption = 116;
        static final int TRANSACTION_requestShareUpdateWithUriList = 64;
        static final int TRANSACTION_requestShareWithData = 114;
        static final int TRANSACTION_requestShareWithFileList = 62;
        static final int TRANSACTION_requestShareWithFileListWithData = 137;
        static final int TRANSACTION_requestShareWithItemFileList = 77;
        static final int TRANSACTION_requestShareWithItemFileListWithData = 99;
        static final int TRANSACTION_requestShareWithPendingIntent = 12;
        static final int TRANSACTION_requestSharedItem = 13;
        static final int TRANSACTION_requestSharedItemDeletion = 14;
        static final int TRANSACTION_requestSharedItemDeletionWithData = 138;
        static final int TRANSACTION_requestSharedItemList = 65;
        static final int TRANSACTION_requestSharedItemListDeletion = 76;
        static final int TRANSACTION_requestSharedItemListDeletionWithData = 98;
        static final int TRANSACTION_requestSharedItemListInTrashBinWithData = 154;
        static final int TRANSACTION_requestSharedItemListUpdate = 78;
        static final int TRANSACTION_requestSharedItemListWithFileList = 66;
        static final int TRANSACTION_requestSharedItemListWithFileListWithData = 103;
        static final int TRANSACTION_requestSharedItemSync = 31;
        static final int TRANSACTION_requestSharedItemSyncWithData = 140;
        static final int TRANSACTION_requestSharedItemSyncWithResolution = 82;
        static final int TRANSACTION_requestSharedItemSyncWithResolutionWithData = 97;
        static final int TRANSACTION_requestSharedItemUpdate = 60;
        static final int TRANSACTION_requestSharedItemWithData = 141;
        static final int TRANSACTION_requestSharedItemWithGroupId = 72;
        static final int TRANSACTION_requestSharedItemWithGroupIdWithData = 142;
        static final int TRANSACTION_requestSpace = 15;
        static final int TRANSACTION_requestSpaceCreation = 16;
        static final int TRANSACTION_requestSpaceCreationWithData = 90;
        static final int TRANSACTION_requestSpaceDeletion = 17;
        static final int TRANSACTION_requestSpaceDeletionWithData = 91;
        static final int TRANSACTION_requestSpaceList = 18;
        static final int TRANSACTION_requestSpaceListSync = 30;
        static final int TRANSACTION_requestSpaceListSyncWithData = 96;
        static final int TRANSACTION_requestSpaceListWithData = 92;
        static final int TRANSACTION_requestSpaceUpdate = 19;
        static final int TRANSACTION_requestSpaceUpdateWithData = 93;
        static final int TRANSACTION_requestSpaceWithData = 89;
        static final int TRANSACTION_requestSync = 1;
        static final int TRANSACTION_requestThumbnailDownload = 111;
        static final int TRANSACTION_requestWebLinkEnabled = 117;
        static final int TRANSACTION_resumeShare = 25;
        static final int TRANSACTION_setBuddyActivityListRead = 47;
        static final int TRANSACTION_setDisclaimerAgreementForSocial = 104;
        static final int TRANSACTION_setShareStatusListener = 28;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IMobileServiceSocial {
            public static IMobileServiceSocial sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public int cancelShare(String str, String str2) {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(26, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().cancelShare(str, str2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void clearSpaceUnreadCount(String str, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(29, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().clearSpaceUnreadCount(str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void clearSpaceUnreadCountWithData(String str, Bundle bundle, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str2);
                    if (this.mRemote.transact(95, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().clearSpaceUnreadCountWithData(str, bundle, str2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int dataSyncDelete(String str, Bundle bundle, IBundleResultCallback iBundleResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iBundleResultCallback != null) {
                        iBinder = iBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(Stub.TRANSACTION_dataSyncDelete, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().dataSyncDelete(str, bundle, iBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int dataSyncGetSyncedData(String str, Bundle bundle, IBundleResultCallback iBundleResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iBundleResultCallback != null) {
                        iBinder = iBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(Stub.TRANSACTION_dataSyncGetSyncedData, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().dataSyncGetSyncedData(str, bundle, iBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int dataSyncUpsert(String str, Bundle bundle, IBundleResultCallback iBundleResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iBundleResultCallback != null) {
                        iBinder = iBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(Stub.TRANSACTION_dataSyncUpsert, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().dataSyncUpsert(str, bundle, iBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public Bundle getBuddyActivityCount(Bundle bundle) {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(81, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle2 = null;
                        }
                    } else {
                        bundle2 = Stub.getDefaultImpl().getBuddyActivityCount(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public Bundle getBuddyActivityList(Bundle bundle) {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(46, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle2 = null;
                        }
                    } else {
                        bundle2 = Stub.getDefaultImpl().getBuddyActivityList(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void getBuddyInfo(Bundle bundle, IBuddyInfoResultCallback iBuddyInfoResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iBuddyInfoResultCallback != null) {
                        iBinder = iBuddyInfoResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(110, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().getBuddyInfo(bundle, iBuddyInfoResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public Bundle getBuddySyncTime() {
                Bundle bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(Stub.TRANSACTION_getBuddySyncTime, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle = null;
                        }
                    } else {
                        bundle = Stub.getDefaultImpl().getBuddySyncTime();
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getCountryTypeForAgreement() {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(61, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().getCountryTypeForAgreement();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getDeviceAuthInfoCached() {
                Bundle bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(107, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle = null;
                        }
                    } else {
                        bundle = Stub.getDefaultImpl().getDeviceAuthInfoCached();
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getDisclaimerAgreementForService(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(106, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() == 0) {
                            z = false;
                        }
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    }
                    boolean disclaimerAgreementForService = Stub.getDefaultImpl().getDisclaimerAgreementForService(bundle);
                    obtain2.recycle();
                    obtain.recycle();
                    return disclaimerAgreementForService;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public boolean getDisclaimerAgreementForSocial() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(105, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisclaimerAgreementForSocial();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getDisclosureScope(Bundle bundle) {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(Stub.TRANSACTION_getDisclosureScope, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().getDisclosureScope(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public Bundle getFamilyQuotaWithData(Bundle bundle) {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(148, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle2 = null;
                        }
                    } else {
                        bundle2 = Stub.getDefaultImpl().getFamilyQuotaWithData(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public List<Bundle> getGroupList(String str) {
                List<Bundle> createTypedArrayList;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(10, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        createTypedArrayList = obtain2.createTypedArrayList(Bundle.CREATOR);
                    } else {
                        createTypedArrayList = Stub.getDefaultImpl().getGroupList(str);
                    }
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<Bundle> getGroupListWithData(Bundle bundle) {
                List<Bundle> createTypedArrayList;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(124, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        createTypedArrayList = obtain2.createTypedArrayList(Bundle.CREATOR);
                    } else {
                        createTypedArrayList = Stub.getDefaultImpl().getGroupListWithData(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return createTypedArrayList;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public Intent getIntentToDisplay(Bundle bundle) {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(43, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentToDisplay(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return intent;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public Bundle getNotification(Bundle bundle) {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(59, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle2 = null;
                        }
                    } else {
                        bundle2 = Stub.getDefaultImpl().getNotification(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public Bundle getQuota() {
                Bundle bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(115, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle = null;
                        }
                    } else {
                        bundle = Stub.getDefaultImpl().getQuota();
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getQuotaWithData(Bundle bundle) {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(118, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle2 = null;
                        }
                    } else {
                        bundle2 = Stub.getDefaultImpl().getQuotaWithData(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public Bundle getServiceState() {
                Bundle bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(68, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle = null;
                        }
                    } else {
                        bundle = Stub.getDefaultImpl().getServiceState();
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getShareStatus(String str, String str2) {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(27, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().getShareStatus(str, str2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getVerifiedOriginalFileListWithData(Bundle bundle) {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(143, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle2 = null;
                        }
                    } else {
                        bundle2 = Stub.getDefaultImpl().getVerifiedOriginalFileListWithData(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public Bundle isPermissionGranted(Bundle bundle) {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(119, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle2 = null;
                        }
                    } else {
                        bundle2 = Stub.getDefaultImpl().isPermissionGranted(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int isServiceActivated(int i2) {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().isServiceActivated(i2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isServiceRegistered(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(108, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() == 0) {
                            z = false;
                        }
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    }
                    boolean isServiceRegistered = Stub.getDefaultImpl().isServiceRegistered(bundle);
                    obtain2.recycle();
                    obtain.recycle();
                    return isServiceRegistered;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public Bundle isSomethingNeeded(Bundle bundle) {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(67, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle2 = null;
                        }
                    } else {
                        bundle2 = Stub.getDefaultImpl().isSomethingNeeded(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int pauseShare(String str, String str2) {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(24, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().pauseShare(str, str2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void requestActivity(Bundle bundle, IActivityBundlePartialResultCallback iActivityBundlePartialResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iActivityBundlePartialResultCallback != null) {
                        iBinder = iActivityBundlePartialResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(51, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestActivity(bundle, iActivityBundlePartialResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestActivityChanges(IActivityBundlePartialResultCallback iActivityBundlePartialResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iActivityBundlePartialResultCallback != null) {
                        iBinder = iActivityBundlePartialResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(74, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestActivityChanges(iActivityBundlePartialResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestActivityContent(Bundle bundle, IActivityBundleResultCallback iActivityBundleResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iActivityBundleResultCallback != null) {
                        iBinder = iActivityBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(86, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestActivityContent(bundle, iActivityBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestActivityContentStreamingUrl(Bundle bundle, IActivityBundleResultCallback iActivityBundleResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iActivityBundleResultCallback != null) {
                        iBinder = iActivityBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(84, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestActivityContentStreamingUrl(bundle, iActivityBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestActivityDeletion(Bundle bundle, IActivityResultCallback iActivityResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iActivityResultCallback != null) {
                        iBinder = iActivityResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(45, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestActivityDeletion(bundle, iActivityResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestActivityImageList(Bundle bundle, IBundleProgressResultCallback iBundleProgressResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iBundleProgressResultCallback != null) {
                        iBinder = iBundleProgressResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(69, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestActivityImageList(bundle, iBundleProgressResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestActivityList(Bundle bundle, IActivityBundlePartialResultCallback iActivityBundlePartialResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iActivityBundlePartialResultCallback != null) {
                        iBinder = iActivityBundlePartialResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(44, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestActivityList(bundle, iActivityBundlePartialResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: android.os.Bundle} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public android.os.Bundle requestActivityPosting(android.os.Bundle r6, android.app.PendingIntent r7, com.samsung.android.sdk.mobileservice.social.common.IBundleProgressResultCallback r8) {
                /*
                    r5 = this;
                    android.os.Parcel r0 = android.os.Parcel.obtain()
                    android.os.Parcel r1 = android.os.Parcel.obtain()
                    java.lang.String r2 = "com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial"
                    r0.writeInterfaceToken(r2)     // Catch:{ all -> 0x0018 }
                    r2 = 1
                    r3 = 0
                    if (r6 == 0) goto L_0x001a
                    r0.writeInt(r2)     // Catch:{ all -> 0x0018 }
                    r6.writeToParcel(r0, r3)     // Catch:{ all -> 0x0018 }
                    goto L_0x001d
                L_0x0018:
                    r5 = move-exception
                    goto L_0x006d
                L_0x001a:
                    r0.writeInt(r3)     // Catch:{ all -> 0x0018 }
                L_0x001d:
                    if (r7 == 0) goto L_0x0026
                    r0.writeInt(r2)     // Catch:{ all -> 0x0018 }
                    r7.writeToParcel(r0, r3)     // Catch:{ all -> 0x0018 }
                    goto L_0x0029
                L_0x0026:
                    r0.writeInt(r3)     // Catch:{ all -> 0x0018 }
                L_0x0029:
                    r2 = 0
                    if (r8 == 0) goto L_0x0031
                    android.os.IBinder r4 = r8.asBinder()     // Catch:{ all -> 0x0018 }
                    goto L_0x0032
                L_0x0031:
                    r4 = r2
                L_0x0032:
                    r0.writeStrongBinder(r4)     // Catch:{ all -> 0x0018 }
                    android.os.IBinder r5 = r5.mRemote     // Catch:{ all -> 0x0018 }
                    r4 = 83
                    boolean r5 = r5.transact(r4, r0, r1, r3)     // Catch:{ all -> 0x0018 }
                    if (r5 != 0) goto L_0x0054
                    com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial r5 = com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial.Stub.getDefaultImpl()     // Catch:{ all -> 0x0018 }
                    if (r5 == 0) goto L_0x0054
                    com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial r5 = com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial.Stub.getDefaultImpl()     // Catch:{ all -> 0x0018 }
                    android.os.Bundle r5 = r5.requestActivityPosting(r6, r7, r8)     // Catch:{ all -> 0x0018 }
                    r1.recycle()
                    r0.recycle()
                    return r5
                L_0x0054:
                    r1.readException()     // Catch:{ all -> 0x0018 }
                    int r5 = r1.readInt()     // Catch:{ all -> 0x0018 }
                    if (r5 == 0) goto L_0x0066
                    android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR     // Catch:{ all -> 0x0018 }
                    java.lang.Object r5 = r5.createFromParcel(r1)     // Catch:{ all -> 0x0018 }
                    r2 = r5
                    android.os.Bundle r2 = (android.os.Bundle) r2     // Catch:{ all -> 0x0018 }
                L_0x0066:
                    r1.recycle()
                    r0.recycle()
                    return r2
                L_0x006d:
                    r1.recycle()
                    r0.recycle()
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial.Stub.Proxy.requestActivityPosting(android.os.Bundle, android.app.PendingIntent, com.samsung.android.sdk.mobileservice.social.common.IBundleProgressResultCallback):android.os.Bundle");
            }

            public void requestActivitySync(IActivityBundleResultCallback iActivityBundleResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iActivityBundleResultCallback != null) {
                        iBinder = iActivityBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(50, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestActivitySync(iActivityBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestAllSpaceList(String str, ISpaceListResultCallback iSpaceListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (iSpaceListResultCallback != null) {
                        iBinder = iSpaceListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(20, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestAllSpaceList(str, iSpaceListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestAllSpaceListWithData(String str, Bundle bundle, ISpaceListResultCallback iSpaceListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSpaceListResultCallback != null) {
                        iBinder = iSpaceListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(94, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestAllSpaceListWithData(str, bundle, iSpaceListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestAppUpdateAndLaunchForBuddy(Bundle bundle, IBuddyInfoResultCallback iBuddyInfoResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iBuddyInfoResultCallback != null) {
                        iBinder = iBuddyInfoResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(Stub.TRANSACTION_requestAppUpdateAndLaunchForBuddy, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestAppUpdateAndLaunchForBuddy(bundle, iBuddyInfoResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestBuddySync(int i2, ISyncResultCallback iSyncResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i2);
                    if (iSyncResultCallback != null) {
                        iBinder = iSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(109, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestBuddySync(i2, iSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public String requestBulkItemShare(Bundle bundle, IShareBulkItemResultCallback iShareBulkItemResultCallback) {
                IBinder iBinder;
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareBulkItemResultCallback != null) {
                        iBinder = iShareBulkItemResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(Stub.TRANSACTION_requestBulkItemShare, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().requestBulkItemShare(bundle, iShareBulkItemResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestCommentCreation(Bundle bundle, IFeedbackBundleResultCallback iFeedbackBundleResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iFeedbackBundleResultCallback != null) {
                        iBinder = iFeedbackBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(54, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestCommentCreation(bundle, iFeedbackBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestCommentDeletion(Bundle bundle, IFeedbackBundleResultCallback iFeedbackBundleResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iFeedbackBundleResultCallback != null) {
                        iBinder = iFeedbackBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(56, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestCommentDeletion(bundle, iFeedbackBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestCommentList(Bundle bundle, IFeedbackBundlePartialResultCallback iFeedbackBundlePartialResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iFeedbackBundlePartialResultCallback != null) {
                        iBinder = iFeedbackBundlePartialResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(53, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestCommentList(bundle, iFeedbackBundlePartialResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestCommentUpdate(Bundle bundle, IFeedbackBundleResultCallback iFeedbackBundleResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iFeedbackBundleResultCallback != null) {
                        iBinder = iFeedbackBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(55, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestCommentUpdate(bundle, iFeedbackBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public Bundle requestContentsController(Bundle bundle) {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(85, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle2 = null;
                        }
                    } else {
                        bundle2 = Stub.getDefaultImpl().requestContentsController(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestDelegateAuthorityOfOwner(String str, String str2, String str3, IGroupResultCallback iGroupResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (iGroupResultCallback != null) {
                        iBinder = iGroupResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(71, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestDelegateAuthorityOfOwner(str, str2, str3, iGroupResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestDelegateAuthorityOfOwnerWithData(Bundle bundle, IGroupResultCallback iGroupResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupResultCallback != null) {
                        iBinder = iGroupResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(135, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestDelegateAuthorityOfOwnerWithData(bundle, iGroupResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestDeleteAllActivity(IDeleteAllActivityResultCallback iDeleteAllActivityResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iDeleteAllActivityResultCallback != null) {
                        iBinder = iDeleteAllActivityResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestDeleteAllActivity(iDeleteAllActivityResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestDeleteAllItemsFromTrashBinWithData(Bundle bundle, ITrashBinResultCallback iTrashBinResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iTrashBinResultCallback != null) {
                        iBinder = iTrashBinResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(153, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestDeleteAllItemsFromTrashBinWithData(bundle, iTrashBinResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestDeleteItemListFromTrashBinWithData(Bundle bundle, ITrashBinResultCallback iTrashBinResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iTrashBinResultCallback != null) {
                        iBinder = iTrashBinResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(152, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestDeleteItemListFromTrashBinWithData(bundle, iTrashBinResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestDisclosureScope(Bundle bundle, IBundleResultCallback iBundleResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iBundleResultCallback != null) {
                        iBinder = iBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(160, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestDisclosureScope(bundle, iBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestDisclosureScopeUpdate(Bundle bundle, IBundleResultCallback iBundleResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iBundleResultCallback != null) {
                        iBinder = iBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(161, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestDisclosureScopeUpdate(bundle, iBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestEmotionMemberList(Bundle bundle, IFeedbackBundlePartialResultCallback iFeedbackBundlePartialResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iFeedbackBundlePartialResultCallback != null) {
                        iBinder = iFeedbackBundlePartialResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(58, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestEmotionMemberList(bundle, iFeedbackBundlePartialResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestEmotionUpdate(Bundle bundle, IFeedbackBundleResultCallback iFeedbackBundleResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iFeedbackBundleResultCallback != null) {
                        iBinder = iFeedbackBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(57, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestEmotionUpdate(bundle, iFeedbackBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestFamilyGroupCreationWithData(Bundle bundle, Bundle bundle2, IGroupResultCallback iGroupResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupResultCallback != null) {
                        iBinder = iGroupResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(146, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestFamilyGroupCreationWithData(bundle, bundle2, iGroupResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestFamilyGroupDeletionWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(147, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestFamilyGroupDeletionWithData(bundle, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestFamilyGroupMemberListWithData(Bundle bundle, IMemberListResultCallback iMemberListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iMemberListResultCallback != null) {
                        iBinder = iMemberListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(149, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestFamilyGroupMemberListWithData(bundle, iMemberListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestFeedback(Bundle bundle, IFeedbackBundlePartialResultCallback iFeedbackBundlePartialResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iFeedbackBundlePartialResultCallback != null) {
                        iBinder = iFeedbackBundlePartialResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(52, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestFeedback(bundle, iFeedbackBundlePartialResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroup(String str, String str2, IGroupResultCallback iGroupResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iGroupResultCallback != null) {
                        iBinder = iGroupResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(9, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroup(str, str2, iGroupResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupAuthorityChange(Bundle bundle, IGroupResultCallback iGroupResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupResultCallback != null) {
                        iBinder = iGroupResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(136, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupAuthorityChange(bundle, iGroupResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupCreation(String str, Bundle bundle, Bundle bundle2, IGroupInvitationResultCallback iGroupInvitationResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupInvitationResultCallback != null) {
                        iBinder = iGroupInvitationResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(32, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupCreation(str, bundle, bundle2, iGroupInvitationResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupCreationWithData(Bundle bundle, Bundle bundle2, Bundle bundle3, IGroupInvitationResultCallback iGroupInvitationResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle3 != null) {
                        obtain.writeInt(1);
                        bundle3.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupInvitationResultCallback != null) {
                        iBinder = iGroupInvitationResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(125, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupCreationWithData(bundle, bundle2, bundle3, iGroupInvitationResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupDeletion(String str, String str2, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(33, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupDeletion(str, str2, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupDeletionWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(126, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupDeletionWithData(bundle, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupInvitationAcceptance(String str, String str2, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(35, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupInvitationAcceptance(str, str2, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupInvitationAcceptanceWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(128, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupInvitationAcceptanceWithData(bundle, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupInvitationRejection(String str, String str2, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(36, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupInvitationRejection(str, str2, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupInvitationRejectionWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(129, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupInvitationRejectionWithData(bundle, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupList(String str, IGroupListResultCallback iGroupListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (iGroupListResultCallback != null) {
                        iBinder = iGroupListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(8, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupList(str, iGroupListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupListWithData(Bundle bundle, IGroupListResultCallback iGroupListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupListResultCallback != null) {
                        iBinder = iGroupListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(121, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupListWithData(bundle, iGroupListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupMemberInvitation(String str, String str2, Bundle bundle, IGroupInvitationResultCallback iGroupInvitationResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupInvitationResultCallback != null) {
                        iBinder = iGroupInvitationResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(34, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupMemberInvitation(str, str2, bundle, iGroupInvitationResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupMemberInvitationWithData(Bundle bundle, Bundle bundle2, IGroupInvitationResultCallback iGroupInvitationResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupInvitationResultCallback != null) {
                        iBinder = iGroupInvitationResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(127, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupMemberInvitationWithData(bundle, bundle2, iGroupInvitationResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupMemberList(String str, String str2, IMemberListResultCallback iMemberListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iMemberListResultCallback != null) {
                        iBinder = iMemberListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(37, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupMemberList(str, str2, iMemberListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupMemberListWithData(Bundle bundle, IMemberListResultCallback iMemberListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iMemberListResultCallback != null) {
                        iBinder = iMemberListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(130, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupMemberListWithData(bundle, iMemberListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupMemberRemoval(String str, String str2, String str3, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(39, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupMemberRemoval(str, str2, str3, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupMemberRemovalWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(132, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupMemberRemovalWithData(bundle, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupPendingInvitationCancellation(String str, String str2, String str3, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(41, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupPendingInvitationCancellation(str, str2, str3, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupPendingInvitationCancellationWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(134, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupPendingInvitationCancellationWithData(bundle, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupSync(String str, IGroupSyncResultCallback iGroupSyncResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (iGroupSyncResultCallback != null) {
                        iBinder = iGroupSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupSync(str, iGroupSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupSyncWithData(Bundle bundle, IGroupSyncResultCallback iGroupSyncResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupSyncResultCallback != null) {
                        iBinder = iGroupSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(122, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupSyncWithData(bundle, iGroupSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupSyncWithoutImage(String str, IGroupSyncResultCallback iGroupSyncResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (iGroupSyncResultCallback != null) {
                        iBinder = iGroupSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(87, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupSyncWithoutImage(str, iGroupSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupSyncWithoutImageWithData(Bundle bundle, IGroupSyncResultCallback iGroupSyncResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupSyncResultCallback != null) {
                        iBinder = iGroupSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(123, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupSyncWithoutImageWithData(bundle, iGroupSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupUpdate(String str, String str2, Bundle bundle, IGroupResultCallback iGroupResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupResultCallback != null) {
                        iBinder = iGroupResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(73, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupUpdate(str, str2, bundle, iGroupResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupWithData(Bundle bundle, IGroupResultCallback iGroupResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupResultCallback != null) {
                        iBinder = iGroupResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(120, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupWithData(bundle, iGroupResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupWithInvitationList(String str, IGroupListWithInvitationResultCallback iGroupListWithInvitationResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (iGroupListWithInvitationResultCallback != null) {
                        iBinder = iGroupListWithInvitationResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(38, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupWithInvitationList(str, iGroupListWithInvitationResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestGroupWithInvitationListWithData(Bundle bundle, IGroupListWithInvitationResultCallback iGroupListWithInvitationResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupListWithInvitationResultCallback != null) {
                        iBinder = iGroupListWithInvitationResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(131, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestGroupWithInvitationListWithData(bundle, iGroupListWithInvitationResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestInstantShare(String str, Bundle bundle, List<Bundle> list, IShareResultCallback iShareResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeTypedList(list);
                    if (iShareResultCallback != null) {
                        iBinder = iShareResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(42, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestInstantShare(str, bundle, list, iShareResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestInvitationLinkCreation(Bundle bundle, IGroupResultCallback iGroupResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupResultCallback != null) {
                        iBinder = iGroupResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(144, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestInvitationLinkCreation(bundle, iGroupResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestInvitationLinkDeletion(Bundle bundle, IGroupResultCallback iGroupResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupResultCallback != null) {
                        iBinder = iGroupResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(145, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestInvitationLinkDeletion(bundle, iGroupResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestLeave(String str, String str2, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(40, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestLeave(str, str2, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestLeaveWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iGroupRequestResultCallback != null) {
                        iBinder = iGroupRequestResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(133, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestLeaveWithData(bundle, iGroupRequestResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestMoveItemListToTrashBinWithData(Bundle bundle, ITrashBinResultCallback iTrashBinResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iTrashBinResultCallback != null) {
                        iBinder = iTrashBinResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(150, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestMoveItemListToTrashBinWithData(bundle, iTrashBinResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestMyActivityPrivacy(IActivityBundleResultCallback iActivityBundleResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iActivityBundleResultCallback != null) {
                        iBinder = iActivityBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(49, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestMyActivityPrivacy(iActivityBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestMyActivityPrivacyUpdate(Bundle bundle, IActivityResultCallback iActivityResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iActivityResultCallback != null) {
                        iBinder = iActivityResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(48, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestMyActivityPrivacyUpdate(bundle, iActivityResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestOriginalGroupImageDownload(String str, String str2, IGroupCoverImageDownloadingResultCallback iGroupCoverImageDownloadingResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iGroupCoverImageDownloadingResultCallback != null) {
                        iBinder = iGroupCoverImageDownloadingResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(21, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestOriginalGroupImageDownload(str, str2, iGroupCoverImageDownloadingResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestOriginalSharedContentWithFileListDownload(String str, String str2, String str3, List<String> list, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle) {
                IBinder iBinder;
                int readInt;
                PendingIntent pendingIntent2 = pendingIntent;
                Bundle bundle2 = bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStringList(list);
                    if (iContentDownloadingResultCallback != null) {
                        iBinder = iContentDownloadingResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent2 != null) {
                        obtain.writeInt(1);
                        pendingIntent2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(63, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestOriginalSharedContentWithFileListDownload(str, str2, str3, list, iContentDownloadingResultCallback, pendingIntent2, bundle2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestOriginalSharedContentWithFileListDownloadWithData(String str, String str2, String str3, List<String> list, Bundle bundle, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle2) {
                IBinder iBinder;
                int readInt;
                Bundle bundle3 = bundle;
                PendingIntent pendingIntent2 = pendingIntent;
                Bundle bundle4 = bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStringList(list);
                    if (bundle3 != null) {
                        obtain.writeInt(1);
                        bundle3.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iContentDownloadingResultCallback != null) {
                        iBinder = iContentDownloadingResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent2 != null) {
                        obtain.writeInt(1);
                        pendingIntent2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle4 != null) {
                        obtain.writeInt(1);
                        bundle4.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(102, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestOriginalSharedContentWithFileListDownloadWithData(str, str2, str3, list, bundle3, iContentDownloadingResultCallback, pendingIntent2, bundle4);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestOriginalSharedContentWithItemFileListDownloadWithPath(String str, String str2, String str3, List<String> list, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle, String str4) {
                IBinder iBinder;
                int readInt;
                PendingIntent pendingIntent2 = pendingIntent;
                Bundle bundle2 = bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStringList(list);
                    if (iContentDownloadingResultCallback != null) {
                        iBinder = iContentDownloadingResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent2 != null) {
                        obtain.writeInt(1);
                        pendingIntent2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    String str5 = str4;
                    obtain.writeString(str5);
                    if (this.mRemote.transact(80, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestOriginalSharedContentWithItemFileListDownloadWithPath(str, str2, str3, list, iContentDownloadingResultCallback, pendingIntent2, bundle2, str5);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestOriginalSharedContentWithItemFileListDownloadWithPathWithData(String str, String str2, String str3, List<String> list, Bundle bundle, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle2, String str4) {
                IBinder iBinder;
                int readInt;
                Bundle bundle3 = bundle;
                PendingIntent pendingIntent2 = pendingIntent;
                Bundle bundle4 = bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    List<String> list2 = list;
                    obtain.writeStringList(list2);
                    if (bundle3 != null) {
                        obtain.writeInt(1);
                        bundle3.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iContentDownloadingResultCallback != null) {
                        iBinder = iContentDownloadingResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent2 != null) {
                        obtain.writeInt(1);
                        pendingIntent2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle4 != null) {
                        obtain.writeInt(1);
                        bundle4.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    String str5 = str4;
                    obtain.writeString(str5);
                    if (this.mRemote.transact(101, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestOriginalSharedContentWithItemFileListDownloadWithPathWithData(str, str2, str3, list2, bundle3, iContentDownloadingResultCallback, pendingIntent2, bundle4, str5);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestOriginalSharedContentsDownload(String str, String str2, String[] strArr, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    if (iContentDownloadingResultCallback != null) {
                        iBinder = iContentDownloadingResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(23, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestOriginalSharedContentsDownload(str, str2, strArr, iContentDownloadingResultCallback, pendingIntent, bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestOriginalSharedContentsDownloadToHiddenFolder(Bundle bundle, IContentDownloadingResultCallback iContentDownloadingResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iContentDownloadingResultCallback != null) {
                        iBinder = iContentDownloadingResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(113, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestOriginalSharedContentsDownloadToHiddenFolder(bundle, iContentDownloadingResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestOriginalSharedContentsDownloadWithPath(String str, String str2, String[] strArr, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle, String str3) {
                IBinder iBinder;
                int readInt;
                Bundle bundle2 = bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    if (iContentDownloadingResultCallback != null) {
                        iBinder = iContentDownloadingResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    String str4 = str3;
                    obtain.writeString(str4);
                    if (this.mRemote.transact(75, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestOriginalSharedContentsDownloadWithPath(str, str2, strArr, iContentDownloadingResultCallback, pendingIntent, bundle2, str4);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestOriginalSpaceImageDownload(String str, String str2, ISpaceCoverImageDownloadingResultCallback iSpaceCoverImageDownloadingResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iSpaceCoverImageDownloadingResultCallback != null) {
                        iBinder = iSpaceCoverImageDownloadingResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(22, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestOriginalSpaceImageDownload(str, str2, iSpaceCoverImageDownloadingResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestOriginalSpaceImageDownloadWithData(String str, String str2, Bundle bundle, ISpaceCoverImageDownloadingResultCallback iSpaceCoverImageDownloadingResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSpaceCoverImageDownloadingResultCallback != null) {
                        iBinder = iSpaceCoverImageDownloadingResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(139, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestOriginalSpaceImageDownloadWithData(str, str2, bundle, iSpaceCoverImageDownloadingResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestProfileImageList(Bundle bundle, IBundleProgressResultCallback iBundleProgressResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iBundleProgressResultCallback != null) {
                        iBinder = iBundleProgressResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(70, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestProfileImageList(bundle, iBundleProgressResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestPublicBuddyInfo(String str, IPublicBuddyInfoResultCallback iPublicBuddyInfoResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (iPublicBuddyInfoResultCallback != null) {
                        iBinder = iPublicBuddyInfoResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestPublicBuddyInfo(str, iPublicBuddyInfoResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestRestoreItemListFromTrashBinWithData(Bundle bundle, ITrashBinResultCallback iTrashBinResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iTrashBinResultCallback != null) {
                        iBinder = iTrashBinResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(151, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestRestoreItemListFromTrashBinWithData(bundle, iTrashBinResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestServiceActivation(int i2, IServiceActivationResultCallback iServiceActivationResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i2);
                    if (iServiceActivationResultCallback != null) {
                        iBinder = iServiceActivationResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestServiceActivation(i2, iServiceActivationResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestServiceDeactivation(int i2, IServiceDeactivationResultCallback iServiceDeactivationResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i2);
                    if (iServiceDeactivationResultCallback != null) {
                        iBinder = iServiceDeactivationResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestServiceDeactivation(i2, iServiceDeactivationResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestServiceNumber(IBundleResultCallback iBundleResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iBundleResultCallback != null) {
                        iBinder = iBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(112, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestServiceNumber(iBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public String requestShareListUpdateWithItemFileList(String str, String str2, List<Bundle> list, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle) {
                IBinder iBinder;
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    if (iShareResultCallback != null) {
                        iBinder = iShareResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(79, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().requestShareListUpdateWithItemFileList(str, str2, list, iShareResultCallback, pendingIntent, bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public String requestShareListUpdateWithItemFileListWithData(String str, String str2, List<Bundle> list, Bundle bundle, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle2) {
                IBinder iBinder;
                String readString;
                PendingIntent pendingIntent2 = pendingIntent;
                Bundle bundle3 = bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareResultCallback != null) {
                        iBinder = iShareResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent2 != null) {
                        obtain.writeInt(1);
                        pendingIntent2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle3 != null) {
                        obtain.writeInt(1);
                        bundle3.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(100, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().requestShareListUpdateWithItemFileListWithData(str, str2, list, bundle, iShareResultCallback, pendingIntent2, bundle3);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestShareSync(String str, IShareSyncResultCallback iShareSyncResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (iShareSyncResultCallback != null) {
                        iBinder = iShareSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(11, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestShareSync(str, iShareSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestShareSyncWithData(String str, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareSyncResultCallback != null) {
                        iBinder = iShareSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(88, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestShareSyncWithData(str, bundle, iShareSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestShareSyncWithOption(String str, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareSyncResultCallback != null) {
                        iBinder = iShareSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(116, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestShareSyncWithOption(str, bundle, iShareSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public String requestShareUpdateWithUriList(String str, String str2, String str3, Bundle bundle, IShareResultWithFileListCallback iShareResultWithFileListCallback, PendingIntent pendingIntent, Bundle bundle2) {
                IBinder iBinder;
                String readString;
                PendingIntent pendingIntent2 = pendingIntent;
                Bundle bundle3 = bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareResultWithFileListCallback != null) {
                        iBinder = iShareResultWithFileListCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent2 != null) {
                        obtain.writeInt(1);
                        pendingIntent2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle3 != null) {
                        obtain.writeInt(1);
                        bundle3.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(64, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().requestShareUpdateWithUriList(str, str2, str3, bundle, iShareResultWithFileListCallback, pendingIntent2, bundle3);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public String requestShareWithData(String str, String str2, List<Bundle> list, Bundle bundle, IShareResultCallback iShareResultCallback) {
                IBinder iBinder;
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareResultCallback != null) {
                        iBinder = iShareResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(114, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().requestShareWithData(str, str2, list, bundle, iShareResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public String requestShareWithFileList(String str, String str2, Bundle bundle, IShareResultWithFileListCallback iShareResultWithFileListCallback, PendingIntent pendingIntent, Bundle bundle2) {
                IBinder iBinder;
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareResultWithFileListCallback != null) {
                        iBinder = iShareResultWithFileListCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(62, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().requestShareWithFileList(str, str2, bundle, iShareResultWithFileListCallback, pendingIntent, bundle2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public String requestShareWithFileListWithData(String str, String str2, Bundle bundle, Bundle bundle2, IShareResultWithFileListCallback iShareResultWithFileListCallback, PendingIntent pendingIntent, Bundle bundle3) {
                IBinder iBinder;
                String readString;
                PendingIntent pendingIntent2 = pendingIntent;
                Bundle bundle4 = bundle3;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareResultWithFileListCallback != null) {
                        iBinder = iShareResultWithFileListCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent2 != null) {
                        obtain.writeInt(1);
                        pendingIntent2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle4 != null) {
                        obtain.writeInt(1);
                        bundle4.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(137, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().requestShareWithFileListWithData(str, str2, bundle, bundle2, iShareResultWithFileListCallback, pendingIntent2, bundle4);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public String requestShareWithItemFileList(String str, String str2, List<Bundle> list, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle) {
                IBinder iBinder;
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    if (iShareResultCallback != null) {
                        iBinder = iShareResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(77, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().requestShareWithItemFileList(str, str2, list, iShareResultCallback, pendingIntent, bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public String requestShareWithItemFileListWithData(String str, String str2, List<Bundle> list, Bundle bundle, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle2) {
                IBinder iBinder;
                String readString;
                PendingIntent pendingIntent2 = pendingIntent;
                Bundle bundle3 = bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareResultCallback != null) {
                        iBinder = iShareResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent2 != null) {
                        obtain.writeInt(1);
                        pendingIntent2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle3 != null) {
                        obtain.writeInt(1);
                        bundle3.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(99, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().requestShareWithItemFileListWithData(str, str2, list, bundle, iShareResultCallback, pendingIntent2, bundle3);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public String requestShareWithPendingIntent(String str, String str2, List<Bundle> list, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle) {
                IBinder iBinder;
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    if (iShareResultCallback != null) {
                        iBinder = iShareResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(12, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().requestShareWithPendingIntent(str, str2, list, iShareResultCallback, pendingIntent, bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSharedItem(String str, String str2, String str3, ISharedItemResultCallback iSharedItemResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (iSharedItemResultCallback != null) {
                        iBinder = iSharedItemResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(13, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItem(str, str2, str3, iSharedItemResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSharedItemDeletion(String str, String str2, String str3, ISharedItemDeletionResultCallback iSharedItemDeletionResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (iSharedItemDeletionResultCallback != null) {
                        iBinder = iSharedItemDeletionResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(14, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemDeletion(str, str2, str3, iSharedItemDeletionResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSharedItemDeletionWithData(String str, String str2, String str3, Bundle bundle, ISharedItemDeletionResultCallback iSharedItemDeletionResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSharedItemDeletionResultCallback != null) {
                        iBinder = iSharedItemDeletionResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(138, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemDeletionWithData(str, str2, str3, bundle, iSharedItemDeletionResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSharedItemList(String str, String str2, String str3, String str4, ISharedItemListResultCallback iSharedItemListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (iSharedItemListResultCallback != null) {
                        iBinder = iSharedItemListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(65, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemList(str, str2, str3, str4, iSharedItemListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSharedItemListDeletion(String str, String str2, List<String> list, ISharedItemListDeletionResultCallback iSharedItemListDeletionResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringList(list);
                    if (iSharedItemListDeletionResultCallback != null) {
                        iBinder = iSharedItemListDeletionResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(76, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemListDeletion(str, str2, list, iSharedItemListDeletionResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSharedItemListDeletionWithData(String str, String str2, List<String> list, Bundle bundle, ISharedItemListDeletionResultCallback iSharedItemListDeletionResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringList(list);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSharedItemListDeletionResultCallback != null) {
                        iBinder = iSharedItemListDeletionResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(98, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemListDeletionWithData(str, str2, list, bundle, iSharedItemListDeletionResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSharedItemListInTrashBinWithData(Bundle bundle, ISharedItemListResultCallback iSharedItemListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSharedItemListResultCallback != null) {
                        iBinder = iSharedItemListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(154, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemListInTrashBinWithData(bundle, iSharedItemListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public String requestSharedItemListUpdate(String str, String str2, List<Bundle> list, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle) {
                IBinder iBinder;
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeTypedList(list);
                    if (iShareResultCallback != null) {
                        iBinder = iShareResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(78, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().requestSharedItemListUpdate(str, str2, list, iShareResultCallback, pendingIntent, bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSharedItemListWithFileList(String str, String str2, String str3, String str4, ISharedItemListResultCallback iSharedItemListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (iSharedItemListResultCallback != null) {
                        iBinder = iSharedItemListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(66, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemListWithFileList(str, str2, str3, str4, iSharedItemListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSharedItemListWithFileListWithData(String str, String str2, String str3, String str4, Bundle bundle, ISharedItemListResultCallback iSharedItemListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSharedItemListResultCallback != null) {
                        iBinder = iSharedItemListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(103, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemListWithFileListWithData(str, str2, str3, str4, bundle, iSharedItemListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSharedItemSync(String str, String str2, IShareSyncResultCallback iShareSyncResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iShareSyncResultCallback != null) {
                        iBinder = iShareSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(31, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemSync(str, str2, iShareSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSharedItemSyncWithData(String str, String str2, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareSyncResultCallback != null) {
                        iBinder = iShareSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(140, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemSyncWithData(str, str2, bundle, iShareSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestSharedItemSyncWithResolution(String str, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareSyncResultCallback != null) {
                        iBinder = iShareSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(82, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestSharedItemSyncWithResolution(str, bundle, iShareSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSharedItemSyncWithResolutionWithData(String str, Bundle bundle, Bundle bundle2, IShareSyncResultCallback iShareSyncResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareSyncResultCallback != null) {
                        iBinder = iShareSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(97, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemSyncWithResolutionWithData(str, bundle, bundle2, iShareSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public String requestSharedItemUpdate(String str, String str2, String str3, Bundle bundle, ISharedItemUpdateResultCallback iSharedItemUpdateResultCallback, PendingIntent pendingIntent, Bundle bundle2) {
                IBinder iBinder;
                String readString;
                PendingIntent pendingIntent2 = pendingIntent;
                Bundle bundle3 = bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSharedItemUpdateResultCallback != null) {
                        iBinder = iSharedItemUpdateResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (pendingIntent2 != null) {
                        obtain.writeInt(1);
                        pendingIntent2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle3 != null) {
                        obtain.writeInt(1);
                        bundle3.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(60, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().requestSharedItemUpdate(str, str2, str3, bundle, iSharedItemUpdateResultCallback, pendingIntent2, bundle3);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSharedItemWithData(String str, String str2, String str3, Bundle bundle, ISharedItemResultCallback iSharedItemResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSharedItemResultCallback != null) {
                        iBinder = iSharedItemResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(141, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemWithData(str, str2, str3, bundle, iSharedItemResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSharedItemWithGroupId(String str, String str2, String str3, String str4, ISharedItemResultCallback iSharedItemResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (iSharedItemResultCallback != null) {
                        iBinder = iSharedItemResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(72, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemWithGroupId(str, str2, str3, str4, iSharedItemResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSharedItemWithGroupIdWithData(String str, String str2, String str3, String str4, Bundle bundle, ISharedItemResultCallback iSharedItemResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSharedItemResultCallback != null) {
                        iBinder = iSharedItemResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(142, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSharedItemWithGroupIdWithData(str, str2, str3, str4, bundle, iSharedItemResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSpace(String str, String str2, ISpaceResultCallback iSpaceResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iSpaceResultCallback != null) {
                        iBinder = iSpaceResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(15, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSpace(str, str2, iSpaceResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSpaceCreation(String str, String str2, Bundle bundle, ISpaceResultCallback iSpaceResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSpaceResultCallback != null) {
                        iBinder = iSpaceResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(16, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSpaceCreation(str, str2, bundle, iSpaceResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSpaceCreationWithData(String str, String str2, Bundle bundle, Bundle bundle2, ISpaceResultCallback iSpaceResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSpaceResultCallback != null) {
                        iBinder = iSpaceResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(90, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSpaceCreationWithData(str, str2, bundle, bundle2, iSpaceResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSpaceDeletion(String str, String str2, ISpaceDeletionResultCallback iSpaceDeletionResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iSpaceDeletionResultCallback != null) {
                        iBinder = iSpaceDeletionResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(17, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSpaceDeletion(str, str2, iSpaceDeletionResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSpaceDeletionWithData(String str, String str2, Bundle bundle, ISpaceDeletionResultCallback iSpaceDeletionResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSpaceDeletionResultCallback != null) {
                        iBinder = iSpaceDeletionResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(91, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSpaceDeletionWithData(str, str2, bundle, iSpaceDeletionResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSpaceList(String str, String str2, ISpaceListResultCallback iSpaceListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iSpaceListResultCallback != null) {
                        iBinder = iSpaceListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(18, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSpaceList(str, str2, iSpaceListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSpaceListSync(String str, IShareSyncResultCallback iShareSyncResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (iShareSyncResultCallback != null) {
                        iBinder = iShareSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(30, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSpaceListSync(str, iShareSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSpaceListSyncWithData(String str, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iShareSyncResultCallback != null) {
                        iBinder = iShareSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(96, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSpaceListSyncWithData(str, bundle, iShareSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSpaceListWithData(String str, String str2, Bundle bundle, ISpaceListResultCallback iSpaceListResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSpaceListResultCallback != null) {
                        iBinder = iSpaceListResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(92, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSpaceListWithData(str, str2, bundle, iSpaceListResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSpaceUpdate(String str, String str2, Bundle bundle, ISpaceResultCallback iSpaceResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSpaceResultCallback != null) {
                        iBinder = iSpaceResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(19, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSpaceUpdate(str, str2, bundle, iSpaceResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestSpaceUpdateWithData(String str, String str2, Bundle bundle, Bundle bundle2, ISpaceResultCallback iSpaceResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSpaceResultCallback != null) {
                        iBinder = iSpaceResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(93, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSpaceUpdateWithData(str, str2, bundle, bundle2, iSpaceResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestSpaceWithData(String str, String str2, Bundle bundle, ISpaceResultCallback iSpaceResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iSpaceResultCallback != null) {
                        iBinder = iSpaceResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(89, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestSpaceWithData(str, str2, bundle, iSpaceResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestSync(ISyncResultCallback iSyncResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iSyncResultCallback != null) {
                        iBinder = iSyncResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestSync(iSyncResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int requestThumbnailDownload(String str, String str2, String str3, String str4, String str5, String str6, IDownloadThumbnailResultCallback iDownloadThumbnailResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    String str7 = str5;
                    obtain.writeString(str7);
                    String str8 = str6;
                    obtain.writeString(str8);
                    if (iDownloadThumbnailResultCallback != null) {
                        iBinder = iDownloadThumbnailResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(111, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestThumbnailDownload(str, str2, str3, str4, str7, str8, iDownloadThumbnailResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public int requestWebLinkEnabled(Bundle bundle, boolean z, IBundleResultCallback iBundleResultCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(z ? 1 : 0);
                    if (iBundleResultCallback != null) {
                        iBinder = iBundleResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(117, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().requestWebLinkEnabled(bundle, z, iBundleResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int resumeShare(String str, String str2) {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(25, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().resumeShare(str, str2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle setBuddyActivityListRead(Bundle bundle) {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(47, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle2 = null;
                        }
                    } else {
                        bundle2 = Stub.getDefaultImpl().setBuddyActivityListRead(bundle);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public boolean setDisclaimerAgreementForSocial(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(104, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() == 0) {
                            z = false;
                        }
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    }
                    boolean disclaimerAgreementForSocial = Stub.getDefaultImpl().setDisclaimerAgreementForSocial(bundle);
                    obtain2.recycle();
                    obtain.recycle();
                    return disclaimerAgreementForSocial;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int setShareStatusListener(String str, String str2, IShareStatusCallback iShareStatusCallback) {
                IBinder iBinder;
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (iShareStatusCallback != null) {
                        iBinder = iShareStatusCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(28, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    } else {
                        readInt = Stub.getDefaultImpl().setShareStatusListener(str, str2, iShareStatusCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMobileServiceSocial asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMobileServiceSocial)) {
                return new Proxy(iBinder);
            }
            return (IMobileServiceSocial) queryLocalInterface;
        }

        public static IMobileServiceSocial getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IMobileServiceSocial iMobileServiceSocial) {
            if (Proxy.sDefaultImpl != null || iMobileServiceSocial == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMobileServiceSocial;
            return true;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v287, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v291, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v33, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v296, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v38, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v360, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v55, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v506, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v65, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v511, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v68, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v516, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v71, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v161, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v74, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v527, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v77, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v530, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v80, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v535, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v83, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v540, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v86, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v545, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v89, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v550, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v92, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v555, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v95, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v560, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v98, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v565, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v101, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v570, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v104, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v164, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v107, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v167, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v110, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v170, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v113, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v578, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v116, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v583, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v119, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v586, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v122, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r13, android.os.Parcel r14, android.os.Parcel r15, int r16) {
            /*
                r12 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                java.lang.String r2 = "com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial"
                if (r13 == r0) goto L_0x1939
                r0 = 0
                r3 = 0
                switch(r13) {
                    case 1: goto L_0x1927;
                    case 2: goto L_0x1915;
                    case 3: goto L_0x18ff;
                    case 4: goto L_0x18e9;
                    case 5: goto L_0x18d3;
                    case 6: goto L_0x18c1;
                    case 7: goto L_0x18a7;
                    case 8: goto L_0x188d;
                    case 9: goto L_0x186f;
                    case 10: goto L_0x185d;
                    case 11: goto L_0x1843;
                    case 12: goto L_0x1800;
                    case 13: goto L_0x17de;
                    case 14: goto L_0x17bc;
                    case 15: goto L_0x179e;
                    case 16: goto L_0x1771;
                    case 17: goto L_0x1753;
                    case 18: goto L_0x1735;
                    case 19: goto L_0x1708;
                    case 20: goto L_0x16ee;
                    case 21: goto L_0x16d0;
                    case 22: goto L_0x16b2;
                    case 23: goto L_0x166e;
                    case 24: goto L_0x1658;
                    case 25: goto L_0x1642;
                    case 26: goto L_0x162c;
                    case 27: goto L_0x1616;
                    case 28: goto L_0x15f8;
                    case 29: goto L_0x15e6;
                    case 30: goto L_0x15cc;
                    case 31: goto L_0x15ae;
                    case 32: goto L_0x1575;
                    case 33: goto L_0x1557;
                    case 34: goto L_0x152a;
                    case 35: goto L_0x150c;
                    case 36: goto L_0x14ee;
                    case 37: goto L_0x14d0;
                    case 38: goto L_0x14b6;
                    case 39: goto L_0x1494;
                    case 40: goto L_0x1476;
                    case 41: goto L_0x1454;
                    case 42: goto L_0x1425;
                    case 43: goto L_0x13ff;
                    case 44: goto L_0x13de;
                    case 45: goto L_0x13bd;
                    case 46: goto L_0x1397;
                    case 47: goto L_0x1371;
                    case 48: goto L_0x1350;
                    case 49: goto L_0x133e;
                    case 50: goto L_0x132c;
                    case 51: goto L_0x130b;
                    case 52: goto L_0x12ea;
                    case 53: goto L_0x12c9;
                    case 54: goto L_0x12a8;
                    case 55: goto L_0x1287;
                    case 56: goto L_0x1266;
                    case 57: goto L_0x1245;
                    case 58: goto L_0x1224;
                    case 59: goto L_0x11fe;
                    case 60: goto L_0x11a9;
                    case 61: goto L_0x119b;
                    case 62: goto L_0x114a;
                    case 63: goto L_0x1102;
                    case 64: goto L_0x10ad;
                    case 65: goto L_0x1086;
                    case 66: goto L_0x105f;
                    case 67: goto L_0x1039;
                    case 68: goto L_0x1022;
                    case 69: goto L_0x1001;
                    case 70: goto L_0x0fe0;
                    case 71: goto L_0x0fbe;
                    case 72: goto L_0x0f97;
                    case 73: goto L_0x0f6a;
                    case 74: goto L_0x0f58;
                    case 75: goto L_0x0f10;
                    case 76: goto L_0x0eee;
                    case 77: goto L_0x0eaa;
                    case 78: goto L_0x0e66;
                    case 79: goto L_0x0e22;
                    case 80: goto L_0x0dd6;
                    case 81: goto L_0x0db0;
                    case 82: goto L_0x0d8b;
                    case 83: goto L_0x0d4d;
                    case 84: goto L_0x0d2c;
                    case 85: goto L_0x0d06;
                    case 86: goto L_0x0ce5;
                    case 87: goto L_0x0ccb;
                    case 88: goto L_0x0ca2;
                    case 89: goto L_0x0c75;
                    case 90: goto L_0x0c35;
                    case 91: goto L_0x0c08;
                    case 92: goto L_0x0bdb;
                    case 93: goto L_0x0b9b;
                    case 94: goto L_0x0b6f;
                    case 95: goto L_0x0b4b;
                    case 96: goto L_0x0b1f;
                    case 97: goto L_0x0ae6;
                    case 98: goto L_0x0ab3;
                    case 99: goto L_0x0a60;
                    case 100: goto L_0x0a0d;
                    case 101: goto L_0x09b0;
                    case 102: goto L_0x0957;
                    case 103: goto L_0x0920;
                    case 104: goto L_0x0903;
                    case 105: goto L_0x08f5;
                    case 106: goto L_0x08d8;
                    case 107: goto L_0x08c1;
                    case 108: goto L_0x08a4;
                    case 109: goto L_0x088e;
                    case 110: goto L_0x086d;
                    case 111: goto L_0x083e;
                    case 112: goto L_0x0828;
                    case 113: goto L_0x0800;
                    case 114: goto L_0x07cd;
                    case 115: goto L_0x07b6;
                    case 116: goto L_0x078d;
                    case 117: goto L_0x0761;
                    case 118: goto L_0x073b;
                    case 119: goto L_0x0715;
                    case 120: goto L_0x06f0;
                    case 121: goto L_0x06cb;
                    case 122: goto L_0x06a6;
                    case 123: goto L_0x0681;
                    case 124: goto L_0x0664;
                    case 125: goto L_0x061f;
                    case 126: goto L_0x05fa;
                    case 127: goto L_0x05c5;
                    case 128: goto L_0x05a0;
                    case 129: goto L_0x057b;
                    case 130: goto L_0x0556;
                    case 131: goto L_0x0531;
                    case 132: goto L_0x050c;
                    case 133: goto L_0x04e7;
                    case 134: goto L_0x04c2;
                    case 135: goto L_0x049d;
                    case 136: goto L_0x0478;
                    case 137: goto L_0x0416;
                    case 138: goto L_0x03e3;
                    case 139: goto L_0x03b6;
                    case 140: goto L_0x0389;
                    case 141: goto L_0x0356;
                    case 142: goto L_0x031f;
                    case 143: goto L_0x02f9;
                    case 144: goto L_0x02d4;
                    case 145: goto L_0x02af;
                    case 146: goto L_0x027a;
                    case 147: goto L_0x0255;
                    case 148: goto L_0x022f;
                    case 149: goto L_0x020a;
                    case 150: goto L_0x01e5;
                    case 151: goto L_0x01c0;
                    case 152: goto L_0x019b;
                    case 153: goto L_0x0176;
                    case 154: goto L_0x0151;
                    case 155: goto L_0x0130;
                    case 156: goto L_0x010b;
                    case 157: goto L_0x00e2;
                    case 158: goto L_0x00b9;
                    case 159: goto L_0x0090;
                    case 160: goto L_0x006b;
                    case 161: goto L_0x0046;
                    case 162: goto L_0x0029;
                    case 163: goto L_0x0012;
                    default: goto L_0x000d;
                }
            L_0x000d:
                boolean r12 = super.onTransact(r13, r14, r15, r16)
                return r12
            L_0x0012:
                r14.enforceInterface(r2)
                android.os.Bundle r12 = r12.getBuddySyncTime()
                r15.writeNoException()
                if (r12 == 0) goto L_0x0025
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x0028
            L_0x0025:
                r15.writeInt(r0)
            L_0x0028:
                return r1
            L_0x0029:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x003b
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x003b:
                int r12 = r12.getDisclosureScope(r3)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0046:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x0058
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0058:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback r13 = com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestDisclosureScopeUpdate(r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x006b:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x007d
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x007d:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback r13 = com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestDisclosureScope(r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0090:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x00a6
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                r3 = r0
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x00a6:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback r14 = com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback.Stub.asInterface(r14)
                int r12 = r12.dataSyncGetSyncedData(r13, r3, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x00b9:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x00cf
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                r3 = r0
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x00cf:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback r14 = com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback.Stub.asInterface(r14)
                int r12 = r12.dataSyncDelete(r13, r3, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x00e2:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x00f8
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                r3 = r0
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x00f8:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback r14 = com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback.Stub.asInterface(r14)
                int r12 = r12.dataSyncUpsert(r13, r3, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x010b:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x011d
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x011d:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareBulkItemResultCallback r13 = com.samsung.android.sdk.mobileservice.social.share.IShareBulkItemResultCallback.Stub.asInterface(r13)
                java.lang.String r12 = r12.requestBulkItemShare(r3, r13)
                r15.writeNoException()
                r15.writeString(r12)
                return r1
            L_0x0130:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x0142
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0142:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.buddy.IBuddyInfoResultCallback r13 = com.samsung.android.sdk.mobileservice.social.buddy.IBuddyInfoResultCallback.Stub.asInterface(r13)
                r12.requestAppUpdateAndLaunchForBuddy(r3, r13)
                r15.writeNoException()
                return r1
            L_0x0151:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x0163
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0163:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemListResultCallback r13 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemListResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestSharedItemListInTrashBinWithData(r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0176:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x0188
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0188:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ITrashBinResultCallback r13 = com.samsung.android.sdk.mobileservice.social.share.ITrashBinResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestDeleteAllItemsFromTrashBinWithData(r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x019b:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x01ad
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x01ad:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ITrashBinResultCallback r13 = com.samsung.android.sdk.mobileservice.social.share.ITrashBinResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestDeleteItemListFromTrashBinWithData(r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x01c0:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x01d2
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x01d2:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ITrashBinResultCallback r13 = com.samsung.android.sdk.mobileservice.social.share.ITrashBinResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestRestoreItemListFromTrashBinWithData(r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x01e5:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x01f7
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x01f7:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ITrashBinResultCallback r13 = com.samsung.android.sdk.mobileservice.social.share.ITrashBinResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestMoveItemListToTrashBinWithData(r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x020a:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x021c
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x021c:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IMemberListResultCallback r13 = com.samsung.android.sdk.mobileservice.social.group.IMemberListResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestFamilyGroupMemberListWithData(r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x022f:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x0241
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0241:
                android.os.Bundle r12 = r12.getFamilyQuotaWithData(r3)
                r15.writeNoException()
                if (r12 == 0) goto L_0x0251
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x0254
            L_0x0251:
                r15.writeInt(r0)
            L_0x0254:
                return r1
            L_0x0255:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x0267
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0267:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r13 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestFamilyGroupDeletionWithData(r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x027a:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x028c
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
                goto L_0x028d
            L_0x028c:
                r13 = r3
            L_0x028d:
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x029c
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                r3 = r0
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x029c:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestFamilyGroupCreationWithData(r13, r3, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x02af:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x02c1
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x02c1:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback r13 = com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestInvitationLinkDeletion(r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x02d4:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x02e6
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x02e6:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback r13 = com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestInvitationLinkCreation(r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x02f9:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                if (r13 == 0) goto L_0x030b
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x030b:
                android.os.Bundle r12 = r12.getVerifiedOriginalFileListWithData(r3)
                r15.writeNoException()
                if (r12 == 0) goto L_0x031b
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x031e
            L_0x031b:
                r15.writeInt(r0)
            L_0x031e:
                return r1
            L_0x031f:
                r14.enforceInterface(r2)
                r13 = r3
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                java.lang.String r6 = r14.readString()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0341
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0341:
                r7 = r13
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemResultCallback r8 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemResultCallback.Stub.asInterface(r13)
                r2 = r12
                int r12 = r2.requestSharedItemWithGroupIdWithData(r3, r4, r5, r6, r7, r8)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0356:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0374
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0374:
                r6 = r13
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemResultCallback.Stub.asInterface(r13)
                r2 = r12
                int r12 = r2.requestSharedItemWithData(r3, r4, r5, r6, r7)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0389:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x03a3
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x03a3:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSharedItemSyncWithData(r2, r3, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x03b6:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x03d0
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x03d0:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceCoverImageDownloadingResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISpaceCoverImageDownloadingResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestOriginalSpaceImageDownloadWithData(r2, r3, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x03e3:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0401
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0401:
                r6 = r13
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemDeletionResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemDeletionResultCallback.Stub.asInterface(r13)
                r2 = r12
                int r12 = r2.requestSharedItemDeletionWithData(r3, r4, r5, r6, r7)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0416:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0432
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r5 = r0
                goto L_0x0433
            L_0x0432:
                r5 = r13
            L_0x0433:
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0443
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r6 = r0
                goto L_0x0444
            L_0x0443:
                r6 = r13
            L_0x0444:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareResultWithFileListCallback r7 = com.samsung.android.sdk.mobileservice.social.share.IShareResultWithFileListCallback.Stub.asInterface(r0)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x045c
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
                r8 = r0
                goto L_0x045d
            L_0x045c:
                r8 = r13
            L_0x045d:
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x046b
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x046b:
                r2 = r12
                r9 = r13
                java.lang.String r12 = r2.requestShareWithFileListWithData(r3, r4, r5, r6, r7, r8, r9)
                r15.writeNoException()
                r15.writeString(r12)
                return r1
            L_0x0478:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x048a
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x048a:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupAuthorityChange(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x049d:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x04af
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x04af:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestDelegateAuthorityOfOwnerWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x04c2:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x04d4
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x04d4:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupPendingInvitationCancellationWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x04e7:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x04f9
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x04f9:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestLeaveWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x050c:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x051e
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x051e:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupMemberRemovalWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0531:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0543
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0543:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupListWithInvitationResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupListWithInvitationResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupWithInvitationListWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0556:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0568
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0568:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IMemberListResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IMemberListResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupMemberListWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x057b:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x058d
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x058d:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupInvitationRejectionWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x05a0:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x05b2
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x05b2:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupInvitationAcceptanceWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x05c5:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x05d8
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.os.Bundle r0 = (android.os.Bundle) r0
                goto L_0x05d9
            L_0x05d8:
                r0 = r13
            L_0x05d9:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x05e7
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x05e7:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupInvitationResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupInvitationResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupMemberInvitationWithData(r0, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x05fa:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x060c
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x060c:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupDeletionWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x061f:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0632
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.os.Bundle r0 = (android.os.Bundle) r0
                goto L_0x0633
            L_0x0632:
                r0 = r13
            L_0x0633:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0642
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                android.os.Bundle r2 = (android.os.Bundle) r2
                goto L_0x0643
            L_0x0642:
                r2 = r13
            L_0x0643:
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x0651
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0651:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupInvitationResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupInvitationResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupCreationWithData(r0, r2, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0664:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0676
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0676:
                java.util.List r12 = r12.getGroupListWithData(r13)
                r15.writeNoException()
                r15.writeTypedList(r12)
                return r1
            L_0x0681:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0693
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0693:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupSyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupSyncResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupSyncWithoutImageWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x06a6:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x06b8
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x06b8:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupSyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupSyncResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupSyncWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x06cb:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x06dd
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x06dd:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupListResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupListResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupListWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x06f0:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0702
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0702:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupWithData(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0715:
                r13 = r3
                r14.enforceInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0727
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0727:
                android.os.Bundle r12 = r12.isPermissionGranted(r13)
                r15.writeNoException()
                if (r12 == 0) goto L_0x0737
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x073a
            L_0x0737:
                r15.writeInt(r0)
            L_0x073a:
                return r1
            L_0x073b:
                r13 = r3
                r14.enforceInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x074d
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x074d:
                android.os.Bundle r12 = r12.getQuotaWithData(r13)
                r15.writeNoException()
                if (r12 == 0) goto L_0x075d
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x0760
            L_0x075d:
                r15.writeInt(r0)
            L_0x0760:
                return r1
            L_0x0761:
                r13 = r3
                r14.enforceInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0773
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0773:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x077a
                r0 = r1
            L_0x077a:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback r14 = com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestWebLinkEnabled(r13, r0, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x078d:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x07a3
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x07a3:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestShareSyncWithOption(r0, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x07b6:
                r14.enforceInterface(r2)
                android.os.Bundle r12 = r12.getQuota()
                r15.writeNoException()
                if (r12 == 0) goto L_0x07c9
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x07cc
            L_0x07c9:
                r15.writeInt(r0)
            L_0x07cc:
                return r1
            L_0x07cd:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.util.ArrayList r5 = r14.createTypedArrayList(r0)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x07eb
                java.lang.Object r13 = r0.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x07eb:
                r6 = r13
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback.Stub.asInterface(r13)
                r2 = r12
                java.lang.String r12 = r2.requestShareWithData(r3, r4, r5, r6, r7)
                r15.writeNoException()
                r15.writeString(r12)
                return r1
            L_0x0800:
                r13 = r3
                r14.enforceInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0814
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
                goto L_0x0815
            L_0x0814:
                r3 = r13
            L_0x0815:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback r13 = com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestOriginalSharedContentsDownloadToHiddenFolder(r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0828:
                r14.enforceInterface(r2)
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback r13 = com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestServiceNumber(r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x083e:
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                java.lang.String r6 = r14.readString()
                java.lang.String r7 = r14.readString()
                java.lang.String r8 = r14.readString()
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IDownloadThumbnailResultCallback r9 = com.samsung.android.sdk.mobileservice.social.share.IDownloadThumbnailResultCallback.Stub.asInterface(r13)
                r2 = r12
                int r12 = r2.requestThumbnailDownload(r3, r4, r5, r6, r7, r8, r9)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x086d:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x087f
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x087f:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.buddy.IBuddyInfoResultCallback r14 = com.samsung.android.sdk.mobileservice.social.buddy.IBuddyInfoResultCallback.Stub.asInterface(r14)
                r12.getBuddyInfo(r13, r14)
                r15.writeNoException()
                return r1
            L_0x088e:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.buddy.ISyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.buddy.ISyncResultCallback.Stub.asInterface(r14)
                r12.requestBuddySync(r13, r14)
                r15.writeNoException()
                return r1
            L_0x08a4:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x08b6
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x08b6:
                boolean r12 = r12.isServiceRegistered(r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x08c1:
                r14.enforceInterface(r2)
                android.os.Bundle r12 = r12.getDeviceAuthInfoCached()
                r15.writeNoException()
                if (r12 == 0) goto L_0x08d4
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x08d7
            L_0x08d4:
                r15.writeInt(r0)
            L_0x08d7:
                return r1
            L_0x08d8:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x08ea
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x08ea:
                boolean r12 = r12.getDisclaimerAgreementForService(r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x08f5:
                r14.enforceInterface(r2)
                boolean r12 = r12.getDisclaimerAgreementForSocial()
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0903:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0915
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0915:
                boolean r12 = r12.setDisclaimerAgreementForSocial(r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0920:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                java.lang.String r6 = r14.readString()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0942
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0942:
                r7 = r13
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemListResultCallback r8 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemListResultCallback.Stub.asInterface(r13)
                r2 = r12
                int r12 = r2.requestSharedItemListWithFileListWithData(r3, r4, r5, r6, r7, r8)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0957:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                java.util.ArrayList r6 = r14.createStringArrayList()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x097b
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r7 = r0
                goto L_0x097c
            L_0x097b:
                r7 = r13
            L_0x097c:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback r8 = com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback.Stub.asInterface(r0)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0994
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
                r9 = r0
                goto L_0x0995
            L_0x0994:
                r9 = r13
            L_0x0995:
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x09a3
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x09a3:
                r2 = r12
                r10 = r13
                int r12 = r2.requestOriginalSharedContentWithFileListDownloadWithData(r3, r4, r5, r6, r7, r8, r9, r10)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x09b0:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                java.util.ArrayList r6 = r14.createStringArrayList()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x09d4
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r7 = r0
                goto L_0x09d5
            L_0x09d4:
                r7 = r13
            L_0x09d5:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback r8 = com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback.Stub.asInterface(r0)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x09ed
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
                r9 = r0
                goto L_0x09ee
            L_0x09ed:
                r9 = r13
            L_0x09ee:
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x09fc
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x09fc:
                r10 = r13
                java.lang.String r11 = r14.readString()
                r2 = r12
                int r12 = r2.requestOriginalSharedContentWithItemFileListDownloadWithPathWithData(r3, r4, r5, r6, r7, r8, r9, r10, r11)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0a0d:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.util.ArrayList r5 = r14.createTypedArrayList(r0)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0a2d
                java.lang.Object r2 = r0.createFromParcel(r14)
                android.os.Bundle r2 = (android.os.Bundle) r2
                r6 = r2
                goto L_0x0a2e
            L_0x0a2d:
                r6 = r13
            L_0x0a2e:
                android.os.IBinder r2 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback.Stub.asInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0a46
                android.os.Parcelable$Creator r2 = android.app.PendingIntent.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                android.app.PendingIntent r2 = (android.app.PendingIntent) r2
                r8 = r2
                goto L_0x0a47
            L_0x0a46:
                r8 = r13
            L_0x0a47:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0a53
                java.lang.Object r13 = r0.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0a53:
                r2 = r12
                r9 = r13
                java.lang.String r12 = r2.requestShareListUpdateWithItemFileListWithData(r3, r4, r5, r6, r7, r8, r9)
                r15.writeNoException()
                r15.writeString(r12)
                return r1
            L_0x0a60:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.util.ArrayList r5 = r14.createTypedArrayList(r0)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0a80
                java.lang.Object r2 = r0.createFromParcel(r14)
                android.os.Bundle r2 = (android.os.Bundle) r2
                r6 = r2
                goto L_0x0a81
            L_0x0a80:
                r6 = r13
            L_0x0a81:
                android.os.IBinder r2 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback.Stub.asInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0a99
                android.os.Parcelable$Creator r2 = android.app.PendingIntent.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                android.app.PendingIntent r2 = (android.app.PendingIntent) r2
                r8 = r2
                goto L_0x0a9a
            L_0x0a99:
                r8 = r13
            L_0x0a9a:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0aa6
                java.lang.Object r13 = r0.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0aa6:
                r2 = r12
                r9 = r13
                java.lang.String r12 = r2.requestShareWithItemFileListWithData(r3, r4, r5, r6, r7, r8, r9)
                r15.writeNoException()
                r15.writeString(r12)
                return r1
            L_0x0ab3:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.util.ArrayList r5 = r14.createStringArrayList()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0ad1
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0ad1:
                r6 = r13
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemListDeletionResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemListDeletionResultCallback.Stub.asInterface(r13)
                r2 = r12
                int r12 = r2.requestSharedItemListDeletionWithData(r3, r4, r5, r6, r7)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0ae6:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                int r3 = r14.readInt()
                if (r3 == 0) goto L_0x0afd
                android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r14)
                android.os.Bundle r3 = (android.os.Bundle) r3
                goto L_0x0afe
            L_0x0afd:
                r3 = r13
            L_0x0afe:
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x0b0c
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0b0c:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSharedItemSyncWithResolutionWithData(r2, r3, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0b1f:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                int r3 = r14.readInt()
                if (r3 == 0) goto L_0x0b37
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
                goto L_0x0b38
            L_0x0b37:
                r3 = r13
            L_0x0b38:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback r13 = com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestSpaceListSyncWithData(r2, r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0b4b:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                int r3 = r14.readInt()
                if (r3 == 0) goto L_0x0b63
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
                goto L_0x0b64
            L_0x0b63:
                r3 = r13
            L_0x0b64:
                java.lang.String r13 = r14.readString()
                r12.clearSpaceUnreadCountWithData(r2, r3, r13)
                r15.writeNoException()
                return r1
            L_0x0b6f:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                int r3 = r14.readInt()
                if (r3 == 0) goto L_0x0b87
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                r3 = r13
                android.os.Bundle r3 = (android.os.Bundle) r3
                goto L_0x0b88
            L_0x0b87:
                r3 = r13
            L_0x0b88:
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceListResultCallback r13 = com.samsung.android.sdk.mobileservice.social.share.ISpaceListResultCallback.Stub.asInterface(r13)
                int r12 = r12.requestAllSpaceListWithData(r2, r3, r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0b9b:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0bb7
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                android.os.Bundle r2 = (android.os.Bundle) r2
                r5 = r2
                goto L_0x0bb8
            L_0x0bb7:
                r5 = r13
            L_0x0bb8:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0bc6
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0bc6:
                r6 = r13
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback.Stub.asInterface(r13)
                r2 = r12
                int r12 = r2.requestSpaceUpdateWithData(r3, r4, r5, r6, r7)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0bdb:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x0bf5
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0bf5:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceListResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISpaceListResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSpaceListWithData(r2, r3, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0c08:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x0c22
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0c22:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceDeletionResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISpaceDeletionResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSpaceDeletionWithData(r2, r3, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0c35:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0c51
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                android.os.Bundle r2 = (android.os.Bundle) r2
                r5 = r2
                goto L_0x0c52
            L_0x0c51:
                r5 = r13
            L_0x0c52:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0c60
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0c60:
                r6 = r13
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback.Stub.asInterface(r13)
                r2 = r12
                int r12 = r2.requestSpaceCreationWithData(r3, r4, r5, r6, r7)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0c75:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                java.lang.String r2 = r14.readString()
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x0c8f
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0c8f:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSpaceWithData(r0, r2, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0ca2:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0cb8
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0cb8:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestShareSyncWithData(r0, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0ccb:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupSyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupSyncResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupSyncWithoutImage(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0ce5:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0cf7
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0cf7:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.activity.IActivityBundleResultCallback r14 = com.samsung.android.sdk.mobileservice.social.activity.IActivityBundleResultCallback.Stub.asInterface(r14)
                r12.requestActivityContent(r13, r14)
                r15.writeNoException()
                return r1
            L_0x0d06:
                r13 = r3
                r14.enforceInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0d18
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0d18:
                android.os.Bundle r12 = r12.requestContentsController(r13)
                r15.writeNoException()
                if (r12 == 0) goto L_0x0d28
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x0d2b
            L_0x0d28:
                r15.writeInt(r0)
            L_0x0d2b:
                return r1
            L_0x0d2c:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0d3e
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0d3e:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.activity.IActivityBundleResultCallback r14 = com.samsung.android.sdk.mobileservice.social.activity.IActivityBundleResultCallback.Stub.asInterface(r14)
                r12.requestActivityContentStreamingUrl(r13, r14)
                r15.writeNoException()
                return r1
            L_0x0d4d:
                r13 = r3
                r14.enforceInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0d60
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                android.os.Bundle r2 = (android.os.Bundle) r2
                goto L_0x0d61
            L_0x0d60:
                r2 = r13
            L_0x0d61:
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x0d6f
                android.os.Parcelable$Creator r13 = android.app.PendingIntent.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.app.PendingIntent r13 = (android.app.PendingIntent) r13
            L_0x0d6f:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.common.IBundleProgressResultCallback r14 = com.samsung.android.sdk.mobileservice.social.common.IBundleProgressResultCallback.Stub.asInterface(r14)
                android.os.Bundle r12 = r12.requestActivityPosting(r2, r13, r14)
                r15.writeNoException()
                if (r12 == 0) goto L_0x0d87
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x0d8a
            L_0x0d87:
                r15.writeInt(r0)
            L_0x0d8a:
                return r1
            L_0x0d8b:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0da1
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0da1:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback.Stub.asInterface(r14)
                r12.requestSharedItemSyncWithResolution(r0, r13, r14)
                r15.writeNoException()
                return r1
            L_0x0db0:
                r13 = r3
                r14.enforceInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0dc2
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0dc2:
                android.os.Bundle r12 = r12.getBuddyActivityCount(r13)
                r15.writeNoException()
                if (r12 == 0) goto L_0x0dd2
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x0dd5
            L_0x0dd2:
                r15.writeInt(r0)
            L_0x0dd5:
                return r1
            L_0x0dd6:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                java.util.ArrayList r6 = r14.createStringArrayList()
                android.os.IBinder r0 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback.Stub.asInterface(r0)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0e02
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
                r8 = r0
                goto L_0x0e03
            L_0x0e02:
                r8 = r13
            L_0x0e03:
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0e11
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0e11:
                r9 = r13
                java.lang.String r10 = r14.readString()
                r2 = r12
                int r12 = r2.requestOriginalSharedContentWithItemFileListDownloadWithPath(r3, r4, r5, r6, r7, r8, r9, r10)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0e22:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.util.ArrayList r5 = r14.createTypedArrayList(r0)
                android.os.IBinder r2 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback r6 = com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback.Stub.asInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0e4c
                android.os.Parcelable$Creator r2 = android.app.PendingIntent.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                android.app.PendingIntent r2 = (android.app.PendingIntent) r2
                r7 = r2
                goto L_0x0e4d
            L_0x0e4c:
                r7 = r13
            L_0x0e4d:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0e59
                java.lang.Object r13 = r0.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0e59:
                r2 = r12
                r8 = r13
                java.lang.String r12 = r2.requestShareListUpdateWithItemFileList(r3, r4, r5, r6, r7, r8)
                r15.writeNoException()
                r15.writeString(r12)
                return r1
            L_0x0e66:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.util.ArrayList r5 = r14.createTypedArrayList(r0)
                android.os.IBinder r2 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback r6 = com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback.Stub.asInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0e90
                android.os.Parcelable$Creator r2 = android.app.PendingIntent.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                android.app.PendingIntent r2 = (android.app.PendingIntent) r2
                r7 = r2
                goto L_0x0e91
            L_0x0e90:
                r7 = r13
            L_0x0e91:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0e9d
                java.lang.Object r13 = r0.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0e9d:
                r2 = r12
                r8 = r13
                java.lang.String r12 = r2.requestSharedItemListUpdate(r3, r4, r5, r6, r7, r8)
                r15.writeNoException()
                r15.writeString(r12)
                return r1
            L_0x0eaa:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.util.ArrayList r5 = r14.createTypedArrayList(r0)
                android.os.IBinder r2 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback r6 = com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback.Stub.asInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0ed4
                android.os.Parcelable$Creator r2 = android.app.PendingIntent.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                android.app.PendingIntent r2 = (android.app.PendingIntent) r2
                r7 = r2
                goto L_0x0ed5
            L_0x0ed4:
                r7 = r13
            L_0x0ed5:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0ee1
                java.lang.Object r13 = r0.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0ee1:
                r2 = r12
                r8 = r13
                java.lang.String r12 = r2.requestShareWithItemFileList(r3, r4, r5, r6, r7, r8)
                r15.writeNoException()
                r15.writeString(r12)
                return r1
            L_0x0eee:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r2 = r14.readString()
                java.util.ArrayList r3 = r14.createStringArrayList()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemListDeletionResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemListDeletionResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSharedItemListDeletion(r13, r2, r3, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0f10:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String[] r5 = r14.createStringArray()
                android.os.IBinder r2 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback r6 = com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback.Stub.asInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0f38
                android.os.Parcelable$Creator r2 = android.app.PendingIntent.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                android.app.PendingIntent r2 = (android.app.PendingIntent) r2
                r7 = r2
                goto L_0x0f39
            L_0x0f38:
                r7 = r13
            L_0x0f39:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0f47
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0f47:
                r8 = r13
                java.lang.String r9 = r14.readString()
                r2 = r12
                int r12 = r2.requestOriginalSharedContentsDownloadWithPath(r3, r4, r5, r6, r7, r8, r9)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0f58:
                r14.enforceInterface(r2)
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.activity.IActivityBundlePartialResultCallback r13 = com.samsung.android.sdk.mobileservice.social.activity.IActivityBundlePartialResultCallback.Stub.asInterface(r13)
                r12.requestActivityChanges(r13)
                r15.writeNoException()
                return r1
            L_0x0f6a:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x0f84
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0f84:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupUpdate(r2, r3, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0f97:
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                java.lang.String r6 = r14.readString()
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemResultCallback.Stub.asInterface(r13)
                r2 = r12
                int r12 = r2.requestSharedItemWithGroupId(r3, r4, r5, r6, r7)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0fbe:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r0 = r14.readString()
                java.lang.String r2 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestDelegateAuthorityOfOwner(r13, r0, r2, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x0fe0:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0ff2
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x0ff2:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.common.IBundleProgressResultCallback r14 = com.samsung.android.sdk.mobileservice.social.common.IBundleProgressResultCallback.Stub.asInterface(r14)
                r12.requestProfileImageList(r13, r14)
                r15.writeNoException()
                return r1
            L_0x1001:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x1013
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x1013:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.common.IBundleProgressResultCallback r14 = com.samsung.android.sdk.mobileservice.social.common.IBundleProgressResultCallback.Stub.asInterface(r14)
                r12.requestActivityImageList(r13, r14)
                r15.writeNoException()
                return r1
            L_0x1022:
                r14.enforceInterface(r2)
                android.os.Bundle r12 = r12.getServiceState()
                r15.writeNoException()
                if (r12 == 0) goto L_0x1035
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x1038
            L_0x1035:
                r15.writeInt(r0)
            L_0x1038:
                return r1
            L_0x1039:
                r13 = r3
                r14.enforceInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x104b
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x104b:
                android.os.Bundle r12 = r12.isSomethingNeeded(r13)
                r15.writeNoException()
                if (r12 == 0) goto L_0x105b
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x105e
            L_0x105b:
                r15.writeInt(r0)
            L_0x105e:
                return r1
            L_0x105f:
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                java.lang.String r6 = r14.readString()
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemListResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemListResultCallback.Stub.asInterface(r13)
                r2 = r12
                int r12 = r2.requestSharedItemListWithFileList(r3, r4, r5, r6, r7)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1086:
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                java.lang.String r6 = r14.readString()
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemListResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemListResultCallback.Stub.asInterface(r13)
                r2 = r12
                int r12 = r2.requestSharedItemList(r3, r4, r5, r6, r7)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x10ad:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x10cd
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r6 = r0
                goto L_0x10ce
            L_0x10cd:
                r6 = r13
            L_0x10ce:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareResultWithFileListCallback r7 = com.samsung.android.sdk.mobileservice.social.share.IShareResultWithFileListCallback.Stub.asInterface(r0)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x10e6
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
                r8 = r0
                goto L_0x10e7
            L_0x10e6:
                r8 = r13
            L_0x10e7:
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x10f5
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x10f5:
                r2 = r12
                r9 = r13
                java.lang.String r12 = r2.requestShareUpdateWithUriList(r3, r4, r5, r6, r7, r8, r9)
                r15.writeNoException()
                r15.writeString(r12)
                return r1
            L_0x1102:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                java.util.ArrayList r6 = r14.createStringArrayList()
                android.os.IBinder r0 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback.Stub.asInterface(r0)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x112e
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
                r8 = r0
                goto L_0x112f
            L_0x112e:
                r8 = r13
            L_0x112f:
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x113d
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x113d:
                r2 = r12
                r9 = r13
                int r12 = r2.requestOriginalSharedContentWithFileListDownload(r3, r4, r5, r6, r7, r8, r9)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x114a:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x1166
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r5 = r0
                goto L_0x1167
            L_0x1166:
                r5 = r13
            L_0x1167:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareResultWithFileListCallback r6 = com.samsung.android.sdk.mobileservice.social.share.IShareResultWithFileListCallback.Stub.asInterface(r0)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x117f
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
                r7 = r0
                goto L_0x1180
            L_0x117f:
                r7 = r13
            L_0x1180:
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x118e
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x118e:
                r2 = r12
                r8 = r13
                java.lang.String r12 = r2.requestShareWithFileList(r3, r4, r5, r6, r7, r8)
                r15.writeNoException()
                r15.writeString(r12)
                return r1
            L_0x119b:
                r14.enforceInterface(r2)
                int r12 = r12.getCountryTypeForAgreement()
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x11a9:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x11c9
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r6 = r0
                goto L_0x11ca
            L_0x11c9:
                r6 = r13
            L_0x11ca:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemUpdateResultCallback r7 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemUpdateResultCallback.Stub.asInterface(r0)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x11e2
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
                r8 = r0
                goto L_0x11e3
            L_0x11e2:
                r8 = r13
            L_0x11e3:
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x11f1
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x11f1:
                r2 = r12
                r9 = r13
                java.lang.String r12 = r2.requestSharedItemUpdate(r3, r4, r5, r6, r7, r8, r9)
                r15.writeNoException()
                r15.writeString(r12)
                return r1
            L_0x11fe:
                r13 = r3
                r14.enforceInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x1210
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x1210:
                android.os.Bundle r12 = r12.getNotification(r13)
                r15.writeNoException()
                if (r12 == 0) goto L_0x1220
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x1223
            L_0x1220:
                r15.writeInt(r0)
            L_0x1223:
                return r1
            L_0x1224:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x1236
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x1236:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundlePartialResultCallback r14 = com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundlePartialResultCallback.Stub.asInterface(r14)
                r12.requestEmotionMemberList(r13, r14)
                r15.writeNoException()
                return r1
            L_0x1245:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x1257
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x1257:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundleResultCallback r14 = com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundleResultCallback.Stub.asInterface(r14)
                r12.requestEmotionUpdate(r13, r14)
                r15.writeNoException()
                return r1
            L_0x1266:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x1278
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x1278:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundleResultCallback r14 = com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundleResultCallback.Stub.asInterface(r14)
                r12.requestCommentDeletion(r13, r14)
                r15.writeNoException()
                return r1
            L_0x1287:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x1299
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x1299:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundleResultCallback r14 = com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundleResultCallback.Stub.asInterface(r14)
                r12.requestCommentUpdate(r13, r14)
                r15.writeNoException()
                return r1
            L_0x12a8:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x12ba
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x12ba:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundleResultCallback r14 = com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundleResultCallback.Stub.asInterface(r14)
                r12.requestCommentCreation(r13, r14)
                r15.writeNoException()
                return r1
            L_0x12c9:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x12db
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x12db:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundlePartialResultCallback r14 = com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundlePartialResultCallback.Stub.asInterface(r14)
                r12.requestCommentList(r13, r14)
                r15.writeNoException()
                return r1
            L_0x12ea:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x12fc
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x12fc:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundlePartialResultCallback r14 = com.samsung.android.sdk.mobileservice.social.feedback.IFeedbackBundlePartialResultCallback.Stub.asInterface(r14)
                r12.requestFeedback(r13, r14)
                r15.writeNoException()
                return r1
            L_0x130b:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x131d
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x131d:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.activity.IActivityBundlePartialResultCallback r14 = com.samsung.android.sdk.mobileservice.social.activity.IActivityBundlePartialResultCallback.Stub.asInterface(r14)
                r12.requestActivity(r13, r14)
                r15.writeNoException()
                return r1
            L_0x132c:
                r14.enforceInterface(r2)
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.activity.IActivityBundleResultCallback r13 = com.samsung.android.sdk.mobileservice.social.activity.IActivityBundleResultCallback.Stub.asInterface(r13)
                r12.requestActivitySync(r13)
                r15.writeNoException()
                return r1
            L_0x133e:
                r14.enforceInterface(r2)
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.activity.IActivityBundleResultCallback r13 = com.samsung.android.sdk.mobileservice.social.activity.IActivityBundleResultCallback.Stub.asInterface(r13)
                r12.requestMyActivityPrivacy(r13)
                r15.writeNoException()
                return r1
            L_0x1350:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x1362
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x1362:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.activity.IActivityResultCallback r14 = com.samsung.android.sdk.mobileservice.social.activity.IActivityResultCallback.Stub.asInterface(r14)
                r12.requestMyActivityPrivacyUpdate(r13, r14)
                r15.writeNoException()
                return r1
            L_0x1371:
                r13 = r3
                r14.enforceInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x1383
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x1383:
                android.os.Bundle r12 = r12.setBuddyActivityListRead(r13)
                r15.writeNoException()
                if (r12 == 0) goto L_0x1393
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x1396
            L_0x1393:
                r15.writeInt(r0)
            L_0x1396:
                return r1
            L_0x1397:
                r13 = r3
                r14.enforceInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x13a9
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x13a9:
                android.os.Bundle r12 = r12.getBuddyActivityList(r13)
                r15.writeNoException()
                if (r12 == 0) goto L_0x13b9
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x13bc
            L_0x13b9:
                r15.writeInt(r0)
            L_0x13bc:
                return r1
            L_0x13bd:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x13cf
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x13cf:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.activity.IActivityResultCallback r14 = com.samsung.android.sdk.mobileservice.social.activity.IActivityResultCallback.Stub.asInterface(r14)
                r12.requestActivityDeletion(r13, r14)
                r15.writeNoException()
                return r1
            L_0x13de:
                r13 = r3
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x13f0
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x13f0:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.activity.IActivityBundlePartialResultCallback r14 = com.samsung.android.sdk.mobileservice.social.activity.IActivityBundlePartialResultCallback.Stub.asInterface(r14)
                r12.requestActivityList(r13, r14)
                r15.writeNoException()
                return r1
            L_0x13ff:
                r13 = r3
                r14.enforceInterface(r2)
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x1411
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x1411:
                android.content.Intent r12 = r12.getIntentToDisplay(r13)
                r15.writeNoException()
                if (r12 == 0) goto L_0x1421
                r15.writeInt(r1)
                r12.writeToParcel(r15, r1)
                goto L_0x1424
            L_0x1421:
                r15.writeInt(r0)
            L_0x1424:
                return r1
            L_0x1425:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x143b
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x143b:
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.util.ArrayList r2 = r14.createTypedArrayList(r2)
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestInstantShare(r0, r13, r2, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1454:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r0 = r14.readString()
                java.lang.String r2 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupPendingInvitationCancellation(r13, r0, r2, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1476:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r0 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestLeave(r13, r0, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1494:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r0 = r14.readString()
                java.lang.String r2 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupMemberRemoval(r13, r0, r2, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x14b6:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupListWithInvitationResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupListWithInvitationResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupWithInvitationList(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x14d0:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r0 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IMemberListResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IMemberListResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupMemberList(r13, r0, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x14ee:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r0 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupInvitationRejection(r13, r0, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x150c:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r0 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupInvitationAcceptance(r13, r0, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x152a:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                java.lang.String r2 = r14.readString()
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x1544
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x1544:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupInvitationResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupInvitationResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupMemberInvitation(r0, r2, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1557:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r0 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupDeletion(r13, r0, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1575:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x158c
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                android.os.Bundle r2 = (android.os.Bundle) r2
                goto L_0x158d
            L_0x158c:
                r2 = r13
            L_0x158d:
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x159b
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x159b:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupInvitationResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupInvitationResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupCreation(r0, r2, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x15ae:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r0 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSharedItemSync(r13, r0, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x15cc:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSpaceListSync(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x15e6:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r14 = r14.readString()
                r12.clearSpaceUnreadCount(r13, r14)
                r15.writeNoException()
                return r1
            L_0x15f8:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r0 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareStatusCallback r14 = com.samsung.android.sdk.mobileservice.social.share.IShareStatusCallback.Stub.asInterface(r14)
                int r12 = r12.setShareStatusListener(r13, r0, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1616:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r14 = r14.readString()
                int r12 = r12.getShareStatus(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x162c:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r14 = r14.readString()
                int r12 = r12.cancelShare(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1642:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r14 = r14.readString()
                int r12 = r12.resumeShare(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1658:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r14 = r14.readString()
                int r12 = r12.pauseShare(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x166e:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String[] r5 = r14.createStringArray()
                android.os.IBinder r0 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback r6 = com.samsung.android.sdk.mobileservice.social.share.IContentDownloadingResultCallback.Stub.asInterface(r0)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x1696
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
                r7 = r0
                goto L_0x1697
            L_0x1696:
                r7 = r13
            L_0x1697:
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x16a5
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x16a5:
                r2 = r12
                r8 = r13
                int r12 = r2.requestOriginalSharedContentsDownload(r3, r4, r5, r6, r7, r8)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x16b2:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r2 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceCoverImageDownloadingResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISpaceCoverImageDownloadingResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestOriginalSpaceImageDownload(r13, r2, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x16d0:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r2 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupCoverImageDownloadingResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupCoverImageDownloadingResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestOriginalGroupImageDownload(r13, r2, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x16ee:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceListResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISpaceListResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestAllSpaceList(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1708:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x1722
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x1722:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSpaceUpdate(r2, r3, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1735:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r2 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceListResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISpaceListResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSpaceList(r13, r2, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1753:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r2 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceDeletionResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISpaceDeletionResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSpaceDeletion(r13, r2, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1771:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x178b
                android.os.Parcelable$Creator r13 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r13.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x178b:
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSpaceCreation(r2, r3, r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x179e:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r2 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISpaceResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSpace(r13, r2, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x17bc:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemDeletionResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemDeletionResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSharedItemDeletion(r13, r2, r3, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x17de:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.ISharedItemResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.ISharedItemResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestSharedItem(r13, r2, r3, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1800:
                r13 = r3
                r14.enforceInterface(r2)
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.util.ArrayList r5 = r14.createTypedArrayList(r2)
                android.os.IBinder r6 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback r6 = com.samsung.android.sdk.mobileservice.social.share.IShareResultCallback.Stub.asInterface(r6)
                int r7 = r14.readInt()
                if (r7 == 0) goto L_0x1829
                android.os.Parcelable$Creator r7 = android.app.PendingIntent.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r14)
                android.app.PendingIntent r7 = (android.app.PendingIntent) r7
                goto L_0x182a
            L_0x1829:
                r7 = r13
            L_0x182a:
                int r8 = r14.readInt()
                if (r8 == 0) goto L_0x1836
                java.lang.Object r13 = r2.createFromParcel(r14)
                android.os.Bundle r13 = (android.os.Bundle) r13
            L_0x1836:
                r2 = r12
                r8 = r13
                java.lang.String r12 = r2.requestShareWithPendingIntent(r3, r4, r5, r6, r7, r8)
                r15.writeNoException()
                r15.writeString(r12)
                return r1
            L_0x1843:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.share.IShareSyncResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestShareSync(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x185d:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.util.List r12 = r12.getGroupList(r13)
                r15.writeNoException()
                r15.writeTypedList(r12)
                return r1
            L_0x186f:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                java.lang.String r2 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroup(r13, r2, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x188d:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupListResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupListResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupList(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x18a7:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.group.IGroupSyncResultCallback r14 = com.samsung.android.sdk.mobileservice.social.group.IGroupSyncResultCallback.Stub.asInterface(r14)
                int r12 = r12.requestGroupSync(r13, r14)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x18c1:
                r14.enforceInterface(r2)
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.activity.IDeleteAllActivityResultCallback r13 = com.samsung.android.sdk.mobileservice.social.activity.IDeleteAllActivityResultCallback.Stub.asInterface(r13)
                r12.requestDeleteAllActivity(r13)
                r15.writeNoException()
                return r1
            L_0x18d3:
                r14.enforceInterface(r2)
                java.lang.String r13 = r14.readString()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.buddy.IPublicBuddyInfoResultCallback r14 = com.samsung.android.sdk.mobileservice.social.buddy.IPublicBuddyInfoResultCallback.Stub.asInterface(r14)
                r12.requestPublicBuddyInfo(r13, r14)
                r15.writeNoException()
                return r1
            L_0x18e9:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.buddy.IServiceDeactivationResultCallback r14 = com.samsung.android.sdk.mobileservice.social.buddy.IServiceDeactivationResultCallback.Stub.asInterface(r14)
                r12.requestServiceDeactivation(r13, r14)
                r15.writeNoException()
                return r1
            L_0x18ff:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                android.os.IBinder r14 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.buddy.IServiceActivationResultCallback r14 = com.samsung.android.sdk.mobileservice.social.buddy.IServiceActivationResultCallback.Stub.asInterface(r14)
                r12.requestServiceActivation(r13, r14)
                r15.writeNoException()
                return r1
            L_0x1915:
                r14.enforceInterface(r2)
                int r13 = r14.readInt()
                int r12 = r12.isServiceActivated(r13)
                r15.writeNoException()
                r15.writeInt(r12)
                return r1
            L_0x1927:
                r14.enforceInterface(r2)
                android.os.IBinder r13 = r14.readStrongBinder()
                com.samsung.android.sdk.mobileservice.social.buddy.ISyncResultCallback r13 = com.samsung.android.sdk.mobileservice.social.buddy.ISyncResultCallback.Stub.asInterface(r13)
                r12.requestSync(r13)
                r15.writeNoException()
                return r1
            L_0x1939:
                r15.writeString(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IMobileServiceSocial {
        public IBinder asBinder() {
            return null;
        }

        public int cancelShare(String str, String str2) {
            return 0;
        }

        public int dataSyncDelete(String str, Bundle bundle, IBundleResultCallback iBundleResultCallback) {
            return 0;
        }

        public int dataSyncGetSyncedData(String str, Bundle bundle, IBundleResultCallback iBundleResultCallback) {
            return 0;
        }

        public int dataSyncUpsert(String str, Bundle bundle, IBundleResultCallback iBundleResultCallback) {
            return 0;
        }

        public Bundle getBuddyActivityCount(Bundle bundle) {
            return null;
        }

        public Bundle getBuddyActivityList(Bundle bundle) {
            return null;
        }

        public Bundle getBuddySyncTime() {
            return null;
        }

        public int getCountryTypeForAgreement() {
            return 0;
        }

        public Bundle getDeviceAuthInfoCached() {
            return null;
        }

        public boolean getDisclaimerAgreementForService(Bundle bundle) {
            return false;
        }

        public boolean getDisclaimerAgreementForSocial() {
            return false;
        }

        public int getDisclosureScope(Bundle bundle) {
            return 0;
        }

        public Bundle getFamilyQuotaWithData(Bundle bundle) {
            return null;
        }

        public List<Bundle> getGroupList(String str) {
            return null;
        }

        public List<Bundle> getGroupListWithData(Bundle bundle) {
            return null;
        }

        public Intent getIntentToDisplay(Bundle bundle) {
            return null;
        }

        public Bundle getNotification(Bundle bundle) {
            return null;
        }

        public Bundle getQuota() {
            return null;
        }

        public Bundle getQuotaWithData(Bundle bundle) {
            return null;
        }

        public Bundle getServiceState() {
            return null;
        }

        public int getShareStatus(String str, String str2) {
            return 0;
        }

        public Bundle getVerifiedOriginalFileListWithData(Bundle bundle) {
            return null;
        }

        public Bundle isPermissionGranted(Bundle bundle) {
            return null;
        }

        public int isServiceActivated(int i2) {
            return 0;
        }

        public boolean isServiceRegistered(Bundle bundle) {
            return false;
        }

        public Bundle isSomethingNeeded(Bundle bundle) {
            return null;
        }

        public int pauseShare(String str, String str2) {
            return 0;
        }

        public Bundle requestActivityPosting(Bundle bundle, PendingIntent pendingIntent, IBundleProgressResultCallback iBundleProgressResultCallback) {
            return null;
        }

        public int requestAllSpaceList(String str, ISpaceListResultCallback iSpaceListResultCallback) {
            return 0;
        }

        public int requestAllSpaceListWithData(String str, Bundle bundle, ISpaceListResultCallback iSpaceListResultCallback) {
            return 0;
        }

        public String requestBulkItemShare(Bundle bundle, IShareBulkItemResultCallback iShareBulkItemResultCallback) {
            return null;
        }

        public Bundle requestContentsController(Bundle bundle) {
            return null;
        }

        public int requestDelegateAuthorityOfOwner(String str, String str2, String str3, IGroupResultCallback iGroupResultCallback) {
            return 0;
        }

        public int requestDelegateAuthorityOfOwnerWithData(Bundle bundle, IGroupResultCallback iGroupResultCallback) {
            return 0;
        }

        public int requestDeleteAllItemsFromTrashBinWithData(Bundle bundle, ITrashBinResultCallback iTrashBinResultCallback) {
            return 0;
        }

        public int requestDeleteItemListFromTrashBinWithData(Bundle bundle, ITrashBinResultCallback iTrashBinResultCallback) {
            return 0;
        }

        public int requestDisclosureScope(Bundle bundle, IBundleResultCallback iBundleResultCallback) {
            return 0;
        }

        public int requestDisclosureScopeUpdate(Bundle bundle, IBundleResultCallback iBundleResultCallback) {
            return 0;
        }

        public int requestFamilyGroupCreationWithData(Bundle bundle, Bundle bundle2, IGroupResultCallback iGroupResultCallback) {
            return 0;
        }

        public int requestFamilyGroupDeletionWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestFamilyGroupMemberListWithData(Bundle bundle, IMemberListResultCallback iMemberListResultCallback) {
            return 0;
        }

        public int requestGroup(String str, String str2, IGroupResultCallback iGroupResultCallback) {
            return 0;
        }

        public int requestGroupAuthorityChange(Bundle bundle, IGroupResultCallback iGroupResultCallback) {
            return 0;
        }

        public int requestGroupCreation(String str, Bundle bundle, Bundle bundle2, IGroupInvitationResultCallback iGroupInvitationResultCallback) {
            return 0;
        }

        public int requestGroupCreationWithData(Bundle bundle, Bundle bundle2, Bundle bundle3, IGroupInvitationResultCallback iGroupInvitationResultCallback) {
            return 0;
        }

        public int requestGroupDeletion(String str, String str2, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestGroupDeletionWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestGroupInvitationAcceptance(String str, String str2, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestGroupInvitationAcceptanceWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestGroupInvitationRejection(String str, String str2, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestGroupInvitationRejectionWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestGroupList(String str, IGroupListResultCallback iGroupListResultCallback) {
            return 0;
        }

        public int requestGroupListWithData(Bundle bundle, IGroupListResultCallback iGroupListResultCallback) {
            return 0;
        }

        public int requestGroupMemberInvitation(String str, String str2, Bundle bundle, IGroupInvitationResultCallback iGroupInvitationResultCallback) {
            return 0;
        }

        public int requestGroupMemberInvitationWithData(Bundle bundle, Bundle bundle2, IGroupInvitationResultCallback iGroupInvitationResultCallback) {
            return 0;
        }

        public int requestGroupMemberList(String str, String str2, IMemberListResultCallback iMemberListResultCallback) {
            return 0;
        }

        public int requestGroupMemberListWithData(Bundle bundle, IMemberListResultCallback iMemberListResultCallback) {
            return 0;
        }

        public int requestGroupMemberRemoval(String str, String str2, String str3, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestGroupMemberRemovalWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestGroupPendingInvitationCancellation(String str, String str2, String str3, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestGroupPendingInvitationCancellationWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestGroupSync(String str, IGroupSyncResultCallback iGroupSyncResultCallback) {
            return 0;
        }

        public int requestGroupSyncWithData(Bundle bundle, IGroupSyncResultCallback iGroupSyncResultCallback) {
            return 0;
        }

        public int requestGroupSyncWithoutImage(String str, IGroupSyncResultCallback iGroupSyncResultCallback) {
            return 0;
        }

        public int requestGroupSyncWithoutImageWithData(Bundle bundle, IGroupSyncResultCallback iGroupSyncResultCallback) {
            return 0;
        }

        public int requestGroupUpdate(String str, String str2, Bundle bundle, IGroupResultCallback iGroupResultCallback) {
            return 0;
        }

        public int requestGroupWithData(Bundle bundle, IGroupResultCallback iGroupResultCallback) {
            return 0;
        }

        public int requestGroupWithInvitationList(String str, IGroupListWithInvitationResultCallback iGroupListWithInvitationResultCallback) {
            return 0;
        }

        public int requestGroupWithInvitationListWithData(Bundle bundle, IGroupListWithInvitationResultCallback iGroupListWithInvitationResultCallback) {
            return 0;
        }

        public int requestInstantShare(String str, Bundle bundle, List<Bundle> list, IShareResultCallback iShareResultCallback) {
            return 0;
        }

        public int requestInvitationLinkCreation(Bundle bundle, IGroupResultCallback iGroupResultCallback) {
            return 0;
        }

        public int requestInvitationLinkDeletion(Bundle bundle, IGroupResultCallback iGroupResultCallback) {
            return 0;
        }

        public int requestLeave(String str, String str2, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestLeaveWithData(Bundle bundle, IGroupRequestResultCallback iGroupRequestResultCallback) {
            return 0;
        }

        public int requestMoveItemListToTrashBinWithData(Bundle bundle, ITrashBinResultCallback iTrashBinResultCallback) {
            return 0;
        }

        public int requestOriginalGroupImageDownload(String str, String str2, IGroupCoverImageDownloadingResultCallback iGroupCoverImageDownloadingResultCallback) {
            return 0;
        }

        public int requestOriginalSharedContentWithFileListDownload(String str, String str2, String str3, List<String> list, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle) {
            return 0;
        }

        public int requestOriginalSharedContentWithFileListDownloadWithData(String str, String str2, String str3, List<String> list, Bundle bundle, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle2) {
            return 0;
        }

        public int requestOriginalSharedContentWithItemFileListDownloadWithPath(String str, String str2, String str3, List<String> list, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle, String str4) {
            return 0;
        }

        public int requestOriginalSharedContentWithItemFileListDownloadWithPathWithData(String str, String str2, String str3, List<String> list, Bundle bundle, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle2, String str4) {
            return 0;
        }

        public int requestOriginalSharedContentsDownload(String str, String str2, String[] strArr, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle) {
            return 0;
        }

        public int requestOriginalSharedContentsDownloadToHiddenFolder(Bundle bundle, IContentDownloadingResultCallback iContentDownloadingResultCallback) {
            return 0;
        }

        public int requestOriginalSharedContentsDownloadWithPath(String str, String str2, String[] strArr, IContentDownloadingResultCallback iContentDownloadingResultCallback, PendingIntent pendingIntent, Bundle bundle, String str3) {
            return 0;
        }

        public int requestOriginalSpaceImageDownload(String str, String str2, ISpaceCoverImageDownloadingResultCallback iSpaceCoverImageDownloadingResultCallback) {
            return 0;
        }

        public int requestOriginalSpaceImageDownloadWithData(String str, String str2, Bundle bundle, ISpaceCoverImageDownloadingResultCallback iSpaceCoverImageDownloadingResultCallback) {
            return 0;
        }

        public int requestRestoreItemListFromTrashBinWithData(Bundle bundle, ITrashBinResultCallback iTrashBinResultCallback) {
            return 0;
        }

        public int requestServiceNumber(IBundleResultCallback iBundleResultCallback) {
            return 0;
        }

        public String requestShareListUpdateWithItemFileList(String str, String str2, List<Bundle> list, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle) {
            return null;
        }

        public String requestShareListUpdateWithItemFileListWithData(String str, String str2, List<Bundle> list, Bundle bundle, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle2) {
            return null;
        }

        public int requestShareSync(String str, IShareSyncResultCallback iShareSyncResultCallback) {
            return 0;
        }

        public int requestShareSyncWithData(String str, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback) {
            return 0;
        }

        public int requestShareSyncWithOption(String str, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback) {
            return 0;
        }

        public String requestShareUpdateWithUriList(String str, String str2, String str3, Bundle bundle, IShareResultWithFileListCallback iShareResultWithFileListCallback, PendingIntent pendingIntent, Bundle bundle2) {
            return null;
        }

        public String requestShareWithData(String str, String str2, List<Bundle> list, Bundle bundle, IShareResultCallback iShareResultCallback) {
            return null;
        }

        public String requestShareWithFileList(String str, String str2, Bundle bundle, IShareResultWithFileListCallback iShareResultWithFileListCallback, PendingIntent pendingIntent, Bundle bundle2) {
            return null;
        }

        public String requestShareWithFileListWithData(String str, String str2, Bundle bundle, Bundle bundle2, IShareResultWithFileListCallback iShareResultWithFileListCallback, PendingIntent pendingIntent, Bundle bundle3) {
            return null;
        }

        public String requestShareWithItemFileList(String str, String str2, List<Bundle> list, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle) {
            return null;
        }

        public String requestShareWithItemFileListWithData(String str, String str2, List<Bundle> list, Bundle bundle, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle2) {
            return null;
        }

        public String requestShareWithPendingIntent(String str, String str2, List<Bundle> list, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle) {
            return null;
        }

        public int requestSharedItem(String str, String str2, String str3, ISharedItemResultCallback iSharedItemResultCallback) {
            return 0;
        }

        public int requestSharedItemDeletion(String str, String str2, String str3, ISharedItemDeletionResultCallback iSharedItemDeletionResultCallback) {
            return 0;
        }

        public int requestSharedItemDeletionWithData(String str, String str2, String str3, Bundle bundle, ISharedItemDeletionResultCallback iSharedItemDeletionResultCallback) {
            return 0;
        }

        public int requestSharedItemList(String str, String str2, String str3, String str4, ISharedItemListResultCallback iSharedItemListResultCallback) {
            return 0;
        }

        public int requestSharedItemListDeletion(String str, String str2, List<String> list, ISharedItemListDeletionResultCallback iSharedItemListDeletionResultCallback) {
            return 0;
        }

        public int requestSharedItemListDeletionWithData(String str, String str2, List<String> list, Bundle bundle, ISharedItemListDeletionResultCallback iSharedItemListDeletionResultCallback) {
            return 0;
        }

        public int requestSharedItemListInTrashBinWithData(Bundle bundle, ISharedItemListResultCallback iSharedItemListResultCallback) {
            return 0;
        }

        public String requestSharedItemListUpdate(String str, String str2, List<Bundle> list, IShareResultCallback iShareResultCallback, PendingIntent pendingIntent, Bundle bundle) {
            return null;
        }

        public int requestSharedItemListWithFileList(String str, String str2, String str3, String str4, ISharedItemListResultCallback iSharedItemListResultCallback) {
            return 0;
        }

        public int requestSharedItemListWithFileListWithData(String str, String str2, String str3, String str4, Bundle bundle, ISharedItemListResultCallback iSharedItemListResultCallback) {
            return 0;
        }

        public int requestSharedItemSync(String str, String str2, IShareSyncResultCallback iShareSyncResultCallback) {
            return 0;
        }

        public int requestSharedItemSyncWithData(String str, String str2, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback) {
            return 0;
        }

        public int requestSharedItemSyncWithResolutionWithData(String str, Bundle bundle, Bundle bundle2, IShareSyncResultCallback iShareSyncResultCallback) {
            return 0;
        }

        public String requestSharedItemUpdate(String str, String str2, String str3, Bundle bundle, ISharedItemUpdateResultCallback iSharedItemUpdateResultCallback, PendingIntent pendingIntent, Bundle bundle2) {
            return null;
        }

        public int requestSharedItemWithData(String str, String str2, String str3, Bundle bundle, ISharedItemResultCallback iSharedItemResultCallback) {
            return 0;
        }

        public int requestSharedItemWithGroupId(String str, String str2, String str3, String str4, ISharedItemResultCallback iSharedItemResultCallback) {
            return 0;
        }

        public int requestSharedItemWithGroupIdWithData(String str, String str2, String str3, String str4, Bundle bundle, ISharedItemResultCallback iSharedItemResultCallback) {
            return 0;
        }

        public int requestSpace(String str, String str2, ISpaceResultCallback iSpaceResultCallback) {
            return 0;
        }

        public int requestSpaceCreation(String str, String str2, Bundle bundle, ISpaceResultCallback iSpaceResultCallback) {
            return 0;
        }

        public int requestSpaceCreationWithData(String str, String str2, Bundle bundle, Bundle bundle2, ISpaceResultCallback iSpaceResultCallback) {
            return 0;
        }

        public int requestSpaceDeletion(String str, String str2, ISpaceDeletionResultCallback iSpaceDeletionResultCallback) {
            return 0;
        }

        public int requestSpaceDeletionWithData(String str, String str2, Bundle bundle, ISpaceDeletionResultCallback iSpaceDeletionResultCallback) {
            return 0;
        }

        public int requestSpaceList(String str, String str2, ISpaceListResultCallback iSpaceListResultCallback) {
            return 0;
        }

        public int requestSpaceListSync(String str, IShareSyncResultCallback iShareSyncResultCallback) {
            return 0;
        }

        public int requestSpaceListSyncWithData(String str, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback) {
            return 0;
        }

        public int requestSpaceListWithData(String str, String str2, Bundle bundle, ISpaceListResultCallback iSpaceListResultCallback) {
            return 0;
        }

        public int requestSpaceUpdate(String str, String str2, Bundle bundle, ISpaceResultCallback iSpaceResultCallback) {
            return 0;
        }

        public int requestSpaceUpdateWithData(String str, String str2, Bundle bundle, Bundle bundle2, ISpaceResultCallback iSpaceResultCallback) {
            return 0;
        }

        public int requestSpaceWithData(String str, String str2, Bundle bundle, ISpaceResultCallback iSpaceResultCallback) {
            return 0;
        }

        public int requestThumbnailDownload(String str, String str2, String str3, String str4, String str5, String str6, IDownloadThumbnailResultCallback iDownloadThumbnailResultCallback) {
            return 0;
        }

        public int requestWebLinkEnabled(Bundle bundle, boolean z, IBundleResultCallback iBundleResultCallback) {
            return 0;
        }

        public int resumeShare(String str, String str2) {
            return 0;
        }

        public Bundle setBuddyActivityListRead(Bundle bundle) {
            return null;
        }

        public boolean setDisclaimerAgreementForSocial(Bundle bundle) {
            return false;
        }

        public int setShareStatusListener(String str, String str2, IShareStatusCallback iShareStatusCallback) {
            return 0;
        }

        public void requestActivityChanges(IActivityBundlePartialResultCallback iActivityBundlePartialResultCallback) {
        }

        public void requestActivitySync(IActivityBundleResultCallback iActivityBundleResultCallback) {
        }

        public void requestDeleteAllActivity(IDeleteAllActivityResultCallback iDeleteAllActivityResultCallback) {
        }

        public void requestMyActivityPrivacy(IActivityBundleResultCallback iActivityBundleResultCallback) {
        }

        public void requestSync(ISyncResultCallback iSyncResultCallback) {
        }

        public void clearSpaceUnreadCount(String str, String str2) {
        }

        public void getBuddyInfo(Bundle bundle, IBuddyInfoResultCallback iBuddyInfoResultCallback) {
        }

        public void requestActivity(Bundle bundle, IActivityBundlePartialResultCallback iActivityBundlePartialResultCallback) {
        }

        public void requestActivityContent(Bundle bundle, IActivityBundleResultCallback iActivityBundleResultCallback) {
        }

        public void requestActivityContentStreamingUrl(Bundle bundle, IActivityBundleResultCallback iActivityBundleResultCallback) {
        }

        public void requestActivityDeletion(Bundle bundle, IActivityResultCallback iActivityResultCallback) {
        }

        public void requestActivityImageList(Bundle bundle, IBundleProgressResultCallback iBundleProgressResultCallback) {
        }

        public void requestActivityList(Bundle bundle, IActivityBundlePartialResultCallback iActivityBundlePartialResultCallback) {
        }

        public void requestAppUpdateAndLaunchForBuddy(Bundle bundle, IBuddyInfoResultCallback iBuddyInfoResultCallback) {
        }

        public void requestBuddySync(int i2, ISyncResultCallback iSyncResultCallback) {
        }

        public void requestCommentCreation(Bundle bundle, IFeedbackBundleResultCallback iFeedbackBundleResultCallback) {
        }

        public void requestCommentDeletion(Bundle bundle, IFeedbackBundleResultCallback iFeedbackBundleResultCallback) {
        }

        public void requestCommentList(Bundle bundle, IFeedbackBundlePartialResultCallback iFeedbackBundlePartialResultCallback) {
        }

        public void requestCommentUpdate(Bundle bundle, IFeedbackBundleResultCallback iFeedbackBundleResultCallback) {
        }

        public void requestEmotionMemberList(Bundle bundle, IFeedbackBundlePartialResultCallback iFeedbackBundlePartialResultCallback) {
        }

        public void requestEmotionUpdate(Bundle bundle, IFeedbackBundleResultCallback iFeedbackBundleResultCallback) {
        }

        public void requestFeedback(Bundle bundle, IFeedbackBundlePartialResultCallback iFeedbackBundlePartialResultCallback) {
        }

        public void requestMyActivityPrivacyUpdate(Bundle bundle, IActivityResultCallback iActivityResultCallback) {
        }

        public void requestProfileImageList(Bundle bundle, IBundleProgressResultCallback iBundleProgressResultCallback) {
        }

        public void requestPublicBuddyInfo(String str, IPublicBuddyInfoResultCallback iPublicBuddyInfoResultCallback) {
        }

        public void requestServiceActivation(int i2, IServiceActivationResultCallback iServiceActivationResultCallback) {
        }

        public void requestServiceDeactivation(int i2, IServiceDeactivationResultCallback iServiceDeactivationResultCallback) {
        }

        public void clearSpaceUnreadCountWithData(String str, Bundle bundle, String str2) {
        }

        public void requestSharedItemSyncWithResolution(String str, Bundle bundle, IShareSyncResultCallback iShareSyncResultCallback) {
        }
    }
}
