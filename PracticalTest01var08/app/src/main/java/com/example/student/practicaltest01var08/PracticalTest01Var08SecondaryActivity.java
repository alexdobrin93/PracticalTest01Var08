package com.example.student.practicaltest01var08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PracticalTest01Var08SecondaryActivity extends AppCompatActivity {

    private Button verify =null;
    private Button cancel=null;
    private TextView textV=null;
    private ButtonListener buttonListener=new ButtonListener();
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.verifyButton:
                    setResult(RESULT_OK,null);
                    finish();
                    break;
                case R.id.cancelButton:
                    setResult(RESULT_CANCELED,null);
                    finish();
                    break;

            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_secondary);

        verify=(Button)findViewById(R.id.verifyButton);
        verify.setOnClickListener(buttonListener);
        cancel=(Button)findViewById(R.id.cancelButton);
        cancel.setOnClickListener(buttonListener);
        textV=(TextView)findViewById(R.id.textViewNou);

        Intent intent= getIntent();
        if(intent!=null&intent.getExtras().containsKey("String")){
            textV.setText(intent.getStringExtra("String"));

        }
    }
}
