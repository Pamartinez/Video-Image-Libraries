package com.samsung.android.gallery.app.ui.list.search;

import com.samsung.android.gallery.module.search.languagepack.NeuralTranslator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Runnable {
    public final /* synthetic */ int d;

    public /* synthetic */ p(int i2) {
        this.d = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                NeuralTranslator.getInstance().close();
                return;
            case 1:
                NeuralTranslator.getInstance().open();
                return;
            case 2:
                NeuralTranslator.getInstance().open();
                return;
            default:
                NeuralTranslator.getInstance().close();
                return;
        }
    }
}
