package com.samsung.android.gallery.settings.ui;

import android.os.Bundle;
import com.samsung.android.gallery.settings.ui.FixFaceTable;
import com.samsung.android.gallery.support.search.LlmQpOperation;
import java.util.function.Function;

/* renamed from: com.samsung.android.gallery.settings.ui.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0648c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3116a;

    public /* synthetic */ C0648c(int i2) {
        this.f3116a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f3116a) {
            case 0:
                return FixFaceTable.lambda$showSelectBucketDlg$2((FixFaceTable.WrongFaceData) obj);
            case 1:
                return String.valueOf(((Bundle) obj).getInt("content_sync_version", 0));
            default:
                return ((LlmQpOperation) obj).getValue();
        }
    }
}
