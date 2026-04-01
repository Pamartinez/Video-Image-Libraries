package W9;

import Ae.c;
import Vf.A;
import android.os.Bundle;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.sdk.moneta.memory.service.MemorySearchService;
import java.util.ArrayList;
import qe.C1227c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements c {
    public final /* synthetic */ PersonalDataCore d;
    public final /* synthetic */ MemorySearchService e;
    public final /* synthetic */ Bundle f;
    public final /* synthetic */ ArrayList g;

    public /* synthetic */ e(PersonalDataCore personalDataCore, MemorySearchService memorySearchService, Bundle bundle, ArrayList arrayList) {
        this.d = personalDataCore;
        this.e = memorySearchService;
        this.f = bundle;
        this.g = arrayList;
    }

    public final Object invoke(Object obj, Object obj2) {
        return this.d.lambda$getRecommendMap$11(this.e, this.f, this.g, (A) obj, (C1227c) obj2);
    }
}
