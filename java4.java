import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        // ��������� ��'���� �����, �� ��������� ��������� �� ��������� ����������� ����
        FoodDelivery foodDelivery1 = new FoodDelivery("bread");
        FoodDelivery foodDelivery2 = new FoodDelivery("apple");

        // ������������ ����������
        System.out.println("Before sorting:");
        System.out.println(foodDelivery1);
        System.out.println(foodDelivery2);

        if (foodDelivery1.compareTo(foodDelivery2) > 0) {
            FoodDelivery temp = foodDelivery1.clone(); // ��������� clone() � foodDelivery1
            foodDelivery1 = foodDelivery2.clone(); // ��������� clone() � foodDelivery2
            foodDelivery2 = temp;
        }

        System.out.println("\nAfter sorting:");
        System.out.println(foodDelivery1);
        System.out.println(foodDelivery2);

        // ������������ ����������
        FoodDelivery clonedFoodDelivery = foodDelivery1.clone();
        System.out.println("\nCloned object:");
        System.out.println(clonedFoodDelivery);

        // ������ ������ � ���������� ��������
        performAction(foodDelivery1);
    }

    // �����, ���� ������ � ����� ��������� ��'��� ���� ������������ �����
    public static void performAction(FoodDeliveryService deliveryService) {
        deliveryService.placeOrder("Burger", deliveryService.getDeliveryAddress());
    }
}

// ����������� ����
abstract class FoodDeliveryService implements Cloneable {
    private String deliveryAddress;

    public abstract void placeOrder(String foodItem, String deliveryAddress);

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}

// ���������
interface DeliveryServiceInterface {
    default void trackDelivery(String orderId) {
        System.out.println("Tracking delivery for order ID: " + orderId);
    }
}

// ����, ���� ������ ��������� �� ������ ����������� ����
class FoodDelivery extends FoodDeliveryService implements Comparable<FoodDelivery> {
    private String foodItem;

    public FoodDelivery(String foodItem) {
        this.foodItem = foodItem;
        setDeliveryAddress("123 Main St"); // ����������� ������ �� ������������� ��� ������������
    }

    @Override
    public void placeOrder(String foodItem, String deliveryAddress) {
        System.out.println("Placing order for: " + foodItem + " to " + deliveryAddress);
    }

    @Override
    public String toString() {
        return "FoodDelivery [foodItem=" + foodItem + ", deliveryAddress=" + getDeliveryAddress() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (obj == null || getClass() != obj.getClass()) return false;
        FoodDelivery that = (FoodDelivery) obj;
        return Objects.equals(foodItem, that.foodItem);
    }

    @Override
    public int compareTo(FoodDelivery o) {
        return this.foodItem.compareTo(o.foodItem);
    }

    // �������������� ������ clone() � ������������� ������� public
    @Override
    public FoodDelivery clone() {
        try {
            return (FoodDelivery) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clone not supported", e);
        }
    }
}
 
