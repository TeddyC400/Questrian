import net.minestom.server.extensions.Extension;

public class QuestrianExtension extends Extension {

    @Override
    public void initialize() {
        System.out.println("Initializing Questrian Extension");
    }

    @Override
    public void terminate() {
        System.out.println("Terminating Questrian Extension");
    }

}
