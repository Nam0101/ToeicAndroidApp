package com.example.englishapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.Activity.ChonPartActivity;
import com.example.englishapp.InterFace.ItemClickListener;
import com.example.englishapp.Model.DeThi;
import com.example.englishapp.R;

import java.util.List;

public class DeThiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<DeThi> array;
    private static final int VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    public DeThiAdapter(Context context, List<DeThi> array) {
        this.context = context;
        this.array = array;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DATA){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dethi,parent,false);
            return new MyViewHoder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
            return new LoadingViewHoder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHoder){
            MyViewHoder myViewHoder = (MyViewHoder) holder;
            DeThi deThi = array.get(position);
            myViewHoder.tenDethi.setText(deThi.getTendethi());
            myViewHoder.boDethi.setText("Bộ đề thi : 20"+ deThi.getNam());
            myViewHoder.thoigian.setText("Thời gian : 120p");
            myViewHoder.phanthi.setText("7 phần thi");
            myViewHoder.socau.setText("200 câu hỏi ");
            myViewHoder.btnChitiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChonPartActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
            myViewHoder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if(!isLongClick){
                        Intent intent = new Intent(context, ChonPartActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }else {
            LoadingViewHoder loadingViewHoder = (LoadingViewHoder) holder;
            loadingViewHoder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return array.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_DATA; // CÁI NÀY CÓ NGHĨA LÀ NẾU CÁI VỊ TRÍ MÀ XUẤT RA NULL THÌ SẼ DÙNG LOADING CÒN NẾU CÓ DÂTA THÌ CỨ XUẤT RA DATA THÔI

    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class LoadingViewHoder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public LoadingViewHoder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);

        }
    }

    public class MyViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tenDethi,boDethi,thoigian,phanthi,socau;
        AppCompatButton btnChitiet;
        private ItemClickListener itemClickListener;
        public MyViewHoder(@NonNull View itemView) {
            super(itemView);

            btnChitiet = itemView.findViewById(R.id.btnChitietdethi);

            tenDethi = itemView.findViewById(R.id.txtTende);
            boDethi = itemView.findViewById(R.id.txtBodethi);
            thoigian = itemView.findViewById(R.id.txtThoigianDethi);
            phanthi = itemView.findViewById(R.id.txtPhanthi);
            socau = itemView.findViewById(R.id.txtSocau);
            itemView.setOnClickListener(this);


        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);

        }
    }
}
