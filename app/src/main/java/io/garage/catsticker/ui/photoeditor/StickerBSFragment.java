package io.garage.catsticker.ui.photoeditor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import io.garage.catsticker.R;
import io.garage.catsticker.ui.dialogs.GoPremiumDialog;

public class StickerBSFragment extends BottomSheetDialogFragment {

    public StickerBSFragment() {
        // Required empty public constructor
    }

    private StickerListener mStickerListener;

    public void setStickerListener(StickerListener stickerListener) {
        mStickerListener = stickerListener;
    }

    public interface StickerListener {
        void onStickerClick(Bitmap bitmap);
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sticker_emoji_dialog, null);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        RecyclerView rvEmoji = contentView.findViewById(R.id.rvEmoji);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        rvEmoji.setLayoutManager(gridLayoutManager);
        StickerAdapter stickerAdapter = new StickerAdapter();
        rvEmoji.setAdapter(stickerAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.ViewHolder> {

        StickerModel[] stickerDataset = new StickerModel[]{
                new StickerModel(R.drawable.aa, false),
                new StickerModel(R.drawable.aa, false),
                new StickerModel(R.drawable.bb, true),
                new StickerModel(R.drawable.bb, true),
        };

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sticker, parent, false);
            return new ViewHolder(view, getContext());
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bindViewWithData(stickerDataset[position]);
        }

        @Override
        public int getItemCount() {
            return stickerDataset.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgSticker;
            ImageView premiumContentWatermark;
            Context context;
            ViewHolder(View itemView, Context context) {
                super(itemView);
                this.context = context;
                imgSticker = itemView.findViewById(R.id.imgSticker);
                premiumContentWatermark = itemView.findViewById(R.id.premiumContentWatermark);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mStickerListener != null) {
                            mStickerListener.onStickerClick(
                                    BitmapFactory.decodeResource(getResources(),
                                            stickerDataset[getLayoutPosition()].resource));
                        }
                        dismiss();
                    }
                });
            }

            public void bindViewWithData(StickerModel data){
                imgSticker.setImageResource(data.resource);
                if (data.isPremium)
                {
                    premiumContentWatermark.setVisibility(View.INVISIBLE);
                }else {
                    premiumContentWatermark.setVisibility(View.VISIBLE);
                    premiumContentWatermark.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Dialog goPremiumDialog = new GoPremiumDialog(context);
                            goPremiumDialog.show();
                        }
                    });
                }
            }
        }
    }

    private String convertEmoji(String emoji) {
        String returnedEmoji = "";
        try {
            int convertEmojiToInt = Integer.parseInt(emoji.substring(2), 16);
            returnedEmoji = getEmojiByUnicode(convertEmojiToInt);
        } catch (NumberFormatException e) {
            returnedEmoji = "";
        }
        return returnedEmoji;
    }

    private String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }
}

class StickerModel{
    int resource = R.drawable.got;
    boolean isPremium = false;

    StickerModel(int resource , boolean isPremium ){
        this.resource = resource;
        this.isPremium = isPremium;
    }
}