package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] formulas;
    List<String> stringList;


    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_point;       //小数点
    private Button btn_clear;       //清空
    private Button btn_del;
    private Button btn_plus;        //加
    private Button btn_subtract;    //减
    private Button btn_mutiply;     //乘
    private Button btn_divide;      //除
    private Button btn_Mod;   //取模
    private Button btn_left;
    private Button btn_right;
    private Button btn_getResult;   //等于
    private TextView text;
    private TextView lastNumbers;
    //==


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_call) {
            MediaPlayer mMediaPlayer = MediaPlayer.create(this, R.raw.ganma);
            mMediaPlayer.start();
            Intent intent = new Intent(MainActivity.this, ListViewItem.class);
            //
            formulas = new String[stringList.size()];
            int i = 0;
            for (String s : stringList) {
                formulas[i++] = s;
            }
            //
            intent.putExtra("formulas", formulas);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**初始化按键内容*/
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_subtract = (Button) findViewById(R.id.btn_subtract);
        btn_mutiply = (Button) findViewById(R.id.btn_cheng);
        btn_divide = (Button) findViewById(R.id.btn_devide);
        btn_getResult = (Button) findViewById(R.id.btn_getResult);
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_right = (Button) findViewById(R.id.btn_right);
        //
        //
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_subtract.setOnClickListener(this);
        btn_mutiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_getResult.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        //
        text = (TextView) findViewById(R.id.et_input);
        text.setText("0");
        stringList = new ArrayList<>();
    }

    boolean isResult = false;

    @Override
    public void onClick(View v) {
        MediaPlayer mMediaPlayer = MediaPlayer.create(this, R.raw.jijiao);
        mMediaPlayer.start();

        /***/
        int id = v.getId();

        if (id == R.id.btn_clear) {
            text.setText("0");
            isResult = false;
        } else if (id == R.id.btn_getResult) {
            isResult = true;

            String str = text.getText().toString();
            String leftFormula = text.getText().toString();

            str = translateDoubleopreate(str);


            str = str.replaceAll("[(]-", "(0-");
            if (str.charAt(0) == '-') {
                str = "0" + str;
            }

            /**特殊处理分界线*/
            boolean isLegal = false;
            try {
                isLegal = Check.validate(str);
            } catch (Exception e) {
                Toast.makeText(this, "错误的表达式！", Toast.LENGTH_LONG).show();
                text.setText("0");
            }

            if (!isLegal) {
                Toast.makeText(this, "错误的表达式！", Toast.LENGTH_LONG).show();
                text.setText("0");
            } else {
                String res = null;
                try {
                    res = (MathExpressionCalculator.calculate(str)) + "";
                    if (res.equals("-0")) {
                        res = "0";
                    }
                    //
                    stringList.add(leftFormula + "  =  " + MathExpressionCalculator.rvZeroAndDot(res));
                    //
                    text.setText(MathExpressionCalculator.rvZeroAndDot(res));
                } catch (Exception e) {
                    Toast.makeText(this, "错误的表达式", Toast.LENGTH_LONG).show();
                    text.setText("0");
                }

            }
        } else if (id == R.id.btn_del) {
            if (!isResult) {
                if (text.getText().toString().equals("0")) {
                    //不做处理
                } else if (text.getText().toString().length() == 1) {
                    text.setText("0");
                } else {
                    text.setText(text.getText().toString().substring(0, text.getText().toString().length() - 1));
                }
            }
        } else {
            if (!isResult){
                if (text.getText().toString().equals("0") && id != R.id.btn_cheng && id != R.id.btn_left && id != R.id.btn_right && id != R.id.btn_devide && id != R.id.btn_subtract && id != R.id.btn_plus && id != R.id.btn_point) {
                    //此时按的就是0-9且只有一个0
                    text.setText(((Button) v).getText());
                    isResult = false;
                } else if (text.getText().toString().equals("0") && id == R.id.btn_left) {
                    text.setText(((Button) v).getText());
                } else {
                    String tmp = text.getText().toString();
                    text.setText(tmp + ((Button) v).getText());
                }
            }else{
                isResult = false;
                text.setText("0");
                onClick(v);
            }
        }
    }

    public String translateDoubleopreate(String str) {
        while (str.contains("--")) {
            str = str.replaceAll("--", "+");
        }
        while (str.contains("+-")) {
            str = str.replaceAll("[+]-", "-");
        }
        //用于处理负数前带有操作数的问题**************
        while (str.contains("*-")) {
            //*-n改成*(0 - n)
            int idx = str.indexOf("*-");
            int i = idx + 2;
            while (i <= str.length() - 1 && (str.charAt(i) < '0' && str.charAt(i) > '9')) {
                ++i;
            }
            str = str.substring(0, idx + 1) + "(0" + str.substring(idx + 1, i) + str.substring(i, str.length()) + ")";
        }
        while (str.contains("/-")) {
            //*-n改成*(0 - n)
            int idx = str.indexOf("/-");
            int i = idx + 2;
            while (i <= str.length() - 1 && (str.charAt(i) < '0' && str.charAt(i) > '9')) {
                ++i;
            }
            str = str.substring(0, idx + 1) + "(0" + str.substring(idx + 1, i) + str.substring(i, str.length()) + ")";
        }
        return str;
    }
}