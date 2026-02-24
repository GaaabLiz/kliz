@file:Suppress("MemberVisibilityCanBePrivate", "DuplicatedCode", "unused", "DEPRECATION", "PackageDirectoryMismatch")package io.github.gaaabliz.kliz.firebase

/**
 * The purpose of this file is to provide a set of utility methods
 * for firebase operations on android.
 *
 * The dependency required for this file are:
 * - All android firebase dependencies.
 * - Gson dependency for json serialization.
 * - Kliz library for logging and some other utility classes.
 *
 *
 * LAST UPDATE AT: 15-03-2024
 * VERSION : 1.0.0
 *
 * This file is part of the Kliz library.
 *
 * @see <a href="https://github.com/GaaabLiz/kliz">Kliz Github Library</a>
 * @author Gabliz
 */


import android.app.Activity
import android.content.Context
import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.google.firebase.firestore.AggregateQuery
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.messaging
import com.google.firebase.storage.storage
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.type.LatLng
import io.github.gaaabliz.kliz.android.util.LogUtils
import io.github.gaaabliz.kliz.common.base.Operation
import io.github.gaaabliz.kliz.common.base.OperationException
import io.github.gaaabliz.kliz.common.model.LoginDetails
import io.github.gaaabliz.kliz.common.model.TextFileSuffix
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import java.util.*

/**
 * This interface must be implemented by every class that wants to be used as a collection in firestore.
 * The [collectionName] property must be overridden with the name of the collection.
 */
@Deprecated("Not used anymore.")
interface FirestoreCollection { val collectionName : String }

/**
 * Class that contains the details of a registration.
 *
 * @property name The name of the user.
 * @property email The email of the user.
 * @property photoUrl The photo url of the user.
 * @property emailVerified The email verification status of the user.
 * @property uid The uid of the user.
 */
data class FbAuthUser(val name : String?, val email : String?, val photoUrl : Uri?, val emailVerified : Boolean?, val uid : String?)

/**
 * class that contains the details of a firestore document.
 *
 * @property field The field to update.
 * @property value The value to update.
 */
data class FirestoreEntryUpdate( val field : String, val value : Any)

/**
 * Enum for type of firebase checks.
 *
 * @property payload
 * @property fieldName
 */
@Deprecated("Not used anymore.")
enum class FirebaseChecks(val payload : String? = null, val fieldName : String? = null) {
    USER_LOGGED,
    @Deprecated("NOT IMPLEMENTED") INTERNET_OK,
    PARAM_CHECK
}

/**
 * Annotation that indicates that the property/function is project based.
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION)
@MustBeDocumented
@Deprecated("Not used anymore.")
private annotation class FbProjectBased


/**
 * Static class that contains utility methods for firebase operations.
 * */
object KlizFirebase {

    /**
     * Some utility methods for firebase operations.
     */
    object Util {
/*
        /**
         * Function that performs some checks on the firebase operations.
         *
         * @param checks
         * @throws IllegalStateException if the user is not logged.
         * @throws UnsupportedOperationException if the internet check is not implemented.
         */
        @Deprecated("Not used anymore.")
        fun check( vararg checks : FirebaseChecks) {
            checks.forEach { check ->
                when(check) {
                    FirebaseChecks.USER_LOGGED -> if(!isUserLogged()) throw IllegalStateException("Operation not permitted! User is not currently logged.")
                    FirebaseChecks.INTERNET_OK -> throw UnsupportedOperationException("Internet check not implemented in fb utils. Must be checked elsewhere.")
                    FirebaseChecks.PARAM_CHECK -> if(check.payload == null || check.payload == "")
                        throw IllegalArgumentException("Parameter ${check.fieldName} is null or empty.")
                }
            }
        }

        fun convertLocationStringToLatLng(location: String) : LatLng {
            val temp = location.split("#")
            return LatLng.newBuilder()
                .setLatitude(temp[0].toDouble())
                .setLongitude(temp[1].toDouble())
                .build()
        }*/
    }


    /**
     * Class that contains all the methods for firebase authentication.
     */
    object Auth {

        fun getFbUser() = Firebase.auth.currentUser
        fun isUserLogged() = Firebase.auth.currentUser != null
        fun hasEssentialInfo(user: FbAuthUser) : Boolean { return user.email != null && user.uid != null }

        fun getFormattedFbUser() = FbAuthUser(
            Firebase.auth.currentUser?.displayName,
            Firebase.auth.currentUser?.email,
            Firebase.auth.currentUser?.photoUrl,
            Firebase.auth.currentUser?.isEmailVerified,
            Firebase.auth.currentUser?.uid
        )

        fun getFbUserString() : String? {
            if(isUserLogged()) {
                val user = Firebase.auth.currentUser
                val name = user?.displayName
                val email = user?.email
                val photoUrl = user?.photoUrl
                val emailVerified = user?.isEmailVerified
                val uid = user?.uid
                val builder : StringBuilder = StringBuilder()
                builder.append("Current firebase user information are:\n")
                builder.append("label = \"$name\"\n")
                builder.append("email = \"$email\"\n")
                builder.append("avatarUrl = \"$photoUrl\"\n")
                builder.append("emailVerified = \"$emailVerified\"\n")
                builder.append("uid = \"$uid\"\n")
                return builder.toString()
            } else {
                return null
            }
        }

        @Deprecated("Use the suspend function instead")
        fun signup(
            email : String,
            password : String,
            activity: Activity,
            onSuccess : () -> Unit,
            onError : (String) -> Unit
        ) {
            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        onError("Error creating user: ${task.exception}.")
                    }
                }
        }

        suspend fun signup(loginDetails: LoginDetails) : Operation<AuthResult> {
            return withContext(Dispatchers.IO) {
                try {
                    val result = Firebase.auth.createUserWithEmailAndPassword(loginDetails.username, loginDetails.password).await()
                    return@withContext Operation.Success(result)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error creating user.")
                }
            }
        }

        suspend fun updatePassword(newPassword: String) : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try {
                    Firebase.auth.currentUser?.updatePassword(newPassword)?.await()
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating password.")
                }
            }
        }

        suspend fun login(loginDetails: LoginDetails) : Operation<AuthResult> {
            return withContext(Dispatchers.IO) {
                try {
                    val result = Firebase.auth.signInWithEmailAndPassword(loginDetails.username, loginDetails.password).await()
                    return@withContext Operation.Success(result)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error logging user.")
                }
            }
        }

        suspend fun deleteAuthAccount() : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try {
                    Firebase.auth.currentUser?.delete()?.await()
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error deleting user.")
                }
            }
        }

        @Deprecated("Use the suspend function instead")
        fun login(
            email : String,
            password : String,
            activity: Activity,
            onSuccess : () -> Unit,
            onError : (String) -> Unit
        ) {
            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        onError("Error logging user: ${task.exception}.")
                    }
                }
        }

        fun logout() {
            Firebase.auth.signOut()
        }

    }


    /**
     * Class that contains all the methods for firebase messagging.
     */
    object Fcm {

        suspend fun getFcmToken() : Operation<String> {
            return withContext(Dispatchers.IO) {
                try{
                    val token = Firebase.messaging.token.await()
                    return@withContext Operation.Success(token)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error getting fcm token.")
                }
            }
        }

        suspend fun subscribeFcmTopic(topicName : String) : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try{
                    Firebase.messaging.subscribeToTopic(topicName).await()
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error subscribing to topic.")
                }
            }
        }

        fun subscribeFcmTopic(topicName: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
            Firebase.messaging.subscribeToTopic(topicName)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        onError("Error subscribing to topic: ${task.exception}.")
                    }
                }
                .addOnFailureListener {
                    onError("Error subscribing to topic: ${it.message}.")
                }
        }

        fun sendNotification(message : RemoteMessage) {
            Firebase.messaging.send(message)
        }

    }


    object Frst {

        fun getFirestore() = Firebase.firestore

        suspend fun updateObject(
            collection : String,
            document : String,
            objectToUpdate : Any
        ) : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try{
                    Firebase.firestore
                        .collection(collection)
                        .document(document)
                        .set(objectToUpdate)
                        .await()
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore object.")
                }
            }
        }

        suspend fun updateEntry(
            collection : String,
            document : String,
            fieldName: String,
            newFieldValue: Any?
        ) : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try{
                    Firebase.firestore
                        .collection(collection)
                        .document(document)
                        .update(fieldName, newFieldValue)
                        .await()
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }

        suspend fun updateEntry2(
            collection : String,
            document : String,
            innerCollection : String,
            innerDocument : String,
            fieldName: String,
            newFieldValue: Any
        ) : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try{
                    Firebase.firestore
                        .collection(collection)
                        .document(document)
                        .collection(innerCollection)
                        .document(innerDocument)
                        .update(fieldName, newFieldValue)
                        .await()
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }

        suspend fun deleteDocument(
            collection : String,
            document : String,
        ) : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try{
                    Firebase.firestore
                        .collection(collection)
                        .document(document)
                        .delete()
                        .await()
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }

        suspend fun updateObject2(
            collection : String,
            document : String,
            innerCollection : String,
            innerDocument : String,
            objectToUpdate : Any
        ) : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try{
                    Firebase.firestore
                        .collection(collection)
                        .document(document)
                        .collection(innerCollection)
                        .document(innerDocument)
                        .set(objectToUpdate)
                        .await()
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore object.")
                }
            }
        }

        suspend inline fun <reified T> getDocument(
            collection : String,
            document : String
        ) : Operation<T> {
            return withContext(Dispatchers.IO) {
                try{
                    val result = Firebase.firestore
                        .collection(collection)
                        .document(document)
                        .get()
                        .await()
                    val obj = result.toObject(T::class.java)
                    return@withContext Operation.Success(obj as T)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error getting firestore entry.")
                }
            }
        }

        suspend inline fun <reified T> getDocument2(
            collection1 : String,
            document1 : String,
            collection2 : String,
            document2 : String,
        ) : Operation<T> {
            return withContext(Dispatchers.IO) {
                try{
                    val result = Firebase.firestore
                        .collection(collection1)
                        .document(document1)
                        .collection(collection2)
                        .document(document2)
                        .get()
                        .await()
                    val obj = result.toObject(T::class.java)
                    return@withContext Operation.Success(obj as T)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error getting firestore entry.")
                }
            }
        }

        suspend fun updateArrayEntry(
            collection: String,
            document: String,
            fieldName: String,
            update: FieldValue
        ) : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try{
                    Firebase.firestore
                        .collection(collection)
                        .document(document)
                        .update(fieldName, update)
                        .await()
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }

        suspend inline fun <reified T> getCollection(collection : String) : Operation<List<T>> {
            return withContext(Dispatchers.IO) {
                try{
                    val data = Firebase.firestore
                        .collection(collection)
                        .get()
                        .await()
                    val output = mutableListOf<T>()
                    data.forEach { doc -> output.add(doc.toObject(T::class.java)) }
                    return@withContext Operation.Success(output)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }

        @Deprecated("old")
        suspend inline fun <reified T> queryEqualTo(collection : String, vararg keyValue : Pair<String, String>) : Operation<List<T>> {
            return withContext(Dispatchers.IO) {
                try{
                    val col = Firebase.firestore.collection(collection)
                    var query : Query = col
                    keyValue.forEach {
                        query = query.whereEqualTo(it.first, it.second)
                    }
                    val data = query.get().await()
                    val output = mutableListOf<T>()
                    data.forEach { doc -> output.add(doc.toObject(T::class.java)) }
                    return@withContext Operation.Success(output)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }

        @Deprecated("old")
        suspend inline fun <reified T> queryLimitOrderByEqualTo(
            collection : String,
            orderByValue:String,
            limit: Int,
            whereKey : String,
            whereValue : String
        ) : Operation<List<T>> {
            return withContext(Dispatchers.IO) {
                try{
                    val data = Firebase.firestore
                        .collection(collection)
                        .whereEqualTo(whereKey, whereValue)
                        .orderBy(orderByValue)
                        .limit(limit.toLong())
                        .get()
                        .await()
                    val output = mutableListOf<T>()
                    data.forEach { doc -> output.add(doc.toObject(T::class.java)) }
                    return@withContext Operation.Success(output)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }

        suspend inline fun <reified T> execQueryCollection(
            query: Query,
        ) : Operation<List<T>> {
            return withContext(Dispatchers.IO) {
                try{
                    val data = query.get().await()
                    val output = mutableListOf<T>()
                    data.forEach { doc -> output.add(doc.toObject(T::class.java)) }
                    return@withContext Operation.Success(output)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }

        suspend fun setEntryOnQueryCollection(
            query: Query,
            fieldName: String,
            fieldValue: Any
        ) : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try{
                    val data = query.get().await()
                    data.forEach { doc ->
                        doc.reference.update(fieldName, fieldValue)
                    }
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }

        suspend fun execQueryCount(query: AggregateQuery) : Operation<Int> {
            return withContext(Dispatchers.IO) {
                try{
                    val data = query.get(AggregateSource.SERVER).await()
                    return@withContext Operation.Success(data.count.toInt())
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }


        suspend fun addArrayEntry(
            collection : String,
            document: String,
            fieldName: String,
            fieldValue: Any
        ) : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try{
                    val data = Firebase.firestore
                        .collection(collection)
                        .document(document)
                        .update(fieldName, FieldValue.arrayUnion(fieldValue))
                        .await()
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }

        suspend fun removeArrayEntry(
            collection : String,
            document: String,
            fieldName: String,
            fieldValue: Any
        ) : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try{
                    val data = Firebase.firestore
                        .collection(collection)
                        .document(document)
                        .update(fieldName, FieldValue.arrayRemove(fieldValue))
                        .await()
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }



        suspend inline fun <reified T> getCollection2(
            collection : String,
            document: String,
            innerCollection : String,
        ) : Operation<List<T>> {
            return withContext(Dispatchers.IO) {
                try{
                    val data = Firebase.firestore
                        .collection(collection)
                        .document(document)
                        .collection(innerCollection)
                        .get()
                        .await()
                    val output = mutableListOf<T>()
                    data.forEach { doc -> output.add(doc.toObject(T::class.java)) }
                    return@withContext Operation.Success(output)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating firestore entry.")
                }
            }
        }

    }

    /**
     * Class that contains all the methods for firebase realtime database.
     */
    object Rdb {

        suspend inline fun <reified T> getDbData(path : String) : Operation<T> {
            return withContext(Dispatchers.IO) {
                try{
                    val ref = Firebase.database.reference.child(path).get().await()
                    val obj = ref.getValue(T::class.java)
                    return@withContext Operation.Success(obj!!)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error reading rdt data.")
                }
            }
        }

        suspend inline fun <reified T> getDbData(vararg keys : String) : Operation<T> {
            return withContext(Dispatchers.IO) {
                try{
                    if (keys.isEmpty()) throw IllegalArgumentException("Keys array is empty.")
                    val ref = Firebase.database.reference
                    for (key in keys) { ref.child(key) }
                    val dataSnapshot = ref.get().await()
                    val data = dataSnapshot.getValue(T::class.java)
                    return@withContext Operation.Success(data!!)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error reading rdt data.")
                }
            }
        }

        suspend fun <T> writeDbData(path: String, objectToSave: T ) : Operation<Void> {
            return withContext(Dispatchers.IO) {
                try{
                    val ref = Firebase.database.reference.child(path)
                    val result = ref.setValue(objectToSave).await()
                    return@withContext Operation.Success(result)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error reading rdt data.")
                }
            }
        }

        suspend fun updateDbEntry(child: String, objectToUpdate: Any) : Operation<Void> {
            return withContext(Dispatchers.IO) {
                try{
                    val ref = Firebase.database.reference.child(child)
                    val update = hashMapOf( child to objectToUpdate )
                    val result = ref.updateChildren(update).await()
                    return@withContext Operation.Success(result)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating rdt data.")
                }
            }
        }

        suspend fun updateDbKeyValue(childs: Array<String>, keyToUpdate:String, newValue: Any) : Operation<Void> {
            return withContext(Dispatchers.IO) {
                try{
                    if (childs.isEmpty()) throw IllegalArgumentException("Keys array is empty.")
                    val ref = Firebase.database.reference
                    for (child in childs) { ref.child(child) }
                    val update = hashMapOf( keyToUpdate to newValue )
                    val result = ref.updateChildren(update).await()
                    return@withContext Operation.Success(result)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating rdt data.")
                }
            }
        }

        suspend fun updateDbKeyValue(path: String, keyToUpdate:String, newValue: Any) : Operation<Void> {
            return withContext(Dispatchers.IO) {
                try{
                    val ref = Firebase.database.reference.child(path)
                    val update = hashMapOf( keyToUpdate to newValue )
                    val result = ref.updateChildren(update).await()
                    return@withContext Operation.Success(result)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error updating rdt data.")
                }
            }
        }

    }

    object Storage {


        suspend fun uploadImageAndGetUrl(
            imageUri: Uri,
            folderPath : String,
            fileName : String,
        ) : Operation<String> {
            return withContext(Dispatchers.IO) {
                try{
                    val storage = Firebase.storage
                    val ref = storage.reference.child("$folderPath/$fileName")
                    ref.putFile(imageUri).await()
                    val downloadUrl = ref.downloadUrl.await()
                    return@withContext Operation.Success(downloadUrl.toString())
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error uploading image.")
                }
            }
        }

        suspend fun downloadJsonFile(path : String) : String {
            return withContext(Dispatchers.IO) {
                try {
                    val storage = Firebase.storage
                    val ref = storage.reference.child(path)
                    val localFile = File.createTempFile("temp", "json")
                    ref.getFile(localFile).await()
                    return@withContext localFile.readText()
                }catch (e : Exception) {
                    throw OperationException(e.message ?: "Unknown Error.")
                }
            }
        }

        suspend fun downloadTextFileFromUrl( url : String, suffix : String) : String {
            return withContext(Dispatchers.IO) {
                try {
                    val storage = Firebase.storage
                    val ref = storage.getReferenceFromUrl(url)
                    val localFile = File.createTempFile("temp", suffix)
                    ref.getFile(localFile).await()
                    return@withContext localFile.readText()
                }catch (e : Exception) {
                    throw OperationException(e.message ?: "Unknown Error.")
                }
            }
        }

        suspend fun downloadFolderFiles(folderPath : String) : Operation<List<String>> {
            return withContext(Dispatchers.IO) {
                try{
                    val storage = Firebase.storage
                    val listRef = storage.reference.child(folderPath)
                    val items = listRef.listAll().await().items
                    val output = mutableListOf<String>()
                    items.forEach { item -> output.add(item.name) }
                    return@withContext Operation.Success(output)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error getting folder files.")
                }
            }
        }

        suspend fun uploadTextFile(
            context : Context,
            folderPath : String,
            fileName : String,
            text : String
        ) : Operation<Any?> {
            return withContext(Dispatchers.IO) {
                try{
                    //val fileString = context.filesDir.toString() + "/" + fileName
                    //val fileToUpload = Uri.fromFile(File(fileString))
                    val storage = Firebase.storage
                    val ref = storage.reference.child("$folderPath/$fileName")
                    ref.putBytes(text.toByteArray()).await()
                    return@withContext Operation.Success(null)
                }catch (e : Exception) {
                    return@withContext Operation.Error(e.message ?: "Error uploading text file.")
                }
            }
        }

        /**
         * Download of a list of text objects from firebase storage.
         * @param folderPath String the path of the folder where the objects are stored.
         * @param fileType String the suffix of the files. es. (.json)
         * @return Operation<List<OB>> the list of objects downloaded.
         */
        suspend inline fun <reified OB> downloadStorageObjects(
            folderPath : String,
            fileType : TextFileSuffix,
            logger : LogUtils.Logger,
            crossinline isObjectValid : (OB) -> Boolean,
        ) : Operation<List<OB>> = withContext(Dispatchers.Default) {

            try {

                /* stampo log inizio e creo lista definitiva */
                val outputList = mutableListOf<OB>()
                logger.debug("Firebase storage objects of type ${OB::class.java.name} download started.")

                /* ottenimento lista file nella cartella */
                val fileList = ArrayList<String>()
                logger.debug("Getting list of files in folder $folderPath.")
                val op = downloadFolderFiles(folderPath)
                Operation.exec(
                    operation = op,
                    onSuccess = { list ->
                        fileList.addAll(list)
                        fileList.forEach{ file -> logger.debug("Found file : \"$file\"") }
                    },
                    onError = { throw OperationException(it ?: "Unknown error") }
                )

                /* per ogni file trovato nella directory:
                *  - ignoro quelli che non sono il tipo specificato
                *  - se è del tipo giusto, lo scarico ed estraggo il contenuto
                *  - controllo se il contenuto è valido
                *  - se i dati sono validi, creo un oggetto e lo aggiungo alla lista definitiva
                *  */
                when(fileType) {
                    /* formato json */
                    TextFileSuffix.JSON -> {
                        fileList.forEach lit@ { fileName ->
                            if (fileName.endsWith(fileType.suffix)) {
                                logger.debug("Object creation started for file \"$fileName\".")
                                val json = downloadJsonFile("$folderPath/$fileName")
                                logger.debug("Successfully downloaded json file \"$fileName\".")
                                try {
                                    val tempFile = Gson().fromJson(json, OB::class.java)
                                    logger.debug("Successfully parsed json file into object: $tempFile.")
                                    if (isObjectValid(tempFile)) {
                                        logger.debug("Current valuating object is valid. Adding to final list...")
                                        outputList.add(tempFile)
                                    } else {
                                        logger.warn("Object \"$fileName\" is not valid. Skipping it...")
                                        return@lit
                                    }
                                } catch (e: JsonSyntaxException) {
                                    logger.error( "Error occurred while parsing json file \"$fileName\": ${e.message}")
                                    logger.warn("Skipping object \"$fileName\"...")
                                    return@lit
                                }
                            } else {
                                logger.warn("File \"$fileName\" is not a json file. Ignoring it...")
                            }
                        }
                    }
                    else -> {
                        logger.debug("File type not supported.")
                        return@withContext Operation.Error("File type not supported.")
                    }
                }
                return@withContext Operation.Success(outputList)
            }catch (e : OperationException) {
                logger.error( "Error occurred while downloading dispense list: ${e.message}")
                return@withContext Operation.Error(e.message ?: "Unknown Error.")
            }
        }
    }

}


/**
 * Base class for FCM services.
 *
 * @property logTag String the tag used for logging.
 */
abstract class BaseFcmService(private val logTag : String) : FirebaseMessagingService() {

    val logger = LogUtils.Logger(logTag)

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        logger.warn("NEW FCM TOKEN RECEIVED: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        /* Controllo se messaggio contiene payload */
        logger.warn("New FCM message from: \"${remoteMessage.from}\".")
        if (remoteMessage.data.isNotEmpty()) {
            logger.debug("FCM message contains payload: \"${remoteMessage.data}\".")
        } else {
            logger.debug("FCM message dont contains payload: \"${remoteMessage.data}\".")
        }

        /* controllo se a msg è associata notifica */
        if(remoteMessage.notification != null) {
            handleNotification(remoteMessage)
        } else {
            logger.debug("FCM message dont contain notifications.")
        }
    }

    open fun handleNotification(remoteMessage: RemoteMessage) {
        logger.debug("FCM message contains notification! Reading...")
        val not = remoteMessage.notification!!
        logger.debug("Nuovo messaggio contiene title notifica: \"${not.title}\".")
        logger.debug("Nuovo messaggio contiene body notifica: \"${not.body}\".")
    }

    override fun onMessageSent(msgId: String) {
        super.onMessageSent(msgId)
        logger.debug("FCM message with id \"$msgId\" sent successfully.")
    }

    override fun onSendError(msgId: String, exception: java.lang.Exception) {
        super.onSendError(msgId, exception)
        logger.error("Error occurred while sending FCM message with id \"$msgId\": ${exception.message}")
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        logger.warn("FCM messages deleted.")
    }

}