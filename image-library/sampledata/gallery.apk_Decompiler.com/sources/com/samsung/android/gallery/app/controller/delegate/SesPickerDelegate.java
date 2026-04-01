package com.samsung.android.gallery.app.controller.delegate;

import A4.A;
import Fa.C0571z;
import K.a;
import M3.c;
import M3.d;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.IntentExtra$InternalKey;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi;
import com.samsung.android.sdk.mobileservice.social.social.SocialPickerRequest;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import org.json.JSONException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SesPickerDelegate {
    private static void composeUrisToAdd(Blackboard blackboard, Intent intent) {
        ArrayList<Uri> arrayList;
        MediaItem[] mediaItemArr = (MediaItem[]) blackboard.pop("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length <= 0) {
            arrayList = null;
        } else {
            arrayList = MdeAlbumHelper.convertMediaItemsToUris(mediaItemArr);
        }
        Optional.ofNullable(arrayList).ifPresent(new d(intent, 0));
    }

    public static Intent createIntent(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("7");
        return new OpenSessionApi().getIntentForSocialPicker(context, new SocialPickerRequest.Builder().setMaxRecipientCount(99).setFilterAppId("22n6hzkam0").setFilterFeatureIdList(arrayList).setShowInvitationChoice(Boolean.valueOf(Features.isEnabled(Features.SUPPORT_INVITE_FROM_SHARE_LINK))).setEnableSkip(true).build());
    }

    private static void createSpace(EventContext eventContext, Intent intent) {
        try {
            eventContext.getContext().startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SesPickerDelegate", "Fail start service");
        }
    }

    private static void createSpaceAndAddContents(EventContext eventContext, Intent intent) {
        Blackboard blackboard = eventContext.getBlackboard();
        composeUrisToAdd(blackboard, intent);
        try {
            eventContext.getContext().startService(intent);
            doneCmd(blackboard, false);
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("SesPickerDelegate", "Fail start service");
        }
    }

    private static void doneCmd(Blackboard blackboard, boolean z) {
        blackboard.post("command://FinishSharingAlbumChoice", (Object) null);
        if (z) {
            blackboard.erase("data://user/selected");
        } else {
            blackboard.postEvent(EventMessage.obtain(1003));
        }
    }

    private static long getCloudImageCount(Blackboard blackboard) {
        MediaItem[] mediaItemArr = (MediaItem[]) blackboard.read("data://user/selected");
        if (mediaItemArr != null) {
            return Arrays.stream(mediaItemArr).filter(new C0571z(5)).filter(new C0571z(29)).count();
        }
        return 0;
    }

    private static long getCloudItemsCount(Blackboard blackboard) {
        MediaItem[] mediaItemArr = (MediaItem[]) blackboard.read("data://user/selected");
        if (mediaItemArr != null) {
            return Arrays.stream(mediaItemArr).filter(new C0571z(5)).count();
        }
        return 0;
    }

    private static Intent getIntentForShare(Blackboard blackboard, String str) {
        Intent createIntent = createIntent(blackboard, str);
        createIntent.putExtra(IntentExtra$InternalKey.KEY_CREATE_GROUP_FOR_SHARE, true);
        return createIntent;
    }

    private static Intent getIntentWithInvitationList(Blackboard blackboard, String str, HashMap<Integer, Object> hashMap) {
        Intent createIntent = createIntent(blackboard, str);
        createIntent.putExtra("invitationRequestData", hashMap);
        return createIntent;
    }

    private static void handleJobOnConditionForShare(EventContext eventContext) {
        Blackboard blackboard = eventContext.getBlackboard();
        String str = (String) blackboard.pop("data://user/CreateSharingAlbumTitle");
        if (isEmptyItemsToAdd(blackboard)) {
            Log.d("SesPickerDelegate", "Selected items are empty. just create space.");
            createSpace(eventContext, getIntentForShare(blackboard, str));
            return;
        }
        long cloudItemsCount = getCloudItemsCount(blackboard);
        if (cloudItemsCount <= 0 || !CloudStateCompat.isEnabled()) {
            createSpaceAndAddContents(eventContext, getIntentForShare(blackboard, str));
            return;
        }
        long cloudImageCount = getCloudImageCount(blackboard);
        blackboard.publish("data://new_space_name", str);
        blackboard.publish("data://create_group_for_share", Boolean.TRUE);
        showAddCloudItemDialog(eventContext, cloudItemsCount, cloudImageCount);
    }

    private static void handleJobOnConditionWithInvitationList(EventContext eventContext, HashMap<Integer, Object> hashMap) {
        Blackboard blackboard = eventContext.getBlackboard();
        String str = (String) blackboard.pop("data://user/CreateSharingAlbumTitle");
        if (isEmptyItemsToAdd(blackboard)) {
            Log.d("SesPickerDelegate", "Selected items are empty. just create space.");
            createSpace(eventContext, getIntentWithInvitationList(blackboard, str, hashMap));
            return;
        }
        long cloudItemsCount = getCloudItemsCount(blackboard);
        if (cloudItemsCount <= 0 || !CloudStateCompat.isEnabled()) {
            createSpaceAndAddContents(eventContext, getIntentWithInvitationList(blackboard, str, hashMap));
            return;
        }
        long cloudImageCount = getCloudImageCount(blackboard);
        blackboard.publish("data://new_space_name", str);
        blackboard.publish("data://invitation_req_data", hashMap);
        showAddCloudItemDialog(eventContext, cloudItemsCount, cloudImageCount);
    }

    public static void handleOnActivityResult(EventContext eventContext, int i2, Intent intent) {
        if (eventContext == null || eventContext.getContext() == null || eventContext.getBlackboard() == null) {
            Log.e("SesPickerDelegate", "handler or blackboard is invalid.");
        } else if (i2 == -1) {
            Optional.ofNullable(intent.getExtras()).ifPresent(new c(eventContext, 0));
        } else if (i2 == 55) {
            handleJobOnConditionForShare(eventContext);
        }
    }

    private static boolean isEmptyItemsToAdd(Blackboard blackboard) {
        MediaItem[] mediaItemArr = (MediaItem[]) blackboard.read("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleOnActivityResult$0(EventContext eventContext, Bundle bundle) {
        String string = bundle.getString(OpenSessionApi.EXTRA_KEY_JSON_RESULT);
        if (!TextUtils.isEmpty(string)) {
            try {
                handleJobOnConditionWithInvitationList(eventContext, makeInvitationRequestData(string));
            } catch (JSONException e) {
                Log.e("SesPickerDelegate", e.getMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onConfirmed$2(ArrayList arrayList, EventContext eventContext, Blackboard blackboard) {
        int i2;
        Intent intent;
        String str = (String) blackboard.pop("data://new_space_name");
        Boolean bool = (Boolean) blackboard.pop("data://create_group_for_share");
        HashMap hashMap = (HashMap) blackboard.pop("data://invitation_req_data");
        if (arrayList != null) {
            i2 = arrayList.size();
        } else {
            i2 = 0;
        }
        if (i2 == 0) {
            Log.d("SesPickerDelegate", "Cancel operation");
            doneCmd(blackboard, true);
            return;
        }
        if (bool == null || !bool.booleanValue()) {
            intent = getIntentWithInvitationList(blackboard, str, hashMap);
        } else {
            intent = getIntentForShare(blackboard, str);
        }
        createSpaceAndAddContents(eventContext, intent);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.HashMap<java.lang.Integer, java.lang.Object> makeInvitationRequestData(java.lang.String r9) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            org.json.JSONArray r3 = new org.json.JSONArray
            r3.<init>(r9)
            r9 = 0
            r4 = r9
        L_0x0016:
            int r5 = r3.length()
            if (r4 >= r5) goto L_0x007b
            org.json.JSONObject r5 = r3.getJSONObject(r4)
            java.lang.String r6 = "type"
            java.lang.String r6 = r5.getString(r6)
            int r7 = r6.hashCode()
            switch(r7) {
                case -1177318867: goto L_0x004a;
                case -1034364087: goto L_0x0040;
                case 3184265: goto L_0x0039;
                case 98629247: goto L_0x002e;
                default: goto L_0x002d;
            }
        L_0x002d:
            goto L_0x0054
        L_0x002e:
            java.lang.String r7 = "group"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0054
            r6 = 103(0x67, float:1.44E-43)
            goto L_0x0055
        L_0x0039:
            java.lang.String r7 = "guid"
            boolean r6 = r6.equals(r7)
            goto L_0x0054
        L_0x0040:
            java.lang.String r7 = "number"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0054
            r6 = 1
            goto L_0x0055
        L_0x004a:
            java.lang.String r7 = "account"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0054
            r6 = 3
            goto L_0x0055
        L_0x0054:
            r6 = r9
        L_0x0055:
            java.lang.String r7 = "value"
            java.lang.String r7 = r5.getString(r7)
            r0.add(r7)
            java.lang.String r7 = "msisdn"
            boolean r8 = r5.has(r7)
            if (r8 == 0) goto L_0x006d
            java.lang.String r5 = r5.getString(r7)
            r1.add(r5)
        L_0x006d:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r6)
            java.lang.Object[] r6 = new java.lang.Object[]{r0, r1}
            r2.put(r5, r6)
            int r4 = r4 + 1
            goto L_0x0016
        L_0x007b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.delegate.SesPickerDelegate.makeInvitationRequestData(java.lang.String):java.util.HashMap");
    }

    /* access modifiers changed from: private */
    public static void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        Optional.ofNullable(eventContext.getBlackboard()).ifPresent(new A(27, (Object) arrayList, (Object) eventContext));
    }

    private static void showAddCloudItemDialog(EventContext eventContext, long j2, long j3) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/AddCloudItemToSharedAlbum").appendArg("cloudItemCount", j2).appendArg("imageCount", j3).build()).setOnDataCompleteListener(new a(7)).start();
    }

    public static void start(Activity activity, Blackboard blackboard, String str) {
        if (!TextUtils.isEmpty(str)) {
            blackboard.publish("data://user/CreateSharingAlbumTitle", str);
            activity.startActivityForResult(createIntent(BlackboardUtils.readAppContext(blackboard)), StatusCodes.INPUT_MISSING);
        }
    }

    private static Intent createIntent(Blackboard blackboard, String str) {
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.MdeSharingService");
        intent.setAction("com.samsung.android.gallery.app.service.MdeSharingService.REQUEST_CREATE_GROUP");
        intent.putExtra("blackboard_name", blackboard.getName());
        intent.putExtra("sharedAlbumTitle", str);
        return intent;
    }
}
