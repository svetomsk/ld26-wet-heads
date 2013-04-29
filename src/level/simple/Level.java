package level.simple;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;
import main.Input;


public class Level 
{
    static int n = 20, m;
    static byte [][] field;
    static int width, height;
    static int size;
    
    static int vector = 0;        
    static Snake snake;
    
    static int fps = 7;
    static int curf = 0;
    
    static Color apple = Color.decode("#808064");
    static Color wall = Color.decode("#808080");
    static Color back = Color.decode("#C0C0C0");
    static Color sn = Color.decode("#5E5E00");
    
    static int applex, appley;
    static boolean eaten = true;
    
    static Input input;
    
    static public void init(int w, int h, Input input)
    {
        Level.input = input;        
        width = w; height = h ;          
        m = n;
        size = height/m; 
        field = new byte[n][m];
        snake = new Snake(n/2, m/2);
        refresh();
    }
    
    static public void refresh()
    {
        field = new byte[n][m];
        for(int i = 0; i < n; i++)
        {
            field[i][0] = -1;
            field[i][n-1] = -1;
        }
        for(int i = 0; i < m; i++)
        {            
            field[0][i] = -1;
            field[n-1][i] = -1;
        }
    }
    
    static public void draw(Graphics2D g)
    {        
        g.setColor(wall);
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(field[i][j] == -1)
                    g.setColor(wall);
                else
                    g.setColor(back);
                g.fillRect(i*size, j*size, size, size);
            }
        }
        g.setColor(sn);
        for(Point p : Snake.body)
        {
            g.fillRect(p.x * size, p.y * size, size, size);
        }
        g.setColor(apple);
        g.fillRect(applex * size, appley * size, size, size);
    }
    
    static public void tick()
    {
        if(curf % fps == 0)
        {            
            if(eaten)
                spawn();
            int x = Snake.x;
            int y = Snake.y;
            if(vector == 0)
            {
                if(field[x-1][y] == 0)
                {                        
                    isApple(x-1, y);
                    Snake.moveUp();                    
                }
                else
                {
    //                System.out.println("finish");
                }
            }
            if(vector == 1)
            {
                if(field[x][y-1] == 0)
                {                    
                    isApple(x, y-1);
                    Snake.moveLeft();
                }
                else
                {
    //                System.out.println("finish");
                }
            }
            if(vector == 2)
            {            
                if(field[x+1][y] == 0)
                {                    
                    isApple(x+1, y);
                    Snake.moveDown();
                }
                else
                {
    //                System.out.println("finish");
                }
            }
            if(vector == 3)
            {
                if(field[x][y+1] == 0)
                {                    
                    isApple(x, y+1);
                    Snake.moveRight();
                }
    //            else
    //                System.out.println("finish");
            }
            Level.refresh();
            Snake.fillField();
        }
        updateKeyState();
        curf = (curf + 1)%62;
        
    }
    
    static private void isApple(int x, int y)
    {
        if(x == applex && y == appley)
        {
            Snake.addTail();
            eaten = true;
        }
    }
    
    static private void spawn()
    {
        boolean c = false;
        while(!c)
        {
            Random r = new Random();
            int genx = 0 + r.nextInt(n);
            int geny = 0 + r.nextInt(m);
            if(field[genx][geny] == 0)
            {
                applex = genx;
                appley = geny;
                c = true;
                eaten = false;
            }
        }
    }
    
    static public void setVector(int vec)
    {
        if(vec != (vector + 2) % 4)
            vector = vec;
    }
    
    static private void updateKeyState()
    {
        if(input.left.down) Level.setVector(0);
        if(input.up.down) Level.setVector(1);
        if(input.right.down) Level.setVector(2);
        if(input.down.down) Level.setVector(3);
    }
}

//class Main extends JFrame implements Runnable
//{
//    Main()
//    {
//        setSize(600, 600);
//        setUndecorated(true);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        Level.init(getWidth(), getHeight());
//        setVisible(true);
//        addKeyListener(new KeyAdapter() 
//        {
//
//            @Override
//            public void keyPressed(KeyEvent e) 
//            {
//                int code = e.getKeyCode();
//                if(code == KeyEvent.VK_LEFT)
//                {
//                    Level.setVector(0);
//                }
//                if(code == KeyEvent.VK_UP)
//                {
//                    Level.setVector(1);
//                }
//                if(code == KeyEvent.VK_RIGHT)
//                {
//                    Level.setVector(2);
//                }
//                if(code == KeyEvent.VK_DOWN)
//                {
//                    Level.setVector(3);
//                }
//                if(code == KeyEvent.VK_T)
//                {
//                    Snake.addTail();
//                }
//            }            
//        });
//    }
//
//    @Override
//    public void paint(Graphics g) 
//    {
//        Level.draw(g);
//    }   
//    
//    public static void main(String [] a)
//    {
//        Main m = new Main();
//        m.start();        
//    }
//
//    @Override
//    public void run() 
//    {
//        while(true)
//        {
//            Level.tick();
//            repaint();
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            }
//        }
//    }
//    
//    public void start()
//    {
//        new Thread(this, "Game thread").start();
//    }   
//}
