package androidx.preference;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.AbsSavedState;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Preference implements Comparable<Preference> {
    private boolean mAllowDividerAbove;
    private boolean mAllowDividerBelow;
    private boolean mBaseMethodCalled;
    private boolean mChangedSummaryColor;
    private boolean mChangedSummaryColorStateList;
    private final View.OnClickListener mClickListener;
    private final Context mContext;
    private boolean mCopyingEnabled;
    private Object mDefaultValue;
    private String mDependencyKey;
    private boolean mDependencyMet;
    private List<Preference> mDependents;
    private int mDividerStartOffset;
    private String mDotDescription;
    private boolean mEnabled;
    private Bundle mExtras;
    private String mFragment;
    private boolean mHasId;
    private boolean mHasSingleLineTitleAttr;
    private Drawable mIcon;
    private int mIconResId;
    private boolean mIconSpaceReserved;
    private long mId;
    private Intent mIntent;
    private boolean mIsDotVisible;
    private boolean mIsPreferenceRoundedBg;
    boolean mIsRoundChanged;
    private View mItemView;
    private String mKey;
    private int mLayoutResId;
    private OnPreferenceChangeInternalListener mListener;
    private OnPreferenceChangeListener mOnChangeListener;
    private OnPreferenceClickListener mOnClickListener;
    private OnPreferenceCopyListener mOnCopyListener;
    private int mOrder;
    private boolean mParentDependencyMet;
    private PreferenceGroup mParentGroup;
    private boolean mPersistent;
    private PreferenceManager mPreferenceManager;
    private boolean mRequiresKey;
    private boolean mSelectable;
    private boolean mShouldDisableView;
    private boolean mSingleLineTitle;
    private boolean mSubheaderRound;
    private CharSequence mSummary;
    private int mSummaryColor;
    private ColorStateList mSummaryColorStateList;
    private SummaryProvider mSummaryProvider;
    private ColorStateList mTextColorSecondary;
    private CharSequence mTitle;
    private int mViewId;
    private boolean mVisible;
    private boolean mWasDetached;
    private int mWhere;
    private int mWidgetLayoutResId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BaseSavedState extends AbsSavedState {
        public static final Parcelable.Creator<BaseSavedState> CREATOR = new Parcelable.Creator<BaseSavedState>() {
            public BaseSavedState createFromParcel(Parcel parcel) {
                return new BaseSavedState(parcel);
            }

            public BaseSavedState[] newArray(int i2) {
                return new BaseSavedState[i2];
            }
        };

        public BaseSavedState(Parcel parcel) {
            super(parcel);
        }

        public BaseSavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnPreferenceChangeInternalListener {
        void onPreferenceChange(Preference preference);

        void onPreferenceHierarchyChange(Preference preference);

        void onPreferenceVisibilityChange(Preference preference);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnPreferenceChangeListener {
        boolean onPreferenceChange(Preference preference, Object obj);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnPreferenceClickListener {
        boolean onPreferenceClick(Preference preference);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OnPreferenceCopyListener implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        private final Preference mPreference;

        public OnPreferenceCopyListener(Preference preference) {
            this.mPreference = preference;
        }

        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            CharSequence summary = this.mPreference.getSummary();
            if (this.mPreference.isCopyingEnabled() && !TextUtils.isEmpty(summary)) {
                contextMenu.setHeaderTitle(summary);
                contextMenu.add(0, 0, 0, R$string.copy).setOnMenuItemClickListener(this);
            }
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            CharSequence summary = this.mPreference.getSummary();
            ((ClipboardManager) this.mPreference.getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Preference", summary));
            Toast.makeText(this.mPreference.getContext(), this.mPreference.getContext().getString(R$string.preference_copied, new Object[]{summary}), 0).show();
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SummaryProvider<T extends Preference> {
        CharSequence provideSummary(T t);
    }

    public Preference(Context context, AttributeSet attributeSet, int i2, int i7) {
        this.mOrder = Integer.MAX_VALUE;
        this.mViewId = 0;
        this.mEnabled = true;
        this.mSelectable = true;
        this.mPersistent = true;
        this.mDependencyMet = true;
        this.mParentDependencyMet = true;
        this.mVisible = true;
        this.mAllowDividerAbove = true;
        this.mAllowDividerBelow = true;
        this.mSingleLineTitle = true;
        this.mDividerStartOffset = 0;
        this.mShouldDisableView = true;
        this.mLayoutResId = R$layout.sesl_preference;
        this.mClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                Preference.this.performClick(view);
            }
        };
        this.mIsPreferenceRoundedBg = false;
        this.mSubheaderRound = false;
        this.mWhere = 0;
        this.mIsRoundChanged = false;
        this.mChangedSummaryColor = false;
        this.mChangedSummaryColorStateList = false;
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, i2, i7);
        this.mIconResId = TypedArrayUtils.getResourceId(obtainStyledAttributes, R$styleable.Preference_icon, R$styleable.Preference_android_icon, 0);
        this.mKey = TypedArrayUtils.getString(obtainStyledAttributes, R$styleable.Preference_key, R$styleable.Preference_android_key);
        this.mTitle = TypedArrayUtils.getText(obtainStyledAttributes, R$styleable.Preference_title, R$styleable.Preference_android_title);
        this.mSummary = TypedArrayUtils.getText(obtainStyledAttributes, R$styleable.Preference_summary, R$styleable.Preference_android_summary);
        this.mOrder = TypedArrayUtils.getInt(obtainStyledAttributes, R$styleable.Preference_order, R$styleable.Preference_android_order, Integer.MAX_VALUE);
        this.mFragment = TypedArrayUtils.getString(obtainStyledAttributes, R$styleable.Preference_fragment, R$styleable.Preference_android_fragment);
        this.mLayoutResId = TypedArrayUtils.getResourceId(obtainStyledAttributes, R$styleable.Preference_layout, R$styleable.Preference_android_layout, R$layout.preference);
        this.mWidgetLayoutResId = TypedArrayUtils.getResourceId(obtainStyledAttributes, R$styleable.Preference_widgetLayout, R$styleable.Preference_android_widgetLayout, 0);
        int i8 = R$styleable.Preference_isDotVisible;
        this.mIsDotVisible = TypedArrayUtils.getBoolean(obtainStyledAttributes, i8, i8, false);
        this.mEnabled = TypedArrayUtils.getBoolean(obtainStyledAttributes, R$styleable.Preference_enabled, R$styleable.Preference_android_enabled, true);
        this.mSelectable = TypedArrayUtils.getBoolean(obtainStyledAttributes, R$styleable.Preference_selectable, R$styleable.Preference_android_selectable, true);
        this.mPersistent = TypedArrayUtils.getBoolean(obtainStyledAttributes, R$styleable.Preference_persistent, R$styleable.Preference_android_persistent, true);
        this.mDependencyKey = TypedArrayUtils.getString(obtainStyledAttributes, R$styleable.Preference_dependency, R$styleable.Preference_android_dependency);
        int i10 = R$styleable.Preference_allowDividerAbove;
        this.mAllowDividerAbove = TypedArrayUtils.getBoolean(obtainStyledAttributes, i10, i10, this.mSelectable);
        int i11 = R$styleable.Preference_allowDividerBelow;
        this.mAllowDividerBelow = TypedArrayUtils.getBoolean(obtainStyledAttributes, i11, i11, this.mSelectable);
        int i12 = R$styleable.Preference_defaultValue;
        if (obtainStyledAttributes.hasValue(i12)) {
            this.mDefaultValue = onGetDefaultValue(obtainStyledAttributes, i12);
        } else {
            int i13 = R$styleable.Preference_android_defaultValue;
            if (obtainStyledAttributes.hasValue(i13)) {
                this.mDefaultValue = onGetDefaultValue(obtainStyledAttributes, i13);
            }
        }
        this.mShouldDisableView = TypedArrayUtils.getBoolean(obtainStyledAttributes, R$styleable.Preference_shouldDisableView, R$styleable.Preference_android_shouldDisableView, true);
        int i14 = R$styleable.Preference_singleLineTitle;
        boolean hasValue = obtainStyledAttributes.hasValue(i14);
        this.mHasSingleLineTitleAttr = hasValue;
        if (hasValue) {
            this.mSingleLineTitle = TypedArrayUtils.getBoolean(obtainStyledAttributes, i14, R$styleable.Preference_android_singleLineTitle, true);
        }
        this.mIconSpaceReserved = TypedArrayUtils.getBoolean(obtainStyledAttributes, R$styleable.Preference_iconSpaceReserved, R$styleable.Preference_android_iconSpaceReserved, false);
        int i15 = R$styleable.Preference_isPreferenceVisible;
        this.mVisible = TypedArrayUtils.getBoolean(obtainStyledAttributes, i15, i15, true);
        int i16 = R$styleable.Preference_enableCopying;
        this.mCopyingEnabled = TypedArrayUtils.getBoolean(obtainStyledAttributes, i16, i16, false);
        obtainStyledAttributes.recycle();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16842808, typedValue, true);
        if (typedValue.resourceId > 0) {
            this.mTextColorSecondary = context.getResources().getColorStateList(typedValue.resourceId);
        }
    }

    private void dispatchSetInitialValue() {
        getPreferenceDataStore();
        if (!shouldPersist() || !getSharedPreferences().contains(this.mKey)) {
            Object obj = this.mDefaultValue;
            if (obj != null) {
                onSetInitialValue(false, obj);
                return;
            }
            return;
        }
        onSetInitialValue(true, (Object) null);
    }

    private void registerDependency() {
        if (!TextUtils.isEmpty(this.mDependencyKey)) {
            Preference findPreferenceInHierarchy = findPreferenceInHierarchy(this.mDependencyKey);
            if (findPreferenceInHierarchy != null) {
                findPreferenceInHierarchy.registerDependent(this);
                return;
            }
            throw new IllegalStateException("Dependency \"" + this.mDependencyKey + "\" not found for preference \"" + this.mKey + "\" (title: \"" + this.mTitle + "\"");
        }
    }

    private void registerDependent(Preference preference) {
        if (this.mDependents == null) {
            this.mDependents = new ArrayList();
        }
        this.mDependents.add(preference);
        preference.onDependencyChanged(this, shouldDisableDependents());
    }

    private void setEnabledStateOnViews(View view, boolean z) {
        view.setEnabled(z);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                setEnabledStateOnViews(viewGroup.getChildAt(childCount), z);
            }
        }
    }

    private void setLineBreakWordStyle(TextView textView) {
        if (Build.VERSION.SDK_INT >= 33) {
            textView.setLineBreakWordStyle(1);
        }
    }

    private void tryCommit(SharedPreferences.Editor editor) {
        if (this.mPreferenceManager.shouldCommit()) {
            editor.apply();
        }
    }

    private void unregisterDependency() {
        Preference findPreferenceInHierarchy;
        String str = this.mDependencyKey;
        if (str != null && (findPreferenceInHierarchy = findPreferenceInHierarchy(str)) != null) {
            findPreferenceInHierarchy.unregisterDependent(this);
        }
    }

    private void unregisterDependent(Preference preference) {
        List<Preference> list = this.mDependents;
        if (list != null) {
            list.remove(preference);
        }
    }

    public void assignParent(PreferenceGroup preferenceGroup) {
        if (preferenceGroup == null || this.mParentGroup == null) {
            this.mParentGroup = preferenceGroup;
            return;
        }
        throw new IllegalStateException("This preference already has a parent. You must remove the existing parent before assigning a new one.");
    }

    public boolean callChangeListener(Object obj) {
        OnPreferenceChangeListener onPreferenceChangeListener = this.mOnChangeListener;
        if (onPreferenceChangeListener == null || onPreferenceChangeListener.onPreferenceChange(this, obj)) {
            return true;
        }
        return false;
    }

    public void callClickListener() {
        OnPreferenceClickListener onPreferenceClickListener = this.mOnClickListener;
        if (onPreferenceClickListener != null) {
            onPreferenceClickListener.onPreferenceClick(this);
        }
    }

    public final void clearWasDetached() {
        this.mWasDetached = false;
    }

    public void dispatchRestoreInstanceState(Bundle bundle) {
        Parcelable parcelable;
        if (hasKey() && (parcelable = bundle.getParcelable(this.mKey)) != null) {
            this.mBaseMethodCalled = false;
            onRestoreInstanceState(parcelable);
            if (!this.mBaseMethodCalled) {
                throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
            }
        }
    }

    public void dispatchSaveInstanceState(Bundle bundle) {
        if (hasKey()) {
            this.mBaseMethodCalled = false;
            Parcelable onSaveInstanceState = onSaveInstanceState();
            if (!this.mBaseMethodCalled) {
                throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
            } else if (onSaveInstanceState != null) {
                bundle.putParcelable(this.mKey, onSaveInstanceState);
            }
        }
    }

    public <T extends Preference> T findPreferenceInHierarchy(String str) {
        PreferenceManager preferenceManager = this.mPreferenceManager;
        if (preferenceManager == null) {
            return null;
        }
        return preferenceManager.findPreference(str);
    }

    public Context getContext() {
        return this.mContext;
    }

    public final String getDotContentDescription() {
        return this.mDotDescription;
    }

    public final boolean getDotVisibility() {
        return this.mIsDotVisible;
    }

    public Bundle getExtras() {
        if (this.mExtras == null) {
            this.mExtras = new Bundle();
        }
        return this.mExtras;
    }

    public StringBuilder getFilterableStringBuilder() {
        StringBuilder sb2 = new StringBuilder();
        CharSequence title = getTitle();
        if (!TextUtils.isEmpty(title)) {
            sb2.append(title);
            sb2.append(' ');
        }
        CharSequence summary = getSummary();
        if (!TextUtils.isEmpty(summary)) {
            sb2.append(summary);
            sb2.append(' ');
        }
        if (sb2.length() > 0) {
            sb2.setLength(sb2.length() - 1);
        }
        return sb2;
    }

    public String getFragment() {
        return this.mFragment;
    }

    public long getId() {
        return this.mId;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public String getKey() {
        return this.mKey;
    }

    public final int getLayoutResource() {
        return this.mLayoutResId;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public PreferenceGroup getParent() {
        return this.mParentGroup;
    }

    public boolean getPersistedBoolean(boolean z) {
        if (!shouldPersist()) {
            return z;
        }
        getPreferenceDataStore();
        return this.mPreferenceManager.getSharedPreferences().getBoolean(this.mKey, z);
    }

    public int getPersistedInt(int i2) {
        if (!shouldPersist()) {
            return i2;
        }
        getPreferenceDataStore();
        return this.mPreferenceManager.getSharedPreferences().getInt(this.mKey, i2);
    }

    public String getPersistedString(String str) {
        if (!shouldPersist()) {
            return str;
        }
        getPreferenceDataStore();
        return this.mPreferenceManager.getSharedPreferences().getString(this.mKey, str);
    }

    public Set<String> getPersistedStringSet(Set<String> set) {
        if (!shouldPersist()) {
            return set;
        }
        getPreferenceDataStore();
        return this.mPreferenceManager.getSharedPreferences().getStringSet(this.mKey, set);
    }

    public PreferenceDataStore getPreferenceDataStore() {
        PreferenceManager preferenceManager = this.mPreferenceManager;
        if (preferenceManager != null) {
            preferenceManager.getPreferenceDataStore();
        }
        return null;
    }

    public PreferenceManager getPreferenceManager() {
        return this.mPreferenceManager;
    }

    public SharedPreferences getSharedPreferences() {
        if (this.mPreferenceManager == null) {
            return null;
        }
        getPreferenceDataStore();
        return this.mPreferenceManager.getSharedPreferences();
    }

    public CharSequence getSummary() {
        if (getSummaryProvider() != null) {
            return getSummaryProvider().provideSummary(this);
        }
        return this.mSummary;
    }

    public final SummaryProvider getSummaryProvider() {
        return this.mSummaryProvider;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public final int getWidgetLayoutResource() {
        return this.mWidgetLayoutResId;
    }

    public boolean hasKey() {
        return !TextUtils.isEmpty(this.mKey);
    }

    public boolean isCopyingEnabled() {
        return this.mCopyingEnabled;
    }

    public boolean isEnabled() {
        if (!this.mEnabled || !this.mDependencyMet || !this.mParentDependencyMet) {
            return false;
        }
        return true;
    }

    public boolean isPersistent() {
        return this.mPersistent;
    }

    public boolean isSelectable() {
        return this.mSelectable;
    }

    public boolean isTalkBackIsRunning() {
        String string;
        AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        if (accessibilityManager == null || !accessibilityManager.isEnabled() || (string = Settings.Secure.getString(getContext().getContentResolver(), "enabled_accessibility_services")) == null) {
            return false;
        }
        if (string.matches("(?i).*com.samsung.accessibility/com.samsung.android.app.talkback.TalkBackService.*") || string.matches("(?i).*com.samsung.android.accessibility.talkback/com.samsung.android.marvin.talkback.TalkBackService.*") || string.matches("(?i).*com.google.android.marvin.talkback.TalkBackService.*") || string.matches("(?i).*com.samsung.accessibility/com.samsung.accessibility.universalswitch.UniversalSwitchService.*")) {
            return true;
        }
        return false;
    }

    public final boolean isVisible() {
        return this.mVisible;
    }

    public void notifyChanged() {
        OnPreferenceChangeInternalListener onPreferenceChangeInternalListener = this.mListener;
        if (onPreferenceChangeInternalListener != null) {
            onPreferenceChangeInternalListener.onPreferenceChange(this);
        }
    }

    public void notifyDependencyChange(boolean z) {
        List<Preference> list = this.mDependents;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                list.get(i2).onDependencyChanged(this, z);
            }
        }
    }

    public void notifyHierarchyChanged() {
        OnPreferenceChangeInternalListener onPreferenceChangeInternalListener = this.mListener;
        if (onPreferenceChangeInternalListener != null) {
            onPreferenceChangeInternalListener.onPreferenceHierarchyChange(this);
        }
    }

    public void onAttached() {
        registerDependency();
    }

    public void onAttachedToHierarchy(PreferenceManager preferenceManager) {
        this.mPreferenceManager = preferenceManager;
        if (!this.mHasId) {
            this.mId = preferenceManager.getNextId();
        }
        dispatchSetInitialValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0174  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(androidx.preference.PreferenceViewHolder r9) {
        /*
            r8 = this;
            android.view.View r0 = r9.itemView
            android.view.View$OnClickListener r1 = r8.mClickListener
            r0.setOnClickListener(r1)
            int r1 = r8.mViewId
            r0.setId(r1)
            r1 = 16908304(0x1020010, float:2.3877274E-38)
            android.view.View r1 = r9.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            r2 = 8
            r3 = 0
            r4 = 0
            if (r1 == 0) goto L_0x007d
            java.lang.CharSequence r5 = r8.getSummary()
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x007a
            r1.setText(r5)
            r8.setLineBreakWordStyle(r1)
            boolean r5 = r8.mChangedSummaryColor
            java.lang.String r6 = "SeslPreference"
            if (r5 == 0) goto L_0x004a
            int r5 = r8.mSummaryColor
            r1.setTextColor(r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "set Summary Color : "
            r5.<init>(r7)
            int r7 = r8.mSummaryColor
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r6, r5)
            goto L_0x006e
        L_0x004a:
            boolean r5 = r8.mChangedSummaryColorStateList
            if (r5 == 0) goto L_0x0067
            android.content.res.ColorStateList r5 = r8.mSummaryColorStateList
            r1.setTextColor(r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "set Summary ColorStateList : "
            r5.<init>(r7)
            android.content.res.ColorStateList r7 = r8.mSummaryColorStateList
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r6, r5)
            goto L_0x006e
        L_0x0067:
            android.content.res.ColorStateList r5 = r8.mTextColorSecondary
            if (r5 == 0) goto L_0x006e
            r1.setTextColor(r5)
        L_0x006e:
            r1.setVisibility(r3)
            int r1 = r1.getCurrentTextColor()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            goto L_0x007e
        L_0x007a:
            r1.setVisibility(r2)
        L_0x007d:
            r1 = r4
        L_0x007e:
            int r5 = r8.mDividerStartOffset
            r9.seslSetDividerStartOffset(r5)
            boolean r5 = r8.mIsPreferenceRoundedBg
            int r6 = r8.mWhere
            boolean r7 = r8.mSubheaderRound
            r9.setPreferenceBackgroundType(r5, r6, r7)
            r5 = 16908310(0x1020016, float:2.387729E-38)
            android.view.View r5 = r9.findViewById(r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x00e0
            java.lang.CharSequence r6 = r8.getTitle()
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 != 0) goto L_0x00c6
            r5.setText(r6)
            r5.setVisibility(r3)
            boolean r6 = r8.mHasSingleLineTitleAttr
            if (r6 == 0) goto L_0x00b0
            boolean r6 = r8.mSingleLineTitle
            r5.setSingleLine(r6)
        L_0x00b0:
            boolean r6 = r8.isSelectable()
            if (r6 != 0) goto L_0x00e0
            boolean r6 = r8.isEnabled()
            if (r6 == 0) goto L_0x00e0
            if (r1 == 0) goto L_0x00e0
            int r1 = r1.intValue()
            r5.setTextColor(r1)
            goto L_0x00e0
        L_0x00c6:
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 == 0) goto L_0x00dd
            boolean r1 = r8 instanceof androidx.preference.PreferenceCategory
            if (r1 == 0) goto L_0x00dd
            r5.setVisibility(r3)
            boolean r1 = r8.mHasSingleLineTitleAttr
            if (r1 == 0) goto L_0x00e0
            boolean r1 = r8.mSingleLineTitle
            r5.setSingleLine(r1)
            goto L_0x00e0
        L_0x00dd:
            r5.setVisibility(r2)
        L_0x00e0:
            r1 = 16908294(0x1020006, float:2.3877246E-38)
            android.view.View r1 = r9.findViewById(r1)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            r5 = 4
            if (r1 == 0) goto L_0x0119
            int r6 = r8.mIconResId
            if (r6 != 0) goto L_0x00f4
            android.graphics.drawable.Drawable r7 = r8.mIcon
            if (r7 == 0) goto L_0x0107
        L_0x00f4:
            android.graphics.drawable.Drawable r7 = r8.mIcon
            if (r7 != 0) goto L_0x0100
            android.content.Context r7 = r8.mContext
            android.graphics.drawable.Drawable r6 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r7, r6)
            r8.mIcon = r6
        L_0x0100:
            android.graphics.drawable.Drawable r6 = r8.mIcon
            if (r6 == 0) goto L_0x0107
            r1.setImageDrawable(r6)
        L_0x0107:
            android.graphics.drawable.Drawable r6 = r8.mIcon
            if (r6 == 0) goto L_0x010f
            r1.setVisibility(r3)
            goto L_0x0119
        L_0x010f:
            boolean r6 = r8.mIconSpaceReserved
            if (r6 == 0) goto L_0x0115
            r6 = r5
            goto L_0x0116
        L_0x0115:
            r6 = r2
        L_0x0116:
            r1.setVisibility(r6)
        L_0x0119:
            int r1 = androidx.preference.R$id.icon_frame
            android.view.View r1 = r9.findViewById(r1)
            if (r1 != 0) goto L_0x0128
            r1 = 16908350(0x102003e, float:2.3877403E-38)
            android.view.View r1 = r9.findViewById(r1)
        L_0x0128:
            if (r1 == 0) goto L_0x013a
            android.graphics.drawable.Drawable r6 = r8.mIcon
            if (r6 == 0) goto L_0x0132
            r1.setVisibility(r3)
            goto L_0x013a
        L_0x0132:
            boolean r3 = r8.mIconSpaceReserved
            if (r3 == 0) goto L_0x0137
            r2 = r5
        L_0x0137:
            r1.setVisibility(r2)
        L_0x013a:
            boolean r1 = r8.mShouldDisableView
            if (r1 == 0) goto L_0x0146
            boolean r1 = r8.isEnabled()
            r8.setEnabledStateOnViews(r0, r1)
            goto L_0x014a
        L_0x0146:
            r1 = 1
            r8.setEnabledStateOnViews(r0, r1)
        L_0x014a:
            boolean r1 = r8.isSelectable()
            r0.setFocusable(r1)
            r0.setClickable(r1)
            boolean r2 = r8.mAllowDividerAbove
            r9.setDividerAllowedAbove(r2)
            boolean r2 = r8.mAllowDividerBelow
            r9.setDividerAllowedBelow(r2)
            boolean r9 = r8.isCopyingEnabled()
            if (r9 == 0) goto L_0x016f
            androidx.preference.Preference$OnPreferenceCopyListener r2 = r8.mOnCopyListener
            if (r2 != 0) goto L_0x016f
            androidx.preference.Preference$OnPreferenceCopyListener r2 = new androidx.preference.Preference$OnPreferenceCopyListener
            r2.<init>(r8)
            r8.mOnCopyListener = r2
        L_0x016f:
            if (r9 == 0) goto L_0x0174
            androidx.preference.Preference$OnPreferenceCopyListener r2 = r8.mOnCopyListener
            goto L_0x0175
        L_0x0174:
            r2 = r4
        L_0x0175:
            r0.setOnCreateContextMenuListener(r2)
            r0.setLongClickable(r9)
            if (r9 == 0) goto L_0x0182
            if (r1 != 0) goto L_0x0182
            androidx.core.view.ViewCompat.setBackground(r0, r4)
        L_0x0182:
            r8.mItemView = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.Preference.onBindViewHolder(androidx.preference.PreferenceViewHolder):void");
    }

    public void onDependencyChanged(Preference preference, boolean z) {
        if (this.mDependencyMet == z) {
            this.mDependencyMet = !z;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public void onDetached() {
        unregisterDependency();
        this.mWasDetached = true;
    }

    public Object onGetDefaultValue(TypedArray typedArray, int i2) {
        return null;
    }

    public void onParentChanged(Preference preference, boolean z) {
        if (this.mParentDependencyMet == z) {
            this.mParentDependencyMet = !z;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public void onPrepareForRemoval() {
        unregisterDependency();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        this.mBaseMethodCalled = true;
        if (parcelable != AbsSavedState.EMPTY_STATE && parcelable != null) {
            throw new IllegalArgumentException("Wrong state class -- expecting Preference State");
        }
    }

    public Parcelable onSaveInstanceState() {
        this.mBaseMethodCalled = true;
        return AbsSavedState.EMPTY_STATE;
    }

    public void onSetInitialValue(Object obj) {
    }

    public void performClick(View view) {
        performClick();
    }

    public boolean persistBoolean(boolean z) {
        if (!shouldPersist()) {
            return false;
        }
        if (z == getPersistedBoolean(!z)) {
            return true;
        }
        getPreferenceDataStore();
        SharedPreferences.Editor editor = this.mPreferenceManager.getEditor();
        editor.putBoolean(this.mKey, z);
        tryCommit(editor);
        return true;
    }

    public boolean persistInt(int i2) {
        if (!shouldPersist()) {
            return false;
        }
        if (i2 == getPersistedInt(~i2)) {
            return true;
        }
        getPreferenceDataStore();
        SharedPreferences.Editor editor = this.mPreferenceManager.getEditor();
        editor.putInt(this.mKey, i2);
        tryCommit(editor);
        return true;
    }

    public boolean persistString(String str) {
        if (!shouldPersist()) {
            return false;
        }
        if (TextUtils.equals(str, getPersistedString((String) null))) {
            return true;
        }
        getPreferenceDataStore();
        SharedPreferences.Editor editor = this.mPreferenceManager.getEditor();
        editor.putString(this.mKey, str);
        tryCommit(editor);
        return true;
    }

    public boolean persistStringSet(Set<String> set) {
        if (!shouldPersist()) {
            return false;
        }
        if (set.equals(getPersistedStringSet((Set<String>) null))) {
            return true;
        }
        getPreferenceDataStore();
        SharedPreferences.Editor editor = this.mPreferenceManager.getEditor();
        editor.putStringSet(this.mKey, set);
        tryCommit(editor);
        return true;
    }

    public void requireKey() {
        if (!TextUtils.isEmpty(this.mKey)) {
            this.mRequiresKey = true;
            return;
        }
        throw new IllegalStateException("Preference does not have a key assigned.");
    }

    public void restoreHierarchyState(Bundle bundle) {
        dispatchRestoreInstanceState(bundle);
    }

    public void saveHierarchyState(Bundle bundle) {
        dispatchSaveInstanceState(bundle);
    }

    public void seslSetDividerStartOffset(int i2) {
        this.mDividerStartOffset = i2;
    }

    public void seslSetSubheaderRoundedBackground(int i2) {
        this.mIsPreferenceRoundedBg = true;
        this.mWhere = i2;
        this.mSubheaderRound = true;
        this.mIsRoundChanged = true;
    }

    public void seslSetSummaryColor(int i2) {
        this.mSummaryColor = i2;
        this.mChangedSummaryColor = true;
        this.mChangedSummaryColorStateList = false;
    }

    public void setDefaultValue(Object obj) {
        this.mDefaultValue = obj;
    }

    public void setDotVisibility(boolean z) {
        if (this.mIsDotVisible != z) {
            this.mIsDotVisible = z;
            notifyChanged();
        }
    }

    public void setEnabled(boolean z) {
        if (this.mEnabled != z) {
            this.mEnabled = z;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.mIcon != drawable) {
            this.mIcon = drawable;
            this.mIconResId = 0;
            notifyChanged();
        }
    }

    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }

    public void setKey(String str) {
        this.mKey = str;
        if (this.mRequiresKey && !hasKey()) {
            requireKey();
        }
    }

    public void setLayoutResource(int i2) {
        this.mLayoutResId = i2;
    }

    public final void setOnPreferenceChangeInternalListener(OnPreferenceChangeInternalListener onPreferenceChangeInternalListener) {
        this.mListener = onPreferenceChangeInternalListener;
    }

    public void setOnPreferenceChangeListener(OnPreferenceChangeListener onPreferenceChangeListener) {
        this.mOnChangeListener = onPreferenceChangeListener;
    }

    public void setOnPreferenceClickListener(OnPreferenceClickListener onPreferenceClickListener) {
        this.mOnClickListener = onPreferenceClickListener;
    }

    public void setOrder(int i2) {
        if (i2 != this.mOrder) {
            this.mOrder = i2;
            notifyHierarchyChanged();
        }
    }

    public void setSummary(CharSequence charSequence) {
        if (getSummaryProvider() != null) {
            throw new IllegalStateException("Preference already has a SummaryProvider set.");
        } else if (!TextUtils.equals(this.mSummary, charSequence)) {
            this.mSummary = charSequence;
            notifyChanged();
        }
    }

    public final void setSummaryProvider(SummaryProvider summaryProvider) {
        this.mSummaryProvider = summaryProvider;
        notifyChanged();
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.equals(charSequence, this.mTitle)) {
            this.mTitle = charSequence;
            notifyChanged();
        }
    }

    public final void setVisible(boolean z) {
        if (this.mVisible != z) {
            this.mVisible = z;
            OnPreferenceChangeInternalListener onPreferenceChangeInternalListener = this.mListener;
            if (onPreferenceChangeInternalListener != null) {
                onPreferenceChangeInternalListener.onPreferenceVisibilityChange(this);
            }
        }
    }

    public void setWidgetLayoutResource(int i2) {
        this.mWidgetLayoutResId = i2;
    }

    public boolean shouldDisableDependents() {
        return !isEnabled();
    }

    public boolean shouldPersist() {
        if (this.mPreferenceManager == null || !isPersistent() || !hasKey()) {
            return false;
        }
        return true;
    }

    public String toString() {
        return getFilterableStringBuilder().toString();
    }

    public int compareTo(Preference preference) {
        int i2 = this.mOrder;
        int i7 = preference.mOrder;
        if (i2 != i7) {
            return i2 - i7;
        }
        CharSequence charSequence = this.mTitle;
        CharSequence charSequence2 = preference.mTitle;
        if (charSequence == charSequence2) {
            return 0;
        }
        if (charSequence == null) {
            return 1;
        }
        if (charSequence2 == null) {
            return -1;
        }
        return charSequence.toString().compareToIgnoreCase(preference.mTitle.toString());
    }

    @Deprecated
    public void onSetInitialValue(boolean z, Object obj) {
        onSetInitialValue(obj);
    }

    public void performClick() {
        PreferenceManager.OnPreferenceTreeClickListener onPreferenceTreeClickListener;
        if (isEnabled() && isSelectable()) {
            onClick();
            OnPreferenceClickListener onPreferenceClickListener = this.mOnClickListener;
            if (onPreferenceClickListener == null || !onPreferenceClickListener.onPreferenceClick(this)) {
                PreferenceManager preferenceManager = getPreferenceManager();
                if ((preferenceManager == null || (onPreferenceTreeClickListener = preferenceManager.getOnPreferenceTreeClickListener()) == null || !onPreferenceTreeClickListener.onPreferenceTreeClick(this)) && this.mIntent != null) {
                    getContext().startActivity(this.mIntent);
                }
            }
        }
    }

    public void setTitle(int i2) {
        setTitle((CharSequence) this.mContext.getString(i2));
    }

    public void onAttachedToHierarchy(PreferenceManager preferenceManager, long j2) {
        this.mId = j2;
        this.mHasId = true;
        try {
            onAttachedToHierarchy(preferenceManager);
        } finally {
            this.mHasId = false;
        }
    }

    public void setIcon(int i2) {
        setIcon(AppCompatResources.getDrawable(this.mContext, i2));
        this.mIconResId = i2;
    }

    public void setSummary(int i2) {
        setSummary((CharSequence) this.mContext.getString(i2));
    }

    public void onClick() {
    }

    @Deprecated
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    public Preference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public Preference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.preferenceStyle, 16842894));
    }

    public Preference(Context context) {
        this(context, (AttributeSet) null);
    }
}
