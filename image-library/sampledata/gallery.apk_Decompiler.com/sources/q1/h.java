package q1;

import c0.C0086a;
import com.google.android.flexbox.FlexboxLayoutManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    public int f1866a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f1867c;
    public int d = 0;
    public boolean e;
    public boolean f;
    public boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ FlexboxLayoutManager f1868h;

    public h(FlexboxLayoutManager flexboxLayoutManager) {
        this.f1868h = flexboxLayoutManager;
    }

    public static void a(h hVar) {
        int i2;
        int i7;
        FlexboxLayoutManager flexboxLayoutManager = hVar.f1868h;
        if (flexboxLayoutManager.isMainAxisDirectionHorizontal() || !flexboxLayoutManager.mIsRtl) {
            if (hVar.e) {
                i2 = flexboxLayoutManager.mOrientationHelper.getEndAfterPadding();
            } else {
                i2 = flexboxLayoutManager.mOrientationHelper.getStartAfterPadding();
            }
            hVar.f1867c = i2;
            return;
        }
        if (hVar.e) {
            i7 = flexboxLayoutManager.mOrientationHelper.getEndAfterPadding();
        } else {
            i7 = flexboxLayoutManager.getWidth() - flexboxLayoutManager.mOrientationHelper.getStartAfterPadding();
        }
        hVar.f1867c = i7;
    }

    public static void b(h hVar) {
        hVar.f1866a = -1;
        hVar.b = -1;
        hVar.f1867c = Integer.MIN_VALUE;
        boolean z = false;
        hVar.f = false;
        hVar.g = false;
        FlexboxLayoutManager flexboxLayoutManager = hVar.f1868h;
        if (flexboxLayoutManager.isMainAxisDirectionHorizontal()) {
            if (flexboxLayoutManager.mFlexWrap == 0) {
                if (flexboxLayoutManager.mFlexDirection == 1) {
                    z = true;
                }
                hVar.e = z;
                return;
            }
            if (flexboxLayoutManager.mFlexWrap == 2) {
                z = true;
            }
            hVar.e = z;
        } else if (flexboxLayoutManager.mFlexWrap == 0) {
            if (flexboxLayoutManager.mFlexDirection == 3) {
                z = true;
            }
            hVar.e = z;
        } else {
            if (flexboxLayoutManager.mFlexWrap == 2) {
                z = true;
            }
            hVar.e = z;
        }
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("AnchorInfo{mPosition=");
        sb2.append(this.f1866a);
        sb2.append(", mFlexLinePosition=");
        sb2.append(this.b);
        sb2.append(", mCoordinate=");
        sb2.append(this.f1867c);
        sb2.append(", mPerpendicularCoordinate=");
        sb2.append(this.d);
        sb2.append(", mLayoutFromEnd=");
        sb2.append(this.e);
        sb2.append(", mValid=");
        sb2.append(this.f);
        sb2.append(", mAssignedFromSavedState=");
        return C0086a.n(sb2, this.g, '}');
    }
}
