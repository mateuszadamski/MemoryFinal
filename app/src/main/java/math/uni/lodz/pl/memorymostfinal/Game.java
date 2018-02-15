package math.uni.lodz.pl.memorymostfinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

import static android.os.SystemClock.sleep;
import static math.uni.lodz.pl.memorymostfinal.MainActivity.bitmapTab;
import static math.uni.lodz.pl.memorymostfinal.MainActivity.sHide;

public class Game extends AppCompatActivity {
    ImageView ImageView1;
    ImageView ImageView2;
    ImageView ImageView3;
    ImageView ImageView4;
    ImageView ImageView5;
    ImageView ImageView6;
    String [] shuffle = new String[6];
    boolean click = false;
    int id1;
    int id2;
    int points = 0;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        shuffle[0] = bitmapTab[1];
        shuffle[1] = bitmapTab[2];
        shuffle[2] = bitmapTab[0];
        shuffle[3] = bitmapTab[1];
        shuffle[4] = bitmapTab[0];
        shuffle[5] = bitmapTab[2];




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ImageView1 = findViewById(R.id.imageView1);
        ImageView2 = findViewById(R.id.imageView2);
        ImageView3 = findViewById(R.id.imageView3);
        ImageView4 = findViewById(R.id.imageView4);
        ImageView5 = findViewById(R.id.imageView5);
        ImageView6 = findViewById(R.id.imageView6);

        ImageView1.setImageBitmap(StringToBitMap(sHide));
        ImageView2.setImageBitmap(StringToBitMap(sHide));
        ImageView3.setImageBitmap(StringToBitMap(sHide));
        ImageView4.setImageBitmap(StringToBitMap(sHide));
        ImageView5.setImageBitmap(StringToBitMap(sHide));
        ImageView6.setImageBitmap(StringToBitMap(sHide));


    }
    public void OnClick1(View view){
        ImageView1.setImageBitmap(StringToBitMap(shuffle[0]));
        if(!click){
            id1 = 0;
            click = true;
        }else if(click){
            id2 = 0;
            check1();
        }
    }
    public void OnClick2(View view){
        ImageView2.setImageBitmap(StringToBitMap(shuffle[1]));
        if(!click){
            id1 = 1;
            click = true;
        }else if(click){
            id2 = 1;
            check2();
        }
    }
    public void OnClick3(View view){
        ImageView3.setImageBitmap(StringToBitMap(shuffle[2]));
        if(!click){
            id1 = 2;
            click = true;
        }else if(click){
            id2 = 2;
            check3();
        }
    }
    public void OnClick4(View view){
        ImageView4.setImageBitmap(StringToBitMap(shuffle[3]));
        if(!click){
            id1 = 3;
            click = true;
        }else if(click){
            id2 = 3;
            check1();
        }
    }
    public void OnClick5(View view){
        ImageView5.setImageBitmap(StringToBitMap(shuffle[4]));
        if(!click){
            id1 = 4;
            click = true;
        }else if (click){
            id2 = 4;
            check3();
        }
    }
    public void OnClick6(View view){
        ImageView6.setImageBitmap(StringToBitMap(shuffle[5]));
        if(!click){
            id1 = 5;
            click = true;
        }else if(click){
            id2 = 5;
            check2();
        }
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    public void check1() {
        Context context = getApplicationContext();

        if (id1 == 0 && id2 == 3 || id1 == 3 && id2 == 0) {
            click = false;
            ImageView1.setVisibility(View.INVISIBLE);
            ImageView4.setVisibility(View.INVISIBLE);
            points += 2;
            Toast toast = Toast.makeText(context, points + " pkt", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            ImageView1.setImageBitmap(StringToBitMap(sHide));
            ImageView4.setImageBitmap(StringToBitMap(sHide));
            points--;
            counter--;
            Toast toast = Toast.makeText(context, points + " pkt", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void check2() {
        Context context = getApplicationContext();
        if (id1 == 1 && id2 == 5 || id1 == 5 && id2 == 1) {
            click = false;
            ImageView2.setVisibility(View.INVISIBLE);
            ImageView6.setVisibility(View.INVISIBLE);
            points += 2;
            counter--;
            Toast toast = Toast.makeText(context, points + " pkt", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            hide();
            points--;
            Toast toast = Toast.makeText(context, points + " pkt", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void check3() {
        Context context = getApplicationContext();
        if (id1 == 2 && id2 == 4 || id1 == 4 && id2 == 2) {
            click = false;
            ImageView3.setVisibility(View.INVISIBLE);
            ImageView5.setVisibility(View.INVISIBLE);
            points += 2;
            counter--;
            Toast toast = Toast.makeText(context, points + " pkt", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            hide();
            points--;
            Toast toast = Toast.makeText(context, points + " pkt", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void hide(){
        Context context = getApplicationContext();
        click = false;
        ImageView1.setImageBitmap(StringToBitMap(sHide));
        ImageView2.setImageBitmap(StringToBitMap(sHide));
        ImageView3.setImageBitmap(StringToBitMap(sHide));
        ImageView4.setImageBitmap(StringToBitMap(sHide));
        ImageView5.setImageBitmap(StringToBitMap(sHide));
        ImageView6.setImageBitmap(StringToBitMap(sHide));
        if(counter==0) {
            Toast toast = Toast.makeText(context, "GG GG GG GG", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}