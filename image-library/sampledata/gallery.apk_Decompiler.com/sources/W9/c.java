package W9;

import Vf.A;
import android.content.Context;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import qe.C1227c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Ae.c {
    public final /* synthetic */ PersonalDataCore d;
    public final /* synthetic */ Context e;
    public final /* synthetic */ String f;

    public /* synthetic */ c(PersonalDataCore personalDataCore, Context context, String str) {
        this.d = personalDataCore;
        this.e = context;
        this.f = str;
    }

    public final Object invoke(Object obj, Object obj2) {
        return this.d.lambda$getCandidatePeopleBy$5(this.e, this.f, (A) obj, (C1227c) obj2);
    }
}
