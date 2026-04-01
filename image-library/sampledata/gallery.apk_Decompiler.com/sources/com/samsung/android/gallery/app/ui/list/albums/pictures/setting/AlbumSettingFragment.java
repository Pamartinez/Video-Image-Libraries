package com.samsung.android.gallery.app.ui.list.albums.pictures.setting;

import H7.A;
import O3.l;
import P4.a;
import P4.b;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.Preference;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.IAlbumSettingView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.settings.ui.BasePreferenceFragment;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumSettingFragment<V extends IAlbumSettingView> extends BasePreferenceFragment<V> implements IBaseFragment, EventContext, IAlbumSettingView {
    protected MediaItem mAlbumItem;

    private void initOptionCategory() {
        Optional.ofNullable(findPreference(MiscSettingPreference.AlbumChangeCover.key)).ifPresent(new a(this, 3));
        if (supportRename()) {
            Optional.ofNullable(findPreference(MiscSettingPreference.AlbumRename.key)).ifPresent(new a(this, 4));
        } else {
            removePreference(MiscSettingPreference.AlbumRename.key);
        }
    }

    private void initOptionScreenshotCustom() {
        if (PreferenceFeatures.OneUi8x.SCREEN_SHOT_FILTER_REORDER && this.mAlbumItem.isScreenShot() && MediaDataFactory.getInstance(getBlackboard()).open("location://albums/fileList/ScreenShotFilter").getCount() > 0) {
            Optional.ofNullable(findPreference(SettingPreference.ScreenshotCategoryReorder1.key)).ifPresent(new a(this, 2));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initOptionCategory$3(Preference preference) {
        preference.setOnPreferenceClickListener(new b(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initOptionCategory$4(Preference preference) {
        preference.setOnPreferenceClickListener(new b(this, 2));
        preference.setSummary((CharSequence) this.mAlbumItem.getDisplayName());
        preference.seslSetSummaryColor(preference.getContext().getColor(R.color.settings_value_text_color));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initOptionScreenshotCustom$5(Preference preference) {
        getBlackboard().post("command://MoveURL", "location://albums/AlbumSetting/ScreenShotFilterCustom");
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initOptionScreenshotCustom$6(Preference preference) {
        preference.setOnPreferenceClickListener(new b(this, 1));
        preference.setVisible(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(View view) {
        BlackboardUtils.publishBackKeyEvent(getBlackboard());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1(GalleryToolbar galleryToolbar) {
        galleryToolbar.setTitle(getTitleId());
        galleryToolbar.setNavigationIcon((int) R.drawable.tw_ic_ab_back_mtrl_with_inset);
        galleryToolbar.setNavigationContentDescription(R.string.navigate_up);
        galleryToolbar.setNavigationOnClickListener(new A(17, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$2(GalleryAppBarLayout galleryAppBarLayout) {
        galleryAppBarLayout.setTitle(getTitleId());
        galleryAppBarLayout.setSubtitle((CharSequence) null);
    }

    /* access modifiers changed from: private */
    public boolean onChangeCoverClicked(Preference preference) {
        if (!setInputBlock(this.TAG + "_onChangeCoverClicked", 500)) {
            return false;
        }
        getPresenter().onChangeCoverClicked(this.mAlbumItem);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onRenameClicked(Preference preference) {
        if (!setInputBlock(this.TAG + "_onRenameClicked", 500)) {
            return false;
        }
        getPresenter().onRenameClicked(this.mAlbumItem);
        return true;
    }

    public int getPreferenceResource() {
        return R.xml.album_setting;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SPLIT_VIEW_SETTING.toString();
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

    public void loadPreference() {
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new l(7));
        try {
            addPreferencesFromResource(getPreferenceResource());
            initOptionCategory();
            initOptionScreenshotCustom();
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("loadPreference failed. e="), this.TAG);
        }
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlbumItem = (MediaItem) getBlackboard().read("data://albums/current", null);
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

    public void onDataChanged(Object obj, Bundle bundle) {
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            MediaItem mediaItem = this.mAlbumItem;
            if (mediaItem != null && mediaItem.getAlbumID() == ((Integer) objArr[0]).intValue()) {
                MediaItem mediaItem2 = (MediaItem) objArr[1];
                this.mAlbumItem = mediaItem2;
                setPreferenceSummary(MiscSettingPreference.AlbumRename.key, mediaItem2.getDisplayName());
            }
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 2012) {
            return false;
        }
        getBlackboard().postBroadcastEvent(EventMessage.obtain(104));
        return false;
    }

    public void onStart() {
        super.onStart();
        setScreenMode();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Optional.ofNullable((GalleryToolbar) view.findViewById(R.id.toolbar)).ifPresent(new a(this, 0));
        Optional.ofNullable((GalleryAppBarLayout) view.findViewById(R.id.appbar)).ifPresent(new a(this, 1));
        updateMainLayoutBackgroundColor();
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportRename() {
        MediaItem mediaItem = this.mAlbumItem;
        if (mediaItem == null || TextUtils.isEmpty(mediaItem.getPath()) || BucketUtils.contains(this.mAlbumItem.getAlbumID()) || BucketUtils.isRoot(this.mAlbumItem.getAlbumID()) || this.mAlbumItem.isMergedAlbum()) {
            return false;
        }
        return true;
    }

    public AlbumSettingPresenter<V> createPresenter() {
        return new AlbumSettingPresenter<>(this);
    }

    public AlbumSettingPresenter getPresenter() {
        return (AlbumSettingPresenter) super.getPresenter();
    }

    public void setDefaultExitTransitioning(boolean z) {
    }
}
