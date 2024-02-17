/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JointCreator;

import JointCreator.ID.BID;
import JointCreator.ID.CFRID;
import JointCreator.ID.CID;
import JointCreator.ID.CTPID;
import JointCreator.ID.CWRID;
import JointCreator.ID.KPID;
import JointCreator.ID.SHID;
import JointCreator.ID.TPID;
import util.dxf.DXFSettings;

/**
 *
 * @author keita
 */
public class WCSJointID {
    
    private String ipath;
    private CID cid;
    private BID bid;
    private CFRID cfrid;
    private CWRID cwrid;
    private SHID shid;
    private KPID kpid;
    private CTPID ctpid;
    private TPID tpid;
    
    public void setC(CID cid){this.cid=cid;}
    public void setB(BID bid){this.bid=bid;}
    public void setCFR(CFRID cfrid){this.cfrid=cfrid;}
    public void setCWR(CWRID cwrid){this.cwrid=cwrid;}
    public void setSH(SHID shid){this.shid=shid;}
    public void setKP(KPID aid){this.kpid=aid;}
    public void setCTP(CTPID ctpid){this.ctpid=ctpid;}
    public void setTP(TPID tpid){this.tpid=tpid;}
    public void setIconPath(String icon){this.ipath=icon;}
    public String getIconPath(){return ipath;}
    public CID getCID(){return cid;}
    public BID getBID(){return bid;}
    public CFRID getCFRID(){return cfrid;}
    public CWRID getCWRID(){return cwrid;}
    public SHID getSHID(){return shid;}
    public KPID getKPID(){return kpid;}
    public CTPID getCTPID(){return ctpid;}
    public TPID getTPID(){return tpid;}
    
    public String[] getLayerNameArray(){
        return new String[]{
            KPID.ICON_KP,
            BID.ICON_B,
            CID.ICON_C,
            CWRID.ICON_CWR,
            CFRID.ICON_CFR,
            SHID.ICON_SH,
            "text"
        };
    }
    
    public int[] getLayerColorArray(){
        return new int[]{
            DXFSettings.DXF_PURPLE,
            DXFSettings.DXF_WHITE,
            DXFSettings.DXF_WHITE,
            DXFSettings.DXF_CYAN,
            DXFSettings.DXF_CYAN,
            DXFSettings.DXF_GREEN,
            DXFSettings.DXF_YELLOW
        };
    }
    
    public String[] getLayerLineArray() {
        return new String[]{
            DXFSettings.DXF_CONTINUOUS,
            DXFSettings.DXF_CONTINUOUS,
            DXFSettings.DXF_CONTINUOUS,
            DXFSettings.DXF_CONTINUOUS,
            DXFSettings.DXF_CONTINUOUS,
            DXFSettings.DXF_CONTINUOUS,
            DXFSettings.DXF_CONTINUOUS,
        };
    }

}
