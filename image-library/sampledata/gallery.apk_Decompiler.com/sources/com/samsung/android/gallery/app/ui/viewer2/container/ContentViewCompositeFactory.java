package com.samsung.android.gallery.app.ui.viewer2.container;

import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditHandler;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiProcessingViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AwesomeIntelligenceHandler;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.ColorCorrectHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.IViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerBuilder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ActionModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ContentDescriptionHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ContentsTypeIconUi;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DecorLayoutHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DexZoomButtonUi;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DlnaUi2;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DragAndDropHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DualShotOptionsViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ExitHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.GroupPanelTransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.HdrDecorBgHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.HistoryRecorder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.MediaItemCorrector;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.MotionPhotoPlayViewerStateHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.MyTagHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.OverlayViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.PostProcessingDefender;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.PppBmpCacheHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ReduceTransparencyDecorBgHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.RemasterTransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ShrinkToCameraHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.TableModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.TransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.VideoMirroringUi;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.XmpTypeLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.CaptureHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.DummyObjectCaptureHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureImageHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureMotionPhotoHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureProcessingHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureVideoHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionImageHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionVideoHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.directorsview.DirectorsViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.dynamicview.DynamicViewController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.dynamicview.DynamicViewPlayerHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverAudioController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverDecorLayoutHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverVideoShotModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverViewerHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.GroupCountUi;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.GroupShotItemLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.HighlightGroupItemLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.SubItemLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.SuperSlowGroupItemLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.GifImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.ImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.MotionPhotoImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.PreviewLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.RemasterGifImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.UnsupportedIconLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.VideoHqPreviewLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.liveeffectvideo.LiveEffectVideoPlayerHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionAudioController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionPhotoMediaPlayer;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode.ShotModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.AudioController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.AudioEraserController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.FlipCoverVideoController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.HighlightFrcHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.HighlightMediaViewPlayerHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoPlayViHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoSaveClipHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoTipHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.LogVideoTipHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaPlayerAppsTransition;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaPlayerAppsTransition2;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaPlayerDoubleTapSeeker;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaViewPlayerHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaViewPrivateAlbumPlayerHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.PageScroll3dEffect;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.ShareVideoDownloadHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoSeekController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoSpeedControlHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLayoutHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsListHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLoadHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsSlideHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsStateHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsTouchListener;
import com.samsung.android.gallery.app.ui.viewer2.details.EditDetailsHandler;
import com.samsung.android.gallery.app.ui.viewer2.grouppanel.GroupItemPanelContentViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.app.ui.viewer2.model.ModelStateHelper;
import com.samsung.android.gallery.app.ui.viewer2.remaster.DivideHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterDecorViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterLayoutHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.focusview.RemasterFocusViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterAnalyticsLoggingHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterFinishingViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterProcessHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterProcessingViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.unlock.UnlockHandler;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.media.InstantSlowMoUtils;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerFactory;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import j4.a;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentViewCompositeFactory {
    private final HashMap<Integer, ViewerObjectCompositeConstructor> CONSTRUCTORS = new HashMap<Integer, ViewerObjectCompositeConstructor>() {
        public static final /* synthetic */ int d = 0;

        {
            put(1, new a(ContentViewCompositeFactory.this, 0));
            put(4, new a(ContentViewCompositeFactory.this, 15));
            put(2, new a(ContentViewCompositeFactory.this, 16));
            put(5, new a(ContentViewCompositeFactory.this, 1));
            put(3, new a(ContentViewCompositeFactory.this, 2));
            put(6, new a(ContentViewCompositeFactory.this, 3));
            put(18, new a(ContentViewCompositeFactory.this, 4));
            put(40, new a(ContentViewCompositeFactory.this, 5));
            put(20, new a(ContentViewCompositeFactory.this, 6));
            if (Features.isEnabled(Features.SUPPORT_DYNAMIC_VIEW)) {
                put(22, new a(ContentViewCompositeFactory.this, 7));
            }
            if (PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL) {
                put(23, new a(ContentViewCompositeFactory.this, 8));
            }
            put(7, new a(ContentViewCompositeFactory.this, 9));
            put(100, new a(ContentViewCompositeFactory.this, 10));
            put(0, new a(ContentViewCompositeFactory.this, 11));
            if (Features.isEnabled(Features.SUPPORT_FLIP_COVER_GALLERY)) {
                put(60, new a(ContentViewCompositeFactory.this, 12));
                put(61, new a(ContentViewCompositeFactory.this, 13));
            }
            if (Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_VIEWER)) {
                put(24, new a(ContentViewCompositeFactory.this, 14));
            }
        }
    };
    protected final ContainerModel mContainerModel;
    protected final String mLocationKey;
    protected final ModelStateHelper mStateHelper;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ViewerObjectCompositeConstructor {
        ViewerObjectComposite construct(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel);
    }

    public ContentViewCompositeFactory(ContainerModel containerModel) {
        this.mContainerModel = containerModel;
        this.mStateHelper = containerModel.getStateHelper();
        this.mLocationKey = containerModel.getLocationKey();
    }

    private List<IViewerObject> createDetails() {
        return Arrays.asList(new IViewerObject[]{new DetailsHandler(), new DetailsTouchListener(), new DetailsListHandler(), new DetailsLoadHandler(), new DetailsStateHandler(), new DetailsLayoutHandler(), new DetailsSlideHandler(), new EditDetailsHandler()});
    }

    private ViewerObjectComposite createEmpty(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        return ViewerBuilder.create(absViewerHolder, contentModel).build();
    }

    /* access modifiers changed from: private */
    public ViewerObjectComposite createRemaster(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        boolean z;
        DivideHandler divideHandler;
        RemasterFinishingViewHandler remasterFinishingViewHandler;
        RemasterGifImageLoader remasterGifImageLoader;
        RemasterProcessingViewHandler remasterProcessingViewHandler;
        RemasterProcessHandler remasterProcessHandler;
        RemasterAnalyticsLoggingHandler remasterAnalyticsLoggingHandler;
        boolean isRemasterPictures = LocationKey.isRemasterPictures(this.mLocationKey);
        MediaItem currentMediaItem = this.mContainerModel.getCurrentMediaItem();
        boolean isRemasterAutosave = MediaItemUtil.isRemasterAutosave(currentMediaItem);
        if (!Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER_GIF) || currentMediaItem == null || !currentMediaItem.isGif()) {
            z = false;
        } else {
            z = true;
        }
        ViewerBuilder addObject = ViewerBuilder.create(absViewerHolder, contentModel.setViewerName("Remaster")).addObject((IViewerObject) new PreviewLoader()).addObject((IViewerObject) new RemasterDecorViewHandler()).addObject((IViewerObject) new RemasterImageLoader());
        RemasterFocusViewHandler remasterFocusViewHandler = null;
        if (!isRemasterAutosave) {
            divideHandler = new DivideHandler();
        } else {
            divideHandler = null;
        }
        ViewerBuilder addObject2 = addObject.addObject((IViewerObject) divideHandler).addObject((IViewerObject) new RemasterLayoutHandler());
        if (!isRemasterAutosave) {
            remasterFinishingViewHandler = new RemasterFinishingViewHandler();
        } else {
            remasterFinishingViewHandler = null;
        }
        ViewerBuilder addObject3 = addObject2.addObject((IViewerObject) remasterFinishingViewHandler).addObject((IViewerObject) new RemasterTransitionHandler());
        if (z) {
            remasterGifImageLoader = new RemasterGifImageLoader();
        } else {
            remasterGifImageLoader = null;
        }
        ViewerBuilder addObject4 = addObject3.addObject((IViewerObject) remasterGifImageLoader);
        if (!isRemasterPictures) {
            remasterProcessingViewHandler = new RemasterProcessingViewHandler(z);
        } else {
            remasterProcessingViewHandler = null;
        }
        ViewerBuilder addObject5 = addObject4.addObject((IViewerObject) remasterProcessingViewHandler);
        if (!isRemasterPictures) {
            remasterProcessHandler = new RemasterProcessHandler();
        } else {
            remasterProcessHandler = null;
        }
        ViewerBuilder addObject6 = addObject5.addObject((IViewerObject) remasterProcessHandler);
        if (!isRemasterPictures) {
            remasterAnalyticsLoggingHandler = new RemasterAnalyticsLoggingHandler();
        } else {
            remasterAnalyticsLoggingHandler = null;
        }
        ViewerBuilder addObject7 = addObject6.addObject((IViewerObject) remasterAnalyticsLoggingHandler);
        if (Features.isEnabled(Features.SUPPORT_REMASTER_FOCUS_ROI) && !z && !isRemasterAutosave) {
            remasterFocusViewHandler = new RemasterFocusViewHandler();
        }
        return addObject7.addObject((IViewerObject) remasterFocusViewHandler).build();
    }

    /* access modifiers changed from: private */
    public ViewerObjectComposite createUnsupported(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        List<IViewerObject> list;
        DexZoomButtonUi dexZoomButtonUi;
        ViewerBuilder create = ViewerBuilder.create(absViewerHolder, contentModel.setViewerName("Unsupported"));
        TableModeHandler tableModeHandler = null;
        if (supportDetails()) {
            list = createDetails();
        } else {
            list = null;
        }
        ViewerBuilder addObject = create.addObject(list).addObject((IViewerObject) new UnsupportedIconLoader());
        if (DeviceInfo.isDexMode(contentModel.getContext())) {
            dexZoomButtonUi = new DexZoomButtonUi();
        } else {
            dexZoomButtonUi = null;
        }
        ViewerBuilder addObject2 = addObject.addObject((IViewerObject) dexZoomButtonUi);
        if (Features.isEnabled(Features.SUPPORT_TABLE_MODE)) {
            tableModeHandler = new TableModeHandler();
        }
        return addObject2.addObject((IViewerObject) tableModeHandler).build();
    }

    private IViewerObject getDecorBgHandler(ContentModel contentModel) {
        if (Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY)) {
            return new ReduceTransparencyDecorBgHandler();
        }
        if (supportHdrDecorBgHandler(contentModel)) {
            return new HdrDecorBgHandler();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ IViewerObject lambda$createLiveEffectVideo$0() {
        return new CaptureHandler(1);
    }

    private boolean supportActionMode() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.PasteClipboardViewer) || !supportObjectCapture()) {
            return false;
        }
        return true;
    }

    private boolean supportAiEdit() {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_ABOVE_DETAILS || LocationKey.isMtp(this.mLocationKey)) {
            return false;
        }
        return true;
    }

    private boolean supportDetails() {
        if (!this.mStateHelper.supportDetails() || !LocationKey.supportDetails(this.mLocationKey)) {
            return false;
        }
        return true;
    }

    private boolean supportHdrDecorBgHandler(ContentModel contentModel) {
        MediaItem currentMediaItem = contentModel.getContainerModel().getCurrentMediaItem();
        if (currentMediaItem == null || !currentMediaItem.isImage()) {
            return true;
        }
        return SuperHdrConfig.isEnabled();
    }

    private boolean supportMotionPhotoViewMode() {
        if (!PreferenceFeatures.OneUi7x.SUPPORT_MOTION_PHOTO_VIEW_MODE || LocationKey.isTrash(this.mLocationKey) || LocationKey.isLongExposure(this.mLocationKey)) {
            return false;
        }
        return true;
    }

    private boolean supportMyTagHandler() {
        if (Features.isEnabled(Features.IS_UPSM) || Features.isEnabled(Features.IS_GED) || PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_ABOVE_DETAILS) {
            return false;
        }
        return true;
    }

    private boolean supportObjectCapture() {
        if (LocationKey.isSuggestionViewList(this.mLocationKey) || LocationKey.isPrivateAlbum(this.mLocationKey) || !DeepSkyHelper.isObjectCaptureSupported() || !this.mStateHelper.isDecorViewEnabled()) {
            return false;
        }
        return true;
    }

    private boolean supportTextExtraction() {
        if (LocationKey.isSuggestionViewList(this.mLocationKey) || !DeepSkyHelper.isTextExtractionSupported() || !this.mStateHelper.isDecorViewEnabled()) {
            return false;
        }
        return true;
    }

    private boolean withGroupPanel() {
        return LocationKey.isSecondDepthGroupPanelView(this.mLocationKey);
    }

    public ViewerObjectComposite create(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel, int i2) {
        ViewerObjectCompositeConstructor viewerObjectCompositeConstructor = this.CONSTRUCTORS.get(Integer.valueOf(i2));
        if (viewerObjectCompositeConstructor != null) {
            return viewerObjectCompositeConstructor.construct(absViewerHolder, contentModel);
        }
        return createEmpty(absViewerHolder, contentModel);
    }

    public ViewerObjectComposite createAiEditGroupPanel(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        IViewerObject iViewerObject;
        String str = this.mLocationKey;
        ViewerBuilder addObject = ViewerBuilder.create(absViewerHolder, contentModel.setViewerName("AiEditGroupPanel")).addObject((IViewerObject) new PreviewLoader());
        if (LocationKey.isSuperSlowGroupPanelView(str)) {
            iViewerObject = new SuperSlowGroupItemLoader();
        } else if (LocationKey.isHighlightGroupPanelView(str)) {
            iViewerObject = new HighlightGroupItemLoader();
        } else {
            iViewerObject = null;
        }
        return addObject.addObject(iViewerObject).addObject((IViewerObject) new TableModeHandler()).build();
    }

    public ViewerBuilder createBasicObject(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        ShrinkToCameraHandler shrinkToCameraHandler;
        List<IViewerObject> list;
        MyTagHandler myTagHandler;
        AiProcessingViewHandler aiProcessingViewHandler;
        AiEditHandler aiEditHandler;
        DexZoomButtonUi dexZoomButtonUi;
        TableModeHandler tableModeHandler;
        DlnaUi2 dlnaUi2;
        GroupItemPanelContentViewHandler groupItemPanelContentViewHandler;
        GroupPanelTransitionHandler groupPanelTransitionHandler;
        ViewerBuilder create = ViewerBuilder.create(absViewerHolder, contentModel);
        AwesomeIntelligenceHandler awesomeIntelligenceHandler = null;
        if (PreferenceFeatures.OneUi5x.SUPPORT_SHRINK_TO_CAMERA) {
            shrinkToCameraHandler = new ShrinkToCameraHandler();
        } else {
            shrinkToCameraHandler = null;
        }
        ViewerBuilder addObject = create.addObject((IViewerObject) shrinkToCameraHandler).addObject((IViewerObject) new ContentDescriptionHandler()).addObject((IViewerObject) new ExitHandler());
        if (supportDetails()) {
            list = createDetails();
        } else {
            list = null;
        }
        ViewerBuilder addObject2 = addObject.addObject(list).addObject((IViewerObject) new TransitionHandler()).addObject((IViewerObject) new PreviewLoader()).addObject((IViewerObject) new DecorLayoutHandler());
        if (supportMyTagHandler()) {
            myTagHandler = new MyTagHandler();
        } else {
            myTagHandler = null;
        }
        ViewerBuilder addObject3 = addObject2.addObject((IViewerObject) myTagHandler);
        if (!supportAiEdit() || (!Features.isEnabled(Features.SUPPORT_AI_PROCESSING_EFFECT) && !Features.isEnabled(Features.SUPPORT_AWESOME_INTELLIGENT_FEATURES))) {
            aiProcessingViewHandler = null;
        } else {
            aiProcessingViewHandler = new AiProcessingViewHandler();
        }
        ViewerBuilder addObject4 = addObject3.addObject((IViewerObject) aiProcessingViewHandler);
        if (supportAiEdit()) {
            aiEditHandler = new AiEditHandler();
        } else {
            aiEditHandler = null;
        }
        ViewerBuilder addObject5 = addObject4.addObject((IViewerObject) aiEditHandler);
        if (DeviceInfo.isDexMode(contentModel.getContext())) {
            dexZoomButtonUi = new DexZoomButtonUi();
        } else {
            dexZoomButtonUi = null;
        }
        ViewerBuilder addObject6 = addObject5.addObject((IViewerObject) dexZoomButtonUi);
        if (Features.isEnabled(Features.SUPPORT_TABLE_MODE)) {
            tableModeHandler = new TableModeHandler();
        } else {
            tableModeHandler = null;
        }
        ViewerBuilder addObject7 = addObject6.addObject((IViewerObject) tableModeHandler);
        if (LocationKey.isCastInFullQualitySupportedView(this.mLocationKey)) {
            dlnaUi2 = new DlnaUi2();
        } else {
            dlnaUi2 = null;
        }
        ViewerBuilder addObject8 = addObject7.addObject((IViewerObject) dlnaUi2).addObject((IViewerObject) new HistoryRecorder()).addObject((IViewerObject) new DragAndDropHandler());
        if (withGroupPanel()) {
            groupItemPanelContentViewHandler = new GroupItemPanelContentViewHandler();
        } else {
            groupItemPanelContentViewHandler = null;
        }
        ViewerBuilder addObject9 = addObject8.addObject((IViewerObject) groupItemPanelContentViewHandler);
        if (withGroupPanel()) {
            groupPanelTransitionHandler = new GroupPanelTransitionHandler();
        } else {
            groupPanelTransitionHandler = null;
        }
        ViewerBuilder addObject10 = addObject9.addObject((IViewerObject) groupPanelTransitionHandler).addObject(getDecorBgHandler(contentModel)).addObject((IViewerObject) new OverlayViewHandler()).addObject((IViewerObject) new GroupCountUi());
        if (Features.isEnabled(Features.SUPPORT_AWESOME_INTELLIGENT_FEATURES)) {
            awesomeIntelligenceHandler = new AwesomeIntelligenceHandler();
        }
        return addObject10.addObject((IViewerObject) awesomeIntelligenceHandler);
    }

    public ViewerObjectComposite createBurstShot(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        TextExtractionImageHandler textExtractionImageHandler;
        IViewerObject iViewerObject;
        ObjectCaptureProcessingHandler objectCaptureProcessingHandler;
        ViewerBuilder addObject = createBasicObject(absViewerHolder, contentModel.setViewerName("BurstShot")).addObject((IViewerObject) new MediaItemCorrector()).addObject((IViewerObject) new ImageLoader()).addObject((IViewerObject) new CaptureHandler(0)).addObject((IViewerObject) new SubItemLoader(DbKey.FILES_BURSTSHOT, GroupType.BURST)).addObject((IViewerObject) new ContentsTypeIconUi());
        ActionModeHandler actionModeHandler = null;
        if (supportTextExtraction()) {
            textExtractionImageHandler = new TextExtractionImageHandler();
        } else {
            textExtractionImageHandler = null;
        }
        ViewerBuilder addObject2 = addObject.addObject((IViewerObject) textExtractionImageHandler);
        if (supportObjectCapture()) {
            iViewerObject = new ObjectCaptureImageHandler();
        } else {
            iViewerObject = new DummyObjectCaptureHandler();
        }
        ViewerBuilder addObject3 = addObject2.addObject(iViewerObject);
        if (supportObjectCapture()) {
            objectCaptureProcessingHandler = new ObjectCaptureProcessingHandler();
        } else {
            objectCaptureProcessingHandler = null;
        }
        ViewerBuilder addObject4 = addObject3.addObject((IViewerObject) objectCaptureProcessingHandler);
        if (supportActionMode()) {
            actionModeHandler = new ActionModeHandler();
        }
        return addObject4.addObject((IViewerObject) actionModeHandler).build();
    }

    public ViewerBuilder createCoverBasicObject(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        ShrinkToCameraHandler shrinkToCameraHandler;
        ViewerBuilder create = ViewerBuilder.create(absViewerHolder, contentModel);
        if (PreferenceFeatures.OneUi5x.SUPPORT_SHRINK_TO_CAMERA) {
            shrinkToCameraHandler = new ShrinkToCameraHandler();
        } else {
            shrinkToCameraHandler = null;
        }
        return create.addObject((IViewerObject) shrinkToCameraHandler).addObject((IViewerObject) new ContentDescriptionHandler()).addObject((IViewerObject) new ExitHandler()).addObject((IViewerObject) new TransitionHandler()).addObject((IViewerObject) new PreviewLoader()).addObject((IViewerObject) new HistoryRecorder()).addObject((IViewerObject) new FlipCoverViewerHandler()).addObject((IViewerObject) new FlipCoverDecorLayoutHandler());
    }

    public ViewerObjectComposite createCoverImage(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        return createCoverBasicObject(absViewerHolder, contentModel.setViewerName("CoverImage")).addObject((IViewerObject) new MediaItemCorrector()).addObject((IViewerObject) new PostProcessingDefender()).addObject((IViewerObject) new ImageLoader()).addObject((IViewerObject) new PppBmpCacheHandler()).build();
    }

    public ViewerObjectComposite createCoverVideo(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        FlipCoverVideoController flipCoverVideoController;
        boolean isEnabled = Features.isEnabled(Features.IS_SEP_LITE);
        ViewerBuilder addObject = createCoverBasicObject(absViewerHolder, contentModel.setViewerName("CoverVideo")).addObject((IViewerObject) new VideoHqPreviewLoader()).addObject((IViewerObject) new MediaViewPlayerHandler());
        if (!isEnabled) {
            flipCoverVideoController = new FlipCoverVideoController();
        } else {
            flipCoverVideoController = null;
        }
        return addObject.addObject((IViewerObject) flipCoverVideoController).addObject((IViewerObject) new FlipCoverAudioController()).addObject((IViewerObject) new FlipCoverVideoShotModeHandler()).addObject((IViewerObject) null).build();
    }

    public ViewerObjectComposite createDualShot(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        TextExtractionImageHandler textExtractionImageHandler;
        IViewerObject iViewerObject;
        ViewerBuilder addObject = createBasicObject(absViewerHolder, contentModel.setViewerName("DualShot")).addObject((IViewerObject) new MediaItemCorrector()).addObject((IViewerObject) new ImageLoader()).addObject((IViewerObject) new CaptureHandler(0)).addObject((IViewerObject) new DualShotOptionsViewHandler()).addObject((IViewerObject) new ShotModeHandler());
        ActionModeHandler actionModeHandler = null;
        if (supportTextExtraction()) {
            textExtractionImageHandler = new TextExtractionImageHandler();
        } else {
            textExtractionImageHandler = null;
        }
        ViewerBuilder addObject2 = addObject.addObject((IViewerObject) textExtractionImageHandler);
        if (supportObjectCapture()) {
            iViewerObject = new ObjectCaptureImageHandler();
        } else {
            iViewerObject = new DummyObjectCaptureHandler();
        }
        ViewerBuilder addObject3 = addObject2.addObject(iViewerObject);
        if (supportActionMode()) {
            actionModeHandler = new ActionModeHandler();
        }
        return addObject3.addObject((IViewerObject) actionModeHandler).build();
    }

    public ViewerObjectComposite createDynamicView(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        TableModeHandler tableModeHandler;
        ViewerBuilder addObject = ViewerBuilder.create(absViewerHolder, contentModel.setViewerName("DynamicView")).addObject((IViewerObject) new PreviewLoader()).addObject((IViewerObject) new VideoHqPreviewLoader()).addObject((IViewerObject) new DecorLayoutHandler()).addObject((IViewerObject) new DynamicViewPlayerHandler()).addObject((IViewerObject) new DynamicViewController()).addObject((IViewerObject) new ShotModeHandler()).addObject((IViewerObject) new TransitionHandler());
        if (Features.isEnabled(Features.SUPPORT_TABLE_MODE)) {
            tableModeHandler = new TableModeHandler();
        } else {
            tableModeHandler = null;
        }
        return addObject.addObject((IViewerObject) tableModeHandler).build();
    }

    public ViewerObjectComposite createImage(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        TextExtractionImageHandler textExtractionImageHandler;
        IViewerObject iViewerObject;
        ObjectCaptureProcessingHandler objectCaptureProcessingHandler;
        ViewerBuilder addObject = createBasicObject(absViewerHolder, contentModel.setViewerName("Image")).addObject((IViewerObject) new MediaItemCorrector()).addObject((IViewerObject) new PostProcessingDefender()).addObject((IViewerObject) new ImageLoader()).addObject((IViewerObject) new CaptureHandler(0)).addObject((IViewerObject) new XmpTypeLoader()).addObject((IViewerObject) new ShotModeHandler()).addObject((IViewerObject) new ContentsTypeIconUi());
        ActionModeHandler actionModeHandler = null;
        if (supportTextExtraction()) {
            textExtractionImageHandler = new TextExtractionImageHandler();
        } else {
            textExtractionImageHandler = null;
        }
        ViewerBuilder addObject2 = addObject.addObject((IViewerObject) textExtractionImageHandler);
        if (supportObjectCapture()) {
            iViewerObject = new ObjectCaptureImageHandler();
        } else {
            iViewerObject = new DummyObjectCaptureHandler();
        }
        ViewerBuilder addObject3 = addObject2.addObject(iViewerObject);
        if (supportObjectCapture()) {
            objectCaptureProcessingHandler = new ObjectCaptureProcessingHandler();
        } else {
            objectCaptureProcessingHandler = null;
        }
        ViewerBuilder addObject4 = addObject3.addObject((IViewerObject) objectCaptureProcessingHandler);
        if (supportActionMode()) {
            actionModeHandler = new ActionModeHandler();
        }
        return addObject4.addObject((IViewerObject) actionModeHandler).addObject((IViewerObject) new PppBmpCacheHandler()).build();
    }

    public ViewerObjectComposite createImageAni(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        TextExtractionImageHandler textExtractionImageHandler;
        IViewerObject iViewerObject;
        ViewerBuilder addObject = createBasicObject(absViewerHolder, contentModel.setViewerName("Animation")).addObject((IViewerObject) new MediaItemCorrector()).addObject((IViewerObject) new ImageLoader()).addObject((IViewerObject) new GifImageLoader()).addObject((IViewerObject) new ShotModeHandler()).addObject((IViewerObject) new ContentsTypeIconUi());
        ObjectCaptureProcessingHandler objectCaptureProcessingHandler = null;
        if (supportTextExtraction()) {
            textExtractionImageHandler = new TextExtractionImageHandler();
        } else {
            textExtractionImageHandler = null;
        }
        ViewerBuilder addObject2 = addObject.addObject((IViewerObject) textExtractionImageHandler);
        if (supportObjectCapture()) {
            iViewerObject = new ObjectCaptureImageHandler();
        } else {
            iViewerObject = new DummyObjectCaptureHandler();
        }
        ViewerBuilder addObject3 = addObject2.addObject(iViewerObject);
        if (supportObjectCapture()) {
            objectCaptureProcessingHandler = new ObjectCaptureProcessingHandler();
        }
        return addObject3.addObject((IViewerObject) objectCaptureProcessingHandler).build();
    }

    public ViewerObjectComposite createImagePostProcessing(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        TableModeHandler tableModeHandler;
        ViewerBuilder addObject = ViewerBuilder.create(absViewerHolder, contentModel.setViewerName("PostProcessing")).addObject((IViewerObject) new PostProcessingDefender()).addObject((IViewerObject) new TransitionHandler()).addObject((IViewerObject) new PreviewLoader()).addObject((IViewerObject) new DecorLayoutHandler());
        HistoryRecorder historyRecorder = null;
        if (Features.isEnabled(Features.SUPPORT_TABLE_MODE)) {
            tableModeHandler = new TableModeHandler();
        } else {
            tableModeHandler = null;
        }
        ViewerBuilder addObject2 = addObject.addObject((IViewerObject) tableModeHandler).addObject((IViewerObject) new ImageLoader()).addObject((IViewerObject) new PppBmpCacheHandler());
        if (GppmHelper.SUPPORT_DONATION) {
            historyRecorder = new HistoryRecorder().forGppm();
        }
        return addObject2.addObject((IViewerObject) historyRecorder).build();
    }

    public ViewerObjectComposite createLiveEffectVideo(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        IViewerObject iViewerObject;
        IViewerObject iViewerObject2;
        MediaPlayerDoubleTapSeeker mediaPlayerDoubleTapSeeker;
        TextExtractionVideoHandler textExtractionVideoHandler;
        VideoMirroringUi videoMirroringUi;
        IViewerObject iViewerObject3;
        ObjectCaptureProcessingHandler objectCaptureProcessingHandler;
        InstantSlowMoPlayViHandler instantSlowMoPlayViHandler;
        InstantSlowMoTipHandler instantSlowMoTipHandler;
        InstantSlowMoSaveClipHandler instantSlowMoSaveClipHandler;
        String str = this.mLocationKey;
        boolean isEnabled = Features.isEnabled(Features.IS_SEP_LITE);
        boolean z10 = false;
        if (isEnabled || !PocFeatures.isEnabled(PocFeatures.DoubleTapSeek)) {
            z = false;
        } else {
            z = true;
        }
        boolean isOpenInGalleryVideoPlayer = PreferenceFeatures.VideoPlayerFeature.isOpenInGalleryVideoPlayer();
        if (!isOpenInGalleryVideoPlayer || !PreferenceFeatures.isEnabled(PreferenceFeatures.VideoCapture) || LocationKey.isAiEditGroupPanelViewer(str)) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (isEnabled || !PreferenceFeatures.OneUi6x.SUPPORT_MOTION_PHOTO_INSTANT_SLOW_MO) {
            z7 = false;
        } else {
            z7 = true;
        }
        if (!z7 || !InstantSlowMoUtils.supportTips(str)) {
            z9 = false;
        } else {
            z9 = true;
        }
        if (z7 && PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO_SAVE_CLIP) {
            z10 = true;
        }
        ViewerBuilder addObject = createBasicObject(absViewerHolder, contentModel.setViewerName("LiveEffectVideo")).addObject((IViewerObject) new VideoHqPreviewLoader());
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            iViewerObject = new MediaPlayerAppsTransition2();
        } else {
            iViewerObject = new MediaPlayerAppsTransition();
        }
        ViewerBuilder addObjectIf = addObject.addObject(iViewerObject).addObjectIf(isOpenInGalleryVideoPlayer, new a(7));
        if (isOpenInGalleryVideoPlayer) {
            iViewerObject2 = new LiveEffectVideoPlayerHandler();
        } else {
            iViewerObject2 = new MediaViewPlayerHandler();
        }
        ViewerBuilder addObjectIf2 = addObjectIf.addObject(iViewerObject2).addObjectIf(!isOpenInGalleryVideoPlayer, new a(8));
        ShareVideoDownloadHandler shareVideoDownloadHandler = null;
        if (z) {
            mediaPlayerDoubleTapSeeker = new MediaPlayerDoubleTapSeeker();
        } else {
            mediaPlayerDoubleTapSeeker = null;
        }
        ViewerBuilder addObjectIf3 = addObjectIf2.addObject((IViewerObject) mediaPlayerDoubleTapSeeker).addObjectIf(z3, new a(9));
        if (supportTextExtraction()) {
            textExtractionVideoHandler = new TextExtractionVideoHandler();
        } else {
            textExtractionVideoHandler = null;
        }
        ViewerBuilder addObject2 = addObjectIf3.addObject((IViewerObject) textExtractionVideoHandler).addObject((IViewerObject) new AudioController());
        if (PreferenceFeatures.Remote.PLAY_VIDEO_IN_PRESENTATION) {
            videoMirroringUi = new VideoMirroringUi();
        } else {
            videoMirroringUi = null;
        }
        ViewerBuilder addObject3 = addObject2.addObject((IViewerObject) videoMirroringUi);
        if (supportObjectCapture()) {
            iViewerObject3 = new ObjectCaptureVideoHandler();
        } else {
            iViewerObject3 = new DummyObjectCaptureHandler();
        }
        ViewerBuilder addObject4 = addObject3.addObject(iViewerObject3);
        if (supportObjectCapture()) {
            objectCaptureProcessingHandler = new ObjectCaptureProcessingHandler();
        } else {
            objectCaptureProcessingHandler = null;
        }
        ViewerBuilder addObject5 = addObject4.addObject((IViewerObject) objectCaptureProcessingHandler);
        if (z7) {
            instantSlowMoPlayViHandler = new InstantSlowMoPlayViHandler();
        } else {
            instantSlowMoPlayViHandler = null;
        }
        ViewerBuilder addObject6 = addObject5.addObject((IViewerObject) instantSlowMoPlayViHandler);
        if (z9) {
            instantSlowMoTipHandler = new InstantSlowMoTipHandler();
        } else {
            instantSlowMoTipHandler = null;
        }
        ViewerBuilder addObject7 = addObject6.addObject((IViewerObject) instantSlowMoTipHandler);
        if (z10) {
            instantSlowMoSaveClipHandler = new InstantSlowMoSaveClipHandler();
        } else {
            instantSlowMoSaveClipHandler = null;
        }
        ViewerBuilder addObject8 = addObject7.addObject((IViewerObject) instantSlowMoSaveClipHandler);
        if (Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY) && LocationKey.isSharings(str)) {
            shareVideoDownloadHandler = new ShareVideoDownloadHandler();
        }
        return addObject8.addObject((IViewerObject) shareVideoDownloadHandler).build();
    }

    public ViewerObjectComposite createMotionPhoto(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        boolean z;
        boolean z3;
        boolean z7;
        IViewerObject iViewerObject;
        ObjectCaptureProcessingHandler objectCaptureProcessingHandler;
        InstantSlowMoPlayViHandler instantSlowMoPlayViHandler;
        InstantSlowMoTipHandler instantSlowMoTipHandler;
        InstantSlowMoSaveClipHandler instantSlowMoSaveClipHandler;
        String str = this.mLocationKey;
        boolean z9 = false;
        if (LocationKey.isSuggestionViewList(str) || LocationKey.isSharings(str)) {
            z = false;
        } else {
            z = true;
        }
        boolean z10 = PreferenceFeatures.OneUi6x.SUPPORT_MOTION_PHOTO_INSTANT_SLOW_MO;
        if (!z10 || !InstantSlowMoUtils.supportTips(str)) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (!z10 || !PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO_SAVE_CLIP) {
            z7 = false;
        } else {
            z7 = true;
        }
        ViewerBuilder addObjectIf = createBasicObject(absViewerHolder, contentModel.setViewerName(MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO)).addObject((IViewerObject) new MediaItemCorrector()).addObject((IViewerObject) new MotionPhotoImageLoader()).addObjectIf(supportTextExtraction(), new a(10));
        if (supportObjectCapture()) {
            iViewerObject = new ObjectCaptureMotionPhotoHandler();
        } else {
            iViewerObject = new DummyObjectCaptureHandler();
        }
        ViewerBuilder addObject = addObjectIf.addObject(iViewerObject);
        AudioEraserController audioEraserController = null;
        if (supportObjectCapture()) {
            objectCaptureProcessingHandler = new ObjectCaptureProcessingHandler();
        } else {
            objectCaptureProcessingHandler = null;
        }
        ViewerBuilder addObject2 = addObject.addObject((IViewerObject) objectCaptureProcessingHandler).addObject((IViewerObject) new MotionPhotoMediaPlayer()).addObject((IViewerObject) new MotionPhotoPlayViewerStateHandler());
        if (z10) {
            instantSlowMoPlayViHandler = new InstantSlowMoPlayViHandler();
        } else {
            instantSlowMoPlayViHandler = null;
        }
        ViewerBuilder addObject3 = addObject2.addObject((IViewerObject) instantSlowMoPlayViHandler);
        if (z3) {
            instantSlowMoTipHandler = new InstantSlowMoTipHandler();
        } else {
            instantSlowMoTipHandler = null;
        }
        ViewerBuilder addObject4 = addObject3.addObject((IViewerObject) instantSlowMoTipHandler);
        if (z7) {
            instantSlowMoSaveClipHandler = new InstantSlowMoSaveClipHandler();
        } else {
            instantSlowMoSaveClipHandler = null;
        }
        ViewerBuilder addObjectIf2 = addObject4.addObject((IViewerObject) instantSlowMoSaveClipHandler).addObjectIf(supportActionMode(), new a(11));
        boolean z11 = PreferenceFeatures.OneUi40.MOTION_PHOTO_PLAYER;
        ViewerBuilder addObjectIf3 = addObjectIf2.addObjectIf(z11, new a(12));
        if (Features.isEnabled(Features.IS_SEP_LITE) || !z11) {
            z9 = true;
        }
        ViewerBuilder addObject5 = addObjectIf3.addObjectIf(z9, new a(8)).addObject((IViewerObject) new MotionAudioController());
        if (Features.isEnabled(Features.SUPPORT_AUDIO_ERASER_IN_GALLERY)) {
            audioEraserController = new AudioEraserController();
        }
        return addObject5.addObject((IViewerObject) audioEraserController).addObjectIf(z, new a(13)).addObjectIf(true, new a(14)).addObjectIf(supportMotionPhotoViewMode(), new a(15)).addObject((IViewerObject) new VideoMirroringUi()).build();
    }

    public ViewerObjectComposite createSimilarShot(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        TextExtractionImageHandler textExtractionImageHandler;
        IViewerObject iViewerObject;
        ObjectCaptureProcessingHandler objectCaptureProcessingHandler;
        ViewerBuilder addObject = createBasicObject(absViewerHolder, contentModel.setViewerName("SimilarShot")).addObject((IViewerObject) new MediaItemCorrector()).addObject((IViewerObject) new ImageLoader()).addObject((IViewerObject) new CaptureHandler(0)).addObject((IViewerObject) new SubItemLoader(DbKey.FILES_SIMILAR, GroupType.SIMILAR)).addObject((IViewerObject) new ShotModeHandler()).addObject((IViewerObject) new ContentsTypeIconUi());
        ActionModeHandler actionModeHandler = null;
        if (supportTextExtraction()) {
            textExtractionImageHandler = new TextExtractionImageHandler();
        } else {
            textExtractionImageHandler = null;
        }
        ViewerBuilder addObject2 = addObject.addObject((IViewerObject) textExtractionImageHandler);
        if (supportObjectCapture()) {
            iViewerObject = new ObjectCaptureImageHandler();
        } else {
            iViewerObject = new DummyObjectCaptureHandler();
        }
        ViewerBuilder addObject3 = addObject2.addObject(iViewerObject);
        if (supportObjectCapture()) {
            objectCaptureProcessingHandler = new ObjectCaptureProcessingHandler();
        } else {
            objectCaptureProcessingHandler = null;
        }
        ViewerBuilder addObject4 = addObject3.addObject((IViewerObject) objectCaptureProcessingHandler);
        if (supportActionMode()) {
            actionModeHandler = new ActionModeHandler();
        }
        return addObject4.addObject((IViewerObject) actionModeHandler).build();
    }

    public ViewerObjectComposite createSingleTaken(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        return ViewerBuilder.create(absViewerHolder, contentModel.setViewerName("SingleTaken")).addObject((IViewerObject) new PreviewLoader()).addObject((IViewerObject) new GroupShotItemLoader(DbKey.FILES_SINGLETAKE, GroupType.SINGLE_TAKEN)).addObject((IViewerObject) new TableModeHandler()).build();
    }

    public ViewerObjectComposite createUnlockScreen(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        return ViewerBuilder.create(absViewerHolder, contentModel.setViewerName("UnLock")).addObject((IViewerObject) new UnlockHandler()).build();
    }

    public ViewerObjectComposite createVideo(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        boolean z11;
        IViewerObject iViewerObject;
        IViewerObject iViewerObject2;
        IViewerObject iViewerObject3;
        InstantSlowMoPlayViHandler instantSlowMoPlayViHandler;
        InstantSlowMoTipHandler instantSlowMoTipHandler;
        InstantSlowMoSaveClipHandler instantSlowMoSaveClipHandler;
        AudioController audioController;
        AudioEraserController audioEraserController;
        PageScroll3dEffect pageScroll3dEffect;
        MediaPlayerDoubleTapSeeker mediaPlayerDoubleTapSeeker;
        CaptureHandler captureHandler;
        DirectorsViewHandler directorsViewHandler;
        TextExtractionVideoHandler textExtractionVideoHandler;
        VideoMirroringUi videoMirroringUi;
        IViewerObject iViewerObject4;
        ObjectCaptureProcessingHandler objectCaptureProcessingHandler;
        ShareVideoDownloadHandler shareVideoDownloadHandler;
        HighlightFrcHandler highlightFrcHandler;
        VideoSpeedControlHandler videoSpeedControlHandler;
        LogVideoTipHandler logVideoTipHandler;
        String str = this.mLocationKey;
        boolean isEnabled = Features.isEnabled(Features.IS_SEP_LITE);
        int videoPlayerMode = PreferenceFeatures.VideoPlayerFeature.getVideoPlayerMode();
        boolean z12 = false;
        if (isEnabled || !PocFeatures.isEnabled(PocFeatures.DoubleTapSeek)) {
            z = false;
        } else {
            z = true;
        }
        boolean isSuperSlowGroupPanelView = LocationKey.isSuperSlowGroupPanelView(str);
        if (!PreferenceFeatures.VideoPlayerFeature.isOpenInGalleryVideoPlayer() || !PreferenceFeatures.isEnabled(PreferenceFeatures.VideoCapture) || LocationKey.isAiEditGroupPanelViewer(str)) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (isEnabled || !PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO || LocationKey.isColorCorrectView(str)) {
            z7 = false;
        } else {
            z7 = true;
        }
        if (!z7 || !InstantSlowMoUtils.supportTips(str)) {
            z9 = false;
        } else {
            z9 = true;
        }
        if (!z7 || !PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO_SAVE_CLIP) {
            z10 = false;
        } else {
            z10 = true;
        }
        if (!Features.isEnabled(Features.SUPPORT_LOG_VIDEO_TIPS) || PreferenceFeatures.isEnabled(PreferenceFeatures.RetailMode) || !SdkConfig.lessThan(SdkConfig.SEM.B_MR5)) {
            z11 = false;
        } else {
            z11 = true;
        }
        if (!isSuperSlowGroupPanelView && Features.isEnabled(Features.SUPPORT_AUDIO_ERASER_IN_GALLERY) && !LocationKey.isAllDayTimeLapse(str) && !LocationKey.isHighlightGroupPanelView(str)) {
            z12 = true;
        }
        ViewerBuilder addObject = createBasicObject(absViewerHolder, contentModel.setViewerName("Video")).addObject((IViewerObject) new VideoHqPreviewLoader());
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            iViewerObject = new MediaPlayerAppsTransition2();
        } else {
            iViewerObject = new MediaPlayerAppsTransition();
        }
        ViewerBuilder addObject2 = addObject.addObject(iViewerObject);
        if (LocationKey.isHighlightGroupPanelView(str)) {
            iViewerObject2 = new HighlightMediaViewPlayerHandler();
        } else if (LocationKey.isPrivateAlbum(str)) {
            iViewerObject2 = new MediaViewPrivateAlbumPlayerHandler();
        } else {
            iViewerObject2 = new MediaViewPlayerHandler();
        }
        ViewerBuilder addObject3 = addObject2.addObject(iViewerObject2);
        ColorCorrectHandler colorCorrectHandler = null;
        if (videoPlayerMode == 0 || this.mStateHelper.isForcePlayVideoInGallery()) {
            iViewerObject3 = new VideoController();
        } else if (videoPlayerMode == 2) {
            iViewerObject3 = new VideoSeekController();
        } else {
            iViewerObject3 = null;
        }
        ViewerBuilder addObject4 = addObject3.addObject(iViewerObject3);
        if (z7) {
            instantSlowMoPlayViHandler = new InstantSlowMoPlayViHandler();
        } else {
            instantSlowMoPlayViHandler = null;
        }
        ViewerBuilder addObject5 = addObject4.addObject((IViewerObject) instantSlowMoPlayViHandler);
        if (z9) {
            instantSlowMoTipHandler = new InstantSlowMoTipHandler();
        } else {
            instantSlowMoTipHandler = null;
        }
        ViewerBuilder addObject6 = addObject5.addObject((IViewerObject) instantSlowMoTipHandler);
        if (z10) {
            instantSlowMoSaveClipHandler = new InstantSlowMoSaveClipHandler();
        } else {
            instantSlowMoSaveClipHandler = null;
        }
        ViewerBuilder addObject7 = addObject6.addObject((IViewerObject) instantSlowMoSaveClipHandler);
        if (!isSuperSlowGroupPanelView) {
            audioController = new AudioController();
        } else {
            audioController = null;
        }
        ViewerBuilder addObject8 = addObject7.addObject((IViewerObject) audioController);
        if (z12) {
            audioEraserController = new AudioEraserController();
        } else {
            audioEraserController = null;
        }
        ViewerBuilder addObject9 = addObject8.addObject((IViewerObject) audioEraserController);
        if (MediaPlayerFactory.SUPPORT_AUDIO_FADE_OUT_3D_EFFECT) {
            pageScroll3dEffect = new PageScroll3dEffect();
        } else {
            pageScroll3dEffect = null;
        }
        ViewerBuilder addObject10 = addObject9.addObject((IViewerObject) pageScroll3dEffect);
        if (z) {
            mediaPlayerDoubleTapSeeker = new MediaPlayerDoubleTapSeeker();
        } else {
            mediaPlayerDoubleTapSeeker = null;
        }
        ViewerBuilder addObject11 = addObject10.addObject((IViewerObject) mediaPlayerDoubleTapSeeker).addObject((IViewerObject) new ShotModeHandler());
        if (z3) {
            captureHandler = new CaptureHandler(1);
        } else {
            captureHandler = null;
        }
        ViewerBuilder addObject12 = addObject11.addObject((IViewerObject) captureHandler);
        if (PreferenceFeatures.OneUi41.SUPPORT_DIRECTORS_VIEW) {
            directorsViewHandler = new DirectorsViewHandler();
        } else {
            directorsViewHandler = null;
        }
        ViewerBuilder addObject13 = addObject12.addObject((IViewerObject) directorsViewHandler);
        if (supportTextExtraction()) {
            textExtractionVideoHandler = new TextExtractionVideoHandler();
        } else {
            textExtractionVideoHandler = null;
        }
        ViewerBuilder addObject14 = addObject13.addObject((IViewerObject) textExtractionVideoHandler);
        if (PreferenceFeatures.Remote.PLAY_VIDEO_IN_PRESENTATION) {
            videoMirroringUi = new VideoMirroringUi();
        } else {
            videoMirroringUi = null;
        }
        ViewerBuilder addObject15 = addObject14.addObject((IViewerObject) videoMirroringUi);
        if (supportObjectCapture()) {
            iViewerObject4 = new ObjectCaptureVideoHandler();
        } else {
            iViewerObject4 = new DummyObjectCaptureHandler();
        }
        ViewerBuilder addObject16 = addObject15.addObject(iViewerObject4);
        if (supportObjectCapture()) {
            objectCaptureProcessingHandler = new ObjectCaptureProcessingHandler();
        } else {
            objectCaptureProcessingHandler = null;
        }
        ViewerBuilder addObject17 = addObject16.addObject((IViewerObject) objectCaptureProcessingHandler);
        if (!Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY) || !LocationKey.isSharings(str)) {
            shareVideoDownloadHandler = null;
        } else {
            shareVideoDownloadHandler = new ShareVideoDownloadHandler();
        }
        ViewerBuilder addObject18 = addObject17.addObject((IViewerObject) shareVideoDownloadHandler);
        if (!LocationKey.isHighlightGroupPanelView(str) || !PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO) {
            highlightFrcHandler = null;
        } else {
            highlightFrcHandler = new HighlightFrcHandler();
        }
        ViewerBuilder addObject19 = addObject18.addObject((IViewerObject) highlightFrcHandler);
        if (isEnabled || !PreferenceFeatures.OneUi6x.SUPPORT_VIDEO_SPEED_CONTROLLER) {
            videoSpeedControlHandler = null;
        } else {
            videoSpeedControlHandler = new VideoSpeedControlHandler();
        }
        ViewerBuilder addObject20 = addObject19.addObject((IViewerObject) videoSpeedControlHandler);
        if (z11) {
            logVideoTipHandler = new LogVideoTipHandler();
        } else {
            logVideoTipHandler = null;
        }
        ViewerBuilder addObject21 = addObject20.addObject((IViewerObject) logVideoTipHandler);
        if (z11) {
            colorCorrectHandler = new ColorCorrectHandler();
        }
        return addObject21.addObject((IViewerObject) colorCorrectHandler).addObject((IViewerObject) new ContentsTypeIconUi()).build();
    }
}
