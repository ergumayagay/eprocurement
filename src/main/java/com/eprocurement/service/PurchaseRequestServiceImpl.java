package com.eprocurement.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eprocurement.domain.Department;
import com.eprocurement.domain.Item;
import com.eprocurement.domain.PurchaseRequest;
import com.eprocurement.domain.PurchaseRequestItem;
import com.eprocurement.domain.PurchaseRequestItemsRepository;
import com.eprocurement.domain.PurchaseRequestRepository;

@Service
public class PurchaseRequestServiceImpl implements PurchaseRequestService {

	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;
	
	@Autowired
	private PurchaseRequestItemsRepository purchaseRequestItemRepository;
	
	@Override
	public void createNewPurchaseRequest(PurchaseRequest purchaseRequest) {
		Date now = new Date(Calendar.getInstance().getTimeInMillis());
		purchaseRequest.setPrDate(now);
		purchaseRequestRepository.save(purchaseRequest);
	}

	@Override
	public void updatePurchaseRequest(PurchaseRequest purchaseRequest, Department department, Date date,
			String modeOfProcurement, String purpose) {
		purchaseRequest.setDepartment(department);
		purchaseRequest.setPrDate(date);
		purchaseRequest.setModeOfProcurement(modeOfProcurement);
		purchaseRequest.setPurpose(purpose);
		purchaseRequestRepository.save(purchaseRequest);
	}

	@Override
	public void addItems(PurchaseRequest purchaseRequest, List<Item> items) {
		for(Item item : items) {
			PurchaseRequestItem purchaseRequestItem = new PurchaseRequestItem();
			purchaseRequestItem.setItem(item);
			purchaseRequestItem.setQuantity(1);
			purchaseRequestItem.setUnit("piece");
			purchaseRequestItem.setPurchaseRequest(purchaseRequest);
			purchaseRequestItemRepository.save(purchaseRequestItem);
		}	
	}

	@Override
	public void updateItems(PurchaseRequestItem purchaseRequestItem, int quantity, String unit) {
		purchaseRequestItem.setQuantity(quantity);
		purchaseRequestItem.setUnit(unit);
		purchaseRequestItemRepository.save(purchaseRequestItem);	
	}
}