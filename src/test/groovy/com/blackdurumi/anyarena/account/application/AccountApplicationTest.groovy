package com.blackdurumi.anyarena.account.application

import com.blackdurumi.anyarena.account.dto.SignInRequest
import com.blackdurumi.anyarena.account.dto.SignUpRequest
import com.blackdurumi.anyarena.account.entity.Account
import com.blackdurumi.anyarena.account.service.AccountService
import com.blackdurumi.anyarena.common.SecurityUtil
import spock.lang.Specification

class AccountApplicationTest extends Specification {
    AccountApplication sut

    AccountService accountService = Mock()
    SecurityUtil securityUtil = Mock()

    def setup() {
        sut = new AccountApplication(
                accountService,
                securityUtil,
        )
    }

    def accountId = 1L
    def identity = "blkc1113"
    def password = "kkk1113"
    def encryptedPassword = "encryptedPassword"
    def name = "blackdurumi"
    def phoneNumber = "010-1234-5678"
    def signUpRequest = SignUpRequest.builder()
            .identity(identity)
            .password(password)
            .name(name)
            .phoneNumber(phoneNumber)
            .build()
    def signInRequest = SignInRequest.builder()
            .identity(identity)
            .password(password)
            .build()
    def account = Account.builder()
            .accountId(accountId)
            .identity(identity)
            .encryptedPassword(encryptedPassword)
            .name(name)
            .phoneNumber(phoneNumber)
            .build()

    def "SignUp"() {
        when:
        def result = sut.signUp(signUpRequest)

        then:
        noExceptionThrown()
        1 * accountService.createAccount(signUpRequest) >> account
        result.getAccountId() == accountId
        result.getIdentity() == identity
        result.getEncryptedPassword() == encryptedPassword
        result.getName() == name
        result.getPhoneNumber() == phoneNumber
    }

    def "SignIn"() {
        when:
        def result = sut.signIn(signInRequest)

        then:
        noExceptionThrown()
        1 * accountService.findByIdentity(identity) >> account
        1 * securityUtil.encrypt(password) >> encryptedPassword
        result.accountId == accountId
        result.identity == identity
        result.encryptedPassword == encryptedPassword
        result.name == name
        result.phoneNumber == phoneNumber
    }

    def "SignIn - no result with identity"() {
        when:
        sut.signIn(signInRequest)

        then:
        thrown(RuntimeException)
        1 * accountService.findByIdentity(identity) >> { new RuntimeException() }
    }

    def "SignIn - password not match"() {
        when:
        sut.signIn(signInRequest)

        then:
        def e = thrown(RuntimeException)
        1 * accountService.findByIdentity(identity) >> account
        1 * securityUtil.encrypt(password) >> "notMatch"
        e.getMessage() == "Password not matched"
    }

    def "DeleteAccount"() {
        when:
        def result = sut.deleteAccount(accountId)

        then:
        noExceptionThrown()
        1 * accountService.deleteAccount(accountId) >> "success"
        result == "success"
    }
}
