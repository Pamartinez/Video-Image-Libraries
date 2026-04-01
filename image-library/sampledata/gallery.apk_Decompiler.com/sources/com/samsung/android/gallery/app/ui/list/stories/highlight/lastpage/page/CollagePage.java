package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.app.controller.externals.StartStoryCollageEditorCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageInfo;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.CollagePageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.StringJoiner;
import o6.t;
import r6.C0511a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CollagePage extends SummaryPage {
    private static final boolean SUPPORT_EDIT;
    protected ViewGroup mCollageContainer;
    protected CollageInfo mCollageInfo;
    private View mSaveTool;
    protected View mTools;
    protected CollageType mType = CollageType.COLLAGE1;

    static {
        boolean z;
        if ((!SdkConfig.atLeast(SdkConfig.SEM.V) || !Features.isEnabled(Features.SUPPORT_DOWNLOAD_VIDEO_TRIMMER)) && !PreferenceFeatures.isEnabled(PreferenceFeatures.StoryCollageForceCreation)) {
            z = false;
        } else {
            z = true;
        }
        SUPPORT_EDIT = z;
    }

    public CollagePage(View view, IStoryHighlightView iStoryHighlightView, View view2) {
        super(view, iStoryHighlightView, view2);
    }

    private void arrangeCollageTool(int i2, int i7, int i8) {
        this.mContainer.setOrientation(i2);
        ViewUtils.removeSelf(this.mTools);
        this.mContainer.addView(this.mTools, i7);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mTools.getLayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.gravity = i8;
        this.mTools.setLayoutParams(layoutParams);
    }

    private void handleSave() {
        if (this.mView.setInputBlock("handleSave", 500)) {
            handleSaveInternal();
        }
    }

    private void initCollageInfo(CollagePageItem collagePageItem) {
        CollageInfo collageInfo = collagePageItem.getCollageInfo();
        this.mCollageInfo = collageInfo;
        if (collageInfo != null) {
            this.mType = collageInfo.getType();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindItem$0(View view) {
        handleSave();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindItem$1(View view) {
        startCollageEditor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startPreview$2() {
        if (this.mPreviewRequested) {
            startPreviewInternal();
        }
    }

    private void setRelativePosition(View view, int i2, int i7, int i8) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (i2 != -1) {
            layoutParams.removeRule(i2);
        }
        if (i8 != -1) {
            layoutParams.addRule(i7, i8);
        } else if (i7 != -1) {
            layoutParams.addRule(i7);
        }
    }

    private void startCollageEditor() {
        Log.d(this.TAG, "startCollageEditor");
        if (this.mCollageInfo != null) {
            MediaItem[] collageItems = getCollageItems();
            new StartStoryCollageEditorCmd().execute(this.mView.getEventContext(), collageItems, Integer.valueOf(this.mType.getLayoutNumber()), getSmartCropRectList(collageItems), getGradientInfo());
        }
    }

    private void updateCollageToolLayout() {
        int intDimen = getIntDimen(R.dimen.story_collage_page_tool_top_margin);
        if (isLandscape()) {
            arrangeCollageTool(0, 0, 8388659);
            setRelativePosition(this.mSaveTool, 17, 3, R.id.edit_btn);
            ViewMarginUtils.setMargin(this.mTools, (Integer) null, 0, Integer.valueOf(intDimen), 0);
            return;
        }
        arrangeCollageTool(1, 1, 8388613);
        setRelativePosition(this.mSaveTool, 3, 17, R.id.edit_btn);
        ViewMarginUtils.setMargin(this.mTools, (Integer) null, Integer.valueOf(intDimen), 0, 0);
    }

    public void bind(PageItem pageItem) {
        initCollageInfo((CollagePageItem) pageItem);
        super.bind(pageItem);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mTools = view.findViewById(R.id.collage_tool);
        this.mSaveTool = view.findViewById(R.id.save_btn);
        this.mCollageContainer = (ViewGroup) view.findViewById(R.id.collage_item_container);
    }

    public MediaItem[] getCollageItems() {
        return (MediaItem[]) this.mCollageInfo.getItems().toArray(new MediaItem[0]);
    }

    public View getContent() {
        return this.mCollageContainer;
    }

    public String getDisplayPositionRect(View view, View view2) {
        if (view2 == null) {
            return "0,0,0,0";
        }
        View findViewById = view2.findViewById(R.id.thumbnail);
        int width = view.getWidth();
        int height = view.getHeight();
        if (width == 0 || height == 0) {
            return "0,0,0,0";
        }
        RectF viewRect = ViewUtils.getViewRect(view);
        RectF viewRect2 = ViewUtils.getViewRect(findViewById);
        float f = viewRect2.left - viewRect.left;
        float f5 = viewRect2.top - viewRect.top;
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        StringBuilder sb2 = new StringBuilder("");
        float f8 = (float) width;
        sb2.append(f / f8);
        StringJoiner add = stringJoiner.add(sb2.toString());
        StringBuilder sb3 = new StringBuilder("");
        float f10 = (float) height;
        sb3.append(f5 / f10);
        StringJoiner add2 = add.add(sb3.toString());
        StringJoiner add3 = add2.add("" + ((viewRect2.width() + f) / f8));
        return add3.add("" + ((viewRect2.height() + f5) / f10)).toString();
    }

    public Object getGradientInfo() {
        return null;
    }

    public int[] getSaveResolution(boolean z) {
        int i2;
        if (z) {
            i2 = 2576;
        } else {
            i2 = 1080;
        }
        return new int[]{i2, (int) (((float) i2) * 1.58f)};
    }

    public abstract ArrayList<String> getSmartCropRectList(MediaItem[] mediaItemArr);

    public abstract void handleSaveInternal();

    public void initEntryEffect() {
        if (this.mEntryEffectHelper.effectRequired()) {
            this.mEntryEffectHelper.addEntryEndEffect(this.mTools);
        }
    }

    public void initLayout(PageSpec.Config config) {
        super.initLayout(config);
        initEntryEffect();
    }

    public void onBindItem() {
        initLayout(new PageSpec(this.mView, this.mRootParent).calculate());
        this.itemView.findViewById(R.id.save_btn).setOnClickListener(new C0511a(this, 0));
        if (SUPPORT_EDIT) {
            View findViewById = this.itemView.findViewById(R.id.edit_btn);
            findViewById.setOnClickListener(new C0511a(this, 1));
            ViewUtils.setVisibleOrGone(findViewById, true);
            ViewUtils.setAccessibilityRoleDescription(findViewById, R.string.speak_button);
        }
        ViewUtils.setAccessibilityRoleDescription(this.itemView.findViewById(R.id.save_btn), R.string.speak_button);
        startPreview(100);
    }

    public String rectToString(RectF rectF) {
        if (!RectUtils.isValidRect(rectF)) {
            return "";
        }
        return "" + rectF.left + GlobalPostProcInternalPPInterface.SPLIT_REGEX + rectF.top + GlobalPostProcInternalPPInterface.SPLIT_REGEX + rectF.right + GlobalPostProcInternalPPInterface.SPLIT_REGEX + rectF.bottom;
    }

    public void recycle() {
        super.recycle();
        stopPreview();
    }

    public void startPreview() {
        super.startPreview();
        startPreview(0);
        Log.d(this.TAG, "startPreview");
    }

    public abstract void startPreviewInternal();

    public void stopPreview() {
        super.stopPreview();
        stopPreviewInternal();
        Log.d(this.TAG, "stopPreview");
    }

    public abstract void stopPreviewInternal();

    public void updateLayout(PageSpec.Config config, int i2) {
        super.updateLayout(config, i2);
        updateCollageToolLayout();
    }

    private void startPreview(int i2) {
        ThreadUtil.postOnUiThreadDelayed(new t(16, this), (long) i2);
    }
}
