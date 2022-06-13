package com.example.nycschools

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPreferenceUtil @Inject constructor(private val context: Context) {


    private val preferenceUtil =
        context.getSharedPreferences(Companion.NYC_SCHOOL_PREFERENCE_FILE_KEY, MODE_PRIVATE)

    private val encryptedSharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        Companion.ENCRYPTED_NYC_SCHOOL_PREFERENCE_FILE_KEY,
        MasterKey.DEFAULT_MASTER_KEY_ALIAS,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

//    val pre = EncryptedSharedPreferences(
//        context = context,
//        fileName = Companion.ENCRYPTED_NYC_SCHOOL_PREFERENCE_FILE_KEY,
//        masterKey = MasterKey.Builder(context).build(),
//        prefKeyEncryptionScheme = EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//        prefValueEncryptionScheme = EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

    fun setString(key: String, value: String) {
        preferenceUtil.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        return preferenceUtil.getString(key, "").toString()
    }

    companion object {
        private const val ENCRYPTED_NYC_SCHOOL_PREFERENCE_FILE_KEY = "com.sample.ENCRYPTED_NYC_SCHOOL_PREFERENCE_FILE_KEY"
        private const val NYC_SCHOOL_PREFERENCE_FILE_KEY = "com.sample.NYC_SCHOOL_PREFERENCE_FILE_KEY"
    }

}