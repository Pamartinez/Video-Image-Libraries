package A4;

import Ae.a;
import Ae.c;
import Vf.C;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.os.CancellationSignal;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.transformer.ExportException;
import androidx.media3.transformer.ExportResult;
import androidx.media3.transformer.Transformer;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.SwitchPreferenceCompat;
import androidx.transition.FragmentTransitionSupport;
import androidx.transition.Transition;
import androidx.work.ListenableFutureKt;
import com.samsung.android.gallery.app.activity.external.StoriesViewNavigatorDelegate;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoDeleteVideoCmd;
import com.samsung.android.gallery.app.controller.story.SaveOnDemandStoryCmd;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.app.remote.v2.PresentationViewPagerAdapter;
import com.samsung.android.gallery.app.remote.v2.PresentationViewPagerHolder;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.ConcatThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter;
import com.samsung.android.gallery.app.ui.list.search.editcreature.NameViewHolder;
import com.samsung.android.gallery.app.ui.list.search.editcreature.RegisteredCreatureAdapter;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchSelectedFiltersDelegate;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.v1.HideRuleSceneItemAdapter;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.v1.HideRuleSceneItemViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.OnDemandPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.theme.StoriesThemeViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.theme.ThemeItem;
import com.samsung.android.gallery.app.ui.list.suggestions.SuggestionsImageViewHolder;
import com.samsung.android.gallery.app.ui.list.suggestions.SuggestionsItemAdapter;
import com.samsung.android.gallery.app.ui.viewer2.grouppanel.list.GroupPanelListAdapter;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.settings.ui.SettingFragment;
import com.samsung.android.gallery.settings.ui.SharingServiceFragment;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.listview.pinch.v3.BitmapCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.LiveMotionAdapter;
import com.samsung.android.sdk.scs.ai.translation.LlmTranslationRequest;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslationRequest;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslator;
import com.samsung.android.sdk.scs.base.tasks.OnCompleteListener;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.scsp.common.PushConsumerManager;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.decorator.AbstractDecorator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Q implements ThumbnailLoadedListener, Preference.OnPreferenceChangeListener, DefaultTrackSelector.TrackInfo.Factory, MediaScannerListener, DataCollectionDelegate.OnDataCompletionListener, ListenerSet.Event, OnCompleteListener, FaultBarrier.ThrowableRunnable, FaultBarrier.ThrowableSupplier, CancellationSignal.OnCancelListener, CallbackToFutureAdapter.Resolver, FutureListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ Q(Object obj, MediaItem mediaItem, Object obj2, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = mediaItem;
        this.g = obj2;
    }

    public void a() {
        FragmentTransitionSupport.lambda$setListenerForTransitionEnd$0((Runnable) this.f, (Transition) this.g, (Runnable) this.e);
    }

    public Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        switch (this.d) {
            case 21:
                return ListenableFutureKt.launchFuture$lambda$1((C1232h) this.f, (C) this.g, (c) this.e, completer);
            default:
                return ListenableFutureKt.executeAsync$lambda$4((Executor) this.f, (String) this.g, (a) this.e, completer);
        }
    }

    public List create(int i2, TrackGroup trackGroup, int[] iArr) {
        return DefaultTrackSelector.lambda$selectTextTrack$4((DefaultTrackSelector.Parameters) this.f, (String) this.g, (String) this.e, i2, trackGroup, iArr);
    }

    public Object get() {
        return ((AbstractDecorator) this.f).lambda$getFeature$3((String) this.g, this.e);
    }

    public void invoke(Object obj) {
        ((Transformer) this.f).lambda$onExportCompletedWithError$2((ExportResult) this.g, (ExportException) this.e, (Transformer.Listener) obj);
    }

    public void onComplete(Task task) {
        NeuralTranslator.lambda$translate$2((Handler) this.f, (LlmTranslationRequest) this.g, (NeuralTranslationRequest) this.e, task);
    }

    public void onCompleted() {
        ((MotionPhotoDeleteVideoCmd) this.f).lambda$onConfirmed$1((MediaItem) this.e, (String) this.g);
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        ((SaveOnDemandStoryCmd) this.f).lambda$onExecute$0((EventContext) this.g, (CharSequence) this.e, eventContext, arrayList);
    }

    public void onFutureDone(Future future) {
        ((EditCreatureNamePresenter) this.f).lambda$executeMerge$22((String) this.g, (String) this.e, future);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 0:
                ((ConcatThumbnailRequestHolder) this.f).lambda$loadConcatThumbnailAsync$1((AtomicInteger) this.g, (MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 1:
                ((StoriesViewNavigatorDelegate) this.f).lambda$preloadBitmap$3((TimeTickLog) this.g, (MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 4:
                ((SearchSelectedFiltersDelegate) this.f).lambda$postThumbnailTypeMainFilter$2((String) this.g, (MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 5:
                ((BitmapCache) this.f).lambda$requestBitmap$0((ThumbnailInterface) this.g, (String) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 6:
                ((LiveMotionAdapter) this.f).lambda$bindThumbnail$1((ListViewHolder) this.g, (MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 8:
                ((SuggestionsItemAdapter) this.f).lambda$onBindViewHolder$1((SuggestionsImageViewHolder) this.g, (MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 10:
                ((GroupPanelListAdapter) this.f).lambda$loadOrDecode$2((MediaItem) this.e, (ImageViewHolder) this.g, bitmap, uniqueKey, thumbKind);
                return;
            case 12:
                ((ShareProvider) this.f).lambda$loadBitmap$2((Uri) this.g, (MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 13:
                ((PresentationViewPagerAdapter) this.f).lambda$loadOrDecode$0((PresentationViewPagerHolder) this.g, (MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 18:
                ((HideRuleSceneItemAdapter) this.f).lambda$onBindViewHolder$1((HideRuleSceneItemViewHolder) this.g, (MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 19:
                ((ClipboardViewAdapter) this.f).lambda$setBitmap$4((ImageView) this.g, (MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 24:
                ((RegisteredCreatureAdapter) this.f).lambda$onBindViewHolder$1((CreatureNameData) this.g, (NameViewHolder) this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 25:
                ((OnDemandPage) this.f).lambda$loadBindThumbnail$2((ListViewHolder) this.g, (MediaItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
            default:
                ((StoriesThemeViewAdapter) this.f).lambda$bindThumbnail$0((ListViewHolder) this.g, (ThemeItem) this.e, bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        switch (this.d) {
            case 2:
                return ((SettingFragment) this.f).lambda$initAdvancedPreference$47((SettingPreference) this.g, (SwitchPreferenceCompat) this.e, preference, obj);
            default:
                return ((SharingServiceFragment) this.f).lambda$loadPreference$2((Context) this.g, (PreferenceCategory) this.e, preference, obj);
        }
    }

    public void run() {
        PushConsumerManager.lambda$add$0((Supplier) this.f, (String[]) this.g, (Bundle) this.e);
    }

    public /* synthetic */ Q(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.e = obj3;
    }
}
