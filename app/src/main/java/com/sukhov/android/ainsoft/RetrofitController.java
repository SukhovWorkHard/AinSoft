package com.sukhov.android.ainsoft;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Sukhov on 24.01.2018.
 */

public class RetrofitController {

    static final String BASE_URL = "http://ainsoft.pro/test/";

    /**
     * Метод getApi() - выполняет работу с Retrofit и создает объект productsApi при помощи которого
     * мы будем выполнять запросы на сервер
     *
     * baseUrl() - получает на входе базовую ссылку на расположение файла
     *
     * addConverterFactory(SimpleXmlConverterFactory.create()) - Конвертер данных, в данном случае я
     * использую конвертер для xml-типа данных
     */
    public static ProductsApi getApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        ProductsApi productsApi = retrofit.create(ProductsApi.class);
        return productsApi;
    }
}
