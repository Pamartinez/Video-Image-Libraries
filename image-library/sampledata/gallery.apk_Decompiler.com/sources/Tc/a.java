package Tc;

import android.util.Pair;
import androidx.work.impl.Processor;
import com.samsung.android.sdk.scs.ai.text.category.DocumentCategoryClassifier;
import com.samsung.android.sdk.scs.ai.text.entity.BasicEntityExtractor;
import com.samsung.android.sdk.scs.ai.text.phrase.KeyPhraseExtractor;
import com.samsung.android.sdk.scs.ai.text.reminder.ReminderEntityExtractor;
import com.samsung.android.sum.core.channel.ReceiveChannelRouter;
import com.samsung.android.sum.core.filter.MediaMuxerFilter;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.message.ResponseHolder;
import com.samsung.android.sum.core.service.LocalServiceProxy;
import com.samsung.android.sum.core.service.RemoteServiceProxy;
import com.samsung.android.sum.core.types.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f834a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f835c;
    public final /* synthetic */ Object d;

    public /* synthetic */ a(Object obj, Serializable serializable, String str, int i2) {
        this.f834a = i2;
        this.f835c = obj;
        this.d = serializable;
        this.b = str;
    }

    public final Object call() {
        switch (this.f834a) {
            case 0:
                return ((BasicEntityExtractor) this.f835c).lambda$isSupported$0((String) this.b, (String) this.d);
            case 1:
                return ((DocumentCategoryClassifier) this.f835c).lambda$classifySynchronously$2((String) this.b, (DocumentCategoryClassifier.ClassifyOptions) this.d);
            case 2:
                return ((DocumentCategoryClassifier) this.f835c).lambda$getCategoryInfo$1((DocumentCategoryClassifier.RequestType) this.d, (String) this.b);
            case 3:
                return ((KeyPhraseExtractor) this.f835c).lambda$isSupported$0((KeyPhraseExtractor.RequestType) this.d, (String) this.b);
            case 4:
                return ((ReminderEntityExtractor) this.f835c).lambda$requestExtract$1((String) this.b, (String) this.d);
            case 5:
                return ((ReceiveChannelRouter) this.f835c).lambda$receiveAny$1((Integer) this.b, (BlockingQueue) this.d);
            case 6:
                return ((MediaMuxerFilter) this.f835c).lambda$run$13((Pair) this.b, (MediaType) this.d);
            case 7:
                return ((LocalServiceProxy) this.f835c).lambda$request$2((Request) this.b, (ResponseHolder) this.d);
            case 8:
                return ((RemoteServiceProxy) this.f835c).lambda$requestThenAwait$3((Request) this.b, (ResponseHolder) this.d);
            default:
                return ((Processor) this.f835c).lambda$startWork$0((ArrayList) this.d, (String) this.b);
        }
    }

    public /* synthetic */ a(Object obj, Object obj2, Object obj3, int i2) {
        this.f834a = i2;
        this.f835c = obj;
        this.b = obj2;
        this.d = obj3;
    }
}
