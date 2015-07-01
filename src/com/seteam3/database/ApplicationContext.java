package com.seteam3.database;

import android.app.Application;
import android.content.Context;

/**
 * This class is created automatically when the app launches.
 * It is used to provide an application-level context for the SQLiteOpenHelper
 */
public class ApplicationContext extends Application
{

    private static ApplicationContext instance;

    public ApplicationContext()
    {
        instance = this;
    }

    public static Context getContext()
    {
        return instance;
    }

}