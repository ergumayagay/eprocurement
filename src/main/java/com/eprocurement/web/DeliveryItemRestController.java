package com.eprocurement.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eprocurement.domain.Delivery;
import com.eprocurement.domain.DeliveryItem;
import com.eprocurement.domain.DeliveryItemRepository;
import com.eprocurement.domain.PurchaseOrderItem;

import com.eprocurement.service.DeliveryServiceImpl;

@RestController
@RequestMapping("/api/deliveryitem")
public class DeliveryItemRestController {
	
	@Autowired
	private DeliveryItemRepository deliveryItemRepository;
	

	@Autowired
	private DeliveryServiceImpl deliveryService;
	
	@GetMapping("/{delivery}")
	public Page<DeliveryItem> getDeliveryItems(@PathVariable Delivery delivery, Pageable pageable){
		return deliveryItemRepository.findByDelivery(delivery, pageable);
	}
	
	@PostMapping("/{delivery}/add")
	public void addDeliveryItems(@PathVariable Delivery delivery, @RequestParam List<PurchaseOrderItem> item) {

		deliveryService.addDeliveryItems(delivery, item);
	}
	
	@PostMapping("/save")
	public void saveItem(@RequestParam List<DeliveryItem> item, @RequestParam List<Integer> quantity) {
		for(int i=0;i < item.size();i++) {
			item.get(i).setQuantity(quantity.get(i));
			deliveryItemRepository.save(item.get(i));
		}
	}
	
	@PostMapping("/delete")
	public void removeItem(@RequestParam DeliveryItem item) {
		deliveryItemRepository.delete(item);
	}
}