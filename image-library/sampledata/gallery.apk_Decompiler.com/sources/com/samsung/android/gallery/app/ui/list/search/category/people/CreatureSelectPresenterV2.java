package com.samsung.android.gallery.app.ui.list.search.category.people;

import A4.C0368c;
import Ad.C0720a;
import B5.c;
import B8.f;
import P8.a;
import T8.C0578a;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.people.RemoveSubscribedCreatureCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import h3.b;
import h4.C0464a;
import i.C0212a;
import i4.C0468a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import n4.C0491c;
import n5.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureSelectPresenterV2<V extends ICreatureSelectView> extends CreatureSelectPresenter<V> {
    private int mAlbumId;
    private String mAlbumName;
    private String mGroupId;
    private boolean mOnlyUnsubscribed;
    private ArrayList<String> mOriginalSelectedCreatures = new ArrayList<>();
    private String mSpaceId;

    public CreatureSelectPresenterV2(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void addContents(String str) {
        if (MdeGroupHelper.isSAFamilyGroup(this.mGroupId)) {
            AutoAlbumHelper.getInstance().addFamilyAlbumContents(str, (long) this.mAlbumId);
        } else {
            AutoAlbumHelper.getInstance().addAutoAlbumContents(str, (long) this.mAlbumId);
        }
    }

    private void createAutoAlbum() {
        ArrayList<Long> selectedPeople = getSelectedPeople(this.mSelectedCreatures);
        String includedMediaIds = PeopleDataManager.getIncludedMediaIds(selectedPeople);
        ArrayList<Long> selectedPet = getSelectedPet(this.mSelectedCreatures);
        String creatureIds = getCreatureIds(includedMediaIds, PetDataManager.getIncludedMediaIds(selectedPet));
        if (creatureIds != null) {
            if (Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM)) {
                EventContext eventContext = ((ICreatureSelectView) this.mView).getEventContext();
                DataCollectionDelegate.getInitInstance(eventContext).setRequestData(getTargetKey(eventContext)).setOnDataCompleteListener(new f((Object) this, (Object) creatureIds, (Object) selectedPeople, (Object) selectedPet, 7)).start();
                return;
            }
            lambda$createAutoAlbum$2(creatureIds, selectedPeople, selectedPet, (ArrayList<Object>) null);
        }
    }

    private String createAutoAlbumTitle() {
        boolean z;
        String[] creatureNamesArray = getCreatureNamesArray();
        String[] strArr = (String[]) Arrays.stream(creatureNamesArray).filter(new C0464a(13)).toArray(new C0578a(19));
        if (!Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM) || strArr.length == 0) {
            return null;
        }
        if (creatureNamesArray.length == strArr.length) {
            z = true;
        } else {
            z = false;
        }
        return getAutoAlbumName(strArr, z);
    }

    private void editAutoAlbum() {
        int i2;
        List<String> differenceUnifiedId = getDifferenceUnifiedId(this.mSelectedCreatures, this.mOriginalSelectedCreatures);
        subscribePeople(differenceUnifiedId);
        subscribePet(differenceUnifiedId);
        List<String> differenceUnifiedId2 = getDifferenceUnifiedId(this.mOriginalSelectedCreatures, this.mSelectedCreatures);
        unsubscribePeople(differenceUnifiedId2);
        unsubscribePet(differenceUnifiedId2);
        if (!differenceUnifiedId2.isEmpty() && differenceUnifiedId.isEmpty()) {
            this.mOnlyUnsubscribed = true;
        }
        Blackboard blackboard = this.mBlackboard;
        if (isFromSharingSetting()) {
            i2 = 6008;
        } else {
            i2 = 101;
        }
        blackboard.postBroadcastEvent(EventMessage.obtain(i2));
        finish();
    }

    private void finish() {
        ThreadUtil.postOnUiThread(new d(this, 1));
    }

    /* access modifiers changed from: private */
    /* renamed from: generateAutoAlbum */
    public void lambda$createAutoAlbum$2(String str, ArrayList<Long> arrayList, ArrayList<Long> arrayList2, ArrayList<Object> arrayList3) {
        int i2 = 0;
        if (arrayList3 != null && !arrayList3.isEmpty()) {
            Object obj = arrayList3.get(0);
            if (obj instanceof Object[]) {
                Object obj2 = ((Object[]) obj)[0];
                if (obj2 instanceof String) {
                    this.mAlbumName = (String) obj2;
                }
            }
        }
        if (!TextUtils.isEmpty(this.mAlbumName)) {
            long currentTimeMillis = System.currentTimeMillis();
            int createAutoAlbum = AutoAlbumHelper.getInstance().createAutoAlbum(this.mAlbumName, str);
            if (AutoAlbumHelper.getInstance().isValidAutoAlbum(createAutoAlbum)) {
                if (!arrayList.isEmpty()) {
                    AutoAlbumHelper.getInstance().subscribePeople(getCreatureList(this.mSelectedCreatures, true), (long) createAutoAlbum);
                }
                if (!arrayList2.isEmpty()) {
                    AutoAlbumHelper.getInstance().subscribePet(getCreatureList(this.mSelectedCreatures, false), (long) createAutoAlbum);
                }
                i2 = AlbumHelper.getInstance().addNewEmptyAutoAlbum(createAutoAlbum, this.mAlbumName);
                ThreadUtil.postOnUiThreadDelayed(new C0368c(this, createAutoAlbum, 29), 100);
            }
            StringCompat stringCompat = this.TAG;
            C0212a.z(new Object[]{Integer.valueOf(createAutoAlbum), Integer.valueOf(arrayList.size()), Integer.valueOf(arrayList2.size()), Integer.valueOf(i2), Long.valueOf(currentTimeMillis)}, new StringBuilder("generateAutoAlbum"), stringCompat);
            finish();
        }
    }

    private String getAutoAlbumName(String[] strArr, boolean z) {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        if (z) {
            if (strArr.length == 1) {
                return strArr[0];
            }
            if (strArr.length == 2) {
                return context.getString(R.string.two_creatures_named_only, new Object[]{strArr[0], strArr[1]});
            }
            if (strArr.length == 3) {
                return context.getString(R.string.three_creatures_named_only, new Object[]{strArr[0], strArr[1], strArr[2]});
            }
            return context.getString(R.string.many_creatures_with_others, new Object[]{strArr[0], strArr[1], strArr[2]});
        } else if (strArr.length == 1) {
            return context.getString(R.string.two_creatures_with_others, new Object[]{strArr[0]});
        } else {
            if (strArr.length == 2) {
                return context.getString(R.string.three_creatures_with_others, new Object[]{strArr[0], strArr[1]});
            }
            if (strArr.length >= 3) {
                return context.getString(R.string.many_creatures_with_others, new Object[]{strArr[0], strArr[1], strArr[2]});
            }
            return null;
        }
    }

    private String getCreatureIds(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            return C0212a.B(str, GlobalPostProcInternalPPInterface.SPLIT_REGEX, str2);
        }
        String A10 = C0212a.A(str, str2);
        if (TextUtils.isEmpty(A10)) {
            return null;
        }
        return A10;
    }

    private List<Pair<Long, String>> getCreatureList(List<String> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        list.stream().filter(new a(z, 1)).forEach(new C0491c(4, this, arrayList));
        return arrayList;
    }

    private String[] getCreatureNamesArray() {
        return (String[]) Arrays.stream((MediaItem[]) getMediaData().getAllData().toArray(new MediaItem[0])).filter(new b(1, this)).map(new C0468a(29)).toArray(new C0578a(20));
    }

    private List<String> getDifferenceUnifiedId(List<String> list, List<String> list2) {
        ArrayList arrayList = new ArrayList();
        list.forEach(new W9.d(list2, arrayList, 1));
        return arrayList;
    }

    private ArrayList<Long> getSelectedPeople(List<String> list) {
        return (ArrayList) list.stream().filter(new C0464a(14)).map(new Gb.a(14)).collect(Collectors.toCollection(new C0720a(1)));
    }

    private ArrayList<Long> getSelectedPet(List<String> list) {
        return (ArrayList) list.stream().filter(new C0464a(15)).map(new Gb.a(14)).collect(Collectors.toCollection(new C0720a(1)));
    }

    private String getTargetKey(EventContext eventContext) {
        return new UriBuilder("data://user/dialog/AutoUpdatingAlbumName").appendArg("screenId", eventContext.getScreenId()).appendArg("title", createAutoAlbumTitle()).appendArg("autoAlbumTitles", ((ICreatureSelectView) this.mView).getAutoAlbumTitles()).build();
    }

    /* access modifiers changed from: private */
    public boolean isEditMode() {
        if (this.mAlbumId != -1) {
            return true;
        }
        return false;
    }

    private boolean isFromPreviewSuggestions() {
        return UnsafeCast.toBoolean(ArgumentsUtil.getArgValue(getLocationKey(), "from_suggestion_pictures", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE));
    }

    private boolean isFromSharingSetting() {
        return UnsafeCast.toBoolean(ArgumentsUtil.getArgValue(getLocationKey(), "from_sharing_setting", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createAutoAlbum$3(String str, ArrayList arrayList, ArrayList arrayList2, EventContext eventContext, ArrayList arrayList3) {
        ArrayList arrayList4;
        if (arrayList3 == null) {
            arrayList4 = new ArrayList();
        }
        SimpleThreadPool.getInstance().execute(new c(this, str, arrayList, arrayList2, arrayList4, 27));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$createAutoAlbumTitle$4(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finish$12() {
        this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        if (needToLaunchPreviewSuggestions() && !this.mOnlyUnsubscribed) {
            launchPreviewSuggestions();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$generateAutoAlbum$8(int i2) {
        Blackboard.publishGlobal("data://usernew_item_creation", new Object[]{this.mAlbumName, null, -1, null, Integer.valueOf(i2)});
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getCreatureList$10(String str, List list, MediaItem mediaItem) {
        if (mediaItem != null && TextUtils.equals(str, mediaItem.getSubCategory())) {
            list.add(new Pair(Long.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(str)), CreatureData.of(mediaItem).faceGroupUuid));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getCreatureList$11(List list, String str) {
        Arrays.stream(getAllItems()).forEach(new C0491c(3, str, list));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getCreatureList$9(boolean z, String str) {
        if (z) {
            return IdentityCreatureUtil.isPerson(str);
        }
        return IdentityCreatureUtil.isPet(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$getCreatureNamesArray$5(MediaItem mediaItem) {
        return this.mSelectedCreatures.contains(mediaItem.getSubCategory());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$getCreatureNamesArray$7(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getDifferenceUnifiedId$1(List list, List list2, String str) {
        if (!list.contains(str)) {
            list2.add(str);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveSelectedCreature$0() {
        if (isEditMode()) {
            editAutoAlbum();
        } else {
            createAutoAlbum();
        }
    }

    private boolean needToLaunchPreviewSuggestions() {
        if (isFromPreviewSuggestions() || TextUtils.isEmpty(this.mGroupId) || TextUtils.isEmpty(this.mSpaceId) || !MdeGroupHelper.isSAFamilyGroup(this.mGroupId)) {
            return false;
        }
        return true;
    }

    private void removeSelectedCreature(int i2, MediaItem mediaItem) {
        this.mSelectedCreatures.remove(mediaItem.getSubCategory());
        ((ICreatureSelectView) this.mView).getAdapter().onUpdateViewHolder(i2);
        updateToolbar(getToolbar());
    }

    private void saveSelectedCreature() {
        if (this.mSelectedCreatures.isEmpty()) {
            Utils.showToast(getContext(), (int) R.string.select_at_least_a_person);
        } else {
            SimpleThreadPool.getInstance().execute(new d(this, 0));
        }
    }

    private void selectCreature(String str) {
        this.mSelectedCreatures.add(str);
        postAnalyticsLog(AnalyticsEventId.EVENT_CREATURE_PICKER_ITEM_SELECT, "1");
    }

    private void subscribePeople(List<String> list) {
        ArrayList<Long> selectedPeople = getSelectedPeople(list);
        if (!selectedPeople.isEmpty()) {
            String includedMediaIds = PeopleDataManager.getIncludedMediaIds(selectedPeople);
            if (!TextUtils.isEmpty(includedMediaIds)) {
                addContents(includedMediaIds);
            }
            AutoAlbumHelper.getInstance().subscribePeople(getCreatureList(list, true), (long) this.mAlbumId);
        }
    }

    private void subscribePet(List<String> list) {
        ArrayList<Long> selectedPet = getSelectedPet(list);
        if (!selectedPet.isEmpty()) {
            String includedMediaIds = PetDataManager.getIncludedMediaIds(selectedPet);
            if (!TextUtils.isEmpty(includedMediaIds)) {
                addContents(includedMediaIds);
            }
            AutoAlbumHelper.getInstance().subscribePet(getCreatureList(list, false), (long) this.mAlbumId);
        }
    }

    private boolean unselectCreature(int i2, MediaItem mediaItem) {
        String subCategory = mediaItem.getSubCategory();
        if (isEditMode()) {
            if (this.mSelectedCreatures.size() == 1) {
                Utils.showToast(getContext(), (int) R.string.select_at_least_a_person);
                return false;
            } else if (this.mOriginalSelectedCreatures.contains(subCategory)) {
                new RemoveSubscribedCreatureCmd().execute(this, Integer.valueOf(i2), mediaItem);
                return false;
            }
        }
        this.mSelectedCreatures.remove(subCategory);
        postAnalyticsLog(AnalyticsEventId.EVENT_CREATURE_PICKER_ITEM_SELECT, "0");
        return true;
    }

    private void unsubscribePeople(List<String> list) {
        ArrayList<Long> selectedPeople = getSelectedPeople(list);
        if (!selectedPeople.isEmpty()) {
            AutoAlbumHelper.getInstance().unsubscribePeople(selectedPeople, (long) this.mAlbumId);
        }
    }

    private void unsubscribePet(List<String> list) {
        ArrayList<Long> selectedPet = getSelectedPet(list);
        if (!selectedPet.isEmpty()) {
            AutoAlbumHelper.getInstance().unsubscribePet(selectedPet, (long) this.mAlbumId);
        }
    }

    public String getDefaultToolbarTitle(Context context) {
        return context.getString(R.string.select_people_and_pets);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 2010) {
            removeSelectedCreature(eventMessage.arg1, (MediaItem) eventMessage.obj);
            return true;
        } else if (i2 != 8001) {
            return super.handleEvent(eventMessage);
        } else {
            saveSelectedCreature();
            postAnalyticsLog(AnalyticsEventId.EVENT_CREATURE_PICKER_SELECT_DONE);
            return true;
        }
    }

    public void launchPreviewSuggestions() {
        this.mBlackboard.post("command://MoveURL", MdeAlbumHelper.buildSharingPicturesSuggestionLocation(this.mAlbumId, this.mGroupId, this.mSpaceId, false));
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (mediaItem != null) {
            String subCategory = mediaItem.getSubCategory();
            if (!this.mSelectedCreatures.contains(subCategory)) {
                selectCreature(subCategory);
            } else if (!unselectCreature(i2, mediaItem)) {
                return;
            }
            ((ICreatureSelectView) this.mView).getAdapter().onUpdateViewHolder(i2);
            updateToolbar(getToolbar());
            ((ICreatureSelectView) this.mView).invalidateOptionsMenu();
        }
    }

    public void onLocationKeyAssigned() {
        super.onLocationKeyAssigned();
        if (isEditMode()) {
            this.mOriginalSelectedCreatures = new ArrayList<>(this.mSelectedCreatures);
        }
        this.mAlbumId = ArgumentsUtil.getArgValue(getLocationKey(), "id", -1);
        this.mAlbumName = ArgumentsUtil.getArgValue(getLocationKey(), "name", (String) null);
        this.mSpaceId = ArgumentsUtil.getArgValue(getLocationKey(), BundleKey.SPACE_ID, (String) null);
        this.mGroupId = ArgumentsUtil.getArgValue(getLocationKey(), "groupId", (String) null);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new CategoryMenuUpdater(v, this) {
            public int getSelectDoneMenuTitle() {
                if (CreatureSelectPresenterV2.this.isEditMode()) {
                    return super.getSelectDoneMenuTitle();
                }
                return R.string.next;
            }

            public boolean isVisibleSelectDoneMenu() {
                return !CreatureSelectPresenterV2.this.mSelectedCreatures.isEmpty();
            }
        };
    }
}
