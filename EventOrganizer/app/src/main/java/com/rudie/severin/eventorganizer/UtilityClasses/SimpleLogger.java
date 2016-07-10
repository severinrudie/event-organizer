package com.rudie.severin.eventorganizer.UtilityClasses;

import android.content.Context;
import android.util.Log;

/**
 * Created by erikrudie on 7/10/16.
 */
public class SimpleLogger {

    String parentName;

    public SimpleLogger(Context context){
        this.parentName = context.getClass().getSimpleName();
    }

    public SimpleLogger(String parentName){
        this.parentName = parentName;
    }

    public void debug(String message){
        Log.d(parentName,message);
    }

    public void error(String message){
        Log.e(parentName,message);
    }
}
