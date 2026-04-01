package com.samsung.android.gallery.app.ui.list.albums.virtual;

import O3.l;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RepairPicturesFragment extends VirtualAlbumPicturesFragment<IPicturesView, RepairPicturesPresenter> {
    RecyclerView mSuggestedList;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FilterAdapter extends RecyclerView.Adapter<KeywordViewHolder> {
        private final Blackboard mBlackboard;
        private final List<String> mEntry;
        private int mSelectedIndex;

        public FilterAdapter(Blackboard blackboard, List<String> list, int i2) {
            this.mBlackboard = blackboard;
            this.mEntry = list;
            this.mSelectedIndex = i2;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onCreateViewHolder$0(KeywordViewHolder keywordViewHolder, KeywordViewHolder keywordViewHolder2) {
            String str;
            int adapterPosition = keywordViewHolder.getAdapterPosition();
            int i2 = this.mSelectedIndex;
            if (adapterPosition != i2) {
                this.mSelectedIndex = adapterPosition;
                notifyItemChanged(i2);
                notifyItemChanged(adapterPosition);
                Log.i("RepairPicturesFragment", "onClick {" + adapterPosition + GlobalPostProcInternalPPInterface.SPLIT_REGEX + keywordViewHolder2.mKeyword + "}");
                Blackboard blackboard = this.mBlackboard;
                if (adapterPosition == 0) {
                    str = "all";
                } else {
                    str = keywordViewHolder2.mKeyword;
                }
                blackboard.publish("repair-data-type", str);
                this.mBlackboard.erase(DataKey.DATA_CURSOR("location://virtual/album/repair/fileList"));
                this.mBlackboard.publish(CommandKey.DATA_REQUEST("location://virtual/album/repair/fileList"), (Object) null);
            }
        }

        public int getItemCount() {
            return this.mEntry.size();
        }

        public void onBindViewHolder(KeywordViewHolder keywordViewHolder, int i2) {
            keywordViewHolder.bind(this.mEntry.get(i2), this.mSelectedIndex == i2);
        }

        public KeywordViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            KeywordViewHolder create = KeywordViewHolder.create(viewGroup, i2);
            create.setOnClickListener(new b(this, create));
            return create;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class KeywordViewHolder extends ListViewHolder {
        private ViewGroup mButton;
        /* access modifiers changed from: private */
        public String mKeyword;
        private Consumer<KeywordViewHolder> mListener;
        private TextView mTitleView;

        public KeywordViewHolder(View view, int i2) {
            super(view, i2);
        }

        public static KeywordViewHolder create(ViewGroup viewGroup, int i2) {
            return new KeywordViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_search_filters, viewGroup, false), i2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$bindView$0(View view) {
            this.mListener.accept(this);
        }

        public void bind(String str, boolean z) {
            this.mKeyword = str;
            this.mTitleView.setText(str);
            this.mButton.setSelected(z);
        }

        public final void bindView(View view) {
            ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.container);
            this.mButton = viewGroup;
            viewGroup.setOnClickListener(new c(this));
            this.mTitleView = (TextView) view.findViewById(R.id.title);
            view.findViewById(R.id.type_icon).setVisibility(8);
        }

        public void setOnClickListener(Consumer<KeywordViewHolder> consumer) {
            this.mListener = consumer;
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mSuggestedList = (RecyclerView) view.findViewById(R.id.recycler_list);
    }

    public int getLayoutId() {
        return R.layout.fragment_repair_layout;
    }

    public String getScreenId() {
        return null;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        Optional.ofNullable(getToolbar()).ifPresent(new l(25));
        this.mSuggestedList.setLayoutManager(new LinearLayoutManager(view.getContext(), 0, false));
        this.mSuggestedList.setAdapter(new FilterAdapter(getBlackboard(), Arrays.asList(new String[]{getString(R.string.all), "invalid time", "wrong width/height", "0-size", "no date/time", "no location"}), 1));
    }

    public boolean supportTimeline() {
        return true;
    }

    public RepairPicturesPresenter createPresenter(IPicturesView iPicturesView) {
        return new RepairPicturesPresenter(this.mBlackboard, iPicturesView);
    }
}
