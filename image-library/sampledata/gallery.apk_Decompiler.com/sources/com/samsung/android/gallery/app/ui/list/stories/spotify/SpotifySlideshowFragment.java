package com.samsung.android.gallery.app.ui.list.stories.spotify;

import A4.C0386v;
import B8.e;
import H3.l;
import H7.A;
import I4.b;
import J6.c;
import K4.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.controller.story.StartHighlightPlayerCmd;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerAdapter;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.SlideshowViewerHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.MarginParams;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpotifySlideshowFragment extends MvpBaseFragment<IMvpBaseView, MvpBasePresenter<IMvpBaseView>> {
    ImageView mBlurredBackground;
    ViewerObjectComposite mCurrent;
    MediaItem mHeader;
    MediaData mMediaData;
    private final MediaData.OnDataChangeListener mMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            int i2;
            StringCompat access$000 = SpotifySlideshowFragment.this.TAG;
            MediaData mediaData = SpotifySlideshowFragment.this.mMediaData;
            if (mediaData != null) {
                i2 = mediaData.getCount();
            } else {
                i2 = -1;
            }
            Log.d(access$000, "onDataChanged", Integer.valueOf(i2));
        }
    };
    final SlideshowModel mModel = new SlideshowModel();
    int mPositionOffset;
    PhotoPreView mPreview;
    final SlideshowProgress mProgress = new SlideshowProgress();
    ViewPager2 mViewPager;

    /* access modifiers changed from: private */
    public void applyBlur(MediaItem mediaItem) {
        if (mediaItem != null) {
            ThumbnailLoader.getInstance().getOrLoad(mediaItem, ThumbKind.SMALL_CROP_KIND, new e(mediaItem, 1), new a(1, this));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: bindBitmap */
    public void lambda$blurAndBindBitmap$4(ImageView imageView, Bitmap bitmap) {
        if (imageView != null && bitmap != null) {
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(0);
        }
    }

    private void blurAndBindBitmap(ImageView imageView, Bitmap bitmap) {
        if (imageView != null && bitmap != null) {
            imageView.post(new c(this, imageView, BitmapUtils.blur(imageView.getContext(), bitmap), 4));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyBlur$2(Bitmap bitmap) {
        blurAndBindBitmap(this.mBlurredBackground, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyBlur$3(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        if (ThreadUtil.isMainThread()) {
            SimpleThreadPool.getInstance().execute(new H.a(23, this, bitmap));
        } else {
            blurAndBindBitmap(this.mBlurredBackground, bitmap);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        boolean z;
        MediaItem mediaItem = this.mHeader;
        StringCompat stringCompat = this.TAG;
        if (mediaItem != null) {
            z = true;
        } else {
            z = false;
        }
        Log.d(stringCompat, "onClick#highlight", Boolean.valueOf(z));
        if (mediaItem != null) {
            new StartHighlightPlayerCmd().execute(this.mPresenter, Integer.valueOf(mediaItem.getAlbumID()));
            postAnalyticsLog(AnalyticsEventId.EVENT_MENU_STORY_STORY_VIDEO);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ SlideshowViewerHolder lambda$initViewPager$5(ViewerObjectComposite viewerObjectComposite) {
        return (SlideshowViewerHolder) viewerObjectComposite.getViewHolder();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initViewPager$8(Integer num) {
        Optional.ofNullable(this.mCurrent).map(new Gb.a(25)).ifPresent(new b(13));
        Optional.ofNullable((SpotifySlideshowAdapter) this.mViewPager.getAdapter()).ifPresent(new c(1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onEnterTransitionEndV2$1() {
        ViewUtils.setVisibility(this.mPreview, 4);
    }

    private void setTransitionPreview(TransitionInfo transitionInfo) {
        MediaItem mediaItem;
        PhotoPreView photoPreView = this.mPreview;
        if (photoPreView != null && transitionInfo != null && (mediaItem = transitionInfo.item) != null) {
            photoPreView.setScaledTransitionInfo(transitionInfo.scale, transitionInfo.point);
            this.mPreview.setBasicInfo(transitionInfo.bitmap, mediaItem, new MarginParams());
        }
    }

    public void bindView(View view) {
        MediaItem mediaItem;
        MediaItem mediaItem2;
        super.bindView(view);
        this.mBlurredBackground = (ImageView) view.findViewById(R.id.photo_background);
        this.mPreview = (PhotoPreView) view.findViewById(R.id.photo_preview);
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.viewer_panel);
        if (viewStub != null) {
            viewStub.setLayoutResource(R.layout.spotify_memory_slideshow_panel_layout);
            View inflate = viewStub.inflate();
            inflate.findViewById(R.id.detail_text).setOnClickListener(new A(6, this));
            this.mProgress.bindView((ProgressBar) inflate.findViewById(R.id.determinate_bar));
        }
        TransitionInfo popTransitionInfo = SharedTransition.popTransitionInfo(this.mBlackboard);
        if (!(popTransitionInfo == null || (mediaItem2 = popTransitionInfo.item) == null)) {
            this.mHeader = mediaItem2;
            SharedTransition.setTransitionName(this.mPreview, popTransitionInfo.name);
            lambda$blurAndBindBitmap$4(this.mBlurredBackground, (Bitmap) this.mBlackboard.read(LocationKey.getHeaderCacheKey("data://user/storyBlurBitmap", String.valueOf(popTransitionInfo.item.getFileId()))));
            setTransitionPreview(popTransitionInfo);
            popTransitionInfo.recycle();
        }
        if (this.mHeader == null) {
            MediaData mediaData = this.mMediaData;
            if (mediaData != null) {
                mediaItem = mediaData.read(0);
            } else {
                mediaItem = null;
            }
            this.mHeader = mediaItem;
        }
        MediaItem mediaItem3 = this.mHeader;
        if (mediaItem3 != null) {
            this.mPositionOffset = this.mMediaData.findPositionByFileId(mediaItem3.getFileId());
        }
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            galleryToolbar.setBackgroundColor(0);
            this.mToolbar.setNavigationIcon((int) R.drawable.tw_ic_ab_back_mtrl_detailview_light);
            this.mToolbar.setNavigationContentDescription(R.string.navigate_up);
            this.mToolbar.setTitle(this.mModel.getTitle(this.mHeader));
            this.mToolbar.setSubtitle((CharSequence) this.mModel.getSubtitle(this.mHeader));
        }
        ViewPager2 viewPager2 = (ViewPager2) view.findViewById(R.id.view_pager);
        this.mViewPager = viewPager2;
        if (viewPager2 != null) {
            initViewPager(viewPager2, this.mPositionOffset);
        }
        Log.d(this.TAG, "bindView", Integer.valueOf(this.mPositionOffset), this.mHeader);
    }

    public MvpBasePresenter<IMvpBaseView> createPresenter(IMvpBaseView iMvpBaseView) {
        return new MvpBasePresenter<IMvpBaseView>(getBlackboard(), this) {
            /* access modifiers changed from: private */
            public static /* synthetic */ MediaItem[] lambda$getAllItems$0(int i2) {
                return new MediaItem[i2];
            }

            /* JADX WARNING: type inference failed for: r2v3, types: [java.lang.Object, java.util.function.IntFunction] */
            public MediaItem[] getAllItems() {
                MediaData mediaData = SpotifySlideshowFragment.this.mMediaData;
                if (mediaData == null || mediaData.getCount() <= 0) {
                    return super.getAllItems();
                }
                String str = this + ".getAllItems";
                try {
                    SpotifySlideshowFragment.this.mMediaData.acquireReadLock(str);
                    IntStream range = IntStream.range(0, SpotifySlideshowFragment.this.mMediaData.getCount());
                    MediaData mediaData2 = SpotifySlideshowFragment.this.mMediaData;
                    Objects.requireNonNull(mediaData2);
                    return (MediaItem[]) range.mapToObj(new C0386v(4, mediaData2)).toArray(new Object());
                } finally {
                    SpotifySlideshowFragment.this.mMediaData.releaseReadLock(str);
                }
            }

            public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
            }
        };
    }

    public int getLayoutId() {
        return R.layout.fragment_spotify_slideshow_layout;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_EVENT_VIEW_SLIDE_SHOW.toString();
    }

    public void initViewPager(ViewPager2 viewPager2, int i2) {
        ContainerModel containerModel = new ContainerModel(this.mSystemUi, this);
        containerModel.setMediaData(this.mMediaData);
        containerModel.bindView(this.mPresenter, viewPager2, (ViewStub) null);
        viewPager2.setAdapter(new SpotifySlideshowAdapter(this.mBlackboard, this.mMediaData, containerModel, new VuContainerAdapter.ViewChangeListener() {
            public void onViewConfirm(int i2, ViewerObjectComposite viewerObjectComposite) {
                boolean z;
                ViewPager2 viewPager2 = SpotifySlideshowFragment.this.mViewPager;
                if (viewPager2 == null || viewPager2.getVisibility() != 0) {
                    z = false;
                } else {
                    z = true;
                }
                SpotifySlideshowFragment spotifySlideshowFragment = SpotifySlideshowFragment.this;
                spotifySlideshowFragment.mCurrent = viewerObjectComposite;
                Log.i(spotifySlideshowFragment.TAG, "onVuConfirm", Boolean.valueOf(z), Integer.valueOf(i2), viewerObjectComposite);
                SpotifySlideshowFragment.this.applyBlur(viewerObjectComposite.getModel().getMediaItem());
                if (z) {
                    SpotifySlideshowFragment.this.mProgress.moveTo(i2);
                }
            }

            public void onViewInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
                Log.i(SpotifySlideshowFragment.this.TAG, "onVuInvalidate", Integer.valueOf(i2));
            }
        }).setOffset(i2));
        int i7 = 0;
        viewPager2.setCurrentItem(i2, false);
        viewPager2.setUserInputEnabled(false);
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            i7 = mediaData.getCount();
        }
        this.mProgress.setOffset(this.mPositionOffset).setCount(i7).setCallback(new K5.a(1, this));
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.mMediaData == null) {
            String locationKey = getLocationKey();
            if (LocationKey.isSpotifySlideShow(locationKey)) {
                locationKey = DataKey.getStorySlideshowDataKey(locationKey);
            }
            MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(locationKey);
            this.mMediaData = open;
            open.register(this.mMediaDataChangeListener);
            Log.d(this.TAG, "onAttach", locationKey, Integer.valueOf(this.mMediaData.getCount()));
        }
    }

    public void onDestroy() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mMediaDataChangeListener);
            this.mMediaData.close();
            this.mMediaData = null;
        }
        releaseViewPager(this.mViewPager);
        super.onDestroy();
    }

    public void onEnterTransitionEndV2() {
        Log.d(this.TAG, "onEnterTransitionEndV2");
        this.mViewPager.setVisibility(0);
        this.mPreview.post(new l(18, this));
        this.mProgress.startNext();
    }

    public void onPause() {
        super.onPause();
        if (this.mProgress.support()) {
            this.mProgress.pause();
            keepScreenOn(false);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mProgress.support()) {
            this.mProgress.resume();
            keepScreenOn(true);
        }
    }

    public void releaseViewPager(ViewPager2 viewPager2) {
        if (viewPager2 != null) {
            Optional.ofNullable((SpotifySlideshowAdapter) viewPager2.getAdapter()).ifPresent(new c(0));
            viewPager2.setAdapter((RecyclerView.Adapter) null);
        }
        this.mProgress.release();
        this.mCurrent = null;
    }

    public void setCustomAnimations(FragmentTransaction fragmentTransaction, IBaseFragment iBaseFragment) {
        if (PreferenceFeatures.OneUi30.MEMORIES) {
            fragmentTransaction.setCustomAnimations(0, 0, R.anim.spotify_depth_out_exit, 0);
        }
    }

    public void initView(View view) {
    }
}
