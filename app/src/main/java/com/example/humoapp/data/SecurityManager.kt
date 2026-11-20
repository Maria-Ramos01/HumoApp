package com.example.humoapp.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecurityManager(context: Context) {


    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPrefs = EncryptedSharedPreferences.create(
        context,
        "secure_iot_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun guardarSesion(uid: String, email: String) {
        sharedPrefs.edit()
            .putString("USER_UID", uid)
            .putString("USER_EMAIL", email)
            .apply()
    }

    fun obtenerEmail(): String? {
        return sharedPrefs.getString("USER_EMAIL", null)
    }

    fun limpiarSesion() {
        sharedPrefs.edit().clear().apply()
    }
}