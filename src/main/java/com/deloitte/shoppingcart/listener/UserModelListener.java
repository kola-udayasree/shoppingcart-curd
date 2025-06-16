package com.deloitte.shoppingcart.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.deloitte.shoppingcart.model.Order;
import com.deloitte.shoppingcart.service.SequenceGeneratorService;

@Component
public class UserModelListener extends AbstractMongoEventListener<Order> {

    private SequenceGeneratorService sequenceGeneratorService;


    @Autowired
    public UserModelListener(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Order> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGeneratorService.generateSequence("database_sequences"));
        }
        
    }
}