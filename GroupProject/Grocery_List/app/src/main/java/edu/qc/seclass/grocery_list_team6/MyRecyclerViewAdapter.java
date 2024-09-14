package edu.qc.seclass.grocery_list_team6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Grocery> mData;
    private LayoutInflater mInflater;

    public Button deleteButton;
    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<Grocery> list) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = list;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mData.get(position).getName();
        String quantity = mData.get(position).getQty();
        boolean checked = mData.get(position).getChecked();
        holder.ch1.setChecked(checked);
        holder.myTextView.setText(name);
        holder.quantityView.setText(quantity);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        CheckBox ch1;
        TextView quantityView;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.name);
            quantityView = itemView.findViewById(R.id.quantity);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(this);
            ch1 = itemView.findViewById(R.id.checkBox);
            ch1.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (view.getId() == R.id.checkBox) {
                // CheckBox clicked
                boolean checked = ch1.isChecked();
                mData.get(position).setChecked(checked);

            } else if (view.getId() == R.id.deleteButton) {
                // Delete button clicked
                mData.remove(position);
                notifyItemRemoved(position);
            }


    }

}
}