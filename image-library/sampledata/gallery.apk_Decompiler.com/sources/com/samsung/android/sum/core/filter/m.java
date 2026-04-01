package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.functional.ModelSelector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements ModelSelector {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ NNFWFilterGroup f4095a;

    public /* synthetic */ m(NNFWFilterGroup nNFWFilterGroup) {
        this.f4095a = nNFWFilterGroup;
    }

    public final ModelSelector.Item select(MediaBuffer mediaBuffer) {
        return this.f4095a.lambda$new$0(mediaBuffer);
    }
}
