package EcommerceAPI;

import java.util.List;

public class OrdersRequest 
{
	private List<orderDetails> orders;

	public List<orderDetails> getOrders() {
		return orders;
	}

	public void setOrders(List<orderDetails> orders) {
		this.orders = orders;
	}
	
	//
//	{
//	    "orders": [
//	        {
//	            "country": "India",
//	            "productOrderedId": "{{productId}}"
//	        }
//	    ]
//	}
	

}
