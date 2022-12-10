public class Product {
    private  String name;
    private  int price;

    public Product(String name, int price)
    {
        this.name=name;
        this.price=price;
    }

    public String getName()
    {
        return  name;
    }

    public void setName(String name)
    {
        if(name!=null)
        {
            this.name=name;
        }
        else
        {
            System.out.println("Повторите попытку!");
        }
    }

    public int getPrice()
    {
        return  price;
    }

    public Product setPrice(int price)
    {
        if(price==0||price<0)
        {
            System.out.println("Повторите попытку!");
        }
        else
        {
            this.price=price;
        }
        return Product.this;
    }

    public String toString()
    {
        return   name  + "                  "+price;
    }
}
