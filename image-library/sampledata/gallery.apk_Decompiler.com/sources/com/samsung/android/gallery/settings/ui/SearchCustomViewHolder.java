package com.samsung.android.gallery.settings.ui;

import Ab.a;
import B2.i;
import Fa.K;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.accessibility.AccessibilityEvent;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.abstraction.VisualSearchCategory;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TriConsumer;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import i.C0212a;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchCustomViewHolder extends ListViewHolder implements View.OnClickListener {
    static final HashMap<String, Integer> CATEGORY_NAME_MAP = new HashMap<String, Integer>() {
        {
            put("location://search/fileList/Category/MyQuery", Integer.valueOf(R$string.category_quick_searches));
            put("location://search/fileList/Category/PeopleAndPets/true", Integer.valueOf(R$string.visual_search_category_people_and_pets));
            int i2 = R$string.people;
            put("location://search/fileList/Category/PeopleAndPets/false", Integer.valueOf(i2));
            put("location://search/fileList/Category/People", Integer.valueOf(i2));
            put("location://search/fileList/Category/ScreenShot", Integer.valueOf(R$string.screenshot));
            put("location://search/fileList/Category/Location", Integer.valueOf(R$string.searchview_location));
            put("location://search/fileList/Category/Documents", Integer.valueOf(R$string.documents));
            put("location://search/fileList/Category/Activity", Integer.valueOf(R$string.category_activity));
            put("location://search/fileList/Category/ShotMode", Integer.valueOf(R$string.shot_types));
            put("location://search/fileList/Category/MyTag", Integer.valueOf(R$string.my_tags));
            put("location://search/fileList/Category/Stories", Integer.valueOf(R$string.stories));
        }
    };
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            if (accessibilityEvent.getEventType() == 32768) {
                StringBuilder sb2 = new StringBuilder();
                SearchCustomViewHolder searchCustomViewHolder = SearchCustomViewHolder.this;
                sb2.append(searchCustomViewHolder.getCategoryName(searchCustomViewHolder.mLocationKey));
                sb2.append(ArcCommonLog.TAG_COMMA);
                sb2.append(SearchCustomViewHolder.this.getCompoundButton().getStateDescription());
                view.setContentDescription(sb2.toString());
            }
        }
    };
    protected String mLocationKey;
    protected View mReorderIconView;
    private boolean mReorderMode;
    private TextView mSearchCategoryNameTextView;
    private SwitchCompat mSwitchCompat;
    private TriConsumer<RecyclerView.ViewHolder, View, MotionEvent> mTouchCallback;

    public SearchCustomViewHolder(View view, int i2) {
        super(view, i2);
    }

    private String getCategoryNameMapKey(String str) {
        String str2;
        StringBuilder s = C0212a.s(str);
        if (isPeopleAndPetCategory(str)) {
            str2 = "/" + IdentityCreatureUtil.isPetRecognized();
        } else {
            str2 = "";
        }
        s.append(str2);
        return s.toString();
    }

    private String getEventId() {
        String str = this.mLocationKey;
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -2065663554:
                if (str.equals("location://search/fileList/Category/MyQuery")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1141680973:
                if (str.equals("location://search/fileList/Category/Location")) {
                    c5 = 1;
                    break;
                }
                break;
            case -403724275:
                if (str.equals("location://search/fileList/Category/Activity")) {
                    c5 = 2;
                    break;
                }
                break;
            case 910438587:
                if (str.equals("location://search/fileList/Category/ShotMode")) {
                    c5 = 3;
                    break;
                }
                break;
            case 1108360570:
                if (str.equals("location://search/fileList/Category/Documents")) {
                    c5 = 4;
                    break;
                }
                break;
            case 1194757598:
                if (str.equals("location://search/fileList/Category/PeopleAndPets")) {
                    c5 = 5;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return AnalyticsEventId.EVENT_SEARCH_CUSTOMIZE_SEARCH_SHORTCUTS.toString();
            case 1:
                return AnalyticsEventId.EVENT_SEARCH_CUSTOMIZE_LOCATIONS.toString();
            case 2:
                return AnalyticsEventId.EVENT_SEARCH_CUSTOMIZE_ACTIVITY.toString();
            case 3:
                return AnalyticsEventId.EVENT_SEARCH_CUSTOMIZE_SHOT_TYPES.toString();
            case 4:
                return AnalyticsEventId.EVENT_SEARCH_CUSTOMIZE_DOCUMENTS.toString();
            case 5:
                return AnalyticsEventId.EVENT_SEARCH_CUSTOMIZE_PEOPLE_AND_PETS.toString();
            default:
                return AnalyticsEventId.EVENT_SEARCH_CUSTOMIZE_TAGS.toString();
        }
    }

    private void inflateReorderIcon() {
        View view = this.mReorderIconView;
        if (view instanceof ViewStub) {
            View inflate = ((ViewStub) view).inflate();
            this.mReorderIconView = inflate;
            inflate.setOnTouchListener(new i(8, this));
            setReorderIconVisibleOrGone(false);
        }
    }

    private boolean isPeopleAndPetCategory(String str) {
        return "location://search/fileList/Category/PeopleAndPets".equals(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$inflateReorderIcon$0(View view, MotionEvent motionEvent) {
        return onReorderTouched(this.itemView, motionEvent);
    }

    /* access modifiers changed from: private */
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (VisualSearchCategory.setEnabled(this.mLocationKey, z)) {
            Blackboard.publishGlobal("command:///SearchCategoryReorder", Boolean.TRUE);
        } else {
            Log.e((CharSequence) this.TAG, "onCheckedChanged update failed", Integer.valueOf(getAbsoluteAdapterPosition()), Boolean.valueOf(z));
        }
        sendSALogging(z);
    }

    private boolean onReorderTouched(View view, MotionEvent motionEvent) {
        TriConsumer<RecyclerView.ViewHolder, View, MotionEvent> triConsumer;
        if (!this.mReorderMode || (triConsumer = this.mTouchCallback) == null) {
            return false;
        }
        triConsumer.accept(this, view, motionEvent);
        return false;
    }

    private void sendSALogging(boolean z) {
        long j2;
        AnalyticsLogger instance = AnalyticsLogger.getInstance();
        String analyticsScreenId = AnalyticsScreenId.SCREEN_SEARCH_CATEGORIES_CUSTOMIZE.toString();
        String eventId = getEventId();
        if (z) {
            j2 = 1;
        } else {
            j2 = 0;
        }
        instance.postLog(analyticsScreenId, eventId, j2);
    }

    private void setReorderIconVisibleOrGone(boolean z) {
        ViewUtils.setVisibleOrGone(this.mReorderIconView, z);
    }

    public void bindView(View view) {
        this.mSearchCategoryNameTextView = (TextView) view.findViewById(R$id.search_category_name);
        createCompoundButton(view);
        if (getCompoundButton() != null) {
            getCompoundButton().setOnCheckedChangeListener(new K(0, this));
        }
        this.mReorderIconView = view.findViewById(R$id.reorder_icon);
    }

    public void createCompoundButton(View view) {
        this.mSwitchCompat = (SwitchCompat) view.findViewById(R$id.switch_category);
    }

    public String getCategoryName(String str) {
        Integer orDefault = CATEGORY_NAME_MAP.getOrDefault(getCategoryNameMapKey(str), (Object) null);
        if (orDefault != null) {
            return this.itemView.getContext().getString(orDefault.intValue());
        }
        return "";
    }

    public CompoundButton getCompoundButton() {
        return this.mSwitchCompat;
    }

    public void onBind(String str) {
        this.mLocationKey = str;
        this.mSearchCategoryNameTextView.setText(getCategoryName(str));
        if (getCompoundButton() != null) {
            getCompoundButton().setChecked(VisualSearchCategory.isEnabled(str));
        }
        this.itemView.setAccessibilityDelegate(this.mAccessibilityDelegate);
        this.itemView.setOnClickListener(new a(21, this));
    }

    public void onClick(View view) {
        if (getCompoundButton() != null) {
            onCheckedChanged(getCompoundButton(), getCompoundButton().isChecked());
        }
    }

    public void onClickListItem(View view) {
        if (!this.mReorderMode && getCompoundButton() != null) {
            getCompoundButton().setChecked(!getCompoundButton().isChecked());
        }
    }

    public void recycle() {
        super.recycle();
        this.mSearchCategoryNameTextView.setText((CharSequence) null);
        if (getCompoundButton() != null) {
            getCompoundButton().setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        }
    }

    public void setReorderMode(boolean z) {
        this.mReorderMode = z;
        if (z) {
            inflateReorderIcon();
        }
        setReorderIconVisibleOrGone(z);
        setSwitchVisibleOrGone(!z);
    }

    public void setSwitchVisibleOrGone(boolean z) {
        ViewUtils.setVisibleOrGone(getCompoundButton(), z);
    }

    public SearchCustomViewHolder setTouchCallback(TriConsumer<RecyclerView.ViewHolder, View, MotionEvent> triConsumer) {
        this.mTouchCallback = triConsumer;
        return this;
    }
}
