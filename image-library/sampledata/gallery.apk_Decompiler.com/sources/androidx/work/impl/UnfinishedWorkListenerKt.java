package androidx.work.impl;

import Ae.e;
import Vf.A;
import Vf.C;
import Vf.C0886x;
import Vf.D;
import Xf.a;
import Yf.f;
import Yf.g;
import Yf.n;
import Zf.c;
import android.content.Context;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.utils.ProcessUtils;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import qe.C1227c;
import qe.C1233i;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u001a+\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\b\u0010\t\"\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f\"\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"LVf/A;", "Landroid/content/Context;", "appContext", "Landroidx/work/Configuration;", "configuration", "Landroidx/work/impl/WorkDatabase;", "db", "Lme/x;", "maybeLaunchUnfinishedWorkListener", "(LVf/A;Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/WorkDatabase;)V", "", "TAG", "Ljava/lang/String;", "", "MAX_DELAY_MS", "J", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class UnfinishedWorkListenerKt {
    /* access modifiers changed from: private */
    public static final long MAX_DELAY_MS = TimeUnit.HOURS.toMillis(1);
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("UnfinishedWorkListener");
        j.d(tagWithPrefix, "tagWithPrefix(\"UnfinishedWorkListener\")");
        TAG = tagWithPrefix;
    }

    public static final void maybeLaunchUnfinishedWorkListener(A a7, Context context, Configuration configuration, WorkDatabase workDatabase) {
        j.e(a7, "<this>");
        j.e(context, StateHandler.KEY_APP_STATE);
        j.e(configuration, "configuration");
        j.e(workDatabase, "db");
        if (ProcessUtils.isDefaultProcess(context, configuration)) {
            n nVar = new n(workDatabase.workSpecDao().hasUnfinishedWorkFlow(), (e) new UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1((C1227c) null));
            a aVar = a.SUSPEND;
            D.n(a7, (C0886x) null, (C) null, new Yf.j(new n((g) new f(new c(nVar, C1233i.d, 0, a.DROP_OLDEST)), (Ae.c) new UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2(context, (C1227c) null)), (C1227c) null), 3);
        }
    }
}
