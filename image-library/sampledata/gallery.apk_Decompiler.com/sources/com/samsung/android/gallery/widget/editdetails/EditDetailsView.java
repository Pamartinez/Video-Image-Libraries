package com.samsung.android.gallery.widget.editdetails;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.material.oneui.dividerbuttonlayout.DividerButtonLayout;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$menu;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import i.C0212a;
import java.util.Locale;
import java.util.Objects;
import k2.s;
import xb.C0716a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditDetailsView extends RelativeLayout {
    private final String TAG = getClass().getSimpleName();
    private View mBottomMenuView;
    private View mCapturedFromView;
    private TextView mEditDateView;
    private View mItemsContainer;
    private EditDetailsEditText mTitleEditTextView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface onMenuItemClickListener {
    }

    public EditDetailsView(Context context) {
        super(context);
        bind();
    }

    private void bind() {
        Trace.beginSection(this.TAG + " bind");
        LayoutInflater.from(getContext()).inflate(R$layout.edit_details_layout, this);
        this.mCapturedFromView = findViewById(R$id.edit_details_captured_from);
        this.mEditDateView = (TextView) findViewById(R$id.edit_details_date_text);
        this.mTitleEditTextView = (EditDetailsEditText) findViewById(R$id.edit_details_title_edit_text);
        this.mItemsContainer = findViewById(R$id.edit_details_container);
        bindBottomMenuView();
        setContextClickable(true);
        setZ(0.0f);
        Trace.endSection();
    }

    private void bindBottomMenuView() {
        View findViewById = findViewById(R$id.edit_details_bottom_floating);
        this.mBottomMenuView = findViewById;
        ((DividerButtonLayout) findViewById).c(R$menu.menu_bottom_appbar_edit);
        ViewUtils.setVisibility(this.mBottomMenuView, 0);
    }

    public String getEditedTitle() {
        return this.mTitleEditTextView.getText().toString();
    }

    public void hideLocationContainer() {
        ViewUtils.setVisibility(findViewById(R$id.location_container), 8);
    }

    public void onViewPause() {
        EditDetailsEditText editDetailsEditText = this.mTitleEditTextView;
        if (editDetailsEditText != null) {
            editDetailsEditText.onPause();
        }
    }

    public void onViewResume() {
        EditDetailsEditText editDetailsEditText = this.mTitleEditTextView;
        if (editDetailsEditText != null) {
            editDetailsEditText.onResume();
        }
    }

    public void setLocationButtonListener(View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        ViewUtils.setOnClickListener(findViewById(R$id.edit_details_location_add_button), onClickListener);
        ViewUtils.setOnClickListener(findViewById(R$id.edit_details_location_delete_button), onClickListener2);
    }

    public void setOnMenuClickListener(onMenuItemClickListener onmenuitemclicklistener) {
        View view = this.mBottomMenuView;
        if (view instanceof DividerButtonLayout) {
            Objects.requireNonNull(onmenuitemclicklistener);
            ((DividerButtonLayout) view).setOnMenuItemClickListener(new C0716a(onmenuitemclicklistener));
        } else if (view instanceof s) {
            Objects.requireNonNull(onmenuitemclicklistener);
            ((s) view).setOnItemSelectedListener(new C0716a(onmenuitemclicklistener));
        } else {
            Log.e(this.TAG, "setListener failed");
        }
    }

    public void setTitleFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.mTitleEditTextView.setOnFocusChangeListener(onFocusChangeListener);
    }

    public void updateBottomMenuBottomMargin(int i2) {
        ViewMarginUtils.setBottomMargin(this.mBottomMenuView, i2);
    }

    public void updateBottomViewVisibility(boolean z) {
        int i2;
        View view = this.mBottomMenuView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        view.setVisibility(i2);
    }

    public void updateCapturedUrlView(String str, View.OnClickListener onClickListener) {
        if (!TextUtils.isEmpty(str)) {
            View view = this.mCapturedFromView;
            if (view instanceof ViewStub) {
                this.mCapturedFromView = ((ViewStub) view).inflate();
            }
            ViewUtils.setText((TextView) this.mCapturedFromView.findViewById(R$id.edit_details_captured_url_text), str);
            ViewUtils.setOnClickListener(this.mCapturedFromView.findViewById(R$id.edit_details_captured_url_delete_button), onClickListener);
            ViewUtils.setVisibility(this.mCapturedFromView, 0);
            return;
        }
        ViewUtils.setVisibility(this.mCapturedFromView, 8);
    }

    public void updateDateText(String str) {
        this.mEditDateView.setText(str);
    }

    public void updateDateView(String str, View.OnClickListener onClickListener) {
        boolean z;
        updateDateText(str);
        this.mEditDateView.setOnClickListener(onClickListener);
        View findViewById = findViewById(R$id.edit_details_date_underline);
        if (onClickListener != null) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleOrGone(findViewById, z);
    }

    public void updateLayout() {
        View view = this.mItemsContainer;
        Context context = getContext();
        int i2 = R$dimen.details_list_horizontal_margin;
        ViewMarginUtils.setStartPadding(view, ResourceCompat.getDimensionPixelOffset(context, i2, 0));
        ViewMarginUtils.setEndPadding(this.mItemsContainer, ResourceCompat.getDimensionPixelOffset(getContext(), i2, 0));
    }

    public void updateLocationView(String str, String str2, boolean z) {
        boolean z3;
        boolean z7;
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean z9 = true;
        if (isEmpty || TextUtils.isEmpty(str2)) {
            z3 = false;
        } else {
            z3 = true;
        }
        TextView textView = (TextView) findViewById(R$id.edit_details_address_text);
        TextView textView2 = (TextView) findViewById(R$id.edit_details_poi_text);
        ImageView imageView = (ImageView) findViewById(R$id.edit_details_poi_icon);
        ViewUtils.setVisibility(findViewById(R$id.location_container), 0);
        if (isEmpty) {
            str = getContext().getString(R$string.no_location);
        }
        ViewUtils.setText(textView, str);
        ViewUtils.setText(textView2, str2);
        ViewUtils.setVisibleOrGone(textView2, z3);
        ViewUtils.setVisibleOrGone(imageView, z3);
        View findViewById = findViewById(R$id.edit_details_location_add_button);
        if (!isEmpty || !z) {
            z7 = false;
        } else {
            z7 = true;
        }
        ViewUtils.setVisibleOrGone(findViewById, z7);
        View findViewById2 = findViewById(R$id.edit_details_location_delete_button);
        if (isEmpty || !z) {
            z9 = false;
        }
        ViewUtils.setVisibleOrGone(findViewById2, z9);
    }

    public void updateTitleView(String str, String str2, boolean z, boolean z3) {
        int i2;
        ImageView imageView = (ImageView) findViewById(R$id.edit_details_mime_type_icon);
        Resources resources = getResources();
        if (z) {
            i2 = R$drawable.gallery_ic_details_video;
        } else {
            i2 = R$drawable.gallery_ic_details_img;
        }
        String str3 = null;
        imageView.setImageDrawable(resources.getDrawable(i2, (Resources.Theme) null));
        this.mTitleEditTextView.setText(str);
        this.mTitleEditTextView.setEnabled(z3);
        ViewUtils.setVisibleOrGone(findViewById(R$id.edit_details_title_underline), z3);
        TextView textView = (TextView) findViewById(R$id.edit_details_title_edit_type_text);
        if (!TextUtils.isEmpty(str2)) {
            Locale locale = Locale.US;
            str3 = C0212a.l(".", str2);
        }
        ViewUtils.setText(textView, str3);
    }
}
