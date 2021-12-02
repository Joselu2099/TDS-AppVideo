package gui.Util;

import tds.video.VideoWeb;

public class VideoWebFactory {
    private static VideoWeb instance;

    /*
     Este factoria en realidad no es para abstracción, sino para aportar singleton
     al VideoWeb ya que da problema con multiple instancia.

     Esto se podría resolverse si los metodos de getThumbnail es estatica...
    */
    public static VideoWeb getInstance (){
        if (instance == null){
            instance = new VideoWeb();
        }
        return instance;
    }
}
