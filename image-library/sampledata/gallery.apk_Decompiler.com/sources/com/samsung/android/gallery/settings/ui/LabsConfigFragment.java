package com.samsung.android.gallery.settings.ui;

import A4.C0375j;
import A4.O;
import A5.a;
import Bb.l;
import D3.i;
import Da.f;
import Fa.C0561o;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.support.utils.BooleanFeatures;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import ld.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LabsConfigFragment extends LabsBaseFragment {
    ArrayList<BooleanFeatures> list;
    String title;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class KnownSet {
        static final HashSet<String> set = ((HashSet) Arrays.stream(PreferenceName.values()).map(new i(23)).collect(Collectors.toCollection(new b(7))));
    }

    /* access modifiers changed from: private */
    public boolean clearPreference(Preference preference) {
        Map<String, ?> all = GalleryPreference.getInstance().getAll();
        List list2 = (List) this.list.stream().filter(new C0561o(0, all)).collect(Collectors.toList());
        if (!list2.isEmpty()) {
            long count = list2.stream().filter(new C0561o(1, all)).count();
            showConfirmDialog("Clear preferences?", "Total=" + list2.size() + "\nChanged=" + count + "\n" + ((String) list2.stream().map(new a(7, all)).collect(Collectors.joining("\n"))), new f(16, this, list2));
            return true;
        }
        Toast.makeText(preference.getContext(), "Nothing changed", 0).show();
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$clearPreference$2(Map map, BooleanFeatures booleanFeatures) {
        Object obj = map.get(booleanFeatures.getKey());
        if (!(obj instanceof Boolean) || booleanFeatures.getDefaultValue() == ((Boolean) obj).booleanValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$clearPreference$3(Map map, BooleanFeatures booleanFeatures) {
        return booleanFeatures.getKey() + "=" + map.get(booleanFeatures.getKey()) + " [" + booleanFeatures.getDefaultValue() + "]";
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$clearPreference$4(List list2) {
        GalleryPreference.getInstance().removeState((Collection<String>) (Collection) list2.stream().map(new i(22)).collect(Collectors.toList()));
        list2.forEach(new l(27));
        loadPreferences();
    }

    public ArrayList<BooleanFeatures> buildPocFeatureList() {
        ArrayList<BooleanFeatures> arrayList = new ArrayList<>(List.of(PocFeatures.values()));
        arrayList.remove(arrayList.size() - 1);
        return arrayList;
    }

    public ArrayList<BooleanFeatures> buildPreferenceFeatureList() {
        ArrayList<BooleanFeatures> arrayList = new ArrayList<>(List.of(PreferenceFeatures.values()));
        arrayList.removeIf(new C0375j(27));
        arrayList.remove(arrayList.size() - 1);
        return arrayList;
    }

    public int getPreferenceResourceId() {
        return R$xml.setting_preference_labs_common_search;
    }

    public int getTitleId() {
        return R$string.gallery_labs_configurations;
    }

    public void initPreferences() {
        String str;
        super.initPreferences();
        if (getContext() != null) {
            PreferenceCategory preferenceCategory = new PreferenceCategory(getContext());
            preferenceCategory.setKey("labs_config_cleanup");
            getPreferenceScreen().addPreference(preferenceCategory);
            addGenericPreference((PreferenceGroup) preferenceCategory, "labs_clear_pref", "Clear all preference", (String) null, (Preference.OnPreferenceClickListener) new O(26, this));
            PreferenceCategory preferenceCategory2 = new PreferenceCategory(getContext());
            preferenceCategory2.setKey("labs_config_features");
            preferenceCategory2.setTitle((CharSequence) this.title);
            getPreferenceScreen().addPreference(preferenceCategory2);
            Iterator<BooleanFeatures> it = this.list.iterator();
            while (it.hasNext()) {
                BooleanFeatures next = it.next();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(next.getKey());
                sb2.append(" [");
                if (next.getDefaultValue()) {
                    str = "T";
                } else {
                    str = "F";
                }
                addSwitchPreference(preferenceCategory2, next, C0212a.p(sb2, str, "]"), (String) null, (Consumer<Boolean>) null);
            }
        }
    }

    public void onAttach(Context context) {
        String str;
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            str = arguments.getString("title", "PocFeatures");
        } else {
            str = "PocFeatures";
        }
        this.title = str;
        if ("PreferenceFeatures".equals(str)) {
            this.list = buildPreferenceFeatureList();
        } else if ("PocFeatures".equals(this.title)) {
            this.list = buildPocFeatureList();
        }
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public /* bridge */ /* synthetic */ RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateRecyclerView(layoutInflater, viewGroup, bundle);
    }
}
