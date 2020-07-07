package com.ascending.training.model;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
    @Table(name = "users")
    public class User
    {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "name")
        private String name;

        @Column(name = "password")
        private String password;

        @Column(name = "secret_key")
        private String secretKey;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "email")
        private String email;


        @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
        @JoinTable(name = "users_role",
                joinColumns =  {@JoinColumn(name = "user_id") },//owning side
                inverseJoinColumns = {@JoinColumn(name = "role_id")}//inverse side
        )
        private Set<Role> roles = new HashSet<>();//如果不实例化则addUser方法无法add



        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = DigestUtils.md5Hex(password.trim());

        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Set<Role> getRoles() {
            return roles;
        }

        public void setRoles(Set<Role> roles) {
            this.roles = roles;
        }


        public void addRole(Role role)
        {
            this.roles.add(role);
            role.getUsers().add(this);
        }// 确保在roles里添加role后   同时由于Many to Many的原因也要在role.getUsers()也就是users里
         // 里添加user！！！ 方便更新，这样以后只需要调用addRole方法就可以了

        public void removeRole(Role role)//remove同理addRole
        {
            this.roles.remove(role);
            role.getUsers().remove(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id &&
                    Objects.equals(name, user.name) &&
                    Objects.equals(password, user.password) &&
                    Objects.equals(secretKey, user.secretKey) &&
                    Objects.equals(firstName, user.firstName) &&
                    Objects.equals(lastName, user.lastName) &&
                    Objects.equals(email, user.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, password, secretKey, firstName, lastName, email);
        }
    }
