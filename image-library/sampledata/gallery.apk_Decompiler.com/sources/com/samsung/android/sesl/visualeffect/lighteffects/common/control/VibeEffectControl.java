package com.samsung.android.sesl.visualeffect.lighteffects.common.control;

import android.view.View;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffect;
import com.samsung.android.sesl.visualeffect.utils.WeakViewArrayList;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R$\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/common/control/VibeEffectControl;", "", "Lcom/samsung/android/sesl/visualeffect/utils/WeakViewArrayList;", "getViews", "()Lcom/samsung/android/sesl/visualeffect/utils/WeakViewArrayList;", "views", "Ljava/util/ArrayList;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffect;", "Lkotlin/collections/ArrayList;", "getVibeRenderEffect", "()Ljava/util/ArrayList;", "vibeRenderEffect", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface VibeEffectControl {

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DefaultImpls {
        public static void add(VibeEffectControl vibeEffectControl, View view) {
            j.e(view, "view");
            vibeEffectControl.getViews().add(view);
            for (VibeRenderEffect add : vibeEffectControl.getVibeRenderEffect()) {
                add.add(view);
            }
        }

        public static void remove(VibeEffectControl vibeEffectControl, View view) {
            j.e(view, "view");
            vibeEffectControl.getViews().remove(view);
            for (VibeRenderEffect remove : vibeEffectControl.getVibeRenderEffect()) {
                remove.remove(view);
            }
        }
    }

    ArrayList<VibeRenderEffect> getVibeRenderEffect();

    WeakViewArrayList getViews();
}
