package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R$attr;
import androidx.appcompat.app.AlertController;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlertDialog extends AppCompatDialog implements DialogInterface {
    final AlertController mAlert = new AlertController(getContext(), this, getWindow());

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {

        /* renamed from: P  reason: collision with root package name */
        private final AlertController.AlertParams f981P;
        private final int mTheme;

        public Builder(Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        public AlertDialog create() {
            AlertDialog alertDialog = new AlertDialog(this.f981P.mContext, this.mTheme);
            this.f981P.apply(alertDialog.mAlert);
            alertDialog.setCancelable(this.f981P.mCancelable);
            if (this.f981P.mCancelable) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(this.f981P.mOnCancelListener);
            alertDialog.setOnDismissListener(this.f981P.mOnDismissListener);
            DialogInterface.OnKeyListener onKeyListener = this.f981P.mOnKeyListener;
            if (onKeyListener != null) {
                alertDialog.setOnKeyListener(onKeyListener);
            }
            return alertDialog;
        }

        public Context getContext() {
            return this.f981P.mContext;
        }

        public Builder setAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mAdapter = listAdapter;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.f981P.mCancelable = z;
            return this;
        }

        public Builder setCustomTitle(View view) {
            this.f981P.mCustomTitleView = view;
            return this;
        }

        public Builder setIcon(Drawable drawable) {
            this.f981P.mIcon = drawable;
            return this;
        }

        public Builder setItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mItems = charSequenceArr;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setMessage(int i2) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mMessage = alertParams.mContext.getText(i2);
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mItems = charSequenceArr;
            alertParams.mOnCheckboxClickListener = onMultiChoiceClickListener;
            alertParams.mCheckedItems = zArr;
            alertParams.mIsMultiChoice = true;
            return this;
        }

        public Builder setNegativeButton(int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mNegativeButtonText = alertParams.mContext.getText(i2);
            this.f981P.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mNeutralButtonText = alertParams.mContext.getText(i2);
            this.f981P.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.f981P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.f981P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.f981P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setPositiveButton(int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mPositiveButtonText = alertParams.mContext.getText(i2);
            this.f981P.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] charSequenceArr, int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mItems = charSequenceArr;
            alertParams.mOnClickListener = onClickListener;
            alertParams.mCheckedItem = i2;
            alertParams.mIsSingleChoice = true;
            return this;
        }

        public Builder setTitle(int i2) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mTitle = alertParams.mContext.getText(i2);
            return this;
        }

        public Builder setView(int i2) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mView = null;
            alertParams.mViewLayoutResId = i2;
            alertParams.mViewSpacingSpecified = false;
            return this;
        }

        public AlertDialog show() {
            AlertDialog create = create();
            create.show();
            return create;
        }

        public Builder(Context context, int i2) {
            this.f981P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, i2)));
            this.mTheme = i2;
        }

        public Builder setMessage(CharSequence charSequence) {
            this.f981P.mMessage = charSequence;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.f981P.mTitle = charSequence;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mNegativeButtonText = charSequence;
            alertParams.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mNeutralButtonText = charSequence;
            alertParams.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mPositiveButtonText = charSequence;
            alertParams.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setView(View view) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mView = view;
            alertParams.mViewLayoutResId = 0;
            alertParams.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter listAdapter, int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f981P;
            alertParams.mAdapter = listAdapter;
            alertParams.mOnClickListener = onClickListener;
            alertParams.mCheckedItem = i2;
            alertParams.mIsSingleChoice = true;
            return this;
        }
    }

    public AlertDialog(Context context, int i2) {
        super(context, resolveDialogTheme(context, i2));
    }

    public static int resolveDialogTheme(Context context, int i2) {
        if (((i2 >>> 24) & ScoverState.TYPE_NFC_SMART_COVER) >= 1) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public Button getButton(int i2) {
        return this.mAlert.getButton(i2);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlert.installContent();
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.mAlert.onKeyDown(i2, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (this.mAlert.onKeyUp(i2, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    public void seslSetBackgroundBlurEnabled(boolean z) {
        this.mAlert.seslSetBackgroundBlurEnabled(z);
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mAlert.setTitle(charSequence);
    }
}
