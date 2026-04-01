package com.samsung.android.gallery.widget.awesome;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$style;
import com.samsung.android.gallery.widget.effects.LightingEffectView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import g6.g;
import java.util.ArrayList;
import java.util.Objects;
import k6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AwesomeIntelligenceDialog {
    private final Context mContext;
    private final AlertDialog mDialog;
    private final RecyclerView mListView;
    private final LightingEffectView mRootView;

    public AwesomeIntelligenceDialog(Context context, ArrayList<IAwesomeItem> arrayList) {
        this.mContext = context;
        LightingEffectView lightingEffectView = (LightingEffectView) LayoutInflater.from(context).inflate(R$layout.awesome_intelligence_popup_dialog, (ViewGroup) null);
        this.mRootView = lightingEffectView;
        this.mListView = (RecyclerView) lightingEffectView.findViewById(R$id.list_view);
        this.mDialog = createDialog(context, lightingEffectView);
        init(arrayList);
    }

    private AlertDialog createDialog(Context context, View view) {
        int i2;
        if (PreferenceFeatures.OneUi7x.SUPPORT_LIGHTING_EFFECT) {
            i2 = R$style.AwesomeDialogLightingStyle;
        } else {
            i2 = R$style.AwesomeDialogStyle;
        }
        AlertDialog create = new AlertDialog.Builder(context, i2).setView(view).create();
        Window window = create.getWindow();
        if (window != null) {
            window.clearFlags(2);
        }
        return create;
    }

    private void init(ArrayList<IAwesomeItem> arrayList) {
        AwesomeIntelligenceAdapter awesomeIntelligenceAdapter = new AwesomeIntelligenceAdapter(arrayList);
        awesomeIntelligenceAdapter.setOnItemClickListener(new g(22, this));
        this.mListView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mListView.setAdapter(awesomeIntelligenceAdapter);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(Void voidR) {
        dismiss();
    }

    public void dismiss() {
        this.mRootView.releaseEffect();
        this.mDialog.dismiss();
    }

    public boolean isShowing() {
        return this.mDialog.isShowing();
    }

    public void setAttributes(boolean z, int i2) {
        int i7;
        int i8;
        int i10;
        Window window = this.mDialog.getWindow();
        if (window != null) {
            if (z) {
                i7 = 48;
            } else {
                i7 = 80;
            }
            if (i2 > 0) {
                i8 = 3;
            } else {
                i8 = 5;
            }
            window.setGravity(i7 | i8);
            WindowManager.LayoutParams attributes = window.getAttributes();
            if (i2 > 0) {
                attributes.x = i2;
            }
            if (z) {
                i10 = R$style.AwesomeDialogLandscapeAnimation;
            } else {
                i10 = R$style.AwesomeDialogPortraitAnimation;
            }
            attributes.windowAnimations = i10;
            window.setAttributes(attributes);
        }
    }

    public void show(boolean z, int i2) {
        setAttributes(z, i2);
        this.mDialog.show();
        LightingEffectView lightingEffectView = this.mRootView;
        Objects.requireNonNull(lightingEffectView);
        ViewUtils.post(lightingEffectView, new b(7, lightingEffectView));
    }
}
