/*******************************************************
* file: Basic3D.java
* authors: Alex Kimea, Jhuo Wei Ku, Andy Liang
* class: CS 445 - Computer Graphics
*
* assignment: Final Program
* date last modified: 5/5/2017
*
* purpose: This program uses the LWJGL library to draw a window of 640x480
* in the center of the screen. The program will create an original scene in 
* Minecraft fashion.
* 
* This class is just a holder for later on calling the engine/ui
* 
* Check Point 1 Requirements:
*   1) a window created that is 640x480 and centered on the screen
*   2) display a cube (which is at least 2 in width) in 3D space with each face colored differently
*   3) navigate the environment using the input.Keyboard class with either the w,a,s,d keys
*   4) escape key quit your application
*/

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 * The main class, initializes program parts such as
 * FPCamera, the window on user's screen, and starts the game.
 * @author 
 */
public class Basic3D {
    private FPCameraController fp;
    private DisplayMode displayMode;
    
    /**
     * Constructor. Instantiates the FPCamera.
     */
    public Basic3D() {
        //fp = new FPCameraController(0f, 0f, 0f);
    }
    
    /**
     * Calls methods to create window on screen, initializes
     * GL, and begins the game.
     */
    public void start() {
        try {
            createWindow();
            initGL();
            fp = new FPCameraController(0f, 0f, 0f);
            fp.gameLoop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Creates a nonfullscreen window of 640x480 with attributes.
     * @throws Exception 
     */
    private void createWindow() throws Exception {
        Display.setFullscreen(false);
        DisplayMode d[] = Display.getAvailableDisplayModes();
        
        for(int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640 && d[i].getHeight() == 480 && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;
            }
        }
        Display.setDisplayMode(displayMode);
        Display.setTitle("Final Project");
        Display.create();
    }
    
    /**
     * Initializes GL attributes and matrices.
     */
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(100.0f, (float)displayMode.getWidth() / (float)
        displayMode.getHeight(), 0.1f, 300.0f);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
        glEnable(GL_DEPTH_TEST);
        
        glEnable(GL_TEXTURE_2D);
        glEnableClientState (GL_TEXTURE_COORD_ARRAY);
    }
    
    /**
     * Main method, creates Basic3D object to start the program.
     * @param args 
     */
    public static void main(String args[]) {
        System.out.println("hi");
        
        Basic3D basic = new Basic3D();
        basic.start();
    }
}
