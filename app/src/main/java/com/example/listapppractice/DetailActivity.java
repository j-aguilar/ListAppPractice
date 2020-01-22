package com.example.listapppractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent in = getIntent();
        int index = in.getIntExtra("com.example.listapppractice.ITEM_INDEX", -1);

        if (index > -1) {
            int pic = getImage(index);
            ImageView img = (ImageView) findViewById(R.id.imageView);
            displayImage(img, pic);
        }
    }

    private int getImage(int index) {
        int idx = index + randTestModifier();
        switch (idx) {
            case 0: return R.drawable.peach;
            case 1: return R.drawable.tomato;
            case 2: return R.drawable.squash;
            case 3: return R.drawable.peach_lx;
            case 4: return R.drawable.tomato_lx;
            case 5: return R.drawable.squash_lx;
            default: return -1;
        }
    }

    private void displayImage(ImageView img, int pic) {
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if ( shouldScale(imgWidth, screenWidth) ) {
            options.inSampleSize = applyRatio(imgWidth, screenWidth);
        }
            options.inJustDecodeBounds = false;

            Bitmap displayImg = BitmapFactory.decodeResource(getResources(), pic, options);
            img.setImageBitmap(displayImg);
    }

    private boolean shouldScale(int imgWidth, int screenWidth) {
        boolean b = (imgWidth > screenWidth)? true : false;
        return b;
    }

    private int applyRatio(int imgWidth, int screenWidth) {
        int ratio = Math.round((float) imgWidth / screenWidth);
        return ratio;
    }

    private int randTestModifier() {
        /* Will randomly choose if a small or large image is used.
           If large is selected we will return item.length access the large images.
        */
        return (Math.round(Math.random()) == 1)? 3 : 0;
    }
}