package com.samsung.android.gallery.app.ui.viewer2.container;

import com.samsung.android.gallery.app.ui.abstraction.delegate.DelegateFactory;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.AiEditDetectorDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.AliveZoomDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.AudioPocDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.BixbyDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.BothSideImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.BottomSheetDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.CacheDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.ConfigurationDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DecorViewDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DeepSkyDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DlnaDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.FlipCoverSelectModeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.HdrContentsDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.JumpToTimelineDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.KeyguardDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.LocationKeyDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.MediaDataDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.MirroringDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.MultiWindowMarginDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.NaviUpDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.RelatedItemObserveDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.SelectModeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.SystemBarDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.UsbOtgStateDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.ViewPagerFilmScrollSyncDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.ViewerDragNDropDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.ViewerTableModeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.EditorDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripSeekerDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripSnapDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover.FlipCoverDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover.FlipCoverMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover.FlipCoverNaviUpDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel.GroupItemPanelDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel.GroupPanelSelectionMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.input.DexNavigationDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.input.ExitGestureDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.input.KeyboardMouseDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.input.ViewerTouchPadDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.FavoriteMenuAnimDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.MenuTipPopupDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ForceSwipeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ViewHolderStateDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ViewPagerDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ViewPositionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.share.ShareConversionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.share.ShareSheetDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.GroupPanelTransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.RemasterTransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.TransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.graphics.CodecCompat;
import com.samsung.android.gallery.module.utils.ViewerUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VuDelegateFactory implements DelegateFactory<AbsVuDelegate, IVuContainerView> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DelegateList<T> extends ArrayList<T> {
        public /* synthetic */ DelegateList(int i2) {
            this();
        }

        private DelegateList() {
        }

        public DelegateList<T> after(Class cls) {
            return this;
        }
    }

    private void addOptionalDelegates(IVuContainerView iVuContainerView, DelegateList<AbsVuDelegate> delegateList, Blackboard blackboard, String str) {
        Object obj;
        if (DeviceInfo.isDexMode(iVuContainerView.getContext())) {
            delegateList.add(new DexNavigationDelegate(iVuContainerView));
        }
        if (Features.isEnabled(Features.SUPPORT_TABLE_MODE)) {
            if (!LocationKey.isSecondDepthGroupPanelView(iVuContainerView.getLocationKey())) {
                delegateList.add(new ViewerTouchPadDelegate(iVuContainerView));
            }
            delegateList.add(new ViewerTableModeDelegate(iVuContainerView));
        }
        if (SelectModeDelegate.isSelectMode(str, blackboard)) {
            if (((ContainerModel) iVuContainerView.getModel()).isFlipCoverGallery()) {
                obj = new FlipCoverSelectModeDelegate(iVuContainerView);
            } else {
                obj = new SelectModeDelegate(iVuContainerView);
            }
            delegateList.add(obj);
        }
        delegateList.add(new BixbyDelegate(iVuContainerView));
        delegateList.add(new KeyboardMouseDelegate(iVuContainerView));
        delegateList.add(new ShareConversionDelegate(iVuContainerView));
        delegateList.add(new DeepSkyDelegate(iVuContainerView));
        if (AliveZoomDelegate.support(str)) {
            delegateList.add(new AliveZoomDelegate(iVuContainerView));
        }
        if (AiEditDetectorDelegate.SUPPORT) {
            delegateList.add(new AiEditDetectorDelegate(iVuContainerView));
        }
        if (((ContainerModel) iVuContainerView.getModel()).isFlipCoverGallery()) {
            delegateList.add(new FlipCoverDelegate(iVuContainerView));
            if (!SelectModeDelegate.isSelectMode(str, blackboard)) {
                delegateList.add(new FlipCoverNaviUpDelegate(iVuContainerView));
                delegateList.add(new FlipCoverMenuDelegate(iVuContainerView));
            }
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.PasteClipboardViewer)) {
            delegateList.add(new ViewerDragNDropDelegate(iVuContainerView));
        }
        if (SdkConfig.atLeast(SdkConfig.SEM.N_MR5)) {
            delegateList.add(new MenuTipPopupDelegate(iVuContainerView));
        }
        if (iVuContainerView.getLocationKey().startsWith("location://SingleTakenShotviewer")) {
            delegateList.add(new GroupItemPanelDelegate(iVuContainerView));
            delegateList.add(new GroupPanelSelectionMenuDelegate(iVuContainerView));
        }
        if (PocFeatures.isEnabled(PocFeatures.SetAudioUnMuteAlways) || PocFeatures.isEnabled(PocFeatures.SetAudioUnMuteUntilAppDestroy)) {
            delegateList.add(new AudioPocDelegate(iVuContainerView));
        }
        if (PocFeatures.SUPPORT_SEARCH_JUMP_TO_TIMELINE) {
            delegateList.add(new JumpToTimelineDelegate(iVuContainerView));
        }
        if (LocationKey.isFromRelated(iVuContainerView.getLocationKey())) {
            delegateList.add(new RelatedItemObserveDelegate(iVuContainerView));
        }
        if (isCameraOtg(str, blackboard)) {
            delegateList.add(new UsbOtgStateDelegate(iVuContainerView));
        }
    }

    private void addRemoteDelegates(IVuContainerView iVuContainerView, DelegateList<AbsVuDelegate> delegateList) {
        if (!Features.isEnabled(Features.IS_GED)) {
            if (PocFeatures.isEnabled(PocFeatures.PresentationEnabled)) {
                delegateList.add(new MirroringDelegate(iVuContainerView));
            }
            if (Features.isEnabled(Features.USE_SCREEN_SHARING)) {
                delegateList.add(new DlnaDelegate(iVuContainerView));
            }
        }
    }

    private boolean isCameraOtg(String str, Blackboard blackboard) {
        if (!Features.isEnabled(Features.SUPPORT_USB_STORAGE) || !LocationKey.isQuickView(str)) {
            return false;
        }
        return LaunchIntent.isFromUSB(blackboard);
    }

    public ArrayList<AbsVuDelegate> createDelegateList(IVuContainerView iVuContainerView) {
        String locationKey = iVuContainerView.getLocationKey();
        Blackboard blackboard = iVuContainerView.getBlackboard();
        DelegateList delegateList = new DelegateList(0);
        delegateList.add(new MediaDataDelegate(iVuContainerView));
        delegateList.add(new LocationKeyDelegate(iVuContainerView));
        delegateList.add(new ViewPagerDelegate(iVuContainerView));
        Class<ViewPagerDelegate> cls = ViewPagerDelegate.class;
        delegateList.after(cls).add(new ViewerMenuDelegate(iVuContainerView));
        delegateList.add(new ViewPositionDelegate(iVuContainerView));
        delegateList.add(new ViewHolderStateDelegate(iVuContainerView));
        if (SuperHdrConfig.SUPPORT && SuperHdrConfig.isEnabled()) {
            delegateList.add(new HdrContentsDelegate(iVuContainerView));
        }
        if (ViewerUtils.supportFilmStrip(locationKey)) {
            delegateList.after(cls).add(new ViewPagerFilmScrollSyncDelegate(iVuContainerView));
            delegateList.after(cls).add(new FilmStripDelegate(iVuContainerView));
            delegateList.after(cls).add(new FilmStripSeekerDelegate(iVuContainerView));
            delegateList.after(cls).add(new FilmStripSnapDelegate(iVuContainerView));
        }
        if (CodecCompat.PATCH_HEIF_FILE_TRANSCODING || SuperHdrConfig.isEnabled()) {
            delegateList.add(new BothSideImageLoader(iVuContainerView));
        }
        delegateList.add(new BottomSheetDelegate(iVuContainerView));
        delegateList.add(new DecorViewDelegate(iVuContainerView));
        delegateList.add(new SystemBarDelegate(iVuContainerView));
        delegateList.add(new ForceSwipeDelegate(iVuContainerView));
        delegateList.add(new EditorDelegate(iVuContainerView));
        delegateList.add(new ShareSheetDelegate(iVuContainerView));
        delegateList.add(new ExitGestureDelegate(iVuContainerView));
        if (LocationKey.isRevitalizationView(iVuContainerView.getLocationKey())) {
            delegateList.add(new RemasterTransitionDelegate(iVuContainerView));
        } else if (LocationKey.isSecondDepthGroupPanelView(iVuContainerView.getLocationKey())) {
            delegateList.add(new GroupPanelTransitionDelegate(iVuContainerView));
        } else {
            delegateList.add(new TransitionDelegate(iVuContainerView));
        }
        if (iVuContainerView.isFromLockScreen()) {
            delegateList.add(new KeyguardDelegate(iVuContainerView));
        }
        delegateList.add(new NaviUpDelegate(iVuContainerView));
        delegateList.add(new FavoriteMenuAnimDelegate(iVuContainerView));
        delegateList.add(new CacheDelegate(iVuContainerView));
        delegateList.add(new ConfigurationDelegate(iVuContainerView));
        delegateList.add(new MultiWindowMarginDelegate(iVuContainerView));
        addRemoteDelegates(iVuContainerView, delegateList);
        addOptionalDelegates(iVuContainerView, delegateList, blackboard, locationKey);
        return delegateList;
    }
}
