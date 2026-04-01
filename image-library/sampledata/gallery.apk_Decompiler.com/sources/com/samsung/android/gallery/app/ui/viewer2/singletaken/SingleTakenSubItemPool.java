package com.samsung.android.gallery.app.ui.viewer2.singletaken;

import A9.c;
import Qa.a;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewer2.container.ContentViewCompositeFactory;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ContentViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.databinding.ContentViewerLayoutBinding;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleTakenSubItemPool implements AbsViewerHolder.StateListener {
    private final ContentViewCompositeFactory mCompositeFactory;
    private final HashMap<Long, ViewerObjectComposite> mItemMap = new HashMap<>();
    private final LayoutInflater mLayoutInflater;
    private final ContentModel mParentModel;
    private final ViewGroup mViewGroup;

    /* renamed from: com.samsung.android.gallery.app.ui.viewer2.singletaken.SingleTakenSubItemPool$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$database$dbtype$MediaType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.gallery.database.dbtype.MediaType[] r0 = com.samsung.android.gallery.database.dbtype.MediaType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$database$dbtype$MediaType = r0
                com.samsung.android.gallery.database.dbtype.MediaType r1 = com.samsung.android.gallery.database.dbtype.MediaType.Image     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$database$dbtype$MediaType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.database.dbtype.MediaType r1 = com.samsung.android.gallery.database.dbtype.MediaType.Video     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.singletaken.SingleTakenSubItemPool.AnonymousClass1.<clinit>():void");
        }
    }

    public SingleTakenSubItemPool(ContentModel contentModel, ViewGroup viewGroup, ContentViewCompositeFactory contentViewCompositeFactory) {
        this.mParentModel = contentModel;
        this.mViewGroup = viewGroup;
        this.mLayoutInflater = (LayoutInflater) contentModel.getContext().getSystemService("layout_inflater");
        this.mCompositeFactory = contentViewCompositeFactory;
    }

    private ViewerObjectComposite create(MediaItem mediaItem) {
        int viewerHolderType = getViewerHolderType(mediaItem);
        return this.mCompositeFactory.create(new ContentViewerHolder(ContentViewerLayoutBinding.inflate(this.mLayoutInflater, this.mViewGroup, false), this), new ContentModel(this.mParentModel.getContext(), this.mParentModel.getBlackboard(), this.mParentModel.getContainerModel()), viewerHolderType);
    }

    private long getSingleTakenSubItemPoolKey(MediaItem mediaItem) {
        int i2;
        boolean z = PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL;
        if (z && MediaItemUtil.isHighLightClip(mediaItem)) {
            String title = mediaItem.getTitle();
            long videoThumbStartTime = mediaItem.getVideoThumbStartTime() + mediaItem.getFileId();
            if (title != null) {
                i2 = title.hashCode();
            } else {
                i2 = 0;
            }
            return videoThumbStartTime + ((long) i2);
        } else if (!z || !MediaItemUtil.isSuperSlowClip(mediaItem)) {
            return mediaItem.getFileId();
        } else {
            return mediaItem.getFileId() + ((long) MediaItemUtil.getSuperSlowClipEffect(mediaItem));
        }
    }

    private int getViewerHolderType(MediaItem mediaItem) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$database$dbtype$MediaType[mediaItem.getMediaType().ordinal()];
        if (i2 == 1) {
            return 1;
        }
        if (i2 == 2) {
            return 20;
        }
        throw new IllegalArgumentException("mediaItem.getMediaType() + " + mediaItem.getMediaType() + "is not supported in singletaken");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ViewerObjectComposite lambda$get$0(MediaItem mediaItem, Long l) {
        return create(mediaItem);
    }

    public ViewerObjectComposite get(MediaItem mediaItem) {
        return this.mItemMap.computeIfAbsent(Long.valueOf(getSingleTakenSubItemPoolKey(mediaItem)), new a(2, (Object) this, (Object) mediaItem));
    }

    public int getPoolSize() {
        return this.mItemMap.size();
    }

    public void recycle() {
        this.mItemMap.forEach(new c(4));
        this.mItemMap.clear();
    }

    public void remove(MediaItem mediaItem) {
        this.mItemMap.remove(Long.valueOf(getSingleTakenSubItemPoolKey(mediaItem)));
    }

    public ViewerObjectComposite removeAndGetNewComposite(MediaItem mediaItem) {
        this.mItemMap.remove(Long.valueOf(getSingleTakenSubItemPoolKey(mediaItem)));
        return get(mediaItem);
    }

    public void onViewConfirm(AbsViewerHolder absViewerHolder) {
    }
}
