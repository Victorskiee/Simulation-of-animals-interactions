import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JTextArea;

public class Swiat {
    private Organizm[][] organizmy;
    private int wysokosc;
    private int szerokosc;
    private ArrayList<Organizm> uporzadkowane_org;
    private char[][] plansza;
    private GUI gui;
    private final int OBIEKTY = 23;

    public Swiat(int szer, int wys){
        this.szerokosc = szer;
        this.wysokosc = wys;

        this.organizmy = new Organizm[this.szerokosc][this.wysokosc];
        this.plansza = new char[this.szerokosc + 2][this.wysokosc + 2];
        this.uporzadkowane_org = new ArrayList<Organizm>(21);

        for (int i = 0; i < this.szerokosc; i++)
        {
            for (int j = 0; j < this.wysokosc; j++)
            {
                this.organizmy[i][j] = null;
            }
        }

        for (int i = 0; i < this.szerokosc + 2; i++)
        {
            for (int j = 0; j < this.wysokosc + 2; j++)
            {
                this.plansza[i][j] = ' ';
            }
        }
        ZerujPlansze();
    }

    public void Start(){
        int[] tabX = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] tabY = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < OBIEKTY; i++)
        {
            Random randX = new Random();
            Random randY = new Random();

            int wspX = randX.nextInt(this.szerokosc);
            int wspY = randY.nextInt(this.wysokosc);
            tabY[i] = wspY;
            tabX[i] = wspX;
            for (int j = 0; j < i; j++)
            {
                if (tabX[i] == tabX[j] && tabY[i] == tabY[j])
                {
                    i--;
                }
            }
        }

        for (int i = 0; i < OBIEKTY; i++)
        {
            System.out.println(tabX[i] + "," + tabY[i]);
        }

        this.organizmy[tabX[0]][tabY[0]] = new Antylopa(tabX[0], tabY[0], this);
        this.organizmy[tabX[1]][tabY[1]] = new Antylopa(tabX[1], tabY[1], this);
        this.organizmy[tabX[2]][tabY[2]] = new BarszczSosnowskiego(tabX[2], tabY[2], this);
        this.organizmy[tabX[3]][tabY[3]] = new BarszczSosnowskiego(tabX[3], tabY[3], this);
        this.organizmy[tabX[4]][tabY[4]] = new Czlowiek(tabX[4], tabY[4], this);
        this.organizmy[tabX[5]][tabY[5]] = new Guarana(tabX[5], tabY[5], this);
        this.organizmy[tabX[6]][tabY[6]] = new Guarana(tabX[6], tabY[6], this);
        this.organizmy[tabX[7]][tabY[7]] = new Lis(tabX[7], tabY[7], this);
        this.organizmy[tabX[8]][tabY[8]] = new Lis(tabX[8], tabY[8], this);
        this.organizmy[tabX[9]][tabY[9]] = new Mlecz(tabX[9], tabY[9], this);
        this.organizmy[tabX[10]][tabY[10]] = new Mlecz(tabX[10], tabY[10], this);
        this.organizmy[tabX[11]][tabY[11]] = new Owca(tabX[11], tabY[11], this);
        this.organizmy[tabX[12]][tabY[12]] = new Owca(tabX[12], tabY[12], this);
        this.organizmy[tabX[13]][tabY[13]] = new Trawa(tabX[13], tabY[13], this);
        this.organizmy[tabX[14]][tabY[14]] = new Trawa(tabX[14], tabY[14], this);
        this.organizmy[tabX[15]][tabY[15]] = new WilczeJagody(tabX[15], tabY[15], this);
        this.organizmy[tabX[16]][tabY[16]] = new WilczeJagody(tabX[16], tabY[16], this);
        this.organizmy[tabX[17]][tabY[17]] = new Wilk(tabX[17], tabY[17], this);
        this.organizmy[tabX[18]][tabY[18]] = new Wilk(tabX[18], tabY[18], this);
        this.organizmy[tabX[19]][tabY[19]] = new Zolw(tabX[19], tabY[19], this);
        this.organizmy[tabX[20]][tabY[20]] = new Zolw(tabX[20], tabY[20], this);
        this.organizmy[tabX[21]][tabY[21]] = new Cyberowca(tabX[21], tabY[21], this);
        this.organizmy[tabX[22]][tabY[22]] = new Cyberowca(tabX[22], tabY[22], this);

        this.uporzadkowane_org.add(this.organizmy[tabX[0]][tabY[0]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[1]][tabY[1]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[2]][tabY[2]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[3]][tabY[3]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[4]][tabY[4]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[5]][tabY[5]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[6]][tabY[6]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[7]][tabY[7]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[8]][tabY[8]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[9]][tabY[9]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[10]][tabY[10]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[11]][tabY[11]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[12]][tabY[12]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[13]][tabY[13]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[14]][tabY[14]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[15]][tabY[15]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[16]][tabY[16]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[17]][tabY[17]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[18]][tabY[18]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[19]][tabY[19]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[20]][tabY[20]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[21]][tabY[21]]);
        this.uporzadkowane_org.add(this.organizmy[tabX[22]][tabY[22]]);

        this.gui = new GUI(this);
    }

    public void TworzOrganizmy(Organizm[][] organizmy){
        ZerujOrganizmy();
    }

    public void TworzPlansze(char[][] plansza){
        ZerujPlansze();
    }

    public void UstawUporzadkowane_org(ArrayList<Organizm> org){
	    this.uporzadkowane_org = org;
    }

    public void ZerujPlansze(){
        for (int i = 0; i < this.szerokosc + 2; i++)
        {
            for (int j = 0; j < this.wysokosc + 2; j++)
            {
                this.plansza[i][j] = ' ';
                if (i == 0 || j == 0 || i == this.szerokosc + 1 || j == this.wysokosc + 1)
                {
                    this.plansza[i][j] = '#';
                }
            }
        }
    }

    public void ZerujOrganizmy(){
        for (int i = 0; i < this.szerokosc; i++)
        {
            for (int j = 0; j < this.wysokosc; j++)
            {
                this.organizmy[i][j] = null;
            }
        }
    }

    public void ZerujOrganizm(int szer, int wys){
        this.organizmy[szer][wys] = null;
        this.plansza[szer + 1][wys + 1] = ' ';
    }

    public void UstawOrganizm(Organizm o1){
        this.organizmy[o1.GetWspolrzednaX()][o1.GetWspolrzednaY()] = o1;
	    this.organizmy[o1.GetWspolrzednaX()][o1.GetWspolrzednaY()].rysowanie();
    }

    public void WykonajTureDoCzlowieka(){
        for (int j = 0; j < this.uporzadkowane_org.size(); j++)
        {
            if (!this.uporzadkowane_org.get(j).GetZycie())
            {
                this.uporzadkowane_org.remove(j);
                j--;
            }
        }
        
        this.gui.UstawWykonywanie(true);
        this.InkrementujWiek();
        this.Sortuj();

        for (int j = 0; j < this.uporzadkowane_org.size(); j++)
        {
            if (this.uporzadkowane_org.get(j).GetZycie() && this.uporzadkowane_org.get(j).GetWiek() > 0)
            {
                if(this.uporzadkowane_org.get(j) instanceof Czlowiek)
                {
                    JTextArea txt = this.GetGUI().GetTextArea();
                    txt.append("\n    TURA CZLOWIEKA\n");
                    this.GetGUI().SetTextArea(txt);
                    return;
                }
                else
                {
                    this.uporzadkowane_org.get(j).akcja();
                }
            }
        }
        this.gui.UstawTura(true);
    }

    public void DokonczTure(){

        for (int j = 0; j < this.uporzadkowane_org.size(); j++)
        {
            if (!this.uporzadkowane_org.get(j).GetZycie())
            {
                this.uporzadkowane_org.remove(j);
                j--;
            }
        }

        this.InkrementujWiek();
        boolean czyWykonywac = false;

        for (int j = 0; j < this.uporzadkowane_org.size(); j++)
        {
            if (this.uporzadkowane_org.get(j).GetZycie() && this.uporzadkowane_org.get(j).GetWiek() > 0)
            {
                if(this.uporzadkowane_org.get(j) instanceof Czlowiek)
                {
                    czyWykonywac = true;
                }
                if(czyWykonywac)
                {
                    this.uporzadkowane_org.get(j).akcja();
                }
            }
        }
        this.gui.repaint();
    }

    public int GetSzerokosc() {
        return this.szerokosc;
    }

    public int GetWysokosc() {
        return this.wysokosc;
    }

    public char[][] GetPlansza() {
        return this.plansza;
    }

    public Organizm[][] GetOrganizmy(){
        return this.organizmy;
    }

    public GUI GetGUI(){
        return this.gui;
    }

    ArrayList<Organizm> GetUporzadkowane_org(){
	    return this.uporzadkowane_org;
    }

    public void InkrementujWiek(){
        for (int i = 0; i < this.uporzadkowane_org.size(); i++)
        {
            int wiek = this.uporzadkowane_org.get(i).GetWiek();
            this.uporzadkowane_org.get(i).UstawWiek(wiek + 1);
        }
    }

    public void Sortuj(){
        for (int i = this.uporzadkowane_org.size()-1; i >= 0; i--)
        {
            for (int j = 0; j < i; j++)
            {
                int inicjatywa_1 = this.uporzadkowane_org.get(j).GetInicjatywa();
                int inicjatywa_2 = this.uporzadkowane_org.get(j + 1).GetInicjatywa();
                int wiek_1 = this.uporzadkowane_org.get(j).GetWiek();
                int wiek_2 = this.uporzadkowane_org.get(j + 1).GetWiek();

                if (inicjatywa_1 < inicjatywa_2)
                {
                    Organizm tmp = this.uporzadkowane_org.get(j);
                    this.uporzadkowane_org.set(j, this.uporzadkowane_org.get(j+1));
                    this.uporzadkowane_org.set(j+1, tmp);
                }
                else if (inicjatywa_1 == inicjatywa_2 && wiek_1 < wiek_2)
                {
                    Organizm tmp = this.uporzadkowane_org.get(j);
                    this.uporzadkowane_org.set(j, this.uporzadkowane_org.get(j+1));
                    this.uporzadkowane_org.set(j+1, tmp);
                }
            }
        }
    }

    public void WczytajZPliku(){
       File plik = new File("stan swiata.txt");
       try{
           Scanner wejscie = new Scanner(plik);
           String irrelevant = wejscie.nextLine();
           int liczba_org = Integer.parseInt(wejscie.nextLine());
           String irr = wejscie.nextLine();
           int szer = Integer.parseInt(wejscie.nextLine());
           irr = wejscie.nextLine();
           int wys = Integer.parseInt(wejscie.nextLine());

           this.szerokosc = szer;
           this.wysokosc = wys;
           this.organizmy = new Organizm[this.szerokosc][this.wysokosc];
           this.plansza = new char[this.szerokosc + 2][this.wysokosc + 2];
           this.uporzadkowane_org = new ArrayList<Organizm>(liczba_org);
           for (int i = 0; i < this.szerokosc; i++)
           {
               for (int j = 0; j < this.wysokosc; j++)
               {
                  this.organizmy[i][j] = null;
               }
           }

           for (int i = 0; i < this.szerokosc + 2; i++)
           {
               for (int j = 0; j < this.wysokosc + 2; j++)
               {
                   this.plansza[i][j] = ' ';
               }
           }

           this.ZerujPlansze();

           for(int i=0; i<liczba_org; i++)
           {
               String linia = wejscie.nextLine();
               String[] pojedyncze_dane = linia.split(" ");
               char znak = pojedyncze_dane[0].charAt(0);
               int sila = Integer.parseInt(pojedyncze_dane[1]);
               int inicjatywa = Integer.parseInt(pojedyncze_dane[2]);
               int wspX = Integer.parseInt(pojedyncze_dane[3]);
               int wspY = Integer.parseInt(pojedyncze_dane[4]);
               int wiek = Integer.parseInt(pojedyncze_dane[5]);
               if(znak == 'C')
               {
                    int nrTuryOczekiwania = Integer.parseInt(pojedyncze_dane[6]);
                    int nrTurySpecjalnej = Integer.parseInt(pojedyncze_dane[7]);
                    this.organizmy[wspX][wspY] = new Czlowiek(wspX, wspY, this);
                    Czlowiek czl = (Czlowiek) this.organizmy[wspX][wspY];
                    if(nrTurySpecjalnej > 0)
                    {
                        czl.UstawAktywowanie(true);
                    }
                    else
                    {
                        czl.UstawAktywowanie(false);
                    }
                    czl.UstawNrTuryOczekiwania(nrTuryOczekiwania);
                    czl.UstawNrTurySpecjalnej(nrTurySpecjalnej);
               }
               else
               {
                    if(znak == 'A')
                    {
                        this.organizmy[wspX][wspY] = new Antylopa(wspX, wspY, this);
                    }
                    else if(znak == 'B')
                    {
                        this.organizmy[wspX][wspY] = new BarszczSosnowskiego(wspX, wspY, this);
                    }
                    else if(znak == 'G')
                    {
                        this.organizmy[wspX][wspY] = new Guarana(wspX, wspY, this);
                    }
                    else if(znak == 'L')
                    {
                        this.organizmy[wspX][wspY] = new Lis(wspX, wspY, this);
                    }
                    else if(znak == 'M')
                    {
                        this.organizmy[wspX][wspY] = new Mlecz(wspX, wspY, this);
                    }
                    else if(znak == 'O')
                    {
                        this.organizmy[wspX][wspY] = new Owca(wspX, wspY, this);
                    }
                    else if(znak == 'T')
                    {
                        this.organizmy[wspX][wspY] = new Trawa(wspX, wspY, this);
                    }
                    else if(znak == 'J')
                    {
                        this.organizmy[wspX][wspY] = new WilczeJagody(wspX, wspY, this);
                    }
                    else if(znak == 'W')
                    {
                        this.organizmy[wspX][wspY] = new Wilk(wspX, wspY, this);
                    }
                    else if(znak == 'Z')
                    {
                        this.organizmy[wspX][wspY] = new Zolw(wspX, wspY, this);
                    }
                    else if(znak == 'X')
                    {
                        this.organizmy[wspX][wspY] = new Cyberowca(wspX, wspY, this);
                    }
               }
               this.organizmy[wspX][wspY].UstawInicjatywe(inicjatywa);
               this.organizmy[wspX][wspY].UstawSile(sila);
               this.organizmy[wspX][wspY].UstawWiek(wiek);
               this.uporzadkowane_org.add(this.organizmy[wspX][wspY]);
           }
           this.gui.repaint();
       }
       catch(IOException e){
           System.out.println("error");
       }
    }

    public void ZapisDoPliku(){
       File nowy = new File("stan swiata.txt");
       
       FileOutputStream fos = null;
       try{
            fos = new FileOutputStream(nowy);
       }
       catch(IOException e){
            System.out.println("error");
       }
       BufferedWriter plik = new BufferedWriter(new OutputStreamWriter(fos));
       
            try{
                plik.write("Liczba organizmów:");
                plik.newLine();
                plik.write(Integer.toString(this.uporzadkowane_org.size()));
                plik.newLine();
                plik.write("Szerokość:");
                plik.newLine();
                plik.write(Integer.toString(this.szerokosc));
                plik.newLine();
                plik.write("Wysokość:");
                plik.newLine();
                plik.write(Integer.toString(this.wysokosc));
                plik.newLine();

                for(int i=0; i<this.uporzadkowane_org.size(); i++)
                {
                    plik.write(this.uporzadkowane_org.get(i).GetZnak() + " " +
                            this.uporzadkowane_org.get(i).GetSila() + " " +
                            this.uporzadkowane_org.get(i).GetInicjatywa() + " " +
                            this.uporzadkowane_org.get(i).GetWspolrzednaX() + " " +
                            this.uporzadkowane_org.get(i).GetWspolrzednaY() + " " +
                            this.uporzadkowane_org.get(i).GetWiek());
                    if(this.uporzadkowane_org.get(i) instanceof Czlowiek)
                    {
                        Czlowiek cz = (Czlowiek) this.uporzadkowane_org.get(i);
                        plik.write(" " + cz.GetNrTuryOczekiwania() + " " + cz.GetNrTurySpecjalnej());

                    }
                    plik.newLine();
                }

                plik.close();
        }
        catch(IOException e){
            System.out.println("error");
        }
    }
}