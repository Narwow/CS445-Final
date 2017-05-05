import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.Sys;

import static org.lwjgl.opengl.GL11.*;

public class FPCameraController {
    private Vector3Float position;
    private Vector3Float lPosition;
    private Vector3Float me;
    
    private float yaw;
    private float pitch;
    
    public FPCameraController(float x, float y, float z) {
        position = new Vector3Float(x, y, z);
        
        lPosition = new Vector3Float(x, y, z);
        lPosition.setX(0f);
        lPosition.setY(15f);
        lPosition.setZ(0f);
        
        yaw = 0.0f;
        pitch = 0.0f;
    }
    
    public void yaw(float amount) {
        yaw += amount;
    }
    
    public void pitch(float amount) {
        pitch -= amount;
    }
    
    public void walkForward(float distance) {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        
        position.setX(position.getX() - xOffset);
        position.setZ(position.getZ() + zOffset);
    }
    
    public void walkBackwards(float distance) {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        
        position.setX(position.getX() + xOffset);
        position.setZ(position.getZ() - zOffset);
    }
    
    public void strafeLeft(float distance) {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw-90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw-90));
        
        position.setX(position.getX() - xOffset);
        position.setZ(position.getZ() + zOffset);
    }
    
    public void strafeRight(float distance) {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw+90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw+90));
        
        position.setX(position.getX() - xOffset);
        position.setZ(position.getZ() + zOffset);
    }
    
    public void moveUp(float distance) {
        position.setY(position.getY() - distance);
    }
    
    public void moveDown(float distance) {
        position.setY(position.getY() + distance);
    }
    
    public void lookThrough() {
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        glTranslatef(position.getX(), position.getY(), position.getZ());
    }
    
    public void setYaw(float amount) {
        this.yaw = amount;
    }
    
    public void setPitch(float amount) {
        this.pitch = amount;
    }
    
    public void gameLoop() {
        FPCameraController camera = new FPCameraController(0, 0, 0);
        
        float dx = 0.0f;
        float dy = 0.0f;
        float dt = 0.0f;
        float lastTime = 0.0f; 
        long time = 0;
        float mouseSensitivity = 0.09f;
        float movementSpeed = .35f;
        
        Mouse.setGrabbed(true);
        
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            time = Sys.getTime();
            lastTime = time;
            
            dx = Mouse.getDX();
            dy = Mouse.getDY();
            
            camera.yaw(dx * mouseSensitivity);
            camera.pitch(dy * mouseSensitivity);
            
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                camera.walkForward(movementSpeed);
            }
            
            if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                camera.walkBackwards(movementSpeed);
            }
            
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                camera.strafeLeft(movementSpeed);
            }
        
            if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                camera.strafeRight(movementSpeed);
            }
    
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                camera.moveUp(movementSpeed);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                camera.moveDown(movementSpeed);
            }
            
            glLoadIdentity();
            camera.lookThrough();
            glEnable(GL_DEPTH_TEST); //had to use this. If not enabled cube shows other side's colors and looks weird.
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            render();
          
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }
    
    private void render() {
        try {
            glBegin(GL_QUADS);
            //Top
            glColor3f(0.0f,0.0f,1.0f);
            glVertex3f( 1.0f, 1.0f,-1.0f);
            glVertex3f(-1.0f, 1.0f,-1.0f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glVertex3f( 1.0f, 1.0f, 1.0f);
            //Bottom
            glColor3f(1.0f,0.0f,1.0f);
            glVertex3f( 1.0f,-1.0f, 1.0f);
            glVertex3f(-1.0f,-1.0f, 1.0f);
            glVertex3f(-1.0f,-1.0f,-1.0f);
            glVertex3f( 1.0f,-1.0f,-1.0f);
            //Front
            glColor3f(1.0f,0.0f,0.0f);
            glVertex3f( 1.0f, 1.0f, 1.0f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glVertex3f(-1.0f,-1.0f, 1.0f);
            glVertex3f( 1.0f,-1.0f, 1.0f);
            //Back
            glColor3f(0.0f,1.0f,1.0f);
            glVertex3f( 1.0f,-1.0f,-1.0f);
            glVertex3f(-1.0f,-1.0f,-1.0f);
            glVertex3f(-1.0f, 1.0f,-1.0f);
            glVertex3f( 1.0f, 1.0f,-1.0f);
            //Left
            glColor3f(1.0f,1.0f,0.0f);
            glVertex3f(-1.0f, 1.0f,1.0f);
            glVertex3f(-1.0f, 1.0f,-1.0f);
            glVertex3f(-1.0f,-1.0f,-1.0f);
            glVertex3f(-1.0f,-1.0f, 1.0f);
            //Right
            glColor3f(0.0f,1.0f,0.0f);
            glVertex3f(1.0f, 1.0f,-1.0f);
            glVertex3f(1.0f, 1.0f, 1.0f);
            glVertex3f(1.0f,-1.0f, 1.0f);
            glVertex3f(1.0f,-1.0f,-1.0f);
            glEnd();     
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
