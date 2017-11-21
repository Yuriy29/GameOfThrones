package gameofthrones.yuriydopa.com.gameofthrones.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gameofthrones.yuriydopa.com.gameofthrones.R;
import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.CharacterEntity;
import gameofthrones.yuriydopa.com.gameofthrones.utils.CircleTransformation;
import gameofthrones.yuriydopa.com.gameofthrones.utils.ConstsManager;

/**
 * Created by yuriy on 20.02.17.
 */

public class CharactersPageAdapter extends RecyclerView.Adapter<CharactersPageAdapter.CharactersPageHolder> {

    private Picasso mPicasso;
    private Context mContext;
    private List<CharacterEntity> mData;
    private OnItemClickListener mOnItemClickListener;

    public CharactersPageAdapter(Picasso picasso) {
        mPicasso = picasso;
        mData = new ArrayList<>();
    }

    @Override
    public CharactersPageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_characters_list, parent, false);
        return new CharactersPageHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(CharactersPageHolder holder, int position) {
        CharacterEntity characterEntity = mData.get(position);

        holder.mMemberName.setText(characterEntity.getName());

        int avatar = 0;
        switch (characterEntity.getRemoteHouseId()) {
            case ConstsManager.STARK_ID_HOUSE:
                avatar = R.mipmap.ic_stark;
                break;
            case ConstsManager.LANNISTER_ID_HOUSE:
                avatar = R.mipmap.ic_lanister;
                break;
            case ConstsManager.TARGARYEN_ID_HOUSE:
                avatar = R.mipmap.ic_targaryen;
                break;
        }

        mPicasso.load(avatar)
                .resizeDimen(R.dimen.size_avatar, R.dimen.size_avatar)
                .onlyScaleDown()
                .centerCrop()
                .transform(new CircleTransformation())
                .into(holder.mAvatar);

    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class CharactersPageHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.avatar) ImageView mAvatar;
        @BindView(R.id.text) TextView mMemberName;

        private OnItemClickListener mOnItemClickListener;

        public CharactersPageHolder(View itemView, OnItemClickListener listener) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                mOnItemClickListener = listener;
                itemView.setOnClickListener(this);
            }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    public void setData(@NonNull List<CharacterEntity> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    public List<CharacterEntity> getData() {
        return mData;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
