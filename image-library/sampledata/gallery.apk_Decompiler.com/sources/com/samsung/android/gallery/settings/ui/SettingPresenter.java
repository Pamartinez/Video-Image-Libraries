package com.samsung.android.gallery.settings.ui;

import D7.g;
import Fa.F;
import Fa.W;
import T3.e;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
import androidx.preference.Preference;
import androidx.preference.SwitchPreferenceCompat;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dal.abstraction.StoryApi;
import com.samsung.android.gallery.database.dbtype.StoryLevel2Cat;
import com.samsung.android.gallery.module.badge.BadgeHelper;
import com.samsung.android.gallery.module.settings.DetailEnhancerOption;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.settings.helper.KnoxRestrictions;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.settings.ui.abstaction.ISettingView;
import com.samsung.android.gallery.settings.ui.delegate.DialogDelegate;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SettingPresenter<V extends ISettingView> extends BasePresenter<V> {
    public SettingPresenter(V v) {
        super(v);
    }

    private boolean handleTrashTurnOff(SwitchPreferenceCompat switchPreferenceCompat) {
        Context context = getContext();
        if (context == null) {
            switchPreferenceCompat.setChecked(false);
            SettingPreference.Trash.setAndNotifyIfChanged(getContext(), false);
            DebugLogger deleteInstance = DebugLogger.getDeleteInstance();
            deleteInstance.insertLog("[" + TrashLogType.TRASH_ENABLE + "][false][Setting]");
            return true;
        }
        new DialogDelegate((IBasePreferenceView) this.mView).showTrashTurnOffDialog(context, switchPreferenceCompat);
        return false;
    }

    private boolean handleTrashTurnOn() {
        SettingPreference.Trash.setAndNotifyIfChanged(getContext(), true);
        DebugLogger deleteInstance = DebugLogger.getDeleteInstance();
        deleteInstance.insertLog("[" + TrashLogType.TRASH_ENABLE + "][true][Setting]");
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$removeStoryAndRecapData$3() {
        StoryApi storyApi = DbCompat.storyApi();
        StoryLevel2Cat storyLevel2Cat = StoryLevel2Cat.QUARTERLY_RECAP_BRIEF;
        int i2 = storyLevel2Cat.sa_type;
        StoryLevel2Cat storyLevel2Cat2 = StoryLevel2Cat.YEARLY_RECAP_BRIEF;
        int i7 = storyLevel2Cat2.sa_type;
        StoryLevel2Cat storyLevel2Cat3 = StoryLevel2Cat.YEARLY_RECAP_PLACES;
        List<Integer> storyIdsFromSaType = storyApi.getStoryIdsFromSaType(new int[]{i2, i7, storyLevel2Cat3.sa_type});
        if (!storyIdsFromSaType.isEmpty()) {
            DbCompat.storyApi().getRecapFilePath((Integer[]) storyIdsFromSaType.toArray(new Integer[0])).forEach(new e(5));
        }
        boolean deleteStoryFromSaType = DbCompat.storyApi().deleteStoryFromSaType(new int[]{storyLevel2Cat.sa_type, storyLevel2Cat2.sa_type, storyLevel2Cat3.sa_type, StoryLevel2Cat.TRIP_SODA.sa_type, StoryLevel2Cat.TRIP_V7_SODA.sa_type});
        if (deleteStoryFromSaType) {
            StoryUpdateNotifier.getInstance().notifyStory(getContext(), true);
        }
        Log.d(this.TAG, "remove story, recap", Boolean.valueOf(deleteStoryFromSaType), Integer.valueOf(storyIdsFromSaType.size()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setGlobalSubscriberList$0(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).loadPreference();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setGlobalSubscriberList$1(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).loadPreference();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateAboutPageBadge$2(Preference preference) {
        boolean hasBadgeOnAboutPagePref = BadgeHelper.hasBadgeOnAboutPagePref();
        preference.setDotVisibility(hasBadgeOnAboutPagePref);
        if (hasBadgeOnAboutPagePref && PreferenceCache.AboutPageBadgeOnTab.compareAndSet(true, false)) {
            Blackboard.publishGlobal("data://settings_badge_updated", Boolean.FALSE);
        }
    }

    /* access modifiers changed from: private */
    public void onAutoPlayMotionPhotoChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.MotionPhotoAutoPlay);
    }

    /* access modifiers changed from: private */
    public void onAutoStoryChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.StoryAutoCreation);
    }

    /* access modifiers changed from: private */
    public void onCmhProviderChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.CmhProvider.key, ((Boolean) obj).booleanValue());
    }

    /* access modifiers changed from: private */
    public void onDetailEnhancerChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setPreferenceSummary(SettingPreference.DetailEnhancer.key, AppResources.getString(((DetailEnhancerOption) obj).getTitleResId()));
    }

    /* access modifiers changed from: private */
    public void onEditMenuOptionsBaiduChanged(Object obj, Bundle bundle) {
        SettingPreference settingPreference = SettingPreference.BaiduCloud;
        ((ISettingView) this.mView).setPreferenceVisible(settingPreference.key, settingPreference.isEnabled());
        ((ISettingView) this.mView).setPreferenceCategoryVisible(SettingPreference.CloudCategory.key);
    }

    /* access modifiers changed from: private */
    public void onEditMenuOptionsTencentChanged(Object obj, Bundle bundle) {
        SettingPreference settingPreference = SettingPreference.TencentCloud;
        ((ISettingView) this.mView).setPreferenceVisible(settingPreference.key, settingPreference.isEnabled());
        ((ISettingView) this.mView).setPreferenceCategoryVisible(SettingPreference.CloudCategory.key);
    }

    /* access modifiers changed from: private */
    public void onHdr10TransCodingChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.Hdr10PlusAutoConv);
    }

    /* access modifiers changed from: private */
    public void onHeifTranscodingChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.HeifAutoConv);
    }

    /* access modifiers changed from: private */
    public void onKnoxRestrictionsChanged(Object obj, Bundle bundle) {
        KnoxRestrictions of2 = KnoxRestrictions.of(getContext());
        ((ISettingView) this.mView).setPreferenceEnabled(SettingPreference.PhotoAssist.key, !of2.contains("key_photo_editor"));
        ((ISettingView) this.mView).setPreferenceEnabled(SettingPreference.AudioEraser.key, !of2.contains("key_audio_eraser"));
    }

    /* access modifiers changed from: private */
    public void onLocationAuthChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.LocationAuth);
    }

    /* access modifiers changed from: private */
    public void onMeituChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.Meitu);
    }

    /* access modifiers changed from: private */
    public void onMergeAlbumsChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.MergedAlbums);
    }

    /* access modifiers changed from: private */
    public void onOpenInVideoPlayerChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.OpenInVideoPlayer);
    }

    /* access modifiers changed from: private */
    public void onSelectEssentialAlbumsChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.EssentialAlbum);
    }

    /* access modifiers changed from: private */
    public void onSharingBlockerChanged(Object obj, Bundle bundle) {
        if (PreferenceFeatures.CHINA.SHARING_SERVICE_ENABLER) {
            Utils.recreateActivity(getActivity());
        }
    }

    /* access modifiers changed from: private */
    public void onSharingNotificationChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.SharedNotification);
    }

    /* access modifiers changed from: private */
    public void onShowHdrImagesChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.PhotoHdr);
    }

    /* access modifiers changed from: private */
    public void onStoryNotificationStateChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.StoryNotification);
    }

    /* access modifiers changed from: private */
    public void onTrashChanged(Object obj, Bundle bundle) {
        ((ISettingView) this.mView).setSwitchPreferenceChecked(SettingPreference.Trash);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!Features.isEnabled(Features.IS_THEME_INSTALLED)) {
            TypedValue typedValue = new TypedValue();
            Activity activity = getActivity();
            if (activity != null) {
                activity.getTheme().resolveAttribute(16842836, typedValue, true);
                activity.getWindow().getDecorView().setBackground(activity.getDrawable(typedValue.resourceId));
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        removeSettingsBadgeOnBottomTab();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        updateAboutPageBadge();
    }

    public boolean onTrashPrefChanged(SwitchPreferenceCompat switchPreferenceCompat, boolean z) {
        if (z) {
            return handleTrashTurnOn();
        }
        return handleTrashTurnOff(switchPreferenceCompat);
    }

    public void removeSettingsBadgeOnBottomTab() {
        if (PreferenceCache.OneDriveBadgeOnTab.compareAndSet(true, false)) {
            Blackboard.publishGlobal("data://settings_badge_updated", Boolean.FALSE);
        }
    }

    public void removeStoryAndRecapData() {
        if (Features.isEnabled(Features.CMH_TO_MP_MIGRATION) && Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
            SimpleThreadPool.getInstance().submit(new g(16, this));
        }
    }

    public void setGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://setting/editMenuOptions/Baidu", new W(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/editMenuOptions/Tencent", new W(this, 2)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/motion_photo_auto_play", new W(this, 4)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/open_in_video_player", new W(this, 5)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/meitu", new W(this, 6)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/showHdrImages", new W(this, 7)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://event/knoxRestrictionsChanged", new W(this, 8)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/detailEnhancer", new W(this, 9)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/stories/AutoStory", new W(this, 10)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/stories/StoryNotificationState", new W(this, 12)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/albums/selectEssentialAlbums", new W(this, 11)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/albums/mergeAlbums", new W(this, 13)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/sharings/Notification", new W(this, 14)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/secure/shared_album_blocker_changed", new W(this, 15)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/advanced/cmhProvider", new W(this, 16)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/advanced/LocationAuth", new W(this, 17)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/advanced/Trash", new W(this, 18)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/advanced/HeifTransCoding", new W(this, 19)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/advanced/HDR10TransCoding", new W(this, 20)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/labs/enabling", new W(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/assistant/PackageChanged", new W(this, 3)).setWorkingOnUI());
    }

    public void updateAboutPageBadge() {
        Optional.ofNullable(((ISettingView) this.mView).findPreference(SettingPreference.About.key)).ifPresent(new F(6));
    }
}
