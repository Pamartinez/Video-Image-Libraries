package com.samsung.android.gallery.app.ui.list.search.category.people;

import A4.C0381p;
import Ad.C0720a;
import Gb.a;
import W9.d;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.app.controller.creature.people.RemoveSubscribedCreatureCmd;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import h4.C0464a;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import k6.b;
import n5.e;
import n5.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PeopleSelectPresenter<V extends ICreatureSelectView> extends CreatureSelectPresenter<V> {
    private int mAlbumId;
    private String mAlbumName;
    private String mGroupId;
    private boolean mOnlyUnsubscribed;
    private ArrayList<String> mOriginalSelectedCreatures = new ArrayList<>();
    private String mSpaceId;

    public PeopleSelectPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void addContents(String str) {
        if (MdeGroupHelper.isSAFamilyGroup(this.mGroupId)) {
            AutoAlbumHelper.getInstance().addFamilyAlbumContents(str, (long) this.mAlbumId);
        } else {
            AutoAlbumHelper.getInstance().addAutoAlbumContents(str, (long) this.mAlbumId);
        }
    }

    private ArrayList<Long> convertSelectedUnifiedIds() {
        return (ArrayList) this.mSelectedCreatures.stream().filter(new C0464a(14)).map(new a(14)).collect(Collectors.toCollection(new C0720a(1)));
    }

    private boolean createAutoAlbum() {
        ArrayList<Long> convertSelectedUnifiedIds = convertSelectedUnifiedIds();
        String includedMediaIds = PeopleDataManager.getIncludedMediaIds(convertSelectedUnifiedIds);
        if (TextUtils.isEmpty(includedMediaIds)) {
            return false;
        }
        int createAutoAlbum = AutoAlbumHelper.getInstance().createAutoAlbum(this.mAlbumName, includedMediaIds);
        if (!AutoAlbumHelper.getInstance().isValidAutoAlbum(createAutoAlbum)) {
            return true;
        }
        if (!convertSelectedUnifiedIds.isEmpty()) {
            AutoAlbumHelper.getInstance().subscribePeople((List) convertSelectedUnifiedIds.stream().map(new e(1)).collect(Collectors.toList()), (long) createAutoAlbum);
        }
        int addNewEmptyAutoAlbum = AlbumHelper.getInstance().addNewEmptyAutoAlbum(createAutoAlbum, this.mAlbumName);
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "count = " + addNewEmptyAutoAlbum);
        ThreadUtil.postOnUiThreadDelayed(new f(this, createAutoAlbum, 0), 100);
        return true;
    }

    private void editAutoAlbum() {
        int i2;
        List<Long> differenceUnifiedId = getDifferenceUnifiedId(this.mSelectedCreatures, this.mOriginalSelectedCreatures);
        ArrayList<Long> convertSelectedUnifiedIds = convertSelectedUnifiedIds();
        if (!differenceUnifiedId.isEmpty()) {
            String includedMediaIds = PeopleDataManager.getIncludedMediaIds(convertSelectedUnifiedIds);
            if (!TextUtils.isEmpty(includedMediaIds)) {
                addContents(includedMediaIds);
            }
            if (!convertSelectedUnifiedIds.isEmpty()) {
                AutoAlbumHelper.getInstance().subscribePeople((List) convertSelectedUnifiedIds.stream().map(new e(2)).collect(Collectors.toList()), (long) this.mAlbumId);
            }
        }
        List<Long> differenceUnifiedId2 = getDifferenceUnifiedId(this.mOriginalSelectedCreatures, this.mSelectedCreatures);
        if (!differenceUnifiedId2.isEmpty()) {
            AutoAlbumHelper.getInstance().unsubscribePeople(differenceUnifiedId2, (long) this.mAlbumId);
            if (differenceUnifiedId.isEmpty()) {
                this.mOnlyUnsubscribed = true;
            }
        }
        Blackboard blackboard = this.mBlackboard;
        if (isFromSharingSetting()) {
            i2 = 6008;
        } else {
            i2 = 101;
        }
        blackboard.postBroadcastEvent(EventMessage.obtain(i2));
    }

    private void finish() {
        ThreadUtil.postOnUiThread(new b(16, this));
    }

    private List<Long> getDifferenceUnifiedId(List<String> list, List<String> list2) {
        ArrayList arrayList = new ArrayList();
        list.forEach(new d(list2, arrayList, 2));
        return arrayList;
    }

    private void initData() {
        this.mAlbumId = ArgumentsUtil.getArgValue(getLocationKey(), "id", -1);
        this.mAlbumName = ArgumentsUtil.getArgValue(getLocationKey(), "name", (String) null);
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "ids", "");
        if (!TextUtils.isEmpty(argValue)) {
            this.mSelectedCreatures = (ArrayList) Stream.of(argValue.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new e(0)).collect(Collectors.toCollection(new C0720a(1)));
        }
        if (isEditMode()) {
            this.mOriginalSelectedCreatures = new ArrayList<>(this.mSelectedCreatures);
        }
        this.mSpaceId = ArgumentsUtil.getArgValue(getLocationKey(), BundleKey.SPACE_ID, (String) null);
        this.mGroupId = ArgumentsUtil.getArgValue(getLocationKey(), "groupId", (String) null);
    }

    private boolean isEditMode() {
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
    public static /* synthetic */ Pair lambda$createAutoAlbum$5(Long l) {
        return new Pair(l, "");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createAutoAlbum$6(int i2) {
        Blackboard.publishGlobal("data://usernew_item_creation", new Object[]{this.mAlbumName, null, -1, null, Integer.valueOf(i2)});
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Pair lambda$editAutoAlbum$3(Long l) {
        return new Pair(l, "");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finish$7() {
        this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        if (needToLaunchPreviewSuggestions() && !this.mOnlyUnsubscribed) {
            launchPreviewSuggestions();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getDifferenceUnifiedId$4(List list, List list2, String str) {
        if (!list.contains(str)) {
            list2.add(Long.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(str)));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$saveSelectedPeople$1(ThreadPool.JobContext jobContext) {
        if (isEditMode()) {
            editAutoAlbum();
            return null;
        }
        createAutoAlbum();
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveSelectedPeople$2(Future future) {
        finish();
    }

    private boolean needToLaunchPreviewSuggestions() {
        if (isFromPreviewSuggestions() || TextUtils.isEmpty(this.mGroupId) || TextUtils.isEmpty(this.mSpaceId) || !MdeGroupHelper.isSAFamilyGroup(this.mGroupId)) {
            return false;
        }
        return true;
    }

    private void removeSelectedPeople(int i2, MediaItem mediaItem) {
        this.mSelectedCreatures.remove(mediaItem.getSubCategory());
        ((ICreatureSelectView) this.mView).getAdapter().onUpdateViewHolder(i2);
        updateToolbar(getToolbar());
    }

    private void saveSelectedPeople() {
        if (this.mSelectedCreatures.isEmpty()) {
            Utils.showToast(getContext(), (int) R.string.select_at_least_a_person);
        } else {
            ThreadPool.getInstance().submit(new C0381p(10, this), new com.samsung.android.sdk.scs.ai.language.a(24, this));
        }
    }

    private void selectPerson(String str) {
        this.mSelectedCreatures.add(str);
        postAnalyticsLog(AnalyticsEventId.EVENT_CREATURE_PICKER_ITEM_SELECT, "1");
    }

    private boolean unselectPerson(int i2, MediaItem mediaItem) {
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

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 2010) {
            removeSelectedPeople(eventMessage.arg1, (MediaItem) eventMessage.obj);
            return true;
        } else if (i2 != 8001) {
            return super.handleEvent(eventMessage);
        } else {
            saveSelectedPeople();
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
                selectPerson(subCategory);
            } else if (!unselectPerson(i2, mediaItem)) {
                return;
            }
            ((ICreatureSelectView) this.mView).getAdapter().onUpdateViewHolder(i2);
            updateToolbar(getToolbar());
            ((ICreatureSelectView) this.mView).invalidateOptionsMenu();
        }
    }

    public void onLocationKeyAssigned() {
        initData();
    }
}
