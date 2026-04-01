package androidx.work.impl.constraints;

import Vf.C;
import Vf.C0867e0;
import Vf.C0886x;
import Vf.D;
import android.content.Context;
import android.net.ConnectivityManager;
import androidx.work.Logger;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import qe.C1227c;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a)\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\t\u001a\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\r\u0010\u000e\"\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011*$\b\u0002\u0010\u0015\"\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00122\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012¨\u0006\u0016"}, d2 = {"Landroidx/work/impl/constraints/WorkConstraintsTracker;", "Landroidx/work/impl/model/WorkSpec;", "spec", "LVf/x;", "dispatcher", "Landroidx/work/impl/constraints/OnConstraintsStateChangedListener;", "listener", "LVf/e0;", "listen", "(Landroidx/work/impl/constraints/WorkConstraintsTracker;Landroidx/work/impl/model/WorkSpec;LVf/x;Landroidx/work/impl/constraints/OnConstraintsStateChangedListener;)LVf/e0;", "Landroid/content/Context;", "context", "Landroidx/work/impl/constraints/NetworkRequestConstraintController;", "NetworkRequestConstraintController", "(Landroid/content/Context;)Landroidx/work/impl/constraints/NetworkRequestConstraintController;", "", "TAG", "Ljava/lang/String;", "Lkotlin/Function1;", "Landroidx/work/impl/constraints/ConstraintsState;", "Lme/x;", "OnConstraintState", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WorkConstraintsTrackerKt {
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("WorkConstraintsTracker");
        j.d(tagWithPrefix, "tagWithPrefix(\"WorkConstraintsTracker\")");
        TAG = tagWithPrefix;
    }

    public static final NetworkRequestConstraintController NetworkRequestConstraintController(Context context) {
        j.e(context, "context");
        Object systemService = context.getSystemService("connectivity");
        j.c(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        return new NetworkRequestConstraintController((ConnectivityManager) systemService, 0, 2, (e) null);
    }

    public static final C0867e0 listen(WorkConstraintsTracker workConstraintsTracker, WorkSpec workSpec, C0886x xVar, OnConstraintsStateChangedListener onConstraintsStateChangedListener) {
        j.e(workConstraintsTracker, "<this>");
        j.e(workSpec, "spec");
        j.e(xVar, "dispatcher");
        j.e(onConstraintsStateChangedListener, "listener");
        return D.n(D.a(xVar), (C0886x) null, (C) null, new WorkConstraintsTrackerKt$listen$1(workConstraintsTracker, workSpec, onConstraintsStateChangedListener, (C1227c) null), 3);
    }
}
