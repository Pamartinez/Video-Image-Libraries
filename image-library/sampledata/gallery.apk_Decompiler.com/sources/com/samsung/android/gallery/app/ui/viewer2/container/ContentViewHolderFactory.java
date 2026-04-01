package com.samsung.android.gallery.app.ui.viewer2.container;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ContentViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.singletaken.SingleTakenViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.unlock.UnlockViewerHolder;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.widget.databinding.ContentViewerLayoutBinding;
import com.samsung.android.gallery.widget.databinding.RemasterViewerLayoutBinding;
import com.samsung.android.gallery.widget.databinding.SingleTakenViewerLayoutBinding;
import com.samsung.android.gallery.widget.databinding.UnlockFragmentLayoutBinding;
import h.C0199b;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentViewHolderFactory {
    private static final ConcurrentLinkedQueue<ContentViewerLayoutBinding> mViewBindingCache = new ConcurrentLinkedQueue<>();
    private final ContainerModel mContainerModel;
    private final LayoutInflater mLayoutInflater;
    private final String mLocationKey;
    private final AbsViewerHolder.StateListener mStateListener;

    /* renamed from: com.samsung.android.gallery.app.ui.viewer2.container.ContentViewHolderFactory$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$database$dbtype$MediaType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
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
                int[] r0 = $SwitchMap$com$samsung$android$gallery$database$dbtype$MediaType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.database.dbtype.MediaType r1 = com.samsung.android.gallery.database.dbtype.MediaType.UnlockScreen     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.container.ContentViewHolderFactory.AnonymousClass1.<clinit>():void");
        }
    }

    public ContentViewHolderFactory(Context context, ContainerModel containerModel, AbsViewerHolder.StateListener stateListener) {
        this.mLayoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mContainerModel = containerModel;
        this.mLocationKey = containerModel.getLocationKey();
        this.mStateListener = stateListener;
    }

    private int createVideoViewer(MediaItem mediaItem) {
        if (!LocationKey.isSecondDepthGroupPanelView(this.mLocationKey)) {
            if (LocationKey.isSuperSlowGroupPanelView(this.mLocationKey)) {
                return 23;
            }
            if (LocationKey.isHighlightGroupPanelView(this.mLocationKey) && supportHighlightClip(mediaItem)) {
                return 23;
            }
        }
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL && Features.isEnabled(Features.SUPPORT_DYNAMIC_VIEW) && LocationKey.isDynamicViewList(this.mLocationKey)) {
            return 22;
        }
        if (supportLiveEffectViewer(mediaItem)) {
            return 24;
        }
        return 20;
    }

    private int getImageViewerType(MediaItem mediaItem) {
        if (isMovie(mediaItem)) {
            return 3;
        }
        if (mediaItem.isSimilarShot() && mediaItem.getCount() > 0) {
            return 5;
        }
        if (mediaItem.isBurstShot()) {
            return 2;
        }
        if (isMotionPhoto(mediaItem) && !Features.isEnabled(Features.IS_SEP_LITE)) {
            return 4;
        }
        if (MediaItemUtil.isDualShot(mediaItem)) {
            return 18;
        }
        return 1;
    }

    private boolean isMotionPhoto(MediaItem mediaItem) {
        return mediaItem.isMotionPhoto();
    }

    private boolean isMovie(MediaItem mediaItem) {
        boolean z;
        if (!Features.isEnabled(Features.SUPPORT_SHARED_GIF_PLAY) || TextUtils.isEmpty(MediaItemMde.getHiddenFilePath(mediaItem))) {
            z = false;
        } else {
            z = true;
        }
        if (!mediaItem.isMovie() || mediaItem.isCloudOnly() || (mediaItem.isSharing() && !z)) {
            return false;
        }
        return true;
    }

    private boolean isSingleTakenShot(MediaItem mediaItem) {
        return mediaItem.isSingleTakenShot();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$prepareCache$0(Context context, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        ConcurrentLinkedQueue<ContentViewerLayoutBinding> concurrentLinkedQueue = mViewBindingCache;
        concurrentLinkedQueue.add(ContentViewerLayoutBinding.inflate(layoutInflater, viewGroup, false));
        concurrentLinkedQueue.add(ContentViewerLayoutBinding.inflate(layoutInflater, viewGroup, false));
    }

    public static void onDestroy() {
        mViewBindingCache.clear();
    }

    public static void prepareCache(Context context, ViewGroup viewGroup) {
        SimpleThreadPool.getInstance().execute(new C0199b(5, context, viewGroup));
    }

    private boolean supportHighlightClip(MediaItem mediaItem) {
        DynamicViewInfo dynamicViewInfo;
        if (mediaItem == null) {
            dynamicViewInfo = null;
        } else {
            dynamicViewInfo = DynamicViewData.of(mediaItem).dynamicViewInfo;
        }
        if (dynamicViewInfo == null || dynamicViewInfo.getClipCount() > 1) {
            return true;
        }
        return false;
    }

    private boolean supportLiveEffectViewer(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_VIEWER) || !mediaItem.isLiveEffect() || !mediaItem.isLocal()) {
            return false;
        }
        return true;
    }

    public AbsViewerHolder createContentViewHolder(ViewGroup viewGroup, int i2) {
        boolean z;
        if (i2 == 7) {
            return new RemasterViewerHolder(RemasterViewerLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false), this.mStateListener);
        }
        if (i2 == 23 || i2 == 40) {
            return new SingleTakenViewerHolder(SingleTakenViewerLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false), this.mStateListener, new ContentViewCompositeFactory(this.mContainerModel));
        }
        if (i2 == 100) {
            return new UnlockViewerHolder(UnlockFragmentLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false), this.mStateListener);
        }
        ContentViewerLayoutBinding poll = mViewBindingCache.poll();
        if (poll == null || poll.getRoot().getContext() != viewGroup.getContext()) {
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            poll = ContentViewerLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false);
        }
        return new ContentViewerHolder(poll, this.mStateListener);
    }

    public int getFlipCoverType(MediaItem mediaItem) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$database$dbtype$MediaType[mediaItem.getMediaType().ordinal()];
        if (i2 == 1) {
            return 60;
        }
        if (i2 == 2) {
            return 61;
        }
        if (i2 != 3) {
            return 0;
        }
        return 100;
    }

    public int getType(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getMediaType() == null) {
            Log.e("ContentViewHolderFactory", "getType null");
            return 1;
        } else if (this.mContainerModel.isFlipCoverGallery()) {
            return getFlipCoverType(mediaItem);
        } else {
            if (this.mLocationKey.startsWith("location://revitalized") && !mediaItem.isBroken()) {
                return 7;
            }
            if (isSingleTakenShot(mediaItem) && !LocationKey.isSecondDepthGroupPanelView(this.mLocationKey)) {
                return 40;
            }
            if (!VideoPropData.of(mediaItem).longExposure) {
                int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$database$dbtype$MediaType[mediaItem.getMediaType().ordinal()];
                if (i2 == 1) {
                    return getImageViewerType(mediaItem);
                }
                if (i2 == 2) {
                    return createVideoViewer(mediaItem);
                }
                if (i2 != 3) {
                    return 0;
                }
                return 100;
            } else if (isMotionPhoto(mediaItem) || mediaItem.isVideo()) {
                return 20;
            } else {
                return 1;
            }
        }
    }
}
