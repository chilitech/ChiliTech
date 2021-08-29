package com.chilitech.navigation;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class FocusAdapter extends RecyclerView.Adapter<FocusAdapter.MyViewHolder> {
    Bean bean;
    private Context context;
    private OnFocusListener onFocusListener;
    private Handler handler;
    private boolean hasRed = false;

    public OnFocusListener getOnFocusListener() {
        return onFocusListener;
    }

    public void setOnFocusListener(OnFocusListener onFocusListener) {
        this.onFocusListener = onFocusListener;
    }

    public FocusAdapter(Context context, Bean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_focus, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(bean.list.get(position));
        holder.tv.setEnabled(false);
        holder.tv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                onFocusListener.onFocusChange(position);
                if (hasFocus) {
                    Log.e("wangxiaoqi", "红");
                    holder.tv.setTextColor(ContextCompat.getColor(context, R.color.design_default_color_error));

                    //TODO Kael 完美
                    hasRed = true;
                } else {


                    //TODO Kael 完美
                    hasRed = false;
                    Log.e("wangxiaoqi", "黑");
                    if (handler == null) {
                        handler = new Handler();
                    }
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (hasRed) {
                                holder.tv.setTextColor(ContextCompat.getColor(context, R.color.cardview_dark_background));
                                hasRed = false;
                            } else {
                            }
                            handler.removeCallbacksAndMessages(null);
                        }
                    }, 100);
                }
            }
        });
    }

    public interface OnFocusListener {
        void onFocusChange(int position);
    }

    @Override
    public int getItemCount() {
        return bean.list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }
}
