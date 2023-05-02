package studify;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.inject.Injector;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import javafx.util.Callback;
import javafx.util.Pair;

public class MyFXML {

    private Injector injector;

    /**
     * Constructor of the MyFXML class
     * @param injector injector for the scenes
     */
    public MyFXML(Injector injector) {
        this.injector = injector;
    }

    /**
     * Method for loading the scenes
     * @param c class of the controller to be loaded
     * @param parts parts of the filepath
     * @return the pair of a scene and its controller
     * @param <T> the type of the controller
     */
    public <T> Pair<T, Parent> load(Class<T> c, String... parts) {
        try {
            var loader = new FXMLLoader(getLocation(parts), null, null, new MyFactory(), StandardCharsets.UTF_8);
            Parent parent = loader.load();
            T ctrl = loader.getController();
            return new Pair<>(ctrl, parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * creates and returns a URL from the parts provided
     * @param parts parts of the URL
     * @return the created URL
     */
    private URL getLocation(String... parts) {
        StringBuilder path = new StringBuilder();
        for(String s : parts) {
            path.append("/").append(s);
        }
        // Aici e ok.
        URL x = MyFXML.class.getResource(path.toString());
        return x;
    }

    private class MyFactory implements BuilderFactory, Callback<Class<?>, Object> {

        @Override
        @SuppressWarnings("rawtypes")
        public Builder<?> getBuilder(Class<?> type) {
            return (Builder) () -> injector.getInstance(type);
        }

        @Override
        public Object call(Class<?> type) {
            return injector.getInstance(type);
        }
    }
}