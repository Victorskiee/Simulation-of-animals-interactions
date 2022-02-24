import java.awt.*;

public class WilczeJagody extends Roslina{
    
    public WilczeJagody(int wX, int wY, Swiat sw){
        super(99, 0, wX, wY, 'J', sw, Color.MAGENTA);
    }

    @Override
    public void kolizja(Organizm zwierze, int staryX, int staryY){
        this.komunikatPrzedWalka(zwierze, staryX, staryY);
	    this.UstawZycie(false);
	    zwierze.UstawZycie(false);
	    this.swiat.ZerujOrganizm(this.wspX, this.wspY);
    }
}