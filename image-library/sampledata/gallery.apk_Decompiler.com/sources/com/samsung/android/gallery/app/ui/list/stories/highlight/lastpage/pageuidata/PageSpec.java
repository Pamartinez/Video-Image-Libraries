package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageSpec {
    private final float mBaseHeight;
    private final boolean mIsOnDemand;
    private final boolean mIsTablet = Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES);
    private final View mReplay;
    private final View mRoot;
    private final View mTool;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Config {
        public float baseSize = 360.0f;
        boolean isPort;
        public int pageBottomSpace;
        public int pageListStartMargin;
        public int pageTopSpace;
        public int sideGap;
        public int width;

        public String toString() {
            String str;
            StringBuilder sb2 = new StringBuilder("PageSpec.Config{b");
            sb2.append(this.baseSize);
            sb2.append(",(w");
            sb2.append(this.width);
            sb2.append(",hIgnore),s");
            sb2.append(this.sideGap);
            sb2.append(",rbm");
            sb2.append(this.pageBottomSpace);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            if (this.isPort) {
                str = "P";
            } else {
                str = "L";
            }
            sb2.append(str);
            return sb2.toString();
        }
    }

    public PageSpec(IStoryHighlightView iStoryHighlightView, View view) {
        float f;
        boolean isOnDemandStory = iStoryHighlightView.getOptions().isOnDemandStory();
        this.mIsOnDemand = isOnDemandStory;
        if (isOnDemandStory) {
            f = 430.0f;
        } else {
            f = 490.0f;
        }
        this.mBaseHeight = f;
        this.mRoot = view;
        this.mTool = view.findViewById(R.id.tool);
        this.mReplay = view.findViewById(R.id.replay_button_layout);
    }

    private int calAvailableSpace(int i2) {
        return ((i2 - getDecoViewsHeight()) - getSystemWindowInsets()) - getVerticalGap();
    }

    private Config calLandscape(int i2, int i7) {
        int i8;
        int calAvailableSpace = calAvailableSpace(i7);
        Config config = new Config();
        config.isPort = false;
        int i10 = 10;
        config.width = (int) ((((float) (calAvailableSpace - 10)) * 310.0f) / this.mBaseHeight);
        if (!isMultiWindow()) {
            i8 = getTopSpace();
        } else {
            i8 = 10;
        }
        config.pageTopSpace = i8;
        if (!isMultiWindow()) {
            i10 = getBottomSpace();
        }
        config.pageBottomSpace = i10;
        config.sideGap = Math.min((i2 - config.width) / 2, getSideGap(i2));
        config.baseSize = ((float) config.width) / 0.8611111f;
        config.pageListStartMargin = getPageListStartMargin();
        Log.d("PageSpec", "land", Integer.valueOf(i2), Integer.valueOf(i7), config);
        return config;
    }

    private Config calPortrait(int i2, int i7) {
        int calAvailableSpace = calAvailableSpace(i7);
        if (this.mIsOnDemand) {
            i2 = getOnDemandWSpace(i2);
        }
        float f = (float) i2;
        Config config = new Config();
        config.isPort = true;
        int i8 = (int) ((((float) (i2 - (((int) ((25.0f * f) / 360.0f)) * 2))) * this.mBaseHeight) / 310.0f);
        int topSpace = getTopSpace();
        int bottomSpace = getBottomSpace();
        if (calAvailableSpace > i8) {
            config.width = (int) ((f * 310.0f) / 360.0f);
        } else {
            int min = Math.min(getVerticalGap(), i8 - calAvailableSpace);
            topSpace = (getVerticalGap() - (min - 10)) / 2;
            config.width = (int) ((((float) ((min - 60) + calAvailableSpace)) * 310.0f) / this.mBaseHeight);
            bottomSpace = topSpace;
        }
        config.pageTopSpace = topSpace;
        config.pageBottomSpace = bottomSpace;
        int i10 = config.width;
        config.sideGap = (i2 - i10) / 2;
        config.baseSize = ((float) i10) / 0.8611111f;
        Log.d("PageSpec", "port", Integer.valueOf(i2), Integer.valueOf(i7), config);
        return config;
    }

    private int getBottomSpace() {
        return getIntDimen(R.dimen.story_last_page_bottom_space);
    }

    public static float getCollageContentRatio() {
        return 0.63265306f;
    }

    private int getCollageToolHeight() {
        return getIntDimen(R.dimen.story_collage_page_tool_top_margin) + getIntDimen(R.dimen.story_collage_page_tool_height);
    }

    public static Config getConfigForSave(int i2) {
        Config config = new Config();
        int i7 = (int) ((((float) i2) * 310.0f) / 360.0f);
        config.width = i7;
        config.baseSize = ((float) i7) / 0.8611111f;
        return config;
    }

    private int getDecoViewsHeight() {
        int replayBtnHeight;
        int height = this.mTool.getHeight();
        if (!isPortrait()) {
            return height;
        }
        if (this.mIsOnDemand) {
            replayBtnHeight = getSaveStoryBtnHeight();
        } else {
            height += getCollageToolHeight();
            replayBtnHeight = getReplayBtnHeight();
        }
        return height + replayBtnHeight;
    }

    private int getOnDemandWSpace(int i2) {
        int i7;
        if (this.mIsTablet) {
            i7 = R.dimen.story_ondemand_page_max_width_tablet;
        } else {
            i7 = R.dimen.story_ondemand_page_max_width;
        }
        return Math.min(i2, getIntDimen(i7));
    }

    private int getReplayBtnHeight() {
        return getIntDimen(R.dimen.story_highlight_related_sub_button_height);
    }

    private int getSaveStoryBtnHeight() {
        int intDimen = getIntDimen(R.dimen.story_ondemand_page_save_btn_top_margin);
        return getIntDimen(R.dimen.story_ondemand_page_rewrite_btn_top_margin) + intDimen + (getReplayBtnHeight() * 2) + 10;
    }

    private int getSideGap(int i2) {
        return (int) (ResourceCompat.getFloatFromDimension(this.mRoot.getContext(), (int) R.dimen.story_last_page_item_side_gap_ratio) * ((float) i2));
    }

    private int getSystemWindowInsets() {
        int i2;
        if (isMultiWindow()) {
            i2 = WindowUtils.getSystemInsetsTop(this.mRoot.getRootWindowInsets());
        } else {
            i2 = WindowUtils.getStatusBarInsets(this.mRoot.getRootWindowInsets(), true).top;
        }
        return WindowUtils.getSystemInsetsBottom(this.mRoot.getRootWindowInsets()) + i2;
    }

    private int getTopSpace() {
        int i2;
        if (this.mIsOnDemand) {
            i2 = R.dimen.story_ondemand_page_top_space;
        } else {
            i2 = R.dimen.story_last_page_top_space;
        }
        return getIntDimen(i2);
    }

    private int getVerticalGap() {
        int i2;
        if (this.mIsOnDemand) {
            int topSpace = getTopSpace();
            if (this.mIsTablet) {
                i2 = R.dimen.story_ondemand_page_bottom_space_tablet;
            } else {
                i2 = R.dimen.story_ondemand_page_bottom_space;
            }
            return topSpace + getIntDimen(i2);
        }
        return getIntDimen(R.dimen.story_collage_page_bottom_space) + getTopSpace() + getBottomSpace();
    }

    private boolean isMultiWindow() {
        return SystemUi.isInMultiWindowMode(Utils.getActivity(this.mRoot));
    }

    private boolean isPortrait() {
        return ResourceCompat.isPortrait(this.mRoot);
    }

    public Config calculate() {
        int width = this.mRoot.getWidth();
        int height = this.mRoot.getHeight();
        if (isPortrait()) {
            return calPortrait(width, height);
        }
        return calLandscape(width, height);
    }

    public int getIntDimen(int i2) {
        return ResourceCompat.getDimensionPixelOffset(this.mRoot.getContext(), i2, 0);
    }

    public int getPageListStartMargin() {
        if (isPortrait()) {
            return 0;
        }
        return getCollageToolHeight();
    }
}
