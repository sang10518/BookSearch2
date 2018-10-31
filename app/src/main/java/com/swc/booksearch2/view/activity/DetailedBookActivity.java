package com.swc.booksearch2.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.swc.booksearch2.R;
import com.swc.booksearch2.model.DetailedBook;
import com.swc.booksearch2.util.GetDataService;
import com.swc.booksearch2.util.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedBookActivity extends AppCompatActivity{

    ProgressDialog progressDialog;
    private TextView mTvTitle, mTvAuthors, mTvDesc, mTvPrice;
    private ImageView mIvBookCover;
    private TextView mTvSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String query = getIntent().getStringExtra("BOOK_ISBN");

        getBookDetail(query);

    }

    private void bindViews() {
        mTvTitle = findViewById(R.id.tvTitle);
        mTvAuthors = findViewById(R.id.tvAuthors);
        mTvDesc = findViewById(R.id.tvDesc);
        mTvPrice = findViewById(R.id.tvPrice);
        mTvSubtitle = findViewById(R.id.tvSubtitle);
        mIvBookCover = findViewById(R.id.ivBookCover);
    }

    private void getBookDetail(String query) {
        Log.e("adsf", "getBookDetail: " + query);
        progressDialog = new ProgressDialog(DetailedBookActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitInstance.getInstance().create(GetDataService.class);

        String keyword = query;
        Call<DetailedBook> call = service.getBookDetail(keyword);
        call.enqueue(new Callback<DetailedBook>() {
            @Override
            public void onResponse(Call<DetailedBook> call, Response<DetailedBook> response) {
                progressDialog.dismiss();
                setContentView(R.layout.activity_detailed_book);
                bindViews();
                updateView(response.body());
            }

            @Override
            public void onFailure(Call<DetailedBook> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("asdf", t.getMessage());
                Toast.makeText(DetailedBookActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateView(DetailedBook book) {
        mTvAuthors.setText(book.getAuthors());
        mTvDesc.setText(book.getDesc());
        mTvPrice.setText(book.getPrice());
        mTvTitle.setText(book.getTitle());
        mTvSubtitle.setText(book.getSubtitle());

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        builder.build().load(book.getImage())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(mIvBookCover);
    }
}
