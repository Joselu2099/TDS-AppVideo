package gui.VideoPreview;

import model.Video;

public interface VideoPreviewCallback {
    // Observer callback
    void clicked(Video v);
}
