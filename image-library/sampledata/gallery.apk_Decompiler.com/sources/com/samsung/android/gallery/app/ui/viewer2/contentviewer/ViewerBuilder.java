package com.samsung.android.gallery.app.ui.viewer2.contentviewer;

import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerBuilder {
    private final ContentModel mContentModel;
    private final AbsViewerHolder<?> mViewerHolder;
    private final ArrayList<IViewerObject> mViewerObjectList = new ArrayList<>();

    private ViewerBuilder(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        this.mViewerHolder = absViewerHolder;
        this.mContentModel = contentModel;
    }

    public static ViewerBuilder create(AbsViewerHolder<?> absViewerHolder, ContentModel contentModel) {
        return new ViewerBuilder(absViewerHolder, contentModel);
    }

    public ViewerBuilder addObject(IViewerObject iViewerObject) {
        if (iViewerObject != null) {
            this.mViewerObjectList.add(iViewerObject);
        }
        return this;
    }

    public ViewerBuilder addObjectIf(boolean z, Supplier<IViewerObject> supplier) {
        if (z) {
            this.mViewerObjectList.add(supplier.get());
        }
        return this;
    }

    public ViewerObjectComposite build() {
        ViewerObjectComposite viewerObjectComposite = new ViewerObjectComposite(this.mViewerHolder, this.mContentModel);
        Iterator<IViewerObject> it = this.mViewerObjectList.iterator();
        while (it.hasNext()) {
            IViewerObject next = it.next();
            viewerObjectComposite.addViewerObject(next);
            if (next instanceof GroupLoader) {
                viewerObjectComposite.setGroupLoader((GroupLoader) next);
            }
        }
        viewerObjectComposite.initialize();
        viewerObjectComposite.onInitialized();
        return viewerObjectComposite;
    }

    public ViewerBuilder addObject(List<IViewerObject> list) {
        if (list != null) {
            for (IViewerObject addObject : list) {
                addObject(addObject);
            }
        }
        return this;
    }
}
