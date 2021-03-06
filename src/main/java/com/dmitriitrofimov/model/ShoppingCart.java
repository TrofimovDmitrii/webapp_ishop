package com.dmitriitrofimov.model;

import com.dmitriitrofimov.Constants;
import com.dmitriitrofimov.exception.ValidationException;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 1535770438453611801L;

	private Map<Integer, ShoppingCartItem> products = new HashMap<>();
	private int totalCount = 0;

	public void addProduct(int idProduct, int count) {
		validateShoppingCartSize(idProduct);
		ShoppingCartItem shoppingCartItem = products.get(idProduct);
		if (shoppingCartItem == null) {
			validateProductCount(count);
			shoppingCartItem = new ShoppingCartItem(idProduct, count);
			products.put(idProduct, shoppingCartItem);
		} else {
			validateProductCount(count + shoppingCartItem.getCount());
			shoppingCartItem.setCount(shoppingCartItem.getCount() + count);
		}
		refreshStatistics();
	}

	public void removeProduct(int idProduct, int count) {
		ShoppingCartItem shoppingCartItem = products.get(idProduct);
		if (shoppingCartItem != null) {
			if (shoppingCartItem.getCount() > count) {
				shoppingCartItem.setCount(shoppingCartItem.getCount() - count);
			} else {
				products.remove(idProduct);
			}
			refreshStatistics();
		}
	}

	public Collection<ShoppingCartItem> getItems() {
		return products.values();
	}

	public int getTotalCount() {
		return totalCount;
	}

	private void validateProductCount(int count) {
		if (count > Constants.MAX_PRODUCT_COUNT_PER_SHOPPING_CART) {
			throw new ValidationException("Limit for product count reached: count = " + count);
		}
	}

	private void validateShoppingCartSize(int idProduct) {
		if (products.size() > Constants.MAX_PRODUCT_COUNT_PER_SHOPPING_CART ||
				(products.size() == Constants.MAX_PRODUCT_COUNT_PER_SHOPPING_CART && !products.containsKey(idProduct))) {
			throw new ValidationException("Limit for shopping cart size reached: size = " + products.size());
		}
	}

	private void refreshStatistics() {
		totalCount = 0;
		for (ShoppingCartItem shoppingCartItem : getItems()) {
			totalCount += shoppingCartItem.getCount();
		}
	}

	@Override
	public String toString() {
		return "ShoppingCart{" +
				"products = " + products +
				", totalCount = " + totalCount +
				'}';
	}

	public String getView() {
		StringBuilder s = new StringBuilder();
		for (ShoppingCartItem shoppingCartItem : getItems()) {
			s.append(shoppingCartItem.getIdProduct()).append("-&gt;")
					.append(shoppingCartItem.getCount()).append("<br>");
		}
		return s.toString();
	}
}