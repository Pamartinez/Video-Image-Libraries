package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import androidx.core.graphics.Insets;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import c0.C0086a;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WindowInsetsCompat {
    public static final WindowInsetsCompat CONSUMED;
    private final Impl mImpl;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BuilderImpl {
        private final WindowInsetsCompat mInsets;
        Insets[] mInsetsTypeMask;

        public BuilderImpl() {
            this(new WindowInsetsCompat((WindowInsetsCompat) null));
        }

        public final void applyInsetTypes() {
            Insets[] insetsArr = this.mInsetsTypeMask;
            if (insetsArr != null) {
                Insets insets = insetsArr[Type.indexOf(1)];
                Insets insets2 = this.mInsetsTypeMask[Type.indexOf(2)];
                if (insets2 == null) {
                    insets2 = this.mInsets.getInsets(2);
                }
                if (insets == null) {
                    insets = this.mInsets.getInsets(1);
                }
                setSystemWindowInsets(Insets.max(insets, insets2));
                Insets insets3 = this.mInsetsTypeMask[Type.indexOf(16)];
                if (insets3 != null) {
                    setSystemGestureInsets(insets3);
                }
                Insets insets4 = this.mInsetsTypeMask[Type.indexOf(32)];
                if (insets4 != null) {
                    setMandatorySystemGestureInsets(insets4);
                }
                Insets insets5 = this.mInsetsTypeMask[Type.indexOf(64)];
                if (insets5 != null) {
                    setTappableElementInsets(insets5);
                }
            }
        }

        public abstract WindowInsetsCompat build();

        public abstract void setMandatorySystemGestureInsets(Insets insets);

        public abstract void setStableInsets(Insets insets);

        public abstract void setSystemGestureInsets(Insets insets);

        public abstract void setSystemWindowInsets(Insets insets);

        public abstract void setTappableElementInsets(Insets insets);

        public BuilderImpl(WindowInsetsCompat windowInsetsCompat) {
            this.mInsets = windowInsetsCompat;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BuilderImpl30 extends BuilderImpl29 {
        public BuilderImpl30() {
        }

        public BuilderImpl30(WindowInsetsCompat windowInsetsCompat) {
            super(windowInsetsCompat);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BuilderImpl34 extends BuilderImpl30 {
        public BuilderImpl34() {
        }

        public BuilderImpl34(WindowInsetsCompat windowInsetsCompat) {
            super(windowInsetsCompat);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl20 extends Impl {
        private Insets[] mOverriddenInsets;
        final WindowInsets mPlatformInsets;
        Insets mRootViewVisibleInsets;
        private WindowInsetsCompat mRootWindowInsets;
        int mSystemUiVisibility;
        private Insets mSystemWindowInsets;

        public Impl20(WindowInsetsCompat windowInsetsCompat, WindowInsets windowInsets) {
            super(windowInsetsCompat);
            this.mSystemWindowInsets = null;
            this.mPlatformInsets = windowInsets;
        }

        private Insets getRootStableInsets() {
            WindowInsetsCompat windowInsetsCompat = this.mRootWindowInsets;
            if (windowInsetsCompat != null) {
                return windowInsetsCompat.getStableInsets();
            }
            return Insets.NONE;
        }

        private Insets getVisibleInsets(View view) {
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }

        public static boolean systemBarVisibilityEquals(int i2, int i7) {
            if ((i2 & 6) == (i7 & 6)) {
                return true;
            }
            return false;
        }

        public void copyRootViewBounds(View view) {
            Insets visibleInsets = getVisibleInsets(view);
            if (visibleInsets == null) {
                visibleInsets = Insets.NONE;
            }
            setRootViewData(visibleInsets);
        }

        public void copyWindowDataInto(WindowInsetsCompat windowInsetsCompat) {
            windowInsetsCompat.setRootWindowInsets(this.mRootWindowInsets);
            windowInsetsCompat.setRootViewData(this.mRootViewVisibleInsets);
            windowInsetsCompat.setSystemUiVisibility(this.mSystemUiVisibility);
        }

        public boolean equals(Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            Impl20 impl20 = (Impl20) obj;
            if (!Objects.equals(this.mRootViewVisibleInsets, impl20.mRootViewVisibleInsets) || !systemBarVisibilityEquals(this.mSystemUiVisibility, impl20.mSystemUiVisibility)) {
                return false;
            }
            return true;
        }

        public Insets getInsets(int i2) {
            return getInsets(i2, false);
        }

        public Insets getInsetsForType(int i2, boolean z) {
            int i7;
            DisplayCutoutCompat displayCutoutCompat;
            if (i2 != 1) {
                Insets insets = null;
                if (i2 != 2) {
                    if (i2 == 8) {
                        Insets[] insetsArr = this.mOverriddenInsets;
                        if (insetsArr != null) {
                            insets = insetsArr[Type.indexOf(8)];
                        }
                        if (insets != null) {
                            return insets;
                        }
                        Insets systemWindowInsets = getSystemWindowInsets();
                        Insets rootStableInsets = getRootStableInsets();
                        int i8 = systemWindowInsets.bottom;
                        if (i8 > rootStableInsets.bottom) {
                            return Insets.of(0, 0, 0, i8);
                        }
                        Insets insets2 = this.mRootViewVisibleInsets;
                        if (insets2 == null || insets2.equals(Insets.NONE) || (i7 = this.mRootViewVisibleInsets.bottom) <= rootStableInsets.bottom) {
                            return Insets.NONE;
                        }
                        return Insets.of(0, 0, 0, i7);
                    } else if (i2 == 16) {
                        return getSystemGestureInsets();
                    } else {
                        if (i2 == 32) {
                            return getMandatorySystemGestureInsets();
                        }
                        if (i2 == 64) {
                            return getTappableElementInsets();
                        }
                        if (i2 != 128) {
                            return Insets.NONE;
                        }
                        WindowInsetsCompat windowInsetsCompat = this.mRootWindowInsets;
                        if (windowInsetsCompat != null) {
                            displayCutoutCompat = windowInsetsCompat.getDisplayCutout();
                        } else {
                            displayCutoutCompat = getDisplayCutout();
                        }
                        if (displayCutoutCompat != null) {
                            return Insets.of(displayCutoutCompat.getSafeInsetLeft(), displayCutoutCompat.getSafeInsetTop(), displayCutoutCompat.getSafeInsetRight(), displayCutoutCompat.getSafeInsetBottom());
                        }
                        return Insets.NONE;
                    }
                } else if (z) {
                    Insets rootStableInsets2 = getRootStableInsets();
                    Insets stableInsets = getStableInsets();
                    return Insets.of(Math.max(rootStableInsets2.left, stableInsets.left), 0, Math.max(rootStableInsets2.right, stableInsets.right), Math.max(rootStableInsets2.bottom, stableInsets.bottom));
                } else if ((this.mSystemUiVisibility & 2) != 0) {
                    return Insets.NONE;
                } else {
                    Insets systemWindowInsets2 = getSystemWindowInsets();
                    WindowInsetsCompat windowInsetsCompat2 = this.mRootWindowInsets;
                    if (windowInsetsCompat2 != null) {
                        insets = windowInsetsCompat2.getStableInsets();
                    }
                    int i10 = systemWindowInsets2.bottom;
                    if (insets != null) {
                        i10 = Math.min(i10, insets.bottom);
                    }
                    return Insets.of(systemWindowInsets2.left, 0, systemWindowInsets2.right, i10);
                }
            } else if (z) {
                return Insets.of(0, Math.max(getRootStableInsets().top, getSystemWindowInsets().top), 0, 0);
            } else {
                if ((this.mSystemUiVisibility & 4) != 0) {
                    return Insets.NONE;
                }
                return Insets.of(0, getSystemWindowInsets().top, 0, 0);
            }
        }

        public Insets getInsetsIgnoringVisibility(int i2) {
            return getInsets(i2, true);
        }

        public final Insets getSystemWindowInsets() {
            if (this.mSystemWindowInsets == null) {
                this.mSystemWindowInsets = Insets.of(this.mPlatformInsets.getSystemWindowInsetLeft(), this.mPlatformInsets.getSystemWindowInsetTop(), this.mPlatformInsets.getSystemWindowInsetRight(), this.mPlatformInsets.getSystemWindowInsetBottom());
            }
            return this.mSystemWindowInsets;
        }

        public WindowInsetsCompat inset(int i2, int i7, int i8, int i10) {
            Builder builder = new Builder(WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets));
            builder.setSystemWindowInsets(WindowInsetsCompat.insetInsets(getSystemWindowInsets(), i2, i7, i8, i10));
            builder.setStableInsets(WindowInsetsCompat.insetInsets(getStableInsets(), i2, i7, i8, i10));
            return builder.build();
        }

        public boolean isRound() {
            return this.mPlatformInsets.isRound();
        }

        public void setOverriddenInsets(Insets[] insetsArr) {
            this.mOverriddenInsets = insetsArr;
        }

        public void setRootViewData(Insets insets) {
            this.mRootViewVisibleInsets = insets;
        }

        public void setRootWindowInsets(WindowInsetsCompat windowInsetsCompat) {
            this.mRootWindowInsets = windowInsetsCompat;
        }

        public void setSystemUiVisibility(int i2) {
            this.mSystemUiVisibility = i2;
        }

        private Insets getInsets(int i2, boolean z) {
            Insets insets = Insets.NONE;
            for (int i7 = 1; i7 <= 512; i7 <<= 1) {
                if ((i2 & i7) != 0) {
                    insets = Insets.max(insets, getInsetsForType(i7, z));
                }
            }
            return insets;
        }

        public Impl20(WindowInsetsCompat windowInsetsCompat, Impl20 impl20) {
            this(windowInsetsCompat, new WindowInsets(impl20.mPlatformInsets));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl28 extends Impl21 {
        public Impl28(WindowInsetsCompat windowInsetsCompat, WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        public WindowInsetsCompat consumeDisplayCutout() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeDisplayCutout());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Impl28)) {
                return false;
            }
            Impl28 impl28 = (Impl28) obj;
            if (!Objects.equals(this.mPlatformInsets, impl28.mPlatformInsets) || !Objects.equals(this.mRootViewVisibleInsets, impl28.mRootViewVisibleInsets) || !Impl20.systemBarVisibilityEquals(this.mSystemUiVisibility, impl28.mSystemUiVisibility)) {
                return false;
            }
            return true;
        }

        public DisplayCutoutCompat getDisplayCutout() {
            return DisplayCutoutCompat.wrap(this.mPlatformInsets.getDisplayCutout());
        }

        public int hashCode() {
            return this.mPlatformInsets.hashCode();
        }

        public Impl28(WindowInsetsCompat windowInsetsCompat, Impl28 impl28) {
            super(windowInsetsCompat, (Impl21) impl28);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl30 extends Impl29 {
        static final WindowInsetsCompat CONSUMED = WindowInsetsCompat.toWindowInsetsCompat(WindowInsets.CONSUMED);

        public Impl30(WindowInsetsCompat windowInsetsCompat, WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        public Insets getInsets(int i2) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsets(TypeImpl30.toPlatformType(i2)));
        }

        public Insets getInsetsIgnoringVisibility(int i2) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsetsIgnoringVisibility(TypeImpl30.toPlatformType(i2)));
        }

        public Impl30(WindowInsetsCompat windowInsetsCompat, Impl30 impl30) {
            super(windowInsetsCompat, (Impl29) impl30);
        }

        public final void copyRootViewBounds(View view) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl34 extends Impl30 {
        static final WindowInsetsCompat CONSUMED = WindowInsetsCompat.toWindowInsetsCompat(WindowInsets.CONSUMED);

        public Impl34(WindowInsetsCompat windowInsetsCompat, WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        public Insets getInsets(int i2) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsets(TypeImpl34.toPlatformType(i2)));
        }

        public Insets getInsetsIgnoringVisibility(int i2) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsetsIgnoringVisibility(TypeImpl34.toPlatformType(i2)));
        }

        public Impl34(WindowInsetsCompat windowInsetsCompat, Impl34 impl34) {
            super(windowInsetsCompat, (Impl30) impl34);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Type {
        public static int displayCutout() {
            return 128;
        }

        public static int ime() {
            return 8;
        }

        public static int indexOf(int i2) {
            if (i2 == 1) {
                return 0;
            }
            if (i2 == 2) {
                return 1;
            }
            if (i2 == 4) {
                return 2;
            }
            if (i2 == 8) {
                return 3;
            }
            if (i2 == 16) {
                return 4;
            }
            if (i2 == 32) {
                return 5;
            }
            if (i2 == 64) {
                return 6;
            }
            if (i2 == 128) {
                return 7;
            }
            if (i2 == 256) {
                return 8;
            }
            if (i2 == 512) {
                return 9;
            }
            throw new IllegalArgumentException(C0086a.i(i2, "type needs to be >= FIRST and <= LAST, type="));
        }

        public static int mandatorySystemGestures() {
            return 32;
        }

        public static int navigationBars() {
            return 2;
        }

        public static int systemBars() {
            return 519;
        }

        public static int tappableElement() {
            return 64;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TypeImpl30 {
        public static int toPlatformType(int i2) {
            int statusBars;
            int i7 = 0;
            for (int i8 = 1; i8 <= 512; i8 <<= 1) {
                if ((i2 & i8) != 0) {
                    if (i8 == 1) {
                        statusBars = WindowInsets.Type.statusBars();
                    } else if (i8 == 2) {
                        statusBars = WindowInsets.Type.navigationBars();
                    } else if (i8 == 4) {
                        statusBars = WindowInsets.Type.captionBar();
                    } else if (i8 == 8) {
                        statusBars = WindowInsets.Type.ime();
                    } else if (i8 == 16) {
                        statusBars = WindowInsets.Type.systemGestures();
                    } else if (i8 == 32) {
                        statusBars = WindowInsets.Type.mandatorySystemGestures();
                    } else if (i8 == 64) {
                        statusBars = WindowInsets.Type.tappableElement();
                    } else if (i8 == 128) {
                        statusBars = WindowInsets.Type.displayCutout();
                    }
                    i7 |= statusBars;
                }
            }
            return i7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TypeImpl34 {
        public static int toPlatformType(int i2) {
            int statusBars;
            int i7 = 0;
            for (int i8 = 1; i8 <= 512; i8 <<= 1) {
                if ((i2 & i8) != 0) {
                    if (i8 == 1) {
                        statusBars = WindowInsets.Type.statusBars();
                    } else if (i8 == 2) {
                        statusBars = WindowInsets.Type.navigationBars();
                    } else if (i8 == 4) {
                        statusBars = WindowInsets.Type.captionBar();
                    } else if (i8 == 8) {
                        statusBars = WindowInsets.Type.ime();
                    } else if (i8 == 16) {
                        statusBars = WindowInsets.Type.systemGestures();
                    } else if (i8 == 32) {
                        statusBars = WindowInsets.Type.mandatorySystemGestures();
                    } else if (i8 == 64) {
                        statusBars = WindowInsets.Type.tappableElement();
                    } else if (i8 == 128) {
                        statusBars = WindowInsets.Type.displayCutout();
                    } else if (i8 == 512) {
                        statusBars = WindowInsets.Type.systemOverlays();
                    }
                    i7 |= statusBars;
                }
            }
            return i7;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 34) {
            CONSUMED = Impl34.CONSUMED;
        } else {
            CONSUMED = Impl30.CONSUMED;
        }
    }

    private WindowInsetsCompat(WindowInsets windowInsets) {
        if (Build.VERSION.SDK_INT >= 34) {
            this.mImpl = new Impl34(this, windowInsets);
        } else {
            this.mImpl = new Impl30(this, windowInsets);
        }
    }

    public static Insets insetInsets(Insets insets, int i2, int i7, int i8, int i10) {
        int max = Math.max(0, insets.left - i2);
        int max2 = Math.max(0, insets.top - i7);
        int max3 = Math.max(0, insets.right - i8);
        int max4 = Math.max(0, insets.bottom - i10);
        if (max == i2 && max2 == i7 && max3 == i8 && max4 == i10) {
            return insets;
        }
        return Insets.of(max, max2, max3, max4);
    }

    public static WindowInsetsCompat toWindowInsetsCompat(WindowInsets windowInsets) {
        return toWindowInsetsCompat(windowInsets, (View) null);
    }

    @Deprecated
    public WindowInsetsCompat consumeDisplayCutout() {
        return this.mImpl.consumeDisplayCutout();
    }

    @Deprecated
    public WindowInsetsCompat consumeStableInsets() {
        return this.mImpl.consumeStableInsets();
    }

    @Deprecated
    public WindowInsetsCompat consumeSystemWindowInsets() {
        return this.mImpl.consumeSystemWindowInsets();
    }

    public void copyRootViewBounds(View view) {
        this.mImpl.copyRootViewBounds(view);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WindowInsetsCompat)) {
            return false;
        }
        return ObjectsCompat.equals(this.mImpl, ((WindowInsetsCompat) obj).mImpl);
    }

    public DisplayCutoutCompat getDisplayCutout() {
        return this.mImpl.getDisplayCutout();
    }

    public Insets getInsets(int i2) {
        return this.mImpl.getInsets(i2);
    }

    public Insets getInsetsIgnoringVisibility(int i2) {
        return this.mImpl.getInsetsIgnoringVisibility(i2);
    }

    @Deprecated
    public Insets getStableInsets() {
        return this.mImpl.getStableInsets();
    }

    @Deprecated
    public int getSystemWindowInsetBottom() {
        return this.mImpl.getSystemWindowInsets().bottom;
    }

    @Deprecated
    public int getSystemWindowInsetLeft() {
        return this.mImpl.getSystemWindowInsets().left;
    }

    @Deprecated
    public int getSystemWindowInsetRight() {
        return this.mImpl.getSystemWindowInsets().right;
    }

    @Deprecated
    public int getSystemWindowInsetTop() {
        return this.mImpl.getSystemWindowInsets().top;
    }

    public int hashCode() {
        Impl impl = this.mImpl;
        if (impl == null) {
            return 0;
        }
        return impl.hashCode();
    }

    public WindowInsetsCompat inset(int i2, int i7, int i8, int i10) {
        return this.mImpl.inset(i2, i7, i8, i10);
    }

    public boolean isConsumed() {
        return this.mImpl.isConsumed();
    }

    @Deprecated
    public WindowInsetsCompat replaceSystemWindowInsets(int i2, int i7, int i8, int i10) {
        return new Builder(this).setSystemWindowInsets(Insets.of(i2, i7, i8, i10)).build();
    }

    public void setOverriddenInsets(Insets[] insetsArr) {
        this.mImpl.setOverriddenInsets(insetsArr);
    }

    public void setRootViewData(Insets insets) {
        this.mImpl.setRootViewData(insets);
    }

    public void setRootWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        this.mImpl.setRootWindowInsets(windowInsetsCompat);
    }

    public void setSystemUiVisibility(int i2) {
        this.mImpl.setSystemUiVisibility(i2);
    }

    public WindowInsets toWindowInsets() {
        Impl impl = this.mImpl;
        if (impl instanceof Impl20) {
            return ((Impl20) impl).mPlatformInsets;
        }
        return null;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BuilderImpl29 extends BuilderImpl {
        final WindowInsets.Builder mPlatBuilder;

        public BuilderImpl29() {
            this.mPlatBuilder = new WindowInsets.Builder();
        }

        public WindowInsetsCompat build() {
            applyInsetTypes();
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(this.mPlatBuilder.build());
            windowInsetsCompat.setOverriddenInsets(this.mInsetsTypeMask);
            return windowInsetsCompat;
        }

        public void setMandatorySystemGestureInsets(Insets insets) {
            this.mPlatBuilder.setMandatorySystemGestureInsets(insets.toPlatformInsets());
        }

        public void setStableInsets(Insets insets) {
            this.mPlatBuilder.setStableInsets(insets.toPlatformInsets());
        }

        public void setSystemGestureInsets(Insets insets) {
            this.mPlatBuilder.setSystemGestureInsets(insets.toPlatformInsets());
        }

        public void setSystemWindowInsets(Insets insets) {
            this.mPlatBuilder.setSystemWindowInsets(insets.toPlatformInsets());
        }

        public void setTappableElementInsets(Insets insets) {
            this.mPlatBuilder.setTappableElementInsets(insets.toPlatformInsets());
        }

        public BuilderImpl29(WindowInsetsCompat windowInsetsCompat) {
            super(windowInsetsCompat);
            WindowInsets.Builder builder;
            WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
            if (windowInsets != null) {
                builder = new WindowInsets.Builder(windowInsets);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.mPlatBuilder = builder;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl21 extends Impl20 {
        private Insets mStableInsets = null;

        public Impl21(WindowInsetsCompat windowInsetsCompat, WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        public WindowInsetsCompat consumeStableInsets() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeStableInsets());
        }

        public WindowInsetsCompat consumeSystemWindowInsets() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeSystemWindowInsets());
        }

        public final Insets getStableInsets() {
            if (this.mStableInsets == null) {
                this.mStableInsets = Insets.of(this.mPlatformInsets.getStableInsetLeft(), this.mPlatformInsets.getStableInsetTop(), this.mPlatformInsets.getStableInsetRight(), this.mPlatformInsets.getStableInsetBottom());
            }
            return this.mStableInsets;
        }

        public boolean isConsumed() {
            return this.mPlatformInsets.isConsumed();
        }

        public Impl21(WindowInsetsCompat windowInsetsCompat, Impl21 impl21) {
            super(windowInsetsCompat, (Impl20) impl21);
            this.mStableInsets = impl21.mStableInsets;
        }
    }

    public static WindowInsetsCompat toWindowInsetsCompat(WindowInsets windowInsets, View view) {
        WindowInsetsCompat windowInsetsCompat = new WindowInsetsCompat((WindowInsets) Preconditions.checkNotNull(windowInsets));
        if (view != null && view.isAttachedToWindow()) {
            windowInsetsCompat.setRootWindowInsets(ViewCompat.getRootWindowInsets(view));
            windowInsetsCompat.copyRootViewBounds(view.getRootView());
            windowInsetsCompat.setSystemUiVisibility(view.getWindowSystemUiVisibility());
        }
        return windowInsetsCompat;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private final BuilderImpl mImpl;

        public Builder() {
            if (Build.VERSION.SDK_INT >= 34) {
                this.mImpl = new BuilderImpl34();
            } else {
                this.mImpl = new BuilderImpl30();
            }
        }

        public WindowInsetsCompat build() {
            return this.mImpl.build();
        }

        @Deprecated
        public Builder setStableInsets(Insets insets) {
            this.mImpl.setStableInsets(insets);
            return this;
        }

        @Deprecated
        public Builder setSystemWindowInsets(Insets insets) {
            this.mImpl.setSystemWindowInsets(insets);
            return this;
        }

        public Builder(WindowInsetsCompat windowInsetsCompat) {
            if (Build.VERSION.SDK_INT >= 34) {
                this.mImpl = new BuilderImpl34(windowInsetsCompat);
            } else {
                this.mImpl = new BuilderImpl30(windowInsetsCompat);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl29 extends Impl28 {
        private Insets mMandatorySystemGestureInsets = null;
        private Insets mSystemGestureInsets = null;
        private Insets mTappableElementInsets = null;

        public Impl29(WindowInsetsCompat windowInsetsCompat, WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        public Insets getMandatorySystemGestureInsets() {
            if (this.mMandatorySystemGestureInsets == null) {
                this.mMandatorySystemGestureInsets = Insets.toCompatInsets(this.mPlatformInsets.getMandatorySystemGestureInsets());
            }
            return this.mMandatorySystemGestureInsets;
        }

        public Insets getSystemGestureInsets() {
            if (this.mSystemGestureInsets == null) {
                this.mSystemGestureInsets = Insets.toCompatInsets(this.mPlatformInsets.getSystemGestureInsets());
            }
            return this.mSystemGestureInsets;
        }

        public Insets getTappableElementInsets() {
            if (this.mTappableElementInsets == null) {
                this.mTappableElementInsets = Insets.toCompatInsets(this.mPlatformInsets.getTappableElementInsets());
            }
            return this.mTappableElementInsets;
        }

        public WindowInsetsCompat inset(int i2, int i7, int i8, int i10) {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.inset(i2, i7, i8, i10));
        }

        public Impl29(WindowInsetsCompat windowInsetsCompat, Impl29 impl29) {
            super(windowInsetsCompat, (Impl28) impl29);
        }
    }

    public WindowInsetsCompat(WindowInsetsCompat windowInsetsCompat) {
        if (windowInsetsCompat != null) {
            Impl impl = windowInsetsCompat.mImpl;
            if (Build.VERSION.SDK_INT >= 34 && (impl instanceof Impl34)) {
                this.mImpl = new Impl34(this, (Impl34) impl);
            } else if (impl instanceof Impl30) {
                this.mImpl = new Impl30(this, (Impl30) impl);
            } else if (impl instanceof Impl29) {
                this.mImpl = new Impl29(this, (Impl29) impl);
            } else if (impl instanceof Impl28) {
                this.mImpl = new Impl28(this, (Impl28) impl);
            } else if (impl instanceof Impl21) {
                this.mImpl = new Impl21(this, (Impl21) impl);
            } else if (impl instanceof Impl20) {
                this.mImpl = new Impl20(this, (Impl20) impl);
            } else {
                this.mImpl = new Impl(this);
            }
            impl.copyWindowDataInto(this);
            return;
        }
        this.mImpl = new Impl(this);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl {
        static final WindowInsetsCompat CONSUMED = new Builder().build().consumeDisplayCutout().consumeStableInsets().consumeSystemWindowInsets();
        final WindowInsetsCompat mHost;

        public Impl(WindowInsetsCompat windowInsetsCompat) {
            this.mHost = windowInsetsCompat;
        }

        public WindowInsetsCompat consumeDisplayCutout() {
            return this.mHost;
        }

        public WindowInsetsCompat consumeStableInsets() {
            return this.mHost;
        }

        public WindowInsetsCompat consumeSystemWindowInsets() {
            return this.mHost;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Impl)) {
                return false;
            }
            Impl impl = (Impl) obj;
            if (isRound() != impl.isRound() || isConsumed() != impl.isConsumed() || !ObjectsCompat.equals(getSystemWindowInsets(), impl.getSystemWindowInsets()) || !ObjectsCompat.equals(getStableInsets(), impl.getStableInsets()) || !ObjectsCompat.equals(getDisplayCutout(), impl.getDisplayCutout())) {
                return false;
            }
            return true;
        }

        public DisplayCutoutCompat getDisplayCutout() {
            return null;
        }

        public Insets getInsets(int i2) {
            return Insets.NONE;
        }

        public Insets getInsetsIgnoringVisibility(int i2) {
            if ((i2 & 8) == 0) {
                return Insets.NONE;
            }
            throw new IllegalArgumentException("Unable to query the maximum insets for IME");
        }

        public Insets getMandatorySystemGestureInsets() {
            return getSystemWindowInsets();
        }

        public Insets getStableInsets() {
            return Insets.NONE;
        }

        public Insets getSystemGestureInsets() {
            return getSystemWindowInsets();
        }

        public Insets getSystemWindowInsets() {
            return Insets.NONE;
        }

        public Insets getTappableElementInsets() {
            return getSystemWindowInsets();
        }

        public int hashCode() {
            return ObjectsCompat.hash(Boolean.valueOf(isRound()), Boolean.valueOf(isConsumed()), getSystemWindowInsets(), getStableInsets(), getDisplayCutout());
        }

        public WindowInsetsCompat inset(int i2, int i7, int i8, int i10) {
            return CONSUMED;
        }

        public boolean isConsumed() {
            return false;
        }

        public boolean isRound() {
            return false;
        }

        public void copyRootViewBounds(View view) {
        }

        public void copyWindowDataInto(WindowInsetsCompat windowInsetsCompat) {
        }

        public void setOverriddenInsets(Insets[] insetsArr) {
        }

        public void setRootViewData(Insets insets) {
        }

        public void setRootWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        }

        public void setSystemUiVisibility(int i2) {
        }
    }
}
