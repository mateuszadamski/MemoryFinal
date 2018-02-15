package math.uni.lodz.pl.memorymostfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    static String[] bitmapTab = new String[5];
    int i = 0;
    Button camera,hider;
    boolean bHider = true;
   static String sHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camera= this.findViewById(R.id.Camera);
        hider= this.findViewById(R.id.Hider);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent makePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(makePhoto,1);
            }
        };
        camera.setOnClickListener(listener);
        View.OnClickListener hide = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent makePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(makePhoto,1);
            }
        };
        hider.setOnClickListener(hide);

        Toast toast = Toast.makeText(context,"Make Cover and 3 photos",Toast.LENGTH_LONG);
        toast.show();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Context context = getApplicationContext();
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if(bHider){
                sHide = BitMapToString(bitmap);
                bHider=false;
                hider.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(context,"Cover saved ",Toast.LENGTH_SHORT);
                toast.show();
            /*}else if(i<4){
                Toast toast = Toast.makeText(context,"something wrong with cover ",Toast.LENGTH_SHORT);
                toast.show();*/
            }else if(i<3) {

                bitmapTab[i] = BitMapToString(bitmap);
                i++;
                Toast toast = Toast.makeText(context,"Photo saved "+i,Toast.LENGTH_SHORT);
                toast.show();
            }
            if(i==3){

                camera.setVisibility(View.INVISIBLE);
                /*Toast toast = Toast.makeText(context,"Enough photos",Toast.LENGTH_SHORT);
                toast.show();*/
            }
        }
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] b = stream.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    public void game(View view){
        if(i==3 && !bHider) {
            Intent Intent = new Intent(this, Game.class);
            startActivity(Intent);
        }else{
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context,"Not enough photos "+"("+i+")",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
