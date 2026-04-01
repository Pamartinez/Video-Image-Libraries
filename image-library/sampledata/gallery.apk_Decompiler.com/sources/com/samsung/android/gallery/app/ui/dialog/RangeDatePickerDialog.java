package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.picker.widget.SeslDatePicker;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.sec.android.gallery3d.R;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;
import l4.b;
import o6.B;
import o6.p;
import q4.n;
import q4.o;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RangeDatePickerDialog extends BaseDialog {
    private Drawable mActiveBackground;
    private int mActiveDateColor;
    private int mActiveTitleColor;
    private long mCurrentDate = -1;
    private SeslDatePicker mDatePicker;
    private boolean mDateSetRangeMode = false;
    private TextView mEndDate;
    private View mEndDateArea;
    private long mEndDateMs = 0;
    private TextView mEndDateTitle;
    private DateFormat mFormat;
    private int mInActiveDateColor;
    private int mInActiveTitleColor;
    private long mMaxDate = -1;
    private long mMinDate = -1;
    private TextView mStartDate;
    private View mStartDateArea;
    private long mStartDateMs = 0;
    private TextView mStartDateTitle;

    private void activeEndTab(boolean z) {
        Typeface typeface;
        int i2;
        int i7;
        Drawable drawable;
        TextView textView = this.mEndDateTitle;
        if (z) {
            typeface = ResourceUtil.SEC_600;
        } else {
            typeface = ResourceUtil.SEC_400;
        }
        textView.setTypeface(typeface);
        TextView textView2 = this.mEndDateTitle;
        if (z) {
            i2 = this.mActiveTitleColor;
        } else {
            i2 = this.mInActiveTitleColor;
        }
        textView2.setTextColor(i2);
        TextView textView3 = this.mEndDate;
        if (z) {
            i7 = this.mActiveDateColor;
        } else {
            i7 = this.mInActiveDateColor;
        }
        textView3.setTextColor(i7);
        View view = this.mEndDateArea;
        if (z) {
            drawable = this.mActiveBackground;
        } else {
            drawable = null;
        }
        view.setBackground(drawable);
    }

    private void activeStartTab(boolean z) {
        Typeface typeface;
        int i2;
        int i7;
        Drawable drawable;
        TextView textView = this.mStartDateTitle;
        if (z) {
            typeface = ResourceUtil.SEC_600;
        } else {
            typeface = ResourceUtil.SEC_400;
        }
        textView.setTypeface(typeface);
        TextView textView2 = this.mStartDateTitle;
        if (z) {
            i2 = this.mActiveTitleColor;
        } else {
            i2 = this.mInActiveTitleColor;
        }
        textView2.setTextColor(i2);
        TextView textView3 = this.mStartDate;
        if (z) {
            i7 = this.mActiveDateColor;
        } else {
            i7 = this.mInActiveDateColor;
        }
        textView3.setTextColor(i7);
        View view = this.mStartDateArea;
        if (z) {
            drawable = this.mActiveBackground;
        } else {
            drawable = null;
        }
        view.setBackground(drawable);
    }

    private void bind(Context context, View view) {
        this.mDatePicker = (SeslDatePicker) view.findViewById(R.id.date_picker);
        this.mStartDate = (TextView) view.findViewById(R.id.start_date);
        this.mEndDate = (TextView) view.findViewById(R.id.end_date);
        this.mStartDateTitle = (TextView) view.findViewById(R.id.start_date_title);
        this.mEndDateTitle = (TextView) view.findViewById(R.id.end_date_title);
        this.mStartDateArea = view.findViewById(R.id.start_date_area);
        this.mEndDateArea = view.findViewById(R.id.end_date_area);
        this.mStartDateArea.setOnClickListener(new o(this, 0));
        this.mEndDateArea.setOnClickListener(new o(this, 1));
        this.mActiveTitleColor = context.getColor(R.color.date_picker_active_date_title_text_color);
        this.mActiveDateColor = context.getColor(R.color.date_picker_active_date_text_color);
        this.mInActiveTitleColor = context.getColor(R.color.date_picker_inactive_date_title_text_color);
        this.mInActiveDateColor = context.getColor(R.color.date_picker_inactive_date_text_color);
        this.mActiveBackground = context.getDrawable(R.drawable.range_date_picker_date_area_drawable);
    }

    private String getDateFormatString(long j2) {
        return this.mFormat.format(Long.valueOf(j2));
    }

    private int getLayoutId() {
        return R.layout.date_picker_dialog;
    }

    private long getTimeOfDay(long j2) {
        return (j2 / 1000) * 1000;
    }

    private void initialize() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.mCurrentDate);
        int i2 = instance.get(1);
        int i7 = instance.get(2);
        int i8 = instance.get(5);
        this.mFormat = DateFormat.getDateInstance(1, Locale.getDefault());
        this.mDatePicker.init(i2, i7, i8, new p(3, this));
        this.mDatePicker.setDateMode(1);
        long j2 = this.mMinDate;
        if (j2 != -1) {
            this.mDatePicker.setMinDate(j2);
        }
        long j3 = this.mMaxDate;
        if (j3 != -1) {
            this.mDatePicker.setMaxDate(j3);
        }
        String dateFormatString = getDateFormatString(getTimeOfDay(instance.getTimeInMillis()));
        this.mStartDate.setText(dateFormatString);
        this.mEndDate.setText(dateFormatString);
        activeStartTab(true);
    }

    private boolean isValidDateRange() {
        if (this.mStartDateMs <= this.mEndDateMs) {
            return true;
        }
        return false;
    }

    private void loadArguments(Bundle bundle) {
        if (bundle != null) {
            this.mMinDate = BundleWrapper.getLong(bundle, "min_date", -1);
            this.mMaxDate = BundleWrapper.getLong(bundle, "max_date", -1);
            this.mCurrentDate = BundleWrapper.getLong(bundle, "current_date", -1);
        }
    }

    /* access modifiers changed from: private */
    public void onCancel(DialogInterface dialogInterface, int i2) {
        dismiss();
    }

    /* access modifiers changed from: private */
    public void onDone(DialogInterface dialogInterface, int i2) {
        this.mDatePicker.clearFocus();
        publishInternal(this.mStartDateMs, this.mEndDateMs);
    }

    /* access modifiers changed from: private */
    public void onEndTabClick(View view) {
        this.mDatePicker.clearFocus();
        this.mDatePicker.setDateMode(2);
        activeStartTab(false);
        activeEndTab(true);
        if (isValidDateRange()) {
            setEnabledDone(true);
        }
        this.mDateSetRangeMode = true;
    }

    /* access modifiers changed from: private */
    public void onStartTabClick(View view) {
        this.mDatePicker.clearFocus();
        this.mDatePicker.setDateMode(1);
        activeStartTab(true);
        activeEndTab(false);
    }

    private void publishInternal(long j2, long j3) {
        getBlackboard().post("data://user/dialog/RangeDatePicker", new Object[]{Long.valueOf(j2), Long.valueOf(j3)});
    }

    private void setDate(Calendar calendar, int i2) {
        long timeInMillis = calendar.getTimeInMillis();
        if (i2 == 0 || i2 == 1) {
            long timeOfDay = getTimeOfDay(timeInMillis);
            this.mStartDateMs = timeOfDay;
            this.mStartDate.setText(getDateFormatString(timeOfDay));
        }
        if (i2 == 0 || i2 == 2) {
            long timeOfDay2 = getTimeOfDay(timeInMillis) + 86399999;
            this.mEndDateMs = timeOfDay2;
            this.mEndDate.setText(getDateFormatString(timeOfDay2));
        }
    }

    private void setEnabledDone(boolean z) {
        Optional.ofNullable((AlertDialog) getDialog()).ifPresent(new b(z, 8));
    }

    /* access modifiers changed from: private */
    public void updateDate(SeslDatePicker seslDatePicker, int i2, int i7, int i8) {
        Calendar instance = Calendar.getInstance();
        instance.set(i2, i7, i8, 0, 0, 0);
        if (this.mStartDateMs <= 0 || this.mEndDateMs <= 0) {
            setDate(instance, 0);
        } else if (this.mDateSetRangeMode) {
            setDate(instance, seslDatePicker.getDateMode());
            if (!isValidDateRange()) {
                if (seslDatePicker.getDateMode() == 1) {
                    setDate(instance, 2);
                } else {
                    setDate(instance, 1);
                }
            }
            setEnabledDone(isValidDateRange());
        } else {
            setDate(instance, 0);
        }
    }

    public Dialog createDialog(Bundle bundle) {
        Context context = getContext();
        loadArguments(getArguments());
        if (context == null || this.mCurrentDate < 0) {
            return super.createDialog(bundle);
        }
        View inflate = LayoutInflater.from(context).inflate(getLayoutId(), (ViewGroup) null, false);
        bind(context, inflate);
        initialize();
        return new AlertDialog.Builder(context).setView(inflate).setPositiveButton((int) R.string.done, (DialogInterface.OnClickListener) new n(this, 0)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new n(this, 1)).create();
    }

    public void onDetach() {
        super.onDetach();
        Optional.ofNullable(getBlackboard()).ifPresent(new B(18));
    }

    public void onStart() {
        super.onStart();
        setEnabledDone(false);
    }
}
