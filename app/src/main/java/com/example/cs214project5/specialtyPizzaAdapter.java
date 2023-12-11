package com.example.cs214project5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;


public class specialtyPizzaAdapter extends RecyclerView.Adapter<specialtyPizzaAdapter.specialtyViewHolder>{

    String data1[], data2[], data3[];
    int images[];
    Context context;
    /**
     * OnItemClickListener: method that defines custom listener for handling clicking inputs in the recyclerView.
     */
    private OnItemClickListener onItemClickListener;

    /**
     * OnItemClickListener: determines interface for click listener.
     */
    public interface OnItemClickListener {
        /**
         * onItemClick: defines behaviour on itemClick
         * @param specialtyPizza on item click, returns name of specialty pizza.
         */
         void onItemClick(String specialtyPizza);
    }

    /**
     * specialtyPizzaAdapter: Construct that initializes various arrays that represent images, toppinglists, prices, as well as its
     * respective listener and context.
     * @param ct
     * @param s1
     * @param s2
     * @param s3
     * @param img
     * @param onItemClickListener
     */
    public specialtyPizzaAdapter(Context ct, String s1[], String s2[], String s3[], int img[], OnItemClickListener onItemClickListener){
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        images = img;
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * specialtyViewHolder Override: determines specific layout for View.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @NotNull
    @Override
    public specialtyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_specialty_pizza, parent, false);
        return new specialtyViewHolder(view);
    }

    /**
     * onBindViewHolder Override: Determines how items will be displayed within the View.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull specialtyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameTextView.setText(data1[position]);
        holder.toppingsTextView.setText(data2[position]);
        holder.prices.setText(data3[position]);
        holder.imageView.setImageResource(images[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick: handles clicking on an object within the view.
             * @param view
             */
            @Override
            public void onClick(View view) {
                if (onItemClickListener  != null) {
                    onItemClickListener.onItemClick(data1[position]);
                }
            }
        });

    }

    /**
     * Returns amount of images.
     * @return images.length
     */
    @Override
    public int getItemCount() {
        return images.length;
    }

    /**
     * specialtyViewHolder class: responsible for creating View within recycleView.
     */
    public class specialtyViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView, toppingsTextView, prices;
        ImageView imageView;
        public specialtyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            toppingsTextView = itemView.findViewById(R.id.toppingsTextView);
            prices = itemView.findViewById(R.id.prices);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
