package com.airbnb.lottie;

import C0.f;
import D0.e;
import D1.g;
import J0.b;
import J0.d;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.ByteArrayInputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import t0.a;
import x0.C0319A;
import x0.C0320B;
import x0.C0322D;
import x0.C0323a;
import x0.C0324b;
import x0.C0325c;
import x0.C0326d;
import x0.C0327e;
import x0.C0328f;
import x0.C0329g;
import x0.C0330h;
import x0.C0331i;
import x0.C0332j;
import x0.E;
import x0.F;
import x0.H;
import x0.I;
import x0.J;
import x0.k;
import x0.m;
import x0.n;
import x0.r;
import x0.v;
import x0.w;
import x0.x;
import x0.z;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LottieAnimationView extends AppCompatImageView {
    public static final C0327e q = new Object();
    public final C0331i d = new C0331i(this, 1);
    public final C0331i e = new C0331i(this, 0);
    public z f;
    public int g = 0;

    /* renamed from: h  reason: collision with root package name */
    public final w f1099h;

    /* renamed from: i  reason: collision with root package name */
    public String f1100i;

    /* renamed from: j  reason: collision with root package name */
    public int f1101j;
    public boolean k;
    public boolean l;
    public boolean m;
    public final HashSet n;

    /* renamed from: o  reason: collision with root package name */
    public final HashSet f1102o;

    /* renamed from: p  reason: collision with root package name */
    public C0322D f1103p;

    public LottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean z;
        String string;
        boolean z3 = true;
        w wVar = new w();
        this.f1099h = wVar;
        this.k = false;
        this.l = false;
        this.m = true;
        HashSet hashSet = new HashSet();
        this.n = hashSet;
        this.f1102o = new HashSet();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, F.f2047a, R.attr.lottieAnimationViewStyle, 0);
        this.m = obtainStyledAttributes.getBoolean(2, true);
        boolean hasValue = obtainStyledAttributes.hasValue(14);
        boolean hasValue2 = obtainStyledAttributes.hasValue(9);
        boolean hasValue3 = obtainStyledAttributes.hasValue(19);
        if (!hasValue || !hasValue2) {
            if (hasValue) {
                int resourceId = obtainStyledAttributes.getResourceId(14, 0);
                if (resourceId != 0) {
                    setAnimation(resourceId);
                }
            } else if (hasValue2) {
                String string2 = obtainStyledAttributes.getString(9);
                if (string2 != null) {
                    setAnimation(string2);
                }
            } else if (hasValue3 && (string = obtainStyledAttributes.getString(19)) != null) {
                setAnimationFromUrl(string);
            }
            setFallbackResource(obtainStyledAttributes.getResourceId(8, 0));
            if (obtainStyledAttributes.getBoolean(1, false)) {
                this.l = true;
            }
            if (obtainStyledAttributes.getBoolean(12, false)) {
                wVar.e.setRepeatCount(-1);
            }
            if (obtainStyledAttributes.hasValue(17)) {
                setRepeatMode(obtainStyledAttributes.getInt(17, 1));
            }
            if (obtainStyledAttributes.hasValue(16)) {
                setRepeatCount(obtainStyledAttributes.getInt(16, -1));
            }
            if (obtainStyledAttributes.hasValue(18)) {
                setSpeed(obtainStyledAttributes.getFloat(18, 1.0f));
            }
            if (obtainStyledAttributes.hasValue(4)) {
                setClipToCompositionBounds(obtainStyledAttributes.getBoolean(4, true));
            }
            if (obtainStyledAttributes.hasValue(3)) {
                setClipTextToBoundingBox(obtainStyledAttributes.getBoolean(3, false));
            }
            if (obtainStyledAttributes.hasValue(6)) {
                setDefaultFontFileExtension(obtainStyledAttributes.getString(6));
            }
            setImageAssetsFolder(obtainStyledAttributes.getString(11));
            boolean hasValue4 = obtainStyledAttributes.hasValue(13);
            float f5 = obtainStyledAttributes.getFloat(13, 0.0f);
            if (hasValue4) {
                hashSet.add(C0330h.SET_PROGRESS);
            }
            wVar.u(f5);
            boolean z7 = obtainStyledAttributes.getBoolean(7, false);
            x xVar = x.MergePathsApi19;
            HashSet hashSet2 = (HashSet) wVar.r.e;
            if (!z7) {
                z = hashSet2.remove(xVar);
            } else if (Build.VERSION.SDK_INT < xVar.minRequiredSdkVersion) {
                b.b(String.format("%s is not supported pre SDK %d", new Object[]{xVar.name(), Integer.valueOf(xVar.minRequiredSdkVersion)}));
                z = false;
            } else {
                z = hashSet2.add(xVar);
            }
            if (wVar.d != null && z) {
                wVar.c();
            }
            if (obtainStyledAttributes.hasValue(5)) {
                wVar.a(new f("**"), C0319A.f2028F, new e(new I(AppCompatResources.getColorStateList(getContext(), obtainStyledAttributes.getResourceId(5, -1)).getDefaultColor())));
            }
            if (obtainStyledAttributes.hasValue(15)) {
                H h5 = H.AUTOMATIC;
                int i2 = obtainStyledAttributes.getInt(15, h5.ordinal());
                setRenderMode(H.values()[i2 >= H.values().length ? h5.ordinal() : i2]);
            }
            if (obtainStyledAttributes.hasValue(0)) {
                C0323a aVar = C0323a.AUTOMATIC;
                int i7 = obtainStyledAttributes.getInt(0, aVar.ordinal());
                setAsyncUpdates(C0323a.values()[i7 >= H.values().length ? aVar.ordinal() : i7]);
            }
            setIgnoreDisabledSystemAnimations(obtainStyledAttributes.getBoolean(10, false));
            if (obtainStyledAttributes.hasValue(20)) {
                setUseCompositionFrameRate(obtainStyledAttributes.getBoolean(20, false));
            }
            obtainStyledAttributes.recycle();
            Context context2 = getContext();
            g gVar = J0.g.f363a;
            wVar.f = Settings.Global.getFloat(context2.getContentResolver(), "animator_duration_scale", 1.0f) == 0.0f ? false : z3;
            return;
        }
        throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use only one at once.");
    }

    private void setCompositionTask(C0322D d2) {
        C0320B b = d2.d;
        w wVar = this.f1099h;
        if (b == null || wVar != getDrawable() || wVar.d != b.f2042a) {
            this.n.add(C0330h.SET_ANIMATION);
            this.f1099h.d();
            b();
            d2.b(this.d);
            d2.a(this.e);
            this.f1103p = d2;
        }
    }

    public final void a() {
        this.l = false;
        this.n.add(C0330h.PLAY_OPTION);
        w wVar = this.f1099h;
        wVar.f2095j.clear();
        wVar.e.cancel();
        if (!wVar.isVisible()) {
            wVar.f2094i = v.NONE;
        }
    }

    public final void b() {
        C0322D d2 = this.f1103p;
        if (d2 != null) {
            C0331i iVar = this.d;
            synchronized (d2) {
                d2.f2043a.remove(iVar);
            }
            C0322D d3 = this.f1103p;
            C0331i iVar2 = this.e;
            synchronized (d3) {
                d3.b.remove(iVar2);
            }
        }
    }

    public final void c() {
        this.n.add(C0330h.PLAY_OPTION);
        this.f1099h.k();
    }

    public C0323a getAsyncUpdates() {
        C0323a aVar = this.f1099h.f2092P;
        if (aVar != null) {
            return aVar;
        }
        return C0326d.f2049a;
    }

    public boolean getAsyncUpdatesEnabled() {
        C0323a aVar = this.f1099h.f2092P;
        if (aVar == null) {
            aVar = C0326d.f2049a;
        }
        if (aVar == C0323a.ENABLED) {
            return true;
        }
        return false;
    }

    public boolean getClipTextToBoundingBox() {
        return this.f1099h.z;
    }

    public boolean getClipToCompositionBounds() {
        return this.f1099h.t;
    }

    public C0332j getComposition() {
        Drawable drawable = getDrawable();
        w wVar = this.f1099h;
        if (drawable == wVar) {
            return wVar.d;
        }
        return null;
    }

    public long getDuration() {
        C0332j composition = getComposition();
        if (composition != null) {
            return (long) composition.b();
        }
        return 0;
    }

    public int getFrame() {
        return (int) this.f1099h.e.k;
    }

    public String getImageAssetsFolder() {
        return this.f1099h.l;
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.f1099h.s;
    }

    public float getMaxFrame() {
        return this.f1099h.e.b();
    }

    public float getMinFrame() {
        return this.f1099h.e.c();
    }

    public E getPerformanceTracker() {
        C0332j jVar = this.f1099h.d;
        if (jVar != null) {
            return jVar.f2056a;
        }
        return null;
    }

    public float getProgress() {
        return this.f1099h.e.a();
    }

    public H getRenderMode() {
        if (this.f1099h.B) {
            return H.SOFTWARE;
        }
        return H.HARDWARE;
    }

    public int getRepeatCount() {
        return this.f1099h.e.getRepeatCount();
    }

    public int getRepeatMode() {
        return this.f1099h.e.getRepeatMode();
    }

    public float getSpeed() {
        return this.f1099h.e.g;
    }

    public final void invalidate() {
        H h5;
        super.invalidate();
        Drawable drawable = getDrawable();
        if (drawable instanceof w) {
            if (((w) drawable).B) {
                h5 = H.SOFTWARE;
            } else {
                h5 = H.HARDWARE;
            }
            if (h5 == H.SOFTWARE) {
                this.f1099h.invalidateSelf();
            }
        }
    }

    public final void invalidateDrawable(Drawable drawable) {
        Drawable drawable2 = getDrawable();
        w wVar = this.f1099h;
        if (drawable2 == wVar) {
            super.invalidateDrawable(wVar);
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode() && this.l) {
            this.f1099h.k();
        }
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        int i2;
        if (!(parcelable instanceof C0329g)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        C0329g gVar = (C0329g) parcelable;
        super.onRestoreInstanceState(gVar.getSuperState());
        this.f1100i = gVar.d;
        C0330h hVar = C0330h.SET_ANIMATION;
        HashSet hashSet = this.n;
        if (!hashSet.contains(hVar) && !TextUtils.isEmpty(this.f1100i)) {
            setAnimation(this.f1100i);
        }
        this.f1101j = gVar.e;
        if (!hashSet.contains(hVar) && (i2 = this.f1101j) != 0) {
            setAnimation(i2);
        }
        if (!hashSet.contains(C0330h.SET_PROGRESS)) {
            this.f1099h.u(gVar.f);
        }
        if (!hashSet.contains(C0330h.PLAY_OPTION) && gVar.g) {
            c();
        }
        if (!hashSet.contains(C0330h.SET_IMAGE_ASSETS)) {
            setImageAssetsFolder(gVar.f2052h);
        }
        if (!hashSet.contains(C0330h.SET_REPEAT_MODE)) {
            setRepeatMode(gVar.f2053i);
        }
        if (!hashSet.contains(C0330h.SET_REPEAT_COUNT)) {
            setRepeatCount(gVar.f2054j);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.view.View$BaseSavedState, android.os.Parcelable, x0.g] */
    public final Parcelable onSaveInstanceState() {
        boolean z;
        ? baseSavedState = new View.BaseSavedState(super.onSaveInstanceState());
        baseSavedState.d = this.f1100i;
        baseSavedState.e = this.f1101j;
        w wVar = this.f1099h;
        d dVar = wVar.e;
        d dVar2 = wVar.e;
        baseSavedState.f = dVar.a();
        if (wVar.isVisible()) {
            z = dVar2.f360p;
        } else {
            v vVar = wVar.f2094i;
            if (vVar == v.PLAY || vVar == v.RESUME) {
                z = true;
            } else {
                z = false;
            }
        }
        baseSavedState.g = z;
        baseSavedState.f2052h = wVar.l;
        baseSavedState.f2053i = dVar2.getRepeatMode();
        baseSavedState.f2054j = dVar2.getRepeatCount();
        return baseSavedState;
    }

    public void setAnimation(int i2) {
        C0322D d2;
        this.f1101j = i2;
        this.f1100i = null;
        if (isInEditMode()) {
            d2 = new C0322D(new C0328f(this, i2), true);
        } else if (this.m) {
            Context context = getContext();
            String j2 = n.j(context, i2);
            d2 = n.a(j2, new m(new WeakReference(context), context.getApplicationContext(), i2, j2), (t8.e) null);
        } else {
            Context context2 = getContext();
            HashMap hashMap = n.f2068a;
            d2 = n.a((String) null, new m(new WeakReference(context2), context2.getApplicationContext(), i2, (String) null), (t8.e) null);
        }
        setCompositionTask(d2);
    }

    @Deprecated
    public void setAnimationFromJson(String str) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        setCompositionTask(n.a((String) null, new a(2, byteArrayInputStream), new t8.e(21, byteArrayInputStream)));
    }

    public void setAnimationFromUrl(String str) {
        C0322D d2;
        if (this.m) {
            Context context = getContext();
            HashMap hashMap = n.f2068a;
            String l8 = C0212a.l("url_", str);
            d2 = n.a(l8, new k(context, str, l8, 0), (t8.e) null);
        } else {
            d2 = n.a((String) null, new k(getContext(), str, (String) null, 0), (t8.e) null);
        }
        setCompositionTask(d2);
    }

    public void setApplyingOpacityToLayersEnabled(boolean z) {
        this.f1099h.y = z;
    }

    public void setAsyncUpdates(C0323a aVar) {
        this.f1099h.f2092P = aVar;
    }

    public void setCacheComposition(boolean z) {
        this.m = z;
    }

    public void setClipTextToBoundingBox(boolean z) {
        w wVar = this.f1099h;
        if (z != wVar.z) {
            wVar.z = z;
            wVar.invalidateSelf();
        }
    }

    public void setClipToCompositionBounds(boolean z) {
        w wVar = this.f1099h;
        if (z != wVar.t) {
            wVar.t = z;
            F0.e eVar = wVar.u;
            if (eVar != null) {
                eVar.f196J = z;
            }
            wVar.invalidateSelf();
        }
    }

    public void setComposition(C0332j jVar) {
        C0323a aVar = C0326d.f2049a;
        w wVar = this.f1099h;
        wVar.setCallback(this);
        this.k = true;
        boolean n3 = wVar.n(jVar);
        if (this.l) {
            wVar.k();
        }
        this.k = false;
        if (getDrawable() != wVar || n3) {
            if (!n3) {
                boolean i2 = wVar.i();
                setImageDrawable((Drawable) null);
                setImageDrawable(wVar);
                if (i2) {
                    wVar.m();
                }
            }
            onVisibilityChanged(this, getVisibility());
            requestLayout();
            Iterator it = this.f1102o.iterator();
            if (it.hasNext()) {
                throw C0212a.h(it);
            }
        }
    }

    public void setDefaultFontFileExtension(String str) {
        w wVar = this.f1099h;
        wVar.f2097p = str;
        B0.a h5 = wVar.h();
        if (h5 != null) {
            h5.f34h = str;
        }
    }

    public void setFailureListener(z zVar) {
        this.f = zVar;
    }

    public void setFallbackResource(int i2) {
        this.g = i2;
    }

    public void setFontAssetDelegate(C0324b bVar) {
        B0.a aVar = this.f1099h.n;
    }

    public void setFontMap(Map<String, Typeface> map) {
        w wVar = this.f1099h;
        if (map != wVar.f2096o) {
            wVar.f2096o = map;
            wVar.invalidateSelf();
        }
    }

    public void setFrame(int i2) {
        this.f1099h.o(i2);
    }

    public void setIgnoreDisabledSystemAnimations(boolean z) {
        this.f1099h.g = z;
    }

    public void setImageAssetDelegate(C0325c cVar) {
        w wVar = this.f1099h;
        wVar.m = cVar;
        B0.b bVar = wVar.k;
        if (bVar != null) {
            bVar.f36c = cVar;
        }
    }

    public void setImageAssetsFolder(String str) {
        this.f1099h.l = str;
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.f1101j = 0;
        this.f1100i = null;
        b();
        super.setImageBitmap(bitmap);
    }

    public void setImageDrawable(Drawable drawable) {
        this.f1101j = 0;
        this.f1100i = null;
        b();
        super.setImageDrawable(drawable);
    }

    public void setImageResource(int i2) {
        this.f1101j = 0;
        this.f1100i = null;
        b();
        super.setImageResource(i2);
    }

    public void setMaintainOriginalImageBounds(boolean z) {
        this.f1099h.s = z;
    }

    public void setMaxFrame(int i2) {
        this.f1099h.p(i2);
    }

    public void setMaxProgress(float f5) {
        w wVar = this.f1099h;
        C0332j jVar = wVar.d;
        if (jVar == null) {
            wVar.f2095j.add(new r(wVar, f5, 0));
            return;
        }
        d dVar = wVar.e;
        dVar.j(dVar.m, J0.f.e(jVar.l, jVar.m, f5));
    }

    public void setMinAndMaxFrame(String str) {
        this.f1099h.r(str);
    }

    public void setMinFrame(int i2) {
        this.f1099h.s(i2);
    }

    public void setMinProgress(float f5) {
        w wVar = this.f1099h;
        C0332j jVar = wVar.d;
        if (jVar == null) {
            wVar.f2095j.add(new r(wVar, f5, 1));
        } else {
            wVar.s((int) J0.f.e(jVar.l, jVar.m, f5));
        }
    }

    public void setOutlineMasksAndMattes(boolean z) {
        w wVar = this.f1099h;
        if (wVar.f2098x != z) {
            wVar.f2098x = z;
            F0.e eVar = wVar.u;
            if (eVar != null) {
                eVar.p(z);
            }
        }
    }

    public void setPerformanceTrackingEnabled(boolean z) {
        w wVar = this.f1099h;
        wVar.w = z;
        C0332j jVar = wVar.d;
        if (jVar != null) {
            jVar.f2056a.f2045a = z;
        }
    }

    public void setProgress(float f5) {
        this.n.add(C0330h.SET_PROGRESS);
        this.f1099h.u(f5);
    }

    public void setRenderMode(H h5) {
        w wVar = this.f1099h;
        wVar.f2081A = h5;
        wVar.e();
    }

    public void setRepeatCount(int i2) {
        this.n.add(C0330h.SET_REPEAT_COUNT);
        this.f1099h.e.setRepeatCount(i2);
    }

    public void setRepeatMode(int i2) {
        this.n.add(C0330h.SET_REPEAT_MODE);
        this.f1099h.e.setRepeatMode(i2);
    }

    public void setSafeMode(boolean z) {
        this.f1099h.f2093h = z;
    }

    public void setSpeed(float f5) {
        this.f1099h.e.g = f5;
    }

    public void setTextDelegate(J j2) {
        this.f1099h.q = j2;
    }

    public void setUseCompositionFrameRate(boolean z) {
        this.f1099h.e.q = z;
    }

    public final void unscheduleDrawable(Drawable drawable) {
        w wVar;
        if (!this.k && drawable == (wVar = this.f1099h) && wVar.i()) {
            this.l = false;
            wVar.j();
        } else if (!this.k && (drawable instanceof w)) {
            w wVar2 = (w) drawable;
            if (wVar2.i()) {
                wVar2.j();
            }
        }
        super.unscheduleDrawable(drawable);
    }

    public void setMaxFrame(String str) {
        this.f1099h.q(str);
    }

    public void setMinFrame(String str) {
        this.f1099h.t(str);
    }

    public void setAnimation(String str) {
        C0322D d2;
        this.f1100i = str;
        this.f1101j = 0;
        if (isInEditMode()) {
            d2 = new C0322D(new G.a(7, this, str), true);
        } else if (this.m) {
            Context context = getContext();
            HashMap hashMap = n.f2068a;
            String l8 = C0212a.l("asset_", str);
            d2 = n.a(l8, new k(context.getApplicationContext(), str, l8, 1), (t8.e) null);
        } else {
            Context context2 = getContext();
            HashMap hashMap2 = n.f2068a;
            d2 = n.a((String) null, new k(context2.getApplicationContext(), str, (String) null, 1), (t8.e) null);
        }
        setCompositionTask(d2);
    }
}
