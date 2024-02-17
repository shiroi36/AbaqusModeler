/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.section;

import util.LineInfo;
import util.coord.CoordXYZ;

/**
 *
 * @author keita
 */
public interface SectionInterface {
    //XY平面上の断面形状を返す。
    public LineInfo[] get2DShape(int RSeparationNumber);
    public CoordXYZ[] getTextPosition(int type);
}
