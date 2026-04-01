package androidx.appcompat.oneui.common.internal.resource;

import N2.j;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0017¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u000fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0017\u0010\u000f¨\u0006\u0018"}, d2 = {"Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColor;", "Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceImpl;", "", "resourceLight", "resourceDark", "<init>", "(II)V", "Landroid/content/Context;", "context", "getResource", "(Landroid/content/Context;)Ljava/lang/Integer;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getResourceLight", "getResourceDark", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ThemeResourceColor extends ThemeResourceImpl<Integer> {
    private final int resourceDark;
    private final int resourceLight;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ThemeResourceColor(int i2, int i7, int i8, e eVar) {
        this(i2, (i8 & 2) != 0 ? i2 : i7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ThemeResourceColor)) {
            return false;
        }
        ThemeResourceColor themeResourceColor = (ThemeResourceColor) obj;
        if (this.resourceLight == themeResourceColor.resourceLight && this.resourceDark == themeResourceColor.resourceDark) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Integer.hashCode(this.resourceDark) + (Integer.hashCode(this.resourceLight) * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ThemeResourceColor(resourceLight=");
        sb2.append(this.resourceLight);
        sb2.append(", resourceDark=");
        return j.e(sb2, this.resourceDark, ')');
    }

    public ThemeResourceColor(int i2, int i7) {
        super(Integer.valueOf(i2), Integer.valueOf(i7));
        this.resourceLight = i2;
        this.resourceDark = i7;
    }

    public Integer getResource(Context context) {
        kotlin.jvm.internal.j.e(context, "context");
        return (Integer) super.getResource(context);
    }
}
