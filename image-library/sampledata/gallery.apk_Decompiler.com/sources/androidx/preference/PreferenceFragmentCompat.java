package androidx.preference;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.appcompat.R$styleable;
import androidx.appcompat.util.SeslRoundedCorner;
import androidx.appcompat.util.SeslSubheaderRoundedCorner;
import androidx.core.graphics.Insets;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PreferenceFragmentCompat extends Fragment implements PreferenceManager.OnPreferenceTreeClickListener, PreferenceManager.OnDisplayPreferenceDialogListener, PreferenceManager.OnNavigateToScreenListener, DialogPreference.TargetFragment {
    /* access modifiers changed from: private */
    public int mBottom = -1;
    private final DividerDecoration mDividerDecoration = new DividerDecoration();
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                PreferenceFragmentCompat.this.bindPreferences();
            }
        }
    };
    private boolean mHavePrefs;
    private boolean mInitDone;
    /* access modifiers changed from: private */
    public int mIsLargeLayout;
    private boolean mIsReducedMargin;
    /* access modifiers changed from: private */
    public boolean mIsRoundedCorner = true;
    private int mLayoutResId = R$layout.preference_list_fragment;
    /* access modifiers changed from: private */
    public int mLeft = -1;
    RecyclerView mList;
    /* access modifiers changed from: private */
    public SeslRoundedCorner mListRoundedCorner;
    /* access modifiers changed from: private */
    public ViewTreeObserver.OnPreDrawListener mOnPreDrawListener;
    private PreferenceManager mPreferenceManager;
    private final Runnable mRequestFocus = new Runnable() {
        public void run() {
            RecyclerView recyclerView = PreferenceFragmentCompat.this.mList;
            recyclerView.focusableViewAvailable(recyclerView);
        }
    };
    /* access modifiers changed from: private */
    public int mRight = -1;
    /* access modifiers changed from: private */
    public SeslRoundedCorner mRoundedCorner;
    /* access modifiers changed from: private */
    public int mScreenWidthDp;
    private Runnable mSelectPreferenceRunnable;
    private int mSubheaderColor;
    /* access modifiers changed from: private */
    public SeslSubheaderRoundedCorner mSubheaderRoundedCorner;
    /* access modifiers changed from: private */
    public int mTop = -1;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class DividerDecoration extends RecyclerView.ItemDecoration {
        private boolean mAllowDividerAfterLastItem = true;
        private Drawable mDivider;
        private int mDividerHeight;

        public DividerDecoration() {
        }

        private boolean shouldDrawDividerBelow(View view, RecyclerView recyclerView) {
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
            if (!(childViewHolder instanceof PreferenceViewHolder) || !((PreferenceViewHolder) childViewHolder).isDividerAllowedBelow()) {
                return false;
            }
            boolean z = this.mAllowDividerAfterLastItem;
            int indexOfChild = recyclerView.indexOfChild(view);
            if (indexOfChild >= recyclerView.getChildCount() - 1) {
                return z;
            }
            RecyclerView.ViewHolder childViewHolder2 = recyclerView.getChildViewHolder(recyclerView.getChildAt(indexOfChild + 1));
            if (!(childViewHolder2 instanceof PreferenceViewHolder) || !((PreferenceViewHolder) childViewHolder2).isDividerAllowedAbove()) {
                return false;
            }
            return true;
        }

        public void seslOnDispatchDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            int i2;
            PreferenceViewHolder preferenceViewHolder;
            int i7;
            int i8;
            super.seslOnDispatchDraw(canvas, recyclerView, state);
            int childCount = recyclerView.getChildCount();
            int paddingLeft = recyclerView.getPaddingLeft() + recyclerView.getLeft();
            int right = recyclerView.getRight() - recyclerView.getPaddingRight();
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = recyclerView.getChildAt(i10);
                RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childAt);
                if (childViewHolder instanceof PreferenceViewHolder) {
                    preferenceViewHolder = (PreferenceViewHolder) childViewHolder;
                    i2 = preferenceViewHolder.seslGetDividerLeftOffset();
                } else {
                    preferenceViewHolder = null;
                    i2 = 0;
                }
                boolean z = true;
                if (PreferenceFragmentCompat.this.getResources().getConfiguration().getLayoutDirection() != 1) {
                    z = false;
                }
                int height = childAt.getHeight() + ((int) childAt.getY());
                if (this.mDivider != null && shouldDrawDividerBelow(childAt, recyclerView)) {
                    int i11 = this.mDividerHeight + height;
                    if (z) {
                        i7 = 0;
                    } else {
                        i7 = i2;
                    }
                    if (z) {
                        i8 = -i2;
                    } else {
                        i8 = 0;
                    }
                    this.mDivider.setBounds(i7 + paddingLeft, height, i8 + right, i11);
                    this.mDivider.draw(canvas);
                }
                if (PreferenceFragmentCompat.this.mIsRoundedCorner && preferenceViewHolder != null && preferenceViewHolder.isBackgroundDrawn()) {
                    if (preferenceViewHolder.isDrawSubheaderRound()) {
                        PreferenceFragmentCompat.this.mSubheaderRoundedCorner.setRoundedCorners(preferenceViewHolder.getDrawCorners());
                        PreferenceFragmentCompat.this.mSubheaderRoundedCorner.drawRoundedCorner(childAt, canvas);
                    } else {
                        PreferenceFragmentCompat.this.mRoundedCorner.setRoundedCorners(preferenceViewHolder.getDrawCorners());
                        PreferenceFragmentCompat.this.mRoundedCorner.drawRoundedCorner(childAt, canvas);
                    }
                }
            }
            if (PreferenceFragmentCompat.this.mIsRoundedCorner) {
                PreferenceFragmentCompat.this.mListRoundedCorner.drawRoundedCorner(canvas, Insets.of(PreferenceFragmentCompat.this.mLeft, PreferenceFragmentCompat.this.mTop, PreferenceFragmentCompat.this.mRight, PreferenceFragmentCompat.this.mBottom));
            }
        }

        public void setAllowDividerAfterLastItem(boolean z) {
            this.mAllowDividerAfterLastItem = z;
        }

        public void setDivider(Drawable drawable) {
            if (drawable != null) {
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerHeight = 0;
            }
            this.mDivider = drawable;
            PreferenceFragmentCompat.this.mList.invalidateItemDecorations();
        }

        public void setDividerHeight(int i2) {
            this.mDividerHeight = i2;
            PreferenceFragmentCompat.this.mList.invalidateItemDecorations();
        }
    }

    private void createOnPreDrawListner() {
        if (this.mList != null) {
            this.mOnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    int i2;
                    RecyclerView recyclerView = PreferenceFragmentCompat.this.mList;
                    if (recyclerView != null) {
                        RecyclerView.Adapter adapter = recyclerView.getAdapter();
                        Configuration configuration = PreferenceFragmentCompat.this.getResources().getConfiguration();
                        int i7 = configuration.screenWidthDp;
                        if ((i7 > 320 || configuration.fontScale < 1.1f) && (i7 >= 411 || configuration.fontScale < 1.3f)) {
                            i2 = 2;
                        } else {
                            i2 = 1;
                        }
                        if (adapter instanceof PreferenceGroupAdapter) {
                            PreferenceGroupAdapter preferenceGroupAdapter = (PreferenceGroupAdapter) adapter;
                            if (PreferenceFragmentCompat.this.needToRefeshSwitch(preferenceGroupAdapter, i2, i7)) {
                                int unused = PreferenceFragmentCompat.this.mIsLargeLayout = i2;
                                for (int i8 = 0; i8 < preferenceGroupAdapter.getItemCount(); i8++) {
                                    Preference item = preferenceGroupAdapter.getItem(i8);
                                    if (item != null && preferenceGroupAdapter.isSwitchLayout(item) && (item instanceof SwitchPreferenceCompat)) {
                                        adapter.notifyItemChanged(i8);
                                    }
                                }
                            }
                        }
                        int unused2 = PreferenceFragmentCompat.this.mScreenWidthDp = configuration.screenWidthDp;
                        PreferenceFragmentCompat.this.mList.getViewTreeObserver().removeOnPreDrawListener(this);
                        ViewTreeObserver.OnPreDrawListener unused3 = PreferenceFragmentCompat.this.mOnPreDrawListener = null;
                    }
                    return false;
                }
            };
        }
    }

    /* access modifiers changed from: private */
    public boolean needToRefeshSwitch(PreferenceGroupAdapter preferenceGroupAdapter, int i2, int i7) {
        if (i2 == this.mIsLargeLayout) {
            if (i2 != 1) {
                return false;
            }
            if (this.mScreenWidthDp != i7 || preferenceGroupAdapter.getListWidth() == 0) {
                return true;
            }
            return false;
        }
        return true;
    }

    private void postBindPreferences() {
        if (!this.mHandler.hasMessages(1)) {
            this.mHandler.obtainMessage(1).sendToTarget();
        }
    }

    private void requirePreferenceManager() {
        if (this.mPreferenceManager == null) {
            throw new RuntimeException("This should be called after super.onCreate.");
        }
    }

    private void unbindPreferences() {
        getListView().setAdapter((RecyclerView.Adapter) null);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            preferenceScreen.onDetached();
        }
        onUnbindPreferences();
    }

    private void updatePadding() {
        boolean z;
        RecyclerView recyclerView = this.mList;
        if (recyclerView != null) {
            recyclerView.setPadding(this.mLeft, this.mTop, this.mRight, this.mBottom);
            RecyclerView recyclerView2 = this.mList;
            int i2 = 0;
            if (this.mLeft == 0 && this.mRight == 0 && this.mTop == 0 && this.mBottom == 0) {
                z = false;
            } else {
                z = true;
            }
            recyclerView2.seslSetFillHorizontalPaddingEnabled(z);
            RecyclerView recyclerView3 = this.mList;
            if (this.mLeft > 0 || this.mRight > 0) {
                i2 = 33554432;
            }
            recyclerView3.setScrollBarStyle(i2);
        }
    }

    public void addPreferencesFromResource(int i2) {
        requirePreferenceManager();
        setPreferenceScreen(this.mPreferenceManager.inflateFromResource(requireContext(), i2, getPreferenceScreen()));
    }

    public void bindPreferences() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            getListView().setAdapter(onCreateAdapter(preferenceScreen));
            preferenceScreen.onAttached();
        }
        onBindPreferences();
    }

    public <T extends Preference> T findPreference(CharSequence charSequence) {
        PreferenceManager preferenceManager = this.mPreferenceManager;
        if (preferenceManager == null) {
            return null;
        }
        return preferenceManager.findPreference(charSequence);
    }

    public Fragment getCallbackFragment() {
        return null;
    }

    public final RecyclerView getListView() {
        return this.mList;
    }

    public PreferenceManager getPreferenceManager() {
        return this.mPreferenceManager;
    }

    public PreferenceScreen getPreferenceScreen() {
        return this.mPreferenceManager.getPreferenceScreen();
    }

    public void onConfigurationChanged(Configuration configuration) {
        boolean z;
        if (getListView() != null) {
            if (this.mOnPreDrawListener == null) {
                ViewTreeObserver viewTreeObserver = getListView().getViewTreeObserver();
                createOnPreDrawListner();
                viewTreeObserver.addOnPreDrawListener(this.mOnPreDrawListener);
            }
            RecyclerView.Adapter adapter = getListView().getAdapter();
            RecyclerView.LayoutManager layoutManager = getListView().getLayoutManager();
            if (configuration.screenWidthDp <= 250) {
                z = true;
            } else {
                z = false;
            }
            if (!(z == this.mIsReducedMargin || !(adapter instanceof PreferenceGroupAdapter) || layoutManager == null)) {
                this.mIsReducedMargin = z;
                if (getContext() != null) {
                    TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.PreferenceFragmentCompat, R$attr.preferenceFragmentCompatStyle, 0);
                    try {
                        setDivider(obtainStyledAttributes.getDrawable(R$styleable.PreferenceFragment_android_divider));
                        Parcelable onSaveInstanceState = layoutManager.onSaveInstanceState();
                        getListView().setAdapter(getListView().getAdapter());
                        layoutManager.onRestoreInstanceState(onSaveInstanceState);
                    } finally {
                        obtainStyledAttributes.recycle();
                    }
                }
            }
        }
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        int i2;
        String str;
        super.onCreate(bundle);
        TypedValue typedValue = new TypedValue();
        boolean z = true;
        requireContext().getTheme().resolveAttribute(R$attr.preferenceTheme, typedValue, true);
        Configuration configuration = getResources().getConfiguration();
        int i7 = configuration.screenWidthDp;
        if ((i7 > 320 || configuration.fontScale < 1.1f) && (i7 >= 411 || configuration.fontScale < 1.3f)) {
            i2 = 2;
        } else {
            i2 = 1;
        }
        this.mIsLargeLayout = i2;
        this.mScreenWidthDp = i7;
        if (i7 > 250) {
            z = false;
        }
        this.mIsReducedMargin = z;
        int i8 = typedValue.resourceId;
        if (i8 == 0) {
            i8 = R$style.PreferenceThemeOverlay;
        }
        requireContext().getTheme().applyStyle(i8, false);
        PreferenceManager preferenceManager = new PreferenceManager(requireContext());
        this.mPreferenceManager = preferenceManager;
        preferenceManager.setOnNavigateToScreenListener(this);
        if (getArguments() != null) {
            str = getArguments().getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT");
        } else {
            str = null;
        }
        onCreatePreferences(bundle, str);
    }

    public RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferenceScreen) {
        return new PreferenceGroupAdapter(preferenceScreen);
    }

    public RecyclerView.LayoutManager onCreateLayoutManager() {
        return new LinearLayoutManager(requireContext());
    }

    public abstract void onCreatePreferences(Bundle bundle, String str);

    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        if (requireContext().getPackageManager().hasSystemFeature("android.hardware.type.automotive") && (recyclerView = (RecyclerView) viewGroup.findViewById(R$id.recycler_view)) != null) {
            return recyclerView;
        }
        RecyclerView recyclerView2 = (RecyclerView) layoutInflater.inflate(R$layout.sesl_preference_recyclerview, viewGroup, false);
        recyclerView2.setLayoutManager(onCreateLayoutManager());
        recyclerView2.setAccessibilityDelegateCompat(new PreferenceRecyclerViewAccessibilityDelegate(recyclerView2));
        return recyclerView2;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2 = 0;
        TypedArray obtainStyledAttributes = requireContext().obtainStyledAttributes((AttributeSet) null, R$styleable.PreferenceFragmentCompat, R$attr.preferenceFragmentCompatStyle, 0);
        this.mLayoutResId = obtainStyledAttributes.getResourceId(R$styleable.PreferenceFragmentCompat_android_layout, this.mLayoutResId);
        Drawable drawable = obtainStyledAttributes.getDrawable(R$styleable.PreferenceFragmentCompat_android_divider);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.PreferenceFragmentCompat_android_dividerHeight, -1);
        boolean z = obtainStyledAttributes.getBoolean(R$styleable.PreferenceFragmentCompat_allowDividerAfterLastItem, true);
        obtainStyledAttributes.recycle();
        Context context = getContext();
        if (context != null) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes((AttributeSet) null, R$styleable.View, 16843272, 0);
            Drawable drawable2 = obtainStyledAttributes2.getDrawable(R$styleable.View_android_background);
            if (drawable2 instanceof ColorDrawable) {
                this.mSubheaderColor = ((ColorDrawable) drawable2).getColor();
            }
            obtainStyledAttributes2.recycle();
        }
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(context);
        View inflate = cloneInContext.inflate(this.mLayoutResId, viewGroup, false);
        View findViewById = inflate.findViewById(16908351);
        if (findViewById instanceof ViewGroup) {
            ViewGroup viewGroup2 = (ViewGroup) findViewById;
            RecyclerView onCreateRecyclerView = onCreateRecyclerView(cloneInContext, viewGroup2, bundle);
            if (onCreateRecyclerView != null) {
                this.mList = onCreateRecyclerView;
                if (this.mOnPreDrawListener == null) {
                    ViewTreeObserver viewTreeObserver = onCreateRecyclerView.getViewTreeObserver();
                    createOnPreDrawListner();
                    viewTreeObserver.addOnPreDrawListener(this.mOnPreDrawListener);
                }
                this.mList.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    public void onViewDetachedFromWindow(View view) {
                        view.getViewTreeObserver().removeOnPreDrawListener(PreferenceFragmentCompat.this.mOnPreDrawListener);
                        view.removeOnAttachStateChangeListener(this);
                        ViewTreeObserver.OnPreDrawListener unused = PreferenceFragmentCompat.this.mOnPreDrawListener = null;
                    }

                    public void onViewAttachedToWindow(View view) {
                    }
                });
                onCreateRecyclerView.addItemDecoration(this.mDividerDecoration);
                setDivider(drawable);
                if (dimensionPixelSize != -1) {
                    setDividerHeight(dimensionPixelSize);
                }
                this.mDividerDecoration.setAllowDividerAfterLastItem(z);
                this.mList.setItemAnimator((RecyclerView.ItemAnimator) null);
                this.mRoundedCorner = new SeslRoundedCorner(context);
                this.mSubheaderRoundedCorner = new SeslSubheaderRoundedCorner(context);
                if (this.mIsRoundedCorner) {
                    onCreateRecyclerView.seslSetFillBottomEnabled(true);
                    onCreateRecyclerView.seslSetFillBottomColor(this.mSubheaderColor);
                    SeslRoundedCorner seslRoundedCorner = new SeslRoundedCorner(context, true);
                    this.mListRoundedCorner = seslRoundedCorner;
                    seslRoundedCorner.setRoundedCorners(3);
                }
                if (this.mList.getParent() == null) {
                    viewGroup2.addView(this.mList);
                }
                this.mHandler.post(this.mRequestFocus);
                int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.sesl_preference_padding_horizontal);
                int i7 = this.mLeft;
                if (i7 < 0) {
                    i7 = dimensionPixelSize2;
                }
                int i8 = this.mTop;
                if (i8 < 0) {
                    i8 = 0;
                }
                int i10 = this.mRight;
                if (i10 >= 0) {
                    dimensionPixelSize2 = i10;
                }
                int i11 = this.mBottom;
                if (i11 >= 0) {
                    i2 = i11;
                }
                setPadding(i7, i8, dimensionPixelSize2, i2);
                return inflate;
            }
            throw new RuntimeException("Could not create RecyclerView");
        }
        throw new IllegalStateException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
    }

    public void onDestroyView() {
        RecyclerView recyclerView;
        this.mHandler.removeCallbacks(this.mRequestFocus);
        this.mHandler.removeMessages(1);
        if (this.mHavePrefs) {
            unbindPreferences();
        }
        if (!(this.mOnPreDrawListener == null || (recyclerView = this.mList) == null)) {
            recyclerView.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mList = null;
        super.onDestroyView();
    }

    public void onDisplayPreferenceDialog(Preference preference) {
        DialogFragment dialogFragment;
        getCallbackFragment();
        for (Fragment fragment = this; fragment != null; fragment = fragment.getParentFragment()) {
        }
        getContext();
        getActivity();
        if (getParentFragmentManager().findFragmentByTag("androidx.preference.PreferenceFragment.DIALOG") == null) {
            if (preference instanceof EditTextPreference) {
                dialogFragment = EditTextPreferenceDialogFragmentCompat.newInstance(preference.getKey());
            } else if (preference instanceof ListPreference) {
                dialogFragment = ListPreferenceDialogFragmentCompat.newInstance(preference.getKey());
            } else if (preference instanceof MultiSelectListPreference) {
                dialogFragment = MultiSelectListPreferenceDialogFragmentCompat.newInstance(preference.getKey());
            } else {
                throw new IllegalArgumentException("Cannot display dialog for an unknown Preference type: " + preference.getClass().getSimpleName() + ". Make sure to implement onPreferenceDisplayDialog() to handle displaying a custom dialog for this Preference.");
            }
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(getParentFragmentManager(), "androidx.preference.PreferenceFragment.DIALOG");
        }
    }

    public void onNavigateToScreen(PreferenceScreen preferenceScreen) {
        getCallbackFragment();
        for (Fragment fragment = this; fragment != null; fragment = fragment.getParentFragment()) {
        }
        getContext();
        getActivity();
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getFragment() == null) {
            return false;
        }
        getCallbackFragment();
        for (Fragment fragment = this; fragment != null; fragment = fragment.getParentFragment()) {
        }
        getContext();
        getActivity();
        Log.w("SeslPreferenceFragmentC", "onPreferenceStartFragment is not implemented in the parent activity - attempting to use a fallback implementation. You should implement this method so that you can configure the new fragment that will be displayed, and set a transition between the fragments.");
        FragmentManager parentFragmentManager = getParentFragmentManager();
        Bundle extras = preference.getExtras();
        Fragment instantiate = parentFragmentManager.getFragmentFactory().instantiate(requireActivity().getClassLoader(), preference.getFragment());
        instantiate.setArguments(extras);
        instantiate.setTargetFragment(this, 0);
        parentFragmentManager.beginTransaction().replace(((View) requireView().getParent()).getId(), instantiate).addToBackStack((String) null).commit();
        return true;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            Bundle bundle2 = new Bundle();
            preferenceScreen.saveHierarchyState(bundle2);
            bundle.putBundle("android:preferences", bundle2);
        }
    }

    public void onStart() {
        super.onStart();
        this.mPreferenceManager.setOnPreferenceTreeClickListener(this);
        this.mPreferenceManager.setOnDisplayPreferenceDialogListener(this);
    }

    public void onStop() {
        super.onStop();
        this.mPreferenceManager.setOnPreferenceTreeClickListener((PreferenceManager.OnPreferenceTreeClickListener) null);
        this.mPreferenceManager.setOnDisplayPreferenceDialogListener((PreferenceManager.OnDisplayPreferenceDialogListener) null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Bundle bundle2;
        PreferenceScreen preferenceScreen;
        super.onViewCreated(view, bundle);
        if (!(bundle == null || (bundle2 = bundle.getBundle("android:preferences")) == null || (preferenceScreen = getPreferenceScreen()) == null)) {
            preferenceScreen.restoreHierarchyState(bundle2);
        }
        if (this.mHavePrefs) {
            bindPreferences();
            Runnable runnable = this.mSelectPreferenceRunnable;
            if (runnable != null) {
                runnable.run();
                this.mSelectPreferenceRunnable = null;
            }
        }
        this.mInitDone = true;
    }

    public void seslSetRoundedCorner(boolean z) {
        this.mIsRoundedCorner = z;
    }

    public void setDivider(Drawable drawable) {
        this.mDividerDecoration.setDivider(drawable);
    }

    public void setDividerHeight(int i2) {
        this.mDividerDecoration.setDividerHeight(i2);
    }

    public void setPadding(int i2, int i7, int i8, int i10) {
        this.mLeft = i2;
        this.mTop = i7;
        this.mRight = i8;
        this.mBottom = i10;
        updatePadding();
    }

    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        if (this.mPreferenceManager.setPreferences(preferenceScreen) && preferenceScreen != null) {
            onUnbindPreferences();
            this.mHavePrefs = true;
            if (this.mInitDone) {
                postBindPreferences();
            }
        }
    }

    public void onBindPreferences() {
    }

    public void onUnbindPreferences() {
    }
}
