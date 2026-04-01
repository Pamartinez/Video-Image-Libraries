package Tc;

import com.samsung.android.sdk.scs.ai.text.entity.BasicEntityExtractor;
import java.time.DayOfWeek;
import java.util.Set;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BasicEntityExtractor f836a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f837c;
    public final /* synthetic */ String d;
    public final /* synthetic */ Set e;
    public final /* synthetic */ long f;
    public final /* synthetic */ BasicEntityExtractor.Hemisphere g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ DayOfWeek f838h;

    public /* synthetic */ b(BasicEntityExtractor basicEntityExtractor, String str, String str2, String str3, Set set, long j2, BasicEntityExtractor.Hemisphere hemisphere, DayOfWeek dayOfWeek) {
        this.f836a = basicEntityExtractor;
        this.b = str;
        this.f837c = str2;
        this.d = str3;
        this.e = set;
        this.f = j2;
        this.g = hemisphere;
        this.f838h = dayOfWeek;
    }

    public final Object call() {
        return this.f836a.lambda$requestExtract$1(this.b, this.f837c, this.d, this.e, this.f, this.g, this.f838h);
    }
}
