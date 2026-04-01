package com.samsung.android.gallery.app.ui.map.picker;

import Z6.a;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PermissionHelper;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PermissionRationaleDialogMap extends AppCompatDialogFragment {
    private String mFunctionName;
    private ArrayList<String> mPermissionList;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DialogListAdapter extends ArrayAdapter<String> {
        private final ArrayList<PermissionGroupInfo> mPermissionGroupInfo;

        public DialogListAdapter(Context context, ArrayList<String> arrayList) {
            super(context, R.layout.request_permission_rationale_row, arrayList);
            this.mPermissionGroupInfo = initPermissionInfo(context, arrayList);
        }

        private ArrayList<PermissionGroupInfo> initPermissionInfo(Context context, ArrayList<String> arrayList) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                Log.d("DialogListAdapter", "initPermissionInfo(), packageManager is null");
                return new ArrayList<>();
            }
            List<PermissionGroupInfo> allPermissionGroups = packageManager.getAllPermissionGroups(128);
            ArrayList<PermissionGroupInfo> arrayList2 = new ArrayList<>();
            ArrayList arrayList3 = new ArrayList();
            try {
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    PermissionInfo permissionInfo = packageManager.getPermissionInfo(it.next(), 128);
                    if (!arrayList3.contains(permissionInfo.group)) {
                        arrayList3.add(permissionInfo.group);
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.d("DialogListAdapter", "PackageManager NameNotFoundException e=" + e.getMessage());
            }
            if (!arrayList3.isEmpty()) {
                for (PermissionGroupInfo next : allPermissionGroups) {
                    if (arrayList3.contains(next.name)) {
                        arrayList2.add(next);
                    }
                }
            }
            return arrayList2;
        }

        private Drawable loadDrawable(PackageManager packageManager, String str, int i2) {
            if (packageManager == null) {
                return null;
            }
            try {
                return packageManager.getResourcesForApplication(str).getDrawable(i2, (Resources.Theme) null);
            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
                Log.d("DialogListAdapter", "Couldn't get resource e=" + e);
                return null;
            }
        }

        private Drawable loadItemInfoIcon(PackageItemInfo packageItemInfo) {
            if (packageItemInfo == null) {
                Log.d("DialogListAdapter", "loadItemInfoIcon(), PackageItemInfo is null");
                return null;
            } else if (packageItemInfo.icon > 0) {
                return loadDrawable(getContext().getPackageManager(), packageItemInfo.packageName, packageItemInfo.icon);
            } else {
                return null;
            }
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
                Log.d("DialogListAdapter", "getCount() is 0");
                return view;
            } else if (i2 > getCount() || i2 < 0) {
                Log.d("DialogListAdapter", "position() is wrong");
                return view;
            } else {
                PermissionGroupInfo permissionGroupInfo = this.mPermissionGroupInfo.get(i2);
                ImageView imageView = (ImageView) view.findViewById(R.id.request_permission_rationale_image);
                TextView textView = (TextView) view.findViewById(R.id.request_permission_rationale_text);
                Drawable loadItemInfoIcon = loadItemInfoIcon(permissionGroupInfo);
                if (loadItemInfoIcon != null) {
                    imageView.setImageDrawable(loadItemInfoIcon);
                }
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

    private void initListView(View view, ArrayList<String> arrayList) {
        ListView listView = (ListView) view.findViewById(R.id.request_permission_rationale_list);
        listView.setAdapter(new DialogListAdapter(getContext(), arrayList));
        listView.setDivider((Drawable) null);
        listView.setFocusable(false);
    }

    private void initPopupText(View view) {
        TextView textView = (TextView) view.findViewById(R.id.request_permission_rationale_title);
        String str = this.mFunctionName;
        if (str == null) {
            str = getResources().getString(R.string.app_name);
        }
        String naturalizeText = SeApiCompat.naturalizeText(getResources().getString(R.string.permission_rationale_dialog_description, new Object[]{str}));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(naturalizeText);
        spannableStringBuilder.setSpan(new StyleSpan(1), naturalizeText.indexOf(str), str.length() + naturalizeText.indexOf(str), 33);
        textView.setText(spannableStringBuilder);
    }

    private void loadArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            int i2 = arguments.getInt("function", -1);
            if (i2 > 0) {
                this.mFunctionName = getString(i2);
            }
            this.mPermissionList = new ArrayList<>();
            int parseInt = Integer.parseInt(arguments.getString("size"));
            for (int i7 = 0; i7 < parseInt; i7++) {
                ArrayList<String> arrayList = this.mPermissionList;
                arrayList.add(arguments.getString("request" + i7));
            }
        }
    }

    /* access modifiers changed from: private */
    public void onClickNegative(DialogInterface dialogInterface, int i2) {
        dismiss();
    }

    /* access modifiers changed from: private */
    public void onClickPositive(DialogInterface dialogInterface, int i2) {
        PermissionHelper.launchApplicationDetailSettings(getContext());
        dismiss();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        loadArguments();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.request_permission_rationale_popup, (ViewGroup) null);
        initListView(inflate, this.mPermissionList);
        initPopupText(inflate);
        return new AlertDialog.Builder(getContext()).setView(inflate).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new a(this, 0)).setPositiveButton((int) R.string.settings, (DialogInterface.OnClickListener) new a(this, 1)).setCancelable(false).create();
    }

    public void onResume() {
        super.onResume();
        Log.d("PermissionRatDlgMap", "onResume");
        setCancelable(false);
    }
}
