package com.samsung.android.gallery.app.ui.viewer2.container;

import C3.C0391a;
import E7.c;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.BixbyDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DecorViewDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.KeyguardDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel.GroupPanelSelectionMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegatePresenter;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.account.AwesomeIntelligenceServiceManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.foldable.FoldableScreen;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenLabel;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import i.C0212a;
import i4.C0468a;
import ic.l;
import j7.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VuContainerPresenter extends AbsVuDelegatePresenter<IVuContainerView> {
    public VuContainerPresenter(Blackboard blackboard, IVuContainerView iVuContainerView) {
        super(blackboard, iVuContainerView);
    }

    private void destroyContentViewHolder() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            currentViewer.onDestroy();
        }
    }

    private boolean handleEventViewHolder(EventMessage eventMessage) {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            return currentViewer.handleBlackboardEvent(eventMessage);
        }
        return false;
    }

    private boolean isFlipCover() {
        boolean z;
        FoldStateManager instance = FoldStateManager.getInstance(this.mBlackboard);
        if (!LaunchIntent.isActionView(this.mBlackboard) || !LaunchIntent.isFlipCoverViewerTheme(this.mBlackboard)) {
            z = false;
        } else {
            z = true;
        }
        boolean isFlipCoverWidgetTheme = LaunchIntent.isFlipCoverWidgetTheme(this.mBlackboard);
        boolean isFlipCoverWidgetContentsPickerTheme = LaunchIntent.isFlipCoverWidgetContentsPickerTheme(this.mBlackboard);
        if (instance == null || !FoldableScreen.FLIP_COVER.equals(instance.getScreen()) || (!z && !isFlipCoverWidgetTheme && !isFlipCoverWidgetContentsPickerTheme)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$0(ArrayList arrayList, KeyguardDelegate keyguardDelegate) {
        keyguardDelegate.createGlobalSubscriberList(this, arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$1(Object obj, Bundle bundle) {
        Object[] objArr = (Object[]) obj;
        ((IVuContainerView) this.mView).setInputBlock((String) objArr[0], ((Integer) objArr[1]).intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onToggleOsd$7(boolean z, View view) {
        int i2;
        Context context = getContext();
        if (z) {
            i2 = R.string.options_shown;
        } else {
            i2 = R.string.options_hidden;
        }
        view.announceForAccessibility(context.getString(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvoker$2(Object[] objArr) {
        ((ContainerModel) this.mModel).setLocationKey(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvoker$3(Object[] objArr) {
        ((IVuContainerView) this.mView).hideNavigationBar();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvoker$4(Object[] objArr) {
        setInputBlock(objArr[0], objArr[1].intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvoker$5(Object[] objArr) {
        ((IVuContainerView) this.mView).releaseInputBlocking();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvoker$6(Object[] objArr) {
        ((IVuContainerView) this.mView).updateBackgroundColor(objArr[0].booleanValue());
    }

    /* access modifiers changed from: private */
    public void onBackKeyEvent(Object... objArr) {
        this.mActionInvoker.invoke(ViewerAction.CONTAINER_BACK_KEY_PREPARE, new Object[0]);
        ((IVuContainerView) this.mView).finish();
    }

    /* access modifiers changed from: private */
    public void onToggleOsd(Object... objArr) {
        boolean z = !((ContainerModel) this.mModel).isOsdVisible();
        C0212a.x("toggle_osd = ", this.TAG, z);
        ((ContainerModel) this.mModel).setOsdVisible(z);
        ((IVuContainerView) this.mView).enableOsd(z);
        Optional.ofNullable((DecorViewDelegate) getDelegate(DecorViewDelegate.class)).ifPresent(new l(11));
        Optional.ofNullable(((IVuContainerView) this.mView).getView()).ifPresent(new c(this, z, 15));
    }

    private void pauseContentViewHolder() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            currentViewer.onPause();
        }
    }

    private void resumeContentViewHolder() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            currentViewer.onResume();
        }
    }

    private void setActionInvoker(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.BACK_KEY_PRESSED, new b(this, 0));
        actionInvoker.add(ViewerAction.TOGGLE_OSD, new b(this, 1));
        actionInvoker.add(ViewerAction.SET_GROUP_SHOT_LOCATION_KEY, new b(this, 2));
        actionInvoker.add(ViewerAction.HIDE_NAVIGATION_BAR, new b(this, 3));
        actionInvoker.add(ViewerAction.SET_INPUT_BLOCK, new b(this, 4));
        actionInvoker.add(ViewerAction.CLEAR_INPUT_BLOCK, new b(this, 5));
        actionInvoker.add(ViewerAction.UPDATE_BACKGROUND_COLOR, new b(this, 6));
    }

    private void startContentViewHolder() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            currentViewer.onStart();
        }
    }

    private void stopContentViewHolder() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            currentViewer.onStop();
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        Optional.ofNullable((KeyguardDelegate) getDelegate(KeyguardDelegate.class)).ifPresent(new e(24, this, arrayList));
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command:///SetInputBlock", new C0391a(28, this)).setWorkingCurrent());
    }

    public MediaItem[] getAllItems() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null) {
            return new MediaItem[0];
        }
        List<MediaItem> subItems = currentViewer.getModel().getSubItems();
        return (MediaItem[]) subItems.toArray(new MediaItem[subItems.size()]);
    }

    public MediaItem getBestItem() {
        MediaItem mediaItem;
        GroupPanelSelectionMenuDelegate groupPanelSelectionMenuDelegate = (GroupPanelSelectionMenuDelegate) getDelegate(GroupPanelSelectionMenuDelegate.class);
        if (groupPanelSelectionMenuDelegate != null) {
            return groupPanelSelectionMenuDelegate.getBestItem();
        }
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            mediaItem = currentViewer.getModel().getBestItem();
        } else {
            mediaItem = null;
        }
        if (mediaItem != null) {
            return mediaItem;
        }
        return super.getBestItem();
    }

    public MediaItem getCurrentItem() {
        return ((ContainerModel) this.mModel).getCurrentMediaItem();
    }

    public Object getEventContextData(String str) {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        str.getClass();
        int i2 = 1;
        int i7 = 0;
        char c5 = 65535;
        switch (str.hashCode()) {
            case -2027176141:
                if (str.equals("content_viewer_holder")) {
                    c5 = 0;
                    break;
                }
                break;
            case -932777941:
                if (str.equals("is_highlight_frc_mode")) {
                    c5 = 1;
                    break;
                }
                break;
            case -773613052:
                if (str.equals("app_transition_seek_position")) {
                    c5 = 2;
                    break;
                }
                break;
            case -449559404:
                if (str.equals("dynamic_view_type")) {
                    c5 = 3;
                    break;
                }
                break;
            case 14197156:
                if (str.equals("editSubInfo")) {
                    c5 = 4;
                    break;
                }
                break;
            case 419321822:
                if (str.equals("shareComponent")) {
                    c5 = 5;
                    break;
                }
                break;
            case 532155769:
                if (str.equals("is_instant_slow_mo_options_tip_recognize_enabled")) {
                    c5 = 6;
                    break;
                }
                break;
            case 1099250941:
                if (str.equals("dynamic_view_clip_index")) {
                    c5 = 7;
                    break;
                }
                break;
            case 1504642082:
                if (str.equals("is_input_blocked")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1601832653:
                if (str.equals("editMode")) {
                    c5 = 9;
                    break;
                }
                break;
            case 1719293769:
                if (str.equals("audio_eraser_on")) {
                    c5 = 10;
                    break;
                }
                break;
            case 1745077339:
                if (str.equals("view_recreated_key")) {
                    c5 = 11;
                    break;
                }
                break;
            case 1762245316:
                if (str.equals("live_effect_shared_view")) {
                    c5 = 12;
                    break;
                }
                break;
            case 1999831345:
                if (str.equals("app_transition_view")) {
                    c5 = 13;
                    break;
                }
                break;
        }
        Class cls = BixbyDelegate.class;
        switch (c5) {
            case 0:
                if (currentViewer != null) {
                    return currentViewer.getViewHolder();
                }
                return null;
            case 1:
                if (currentViewer != null) {
                    return Boolean.valueOf(currentViewer.getModel().isHighlightFrcMode());
                }
                return null;
            case 2:
                if (currentViewer != null) {
                    i7 = currentViewer.getModel().getVideoSeekPosition();
                }
                return Integer.valueOf(i7);
            case 3:
                return Optional.ofNullable(getCurrentItem()).map(new C0468a(12)).map(new C0468a(13)).orElse((Object) null);
            case 4:
                return Optional.ofNullable((BixbyDelegate) getDelegate(cls)).map(new C0468a(16)).orElse((Object) null);
            case 5:
                return Optional.ofNullable((BixbyDelegate) getDelegate(cls)).map(new C0468a(14)).orElse((Object) null);
            case 6:
                if (currentViewer != null) {
                    return Boolean.valueOf(currentViewer.getModel().isInstantSlowMoOptionsTipRecognizeEnabled());
                }
                return null;
            case 7:
                return Optional.ofNullable(getCurrentItem()).map(new C0468a(17)).map(new C0468a(18)).orElse((Object) null);
            case 8:
                return Boolean.valueOf(isInputBlocked());
            case 9:
                return Optional.ofNullable((BixbyDelegate) getDelegate(cls)).map(new C0468a(15)).orElse((Object) null);
            case 10:
                if (currentViewer == null || currentViewer.getModel().getContainerModel().isAudioEraserOff()) {
                    i2 = 0;
                }
                return Integer.valueOf(i2);
            case 11:
                return "lifecycle://on_view_created" + ((IVuContainerView) this.mView).hashCode();
            case 12:
                return ((ContainerModel) this.mModel).getFastOptionView();
            case 13:
                if (currentViewer != null) {
                    return currentViewer.getViewHolder().getTransitionView();
                }
                return null;
            default:
                return null;
        }
    }

    public MediaData getMediaData() {
        return ((ContainerModel) this.mModel).getMediaData();
    }

    public String getScreenLabel() {
        MediaItem currentItem = getCurrentItem();
        if (currentItem == null || !currentItem.isSingleTakenShot()) {
            return super.getScreenLabel();
        }
        return AnalyticsScreenLabel.SCREEN_LABEL_SINGLE_TAKE.toString();
    }

    public MediaItem[] getSelectedItems() {
        if (LocationKey.isSecondDepthGroupPanelView(((ContainerModel) this.mModel).getLocationKey()) && ((ContainerModel) this.mModel).isSelectMode()) {
            return ((ContainerModel) this.mModel).getSelectedItems();
        }
        MediaItem currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem();
        if (currentMediaItem != null) {
            return new MediaItem[]{currentMediaItem};
        }
        return super.getSelectedItems();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        boolean z;
        int i2 = eventMessage.what;
        if (i2 == 1100) {
            this.mActionInvoker.invoke(ViewerAction.CLOSE_MOTION_PHOTO, new Object[0]);
        } else if (i2 == 1110) {
            if (PreferenceFeatures.OneUi41.SUPPORT_DEEP_SKY_DONATION && ((Boolean) eventMessage.obj).booleanValue() && DeepSkyHelper.isDonationSupported()) {
                this.mActionInvoker.invoke(ViewerAction.DONATE_DEEP_SKY, new Object[0]);
            }
            if ((((IVuContainerView) this.mView).isInMultiWindowMode() || isDexMode()) && ((Boolean) eventMessage.obj).booleanValue()) {
                this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_VIEW_RESUME_FOR_MULTI_WINDOW, new Object[0]);
            }
            this.mActionInvoker.invoke(ViewerAction.WINDOW_FOCUS_CHANGED, eventMessage.obj);
        } else if (i2 == 3002) {
            ((IVuContainerView) this.mView).requestForceRotate();
        } else if (i2 == 3009) {
            this.mActionInvoker.invoke(ViewerAction.OBJECT_CAPTURE_MENU_CLICKED, Integer.valueOf(eventMessage.arg1), eventMessage.obj);
        } else if (i2 == 3014) {
            onNavigationPressed((View) null);
        } else if (i2 != 4002) {
            z = handleEventViewHolder(eventMessage);
            return super.handleEvent(eventMessage) | z;
        }
        z = true;
        return super.handleEvent(eventMessage) | z;
    }

    public boolean hasOptionsMenu() {
        return true;
    }

    public boolean isInputBlockedExceptBackKey() {
        return false;
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            currentViewer.onApplyWindowInsets();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            currentViewer.onConfigurationChanged(configuration);
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            currentViewer.onMultiWindowModeChanged(z);
        }
    }

    public void onTableModeChanged(boolean z, int i2) {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null) {
            currentViewer.onTableModeChanged(z, i2);
        }
    }

    public void onViewAttach() {
        super.onViewAttach();
        if (FoldUtils.SUPPORT_FLIP_COVER_GALLERY) {
            ((ContainerModel) this.mModel).setFlipCoverGallery(isFlipCover());
        }
    }

    public void onViewCreate() {
        super.onViewCreate();
        String str = this.TAG;
        ((IVuContainerView) this.mView).printLog(str, "onViewCreate :" + ((ContainerModel) this.mModel).getPosition());
        setActionInvoker(this.mActionInvoker);
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        destroyContentViewHolder();
        if (Features.isEnabled(Features.SUPPORT_AWESOME_INTELLIGENT_FEATURES) && !PreferenceFeatures.isEnabled(PreferenceFeatures.RetailMode)) {
            AwesomeIntelligenceServiceManager.getInstance().release();
        }
    }

    public void onViewPause() {
        String str = this.TAG;
        ((IVuContainerView) this.mView).printLog(str, "onViewPause :" + ((ContainerModel) this.mModel).getPosition());
        super.onViewPause();
        pauseContentViewHolder();
    }

    public void onViewResume() {
        String str = this.TAG;
        ((IVuContainerView) this.mView).printLog(str, "onViewResume :" + ((ContainerModel) this.mModel).getPosition());
        super.onViewResume();
        resumeContentViewHolder();
    }

    public void onViewStart() {
        super.onViewStart();
        startContentViewHolder();
    }

    public void onViewStop() {
        super.onViewStop();
        stopContentViewHolder();
    }

    public void initMenu() {
    }

    public void prepareOptionsMenu(Menu menu) {
    }

    public void createOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }
}
