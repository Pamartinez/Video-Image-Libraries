package com.samsung.android.gallery.app.ui.list.albums.pictures.setting;

import M9.o;
import Ob.a;
import P4.c;
import P4.d;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.SwitchPreferenceCompat;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.IAlbumSettingView;
import com.samsung.android.gallery.module.album.AutoAlbumSettingData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AutoAlbumSettingFragment<V extends IAlbumSettingView> extends AlbumSettingFragment<V> {
    private static final boolean SUPPORT_PET = Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM);

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadPreference$3(AutoAlbumSettingData autoAlbumSettingData, SwitchPreferenceCompat switchPreferenceCompat) {
        switchPreferenceCompat.setChecked(autoAlbumSettingData.isAutoUpdatingEnabled());
        switchPreferenceCompat.setOnPreferenceChangeListener(new d(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadPreference$4(AutoAlbumSettingData autoAlbumSettingData, Preference preference) {
        int i2;
        boolean z = SUPPORT_PET;
        if (z) {
            preference.setTitle((int) R.string.people_and_pets_to_add);
        }
        preference.setOnPreferenceClickListener(new d(this, 1));
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
    public /* synthetic */ void lambda$loadPreference$5(AutoAlbumSettingData autoAlbumSettingData, DropDownPreference dropDownPreference) {
        if (SUPPORT_PET) {
            dropDownPreference.setEntries((int) R.array.suggested_contents_rule_including_pets);
        }
        dropDownPreference.setValueIndex(autoAlbumSettingData.getSuggestedContentsRuleType());
        dropDownPreference.seslSetSummaryColor(getResources().getColor(R.color.settings_value_text_color, (Resources.Theme) null));
        dropDownPreference.setOnPreferenceChangeListener(new d(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCreatureCount$0(AutoAlbumSettingData autoAlbumSettingData, Preference preference) {
        int creatureCount = autoAlbumSettingData.getCreatureCount();
        if (creatureCount >= 0) {
            postAnalyticsLog(AnalyticsEventId.EVENT_ALBUM_SETTINGS_SELECT_PEOPLE_TO_INCLUDE.toString(), String.valueOf(creatureCount));
            preference.setSummary((CharSequence) getResources().getQuantityString(R.plurals.n_people_selected, creatureCount, new Object[]{Integer.valueOf(creatureCount)}));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCreatureCount$1(AutoAlbumSettingData autoAlbumSettingData) {
        Optional.ofNullable(findPreference(MiscSettingPreference.AlbumSelectedPeople.key)).ifPresent(new c(this, autoAlbumSettingData, 3));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCreatureCount$2() {
        AutoAlbumSettingData autoAlbumSettingData = getAutoAlbumSettingData();
        autoAlbumSettingData.loadSubscribeCreatureList();
        ThreadUtil.postOnUiThread(new a(1, this, autoAlbumSettingData));
    }

    /* access modifiers changed from: private */
    public boolean onAutoUpdateChanged(Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        getAutoAlbumSettingData().onAutoUpdateChanged(bool.booleanValue());
        postAnalyticsLog(AnalyticsEventId.EVENT_ALBUM_SETTINGS_AUTO_ADD_PICTURES_AND_VIDEOS.toString(), bool.booleanValue());
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onSelectedCreatureClicked(Preference preference) {
        if (!setInputBlock(this.TAG + "onSelectedCreatureClicked", 500)) {
            return false;
        }
        getPresenter().onSelectedCreatureClicked(this.mAlbumItem.getAlbumID());
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onSuggestedContentsRuleChanged(Preference preference, Object obj) {
        String str;
        String analyticsEventId = AnalyticsEventId.EVENT_ALBUM_SETTINGS_ADD_PICTURES_AND_VIDEOS_WITH.toString();
        String str2 = (String) obj;
        if (Integer.parseInt(str2) == 0) {
            str = AnalyticsDetail.EVENT_DETAIL_ADD_PICTURES_AND_VIDEOS_WITH_ANY_OF.toString();
        } else {
            str = AnalyticsDetail.EVENT_DETAIL_ADD_PICTURES_AND_VIDEOS_WITH_ALL_OF.toString();
        }
        postAnalyticsLog(analyticsEventId, str);
        return getPresenter().onSuggestedContentsRuleChanged((DropDownPreference) preference, this.mAlbumItem.getAlbumID(), Integer.parseInt(str2));
    }

    private void updateCreatureCount() {
        SimpleThreadPool.getInstance().execute(new o(19, this));
    }

    public AutoAlbumSettingData getAutoAlbumSettingData() {
        return getPresenter().getAutoAlbumSettingData(this.mAlbumItem.getAlbumID());
    }

    public int getPreferenceResource() {
        return R.xml.auto_album_setting;
    }

    public void loadPreference() {
        super.loadPreference();
        if (findPreference(MiscSettingPreference.CategoryAutoAlbum.key) != null) {
            AutoAlbumSettingData autoAlbumSettingData = getAutoAlbumSettingData();
            autoAlbumSettingData.load();
            Optional.ofNullable((SwitchPreferenceCompat) findPreference(MiscSettingPreference.AlbumAutoUpdate.key)).ifPresent(new c(this, autoAlbumSettingData, 0));
            Optional.ofNullable(findPreference(MiscSettingPreference.AlbumSelectedPeople.key)).ifPresent(new c(this, autoAlbumSettingData, 1));
            MiscSettingPreference miscSettingPreference = MiscSettingPreference.AlbumSuggestRule;
            if (miscSettingPreference.support(getContext())) {
                Optional.ofNullable((DropDownPreference) findPreference(miscSettingPreference.key)).ifPresent(new c(this, autoAlbumSettingData, 2));
            } else {
                removePreference(miscSettingPreference.key);
            }
        }
    }

    public boolean onHandleBroadcastEvent(Object obj, Bundle bundle) {
        if (((EventMessage) obj).what != 101) {
            return super.onHandleBroadcastEvent(obj, bundle);
        }
        updateCreatureCount();
        return true;
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 104) {
            Object obj = eventMessage.obj;
            if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                if (objArr.length > 1 && (objArr[0] instanceof Integer) && (objArr[1] instanceof MediaItem)) {
                    onDataChanged(obj, (Bundle) null);
                }
                return super.onHandleEvent(eventMessage);
            }
        }
        if (i2 == 101) {
            getBlackboard().postBroadcastEvent(EventMessage.obtain(104));
        }
        return super.onHandleEvent(eventMessage);
    }

    public boolean supportRename() {
        return true;
    }

    public AutoAlbumSettingPresenter<V> createPresenter() {
        return new AutoAlbumSettingPresenter<>(this);
    }

    public AutoAlbumSettingPresenter getPresenter() {
        return (AutoAlbumSettingPresenter) super.getPresenter();
    }
}
