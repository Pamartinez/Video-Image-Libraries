package com.samsung.android.vexfwk.sdk.common;

import android.view.Surface;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture;
import com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import i.C0212a;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i implements VexFwkHelperConfigurationFuture.Stream {

    /* renamed from: i  reason: collision with root package name */
    public static final VexFwkHelperConfigurationFuture$SurfaceStream$Companion f4156i = new VexFwkHelperConfigurationFuture$SurfaceStream$Companion((e) null);

    /* renamed from: a  reason: collision with root package name */
    public final int f4157a;
    public final VexFwkStreamInoutDirection b;

    /* renamed from: c  reason: collision with root package name */
    public final VexFwkStreamType f4158c;
    public final VexFwkStreamUsage d;
    public final int e;
    public final int f;
    public final int g;

    /* renamed from: h  reason: collision with root package name */
    public final Surface f4159h;

    public i(int i2, VexFwkStreamInoutDirection vexFwkStreamInoutDirection, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage, int i7, int i8, int i10, Surface surface) {
        this.f4157a = i2;
        this.b = vexFwkStreamInoutDirection;
        this.f4158c = vexFwkStreamType;
        this.d = vexFwkStreamUsage;
        this.e = i7;
        this.f = i8;
        this.g = i10;
        this.f4159h = surface;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        if (this.f4157a == iVar.f4157a && this.b == iVar.b && this.f4158c == iVar.f4158c && this.d == iVar.d && this.e == iVar.e && this.f == iVar.f && this.g == iVar.g && j.a(this.f4159h, iVar.f4159h)) {
            return true;
        }
        return false;
    }

    public final VexFwkStreamInoutDirection getDirection() {
        return this.b;
    }

    public final int getFormat() {
        return this.g;
    }

    public final int getHeight() {
        return this.f;
    }

    public final int getId() {
        return this.f4157a;
    }

    public final Surface getSurface() {
        return this.f4159h;
    }

    public final VexFwkStreamType getType() {
        return this.f4158c;
    }

    public final VexFwkStreamUsage getUsage() {
        return this.d;
    }

    public final int getWidth() {
        return this.e;
    }

    public final int hashCode() {
        int i2;
        int hashCode = this.b.hashCode();
        int hashCode2 = this.f4158c.hashCode();
        int hashCode3 = this.d.hashCode();
        int b5 = C0212a.b(this.g, C0212a.b(this.f, C0212a.b(this.e, (hashCode3 + ((hashCode2 + ((hashCode + (Integer.hashCode(this.f4157a) * 31)) * 31)) * 31)) * 31, 31), 31), 31);
        Surface surface = this.f4159h;
        if (surface == null) {
            i2 = 0;
        } else {
            i2 = surface.hashCode();
        }
        return b5 + i2;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("SurfaceStream(id=");
        sb2.append(this.f4157a);
        sb2.append(", direction=");
        sb2.append(this.b);
        sb2.append(", type=");
        sb2.append(this.f4158c);
        sb2.append(", usage=");
        sb2.append(this.d);
        sb2.append(", width=");
        N2.j.x(sb2, this.e, ", height=", this.f, ", format=");
        sb2.append(this.g);
        sb2.append(", surface=");
        sb2.append(this.f4159h);
        sb2.append(")");
        return sb2.toString();
    }
}
