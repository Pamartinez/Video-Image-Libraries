package com.samsung.android.gallery.settings.widget;

import E9.a;
import N2.j;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HelpPreference extends BasePreference {
    protected String mHelpDescription;
    protected Drawable mHelpImage;
    /* access modifiers changed from: private */
    public Runnable mLinkClickListener;
    private String mLinkDescriptions;

    public HelpPreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLinkDescription$0(TextView textView) {
        if (TextUtils.isEmpty(this.mLinkDescriptions) || !this.mLinkDescriptions.contains("%1$s") || !this.mLinkDescriptions.contains("%2$s")) {
            textView.setText(this.mLinkDescriptions);
            return;
        }
        try {
            int indexOf = this.mLinkDescriptions.indexOf("%1$s");
            int indexOf2 = this.mLinkDescriptions.indexOf("%2$s") - 4;
            Spannable spannable = (Spannable) Html.fromHtml(String.format(this.mLinkDescriptions, new Object[]{"<u><b>", "</b></u>", "<u><b>", "</b></u>"}), 0);
            spannable.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    if (HelpPreference.this.mLinkClickListener != null) {
                        HelpPreference.this.mLinkClickListener.run();
                    }
                }
            }, indexOf, indexOf2, 33);
            spannable.setSpan(new ForegroundColorSpan(getContext().getColor(R$color.settings_description_text_color)), indexOf, indexOf2, 33);
            textView.setText(spannable);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (RuntimeException e) {
            j.u(e, new StringBuilder("updateLinkDescription failed. e="), "HelpPreference");
        }
    }

    private void setRoundedCorner(View view) {
        if (view != null) {
            SeApiCompat.setViewRoundedCorner(view, 15);
            SeApiCompat.setViewRoundedCornerColor(view, 15, view.getResources().getColor(R$color.gallery_status_bar_fw_background_color, (Resources.Theme) null));
        }
    }

    private void updateHelpDescription(PreferenceViewHolder preferenceViewHolder) {
        ViewUtils.setText((TextView) preferenceViewHolder.itemView.findViewById(R$id.help_description), this.mHelpDescription);
    }

    private void updateHelpImage(PreferenceViewHolder preferenceViewHolder) {
        setRoundedCorner(preferenceViewHolder.itemView.findViewById(R$id.help_image_container));
        ViewUtils.setImageDrawable((ImageView) preferenceViewHolder.itemView.findViewById(R$id.help_image), this.mHelpImage);
    }

    private void updateLinkDescription(PreferenceViewHolder preferenceViewHolder) {
        Optional.ofNullable((TextView) preferenceViewHolder.itemView.findViewById(R$id.link_description)).ifPresent(new a(24, this));
    }

    public int getLayoutId() {
        return R$layout.help_preference_layout;
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.itemView.setClickable(false);
        updateHelpImage(preferenceViewHolder);
        updateHelpDescription(preferenceViewHolder);
        updateLinkDescription(preferenceViewHolder);
    }

    public void setHelpDescription(String str) {
        this.mHelpDescription = str;
        notifyChanged();
    }

    public void setHelpImage(Drawable drawable) {
        this.mHelpImage = drawable;
        notifyChanged();
    }

    public void setLinkClickListener(Runnable runnable) {
        this.mLinkClickListener = runnable;
    }

    public void setLinkDescription(String str) {
        this.mLinkDescriptions = str;
        notifyChanged();
    }

    public boolean supportCustomLayout() {
        return true;
    }

    public HelpPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
