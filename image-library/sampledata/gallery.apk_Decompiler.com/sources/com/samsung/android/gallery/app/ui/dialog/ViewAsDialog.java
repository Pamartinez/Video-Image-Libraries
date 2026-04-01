package com.samsung.android.gallery.app.ui.dialog;

import Fa.C0558l;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import gc.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewAsDialog extends BaseDialog {
    private int mMaxDepth;
    private boolean mPinchAscOrder;
    protected RadioGroup mRadioGroup;
    protected RadioButton mViewAsLevel0;
    protected RadioButton mViewAsLevel1;
    protected RadioButton mViewAsLevel2;

    private void bindViews(View view) {
        this.mViewAsLevel0 = (RadioButton) view.findViewById(R.id.action_view_as_level_0);
        this.mViewAsLevel1 = (RadioButton) view.findViewById(R.id.action_view_as_level_1);
        this.mViewAsLevel2 = (RadioButton) view.findViewById(R.id.action_view_as_level_2);
        this.mRadioGroup = (RadioGroup) view.findViewById(R.id.view_as_radio_group);
    }

    private void initRadioButtonForAlbum() {
        this.mViewAsLevel0.setText(R.string.medium_album_thumb);
        this.mViewAsLevel1.setText(R.string.small_album_thumb);
        this.mViewAsLevel2.setVisibility(8);
    }

    private void initRadioButtonForPictures() {
        this.mViewAsLevel0.setText(R.string.large_thumbnails);
        this.mViewAsLevel1.setText(R.string.medium_thumbnails);
        this.mViewAsLevel2.setText(R.string.small_thumbnails);
    }

    private void initRadioButtonForStory() {
        this.mViewAsLevel0.setText(R.string.large_thumbnails);
        this.mViewAsLevel1.setText(R.string.medium_thumbnails);
        this.mViewAsLevel2.setVisibility(8);
    }

    private void initRatioButton(String str) {
        if (isAlbumsGrid(str)) {
            initRadioButtonForAlbum();
        } else if (LocationKey.isStoryPictures(str) || LocationKey.isStoryHighlight(str)) {
            initRadioButtonForStory();
        } else {
            initRadioButtonForPictures();
        }
    }

    private static boolean isAlbumsGrid(String str) {
        if (LocationKey.isAlbumsMatch(str) || LocationKey.isAllAlbumsMatch(str) || LocationKey.isFolder(str)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onClickRadioButton(RadioGroup radioGroup, int i2) {
        int i7;
        int i8;
        switch (i2) {
            case R.id.action_view_as_level_0:
                publishInternal(Integer.valueOf(this.mMaxDepth));
                break;
            case R.id.action_view_as_level_1:
                int i10 = this.mMaxDepth;
                if (this.mPinchAscOrder) {
                    i7 = 1;
                } else {
                    i7 = -1;
                }
                publishInternal(Integer.valueOf(i10 + i7));
                break;
            case R.id.action_view_as_level_2:
                int i11 = this.mMaxDepth;
                if (this.mPinchAscOrder) {
                    i8 = 3;
                } else {
                    i8 = -3;
                }
                publishInternal(Integer.valueOf(Math.max(0, i11 + i8)));
                break;
        }
        dismiss();
    }

    private void publishInternal(Object obj) {
        getBlackboard().post("data://user/dialog/ViewAs", obj);
    }

    private void setup(String str, int i2) {
        initRatioButton(str);
        toggleCurrentLevel(i2);
    }

    private void toggleCurrentLevel(int i2) {
        if (i2 == 0) {
            this.mViewAsLevel0.toggle();
        } else if (i2 == 1) {
            this.mViewAsLevel1.toggle();
        } else {
            this.mViewAsLevel2.toggle();
        }
    }

    public Dialog createDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_as_dialog, (ViewGroup) null);
        bindViews(inflate);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String removeArgs = ArgumentsUtil.removeArgs(arguments.getString("locationKey"));
            int parseInt = Integer.parseInt(arguments.getString("current_depth"));
            boolean isAlbumsGrid = isAlbumsGrid(removeArgs);
            this.mPinchAscOrder = !isAlbumsGrid;
            if (!isAlbumsGrid) {
                this.mMaxDepth = 0;
                setup(removeArgs, parseInt);
            } else {
                int parseInt2 = Integer.parseInt(arguments.getString("max_depth"));
                this.mMaxDepth = parseInt2;
                setup(removeArgs, parseInt2 - parseInt);
            }
        }
        this.mRadioGroup.setOnCheckedChangeListener(new a(3, this));
        AlertDialog create = new AlertDialog.Builder(getContext()).setTitle((int) R.string.view_as).setView(inflate).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new C0558l(18, this)).create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/ViewAs", (Object) null);
    }

    /* access modifiers changed from: private */
    public void onClickNegative(DialogInterface dialogInterface, int i2) {
    }
}
