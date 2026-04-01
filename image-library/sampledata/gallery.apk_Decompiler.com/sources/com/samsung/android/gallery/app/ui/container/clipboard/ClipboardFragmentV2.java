package com.samsung.android.gallery.app.ui.container.clipboard;

import M9.a;
import a6.C0419b;
import a8.d;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardPresenter;
import com.samsung.android.gallery.app.ui.container.clipboard.IClipboardView;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClipboardFragmentV2<V extends IClipboardView, P extends ClipboardPresenter> extends ClipboardFragment<V, P> implements IClipboardView {
    private ClipboardAnimDelegate mClipboardAnimDelegate;
    View mLineView;
    ViewGroup mSelectedLayout;
    ImageView mToggle;
    View mToggleLayout;
    private boolean mUserFrameworkDefaultBG = false;

    private void closeClipboard(boolean z) {
        this.mBlackboard.publish("data://clipboard_opened_status", Boolean.FALSE);
        if (isOpened()) {
            getClipboardAnimDelegate().doClose(z);
        }
    }

    private ClipboardAnimDelegate getClipboardAnimDelegate() {
        if (this.mClipboardAnimDelegate == null) {
            this.mClipboardAnimDelegate = new ClipboardAnimDelegate(this.mSelectedLayout, this.mToggleLayout);
        }
        return this.mClipboardAnimDelegate;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadPreselectedItems$0(String str, String str2, LaunchIntent launchIntent) {
        Cursor query;
        long currentTimeMillis = System.currentTimeMillis();
        Clipboard instance = Clipboard.getInstance(this.mBlackboard);
        QueryParams groupTypes = new QueryParams(DbKey.ALL_PICTURES).setGroupTypes(GroupType.BURST);
        if (!TextUtils.isEmpty(str)) {
            groupTypes.setFileIds(str);
        } else if (!TextUtils.isEmpty(str2)) {
            groupTypes.setMediaIds(str2);
        }
        Optional ofNullable = Optional.ofNullable(launchIntent.getType());
        Objects.requireNonNull(groupTypes);
        ofNullable.ifPresent(new a(1, groupTypes));
        try {
            query = DbCompat.query(groupTypes);
            if (query != null) {
                if (query.moveToFirst()) {
                    ArrayList arrayList = new ArrayList();
                    do {
                        MediaItem create = MediaItemBuilder.create(query);
                        arrayList.add(create);
                        instance.add(Long.valueOf(create.getFileId()));
                    } while (query.moveToNext());
                    getBlackboard().postEvent(EventMessage.obtain(1020, arrayList));
                    Log.d("ClipboardFragment", "loadPreselectedItems#preselected" + Logger.vt(Integer.valueOf(query.getCount()), launchIntent.getType(), str, str2, Long.valueOf(currentTimeMillis)));
                }
            }
            if (query != null) {
                query.close();
                return;
            }
            return;
        } catch (Error | Exception e) {
            e.printStackTrace();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void loadPreselectedItems() {
        String str;
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent");
        String str2 = null;
        if (launchIntent != null) {
            str = (String) launchIntent.getExtra("picker_preselected_sem_id", null);
        } else {
            str = null;
        }
        if (str == null && launchIntent != null) {
            str2 = (String) launchIntent.getExtra("picker_preselected_ged_id", null);
        }
        String str3 = str2;
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str3)) {
            SimpleThreadPool.getInstance().execute(new d((Object) this, str, (Object) str3, (Object) launchIntent, 14));
        }
    }

    private boolean needCloseClipboard() {
        if (!isLandscape() || Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onTouchToggleButton(View view) {
        String str;
        if (!getClipboardAnimDelegate().isRunning()) {
            boolean isOpened = isOpened();
            this.mBlackboard.post("command://OperateClipboardArea", Boolean.valueOf(!isOpened));
            AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_PICKER_TOGGLE_BUTTON;
            if (isOpened) {
                str = "Collapsed";
            } else {
                str = "Expanded";
            }
            postAnalyticsLog(analyticsEventId, str);
            setToggleContentDescription(!isOpened);
        }
    }

    private void openClipboard(boolean z) {
        int i2;
        this.mBlackboard.publish("data://clipboard_opened_status", Boolean.TRUE);
        if (!isOpened()) {
            if (((ClipboardPresenter) this.mPresenter).getSelectedItemCount() > 0) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            setClipboardViewVisibility(i2);
            getClipboardAnimDelegate().doOpen(this.mUserFrameworkDefaultBG, z);
        }
    }

    private void setToggleContentDescription(boolean z) {
        int i2;
        ImageView imageView = this.mToggle;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getString(R.string.selected_items));
        sb2.append(" ");
        if (z) {
            i2 = R.string.expanded;
        } else {
            i2 = R.string.collapsed;
        }
        sb2.append(getString(i2));
        imageView.setContentDescription(sb2.toString());
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mSelectedLayout = (ViewGroup) view.findViewById(R.id.selected_layout);
        this.mToggleLayout = view.findViewById(R.id.toggle_layout);
        this.mLineView = view.findViewById(R.id.toggle_line);
        this.mToggle = (ImageView) view.findViewById(R.id.toggle);
    }

    public void createClipboardView() {
        super.createClipboardView();
        this.mToggle.setOnClickListener(new C0419b(16, this));
        setToggleContentDescription(isOpened());
    }

    public int getLayoutId() {
        return R.layout.clipboard_custom_v2;
    }

    public boolean isOpened() {
        ImageView imageView = this.mToggle;
        if (imageView == null || imageView.getRotation() != 180.0f) {
            return false;
        }
        return true;
    }

    public void onBindView(View view) {
        createClipboardView();
        if (needCloseClipboard()) {
            closeClipboard(false);
        }
        loadPreselectedItems();
    }

    public void operateClipboard(boolean z, boolean z3) {
        if (z) {
            openClipboard(z3);
        } else {
            closeClipboard(z3);
        }
    }

    public void updateClipboardBackground(boolean z) {
        int i2;
        Context context = getContext();
        if (context != null) {
            this.mUserFrameworkDefaultBG = z;
            if (z) {
                i2 = R.color.default_fw_background;
            } else {
                i2 = R.color.default_background;
            }
            int color = context.getColor(i2);
            this.mSelectedLayout.setBackgroundColor(color);
            this.mToggle.setBackgroundColor(color);
            this.mToggleLayout.setBackgroundColor(color);
        }
    }

    public void updateClipboardLayout() {
        boolean z = false;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(getLayoutId(), (ViewGroup) null, false);
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.selected_layout);
        ViewGroup viewGroup3 = (ViewGroup) viewGroup.findViewById(R.id.toggle_layout);
        TextView textView = (TextView) viewGroup.findViewById(R.id.no_item_selected);
        RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_list);
        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.toggle);
        View findViewById = viewGroup.findViewById(R.id.toggle_line);
        ViewUtils.removeAllViews(viewGroup);
        ViewUtils.removeAllViews(viewGroup2);
        ViewUtils.removeAllViews(viewGroup3);
        ViewUtils.removeAllViews(this.mClipboardLayout);
        this.mClipboardLayout.addView(viewGroup2);
        this.mClipboardLayout.addView(viewGroup3);
        viewGroup2.addView(textView);
        viewGroup2.addView(recyclerView);
        viewGroup3.addView(findViewById);
        viewGroup3.addView(imageView);
        this.mNoItem = textView;
        this.mClipboardView = recyclerView;
        this.mSelectedLayout = viewGroup2;
        this.mToggleLayout = viewGroup3;
        this.mToggle = imageView;
        this.mLineView = findViewById;
        restoreClipboardView();
        ((ClipboardPresenter) this.mPresenter).restore();
        getClipboardAnimDelegate().setTargetView(this.mSelectedLayout, this.mToggleLayout);
        Blackboard blackboard = this.mBlackboard;
        String appendArgs = ArgumentsUtil.appendArgs("command://OperateClipboardArea", "clipboard_no_animation");
        if (!needCloseClipboard() && ((Boolean) this.mBlackboard.read("data://clipboard_opened_status", Boolean.TRUE)).booleanValue()) {
            z = true;
        }
        blackboard.post(appendArgs, Boolean.valueOf(z));
    }
}
