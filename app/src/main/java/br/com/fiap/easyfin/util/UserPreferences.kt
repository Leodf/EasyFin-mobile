package br.com.fiap.easyfin.util

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(
        "EasyFinPreferences", Context.MODE_PRIVATE
    )
    
    companion object {
        private const val HAS_SEEN_ONBOARDING = "has_seen_onboarding"
        
        @Volatile
        private var instance: UserPreferences? = null
        
        fun getInstance(context: Context): UserPreferences {
            return instance ?: synchronized(this) {
                instance ?: UserPreferences(context.applicationContext).also { instance = it }
            }
        }
    }
    
    fun setOnboardingComplete(hasSeenOnboarding: Boolean) {
        preferences.edit().putBoolean(HAS_SEEN_ONBOARDING, hasSeenOnboarding).apply()
    }
    
    fun hasSeenOnboarding(): Boolean {
        return preferences.getBoolean(HAS_SEEN_ONBOARDING, false)
    }
} 