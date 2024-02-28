package com.hachatml.zenix

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hachatml.zenix.signin.UserData

class MeditationRoomVM {
    var userList = mutableListOf<UserData>()
    val db = Firebase.database
    var usersRef = db.getReference("Users")
    var localUserID: String = ""

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

    fun setReadyState(localUserID: String, boolState: Boolean) {
        val userRef = db.getReference("Users").child(localUserID)
        userRef.child("ready").setValue(boolState)
    }

    fun populateUserList() {
        val usersRef = db.getReference("Users")
        val valueEventListener = object : ValueEventListener {
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

class Meditator(
    val userId: String?,
    val username: String?,
    val profilePictureUrl: String?,
    val ready: Boolean
)