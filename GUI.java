import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener{
    
    private JPanel kontener;
    private JPanel plansza;
    private JPanel legenda;
    private JPanel komentator;
    private JPanel akcje; 
    private JTextArea txt;
    private JButton kolejna_tura;
    private JButton zapis;
    private JButton wczyt;
    private JButton super_moc;
    private Swiat swiat;
    private Color colo;
    private Organizm[][] organizmy;
    private int kierunek;
    private boolean czyWykonywac;
    private boolean czyTura;
    private boolean czyAktywna;

    
    public GUI(Swiat sw){
        setTitle("Trójkąty i kwadraty");
        setSize(960, 960);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.kierunek = 0;
        this.czyAktywna = false;
        this.czyWykonywac = false;
        this.czyTura = true;
        this.swiat = sw;
        this.colo = Color.GREEN;
        this.organizmy = new Organizm[20][20];

        this.kontener = new JPanel();
        this.plansza = new JPanel();
        this.legenda = new JPanel();
        this.komentator = new JPanel();
        this.akcje = new JPanel();
        this.kontener.setLayout(new GridLayout(2, 2, 100, 350));
        this.kontener.add(this.plansza);
        this.kontener.add(this.legenda);
        this.kontener.add(this.komentator);
        this.kontener.add(this.akcje);
        add(this.kontener);
        this.txt = new JTextArea(15, 37);

        this.txt.setEditable(false);
        this.komentator.add(this.txt);

        this.akcje.setLayout(new GridLayout(4, 0, 0, 0));
    
        this.kolejna_tura = new JButton();
        this.super_moc = new JButton();
        this.zapis = new JButton();
        this.wczyt = new JButton();

        this.kolejna_tura.setFocusable(false);
        this.super_moc.setFocusable(false);
        this.zapis.setFocusable(false);
        this.wczyt.setFocusable(false);

        this.zapis.setText("ZAPISZ STAN ŚWIATA DO PLIKU");
        this.wczyt.setText("WCZYTAJ DANE O ŚWIECIE Z PLIKU");
        this.kolejna_tura.setText("PRZEJDŹ DO KOLEJNEJ TURY");
        this.super_moc.setText("AKTYWUJ SUPER MOC");

        this.kolejna_tura.addActionListener(this);
        this.zapis.addActionListener(this);
        this.wczyt.addActionListener(this);
        this.super_moc.addActionListener(this);

        this.akcje.add(this.kolejna_tura);
        this.akcje.add(this.super_moc);
        this.akcje.add(this.wczyt);
        this.akcje.add(this.zapis);

        JScrollPane scroll = new JScrollPane(this.txt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.komentator.add(scroll);

        for(int i=0; i<20; i++)
        {
            for(int j=0; j<20; j++)
            {
                this.organizmy[i][j] = null;
            }
        }
    }

    @Override
    public void paint(Graphics g){

        for(int i=0; i<20; i++)
        {
            
            for(int j=0; j<20; j++)
            {
                int x1 = 15 + 25*j;
                int y1 = 35 + 25*i;
                
                if(this.organizmy[j][i] == null)
                {
                    g.setColor(Color.WHITE);
                }
                else
                {
                    colo = this.organizmy[j][i].GetKolor();
                    g.setColor(colo);
                }
                g.fillRect(x1, y1, 25, 25);
            }
        }
        
        g.setColor(Color.BLACK);
        for(int i=0; i<20; i++)
        {
            for(int j=0; j<20; j++)
            {
                int x = 15 + 25*j;
                int y = 35 + 25*i;

                g.drawLine(x, y, x+25, y);
                g.drawLine(x, y, x, y+25);
                g.drawLine(x+25, y+25, x, y+25);
                g.drawLine(x+25, y+25, x+25, y);
            }
        }

        g.drawLine(700, 15, 700, 535);
        g.drawLine(700, 535, 960, 535);
       

        int x = 740;
        int rect_width =15;
        int rect_height = 15;
        int []Y = new int [12];
        Y[0] = 80;
        for(int i=1; i<12; i++)
        {
            Y[i] = Y[i-1] + 40;
        }

        g.setColor(Color.ORANGE);
        g.fillRect(x-20, Y[0]-11, rect_width, rect_height);
        g.setColor(Color.BLACK);
        g.drawString("  Antylopa", x, Y[0]);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x-20, Y[1]-11, rect_width, rect_height);
        g.setColor(Color.BLACK);
        g.drawString("  Barszcz Sosnowskiego", x, Y[1]);

        g.fillRect(x-20, Y[2]-11, rect_width, rect_height);
        g.setColor(Color.BLACK);
        g.drawString("  Cyberowca", x, Y[2]);

        g.setColor(Color.PINK);
        g.fillRect(x-20, Y[3]-11, rect_width, rect_height);
        g.setColor(Color.BLACK);
        g.drawString("  Człowiek", x, Y[3]);

        g.setColor(Color.CYAN);
        g.fillRect(x-20, Y[4]-11, rect_width, rect_height);
        g.setColor(Color.BLACK);
        g.drawString("  Guarana", x, Y[4]);

        g.setColor(Color.RED);
        g.fillRect(x-20, Y[5]-11, rect_width, rect_height);
        g.setColor(Color.BLACK);
        g.drawString("  Lis", x, Y[5]);

        g.setColor(Color.YELLOW);
        g.fillRect(x-20, Y[6]-11, rect_width, rect_height);
        g.setColor(Color.BLACK);
        g.drawString("  Mlecz", x, Y[6]);

        g.setColor(Color.GRAY);
        g.fillRect(x-20, Y[7]-11, rect_width, rect_height);
        g.setColor(Color.BLACK);
        g.drawString("  Owca", x, Y[7]);

        g.setColor(Color.GREEN);
        g.fillRect(x-20, Y[8]-11, rect_width, rect_height);
        g.setColor(Color.BLACK);
        g.drawString("  Trawa", x, Y[8]);

        g.setColor(Color.MAGENTA);
        g.fillRect(x-20, Y[9]-11, rect_width, rect_height);
        g.setColor(Color.BLACK);
        g.drawString("  Wilcze jagody", x, Y[9]);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(x-20, Y[10]-11, rect_width, rect_height);
        g.setColor(Color.BLACK);
        g.drawString("  Wilk", x, Y[10]);

        g.setColor(Color.BLUE);
        g.fillRect(x-20, Y[11]-11, rect_width, rect_height);
        g.setColor(Color.BLACK);
        g.drawString("  Zolw", x, Y[11]);

        g.drawLine(0, 600, 434, 600);
        g.drawLine(434, 600, 434, 960);

        g.drawString("KOMENTATOR: ", 150, 650);

        this.txt.append("------------------------------------\n");

        setFocusable(true);

        addKeyListener(new KeyAdapter(){
            
            @Override
            public void keyReleased(KeyEvent e){
                int keyCode = e.getKeyCode();
                Swiat sw = GetSwiat();

                switch(keyCode){
                    case KeyEvent.VK_UP:
                        UstawKierunek(0);
                        break;

                    case KeyEvent.VK_DOWN: 
                        UstawKierunek(1);
                        break;

                    case KeyEvent.VK_RIGHT: 
                        UstawKierunek(3);
                        break;

                    case KeyEvent.VK_LEFT: 
                        UstawKierunek(2);
                        break;
                }

                if(czyWykonywac)
                {
                    sw.DokonczTure();
                    UstawOrganizmy(sw.GetOrganizmy());
                    repaint();
                    czyWykonywac = false;
                    czyTura = true;
                    czyAktywna = false;
                }
            }
        });
        
    }

    public int GetKierunek(){
        return this.kierunek;
    }

    public Swiat GetSwiat(){
        return this.swiat;
    }

    public Organizm[][] GetOrganizmy(){
        return this.organizmy;
    }

    public JTextArea GetTextArea(){
        return this.txt;
    }

    public boolean GetWykonywanie(){
        return this.czyWykonywac;
    }

    public boolean GetTura(){
        return this.czyTura;
    }

    public boolean GetAktywowanie(){
        return this.czyAktywna;
    }

    public void SetTextArea(JTextArea komentarze){
        this.txt = komentarze;
    }
   
    public void UstawWykonywanie(boolean flaga){
        this.czyWykonywac = flaga;
    }

    public void UstawTura(boolean flaga){
        this.czyTura = flaga;
    }

    public void UstawAktywowanie(boolean flaga){
        this.czyAktywna = flaga;
    }

    public void UstawKierunek(int kier){
        this.kierunek = kier;
    }

    public void UstawOrganizmy(Organizm[][] org){
        this.organizmy = org;
    }


    @Override
    public void actionPerformed(ActionEvent e){
       
        JButton b1 = new JButton();
        b1 = (JButton)e.getSource();

        if(b1.getText() == this.kolejna_tura.getText())
        {
            if(czyTura)
            {
                czyTura = false;
                this.swiat.WykonajTureDoCzlowieka();
                this.organizmy = this.swiat.GetOrganizmy();
                this.repaint();
            }
        }
        else if(b1.getText() == this.zapis.getText())
        {
            this.swiat.ZapisDoPliku();
            this.txt.append("Zapisano stan świata do pliku\n");
            this.repaint();
        }
        else if(b1.getText() == this.wczyt.getText())
        {
            this.swiat.WczytajZPliku();
            this.txt.append("Wczytano stan świata z pliku\n");
            this.organizmy = this.swiat.GetOrganizmy();
            this.repaint();
        }
        else if(b1.getText() == this.super_moc.getText())
        {
            this.czyAktywna = true;
        }
        b1.setFocusable(false);
        this.setFocusable(true);
    }
}