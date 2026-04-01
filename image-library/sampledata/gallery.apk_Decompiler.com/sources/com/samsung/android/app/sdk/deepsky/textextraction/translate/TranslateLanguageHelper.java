package com.samsung.android.app.sdk.deepsky.textextraction.translate;

import android.widget.LinearLayout;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H&¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH&¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageHelper;", "", "Landroid/widget/LinearLayout;", "layout", "Lme/x;", "initLayout", "(Landroid/widget/LinearLayout;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/LayoutState;", "state", "invalidateLayout", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/LayoutState;)V", "onConfigurationChanged", "()V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageListener;", "listener", "setListener", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslateLanguageListener;)V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TranslateLanguageHelper {
    void initLayout(LinearLayout linearLayout);

    void invalidateLayout(LayoutState layoutState);

    void onConfigurationChanged();

    void setListener(TranslateLanguageListener translateLanguageListener);
}
