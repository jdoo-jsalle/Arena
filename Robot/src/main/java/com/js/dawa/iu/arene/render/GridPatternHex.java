package com.js.dawa.iu.arene.render;

import java.awt.Graphics2D;
import java.awt.Polygon;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.position.Position;

public class GridPatternHex implements GridPattern {

    int mDecal = 0;
    int mSizeArene = 4;
    int mSizeCase = 20; // rayon de l’hexagone

    @Override
    public void init(Arene pArene) {
        mSizeArene = pArene.getAreneProps().getSize();
    }

    @Override
    public void paint(Graphics2D g) {
        double w = Math.sqrt(3) * mSizeCase;
        double h = 2 * mSizeCase;
        for (int r = 0; r < mSizeArene; r++) {
            for (int q = 0; q < mSizeArene; q++) {
                Position pos = transform(new Position(q + 1, r + 1));
                Polygon hex = hexagon((int) pos.getX(), (int) pos.getY(), mSizeCase);
                g.drawPolygon(hex);
            }
        }
    }

    private Polygon hexagon(int x, int y, int size) {
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = Math.PI / 3.0 * i + Math.PI / 6.0; // flat-topped
            int px = (int) (x + size * Math.cos(angle));
            int py = (int) (y + size * Math.sin(angle));
            p.addPoint(px, py);
        }
        return p;
    }

    @Override
    public Position transform(Position p) {
        // q = colonne, r = ligne (1-based)
        int q = (int) p.getX() - 1;
        int r = (int) p.getY() - 1;
        double w = Math.sqrt(3) * mSizeCase;
        double h = 2 * mSizeCase;
        // Décalage horizontal pour chaque ligne impaire
        double x = mDecal + w * q + (r % 2) * (w / 2);
        double y = mDecal + r * (3.0 / 4.0) * h;
        return new Position(x, y, p.getAxe());
    }
}