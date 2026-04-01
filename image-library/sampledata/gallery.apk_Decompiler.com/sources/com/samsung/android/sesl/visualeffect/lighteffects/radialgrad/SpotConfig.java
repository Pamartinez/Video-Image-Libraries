package com.samsung.android.sesl.visualeffect.lighteffects.radialgrad;

import android.graphics.PointF;
import com.samsung.android.sum.core.message.Message;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0017\b\b\u0018\u0000 $2\u00020\u0001:\u0001$B7\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0014\u001a\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001a\u0010\u0012R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010!\u001a\u0004\b\"\u0010#¨\u0006%"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/SpotConfig;", "", "", "enabled", "", "color", "Landroid/graphics/PointF;", "position", "", "scale", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/WiggleAnimationConfig;", "wiggle", "<init>", "(ZILandroid/graphics/PointF;FLcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/WiggleAnimationConfig;)V", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "getEnabled", "()Z", "I", "getColor", "Landroid/graphics/PointF;", "getPosition", "()Landroid/graphics/PointF;", "F", "getScale", "()F", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/WiggleAnimationConfig;", "getWiggle", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/WiggleAnimationConfig;", "Companion", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SpotConfig {
    public static final Companion Companion = new Companion((e) null);
    private final int color;
    private final boolean enabled;
    private final PointF position;
    private final float scale;
    private final WiggleAnimationConfig wiggle;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/SpotConfig$Companion;", "", "<init>", "()V", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public SpotConfig(boolean z, int i2, PointF pointF, float f, WiggleAnimationConfig wiggleAnimationConfig) {
        j.e(pointF, Message.KEY_POSITION);
        this.enabled = z;
        this.color = i2;
        this.position = pointF;
        this.scale = f;
        this.wiggle = wiggleAnimationConfig;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SpotConfig)) {
            return false;
        }
        SpotConfig spotConfig = (SpotConfig) obj;
        if (this.enabled == spotConfig.enabled && this.color == spotConfig.color && j.a(this.position, spotConfig.position) && Float.compare(this.scale, spotConfig.scale) == 0 && j.a(this.wiggle, spotConfig.wiggle)) {
            return true;
        }
        return false;
    }

    public final int getColor() {
        return this.color;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final PointF getPosition() {
        return this.position;
    }

    public final float getScale() {
        return this.scale;
    }

    public final WiggleAnimationConfig getWiggle() {
        return this.wiggle;
    }

    public int hashCode() {
        int i2;
        int b = C0212a.b(this.color, Boolean.hashCode(this.enabled) * 31, 31);
        int a7 = N2.j.a(this.scale, (this.position.hashCode() + b) * 31, 31);
        WiggleAnimationConfig wiggleAnimationConfig = this.wiggle;
        if (wiggleAnimationConfig == null) {
            i2 = 0;
        } else {
            i2 = wiggleAnimationConfig.hashCode();
        }
        return a7 + i2;
    }

    public String toString() {
        boolean z = this.enabled;
        int i2 = this.color;
        PointF pointF = this.position;
        float f = this.scale;
        WiggleAnimationConfig wiggleAnimationConfig = this.wiggle;
        return "SpotConfig(enabled=" + z + ", color=" + i2 + ", position=" + pointF + ", scale=" + f + ", wiggle=" + wiggleAnimationConfig + ")";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SpotConfig(boolean z, int i2, PointF pointF, float f, WiggleAnimationConfig wiggleAnimationConfig, int i7, e eVar) {
        this((i7 & 1) != 0 ? true : z, i2, pointF, f, (i7 & 16) != 0 ? null : wiggleAnimationConfig);
    }
}
