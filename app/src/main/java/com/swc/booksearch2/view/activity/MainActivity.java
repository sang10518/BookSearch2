package com.swc.booksearch2.view.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.swc.booksearch2.util.GetDataService;
import com.swc.booksearch2.R;
import com.swc.booksearch2.model.RawResponse;
import com.swc.booksearch2.util.RetrofitInstance;
import com.swc.booksearch2.model.Book;
import com.swc.booksearch2.view.adapter.BookAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private BookAdapter mBookAdapter;
    private RecyclerView mRecyclerView;
    ProgressDialog progressDialog;
    SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchView = findViewById(R.id.search);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getBooks(query);
                mSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    private void getBooks(String query) {
        Log.e("adsf", "getBooks: " + query);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitInstance.getInstance().create(GetDataService.class);

        String keyword = query;
        Call<RawResponse> call = service.getAllResponse(keyword);
        call.enqueue(new Callback<RawResponse>() {
            @Override
            public void onResponse(Call<RawResponse> call, Response<RawResponse> response) {
                progressDialog.dismiss();
                generateDataList(response.body().getBooks());
                for (Book b:  response.body().getBooks()){
                    Log.e("asdf", b.getTitle());
                }


            }

            @Override
            public void onFailure(Call<RawResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("asdf", t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Book> bookList) {
        mRecyclerView = findViewById(R.id.rvBooks);
        LinearLayout llNoBooks = findViewById(R.id.llNoBooks);

        if (bookList.size() ==0){
            mRecyclerView.setVisibility(View.GONE);
            llNoBooks.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            llNoBooks.setVisibility(View.GONE);

            mBookAdapter = new BookAdapter(this,bookList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mBookAdapter);
        }


    }
}
