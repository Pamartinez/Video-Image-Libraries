package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import android.view.KeyEvent;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.livemotion.LiveMotionViewPager;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeyboardDelegate {
    private final BooleanSupplier mFocusSupplier;
    private final IStoryHighlightView mView;
    private final LiveMotionViewPager mViewPager;

    public KeyboardDelegate(IStoryHighlightView iStoryHighlightView, LiveMotionViewPager liveMotionViewPager, BooleanSupplier booleanSupplier) {
        this.mView = iStoryHighlightView;
        this.mViewPager = liveMotionViewPager;
        this.mFocusSupplier = booleanSupplier;
    }

    private boolean handleKeyDown(int i2, KeyEvent keyEvent) {
        boolean isEnabled = Features.isEnabled(Features.IS_RTL);
        if (i2 == 84) {
            return true;
        }
        if (i2 != 92) {
            if (i2 != 93) {
                if (i2 != 122) {
                    if (i2 != 123) {
                        switch (i2) {
                            case 19:
                                break;
                            case 20:
                                ViewGroup viewGroup = (ViewGroup) this.mView.getView().findViewById(R.id.bottom_deco_container);
                                if (hasFocus() && ViewUtils.isVisible(viewGroup)) {
                                    viewGroup.requestFocus();
                                    return true;
                                }
                            case 21:
                                if (hasFocus()) {
                                    LiveMotionViewPager liveMotionViewPager = this.mViewPager;
                                    if (!isEnabled ? !liveMotionViewPager.movePrev() : !liveMotionViewPager.moveNext()) {
                                        return false;
                                    }
                                    return true;
                                }
                                return false;
                            case 22:
                                if (hasFocus()) {
                                    LiveMotionViewPager liveMotionViewPager2 = this.mViewPager;
                                    if (!isEnabled ? !liveMotionViewPager2.moveNext() : !liveMotionViewPager2.movePrev()) {
                                        return false;
                                    }
                                    return true;
                                }
                                return false;
                            default:
                                return false;
                        }
                        if (!hasFocus() || !ViewUtils.isVisible(this.mView.getToolbar())) {
                            return false;
                        }
                        this.mView.getToolbar().requestFocus();
                        return true;
                    } else if (!hasFocus()) {
                        return false;
                    } else {
                        this.mViewPager.moveToLast();
                        return true;
                    }
                } else if (!hasFocus()) {
                    return false;
                } else {
                    this.mViewPager.scrollToPosition(0, false);
                    return true;
                }
            } else if (!hasFocus() || !this.mViewPager.movePrev()) {
                return false;
            } else {
                return true;
            }
        } else if (!hasFocus() || !this.mViewPager.moveNext()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean handleKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 66 || !hasFocus()) {
            return false;
        }
        this.mView.getEventHandler().postEvent(Event.TOGGLE_OSD, new Object[0]);
        return true;
    }

    private boolean hasFocus() {
        return this.mFocusSupplier.getAsBoolean();
    }

    public boolean onKeyEvent(int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            return handleKeyUp(i2, keyEvent);
        }
        if (keyEvent.getAction() == 0) {
            return handleKeyDown(i2, keyEvent);
        }
        return false;
    }
}
