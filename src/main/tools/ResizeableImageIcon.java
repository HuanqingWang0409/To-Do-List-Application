package main.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ResizeableImageIcon extends ImageIcon{
    public ResizeableImageIcon(String str) {
        super(str);
    }

    public void scaleImage(int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(this.getImage(), 0, 0, w, h, null);
        g2.dispose();

        setImage(resizedImg);
    }
}