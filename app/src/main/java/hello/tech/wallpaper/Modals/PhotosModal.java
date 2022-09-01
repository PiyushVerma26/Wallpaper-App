package hello.tech.wallpaper.Modals;

import java.util.ArrayList;

public class PhotosModal {
    ArrayList<SrcModal> photos;

    public PhotosModal(ArrayList<SrcModal> photos) {
        this.photos = photos;
    }

    public ArrayList<SrcModal> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<SrcModal> photos) {
        this.photos = photos;
    }
}
