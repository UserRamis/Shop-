import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static ArrayList<Product> product = new ArrayList<Product>() {{
        add(new Product("Молоко", 56));//добавляем обькеты в лист
        add(new Product("Хлеб", 34));
        add(new Product("Сахар", 67));
        add(new Product("Вода", 54));
        add(new Product("Сок", 44));
        add(new Product("Мука", 32));
        add(new Product("Батон", 13));
        add(new Product("Булочка", 21));

    }};

    public static Scanner sc = new Scanner(System.in);
    public static int coupon = 12345;//также можем менять номера купонов
    public static String login = "admin";//можем менять логин и пароль прямо здесь)
    public static String password = "admin";


    public static void main(String[] args) {
        System.out.println("Вы хотите войти как покупатель или администратор?\n1-покупатель\n2-администратор\nВ случае выхода напишите выход");
        String vh = "";
        while (!vh.equalsIgnoreCase("Выход")) {
            vh = sc.nextLine();
            if (vh.equals("2"))//если пользователь выбрал администратора
            {
                String login1 = null;
                String password1 = null;
                //while (!vh.equalsIgnoreCase("Выход")) {
                    System.out.println("Рады приветсовать в нашем магазине KFU store!\nВыполните вход в систему!\nВведите логин и пароль");
                    System.out.println("Логин ");
                    login1 = sc.nextLine();
                    if (login1.equalsIgnoreCase("Выход")) return;
                    System.out.println("Пароль ");
                    password1 = sc.nextLine();
                    if (password1.equalsIgnoreCase("Выход")) return;
                    int attempt = authorization(login1, password1);
                    if (attempt == 1)
                    {
                        System.out.println("Вход выполнен успешно!");
                        GetInfoAboutProduct();
                        System.out.println("Выберите что хотите сделать\n1-добавить новый товар\n2-удалить товар\n3-изменение цены товара");
                        while (true) {//цикл пока пользователь не введет число 1 или 2
                            int v = sc.nextInt();
                            if (v == 1) {//add
                                System.out.println("Введите название нового товара");
                                String nm = sc.nextLine();
                                nm = sc.nextLine();
                                System.out.println("Введите цену нового товара");
                                int pr = sc.nextInt();
                                addNewProduct(nm, pr);
                                System.out.println("Выберите действие администратора(1-3)");
                                break;
                            } else if (v == 2)//delete
                            {
                                System.out.println("Введите название товара");
                                String nm = sc.nextLine();
                                nm = sc.nextLine();
                                deleteProduct(nm);
                                System.out.println("Выберите действие администратора(1-3)");
                                break;
                            } else if (v == 3)//edit
                            {
                                System.out.println("Введите название товара");
                                String nm = sc.nextLine();
                                nm = sc.nextLine();
                                System.out.println("Введите новую цену товара");
                                int pr = sc.nextInt();
                                editPrice(nm, pr);
                                System.out.println("Выберите действие администратора(1-3)");
                                break;
                            } else {
                                System.out.println("Повторите попытку!");//если пользователь введет больше 3 или меньше 1
                            }
                        }
                    } else {
                        System.out.println("Неправильный логин или пароль");
                    }
                //}
            } else if (vh.equals("1"))//Если продолжить как покупатель
            {
                GetInfoAboutProduct();
                System.out.println("Вам выведены товары которые находятся в наличии, выбирите что хотите купить");
                buyproduct();
            } else {
                System.out.println("Повторите попытку!");
            }
        }
    }

    public static int authorization(String login1, String password1) {
        if (login1.equals(login) && password1.equals(password)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void addNewProduct(String nm, int pr)
    {
        if(nm!=null)
        {
            if(!checkingForAvailability(nm))
            {
                product.add(new Product(nm, pr));
                System.out.println("Вы добавили новый товар " + nm + " с ценой " + pr + "\nОбновленный список");
                GetInfoAboutProduct();
            }
            else
            {
                System.out.println("Некорректные данные или данный товар уже есть в списке");
            }
        }
        else
        {
            System.out.println("Некорректные данные или данный товар уже есть в списке");
        }
    }

    public static void editPrice(String nm, int pr)//изменение цены товара
    {
        if(checkingForAvailability(nm))
        {
            for (int i = 0; i < product.size(); i++) {
                if (product.get(i).getName().equalsIgnoreCase(nm)) {

                    product.set(i, product.get(i).setPrice(pr));
                    break;
                }
            }
            System.out.println("Вы изменили товар " + nm + " на новую цену " + pr + "\nОбновленный список");
            GetInfoAboutProduct();
        }
        else
        {
            System.out.println("Продукт не найден!");
        }
    }

    public static void deleteProduct(String nm)//удаление товара из списка
    {
        if(checkingForAvailability(nm))
        {
            for (int i = 0; i < product.size(); i++) {
                if (product.get(i).getName().equalsIgnoreCase(nm)) {
                    product.remove(i);
                    break;
                }
            }
            System.out.println("Вы выбрали удаление товара " + nm + " Обновленный список:");
            GetInfoAboutProduct();
        }
        else {
            System.out.println("Элемент отсуствует!");
            GetInfoAboutProduct();
        }
    }

    public static void GetInfoAboutProduct()//метод получения информации о товаре
    {
        for (int i = 0; i < product.size(); i++) {
            Product shop_FromCollection = product.get(i);//получаем данные с обьекта и выводим их на консоль
            System.out.println(shop_FromCollection);
        }
    }

    public static void buyproduct() {
        System.out.println("Пишите, что хотите купить, после последнего выбранного товара наберите \"Стоп\"");
        int sum = 0;
        String s = "";
        while (!s.equalsIgnoreCase("Стоп")) {
            s = sc.nextLine();
            if(checkingForAvailability(s))
            {
                int index = 0;
                for (int i = 0; i < product.size(); i++) {
                    if (product.get(i).getName().equalsIgnoreCase(s)) {
                        sum += product.get(i).getPrice();
                        break;
                    }
                }
            }
            else System.out.println("Элемент отсуствует!");
        }
        System.out.println("Итоговая цена " + sum);
        Sale(sum);
    }

    public static void Sale(int sum) {
        System.out.println("У вас есть купон? Напишите 1, если да. 2, если нет");
        int sale = sc.nextInt();
        if (sale == 1) {
            System.out.println("Введите номер купона ");
            int coupon1 = sc.nextInt();
            if (coupon1 == coupon) {
                sum = sum / 2;
                System.out.println("Купон успешно активирован, итоговая сумма: " + sum);
                System.exit(0);
            } else {
                System.out.println("Купон не найден!");
                System.out.println("К оплате " + sum);
                System.exit(0);
            }
        } else
        {
            System.out.println("Купон не был применен. Итоговая цена " + sum);
            System.exit(0);
        }
    }

    public static boolean checkingForAvailability(String nm)
    {
        for (int i = 0; i < product.size(); i++)
        {
            if (product.get(i).getName().equalsIgnoreCase(nm))
            {
                return true;
            }
        }
        return false;
    }
}