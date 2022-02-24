public class Coordinates {
    private int wX;
    private int wY;

    public Coordinates(){
        this.wX = 0;
        this.wY = 0;
    }

    public int GetWspolrzednaX(){
        return this.wX;
    }

    public int GetWspolrzednaY(){
        return this.wY;
    }

    public void UstawWsporzedne(int x, int y){
        this.wX = x;
        this.wY = y;
    }
}