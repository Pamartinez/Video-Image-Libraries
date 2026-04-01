package com.samsung.android.sum.core.functional;

import A4.Q;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import androidx.fragment.app.Fragment;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator;
import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegate;
import com.samsung.android.gallery.app.ui.abstraction.delegate.DelegateComposite;
import com.samsung.android.gallery.app.ui.container.phone.BottomTabFragment;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.ExportHandler;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.idleworker.IdleWorker;
import com.samsung.android.gallery.module.lottie.recap.data.DummyAnalyzer;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.thumbnail.VideoThumbnailLoader;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.SqliteCaseBuilder;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslationErrorCode;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.scsp.error.FaultBarrier;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ g(DummyAnalyzer dummyAnalyzer, HashMap hashMap, StringJoiner stringJoiner) {
        this.d = 11;
        this.e = dummyAnalyzer;
        this.g = hashMap;
        this.f = stringJoiner;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                OperatorMap.lambda$run$0((MutableMediaFormat) this.e, (MutableMediaBuffer) this.f, (HashMap) this.g, (Operator) obj);
                return;
            case 1:
                FaultBarrier.run(new Q((Object) (Supplier) this.e, (Object) (String[]) this.f, (Object) (Bundle) this.g, 16));
                return;
            case 2:
                PrivateDatabase.lambda$parseSqlSelection$3((String[]) this.e, (AtomicInteger) this.f, (HashMap) this.g, (String[]) obj);
                return;
            case 3:
                DelegateComposite.lambda$onViewCreatedInternal$3((View) this.e, (Bundle) this.f, (ArrayList) this.g, (AbsDelegate) obj);
                return;
            case 4:
                ((ExportHandler) this.e).lambda$moveToStorage$10((Consumer) this.f, (TimeTickLog) this.g, (MediaItem) obj);
                return;
            case 5:
                ((MediaPlayerViewImp) this.e).lambda$computeVideo$4((VideoReqInfo) this.f, (Consumer) this.g, (MediaHelper.VideoInfo) obj);
                return;
            case 6:
                ((CategoryFragment) this.e).lambda$adjustPickerViewAreaMargin$1((WindowInsets) this.f, (View) this.g, (WindowInsets) obj);
                return;
            case 7:
                ((BottomTabFragment) this.e).lambda$switchFragment$3((AtomicReference) this.f, (String) this.g, (Fragment) obj);
                return;
            case 8:
                ((SqliteCaseBuilder) this.e).whenThen(C0212a.B((String) this.f, "=", (String) obj), ((AtomicInteger) this.g).getAndIncrement());
                return;
            case 9:
                IdleWorker.lambda$startIdle$0((Context) this.e, (JobScheduler) this.f, (ArrayList) this.g, (IdleWorkerJob) obj);
                return;
            case 10:
                ((VideoThumbnailLoader) this.e).lambda$load$0((List) this.f, (byte[]) this.g, (Boolean) obj);
                return;
            case 11:
                ((DummyAnalyzer) this.e).lambda$createDataJson$3((HashMap) this.g, (StringJoiner) this.f, (MediaItem) obj);
                return;
            default:
                TextTranslator.translateText$lambda$5((StringBuilder) this.e, (String) this.f, (CountDownLatch) this.g, (NeuralTranslationErrorCode) obj);
                return;
        }
    }

    public /* synthetic */ g(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }
}
