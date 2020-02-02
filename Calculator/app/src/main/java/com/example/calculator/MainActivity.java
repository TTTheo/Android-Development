package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    // GUI elements
    private TextView display ;
    private Button btn1 ;
    private Button btn2 ;
    private Button btn3 ;
    private Button btn4 ;
    private Button btn5 ;
    private Button btn6 ;
    private Button btn7 ;
    private Button btn8 ;
    private Button btn9 ;
    private Button btn0 ;
    private Button btn_plus ;
    private Button btn_minus ;
    private Button btn_multi ;
    private Button btn_div ;
    private Button btn_sqrt ;
    private Button btn_dot ;
    private Button btn_equals ;
    private Button btn_del ;
    private Button btn_clear ;

    // backend variables
    private HashMap<Button, String> map = new HashMap<>() ;
    private double first = 0 ;
    private String operation = "plus";

    // GUI elements initiation
    public void init(){
        display = (TextView) findViewById(R.id.display) ;
        btn1 = (Button)findViewById(R.id.btn1) ;
        btn2 = (Button)findViewById(R.id.btn2) ;
        btn3 = (Button)findViewById(R.id.btn3) ;
        btn4 = (Button)findViewById(R.id.btn4) ;
        btn5 = (Button)findViewById(R.id.btn5) ;
        btn6 = (Button)findViewById(R.id.btn6) ;
        btn7 = (Button)findViewById(R.id.btn7) ;
        btn8 = (Button)findViewById(R.id.btn8) ;
        btn9 = (Button)findViewById(R.id.btn9) ;
        btn0 = (Button)findViewById(R.id.btn0) ;
        btn_plus = (Button)findViewById(R.id.btn_plus) ;
        btn_minus = (Button)findViewById(R.id.btn_minus) ;
        btn_multi = (Button)findViewById(R.id.btn_multi) ;
        btn_div = (Button)findViewById(R.id.btn_div) ;
        btn_sqrt = (Button)findViewById(R.id.btn_sqrt) ;
        btn_dot = (Button)findViewById(R.id.btn_dot) ;
        btn_equals = (Button)findViewById(R.id.btn_equals) ;
        btn_del = (Button)findViewById(R.id.btn_del) ;
        btn_clear = (Button)findViewById(R.id.btn_clear) ;
        map.put(btn1,"1") ;
        map.put(btn2,"2") ;
        map.put(btn3,"3") ;
        map.put(btn4,"4") ;
        map.put(btn5,"5") ;
        map.put(btn6,"6") ;
        map.put(btn7,"7") ;
        map.put(btn8,"8") ;
        map.put(btn9,"9") ;
        map.put(btn0,"0") ;

        display.setText("");

    }

    //set listeners for all buttons
    public void setListeners(){
        for(final Button number : map.keySet()){
            number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    display.setText(display.getText() + map.get(number));
                }
            });
        }

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = display.getText().toString() ;
                if(!temp.equals("") && !temp.equals("-")){
                    display.setText(temp + ".");
                }
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(display.getText().equals("")) return ;
                calculate() ;
                operation = "plus" ;
                display.setText("");
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = display.getText().toString() ;
                if(display.getText().equals("")){
                    display.setText("-");
                    return ;
                }
                calculate() ;
                operation = "minus" ;
                display.setText("");
            }
        });

        btn_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(display.getText().equals("")) return ;
                calculate() ;
                operation = "multi" ;
                display.setText("");
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(display.getText().equals("")) return ;
                calculate() ;
                operation = "div" ;
                display.setText("");
            }
        });

        btn_sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate() ;
                if(first < 0){
                    display.setText("Math Error");

                }else{
                    display.setText(Math.sqrt(first) + "") ;
                }
                first = 0 ;
                operation = "plus" ;
            }
        });

        btn_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate() ;
                operation = "plus" ;
                first = 0 ;
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = display.getText().toString() ;
                if(input.equals("")) return ;
                display.setText(input.substring(0,input.length()-1));
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first = 0 ;
                display.setText("");
                operation = "plus" ;
            }
        });
    }

    // calculation for each operation except for square root
    public void calculate(){
        double second = Double.parseDouble(display.getText().toString()) ;
        switch (operation) {

            case "plus":
                first = first + second;
                display.setText(first + "");
                break;

            case "minus":
                first = first - second;
                display.setText(first + "");
                break ;

            case "multi":
                first = first * second;
                display.setText(first + "");
                break ;

            case "div":
                if(second == 0){
                    display.setText("Math Error");
                }else{
                    first = first / second;
                    display.setText(first + "");
                }
                break ;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListeners() ;
    }

}
