package com.soma.lightening.board.service;

import com.soma.lightening.board.repository.AccountRepository;
import com.soma.lightening.entity.OAuth2Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    // git 로그인이라 회원가입이 없어서 중복 조회는 안하였는데 뭔가 필요할 것 같습니다..
    @Transactional
    public Long join(OAuth2Account account) {
        accountRepository.save(account);
        return account.getId();
    }
}
