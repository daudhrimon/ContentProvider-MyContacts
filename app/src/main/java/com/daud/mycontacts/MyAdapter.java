package com.daud.mycontacts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;
import java.util.jar.Attributes;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHoldeer> {
    private Context context;
    private List<String> Name;
    private List<String> Number;

    public MyAdapter(Context context, List<String> Name, List<String> Number) {
        this.context = context;
        this.Name = Name;
        this.Number = Number;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHoldeer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_layout,parent,false);
        return new MyViewHoldeer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHoldeer holder, int position) {
      holder.NameVH.setText(String.format(Name.get(position)));
      holder.itemView.setClickable(true);
      holder.itemView.setOnClickListener(view -> {
          Intent intent=new Intent(context,ContactActivity.class);
          intent.putExtra("RvName",Name.get(position));
          intent.putExtra("RvNumber",Number.get(position));
          context.startActivity(intent);
      });
    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public class MyViewHoldeer extends RecyclerView.ViewHolder {
        private TextView NameVH;
        public MyViewHoldeer(@NonNull View itemView) {
            super(itemView);
            NameVH=itemView.findViewById(R.id.NameVH);
            context = itemView.getContext();
            
        }

    }
}
