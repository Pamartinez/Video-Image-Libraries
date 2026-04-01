package com.samsung.android.gallery.app.ui.dialog.permission;

import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RuntimePermissionUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PermissionRationaleDialogAdapter extends ArrayAdapter<String> {
    private final ArrayList<PermissionGroupInfo> mPermissionGroupInfo;

    public PermissionRationaleDialogAdapter(Context context, ArrayList<String> arrayList) {
        super(context, R.layout.request_permission_rationale_row, arrayList);
        this.mPermissionGroupInfo = getPermissionGroupInfo(context, arrayList);
    }

    private ArrayList<PermissionGroupInfo> getPermissionGroupInfo(Context context, ArrayList<String> arrayList) {
        PackageManager packageManager = getContext().getPackageManager();
        if (packageManager == null) {
            Log.e("PermissionRDlgAdapter", "initPermissionInfo(), packageManager is null");
            return new ArrayList<>();
        }
        List<PermissionGroupInfo> allPermissionGroups = packageManager.getAllPermissionGroups(128);
        ArrayList arrayList2 = new ArrayList();
        try {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                String permissionGroup = RuntimePermissionUtil.getPermissionGroup(packageManager.getPermissionInfo(it.next(), 128).name);
                if (!arrayList2.contains(permissionGroup)) {
                    arrayList2.add(permissionGroup);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("PermissionRDlgAdapter", "PackageManager NameNotFoundException e=" + e.getMessage());
        }
        ArrayList<PermissionGroupInfo> arrayList3 = new ArrayList<>();
        if (!arrayList2.isEmpty()) {
            for (PermissionGroupInfo next : allPermissionGroups) {
                if (arrayList2.contains(next.name)) {
                    arrayList3.add(next);
                }
            }
        }
        return arrayList3;
    }

    private Drawable loadDrawable(PackageManager packageManager, String str, int i2) {
        if (packageManager != null) {
            try {
                return packageManager.getResourcesForApplication(str).getDrawable(i2, (Resources.Theme) null);
            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
                Log.d("PermissionRDlgAdapter", "Couldn't get resource e=" + e);
            }
        }
        return null;
    }

    private Drawable loadItemInfoIcon(PackageItemInfo packageItemInfo) {
        if (packageItemInfo == null || packageItemInfo.icon <= 0) {
            return null;
        }
        return loadDrawable(getContext().getPackageManager(), packageItemInfo.packageName, packageItemInfo.icon);
    }

    public int getCount() {
        return this.mPermissionGroupInfo.size();
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        CharSequence charSequence;
        if (view == null) {
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.request_permission_rationale_popup_listview_row_text_bottom_padding);
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.request_permission_rationale_row, viewGroup, false);
            inflate.setPadding(0, 0, 0, dimensionPixelSize);
            view = inflate;
        }
        if (getCount() == 0) {
            Log.d("PermissionRDlgAdapter", "getCount() is 0");
            return view;
        } else if (i2 > getCount() || i2 < 0) {
            Log.d("PermissionRDlgAdapter", "position() is wrong");
            return view;
        } else {
            PermissionGroupInfo permissionGroupInfo = this.mPermissionGroupInfo.get(i2);
            TextView textView = (TextView) view.findViewById(R.id.request_permission_rationale_text);
            ((ImageView) view.findViewById(R.id.request_permission_rationale_image)).setImageDrawable(loadItemInfoIcon(permissionGroupInfo));
            if (permissionGroupInfo == null) {
                charSequence = "";
            } else {
                charSequence = permissionGroupInfo.loadLabel(getContext().getPackageManager());
            }
            textView.setText(charSequence);
            return view;
        }
    }
}
