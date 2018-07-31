/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shivani Pathak
 */
public class abc {
    
    
    public static void main(String[] args) {
        
        
                    String encodearr[]= new String[]{"%3A","%2F","%3F","%3D","%26","%25","%2C","%3B","%22"};
                    String decodearr[] = new String[]{":","/","?","=","&","%",",",";","\""};
                      String s="https%3A%2F%2Fr13---sn-qxa7en7s.googlevideo.com%2Fvideoplayback%3Fei%3DwTt3WMfaO5KxogOv0ZPgDQ%26key%3Dyt6%26keepalive%3Dyes%26initcwndbps%3D67500%26expire%3D1484230690%26pl%3D24%26itag%3D244%26mt%3D1484199659%26ms%3Dau%26requiressl%3Dyes%26mime%3Dvideo%252Fwebm%26dur%3D269.060%26id%3Do-ACORN1lgPBqHDJDppMmLZ8G4eHvWoAzopWClpw_fP3WR%26mn%3Dsn-qxa7en7s%26mm%3D31%26ip%3D103.18.167.26%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cinitcwndbps%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cupn%252Cexpire%26mv%3Dm%26lmt%3D1449599724751451%26ipbits%3D0%26gir%3Dyes%26upn%3D5baFOQTZbxQ%26source%3Dyoutube%26clen%3D13742053";
                        for(int i=0;i<encodearr.length;i++)
                        {
                            s = s.replaceAll(encodearr[i], decodearr[i]);
                        }
                        System.out.println(s);
                      
    }
}
