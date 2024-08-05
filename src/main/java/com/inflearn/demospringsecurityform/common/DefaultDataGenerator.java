package com.inflearn.demospringsecurityform.common;

import com.inflearn.demospringsecurityform.account.Account;
import com.inflearn.demospringsecurityform.account.AccountService;
import com.inflearn.demospringsecurityform.book.Book;
import com.inflearn.demospringsecurityform.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataGenerator implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account dong = createUser("dong");
        Account hyun = createUser("hyun");

        createBook("spring", dong);
        createBook("hibernate", hyun);
    }

    private void createBook(String title, Account dong) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(dong);
        bookRepository.save(book);
    }

    private Account createUser(String username) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword("123");
        account.setRole("USER");

        return accountService.createNew(account);
    }
}
