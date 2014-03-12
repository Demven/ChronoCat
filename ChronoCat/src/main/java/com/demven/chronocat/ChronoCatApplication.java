package com.demven.chronocat;

import android.app.Application;
import com.demven.chronocat.managers.FontManager;

/**
 * @author Dmitry Salnikov (Demven)
 * @since 11.03.2014
 */
public class ChronoCatApplication extends Application {

    private FontManager fontManager;


    @Override
    public void onCreate(){
        fontManager = new FontManager();
    }



    public FontManager getFontManager() {
        return fontManager;
    }
}
