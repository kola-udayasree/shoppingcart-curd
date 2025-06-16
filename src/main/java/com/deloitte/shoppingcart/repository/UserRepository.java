package com.deloitte.shoppingcart.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.deloitte.shoppingcart.model.User;


public interface UserRepository extends MongoRepository<User, String> {

    public User findByEmailId(String emailId);
     
  
}
