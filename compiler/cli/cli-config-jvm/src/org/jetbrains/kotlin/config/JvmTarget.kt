/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config

import org.jetbrains.kotlin.platform.TargetPlatformVersion
import org.jetbrains.org.objectweb.asm.Opcodes

enum class JvmTarget(override val description: String) : TargetPlatformVersion {
    JVM_1_6("1.6"),
    JVM_1_8("1.8"),
    JVM_9("9"),
    JVM_10("10"),
    JVM_11("11"),
    JVM_12("12"),
    JVM_13("13"),
    ;

    val bytecodeVersion: Int by lazy {
        when (this) {
            JVM_1_6 -> Opcodes.V1_6
            JVM_1_8 -> Opcodes.V1_8
            JVM_9 -> Opcodes.V9
            JVM_10 -> Opcodes.V10
            JVM_11 -> Opcodes.V11
            JVM_12 -> Opcodes.V12
            JVM_13 -> Opcodes.V12 + 1
        }
    }

    companion object {
        @JvmField
        val DEFAULT = JVM_1_6

        @JvmStatic
        fun fromString(string: String) = values().find { it.description == string }

        fun getDescription(bytecodeVersion: Int): String {
            val platformDescription = values().find { it.bytecodeVersion == bytecodeVersion }?.description ?: when (bytecodeVersion) {
                Opcodes.V1_7 -> "1.7"
                else -> null
            }

            return if (platformDescription != null) "JVM target $platformDescription"
            else "JVM bytecode version $bytecodeVersion"
        }
    }
}