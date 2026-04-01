package W;

import E2.h;
import a3.b;
import a3.c;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.util.Supplier;
import androidx.media3.common.FlagSet;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import androidx.media3.extractor.mp3.Mp3Extractor;
import androidx.media3.extractor.mp4.Mp4Extractor;
import androidx.media3.extractor.mp4.Track;
import androidx.media3.transformer.DefaultDecoderFactory;
import androidx.media3.transformer.EncoderSelector;
import androidx.media3.transformer.Transformer;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandFloatingFragment;
import com.samsung.android.gallery.module.commandline.ops.Command;
import com.samsung.android.gallery.module.commandline.ops.CommandOperator;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterrupter;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.listview.InterceptTouchListener;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutorFactory;
import com.samsung.android.sdk.scs.base.tasks.OnCompleteListener;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDetector;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDewarper;
import com.samsung.android.vexfwk.sdk.docscan.VexFwkDocRefiner;
import com.samsung.android.visual.ai.sdkcommon.q;
import com.samsung.android.visual.ai.sdkcommon.r;
import com.samsung.scsp.common.ContextFactory;
import com.samsung.scsp.error.FaultBarrier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Id3Decoder.FramePredicate, h, OnCompleteListener, InterceptTouchListener, DefaultDecoderFactory.Listener, EncoderSelector, ListenerSet.IterationFinishedEvent, MediaScannerListener, ObjectConstructor, CommandOperator, ThumbnailInterrupter, AiServiceExecutorFactory.ServiceStubFactory, Supplier, FaultBarrier.ThrowableSupplier, WidthAnimator.WidthAnimationCallback {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public Object apply(Object obj) {
        switch (this.d) {
            case 1:
                return Mp4Extractor.lambda$processMoovAtom$2((Track) obj);
            default:
                return ((Cue) obj).toSerializableBundle();
        }
    }

    public Object construct() {
        switch (this.d) {
            case 9:
                return ConstructorConstructor.lambda$newMapConstructor$14();
            case 10:
                return ConstructorConstructor.lambda$newMapConstructor$15();
            case 11:
                return ConstructorConstructor.lambda$newMapConstructor$16();
            case 12:
                return ConstructorConstructor.lambda$newMapConstructor$17();
            case 13:
                return ConstructorConstructor.lambda$newMapConstructor$18();
            case 14:
                return ConstructorConstructor.lambda$newCollectionConstructor$10();
            case 15:
                return ConstructorConstructor.lambda$newCollectionConstructor$11();
            case 16:
                return ConstructorConstructor.lambda$newCollectionConstructor$12();
            default:
                return ConstructorConstructor.lambda$newCollectionConstructor$13();
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [a3.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v5, types: [com.samsung.android.visual.ai.sdkcommon.p, java.lang.Object] */
    public Object createStub(IBinder iBinder) {
        switch (this.d) {
            case 21:
                int i2 = b.f977a;
                if (iBinder == null) {
                    return null;
                }
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.aicore.sdkcommon.IOnDeviceService");
                if (queryLocalInterface != null && (queryLocalInterface instanceof c)) {
                    return (c) queryLocalInterface;
                }
                ? obj = new Object();
                obj.f976a = iBinder;
                return obj;
            default:
                int i7 = q.f4191a;
                if (iBinder == null) {
                    return null;
                }
                IInterface queryLocalInterface2 = iBinder.queryLocalInterface("com.samsung.android.visual.ai.sdkcommon.IImageEditorService");
                if (queryLocalInterface2 != null && (queryLocalInterface2 instanceof r)) {
                    return (r) queryLocalInterface2;
                }
                ? obj2 = new Object();
                obj2.f4190a = iBinder;
                return obj2;
        }
    }

    public boolean evaluate(int i2, int i7, int i8, int i10, int i11) {
        return Mp3Extractor.lambda$static$1(i2, i7, i8, i10, i11);
    }

    public Object get() {
        switch (this.d) {
            case 23:
                return VexFwkSdkBase.Companion.isAvailable$lambda$0();
            case 24:
                return VexFwkDocDetector.supportedOperationTypes_delegate$lambda$14$lambda$13();
            case 25:
                return VexFwkDocDewarper.dewarpDocumentImpl$lambda$12$lambda$7$lambda$6$lambda$5();
            case 26:
                return VexFwkDocRefiner.supportedModes_delegate$lambda$24$lambda$23();
            case 27:
                return VexFwkDocRefiner.Companion.capabilities$lambda$1$lambda$0();
            default:
                return ContextFactory.getApplicationContext().getPackageManager().getPackageInfo(ContextFactory.getApplicationContext().getPackageName(), 0).versionName;
        }
    }

    public void invoke(Object obj, FlagSet flagSet) {
        Transformer.Builder.lambda$new$0((Transformer.Listener) obj, flagSet);
    }

    public boolean isInterrupted() {
        return ReqInfo.lambda$addExtraListener$0();
    }

    public boolean isWidthAnimationNeeded(View view, int i2) {
        return PicturesPinchAnimationManager.lambda$createTitleViewAnim$7(view, i2);
    }

    public void onComplete(Task task) {
        Log.s("NeuralTranslator", "refreshed");
    }

    public void onCompleted() {
        switch (this.d) {
            case 8:
                Log.d("MotionPhotoClipService", "scan done");
                return;
            default:
                Log.d("CollageSaveHelper", "scan finished");
                return;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return OnDemandFloatingFragment.lambda$setClearViewMode$1(motionEvent);
    }

    public Bundle operate(Command command, Context context, String[] strArr) {
        return Command.lambda$static$0(command, context, strArr);
    }
}
