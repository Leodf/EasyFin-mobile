package br.com.fiap.easyfin.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _userName = mutableStateOf("")
    val userName: State<String> get() = _userName

    init {
        fetchCurrentUserName()
    }

    private fun fetchCurrentUserName() {
        val currentUser = auth.currentUser

        if (currentUser != null) {
            val displayName = currentUser.displayName
            if (!displayName.isNullOrEmpty()) {
                _userName.value = displayName
            } else {
                val uid = currentUser.uid
                firestore.collection("users").document(uid).get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            val name = document.getString("name") ?: ""
                            _userName.value = name
                        }
                    }
                    .addOnFailureListener { exception ->
                        println("Erro ao buscar o nome do usuário: ${exception.message}")
                    }
            }
        } else {
            _userName.value = "Usuário não logado"
        }
    }

    fun logout() {
        auth.signOut()
        _userName.value = ""
    }
}
