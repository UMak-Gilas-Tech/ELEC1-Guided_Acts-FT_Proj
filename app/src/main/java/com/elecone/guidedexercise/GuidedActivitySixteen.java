package com.elecone.guidedexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import android.Manifest;

public class GuidedActivitySixteen extends BaseActivity{
    Button send, capturePic;
    EditText receiver, subject, message;
    ImageView pic;
    Intent intent,chooser;
    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge16_email_and_camera);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Guided Exercise #16: Email & Camera");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        init();
        sendEmail();
        enableRuntimePermission();
        capturePic();
    }

    public void init(){
        receiver = findViewById(R.id.etReceiver);
        subject = findViewById(R.id.etSubject);
        message = findViewById(R.id.etMessage);
        pic = findViewById(R.id.ivPic);
        send = findViewById(R.id.btnSend);
        capturePic = findViewById(R.id.btnCapturePic);
    }

    public void sendEmail(){
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_SEND);

                intent.setData(Uri.parse("mailto:"));

                String[] to = {receiver.getText().toString()};

                intent.putExtra(Intent.EXTRA_EMAIL, to); // for recepient
                intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString()); // subject of email
                intent.putExtra(Intent.EXTRA_TEXT,message.getText().toString()); // body of email

                intent.setType("message/rfc822");

                chooser = intent.createChooser(intent,"Send Email");

                if(receiver.getText().toString().isEmpty()){
                    receiver.setError("Email required!");
                }else{
                    startActivity(chooser);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 7 && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            pic.setImageBitmap(bitmap);
        }
    }

    public void  enableRuntimePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(GuidedActivitySixteen.this, Manifest.permission.CAMERA)){
            Toast.makeText(getApplicationContext(),"CAMERA permission allows us to Access CAMERA app",Toast.LENGTH_LONG).show();
        }else{
            ActivityCompat.requestPermissions(GuidedActivitySixteen.this, new String[]{Manifest.permission.CAMERA},RequestPermissionCode);
        }
    }

    public void capturePic(){
        capturePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,7);
            }
        });
    }
}

