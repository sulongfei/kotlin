/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.script

import com.intellij.testFramework.LightProjectDescriptor
import org.jdom.output.XMLOutputter
import org.jetbrains.kotlin.idea.core.script.ScriptDefinitionsManager
import org.jetbrains.kotlin.idea.core.script.settings.KotlinScriptingSettings
import org.jetbrains.kotlin.idea.test.KotlinLightCodeInsightFixtureTestCase
import org.jetbrains.kotlin.idea.test.KotlinLightProjectDescriptor
import org.jetbrains.kotlin.test.JUnit3WithIdeaConfigurationRunner
import org.junit.runner.RunWith

@RunWith(JUnit3WithIdeaConfigurationRunner::class)
class ScriptOptionsSaveTest : KotlinLightCodeInsightFixtureTestCase() {

    fun testSaveAutoReload() {
        val project = myFixture.project
        val settings = KotlinScriptingSettings.getInstance(project)
        val definition = ScriptDefinitionsManager.getInstance(project).getAllDefinitions().first()
        val initialAutoReload = settings.autoReloadConfigurations(definition)

        settings.setAutoReloadConfigurations(0, definition, !initialAutoReload)

        assertEquals(
            "isAutoReloadEnabled should be set to true",
            "<KotlinScriptingSettings><scriptDefinition className=\"org.jetbrains.kotlin.idea.core.script.StandardIdeScriptDefinition\" definitionName=\"Kotlin Script\"><order>0</order><autoReloadConfigurations>true</autoReloadConfigurations></scriptDefinition></KotlinScriptingSettings>",
            XMLOutputter().outputString(settings.state)
        )

        settings.setAutoReloadConfigurations(0, definition, initialAutoReload)
    }

    fun testSaveScriptDefinitionOff() {
        val project = myFixture.project
        val scriptDefinition = ScriptDefinitionsManager.getInstance(project).getAllDefinitions().first()

        val settings = KotlinScriptingSettings.getInstance(project)

        val initialIsEnabled = settings.isScriptDefinitionEnabled(scriptDefinition)

        settings.setEnabled(-1, scriptDefinition, !initialIsEnabled)

        assertEquals(
            "scriptDefinition should be off",
            "<KotlinScriptingSettings><scriptDefinition><order>0</order><isEnabled>false</isEnabled></scriptDefinition></KotlinScriptingSettings>",
            XMLOutputter().outputString(settings.state).replace("scriptDefinition .*\">".toRegex(), "scriptDefinition>")
        )

        settings.setEnabled(-1, scriptDefinition, initialIsEnabled)
    }

    override fun getProjectDescriptor(): LightProjectDescriptor {
        return KotlinLightProjectDescriptor.INSTANCE
    }
}