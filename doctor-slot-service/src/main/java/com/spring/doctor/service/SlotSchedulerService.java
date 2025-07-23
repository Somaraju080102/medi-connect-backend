package com.spring.doctor.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.spring.doctor.entity.Slot;
import com.spring.doctor.model.SlotStatus;
import com.spring.doctor.repository.SlotRepository;

@Service
public class SlotSchedulerService {
	
	@Autowired
	private SlotRepository slotRepository;
	
    @Scheduled(cron = "0 0 0 * * *") // Every hour — or "0 0 0 * * *" for midnight daily
	public void expireOldSlots() {
    	 LocalDateTime now = LocalDateTime.now();
    	 System.out.println("NOW: " + now);

    	    List<Slot> expiredSlots = slotRepository.findAllByEndTimeBeforeAndStatusNot(now, SlotStatus.EXPIRED);
    	System.out.println(expiredSlots.size());


        for (Slot slot : expiredSlots) {
        	if (slot.getEndTime().isBefore(LocalDateTime.now())) {
        	    slot.setStatus(SlotStatus.EXPIRED);
        	}
        }

        slotRepository.saveAll(expiredSlots);

        System.out.println("Expired " + expiredSlots.size() + " slots at " + now);
    }


}
