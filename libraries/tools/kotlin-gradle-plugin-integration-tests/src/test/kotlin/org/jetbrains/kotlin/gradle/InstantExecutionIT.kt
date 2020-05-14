/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle

import org.junit.Test

class InstantExecutionIT : BaseGradleIT() {
    @Test
    fun testInstantExecution() {
        val project = Project("instantExecution")

        project.build("build", "-Dorg.gradle.unsafe.instant-execution=true") {
            assertSuccessful()
            assertTasksExecuted(
                ":compileKotlin",
                ":compileTestKotlin"
            )
        }

    }

    @Test
    fun testInstantExecutionForJs() {
        val project = Project("instantExecutionToJs")

        project.build("build", "-Dorg.gradle.unsafe.instant-execution=true") {
            assertSuccessful()

            assertTasksExecuted(
                ":compileKotlin2Js",
                ":compileTestKotlin2Js"
            )

            assertFileExists("build/kotlin2js/main/module.js")
            assertFileExists("build/kotlin2js/test/module-tests.js")
        }

    }
}