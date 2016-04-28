package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

public class ImageReaderFactory implements ImageReader{
    public static ImageReader getReader(ImageTypes imgType) {
        switch (imgType){
            case BMP:return new BmpReader();
            case JPG:return new JpgReader();
            case PNG:return new PngReader();
            default:throw new IllegalArgumentException("Неизвестный тип картинки");
        }
//        ImageReader reader;
//        if (imgType==ImageTypes.BMP) reader=new BmpReader();
//        else if(imgType==ImageTypes.JPG) reader=new JpgReader();
//        else if(imgType==ImageTypes.PNG) reader=new PngReader();
//        else throw new IllegalArgumentException("Неизвестный тип картинки");
//        return reader;
    }
}
