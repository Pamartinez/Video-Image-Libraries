package com.samsung.android.gallery.app.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import n5.e;
import o5.C0496a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CustomChooserDialog extends BaseDialog {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BaseAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private final String[] mData;
        private View.OnClickListener mOnClickListener;

        public BaseAdapter(String[] strArr) {
            this.mData = strArr;
        }

        /* access modifiers changed from: private */
        public int getGridSize() {
            return getItemCount();
        }

        private int getItemLayoutId() {
            return R.layout.custom_chooser_dialog_item;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Integer[] lambda$onBindViewHolder$0(int i2) {
            return new Integer[i2];
        }

        /* access modifiers changed from: private */
        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
        }

        private void setTouchArea(View view, int i2) {
            View view2;
            int itemCount = getItemCount();
            if (itemCount >= 1 && view != null && (view2 = (View) view.getParent()) != null) {
                int dimensionPixelOffset = view.getResources().getDimensionPixelOffset(R.dimen.custom_chooser_dialog_container_padding_horizontal);
                if (itemCount == 1) {
                    view2.setPaddingRelative(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
                    ViewUtils.setTouchArea(view, view2, dimensionPixelOffset, 0, dimensionPixelOffset, 0);
                    return;
                }
                View view3 = view;
                if (i2 == 0) {
                    view2.setPaddingRelative(dimensionPixelOffset, 0, 0, 0);
                    ViewUtils.setTouchArea(view3, view2, dimensionPixelOffset, 0, 0, 0);
                } else if (i2 == getItemCount() - 1) {
                    view2.setPaddingRelative(0, 0, dimensionPixelOffset, 0);
                    ViewUtils.setTouchArea(view3, view2, 0, 0, dimensionPixelOffset, 0);
                }
            }
        }

        public int getItemCount() {
            return this.mData.length;
        }

        public void onBindViewHolder(ItemViewHolder itemViewHolder, int i2) {
            if (i2 >= 0 && i2 < getItemCount()) {
                Integer[] numArr = (Integer[]) Arrays.stream(this.mData[i2].split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new e(18)).toArray(new a(1));
                itemViewHolder.itemView.setOnClickListener(this.mOnClickListener);
                itemViewHolder.itemView.setTag(numArr[1]);
                itemViewHolder.setIcon(numArr[0].intValue());
                itemViewHolder.setTitle(numArr[1].intValue());
                itemViewHolder.setDescription(numArr[2].intValue());
                setTouchArea(itemViewHolder.itemView.findViewById(R.id.container), i2);
            }
        }

        public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return ItemViewHolder.create(viewGroup.getContext(), getItemLayoutId());
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mDescriptionView;
        ImageView mIconView;
        TextView mTitleView;

        private ItemViewHolder(View view) {
            super(view);
            bindView(view);
        }

        private void bindView(View view) {
            this.mIconView = (ImageView) view.findViewById(R.id.icon);
            this.mTitleView = (TextView) view.findViewById(R.id.title);
            this.mDescriptionView = (TextView) view.findViewById(R.id.description);
        }

        /* access modifiers changed from: private */
        public static ItemViewHolder create(Context context, int i2) {
            return new ItemViewHolder(LayoutInflater.from(context).inflate(i2, (ViewGroup) null, false));
        }

        /* access modifiers changed from: private */
        public void setDescription(int i2) {
            if (i2 > 0) {
                this.mDescriptionView.setText(i2);
            } else {
                ViewUtils.setVisibility(this.mDescriptionView, 8);
            }
        }

        /* access modifiers changed from: private */
        public void setIcon(int i2) {
            if (i2 > 0) {
                this.mIconView.setImageResource(i2);
            } else {
                ViewUtils.setVisibility(this.mIconView, 8);
            }
        }

        /* access modifiers changed from: private */
        public void setTitle(int i2) {
            if (i2 > 0) {
                this.mTitleView.setText(i2);
            } else {
                ViewUtils.setVisibility(this.mTitleView, 8);
            }
        }
    }

    private View createDialogLayout() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_chooser_dialog, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.custom_chooser_list);
        BaseAdapter baseAdapter = new BaseAdapter(loadItems());
        baseAdapter.setOnClickListener(new C0496a(10, this));
        recyclerView.setLayoutManager(new GridLayoutManager(inflate.getContext(), baseAdapter.getGridSize()));
        recyclerView.setAdapter(baseAdapter);
        return inflate;
    }

    /* access modifiers changed from: private */
    public void itemClicked(View view) {
        Integer num = (Integer) view.getTag();
        num.intValue();
        getBlackboard().post("data://user/dialog/CustomChooser", num);
        dismiss();
    }

    private String[] loadItems() {
        String str;
        Bundle arguments = getArguments();
        if (arguments != null) {
            str = arguments.getString("list_chooser_items");
        } else {
            str = null;
        }
        if (str != null) {
            return str.split(";");
        }
        return new String[0];
    }

    private int loadTitle() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return Integer.parseInt(arguments.getString("title", "0"));
        }
        return 0;
    }

    public Dialog createDialog(Bundle bundle) {
        return new AlertDialog.Builder(getActivity()).setView(createDialogLayout()).setTitle(loadTitle()).create();
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/CustomChooser", (Object) null);
    }
}
