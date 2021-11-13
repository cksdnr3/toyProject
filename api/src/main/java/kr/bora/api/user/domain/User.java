package kr.bora.api.user.domain;

<<<<<<< HEAD
import kr.bora.api.todo.domain.Todo;
=======
import kr.bora.api.mailauth.AuthMail;
>>>>>>> cbbd8ad00491d604681c09ab10ad1826609f4849
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import javax.validation.constraints.Email;

@NoArgsConstructor
@Getter
@Entity
@Table(name="users")
public class User {
//notnull : username , password , phonenum, fisrnma,lastname,gender
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    //username은 이메일이다.
    @Column(name="username", nullable = false, length = 50)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "last_name", nullable = false, length = 10)
    private String lastName;

    @Column(name = "first_name", nullable = false,length = 30)
    private String firstName;

    @Column(name = "nick_name", nullable = true, length = 20)
    private String nickName;

    @Column(name = "phone_num", nullable = false, length = 13)
    private String phoneNum;

    @Column(name = "gender",nullable = false)
    private int gender;

    @Column(name="authority")
    @Enumerated(EnumType.STRING) // enum 문자열 자체가 저장(USER, ADMIN 등)
    private Authority authority;


<<<<<<< HEAD
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    @JoinColumn(name="todo_id")
    private Todo todo;


=======
>>>>>>> cbbd8ad00491d604681c09ab10ad1826609f4849
    @Builder
    public User(Long userId, String username, String password, String lastName, String firstName
        , String nickName, String phoneNum, int gender, Authority authority) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
        this.gender = gender;
        this.authority = authority;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}