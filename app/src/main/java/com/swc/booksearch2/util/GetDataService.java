package com.swc.booksearch2.util;


import com.swc.booksearch2.model.DetailedBook;
import com.swc.booksearch2.model.RawResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("search/{keyword}/{page}")
    Call<RawResponse> getAllResponse(@Path("keyword") String keyword, @Path("page") int page);

    @GET("books/{keyword}")
    Call<DetailedBook> getBookDetail(@Path("keyword") String keyword);
}

