package com.samsung.android.gallery.settings.ui;

import com.samsung.android.gallery.settings.ui.LabsFragment;
import com.samsung.android.gallery.widget.dialog.ProgressDialogCompat;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class B implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LabsFragment.DebugDumpWorker f3113a;

    public /* synthetic */ B(LabsFragment.DebugDumpWorker debugDumpWorker) {
        this.f3113a = debugDumpWorker;
    }

    public final Object apply(Object obj) {
        return this.f3113a.lambda$onPreExecute$0((ProgressDialogCompat) obj);
    }
}
