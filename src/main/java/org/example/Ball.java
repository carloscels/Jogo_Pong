package org.example;

import java.awt.*;
import java.util.Random;

public class Ball {
    public double x;
    public double y;
    public int wigth;
    public int heigth;
    public double dx;
    public double dy;
    public double speed = 1.5D;

    public Ball(int x, int y) {
        this.x = (double)x;
        this.y = (double)y;
        this.wigth = 4;
        this.heigth = 4;
        int angle = (new Random()).nextInt(110) + 40;
        this.dx = Math.cos(Math.toRadians((double)angle));
        this.dy = Math.sin(Math.toRadians((double)angle));
    }

    public void tick() {
        if (this.x + this.dx * this.speed + (double)this.wigth > (double)Game.WIDTH) {
            this.dx *= -1.0D;
        } else if (this.x + this.dx * this.speed < 0.0D) {
            this.dx *= -1.0D;
        }

        if (this.y > (double)Game.HEIGTH) {
            System.out.println("ponto do Enemy");
            new Game();
        } else if (this.y < 0.0D) {
            System.out.println("ponto do Player");
            new Game();
        } else {
            Rectangle bounds = new Rectangle((int)(this.x + this.dx * this.speed), (int)(this.y + this.dy * this.speed), this.wigth, this.heigth);
            Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.wigth, Game.player.heigth);
            Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.wigth, Game.enemy.heigth);
            int angle;
            if (bounds.intersects(boundsEnemy)) {
                angle = (new Random()).nextInt(110) + 40;
                this.dx = Math.cos(Math.toRadians((double)angle));
                this.dy = Math.sin(Math.toRadians((double)angle));
                if (this.dy < 0.0D) {
                    this.dy *= -1.0D;
                }
            } else if (bounds.intersects(boundsPlayer)) {
                angle = (new Random()).nextInt(110) + 40;
                this.dx = Math.cos(Math.toRadians((double)angle));
                this.dy = Math.sin(Math.toRadians((double)angle));
                if (this.dy > 0.0D) {
                    this.dy *= -1.0D;
                }
            }

            this.x += this.dx * this.speed;
            this.y += this.dy * this.speed;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int)this.x, (int)this.y, this.wigth, this.heigth);
    }
}
