package room.vn.roomwithrxjava2.ui.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import room.vn.roomwithrxjava2.R;
import room.vn.roomwithrxjava2.room.model.Room_User;

/**
 * Created by jackyhieu1211 on 7/12/17.
 */

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Room_User> room_users;

    public TestAdapter(List<Room_User> room_users, OnItemClickListener onItemClickListener) {
        this.room_users = room_users;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_user, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bind(room_users.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return room_users.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_avatar)
        AppCompatImageView imgAvatar;
        @BindView(R.id.tv_login)
        AppCompatTextView tvLogin;
        @BindView(R.id.tv_id)
        AppCompatTextView tvId;
        @BindView(R.id.layout_item)
        RelativeLayout layoutItem;

        public ItemViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        public void bind(Room_User roomUser) {
            Glide.with(itemView.getContext()).load(roomUser.avatarUrl).into(imgAvatar);
            tvLogin.setText(roomUser.login);
            tvId.setText(String.valueOf(roomUser.id_user));
            layoutItem.setOnClickListener(v -> onItemClickListener.onItemClick(roomUser));
        }
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Room_User roomUser);
    }
}