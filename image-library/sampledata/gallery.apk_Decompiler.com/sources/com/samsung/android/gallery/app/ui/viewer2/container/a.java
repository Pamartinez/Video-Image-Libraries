package com.samsung.android.gallery.app.ui.viewer2.container;

import com.samsung.android.gallery.app.ui.viewer2.container.ContentViewCompositeFactory;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ContentViewCompositeFactory.ViewerObjectCompositeConstructor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2574a;
    public final /* synthetic */ ContentViewCompositeFactory b;

    public /* synthetic */ a(ContentViewCompositeFactory contentViewCompositeFactory, int i2) {
        this.f2574a = i2;
        this.b = contentViewCompositeFactory;
    }

    public final ViewerObjectComposite construct(AbsViewerHolder absViewerHolder, ContentModel contentModel) {
        int i2 = this.f2574a;
        ContentViewCompositeFactory contentViewCompositeFactory = this.b;
        switch (i2) {
            case 0:
                return contentViewCompositeFactory.createImage(absViewerHolder, contentModel);
            case 1:
                return contentViewCompositeFactory.createSimilarShot(absViewerHolder, contentModel);
            case 2:
                return contentViewCompositeFactory.createImageAni(absViewerHolder, contentModel);
            case 3:
                return contentViewCompositeFactory.createImagePostProcessing(absViewerHolder, contentModel);
            case 4:
                return contentViewCompositeFactory.createDualShot(absViewerHolder, contentModel);
            case 5:
                return contentViewCompositeFactory.createSingleTaken(absViewerHolder, contentModel);
            case 6:
                return contentViewCompositeFactory.createVideo(absViewerHolder, contentModel);
            case 7:
                return contentViewCompositeFactory.createDynamicView(absViewerHolder, contentModel);
            case 8:
                return contentViewCompositeFactory.createAiEditGroupPanel(absViewerHolder, contentModel);
            case 9:
                int i7 = ContentViewCompositeFactory.AnonymousClass1.d;
                return contentViewCompositeFactory.createRemaster(absViewerHolder, contentModel);
            case 10:
                return contentViewCompositeFactory.createUnlockScreen(absViewerHolder, contentModel);
            case 11:
                int i8 = ContentViewCompositeFactory.AnonymousClass1.d;
                return contentViewCompositeFactory.createUnsupported(absViewerHolder, contentModel);
            case 12:
                return contentViewCompositeFactory.createCoverImage(absViewerHolder, contentModel);
            case 13:
                return contentViewCompositeFactory.createCoverVideo(absViewerHolder, contentModel);
            case 14:
                return contentViewCompositeFactory.createLiveEffectVideo(absViewerHolder, contentModel);
            case 15:
                return contentViewCompositeFactory.createMotionPhoto(absViewerHolder, contentModel);
            default:
                return contentViewCompositeFactory.createBurstShot(absViewerHolder, contentModel);
        }
    }
}
