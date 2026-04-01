package a8;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.work.Configuration;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkGenerationalId;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.remote.v2.PresentationViewPager;
import com.samsung.android.gallery.app.service.HighlightEncodeService;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardFragmentV2;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardViewAdapter;
import com.samsung.android.gallery.app.ui.list.reorder.ReorderItemController;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeaderView;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinModel;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.v1.HideRuleSceneItemAdapter;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.v1.HideRuleSceneItemViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageInfo;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageItemPicker;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.IrregularGradientHelper;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.OnDemandPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.app.ui.menu.list.ListMenuHandler;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiEditList;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import com.samsung.android.gallery.app.ui.viewholders.StoryImageViewHolder;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.EditDetailsUpdater;
import com.samsung.android.gallery.module.details.EditDetailsUpdater2;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapTemplateBinder;
import com.samsung.android.gallery.module.story.transcode.config.EncodeInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.widget.appbar.CustomCover;
import com.samsung.android.gallery.widget.details.DetailsListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import com.samsung.android.sdk.cover.ScoverState;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import x0.C0332j;
import z2.C0362a;
import z2.p;
import z2.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2483h;

    public /* synthetic */ d(MediaItem mediaItem, String str, Intent intent, EventContext eventContext) {
        this.d = 8;
        this.e = mediaItem;
        this.f2483h = str;
        this.f = intent;
        this.g = eventContext;
    }

    public final void run() {
        boolean z;
        switch (this.d) {
            case 0:
                ((StoryImageViewHolder) this.f).lambda$bindChunkTagView$2((MediaItem) this.e, (TextView) this.g, (String) this.f2483h);
                return;
            case 1:
                ((PresentationViewPager) this.f).lambda$updateViews$7((PhotoViewMotionControl) this.g, (MediaItem) this.e, (Bitmap) this.f2483h);
                return;
            case 2:
                ((StoriesPinModel) this.f).lambda$notifyDataRangeChanged$3((ArrayList) this.e, (ArrayList) this.g, (List) this.f2483h);
                return;
            case 3:
                ((HighlightEncodeService) this.f).lambda$prepareMotionPhotoInfo$13((EncodeInfo) this.e, (AtomicBoolean) this.g, (Consumer) this.f2483h);
                return;
            case 4:
                ((CollageItemPicker) this.f).lambda$pick$1((MediaItem) this.e, (CollageInfo) this.g, (Consumer) this.f2483h);
                return;
            case 5:
                ((EditDetailsUpdater) this.f).lambda$onSave$1((MediaItem) this.e, (Runnable) this.g, (String) this.f2483h);
                return;
            case 6:
                ((EditDetailsUpdater2) this.f).lambda$onSave$4((MediaItem) this.e, (Runnable) this.g, (String) this.f2483h);
                return;
            case 7:
                ((RecapTemplateBinder) this.f).lambda$createLottie$3((AtomicBoolean) this.e, (BiConsumer) this.g, (C0332j) this.f2483h);
                return;
            case 8:
                ListMenuHandler.lambda$handleCommonAction$2((MediaItem) this.e, (String) this.f2483h, (Intent) this.f, (EventContext) this.g);
                return;
            case 9:
                ((HideRuleSceneItemAdapter) this.f).lambda$onBindViewHolder$0((HideRuleSceneItemViewHolder) this.g, (MediaItem) this.e, (Bitmap) this.f2483h);
                return;
            case 10:
                ((ReorderItemController) this.f).lambda$sendDropEvent$2((View) this.e, (DragEvent) this.g, (GalleryListView) this.f2483h);
                return;
            case 11:
                ((AiEditList) this.f).lambda$loadAndUpdateVisibility$3((BooleanSupplier) this.g, (MediaItem) this.e, (ArrayList) this.f2483h);
                return;
            case 12:
                ((AiEditList) this.f).lambda$updateMediaItemAndVisibility$1((MediaItem) this.e, (Bitmap) this.g, (BooleanSupplier) this.f2483h);
                return;
            case 13:
                ((AiEditItem) this.f).lambda$requestDownload$4((MediaItem) this.e, (Consumer) this.g, (DownloadType) this.f2483h);
                return;
            case 14:
                ((ClipboardFragmentV2) this.f).lambda$loadPreselectedItems$0((String) this.f2483h, (String) this.e, (LaunchIntent) this.g);
                return;
            case 15:
                ((ClipboardViewAdapter) this.f).lambda$setBitmap$3((ImageView) this.g, (MediaItem) this.e, (Bitmap) this.f2483h);
                return;
            case 16:
                ((CustomCover) this.f).lambda$bindImage$3((ImageView) this.g, (Bitmap) this.f2483h, (MediaItem) this.e);
                return;
            case 17:
                Schedulers.lambda$registerRescheduling$0((List) this.f, (WorkGenerationalId) this.e, (Configuration) this.g, (WorkDatabase) this.f2483h);
                return;
            case 18:
                ((IrregularGradientHelper) this.f).lambda$setGradientBackground$3((ArrayList) this.e, (View) this.g, (IrregularView) this.f2483h);
                return;
            case 19:
                ((OnDemandPage) this.f).lambda$onThumbnailLoadCompleted$3((Bitmap) this.g, (MediaItem) this.e, (ListViewHolder) this.f2483h);
                return;
            case 20:
                ((IrregularView) this.f).lambda$onThumbnailLoadCompleted$1((Bitmap) this.g, (MediaItem) this.e, (ThumbKind) this.f2483h);
                return;
            case 21:
                ((DetailsListAdapter) this.f).lambda$onThumbnailLoadCompleted$3((ListViewHolder) this.e, (UniqueKey) this.g, (Bitmap) this.f2483h);
                return;
            case 22:
                ((SearchPicturesPresenter) this.f).lambda$reloadSearchFilter$9((Context) this.e, (Bundle) this.g, (String) this.f2483h);
                return;
            case 23:
                ((ClusterPicturesPresenter) this.f).lambda$updateToolbarCount$1((String) this.f2483h, (Context) this.e, (Bundle) this.g);
                return;
            case 24:
                ((StoryHighlightViewPagerAdapter) this.f).lambda$requestLargeImage$6((MediaItem) this.e, (ViewPagerHolder) this.g, (String) this.f2483h);
                return;
            case 25:
                ((StoryHighlightViewPagerAdapter) this.f).lambda$onThumbnailLoadCompleted$1((MediaItem) this.e, (ViewPagerHolder) this.g, (Bitmap) this.f2483h);
                return;
            case 26:
                q qVar = (q) this.f;
                SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) this.e;
                TextView textView = (TextView) this.g;
                Button button = (Button) this.f2483h;
                p pVar = qVar.f2221i;
                int measuredWidth = snackbarContentLayout.getMeasuredWidth();
                int measuredHeight = snackbarContentLayout.getMeasuredHeight();
                if (qVar.f2220h.getResources().getDimensionPixelSize(R.dimen.sesl_design_snackbar_suggest_background_radius) <= 22) {
                    z = true;
                } else {
                    z = false;
                }
                int i2 = 44;
                int i7 = 44;
                if (!z) {
                    i2 = 100;
                }
                if (!z) {
                    i7 = 100;
                }
                qVar.h(i2, i7, snackbarContentLayout);
                snackbarContentLayout.setAlpha(1.0f);
                q.f(snackbarContentLayout, ScoverState.TYPE_NFC_SMART_COVER);
                pVar.setAlpha(1.0f);
                pVar.setTranslationY((float) (q.y + measuredHeight));
                SpringAnimation springAnimation = new SpringAnimation(pVar, DynamicAnimation.TRANSLATION_Y);
                springAnimation.cancel();
                springAnimation.setSpring(new SpringForce().setStiffness(350.0f).setDampingRatio(1.0f));
                springAnimation.animateToFinalPosition(0.0f - ((float) q.y));
                springAnimation.setStartVelocity(0.1f);
                springAnimation.start();
                new Handler(Looper.getMainLooper()).postDelayed(new C0362a(qVar, snackbarContentLayout, textView, button, i2, measuredWidth, i7, measuredHeight), 200);
                return;
            default:
                ((SearchCreatureHeaderView) this.f).lambda$bindImage$18((MediaItem) this.e, (ImageView) this.g, (Bitmap) this.f2483h);
                return;
        }
    }

    public /* synthetic */ d(CustomCover customCover, ImageView imageView, Bitmap bitmap, MediaItem mediaItem) {
        this.d = 16;
        this.f = customCover;
        this.g = imageView;
        this.f2483h = bitmap;
        this.e = mediaItem;
    }

    public /* synthetic */ d(Object obj, Object obj2, MediaItem mediaItem, Object obj3, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.e = mediaItem;
        this.f2483h = obj3;
    }

    public /* synthetic */ d(Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = obj2;
        this.g = obj3;
        this.f2483h = obj4;
    }

    public /* synthetic */ d(Object obj, String str, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.f = obj;
        this.f2483h = str;
        this.e = obj2;
        this.g = obj3;
    }
}
