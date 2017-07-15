
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * ImageViewResizable class
 *
 * <p>
 *     This class is used to make an ImageView resizable and also set an image to fit it's container
 * </p>
 *
 * @author Massimo Vlacancich
 * @version 1.0
 * @since 0.1
 */
public class ImageViewResizable extends ImageView {
    public ImageViewResizable() {
    }

    public ImageViewResizable(Image img) {
        super(img);
    }

    public ImageViewResizable(String url) {
        super(url);
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        super.setFitWidth(width);
        super.setFitHeight(height);
    }

}