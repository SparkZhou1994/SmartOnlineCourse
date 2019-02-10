package spark.course.security;

/**
 * @ClassName ValidateCodeProperties
 * @Description TODO
 * @Author Spark
 * @Date 2/10/2019 7:16 PM
 * @Version 1.0
 **/
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
