package com.eprocurement.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eprocurement.domain.PurchaseOrder;
import com.eprocurement.domain.PurchaseOrderItem;
import com.eprocurement.domain.PurchaseOrderItemRepository;
import com.eprocurement.domain.QuotationItem;

import com.eprocurement.service.PurchaseOrderServiceImpl;

@RestController
public class PurchaseOrderItemRestController {
	
	@Autowired
	private PurchaseOrderItemRepository purchaseOrderItemRepository;

	@Autowired
	private PurchaseOrderServiceImpl purchaseOrderService;
	
	@GetMapping("/api/poitems/{po}")
	public Page<PurchaseOrderItem> getPoItems(Pageable pageable, @PathVariable PurchaseOrder po){
		return  purchaseOrderItemRepository.findByPurchaseOrder(po, pageable);
	}
	
	@PostMapping("/api/poitems/add")
	public void addPurchaseOrderItems(@RequestParam List <QuotationItem> item, 
			@RequestParam PurchaseOrder poNo) {
		purchaseOrderService.addPurchaseOrderItems(poNo, item);
	}
	
	@PostMapping("/api/poitems/delete")
	public void removeItem(@RequestParam PurchaseOrderItem item) {
		purchaseOrderItemRepository.delete(item);
	}
}