package ln.implicitintentdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {


    private static final int PICK_IMAGE = 1;

    private EditText edDial;
    private Button btnDial,btnUpload;
    private ImageView ivPic;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edDial = (EditText) findViewById(R.id.ed_number);
        btnDial = (Button) findViewById(R.id.btn_dial);
        btnUpload = (Button) findViewById(R.id.btn_upload);
        ivPic = (ImageView) findViewById(R.id.iv_pic);

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*String s = edDial.getText().toString();

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+s));
                startActivity(intent);*/


                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(Uri.parse("smsto:"+"9971227563;9990900909"));
                sendIntent.putExtra("sms_body", "hiii");
                //sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);


            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK){
            try{
                if(bitmap != null){
                    bitmap.recycle();
                }
                InputStream stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);
                stream.close();
                ivPic.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
