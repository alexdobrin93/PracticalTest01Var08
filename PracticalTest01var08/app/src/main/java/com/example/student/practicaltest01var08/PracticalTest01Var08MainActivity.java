package com.example.student.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    private Button topLeft= null;
    private Button toRight= null;
    private Button buttonLeft= null;
    private Button buttonRight= null;
    private Button center= null;
    private Button navigare=null;
    private TextView text=null;

    int total=0;
    int accept=0;
    int faild=0;
    public boolean service=false;

    private IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("broadcast_message");
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            Log.d("MERGE", message);
        }

    }

    private ButtonListener buttonListener=new ButtonListener();
    private class ButtonListener implements View.OnClickListener{
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.buttonLeft:
                    text.setText(text.getText() + "," + "buttonLeft");
                    break;
                case R.id.buttonRight:
                    text.setText(text.getText() + "," + "buttonRight");
                    break;
                case R.id.topLeft:
                    text.setText(text.getText() + "," + "topLeft");
                    break;
                case R.id.topRight:
                    text.setText(text.getText() + "," + "topRight");
                    break;
                case R.id.Center:
                    text.setText(text.getText() + "," + "Center");
                    break;
                case R.id.navigare:
                    Intent intent=new Intent(getApplicationContext(),PracticalTest01Var08SecondaryActivity.class);
                    intent.putExtra("String", text.getText());
                    startActivityForResult(intent,2016);
                    break;


            }
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("fail", faild);
        outState.putInt("accept", accept);
        outState.putInt("total", total);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        faild=savedInstanceState.getInt("fail");
        accept=savedInstanceState.getInt("fail");
        total=savedInstanceState.getInt("fail");
        Toast.makeText(getApplicationContext(), "Stored" + text.getText().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);


        topLeft=(Button)findViewById(R.id.topLeft);
        topLeft.setOnClickListener(buttonListener);
        toRight=(Button)findViewById(R.id.topRight);
        toRight.setOnClickListener(buttonListener);
        buttonLeft=(Button)findViewById(R.id.buttonLeft);
        buttonLeft.setOnClickListener(buttonListener);
        buttonRight=(Button)findViewById(R.id.buttonRight);
        buttonRight.setOnClickListener(buttonListener);
        center=(Button)findViewById(R.id.Center);
        center.setOnClickListener(buttonListener);

        intentFilter.addAction("broadcast_message_action");

        text=(TextView)findViewById(R.id.textVi);
        text.setText("");
        navigare=(Button)findViewById(R.id.navigare);
        navigare.setOnClickListener(buttonListener);

    }

    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onActivityResult(int reqC,int result, Intent intent){


        if(result==RESULT_CANCELED){
            faild++;
            total++;
            Toast.makeText(this, "CANCEL " + RESULT_CANCELED + " fail" + faild + " total" +total, Toast.LENGTH_LONG).show();

        }
        else{
            if(result==RESULT_OK) {
                    accept++;
                    total++;
                Toast.makeText(this, "CANCEL " + RESULT_OK + "acc" + accept + "total" + total , Toast.LENGTH_LONG).show();
                }

        }
        if(total==3&&service!=true){
            Intent intentt=new Intent(getApplicationContext(),PracticalTest01Var08Service.class);
            intentt.putExtra("string",text.getText());
            startService(intentt);
        }
    }

}
