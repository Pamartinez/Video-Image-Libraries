package com.samsung.android.gallery.app.ui.viewer2.slideshow;

import A.a;
import com.samsung.android.gallery.app.ui.viewer2.container.ContentViewCompositeFactory;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.IViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerBuilder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.ImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.PreviewLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.SlideShowMediaPlayerHandler;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideshowViewCompositeFactory extends ContentViewCompositeFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NextImageLoader extends ViewerObject {
        public void onViewAttached() {
            int i2;
            long j2;
            try {
                MediaData mediaData = this.mModel.getContainerModel().getMediaData();
                int position = this.mModel.getPosition();
                int count = mediaData.getCount();
                if (position == count - 1) {
                    i2 = 0;
                } else {
                    i2 = position + 1;
                }
                MediaItem read = mediaData.read(i2);
                StringCompat stringCompat = this.TAG;
                Integer valueOf = Integer.valueOf(position);
                String str = i2 + "/" + count;
                if (read != null) {
                    j2 = read.getFileId();
                } else {
                    j2 = -1;
                }
                Log.d(stringCompat, "onSlideIn#next", valueOf, str, Long.valueOf(j2));
                if (read != null && read.isImage()) {
                    String viewerBitmapKey = MediaItemUtil.getViewerBitmapKey(read, i2);
                    Blackboard blackboard = getBlackboard();
                    if (blackboard.isEmpty(viewerBitmapKey)) {
                        blackboard.publishIfEmpty(CommandKey.DATA_REQUEST(viewerBitmapKey), read);
                    }
                }
            } catch (Exception e) {
                a.r(e, new StringBuilder("onSlideIn#next failed. e="), this.TAG);
            }
        }
    }

    public SlideshowViewCompositeFactory(ContainerModel containerModel) {
        super(containerModel);
    }

    public ViewerBuilder createBasicObject(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        return ViewerBuilder.create(absViewerHolder, contentModel).addObject((IViewerObject) new PreviewLoader());
    }

    public ViewerObjectComposite createImage(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        return createBasicObject(absViewerHolder, contentModel.setViewerName("Image")).addObject((IViewerObject) new ImageLoader()).addObject((IViewerObject) new NextImageLoader()).build();
    }

    public ViewerObjectComposite createVideo(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        return createBasicObject(absViewerHolder, contentModel.setViewerName("Video")).addObject((IViewerObject) new SlideShowMediaPlayerHandler()).addObject((IViewerObject) new NextImageLoader()).build();
    }
}
