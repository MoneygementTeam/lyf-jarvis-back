package com.moneygement.lyf.jarvis.user.domain;

import com.moneygement.lyf.jarvis.common.audit.BaseEntity;
import com.google.common.base.Preconditions;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id", length = 40)
    private String userId;

    @Column(name = "user_pw", length = 40)
    private String userPw;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "current_character_id")
    private Long currentCharacterId;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", length = 20)
    private LoginProvider provider;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", length = 10, nullable = false)
    private UserType userType;

    /**
     * 일반 사용자 생성
     */
    public static User create(String userId, String userPw) {
        User user = new User();
        user.userId = userId;
        user.userPw = userPw;
        user.userType = UserType.REGULAR;
        return user;
    }


    public static User createOauth(LoginProvider provider, String providerId, String nickname, String accessToken) {
        User user = new User();
        user.provider = provider;
        user.userId = providerId;
        user.nickname = nickname;
        user.accessToken = accessToken;
        user.userType = UserType.SOCIAL;
        return user;
    }

    /**
     * 비밀번호를 변경하는 메서드
     *
     * @param newPassword 새로운 비밀번호
     */
    public void changePassword(String newPassword) {
        Preconditions.checkNotNull(newPassword, "Password cannot be null");
        Preconditions.checkArgument(newPassword.length() >= 8, "Password must be at least 8 characters long");

        this.userPw = newPassword;
    }

    /**
     * 현재 캐릭터 ID를 변경하는 메서드
     *
     * @param characterId 새로운 캐릭터 ID
     */
    public void changeCurrentCharacter(Long characterId) {
        Preconditions.checkNotNull(characterId, "Character ID cannot be null");

        this.currentCharacterId = characterId;
    }

    /**
     * 토큰 값 변경하는 메서드
     * @param accessToken
     * @param refreshToken
     */
    public void update(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
