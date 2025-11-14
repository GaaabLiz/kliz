package io.github.gaaabliz.kliz.common.model

import io.github.gaaabliz.kliz.common.util.GenUtils


/**
 * Represents login credentials for a user.
 *
 * @property username The user's username.
 * @property password The user's password.
 */
data class LoginDetails(val username:String, val password:String) {
    /**
     * Checks if either the username or the password (or both) are empty.
     * @return `true` if username or password is empty, `false` otherwise.
     */
    fun someDetailsEmpty() : Boolean = username.isEmpty() || password.isEmpty()
}

/**
 * Represents a software developer.
 *
 * @property avatarId An ID for the developer's avatar.
 * @property name The developer's first name.
 * @property surname The developer's last name.
 * @property email The developer's email address.
 */
data class Developer(
    val avatarId : Int,
    val name : String,
    val surname : String,
    val email : String,
) {
    companion object {
        /**
         * Generates a random [Developer] instance with predefined name and surname,
         * a random avatar ID, and an empty email.
         * @return A randomly generated [Developer] object.
         */
        fun random() : Developer {
            return Developer(
                avatarId = GenUtils.generateRandomInt(1, 10),
                name = "Mario",
                surname = "Rossi",
                email = ""
            )
        }
    }
}

/**
 * Represents the author of a project.
 *
 * @property name The author's first name.
 * @property surname The author's last name.
 * @property email The author's email address.
 * @property github An optional GitHub profile URL or username.
 */
data class ProjectAuthor(
    val name : String,
    val surname : String,
    val email : String,
    val github : String? = null,
)

