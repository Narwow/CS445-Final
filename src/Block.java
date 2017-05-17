/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joe Ku
 */
public class Block {

    private boolean IsActive;
    private BlockType Type;
    private float x, y, z;

    //Define the block types
    public enum BlockType {
        BlockType_Grass(0),
        BlockType_Sand(1),
        BlockType_Water(2),
        BlockType_Dirt(3),
        BlockType_Stone(4),
        BlockType_Bedrock(5);
        private int BlockID;

        BlockType(int i) {
            BlockID = i;
        }

        public int GetID() {
            return BlockID;
        }

        public void SetID(int i) {
            BlockID = i;
        }
    }

    //Constructor
    public Block(BlockType type) {
        Type = type;
    }

    //Set coordinate position
    public void setCoords(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //Return true if active, false otherwise
    public boolean IsActive() {
        return IsActive;
    }

    //Set block status to active
    public void SetActive(boolean active) {
        IsActive = active;
    }

    //Get ID
    public int GetID() {
        return Type.GetID();
    }
}
