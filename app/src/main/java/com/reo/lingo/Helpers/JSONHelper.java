package com.reo.lingo.Helpers;

import android.content.Context;
import android.content.res.Resources;

import com.reo.lingo.Activities.MainActivity;
import com.reo.lingo.Models.Module;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by patrick on 2/07/18.
 */

public class JSONHelper {
    public Module parseModule(int resourceId, Resources resources) {
        String json = "";

        InputStream is = resources.openRawResource(resourceId);
        try {
            json = IOUtils.toString(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        IOUtils.closeQuietly(is);

        return MainActivity.gson.fromJson(json, Module.class);
    }
}
