package com.samsung.android.sesl.visualeffect.lighteffects.common.config;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u0003R:\u0010\r\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u000bj\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004`\f8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/common/config/BaseVibeEffectConfig;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/config/VibeEffectConfig;", "<init>", "()V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/config/AnimatableAttribute;", "attr", "", "add", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/common/config/AnimatableAttribute;)Z", "Lme/x;", "destroy", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "animatableAttribute", "Ljava/util/ArrayList;", "getAnimatableAttribute$sesl_visualeffect_INTERNAL_2_1_6_release", "()Ljava/util/ArrayList;", "setAnimatableAttribute$sesl_visualeffect_INTERNAL_2_1_6_release", "(Ljava/util/ArrayList;)V", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseVibeEffectConfig implements VibeEffectConfig {
    private ArrayList<AnimatableAttribute<?>> animatableAttribute = new ArrayList<>();

    public final boolean add(AnimatableAttribute<?> animatableAttribute2) {
        j.e(animatableAttribute2, "attr");
        return this.animatableAttribute.add(animatableAttribute2);
    }

    public final void destroy() {
        this.animatableAttribute.clear();
    }

    public final ArrayList<AnimatableAttribute<?>> getAnimatableAttribute$sesl_visualeffect_INTERNAL_2_1_6_release() {
        return this.animatableAttribute;
    }
}
