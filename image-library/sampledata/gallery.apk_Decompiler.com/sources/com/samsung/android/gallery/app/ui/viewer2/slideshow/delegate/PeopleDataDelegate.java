package com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate;

import U5.b;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.ISlideshowView;
import com.samsung.android.gallery.module.creature.people.PeopleData;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PeopleDataDelegate extends AbsVuDelegate<ISlideshowView> {
    private final ConcurrentHashMap<Long, RectF> mFaceRectMap = new ConcurrentHashMap<>();

    public PeopleDataDelegate(ISlideshowView iSlideshowView) {
        super(iSlideshowView);
    }

    private RectF getDisplayRectF(MediaItem mediaItem) {
        boolean z;
        int i2;
        int i7;
        float f;
        int i8;
        int i10;
        View view = ((ISlideshowView) this.mView).getView();
        if (mediaItem == null || mediaItem.getWidth() == 0 || mediaItem.getHeight() == 0) {
            return new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        }
        boolean z3 = true;
        if (mediaItem.getOrientation() == 90 || mediaItem.getOrientation() == 270) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i2 = mediaItem.getHeight();
        } else {
            i2 = mediaItem.getWidth();
        }
        if (z) {
            i7 = mediaItem.getWidth();
        } else {
            i7 = mediaItem.getHeight();
        }
        float f5 = (float) i7;
        float f8 = (float) i2;
        if (f5 / f8 <= ((float) view.getHeight()) / ((float) view.getWidth())) {
            z3 = false;
        }
        if (z3) {
            f = ((float) view.getHeight()) / f5;
        } else {
            f = ((float) view.getWidth()) / f8;
        }
        if (z3) {
            i8 = (int) (f8 * f);
        } else {
            i8 = view.getWidth();
        }
        if (z3) {
            i10 = view.getHeight();
        } else {
            i10 = (int) (f5 * f);
        }
        int i11 = i8 / 2;
        int i12 = i10 / 2;
        return new RectF((float) ((view.getWidth() / 2) - i11), (float) ((view.getHeight() / 2) - i12), (float) ((view.getWidth() / 2) + i11), (float) ((view.getHeight() / 2) + i12));
    }

    private MediaItem getItem(int i2) {
        return ((ContainerModel) this.mModel).getMediaData().read(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPageSelected$0(MediaItem mediaItem, ArrayList arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            PeopleData peopleData = (PeopleData) arrayList.get(0);
            peopleData.setFaceRectF(getDisplayRectF(mediaItem), mediaItem.getOrientation());
            this.mFaceRectMap.put(Long.valueOf(mediaItem.getFileId()), peopleData.getFaceRectF());
        }
    }

    public RectF getFaceRectF(int i2) {
        MediaItem item = getItem(i2);
        if (item != null) {
            return this.mFaceRectMap.get(Long.valueOf(item.getFileId()));
        }
        return null;
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        int i7 = i2 + 1;
        if (i7 == ((ContainerModel) this.mModel).getMediaData().getCount()) {
            i7 = 0;
        }
        MediaItem item = getItem(i7);
        if (item != null && !this.mFaceRectMap.containsKey(Long.valueOf(item.getFileId()))) {
            PeopleDataManager.load(item.getFileId(), new b(9, this, item));
        }
    }
}
