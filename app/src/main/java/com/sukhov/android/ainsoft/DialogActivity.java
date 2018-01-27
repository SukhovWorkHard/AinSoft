package com.sukhov.android.ainsoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Решил попробовать Activity вместо Alertdialog
 */
public class DialogActivity extends Activity {

    private Button btnOk;
    private Button btnCancel;
    private EditText etPrice;
    private String oldprice;
    private int position;
    private SQLiteConnector mSQLiteConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        mSQLiteConnector = new SQLiteConnector(this);
        final Intent intent = getIntent();
        oldprice = intent.getStringExtra("old_price");
        position = intent.getIntExtra("position", 0);
        position+=1;

        etPrice = findViewById(R.id.et_dialog_price);
        etPrice.setText(oldprice);

        btnOk = findViewById(R.id.btn_dialog_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newprice = etPrice.getText().toString();
                mSQLiteConnector.updatePrice(position, getBaseContext(), newprice);
                finish();
            }
        });

        btnCancel = findViewById(R.id.btn_dialog_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
