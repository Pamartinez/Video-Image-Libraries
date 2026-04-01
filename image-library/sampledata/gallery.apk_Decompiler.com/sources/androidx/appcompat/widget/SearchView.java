package androidx.appcompat.widget;

import B2.C0000a;
import Fb.h;
import N2.j;
import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.oneui.common.BlurSupportable;
import androidx.appcompat.oneui.common.internal.policy.BlurInfoState;
import androidx.appcompat.oneui.common.internal.semblurinfo.SemBlurInfoStateBuilder;
import androidx.appcompat.util.SeslMisc;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.core.content.pm.PackageInfoCompat;
import androidx.core.oneui.common.internal.semblurinfo.SemBlurInfoState;
import androidx.core.view.ViewCompat;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.customview.view.AbsSavedState;
import androidx.reflect.os.SeslBuildReflector$SeslVersionReflector;
import androidx.reflect.view.inputmethod.SeslInputMethodManagerReflector;
import androidx.reflect.widget.SeslTextViewReflector;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.settings.widget.LabsSearchDelegate;
import com.samsung.android.ocr.MOCRLang;
import e5.C0451a;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.WeakHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchView extends LinearLayoutCompat implements CollapsibleActionView, BlurSupportable {
    private static final String AUTHORITY_SVI_APP;
    private static final String KEY_SVI_APP_LOCALE;
    static final PreQAutoCompleteTextViewReflector PRE_API_29_HIDDEN_METHOD_INVOKER = null;
    private static final String SVI_ACTION;
    private static final String SVI_PACKAGE;
    private Bundle mAppSearchData;
    final ImageView mBackButton;
    private SemBlurInfoState mBlurInfo;
    private int mBlurMode;
    private Typeface mBoldTypeface;
    private boolean mClearingFocus;
    final ImageView mCloseButton;
    private int mCollapsedImeOptions;
    /* access modifiers changed from: private */
    public Context mContext;
    private final CharSequence mDefaultQueryHint;
    private final View mDropDownAnchor;
    private boolean mExpandedInActionView;
    final Drawable mGVIVoiceIcon;
    final ImageView mGoButton;
    private boolean mIconified;
    private boolean mIconifiedByDefault;
    private InputMethodManager mImm;
    private final boolean mIsBlurApplied;
    private final boolean mIsBottomSearchView;
    private boolean mIsSearchMagIconButtonEnabled;
    private int mMaxWidth;
    final ImageView mMoreButton;
    private CharSequence mOldQueryText;
    private final View.OnClickListener mOnClickListener;
    private OnCloseListener mOnCloseListener;
    private final TextView.OnEditorActionListener mOnEditorActionListener;
    private final AdapterView.OnItemClickListener mOnItemClickListener;
    private final AdapterView.OnItemSelectedListener mOnItemSelectedListener;
    private OnQueryTextListener mOnQueryChangeListener;
    View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
    private View.OnClickListener mOnSearchClickListener;
    private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
    private CharSequence mQueryHint;
    private boolean mQueryRefinement;
    private Runnable mReleaseCursorRunnable;
    final Drawable mSVIVoiceIcon;
    private final Intent mSVoiceSearchIntent;
    final ImageView mSearchButton;
    private final View mSearchEditFrame;
    private final Drawable mSearchHintIcon;
    private int mSearchIconResId;
    /* access modifiers changed from: private */
    public final ImageView mSearchMagIconButton;
    private final View mSearchPlate;
    private final View mSearchPlateFrame;
    private final View mSearchPlateStroke;
    final SearchAutoComplete mSearchSrcTextView;
    private Rect mSearchSrcTextViewBounds;
    private Rect mSearchSrtTextViewBoundsExpanded;
    SearchableInfo mSearchable;
    private final View mSubmitArea;
    private boolean mSubmitButtonEnabled;
    private final int mSuggestionCommitIconResId;
    private final int mSuggestionRowLayout;
    CursorAdapter mSuggestionsAdapter;
    private int[] mTemp;
    private int[] mTemp2;
    View.OnKeyListener mTextKeyListener;
    private TextWatcher mTextWatcher;
    private UpdatableTouchDelegate mTouchDelegate;
    private final Runnable mUpdateDrawableStateRunnable;
    private boolean mUseSVI;
    private CharSequence mUserQuery;
    private final Intent mVoiceAppSearchIntent;
    final ImageView mVoiceButton;
    private boolean mVoiceButtonEnabled;
    private final Intent mVoiceWebSearchIntent;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api29Impl {
        public static void refreshAutoCompleteResults(AutoCompleteTextView autoCompleteTextView) {
            autoCompleteTextView.refreshAutoCompleteResults();
        }

        public static void setInputMethodMode(SearchAutoComplete searchAutoComplete, int i2) {
            searchAutoComplete.setInputMethodMode(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnCloseListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnQueryTextListener {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnSuggestionListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PreQAutoCompleteTextViewReflector {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }

            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        };
        boolean isIconified;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("SearchView.SavedState{");
            sb2.append(Integer.toHexString(System.identityHashCode(this)));
            sb2.append(" isIconified=");
            return j.h(sb2, this.isIconified, "}");
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeValue(Boolean.valueOf(this.isIconified));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.isIconified = ((Boolean) parcel.readValue((ClassLoader) null)).booleanValue();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        private boolean mForceNotCallShowSoftInput;
        private boolean mHasPendingShowSoftInputRequest;
        final Runnable mRunShowSoftInputIfNecessary;
        private SearchView mSearchView;
        private int mThreshold;

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i2 = configuration.screenWidthDp;
            int i7 = configuration.screenHeightDp;
            if (i2 >= 960 && i7 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i2 >= 600) {
                return 192;
            }
            if (i2 < 640 || i7 < 480) {
                return MOCRLang.GURMUKHI;
            }
            return 192;
        }

        public boolean enoughToFilter() {
            if (this.mThreshold <= 0 || super.enoughToFilter()) {
                return true;
            }
            return false;
        }

        public void ensureImeVisible() {
            Api29Impl.setInputMethodMode(this, 1);
            if (getFilter() != null && enoughToFilter()) {
                showDropDown();
            }
        }

        public boolean isEmpty() {
            if (TextUtils.getTrimmedLength(getText()) == 0) {
                return true;
            }
            return false;
        }

        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.mHasPendingShowSoftInputRequest) {
                removeCallbacks(this.mRunShowSoftInputIfNecessary);
                post(this.mRunShowSoftInputIfNecessary);
            }
            return onCreateInputConnection;
        }

        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, (float) getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        public void onFocusChanged(boolean z, int i2, Rect rect) {
            super.onFocusChanged(z, i2, rect);
            this.mSearchView.onTextFocusChanged();
        }

        public boolean onKeyPreIme(int i2, KeyEvent keyEvent) {
            return super.onKeyPreIme(i2, keyEvent);
        }

        public boolean onPrivateIMECommand(String str, Bundle bundle) {
            return super.onPrivateIMECommand(str, bundle);
        }

        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.mSearchView.hasFocus() && getVisibility() == 0) {
                this.mHasPendingShowSoftInputRequest = true;
                if (SearchView.isLandscapeMode(getContext())) {
                    ensureImeVisible();
                }
            }
        }

        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.mHasPendingShowSoftInputRequest = false;
                removeCallbacks(this.mRunShowSoftInputIfNecessary);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this)) {
                this.mHasPendingShowSoftInputRequest = false;
                removeCallbacks(this.mRunShowSoftInputIfNecessary);
                inputMethodManager.showSoftInput(this, 0);
            } else {
                this.mHasPendingShowSoftInputRequest = true;
            }
        }

        public void setNotCallShowSoftInput(boolean z) {
            this.mForceNotCallShowSoftInput = z;
        }

        public void setSearchView(SearchView searchView) {
            this.mSearchView = searchView;
        }

        public void setThreshold(int i2) {
            super.setThreshold(i2);
            this.mThreshold = i2;
        }

        public void showSoftInputIfNecessary() {
            if (!this.mForceNotCallShowSoftInput && this.mHasPendingShowSoftInputRequest) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.mHasPendingShowSoftInputRequest = false;
            }
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            this.mRunShowSoftInputIfNecessary = new Runnable() {
                public void run() {
                    SearchAutoComplete.this.showSoftInputIfNecessary();
                }
            };
            this.mThreshold = getThreshold();
        }

        public void performCompletion() {
        }

        public void replaceText(CharSequence charSequence) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SeslSearchViewStyle {
        LIGHT_WITH_BACKGROUND(r3, r4, r5, r6),
        LIGHT_WITHOUT_BACKGROUND(r10, r11, r12, r19),
        DARK_WITH_BACKGROUND(R$color.sesl_search_view_background_text_color_dark, R$color.sesl_search_view_background_hint_text_color_dark, R$color.sesl_search_view_background_icon_color_dark, r6),
        DARK_WITHOUT_BACKGROUND(R$color.sesl_search_view_text_color_dark, R$color.sesl_search_view_hint_text_color_dark, R$color.sesl_search_view_icon_color_dark, r19);
        
        private final int mHintTextColorRes;
        private final int mIconColorRes;
        private final int mTextColorRes;
        /* access modifiers changed from: private */
        public final int mTextSizeRes;

        private SeslSearchViewStyle(int i2, int i7, int i8, int i10) {
            this.mTextColorRes = i2;
            this.mHintTextColorRes = i7;
            this.mIconColorRes = i8;
            this.mTextSizeRes = i10;
        }

        public static SeslSearchViewStyle create(Context context, boolean z) {
            return (SeslMisc.isLightTheme(context) ? new SeslSearchViewStyle[]{LIGHT_WITH_BACKGROUND, LIGHT_WITHOUT_BACKGROUND} : new SeslSearchViewStyle[]{DARK_WITH_BACKGROUND, DARK_WITHOUT_BACKGROUND})[!z];
        }

        public void apply(Resources resources, TextView textView, List<ImageView> list) {
            Log.d("SearchView", "[SeslSearchViewStyle] apply " + this);
            textView.setTextColor(resources.getColor(this.mTextColorRes));
            textView.setHintTextColor(resources.getColor(this.mHintTextColorRes));
            textView.setTextSize(0, (float) resources.getDimensionPixelSize(this.mTextSizeRes));
            for (ImageView colorFilter : list) {
                colorFilter.setColorFilter(resources.getColor(this.mIconColorRes));
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UpdatableTouchDelegate extends TouchDelegate {
        private final Rect mActualBounds = new Rect();
        private boolean mDelegateTargeted;
        private final View mDelegateView;
        private final int mSlop;
        private final Rect mSlopBounds = new Rect();
        private final Rect mTargetBounds = new Rect();

        public UpdatableTouchDelegate(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.mSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            setBounds(rect, rect2);
            this.mDelegateView = view;
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x0042  */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x0074 A[RETURN] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTouchEvent(android.view.MotionEvent r9) {
            /*
                r8 = this;
                float r0 = r9.getX()
                int r0 = (int) r0
                float r1 = r9.getY()
                int r1 = (int) r1
                int r2 = r9.getAction()
                r3 = 2
                r4 = 0
                r5 = 1
                if (r2 == 0) goto L_0x0032
                if (r2 == r5) goto L_0x0023
                if (r2 == r3) goto L_0x0023
                r6 = 3
                if (r2 == r6) goto L_0x001b
                goto L_0x003e
            L_0x001b:
                boolean r2 = r8.mDelegateTargeted
                r8.mDelegateTargeted = r4
            L_0x001f:
                r7 = r5
                r5 = r2
                r2 = r7
                goto L_0x0040
            L_0x0023:
                boolean r2 = r8.mDelegateTargeted
                if (r2 == 0) goto L_0x001f
                android.graphics.Rect r6 = r8.mSlopBounds
                boolean r6 = r6.contains(r0, r1)
                if (r6 != 0) goto L_0x001f
                r5 = r2
                r2 = r4
                goto L_0x0040
            L_0x0032:
                android.graphics.Rect r2 = r8.mTargetBounds
                boolean r2 = r2.contains(r0, r1)
                if (r2 == 0) goto L_0x003e
                r8.mDelegateTargeted = r5
                r2 = r5
                goto L_0x0040
            L_0x003e:
                r2 = r5
                r5 = r4
            L_0x0040:
                if (r5 == 0) goto L_0x0074
                if (r2 == 0) goto L_0x0060
                android.graphics.Rect r2 = r8.mActualBounds
                boolean r2 = r2.contains(r0, r1)
                if (r2 != 0) goto L_0x0060
                android.view.View r0 = r8.mDelegateView
                int r0 = r0.getWidth()
                int r0 = r0 / r3
                float r0 = (float) r0
                android.view.View r1 = r8.mDelegateView
                int r1 = r1.getHeight()
                int r1 = r1 / r3
                float r1 = (float) r1
                r9.setLocation(r0, r1)
                goto L_0x006d
            L_0x0060:
                android.graphics.Rect r2 = r8.mActualBounds
                int r3 = r2.left
                int r0 = r0 - r3
                float r0 = (float) r0
                int r2 = r2.top
                int r1 = r1 - r2
                float r1 = (float) r1
                r9.setLocation(r0, r1)
            L_0x006d:
                android.view.View r8 = r8.mDelegateView
                boolean r8 = r8.dispatchTouchEvent(r9)
                return r8
            L_0x0074:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.UpdatableTouchDelegate.onTouchEvent(android.view.MotionEvent):boolean");
        }

        public void setBounds(Rect rect, Rect rect2) {
            this.mTargetBounds.set(rect);
            this.mSlopBounds.set(rect);
            Rect rect3 = this.mSlopBounds;
            int i2 = this.mSlop;
            rect3.inset(-i2, -i2);
            this.mActualBounds.set(rect2);
        }
    }

    static {
        String str;
        String str2;
        String str3;
        String str4;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 33) {
            str = "com.samsung.android.svoiceime";
        } else {
            str = "com.samsung.android.honeyboard";
        }
        SVI_PACKAGE = str;
        if (i2 < 33) {
            str2 = "samsung.svoiceime.action.RECOGNIZE_SPEECH";
        } else {
            str2 = "samsung.honeyboard.honeyvoice.action.RECOGNIZE_SPEECH";
        }
        SVI_ACTION = str2;
        if (i2 < 33) {
            str3 = "com.samsung.android.svoiceime.provider";
        } else {
            str3 = "com.samsung.android.honeyboard.provider.VoiceLanguageListProvider";
        }
        AUTHORITY_SVI_APP = str3;
        if (i2 < 33) {
            str4 = "is_svoice_locale_supported";
        } else {
            str4 = "is_honeyvoice_locale_supported";
        }
        KEY_SVI_APP_LOCALE = str4;
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.searchViewStyle);
    }

    private void applyBottomSearchPlateStyle(Resources resources) {
        View view;
        if (this.mIsBottomSearchView && (view = this.mSearchPlateFrame) != null && this.mSearchPlate != null && this.mSearchPlateStroke != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.sesl_search_bottom_horizontal_margin);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                if (!(marginLayoutParams.getMarginStart() == dimensionPixelSize && marginLayoutParams.getMarginEnd() == dimensionPixelSize)) {
                    marginLayoutParams.setMarginStart(dimensionPixelSize);
                    marginLayoutParams.setMarginEnd(dimensionPixelSize);
                    this.mSearchPlateFrame.setLayoutParams(marginLayoutParams);
                }
            } else {
                Log.w("SearchView", "SearchPlateFrame's LayoutParams are not an instance of MarginLayoutParams.");
            }
            float dimension = resources.getDimension(R$dimen.sesl_figma_elevation_md);
            this.mSearchPlate.setElevation(dimension);
            this.mSearchPlateStroke.setElevation(dimension);
            SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
            searchAutoComplete.setImeOptions(searchAutoComplete.getImeOptions() | 268435456);
            setIconifiedByDefault(false);
            seslSetSearchMagIconVisibility(0);
            this.mSearchPlateStroke.setVisibility(0);
        }
    }

    private void applySearchViewBlur() {
        if (this.mIsBlurApplied && this.mSearchPlate != null) {
            applyBlurInfo(getContext());
        }
    }

    private Intent createIntent(String str, Uri uri, String str2, String str3, int i2, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.mUserQuery);
        if (str3 != null) {
            intent.putExtra(Contract.QUERY, str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.mAppSearchData;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i2 != 0) {
            intent.putExtra("action_key", i2);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.mSearchable.getSearchActivity());
        return intent;
    }

    private Intent createIntentFromSuggestion(Cursor cursor, int i2, String str) {
        int i7;
        Uri uri;
        String columnString;
        try {
            String columnString2 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_action");
            if (columnString2 == null) {
                columnString2 = this.mSearchable.getSuggestIntentAction();
            }
            if (columnString2 == null) {
                columnString2 = "android.intent.action.SEARCH";
            }
            String str2 = columnString2;
            String columnString3 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_data");
            if (columnString3 == null) {
                columnString3 = this.mSearchable.getSuggestIntentData();
            }
            if (!(columnString3 == null || (columnString = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_data_id")) == null)) {
                columnString3 = columnString3 + "/" + Uri.encode(columnString);
            }
            if (columnString3 == null) {
                uri = null;
            } else {
                uri = Uri.parse(columnString3);
            }
            return createIntent(str2, uri, SuggestionsAdapter.getColumnString(cursor, "suggest_intent_extra_data"), SuggestionsAdapter.getColumnString(cursor, "suggest_intent_query"), i2, str);
        } catch (RuntimeException e) {
            RuntimeException runtimeException = e;
            try {
                i7 = cursor.getPosition();
            } catch (RuntimeException unused) {
                i7 = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + i7 + " returned exception.", runtimeException);
            return null;
        }
    }

    private Intent createSVoiceSearchIntent(Intent intent, SearchableInfo searchableInfo) {
        PendingIntent pendingIntent;
        String str;
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        if (Build.VERSION.SDK_INT >= 31) {
            pendingIntent = PendingIntent.getActivity(getContext(), 0, intent2, 1107296256);
        } else {
            pendingIntent = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        }
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.mAppSearchData;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        if (searchActivity == null) {
            str = null;
        } else {
            str = searchActivity.flattenToShortString();
        }
        intent3.putExtra("calling_package", str);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", pendingIntent);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    private Intent createVoiceAppSearchIntent(Intent intent, SearchableInfo searchableInfo) {
        PendingIntent pendingIntent;
        String str;
        String str2;
        String str3;
        int i2;
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        if (Build.VERSION.SDK_INT >= 31) {
            pendingIntent = PendingIntent.getActivity(getContext(), 0, intent2, 1107296256);
        } else {
            pendingIntent = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        }
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.mAppSearchData;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        if (searchableInfo.getVoiceLanguageModeId() != 0) {
            str = resources.getString(searchableInfo.getVoiceLanguageModeId());
        } else {
            str = "free_form";
        }
        String str4 = null;
        if (searchableInfo.getVoicePromptTextId() != 0) {
            str2 = resources.getString(searchableInfo.getVoicePromptTextId());
        } else {
            str2 = null;
        }
        if (searchableInfo.getVoiceLanguageId() != 0) {
            str3 = resources.getString(searchableInfo.getVoiceLanguageId());
        } else {
            str3 = null;
        }
        if (searchableInfo.getVoiceMaxResults() != 0) {
            i2 = searchableInfo.getVoiceMaxResults();
        } else {
            i2 = 1;
        }
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", str);
        intent3.putExtra("android.speech.extra.PROMPT", str2);
        intent3.putExtra("android.speech.extra.LANGUAGE", str3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", i2);
        if (searchActivity != null) {
            str4 = searchActivity.flattenToShortString();
        }
        intent3.putExtra("calling_package", str4);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", pendingIntent);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    private Intent createVoiceWebSearchIntent(Intent intent, SearchableInfo searchableInfo) {
        String str;
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        if (searchActivity == null) {
            str = null;
        } else {
            str = searchActivity.flattenToShortString();
        }
        intent2.putExtra("calling_package", str);
        return intent2;
    }

    private void dismissSuggestions() {
        this.mSearchSrcTextView.dismissDropDown();
    }

    private SemBlurInfoState generateBlurInfo(Context context) {
        float dimension = context.getResources().getDimension(R$dimen.sesl_search_plate_radius_size);
        SemBlurInfoStateBuilder generateFloatingComponentBlurInfoStateBuilder = BlurInfoState.INSTANCE.generateFloatingComponentBlurInfoStateBuilder(context, this.mBlurMode);
        if (this.mSearchPlate.getBackground() != null) {
            generateFloatingComponentBlurInfoStateBuilder.nonBlurBackground(this.mSearchPlate.getBackground());
        }
        return generateFloatingComponentBlurInfoStateBuilder.cornerRadius(dimension).build();
    }

    private void getChildBoundsWithinSearchView(View view, Rect rect) {
        view.getLocationInWindow(this.mTemp);
        getLocationInWindow(this.mTemp2);
        int[] iArr = this.mTemp;
        int i2 = iArr[1];
        int[] iArr2 = this.mTemp2;
        int i7 = i2 - iArr2[1];
        int i8 = iArr[0] - iArr2[0];
        rect.set(i8, i7, view.getWidth() + i8, view.getHeight() + i7);
    }

    private int getPreferredHeight() {
        if (this.mIsBottomSearchView) {
            return getContext().getResources().getDimensionPixelSize(R$dimen.sesl_search_view_bottom_preferred_height);
        }
        return getContext().getResources().getDimensionPixelSize(R$dimen.sesl_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.sesl_search_view_preferred_width);
    }

    private boolean hasVoiceSearch() {
        Intent intent;
        SearchableInfo searchableInfo = this.mSearchable;
        if (searchableInfo != null && searchableInfo.getVoiceSearchEnabled()) {
            if (this.mSearchable.getVoiceSearchLaunchWebSearch()) {
                intent = this.mVoiceWebSearchIntent;
            } else if (!this.mSearchable.getVoiceSearchLaunchRecognizer()) {
                intent = null;
            } else if (this.mUseSVI) {
                intent = this.mSVoiceSearchIntent;
            } else {
                intent = this.mVoiceAppSearchIntent;
            }
            if (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isLandscapeMode(Context context) {
        if (context.getResources().getConfiguration().orientation == 2) {
            return true;
        }
        return false;
    }

    private boolean isSubmitAreaEnabled() {
        if ((this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !isIconified()) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isSystemLocaleSupported() {
        /*
            r8 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "content://"
            r0.<init>(r1)
            java.lang.String r1 = AUTHORITY_SVI_APP
            r0.append(r1)
            java.lang.String r1 = "/"
            r0.append(r1)
            java.lang.String r1 = KEY_SVI_APP_LOCALE
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.net.Uri r2 = android.net.Uri.parse(r0)
            r7 = 0
            android.content.Context r8 = r8.mContext     // Catch:{ Exception -> 0x0035 }
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch:{ Exception -> 0x0035 }
            r5 = 0
            r6 = 0
            r3 = 0
            r4 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0035 }
            if (r8 != 0) goto L_0x003a
            if (r8 == 0) goto L_0x0039
            r8.close()     // Catch:{ Exception -> 0x0035 }
            return r7
        L_0x0035:
            r0 = move-exception
            r8 = r0
            r1 = r7
            goto L_0x0060
        L_0x0039:
            return r7
        L_0x003a:
            r1 = r7
        L_0x003b:
            boolean r0 = r8.moveToNext()     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x004f
            java.lang.String r0 = KEY_SVI_APP_LOCALE     // Catch:{ all -> 0x004c }
            int r0 = r8.getColumnIndex(r0)     // Catch:{ all -> 0x004c }
            int r1 = r8.getInt(r0)     // Catch:{ all -> 0x004c }
            goto L_0x003b
        L_0x004c:
            r0 = move-exception
            r2 = r0
            goto L_0x0056
        L_0x004f:
            r8.close()     // Catch:{ Exception -> 0x0053 }
            goto L_0x0073
        L_0x0053:
            r0 = move-exception
            r8 = r0
            goto L_0x0060
        L_0x0056:
            r8.close()     // Catch:{ all -> 0x005a }
            goto L_0x005f
        L_0x005a:
            r0 = move-exception
            r8 = r0
            r2.addSuppressed(r8)     // Catch:{ Exception -> 0x0053 }
        L_0x005f:
            throw r2     // Catch:{ Exception -> 0x0053 }
        L_0x0060:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "isSystemLocaleSupported: exception!!"
            r0.<init>(r2)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "SearchView"
            android.util.Log.w(r0, r8)
        L_0x0073:
            r8 = 1
            if (r1 != r8) goto L_0x0077
            r7 = r8
        L_0x0077:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.isSystemLocaleSupported():boolean");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view, boolean z) {
        if (this.mIsSearchMagIconButtonEnabled) {
            int i2 = 8;
            if (!z && this.mSearchSrcTextView.isEmpty()) {
                i2 = 0;
            }
            updateSearchMagIconVisibility(i2);
        }
        View.OnFocusChangeListener onFocusChangeListener = this.mOnQueryTextFocusChangeListener;
        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(this, z);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$seslSetSviEnabled$1() {
        updateViewsVisibility(isIconified());
    }

    private void launchIntent(Intent intent) {
        if (intent != null) {
            try {
                getContext().startActivity(intent);
            } catch (RuntimeException e) {
                Log.e("SearchView", "Failed launch activity: " + intent, e);
            }
        }
    }

    private boolean launchSuggestion(int i2, int i7, String str) {
        Cursor cursor = this.mSuggestionsAdapter.getCursor();
        if (cursor == null || !cursor.moveToPosition(i2)) {
            return false;
        }
        launchIntent(createIntentFromSuggestion(cursor, i7, str));
        return true;
    }

    private void postUpdateFocusedState() {
        post(this.mUpdateDrawableStateRunnable);
    }

    private void rewriteQueryFromSuggestion(int i2) {
        Editable text = this.mSearchSrcTextView.getText();
        Cursor cursor = this.mSuggestionsAdapter.getCursor();
        if (cursor != null) {
            if (cursor.moveToPosition(i2)) {
                CharSequence convertToString = this.mSuggestionsAdapter.convertToString(cursor);
                if (convertToString != null) {
                    setQuery(convertToString);
                } else {
                    setQuery(text);
                }
            } else {
                setQuery(text);
            }
        }
    }

    private void seslCheckMaxFont() {
        boolean z;
        Resources resources = this.mContext.getResources();
        float f = resources.getConfiguration().fontScale;
        View view = this.mSearchPlate;
        if (view == null || view.getBackground() == null) {
            z = false;
        } else {
            z = true;
        }
        float dimensionPixelSize = (float) resources.getDimensionPixelSize(SeslSearchViewStyle.create(this.mContext, z).mTextSizeRes);
        if (f > 1.3f) {
            dimensionPixelSize = (dimensionPixelSize / f) * 1.3f;
        }
        this.mSearchSrcTextView.setTextSize(0, dimensionPixelSize);
    }

    private void updateCloseButton() {
        int i2;
        int[] iArr;
        boolean isEmpty = TextUtils.isEmpty(this.mSearchSrcTextView.getText());
        ImageView imageView = this.mCloseButton;
        if (!isEmpty) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        Drawable drawable = this.mCloseButton.getDrawable();
        if (drawable != null) {
            if (!isEmpty) {
                iArr = ViewGroup.ENABLED_STATE_SET;
            } else {
                iArr = ViewGroup.EMPTY_STATE_SET;
            }
            drawable.setState(iArr);
        }
    }

    private void updateQueryHint() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(queryHint);
    }

    private void updateSearchAutoComplete() {
        this.mSearchSrcTextView.setThreshold(this.mSearchable.getSuggestThreshold());
        this.mSearchSrcTextView.setImeOptions(this.mSearchable.getImeOptions());
        int inputType = this.mSearchable.getInputType();
        int i2 = 1;
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.mSearchable.getSuggestAuthority() != null) {
                inputType |= 65536;
            }
        }
        this.mSearchSrcTextView.setInputType(inputType);
        CursorAdapter cursorAdapter = this.mSuggestionsAdapter;
        if (cursorAdapter != null) {
            cursorAdapter.changeCursor((Cursor) null);
        }
        if (this.mSearchable.getSuggestAuthority() != null) {
            SuggestionsAdapter suggestionsAdapter = new SuggestionsAdapter(getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
            this.mSuggestionsAdapter = suggestionsAdapter;
            this.mSearchSrcTextView.setAdapter(suggestionsAdapter);
            SuggestionsAdapter suggestionsAdapter2 = (SuggestionsAdapter) this.mSuggestionsAdapter;
            if (this.mQueryRefinement) {
                i2 = 2;
            }
            suggestionsAdapter2.setQueryRefinement(i2);
        }
    }

    private void updateSearchMagIconVisibility(int i2) {
        ImageView imageView = this.mSearchMagIconButton;
        if (imageView != null && imageView.getVisibility() != i2) {
            this.mSearchMagIconButton.setVisibility(i2);
            updateSearchPlatePadding();
        }
    }

    private void updateSearchPlatePadding() {
        int i2;
        if (this.mSearchPlate != null) {
            Resources resources = this.mContext.getResources();
            if (!this.mIsBottomSearchView || this.mSearchMagIconButton.getVisibility() == 0) {
                i2 = resources.getDimensionPixelSize(R$dimen.sesl_search_plate_padding_start);
            } else {
                i2 = resources.getDimensionPixelSize(R$dimen.sesl_search_plate_bottom_padding_start);
            }
            int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.sesl_search_plate_bottom_padding_end);
            this.mSearchPlate.setPaddingRelative(i2, this.mSearchPlate.getPaddingTop(), dimensionPixelSize, this.mSearchPlate.getPaddingBottom());
        }
    }

    private void updateSubmitArea() {
        int i2;
        if (!isSubmitAreaEnabled() || !(this.mGoButton.getVisibility() == 0 || this.mVoiceButton.getVisibility() == 0)) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        this.mSubmitArea.setVisibility(i2);
    }

    private void updateSubmitButton(boolean z) {
        int i2;
        if (!this.mSubmitButtonEnabled || !isSubmitAreaEnabled() || !hasFocus() || (!z && this.mVoiceButtonEnabled)) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        this.mGoButton.setVisibility(i2);
    }

    private void updateViewsVisibility(boolean z) {
        int i2;
        this.mIconified = z;
        int i7 = 8;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        boolean isEmpty = TextUtils.isEmpty(this.mSearchSrcTextView.getText());
        this.mSearchButton.setVisibility(i2);
        updateSubmitButton(!isEmpty);
        View view = this.mSearchEditFrame;
        if (!z) {
            i7 = 0;
        }
        view.setVisibility(i7);
        updateCloseButton();
        updateVoiceButton(isEmpty);
        updateSubmitArea();
    }

    private void updateVoiceButton(boolean z) {
        int i2 = 8;
        if (this.mVoiceButtonEnabled && !isIconified() && z) {
            this.mGoButton.setVisibility(8);
            i2 = 0;
        }
        updateVoiceButtonDrawable();
        this.mVoiceButton.setVisibility(i2);
    }

    private void updateVoiceButtonDrawable() {
        Drawable drawable;
        ImageView imageView = this.mVoiceButton;
        if (this.mUseSVI) {
            drawable = this.mSVIVoiceIcon;
        } else {
            drawable = this.mGVIVoiceIcon;
        }
        imageView.setImageDrawable(drawable);
    }

    public void adjustDropDownSizeAndPosition() {
        int i2;
        if (this.mDropDownAnchor.getWidth() > 1) {
            Rect rect = new Rect();
            boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
            if (this.mSearchSrcTextView.getDropDownBackground() != null) {
                this.mSearchSrcTextView.getDropDownBackground().getPadding(rect);
            }
            if (isLayoutRtl) {
                i2 = -rect.left;
            } else {
                i2 = 0 - rect.left;
            }
            this.mSearchSrcTextView.setDropDownHorizontalOffset(i2);
            this.mSearchSrcTextView.setDropDownWidth(this.mDropDownAnchor.getWidth() + rect.left + rect.right);
            if (this.mSearchSrcTextView.isPopupShowing()) {
                this.mSearchSrcTextView.showDropDown();
            }
        }
    }

    public boolean applyBlurInfo(Context context) {
        if (Build.VERSION.SDK_INT < 36) {
            return false;
        }
        clearBlurInfo(context);
        SemBlurInfoState generateBlurInfo = generateBlurInfo(context);
        if (generateBlurInfo.applyBlurInfo(this.mSearchPlate)) {
            this.mBlurInfo = generateBlurInfo;
        }
        return false;
    }

    public void clearBlurInfo(Context context) {
        View view;
        SemBlurInfoState semBlurInfoState = this.mBlurInfo;
        if (semBlurInfoState != null && (view = this.mSearchPlate) != null) {
            semBlurInfoState.clearBlurInfo(view);
            this.mBlurInfo = null;
        }
    }

    public void clearFocus() {
        this.mClearingFocus = true;
        super.clearFocus();
        this.mSearchSrcTextView.clearFocus();
        this.mSearchSrcTextView.setImeVisibility(false);
        this.mClearingFocus = false;
    }

    public void forceSuggestionQuery() {
        Api29Impl.refreshAutoCompleteResults(this.mSearchSrcTextView);
        if (this.mIsSearchMagIconButtonEnabled) {
            updateSearchMagIconVisibility(8);
        }
    }

    public int getImeOptions() {
        return this.mSearchSrcTextView.getImeOptions();
    }

    public int getInputType() {
        return this.mSearchSrcTextView.getInputType();
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public CharSequence getQuery() {
        return this.mSearchSrcTextView.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.mQueryHint;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.mSearchable;
        if (searchableInfo == null || searchableInfo.getHintId() == 0) {
            return this.mDefaultQueryHint;
        }
        return getContext().getText(this.mSearchable.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.mSuggestionCommitIconResId;
    }

    public int getSuggestionRowLayout() {
        return this.mSuggestionRowLayout;
    }

    public CursorAdapter getSuggestionsAdapter() {
        return this.mSuggestionsAdapter;
    }

    public boolean isIconified() {
        return this.mIconified;
    }

    public void launchQuerySearch(int i2, String str, String str2) {
        getContext().startActivity(createIntent("android.intent.action.SEARCH", (Uri) null, (String) null, str2, i2, str));
    }

    public void onActionViewCollapsed() {
        setQuery("", false);
        clearFocus();
        updateViewsVisibility(true);
        this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions);
        this.mExpandedInActionView = false;
    }

    public void onActionViewExpanded() {
        if (!this.mExpandedInActionView) {
            this.mExpandedInActionView = true;
            int imeOptions = this.mSearchSrcTextView.getImeOptions();
            this.mCollapsedImeOptions = imeOptions;
            this.mSearchSrcTextView.setImeOptions(imeOptions | 33554432);
            this.mSearchSrcTextView.setText("");
            setIconified(false);
        }
    }

    public void onCloseClicked() {
        if (!TextUtils.isEmpty(this.mSearchSrcTextView.getText())) {
            this.mSearchSrcTextView.setText("");
            this.mSearchSrcTextView.requestFocus();
            this.mSearchSrcTextView.announceForAccessibility(getResources().getString(R$string.sesl_searchview_description_clear_field));
            if (SeslInputMethodManagerReflector.isAccessoryKeyboardState(this.mImm) != 0) {
                this.mSearchSrcTextView.setImeVisibility(false);
            } else {
                this.mSearchSrcTextView.setImeVisibility(true);
            }
        } else if (this.mIconifiedByDefault) {
            OnCloseListener onCloseListener = this.mOnCloseListener;
            if (onCloseListener == null || !((LabsSearchDelegate) ((h) onCloseListener).e).onCloseButtonClicked()) {
                clearFocus();
                updateViewsVisibility(true);
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        seslCheckMaxFont();
    }

    public void onDetachedFromWindow() {
        removeCallbacks(this.mUpdateDrawableStateRunnable);
        post(this.mReleaseCursorRunnable);
        super.onDetachedFromWindow();
    }

    public boolean onItemClicked(int i2, int i7, String str) {
        launchSuggestion(i2, 0, (String) null);
        this.mSearchSrcTextView.setImeVisibility(false);
        dismissSuggestions();
        return true;
    }

    public boolean onItemSelected(int i2) {
        rewriteQueryFromSuggestion(i2);
        return true;
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        if (z) {
            getChildBoundsWithinSearchView(this.mSearchSrcTextView, this.mSearchSrcTextViewBounds);
            Rect rect = this.mSearchSrtTextViewBoundsExpanded;
            Rect rect2 = this.mSearchSrcTextViewBounds;
            rect.set(rect2.left, 0, rect2.right, i10 - i7);
            UpdatableTouchDelegate updatableTouchDelegate = this.mTouchDelegate;
            if (updatableTouchDelegate == null) {
                UpdatableTouchDelegate updatableTouchDelegate2 = new UpdatableTouchDelegate(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds, this.mSearchSrcTextView);
                this.mTouchDelegate = updatableTouchDelegate2;
                setTouchDelegate(updatableTouchDelegate2);
                return;
            }
            updatableTouchDelegate.setBounds(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds);
        }
    }

    public void onMeasure(int i2, int i7) {
        int i8;
        if (isIconified()) {
            super.onMeasure(i2, i7);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            int i10 = this.mMaxWidth;
            if (i10 > 0) {
                size = Math.min(i10, size);
            }
        } else if (mode == 0) {
            size = this.mMaxWidth;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i8 = this.mMaxWidth) > 0) {
            size = Math.min(i8, size);
        }
        int mode2 = View.MeasureSpec.getMode(i7);
        int size2 = View.MeasureSpec.getSize(i7);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    public void onQueryRefine(CharSequence charSequence) {
        setQuery(charSequence);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        updateViewsVisibility(savedState.isIconified);
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.isIconified = isIconified();
        return savedState;
    }

    public void onSearchClicked() {
        updateViewsVisibility(false);
        if (this.mIsSearchMagIconButtonEnabled) {
            updateSearchMagIconVisibility(8);
        }
        this.mSearchSrcTextView.requestFocus();
        if (SeslInputMethodManagerReflector.isAccessoryKeyboardState(this.mImm) != 0) {
            this.mSearchSrcTextView.setImeVisibility(false);
        } else {
            this.mSearchSrcTextView.setImeVisibility(true);
        }
        View.OnClickListener onClickListener = this.mOnSearchClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void onSubmitQuery() {
        Editable text = this.mSearchSrcTextView.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            OnQueryTextListener onQueryTextListener = this.mOnQueryChangeListener;
            if (onQueryTextListener == null || !onQueryTextListener.onQueryTextSubmit(text.toString())) {
                if (this.mSearchable != null) {
                    launchQuerySearch(0, (String) null, text.toString());
                }
                this.mSearchSrcTextView.setImeVisibility(false);
                dismissSuggestions();
            }
        }
    }

    public boolean onSuggestionsKey(View view, int i2, KeyEvent keyEvent) {
        int i7;
        if (this.mSearchable != null && this.mSuggestionsAdapter != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i2 == 66 || i2 == 84 || i2 == 61 || i2 == 160) {
                return onItemClicked(this.mSearchSrcTextView.getListSelection(), 0, (String) null);
            }
            if (i2 == 21 || i2 == 22) {
                if (i2 == 21) {
                    i7 = 0;
                } else {
                    i7 = this.mSearchSrcTextView.length();
                }
                this.mSearchSrcTextView.setSelection(i7);
                this.mSearchSrcTextView.setListSelection(0);
                this.mSearchSrcTextView.clearListSelection();
                this.mSearchSrcTextView.ensureImeVisible();
                return true;
            } else if (i2 == 19) {
                this.mSearchSrcTextView.getListSelection();
                return false;
            }
        }
        return false;
    }

    public void onTextChanged(CharSequence charSequence) {
        Editable text = this.mSearchSrcTextView.getText();
        this.mUserQuery = text;
        boolean isEmpty = TextUtils.isEmpty(text);
        updateSubmitButton(!isEmpty);
        updateVoiceButton(isEmpty);
        updateCloseButton();
        updateSubmitArea();
        if (!TextUtils.equals(charSequence, this.mOldQueryText)) {
            this.mOldQueryText = charSequence.toString();
            OnQueryTextListener onQueryTextListener = this.mOnQueryChangeListener;
            if (onQueryTextListener != null) {
                onQueryTextListener.onQueryTextChange(charSequence.toString());
            }
        }
    }

    public void onTextFocusChanged() {
        updateViewsVisibility(isIconified());
        postUpdateFocusedState();
        if (this.mSearchSrcTextView.hasFocus()) {
            forceSuggestionQuery();
        }
    }

    public void onVoiceClicked() {
        SearchableInfo searchableInfo = this.mSearchable;
        if (searchableInfo != null) {
            try {
                if (this.mUseSVI) {
                    if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                        this.mContext.startActivity(createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, searchableInfo));
                    } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                        this.mContext.startActivity(createSVoiceSearchIntent(this.mSVoiceSearchIntent, searchableInfo));
                    }
                } else if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                    this.mContext.startActivity(createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, searchableInfo));
                } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                    this.mContext.startActivity(createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, searchableInfo));
                }
            } catch (ActivityNotFoundException unused) {
                Log.w("SearchView", "Could not find voice search activity");
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (SeslInputMethodManagerReflector.isAccessoryKeyboardState(this.mImm) == 0) {
            postUpdateFocusedState();
        }
    }

    public boolean performLongClick() {
        TooltipCompat.seslSetNextTooltipForceBelow(true);
        TooltipCompat.seslSetNextTooltipForceActionBarPosX(true);
        return super.performLongClick();
    }

    public boolean requestFocus(int i2, Rect rect) {
        if (this.mClearingFocus || !isFocusable()) {
            return false;
        }
        if (isIconified()) {
            return super.requestFocus(i2, rect);
        }
        boolean requestFocus = this.mSearchSrcTextView.requestFocus(i2, rect);
        if (requestFocus) {
            updateViewsVisibility(false);
        }
        return requestFocus;
    }

    public ImageView seslGetOverflowMenuButton() {
        return this.mMoreButton;
    }

    public ImageView seslGetUpButton() {
        return this.mBackButton;
    }

    public void seslSetNotCallShowSoftInput(boolean z) {
        this.mSearchSrcTextView.setNotCallShowSoftInput(z);
    }

    public void seslSetOnUpButtonClickListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.mBackButton;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    public void seslSetSearchMagIconVisibility(int i2) {
        boolean z;
        if (this.mSearchMagIconButton != null) {
            if (i2 == 0) {
                z = true;
            } else {
                z = false;
            }
            this.mIsSearchMagIconButtonEnabled = z;
            updateSearchMagIconVisibility(i2);
        }
    }

    public boolean seslSetSviEnabled(boolean z) {
        long j2;
        if (SeslBuildReflector$SeslVersionReflector.getField_SEM_PLATFORM_INT() < 110100) {
            Log.w("SearchView", "seslSetSviEnabled: SEP Version is not supported");
            return false;
        }
        this.mUseSVI = z;
        if (z) {
            try {
                PackageInfo packageInfo = getContext().getPackageManager().getPackageInfo(SVI_PACKAGE, 0);
                if (packageInfo != null) {
                    j2 = PackageInfoCompat.getLongVersionCode(packageInfo);
                } else {
                    j2 = -1;
                }
                if (j2 < 220002001) {
                    Log.w("SearchView", "seslSetSviEnabled: not supported SVI version");
                    this.mUseSVI = false;
                }
                if (!isSystemLocaleSupported()) {
                    Log.w("SearchView", "seslSetSviEnabled: not supported system locale");
                    this.mUseSVI = false;
                }
            } catch (Exception e) {
                Log.w("SearchView", "Exception " + e);
                this.mUseSVI = false;
            }
        }
        post(new C0451a(21, this));
        return this.mUseSVI;
    }

    public void setAppSearchData(Bundle bundle) {
        this.mAppSearchData = bundle;
    }

    public void setBackground(Drawable drawable) {
        View view = this.mSearchPlate;
        if (view != null) {
            ViewCompat.setBackground(view, drawable);
        }
    }

    public void setBackgroundResource(int i2) {
        View view = this.mSearchPlate;
        if (view != null) {
            ViewCompat.setBackground(view, getContext().getResources().getDrawable(i2));
        }
    }

    public void setBlurMode(int i2) {
        this.mBlurMode = i2;
        applySearchViewBlur();
    }

    public void setElevation(float f) {
        View view = this.mSearchPlate;
        if (view != null) {
            ViewCompat.setElevation(view, f);
        }
    }

    public void setIconified(boolean z) {
        if (z) {
            onCloseClicked();
        } else {
            onSearchClicked();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.mIconifiedByDefault != z) {
            this.mIconifiedByDefault = z;
            updateViewsVisibility(z);
            updateQueryHint();
        }
    }

    public void setImeOptions(int i2) {
        this.mSearchSrcTextView.setImeOptions(i2);
    }

    public void setInputType(int i2) {
        this.mSearchSrcTextView.setInputType(i2);
    }

    public void setMaxWidth(int i2) {
        this.mMaxWidth = i2;
        requestLayout();
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.mOnCloseListener = onCloseListener;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.mOnQueryTextFocusChangeListener = onFocusChangeListener;
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.mOnQueryChangeListener = onQueryTextListener;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.mOnSearchClickListener = onClickListener;
    }

    public void setQuery(CharSequence charSequence, boolean z) {
        this.mSearchSrcTextView.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.mUserQuery = charSequence;
            if (this.mIsSearchMagIconButtonEnabled && !this.mSearchSrcTextView.hasFocus()) {
                updateSearchMagIconVisibility(TextUtils.isEmpty(charSequence) ? 0 : 8);
            }
        }
        if (z && !TextUtils.isEmpty(charSequence)) {
            onSubmitQuery();
        }
    }

    public void setQueryHint(CharSequence charSequence) {
        this.mQueryHint = charSequence;
        updateQueryHint();
    }

    public void setQueryRefinementEnabled(boolean z) {
        int i2;
        this.mQueryRefinement = z;
        CursorAdapter cursorAdapter = this.mSuggestionsAdapter;
        if (cursorAdapter instanceof SuggestionsAdapter) {
            SuggestionsAdapter suggestionsAdapter = (SuggestionsAdapter) cursorAdapter;
            if (z) {
                i2 = 2;
            } else {
                i2 = 1;
            }
            suggestionsAdapter.setQueryRefinement(i2);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.mSearchable = searchableInfo;
        if (searchableInfo != null) {
            updateSearchAutoComplete();
            updateQueryHint();
        }
        this.mVoiceButtonEnabled = hasVoiceSearch();
        updateViewsVisibility(isIconified());
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.mSubmitButtonEnabled = z;
        updateViewsVisibility(isIconified());
    }

    public void setSuggestionsAdapter(CursorAdapter cursorAdapter) {
        this.mSuggestionsAdapter = cursorAdapter;
        this.mSearchSrcTextView.setAdapter(cursorAdapter);
    }

    public void updateFocusedState() {
        int[] iArr;
        if (this.mSearchSrcTextView.hasFocus()) {
            iArr = ViewGroup.FOCUSED_STATE_SET;
        } else {
            iArr = ViewGroup.EMPTY_STATE_SET;
        }
        Drawable background = this.mSearchPlate.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.mSubmitArea.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SearchView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        boolean z;
        Context context2 = context;
        this.mSearchSrcTextViewBounds = new Rect();
        this.mSearchSrtTextViewBoundsExpanded = new Rect();
        this.mTemp = new int[2];
        this.mTemp2 = new int[2];
        this.mIsSearchMagIconButtonEnabled = false;
        this.mBlurMode = 2;
        this.mUseSVI = false;
        this.mUpdateDrawableStateRunnable = new Runnable() {
            public void run() {
                SearchView.this.updateFocusedState();
            }
        };
        this.mReleaseCursorRunnable = new Runnable() {
            public void run() {
                CursorAdapter cursorAdapter = SearchView.this.mSuggestionsAdapter;
                if (cursorAdapter instanceof SuggestionsAdapter) {
                    cursorAdapter.changeCursor((Cursor) null);
                }
            }
        };
        this.mOutsideDrawablesCache = new WeakHashMap<>();
        AnonymousClass4 r82 = new View.OnClickListener() {
            public void onClick(View view) {
                SearchView searchView = SearchView.this;
                if (view == searchView.mSearchButton || view == searchView.mSearchMagIconButton) {
                    SearchView.this.onSearchClicked();
                    return;
                }
                SearchView searchView2 = SearchView.this;
                if (view == searchView2.mCloseButton) {
                    searchView2.onCloseClicked();
                } else if (view == searchView2.mGoButton) {
                    searchView2.onSubmitQuery();
                } else if (view == searchView2.mVoiceButton) {
                    searchView2.onVoiceClicked();
                } else if (view == searchView2.mSearchSrcTextView) {
                    searchView2.forceSuggestionQuery();
                }
            }
        };
        this.mOnClickListener = r82;
        this.mTextKeyListener = new View.OnKeyListener() {
            public boolean onKey(View view, int i2, KeyEvent keyEvent) {
                InputMethodManager inputMethodManager;
                if (SearchView.this.mContext.getPackageManager().hasSystemFeature("com.sec.feature.folder_type") && (inputMethodManager = (InputMethodManager) SearchView.this.getContext().getSystemService("input_method")) != null && i2 == 23) {
                    inputMethodManager.viewClicked(view);
                    inputMethodManager.showSoftInput(view, 1);
                }
                SearchView searchView = SearchView.this;
                if (searchView.mSearchable == null) {
                    return false;
                }
                if (searchView.mSearchSrcTextView.isPopupShowing() && SearchView.this.mSearchSrcTextView.getListSelection() != -1) {
                    return SearchView.this.onSuggestionsKey(view, i2, keyEvent);
                }
                if (SearchView.this.mSearchSrcTextView.isEmpty() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || (i2 != 66 && i2 != 160)) {
                    return false;
                }
                view.cancelLongPress();
                SearchView searchView2 = SearchView.this;
                searchView2.launchQuerySearch(0, (String) null, searchView2.mSearchSrcTextView.getText().toString());
                return true;
            }
        };
        AnonymousClass6 r9 = new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                SearchView.this.onSubmitQuery();
                return true;
            }
        };
        this.mOnEditorActionListener = r9;
        AnonymousClass7 r10 = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                SearchView.this.onItemClicked(i2, 0, (String) null);
            }
        };
        this.mOnItemClickListener = r10;
        AnonymousClass8 r11 = new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
                SearchView.this.onItemSelected(i2);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        this.mOnItemSelectedListener = r11;
        this.mTextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
                SearchView.this.onTextChanged(charSequence);
            }

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
            }
        };
        int[] iArr = R$styleable.SearchView;
        AttributeSet attributeSet2 = attributeSet;
        int i7 = i2;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context2, attributeSet2, iArr, i7, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context2, iArr, attributeSet2, obtainStyledAttributes.getWrappedTypeArray(), i7, 0);
        LayoutInflater.from(context2).inflate(obtainStyledAttributes.getResourceId(R$styleable.SearchView_layout, R$layout.sesl_search_view), this, true);
        this.mContext = context2;
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R$id.search_src_text);
        this.mSearchSrcTextView = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.mSearchEditFrame = findViewById(R$id.search_edit_frame);
        View findViewById = findViewById(R$id.search_plate);
        this.mSearchPlate = findViewById;
        this.mSearchPlateFrame = findViewById(R$id.search_plate_frame);
        this.mSearchPlateStroke = findViewById(R$id.search_plate_stroke_overlay);
        View findViewById2 = findViewById(R$id.submit_area);
        this.mSubmitArea = findViewById2;
        ImageView imageView = (ImageView) findViewById(R$id.search_button);
        this.mSearchButton = imageView;
        ImageView imageView2 = (ImageView) findViewById(R$id.search_go_btn);
        this.mGoButton = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R$id.search_close_btn);
        this.mCloseButton = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R$id.search_voice_btn);
        this.mVoiceButton = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R$id.search_more_btn);
        this.mMoreButton = imageView5;
        ImageView imageView6 = (ImageView) findViewById(R$id.search_back_btn);
        this.mBackButton = imageView6;
        ImageView imageView7 = (ImageView) findViewById(R$id.search_mag_icon);
        this.mSearchMagIconButton = imageView7;
        ViewCompat.setBackground(findViewById, obtainStyledAttributes.getDrawable(R$styleable.SearchView_queryBackground));
        ViewCompat.setBackground(findViewById2, obtainStyledAttributes.getDrawable(R$styleable.SearchView_submitBackground));
        int i8 = R$styleable.SearchView_searchIcon;
        View view = findViewById;
        this.mSearchIconResId = obtainStyledAttributes.getResourceId(i8, 0);
        this.mIsBlurApplied = obtainStyledAttributes.getBoolean(R$styleable.SearchView_seslSearchViewApplyBlur, false);
        this.mIsBottomSearchView = obtainStyledAttributes.getBoolean(R$styleable.SearchView_seslIsBottomSearchView, false);
        imageView.setImageDrawable(obtainStyledAttributes.getDrawable(i8));
        imageView2.setImageDrawable(obtainStyledAttributes.getDrawable(R$styleable.SearchView_goIcon));
        imageView3.setImageDrawable(obtainStyledAttributes.getDrawable(R$styleable.SearchView_closeIcon));
        imageView7.setImageDrawable(obtainStyledAttributes.getDrawable(i8));
        this.mGVIVoiceIcon = obtainStyledAttributes.getDrawable(R$styleable.SearchView_voiceIcon);
        this.mSVIVoiceIcon = obtainStyledAttributes.getDrawable(R$styleable.SearchView_voiceIconSVI);
        updateVoiceButtonDrawable();
        this.mSearchHintIcon = obtainStyledAttributes.getDrawable(R$styleable.SearchView_searchHintIcon);
        TooltipCompat.setTooltipText(imageView, imageView.getContentDescription());
        TooltipCompat.setTooltipText(imageView7, imageView7.getContentDescription());
        TooltipCompat.setTooltipText(imageView3, imageView3.getContentDescription());
        TooltipCompat.setTooltipText(imageView2, imageView2.getContentDescription());
        TooltipCompat.setTooltipText(imageView4, imageView4.getContentDescription());
        TooltipCompat.setTooltipText(imageView5, imageView5.getContentDescription());
        TooltipCompat.setTooltipText(imageView6, imageView6.getContentDescription());
        this.mSuggestionRowLayout = obtainStyledAttributes.getResourceId(R$styleable.SearchView_suggestionRowLayout, R$layout.sesl_search_dropdown_item_icons_2line);
        this.mSuggestionCommitIconResId = obtainStyledAttributes.getResourceId(R$styleable.SearchView_commitIcon, 0);
        imageView.setOnClickListener(r82);
        imageView3.setOnClickListener(r82);
        imageView2.setOnClickListener(r82);
        imageView4.setOnClickListener(r82);
        imageView7.setOnClickListener(r82);
        searchAutoComplete.setOnClickListener(r82);
        searchAutoComplete.addTextChangedListener(this.mTextWatcher);
        searchAutoComplete.setOnEditorActionListener(r9);
        searchAutoComplete.setOnItemClickListener(r10);
        searchAutoComplete.setOnItemSelectedListener(r11);
        searchAutoComplete.setOnKeyListener(this.mTextKeyListener);
        searchAutoComplete.setOnFocusChangeListener(new C0000a(7, this));
        setIconifiedByDefault(obtainStyledAttributes.getBoolean(R$styleable.SearchView_iconifiedByDefault, true));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.SearchView_android_maxWidth, -1);
        if (dimensionPixelSize != -1) {
            setMaxWidth(dimensionPixelSize);
        }
        this.mDefaultQueryHint = obtainStyledAttributes.getText(R$styleable.SearchView_defaultQueryHint);
        this.mQueryHint = obtainStyledAttributes.getText(R$styleable.SearchView_queryHint);
        int i10 = obtainStyledAttributes.getInt(R$styleable.SearchView_android_imeOptions, -1);
        if (i10 != -1) {
            setImeOptions(i10);
        }
        int i11 = obtainStyledAttributes.getInt(R$styleable.SearchView_android_inputType, -1);
        if (i11 != -1) {
            setInputType(i11);
        }
        setFocusable(obtainStyledAttributes.getBoolean(R$styleable.SearchView_android_focusable, true));
        imageView7.setImageDrawable(obtainStyledAttributes.getDrawable(i8));
        imageView.setImageDrawable(obtainStyledAttributes.getDrawable(i8));
        Resources resources = this.mContext.getResources();
        int i12 = Build.VERSION.SDK_INT;
        if (i12 >= 34) {
            this.mBoldTypeface = Typeface.create(Typeface.create("sec", 0), 600, false);
            z = true;
        } else {
            z = true;
            this.mBoldTypeface = Typeface.create(resources.getString(R$string.sesl_font_family_regular), 1);
        }
        searchAutoComplete.setTypeface(this.mBoldTypeface);
        SeslSearchViewStyle.create(this.mContext, view.getBackground() == null ? false : z).apply(resources, searchAutoComplete, Arrays.asList(new ImageView[]{imageView2, imageView3, imageView4, imageView5, imageView}));
        obtainStyledAttributes.recycle();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.mVoiceWebSearchIntent = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.mVoiceAppSearchIntent = intent2;
        intent2.addFlags(268435456);
        Intent intent3 = new Intent(SVI_ACTION);
        this.mSVoiceSearchIntent = intent3;
        intent3.addFlags(268435456);
        if (i12 < 33) {
            intent3.putExtra("samsung.svoiceime.extra.LANGUAGE", Locale.getDefault().toString());
        }
        View findViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.mDropDownAnchor = findViewById3;
        if (findViewById3 != null) {
            findViewById3.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
                    SearchView.this.adjustDropDownSizeAndPosition();
                }
            });
        }
        updateViewsVisibility(this.mIconifiedByDefault);
        updateQueryHint();
        this.mImm = (InputMethodManager) getContext().getSystemService("input_method");
        int field_SEM_AUTOFILL_ID = SeslTextViewReflector.getField_SEM_AUTOFILL_ID();
        if (field_SEM_AUTOFILL_ID != 0) {
            SeslTextViewReflector.semSetActionModeMenuItemEnabled(searchAutoComplete, field_SEM_AUTOFILL_ID, false);
        }
        seslCheckMaxFont();
        applySearchViewBlur();
        applyBottomSearchPlateStyle(resources);
    }

    private void setQuery(CharSequence charSequence) {
        this.mSearchSrcTextView.setText(charSequence);
        int i2 = 0;
        this.mSearchSrcTextView.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
        if (this.mIsSearchMagIconButtonEnabled && !this.mSearchSrcTextView.hasFocus()) {
            if (!TextUtils.isEmpty(charSequence)) {
                i2 = 8;
            }
            updateSearchMagIconVisibility(i2);
        }
    }

    public void setOnSuggestionListener(OnSuggestionListener onSuggestionListener) {
    }
}
