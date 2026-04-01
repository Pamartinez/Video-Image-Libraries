package com.samsung.android.gallery.app.controller.externals;

import N2.j;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.search.languagepack.NeuralTranslator;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LanguagePackDownloadCmd extends BaseCommand {
    public void onExecute(EventContext eventContext, Object... objArr) {
        String languagePackCode = NeuralTranslator.getInstance().getLanguagePackCode();
        if (!TextUtils.isEmpty(languagePackCode)) {
            Intent intent = new Intent();
            intent.setAction("com.samsung.android.settings.action.REQUEST_LANGUAGE_PACK_DOWNLOAD");
            intent.putExtra("package", "com.sec.android.gallery3d");
            intent.putExtra("locale", languagePackCode);
            intent.putExtra("function", getContext().getResources().getString(R.string.app_name));
            if (getActivity() != null) {
                try {
                    getActivity().startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    j.p(e, new StringBuilder("Unable to start activity e="), this.TAG);
                }
            }
        }
    }
}
