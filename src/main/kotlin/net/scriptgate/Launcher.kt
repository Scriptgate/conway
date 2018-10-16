package net.scriptgate

import net.scriptgate.conway.Conway
import net.scriptgate.engine.Application
import net.scriptgate.engine.InputComponent
import net.scriptgate.engine.Renderer
import net.scriptgate.engine.lwjgl.OpenGLApplicationHandler

import net.scriptgate.engine.Engine.HEIGHT
import net.scriptgate.engine.Engine.WIDTH

class Launcher : Application {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            OpenGLApplicationHandler().start(Conway())
        }
    }

}
