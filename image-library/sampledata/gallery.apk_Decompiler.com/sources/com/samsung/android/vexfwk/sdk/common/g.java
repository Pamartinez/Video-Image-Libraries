package com.samsung.android.vexfwk.sdk.common;

import android.view.Surface;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture;
import com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import kotlin.jvm.internal.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements VexFwkHelperConfigurationFuture.Stream {
    public static final VexFwkHelperConfigurationFuture$BufferStream$Companion e = new VexFwkHelperConfigurationFuture$BufferStream$Companion((e) null);

    /* renamed from: a  reason: collision with root package name */
    public final int f4152a;
    public final VexFwkStreamInoutDirection b;

    /* renamed from: c  reason: collision with root package name */
    public final VexFwkStreamType f4153c;
    public final VexFwkStreamUsage d;

    public g(int i2, VexFwkStreamInoutDirection vexFwkStreamInoutDirection, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage) {
        this.f4152a = i2;
        this.b = vexFwkStreamInoutDirection;
        this.f4153c = vexFwkStreamType;
        this.d = vexFwkStreamUsage;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        if (this.f4152a == gVar.f4152a && this.b == gVar.b && this.f4153c == gVar.f4153c && this.d == gVar.d) {
            return true;
        }
        return false;
    }

    public final VexFwkStreamInoutDirection getDirection() {
        return this.b;
    }

    public final int getFormat() {
        return -1;
    }

    public final int getHeight() {
        return -1;
    }

    public final int getId() {
        return this.f4152a;
    }

    public final Surface getSurface() {
        return null;
    }

    public final VexFwkStreamType getType() {
        return this.f4153c;
    }

    public final VexFwkStreamUsage getUsage() {
        return this.d;
    }

    public final int getWidth() {
        return -1;
    }

    public final int hashCode() {
        int hashCode = this.b.hashCode();
        int hashCode2 = this.f4153c.hashCode();
        return this.d.hashCode() + ((hashCode2 + ((hashCode + (Integer.hashCode(this.f4152a) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "BufferStream(id=" + this.f4152a + ", direction=" + this.b + ", type=" + this.f4153c + ", usage=" + this.d + ")";
    }
}
