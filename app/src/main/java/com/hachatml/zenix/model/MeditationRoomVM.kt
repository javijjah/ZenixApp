package com.hachatml.zenix.model

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hachatml.zenix.signin.UserData

/**
 * ViewModel del gestor de salas de meditación, la cual se conecta con la Realtime Database.
 * @property userList Lista de usuarios en la Realtime Database (RTDB a partir de ahora).
 * @property db Gestor de Base de datos de Firebase
 * @property usersRef Llamada a la colección "Users" de la database
 * @property localUserID El ID en la database del dispositivo que está ejecutando la aplicación
 */
class MeditationRoomVM {
    var userList = mutableListOf<UserData>()
    val db = Firebase.database
    var usersRef = db.getReference("Users")
    var localUserID: String = ""

    /**
     * Ejecutado al entrar el usuario en la sala de meditación, añadirá los datos del usuario a la RTDB
     * @param userData El usuario que entra en la sala
     * Actualizará localUserID para apuntar el ID del usuario
     */
    fun userEntered(userData: UserData?) {
        val localMeditator =
            Meditator(userData?.userId, userData?.username, userData?.profilePictureUrl, false)
        localUserID = usersRef.push().key.toString()
        usersRef.child(localUserID).setValue(localMeditator)
            .addOnSuccessListener {
                println("User added to database with ID: $localUserID")
            }
            .addOnFailureListener { exception ->
                println("Error adding user data: $exception")
            }
    }

    /**
     * Cambia en la RTDB el estado del usuario de ready or not
     *@param localUserID ID en la RTDB del usuario a cambiar
     *@param boolState Estado del booleano a colocar
     */
    fun setReadyState(localUserID: String, boolState: Boolean) {
        val userRef = db.getReference("Users").child(localUserID)
        userRef.child("ready").setValue(boolState)
    }

    /**
     * Llena la userList de la clase con los usuarios ya en la RTDB, para que estos sean mucho más
     * accesibles y fácil de iterar. Esta función llama a un Listener que vigila los cambios de los datos,
     * para luego añadir cada usuario que encuentre en "Users" a userList.
     */
    fun populateUserList() {
        val usersRef = db.getReference("Users")
        val valueEventListener = object : ValueEventListener {
            //La forma de usar este listener es haciendo override como mínimo a sus dos métodos principales,
            //onDataChange y onCancelled
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (userSnapshot in snapshot.children) {
                    val userData = userSnapshot.getValue(UserData::class.java)
                    userData?.let { user ->
                        userList.add(user)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                println("Error retrieving user data: $error")
            }
        }
        usersRef.addListenerForSingleValueEvent(valueEventListener)
    }

    /**
     * Ejecutado al salir de la sala de meditación, sacará los datos del usuario de la RTDB
     * @param userData usuario a eliminar de la RTDB
     */
    fun userLeft(userData: UserData?) {
        println("called userLeft")
        val userRef = userData?.let { usersRef.child(localUserID) }
        userRef?.removeValue()
            ?.addOnSuccessListener {
                println("Removed user ${userData?.userId}${userData?.username} successfully")
            }
            ?.addOnFailureListener { exception ->
                println("Error deleting user ${userData?.userId} Error: $exception")
            }
    }
}

/**
 * Cumple como objetivo el permitir añadir a la RTBD el parámetro ready además de todos los de un
 * usuario base, sin perturbar userData, que debe ser utilizado con el login de google y no debe ser modificado.
 * @param userId ID del UserData
 * @param username username de UserData
 * @param profilePictureUrl link a la foto de UserData
 * @param ready Si el usuario se encuentra listo para iniciar la sesión
 */
class Meditator(
    val userId: String?,
    val username: String?,
    val profilePictureUrl: String?,
    val ready: Boolean
)