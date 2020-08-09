/*
 * Copyright 2020 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.paulrybitskyi.commons.utils

import java.security.MessageDigest
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


private const val HASH_ALGORITHM_SHA1 = "SHA-1"
private const val HASH_ALGORITHM_HMAC_SHA256 = "HmacSHA256"
private const val HASH_ALGORITHM_MD5 = "MD5"


/**
 * Hashes the text using a SHA-1 algorithm.
 *
 * @param text The text to hash
 *
 * @return The SHA-1 hash of the text
 */
fun hashSha1(text: String): String {
    val messageDigest = MessageDigest.getInstance(HASH_ALGORITHM_SHA1)
    val resultBytes = messageDigest.digest(text.toByteArray())

    return toHexString(resultBytes)
}


/**
 * Hashes the text using a HmacSHA256 algorithm.
 *
 * @param secret The secret to use for the algorithm
 * @param text The text to hash
 *
 * @return The HmacSHA256 hash of the text
 */
fun hashHmacSha256(secret: String, text: String): String {
    val secretKey = SecretKeySpec(secret.toByteArray(), HASH_ALGORITHM_HMAC_SHA256)
    val algorithm = Mac.getInstance(HASH_ALGORITHM_HMAC_SHA256).apply {
        init(secretKey)
    }
    val hash = algorithm.doFinal(text.toByteArray())

    return toHexString(hash)
}


/**
 * Hashes the text using an MD5 algorithm.
 *
 * @param text The text to hash
 *
 * @return The MD5 hash of the text
 */
fun hashMD5(text: String): String {
    val messageDigest = MessageDigest.getInstance(HASH_ALGORITHM_MD5)
    val resultBytes = messageDigest.digest(text.toByteArray())

    return toHexString(resultBytes)
}


/**
 * Converts bytes to a hexadecimal string representation.
 *
 * @param bytes The bytes to convert
 *
 * @return The hexadecimal string of the bytes
 */
private fun toHexString(bytes: ByteArray): String {
     return Formatter().let {
         for(b in bytes) {
             it.format("%02x", b)
         }

         it.toString()
     }
}