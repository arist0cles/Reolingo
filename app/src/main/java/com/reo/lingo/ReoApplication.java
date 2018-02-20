package com.reo.lingo;

import android.content.Context;

import java.io.InputStream;

import jsonobj.Module;
import parser.ReoJsonParser;


/**
 * Created by Butlerslad on 19/02/18.
 */

public class ReoApplication {

    private Context mActivity;

    public ReoApplication(Context mActivity)
    {
        this.mActivity = mActivity;
    }

    public Module loadModuleFromFile(int id)
    {
        InputStream is = mActivity.getResources().openRawResource(id);
        ReoJsonParser parser = new ReoJsonParser();
        return (Module) parser.parseJSONFromFile(is);
    }

    public Context getmActivity() {
        return mActivity;
    }
}
