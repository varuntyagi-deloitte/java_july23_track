class box{
    int length,breadth,height;
    public box(int length,int breadth,int height){
        this.length=length;
        this.breadth=breadth;
        this.height=height;
    }
}
public class box3d extends box{
    public box3d(int l,int b,int h)
    {
        super(l,b,h);
    }
    public void area(){
        System.out.println("Area of box is: "+((2*this.length*this.breadth)+(2*this.breadth*this.height)+(2*this.height*this.length)));
    }
    public void volume(){
        System.out.println("Volume of box is: "+(this.length*this.breadth*this.height));
    }
    public static void main(String[] args){
        box3d obj = new box3d(2,3,4);
        obj.area();
        obj.volume();
    }

}
