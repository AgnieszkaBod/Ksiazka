package com.example.ksiazka;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PrzepisAdapter extends RecyclerView.Adapter<PrzepisAdapter.ViewHolder> implements Filterable {

    private List<String> mData;
    private List<String> mDataWhole;
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    PrzepisAdapter(Context context, List<String> data) {
        Log.i("Constructor", data.toString());
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        mDataWhole = new ArrayList<>(data);
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.single_przepis, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String przepis = mData.get(position);
        holder.myTextView.setText(przepis);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * <p>Returns a filter that can be used to constrain data with a filtering
     * pattern.</p>
     *
     * <p>This method is usually implemented by {@link Adapter}
     * classes.</p>
     *
     * @return a filter used to constrain data
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults filtred = new FilterResults();
                List<String> results = new ArrayList<>();
                constraint = constraint.toString().toLowerCase(Locale.ROOT);
                if (constraint.length() > 0) {
                    if (mData != null && mData.size() > 0) {
                        for (final String e : mData) {
                            if (e.toLowerCase(Locale.ROOT).contains(constraint)) {
                                results.add(e);
                            }
                            Log.e("wole0", mDataWhole.toString());
                        }
                    }
                    filtred.values = results;
                    filtred.count = results.size();
                }
                else {
                    filtred.values = new ArrayList<String>(mDataWhole);
                    filtred.count = mDataWhole.size();
                }
                return filtred;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (constraint.length() > 0 && results.count > 0){
                    mData.clear();
                    mData.addAll((ArrayList<String>) results.values);
                }
                else{
                    mData = new ArrayList<>(mDataWhole);
                }
                notifyDataSetChanged();
            }
        };
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.nazwa_przepis);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

