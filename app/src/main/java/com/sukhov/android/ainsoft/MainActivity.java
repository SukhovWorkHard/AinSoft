package com.sukhov.android.ainsoft;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "AINSOFT_TAG";
    private Button btnDownload;
    private Button btnList;
    private static ProductsApi sProductsApi;
    private SQLiteConnector mSQLiteConnector;
    private SQLiteDatabase mSQLiteDatabase;
    private List<Products> mList;
    private ArrayList<Products> mProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sProductsApi = RetrofitController.getApi();

        mSQLiteConnector = new SQLiteConnector(this);
        mSQLiteDatabase = mSQLiteConnector.getWritableDatabase();

        btnList = (Button) findViewById(R.id.btn_list);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
                finish();
            }
        });


        btnDownload = (Button) findViewById(R.id.btn_download);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Скачивание + парсинг XML-файла по ссылке  http://ainsoft.pro/test/test.xml
                 */
                Call<ProductsFromXml> call = sProductsApi.getProducts();
                call.enqueue(new Callback<ProductsFromXml>() {
                    @Override
                    public void onResponse(Call<ProductsFromXml> call, Response<ProductsFromXml> response) {
                        if (response.isSuccessful()) {
                            mList = response.body().products;
                            mProducts = new ArrayList<>(mList);
                            Log.d(LOG_TAG, "RESPONSE IS SUCCESSFUL!");


                            /**
                             * Передача информации в БД
                             */

                            ContentValues values = new ContentValues();
                            try {
                                for (int i = 0; i < mProducts.get(0).product.size(); i++) {
                                    values.put("_id", mProducts.get(0).product.get(i).getId());
                                    values.put("name", mProducts.get(0).product.get(i).getName());
                                    values.put("price", mProducts.get(0).product.get(i).getPrice());
                                    long newRowId = mSQLiteDatabase.insert("Products", null, values);
                                }
                                Log.d(LOG_TAG, "DATA SAVED IS SUCCESSFUL!");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(getBaseContext(), "DATA SAVED IS SUCCESSFUL!", Toast.LENGTH_SHORT).show();

                            //int rowsDeleted = mSQLiteDatabase.delete("Products", null, null);

                        } else {
                            Log.d(LOG_TAG, "RESPONSE CODE " + response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<ProductsFromXml> call, Throwable t) {
                        Log.d(LOG_TAG, "FAILURE " + t);
                    }
                });
            }
        });

    }
}
