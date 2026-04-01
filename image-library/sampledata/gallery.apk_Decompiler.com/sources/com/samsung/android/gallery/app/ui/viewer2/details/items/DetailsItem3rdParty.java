package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartLightRoomCmd;
import com.samsung.android.gallery.app.controller.externals.StartMeituCmd;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItem3rdParty extends DetailsItem implements DetailsViewUpdater {
    private SubscriberListener mSubscriberListener;

    public DetailsItem3rdParty(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onClicked$3(BaseCommand baseCommand) {
        EventContext eventContext = this.mEventContext;
        baseCommand.execute(eventContext, eventContext.getCurrentItem(), Boolean.FALSE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onResume$0(Object obj, Bundle bundle) {
        StartLightRoomCmd startLightRoomCmd = new StartLightRoomCmd();
        EventContext eventContext = this.mEventContext;
        startLightRoomCmd.execute(eventContext, eventContext.getCurrentItem(), Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewInflate$1(View view) {
        onClicked(new StartLightRoomCmd());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewInflate$2(View view) {
        onClicked(new StartMeituCmd());
    }

    private void onClicked(BaseCommand baseCommand) {
        if (setInputBlocked(1000)) {
            this.mBlackboard.postEvent(EventMessage.obtain(3057, Boolean.TRUE));
            ThreadUtil.postOnUiThreadDelayed(new C0447a(0, this, baseCommand), 500);
        }
    }

    public int getLayoutId() {
        return R.id.moreinfo_3rd_party;
    }

    public void onPause() {
        SubscriberListener subscriberListener = this.mSubscriberListener;
        if (subscriberListener != null) {
            this.mBlackboard.unsubscribe("command://event/AddedToLightRoomLibrary", subscriberListener);
            this.mSubscriberListener = null;
        }
    }

    public void onResume() {
        if (this.mSubscriberListener == null) {
            C0448b bVar = new C0448b(this);
            this.mSubscriberListener = bVar;
            this.mBlackboard.subscribe("command://event/AddedToLightRoomLibrary", bVar);
        }
    }

    public void onViewInflate(ViewStub viewStub, View view) {
        getView(view, (int) R.id.moreinfo_lightroom).setOnClickListener(new C0449c(this, 0));
        getView(view, (int) R.id.moreinfo_meitu).setOnClickListener(new C0449c(this, 1));
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.DEFAULT, this);
    }

    public boolean supportItem(MediaItem mediaItem) {
        if (supportLightroom(mediaItem) || supportMeitu(mediaItem)) {
            return true;
        }
        return false;
    }

    public boolean supportLightroom(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_LIGHTROOM) || !mediaItem.isDng() || mediaItem.isBroken() || mediaItem.isUriItem() || mediaItem.isSharing() || mediaItem.isDrm() || mediaItem.isPrivateItem()) {
            return false;
        }
        return true;
    }

    public boolean supportMeitu(MediaItem mediaItem) {
        Context context;
        if (mediaItem == null || !mediaItem.isImage() || mediaItem.isBroken() || mediaItem.isSharing() || mediaItem.isDrm() || mediaItem.isPrivateItem() || !Features.isEnabled(Features.SUPPORT_MEITU_IMAGE_TO_IMAGE) || !SettingPreference.Meitu.isEnabled() || (context = this.mEventContext.getContext()) == null || SamsungAccountManager.isChildAccount(context) || ((float) Math.max(mediaItem.getWidth(), mediaItem.getHeight())) / ((float) Math.min(mediaItem.getWidth(), mediaItem.getHeight())) > 3.5f) {
            return false;
        }
        return true;
    }

    public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        setVisibleView(R.id.moreinfo_lightroom, supportLightroom(mediaItem));
        setVisibleView(R.id.moreinfo_meitu, supportMeitu(mediaItem));
        if (PreferenceFeatures.OneUi7x.IS_ONE_UI_70 && supportMeitu(mediaItem)) {
            setTextAndVisibility((int) R.id.meitu_title, (CharSequence) AppResources.getString(R.string.open_in_meitu));
        }
    }
}
