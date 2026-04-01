package com.samsung.android.gallery.settings.ui;

import E9.a;
import Fa.F;
import Fa.O;
import Fa.P;
import Fa.Q;
import Fa.S;
import Fa.T;
import Fa.U;
import Fa.V;
import N2.j;
import O3.l;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.SeslSwitchPreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.search.rubin.RubinManager;
import com.samsung.android.gallery.module.settings.CmhProviderPermission;
import com.samsung.android.gallery.module.settings.DetailEnhancerOption;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$menu;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.settings.helper.KnoxRestrictions;
import com.samsung.android.gallery.settings.helper.PopoverUtils;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.settings.ui.abstaction.ISettingView;
import com.samsung.android.gallery.settings.ui.delegate.DialogDelegate;
import com.samsung.android.gallery.settings.ui.delegate.NavigateAppCmd;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SettingFragment<V extends ISettingView> extends BasePreferenceFragment<V> implements ISettingView {
    private boolean mIsPopover;
    private final MenuProvider mMenuProvider = new MenuProvider() {
        public void onCreateMenu(Menu menu, MenuInflater menuInflater) {
            menu.clear();
            menuInflater.inflate(R$menu.menu_settings, menu);
        }

        public boolean onMenuItemSelected(MenuItem menuItem) {
            if (menuItem.getItemId() != R$id.edit_menu_options) {
                return true;
            }
            new NavigateAppCmd((IBaseView) SettingFragment.this).startEditMenuOptions(SettingFragment.this.getContext());
            return true;
        }
    };
    private final SettingCloud mSettingCloud = new SettingCloud2(this);
    private final SettingTipCard mSettingTipCard = new SettingTipCard(this);

    private void initAboutPreference() {
        SettingPreference settingPreference = SettingPreference.CategoryAboutPage;
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(settingPreference.key);
        if (preferenceCategory != null) {
            computePreference((PreferenceGroup) preferenceCategory, SettingPreference.About, new P(this, 11));
            computePreference((PreferenceGroup) preferenceCategory, SettingPreference.ContactUs, new P(this, 12));
            SettingPreference settingPreference2 = SettingPreference.GalleryLabs;
            if (settingPreference2.support(preferenceCategory.getContext())) {
                addGenericPreference(settingPreference.key, settingPreference2.key, getString(R$string.gallery_labs_title), (String) null, (Preference.OnPreferenceClickListener) new O(this, 3));
            }
        }
    }

    private void initAdvancedPreference() {
        String charSequence;
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(SettingPreference.CategoryAdvanced.key);
        if (preferenceCategory != null) {
            computeSwitchPreference(preferenceCategory, SettingPreference.Trash, new P(this, 1));
            computeSwitchPreference(preferenceCategory, SettingPreference.CmhProvider, new P(this, 2));
            SettingPreference settingPreference = SettingPreference.LocationAuth;
            if (computeSwitchPreference(preferenceCategory, settingPreference, new P(this, 3)) == null) {
                settingPreference.setAndNotifyIfChanged(preferenceCategory.getContext(), false);
            }
            computeSwitchPreference(preferenceCategory, SettingPreference.HeifAutoConv, new P(this, 4));
            computeSwitchPreference(preferenceCategory, SettingPreference.Hdr10PlusAutoConv, new P(this, 5));
            if (Features.isEnabled(Features.SUPPORT_GALLERY_ASSISTANT)) {
                CharSequence applicationLabel = PackageMonitorCompat.getInstance().getApplicationLabel("com.samsung.android.gallery.assistant.app");
                if (applicationLabel == null) {
                    charSequence = getString(R$string.gallery_assistant);
                } else {
                    charSequence = applicationLabel.toString();
                }
                addGenericPreference((PreferenceGroup) preferenceCategory, "gallery_assistant", charSequence, (String) null, (Preference.OnPreferenceClickListener) new O(this, 2));
            }
        }
    }

    private void initAlbumsPreference() {
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(SettingPreference.CategoryAlbum.key);
        if (preferenceCategory != null) {
            if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                preferenceCategory.setTitle(R$string.tab_tag_albums);
            }
            computeSwitchPreference(preferenceCategory, SettingPreference.EssentialAlbum, new P(this, 21));
            computeSwitchPreference(preferenceCategory, SettingPreference.MergedAlbums, new P(this, 22));
            SettingPreference settingPreference = SettingPreference.SharingServiceEnabler;
            if (settingPreference.support(preferenceCategory.getContext())) {
                SeslSwitchPreferenceScreen seslSwitchPreferenceScreen = new SeslSwitchPreferenceScreen(preferenceCategory.getContext());
                seslSwitchPreferenceScreen.setKey(settingPreference.key);
                seslSwitchPreferenceScreen.setTitle(R$string.shared_album);
                seslSwitchPreferenceScreen.setChecked(settingPreference.isEnabled());
                preferenceCategory.addPreference(seslSwitchPreferenceScreen);
                seslSwitchPreferenceScreen.setOnPreferenceClickListener(new O(this, 0));
                seslSwitchPreferenceScreen.setOnPreferenceChangeListener(new O(this, 1));
                removePreference(SettingPreference.SharedNotification.key);
            } else {
                computeSwitchPreference(preferenceCategory, SettingPreference.SharedNotification, new P(this, 0));
            }
            setPreferenceCategoryVisible((PreferenceGroup) preferenceCategory);
        }
    }

    private void initEditingPreference() {
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(SettingPreference.EditingCategory.key);
        if (preferenceCategory != null) {
            KnoxRestrictions of2 = KnoxRestrictions.of(preferenceCategory.getContext());
            computePreference((PreferenceGroup) preferenceCategory, SettingPreference.PhotoAssist, new S(this, of2, 0));
            computePreference((PreferenceGroup) preferenceCategory, SettingPreference.AudioEraser, new S(this, of2, 1));
            computePreference((PreferenceGroup) preferenceCategory, SettingPreference.AIEditSuggestions, new P(this, 14));
            computePreference((PreferenceGroup) preferenceCategory, SettingPreference.DetailEnhancer, new P(this, 15));
            computePreference((PreferenceGroup) preferenceCategory, SettingPreference.PhotoEditorSettings, new P(this, 16));
            removePreferenceIfEmpty(preferenceCategory);
        }
    }

    private void initIntelligentFeaturesPreference() {
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(SettingPreference.IntelligentFeaturesCategory.key);
        if (preferenceCategory != null) {
            computePreference((PreferenceGroup) preferenceCategory, SettingPreference.IntelligentFeatures, new P(this, 13));
            removePreferenceIfEmpty(preferenceCategory);
        }
    }

    private void initPrivacyPreference() {
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(SettingPreference.CategoryPrivacy.key);
        if (preferenceCategory != null) {
            computePreference((PreferenceGroup) preferenceCategory, SettingPreference.PrivacyPolicy, new P(this, 9));
            computePreference((PreferenceGroup) preferenceCategory, SettingPreference.AppPermission, new P(this, 10));
        }
    }

    private void initStoryPreference() {
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(SettingPreference.CategoryStory.key);
        if (preferenceCategory != null) {
            SettingPreference settingPreference = SettingPreference.StoryAutoCreation;
            if (computeSwitchPreference(preferenceCategory, settingPreference, new P(this, 6)) == null && !Features.isEnabled(Features.SUPPORT_AUTO_CREATE_STORY)) {
                settingPreference.setEnabled(true);
            }
            computeSwitchPreference(preferenceCategory, SettingPreference.StoryNotification, new P(this, 7));
            computePreference((PreferenceGroup) preferenceCategory, SettingPreference.Rubin, new P(this, 8));
            setPreferenceCategoryVisible((PreferenceGroup) preferenceCategory);
        }
    }

    private void initViewingPreference() {
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(SettingPreference.ViewingCategory.key);
        if (preferenceCategory != null) {
            computeSwitchPreference(preferenceCategory, SettingPreference.PhotoHdr, new P(this, 17));
            computeSwitchPreference(preferenceCategory, SettingPreference.MotionPhotoAutoPlay, new P(this, 18));
            computeSwitchPreference(preferenceCategory, SettingPreference.OpenInVideoPlayer, new P(this, 19));
            computeSwitchPreference(preferenceCategory, SettingPreference.Meitu, new P(this, 20));
            removePreferenceIfEmpty(preferenceCategory);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAboutPreference$58(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startAboutGallery(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAboutPreference$59(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new O(this, 11));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAboutPreference$60(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startContactUs(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAboutPreference$61(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new O(this, 14));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAboutPreference$62(Preference preference) {
        return commitFragment(new LabsFragment());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAdvancedPreference$43(Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        if (!bool.booleanValue()) {
            if (!setInputBlock(this.TAG + "_Trash", 500)) {
                return false;
            }
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_TRASH.toString(), bool.booleanValue());
        return getPresenter().onTrashPrefChanged((SwitchPreferenceCompat) preference, bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAdvancedPreference$44(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new O(this, 16));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAdvancedPreference$45(SettingPreference settingPreference, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_CMH_PROVIDER.toString(), bool.booleanValue());
        settingPreference.notifyChanged(preference.getContext(), bool.booleanValue());
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAdvancedPreference$46(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new T(this, settingPreference, 1));
        CmhProviderPermission.load(new a(11, switchPreferenceCompat));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAdvancedPreference$47(SettingPreference settingPreference, SwitchPreferenceCompat switchPreferenceCompat, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        if (bool.booleanValue()) {
            if (!setInputBlock(this.TAG + "_onLocationAuthPrefChanged", 500)) {
                return false;
            }
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_SHOW_PLACE_NAMES.toString(), bool.booleanValue());
        if (!bool.booleanValue() || settingPreference.isEnabled()) {
            removeStoryAndRecapData(bool.booleanValue());
            settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
        } else {
            new DialogDelegate(this).showGdprDialog(getActivity(), new Q(this, 2));
        }
        return switchPreferenceCompat.isChecked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAdvancedPreference$48(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new A4.Q((Object) this, (Object) settingPreference, (Object) switchPreferenceCompat, 2));
        if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
            switchPreferenceCompat.setTitle(R$string.show_location_info);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAdvancedPreference$49(SettingPreference settingPreference, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_AUTO_CONVERT_HEIF.toString(), bool.booleanValue());
        return settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAdvancedPreference$50(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new T(this, settingPreference, 5));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAdvancedPreference$51(SettingPreference settingPreference, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_AUTO_CONVERT_HDR_TEN_PLUS.toString(), bool.booleanValue());
        return settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAdvancedPreference$52(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new T(this, settingPreference, 7));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAdvancedPreference$53(Preference preference) {
        try {
            preference.getContext().startActivity(new Intent("com.samsung.android.gallery.assistant.app.action.ACTION_LAUNCH_ASSISTANT_GALLERY"));
            return true;
        } catch (ActivityNotFoundException | NullPointerException e) {
            j.u(e, new StringBuilder("startGalleryAssistant failed e="), this.TAG);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAlbumsPreference$34(SettingPreference settingPreference, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_SELECT_ESSENTIAL_ALBUMS.toString(), bool.booleanValue());
        settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAlbumsPreference$35(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new T(this, settingPreference, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAlbumsPreference$36(SettingPreference settingPreference, Preference preference, Object obj) {
        if (!setInputBlock(this.TAG + "_initAlbumsPreference", 500)) {
            return false;
        }
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_MERGE_ALBUMS.toString(), bool.booleanValue());
        return settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAlbumsPreference$37(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new T(this, settingPreference, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAlbumsPreference$38(Preference preference) {
        return commitFragment(new SharingServiceFragment());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAlbumsPreference$39(Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        SettingPreference.SharedNotification.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_SHARED_ALBUM_SERVICE_SWITCH.toString(), bool.booleanValue());
        SettingPreference.SharingServiceEnabler.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAlbumsPreference$40(Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_SHARED_ALBUM_NOTIFICATIONS_SWITCH.toString(), bool.booleanValue());
        SettingPreference.SharedNotification.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initAlbumsPreference$41(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startNotifications(preference.getContext(), ((SeslSwitchPreferenceScreen) preference).isChecked());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAlbumsPreference$42(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            switchPreferenceCompat.setTitle(R$string.shared_album_notification);
        }
        switchPreferenceCompat.setOnPreferenceChangeListener(new O(this, 9));
        switchPreferenceCompat.setOnPreferenceClickListener(new O(this, 10));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initEditingPreference$14(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startPhotoAssist(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initEditingPreference$15(KnoxRestrictions knoxRestrictions, Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new O(this, 6));
        preference.setEnabled(!knoxRestrictions.contains("key_photo_editor"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initEditingPreference$16(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startAudioEraser(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initEditingPreference$17(KnoxRestrictions knoxRestrictions, Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new O(this, 5));
        preference.setEnabled(!knoxRestrictions.contains("key_audio_eraser"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initEditingPreference$18(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startAIEditSuggestions(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initEditingPreference$19(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new O(this, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initEditingPreference$20(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startDetailEnhancer(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initEditingPreference$21(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new O(this, 8));
        preference.setSummary(DetailEnhancerOption.getCurrent().getTitleResId());
        preference.seslSetSummaryColor(preference.getContext().getColor(R$color.settings_value_text_color));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initEditingPreference$22(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startPhotoEditorSettings(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initEditingPreference$23(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new O(this, 15));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initIntelligentFeaturesPreference$3(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startIntelligentFeatures(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initIntelligentFeaturesPreference$5(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new O(this, 7));
        ArrayList arrayList = new ArrayList(List.of(Integer.valueOf(R$string.edit_suggestions)));
        if (SettingPreference.ObjectEraser.support(preference.getContext())) {
            arrayList.add(Integer.valueOf(R$string.object_eraser));
        }
        if (SettingPreference.BestFace.support(preference.getContext())) {
            arrayList.add(Integer.valueOf(R$string.opt_best_face));
        }
        preference.setSummary((CharSequence) arrayList.stream().map(new A5.a(8, preference)).collect(Collectors.joining(ArcCommonLog.TAG_COMMA)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPrivacyPreference$54(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startPrivacyPolicy(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPrivacyPreference$55(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new O(this, 13));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPrivacyPreference$56(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startPermissions(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPrivacyPreference$57(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new O(this, 17));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initStoryPreference$24(SettingPreference settingPreference, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_AUTO_CREATE_STORIES.toString(), bool.booleanValue());
        return settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$initStoryPreference$25(SettingPreference settingPreference, SwitchPreferenceCompat switchPreferenceCompat, Boolean bool) {
        settingPreference.setEnabled(bool.booleanValue());
        switchPreferenceCompat.setChecked(bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initStoryPreference$26(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new T(this, settingPreference, 8));
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            updateStoryFeatureState("activate_story_recommendation", settingPreference.isEnabled(), new U(settingPreference, switchPreferenceCompat, 0));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initStoryPreference$27(SettingPreference settingPreference, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_STORY_NOTIFICATION.toString(), bool.booleanValue());
        return settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$initStoryPreference$28(SettingPreference settingPreference, SwitchPreferenceCompat switchPreferenceCompat, Boolean bool) {
        settingPreference.setEnabled(bool.booleanValue());
        switchPreferenceCompat.setChecked(bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initStoryPreference$29(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new T(this, settingPreference, 10));
        updateStoryFeatureState("story_notification", settingPreference.isEnabled(), new U(settingPreference, switchPreferenceCompat, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initStoryPreference$30(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startRubinManager(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initStoryPreference$31(Preference preference, SettingPreference settingPreference) {
        preference.setSummary((CharSequence) " ");
        preference.seslSetSummaryColor(preference.getContext().getColor(R$color.settings_value_text_color));
        preference.setOnPreferenceClickListener(new O(this, 12));
        RubinManager.loadRubinState(preference.getContext(), new a(12, preference));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initViewingPreference$10(SettingPreference settingPreference, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_OPEN_IN_VIDEO_PLAYER.toString(), bool.booleanValue());
        return settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initViewingPreference$11(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new T(this, settingPreference, 9));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initViewingPreference$12(SettingPreference settingPreference, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_MEITU_POSTS_AND_MOVIES.toString(), bool.booleanValue());
        return settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initViewingPreference$13(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new T(this, settingPreference, 6));
        if (!Features.isEnabled(Features.SUPPORT_MEITU_IMAGE_TO_IMAGE)) {
            switchPreferenceCompat.setTitle(R$string.meitu_posts_and_movies);
            switchPreferenceCompat.setSummary((CharSequence) getString(R$string.service_is_provided_by, getString(R$string.meitu_app)));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initViewingPreference$6(SettingPreference settingPreference, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_PHOTO_HDR.toString(), bool.booleanValue());
        return settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initViewingPreference$7(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new T(this, settingPreference, 3));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initViewingPreference$8(SettingPreference settingPreference, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_AUTO_PLAY_MOTION_PHOTO.toString(), bool.booleanValue());
        return settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initViewingPreference$9(SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        switchPreferenceCompat.setOnPreferenceChangeListener(new T(this, settingPreference, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$2(ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(isShowHomeAsUp());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onResume$1(Preference preference) {
        if (!" ".equals(String.valueOf(preference.getSummary()))) {
            RubinManager.loadRubinState(preference.getContext(), new a(12, preference));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(FragmentActivity fragmentActivity) {
        fragmentActivity.addMenuProvider(this.mMenuProvider, getViewLifecycleOwner());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateStoryFeatureState$32(Consumer consumer, boolean z) {
        try {
            consumer.accept(Boolean.valueOf(z));
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateStoryFeatureState$33(String str, boolean z, Consumer consumer) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            boolean isEnableFeatureState = DbCompat.storyFeatureApi().isEnableFeatureState(str);
            if (isEnableFeatureState == z) {
                return;
            }
            if (DbCompat.storyFeatureApi().updateFeatureState(str, z ? 1 : 0)) {
                String str2 = this.TAG;
                Log.d(str2, "story feature update" + Logger.vt(str, Boolean.valueOf(z), Boolean.valueOf(isEnableFeatureState), Long.valueOf(currentTimeMillis)));
                return;
            }
            String str3 = this.TAG;
            Log.e(str3, "story feature update failed" + Logger.vt(str, Boolean.valueOf(z), Boolean.valueOf(isEnableFeatureState), Long.valueOf(currentTimeMillis)));
            ThreadUtil.postOnUiThread(new V(consumer, 0, isEnableFeatureState));
        } catch (Exception e) {
            String str4 = this.TAG;
            Log.e(str4, "story feature update" + Logger.v(str, Boolean.valueOf(z)) + " failed. e=" + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void removeStoryAndRecapData(boolean z) {
        if (!z) {
            ((SettingPresenter) this.mPresenter).removeStoryAndRecapData();
        }
    }

    private void updateStoryFeatureState(String str, boolean z, Consumer<Boolean> consumer) {
        SimpleThreadPool.getInstance().execute(new F8.a((Object) this, (Object) str, z, (Object) consumer, 1));
    }

    public BasePresenter createPresenter() {
        return new SettingPresenter(this);
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SETTING.toString();
    }

    public boolean isShowHomeAsUp() {
        return !this.mIsPopover;
    }

    public void loadPreference() {
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new l(7));
        try {
            addPreferencesFromResource(R$xml.setting_preference_screen);
            this.mSettingTipCard.initPreference();
            this.mSettingCloud.initPreference();
            initIntelligentFeaturesPreference();
            initViewingPreference();
            initEditingPreference();
            initStoryPreference();
            initAlbumsPreference();
            initAdvancedPreference();
            initPrivacyPreference();
            initAboutPreference();
        } catch (NullPointerException e) {
            String str = this.TAG;
            Log.e(str, "loadPreference failed e=" + e.getMessage());
        }
    }

    public void onActivityResult(int i2, int i7, Intent intent) {
        this.mSettingCloud.onActivityResult(i2, i7, intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        boolean isPopover = PopoverUtils.isPopover(getContext());
        if (this.mIsPopover != isPopover) {
            this.mIsPopover = isPopover;
        }
        super.onConfigurationChanged(configuration);
        Optional.ofNullable(getActionBar()).ifPresent(new Q(this, 1));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSettingCloud.onCreate();
    }

    public RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferenceScreen) {
        return new HighlightGroupAdapter(preferenceScreen);
    }

    public RecyclerView.LayoutManager onCreateLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    public void onDestroy() {
        super.onDestroy();
        this.mSettingCloud.onDestroy();
    }

    public boolean onHandleBroadcastEvent(Object obj, Bundle bundle) {
        EventMessage eventMessage = (EventMessage) obj;
        String str = this.TAG;
        Log.d(str, "onHandleBroadcastEvent " + eventMessage);
        if (eventMessage.what != 1076) {
            return super.onHandleBroadcastEvent(obj, bundle);
        }
        this.mSettingCloud.updateBackUpSdCard(SamsungAccountManager.getInstance().getAccount());
        return true;
    }

    public boolean onHandleEvent(Object obj, Bundle bundle) {
        EventMessage eventMessage = (EventMessage) obj;
        String str = this.TAG;
        Log.d(str, "onHandleEvent " + eventMessage);
        switch (eventMessage.what) {
            case 7005:
                loadPreference();
                return true;
            case 7006:
            case 7007:
                this.mSettingTipCard.initPreference();
                return true;
            default:
                return super.onHandleEvent(obj, bundle);
        }
    }

    public void onResume() {
        super.onResume();
        this.mSettingCloud.onResume();
        SettingPreference settingPreference = SettingPreference.Rubin;
        if (settingPreference.support(getContext())) {
            Optional.ofNullable(findPreference(settingPreference.key)).ifPresent(new F(5));
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mIsPopover = PopoverUtils.isPopover(getContext());
        if (SettingPreference.BaiduCloud.support(getContext()) || SettingPreference.TencentCloud.support(getContext())) {
            Optional.ofNullable(getActivity()).ifPresent(new Q(this, 0));
        }
        loadPreference();
        showHighlightIfGuided(getArguments());
    }

    public SettingPresenter getPresenter() {
        return (SettingPresenter) super.getPresenter();
    }
}
