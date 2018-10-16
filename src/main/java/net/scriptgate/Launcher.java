package net.scriptgate;

import net.scriptgate.conway.Conway;
import net.scriptgate.engine.Application;
import net.scriptgate.engine.InputComponent;
import net.scriptgate.engine.Renderer;
import net.scriptgate.engine.lwjgl.OpenGLApplicationHandler;

import static net.scriptgate.engine.Engine.HEIGHT;
import static net.scriptgate.engine.Engine.WIDTH;

public class Launcher implements Application {

    public static void main(String[] args) {
        new OpenGLApplicationHandler().start(new Conway());
    }

}
