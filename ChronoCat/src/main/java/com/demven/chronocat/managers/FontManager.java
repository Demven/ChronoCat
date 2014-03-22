package com.demven.chronocat.managers;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Provides convenient methods to operate with fonts.
 * @author Dmitry Salnikov (Demven)
 * @since 11.03.2014
 */
public class FontManager {

    private final String FONTS_DIR = "fonts/";

    public static final String BRIE_LIGHT = "brie_light.ttf";
    public static final String ARL_NARROW = "arl_narrow.ttf";

    public Typeface getTypeface(String fontName, Context context){
        return Typeface.createFromAsset(context.getAssets(), FONTS_DIR + fontName);
    }
}
