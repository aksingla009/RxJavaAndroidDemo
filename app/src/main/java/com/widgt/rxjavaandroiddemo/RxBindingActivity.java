package com.widgt.rxjavaandroiddemo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxCompoundButton;
import com.jakewharton.rxbinding3.widget.RxTextView;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Unit;

public class RxBindingActivity extends AppCompatActivity {

    private EditText inputEditText;
    private TextView viewText;
    private Button clearButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEditText = findViewById(R.id.editText);
        clearButton = findViewById(R.id.button);
        viewText = findViewById(R.id.greetTV);

        /*inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewText.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.setText("");
                viewText.setText("");
            }
        });*/

        //Writing the same code using RxJava using RxBinding

        Disposable disposable = RxTextView.textChanges(inputEditText).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                viewText.setText(charSequence);
            }
        });

        Disposable disposable1 = RxView.clicks(clearButton).subscribe(new Consumer<Unit>() {
            @Override
            public void accept(Unit unit) throws Exception {
                inputEditText.setText("");
                viewText.setText("");
            }
        });
    }
}
