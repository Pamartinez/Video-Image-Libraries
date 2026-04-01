package com.samsung.android.gallery.settings.activity;

import N2.j;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$dimen;
import com.samsung.android.gallery.settings.R$drawable;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$style;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.UrlLookup;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThirdPartyAccessActivity extends BaseActivity {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SubCategory {
        SERVICE_PROVIDER(R$id.service_provider, R$string.third_party_access_service_provider, -1),
        DATA_REQUIRED_PERMISSION(R$id.data_required_permission, R$string.third_party_access_data_required_permission, R$string.third_party_access_data_required_permission_description),
        PURPOSE(R$id.purpose, R$string.third_party_access_purpose, R$string.third_party_access_purpose_description),
        TERMS_AND_CONDITIONS(R$id.terms_and_conditions, R$string.third_party_access_terms_and_conditions, -1);
        
        final int descriptionRes;
        final int titleRes;
        final int viewRes;

        private SubCategory(int i2, int i7, int i8) {
            this.viewRes = i2;
            this.titleRes = i7;
            this.descriptionRes = i8;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TermsAndConditionHolder {
        static ArrayList<String> urls = new ArrayList<String>() {
            {
                if (SdkConfig.atLeast(SdkConfig.SEM.B)) {
                    add(UrlLookup.getUrl("baidu.v2") + "/index.php?title=open/law");
                    add(UrlLookup.getUrl("baidu.v2") + "/index.php?title=openprivacy");
                    add(UrlLookup.getUrl("autonavi.tc") + "/legal-agreement/terms/c_platform_service_agreement/20250314165704167/20250314165704167.html");
                    add(UrlLookup.getUrl("autonavi.pp") + "/pages/privacy");
                    return;
                }
                add(UrlLookup.getUrl("amap.tnc"));
            }
        };
    }

    /* access modifiers changed from: private */
    public void bindCategoryView(SubCategory subCategory) {
        String str;
        ViewGroup viewGroup = (ViewGroup) findViewById(subCategory.viewRes);
        ViewUtils.setText((TextView) viewGroup.findViewById(R$id.category_title), subCategory.titleRes);
        TextView textView = getTextView(viewGroup);
        if (SubCategory.SERVICE_PROVIDER.equals(subCategory)) {
            if (SdkConfig.atLeast(SdkConfig.SEM.B)) {
                str = viewGroup.getContext().getString(R$string.poi_provider_tnc_baidu) + "\n";
            } else {
                str = "";
            }
            StringBuilder s = C0212a.s(str);
            s.append(viewGroup.getContext().getString(R$string.poi_provider_tnc_autonavi));
            ViewUtils.setText(textView, s.toString());
        } else if (SubCategory.TERMS_AND_CONDITIONS.equals(subCategory)) {
            bindLinkText(viewGroup);
            return;
        } else {
            ViewUtils.setText(textView, subCategory.descriptionRes);
        }
        viewGroup.addView(textView);
    }

    private void bindLinkText(ViewGroup viewGroup) {
        Iterator<String> it = TermsAndConditionHolder.urls.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!TextUtils.isEmpty(next)) {
                TextView textView = getTextView(viewGroup);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(next);
                spannableStringBuilder.setSpan(new UnderlineSpan(), 0, next.length(), 18);
                textView.setText(spannableStringBuilder);
                textView.setTextColor(getColor(R$color.third_party_access_layout_link_text_color));
                textView.setBackground(getDrawable(R$drawable.gdpr_ripple_background));
                textView.setTextAppearance(R$style.OneUiRobotoSemiBold);
                textView.setOnClickListener(new h(this));
                viewGroup.addView(textView);
            }
        }
    }

    private String getDescription() {
        return getString(R$string.third_party_access_description_1) + " " + getString(R$string.third_party_access_description_2) + " " + getString(R$string.third_party_access_description_3);
    }

    private TextView getTextView(ViewGroup viewGroup) {
        TextView textView = new TextView(viewGroup.getContext());
        textView.setTextColor(viewGroup.getContext().getColor(R$color.third_party_access_layout_category_description_text_color));
        textView.setTextDirection(5);
        textView.setTextSize(0, (float) getResources().getDimensionPixelSize(R$dimen.third_party_access_layout_category_description_text_size));
        return textView;
    }

    /* access modifiers changed from: private */
    public void onLinkClicked(View view) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((TextView) view).getText().toString())));
        } catch (ActivityNotFoundException e) {
            j.p(e, new StringBuilder("start terms and conditions failed e="), this.TAG);
        }
    }

    public void bindView() {
        ViewUtils.setText((TextView) findViewById(R$id.header), R$string.third_party_access_header);
        ViewUtils.setText((TextView) findViewById(R$id.title), R$string.third_party_access_description_title);
        ViewUtils.setText((TextView) findViewById(R$id.description), getDescription());
        Arrays.stream(SubCategory.values()).forEach(new a(this, 1));
    }

    public String getActivityTitle() {
        return getString(R$string.third_party_access_notice_title);
    }

    public ViewGroup getContentFrame() {
        return (ViewGroup) findViewById(R$id.content);
    }

    public int getLayoutId() {
        return R$layout.activity_third_party_access_layout;
    }

    public /* bridge */ /* synthetic */ void initActionBar() {
        super.initActionBar();
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public /* bridge */ /* synthetic */ void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
    }

    public /* bridge */ /* synthetic */ boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    public /* bridge */ /* synthetic */ void setBackPressedCallbackEnabled(boolean z) {
        super.setBackPressedCallbackEnabled(z);
    }

    public /* bridge */ /* synthetic */ void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }
}
