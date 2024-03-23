package com.blackdurumi.anyarena.account.service

import com.blackdurumi.anyarena.account.dao.AccountRepository
import com.blackdurumi.anyarena.account.dto.SignUpRequest
import com.blackdurumi.anyarena.account.entity.Account
import com.blackdurumi.anyarena.common.SecurityUtil
import spock.lang.Specification

class AccountServiceTest extends Specification {
    AccountService sut

    AccountRepository accountRepository = Mock()
    SecurityUtil securityUtil = Mock()

    def setup() {
        sut = new AccountService(
                accountRepository,
                securityUtil,
        )
    }

    def accountId = 1L
    def identity = "blkc1113"
    def password = "kkk1113"
    def encryptedPassword = "encryptedPassword"
    def name = "blackdurumi"
    def phoneNumber = "010-1234-5678"
    def request = SignUpRequest.builder()
            .identity(identity)
            .password(password)
            .name(name)
            .phoneNumber(phoneNumber)
            .build()
    def account = Account.builder()
            .accountId(accountId)
            .identity(identity)
            .encryptedPassword(encryptedPassword)
            .name(name)
            .phoneNumber(phoneNumber)
            .build()

    def "CreateAccount"() {
        when:
        def result = sut.createAccount(request)

        then:
        noExceptionThrown()
        1 * securityUtil.encrypt(password) >> encryptedPassword
        1 * accountRepository.save(_) >> account
        result.identity == identity
        result.encryptedPassword == encryptedPassword
        result.name == name
        result.phoneNumber == phoneNumber
    }

    def "CreateAccount - failed to encrypt password"() {
        when:
        sut.createAccount(request)

        then:
        thrown(RuntimeException)
        1 * securityUtil.encrypt(password) >> { throw new RuntimeException() }
        0 * accountRepository.save(_)
    }

    def "FindByIdentity"() {
        when:
        def result = sut.findByIdentity(identity)

        then:
        noExceptionThrown()
        1 * accountRepository.findByIdentity(identity) >> Optional.of(account)
        result.accountId == accountId
        result.identity == identity
        result.encryptedPassword == encryptedPassword
        result.name == name
        result.phoneNumber == phoneNumber
    }

    def "FindByIdentity - no result"() {
        given:

        when:
        def result = sut.findByIdentity(identity)

        then:
        def e = thrown(RuntimeException)
        1 * accountRepository.findByIdentity(identity) >> Optional.empty()
        e.message == "Account not found"
    }

    def "GetById"() {
        when:
        def result = sut.getById(accountId)

        then:
        noExceptionThrown()
        1 * accountRepository.findById(accountId) >> Optional.of(account)
        result.accountId == accountId
        result.identity == identity
        result.encryptedPassword == encryptedPassword
        result.name == name
        result.phoneNumber == phoneNumber
    }

    def "GetById - no result"() {
        when:
        sut.getById(accountId)

        then:
        def e = thrown(RuntimeException)
        1 * accountRepository.findById(accountId) >> Optional.empty()
        e.getMessage() == "Account not found"
    }

    def "DeleteAccount"() {
        when:
        def result = sut.deleteAccount(accountId)

        then:
        noExceptionThrown()
        1 * accountRepository.findById(accountId) >> Optional.of(account)
        1 * accountRepository.delete(account)
        result == "success to delete account with accountId: " + accountId;
    }

    def "DeleteAccount - no account"() {
        when:
        def result = sut.deleteAccount(accountId)

        then:
        def e = thrown(RuntimeException)
        1 * accountRepository.findById(accountId) >> Optional.empty()
        0 * accountRepository.delete(_)
        e.getMessage() == "Account not found"
    }
}
