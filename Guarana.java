import java.awt.*;

public class Guarana extends Roslina{
    
    public Guarana(int wX, int wY, Swiat sw){
        super(0, 0, wX, wY, 'G', sw, Color.CYAN);
    }

    @Override
    public void kolizja(Organizm zwierze, int staryX, int staryY){
        super.kolizja(zwierze, staryX, staryY);
	    int sila = zwierze.GetSila();
	    zwierze.UstawSile(sila + 3);
    }
}