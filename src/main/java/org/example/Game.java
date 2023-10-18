package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Game extends Canvas implements Runnable, KeyListener {
    private static final long serialVersionUID = 1L;
    public static int WIDTH = 160;
    public static int HEIGTH = 120;
    public static int SCALE = 3;
    public BufferedImage layer;
    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    public Game() {
        this.layer = new BufferedImage(WIDTH, HEIGTH, 4);
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGTH * SCALE));
        this.addKeyListener(this);
        player = new Player(100, HEIGTH - 3);
        enemy = new Enemy(100, 0);
        ball = new Ball(100, HEIGTH / 2);
    }


    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo((Component)null);
        frame.setVisible(true);
        (new Thread(game)).start();
    }

    public void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
        } else {
            Graphics g = this.layer.getGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH * SCALE, HEIGTH * SCALE);
            player.render(g);
            enemy.render(g);
            ball.render(g);
            g = bs.getDrawGraphics();
            g.drawImage(this.layer, 0, 0, WIDTH * SCALE, HEIGTH * SCALE, (ImageObserver)null);
            bs.show();
        }
    }

    @Override
    public void run() {
        this.requestFocus();

        while(true) {
            this.tick();
            this.render();

            try {
                Thread.sleep(16L);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 39) {
            player.right = true;
        } else if (e.getKeyCode() == 37) {
            player.left = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 39) {
            player.right = false;
        } else if (e.getKeyCode() == 37) {
            player.left = false;
        }

    }
}

