package com.demo.warehouse;

import com.demo.warehouse.catalog.Product;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ReviewServiceClient {

    private final String host;
    private final int port;
    private Gson gson;
    private final OkHttpClient client = new OkHttpClient();

    public ReviewServiceClient(String host, int port, Gson gson) {
        this.host = host;
        this.port = port;
        this.gson = gson;
    }

    public List<Review> getForProduct(Product product) {
        Request request = new Request.Builder()
                .url(String.format("http://%s:%s/review/%s", this.host, this.port, product.getId()))
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            Review[] reviews = this.gson.fromJson(json, Review[].class);
            return Arrays.asList(reviews);
        } catch (IOException e) {
            return null;
        }
    }
}
