package com.example.student.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

/**
 * Created by student on 4/1/16.
 */
public class ProcessingThread extends Thread {
    Context context;
    private String sum;

    public boolean ok=true;
    public ProcessingThread(Context context, String sum) {
        this.context = context;
        this.sum = sum;
    }

    public void run() {
        while(ok) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            Intent intent = new Intent("broadcast_message_action");
            String message = new Date(System.currentTimeMillis()).toString() + " and result is: " + sum;
            intent.putExtra("broadcast_message", message);
            Log.d("RECEIVER", message);
            context.sendBroadcast(intent);
        }
    }
    public void onDestroy(){
        ok=false;
    }
}