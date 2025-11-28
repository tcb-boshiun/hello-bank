package com.rick.backend.db.init;

import com.rick.backend.db.dao.UserRepository;
import com.rick.backend.db.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository repo;

    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            repo.save(create("A123456789", "Rick", "rick",
                    "@1111", "0912345678", "rick@mail.com"));
            repo.save(create("B987654321", "Amy", "amy",
                    "@2222", "0922345678", "amy@mail.com"));
            repo.save(create("F224466880", "John", "john",
                    "@3333", "0932345678", "john@mail.com"));
        }
    }

    private User create(String uid, String name, String act, String pwd, String phone, String mail) {
        User u = new User();
        u.setUserId(uid);
        u.setUserName(name);
        u.setUserAct(act);
        u.setUserPwd(pwd);
        u.setUserPhone(phone);
        u.setUserMail(mail);
        return u;
    }
}

