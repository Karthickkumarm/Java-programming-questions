public class BoxWeight extends Box{
    int weight;
    BoxWeight(int a,int b,int c) {
        super(a, b, c);
        //super();
        this.weight=a*b*c;
    }
}
