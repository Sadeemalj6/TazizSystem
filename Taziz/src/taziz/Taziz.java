/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taziz;

/**
 *
 * @author sdoom
 */
public class Taziz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new AddEvent().setVisible(true);
        new AvailableEvents().setVisible(true);
        ConfirmationFile.createConfirmation();
    }
    
}
