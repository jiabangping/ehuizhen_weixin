package com.ehuizhen.test.template;

public class OrderResponse {

	public Order getOrder() {
		return new OrderResponse.Order();
	}
	
	class Order {
		private String receiver_name;
		private String receiver_mobile;
		private String receiver_city;
		private String receiver_zone;
		private String receiver_address;
		private String buyer_openid;
		private String product_name;
		private String order_total_price;
		private String order_create_time;
		
		public String getReceiver_name() {
			return receiver_name;
		}
		public void setReceiver_name(String receiver_name) {
			this.receiver_name = receiver_name;
		}
		public String getReceiver_mobile() {
			return receiver_mobile;
		}
		public void setReceiver_mobile(String receiver_mobile) {
			this.receiver_mobile = receiver_mobile;
		}
		public String getReceiver_city() {
			return receiver_city;
		}
		public void setReceiver_city(String receiver_city) {
			this.receiver_city = receiver_city;
		}
		public String getReceiver_zone() {
			return receiver_zone;
		}
		public void setReceiver_zone(String receiver_zone) {
			this.receiver_zone = receiver_zone;
		}
		public String getReceiver_address() {
			return receiver_address;
		}
		public void setReceiver_address(String receiver_address) {
			this.receiver_address = receiver_address;
		}
		public String getBuyer_openid() {
			return buyer_openid;
		}
		public void setBuyer_openid(String buyer_openid) {
			this.buyer_openid = buyer_openid;
		}
		public String getProduct_name() {
			return product_name;
		}
		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}
		public String getOrder_total_price() {
			return order_total_price;
		}
		public void setOrder_total_price(String order_total_price) {
			this.order_total_price = order_total_price;
		}
		public String getOrder_create_time() {
			return order_create_time;
		}
		public void setOrder_create_time(String order_create_time) {
			this.order_create_time = order_create_time;
		}
		
		
	}
}


