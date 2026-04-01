package com.samsung.android.gallery.app.ui.dialog.creature.relationship;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelationshipChoiceViewHolder extends RecyclerView.ViewHolder {
    TextView mAddManualTextView;
    LinearLayout mCustomButtonContainer;
    ImageButton mDeleteNameButton;
    ImageButton mEditNameButton;
    AppCompatRadioButton mRadioButton;
    final int mViewType;

    public RelationshipChoiceViewHolder(View view, int i2) {
        super(view);
        this.mViewType = i2;
        bindView(view);
    }

    public void bind(String str) {
        if (this.mViewType == 0) {
            this.mAddManualTextView.setVisibility(0);
            this.mAddManualTextView.setText(str);
            return;
        }
        this.mRadioButton.setVisibility(0);
        this.mRadioButton.setText(str);
        if (PreferenceFeatures.OneUi6x.SUPPORT_CUSTOM_RELATIONSHIP && PreferenceFeatures.isEnabled(PreferenceFeatures.CustomPeopleRelationshipEditAndRemove) && this.mViewType == 1) {
            this.mCustomButtonContainer.setVisibility(0);
        }
    }

    public final void bindView(View view) {
        if (this.mViewType == 0) {
            this.mAddManualTextView = (TextView) view.findViewById(R.id.add_manual_relation);
            return;
        }
        this.mRadioButton = (AppCompatRadioButton) view.findViewById(R.id.radio_btn);
        if (this.mViewType == 1) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.custom_relation_button_container);
            this.mCustomButtonContainer = linearLayout;
            this.mEditNameButton = (ImageButton) linearLayout.findViewById(R.id.edit_relation_name);
            this.mDeleteNameButton = (ImageButton) this.mCustomButtonContainer.findViewById(R.id.delete_relation_name);
        }
    }

    public String getName() {
        if (this.mViewType == 0) {
            return this.mAddManualTextView.getText().toString();
        }
        return this.mRadioButton.getText().toString();
    }

    public void setChecked(boolean z) {
        if (this.mViewType != 0) {
            this.mRadioButton.setChecked(z);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        if (this.mViewType == 0) {
            this.mAddManualTextView.setOnClickListener(onClickListener);
        } else {
            this.mRadioButton.setOnClickListener(onClickListener);
        }
    }

    public void setOnDeleteClickListener(View.OnClickListener onClickListener) {
        this.mDeleteNameButton.setOnClickListener(onClickListener);
    }

    public void setOnEditClickListener(View.OnClickListener onClickListener) {
        this.mEditNameButton.setOnClickListener(onClickListener);
    }
}
