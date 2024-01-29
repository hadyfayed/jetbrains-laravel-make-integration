package com.github.niclasvaneyk.laravelmake.plugin.jetbrains.listener

import com.github.niclasvaneyk.laravelmake.plugin.jetbrains.services.LaravelMakeProjectService
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class ProjectOpenedListener: ProjectActivity {
    override suspend fun execute(project: Project) {
        val application = project.service<LaravelMakeProjectService>().application

        if (application == null) {
            val log = logger<LaravelMakeProjectService>()
            log.info("${project.name} is not Laravel project")
            return
        }

        application.initialize()
    }
}