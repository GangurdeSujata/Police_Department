/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package policedepartment;

import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JOptionPane;

/**
 *
 * @author Gokul
 */
public class PoliceDepartment {

 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         progressbar r=new progressbar();
 r.setVisible(true);
   int result = JOptionPane.showConfirmDialog(null, "Click on ok to go further !!!");
      switch (result) {
         case JOptionPane.YES_OPTION:
     
         break;
         case JOptionPane.NO_OPTION:
         System.exit(WIDTH);
         break;
      case JOptionPane.CANCEL_OPTION:
    System.exit(WIDTH);
       break;
         case JOptionPane.CLOSED_OPTION:
         System.out.println("Closed");
         break;
      }
  LoginScreen d=new LoginScreen();
  try
   {
 for(int x=0;x<=100;x++)
 {
     
 Thread.sleep(50);
// progressbar.per.setText(Integer.toString(x)+"%");
 
                if(x==100)
                {     
                    d.setVisible(true);
                   r.dispose();
                    d.show();
                    
                }
 progressbar.loardbar.setValue(x);
 }
  }
  catch(Exception e){}  
  //for(int x=0;x<=100;x++)
 //{
   // progressbar.loardbar.setValue(x);
// }
       }
                                        
}  