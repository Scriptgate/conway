package net.scriptgate.conway;

import net.scriptgate.engine.Application;
import net.scriptgate.engine.InputComponent;
import net.scriptgate.engine.Renderer;
import net.scriptgate.engine.lwjgl.OpenGLApplicationHandler;

import static net.scriptgate.engine.Engine.HEIGHT;
import static net.scriptgate.engine.Engine.WIDTH;

public class Launcher implements Application {

    private Conway conway;

    public static void main(String[] args) {
        new OpenGLApplicationHandler().start(new Launcher());
    }

    public Launcher() {

    }

    @Override
    public void initialize() {
        this.conway = new Conway(WIDTH, HEIGHT);

    }

    @Override
    public void onTick(InputComponent inputComponent, double elapsedTime) {
        conway.update();
    }

    @Override
    public void render(Renderer renderer) {
        conway.render(renderer);
    }
}
