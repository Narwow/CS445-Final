/**
 * Keeps x, y, and z attributes of camera.
 * @author 
 */
public class Vector3Float {
    private float x, y, z;
    
    /**
     * Constructor. Takes in x, y, and z coordinates and sets them
     * in this object.
     * @param x
     * @param y
     * @param z 
     */
    public Vector3Float(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * Returns the x value.
     * @return 
     */
    public float getX() {
        return x;
    }
    
    /**
     * Sets x value, used for modifications for camera movement
     * @param x 
     */
    public void setX(float x) {
        this.x = x;
    }
    
    /**
     * Returns y value
     * @return 
     */
    public float getY() {
        return y;
    }
    
    /**
     * Sets y value. Used for camera movement
     * @param y 
     */
    public void setY(float y) {
        this.y = y;
    }
    
    /**
     * Returns z value.
     * @return 
     */
    public float getZ() {
        return z;
    }
    
    /**
     * Sets new z value, used for camera movements.
     * @param z 
     */
    public void setZ(float z) {
        this.z = z;
    }
    
}
