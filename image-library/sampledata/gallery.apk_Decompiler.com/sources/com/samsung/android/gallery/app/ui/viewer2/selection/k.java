package com.samsung.android.gallery.app.ui.viewer2.selection;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2613a;

    public /* synthetic */ k(int i2) {
        this.f2613a = i2;
    }

    public final Object apply(Object obj) {
        SelectionViewAdapter selectionViewAdapter = (SelectionViewAdapter) obj;
        switch (this.f2613a) {
            case 0:
                return selectionViewAdapter.getSelectedPositions();
            default:
                return selectionViewAdapter.getSelectedItems();
        }
    }
}
