package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.picker.widget.SeslDatePicker;
import androidx.picker.widget.SeslTimePicker;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.android.gallery3d.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;
import o6.B;
import q4.h;
import q4.i;
import q4.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DateTimePickerDialog extends BaseDialog {
    private Drawable mActiveBackgroundDrawable;
    private Typeface mActiveTextStyle;
    private Calendar mCurrentDate;
    /* access modifiers changed from: private */
    public LinearLayout mDateContainer;
    /* access modifiers changed from: private */
    public SeslDatePicker mDatePicker;
    /* access modifiers changed from: private */
    public TextView mDateTitle;
    /* access modifiers changed from: private */
    public TextView mDateValue;
    private boolean mHasVideo;
    private int mInactiveTextColor;
    private Typeface mInactiveTextStyle;
    private boolean mIsTimeChanged;
    /* access modifiers changed from: private */
    public LinearLayout mTimeContainer;
    private DateFormat mTimeFormat;
    /* access modifiers changed from: private */
    public SeslTimePicker mTimePicker;
    /* access modifiers changed from: private */
    public TextView mTimeTitle;
    /* access modifiers changed from: private */
    public TextView mTimeValue;
    private int mTitleActiveTextColor;
    private int mValueActiveTextColor;

    private void bind(View view) {
        bindDateViews(view);
        bindTimeViews(view);
        ViewUtils.setTouchAreaComposite(this.mDateContainer, view.getResources().getDimensionPixelSize(R.dimen.range_date_picker_header_padding_side), 0, 0, 0);
        ViewUtils.setTouchAreaComposite(this.mTimeContainer, 0, 0, view.getResources().getDimensionPixelSize(R.dimen.range_date_picker_header_padding_side), 0);
        initAccessibilityDelegate();
    }

    private void bindDateViews(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.date_container);
        this.mDateContainer = linearLayout;
        linearLayout.setOnClickListener(new i(this, 1));
        TextView textView = (TextView) view.findViewById(R.id.date_title);
        this.mDateTitle = textView;
        textView.setContentDescription(this.mDateTitle.getResources().getString(R.string.date_taken) + ArcCommonLog.TAG_COMMA + this.mDateTitle.getResources().getString(R.string.speak_tab));
        this.mDateValue = (TextView) view.findViewById(R.id.date_value);
        SeslDatePicker seslDatePicker = (SeslDatePicker) view.findViewById(R.id.date_picker);
        this.mDatePicker = seslDatePicker;
        seslDatePicker.init(this.mCurrentDate.get(1), this.mCurrentDate.get(2), this.mCurrentDate.get(5), new j(this));
        this.mDatePicker.setCurrentViewType(1);
        if (this.mHasVideo) {
            this.mDatePicker.setMinDate(TimeUtil.getDateTimeMillis(1971, 0, 1, 0, 0, 0));
            this.mDatePicker.setMaxDate(TimeUtil.getDateTimeMillis(2039, 11, 31, 23, 59, 59));
        }
    }

    private void bindTimeViews(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.time_container);
        this.mTimeContainer = linearLayout;
        linearLayout.setOnClickListener(new i(this, 0));
        this.mTimeTitle = (TextView) view.findViewById(R.id.time_title);
        TextView textView = (TextView) view.findViewById(R.id.time_value);
        this.mTimeValue = textView;
        textView.setText(this.mTimeFormat.format(Long.valueOf(this.mCurrentDate.getTimeInMillis())));
        SeslTimePicker seslTimePicker = (SeslTimePicker) view.findViewById(R.id.time_picker);
        this.mTimePicker = seslTimePicker;
        seslTimePicker.setIs24HourView(Boolean.valueOf(TimeUtil.is24HourFormat(getContext())));
        this.mTimePicker.showMarginLeft(Boolean.TRUE);
        this.mTimePicker.setHour(this.mCurrentDate.get(11));
        this.mTimePicker.setMinute(this.mCurrentDate.get(12));
        this.mTimePicker.setOnTimeChangedListener(new j(this));
    }

    private void changePicker(View view, boolean z) {
        updateDatePicker(z);
        updateTimePicker(!z);
        Utils.hideSip(view.getWindowToken());
    }

    private int getLayoutId() {
        return R.layout.date_time_picker_dialog;
    }

    private View inflateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(getLayoutId(), (ViewGroup) null, false);
        bind(inflate);
        return inflate;
    }

    private void initAccessibilityDelegate() {
        this.mDateContainer.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void sendAccessibilityEvent(View view, int i2) {
                String str;
                if (i2 == 32768) {
                    LinearLayout j2 = DateTimePickerDialog.this.mDateContainer;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(DateTimePickerDialog.this.mDateTitle.getText());
                    sb2.append(ArcCommonLog.TAG_COMMA);
                    sb2.append(DateTimePickerDialog.this.mDateValue.getText());
                    sb2.append(ArcCommonLog.TAG_COMMA);
                    sb2.append(DateTimePickerDialog.this.mDateContainer.getResources().getString(R.string.speak_tab));
                    if (DateTimePickerDialog.this.mDatePicker.isShown()) {
                        str = ArcCommonLog.TAG_COMMA + DateTimePickerDialog.this.mDateContainer.getResources().getString(R.string.speak_item_selected);
                    } else {
                        str = "";
                    }
                    sb2.append(str);
                    j2.setContentDescription(sb2.toString());
                }
                super.sendAccessibilityEvent(view, i2);
            }
        });
        this.mTimeContainer.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void sendAccessibilityEvent(View view, int i2) {
                String str;
                if (i2 == 32768) {
                    LinearLayout n = DateTimePickerDialog.this.mTimeContainer;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(DateTimePickerDialog.this.mTimeTitle.getText());
                    sb2.append(ArcCommonLog.TAG_COMMA);
                    sb2.append(DateTimePickerDialog.this.mTimeValue.getText());
                    sb2.append(ArcCommonLog.TAG_COMMA);
                    sb2.append(DateTimePickerDialog.this.mTimeContainer.getResources().getString(R.string.speak_tab));
                    if (DateTimePickerDialog.this.mTimePicker.isShown()) {
                        str = ArcCommonLog.TAG_COMMA + DateTimePickerDialog.this.mTimeContainer.getResources().getString(R.string.speak_item_selected);
                    } else {
                        str = "";
                    }
                    sb2.append(str);
                    n.setContentDescription(sb2.toString());
                }
                super.sendAccessibilityEvent(view, i2);
            }
        });
    }

    private void initDateTime(Bundle bundle) {
        this.mCurrentDate = Calendar.getInstance();
        if (bundle != null) {
            String string = bundle.getString("current_date", (String) null);
            this.mHasVideo = BundleWrapper.getBoolean(bundle, "has_video");
            if (string != null) {
                String[] split = string.split(" ");
                updateDate(split[0].split(NumericEnum.SEP));
                updateTime(split[1].split(NumericEnum.SEP));
            }
        }
    }

    private void initResources(Context context) {
        this.mActiveBackgroundDrawable = context.getDrawable(R.drawable.range_date_picker_date_area_drawable);
        this.mTitleActiveTextColor = context.getColor(R.color.date_picker_active_date_title_text_color);
        this.mValueActiveTextColor = context.getColor(R.color.date_picker_active_date_text_color);
        this.mInactiveTextColor = context.getColor(R.color.date_picker_inactive_date_text_color);
        this.mActiveTextStyle = ResourceUtil.SEC_600;
        this.mInactiveTextStyle = ResourceUtil.SEC_400;
    }

    private void initTimeFormat() {
        DateFormat dateFormat;
        if (TimeUtil.is24HourFormat(getContext())) {
            dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        } else {
            dateFormat = DateFormat.getTimeInstance(3, Locale.getDefault());
        }
        this.mTimeFormat = dateFormat;
    }

    private void initialize(Context context, Bundle bundle) {
        initDateTime(bundle);
        initTimeFormat();
        initResources(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindDateViews$1(View view) {
        changePicker(view, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindTimeViews$2(View view) {
        changePicker(view, false);
    }

    /* access modifiers changed from: private */
    public void onDateChanged(SeslDatePicker seslDatePicker, int i2, int i7, int i8) {
        this.mCurrentDate.set(i2, i7, i8);
        this.mDateValue.setText(TimeUtil.toLocalDate(this.mCurrentDate.getTimeInMillis(), "MD"));
    }

    /* access modifiers changed from: private */
    public void onTimeChanged(SeslTimePicker seslTimePicker, int i2, int i7) {
        this.mCurrentDate.set(11, i2);
        this.mCurrentDate.set(12, i7);
        this.mTimeValue.setText(this.mTimeFormat.format(Long.valueOf(this.mCurrentDate.getTimeInMillis())));
        this.mIsTimeChanged = true;
    }

    private void updateDate(String[] strArr) {
        this.mCurrentDate.set(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]) - 1, Integer.parseInt(strArr[2]));
    }

    private void updateDatePicker(boolean z) {
        Drawable drawable;
        Typeface typeface;
        int i2;
        int i7;
        ViewUtils.setVisibleOrInvisible(this.mDatePicker, z);
        LinearLayout linearLayout = this.mDateContainer;
        if (z) {
            drawable = this.mActiveBackgroundDrawable;
        } else {
            drawable = null;
        }
        linearLayout.setBackground(drawable);
        TextView textView = this.mDateTitle;
        if (z) {
            typeface = this.mActiveTextStyle;
        } else {
            typeface = this.mInactiveTextStyle;
        }
        textView.setTypeface(typeface);
        TextView textView2 = this.mDateTitle;
        if (z) {
            i2 = this.mTitleActiveTextColor;
        } else {
            i2 = this.mInactiveTextColor;
        }
        textView2.setTextColor(i2);
        TextView textView3 = this.mDateValue;
        if (z) {
            i7 = this.mValueActiveTextColor;
        } else {
            i7 = this.mInactiveTextColor;
        }
        textView3.setTextColor(i7);
    }

    private void updateTime(String[] strArr) {
        this.mCurrentDate.set(11, Integer.parseInt(strArr[0]));
        this.mCurrentDate.set(12, Integer.parseInt(strArr[1]));
        this.mCurrentDate.set(13, 0);
    }

    private void updateTimePicker(boolean z) {
        Drawable drawable;
        Typeface typeface;
        int i2;
        int i7;
        ViewUtils.setVisibleOrInvisible(this.mTimePicker, z);
        LinearLayout linearLayout = this.mTimeContainer;
        if (z) {
            drawable = this.mActiveBackgroundDrawable;
        } else {
            drawable = null;
        }
        linearLayout.setBackground(drawable);
        TextView textView = this.mTimeTitle;
        if (z) {
            typeface = this.mActiveTextStyle;
        } else {
            typeface = this.mInactiveTextStyle;
        }
        textView.setTypeface(typeface);
        TextView textView2 = this.mTimeTitle;
        if (z) {
            i2 = this.mTitleActiveTextColor;
        } else {
            i2 = this.mInactiveTextColor;
        }
        textView2.setTextColor(i2);
        TextView textView3 = this.mTimeValue;
        if (z) {
            i7 = this.mValueActiveTextColor;
        } else {
            i7 = this.mInactiveTextColor;
        }
        textView3.setTextColor(i7);
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        if (context == null) {
            return super.createDialog(bundle);
        }
        initialize(context, getArguments());
        AlertDialog create = new AlertDialog.Builder(context).setView(inflateView(context)).setPositiveButton((int) R.string.done, (DialogInterface.OnClickListener) new h(this, 0)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new h(this, 1)).create();
        Optional.ofNullable(create.getWindow()).ifPresent(new B(17));
        return create;
    }

    public void onCancelClicked(DialogInterface dialogInterface, int i2) {
        dismissAllowingStateLoss();
    }

    public void onDetach() {
        super.onDetach();
        Optional.ofNullable(getBlackboard()).ifPresent(new B(16));
    }

    public void onDoneClicked(DialogInterface dialogInterface, int i2) {
        if (this.mDatePicker.isEditTextMode()) {
            this.mDatePicker.setEditTextMode(false);
            this.mDatePicker.clearFocus();
        }
        getBlackboard().post("data://user/dialog/DateTimePicker", new Object[]{this.mCurrentDate, Boolean.valueOf(this.mIsTimeChanged)});
    }
}
