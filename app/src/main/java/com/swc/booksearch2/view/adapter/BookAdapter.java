package com.swc.booksearch2.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.swc.booksearch2.view.activity.DetailedBookActivity;
import com.swc.booksearch2.R;
import com.swc.booksearch2.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.CustomViewHolder> {

    private List<Book> dataList;
    private Context context;

    public BookAdapter(Context context, List<Book> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    public void addBooks(Book b){
        if (dataList != null) dataList.add(b);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView tvTitle;
        TextView tvPrice;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            tvTitle = mView.findViewById(R.id.tvTitle);
            tvPrice = mView.findViewById(R.id.tvPrice);
            coverImage = mView.findViewById(R.id.ivBookCover);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.book_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        holder.tvTitle.setText(dataList.get(position).getTitle());
        holder.tvPrice.setText(dataList.get(position).getPrice());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getImage())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedBookActivity.class);
                intent.putExtra("BOOK_ISBN", dataList.get(position).getIsbn13());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
