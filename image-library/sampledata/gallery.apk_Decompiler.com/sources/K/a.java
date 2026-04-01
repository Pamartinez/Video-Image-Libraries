package K;

import E2.h;
import android.bluetooth.BluetoothAdapter;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCaptureDrawHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelper;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.delegate.SesPickerDelegate;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.app.ui.list.sharings.pinch.SharingPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.suggestions.remaster.RemasterItemListAdapter;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller;
import com.samsung.android.gallery.module.creature.base.CreatureIndexingDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remotegallery.IThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.search.SearchIndexListener;
import com.samsung.android.gallery.widget.animations.IThemeColor;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.scloud.cloudagent.CloudStore;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.util.DeviceUtil;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer, DrmSessionManager.DrmSessionReference, MediaCodecSelector, h, DataCollectionDelegate.OnDataCompletionListener, IThumbnailLoader, ObjectCaptureDrawHelper.OnStartDragListener, SearchIndexListener, TextExtractionDrawHelper.ImageTranslationResultCallback, DefaultExtractorsFactory.ExtensionLoader.ConstructorSupplier, ListViewHolder.OnItemLongClickListener, WidthAnimator.WidthAnimationCallback, PropertyAnimator.PropertyAnimationListener, DownloadCanceller, Id3Decoder.FramePredicate, FaultBarrier.ThrowableSupplier, IThemeColor {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((DrmSessionEventListener.EventDispatcher) obj).drmKeysLoaded();
                return;
            case 1:
                ((DrmSessionEventListener.EventDispatcher) obj).drmKeysRestored();
                return;
            default:
                ((ExecutorService) obj).shutdown();
                return;
        }
    }

    public Object apply(Object obj) {
        switch (this.d) {
            case 5:
                return ((Extractor) obj).getUnderlyingImplementation().getClass().getSimpleName();
            default:
                return Integer.valueOf(((TrackGroup) obj).type);
        }
    }

    public boolean evaluate(int i2, int i7, int i8, int i10, int i11) {
        return Id3Decoder.lambda$static$0(i2, i7, i8, i10, i11);
    }

    public Object get() {
        switch (this.d) {
            case 22:
                return DeviceUtil.getSystemProperties("ril.product_code", "");
            case 23:
                return BluetoothAdapter.getDefaultAdapter().getName();
            case 24:
                return DeviceUtil.lambda$getOneUiVersionValue$1();
            case 25:
                return DeviceUtil.lambda$getCsc$9();
            case 26:
                return DeviceUtil.lambda$getIso3CountryCode$8();
            default:
                return DeviceUtil.lambda$getOneUiVersion$2();
        }
    }

    public Constructor getConstructor() {
        switch (this.d) {
            case 14:
                return DefaultExtractorsFactory.getFlacExtractorConstructor();
            default:
                return DefaultExtractorsFactory.getMidiExtractorConstructor();
        }
    }

    public List getDecoderInfos(String str, boolean z, boolean z3) {
        switch (this.d) {
            case 3:
                return MediaCodecUtil.getDecoderInfos(str, z, z3);
            default:
                return MediaCodecUtil.getDecoderInfosSortedBySoftwareOnly(MediaCodecSelector.DEFAULT.getDecoderInfos(str, z, z3));
        }
    }

    public Bitmap getThumbnail(MediaItem mediaItem) {
        switch (this.d) {
            case 8:
                return ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.MEDIUM_KIND);
            default:
                return ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.MEDIUM_KIND);
        }
    }

    public boolean isWidthAnimationNeeded(View view, int i2) {
        return SharingPinchAnimationManager.lambda$prepareDividerViewHolder$0(view, i2);
    }

    public void onAnimationEnd(View view) {
        switch (this.d) {
            case 18:
                SharingPinchAnimationManager.lambda$prepareDividerViewHolder$1(view);
                return;
            default:
                SharingPinchAnimationManager.lambda$prepareTitleAnimation$3(view);
                return;
        }
    }

    public void onCancel(Context context) {
        CloudStore.API.cancelDownloadOriginalFile(context);
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        SesPickerDelegate.onConfirmed(eventContext, arrayList);
    }

    public void onIndexComplete(int i2) {
        switch (this.d) {
            case 12:
                FixDateTimeCmd.lambda$new$16(i2);
                return;
            default:
                CreatureIndexingDelegate.lambda$new$0(i2);
                return;
        }
    }

    public boolean onItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        return RemasterItemListAdapter.lambda$onBindViewHolder$0(listViewHolder, i2, mediaItem, i7);
    }

    public ClipData onStarDrag() {
        return ObjectCaptureHelper.lambda$clearCaptureDrawHelper$0();
    }

    public void release() {
        DrmSessionManager.DrmSessionReference.lambda$static$0();
    }
}
