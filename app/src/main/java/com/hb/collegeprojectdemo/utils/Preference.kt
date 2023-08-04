package com.hb.collegeprojectdemo.utils

import android.content.Context
import android.content.SharedPreferences


object Preference {
    private const val LOGGED_IN = "LOGGED_IN"


    private lateinit var mPrefs: SharedPreferences

    private var mEditor: SharedPreferences.Editor? = null

    const val KEY_APP = "demoapp_pref"


    fun deleteEveryThing(){
        mEditor?.clear()
        mEditor?.commit()
    }

    fun init(context: Context) {
        mPrefs = context.getSharedPreferences(KEY_APP, Context.MODE_PRIVATE)
        mEditor = mPrefs.edit()
    }

    fun getUserLoggedIn(): Boolean {
        return mPrefs.getBoolean(LOGGED_IN, false)
    }

    fun setUserLoggedIn(log: Boolean) {
        mEditor?.putBoolean(LOGGED_IN, log)
        mEditor?.apply()
    }



    fun logout() {
        setUserLoggedIn(false)
    }



}