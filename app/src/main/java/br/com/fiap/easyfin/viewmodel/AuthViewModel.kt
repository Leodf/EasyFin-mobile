package br.com.fiap.easyfin.viewmodel

import androidx.lifecycle.ViewModel
import br.com.fiap.easyfin.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AuthViewModel: ViewModel() {

    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    fun Login(email: String, password: String, onResult: (Boolean, String?) -> Unit){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    onResult(true,null)
                } else {
                    onResult(false, it.exception?.localizedMessage ?: "Erro desconhecido")
                }
            }
    }

    fun SignUp(email: String, name: String, password: String, onResult: (Boolean, String?) -> Unit){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    var userId = it.result?.user?.uid

                    val userModel = UserModel(name, email, userId!!)
                    firestore
                        .collection("users")
                        .document(userId)
                        .set(userModel)
                        .addOnCompleteListener{dbTask ->
                            if(dbTask.isSuccessful){
                                onResult(true,null)
                            } else {
                                onResult(false,"Alguma coisa deu errado!")
                            }
                        }

                } else {
                    onResult(false, it.exception?.localizedMessage ?: "Erro desconhecido")
                }
            }
    }
}