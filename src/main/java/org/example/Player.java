package org.example;

import java.awt.*;

public class Player {
    public boolean right;
    public boolean left;
    public int x;
    public int y;
    public int wigth;
    public int heigth;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.wigth = 40;
        this.heigth = 3;
    }

    public void tick() {
        if (this.right) {
            ++this.x;
        } else if (this.left) {
            --this.x;
        }

        if (this.x + this.wigth > Game.WIDTH) {
            this.x = Game.WIDTH - this.wigth;
        } else if (this.x < 0) {
            this.x = 0;
        }

    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(this.x, this.y, this.wigth, this.heigth);
    }
}