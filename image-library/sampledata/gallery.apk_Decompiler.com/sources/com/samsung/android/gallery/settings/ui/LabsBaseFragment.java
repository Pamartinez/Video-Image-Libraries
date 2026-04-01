package com.samsung.android.gallery.settings.ui;

import A.a;
import O3.l;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.settings.widget.SearchPreference;
import com.samsung.android.gallery.settings.widget.SummaryPreference;
import com.samsung.android.gallery.support.utils.BooleanFeatures;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadBuilder;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dialog.ProgressDialogCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class LabsBaseFragment extends BasePreferenceFragment<IBasePreferenceView> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BooleanChoiceDialogBuilder extends ChoiceDialogBuilder {
        final BooleanFeatures feature;

        public BooleanChoiceDialogBuilder(BooleanFeatures booleanFeatures) {
            this.feature = booleanFeatures;
            setCallback(new r(0, booleanFeatures));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$new$0(BooleanFeatures booleanFeatures, Integer num) {
            boolean z = true;
            if (num.intValue() != 1) {
                z = false;
            }
            booleanFeatures.setEnabled(z);
        }

        public void attach(Preference preference, String[] strArr) {
            super.attach(preference, strArr, this.feature.isEnabled() ? 1 : 0);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ChoiceDialogBuilder {
        Consumer<Integer> callback;

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$attach$1(AtomicInteger atomicInteger, Preference preference, String[] strArr, DialogInterface dialogInterface, int i2) {
            Consumer<Integer> consumer = this.callback;
            if (consumer != null) {
                consumer.accept(Integer.valueOf(atomicInteger.get()));
            }
            preference.setSummary((CharSequence) "Option: " + strArr[atomicInteger.get()]);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$attach$2(Preference preference, String[] strArr, AtomicInteger atomicInteger, Preference preference2) {
            new AlertDialog.Builder(preference.getContext()).setTitle(preference.getTitle()).setSingleChoiceItems((CharSequence[]) strArr, atomicInteger.get(), (DialogInterface.OnClickListener) new C0650e(atomicInteger, 1)).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new C0651f(this, atomicInteger, preference, strArr)).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).show();
            return true;
        }

        public void attach(Preference preference, String[] strArr, int i2) {
            AtomicInteger atomicInteger = new AtomicInteger(i2);
            preference.setSummary((CharSequence) "Option: " + strArr[atomicInteger.get()]);
            Preference preference2 = preference;
            preference2.setOnPreferenceClickListener(new s(this, preference2, strArr, atomicInteger, 0));
        }

        public ChoiceDialogBuilder setCallback(Consumer<Integer> consumer) {
            this.callback = consumer;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MultiChoiceDialogBuilder extends ChoiceDialogBuilder {
        Consumer<String> callback;
        String value;

        public MultiChoiceDialogBuilder(GalleryPreference galleryPreference, String str) {
            this.value = galleryPreference.loadString(str, "");
            this.callback = new C0656k(this, galleryPreference, str);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$attach$1(boolean[] zArr, Set set, String[] strArr, int i2) {
            zArr[i2] = set.contains(strArr[i2]);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$attach$2(boolean[] zArr, DialogInterface dialogInterface, int i2, boolean z) {
            zArr[i2] = z;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$attach$3(boolean[] zArr, int i2) {
            return zArr[i2];
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ String lambda$attach$4(String[] strArr, int i2) {
            return strArr[i2];
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$attach$5(String[] strArr, boolean[] zArr, Preference preference, DialogInterface dialogInterface, int i2) {
            String str = (String) IntStream.range(0, strArr.length).filter(new v(zArr)).mapToObj(new w(strArr)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
            this.callback.accept(str);
            preference.setSummary((CharSequence) "Option: " + str);
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [java.io.Serializable] */
        /* access modifiers changed from: private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ boolean lambda$attach$6(androidx.preference.Preference r8, java.lang.String[] r9, boolean[] r10, androidx.preference.Preference r11) {
            /*
                r7 = this;
                androidx.appcompat.app.AlertDialog$Builder r11 = new androidx.appcompat.app.AlertDialog$Builder
                android.content.Context r0 = r8.getContext()
                r11.<init>(r0)
                java.lang.CharSequence r0 = r8.getTitle()
                androidx.appcompat.app.AlertDialog$Builder r11 = r11.setTitle((java.lang.CharSequence) r0)
                com.samsung.android.gallery.settings.ui.u r0 = new com.samsung.android.gallery.settings.ui.u
                r0.<init>(r10)
                androidx.appcompat.app.AlertDialog$Builder r11 = r11.setMultiChoiceItems(r9, r10, r0)
                int r0 = com.samsung.android.gallery.settings.R$string.ok
                com.samsung.android.gallery.settings.ui.f r1 = new com.samsung.android.gallery.settings.ui.f
                r2 = 2
                r4 = r7
                r6 = r8
                r5 = r9
                r3 = r10
                r1.<init>(r2, r3, r4, r5, r6)
                androidx.appcompat.app.AlertDialog$Builder r7 = r11.setPositiveButton((int) r0, (android.content.DialogInterface.OnClickListener) r1)
                int r8 = com.samsung.android.gallery.settings.R$string.cancel
                r9 = 0
                androidx.appcompat.app.AlertDialog$Builder r7 = r7.setNegativeButton((int) r8, (android.content.DialogInterface.OnClickListener) r9)
                r7.show()
                r7 = 1
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.settings.ui.LabsBaseFragment.MultiChoiceDialogBuilder.lambda$attach$6(androidx.preference.Preference, java.lang.String[], boolean[], androidx.preference.Preference):boolean");
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(GalleryPreference galleryPreference, String str, String str2) {
            this.value = str2;
            if (TextUtils.isEmpty(str2)) {
                galleryPreference.removeState(str);
            } else {
                galleryPreference.saveState(str, str2);
            }
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [boolean[], java.io.Serializable] */
        public void attach(Preference preference, String[] strArr) {
            String str;
            ? r5 = new boolean[strArr.length];
            IntStream.range(0, strArr.length).forEach(new t(r5, (Set) Stream.of(this.value.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).collect(Collectors.toSet()), strArr));
            StringBuilder sb2 = new StringBuilder("Option: ");
            if (TextUtils.isEmpty(this.value)) {
                str = "n/a";
            } else {
                str = this.value;
            }
            sb2.append(str);
            preference.setSummary((CharSequence) sb2.toString());
            Preference preference2 = preference;
            preference2.setOnPreferenceClickListener(new s(this, preference2, strArr, r5, 1));
        }
    }

    /* access modifiers changed from: private */
    public void addPreferenceBottomPadding(PreferenceScreen preferenceScreen) {
        PreferenceCategory preferenceCategory = new PreferenceCategory(preferenceScreen.getContext());
        preferenceCategory.setLayoutResource(R$layout.preference_category_footer_layout);
        preferenceCategory.setKey("edge_to_edge_bottom_padding");
        preferenceCategory.setOrder(Integer.MAX_VALUE);
        preferenceScreen.addPreference(preferenceCategory);
        preferenceCategory.addPreference(new PreferenceCategory(preferenceScreen.getContext()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$addSwitchPreference$8(BooleanFeatures booleanFeatures, Consumer consumer, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        boolean booleanValue = bool.booleanValue();
        booleanFeatures.setEnabled(booleanValue);
        if (consumer != null) {
            consumer.accept(bool);
        }
        if (!(booleanFeatures instanceof PocFeatures) || booleanFeatures.getDefaultValue() != booleanValue) {
            return true;
        }
        ThreadUtil.postOnUiThread(new C0662q(booleanFeatures, 0));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreferences$1(SearchPreference searchPreference) {
        searchPreference.setPreferenceScreen(getPreferenceScreen());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$initSwitchPreference$12(BooleanFeatures booleanFeatures, Consumer consumer, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        boolean booleanValue = bool.booleanValue();
        booleanFeatures.setEnabled(booleanValue);
        if (consumer != null) {
            consumer.accept(bool);
        }
        if (!(booleanFeatures instanceof PocFeatures) || booleanFeatures.getDefaultValue() != booleanValue) {
            return true;
        }
        ThreadUtil.postOnUiThread(new C0662q(booleanFeatures, 1));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showConfirmDialog$6(String str, String str2, Runnable runnable) {
        Optional.ofNullable(getContext()).ifPresent(new C0656k(str, str2, runnable));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$showSwitchConfirmDialog$2(Preference preference, BooleanFeatures booleanFeatures, DialogInterface dialogInterface, int i2) {
        ((SwitchPreferenceCompat) preference).setChecked(true);
        booleanFeatures.setEnabled(true);
    }

    public Preference addSummaryPreference(PreferenceGroup preferenceGroup, String str, String str2, String str3, Preference.OnPreferenceClickListener onPreferenceClickListener) {
        if (preferenceGroup == null) {
            return null;
        }
        SummaryPreference summaryPreference = new SummaryPreference(preferenceGroup.getContext());
        summaryPreference.setKey(str);
        summaryPreference.setTitle((CharSequence) str2);
        summaryPreference.setSummary((CharSequence) str3);
        summaryPreference.setOnPreferenceClickListener(onPreferenceClickListener);
        preferenceGroup.addPreference(summaryPreference);
        return summaryPreference;
    }

    public Preference addSwitchPreference(PreferenceGroup preferenceGroup, BooleanFeatures booleanFeatures, String str, String str2) {
        return addSwitchPreference(preferenceGroup, booleanFeatures, str, str2, (Consumer<Boolean>) null);
    }

    public PreferenceGroup computePreferenceCategory(String str, String str2) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference(str);
        if (preferenceGroup == null) {
            preferenceGroup = new PreferenceCategory(getContext());
            preferenceGroup.setKey(str);
            preferenceGroup.setTitle((CharSequence) str2);
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            if (preferenceScreen != null) {
                preferenceScreen.addPreference(preferenceGroup);
            }
        }
        return preferenceGroup;
    }

    public int getPreferenceResourceId() {
        return R$xml.setting_preference_labs_common;
    }

    public void initPreferences() {
        Optional.ofNullable((SearchPreference) findPreference("labs_search_bar")).ifPresent(new C0658m(this, 1));
    }

    public SwitchPreferenceCompat initSwitchPreference(String str, BooleanFeatures booleanFeatures) {
        return initSwitchPreference(str, booleanFeatures, (Consumer<Boolean>) null);
    }

    public final void loadPreferences() {
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new l(7));
        try {
            addPreferencesFromResource(getPreferenceResourceId());
            initPreferences();
            Optional.ofNullable(getPreferenceScreen()).ifPresent(new C0658m(this, 0));
        } catch (Exception e) {
            a.s(e, new StringBuilder("loadPreferences failed. e="), this.TAG);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Optional.ofNullable(getAppbarLayout()).ifPresent(new C0657l(0));
        getPreferenceManager().setSharedPreferencesName("gallery_pref");
        loadPreferences();
    }

    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setListContainerHeightFromActivity(viewGroup);
        return super.onCreateRecyclerView(layoutInflater, viewGroup, bundle);
    }

    public void setListContainerHeightFromActivity(ViewGroup viewGroup) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            layoutParams.height = activity.findViewById(R$id.content).getHeight();
            viewGroup.setLayoutParams(layoutParams);
        }
    }

    public void showConfirmDialog(String str, Runnable runnable) {
        showConfirmDialog(str, (String) null, runnable);
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [android.content.DialogInterface$OnClickListener, java.lang.Object] */
    public void showSwitchConfirmDialog(Preference preference, BooleanFeatures booleanFeatures, String str, String str2) {
        new AlertDialog.Builder(preference.getContext()).setTitle((CharSequence) str).setMessage((CharSequence) str2).setCancelable(true).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new C0660o(0, preference, booleanFeatures)).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) new Object()).create().show();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class WorkerTask {
        protected final String TAG = getClass().getSimpleName();
        protected ProgressDialogCompat mProgressDialog;
        Consumer<Object[]> mRunnable;

        public WorkerTask() {
        }

        public void execute(Object... objArr) {
            new ThreadBuilder().setPreExecutor(new x(this, objArr, 0)).setPostExecutor(new x(this, objArr, 1)).execute(new x(this, objArr, 2));
        }

        public void hideProgressBar() {
            ProgressDialogCompat progressDialogCompat = this.mProgressDialog;
            if (progressDialogCompat != null) {
                progressDialogCompat.dismiss();
                this.mProgressDialog = null;
            }
        }

        /* renamed from: onExecute */
        public void lambda$execute$2(Object... objArr) {
            Consumer<Object[]> consumer = this.mRunnable;
            if (consumer != null) {
                consumer.accept(objArr);
            }
        }

        /* renamed from: onPostExecute */
        public void lambda$execute$1(Object... objArr) {
            hideProgressBar();
        }

        /* renamed from: onPreExecute */
        public void lambda$execute$0(Object... objArr) {
            showProgressBar(objArr[0]);
        }

        public void showProgressBar(Context context) {
            if (this.mProgressDialog == null) {
                this.mProgressDialog = new ProgressDialogCompat(context).setProgressMessage(R$string.processing).setCancelable(false).setCanceledOnTouchOutside(false).build().show();
            }
        }

        public void updateProgress(String str) {
            ProgressDialogCompat progressDialogCompat = this.mProgressDialog;
            if (progressDialogCompat != null && progressDialogCompat.isShowing()) {
                this.mProgressDialog.updateMessage(str);
            }
        }

        public WorkerTask(Consumer<Object[]> consumer) {
            this.mRunnable = consumer;
        }
    }

    public Preference addSwitchPreference(PreferenceGroup preferenceGroup, BooleanFeatures booleanFeatures, String str, String str2, Consumer<Boolean> consumer) {
        boolean isEnabled = booleanFeatures.isEnabled();
        SwitchPreferenceCompat switchPreferenceCompat = new SwitchPreferenceCompat(getContext());
        switchPreferenceCompat.setTitle((CharSequence) str);
        switchPreferenceCompat.setKey(booleanFeatures.getKey());
        switchPreferenceCompat.setDefaultValue(Boolean.valueOf(isEnabled));
        switchPreferenceCompat.setSummary((CharSequence) str2);
        switchPreferenceCompat.setOnPreferenceChangeListener(new C0659n(0, booleanFeatures, consumer));
        switchPreferenceCompat.setChecked(isEnabled);
        if (preferenceGroup != null) {
            preferenceGroup.addPreference(switchPreferenceCompat);
        }
        return switchPreferenceCompat;
    }

    public SwitchPreferenceCompat initSwitchPreference(String str, BooleanFeatures booleanFeatures, Consumer<Boolean> consumer) {
        SwitchPreferenceCompat switchPreferenceCompat = (SwitchPreferenceCompat) findPreference(str);
        if (switchPreferenceCompat != null) {
            boolean isEnabled = booleanFeatures.isEnabled();
            switchPreferenceCompat.setOnPreferenceChangeListener(new C0659n(1, booleanFeatures, consumer));
            switchPreferenceCompat.setChecked(isEnabled);
        }
        return switchPreferenceCompat;
    }

    public void showConfirmDialog(String str, String str2, Runnable runnable) {
        ThreadUtil.runOnUiThread(new C0655j(this, str, str2, runnable, 0));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$showSwitchConfirmDialog$3(DialogInterface dialogInterface, int i2) {
    }
}
