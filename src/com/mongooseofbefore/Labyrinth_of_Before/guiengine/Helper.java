package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Aflah on 12/29/2014.
 */
public class Helper {

    public static Bitmap getBitmapFromAsset(String strName, Context context)
    {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(strName);
            return BitmapFactory.decodeStream(inputStream);
        }
        catch (IOException e){
            Toast.makeText(context, "Error in filepath", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return null;
    }
}
