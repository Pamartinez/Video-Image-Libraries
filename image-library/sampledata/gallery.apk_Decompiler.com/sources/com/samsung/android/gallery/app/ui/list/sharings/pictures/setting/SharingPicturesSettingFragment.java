package com.samsung.android.gallery.app.ui.list.sharings.pictures.setting;

import A4.L;
import H7.A;
import O3.l;
import O3.o;
import P5.a;
import P5.b;
import P5.c;
import P5.d;
import P5.e;
import P5.f;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.SwitchPreferenceCompat;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.ChangeSharedAlbumCoverCmd;
import com.samsung.android.gallery.app.controller.sharing.RenameSharedAlbumCmd;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.module.album.AutoAlbumSettingData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.settings.ui.BasePreferenceFragment;
import com.samsung.android.gallery.settings.ui.BasePresenter;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.settings.ui.delegate.DialogDelegate;
import com.samsung.android.gallery.settings.widget.ExSumSwitchPreference;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.mobileservice.social.share.result.SpaceResult;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingPicturesSettingFragment extends BasePreferenceFragment<IBasePreferenceView> implements IBaseFragment, IBasePreferenceView, EventContext {
    private static final boolean SUPPORT_PET = Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM);
    private AutoAlbumSettingData mAutoAlbumSettingData;
    private Blackboard mCaller;
    private final DialogDelegate.OnDialogListener mDialogListener = new DialogDelegate.OnDialogListener() {
        public void onConfirmed(int i2) {
            SharingPicturesSettingFragment.this.confirmChange();
        }

        public void onDismiss(DropDownPreference dropDownPreference, int i2) {
            int suggestedContentsRuleType = SharingPicturesSettingFragment.this.getAutoAlbumSettingData().getSuggestedContentsRuleType();
            dropDownPreference.setValueIndex(suggestedContentsRuleType);
            dropDownPreference.seslGetSpinner().setSelection(suggestedContentsRuleType);
        }
    };
    private int mFamilyAlbumId;
    private String mGroupId;
    private MediaItem mHeaderItem;
    private boolean mIsOwner;
    /* access modifiers changed from: private */
    public String mSpaceId;
    private String mSpaceName;
    private long mSpaceWebLinkExpiry;
    private String mSpaceWebLinkUrl;

    /* access modifiers changed from: private */
    public void confirmChange() {
        getAutoAlbumSettingData().updateSuggestedContentsRuleType(1);
    }

    private void confirmSuggestedContentsRule(DropDownPreference dropDownPreference, int i2) {
        if (dropDownPreference != null) {
            new DialogDelegate(this).showSuggestedContentsRuleConfirmDialog(dropDownPreference, i2, this.mDialogListener);
        }
    }

    private void executeRequestErrorHandling(boolean z) {
        int i2;
        Context context = getContext();
        if (z) {
            i2 = R.string.share_link_error_on;
        } else {
            i2 = R.string.share_link_error_off;
        }
        Utils.showToast(context, i2);
        ThreadUtil.postOnUiThreadDelayed(new c(this, 1), 300);
        String str = this.TAG;
        Log.e((CharSequence) str, "onSharingAlbumLinkChanged api failed[", z + "], ", this.mSpaceId);
    }

    private String getAlbumLinkDescription(boolean z, long j2) {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getString(R.string.sharing_album_link_description));
        if (z) {
            str = "\n" + getString(R.string.expired_description, getLocalTime(j2));
        } else {
            str = "";
        }
        sb2.append(str);
        return sb2.toString();
    }

    /* access modifiers changed from: private */
    public AutoAlbumSettingData getAutoAlbumSettingData() {
        if (this.mAutoAlbumSettingData == null) {
            this.mAutoAlbumSettingData = new AutoAlbumSettingData(this.mFamilyAlbumId);
        }
        return this.mAutoAlbumSettingData;
    }

    private String getLocalTime(long j2) {
        return TimeUtil.toLocalDate(j2, "YYMD");
    }

    private void initAlbumLink() {
        boolean z;
        if (!PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK || MdeGroupHelper.isSAFamilyGroupId(this.mGroupId)) {
            removePreference(MiscSettingPreference.SharedAlbumLink.key);
            removePreference(MiscSettingPreference.SharedAlbumShortcut.key);
            return;
        }
        ExSumSwitchPreference exSumSwitchPreference = (ExSumSwitchPreference) findPreference(MiscSettingPreference.SharedAlbumLink.key);
        Preference findPreference = findPreference(MiscSettingPreference.SharedAlbumShortcut.key);
        if (exSumSwitchPreference != null && findPreference != null) {
            if (this.mSpaceWebLinkUrl == null || !this.mIsOwner) {
                z = false;
            } else {
                z = true;
            }
            if (this.mIsOwner) {
                exSumSwitchPreference.setSummary((CharSequence) getAlbumLinkDescription(z, this.mSpaceWebLinkExpiry));
                exSumSwitchPreference.setExtraSummary(this.mSpaceWebLinkUrl);
                exSumSwitchPreference.setExtraSummaryColor(getResources().getColor(R.color.sharing_album_link_text_color, (Resources.Theme) null));
                exSumSwitchPreference.setChecked(z);
                exSumSwitchPreference.setOnPreferenceChangeListener(new d(this, 5));
                findPreference.setOnPreferenceClickListener(new d(this, 6));
            } else {
                exSumSwitchPreference.setVisible(false);
            }
            findPreference.setVisible(z);
        }
    }

    private void initSharingAlbumOption() {
        boolean z;
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(MiscSettingPreference.CategorySharedAlbum.key);
        if (preferenceCategory != null) {
            if (isFamilyAlbum() || this.mIsOwner) {
                z = true;
            } else {
                z = false;
            }
            preferenceCategory.setVisible(z);
            if (z) {
                Optional.ofNullable(findPreference(MiscSettingPreference.AlbumChangeCover.key)).ifPresent(new a(this, 6));
                Optional.ofNullable(findPreference(MiscSettingPreference.AlbumRename.key)).ifPresent(new a(this, 7));
            }
        }
    }

    private void initUpdateCategory() {
        if (!supportSuggestion()) {
            removePreference(MiscSettingPreference.CategoryAutoAlbum.key);
        } else if (findPreference(MiscSettingPreference.CategoryAutoAlbum.key) != null) {
            AutoAlbumSettingData autoAlbumSettingData = getAutoAlbumSettingData();
            autoAlbumSettingData.load();
            Optional.ofNullable((SwitchPreferenceCompat) findPreference(MiscSettingPreference.AlbumAutoUpdate.key)).ifPresent(new b(this, autoAlbumSettingData, 2));
            Optional.ofNullable(findPreference(MiscSettingPreference.AlbumSelectedPeople.key)).ifPresent(new b(this, autoAlbumSettingData, 0));
            MiscSettingPreference miscSettingPreference = MiscSettingPreference.AlbumSuggestRule;
            if (miscSettingPreference.support(getContext())) {
                Optional.ofNullable((DropDownPreference) findPreference(miscSettingPreference.key)).ifPresent(new b(this, autoAlbumSettingData, 1));
            } else {
                removePreference(miscSettingPreference.key);
            }
        }
    }

    private boolean isFamilyAlbum() {
        return MdeGroupHelper.isSAFamilyGroup(this.mGroupId);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeRequestErrorHandling$17(ExSumSwitchPreference exSumSwitchPreference) {
        boolean z = false;
        exSumSwitchPreference.showProgress(false);
        if (this.mSpaceWebLinkUrl != null && this.mIsOwner) {
            z = true;
        }
        exSumSwitchPreference.setChecked(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeRequestErrorHandling$18() {
        Optional.ofNullable((ExSumSwitchPreference) findPreference(MiscSettingPreference.SharedAlbumLink.key)).ifPresent(new a(this, 4));
        setPreferenceEnabled(MiscSettingPreference.SharedAlbumShortcut.key, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initSharingAlbumOption$10(Preference preference) {
        preference.setOnPreferenceClickListener(new d(this, 3));
        preference.setSummary((CharSequence) this.mSpaceName);
        preference.seslSetSummaryColor(preference.getContext().getColor(R.color.settings_value_text_color));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initSharingAlbumOption$9(Preference preference) {
        boolean z;
        MediaItem mediaItem = this.mHeaderItem;
        if (mediaItem == null || mediaItem.getCount() <= 0) {
            z = false;
        } else {
            z = true;
        }
        preference.setVisible(z);
        if (z) {
            preference.setOnPreferenceClickListener(new d(this, 4));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initUpdateCategory$19(AutoAlbumSettingData autoAlbumSettingData, SwitchPreferenceCompat switchPreferenceCompat) {
        switchPreferenceCompat.setChecked(autoAlbumSettingData.isAutoUpdatingEnabled());
        switchPreferenceCompat.setOnPreferenceChangeListener(new d(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initUpdateCategory$20(AutoAlbumSettingData autoAlbumSettingData, Preference preference) {
        int i2;
        boolean z = SUPPORT_PET;
        if (z) {
            preference.setTitle((int) R.string.people_and_pets_to_add);
        }
        preference.setOnPreferenceClickListener(new d(this, 0));
        int creatureCount = autoAlbumSettingData.getCreatureCount();
        Resources resources = preference.getContext().getResources();
        if (z) {
            i2 = R.plurals.n_selected;
        } else {
            i2 = R.plurals.n_people_selected;
        }
        preference.setSummary((CharSequence) resources.getQuantityString(i2, creatureCount, new Object[]{Integer.valueOf(creatureCount)}));
        preference.seslSetSummaryColor(preference.getContext().getColor(R.color.settings_value_text_color));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initUpdateCategory$21(AutoAlbumSettingData autoAlbumSettingData, DropDownPreference dropDownPreference) {
        if (SUPPORT_PET) {
            dropDownPreference.setEntries((int) R.array.suggested_contents_rule_including_pets);
        }
        dropDownPreference.setValueIndex(autoAlbumSettingData.getSuggestedContentsRuleType());
        dropDownPreference.seslSetSummaryColor(getResources().getColor(R.color.settings_value_text_color, (Resources.Theme) null));
        dropDownPreference.setOnPreferenceChangeListener(new d(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSharingWebLinkPrefChanged$15(boolean z, SpaceResult spaceResult) {
        String str;
        long j2;
        Log.d(this.TAG, "onSharingAlbumLinkChanged", Boolean.valueOf(z), this.mSpaceId, MdeResultCode.toString(spaceResult.getStatus()));
        if (MdeResultCode.isSuccess(spaceResult.getStatus().getCode())) {
            if (z) {
                str = spaceResult.getResult().getWebLink().getUrl();
            } else {
                str = null;
            }
            if (z) {
                j2 = spaceResult.getResult().getWebLink().getExpiredTime();
            } else {
                j2 = 0;
            }
            updateLinkSwitchState(z, str, j2);
            return;
        }
        executeRequestErrorHandling(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSharingWebLinkPrefChanged$16(boolean z) {
        if (!MdeResultCode.isSuccess(MdeSharingHelper.requestWebLinkEnabled(this.mGroupId, this.mSpaceId, z, new E7.c(this, z, 2)))) {
            executeRequestErrorHandling(z);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1(View view) {
        BlackboardUtils.publishBackKeyEvent(getBlackboard());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$2(GalleryToolbar galleryToolbar) {
        galleryToolbar.setTitle((int) R.string.sharing_album_setting);
        galleryToolbar.setNavigationIcon((int) R.drawable.tw_ic_ab_back_mtrl_with_inset);
        galleryToolbar.setNavigationContentDescription(R.string.navigate_up);
        galleryToolbar.setNavigationOnClickListener(new A(19, this));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onViewCreated$3(GalleryAppBarLayout galleryAppBarLayout) {
        galleryAppBarLayout.setTitle((int) R.string.sharing_album_setting);
        galleryAppBarLayout.setSubtitle((CharSequence) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLinkSwitchState$11(boolean z, long j2, String str, ExSumSwitchPreference exSumSwitchPreference) {
        exSumSwitchPreference.setChecked(z);
        exSumSwitchPreference.setSummary((CharSequence) getAlbumLinkDescription(z, j2));
        exSumSwitchPreference.setExtraSummary(str);
        exSumSwitchPreference.showProgress(false);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateLinkSwitchState$12(boolean z, Preference preference) {
        preference.setVisible(z);
        preference.setEnabled(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLinkSwitchState$13(boolean z, long j2, String str) {
        boolean z3 = z;
        Optional.ofNullable((ExSumSwitchPreference) findPreference(MiscSettingPreference.SharedAlbumLink.key)).ifPresent(new f(this, z3, j2, str));
        Optional.ofNullable(findPreference(MiscSettingPreference.SharedAlbumShortcut.key)).ifPresent(new L(z3, 17));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePeopleCount$4(Preference preference) {
        int creatureCount = getAutoAlbumSettingData().getCreatureCount();
        postAnalyticsLog(AnalyticsEventId.EVENT_ALBUM_SETTINGS_SELECT_PEOPLE_TO_INCLUDE.toString(), String.valueOf(creatureCount));
        preference.setSummary((CharSequence) getResources().getQuantityString(R.plurals.n_people_selected, creatureCount, new Object[]{Integer.valueOf(creatureCount)}));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePeopleCount$5() {
        Optional.ofNullable(findPreference(MiscSettingPreference.AlbumSelectedPeople.key)).ifPresent(new a(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePeopleCount$6() {
        getAutoAlbumSettingData().loadSubscribeCreatureList();
        ThreadUtil.postOnUiThread(new c(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePreference$7(ExSumSwitchPreference exSumSwitchPreference) {
        boolean z;
        boolean z3 = false;
        if (this.mSpaceWebLinkUrl != null) {
            z = true;
        } else {
            z = false;
        }
        exSumSwitchPreference.setChecked(z);
        if (this.mSpaceWebLinkUrl != null) {
            z3 = true;
        }
        exSumSwitchPreference.setSummary((CharSequence) getAlbumLinkDescription(z3, this.mSpaceWebLinkExpiry));
        exSumSwitchPreference.setExtraSummary(this.mSpaceWebLinkUrl);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePreference$8(Object obj) {
        boolean z;
        Preference preference = (Preference) obj;
        if (this.mSpaceWebLinkUrl == null || !this.mIsOwner) {
            z = false;
        } else {
            z = true;
        }
        preference.setVisible(z);
    }

    /* access modifiers changed from: private */
    public void loadArgument(String str) {
        Bundle args = ArgumentsUtil.getArgs(str);
        this.mIsOwner = BundleWrapper.getBoolean(args, "owner", false);
        this.mSpaceName = args.getString("space_name");
        this.mSpaceId = args.getString(BundleKey.SPACE_ID);
        this.mGroupId = args.getString("groupId");
        this.mFamilyAlbumId = BundleWrapper.getInt(args, "familyAlbumId", 0);
        this.mSpaceWebLinkUrl = args.getString("space_weblink_url");
        this.mSpaceWebLinkExpiry = BundleWrapper.getLong(args, "space_weblink_expiry", 0);
        this.mCaller = Blackboard.getInstance(args.getString("blackboard_name"));
        StringBuilder sb2 = new StringBuilder(" Own : ");
        sb2.append(this.mIsOwner);
        sb2.append(" : ");
        sb2.append(Logger.getEncodedString(this.mSpaceName + ArcCommonLog.TAG_COMMA + this.mSpaceWebLinkUrl + ArcCommonLog.TAG_COMMA + this.mSpaceWebLinkExpiry));
        Log.d(this.TAG, "loadArgument", args.getString("blackboard_name"), " FAId : " + this.mFamilyAlbumId, " GID : " + this.mGroupId, sb2.toString());
    }

    private MediaItem loadHeaderItem() {
        MediaData open;
        try {
            open = MediaDataFactory.getInstance(getCallerBlackboard()).open("location://sharing/albums");
            for (int i2 = 0; i2 < open.getCount(); i2++) {
                if (MediaItemMde.getSpaceId(open.read(i2)).equals(this.mSpaceId)) {
                    MediaItem read = open.read(i2);
                    open.close();
                    return read;
                }
            }
            open.close();
            return null;
        } catch (Exception e) {
            Log.e(this.TAG, e.getMessage());
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void loadPreference() {
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new l(7));
        try {
            addPreferencesFromResource(R.xml.sharing_album_setting);
            initUpdateCategory();
            initSharingAlbumOption();
            initAlbumLink();
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("loadPreference failed. e="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public boolean onAutoUpdateChanged(Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_ALBUM_SETTINGS_AUTO_ADD_PICTURES_AND_VIDEOS.toString(), bool.booleanValue());
        getAutoAlbumSettingData().onAutoUpdateChanged(bool.booleanValue());
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onChangeSpaceCoverClicked(Preference preference) {
        if (!setInputBlock(this.TAG + "_onChangeSpaceCoverClicked", 500)) {
            return false;
        }
        new ChangeSharedAlbumCoverCmd().execute(this, new Object[0]);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onChangeSpaceNameClicked(Preference preference) {
        if (!setInputBlock(this.TAG + "_onChangeSpaceNameClicked", 500)) {
            return false;
        }
        new RenameSharedAlbumCmd().execute(this, AnalyticsEventId.EVENT_MENU_SHARING_RENAME_ALBUM);
        return true;
    }

    /* access modifiers changed from: private */
    public void onDataChanged(Bundle bundle) {
        this.mSpaceName = bundle.getString("space_name");
        this.mSpaceWebLinkUrl = bundle.getString("space_weblink_url");
        this.mSpaceWebLinkExpiry = BundleWrapper.getLong(bundle, "space_weblink_expiry", 0);
        this.mHeaderItem = loadHeaderItem();
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mIsOwner);
        sb2.append(" : ");
        sb2.append(Logger.getEncodedString(this.mSpaceName + ArcCommonLog.TAG_COMMA + this.mSpaceWebLinkUrl + ArcCommonLog.TAG_COMMA + this.mSpaceWebLinkExpiry));
        Log.d(str, "onDataChanged", sb2.toString());
    }

    /* access modifiers changed from: private */
    public boolean onSelectedCreatureClicked(Preference preference) {
        String str;
        Features features = Features.SUPPORT_PET_ON_AUTO_ALBUM;
        if (Features.isEnabled(features)) {
            str = "location://search/fileList/Category/CreatureSelect";
        } else {
            str = "location://search/fileList/Category/PeopleSelect";
        }
        UriBuilder appendArg = new UriBuilder(str).appendArg("from_sharing_setting", true).appendArg("id", this.mFamilyAlbumId).appendArg("groupId", this.mGroupId).appendArg(BundleKey.SPACE_ID, this.mSpaceId);
        if (Features.isEnabled(features)) {
            appendArg.appendArg("creatures_ids", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, getAutoAlbumSettingData().getCreatureIds()));
        } else {
            appendArg.appendArg("ids", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, getAutoAlbumSettingData().getPeopleIds()));
        }
        getBlackboard().post("command://MoveURL", appendArg.build());
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onSharingWebLinkClicked(Preference preference) {
        if (!setInputBlock(this.TAG + "_onSharingWebLinkClicked", 1000)) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", this.mSpaceWebLinkUrl);
        startActivity(Intent.createChooser(intent, getString(R.string.share_short)));
        postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_ALBUM_LINK_SELECT.toString());
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onSharingWebLinkPrefChanged(Preference preference, Object obj) {
        ExSumSwitchPreference exSumSwitchPreference = (ExSumSwitchPreference) preference;
        if (exSumSwitchPreference.isProgressShowing()) {
            return false;
        }
        Optional.ofNullable(findPreference(MiscSettingPreference.SharedAlbumShortcut.key)).ifPresent(new l(10));
        exSumSwitchPreference.showProgress(true);
        boolean booleanValue = ((Boolean) obj).booleanValue();
        SimpleThreadPool.getInstance().execute(new B4.c((Object) this, booleanValue, 14));
        postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_ALBUM_LINK_SWITCH.toString(), AnalyticsDetail.getOnOff(booleanValue));
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onSuggestedContentsRuleChanged(Preference preference, Object obj) {
        String str;
        int parseInt = Integer.parseInt((String) obj);
        String analyticsEventId = AnalyticsEventId.EVENT_ALBUM_SETTINGS_ADD_PICTURES_AND_VIDEOS_WITH.toString();
        if (parseInt == 0) {
            str = AnalyticsDetail.EVENT_DETAIL_ADD_PICTURES_AND_VIDEOS_WITH_ANY_OF.toString();
        } else {
            str = AnalyticsDetail.EVENT_DETAIL_ADD_PICTURES_AND_VIDEOS_WITH_ALL_OF.toString();
        }
        postAnalyticsLog(analyticsEventId, str);
        if (parseInt == 1) {
            confirmSuggestedContentsRule((DropDownPreference) preference, this.mFamilyAlbumId);
            return false;
        }
        getAutoAlbumSettingData().updateSuggestedContentsRuleType(parseInt);
        return true;
    }

    private boolean supportSuggestion() {
        if (this.mFamilyAlbumId <= 0 || !SdkConfig.atLeast(SdkConfig.SEM.T_MR1)) {
            return false;
        }
        return true;
    }

    private void updateLinkSwitchState(boolean z, String str, long j2) {
        this.mSpaceWebLinkUrl = str;
        this.mSpaceWebLinkExpiry = j2;
        if (!isAdded()) {
            Log.e(this.TAG, "not attached to a context");
            return;
        }
        ThreadUtil.runOnUiThread(new e(this, z, j2, str));
    }

    private void updatePeopleCount() {
        SimpleThreadPool.getInstance().execute(new c(this, 2));
    }

    /* access modifiers changed from: private */
    public void updatePreference() {
        if (!isAdded()) {
            Log.e(this.TAG, "not attached to a context");
            return;
        }
        setPreferenceSummary(MiscSettingPreference.AlbumRename.key, this.mSpaceName);
        Optional.ofNullable((ExSumSwitchPreference) findPreference(MiscSettingPreference.SharedAlbumLink.key)).ifPresent(new a(this, 2));
        Optional.ofNullable(findPreference(MiscSettingPreference.SharedAlbumShortcut.key)).ifPresent(new a(this, 3));
    }

    public BasePresenter<IBasePreferenceView> createPresenter() {
        return new BasePresenter<IBasePreferenceView>(this) {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$setGlobalSubscriberList$0(Object obj, Bundle bundle) {
                Bundle args = ArgumentsUtil.getArgs((String) obj);
                String string = args.getString(BundleKey.SPACE_ID);
                if (string == null || !string.equals(SharingPicturesSettingFragment.this.mSpaceId)) {
                    String str = this.TAG;
                    StringBuilder sb2 = new StringBuilder("not matched space id ");
                    StringBuilder t = C0212a.t(string, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    t.append(SharingPicturesSettingFragment.this.mSpaceId);
                    sb2.append(Logger.getEncodedString(t.toString()));
                    Log.e(str, sb2.toString());
                    return;
                }
                SharingPicturesSettingFragment.this.onDataChanged(args);
                SharingPicturesSettingFragment.this.updatePreference();
            }

            public void setGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
                arrayList.add(new SubscriberInfo("global://sharing/setting/dataChanged", new a(this)).setWorkingOnUI());
            }
        };
    }

    public Blackboard getCallerBlackboard() {
        return (Blackboard) Optional.ofNullable(this.mCaller).orElse(getBlackboard());
    }

    public MediaItem getHeaderItem() {
        return this.mHeaderItem;
    }

    public String getScreenId() {
        AnalyticsScreenId analyticsScreenId;
        if (isFamilyAlbum()) {
            analyticsScreenId = AnalyticsScreenId.SCREEN_SHARED_FAMILY_VIEW_SETTING;
        } else {
            analyticsScreenId = AnalyticsScreenId.SCREEN_SHARED_VIEW_SETTING;
        }
        return analyticsScreenId.toString();
    }

    public int getTitleId() {
        return R.string.sharing_album_setting;
    }

    public Toolbar getToolbar() {
        if (getView() != null) {
            return (Toolbar) getView().findViewById(R.id.toolbar);
        }
        return null;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Optional.ofNullable(getArguments()).map(new o(7)).ifPresent(new a(this, 5));
        this.mHeaderItem = loadHeaderItem();
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadPreference();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(R.layout.activity_settings_layout, viewGroup, false);
        inflate.setFitsSystemWindows(true);
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(R.id.content);
        if (viewGroup2 != null) {
            viewGroup2.addView(onCreateView);
        }
        return inflate;
    }

    public boolean onHandleBroadcastEvent(Object obj, Bundle bundle) {
        EventMessage eventMessage = (EventMessage) obj;
        if (!isFamilyAlbum() || !supportSuggestion() || eventMessage.what != 6008) {
            return super.onHandleBroadcastEvent(obj, bundle);
        }
        updatePeopleCount();
        return true;
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 6019) {
            return false;
        }
        Object obj = eventMessage.obj;
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) obj;
        if (!MediaItemMde.getSpaceId(this.mHeaderItem).equals(MediaItemMde.getSpaceId(mediaItem))) {
            return false;
        }
        MediaItem mediaItem2 = this.mHeaderItem;
        if (mediaItem2 != null) {
            mediaItem.setTitle(mediaItem2.getTitle());
        }
        this.mHeaderItem = mediaItem;
        return false;
    }

    public void onStart() {
        super.onStart();
        setScreenMode();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Optional.ofNullable((GalleryToolbar) view.findViewById(R.id.toolbar)).ifPresent(new a(this, 0));
        Optional.ofNullable((GalleryAppBarLayout) view.findViewById(R.id.appbar)).ifPresent(new l(11));
        updateMainLayoutBackgroundColor();
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public void setDefaultExitTransitioning(boolean z) {
    }
}
