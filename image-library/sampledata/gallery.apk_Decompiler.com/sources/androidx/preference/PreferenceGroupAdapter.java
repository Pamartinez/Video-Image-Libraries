package androidx.preference;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PreferenceGroupAdapter extends RecyclerView.Adapter<PreferenceViewHolder> implements Preference.OnPreferenceChangeInternalListener {
    private List<Integer> mAccessibilityPositionTable;
    private int mCategoryLayoutId = R$layout.sesl_preference_category;
    private final Handler mHandler;
    private boolean mIsCategoryAfter = false;
    private Preference mNextGroupPreference = null;
    private Preference mNextPreference = null;
    private ViewGroup mParent;
    private int mParentWidth = 0;
    private final PreferenceGroup mPreferenceGroup;
    private final List<PreferenceResourceDescriptor> mPreferenceResourceDescriptors;
    private List<Preference> mPreferences;
    private final Runnable mSyncRunnable = new Runnable() {
        public void run() {
            PreferenceGroupAdapter.this.updatePreferences();
        }
    };
    private List<Preference> mVisiblePreferences;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PreferenceResourceDescriptor {
        String mClassName;
        String mDotDescription;
        boolean mIsDotVisibled;
        int mLayoutResId;
        int mWidgetLayoutResId;

        public PreferenceResourceDescriptor(Preference preference) {
            this.mClassName = preference.getClass().getName();
            this.mLayoutResId = preference.getLayoutResource();
            this.mWidgetLayoutResId = preference.getWidgetLayoutResource();
            this.mIsDotVisibled = preference.getDotVisibility();
            this.mDotDescription = preference.getDotContentDescription();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PreferenceResourceDescriptor)) {
                return false;
            }
            PreferenceResourceDescriptor preferenceResourceDescriptor = (PreferenceResourceDescriptor) obj;
            if (this.mLayoutResId == preferenceResourceDescriptor.mLayoutResId && this.mWidgetLayoutResId == preferenceResourceDescriptor.mWidgetLayoutResId && TextUtils.equals(this.mClassName, preferenceResourceDescriptor.mClassName) && this.mIsDotVisibled == preferenceResourceDescriptor.mIsDotVisibled && TextUtils.equals(this.mDotDescription, preferenceResourceDescriptor.mDotDescription)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.mClassName.hashCode() + ((((527 + this.mLayoutResId) * 31) + this.mWidgetLayoutResId) * 31);
        }
    }

    public PreferenceGroupAdapter(PreferenceGroup preferenceGroup) {
        this.mPreferenceGroup = preferenceGroup;
        this.mHandler = new Handler(Looper.getMainLooper());
        preferenceGroup.setOnPreferenceChangeInternalListener(this);
        this.mPreferences = new ArrayList();
        this.mVisiblePreferences = new ArrayList();
        this.mPreferenceResourceDescriptors = new ArrayList();
        this.mAccessibilityPositionTable = new ArrayList();
        if (preferenceGroup instanceof PreferenceScreen) {
            setHasStableIds(((PreferenceScreen) preferenceGroup).shouldUseGeneratedIds());
        } else {
            setHasStableIds(true);
        }
        updatePreferences();
    }

    private List<Integer> createAccessibilityPositionTable() {
        int i2;
        ArrayList arrayList = new ArrayList();
        Iterator<Preference> it = this.mVisiblePreferences.iterator();
        int i7 = -1;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (it.next().getLayoutResource() != R$layout.sesl_preference_category_empty) {
                i7++;
            }
            arrayList.add(Integer.valueOf(Math.max(i7, 0)));
        }
        if (arrayList.size() > 0 && ((Integer) C0212a.i(arrayList, 1)).intValue() >= this.mVisiblePreferences.size()) {
            Log.w("PreferenceGroupAdapter", "accessibilityPosition over visible size | last " + arrayList.get(arrayList.size() - 1) + " vsize " + this.mVisiblePreferences.size());
            for (i2 = 0; i2 < arrayList.size(); i2++) {
                arrayList.set(i2, Integer.valueOf(i2));
            }
        }
        return arrayList;
    }

    private ExpandButton createExpandButton(final PreferenceGroup preferenceGroup, List<Preference> list) {
        ExpandButton expandButton = new ExpandButton(preferenceGroup.getContext(), list, preferenceGroup.getId());
        expandButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                preferenceGroup.setInitialExpandedChildrenCount(Integer.MAX_VALUE);
                PreferenceGroupAdapter.this.onPreferenceHierarchyChange(preference);
                preferenceGroup.getOnExpandButtonClickListener();
                return true;
            }
        });
        return expandButton;
    }

    private List<Preference> createVisiblePreferencesList(PreferenceGroup preferenceGroup) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int preferenceCount = preferenceGroup.getPreferenceCount();
        int i2 = 0;
        for (int i7 = 0; i7 < preferenceCount; i7++) {
            Preference preference = preferenceGroup.getPreference(i7);
            if (preference.isVisible()) {
                if (!isGroupExpandable(preferenceGroup) || i2 < preferenceGroup.getInitialExpandedChildrenCount()) {
                    arrayList.add(preference);
                } else {
                    arrayList2.add(preference);
                }
                if (!(preference instanceof PreferenceGroup)) {
                    i2++;
                } else {
                    PreferenceGroup preferenceGroup2 = (PreferenceGroup) preference;
                    if (!preferenceGroup2.isOnSameScreenAsChildren()) {
                        continue;
                    } else if (!isGroupExpandable(preferenceGroup) || !isGroupExpandable(preferenceGroup2)) {
                        for (Preference next : createVisiblePreferencesList(preferenceGroup2)) {
                            if (!isGroupExpandable(preferenceGroup) || i2 < preferenceGroup.getInitialExpandedChildrenCount()) {
                                arrayList.add(next);
                            } else {
                                arrayList2.add(next);
                            }
                            i2++;
                        }
                    } else {
                        throw new IllegalStateException("Nesting an expandable group inside of another expandable group is not supported!");
                    }
                }
            }
        }
        if (isGroupExpandable(preferenceGroup) && i2 > preferenceGroup.getInitialExpandedChildrenCount()) {
            arrayList.add(createExpandButton(preferenceGroup, arrayList2));
        }
        return arrayList;
    }

    private void flattenPreferenceGroup(List<Preference> list, PreferenceGroup preferenceGroup) {
        preferenceGroup.sortPreferences();
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            Preference preference = preferenceGroup.getPreference(i2);
            if (i2 == preferenceCount - 1) {
                this.mNextPreference = null;
                if (this.mIsCategoryAfter && preference == this.mNextGroupPreference) {
                    this.mNextGroupPreference = null;
                }
            } else {
                this.mNextPreference = preferenceGroup.getPreference(i2 + 1);
                if (preference == this.mNextGroupPreference) {
                    this.mNextGroupPreference = null;
                }
            }
            boolean z = preference instanceof PreferenceCategory;
            if (z && !preference.mIsRoundChanged) {
                preference.seslSetSubheaderRoundedBackground(15);
            }
            list.add(preference);
            if (z && TextUtils.isEmpty(preference.getTitle()) && this.mCategoryLayoutId == preference.getLayoutResource()) {
                preference.setLayoutResource(R$layout.sesl_preference_category_empty);
            }
            PreferenceResourceDescriptor preferenceResourceDescriptor = new PreferenceResourceDescriptor(preference);
            if (!this.mPreferenceResourceDescriptors.contains(preferenceResourceDescriptor)) {
                this.mPreferenceResourceDescriptors.add(preferenceResourceDescriptor);
            }
            if (preference instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup2 = (PreferenceGroup) preference;
                if (preferenceGroup2.isOnSameScreenAsChildren()) {
                    this.mNextGroupPreference = this.mNextPreference;
                    flattenPreferenceGroup(list, preferenceGroup2);
                }
            }
            preference.setOnPreferenceChangeInternalListener(this);
        }
    }

    private boolean isGroupExpandable(PreferenceGroup preferenceGroup) {
        if (preferenceGroup.getInitialExpandedChildrenCount() != Integer.MAX_VALUE) {
            return true;
        }
        return false;
    }

    public Preference getItem(int i2) {
        if (i2 < 0 || i2 >= getItemCount()) {
            return null;
        }
        return this.mVisiblePreferences.get(i2);
    }

    public int getItemCount() {
        return this.mVisiblePreferences.size();
    }

    public long getItemId(int i2) {
        if (!hasStableIds() || getItem(i2) == null) {
            return -1;
        }
        return getItem(i2).getId();
    }

    public int getItemViewType(int i2) {
        PreferenceResourceDescriptor preferenceResourceDescriptor = new PreferenceResourceDescriptor(getItem(i2));
        int indexOf = this.mPreferenceResourceDescriptors.indexOf(preferenceResourceDescriptor);
        if (indexOf != -1) {
            return indexOf;
        }
        int size = this.mPreferenceResourceDescriptors.size();
        this.mPreferenceResourceDescriptors.add(preferenceResourceDescriptor);
        return size;
    }

    public int getListWidth() {
        return this.mParentWidth;
    }

    public int getPreferenceAdapterPosition(String str) {
        int size = this.mVisiblePreferences.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (TextUtils.equals(str, this.mVisiblePreferences.get(i2).getKey())) {
                return i2;
            }
        }
        return -1;
    }

    public boolean isSwitchLayout(Preference preference) {
        if (preference.getLayoutResource() == R$layout.sesl_preference_switch && preference.getWidgetLayoutResource() == R$layout.sesl_preference_widget_switch) {
            return true;
        }
        if (preference.getLayoutResource() == R$layout.sesl_preference_switch_screen && preference.getWidgetLayoutResource() == R$layout.sesl_switch_preference_screen_widget_divider) {
            return true;
        }
        return false;
    }

    public void onPreferenceChange(Preference preference) {
        int indexOf = this.mVisiblePreferences.indexOf(preference);
        if (indexOf != -1) {
            notifyItemChanged(indexOf, preference);
        }
    }

    public void onPreferenceHierarchyChange(Preference preference) {
        this.mHandler.removeCallbacks(this.mSyncRunnable);
        this.mHandler.post(this.mSyncRunnable);
    }

    public void onPreferenceVisibilityChange(Preference preference) {
        onPreferenceHierarchyChange(preference);
    }

    public int seslGetAccessibilityItemCount() {
        List<Integer> list = this.mAccessibilityPositionTable;
        if (list != null && list.size() > 0) {
            return ((Integer) C0086a.f(1, this.mAccessibilityPositionTable)).intValue() + 1;
        }
        int i2 = 0;
        for (Preference layoutResource : this.mVisiblePreferences) {
            if (layoutResource.getLayoutResource() == R$layout.sesl_preference_category_empty) {
                i2++;
            }
        }
        return getItemCount() - i2;
    }

    public int seslGetAccessibilityItemPosition(int i2) {
        List<Integer> list = this.mAccessibilityPositionTable;
        if (list == null || i2 >= list.size()) {
            return -1;
        }
        return this.mAccessibilityPositionTable.get(i2).intValue();
    }

    public boolean seslUseCustomAccessibilityPosition() {
        return true;
    }

    public void updatePreferences() {
        for (Preference onPreferenceChangeInternalListener : this.mPreferences) {
            onPreferenceChangeInternalListener.setOnPreferenceChangeInternalListener((Preference.OnPreferenceChangeInternalListener) null);
        }
        ArrayList arrayList = new ArrayList(this.mPreferences.size());
        this.mPreferences = arrayList;
        flattenPreferenceGroup(arrayList, this.mPreferenceGroup);
        this.mVisiblePreferences = createVisiblePreferencesList(this.mPreferenceGroup);
        this.mAccessibilityPositionTable = createAccessibilityPositionTable();
        PreferenceManager preferenceManager = this.mPreferenceGroup.getPreferenceManager();
        if (preferenceManager != null) {
            preferenceManager.getPreferenceComparisonCallback();
        }
        notifyDataSetChanged();
        for (Preference clearWasDetached : this.mPreferences) {
            clearWasDetached.clearWasDetached();
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder, int i2) {
        Preference item = getItem(i2);
        preferenceViewHolder.resetState();
        if (isSwitchLayout(item)) {
            int width = this.mParent.getWidth();
            this.mParentWidth = width;
            if (item instanceof SwitchPreference) {
                ((SwitchPreference) item).onBindViewHolder(preferenceViewHolder, width);
            } else if (item instanceof SwitchPreferenceCompat) {
                ((SwitchPreferenceCompat) item).onBindViewHolder(preferenceViewHolder, width);
            } else {
                item.onBindViewHolder(preferenceViewHolder);
            }
        } else {
            if (item instanceof SeekBarPreference) {
                preferenceViewHolder.seslSetViewHolderRecoilEffectEnabled(false);
            }
            item.onBindViewHolder(preferenceViewHolder);
        }
    }

    public PreferenceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        PreferenceResourceDescriptor preferenceResourceDescriptor = this.mPreferenceResourceDescriptors.get(i2);
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        this.mParent = viewGroup;
        View inflate = from.inflate(preferenceResourceDescriptor.mLayoutResId, viewGroup, false);
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(16908312);
        if (viewGroup2 != null) {
            int i7 = preferenceResourceDescriptor.mWidgetLayoutResId;
            if (i7 != 0) {
                from.inflate(i7, viewGroup2);
            } else {
                viewGroup2.setVisibility(8);
            }
        }
        View findViewById = inflate.findViewById(R$id.badge_frame);
        if (findViewById != null) {
            if (preferenceResourceDescriptor.mIsDotVisibled) {
                findViewById.setVisibility(0);
            } else {
                findViewById.setVisibility(8);
            }
            String str = preferenceResourceDescriptor.mDotDescription;
            if (str != null) {
                findViewById.setContentDescription(str);
            }
        }
        return new PreferenceViewHolder(inflate);
    }
}
