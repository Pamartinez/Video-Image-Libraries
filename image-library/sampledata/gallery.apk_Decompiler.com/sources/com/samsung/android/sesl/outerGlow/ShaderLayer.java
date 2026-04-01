package com.samsung.android.sesl.outerGlow;

import c0.C0086a;
import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b)\b\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u0011J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010+\u001a\u00020\bHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010-\u001a\u00020\bHÆ\u0003J\t\u0010.\u001a\u00020\bHÆ\u0003J\t\u0010/\u001a\u00020\u000eHÆ\u0003J\t\u00100\u001a\u00020\u000eHÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\nHÆ\u0003Jm\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u00103\u001a\u00020\b2\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u00020\u0003HÖ\u0001J\t\u00106\u001a\u00020\nHÖ\u0001R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\f\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0017\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u000b\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0017\"\u0004\b\u001e\u0010\u001dR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0013\"\u0004\b \u0010\u0015R\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010\u000f\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\"\"\u0004\b&\u0010$R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u00067"}, d2 = {"Lcom/samsung/android/sesl/outerGlow/ShaderLayer;", "", "id", "", "uniforms", "", "Lcom/samsung/android/sesl/outerGlow/Uniform;", "enabled", "", "agslShaderCode", "", "isCanvasDraw", "isBlur", "radiusX", "", "radiusY", "name", "(ILjava/util/List;ZLjava/lang/String;ZZFFLjava/lang/String;)V", "getAgslShaderCode", "()Ljava/lang/String;", "setAgslShaderCode", "(Ljava/lang/String;)V", "getEnabled", "()Z", "getId", "()I", "setId", "(I)V", "setBlur", "(Z)V", "setCanvasDraw", "getName", "setName", "getRadiusX", "()F", "setRadiusX", "(F)V", "getRadiusY", "setRadiusY", "getUniforms", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "graphic-solution_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ShaderLayer {
    private String agslShaderCode;
    private final boolean enabled;
    private int id;
    private boolean isBlur;
    private boolean isCanvasDraw;
    private String name;
    private float radiusX;
    private float radiusY;
    private final List<Uniform> uniforms;

    public ShaderLayer(int i2, List<Uniform> list, boolean z, String str, boolean z3, boolean z7, float f, float f5, String str2) {
        j.e(list, "uniforms");
        this.id = i2;
        this.uniforms = list;
        this.enabled = z;
        this.agslShaderCode = str;
        this.isCanvasDraw = z3;
        this.isBlur = z7;
        this.radiusX = f;
        this.radiusY = f5;
        this.name = str2;
    }

    public static /* synthetic */ ShaderLayer copy$default(ShaderLayer shaderLayer, int i2, List<Uniform> list, boolean z, String str, boolean z3, boolean z7, float f, float f5, String str2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i2 = shaderLayer.id;
        }
        if ((i7 & 2) != 0) {
            list = shaderLayer.uniforms;
        }
        if ((i7 & 4) != 0) {
            z = shaderLayer.enabled;
        }
        if ((i7 & 8) != 0) {
            str = shaderLayer.agslShaderCode;
        }
        if ((i7 & 16) != 0) {
            z3 = shaderLayer.isCanvasDraw;
        }
        if ((i7 & 32) != 0) {
            z7 = shaderLayer.isBlur;
        }
        if ((i7 & 64) != 0) {
            f = shaderLayer.radiusX;
        }
        if ((i7 & 128) != 0) {
            f5 = shaderLayer.radiusY;
        }
        if ((i7 & 256) != 0) {
            str2 = shaderLayer.name;
        }
        float f8 = f5;
        String str3 = str2;
        boolean z9 = z7;
        float f10 = f;
        String str4 = str;
        boolean z10 = z3;
        return shaderLayer.copy(i2, list, z, str4, z10, z9, f10, f8, str3);
    }

    public final int component1() {
        return this.id;
    }

    public final List<Uniform> component2() {
        return this.uniforms;
    }

    public final boolean component3() {
        return this.enabled;
    }

    public final String component4() {
        return this.agslShaderCode;
    }

    public final boolean component5() {
        return this.isCanvasDraw;
    }

    public final boolean component6() {
        return this.isBlur;
    }

    public final float component7() {
        return this.radiusX;
    }

    public final float component8() {
        return this.radiusY;
    }

    public final String component9() {
        return this.name;
    }

    public final ShaderLayer copy(int i2, List<Uniform> list, boolean z, String str, boolean z3, boolean z7, float f, float f5, String str2) {
        j.e(list, "uniforms");
        return new ShaderLayer(i2, list, z, str, z3, z7, f, f5, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShaderLayer)) {
            return false;
        }
        ShaderLayer shaderLayer = (ShaderLayer) obj;
        if (this.id == shaderLayer.id && j.a(this.uniforms, shaderLayer.uniforms) && this.enabled == shaderLayer.enabled && j.a(this.agslShaderCode, shaderLayer.agslShaderCode) && this.isCanvasDraw == shaderLayer.isCanvasDraw && this.isBlur == shaderLayer.isBlur && Float.compare(this.radiusX, shaderLayer.radiusX) == 0 && Float.compare(this.radiusY, shaderLayer.radiusY) == 0 && j.a(this.name, shaderLayer.name)) {
            return true;
        }
        return false;
    }

    public final String getAgslShaderCode() {
        return this.agslShaderCode;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final float getRadiusX() {
        return this.radiusX;
    }

    public final float getRadiusY() {
        return this.radiusY;
    }

    public final List<Uniform> getUniforms() {
        return this.uniforms;
    }

    public int hashCode() {
        int i2;
        int e = C0212a.e(C0212a.f(this.uniforms, Integer.hashCode(this.id) * 31, 31), 31, this.enabled);
        String str = this.agslShaderCode;
        int i7 = 0;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        int a7 = N2.j.a(this.radiusY, N2.j.a(this.radiusX, C0212a.e(C0212a.e((e + i2) * 31, 31, this.isCanvasDraw), 31, this.isBlur), 31), 31);
        String str2 = this.name;
        if (str2 != null) {
            i7 = str2.hashCode();
        }
        return a7 + i7;
    }

    public final boolean isBlur() {
        return this.isBlur;
    }

    public final boolean isCanvasDraw() {
        return this.isCanvasDraw;
    }

    public final void setAgslShaderCode(String str) {
        this.agslShaderCode = str;
    }

    public final void setBlur(boolean z) {
        this.isBlur = z;
    }

    public final void setCanvasDraw(boolean z) {
        this.isCanvasDraw = z;
    }

    public final void setId(int i2) {
        this.id = i2;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setRadiusX(float f) {
        this.radiusX = f;
    }

    public final void setRadiusY(float f) {
        this.radiusY = f;
    }

    public String toString() {
        int i2 = this.id;
        List<Uniform> list = this.uniforms;
        boolean z = this.enabled;
        String str = this.agslShaderCode;
        boolean z3 = this.isCanvasDraw;
        boolean z7 = this.isBlur;
        float f = this.radiusX;
        float f5 = this.radiusY;
        String str2 = this.name;
        StringBuilder sb2 = new StringBuilder("ShaderLayer(id=");
        sb2.append(i2);
        sb2.append(", uniforms=");
        sb2.append(list);
        sb2.append(", enabled=");
        sb2.append(z);
        sb2.append(", agslShaderCode=");
        sb2.append(str);
        sb2.append(", isCanvasDraw=");
        sb2.append(z3);
        sb2.append(", isBlur=");
        sb2.append(z7);
        sb2.append(", radiusX=");
        C0086a.y(sb2, f, ", radiusY=", f5, ", name=");
        return C0212a.p(sb2, str2, ")");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ShaderLayer(int i2, List list, boolean z, String str, boolean z3, boolean z7, float f, float f5, String str2, int i7, e eVar) {
        this(i2, list, z, (i7 & 8) != 0 ? null : str, (i7 & 16) != 0 ? false : z3, (i7 & 32) != 0 ? false : z7, (i7 & 64) != 0 ? 0.0f : f, (i7 & 128) != 0 ? 0.0f : f5, (i7 & 256) != 0 ? null : str2);
    }
}
