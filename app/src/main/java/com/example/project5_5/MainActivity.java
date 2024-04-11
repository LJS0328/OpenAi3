package com.example.project5_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etNum1, etNum2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView tvResult;
    Button[] btnNums = new Button[10];
    int[] btnNumsIds = {
            R.id.btnNum0, R.id.btnNum1, R.id.btnNum2, R.id.btnNum3, R.id.btnNum4,
            R.id.btnNum5, R.id.btnNum6, R.id.btnNum7, R.id.btnNum8, R.id.btnNum9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        tvResult = findViewById(R.id.tvResult);

        // 숫자 버튼에 대한 클릭 리스너 설정
        for(int i = 0; i < btnNumsIds.length; i++) {
            btnNums[i] = findViewById(btnNumsIds[i]);
            setNumberClickListener(btnNums[i]);
        }

        // 연산자 버튼에 대한 클릭 리스너 설정
        setOperatorClickListener(btnAdd);
        setOperatorClickListener(btnSub);
        setOperatorClickListener(btnMul);
        setOperatorClickListener(btnDiv);
    }

    // 숫자 버튼 클릭 리스너 설정
    private void setNumberClickListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText focusedEditText = getFocusedEditText();
                if (focusedEditText != null) {
                    focusedEditText.append(((Button) v).getText());
                } else {
                    Toast.makeText(MainActivity.this, "숫자 입력창 선택 필수!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // 연산자 버튼 클릭 리스너 설정
    private void setOperatorClickListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNum1.length() == 0) {
                    Toast.makeText(MainActivity.this, "숫자1 입력 필수", Toast.LENGTH_SHORT).show();
                    etNum1.requestFocus();
                    return;
                } else if (etNum2.length() == 0) {
                    Toast.makeText(MainActivity.this, "숫자2 입력 필수", Toast.LENGTH_SHORT).show();
                    etNum2.requestFocus();
                    return;
                }

                int num1 = Integer.parseInt(etNum1.getText().toString());
                int num2 = Integer.parseInt(etNum2.getText().toString());

                int result = 0;
                int id = v.getId();
                if (id == R.id.btnAdd) {
                    result = num1 + num2;
                } else if (id == R.id.btnSub) {
                    result = num1 - num2;
                } else if (id == R.id.btnMul) {
                    result = num1 * num2;
                } else if (id == R.id.btnDiv) {
                    if (num2 == 0) {
                        Toast.makeText(MainActivity.this, "0으로 나눌 수 없음", Toast.LENGTH_SHORT).show();
                        etNum2.requestFocus();
                        return;
                    }
                    result = num1 / num2;
                }
                tvResult.setText("계산 결과 : " + result);
            }
        });
    }

    // 포커스된 EditText 반환
    private EditText getFocusedEditText() {
        if (etNum1.isFocused()) {
            return etNum1;
        } else if (etNum2.isFocused()) {
            return etNum2;
        }
        return null;
    }
}
