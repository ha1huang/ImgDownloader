package com.example.http;

import java.io.IOException;

import okhttp3.Response;

public interface HttpHandler<T> {
     T handleGetResponse( Response response) throws IOException;
//     T handleGetError( Response response) throws IOException;
}
