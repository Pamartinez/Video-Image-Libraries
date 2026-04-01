package com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate;

import A4.C0368c;
import Qa.a;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ViewHolderStateDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.pagetransformer.ViewerPage2TransformerComposition;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.ISlideshowView;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.SlideshowAdapter;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.transformer.SShowMarginPageTransformer;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.UnsupportedApiException;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPagerDelegate extends AbsVuDelegate<ISlideshowView> {
    private ViewerObjectComposite mCurrentViewer;
    int mPosition;
    private ViewPager2 mViewPager;
    private SlideshowAdapter mViewPagerAdapter;

    public ViewPagerDelegate(ISlideshowView iSlideshowView) {
        super(iSlideshowView);
    }

    private int findHoldingPosition(MediaItem mediaItem) {
        long j2;
        int i2;
        MediaData mediaData = ((ContainerModel) this.mModel).getMediaData();
        long mediaId = mediaItem.getMediaId();
        long fileId = mediaItem.getFileId();
        if (hasValidMediaId(mediaItem)) {
            j2 = mediaId;
        } else {
            j2 = fileId;
        }
        if (hasValidMediaId(mediaItem)) {
            i2 = mediaData.findPosition(mediaId);
        } else {
            i2 = mediaData.findPositionByFileId(fileId);
        }
        if (i2 < 0) {
            i2 = findHoldingPositionByScan(mediaData, j2, new a(3, (Object) this, (Object) mediaItem));
        }
        if (i2 >= 0) {
            String str = this.TAG;
            Log.d(str, "findHoldingPosition " + Logger.v(Long.valueOf(mediaId), Long.valueOf(fileId), Integer.valueOf(i2)));
        }
        return i2;
    }

    private int findHoldingPositionByScan(MediaData mediaData, long j2, Function<MediaItem, Long> function) {
        int currentItem = this.mViewPager.getCurrentItem();
        int min = Math.min(currentItem + 100, mediaData.getCount());
        for (int max = Math.max(0, currentItem - 100); max < min; max++) {
            if (function.apply(mediaData.read(max)).longValue() == j2) {
                String str = this.TAG;
                Log.d(str, "findHoldingPositionByScan" + Logger.v(Long.valueOf(j2), Integer.valueOf(max)));
                return max;
            }
        }
        return -1;
    }

    private int getViewPagerMargin() {
        Resources resources;
        Context context = ((ISlideshowView) this.mView).getContext();
        if (context != null) {
            resources = context.getResources();
        } else {
            resources = null;
        }
        if (resources != null) {
            return resources.getDimensionPixelOffset(R.dimen.viewer_item_margin);
        }
        String str = this.TAG;
        Log.e(str, "getViewPagerMargin fail " + context);
        return 60;
    }

    private boolean hasValidMediaId(MediaItem mediaItem) {
        if (mediaItem.getMediaId() > 0) {
            return true;
        }
        return false;
    }

    private void holdPosition() {
        if (this.mViewPager.isFakeDragging()) {
            this.mViewPager.endFakeDrag();
        }
        if (this.mViewPager.getScrollState() == 0) {
            ViewerObjectComposite currentContentViewer = ((ISlideshowView) this.mView).getCurrentContentViewer();
            MediaItem mediaItem = null;
            if (currentContentViewer != null) {
                try {
                    mediaItem = currentContentViewer.getModel().getRepresentativeItem();
                } catch (UnsupportedApiException | NullPointerException e) {
                    String str = this.TAG;
                    Log.e(str, "holdPosition failed " + MediaItemUtil.getDebugLog((MediaItem) null) + " e=" + e.getMessage());
                    return;
                }
            }
            if (mediaItem != null) {
                int findHoldingPosition = findHoldingPosition(mediaItem);
                if (findHoldingPosition >= 0) {
                    String str2 = this.TAG;
                    ((ISlideshowView) this.mView).printLog(str2, "hold to pos " + currentContentViewer.getModel().getPosition() + " > " + findHoldingPosition);
                    if (((ISlideshowView) this.mView).isViewResumed()) {
                        this.mViewPager.setCurrentItem(findHoldingPosition, false);
                    } else {
                        ViewUtils.post(this.mViewPager, new C0368c(this, findHoldingPosition, 19));
                    }
                }
            } else {
                String str3 = this.TAG;
                Log.w(str3, "holdPosition failed : c=" + currentContentViewer);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Long lambda$findHoldingPosition$1(MediaItem mediaItem, MediaItem mediaItem2) {
        long j2;
        if (hasValidMediaId(mediaItem)) {
            j2 = mediaItem2.getMediaId();
        } else {
            j2 = mediaItem2.getFileId();
        }
        return Long.valueOf(j2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$holdPosition$0(int i2) {
        this.mViewPager.setCurrentItem(i2, false);
    }

    private void setPosition() {
        this.mViewPager.setCurrentItem(this.mPosition, false);
    }

    private void setViewPager() {
        SlideshowAdapter slideshowAdapter = new SlideshowAdapter(this.mBlackboard, ((ContainerModel) this.mModel).getMediaData(), (ContainerModel) this.mModel, (ViewHolderStateDelegate) getDelegate(ViewHolderStateDelegate.class));
        this.mViewPagerAdapter = slideshowAdapter;
        slideshowAdapter.setHasStableIds(true);
        this.mViewPager.setAdapter(this.mViewPagerAdapter);
        this.mViewPager.setPageTransformer(ViewerPage2TransformerComposition.Builder.create().addTransformer(new SShowMarginPageTransformer(getViewPagerMargin())).build());
    }

    public ViewerObjectComposite getCurrentContentViewer() {
        return this.mCurrentViewer;
    }

    public void onBindView(View view) {
        this.mViewPager = (ViewPager2) view.findViewById(R.id.view_pager);
    }

    public void onDestroy() {
        SlideshowAdapter slideshowAdapter = this.mViewPagerAdapter;
        if (slideshowAdapter != null) {
            slideshowAdapter.destroy();
        }
    }

    public void onMediaDataChanged(int i2, int i7) {
        if (this.mViewPagerAdapter != null) {
            holdPosition();
            this.mViewPagerAdapter.notifyDataSetChanged();
        }
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        this.mCurrentViewer = viewerObjectComposite;
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mPosition = ArgumentsUtil.getArgValue(DataKey.getSlideshowDataKey(((ISlideshowView) this.mView).getLocationKey()), Message.KEY_POSITION, 0);
        setViewPager();
        setPosition();
    }
}
