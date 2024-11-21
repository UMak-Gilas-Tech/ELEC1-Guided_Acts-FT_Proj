package com.elecone.guidedexercise;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class GuidedActivityFifteen extends BaseActivity {

    Button sendSMS, sendBSMS, call;
    EditText phoneNo, message;
    ProgressBar progressBar;
    Intent smsIntent, callIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guided_activity_fifteen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("SMSandPhoneCall");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        init();
        sendMessage();
        sendMessageBuiltIn();
        phoneCall();
    }

    public void init() {
        progressBar = findViewById(R.id.progressBar);
        sendSMS = findViewById(R.id.btnSMS);
        sendBSMS = findViewById(R.id.btnBSMS);
        call = findViewById(R.id.btnPhoneCall);
        phoneNo = findViewById(R.id.etPhoneNo);
        message = findViewById(R.id.etSMS);
        progressBar.setVisibility(View.GONE);
    }

    public void sendMessage() {
        sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    showProgressBar(true);
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(
                            phoneNo.getText().toString(),
                            null,
                            message.getText().toString(),
                            null,
                            null
                    );

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showProgressBar(false);
                            Toast.makeText(GuidedActivityFifteen.this, "Message Sent!", Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);
                } catch (Exception e) {
                    showProgressBar(false);
                    Toast.makeText(GuidedActivityFifteen.this, "Message was not delivered!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendMessageBuiltIn() {
        sendBSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo.getText().toString()));
                smsIntent.putExtra("sms_body", message.getText().toString());
                startActivity(smsIntent);
            }
        });
    }

    public void phoneCall() {
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNo.getText().toString()));

                if (ContextCompat.checkSelfPermission(GuidedActivityFifteen.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(GuidedActivityFifteen.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    try {
                        startActivity(callIntent);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
