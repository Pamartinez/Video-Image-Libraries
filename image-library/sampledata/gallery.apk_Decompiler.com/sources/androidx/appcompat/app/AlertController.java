package androidx.appcompat.app;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.util.SeslMisc;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.util.Consumer;
import androidx.core.view.SemBlurCompat;
import androidx.core.view.SeslTouchDelegateFactory;
import androidx.core.view.SeslTouchTargetDelegate;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.reflect.feature.SeslFloatingFeatureReflector;
import androidx.reflect.widget.SeslAdapterViewReflector;
import androidx.reflect.widget.SeslTextViewReflector;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlertController {
    ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private final View.OnClickListener mButtonHandler = new View.OnClickListener() {
        public void onClick(View view) {
            Message message;
            Message message2;
            Message message3;
            Message message4;
            AlertController alertController = AlertController.this;
            if (view == alertController.mButtonPositive && (message4 = alertController.mButtonPositiveMessage) != null) {
                message = Message.obtain(message4);
            } else if (view == alertController.mButtonNegative && (message3 = alertController.mButtonNegativeMessage) != null) {
                message = Message.obtain(message3);
            } else if (view != alertController.mButtonNeutral || (message2 = alertController.mButtonNeutralMessage) == null) {
                message = null;
            } else {
                message = Message.obtain(message2);
            }
            if (message != null) {
                message.sendToTarget();
            }
            AlertController alertController2 = AlertController.this;
            alertController2.mHandler.obtainMessage(1, alertController2.mDialog).sendToTarget();
        }
    };
    private final int mButtonIconDimen;
    Button mButtonNegative;
    private Drawable mButtonNegativeIcon;
    Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    Button mButtonNeutral;
    private Drawable mButtonNeutralIcon;
    Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint = 0;
    private int mButtonPanelSideLayout;
    Button mButtonPositive;
    private Drawable mButtonPositiveIcon;
    Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    int mCheckedItem = -1;
    /* access modifiers changed from: private */
    public final Context mContext;
    private View mCustomTitleView;
    private Consumer<ViewGroup> mDefaultButtonPanelJob;
    final AppCompatDialog mDialog;
    Handler mHandler;
    private Drawable mIcon;
    private int mIconId = 0;
    private ImageView mIconView;
    private boolean mIsBlurEnabled = false;
    private boolean mIsDefaultBlurEnabled = true;
    /* access modifiers changed from: private */
    public int mLastOrientation;
    int mListItemLayout;
    int mListLayout;
    ListView mListView;
    private CharSequence mMessage;
    private TextView mMessageView;
    int mMultiChoiceItemLayout;
    NestedScrollView mScrollView;
    private boolean mShowTitle;
    private boolean mSingleChoiceChecked;
    int mSingleChoiceItemLayout;
    private CompoundButton.OnCheckedChangeListener mSingleChoiceListener;
    private CharSequence mSingleChoiceOptionLabel;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified = false;
    private int mViewSpacingTop;
    private final Window mWindow;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AlertParams {
        public boolean isSingleChoiceOption = false;
        public ListAdapter mAdapter;
        public boolean mCancelable;
        public int mCheckedItem = -1;
        public boolean[] mCheckedItems;
        public final Context mContext;
        public Cursor mCursor;
        public View mCustomTitleView;
        public Drawable mIcon;
        public int mIconAttrId = 0;
        public int mIconId = 0;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence[] mItems;
        public String mLabelColumn;
        public CharSequence mMessage;
        public Drawable mNegativeButtonIcon;
        public DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public Drawable mNeutralButtonIcon;
        public DialogInterface.OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
        public DialogInterface.OnClickListener mOnClickListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public AdapterView.OnItemSelectedListener mOnItemSelectedListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public Drawable mPositiveButtonIcon;
        public DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure = true;
        public boolean mSingleChoiceChecked = false;
        public CompoundButton.OnCheckedChangeListener mSingleChoiceListener;
        public CharSequence mSingleChoiceOptionLabel;
        public CharSequence mTitle;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified = false;
        public int mViewSpacingTop;

        public AlertParams(Context context) {
            this.mContext = context;
            this.mCancelable = true;
            this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        private void createListView(AlertController alertController) {
            final AlertController alertController2;
            AlertParams alertParams;
            ListAdapter listAdapter;
            int i2;
            final RecycleListView recycleListView = (RecycleListView) this.mInflater.inflate(alertController.mListLayout, (ViewGroup) null);
            if (!this.mIsMultiChoice) {
                alertParams = this;
                alertController2 = alertController;
                if (alertParams.mIsSingleChoice) {
                    i2 = alertController2.mSingleChoiceItemLayout;
                } else {
                    i2 = alertController2.mListItemLayout;
                }
                int i7 = i2;
                if (alertParams.mCursor != null) {
                    listAdapter = new SimpleCursorAdapter(alertParams.mContext, i7, alertParams.mCursor, new String[]{alertParams.mLabelColumn}, new int[]{16908308});
                } else {
                    listAdapter = alertParams.mAdapter;
                    if (listAdapter == null) {
                        listAdapter = new CheckedItemAdapter(alertParams.mContext, i7, 16908308, alertParams.mItems);
                    }
                }
            } else if (this.mCursor == null) {
                final RecycleListView recycleListView2 = recycleListView;
                alertParams = this;
                listAdapter = new ArrayAdapter<CharSequence>(this.mContext, alertController.mMultiChoiceItemLayout, 16908308, this.mItems) {
                    public View getView(int i2, View view, ViewGroup viewGroup) {
                        View view2 = super.getView(i2, view, viewGroup);
                        boolean[] zArr = AlertParams.this.mCheckedItems;
                        if (zArr != null && zArr[i2]) {
                            recycleListView2.setItemChecked(i2, true);
                        }
                        return view2;
                    }
                };
                recycleListView = recycleListView2;
                alertController2 = alertController;
            } else {
                alertParams = this;
                RecycleListView recycleListView3 = recycleListView;
                alertController2 = alertController;
                listAdapter = new CursorAdapter(alertParams.mContext, alertParams.mCursor, false) {
                    private final int mIsCheckedIndex;
                    private final int mLabelIndex;

                    {
                        Cursor cursor = getCursor();
                        this.mLabelIndex = cursor.getColumnIndexOrThrow(AlertParams.this.mLabelColumn);
                        this.mIsCheckedIndex = cursor.getColumnIndexOrThrow(AlertParams.this.mIsCheckedColumn);
                    }

                    public void bindView(View view, Context context, Cursor cursor) {
                        ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.mLabelIndex));
                        RecycleListView recycleListView = recycleListView;
                        int position = cursor.getPosition();
                        int i2 = cursor.getInt(this.mIsCheckedIndex);
                        boolean z = true;
                        if (i2 != 1) {
                            z = false;
                        }
                        recycleListView.setItemChecked(position, z);
                    }

                    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                        return AlertParams.this.mInflater.inflate(alertController2.mMultiChoiceItemLayout, viewGroup, false);
                    }
                };
            }
            alertController2.mAdapter = listAdapter;
            alertController2.mCheckedItem = alertParams.mCheckedItem;
            if (alertParams.mOnClickListener != null) {
                recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                        AlertParams.this.mOnClickListener.onClick(alertController2.mDialog, i2);
                        if (!AlertParams.this.mIsSingleChoice) {
                            alertController2.mDialog.dismiss();
                        }
                    }
                });
            } else if (alertParams.mOnCheckboxClickListener != null) {
                recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                        boolean[] zArr = AlertParams.this.mCheckedItems;
                        if (zArr != null) {
                            zArr[i2] = recycleListView.isItemChecked(i2);
                        }
                        AlertParams.this.mOnCheckboxClickListener.onClick(alertController2.mDialog, i2, recycleListView.isItemChecked(i2));
                    }
                });
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = alertParams.mOnItemSelectedListener;
            if (onItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (alertParams.mIsSingleChoice) {
                recycleListView.setChoiceMode(1);
            } else if (alertParams.mIsMultiChoice) {
                recycleListView.setChoiceMode(2);
            }
            alertController2.mListView = recycleListView;
        }

        public void apply(AlertController alertController) {
            View view = this.mCustomTitleView;
            if (view != null) {
                alertController.setCustomTitle(view);
            } else {
                CharSequence charSequence = this.mTitle;
                if (charSequence != null) {
                    alertController.setTitle(charSequence);
                }
                Drawable drawable = this.mIcon;
                if (drawable != null) {
                    alertController.setIcon(drawable);
                }
                int i2 = this.mIconId;
                if (i2 != 0) {
                    alertController.setIcon(i2);
                }
                int i7 = this.mIconAttrId;
                if (i7 != 0) {
                    alertController.setIcon(alertController.getIconAttributeResId(i7));
                }
            }
            CharSequence charSequence2 = this.mMessage;
            if (charSequence2 != null) {
                alertController.setMessage(charSequence2);
            }
            CharSequence charSequence3 = this.mSingleChoiceOptionLabel;
            if (!(charSequence3 == null || this.mMessage == null)) {
                alertController.setSingleChoiceOption(charSequence3, this.mSingleChoiceChecked, this.mSingleChoiceListener);
            }
            CharSequence charSequence4 = this.mPositiveButtonText;
            if (!(charSequence4 == null && this.mPositiveButtonIcon == null)) {
                alertController.setButton(-1, charSequence4, this.mPositiveButtonListener, (Message) null, this.mPositiveButtonIcon);
            }
            CharSequence charSequence5 = this.mNegativeButtonText;
            if (!(charSequence5 == null && this.mNegativeButtonIcon == null)) {
                alertController.setButton(-2, charSequence5, this.mNegativeButtonListener, (Message) null, this.mNegativeButtonIcon);
            }
            CharSequence charSequence6 = this.mNeutralButtonText;
            if (!(charSequence6 == null && this.mNeutralButtonIcon == null)) {
                alertController.setButton(-3, charSequence6, this.mNeutralButtonListener, (Message) null, this.mNeutralButtonIcon);
            }
            if (!(this.mItems == null && this.mCursor == null && this.mAdapter == null)) {
                createListView(alertController);
            }
            View view2 = this.mView;
            if (view2 == null) {
                int i8 = this.mViewLayoutResId;
                if (i8 != 0) {
                    alertController.setView(i8);
                }
            } else if (this.mViewSpacingSpecified) {
                alertController.setView(view2, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            } else {
                alertController.setView(view2);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ButtonHandler extends Handler {
        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialogInterface) {
            this.mDialog = new WeakReference<>(dialogInterface);
        }

        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == -3 || i2 == -2 || i2 == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.mDialog.get(), message.what);
            } else if (i2 == 1) {
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CheckedItemAdapter extends ArrayAdapter<CharSequence> {
        public CheckedItemAdapter(Context context, int i2, int i7, CharSequence[] charSequenceArr) {
            super(context, i2, i7, charSequenceArr);
        }

        public long getItemId(int i2) {
            return (long) i2;
        }

        public boolean hasStableIds() {
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RecycleListView extends ListView {
        private final int mPaddingBottomNoButtons;
        private final int mPaddingTopNoTitle;

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecycleListView);
            this.mPaddingBottomNoButtons = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.mPaddingTopNoTitle = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingTopNoTitle, -1);
        }

        public void setHasDecor(boolean z, boolean z3) {
            int i2;
            int i7;
            if (!z3 || !z) {
                int paddingLeft = getPaddingLeft();
                if (z) {
                    i2 = getPaddingTop();
                } else {
                    i2 = this.mPaddingTopNoTitle;
                }
                int paddingRight = getPaddingRight();
                if (z3) {
                    i7 = getPaddingBottom();
                } else {
                    i7 = this.mPaddingBottomNoButtons;
                }
                setPadding(paddingLeft, i2, paddingRight, i7);
            }
        }
    }

    public AlertController(Context context, AppCompatDialog appCompatDialog, Window window) {
        this.mContext = context;
        this.mDialog = appCompatDialog;
        this.mWindow = window;
        this.mHandler = new ButtonHandler(appCompatDialog);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, R$styleable.AlertDialog, R$attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_android_layout, 0);
        this.mButtonPanelSideLayout = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.mListLayout = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listLayout, 0);
        this.mMultiChoiceItemLayout = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.mSingleChoiceItemLayout = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.mListItemLayout = obtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listItemLayout, 0);
        this.mShowTitle = obtainStyledAttributes.getBoolean(R$styleable.AlertDialog_showTitle, true);
        this.mButtonIconDimen = obtainStyledAttributes.getDimensionPixelSize(R$styleable.AlertDialog_buttonIconDimen, 0);
        obtainStyledAttributes.recycle();
        appCompatDialog.supportRequestWindowFeature(1);
    }

    private void adjustButtonsPadding() {
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_dialog_button_text_size);
        if (this.mButtonPositive.getVisibility() != 8) {
            this.mButtonPositive.setTextSize(0, (float) dimensionPixelSize);
            checkMaxFontScale(this.mButtonPositive, dimensionPixelSize);
        }
        if (this.mButtonNegative.getVisibility() != 8) {
            this.mButtonNegative.setTextSize(0, (float) dimensionPixelSize);
            checkMaxFontScale(this.mButtonNegative, dimensionPixelSize);
        }
        if (this.mButtonNeutral.getVisibility() != 8) {
            this.mButtonNeutral.setTextSize(0, (float) dimensionPixelSize);
            checkMaxFontScale(this.mButtonNeutral, dimensionPixelSize);
        }
    }

    private void adjustParentPanelPadding(View view) {
        view.setPadding(0, 0, 0, 0);
    }

    private void adjustTopPanelPadding(View view) {
        View findViewById = view.findViewById(R$id.title_template);
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_dialog_padding_horizontal);
        findViewById.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
    }

    public static boolean canTextInput(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (canTextInput(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    private void centerButton(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    private void checkMaxFontScale(TextView textView, int i2) {
        float f = this.mContext.getResources().getConfiguration().fontScale;
        if (f > 1.3f) {
            textView.setTextSize(0, (((float) i2) / f) * 1.3f);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$seslExpandTouchTarget$0(LinearLayout linearLayout) {
        ArrayList arrayList = new ArrayList();
        int childCount = linearLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = linearLayout.getChildAt(i2);
            if ((childAt instanceof Button) && childAt.getVisibility() != 8) {
                arrayList.add(childAt);
            }
        }
        if (linearLayout.getLayoutDirection() == 1 && linearLayout.getOrientation() == 0) {
            Collections.reverse(arrayList);
        }
        SeslTouchTargetDelegate.Builder make = SeslTouchDelegateFactory.make(linearLayout, arrayList);
        if (make != null) {
            make.apply();
        }
    }

    private boolean requestFocusForContent(View view) {
        if (view != null && view.requestFocus()) {
            return true;
        }
        ListView listView = this.mListView;
        if (listView == null) {
            return false;
        }
        listView.setSelection(0);
        return true;
    }

    private void requestFocusForDefaultButton() {
        if (this.mButtonPositive.getVisibility() == 0) {
            this.mButtonPositive.requestFocus();
        } else if (this.mButtonNegative.getVisibility() == 0) {
            this.mButtonNegative.requestFocus();
        } else if (this.mButtonNeutral.getVisibility() == 0) {
            this.mButtonNeutral.requestFocus();
        }
    }

    private ViewGroup resolvePanel(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    private int selectContentView() {
        int i2 = this.mButtonPanelSideLayout;
        if (i2 == 0) {
            return this.mAlertDialogLayout;
        }
        if (this.mButtonPanelLayoutHint == 1) {
            return i2;
        }
        return this.mAlertDialogLayout;
    }

    /* access modifiers changed from: private */
    public void seslExpandTouchTarget(ViewGroup viewGroup) {
        LinearLayout linearLayout;
        if (viewGroup != null && (linearLayout = (LinearLayout) viewGroup.findViewById(R$id.buttonBarLayout)) != null) {
            linearLayout.post(new b(linearLayout));
        }
    }

    private void setScrollIndicators(ViewGroup viewGroup, View view, int i2, int i7) {
        View findViewById = this.mWindow.findViewById(R$id.scrollIndicatorUp);
        View findViewById2 = this.mWindow.findViewById(R$id.scrollIndicatorDown);
        ViewCompat.setScrollIndicators(view, i2, i7);
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
        }
        if (findViewById2 != null) {
            viewGroup.removeView(findViewById2);
        }
    }

    private void setupButtons(ViewGroup viewGroup) {
        boolean z;
        int i2;
        boolean z3;
        boolean z7;
        boolean z9;
        ViewGroup viewGroup2;
        ContentResolver contentResolver = this.mContext.getContentResolver();
        boolean z10 = true;
        if (contentResolver == null || Settings.System.getInt(contentResolver, "show_button_background", 0) != 1) {
            z = false;
        } else {
            z = true;
        }
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(16842801, typedValue, true);
        if (typedValue.resourceId > 0) {
            i2 = this.mContext.getResources().getColor(typedValue.resourceId);
        } else {
            i2 = -1;
        }
        Button button = (Button) viewGroup.findViewById(16908313);
        this.mButtonPositive = button;
        button.setOnClickListener(this.mButtonHandler);
        if (typedValue.resourceId > 0) {
            SeslTextViewReflector.semSetButtonShapeEnabled(this.mButtonPositive, z, i2);
        } else {
            SeslTextViewReflector.semSetButtonShapeEnabled(this.mButtonPositive, z);
        }
        if (!TextUtils.isEmpty(this.mButtonPositiveText) || this.mButtonPositiveIcon != null) {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            Drawable drawable = this.mButtonPositiveIcon;
            if (drawable != null) {
                int i7 = this.mButtonIconDimen;
                drawable.setBounds(0, 0, i7, i7);
                this.mButtonPositive.setCompoundDrawables(this.mButtonPositiveIcon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonPositive.setVisibility(0);
            z3 = true;
        } else {
            this.mButtonPositive.setVisibility(8);
            z3 = false;
        }
        Button button2 = (Button) viewGroup.findViewById(16908314);
        this.mButtonNegative = button2;
        button2.setOnClickListener(this.mButtonHandler);
        if (typedValue.resourceId > 0) {
            SeslTextViewReflector.semSetButtonShapeEnabled(this.mButtonNegative, z, i2);
        } else {
            SeslTextViewReflector.semSetButtonShapeEnabled(this.mButtonNegative, z);
        }
        if (!TextUtils.isEmpty(this.mButtonNegativeText) || this.mButtonNegativeIcon != null) {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            Drawable drawable2 = this.mButtonNegativeIcon;
            if (drawable2 != null) {
                int i8 = this.mButtonIconDimen;
                drawable2.setBounds(0, 0, i8, i8);
                this.mButtonNegative.setCompoundDrawables(this.mButtonNegativeIcon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonNegative.setVisibility(0);
            z3 |= true;
        } else {
            this.mButtonNegative.setVisibility(8);
        }
        Button button3 = (Button) viewGroup.findViewById(16908315);
        this.mButtonNeutral = button3;
        button3.setOnClickListener(this.mButtonHandler);
        if (typedValue.resourceId > 0) {
            SeslTextViewReflector.semSetButtonShapeEnabled(this.mButtonNeutral, z, i2);
        } else {
            SeslTextViewReflector.semSetButtonShapeEnabled(this.mButtonNeutral, z);
        }
        if (!TextUtils.isEmpty(this.mButtonNeutralText) || this.mButtonNeutralIcon != null) {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            Drawable drawable3 = this.mButtonNeutralIcon;
            if (drawable3 != null) {
                int i10 = this.mButtonIconDimen;
                drawable3.setBounds(0, 0, i10, i10);
                this.mButtonNeutral.setCompoundDrawables(this.mButtonNeutralIcon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonNeutral.setVisibility(0);
            z3 |= true;
        } else {
            this.mButtonNeutral.setVisibility(8);
        }
        if (shouldCenterSingleButton(this.mContext)) {
            if (z3) {
                centerButton(this.mButtonPositive);
            } else if (z3) {
                centerButton(this.mButtonNegative);
            } else if (z3) {
                centerButton(this.mButtonNeutral);
            }
        }
        if (!z3) {
            viewGroup.setVisibility(8);
        }
        if (this.mButtonNeutral.getVisibility() == 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        if (this.mButtonPositive.getVisibility() == 0) {
            z9 = true;
        } else {
            z9 = false;
        }
        if (this.mButtonNegative.getVisibility() != 0) {
            z10 = false;
        }
        View findViewById = this.mWindow.findViewById(R$id.sem_divider2);
        if (findViewById != null && ((z7 && z9) || (z7 && z10))) {
            findViewById.setVisibility(0);
        }
        View findViewById2 = this.mWindow.findViewById(R$id.sem_divider1);
        if (findViewById2 != null && z9 && z10) {
            findViewById2.setVisibility(0);
        }
        if (this.mDefaultButtonPanelJob != null && (viewGroup2 = (ViewGroup) viewGroup.findViewById(R$id.buttonBarLayout)) != null) {
            this.mDefaultButtonPanelJob.accept(viewGroup2);
        }
    }

    private void setupContent(ViewGroup viewGroup) {
        NestedScrollView nestedScrollView = (NestedScrollView) this.mWindow.findViewById(R$id.scrollView);
        this.mScrollView = nestedScrollView;
        nestedScrollView.setFocusable(false);
        this.mScrollView.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroup.findViewById(16908299);
        this.mMessageView = textView;
        if (textView != null) {
            CharSequence charSequence = this.mMessage;
            if (charSequence != null) {
                textView.setText(charSequence);
                checkMaxFontScale(this.mMessageView, this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_dialog_body_text_size));
                CheckBox checkBox = (CheckBox) viewGroup.findViewById(R$id.single_choice_option);
                if (checkBox != null) {
                    CharSequence charSequence2 = this.mSingleChoiceOptionLabel;
                    if (charSequence2 != null) {
                        checkBox.setText(charSequence2);
                        checkBox.setChecked(this.mSingleChoiceChecked);
                        checkBox.setVisibility(0);
                        checkBox.setOnCheckedChangeListener(this.mSingleChoiceListener);
                        return;
                    }
                    checkBox.setVisibility(8);
                    return;
                }
                return;
            }
            textView.setVisibility(8);
            this.mScrollView.removeView(this.mMessageView);
            if (this.mListView != null) {
                ViewGroup viewGroup2 = (ViewGroup) this.mScrollView.getParent();
                int indexOfChild = viewGroup2.indexOfChild(this.mScrollView);
                viewGroup2.removeViewAt(indexOfChild);
                viewGroup2.addView(this.mListView, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
                return;
            }
            viewGroup.setVisibility(8);
        }
    }

    private void setupCustomContent(ViewGroup viewGroup) {
        View view = this.mView;
        boolean z = false;
        if (view == null) {
            if (this.mViewLayoutResId != 0) {
                view = LayoutInflater.from(this.mContext).inflate(this.mViewLayoutResId, viewGroup, false);
            } else {
                view = null;
            }
        }
        if (view != null) {
            z = true;
        }
        if (!z || !canTextInput(view)) {
            this.mWindow.setFlags(131072, 131072);
        }
        if (z) {
            FrameLayout frameLayout = (FrameLayout) this.mWindow.findViewById(R$id.custom);
            frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
            if (this.mViewSpacingSpecified) {
                frameLayout.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            }
            if (this.mListView == null) {
                return;
            }
            if (viewGroup.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) viewGroup.getLayoutParams()).weight = 0.0f;
            } else {
                ((LinearLayoutCompat.LayoutParams) viewGroup.getLayoutParams()).weight = 0.0f;
            }
        } else {
            viewGroup.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void setupPaddings() {
        boolean z;
        boolean z3;
        boolean z7;
        View findViewById = this.mWindow.findViewById(R$id.parentPanel);
        View findViewById2 = this.mWindow.findViewById(R$id.middlePanel);
        View findViewById3 = findViewById.findViewById(R$id.title_template);
        View findViewById4 = findViewById.findViewById(R$id.scrollView);
        View findViewById5 = findViewById.findViewById(R$id.topPanel);
        View findViewById6 = findViewById.findViewById(R$id.buttonBarLayout);
        View findViewById7 = findViewById.findViewById(R$id.customPanel);
        View findViewById8 = findViewById.findViewById(R$id.contentPanel);
        boolean z9 = true;
        if (findViewById7 == null || findViewById7.getVisibility() == 8) {
            z = false;
        } else {
            z = true;
        }
        if (findViewById5 == null || findViewById5.getVisibility() == 8) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (findViewById8 == null || findViewById8.getVisibility() == 8) {
            z7 = false;
        } else {
            z7 = true;
        }
        View view = this.mCustomTitleView;
        if (view == null || view.getVisibility() == 8) {
            z9 = false;
        }
        Resources resources = this.mContext.getResources();
        if (findViewById2 != null) {
            if ((!z || z3 || z7) && !z9) {
                findViewById2.setPadding(0, resources.getDimensionPixelSize(R$dimen.sesl_dialog_title_padding_top), 0, 0);
            } else {
                findViewById2.setPadding(0, 0, 0, 0);
            }
        }
        if (findViewById3 != null) {
            int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.sesl_dialog_padding_horizontal);
            if (!z || !z3 || z7) {
                findViewById3.setPadding(dimensionPixelSize, 0, dimensionPixelSize, resources.getDimensionPixelSize(R$dimen.sesl_dialog_title_padding_bottom));
            } else {
                findViewById3.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
            }
        }
        if (findViewById4 != null) {
            findViewById4.setPadding(resources.getDimensionPixelSize(R$dimen.sesl_dialog_body_text_scroll_padding_start), 0, resources.getDimensionPixelSize(R$dimen.sesl_dialog_body_text_scroll_padding_end), resources.getDimensionPixelSize(R$dimen.sesl_dialog_body_text_padding_bottom));
        }
        if (findViewById6 != null) {
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.sesl_dialog_button_bar_padding_horizontal);
            findViewById6.setPadding(dimensionPixelSize2, 0, dimensionPixelSize2, resources.getDimensionPixelSize(R$dimen.sesl_dialog_button_bar_padding_bottom));
        }
    }

    private void setupTitle(ViewGroup viewGroup) {
        if (this.mCustomTitleView != null) {
            viewGroup.addView(this.mCustomTitleView, 0, new ViewGroup.LayoutParams(-1, -2));
            this.mWindow.findViewById(R$id.title_template).setVisibility(8);
            return;
        }
        this.mIconView = (ImageView) this.mWindow.findViewById(16908294);
        if (TextUtils.isEmpty(this.mTitle) || !this.mShowTitle) {
            this.mWindow.findViewById(R$id.title_template).setVisibility(8);
            this.mIconView.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        TextView textView = (TextView) this.mWindow.findViewById(R$id.alertTitle);
        this.mTitleView = textView;
        textView.setText(this.mTitle);
        checkMaxFontScale(this.mTitleView, this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_dialog_title_text_size));
        int i2 = this.mIconId;
        if (i2 != 0) {
            this.mIconView.setImageResource(i2);
            return;
        }
        Drawable drawable = this.mIcon;
        if (drawable != null) {
            this.mIconView.setImageDrawable(drawable);
            return;
        }
        this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
        this.mIconView.setVisibility(8);
    }

    private void setupView() {
        a aVar;
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        SemBlurCompat.CurveParameter curveParameter;
        ListAdapter listAdapter;
        char c5;
        NestedScrollView nestedScrollView;
        final View findViewById = this.mWindow.findViewById(R$id.parentPanel);
        View findViewById2 = this.mWindow.findViewById(R$id.middlePanel);
        findViewById.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
                view.post(new Runnable() {
                    public void run() {
                        if (AlertController.this.mContext.getResources().getConfiguration().orientation != AlertController.this.mLastOrientation) {
                            AlertController.this.setupPaddings();
                            findViewById.requestLayout();
                        }
                        AlertController alertController = AlertController.this;
                        int unused = alertController.mLastOrientation = alertController.mContext.getResources().getConfiguration().orientation;
                    }
                });
            }
        });
        int i2 = R$id.topPanel;
        View findViewById3 = findViewById.findViewById(i2);
        int i7 = R$id.contentPanel;
        View findViewById4 = findViewById.findViewById(i7);
        int i8 = R$id.buttonPanel;
        View findViewById5 = findViewById.findViewById(i8);
        ViewGroup viewGroup = (ViewGroup) findViewById.findViewById(R$id.customPanel);
        setupCustomContent(viewGroup);
        View findViewById6 = viewGroup.findViewById(i2);
        View findViewById7 = viewGroup.findViewById(i7);
        View findViewById8 = viewGroup.findViewById(i8);
        ViewGroup resolvePanel = resolvePanel(findViewById6, findViewById3);
        ViewGroup resolvePanel2 = resolvePanel(findViewById7, findViewById4);
        ViewGroup resolvePanel3 = resolvePanel(findViewById8, findViewById5);
        if (resolvePanel3 == findViewById5) {
            aVar = new a(this);
        } else {
            aVar = null;
        }
        this.mDefaultButtonPanelJob = aVar;
        setupContent(resolvePanel2);
        setupButtons(resolvePanel3);
        setupTitle(resolvePanel);
        boolean z14 = true;
        if (viewGroup.getVisibility() != 8) {
            z = true;
        } else {
            z = false;
        }
        if (resolvePanel == null || resolvePanel.getVisibility() == 8) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (resolvePanel3 == null || resolvePanel3.getVisibility() == 8) {
            z7 = false;
        } else {
            z7 = true;
        }
        if (findViewById3 == null || findViewById3.getVisibility() == 8) {
            z9 = false;
        } else {
            z9 = true;
        }
        if (findViewById4 == null || findViewById4.getVisibility() == 8) {
            z10 = false;
        } else {
            z10 = true;
        }
        View view = this.mCustomTitleView;
        if (view == null || view.getVisibility() == 8) {
            z11 = false;
        } else {
            z11 = true;
        }
        if ((z && !z9 && !z10) || z11) {
            adjustParentPanelPadding(findViewById2);
        }
        if (z && z9 && !z10) {
            adjustTopPanelPadding(findViewById);
        }
        adjustButtonsPadding();
        if (!findViewById.isInTouchMode()) {
            if (!z) {
                viewGroup = resolvePanel2;
            }
            if (!requestFocusForContent(viewGroup)) {
                requestFocusForDefaultButton();
            }
        }
        if (z3 && (nestedScrollView = this.mScrollView) != null) {
            nestedScrollView.setClipToPadding(true);
        }
        ListView listView = this.mListView;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).setHasDecor(z3, z7);
        }
        if (!z) {
            View view2 = this.mListView;
            if (view2 == null) {
                view2 = this.mScrollView;
            }
            if (view2 != null) {
                if (z7) {
                    c5 = 2;
                } else {
                    c5 = 0;
                }
                setScrollIndicators(resolvePanel2, view2, z3 | c5 ? 1 : 0, 3);
            }
        }
        ListView listView2 = this.mListView;
        if (!(listView2 == null || (listAdapter = this.mAdapter) == null)) {
            listView2.setAdapter(listAdapter);
            SeslAdapterViewReflector.semSetBottomColor(listView2, 0);
            int i10 = this.mCheckedItem;
            if (i10 > -1) {
                listView2.setItemChecked(i10, true);
                listView2.setSelectionFromTop(i10, this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_select_dialog_padding_top));
            }
        }
        if (Build.VERSION.SDK_INT >= 36) {
            String string = SeslFloatingFeatureReflector.getString("SEC_FLOATING_FEATURE_GRAPHICS_SUPPORT_3D_SURFACE_TRANSITION_FLAG", "FALSE");
            boolean isLightTheme = SeslMisc.isLightTheme(this.mContext);
            if (Settings.System.getString(this.mContext.getContentResolver(), "current_sec_active_themepackage") != null) {
                z12 = true;
            } else {
                z12 = false;
            }
            if (z) {
                z13 = this.mIsBlurEnabled;
            } else {
                z13 = this.mIsDefaultBlurEnabled;
            }
            Drawable drawable = this.mContext.getResources().getDrawable(R$drawable.sesl_dialog_inset_background, this.mContext.getTheme());
            View decorView = this.mWindow.getDecorView();
            if (!(decorView == null || decorView.getBackground() == null || drawable.getConstantState() == null || drawable.getConstantState().equals(decorView.getBackground().getConstantState()))) {
                z14 = false;
            }
            if (!"FALSE".equalsIgnoreCase(string) && z13 && !z12 && z14) {
                if (findViewById2 != null && findViewById2.getBackground() == null && !isLightTheme) {
                    findViewById2.setBackground(this.mContext.getDrawable(R$drawable.sesl_dialog_middle_panel_background));
                }
                int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_dialog_background_corner_radius);
                if (isLightTheme) {
                    curveParameter = new SemBlurCompat.CurveParameter(StatusCodes.INPUT_MISSING, 0.75f, 25.0f, 15.0f, 235.0f, 214.6f, 252.8f);
                } else {
                    curveParameter = new SemBlurCompat.CurveParameter(StatusCodes.INPUT_MISSING, 0.7f, -15.0f, 0.0f, 235.0f, 36.7f, 87.7f);
                }
                SemBlurCompat.setBlurEffectPreset(findViewById, 0, curveParameter, Integer.valueOf(this.mContext.getColor(R$color.sesl_dialog_blur_background_color)), Float.valueOf((float) dimensionPixelSize), 0);
            }
        }
    }

    private static boolean shouldCenterSingleButton(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            return true;
        }
        return false;
    }

    public Button getButton(int i2) {
        if (i2 == -3) {
            return this.mButtonNeutral;
        }
        if (i2 == -2) {
            return this.mButtonNegative;
        }
        if (i2 != -1) {
            return null;
        }
        return this.mButtonPositive;
    }

    public int getIconAttributeResId(int i2) {
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(i2, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public void installContent() {
        this.mDialog.setContentView(selectContentView());
        setupView();
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.mScrollView;
        if (nestedScrollView == null || !nestedScrollView.executeKeyEvent(keyEvent)) {
            return false;
        }
        return true;
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.mScrollView;
        if (nestedScrollView == null || !nestedScrollView.executeKeyEvent(keyEvent)) {
            return false;
        }
        return true;
    }

    public void seslSetBackgroundBlurEnabled(boolean z) {
        this.mIsBlurEnabled = z;
        this.mIsDefaultBlurEnabled = z;
    }

    public void setButton(int i2, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.mHandler.obtainMessage(i2, onClickListener);
        }
        if (i2 == -3) {
            this.mButtonNeutralText = charSequence;
            this.mButtonNeutralMessage = message;
            this.mButtonNeutralIcon = drawable;
        } else if (i2 == -2) {
            this.mButtonNegativeText = charSequence;
            this.mButtonNegativeMessage = message;
            this.mButtonNegativeIcon = drawable;
        } else if (i2 == -1) {
            this.mButtonPositiveText = charSequence;
            this.mButtonPositiveMessage = message;
            this.mButtonPositiveIcon = drawable;
        } else {
            throw new IllegalArgumentException("Button does not exist");
        }
    }

    public void setCustomTitle(View view) {
        this.mCustomTitleView = view;
    }

    public void setIcon(int i2) {
        this.mIcon = null;
        this.mIconId = i2;
        ImageView imageView = this.mIconView;
        if (imageView == null) {
            return;
        }
        if (i2 != 0) {
            imageView.setVisibility(0);
            this.mIconView.setImageResource(this.mIconId);
            return;
        }
        imageView.setVisibility(8);
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
        TextView textView = this.mMessageView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void setSingleChoiceOption(CharSequence charSequence, boolean z, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.mSingleChoiceOptionLabel = charSequence;
        this.mSingleChoiceChecked = z;
        this.mSingleChoiceListener = onCheckedChangeListener;
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        TextView textView = this.mTitleView;
        if (textView != null) {
            textView.setText(charSequence);
        }
        this.mWindow.setTitle(charSequence);
    }

    public void setView(int i2) {
        this.mView = null;
        this.mViewLayoutResId = i2;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view) {
        this.mView = view;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = false;
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        this.mIconId = 0;
        ImageView imageView = this.mIconView;
        if (imageView == null) {
            return;
        }
        if (drawable != null) {
            imageView.setVisibility(0);
            this.mIconView.setImageDrawable(drawable);
            return;
        }
        imageView.setVisibility(8);
    }

    public void setView(View view, int i2, int i7, int i8, int i10) {
        this.mView = view;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = true;
        this.mViewSpacingLeft = i2;
        this.mViewSpacingTop = i7;
        this.mViewSpacingRight = i8;
        this.mViewSpacingBottom = i10;
    }
}
