//package com.capstone.Expense_TrackerBackend.entity;
//
//import com.capstone.Expense_TrackerBackend.repository.UserRepo;
//import com.capstone.Expense_TrackerBackend.services.UserServices;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//public class codeSaver {
//package com.capstone.Expense_TrackerBackend.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "users")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String username;
//
//    private String email;
//
//    private String password;
//
//
//}
//package com.capstone.Expense_TrackerBackend.controller;
//
//
//import com.capstone.Expense_TrackerBackend.entity.User;
//import com.capstone.Expense_TrackerBackend.services.UserServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//    @RestController
//    @RequestMapping("/api/user")
//    public class UserController {
//
//        @Autowired
//        private UserServices userServices;
//
//        @PostMapping("/register")
//        public com.capstone.Expense_TrackerBackend.entity.User register(@RequestBody com.capstone.Expense_TrackerBackend.entity.User user){
//            return userServices.registerUser(user);
//        }
//
//
//    }
//package com.capstone.Expense_TrackerBackend.dto;
//
//import lombok.Data;
//
//    @Data
//    public class UserDTO {
//        private Long id;
//        private String username;
//        private String email;
//    }
//package com.capstone.Expense_TrackerBackend.repository;
//
//
//import com.capstone.Expense_TrackerBackend.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//    public interface UserRepo extends JpaRepository<com.capstone.Expense_TrackerBackend.entity.User, Long> {
//        Optional<com.capstone.Expense_TrackerBackend.entity.User> findByUsername(String username);
//        Optional<com.capstone.Expense_TrackerBackend.entity.User> findByEmail(String email);
//
//    }
//package com.capstone.Expense_TrackerBackend.services;
//
//
//import com.capstone.Expense_TrackerBackend.entity.User;
//import com.capstone.Expense_TrackerBackend.repository.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//    @Service
//    public class UserServiceImpl implements UserServices {
//
//        @Autowired
//        private com.capstone.Expense_TrackerBackend.repository.UserRepo userRepository;
//
//        public com.capstone.Expense_TrackerBackend.entity.User registerUser(com.capstone.Expense_TrackerBackend.entity.User user) {
//            return userRepository.save(user);
//        }
//
//        public Optional<com.capstone.Expense_TrackerBackend.entity.User> findByUsername(String username){
//            return userRepository.findByUsername(username);
//        }
//
//
//
//    }
//package com.capstone.Expense_TrackerBackend.services;
//
//
//import com.capstone.Expense_TrackerBackend.entity.User;
//
//import java.util.Optional;
//
//    public interface UserServices {
//
//        Optional<com.capstone.Expense_TrackerBackend.entity.User> findByUsername(String username);
//
//        com.capstone.Expense_TrackerBackend.entity.User registerUser(com.capstone.Expense_TrackerBackend.entity.User user);
//
//    }
//
//
//}
