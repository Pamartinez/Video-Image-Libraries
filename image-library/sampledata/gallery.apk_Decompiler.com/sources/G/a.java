package G;

import android.content.Context;
import android.net.Uri;
import androidx.media3.datasource.DataSourceBitmapLoader;
import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.sdk.scs.ai.text.bnlp.BasicNlpAnalyzer;
import com.samsung.android.sdk.scs.ai.text.event.EventCategoryClassifier;
import com.samsung.android.sdk.scs.ai.text.event.EventExtractor;
import com.samsung.android.sdk.scs.ai.text.language.LanguageDetector;
import com.samsung.android.sdk.scs.ai.text.reminder.ReminderEntityExtractor;
import com.samsung.android.sdk.scs.ai.text.unit.UnitConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import x0.n;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f279a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f280c;

    public /* synthetic */ a(int i2, Object obj, Object obj2) {
        this.f279a = i2;
        this.b = obj;
        this.f280c = obj2;
    }

    public final Object call() {
        int i2 = this.f279a;
        Object obj = this.f280c;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((DataSourceBitmapLoader) obj2).lambda$loadBitmap$2((Uri) obj);
            case 1:
                return ((LanguageDetector) obj2).lambda$detect$0((String) obj);
            case 2:
                return ((UnitConverter) obj2).lambda$getBundleFromInput$0((String) obj);
            case 3:
                return ((BasicNlpAnalyzer) obj2).lambda$isSupportedLanguage$0((String) obj);
            case 4:
                return ((EventCategoryClassifier) obj2).lambda$classify$0((ArrayList) obj);
            case 5:
                return ((EventExtractor) obj2).lambda$isSupported$0((String) obj);
            case 6:
                return ((ReminderEntityExtractor) obj2).lambda$isSupported$0((String) obj);
            default:
                LottieAnimationView lottieAnimationView = (LottieAnimationView) obj2;
                String str = (String) obj;
                if (!lottieAnimationView.m) {
                    return n.b(lottieAnimationView.getContext(), str, (String) null);
                }
                Context context = lottieAnimationView.getContext();
                HashMap hashMap = n.f2068a;
                return n.b(context, str, "asset_" + str);
        }
    }
}
