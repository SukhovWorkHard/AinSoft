package com.sukhov.android.ainsoft;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Sukhov on 24.01.2018.
 */

/**
 * Описание Анотации, интерфейс необходим для работы с Retrofit
 * метод getProducts() - возвращает объект типа <List<Products>>
 */
public interface ProductsApi {
    @GET("test.xml")
    Call<ProductsFromXml> getProducts();
}
