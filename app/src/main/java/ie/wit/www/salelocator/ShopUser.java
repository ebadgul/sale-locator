package ie.wit.www.salelocator;

/**
 * Created by ebad on 13/11/15.
 */
public class ShopUser {
    public String shopUserName;
    public String shopName;
    public String shopAddress;
    public String password;
    public String passwordConfirmation;
    public double latitude;
    public double longitude;

    public ShopUser(String shopUserName, String shopName, String shopAddress){
        this.shopUserName = shopUserName;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
  //      this.password = password;
   //     this.passwordConfirmation = passwordConfirmation;
    }


    public void setLatLng(double lat, double lng){

        this.latitude = lat;
        this.longitude = lng;

    }

}
